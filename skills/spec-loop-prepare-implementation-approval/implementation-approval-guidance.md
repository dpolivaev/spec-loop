# Implementation-approval guidance for `spec-loop-prepare-implementation-approval`

This file is the authoritative source for task-file review readiness,
pre-implementation approval-seeking behavior, readiness checks,
diagram-first presentation, duplication removal, and response rules.

It reuses the full `spec-loop-plan-task` bundle for shared workflow
conventions.

Optional compact examples:
- [examples/example-task-session-state-boundary.md](./examples/example-task-session-state-boundary.md)
- [examples/example-task-serialized-payload-change.md](./examples/example-task-serialized-payload-change.md)

Use the examples as pattern collections, not as required task size.

## 1. Source of truth

Refresh the relevant `spec-loop-plan-task` bundle requirements in the
active context.

Use the active task or active subtask as the controlling specification
for the current increment. Re-read only the current increment and the
sections that control approval readiness. Do not inflate future
subtasks.

Capture any new readiness-check decisions in the same task so it stays
internally consistent.

## 2. Allowed user-facing outcomes

Only these outcomes are allowed.

### A. Task review may proceed

Polish the task for human design review before showing it to the user.

If the next action is implementation approval, ask for implementation
approval.

Use approval language such as:
- `The task is ready for your implementation approval.`
- `Please review the task and let me know whether to implement it.`

Do not say only `ready for review`, because `review` is overloaded
between design review and post-implementation review state.

### B. Review is blocked

Do not show the task as reviewable and do not ask for implementation
approval.

State clearly that the task is not yet review ready, and list the exact
missing items.

### C. Clarification is required

If any material unresolved question remains during approval
preparation, the task is not review ready.

Keep the work in PLAN, invoke `../spec-loop-clarify-task/SKILL.md` in
the same turn while preserving any clarification grill level already in
force for the work item, and ask only the exact questions needed to
remove the uncertainty through that workflow.

Approval preparation and any clarification it invokes both stay in
PLAN. They do not freeze the active task.

## 3. Readiness loop

Before seeking implementation approval, repair and revise the active
task in place as needed for the current work item.

If readiness checks fail, use this branch order:
1. If the failure is directly fixable in the active task, fix it and
   re-run the readiness checks.
2. If the failure can be resolved from existing evidence, update the
   active task and re-run the readiness checks.
3. If compaction is needed, use
   `../spec-loop-compact-task-file/SKILL.md`, then resume this skill.
4. If any material unresolved question remains, use outcome C
   immediately.
5. Use outcome B only for non-question blockers that remain after the
   applicable direct fixes and evidence-based updates, or when the
   user explicitly asked for status-only feedback.

Unlike ordinary directly fixable defects, structural decomposition
changes must not be applied silently. If approval preparation finds
that converting a no-subtask task to subtask form or splitting the
active subtask would materially improve decomposition of the current
work item by splitting it into parts that are easier to review and
implement, briefly present the proposed restructuring and ask whether
to apply it now.

If the User agrees, restructure in place, keep only genuinely shared
context at task level, move subtask-specific Research, Analysis,
Design, and Test specification into the relevant subtask, avoid
parallel task-level and subtask-level restatements of the same
material, then use `../spec-loop-compact-task-file/SKILL.md` and
resume this skill.

After restructuring and compaction and before re-running readiness
checks, verify that no needed content was lost or moved incorrectly,
that no meaning changed unintentionally, that shared and
subtask-specific content now have one authoritative location at the
correct level, and that no contradictory duplicate statements remain.

Then re-run readiness checks and continue approval preparation. If the
User declines, continue approval preparation on the current
structure.

## 4. Review-preparation rules

Make the Design section reviewer-friendly without losing planning
precision.

Preferred review surface:
- final decisions first;
- diagrams for structure, boundaries, and interaction; and
- compact supporting inventories only where diagrams are weak.

Canonical ownership by information kind:
- design decisions and approvals -> concise decision lists;
- structure, boundaries, collaborators, and review-relevant members of
  displayed types -> diagrams;
- exact external identifiers, file names, schema names, action keys,
  and serialized field names -> compact lists or tables; and
- unresolved questions and remaining gaps -> explicit gap lists.

Draft scaffolding may exist temporarily, but the approval-prepared task
should remove or shrink duplicated content. Before approval seeking,
use `../spec-loop-compact-task-file/SKILL.md` when needed, then resume
this skill.

