---
name: assess-pull-request
description: >-
  Reconstruct a review file from an existing pull request, merge
  request, branch diff, or commit range. Use when the user wants
  retrospective review of already-implemented changes, including an
  optional provider-specific sharing variant written to a local file
  when needed.
---

Use for retrospective review of an existing pull request, merge
request, branch diff, or commit range.

Before doing that work, read `../plan-task/SKILL.md`, follow all files
it requires you to read, and apply the full `plan-task` skill bundle as
shared convention guidance, including its glossary policy, diagram
rules, and supporting examples. Then read
[review-guidance.md](./review-guidance.md) and apply its
retrospective review guidance and overrides.

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

If the user asks for a provider-specific sharing variant and the target
provider cannot use the canonical review artifact directly, write or
update a local sibling variant. If no explicit review-diagram mode is
configured, derive the effective local diagram mode from the project's
default plus the detected provider first. If the canonical review
already matches the target provider's rendering support, or it has no
diagrams, reuse it directly and do not generate a separate variant.

Posting that content to the provider is outside this skill.
