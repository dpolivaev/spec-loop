# Fileless-path guidance for `spec-loop-implementation-flow`

This file applies only on the fileless planning path during
implementation.

Use it with the shared core in
[implementation-flow-guidance.md](./implementation-flow-guidance.md).
It says how the shared implementation-flow rules work on the fileless
path.

Minimal orientation only:

- one active fileless task at a time;
- no fileless subtasks;
- no fileless diagrams; and
- the current canonical fileless task lives in chat.

If fileless simplicity no longer holds, promote the work to the
task-file path before continuing executable work.

## 0. Authorized canonical chat updates

On the fileless path, the canonical task in chat is the approved
artifact for this work plus the controlled post-implementation
record. Do not use canonical fileless updates as a scratchpad or
silently normalize them to the code.

Only these canonical fileless updates are allowed:

- emit `Fileless task update:` followed by only the changed sections
  with their exact shared section labels when the shared core gives
  authority for that change;
- emit `Fileless task update:` followed by `Implementation notes` at
  the mandatory checkpoint when relevant notes content exists;
- re-emit a fresh full current fileless task with title, identifier,
  and all current sections when reconstruction confidence is not high
  enough to continue safely or when promotion needs a trustworthy
  canonical baseline; and
- perform minimal mechanical cleanup strictly incidental to one of the
  allowed updates above.

Any other canonical fileless update during implementation flow
requires explicit User approval.

If the agent discovers that it has already made an unauthorized
canonical fileless update during implementation flow, it must stop and
disclose in chat the exact unauthorized update. It must make no
further canonical fileless updates except those explicitly approved by
the User or otherwise authorized by this guidance, and must then
follow the applicable shared route before continuing.

Omitted sections in a section-only fileless update mean unchanged, not
removed. To remove a previously present section on the fileless path,
re-emit a fresh full current fileless task instead of a section-only
update.

## 1. Fileless actions for the shared routes

Use the shared route semantics from
[implementation-flow-guidance.md](./implementation-flow-guidance.md).
This file gives the fileless actions for those routes.

For shared route **B. Pause the affected implementation and ask
 targeted User questions**:

- after a clarification that still fits inside the approved design,
  emit `Fileless task update:` with only the minimal affected
  canonical sections before continuing.

For shared route **C. Return to PLAN and seek renewed approval**:

- also use that route when fileless simplicity no longer holds,
  including when more than one active task is needed, diagrams would
  materially help, or reliable canonical reconstruction cannot be
  maintained safely in chat;
- if reconstruction confidence is insufficient, first re-emit a fresh
  full current fileless task in chat;
- if the fileless path must be promoted, reconstruct the task file
  from the current canonical fileless task state;
- then use `../spec-loop-prepare-implementation-approval/SKILL.md`
  and regain normal task-file implementation approval before
  continuing executable work.

For shared route **D. Seek post-implementation User approval of an
 implemented deviation**:

- if the User approves keeping the deviation, emit `Fileless task
  update:` with only the minimal affected canonical sections before
  continuing the shared completion checkpoint.

## 2. Fileless `Implementation notes` expression

When the shared core requires `Implementation notes` on the fileless
path, record them in a canonical `Fileless task update:` block unless
you already need a full recovery re-emit.

Example fileless update when notes are relevant:

```md
Fileless task update:

- **Implementation notes:**
  - **Tradeoffs:**
    - Kept duplicate detection in the service layer instead of the
      repository so duplicate handling stays testable without
      persistence-coupled error mapping.
```

## 3. Fileless expression of `review`

When the shared completion checklist passes, express `review` on the
fileless path by:

- recording any required canonical fileless updates, including
  `Implementation notes` when relevant; and
- presenting the implemented work in chat as ready for User review.

There is no fileless folder or subtask-status move.
