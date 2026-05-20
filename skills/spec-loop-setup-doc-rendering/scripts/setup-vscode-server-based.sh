#!/usr/bin/env bash
set -euo pipefail

SCRIPT_NAME="$(basename "$0")"
REQUIRED_EXTENSIONS=(
  "myml.vscode-markdown-plantuml-preview"
  "asciidoctor.asciidoctor-vscode"
)

usage() {
  cat <<EOF
Usage: $SCRIPT_NAME [--check|--apply|--help]

Check or apply the Spec Loop VS Code server-based rendering setup.

Requirements:
- A VS Code CLI command on PATH: code, code-insiders,
  code.cmd, or code-insiders.cmd

This helper is intended for macOS, Linux, WSL, and Git Bash for Windows.

Actions:
- --check  Detect the VS Code CLI and report whether the required
           extensions are installed. This is the default.
- --apply  Install any missing required extensions.
- --help   Show this help.

This helper covers only the server-based VS Code path. It does not:
- configure local PlantUML rendering,
- automate JetBrains IDE setup, or
- edit optional VS Code settings such as markdown.plantuml.server.
EOF
}

find_vscode_command() {
  local candidate
  for candidate in code code-insiders code.cmd code-insiders.cmd; do
    if command -v "$candidate" >/dev/null 2>&1; then
      printf '%s\n' "$candidate"
      return 0
    fi
  done
  return 1
}

list_extensions() {
  local output
  if ! output="$("$VSCODE_CMD" --list-extensions 2>&1)"; then
    printf 'Error: Failed to query VS Code extensions with "%s --list-extensions".\n' "$VSCODE_CMD" >&2
    printf '%s\n' "$output" >&2
    exit 1
  fi
  output=${output//$'\r'/}
  printf '%s\n' "$output"
}

extension_is_installed() {
  local extension="$1"
  printf '%s\n' "$EXTENSIONS_OUTPUT" | grep -Fxq "$extension"
}

collect_missing_extensions() {
  MISSING_EXTENSIONS=()
  local extension
  for extension in "${REQUIRED_EXTENSIONS[@]}"; do
    if ! extension_is_installed "$extension"; then
      MISSING_EXTENSIONS+=("$extension")
    fi
  done
}

print_status() {
  local extension
  printf 'VS Code CLI: %s\n' "$VSCODE_CMD"
  for extension in "${REQUIRED_EXTENSIONS[@]}"; do
    if extension_is_installed "$extension"; then
      printf 'OK: %s\n' "$extension"
    else
      printf 'MISSING: %s\n' "$extension"
    fi
  done
}

install_missing_extensions() {
  local extension
  for extension in "${MISSING_EXTENSIONS[@]}"; do
    printf 'Installing: %s\n' "$extension"
    "$VSCODE_CMD" --install-extension "$extension"
  done
}

MODE="--check"
if [[ $# -gt 1 ]]; then
  usage >&2
  exit 2
elif [[ $# -eq 1 ]]; then
  MODE="$1"
fi

case "$MODE" in
  --check|--apply)
    ;;
  --help|-h)
    usage
    exit 0
    ;;
  *)
    usage >&2
    exit 2
    ;;
esac

if ! VSCODE_CMD="$(find_vscode_command)"; then
  printf 'Error: No VS Code CLI was found on PATH. Expected one of: code, code-insiders, code.cmd, code-insiders.cmd.\n' >&2
  exit 1
fi

EXTENSIONS_OUTPUT="$(list_extensions)"
collect_missing_extensions
print_status

if [[ "$MODE" == "--check" ]]; then
  if [[ ${#MISSING_EXTENSIONS[@]} -eq 0 ]]; then
    printf 'Ready: server-based VS Code rendering support is installed.\n'
    exit 0
  fi
  printf 'Not ready: install the missing extensions or rerun with --apply.\n' >&2
  exit 1
fi

if [[ ${#MISSING_EXTENSIONS[@]} -eq 0 ]]; then
  printf 'No changes needed.\n'
  exit 0
fi

install_missing_extensions
EXTENSIONS_OUTPUT="$(list_extensions)"
collect_missing_extensions
print_status

if [[ ${#MISSING_EXTENSIONS[@]} -eq 0 ]]; then
  printf 'Done: server-based VS Code rendering support is installed.\n'
  exit 0
fi

printf 'Error: Some required extensions are still missing after installation.\n' >&2
exit 1
