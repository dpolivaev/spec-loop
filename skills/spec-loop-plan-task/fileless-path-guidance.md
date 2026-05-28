# Fileless Path Guidance

This file applies only on the fileless planning path of
`spec-loop-plan-task`.

Read [common-task-guidance.md](./common-task-guidance.md) first.
That file defines the shared no-subtask main-task form, section
semantics, current-increment readiness rules, testing policy,
context-preservation rules, and formatting conventions used on both
planning paths.

This file adds only fileless-path mechanics: canonical chat-task
expression, section-only updates, reconstruction, later-work
relationship handling, and promotion to the task-file path.

## Fileless path mechanics

When the fileless planning path is in use, keep the work in chat only.

### Canonical fileless task

- emit an initial canonical fileless task in chat using the shared
  no-subtask main-task form and no diagrams;
- always include the title line and exactly one identifier;
- include any established sections in the exact shared order; on
  this initial emission, omitted sections mean not yet
  established;
- treat that emitted task as the current canonical fileless task;
- keep only one active fileless task in the conversation at a
  time;
- when the current increment reaches implementation readiness,
  present the canonical fileless task to the User as a request to
  approve both continuing on the fileless planning path without
  creating a task file and moving from planning into
  implementation from that fileless task.

When proposing the fileless path to a User who may not already be
informed, include one brief inline note that fileless avoids task-file
overhead for simple work but carries higher chat-alignment risk and
may require a full task re-emission or promotion if confidence drops.
Do not repeat that note when the User is already informed unless the
risk basis materially changes.

### Canonical updates

While the fileless path remains active, normal canonical updates may
re-emit only the changed sections. Those updates must use the exact
marker `Fileless task update:` followed by only the changed sections
with their exact shared section labels in the shared order. Omitted
sections in these updates mean unchanged, not removed. To remove a
previously present section, re-emit a fresh full current task
instead of a section-only update.

### Reconstruction and recovery

If the assistant cannot confidently reconstruct the current canonical
fileless task, it must first re-emit a fresh full current task in chat
with title, identifier, and all current sections before continuing. If
safe reconstruction still cannot be restored, promote the work to the
task-file path.

### Later work relation handling

When new work appears after a fileless task, use the User's message
or later clarification to decide whether it is a subtask or extension
of the earlier task, or a new follow-up task. Ask only when that
relation is not clear.

If the User's message or later clarification shows it is a subtask or
extension, use the task-file path because fileless work has no
subtasks.

If the User's message or later clarification shows it is a new
follow-up task, a new fileless task may start in chat without
creating any task file when the earlier fileless task is no longer
active and the new task independently still fits the fileless
criteria.

Do not allow two active fileless tasks at once. If the new work
extends the active task or the combined work stops being simple, stay
in the same task or promote to the task-file path.

### Promotion triggers

If the fileless path later needs heavier research, more than one
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
Fileless task update:

- **Constraints:**
  Preserve existing color rendering while truncating width.

- **Design:**
  Truncate only the rendered line width. Do not change content before
  color formatting.
```
