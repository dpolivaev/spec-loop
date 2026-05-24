---
name: spec-loop-assess-pull-request
description: >-
  Optional skill. Reconstruct a human-review-preparation file from an
  existing pull request, merge request, branch diff, or commit range
  in a repository the user trusts. Use when the user wants
  retrospective understanding of already-implemented changes, AI-side
  assessment and recommendations, and an optional provider-specific
  sharing variant written to a local file when needed.
---

Use this skill only for pull requests, merge requests, branch diffs,
or commit ranges from repositories the user trusts. This skill is
optional and is not required for the core Spec Loop planning workflow.
If trust is unclear, stop and ask before fetching provider or Git
content.

Follow `review-guidance.md` for the full trust-boundary and
prompt-injection-handling rules.

Before work:

- read `../spec-loop-plan-task/SKILL.md` for shared planning,
  glossary, and phase terminology;
- read `../spec-loop-plan-task/shared-task-semantics.md` for shared
  artifact structure, formatting, glossary, and section semantics
  reused by retrospective review files;
- read `../spec-loop-plan-task/task-file-constitution.md` for
  task-file-only diagram conventions and any other task-file-specific
  rules that clearly fit retrospective review artifacts;
- apply those shared conventions where they fit retrospective review
  work, but do not apply the fileless planning path, task-file
  lifecycle routing, or the normal `PLAN -> IMPLEMENTATION` approval
  gate to the reviewed change itself;
- then read [review-guidance.md](./review-guidance.md).

Optional compact example:
[examples/example-review-settings-loader.md](./examples/example-review-settings-loader.md).
Use it as a pattern collection for structure, area-wise outcome synthesis, and reconstructive
`Test specification`, not as a required review size.

`review-guidance.md` = authoritative source for review purpose, evidence, file structure, 
section semantics, `Review outcome` labels, `Review Area` behavior, assessment style, 
intent-vs-implementation analysis, tone, translation rules, diagram rules, sharing variant behavior. 
`SKILL.md` = orchestration/entry-point only, not second guidance doc.

Reconstruct already-implemented work as the retrospective Spec Loop
review artifact that should have existed, then add AI assessment and
recommendations.
Do not treat the change as waiting at the normal PLAN -> IMPLEMENTATION
gate.

Use read-only provider commands for evidence when available:

- `gh` for GitHub
- `glab` for GitLab, including self-hosted GitLab when available

Detect provider from explicit review reference first. If unclear, inspect repo origin/remote host. 
If provider, evidence source, or comparison range still unclear, stop and ask.

Write local review artifacts under `reviews/`.

If the user wants a provider-specific sharing variant, follow `review-guidance.md`
to decide whether a local sibling variant is needed or whether the canonical artifact can be reused directly.
Posting to the provider is outside this skill.
