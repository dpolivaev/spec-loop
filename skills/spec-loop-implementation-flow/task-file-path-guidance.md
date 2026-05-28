# Task-file Path Guidance for `spec-loop-implementation-flow`

This file applies only on the Task-file path during
implementation.

Use it with the shared core in
[implementation-flow-guidance.md](./implementation-flow-guidance.md)
and the task-file mechanics in
`../spec-loop-plan-task/task-file-path-guidance.md`.
It defines only the task-file implementation-time delta.

Task-file lifecycle, subtask, and folder ownership remain in the
Task-file Path Guidance.

## 0. Authorized task-file updates

On the task-file path during implementation flow, the active task file
is an approved, reviewer-facing artifact plus a controlled
post-implementation record. Do not treat it as a general scratchpad or
silently normalize it to the code.

Only these task-file edits are allowed:

- add or update `Implementation notes` at the mandatory checkpoint;
- update canonical task sections only when the shared core gives
  explicit authority for that change;
- perform the explicitly allowed lifecycle transition, including the
  minimal status or folder edits required by the Task-file
  Path Guidance; and
- perform minimal mechanical cleanup strictly incidental to one of the
  allowed edits above, such as wrapping, indentation, spacing, or
  nearby list-formatting cleanup required to keep the file readable
  and well-formed.

Any other task-file edit during implementation flow requires explicit
User approval.

If the agent discovers that it has already made an unauthorized
Task-file edit during implementation flow, it must stop and disclose
in chat the exact unauthorized edit. It must make no further
Task-file edits except those explicitly approved by the User or
otherwise authorized by this guidance, and must then follow the
applicable shared route before continuing.

## 1. Task-file actions for the shared routes

Use the shared route semantics from
[implementation-flow-guidance.md](./implementation-flow-guidance.md).
This file gives the task-file actions for those routes.

For shared route **B. Pause the affected implementation and ask
 targeted User questions**:

- after a clarification that still fits inside the approved design,
  update only the minimal affected canonical sections in the active
  task file before continuing.

For shared route **C. Return to PLAN and seek renewed approval**:

- revise the task file as needed;
- use `../spec-loop-prepare-implementation-approval/SKILL.md` again
  before resuming implementation.

For shared route **D. Seek post-implementation User approval of an
 implemented deviation**:

- if the User approves keeping the deviation, update only the minimal
  affected canonical sections in the active task file before
  continuing the shared completion checkpoint.

## 2. Task-file `Implementation notes` expression

When the shared core requires recording `Implementation notes` on the
Task-file path, use this placement:

- without subtasks: task level;
- with subtasks: the active implementation subtask, unless a genuine
  task-level note is needed.

Example task-file content when notes are relevant:

- **Implementation notes:**
  - **Interpretations:**
    - Treated repeated invite submission as idempotent because the
      approved task required retry-safe behavior but did not define
      duplicate handling precisely.
  - **Tradeoffs:**
    - Kept duplicate detection in the service layer instead of the
      repository so duplicate handling stays testable without
      persistence-coupled error mapping.
  - **Open questions:**
    - Bulk invite behavior is out of scope for the current increment,
      so one deferred decision remains: should bulk invite flow use
      the same idempotency rule as single invite submission?

## 3. Task-file expression of `review`

When the shared completion checklist passes, express `review` on the
Task-file path using the Task-file Path Guidance lifecycle rules.

- Without subtasks: move the task from `in-progress` to `review`.
- With subtasks: move the current subtask from `in-progress` to
  `review`.
- When that subtask move leaves no more task-level or subtask-level
  work expected and every subtask is either `review` or `done`, move
  the overall task to `review` too.
- Do not move the overall task to `review` yet if more task-level work
  is still expected or another subtask is still `backlog` or
  `in-progress`.
