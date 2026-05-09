---
name: assess-pull-request
description: >-
  Reconstruct a human-review-preparation file from an existing pull
  request, merge request, branch diff, or commit range. Use when the
  user wants retrospective understanding of already-implemented
  changes, AI-side assessment and recommendations, and an optional
  provider-specific sharing variant written to a local file when
  needed.
---

Use for retrospective review of an existing pull request, merge
request, branch diff, or commit range.

Before doing that work, read `../plan-task/SKILL.md`, follow all files
it requires you to read, and apply the full `plan-task` skill bundle as
shared convention guidance, including its glossary policy, diagram
rules, and supporting examples. Then read
[review-guidance.md](./review-guidance.md).

Treat `review-guidance.md` as the authoritative source for review
purpose, evidence handling, file structure, section semantics,
`Review Area` behavior, assessment style, intent-versus-
implementation analysis, tone, translation rules, diagram rules, and
sharing variant behavior. Keep `SKILL.md` as orchestration and
entry-point instruction, not as a second guidance document.

This skill reconstructs already-implemented work as a detailed human
review-preparation artifact. Reconstruct the reviewed change as if you
were retrospectively assembling the task file that should have existed
under `plan-task`, then layer AI-side assessment and recommendations on
top of that reconstruction.

This skill reconstructs already-implemented work. Do not treat the
change as waiting at the normal PLAN -> IMPLEMENTATION gate.

Use read-only provider commands for review-backed evidence when
available:

- `gh` for GitHub
- `glab` for GitLab, including self-hosted GitLab when available

Detect the provider from the explicit review reference first. If that
is not enough, inspect the repository origin / remote host. If the
provider, evidence source, or comparison range is still unclear, stop
and ask.

Write local review artifacts under `reviews/`.

If the user asks for a provider-specific sharing variant, follow
`review-guidance.md` to decide whether a local sibling variant is
needed or whether the canonical review artifact can be reused directly.

Posting that content to the provider is outside this skill.
