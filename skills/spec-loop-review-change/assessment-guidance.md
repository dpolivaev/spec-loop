# Assessment Guidance

This file defines the high-level assessment document.

It covers:

- the minimum evidence for this mode;
- the document structure;
- verdict handling;
- review areas;
- section meanings;
- diagrams for this mode; and
- GitHub-ready or GitLab-ready copies.

Shared trust, naming, and writing-style rules stay in
[review-core-guidance.md](./review-core-guidance.md).

Before drafting this kind of review:

- read [spec-loop-plan-task/SKILL.md](../spec-loop-plan-task/SKILL.md) for shared planning,
  glossary, and phase terminology that this review may reuse;
- read [spec-loop-plan-task/common-task-guidance.md](../spec-loop-plan-task/common-task-guidance.md) for shared
  section formatting, glossary expectations, and testing-policy shape
  reused by this review; and
- read [spec-loop-plan-task/task-file-path-guidance.md](../spec-loop-plan-task/task-file-path-guidance.md) only if
  this review needs diagrams or another task-file convention reused
  from there.

Reuse only the conventions this review actually needs.
Other Spec Loop workflow rules do not matter when creating the review
document itself.

## Purpose

Produce a human review document, not a terse verdict.

Reconstruct the reviewed change as the review document that should have
existed while the change was being implemented, then add analysis and
recommendations.

When the user does not want a merge verdict, omit the global
`Review outcome` line and keep recommendations inside `Assessment`.

## Minimum evidence for this mode

For a GitHub or GitLab pull request or merge request review, normally
collect:

- title, description, URL, base ref, head ref, author, and current
  state;
- discussion comments;
- the implemented diff; and
- all commits in the reviewed range, including commit messages.

For a branch-diff or commit-range review, normally collect:

- the full diff for the selected range; and
- all commits in the range when they exist.

For a local branch review without an explicit base commit or range,
reconstruct the branch change as the net diff against the intended
target branch.
Prefer the remote-tracking target branch when it exists locally;
otherwise use the corresponding local target branch.
Use three dots for this diff.

For a local uncommitted-change review without commits or pull request
context:

- reconstruct motivation from the current diff, confirmed user
  statements, and nearby code; and
- mark inferred motivation as inference.

If required evidence for this mode is unavailable, either:

- ask for it;
- narrow the review claims to what the evidence supports; or
- fall back to a pure walk-through if the user only needs code review.

## Document structure

Use this structure unless the user explicitly requests another one:

- Title: `# Review: <title>`
- One identifier line:
  - `- **Ticket:** github:owner/repo#123` for a GitHub review;
  - `- **Ticket:** gitlab:group/project!456` for a GitLab review; or
  - `- **Task Identifier:** <base-name>` for a branch-diff,
    commit-range, or local review.
- One global outcome line immediately after the identifier when a
  verdict is requested:
  - `- **Review outcome:** <value>`
- Main sections in order:
  - `- **Scope:**`
  - `- **Motivation:**`
  - `- **Scenario:**` (conditional)
  - `- **Constraints:**` (optional)
  - `- **Briefing:**`
  - `- **Research:**`
  - `- **Design:**`
  - `- **Test specification:**`
  - `- **Assessment:**`

`Assessment` must be the last main section of the main review portion.
If a combined file also includes a walk-through, place the walk-through
after the assessment under a clear section such as
`## Detailed Walk-Through`.

## Review outcome

`Review outcome` is the only place for a global merge recommendation.
Use it only when the user asked for a verdict.

Allowed values:

- `merge`
- `merge after minor improvements`
- `request changes`
- `do not merge`

When `Review outcome` is present, include concise supporting bullets:

- `Intent`
- `Implementation`
- `Verification`
- `Complexity`
- blockers
- required improvements
- non-blocking improvements
- net benefits or complexity reductions
- net costs or complexity increases
- summary by review area
- whether major parts should be kept, simplified, split, deferred, or
  dropped
- when recommending split: the suggested first follow-up slice

Keep those four bullet types separate.
Do not mix `Intent`, `Implementation`, `Verification`, and `Complexity`
inside one bullet.
Do not repeat the global verdict inside `Assessment`.

The assessment document should show only current-state findings.
When a blocker, required improvement, non-blocking improvement, open
question, or other review finding is fixed and the fix is validated,
delete it from the review document and update the surrounding verdict
or assessment text to match.
If a finding is accepted, deferred, or intentionally left unfixed,
keep it in the review document and update its wording only as needed to
reflect that current state.

### Axis discipline

Use the shared axis names with these stricter meanings in assessment
mode:

- `Intent` — whether the change or review area should exist at all,
  and in roughly this form;
- `Implementation` — whether the chosen change is realized correctly,
  coherently, completely, and safely, including wiring and
  integration;
- `Verification` — merge confidence from test evidence and other
  direct evidence against the reconstructed `Test specification`; and
- `Complexity` — enduring structural, operational, or maintenance
  burden only. Distinguish justified complexity from accidental
  complexity.

