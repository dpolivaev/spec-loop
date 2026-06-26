---
name: spec-loop-plan-work-breakdown
description: >-
  Create or revise a Spec Loop file-based work breakdown. Use when the
  user brings a large project, feature, or initiative idea that needs
  task-file planning; asks to plan work as tasks or subtasks; asks to
  create, order, or refine a backlog; or asks to revise an existing
  work breakdown. Because chat-only planning is not durable project
  state, work breakdown planning is captured in task files. Enforces
  that each task or subtask is independently acceptable by default and
  each implementation task or subtask is releasable; non-releasable
  items require explicit user opt-out.
---

Use this skill only on the task-file path for file-based Spec Loop
work breakdown. It is a planning companion to `spec-loop-plan-task`;
it does not replace route selection, clarification, task-file rules,
or approval gates.

Use it when:
- a large project, feature, or initiative idea needs task-file
  planning;
- work needs to be planned as tasks or subtasks;
- an ordered list of future task-file work items is created, ordered,
  or refined; or
- an existing work breakdown needs revision.

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
updates needed for that increment to stand alone.

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

Do not add Briefing, Research, Scenario, Glossary, Constraints,
Analysis, Design, Test specification, or Implementation notes for
work breakdown items unless:
- the item is becoming current;
- the section is needed to make the work breakdown safe and
  understandable; or
- the User explicitly asks for that detail.

Before implementation approval, complete the current-increment
sections required by `spec-loop-plan-task`.

## Work breakdown construction

When drafting a work breakdown:
- name each item by its delivered outcome, not by a technical layer;
- prefer the smallest sequence where each item remains releasable;
- include all cross-layer work needed for each item in that same item;
- keep future subtasks lightweight until they are current, but do not
  make them vague about the independent increment they deliver;
- place supporting docs, glossary changes, config, migration, and tests
  in the same item as the behavior they support unless they are
  standalone deliverables; and
- if a candidate item is not releasable, reshape it, merge it with an
  adjacent item, or ask the User whether to opt out explicitly.

Use subtasks only when the work remains one overall task but needs
separately tracked releasable increments. Use separate tasks when the
items have independent purpose, acceptance, or release value outside a
single task.

## Final self-check

Before presenting or saving a file-based work breakdown, verify:
- each implementation task or subtask can reach `review` on its own;
- each implementation task or subtask has or will receive its own
  automated tests for that increment;
- releasing after any completed item in order would leave the project
  coherent;
- no item is named only for a layer or preparation activity unless it
  is independently releasable;
- any non-releasable item has explicit User opt-out recorded; and
- any current item is detailed enough for its next approval step while
  future items are only as detailed as needed.
