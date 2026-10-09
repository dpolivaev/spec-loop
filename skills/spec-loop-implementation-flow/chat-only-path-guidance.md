# Chat-only path guidance for `spec-loop-implementation-flow`

This file applies only on the chat-only planning path during
implementation.

Use it with the shared core in
[implementation-flow-guidance.md](implementation-flow-guidance.md).
It says how the shared implementation-flow rules work on the chat-only
path.

Basic facts:

- one active chat-only task at a time;
- no chat-only subtasks;
- no chat-only diagrams; and
- the current task lives in chat and has exactly Scope, Design, and Test
  specification. When repeating the complete task, keep these three
  sections.

If the work is no longer simple enough for chat, or confirmed
clarification decisions kept only in chat would risk loss through
compaction or context loss, promote it to the task-file path before
continuing implementation work.

## 0. Authorized canonical chat updates

The current chat-only task defines the approved work. Do not use task
updates as a scratchpad or change the task just to match the code.
Keep Implementation notes in the review message, outside the task.

Use `Task changes:` as the default canonical revision format during
implementation.

The most recent complete chat-only task plus later `Task changes:`
blocks is the current task state.

Only these chat-only revisions are allowed:

- emit `Task changes:` with changed sections only, following
  [spec-loop-plan-task/chat-only-path-guidance.md](../spec-loop-plan-task/chat-only-path-guidance.md), when the
  shared core allows that change;
- repeat the complete current task with its title, identifier, and three
  sections when the User asks, when its current state is not clear enough
  to continue safely, or before moving it to a task file; and
- perform minimal mechanical cleanup strictly incidental to one of the
  allowed revisions above.

Any other chat-only revision during implementation needs explicit
User approval.

If the agent finds that it already made an unauthorized chat-only
revision during implementation, it must stop and disclose the exact
revision in chat. It must make no further chat-only revisions except
those explicitly approved by the User or otherwise allowed by this
guidance, and must then follow the applicable shared route before
continuing.

Omitted sections in `Task changes:` mean unchanged. If the User asks
for the complete current task, if a change cannot be expressed clearly as
a change list, or if the task can no longer be reconstructed safely,
re-emit a complete current chat-only task instead.

## 1. Chat-only actions for the shared routes

Use the shared route semantics from
[implementation-flow-guidance.md](implementation-flow-guidance.md).
This file gives the chat-only actions for those routes.

For shared route **B. Pause the affected implementation and ask
 targeted User questions**:

- use this route only while final clarification state can safely
  remain in chat; if not, switch to route **C**; and
- after a clarification that still fits inside the approved design,
  emit `Task changes:` with only the minimal affected canonical
  sections before continuing.

For shared route **C. Return to PLAN and seek renewed approval**:

- also use this route when chat-only no longer fits: the work needs more
  than one active task, diagrams would help in a meaningful way, the
  current task cannot be reconstructed safely, or final decisions could
  be lost through compaction or context loss;
- if the current task is unclear, first repeat the complete task;
- if chat-only still meets the rules and the User has permitted it,
  update the task in chat and ask for renewed execution approval there;
- otherwise, use [spec-loop-plan-task/SKILL.md](../spec-loop-plan-task/SKILL.md)
  to move the work to the required file-based form. Create the task file
  from the current chat task and complete the task-file planning and
  required checks. Only then use
  [spec-loop-prepare-execution-approval/SKILL.md](../spec-loop-prepare-execution-approval/SKILL.md)
  and ask for renewed execution approval.

In either case, wait for execution approval before continuing. Do not
use the task-file approval-preparation skill while the task stays in chat.

For shared route **D. Seek post-implementation User approval of an
 implemented deviation**:

- if the User approves keeping the deviation, emit `Task changes:`
  with only the minimal affected canonical sections before continuing
  the shared completion checkpoint.

## 2. Chat-only review handoff

When the shared completion checklist passes:
- emit `Task changes:` for any authorized changes to Scope, Design, or
  Test specification; otherwise omit that block;
- put meaningful Implementation notes in the review message, outside the
  task, using the shared core's fixed subheadings;
- omit notes when the mandatory check finds no meaningful content; and
- present the implemented work as ready for User review.

Do not add Implementation notes to the task or its `Task changes:`.
There is no chat-only folder or subtask-status move.
