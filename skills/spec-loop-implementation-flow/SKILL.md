---
name: spec-loop-implementation-flow
description: >-
  Use this skill after execution approval for implementation work in
  an approved chat-only or task-file task. It governs
  implementation-time routing, canonical task updates, and the final
  review checkpoint.
---

Use this skill after execution approval for implementation work in an
approved chat-only or task-file task.

If no approved active task exists, return to
[spec-loop-plan-task/SKILL.md](../spec-loop-plan-task/SKILL.md).

## Core rules

- Keep the approved active task as the source of truth.
- Do not create a second implementation artifact or silently rewrite
  the task to match the code.
- If important open decisions appear and they could materially change
  scope, constraints, design, or test specification, use
  [spec-loop-clarify-task/SKILL.md](../spec-loop-clarify-task/SKILL.md) before continuing.
- Chat-only implementation is allowed only while confirmed task state
  can safely remain in chat. If resolved decisions kept only in chat
  would risk loss through compaction or context loss, promote to the
  task-file path before continuing.
- This skill governs only the approved current increment until it
  reaches `review`.
- If the User introduces additional implementation work outside that
  increment, return it to [spec-loop-plan-task/SKILL.md](../spec-loop-plan-task/SKILL.md) for route
  selection and any renewed execution approval.
- Before presenting work as ready, perform the `Implementation notes`
  check.
- If another implementation-related skill conflicts with this skill or
  the governing `spec-loop-plan-task` bundle, stop and ask the User
  which rules should govern.

## Read and path selection

- Read [spec-loop-plan-task/SKILL.md](../spec-loop-plan-task/SKILL.md) and the files it requires.
- Choose the active path:
  - task-file path = an approved task file controls the current
    increment
  - chat-only path = an approved canonical chat-only task in chat
    controls the current increment
- Then read:
  - [implementation-flow-guidance.md](implementation-flow-guidance.md)
  - the matching path companion for the active path:
    - [chat-only-path-guidance.md](chat-only-path-guidance.md)
    - [task-file-path-guidance.md](task-file-path-guidance.md)

Keep using the approved active task as the source of truth. Edit the
active task file or canonical chat-only task only when the shared
core and the active path companion allow it.

[implementation-flow-guidance.md](implementation-flow-guidance.md) defines the route rules, when
canonical task sections may change, the meaning of `review`,
`Implementation notes`, and the completion checklist.

The path companion defines the active path mechanics, including
chat-only recovery or promotion or the task-file move into `review`.

If the current increment is unclear, do not use this skill. Return to
[spec-loop-plan-task/SKILL.md](../spec-loop-plan-task/SKILL.md).
