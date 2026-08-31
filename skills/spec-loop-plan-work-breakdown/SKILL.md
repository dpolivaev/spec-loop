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
A User request phrased as `task` or `subtask` does not decide by
itself whether breakdown is needed.

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

## Scope and motivation gate

Do not draft or revise a work breakdown until the whole work being split
has clear Scope and Motivation.

For initial work breakdown and material revisions to an existing work
breakdown, apply the clarification skill's mandatory decision screening
to:
- the whole work's Scope and Motivation;
- each proposed task/subtask Scope and Motivation; and
- any Constraint included in a task/subtask or needed to decide item
  boundaries, ordering, releasability, or coherence.

Do not use work breakdown to discover, clarify, or decide Constraints
that only affect later Design or Test specification inside an already
coherent item. If an unknown constraint affects item boundaries,
ordering, releasability, or coherence, treat it as a breakdown blocker;
otherwise defer it until the item becomes current.

If Scope or Motivation is unclear, do only enough research to frame the
gap, then use
[spec-loop-clarify-task/SKILL.md](../spec-loop-clarify-task/SKILL.md)
before drafting breakdown items.

Do not create placeholder implementation items for unclear future work.

For work breakdown, clarify only enough to make item boundaries,
ordering, and coherence sound. Defer detailed rule, API, design, and
test clarification until the item is current.

## Standalone-item rule

Every task or subtask in a work breakdown must stand on its own by
default. For implementation work, that means the item must be
releasable.

A releasable implementation item can be implemented, verified,
reviewed, and accepted on its own while leaving the project coherent
if no later sibling is implemented. It must include its tests and any
supporting config, migration, documentation, glossary, or operational
updates needed to stand on its own. This is a validity criterion, not
a recommendation to release each task or subtask independently.

For feature implementation, choose item boundaries by slicing the
overall work into releasable vertical slices when a coherent split
exists.

Use separate task files/backlog items when separate release decisions
are advisable. Use subtasks when the resulting items can safely stand
alone but separate release decisions are not advisable.

Do not create non-releasable implementation items unless the User
explicitly opts out. If the User opts out, record the opt-out in the
affected item's Scope.

Forbidden by default:
- scaffolding-only, setup-only, model-only, logic-only, API-only,
  persistence-only, UI-only, test-only, or docs-only implementation
  items that do not stand alone;
- a preparation-heavy implementation item with no observable
  accepted-result difference or other immediately demonstrable
  coherent capability when a smaller behavior-first slice is viable;
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

Prefer the earliest coherent implementation item that shows visible
executable behavior or another immediately demonstrable coherent
capability. For feature work, that usually means the first releasable
vertical slice.

Defer setup, generation, storage, orchestration, and generalization
until they affect that item's accepted result, unless they are
required for coherence or independent acceptability.

If the same behavior can be reviewed first with deterministic setup,
prefer that slice. If a candidate item still carries substantial
plumbing or generalization with too little visible result, or internal
choices it cannot yet expose, split it again unless that would break
independent acceptability.

Do not pull speculative flexibility or future-proofing into an earlier
item when a narrower one can already stand alone.

## Work breakdown detail level

For an initial task or subtask created from scratch, include only the
title, required identifier/status metadata, and these planning
sections:
- Scope;
- Motivation; and
- Constraints, only for already known limits that materially affect the
  item.

For subtasks, keep the normal `- **Status:**` line because it is
lifecycle metadata, not planning content.

For research, spike, prototype, catalog, or proof tasks/subtasks,
Scope states the investigation boundary and expected output;
Motivation states what it blocks or enables.

Do not newly draft Briefing, Research, Scenario, Glossary, Analysis,
Design, or Test specification during work breakdown. Complete those
planning sections only after the item becomes current under
`spec-loop-plan-task`.

Do not create Findings or Implementation notes during work breakdown.
Findings is filled only after approved investigation work produces
reviewed output. Implementation notes follow
`spec-loop-implementation-flow`.

Include a Constraints section only for already known limits that
materially affect the item. Do not create placeholder Constraints.

The exception is restructuring existing planned work, including
converting a no-subtask task into subtask form. Existing section
content may be moved to the correct resulting task or subtask, but new
content for those sections must not be drafted during breakdown.

Before execution approval for a task-file item, complete the
sections required for the current work by `spec-loop-plan-task`.

## Blocking unknowns

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
  acceptable, each implementation item remains releasable, and earlier
  items follow the Fast-payoff preference above;
- create research, spike, prototype, catalog, or proof tasks/subtasks
  only when the investigation itself requires explicit planning,
  separate tracking, or a reviewable output;
- for implementation items, include all cross-layer work needed in
  that same item;
- if the refactoring is standalone and the change remains one overall
  task, put it in its own subtask;
- if the refactoring is standalone and separate release decisions are
  advisable, use a separate task file or backlog item;
- if the refactoring is not standalone, keep it inside the feature
  item;
- keep future tasks and subtasks created from scratch to title,
  required identifier/status metadata, Scope, Motivation, and any
  already known item-relevant Constraints until they are current, but
  make the standalone result clear;
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
  automated tests for that item;
- for implementation items without an explicit non-releasable opt-out,
  after each completed item the resulting software state would be
  coherent and releasable if no later sibling were implemented;
- earlier implementation items, especially the first, satisfy the
  Fast-payoff preference above and do not pull setup, generation,
  storage, orchestration, or generalization forward without need;
- no item is named only for a layer or preparation activity unless it
  is independently acceptable;
- any non-releasable implementation item has explicit User opt-out
  recorded;
- each research, spike, prototype, catalog, or proof task/subtask
  shows why separate tracking is needed;
- no downstream implementation item depends on an unresolved unknown;
- new backlog task files created by this breakdown have readable
  three-digit prefixes local to the containing folder;
- each task or subtask created from scratch contains only the title,
  required identifier/status metadata, Scope, Motivation, and already
  known item-relevant Constraints when present;
- each refactoring-only task or subtask is a standalone refactoring;
- separate task files or backlog items are used for standalone
  refactoring only when separate release decisions are advisable;
- fuller section content appears only in unchanged pre-existing current
  items or because it was moved from an existing task or subtask during
  restructuring, including conversion from no-subtask task to subtask
  form.