Do not mix the axes inside one bullet.
Do not manufacture symmetry or offsetting pros and cons the evidence
does not support.
Do not use ordinary correctness defects or missing tests as complexity
arguments unless they create lasting burden after merge.
When findings differ across review areas, synthesize them explicitly.
Say which areas are worth keeping, which should be narrowed, and which
should be deferred or dropped.

## Review areas

For substantial or multi-area changes, review areas are expected.
Start from the canonical review blocks defined for the change.
Use the same blocks as review areas by default.
If the high-level document reads better with broader groups, merge
several related review blocks into one review area.
Do not create a conflicting file grouping.
Small single-area changes may use one global review without separate
review areas.

If a review area merges several review blocks, say so briefly in the
area text or in `Briefing`.

Place review areas after all global sections using:

- `## Review Area: <title>`
- `- **Status:** <status>`

Recommended status values:

- `acceptable`
- `needs-changes`
- `needs-information`
- `out-of-scope`

Each review area uses the same section ordering as the main review,
ends with its own `Assessment`, and should make clear what the area
adds, changes, or removes and why.
Do not split one file across multiple review areas.

## Section meanings

- `Scope` — actual scope of the reviewed change: major additions,
  modifications, removals, affected modules, boundaries, and explicit
  non-goals.
- `Motivation` — why the change exists, based on the pull request or
  merge request description, linked tickets, commit messages,
  confirmed user explanation, or clearly marked inference when explicit
  statements are missing.
- `Scenario` — reconstructed use cases, user flows, or operational
  situations the reviewed work enables or changes. Include a global
  `Scenario` unless the change is so narrowly technical that any
  scenario would be fake filler.
- `Constraints` — explicit non-goals, compatibility limits, review
  boundaries, restrictions, or known unknowns found in the change or in
  confirmed surrounding context.
- `Briefing` — reviewer onboarding and orientation. For a GitHub or
  GitLab review, store the full review URL here. Summarize main change
  areas, reading order, hotspots, and strategic questions.
- `Research` — the relevant previous state. Start from changed files
  and changed symbols in the diff. Inspect earlier versions, direct
  relationships, and relevant unchanged neighbors. Compare old and new
  versions against similar patterns in the same module to identify
  conventions followed, reused, or broken.
- `Design` — the implemented target state at the reviewed head. Focus
  on resulting structure, behavior, interfaces, interactions, and
  changed relationships.
- `Test specification` — the tests or checks this change should
  satisfy, reconstructed from behavior, design, risks, and integration
  points. Use
  [spec-loop-plan-task/test-specification-guidance.md](../spec-loop-plan-task/test-specification-guidance.md)
  when the needed checks are known or can be reconstructed safely. Keep evidence
  sufficiency judgments in `Assessment`, not inside `Test
  specification`.
- `Assessment` — analytic findings, uncertainties, unresolved
  concerns, recommendations, reviewer attention points, and trade-off
  analysis.

## If this file also includes a walk-through

When one file contains both the assessment and the walk-through:

- keep the assessment complete enough to stand alone as the high-level
  review;
- keep detailed file-wise explanation only in the walk-through portion;
  and
- let the high-level `Assessment` section summarize the main
  conclusions and point to the detailed walk-through when useful.

## Diagrams and GitHub/GitLab-ready copies

Use diagrams when the reviewed change alters structure, component
interaction, runtime flow, or workflow non-trivially.
For substantial or multi-area changes, area-level diagrams are expected
unless the user explicitly opts out.

Supported review diagram modes:

- `inherit`
- `plantuml`
- `mermaid`
- `none`

Resolve them like this:

- explicit user or project mode: use it;
- otherwise use `inherit` and start from the project default; and
- if you still do not know whether the target is GitHub or GitLab and
  that changes whether inherited PlantUML stays PlantUML or becomes
  Mermaid, keep the inherited local default and stop and ask before
  generating any target-site copy.

GitHub/GitLab mapping for inherited mode:

- inherited `plantuml` + GitHub target → `mermaid` in the local
  assessment file;
- inherited `plantuml` + GitLab target → `plantuml` in the local
  assessment file;
- inherited `mermaid` → `mermaid`; and
- inherited `none` → `none`.

Explicit mode rules:

- `plantuml` → keep PlantUML in the local assessment file;
- `mermaid` → write Mermaid in the local assessment file; and
- `none` → omit diagrams unless the user overrides it.

Write a target-specific copy only when the effective local assessment
file still uses a diagram format that the target site cannot render
directly.

In practice:

- GitHub target + local PlantUML assessment file → write a matching
  Mermaid copy;
- GitLab target, Mermaid, or no-diagram file → no extra copy; and
- if you still do not know whether the target is GitHub or GitLab and
  that changes whether you need a copy, stop and ask.

By default, create these copies from the assessment file, not from the
full low-level walk-through, unless the user explicitly asks for that.
Create GitHub-ready or GitLab-ready copies as local files only. Do not
post them from this skill.
