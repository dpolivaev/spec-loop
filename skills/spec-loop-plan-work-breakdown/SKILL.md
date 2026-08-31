---
name: spec-loop-plan-work-breakdown
description: >-
  Create or revise file-based Spec Loop work breakdown artifacts for
  task-file work, including splitting a requested single item into
  subtasks or multiple task files / backlog items when planning or
  design shows that split is needed.
---

Use this skill for file-based Spec Loop work breakdown on the
task-file path. It is a planning companion to `spec-loop-plan-task`;
it does not replace
[planning-form selection](../spec-loop-plan-task/planning-form-selection-guidance.md),
clarification, task-file rules, or approval gates.

Use it after planning-form selection chooses `task file with subtasks`
or `multiple task files / backlog items`.
Also use it when revising an existing file-based work breakdown, or
when later planning or design shows that a requested single task or
subtask should be split.

If this skill is loaded before planning-form selection is complete, do
not draft breakdown artifacts. If first classification is incomplete,
or if later planning or design may change the viable forms, return to
[spec-loop-plan-task](../spec-loop-plan-task/SKILL.md). Otherwise
follow
[guidance](../spec-loop-plan-task/planning-form-selection-guidance.md)
first.

If this need appears during chat-only/fileless planning, switch or
promote to the task-file path before creating the breakdown. Chat-only
content is not durable project state outside the current chat, so
work breakdown state must live in a task file.

In the sections below, `(sub)task` means a task or subtask in the work
breakdown.

## Scope and motivation gate

Do not draft or revise a work breakdown until the whole work being split
has clear Scope and Motivation.

For initial work breakdown and material revisions to an existing work
breakdown, apply the clarification skill's mandatory decision screening
to:
- the whole work's Scope and Motivation;
- each proposed (sub)task Scope and Motivation; and
- any Constraint included in a (sub)task or needed to decide
  (sub)task boundaries, ordering, releasability, or coherence.

Do not use work breakdown to discover, clarify, or decide Constraints
that only affect later Design or Test specification inside an already
coherent (sub)task. If an unknown constraint affects (sub)task
boundaries, ordering, releasability, or coherence, treat it as a
breakdown blocker; otherwise defer it until that (sub)task
becomes current.

If Scope or Motivation is unclear, do only enough research to frame the
gap, then use
[spec-loop-clarify-task/SKILL.md](../spec-loop-clarify-task/SKILL.md)
before drafting breakdown items.

Do not create placeholder implementation (sub)tasks for
unclear future work.

For work breakdown, clarify only enough to make (sub)task
boundaries, ordering, and coherence sound. Defer detailed rule, API,
design, and test clarification until the (sub)task is current.

## Standalone (sub)task rule

Every (sub)task in a work breakdown must stand on its own by
default. For implementation work, that means the (sub)task must
be releasable.

A releasable implementation (sub)task can be implemented,
verified, reviewed, and accepted on its own while leaving the project
coherent if no later sibling is implemented. It must include its tests
and any supporting config, migration, documentation, glossary, or
operational updates needed to stand on its own. This is a validity
criterion, not a recommendation to release each (sub)task
independently.

For feature implementation, choose (sub)task boundaries by
slicing the overall work into releasable vertical slices when a
coherent split exists.

Use separate task files/backlog items when separate release decisions
are advisable. Use subtasks when the resulting subtasks can safely
stand alone but separate release decisions are not advisable.

Do not create non-releasable implementation (sub)tasks unless
the User explicitly opts out. If the User opts out, record the opt-out
in the affected (sub)task's Scope.

Forbidden by default:
- scaffolding-only, setup-only, model-only, logic-only, API-only,
  persistence-only, UI-only, test-only, or docs-only implementation
  (sub)tasks that do not stand alone;
- a preparation-heavy implementation (sub)task with no
  observable accepted-result difference or other immediately
  demonstrable coherent capability when a smaller behavior-first
  vertical slice is viable;
- a "foundation" subtask that only enables later behavior but has no
  independently reviewable result; and
- splitting implementation and tests for the same behavior into
  separate subtasks.

A standalone refactoring makes no intended externally observable
behavior change and is still acceptable if merged even if the later
feature item is never implemented.

Allowed when they stand alone:
- infrastructure or setup work that leaves a coherent build or runtime
  capability;
- documentation, test-hardening, migration, or cleanup work whose
  standalone artifact is the accepted deliverable;
- standalone refactoring; and
- a layer-focused change that is itself a complete reviewable behavior
  or operational capability, not merely a dependency for a later
  sibling.

## Fast-payoff preference

Prefer the earliest coherent implementation (sub)task that shows
visible executable behavior or another immediately demonstrable
coherent capability. For feature work, that usually means the first
releasable vertical slice.

Defer setup, generation, storage, orchestration, and generalization
until they affect the accepted result of that (sub)task, unless
they are required for coherence or independent acceptability.

If the same behavior can be reviewed first with deterministic setup,
prefer that slice. If a candidate (sub)task still carries
substantial plumbing or generalization with too little visible result,
or internal choices it cannot yet expose, split it again unless that
would break independent acceptability.

Do not pull speculative flexibility or future-proofing into an earlier
(sub)task when a narrower one can already stand alone.

