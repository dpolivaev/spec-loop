---
name: spec-loop-prepare-execution-approval
description: >-
  Mandatory only on the task-file path of `spec-loop-plan-task`. Use
  when an active task file already exists and the next user-facing
  action would otherwise present task-file work for evaluation,
  feedback, review, or execution approval.
---

This skill is mandatory only on the task-file path of the
`spec-loop-plan-task` workflow. It is not used for chat-only planning
path work kept in chat.

Before work: read [spec-loop-plan-task/SKILL.md](../spec-loop-plan-task/SKILL.md), follow all files it
requires, and apply the full `spec-loop-plan-task` bundle as shared
convention guidance. Then read
[execution-approval-guidance.md](execution-approval-guidance.md).

Update the same active task file for user-facing review readiness. Do
not create a second task artifact or a new pre-execution task status.

Use [execution-approval-guidance.md](execution-approval-guidance.md) as the authoritative source
for readiness checks, diagram-first preparation, duplication removal,
user-facing review gating, and approval-seeking output.

Follow [execution-approval-guidance.md](execution-approval-guidance.md) for the allowed user-facing
outcomes and response rules.

If readiness checks find directly fixable task defects, update the
active task in place and re-run the readiness checks before producing
user-facing output, except for structural decomposition changes
handled specially by [execution-approval-guidance.md](execution-approval-guidance.md).

If readiness checks find issues that can be resolved from existing
evidence, update the active task in place and re-run the readiness
checks before producing user-facing output.

If readiness checks show that task-file compaction is needed, use
[spec-loop-compact-task-file/SKILL.md](../spec-loop-compact-task-file/SKILL.md) and then resume this skill.

If any important open decision remains and it is
user-preference-sensitive, needed to choose the next path, or could
materially change scope, constraints, design, or test specification,
do not end with a blocker list. Invoke
[spec-loop-clarify-task/SKILL.md](../spec-loop-clarify-task/SKILL.md) in the same turn, then resume this
skill after clarification before seeking execution approval.

After task-file execution approval:
- implementation work is governed by
  [spec-loop-implementation-flow/SKILL.md](../spec-loop-implementation-flow/SKILL.md);
- investigation work stays under the `spec-loop-plan-task` task-file
  path: perform only the approved investigation, record final output in
  `Findings`, satisfy any `Test specification`, and move the task or
  subtask to `review`.

If no active task file exists, if the current increment is unclear, or
if chat-only planning is in use, do not use this skill. Continue task
work under the `spec-loop-plan-task` bundle instead.
