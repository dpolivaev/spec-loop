# Chat-based path guidance for `spec-loop-implementation-flow`

This file applies only on the chat-based planning path during
implementation.

Use it with the shared core in
[implementation-flow-guidance.md](./implementation-flow-guidance.md).
It says how the shared implementation-flow rules work on the chat-based
path.

Minimal orientation only:

- one active chat-based task at a time;
- no chat-based subtasks;
- no chat-based diagrams; and
- the current canonical chat-based task lives in chat.

If chat-based simplicity no longer holds, promote the work to the
task-file path before continuing executable work.

## 0. Authorized canonical chat updates

On the chat-based path, the canonical task in chat is the approved
artifact for this work plus the controlled post-implementation
record. Do not use canonical chat-based revisions as a scratchpad or
silently normalize them to the code.

Use `Task changes:` as the default canonical revision format during
implementation.

Treat the latest full chat-based task plus all later `Task changes:`
blocks as the current canonical state.

Only these canonical chat-based revisions are allowed:

- emit `Task changes:` with changed sections only, using the same
  change-list rules as
  `../spec-loop-plan-task/chat-based-path-guidance.md`, when the
  shared core gives authority for that change;
- emit `Task changes:` that records `Implementation notes` at the
  mandatory checkpoint when relevant notes content exists;
- re-emit a fresh full current chat-based task with title, identifier,
  and all current sections when the User asks for it, when
  reconstruction confidence is not high enough to continue safely, or
  when promotion needs a trustworthy full baseline; and
- perform minimal mechanical cleanup strictly incidental to one of the
  allowed revisions above.

Any other canonical chat-based revision during implementation flow
requires explicit User approval.

If the agent discovers that it has already made an unauthorized
canonical chat-based revision during implementation flow, it must stop
and disclose in chat the exact unauthorized revision. It must make no
further canonical chat-based revisions except those explicitly
approved by the User or otherwise authorized by this guidance, and
must then follow the applicable shared route before continuing.

Omitted sections in `Task changes:` mean unchanged. If the User asks
for the full current task, if a change cannot be expressed clearly as
a change list, or if safe reconstruction is no longer possible,
re-emit a fresh full current chat-based task instead.

## 1. Chat-based actions for the shared routes

Use the shared route semantics from
[implementation-flow-guidance.md](./implementation-flow-guidance.md).
This file gives the chat-based actions for those routes.

For shared route **B. Pause the affected implementation and ask
 targeted User questions**:

- after a clarification that still fits inside the approved design,
  emit `Task changes:` with only the minimal affected canonical
  sections before continuing.

For shared route **C. Return to PLAN and seek renewed approval**:

- also use that route when chat-based simplicity no longer holds,
  including when more than one active task is needed, diagrams would
  materially help, or reliable canonical reconstruction cannot be
  maintained safely in chat;
- if reconstruction confidence is insufficient, first re-emit a fresh
  full current chat-based task in chat;
- if the chat-based path must be promoted, reconstruct the task file
  from the current canonical chat-based task state;
- then use `../spec-loop-prepare-implementation-approval/SKILL.md`
  and regain normal task-file implementation approval before
  continuing executable work.

For shared route **D. Seek post-implementation User approval of an
 implemented deviation**:

- if the User approves keeping the deviation, emit `Task changes:`
  with only the minimal affected canonical sections before continuing
  the shared completion checkpoint.

## 2. Chat-based `Implementation notes` expression

When the shared core requires `Implementation notes` on the chat-based
path, record them in a canonical `Task changes:` block unless you
already need a full recovery re-emit.

If `Implementation notes` has content, include the complete
`Implementation notes` section in that canonical revision, not only
selected lines from it.

Example chat-based revision when notes are relevant:

```md
Task changes:

- **Implementation notes:**
  - **Tradeoffs:**
    - Kept duplicate detection in the service layer instead of the
      repository so duplicate handling stays testable without
      persistence-coupled error mapping.
```

## 3. Chat-based expression of `review`

When the shared completion checklist passes, express `review` on the
chat-based path by:

- emitting `Task changes:` for any canonical task changes;
- when `Implementation notes` has content, including the complete
  `Implementation notes` section in that canonical revision; and
- only then presenting the implemented work in chat as ready for User
  review.

If there are no canonical task changes and `Implementation notes` has
no content, no `Task changes:` block is required.

There is no chat-based folder or subtask-status move.