The Design section prepared for approval must contain no placeholders.
Do not leave role stand-ins, candidate names, temporary labels,
abstract aliases, or generic structural placeholders in Design
prose, diagrams, tables, or lists. If an exact intended name is not
yet known, approval-seeking is blocked.

Keep both a diagram and a table only when each adds distinct value. If
a table only restates the diagram, remove it or reduce it to the exact
information the diagram cannot carry cleanly.

If a class diagram exists, review-relevant methods and fields of
displayed types must appear in it, not only in prose, lists, or
tables. Prose blocks such as `X contract:` or `Y fields:` are
duplication unless they add information the diagram cannot carry
cleanly.

Approval is blocked if those members are missing from the class
diagram.

## 5. Readiness checks

Before showing the task to the user for evaluation or seeking
implementation approval, check at least these items for the current
increment:
- the task is the correct active artifact and the current increment is
  clear;
- `Research`, `Scenario` and `Glossary` when required, `Design`, and
  `Test specification` are complete enough for the current increment;
- approval preparation has completed the pre-approval term-reduction,
  term-classification, and glossary-repair pass required by
  `../spec-loop-plan-task/scenario-and-glossary-guidance.md`, and has
  recorded the result by repairing canonical sections rather than by
  adding a separate classification table unless the User asked for
  one;
- approval is blocked if coined terms remain where existing canonical
  terms or ordinary prose would preserve review-relevant precision;
- approval is blocked if any new review-relevant term introduced in
  `Design`, diagrams, or `Test specification` remains unclassified or
  ambiguously classified;
- qualifying shared domain terms appear in `Scenario` and, when they
  are a delta for the current increment, in task `Glossary`;
  qualifying exact external technical terms appear in task `Glossary`
  when the exact external type or API is part of the reviewed
  contract; approval is blocked if required canonical repairs are
  missing;
- internal implementation terms are not used in `Scenario`, task
  `Glossary`, or behavior-level diagrams and prose;
- no unresolved essential doubts remain about scope, behavior,
  constraints, naming, or structural boundaries;
- final intended names are used for new structural elements and
  externally meaningful identifiers, consistent with the no-placeholder
  rule above;
- required diagrams are present and readable;
- when task `Glossary` is present, its focused Mermaid visual glossary
  is in sync with the text glossary; approval is blocked if they
  diverge, including disagreement, omission, or stale mismatch, on
  terms, relationships, boundaries, actors, or flows;
- markdown structure is renderer-safe under the Task-file
  Constitution formatting rules, especially in list-item sections that
  contain fenced blocks;
- when production structure changes, new or changed top-level
  production types and externally meaningful identifiers are explicit
  enough for review;
- when current or target class design is materially relevant, the
  required class diagrams are present in `Research` and/or `Design`
  and show the review-relevant members of displayed types;
- stale alternatives, obsolete wording, and contradictory
  descriptions are removed;
- design review focuses on structural concerns, not local variables,
  private methods, or other implementation-local details unless the
  user explicitly asked for that level; and
- the task does not present duplicated diagram-plus-table restatements
  without added value; and
- the active task file's current main-task/subtask structure is a good
  fit for the current increment.

## 6. Diagram choice

Use the smallest diagram set that makes the increment reviewable.
Typical choices:
- class diagram when current or target class design, structural
  ownership, or review-relevant members matter;
- component diagram for subsystem or plugin boundaries; and
- sequence diagram when runtime flow or control handoff matters.

Do not add diagrams for decoration. Use them only when they make the
review faster and clearer.

When a compact identifier list is enough, do not turn it into a second
structural artifact.

## 7. Interaction with `spec-loop-plan-task`

`spec-loop-plan-task` owns workflow routing and task drafting. This
skill is its user-facing review and approval-seeking companion.

That means:
- do not replace `spec-loop-plan-task`;
- do not duplicate `spec-loop-plan-task`,
  `common-task-guidance.md`, or task-file path guidance ownership
  here; and
- add only the approval-preparation delta.

After implementation approval, post-approval execution and the
transition to `review` are handled by
`../spec-loop-implementation-flow/SKILL.md`.

If new structural decisions emerge during readiness checking, update
the task in place and keep the conversation in PLAN until the gaps are
resolved.
