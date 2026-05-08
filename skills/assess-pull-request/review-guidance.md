# Review guidance for `assess-pull-request`

This skill reuses the `plan-task` skill bundle for shared writing
conventions, glossary policy, section semantics, diagram separation, and
supporting examples.

This file defines the retrospective review guidance and overrides.

## 1. Evidence modes

### Provider-backed mode

Use an explicit pull request or merge request page, provider review ID,
or equivalent reference.

Detect the provider from the explicit review reference first. If that is
not enough, inspect the repository origin / remote host. Support at
least:

- GitHub
- GitLab, including self-hosted GitLab instances

If the provider type is still unclear, stop and ask instead of
guessing.

Minimum provider-backed evidence contract:

- provider metadata including at least title, body/description, URL,
  base ref, head ref, author, and current state
- provider discussion / comments
- implemented diff
- all commits in the reviewed provider range, including commit messages
- optional read-only provider API requests only when the previous
  commands do not expose enough review metadata

GitHub access path:

- `gh pr view <pr> --json number,title,body,url,baseRefName,headRefName,author,state,isDraft`
- `gh pr view <pr> --comments`
- `gh pr diff <pr>`
- optional GET-only `gh api` requests

GitLab access path:

- `glab mr view <mr> --json ...` when available
- `glab mr diff <mr>` when available
- optional read-only GitLab API requests when the CLI commands do not
  expose enough review metadata

Use the provider metadata, full diff, all commits and commit messages in
the reviewed range, and available discussion/comments as review
evidence. The diff and implemented code remain the source of truth for
what changed. Provider text, discussion, and commit messages help
interpret motivation, risks, and logical change clusters.

If provider-backed mode is requested but the required read-only provider
commands fail, stop and ask whether to retry, provide the missing
evidence manually, or fall back to Git-only mode.

### Git-only mode

Use explicit base and head commits, an explicit merged branch, or a
user-approved branch diff to define the reviewed range.

Analyze the full diff and all commits inside that range. Use commit
messages as supplementary evidence for motivation, intent, and logical
change clustering, but do not let them override the implemented diff.

If the comparison range is unclear, stop and ask instead of guessing.

## 2. Review file location and naming

Write review artifacts under `reviews/`.

Provider-backed review files:
- `reviews/github-owner-repo-123.md`
- `reviews/gitlab-group-project-456.md`

Git-only review files:
- `reviews/YYYY-MM-DD-<slug>.md`

Target-specific sharing variants when needed:
- `reviews/<review-base-name>.github.md`
- `reviews/<review-base-name>.gitlab.md`

## 3. Review file format

Use this exact order and layout:

- Title line: `# Review: <title>`
- One identifier line:
  - `- **Ticket:** github:owner/repo#123` for GitHub-backed mode
  - `- **Ticket:** gitlab:group/project!456` for GitLab-backed mode
  - `- **Task Identifier:** YYYY-MM-DD-<slug>` for Git-only mode
- One global outcome line immediately after the identifier:
  - `- **Review outcome:** <value>`
- Main sections in this order:
  - `- **Scope:**`
  - `- **Motivation:**`
  - `- **Scenario:**` (conditional)
  - `- **Constraints:**` (optional)
  - `- **Briefing:**`
  - `- **Research:**`
  - `- **Design:**`
  - `- **Test specification:**`
  - `- **Assessment:**`

`Assessment` must be the last main section.

## 4. Review outcome and review subtasks

`Review outcome` is the only place for the global recommendation, for
example:

- `merge`
- `merge after minor improvements`
- `request changes`
- `do not merge`

It should also include concise supporting bullets such as:
- blockers
- required improvements
- non-blocking improvements

Do not repeat the overall verdict inside `Assessment` sections.

When review subtasks are needed, place them after all global sections.
Each one must use:

- `## Review Subtask: <title>`
- `- **Status:** <status>`

