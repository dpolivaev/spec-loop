---
name: spec-loop-implementation-flow
description: >-
  Mandatory after implementation approval on either planning path of
  `spec-loop-plan-task`. Use when implementation deviates from the
  approved task, when uncertainty must be clarified instead of guessed
  through, when new blocking questions arise, or before presenting the
  current increment to the User. Governs shared implementation-time
  routing, canonical task updates when explicit User clarification,
  accepted review feedback, or explicit post-implementation User
  approval changes the current task definition, the post-
  implementation `Implementation notes` check, and, through exactly
  one path-specific companion, fileless or task-file implementation
  mechanics. May be applied in parallel with other
  implementation-related skills.
---

This skill is mandatory for approved implementation under the
`spec-loop-plan-task` workflow on both planning paths.

Use this skill when implementation deviates from the approved task,
when uncertainty must be clarified instead of guessed through, when
new blocking questions arise, or before presenting the current
increment to the User.

Before work: read `../spec-loop-plan-task/SKILL.md`, follow all files
it requires, and apply the full `spec-loop-plan-task` bundle as
shared convention guidance.

Then determine the active path:

- task-file path = an approved task file controls the current
  increment; or
- fileless path = an approved canonical fileless task in chat
  controls the current increment.

Then read
[implementation-flow-guidance.md](./implementation-flow-guidance.md)
fully.

Then read exactly one path-specific companion:

- on the fileless path, read
  [fileless-path-guidance.md](./fileless-path-guidance.md);
- on the task-file path, read
  [task-file-path-guidance.md](./task-file-path-guidance.md).

Keep using the approved task that controls the current increment.
Do not create a second implementation artifact or a new task status.
Edit the active task file or canonical fileless task only when the
shared core and the active path companion allow it.

`implementation-flow-guidance.md` is the shared core. It defines the
route rules, authority to change canonical sections, the meaning of
`review`, `Implementation notes`, and the completion checklist.

The selected path companion says how those rules work on the active
path, including fileless recovery or promotion or the task-file move
into `review`.

`Implementation notes` captures relevant implementation decisions and
reasons above code level. It is not a live worklog. After
implementation and verification, and before presenting work as ready,
moving task-file work to `review`, or proposing a commit, perform the
mandatory `Implementation notes` check from the shared core and the
active path companion.

This skill may be applied in parallel with other implementation-
related skills only when their instructions do not contradict this
skill or the governing `spec-loop-plan-task` bundle. If
implementation-related skills conflict or set different priorities,
stop and ask the User to clarify which priorities govern the work.

Follow the shared core and the active path companion.

If neither an approved active task file nor an approved active
fileless task exists, or if the current increment is unclear, do not
use this skill. Continue task work under the `spec-loop-plan-task`
bundle instead.
