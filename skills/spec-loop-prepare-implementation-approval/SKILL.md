---
name: spec-loop-prepare-implementation-approval
description: >-
  Mandatory only on the task-file path of `spec-loop-plan-task`. Use
  when an active task file already exists and the next user-facing
  action would otherwise present that task for evaluation, feedback,
  review, or implementation approval.
---

This skill is mandatory only on the task-file path of the
`spec-loop-plan-task` workflow. It is not used for chat-based planning
path work kept in chat.

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

If readiness checks find directly fixable task defects, update the
active task in place and re-run the readiness checks before producing
user-facing output.

If readiness checks find issues that can be resolved from existing
evidence, update the active task in place and re-run the readiness
checks before producing user-facing output.

If any material unresolved question remains and it is
user-preference-sensitive, needed to choose the next decision path, or
could materially change scope, constraints, design, or test
specification, do not end with a blocker list. Invoke
`../spec-loop-clarify-task/SKILL.md` in the same turn and resume this
skill after clarification before seeking implementation approval.

After task-file implementation approval, implementation is governed by
`../spec-loop-implementation-flow/SKILL.md`.

If no active task file exists, if the current increment is unclear, or
if chat-based planning is in use, do not use this skill. Continue task
work under the `spec-loop-plan-task` bundle instead.
