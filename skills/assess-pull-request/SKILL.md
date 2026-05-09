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

Before work: read `../plan-task/SKILL.md`, follow all files it requires, apply full `plan-task` 
skill bundle as shared convention guidance. Then read [review-guidance.md](./review-guidance.md).

`review-guidance.md` = authoritative source for review purpose, evidence, file structure, 
section semantics, `Review Area` behavior, assessment style, intent-vs-implementation analysis, 
tone, translation rules, diagram rules, sharing variant behavior. 
`SKILL.md` = orchestration/entry-point only, not second guidance doc.

Skill reconstructs already-implemented work as detailed human review-prep artifact. 
Reconstruct as if assembling task file that should have existed under `plan-task`, 
then layer AI assessment and recommendations on top. Change already implemented — 
not waiting at PLAN -> IMPLEMENTATION gate.

Use read-only provider commands for evidence when available:

- `gh` for GitHub
- `glab` for GitLab, including self-hosted GitLab when available

Detect provider from explicit review reference first. If unclear, inspect repo origin/remote host. 
If provider, evidence source, or comparison range still unclear, stop and ask.

Write local review artifacts under `reviews/`.

If user wants provider-specific sharing variant, follow `review-guidance.md` 
to decide whether local sibling variant needed or canonical artifact reusable directly. 
Posting to provider outside this skill.