# Implementation-approval guidance for `spec-loop-prepare-implementation-approval`

This skill reuses the full `spec-loop-plan-task` bundle for shared workflow
conventions.

This file is the authoritative source for user-facing task-review
gating, pre-implementation approval-seeking behavior, readiness
checks, diagram-first presentation, duplication removal, and response
rules.
Optional compact examples:
- [examples/example-task-session-state-boundary.md](./examples/example-task-session-state-boundary.md)
- [examples/example-task-serialized-payload-change.md](./examples/example-task-serialized-payload-change.md)

Use the examples as pattern collections for approval-prepared task
presentation, not as required task size.

## 1. Read order and source of truth

Refresh the relevant `spec-loop-plan-task` bundle requirements in
active context. If the `spec-loop-plan-task` skill digest, shared task
semantics scope, or Task-file Path Guidance scope is not already
available in the current context, read them through
`../spec-loop-plan-task/SKILL.md` as that skill requires.

Use the active task or active subtask as the controlling
specification for the current increment. Follow all applicable task
sections. Re-read only the current increment and the sections that
control approval readiness. Do not inflate future subtasks.

Capture any new readiness-check decisions in the task itself so the
task stays internally consistent.

## 2. Output contract

Three outcomes are allowed.

### A. User-facing task review may proceed

Polish the task for human design review before showing it to the User.

If the next action is implementation approval, ask for implementation
approval.

Use approval language, for example:
- `The task is ready for your implementation approval.`
- `Please review the task and let me know whether to implement it.`

Avoid saying only `ready for review`, because `review` is overloaded
between design review and post-implementation review state.

### B. User-facing review is blocked

Do not show the task as reviewable and do not ask for implementation
approval.

State clearly that the task is not yet review ready,
and list the exact missing items, for example:
- unresolved structural decision;
- missing diagram required by the Task-file Path Guidance;
- incomplete test specification for the current increment;
- stale or contradictory naming.

### C. Clarification is required

If any material unresolved question remains during approval
preparation, the task is not review ready. Keep it in PLAN, invoke
`../spec-loop-clarify-task/SKILL.md` in the same turn while
preserving any clarification grill level already in force for the
current work item, and ask only the exact questions needed to remove
the uncertainty through that workflow.

Approval preparation and any clarification it invokes both stay in
PLAN. They do not freeze the active task.

Before seeking implementation approval, repair and revise the active
task in place as needed for the current work item.

## 3. Review-preparation transformation rules

Make the design section reviewer-friendly without losing planning
precision.

Preferred review surface:
- final decisions first;
- diagrams for structure, boundaries, and interaction;
- compact supporting inventories only where diagrams are weak.

Canonical ownership by information kind:
- design decisions and approvals -> concise decision lists;
- structure, boundaries, collaborators, and review-relevant members of
  displayed types -> diagrams;
- exact external identifiers, file names, schema names, action keys,
  and serialized field names -> compact lists or tables;
- unresolved questions and remaining gaps -> explicit gap lists.

Draft scaffolding may exist temporarily, but the approval-prepared task
should remove or shrink duplicated content. Before approval seeking,
use `../spec-loop-compact-task-file/SKILL.md`. Then resume this
skill.

The Design section prepared for approval must contain no placeholders
of any kind. Do not leave role stand-ins, candidate names, temporary
labels, abstract aliases, or generic structural placeholders in
Design diagrams, tables, lists, or prose. If an exact intended name is
not yet known, approval-seeking is blocked.

Keep both a diagram and a table only when each adds distinct value.
If a table only restates what the diagram already shows, remove it or
reduce it to the exact information that the diagram cannot carry
cleanly.

If a class diagram exists, review-relevant methods and fields of
displayed types must appear in it, not only in prose, lists, or
tables. Prose blocks such as `X contract:` or `Y fields:` are
duplication unless they add information the diagram cannot carry
cleanly.

Approval is blocked if those members are missing from the class
diagram.

## 4. Readiness checks

Before showing the task to the User for evaluation or seeking
implementation approval, check at least these items for the current
increment.

- The task is the correct active artifact and the current increment is
  clear.
- Research, Scenario when required, Design, and Test specification are
  complete enough for the current increment.
- No unresolved essential doubts remain about scope, behavior,
  constraints, naming, or structural boundaries.
- Final intended names are used for new structural elements and
  externally meaningful identifiers, consistent with the exact-naming
  and no-placeholder rule above.
- Required diagrams are present and readable.
- Markdown structure is renderer-safe under the Task-file
  Constitution formatting rules, especially in list-item sections that
  contain fenced blocks.
- When production structure changes, new or changed top-level
  production types and externally meaningful identifiers are explicit
  enough for review.
- When current or target class design is materially relevant, the
  required class diagrams are present in Research and/or Design and show
  the review-relevant members of displayed types.
- Stale alternatives, obsolete wording, and contradictory descriptions
  are removed.
- Design review focuses on structural concerns, not local variables,
  private methods, or other implementation-local details unless the
  user explicitly asked for that level.
- The task does not present duplicated diagram-plus-table restatements
  without added value.

If any check fails, stay in PLAN and apply this branch order:

1. If the failure is directly fixable in the active task, fix it and
   re-run the readiness checks.
2. If the failure can be resolved from existing evidence, update the
   active task and re-run the readiness checks.
3. If any material unresolved question remains, use `Clarification is
   required` immediately.
4. Use `User-facing review is blocked` only for non-question blockers
   that remain after the applicable direct fixes and evidence-based
   updates, or when the User explicitly asked for status-only
   feedback.

## 5. Diagram-first guidance

Use the smallest diagram set that makes the increment reviewable.
Typical choices:

- class diagram when current or target class design, structural
  ownership, or review-relevant members matter;
- component diagram for subsystem or plugin boundaries;
- sequence diagram when runtime flow or control handoff matters.

Do not add diagrams just for decoration. Use them when they make the
review faster and clearer.

When a compact identifier list is enough, do not turn it into a second
structural artifact.

## 6. Interaction with `spec-loop-plan-task`

`spec-loop-plan-task` owns workflow routing and task drafting.
This skill is its user-facing review and approval-seeking companion.

That means:
- do not replace `spec-loop-plan-task`;
- do not duplicate `spec-loop-plan-task`,
  `common-task-guidance.md`, or task-file path guidance ownership
  here;
- add only the approval-preparation delta.

After implementation approval, post-approval execution and the
transition to `review` are handled by
`../spec-loop-implementation-flow/SKILL.md`.

If new structural decisions emerge during readiness checking, update
the task in place and keep the conversation in PLAN until the gaps are
resolved.
