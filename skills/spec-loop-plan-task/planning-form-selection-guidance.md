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
- `taskless`: keep for standalone documentation, including ADR-only
  work. For implementation, keep only when the work is trivial and the
  User explicitly agrees. For investigation, keep only when no separate
  planning, tracking, or reviewable output is needed.
- `chat-only task`: leave in only when task state can safely stay in
  chat. Avoid it when task state needs durability, research or design
  is complex, subtasks or multiple task files are needed, project rules
  require a task file, or the User prefers a task file.
- `task file`: one durable work item, not split into tracked
  subtasks.
- `task file with subtasks`: one task split into separately tracked
  subtasks. For implementation work, these should usually each be one
  releasable vertical slice. Separate release after each subtask must
  be possible, but is normally not advisable.
- `multiple task files / backlog items`: separate task and release
  decisions. Use this when separate releases are advisable, not merely
  possible.

For non-trivial implementation, name any split points that would
produce separately reviewable tasks or subtasks as part of filtering.
For feature work, those tasks or subtasks should usually each be one
releasable vertical slice. If the same overall change contains
standalone refactoring plus later behavior change, treat that as a
candidate split:
- Keep `multiple task files / backlog items` when separate releases are
  advisable, including standalone refactoring only when that split also
  needs separate release decisions.
- Keep `task file with subtasks` when the resulting subtasks can stand
  alone but should normally be released together, including standalone
  refactoring plus later behavior change within one overall task.
- Keep plain `task file` when no useful releasable split is visible,
  or the User already rejected subtasks.
- If it is unclear whether a refactoring is standalone, do not remove
  `task file with subtasks` or `multiple task files / backlog items`
  on that basis alone. Clarify instead.
- If unsure, leave the form in.

Immediately after filtering:
1. If the User has already chosen a form for this work and it still meets
   the rules, use it. That counts as permission; do not ask again or
   choose a different form. If the User is now requesting a form that
   does not meet the rules, explain why and ask before choosing another.
2. Otherwise, a task file is the default. If `taskless` or `chat-only
   task` meets the rules above, recommend the lighter allowed form and
   ask permission to use it. Prefer `taskless` before `chat-only task`.
   Explain why it fits and wait for the answer. Use it only if the User
   agrees; if the User declines, use a file-based form.
3. For file-based work, use the split and release rules above to choose
   between `task file`, `task file with subtasks`, and `multiple task
   files / backlog items`. If those rules do not settle the choice, ask
   `What planning form should this work use?` and wait for the answer.
   List only allowed forms, using their exact names above. Follow the
   clarification skill's question format, with a recommendation and
   reason.

State the chosen form and why it fits. For plain `task file`, explain
why it has no subtasks. Do not relax any rule to use a lighter form.

Permission to use a form is not permission to start EXECUTION. A task
must be ready, and the User must explicitly approve execution. Taskless
implementation still needs explicit User agreement to implement.

If the current form no longer meets the rules, stop and choose another
form before continuing. Apply all rules for the new form, including its
planning and approval requirements.

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
