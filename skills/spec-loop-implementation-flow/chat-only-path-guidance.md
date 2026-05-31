# Chat-only path guidance for `spec-loop-implementation-flow`

This file applies only on the chat-only planning path during
implementation.

Use it with the shared core in
[implementation-flow-guidance.md](./implementation-flow-guidance.md).
It says how the shared implementation-flow rules work on the chat-only
path.

Basic facts:

- one active chat-only task at a time;
- no chat-only subtasks;
- no chat-only diagrams; and
- the current canonical chat-only task lives in chat.

If the work is no longer simple enough for chat, or confirmed
clarification decisions kept only in chat would risk loss through
compaction or context loss, promote it to the task-file path before
continuing executable work.

## 0. Authorized canonical chat updates

On the chat-only path, the task in chat is the approved task for
this work and the post-implementation record. Do not use chat-only
revisions as a scratchpad or silently rewrite the task to match the
code.

Use `Task changes:` as the default canonical revision format during
implementation.

The most recent full chat-only task plus later `Task changes:`
blocks is the current task state.

Only these chat-only revisions are allowed:

- emit `Task changes:` with changed sections only, following
  `../spec-loop-plan-task/chat-only-path-guidance.md`, when the
  shared core allows that change;
- emit `Task changes:` that records `Implementation notes` at the
  mandatory checkpoint when relevant notes content exists;
- re-emit a fresh full current chat-only task with title, identifier,
  and all current sections when the User asks for it, when
  reconstruction confidence is not high enough to continue safely, or
  when promotion needs a trustworthy full baseline; and
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
for the full current task, if a change cannot be expressed clearly as
a change list, or if the task can no longer be reconstructed safely,
re-emit a fresh full current chat-only task instead.

## 1. Chat-only actions for the shared routes

Use the shared route semantics from
[implementation-flow-guidance.md](./implementation-flow-guidance.md).
This file gives the chat-only actions for those routes.

For shared route **B. Pause the affected implementation and ask
 targeted User questions**:

- use this route only while confirmed clarification state can safely
  remain in chat; if not, switch to route **C**; and
- after a clarification that still fits inside the approved design,
  emit `Task changes:` with only the minimal affected canonical
  sections before continuing.

For shared route **C. Return to PLAN and seek renewed approval**:

- also use that route when chat-only simplicity no longer holds,
  including when more than one active task is needed, diagrams would
  materially help, reliable canonical reconstruction cannot be
  maintained safely in chat, or confirmed clarification decisions
  kept only in chat would risk loss through compaction or context
  loss;
- if reconstruction confidence is insufficient, first re-emit a fresh
  full current chat-only task in chat;
- if the chat-only path must be promoted, reconstruct the task file
  from the current canonical chat-only task state;
- then use `../spec-loop-prepare-implementation-approval/SKILL.md`
  and regain normal task-file implementation approval before
  continuing executable work.

For shared route **D. Seek post-implementation User approval of an
 implemented deviation**:

- if the User approves keeping the deviation, emit `Task changes:`
  with only the minimal affected canonical sections before continuing
  the shared completion checkpoint.

## 2. Chat-only `Implementation notes` expression

When the shared core requires `Implementation notes` on the chat-only
path, record them in a canonical `Task changes:` block unless you
already need a full recovery re-emit.

If `Implementation notes` has content, include the complete
`Implementation notes` section in that canonical revision, not only
selected lines from it.

Example chat-only revision when notes are relevant:

```md
Task changes:

- **Implementation notes:**
  - **Tradeoffs:**
    - Kept duplicate detection in the service layer instead of the
      repository so duplicate handling stays testable without
      persistence-coupled error mapping.
```

## 3. Chat-only expression of `review`

When the shared completion checklist passes, express `review` on the
chat-only path by:

- emitting `Task changes:` for any canonical task changes;
- when `Implementation notes` has content, including the complete
  `Implementation notes` section in that canonical revision; and
- only then presenting the implemented work in chat as ready for User
  review.

If there are no canonical task changes and `Implementation notes` has
no content, no `Task changes:` block is required.

There is no chat-only folder or subtask-status move.
