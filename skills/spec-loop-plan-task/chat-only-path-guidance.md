# Chat-only Path Guidance

This file applies only on the chat-only planning path of
`spec-loop-plan-task`.

Read [common-task-guidance.md](common-task-guidance.md) first.
It defines the full format for task files without subtasks, section
meanings, requirements before work can start, how to keep task context,
formatting rules, and Test specification requirements for both forms.

This file covers only chat-only path mechanics: the chat task,
`Task changes:`, reconstruction, later-work handling, and promotion
to the task-file path.

`chat-only` means a planned task kept in chat, not taskless work. It
still needs a plan and explicit execution approval. Use the three-section
format below; the full format belongs to task files. If the User asks
for the full format in chat, ask whether to use the three-section
chat-only format or a task file. Do not choose for the User or silently
create a file.

The task-file prose-wrap requirement does not apply to chat-only work
unless the User asks for wrapped lines. Keep chat-only tasks readable
and structurally clear.

After execution approval for implementation work on the chat-only
path, follow
[spec-loop-implementation-flow/SKILL.md](../spec-loop-implementation-flow/SKILL.md) and its chat-only path
companion for implementation-time handling, task revisions,
`Implementation notes`, and chat-only `review`.

## Chat-only path mechanics

On the chat-only path, keep the work in chat only while confirmed
state can safely remain in chat.

If final clarification decisions kept only in chat would risk loss
through compaction or context loss, promote the work to the task-file
path before continuing. After that, do not return to the chat-only
path for that task.

### Compact chat-only format

Keep the shared title and identifier. Use exactly these three sections,
in this order:
- `Scope`: what is included and excluded, constraints, relevant facts,
  and final decisions with their reasons when useful;
- `Design`: enough detail to implement the agreed change, or the approach
  for an investigation;
- `Test specification`: the required verification anchors, or `N/A`
  only when the shared testing rules allow it.

All other required planning information goes in Scope. Follow the shared
rules for each kind of information. Do not add task sections or copy the
full template into subsections inside Scope. Approval and verification
rules still apply, as do the rules for switching to a task file.

### Canonical chat-only task

- emit an initial canonical task in the compact format with no subtasks
  or diagrams;
- always include the title line and exactly one identifier;
- include all required content; otherwise the task is not execution-ready;
- treat that emitted task as the current canonical chat-only task;
- keep only one active chat-only task at a time;
- ask permission to use chat-only under the form-selection rules; do not
  ask again if the User has already given that permission;
- when the task is ready, ask for explicit PLAN-to-EXECUTION approval.
  Permission to keep the task in chat does not approve execution.

When first offering chat-only, briefly explain that it avoids a task
file for simple work, but its current state can be harder to keep clear.
If that happens, repeat the complete task or move it to a file. Do not
repeat this warning unless the User is uninformed or the risk changes
in a way that matters.

### Chat-only task revisions

The initial emission is a complete task in the compact format. Later
revisions normally use `Task changes:` instead of reprinting it.

Use this marker:

`Task changes:`

List only changed sections from Scope, Design, and Test specification,
in that order. Omitted sections mean unchanged.

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

The most recent complete chat-only task plus later `Task changes:`
blocks is the current task state.

Reprint the complete current chat-only task only when:

- the User asks for it;
- reconstruction is no longer safe;
- promotion to a task file needs a complete current version; or
- the change cannot be expressed clearly as a change list.

### Reconstruction and recovery

If the latest complete task plus later `Task changes:` blocks no longer
make the current task clear, repeat the complete task with its title,
identifier, and three sections before continuing. If it is still unsafe
to keep the task in chat, move it to a task file. Follow all task-file
rules, including the full format and required checks. Repeating the
three-section chat task does not meet those task-file requirements.

### Investigation execution on the chat-only path

After approved investigation work, record the final output in Scope.
Keep it distinct from the starting evidence. Use `Task changes:` or
repeat the complete task, then present it as ready for User review.
If the result cannot safely stay in chat, move the task to a file before
presenting it as ready.

### Later work relation handling

When new work appears after a chat-only task, first decide whether
that earlier task is still an active planning task or has already been
presented as ready for User review.

If it has already been presented as ready for User review, use the
shared follow-up rule in
[common-task-guidance.md](common-task-guidance.md) to decide whether
later implementation follow-up may stay in the current chat-only task,
needs task-file promotion for separate tracked history, or is a new
follow-up task.

If it is still an active planning task, use the User's message or
later clarification to decide whether the new work extends that task
or is a new follow-up task. Ask only when that relation is not clear.

If the work stays in the same chat-only task, keep it there only while
chat-only safety still holds. Do not force task-file promotion solely
because the earlier implementation created an intermediate state.

If separate tracking within the same overall task is needed, use the
task-file path because chat-only work has no subtasks.

If it is a new follow-up task, re-run planning-form selection for that
new work item. A new chat-only task may start in chat without creating
any task file only when the earlier chat-only task is no longer active
and the new task independently still fits the chat-only criteria.

Do not allow two active chat-only tasks at once. If the new work
extends the active task or the combined work stops being simple, stay
in the same task or promote to the task-file path.

### Promotion triggers

If the chat-only path later needs heavier research, more than one
plausible implementation path, heavier verification, more than one
active task, diagrams that would materially help, or final
clarification decisions that would be unsafe to keep only in chat,
use `spec-loop-plan-task` again and promote the task to a task file
before continuing.

## Example

Good fit because the change is local, there is one clear
implementation path, and verification is one targeted regression test
plus the normal test run.

```md
# Task: Fix read-only viewer line truncation

- **Task Identifier:** 2026-05-24-viewer-truncation

- **Scope:**
  Prevent the read-only viewer from failing on overlong rendered lines.
  The fix is local and has one clear implementation path.
  - Constraints: keep existing behavior apart from safe truncation.
  - Current facts: the render path can return lines wider than the
    terminal; relevant files are that path and its tests. The
    living-project-document list is in `README.md`; no listed document
    is affected, and no project glossary exists.

- **Design:**
  Truncate rendered lines to the available width before display and
  add one regression test.

  - **Living project documents:** None affected

- **Test specification:**
  - **Automated tests:**
    - `ReadOnlyViewerTest`
      - `truncatesOverlongLinesToAvailableTerminalWidth`: overlong
        read-only viewer lines are truncated to the available terminal
        width.
```

Later clarification on the same task:

```md
Task changes:

- **Scope:**
  - Added:
    - `Constraints: preserve existing color rendering while truncating width.`

- **Design:**
  Truncate only the rendered line width. Do not change content before
  color formatting. Add one regression test.

  - **Living project documents:** None affected
```

## Example: non-trivial follow-up after a reviewed task-file task

Observed shape:

- an earlier task-file task is already in `review` or `done`;
- the User asks for a follow-up change;
- the follow-up spans multiple files or needs exploration;
- the User wants the next Spec Loop task in chat; and
- chat-only remains possible after normal planning-form selection.

Required assistant behavior:

1. classify the follow-up as non-trivial;
2. say that a Spec Loop task should be used;
3. run normal planning-form selection and do not offer chat-only if a
   task-file planning form, subtasks, or multiple task files are
   required;
4. if the User approves chat-only, emit the canonical task in the
   compact format; and
5. do not shorten the work to an informal plan or taskless handling
   just because the requested change is concrete.
