# Planning-form selection guidance

Choose from:
- `taskless`;
- `chat-only task`;
- `task file`;
- `task file with subtasks`; and
- `multiple task files / backlog items`.

A User request like `add task` or `add subtask` does not decide the
planning form or whether breakdown is needed. Evaluate the work
itself.

A standalone refactoring makes no intended externally observable
behavior change and is still acceptable if merged even if the later
feature item is never implemented.

Start with all forms. Remove a form only when the reason is obvious:
- `taskless`: keep for standalone documentation and ADR-only work. For
  implementation, keep only when the work is trivial and the User
  explicitly agrees. For investigation, keep only when no separate
  planning, tracking, or reviewable output is needed.
- `chat-only task`: leave in only when task state can safely stay in
  chat. Avoid it when task state needs durability, research or design
  is complex, subtasks or multiple task files are needed, project rules
  require a task file, or the User prefers a task file.
- `task file`: one durable work item, not split into tracked
  subtasks.
- `task file with subtasks`: one task split into separately tracked
  subtasks. For implementation work, these should usually be
  releasable vertical slices. Separate release after each subtask
  must be possible, but is normally not advisable.
- `multiple task files / backlog items`: separate task and release
  decisions. Use this when separate releases are advisable, not merely
  possible.

For non-trivial implementation, name any parts of the work that can be
reviewed separately as part of filtering. If the same overall change
contains standalone refactoring plus later behavior change, treat them
as separate candidate parts:
- Keep `multiple task files / backlog items` when separate releases are
  advisable, including standalone refactoring only when that split also
  needs separate release decisions.
- Keep `task file with subtasks` when those parts can stand alone but
  should normally be released together, including standalone
  refactoring plus later behavior change within one overall task.
- Keep plain `task file` when no useful releasable split is visible,
  or the User already rejected subtasks.
- If it is unclear whether a refactoring is standalone, do not remove
  `task file with subtasks` or `multiple task files / backlog items`
  on that basis alone. Clarify instead.
- If unsure, leave the form in.

Immediately after filtering:
- If exactly one form remains, state it and the reason. If it is plain
  `task file`, include the no-subtask reason. If it is taskless
  implementation, ask for explicit User agreement.
- If more than one form remains, ask `What planning form should this
  work use?` Use only the exact form names above as option text, omit
  removed forms, and include `Recommended option:` and `Reason:` as
  required by the clarification question format.
- Ask whether work may be chat-only only when `chat-only task` remains
  possible and no task-file planning form is recommended.

After selecting `task file with subtasks` or `multiple task files /
backlog items`, hand off to
[spec-loop-plan-work-breakdown](../spec-loop-plan-work-breakdown/SKILL.md)
before detailed clarification or planning beyond the minimum needed for
selection and a sound rough breakdown.

If later planning or design reveals a new independently acceptable
split, re-run planning-form selection before updating the durable
artifact. For example, do this when a requested single task or subtask
turns out to contain standalone refactoring plus later behavior
change.

When later implementation follow-up appears after a task or subtask
already in `review` or `done`, or after a chat-only task already
presented as ready for User review, return to PLAN and re-run
planning-form selection. Reuse the existing task artifact only when
the shared follow-up rule in
[common-task-guidance.md](common-task-guidance.md) still allows it to
govern the same active work item.