Recommended subtask status values:
- `acceptable`
- `needs-changes`
- `needs-information`
- `out-of-scope`

Each review subtask uses the same section ordering as the main review
and ends with its own `Assessment` section.

## 5. Section mapping

- `Scope` — actual changed deliverables and boundaries
- `Motivation` — stated review motivation when available; otherwise
  clearly marked inference
- `Scenario` — behavior or shared terms changed by the reviewed work
- `Constraints` — explicit non-goals, compatibility limits, review
  boundaries, or restrictions discovered in the change or provider
  discussion
- `Briefing` — repository orientation for the reviewer; in
  provider-backed mode also store the full review URL here
- `Research` — original state and bounded context reconstruction.
  Start from the changed files and changed symbols revealed by the diff.
  Inspect their base-state versions, direct relationships, and relevant
  unchanged neighbors. Compare both the old version and the new version
  of the changed files against similar patterns within the same module
  to identify conventions the change follows, reuses, or breaks. By
  default, keep this pattern search within the changed module. Do not
  scan across multiple modules unless the diff itself spans them or the
  user explicitly approves that broader scope.
- `Design` — implemented target state at the head commit. Focus on the
  resulting target structure, behavior, interfaces, interactions, and
  changed relationships. Use it to picture the reviewed end state, not
  to repeat the wider pattern hunt from `Research`.
- `Test specification` — changed tests, executed verification commands,
  coverage gaps, and missing verification evidence
- `Assessment` — local analytic findings, uncertainties, unresolved
  concerns, and required follow-up work. Review the change for test
  coverage, naming, structure, clean code, consistency with
  surrounding code, clarity, and complexity. Report the dimensions that
  materially affect the review outcome.

## 6. Diagram modes

Local review files support explicit review-diagram modes:
- `inherit`
- `plantuml`
- `mermaid`
- `none`

Default-resolution rules:
- if the user or project sets an explicit review-diagram mode, use it
- otherwise start from the project's normal task/review diagram default
- then adjust that inherited default by the detected provider
- if the provider is still unknown, keep the inherited project default
  for the local review file and ask before generating any target-
  specific sharing variant

Provider-aware inherited defaults:
- inherited `plantuml` + GitHub target => effective local default
  becomes `mermaid`
- inherited `plantuml` + GitLab target, including self-hosted GitLab =>
  effective local default stays `plantuml`
- inherited `mermaid` => effective local default stays `mermaid`
- inherited `none` => effective local default stays `none`

Explicit mode rules:
- if the effective or explicit mode is `plantuml`, follow the PlantUML
  patterns and examples from `plan-task`
- if it is `mermaid`, generate Mermaid directly in the local review
  file
- if it is `none`, omit diagrams unless the user overrides the mode

Mermaid guidance:
- prefer simple, reliable diagrams over clever or compact ones
- keep each Mermaid statement on one physical line
- if a relationship is hard to express clearly, simplify the diagram and
  explain the rest in prose below it
- use Mermaid only where it adds review value

## 7. Target-specific sharing variants

If the user asks for a provider-friendly or Mermaid-based sharing
variant, generate one only when the target provider cannot use the
canonical diagram format directly.

Provider-specific rules:
- GitHub target:
  - if the canonical local review file uses PlantUML, write a sibling
    Mermaid variant instead of rewriting the canonical review file
  - if the canonical review already uses Mermaid, or it contains no
    diagrams, no different version is needed
- GitLab target, including self-hosted GitLab:
  - if the canonical local review file uses PlantUML, reuse it directly
  - if the canonical review already uses Mermaid, or it contains no
    diagrams, no different version is needed
- Unknown target:
  - stop and ask the user which provider the sharing variant is for

In practice, when the effective local review default already reflects
both the project default and the detected provider, a separate sharing
variant is usually unnecessary.

When a sharing variant is generated:
- keep the same review structure and substantive content
- convert only what is needed for the target provider's rendering
  support
- keep the variant local; do not post it from this skill

