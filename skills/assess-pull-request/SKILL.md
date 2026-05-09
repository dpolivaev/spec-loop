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

Use for retrospective review of existing PR, MR, branch diff, or commit range.

Before work: read `../plan-task/SKILL.md`, follow all files it requires, and apply the full `plan-task`
skill bundle as shared convention guidance. Then read [review-guidance.md](./review-guidance.md).
Optional compact example:
[examples/example-review-settings-loader.md](./examples/example-review-settings-loader.md).
Use it as a pattern collection for structure, area-wise outcome synthesis, and reconstructive
`Test specification`, not as a required review size.

`review-guidance.md` = authoritative source for review purpose, evidence, file structure, 
section semantics, `Review Area` behavior, assessment style, intent-vs-implementation analysis, 
tone, translation rules, diagram rules, sharing variant behavior. 
`SKILL.md` = orchestration/entry-point only, not second guidance doc.

Reconstruct already-implemented work as the retrospective `plan-task`-style review artifact that
should have existed, then add AI assessment and recommendations.
Do not treat the change as waiting at the normal PLAN -> IMPLEMENTATION gate.

Use read-only provider commands for evidence when available:

- `gh` for GitHub
- `glab` for GitLab, including self-hosted GitLab when available

Detect provider from explicit review reference first. If unclear, inspect repo origin/remote host. 
If provider, evidence source, or comparison range still unclear, stop and ask.

Write local review artifacts under `reviews/`.

If the user wants a provider-specific sharing variant, follow `review-guidance.md`
to decide whether a local sibling variant is needed or whether the canonical artifact can be reused directly.
Posting to the provider is outside this skill.
