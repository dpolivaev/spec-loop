---
name: spec-loop-prepare-implementation-approval
description: >-
  Mandatory only on the task-file path of `spec-loop-plan-task`. Use
  when an active task file already exists and the next user-facing
  action would otherwise present that task for evaluation, feedback,
  review, or implementation approval.
---

This skill is mandatory only on the task-file path of the
`spec-loop-plan-task` workflow. It is not used for short planning path
work kept in chat.

Before work: read `../spec-loop-plan-task/SKILL.md`, follow all files it
requires, and apply the full `spec-loop-plan-task` bundle as shared
convention guidance. Then read
[implementation-approval-guidance.md](./implementation-approval-guidance.md).

Update the same active task file for user-facing review readiness. Do
not create a second task artifact or a new pre-implementation task
status.

Use `implementation-approval-guidance.md` as the authoritative source
for readiness checks, diagram-first preparation, duplication removal,
user-facing review gating, and approval-seeking output.

Follow `implementation-approval-guidance.md` for the allowed user-facing
outcomes and response rules.

After task-file implementation approval, implementation is governed by
`../spec-loop-implementation-flow/SKILL.md`.

If no active task file exists, if the current increment is unclear, or
if short-path planning is in use, do not use this skill. Continue task
work under the `spec-loop-plan-task` bundle instead.
