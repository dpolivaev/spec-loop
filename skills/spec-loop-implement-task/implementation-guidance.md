# Implementation guidance for `spec-loop-implement-task`

This skill reuses the full `spec-loop-plan-task` bundle for shared
workflow conventions plus `task-file-constitution.md` for task-file
structure, lifecycle, and formatting rules.

This file is the authoritative source for implementation-phase task
maintenance, clarification handling, `Implementation notes`,
completion checks, and `in-progress` -> `review` behavior on the
Task-file path.

## 1. Read order and source of truth

Refresh the relevant `spec-loop-plan-task` bundle requirements in
active context. If the `spec-loop-plan-task` skill digest or the
Task-file Constitution scope is not already available in the current
context, read them through `../spec-loop-plan-task/SKILL.md` as that
skill requires.

Use the approved active task or active subtask as the controlling
specification for the current increment. Follow all applicable task
sections. Re-read only the current increment and the sections that
control the affected work. Do not inflate future subtasks.

`Test specification` defines required verification, not a hard upper
bound. Adding tests beyond it is always allowed and does not need
separate confirmation, as long as those tests stay within the approved
increment.

Capture new implementation-time decisions in the task, but do not let
them contradict the approved specification. Keep current truth in the
normal task sections. `Implementation notes` records implementation-
time history only.

## 2. Implementation-time routing

When implementation reaches a decision point, only these routes are
allowed.

### A. Continue implementation and update the task in place

Use this route when implementation stays inside the approved design
boundary and no blocking ambiguity remains.

### B. Pause the affected implementation and ask targeted User questions

Use this route when implementation reveals a business-logic ambiguity
or another issue that cannot be resolved safely from the approved task
alone, but no redesign is yet known.

Pause only the affected implementation area. Explain the exact reason
for the pause.

After the User clarifies the issue, update the canonical task sections.
If the clarification still fits inside the approved design, the User's
chat clarification plus the task update is sufficient authority to
continue. No separate go-ahead step is required.

### C. Return to PLAN and seek renewed approval

Use this route when the clarification or discovered issue changes the
approved contract or needs redesign.

Return to PLAN when the change affects any of the following:

- approved scope;
- business behavior or scenario contract;
- constraints or non-goals;
- new or renamed planned top-level production types;
- new or renamed planned structural elements with externally relevant
  meaning;
- new cross-class boundaries;
- new externally meaningful identifiers;
- serialized, persisted, or configuration contracts; or
- dependency decisions not already approved.

When this route applies, return to `spec-loop-plan-task`. If the
Task-file path remains in use, revise the task there and use
`spec-loop-prepare-implementation-approval` again before resuming
implementation.

### D. Move the task or subtask to `review`

Use this route when implementation and local verification are
complete for the current increment and the Task-file Constitution's
lifecycle rules allow the move.

## 3. `Implementation notes`

`Implementation notes` is conditional. Include it whenever any of its
canonical parts has meaningful content. Omit it only when every
canonical part would be empty. Do not add boilerplate when there is
nothing meaningful to record.

Placement:

- without subtasks: task level;
- with subtasks: the active implementation subtask, unless a genuine
  task-level note is needed.

Use these fixed canonical subheadings when notes are present:

- `Interpretations`
- `Tradeoffs`
- `Open questions`

Omit empty subheadings instead of adding placeholders.

Meaning of the subheadings:

- `Interpretations` - choices made where the approved task definition
  was ambiguous but the approved contract remained intact;
- `Tradeoffs` - meaningful alternatives considered during
  implementation and why the chosen path won; and
- `Open questions` - non-blocking follow-up items explicitly deferred
  to named follow-up work. Naming the intended follow-up work is
  sufficient; the follow-up subtask does not need to exist yet.

Record policy:

- `Implementation notes` is a durable implementation record, not a
  temporary scratchpad.
- Keep meaningful implementation-time decisions and explanations for
  later review.
- Record only the model's own implementation-time interpretations,
  tradeoffs, and open questions.
- Do not use it as a log of user clarification, review feedback, or
  general conversation history.
- Do not require chronological ordering.
- Prefer organizing notes by meaning rather than by timestamp.
- Allow only light cleanup such as removing obvious noise,
  duplication, wording defects, or unhelpful ordering that does not
  erase the decision trail.

`Open questions` may remain only when all of the following are true:

- they do not block the current increment;
- they are explicitly deferred to named follow-up work, where naming
  the intended follow-up work is sufficient even if the follow-up
  subtask does not yet exist; and
- they therefore do not prevent the current task or subtask from
  moving to `review`.

If an implementation question blocks the current increment, do not
leave it unresolved in `Implementation notes`. Clarify it in chat.
If an explicit User clarification statement can be kept without
returning to PLAN, update the affected canonical task section or
sections instead of recording that clarification in
`Implementation notes`.

## 4. Task updates after kept clarification

After an explicit User clarification statement, update canonical task
sections only when that clarification must be kept in the task without
returning to PLAN.

The existing approved task specification remains controlling by
default. Only an explicit User clarification statement may override
earlier task wording or decisions.

Update only the minimal affected canonical section or sections. Do not
rewrite canonical task sections for ordinary internal implementation
choices or for history that belongs in `Implementation notes`.

Update whichever canonical section or sections the explicit User
clarification actually changes, including `Scenario`, `Constraints`,
`Design`, or `Test specification`.

Accepted review feedback follows the same rule: apply it directly to
the relevant canonical task sections instead of recording the feedback
itself in `Implementation notes`.

Keep accepted clarifications and review adjustments in canonical task
sections rather than logging that conversation history in
`Implementation notes`.

## 5. Completion and transition to `review`

Before moving the task or subtask to `review`, ensure that:

- the approved scope for the current increment is implemented;
- `Constraints`, `Design`, and `Test specification` are implemented as
  applicable for that increment;
- canonical task sections are updated to match the implemented current
  truth for the increment;
- required tests or other local verification from `Test
  specification` pass, unless the User waived them;
- required glossary work is complete;
- meaningful implementation-time history is recorded in
  `Implementation notes` when needed;
- any remaining `Open questions` are non-blocking and explicitly
  deferred to named follow-up work; and
- no obvious leftovers remain, such as TODOs, placeholders, example
  names, temporary comments, or unused imports.

If any check fails, do not move to `review`.
Use the applicable route from Section 2 instead.

When the checks pass, follow the Task-file Constitution's lifecycle
rules and move the task or subtask from `in-progress` to `review`.

## 6. Interaction with the other task skills

`spec-loop-plan-task` owns workflow routing, planning, and approval
gates.

`spec-loop-prepare-implementation-approval` owns pre-implementation
Task-file readiness and approval-seeking preparation.

This skill owns post-approval execution on the Task-file path:
implementation-phase task maintenance, clarification handling,
`Implementation notes`, completion checks, and the transition to
`review`.

Do not replace the planning or approval-preparation skills. Reuse them
when implementation uncovers a change that needs renewed planning or
approval under Section 2C.
