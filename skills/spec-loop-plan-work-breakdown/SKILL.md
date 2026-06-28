---
name: spec-loop-plan-work-breakdown
description: >-
  Create or revise file-based Spec Loop work breakdown artifacts for
  task-file work that uses subtasks or multiple task files / backlog
  items.
---

Use this skill for file-based Spec Loop work breakdown on the
task-file path. It is a planning companion to `spec-loop-plan-task`;
it does not replace
[planning-form selection](../spec-loop-plan-task/planning-form-selection-guidance.md),
clarification, task-file rules, or approval gates.

Use it after planning-form selection chooses `task file with subtasks`
or `multiple task files / backlog items`, or when revising an existing
file-based work breakdown.

If this skill is loaded before planning-form selection is complete, do
not draft breakdown artifacts. If first classification is incomplete,
return to [spec-loop-plan-task](../spec-loop-plan-task/SKILL.md).
Otherwise follow
[guidance](../spec-loop-plan-task/planning-form-selection-guidance.md)
first.

If this need appears during chat-only/fileless planning, switch or
promote to the task-file path before creating the breakdown. Chat-only
content is not durable project state outside the current chat, so
work breakdown state must live in a task file.

## Releasable-increment rule

Every task or subtask in a work breakdown must be an independently
acceptable increment by default. For implementation work, that means a
releasable increment.

A releasable increment can be implemented, verified, reviewed, and
accepted independently while leaving the project coherent if no later
sibling is implemented yet. It includes the tests and any same-slice
supporting config, migration, documentation, glossary, or operational
updates needed for that increment to stand alone. This is a
vertical-slice validity criterion, not a recommendation to release
each task or subtask independently.

Use separate task files/backlog items when separate release decisions
are advisable. Use subtasks when the slices can safely stand alone but
separate release decisions are not advisable.

Do not create non-releasable implementation slices unless the User
explicitly opts out. If the User opts out, record the opt-out in the
active task artifact near the affected task or subtask.

Forbidden by default:
- scaffolding-only, setup-only, model-only, logic-only, API-only,
  persistence-only, UI-only, test-only, or docs-only implementation
  slices that do not stand alone;
- a "foundation" subtask that only enables later behavior but has no
  independently reviewable result; and
- splitting implementation and tests for the same behavior into
  separate subtasks.

Allowed when they stand alone:
- infrastructure or setup work that leaves a coherent build or runtime
  capability;
- documentation, test-hardening, migration, or cleanup work whose
  standalone artifact is the accepted deliverable; and
- a layer-focused change that is itself a complete reviewable behavior
  or operational capability, not merely a dependency for a later
  sibling.

## Work breakdown detail level

For an initial work breakdown, each task or subtask only needs:
- title;
- Scope; and
- Motivation.

For subtasks, keep the normal `- **Status:**` line because it is
lifecycle metadata, not planning content.

For research, spike, prototype, catalog, or proof tasks/subtasks,
Scope states the investigation boundary and expected output;
Motivation states what it blocks or enables.

Do not add Briefing, Research, Scenario, Glossary, Constraints,
Analysis, Design, Test specification, or Implementation notes for
work breakdown items unless:
- the item is becoming current;
- the section is needed to make the work breakdown safe and
  understandable; or
- the User explicitly asks for that detail.

Before execution approval for a task-file item, complete the
current-increment sections required by `spec-loop-plan-task`.

## Blocking unknowns

Plan only work whose Scope and Motivation are understood enough for
review.

If a blocking unknown appears during work breakdown, use
[spec-loop-clarify-task/SKILL.md](../spec-loop-clarify-task/SKILL.md)
when existing evidence or User engagement can resolve it.

If the blocker still needs a durable decision, route ADR work. If it
needs new evidence, create a research, spike, prototype, catalog, or
proof task/subtask only when that investigation requires explicit
planning, separate tracking, or a reviewable output.

If accepted investigation findings show that a durable architecture or
policy decision is needed, route ADR work before planning dependent
implementation items.

Do not create downstream implementation items or placeholders that
depend on the unknown result. If blocking work is needed, pause work
breakdown. After that work is accepted, continue the work breakdown
from the new evidence or decision.

If converting a no-subtask task to subtask form, adding a new subtask
after earlier subtasks already contain full section content, or making
the task file too large to use safely, use
[spec-loop-compact-task-file/SKILL.md](../spec-loop-compact-task-file/SKILL.md)
and then resume this skill.

## Work breakdown construction

When drafting a work breakdown:
- name each item by its delivered outcome, not by a technical layer;
- when creating backlog task files, number them with readable
  three-digit prefixes local to the containing folder;
- prefer the smallest sequence where each item remains independently
  acceptable and each implementation item remains releasable;
- create research, spike, prototype, catalog, or proof tasks/subtasks
  only when the investigation itself requires explicit planning,
  separate tracking, or a reviewable output;
- for implementation items, include all cross-layer work needed in
  that same item;
- keep future subtasks lightweight until they are current, but do not
  make them vague about the independent increment they deliver;
- place supporting docs, glossary changes, config, migration, and tests
  in the same item as the behavior they support unless they are
  standalone deliverables; and
- if a candidate implementation item is not releasable, reshape it,
  merge it with an adjacent item, or ask the User whether to opt out
  explicitly.

## Final self-check

Before presenting or saving a file-based work breakdown, verify:
- each implementation task or subtask can reach `review` on its own;
- each implementation task or subtask has or will receive its own
  automated tests for that increment;
- releasing after any completed implementation item in order would
  leave the project coherent, whether or not that item is actually
  released independently;
- no item is named only for a layer or preparation activity unless it
  is independently acceptable;
- any non-releasable implementation item has explicit User opt-out
  recorded;
- each research, spike, prototype, catalog, or proof task/subtask
  shows why separate tracking is needed;
- no downstream implementation item depends on an unresolved unknown;
- new backlog task files created by this breakdown have readable
  three-digit prefixes local to the containing folder;
- any current item is detailed enough for its next approval step while
  future items are only as detailed as needed.
