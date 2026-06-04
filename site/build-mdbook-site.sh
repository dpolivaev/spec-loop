#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)
REPO_ROOT=$(cd -- "$SCRIPT_DIR/.." && pwd)
DEFAULT_OUTPUT_DIR="$REPO_ROOT/build/gh-pages"
OUTPUT_DIR=${1:-$DEFAULT_OUTPUT_DIR}
OUTPUT_DIR=$(python3 -c 'import os, sys; print(os.path.abspath(sys.argv[1]))' "$OUTPUT_DIR")
REPO_URL=${GITHUB_REPO_URL:-https://github.com/dpolivaev/spec-loop}
REPO_REF=${SITE_GIT_REF:-$(git -C "$REPO_ROOT" rev-parse --abbrev-ref HEAD 2>/dev/null || true)}

if [[ -z "$REPO_REF" || "$REPO_REF" == "HEAD" ]]; then
  REPO_REF=main
fi

for tool in mdbook mdbook-toc python3; do
  if ! command -v "$tool" >/dev/null 2>&1; then
    echo "Missing required tool: $tool" >&2
    exit 1
  fi
done

WORKSPACE=$(mktemp -d "${TMPDIR:-/tmp}/spec-loop-site.XXXXXX")
cleanup() {
  if [[ ${KEEP_SITE_WORKSPACE:-0} == 1 ]]; then
    echo "Temporary workspace kept at: $WORKSPACE"
    return
  fi
  rm -rf "$WORKSPACE"
}
trap cleanup EXIT

mkdir -p "$WORKSPACE/src" "$(dirname -- "$OUTPUT_DIR")"
cp "$SCRIPT_DIR/book.toml" "$WORKSPACE/book.toml"
cp "$SCRIPT_DIR/src/SUMMARY.md" "$WORKSPACE/src/SUMMARY.md"
cp "$REPO_ROOT/LICENSE.md" "$WORKSPACE/src/LICENSE.md"

REPO_ROOT="$REPO_ROOT" WORKSPACE="$WORKSPACE" REPO_URL="$REPO_URL" REPO_REF="$REPO_REF" python3 <<'PY'
from __future__ import annotations

import os
import posixpath
import re
import shutil
from pathlib import Path
from urllib.parse import urlsplit, urlunsplit

repo_root = Path(os.environ["REPO_ROOT"])
workspace = Path(os.environ["WORKSPACE"])
repo_url = os.environ["REPO_URL"].rstrip("/")
repo_ref = os.environ["REPO_REF"]

FENCE_RE = re.compile(r"(^```.*?^```[ \t]*$)", re.MULTILINE | re.DOTALL)
MARKDOWN_LINK_RE = re.compile(r"(!?\[[^\]]*\])\(([^)\n]+)\)")
HTML_LINK_RE = re.compile(r"(?P<prefix>\b(?:href|src)=['\"])(?P<url>[^'\"]+)(?P<suffix>['\"])")


def split_target(target: str) -> tuple[str, str]:
    if target.startswith("<") and target.endswith(">"):
        return target[1:-1], "<>"
    return target, ""


def join_target(target: str, wrapper: str) -> str:
    if wrapper == "<>":
        return f"<{target}>"
    return target


def is_external(target: str) -> bool:
    parts = urlsplit(target)
    return bool(parts.scheme or parts.netloc)


def normalize_repo_path(raw_path: str, base_repo_path: str) -> str:
    if raw_path.startswith("/"):
        normalized = posixpath.normpath(raw_path.lstrip("/"))
    else:
        normalized = posixpath.normpath(
            posixpath.join(posixpath.dirname(base_repo_path), raw_path)
        )
    return "" if normalized == "." else normalized


def published_repo_path(repo_path: str) -> str | None:
    if repo_path == "README.md":
        return "index.md"
    if repo_path.startswith("docs/"):
        return repo_path
    return None


def github_repo_url(repo_path: str, fragment: str) -> str:
    target_path = repo_root / repo_path
    route = "tree" if target_path.is_dir() else "blob"
    url = f"{repo_url}/{route}/{repo_ref}/{repo_path}"
    if fragment:
        return f"{url}#{fragment}"
    return url


def staged_relative_path(base_repo_path: str, published_path: str) -> str:
    base_staged_path = "index.md" if base_repo_path == "README.md" else base_repo_path
    relative = posixpath.relpath(published_path, posixpath.dirname(base_staged_path) or ".")
    return relative


def transform_link(target: str, base_repo_path: str) -> str:
    raw_target, wrapper = split_target(target)
    if raw_target.startswith("#") or is_external(raw_target):
        return target

    parts = urlsplit(raw_target)
    if not parts.path:
        return target

    repo_path = normalize_repo_path(parts.path, base_repo_path)
    published_path = published_repo_path(repo_path)

    if published_path is not None:
        staged_path = staged_relative_path(base_repo_path, published_path)
        rewritten = urlunsplit(("", "", staged_path, parts.query, parts.fragment))
        return join_target(rewritten, wrapper)

    rewritten = github_repo_url(repo_path, parts.fragment)
    return join_target(rewritten, wrapper)


def rewrite_non_code(text: str, base_repo_path: str) -> str:
    text = HTML_LINK_RE.sub(
        lambda match: (
            f"{match.group('prefix')}"
            f"{transform_link(match.group('url'), base_repo_path)}"
            f"{match.group('suffix')}"
        ),
        text,
    )
    text = MARKDOWN_LINK_RE.sub(
        lambda match: f"{match.group(1)}({transform_link(match.group(2), base_repo_path)})",
        text,
    )
    return text


def rewrite_markdown(text: str, base_repo_path: str) -> str:
    parts = FENCE_RE.split(text)
    for index in range(0, len(parts), 2):
        parts[index] = rewrite_non_code(parts[index], base_repo_path)
    return "".join(parts)


def write_transformed_markdown(source_path: Path, target_path: Path, base_repo_path: str) -> None:
    target_path.parent.mkdir(parents=True, exist_ok=True)
    text = source_path.read_text()
    target_path.write_text(rewrite_markdown(text, base_repo_path))


write_transformed_markdown(
    repo_root / "README.md",
    workspace / "src" / "index.md",
    "README.md",
)

for source_path in sorted((repo_root / "docs").rglob("*")):
    if source_path.is_dir():
        continue
    relative_repo_path = source_path.relative_to(repo_root).as_posix()
    if any(part.startswith(".") for part in source_path.relative_to(repo_root).parts):
        continue

    target_path = workspace / "src" / relative_repo_path
    target_path.parent.mkdir(parents=True, exist_ok=True)

    if source_path.suffix == ".md":
        write_transformed_markdown(source_path, target_path, relative_repo_path)
    else:
        shutil.copy2(source_path, target_path)
PY

rm -rf "$OUTPUT_DIR"
mkdir -p "$OUTPUT_DIR"
mdbook build "$WORKSPACE" --dest-dir "$OUTPUT_DIR"

echo "Built site at $OUTPUT_DIR"
