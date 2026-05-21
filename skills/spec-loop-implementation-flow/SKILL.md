---
name: spec-loop-implementation-flow
description: >-
  Mandatory only on the task-file path of `spec-loop-plan-task` after
  implementation approval. Use when implementation deviates from the
  approved task, when uncertainty must be clarified instead of guessed
  through, when new blocking questions arise, or before presenting the
  current task or subtask to the User. Governs clarification routing,
  canonical task updates when explicit User clarification, accepted
  review feedback, or explicit post-implementation User approval
  changes the current task definition, the post-implementation
  `Implementation notes` check, and whether the increment may move to
  `review`. May be applied in parallel with other
  implementation-related skills.
---

This skill is mandatory only for approved task-file-path
implementation under the `spec-loop-plan-task` workflow. It is not used
for short planning path work kept in chat.

Use this skill when implementation deviates from the approved task,
when uncertainty must be clarified instead of guessed through, when
new blocking questions arise, or before presenting the current task or
subtask to the User.

Before work: read `../spec-loop-plan-task/SKILL.md`, follow all files it
requires, and apply the full `spec-loop-plan-task` bundle as shared
convention guidance. Then read
[implementation-flow-guidance.md](./implementation-flow-guidance.md).

Use the same active task file used for planning and approval.
Do not create a second implementation artifact or a new task status.
Do not edit that active task file except as explicitly authorized by
`implementation-flow-guidance.md`.

Use `implementation-flow-guidance.md` as the authoritative source for
implementation-phase authorized task-file updates, clarification
routing, post-implementation approval handling for implemented
deviations, `Implementation notes`, completion checks, and the
`in-progress` -> `review` transition.

`Implementation notes` simplifies change review by capturing relevant
implementation decisions and reasons above code level. It is not a
live worklog. After implementation and verification, and before
presenting work as ready, moving it to `review`, or proposing a
commit, perform the mandatory `Implementation notes` check defined in
`implementation-flow-guidance.md`.

This skill may be applied in parallel with other implementation-
related skills only when their instructions do not contradict this
skill or the governing `spec-loop-plan-task` bundle. If
implementation-related skills conflict or set different priorities,
stop and ask the User to clarify which priorities govern the work.

Follow `implementation-flow-guidance.md` for the implementation-time
routing and response rules.

If no approved active task file exists, if short-path planning is in
use, or if the current increment is unclear, do not use this skill.
Continue task work under the `spec-loop-plan-task` bundle instead.
