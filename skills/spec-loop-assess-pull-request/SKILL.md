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

Follow [review-guidance.md](review-guidance.md) for trust
handling, evidence collection, review structure, diagram rules, and
sharing-variant behavior.

Before work:

- read [spec-loop-plan-task/SKILL.md](../spec-loop-plan-task/SKILL.md) for shared planning,
  glossary, and phase terminology;
- read [spec-loop-plan-task/common-task-guidance.md](../spec-loop-plan-task/common-task-guidance.md) for shared
  artifact structure, formatting, glossary, and section semantics
  reused by retrospective review files;
- read [spec-loop-plan-task/task-file-path-guidance.md](../spec-loop-plan-task/task-file-path-guidance.md) for
  diagram conventions and any other task-file-specific rules that
  clearly fit retrospective review artifacts;
- apply those shared conventions where they fit retrospective review
  work, but do not apply the chat-only planning path, task-file
  lifecycle routing, or the normal `PLAN -> EXECUTION` approval
  gate to the reviewed change itself; and
- then read [review-guidance.md](review-guidance.md).

Reconstruct already-implemented work as the retrospective Spec Loop
review artifact that should have existed, then add AI assessment and
recommendations. Do not treat the change as waiting at the normal
`PLAN -> EXECUTION` gate.

Use read-only provider commands for evidence when available:
- `gh` for GitHub
- `glab` for GitLab, including self-hosted GitLab when available

When trust, provider, evidence source, or comparison range is unclear,
stop and ask.
