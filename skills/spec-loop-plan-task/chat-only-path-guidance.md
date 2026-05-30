# Chat-only Path Guidance

This file applies only on the chat-only planning path of
`spec-loop-plan-task`.

Read [common-task-guidance.md](./common-task-guidance.md) first.
That file defines the shared no-subtask main-task form, section
semantics, current-increment readiness rules, testing policy,
context-preservation rules, and formatting conventions used on both
planning paths.

This file covers only chat-only path mechanics: the chat task,
`Task changes:`, reconstruction, later-work handling, and promotion
to the task-file path.

User-facing term: `chat-only`. This is still a full structured Spec
Loop task kept in chat, not taskless work.

If the User asks for the sectioned task format, emit the full task in
chat. Do not replace a requested chat-only task with an informal
short plan.

The task-file prose-wrap requirement does not apply to chat-only work
unless the User asks for wrapped lines. Keep chat-only tasks readable
and structurally clear.

After implementation approval on the chat-only path, follow
`../spec-loop-implementation-flow/SKILL.md` and its chat-only path
companion for implementation-time handling, task revisions,
`Implementation notes`, and chat-only `review`.

## Chat-only path mechanics

On the chat-only path, keep the work in chat only.

### Canonical chat-only task

- emit an initial canonical chat-only task in chat using the shared
  no-subtask main-task form and no diagrams;
- always include the title line and exactly one identifier;
- include any established sections in the exact shared order; on
  this initial emission, omitted sections mean not yet
  established;
- treat that emitted task as the current canonical chat-only task;
- keep only one active chat-only task in the conversation at a
  time;
- when the current increment reaches implementation readiness,
  present the canonical chat-only task to the User as a request to
  approve both continuing on the chat-only planning path without
  creating a task file and moving from planning into
  implementation from that chat-only task.

When proposing the chat-only path to a User who may not already be
informed, include one brief inline note that chat-only avoids task-file
overhead for simple work but carries higher chat-alignment risk and
may require a full task re-emission or promotion if confidence drops.
Do not repeat that note when the User is already informed unless the
risk basis materially changes.

### Chat-only task revisions

The first chat-only task for a work item is a full task.

Later revisions normally use `Task changes:` instead of reprinting the
full task.

Use this marker:

`Task changes:`

List only changed sections, in the normal shared task section order.
Omitted sections mean unchanged.

Inside each changed section, either:

- use one or more of these labels:
  - `Added:`
  - `Removed:`
  - `Replaced:`

or

- provide the full new section body with no change labels.

Rules:

- `Added:` adds new text under that section.
- `Removed:` removes old text from that section.
- `Replaced:` includes both:
  - `from: ...`
  - `to: ...`
- If a changed section uses no change labels, treat the shown content
  as the full new section body.
- Do not mix unlabeled full-section text with `Added`, `Removed`, or
  `Replaced` in the same section.

Quote the old and new text so the current chat-only task can be
reconstructed without guessing.

The most recent full chat-only task plus later `Task changes:`
blocks is the current task state.

Reprint the full current chat-only task only when:

- the User asks for it;
- reconstruction is no longer safe;
- promotion to a task file needs a full current version; or
- the change cannot be expressed clearly as a change list.

### Reconstruction and recovery

If the current chat-only task cannot be reconstructed safely from the
most recent full task plus later `Task changes:` blocks, re-emit a
fresh full current task in chat with title, identifier, and all
current sections before continuing. If that still does not restore a
safe state, promote the work to the task-file path.

### Later work relation handling

When new work appears after a chat-only task, use the User's message
or later clarification to decide whether it is a subtask or extension
of the earlier task, or a new follow-up task. Ask only when that
relation is not clear.

If the User's message or later clarification shows it is a subtask or
extension, use the task-file path because chat-only work has no
subtasks.

If the User's message or later clarification shows it is a new
follow-up task, re-run route selection for that new work item. A new
chat-only task may start in chat without creating any task file only
when the earlier chat-only task is no longer active and the new task
independently still fits the chat-only criteria.

Do not allow two active chat-only tasks at once. If the new work
extends the active task or the combined work stops being simple, stay
in the same task or promote to the task-file path.

### Promotion triggers

If the chat-only path later needs heavier research, more than one
plausible implementation path, heavier verification, more than one
active task, or diagrams that would materially help, use
`spec-loop-plan-task` again and promote the task to a task file
before continuing.

## Example

Good fit because the change is local, there is one clear
implementation path, and verification is one targeted regression test
plus the normal test run.

```md
# Task: Fix read-only viewer line truncation

- **Task Identifier:** 2026-05-24-viewer-truncation

- **Scope:**
  Prevent the read-only viewer from failing on overlong rendered
  lines.

- **Motivation:**
  The current viewer can fail on long lines. The fix is local and has
  one clear implementation path.

- **Constraints:**
  Keep existing viewer behavior unchanged apart from safe truncation.

- **Briefing:**
  Relevant files: the read-only viewer render path and its tests.

- **Research:**
  The viewer can return lines wider than the terminal width.

- **Design:**
  Truncate rendered lines to the available width before display and
  add one regression test.

- **Test specification:**
  - **Automated tests:**
    - One targeted regression test for overlong viewer lines.
    - Full `npm test`.
  - **Manual tests:**
    - N/A.
```

Later clarification on the same task:

```md
Task changes:

- **Constraints:**
  - Added:
    - `Preserve existing color rendering while truncating width.`

- **Design:**
  Truncate only the rendered line width. Do not change content before
  color formatting. Add one regression test.
```

## Example: non-trivial follow-up after a reviewed task-file task

Observed shape:

- an earlier task-file task is already in `review` or `done`;
- the User asks for a follow-up change;
- the follow-up spans multiple files or needs exploration; and
- the User wants the next Spec Loop task in chat.

Required assistant behavior:

1. classify the follow-up as non-trivial;
2. say that a Spec Loop task should be used;
3. ask whether it may be chat-only or should use a task file;
4. if the User chooses chat-only, emit the canonical full chat-only
   task in the shared sectioned format; and
5. do not shorten the work to an informal plan or taskless handling
   just because the requested change is concrete.
