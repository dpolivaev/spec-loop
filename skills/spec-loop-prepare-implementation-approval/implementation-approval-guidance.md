# Implementation-approval guidance for `spec-loop-prepare-implementation-approval`

This skill reuses the full `spec-loop-plan-task` bundle for shared workflow
conventions.

This file is the authoritative source for pre-implementation
approval-seeking behavior, readiness checks, diagram-first
presentation, duplication removal, and response rules.
Optional compact examples:
- [examples/example-task-session-state-boundary.md](./examples/example-task-session-state-boundary.md)
- [examples/example-task-serialized-payload-change.md](./examples/example-task-serialized-payload-change.md)

Use the examples as pattern collections for approval-prepared task
presentation, not as required task size.

## 0. Purpose and boundary

Use this skill when an active task already exists and the agent is
about to seek implementation approval.

This skill is about pre-implementation task readiness. It is not the
same thing as the Constitution's post-implementation `review` task
state.

Do not add a new task-file status or marker for readiness. The
binding is behavioral:

- before implementation approval seeking inside the `spec-loop-plan-task`
  workflow, apply this skill;
- if the task is still not ready, do not seek approval;
- return exact remaining gaps instead.

Do not create a second task artifact. Update the same active task
file.

## 1. Read order and source of truth

Start from the existing active task.

Refresh the relevant `spec-loop-plan-task` bundle requirements in active
context. If the Constitution digest is not already available in the
current context, read it through `../spec-loop-plan-task/SKILL.md` as that skill
requires.

Then reread only the sections needed for the current implementation
increment:

- main-task context that the increment depends on;
- the active subtask, if subtasks exist;
- any diagrams, identifier lists, or test-spec sections that control
  the current increment.

Do not inflate future backlog subtasks just to make the task look
complete.

## 2. Output contract

Two outcomes are allowed.

### A. Approval-seeking may proceed

Polish the task for human design review, then ask the user for
implementation approval.

Use approval language, for example:
- `The task is ready for your implementation approval.`
- `Please review the task and let me know whether to implement it.`

Avoid saying only `ready for review`, because `review` is overloaded
between design review and post-implementation review state.

### B. Approval-seeking is blocked

Do not ask for implementation approval.

State clearly that the task is not yet ready for approval-seeking,
and list the exact missing items, for example:
- unresolved structural decision;
- missing diagram required by the Constitution;
- incomplete test specification for the current increment;
- stale or contradictory naming.

## 3. Review-preparation transformation rules

Make the design section reviewer-friendly without losing planning
precision.

Preferred review surface:
- final decisions first;
- diagrams for structure, boundaries, and interaction;
- compact supporting inventories only where diagrams are weak.

Canonical ownership by information kind:
- design decisions and approvals -> concise decision lists;
- structure, boundaries, and collaborators -> diagrams;
- exact identifiers, file names, schema names, action keys, and
  serialized field names -> compact lists or tables;
- unresolved questions and remaining gaps -> explicit gap lists.

Draft scaffolding may exist temporarily, but the approval-prepared task
should remove or shrink duplicated content.

Keep both a diagram and a table only when each adds distinct value.
If a table only restates what the diagram already shows, remove it or
reduce it to the exact information that the diagram cannot carry
cleanly.

## 4. Readiness checks

Before seeking implementation approval, check at least these items for
the current increment.

- The task is the correct active artifact and the current increment is
  clear.
- Research, Scenario when required, Design, and Test specification are
  complete enough for the current increment.
- Final intended names are used for new structural elements and
  externally meaningful identifiers.
- Required diagrams are present and readable.
- When production structure changes, new or changed top-level
  production types and externally meaningful identifiers are explicit
  enough for review.
- Stale alternatives, obsolete wording, and contradictory descriptions
  are removed.
- Design review focuses on structural concerns, not local variables,
  private methods, or other implementation-local details unless the
  user explicitly asked for that level.
- The task does not present duplicated diagram-plus-table restatements
  without added value.

If any check fails, stay in PLAN and report the exact gap.

## 5. Diagram-first guidance

Use the smallest diagram set that makes the increment reviewable.
Typical choices:

- class diagram when classes are added, removed, or structurally
  modified;
- component diagram for subsystem or plugin boundaries;
- sequence diagram when runtime flow or control handoff matters.

Do not add diagrams just for decoration. Use them when they make the
review faster and clearer.

When a compact identifier list is enough, do not turn it into a second
structural artifact.

## 6. Interaction with `spec-loop-plan-task`

`spec-loop-plan-task` is the primary Constitution owner and the drafting skill.
This skill is its approval-seeking companion.

That means:
- do not replace `spec-loop-plan-task`;
- do not duplicate Constitution ownership here;
- add only the approval-preparation delta;
- if implementation-ready structure is still missing, keep working in
  the task instead of pretending approval can proceed.

If new structural decisions emerge during readiness checking, update
the task in place and keep the conversation in PLAN until the gaps are
resolved.
