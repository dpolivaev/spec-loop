---
name: spec-loop-prepare-implementation-approval
description: >-
  Mandatory as part of the `spec-loop-plan-task` workflow. Use when an
  active task already exists and the next agent action would
  otherwise be to seek implementation approval from the user.
---

This skill is mandatory as part of the `spec-loop-plan-task` workflow. It is
not independently optional when `spec-loop-plan-task` is in use.

Before work: read `../spec-loop-plan-task/SKILL.md`, follow all files it
requires, and apply the full `spec-loop-plan-task` bundle as shared
convention guidance. Then read
[implementation-approval-guidance.md](./implementation-approval-guidance.md).

Update the same active task artifact for implementation-approval
readiness. Do not create a second task artifact or a new
pre-implementation task status.

Use `implementation-approval-guidance.md` as the authoritative
source for readiness checks, diagram-first preparation, duplication
removal, and approval-seeking output.

If readiness checks pass, seek implementation approval.
If no active task exists, if the current increment is unclear, or if
blocking gaps remain, do not seek approval. Return the exact gaps
and continue task work under the `spec-loop-plan-task` bundle instead.