## Work breakdown detail level

For an initial (sub)task created from scratch, include only the
title, required identifier/status metadata, and these planning
sections:
- Scope;
- Motivation; and
- Constraints, only for already known limits that materially affect the
  (sub)task.

For subtasks, keep the normal `- **Status:**` line because it is
lifecycle metadata, not planning content.

For research, spike, prototype, catalog, or proof (sub)tasks,
Scope states the investigation boundary and expected output;
Motivation states what it blocks or enables.

Do not newly draft Briefing, Research, Scenario, Glossary, Analysis,
Design, or Test specification during work breakdown. Complete those
planning sections only after the (sub)task becomes current
under `spec-loop-plan-task`.

Do not create Findings or Implementation notes during work breakdown.
Findings is filled only after approved investigation work produces
reviewed output. Implementation notes follow
`spec-loop-implementation-flow`.

Include a Constraints section only for already known limits that
materially affect the (sub)task. Do not create placeholder
Constraints.

The exception is restructuring existing planned work, including
converting a no-subtask task into subtask form. Existing section
content may be moved to the correct resulting (sub)task, but new
content for those sections must not be drafted during breakdown.

Before execution approval on the task-file path, complete the
sections required for the current task under `spec-loop-plan-task`.
If the task uses subtasks, apply this to the current subtask plus
needed task-level context.

## Blocking unknowns

If a blocking unknown appears during work breakdown, use
[spec-loop-clarify-task/SKILL.md](../spec-loop-clarify-task/SKILL.md)
when existing evidence or User engagement can resolve it.

If the blocker still needs a durable decision, route ADR work. If it
needs new evidence, create a research, spike, prototype, catalog, or
proof (sub)task only when that investigation requires explicit
planning, separate tracking, or a reviewable output.

If accepted investigation findings show that a durable architecture or
policy decision is needed, route ADR work before planning dependent
implementation (sub)tasks.

Do not create downstream implementation (sub)tasks or
placeholders that depend on the unknown result. If blocking work is
needed, pause work breakdown. After that work is accepted, continue
the work breakdown from the new evidence or decision.

If converting a no-subtask task to subtask form, adding a new subtask
after earlier subtasks already contain full section content, or making
the task file too large to use safely, use
[spec-loop-compact-task-file/SKILL.md](../spec-loop-compact-task-file/SKILL.md)
and then resume this skill.

## Work breakdown construction

When drafting a work breakdown:
- name each (sub)task by its delivered outcome, not by a
  technical layer;
- when creating backlog task files, number them with readable
  three-digit prefixes local to the containing folder;
- prefer the smallest sequence where each (sub)task remains
  independently acceptable, each implementation (sub)task
  remains releasable, and earlier (sub)tasks follow the
  Fast-payoff preference above;
- create research, spike, prototype, catalog, or proof (sub)tasks
  only when the investigation itself requires explicit planning,
  separate tracking, or a reviewable output;
- for implementation (sub)tasks, include all cross-layer work
  needed in that same (sub)task;
- if the refactoring is standalone and the change remains one overall
  task, put it in its own subtask;
- if the refactoring is standalone and separate release decisions are
  advisable, use a separate task file or backlog item;
- if the refactoring is not standalone, keep it inside the feature
  (sub)task;
- keep future (sub)tasks created from scratch to title,
  required identifier/status metadata, Scope, Motivation, and any
  already known relevant Constraints until they are current, but make
  the standalone result clear;
- place supporting docs, glossary changes, config, migration, and
  tests in the same (sub)task as the behavior they support
  unless they are standalone deliverables; and
- if a candidate implementation (sub)task is not releasable,
  reshape it, merge it with an adjacent (sub)task, or ask the
  User whether to opt out explicitly.

## Final self-check

Before presenting or saving a file-based work breakdown, verify:
- each implementation (sub)task can reach `review` on its own;
- each implementation (sub)task has or will receive its own
  automated tests;
- for implementation (sub)tasks without an explicit
  non-releasable opt-out, after each completed (sub)task the
  resulting software state would be coherent and releasable if no
  later sibling were implemented;
- earlier implementation (sub)tasks, especially the first,
  satisfy the Fast-payoff preference above and do not pull setup,
  generation, storage, orchestration, or generalization forward
  without need;
- no (sub)task is named only for a layer or preparation
  activity unless it is independently acceptable;
- any non-releasable implementation (sub)task has explicit User
  opt-out recorded;
- each research, spike, prototype, catalog, or proof (sub)task
  shows why separate tracking is needed;
- no downstream implementation (sub)task depends on an
  unresolved unknown;
- new backlog task files created by this breakdown have readable
  three-digit prefixes local to the containing folder;
- each (sub)task created from scratch contains only the title,
  required identifier/status metadata, Scope, Motivation, and already
  known relevant Constraints when present;
- each refactoring-only (sub)task is a standalone refactoring;
- separate task files or backlog items are used for standalone
  refactoring only when separate release decisions are advisable;
- fuller section content appears only in an unchanged pre-existing
  current task, in an unchanged pre-existing current subtask, or
  because it was moved from an existing (sub)task during
  restructuring, including conversion from no-subtask task to subtask
  form.
