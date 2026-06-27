# Planning-form selection guidance

Consider five internal candidates:
- `taskless`;
- `chat-only task`;
- `task file`;
- `task file with subtasks`; and
- `multiple task files / backlog items`.

Filter them before prompting:
- `taskless` only when the Planning-form-specific handling section of
  [SKILL.md](SKILL.md) allows it; trivial implementation still needs
  explicit User agreement.
- Exclude `chat-only task` when task state needs durability, research
  or design is complex, subtasks or multiple task files are needed,
  project rules require a task file, or the User prefers a task file.
- Use `task file` for one durable, coherent increment without tracked
  slices.
- Use `task file with subtasks` when the work remains one overall task
  but needs separately processed vertical slices to reduce context or
  stage review. Implementation subtasks must still be releasable; this
  checks vertical-slice validity, not whether each subtask should be
  released independently.
- Use `multiple task files / backlog items` when increments have
  independent purpose, acceptance, or release value outside one task,
  and that independent value justifies the extra planning and review
  overhead.

After filtering:
- If one candidate remains, state it and the reason. If it is
  taskless implementation, ask for explicit User agreement.
- If two or more credible candidates remain, ask the clarification
  question `What planning form should this work use?` Use only the
  exact candidate names above as option text, omit excluded forms, and
  include `Recommended option:` and `Reason:` as required by the
  clarification question format.
- Ask whether work may be chat-only only when `chat-only task` is
  credible and no task-file planning form is recommended.

After selecting `task file with subtasks` or `multiple task files /
backlog items`, hand off to
[spec-loop-plan-work-breakdown](../spec-loop-plan-work-breakdown/SKILL.md)
before drafting or updating the durable artifact.

When later implementation follow-up appears after a task or subtask
already in `review` or `done`, or after a chat-only task already
presented as ready for User review, return to PLAN and re-run
planning-form selection. Reuse the existing task artifact only when
the shared follow-up rule in
[common-task-guidance.md](common-task-guidance.md) still allows it to
govern the same active work item.
