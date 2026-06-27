# Implementation-flow guidance for `spec-loop-implementation-flow`

This file is the shared implementation-flow core.

Use it with exactly one path companion chosen by [SKILL.md](SKILL.md):
- [chat-only-path-guidance.md](chat-only-path-guidance.md)
- [task-file-path-guidance.md](task-file-path-guidance.md)

This core owns:
- implementation-time routes;
- authority to change canonical task sections;
- the meaning of `review`;
- `Implementation notes`; and
- the completion checkpoint.

The path companion adds only path-specific mechanics for recording or
expressing those shared rules.

## 1. Shared ownership and source of truth

- Read this file with exactly one path companion.
- The path companion does not override this file.
- The approved active task remains the source of truth.
- Do not create a second implementation artifact or silently rewrite
  the task to match the code.
- When this file requires recording a canonical change,
  `Implementation notes`, or `review`, use the path companion to
  decide how to express it.

Implementation-start checklist:
- stay inside the approved current increment;
- return new implementation work outside that increment to PLAN;
- do not make implementation edits outside the approved design
  boundary unless this guidance or the User explicitly authorizes
  them;
- if `Implementation notes` are later needed, use only the fixed
  subheadings `Interpretations`, `Tradeoffs`, and `Open questions`;
- do not write `Implementation notes` as a chronological worklog,
  test log, user clarification history, or scratchpad; and
- treat `Manual tests` as optional human-review hints, not as
  blockers for `review`.

## 2. Allowed implementation-time routes

Only these routes are allowed when implementation reaches a decision
point.

### A. Continue implementation

Use this route when implementation stays inside the approved design
boundary and no blocking ambiguity remains.

Adding tests beyond `Test specification` is allowed as long as those
tests stay within the approved increment.

### B. Pause the affected area and clarify

Use this route when implementation reveals a business-logic ambiguity
or another issue that cannot be resolved safely from the approved task
alone, but no redesign is yet known.

- Pause only the affected implementation area.
- Explain the exact reason for the pause.
- Clarification may take more than one step.
- Use the path companion to decide whether interim canonical task
  updates are required to preserve confirmed state.

When the relevant clarification result is confirmed, update the minimal
affected canonical section or sections as the path companion requires.
If the clarification still fits inside the approved design, the user's
confirmed clarification plus that canonical update is sufficient
authority to continue. No separate go-ahead step is required.

### C. Return to PLAN and seek renewed approval

Use this route when the discovered issue or clarification changes the
approved contract or needs redesign.

Return to PLAN when the change affects any of the following:
- approved scope;
- business behavior or scenario contract;
- approved `Glossary` meaning, shared domain terms, or glossary usage
  constraints;
- when no explicit project glossary exists, current domain language
  derived from `Research` and the existing codebase;
- constraints or non-goals;
- new or renamed planned top-level production types;
- new or renamed planned structural elements with externally relevant
  meaning;
- new cross-class boundaries;
- new externally meaningful identifiers;
- serialized, persisted, or configuration contracts; or
- dependency decisions not already approved.

When this route applies, return to `spec-loop-plan-task`. Use the path
companion for the next path-specific steps before more implementation work
continues.

### D. Seek post-implementation approval of an implemented deviation

Use this route when the completion comparison shows that the
implementation violates the approved task content.

- Describe the deviation exactly.
- Do not rewrite canonical task sections before the user responds.
- If the user approves keeping the deviation, update only the minimal
  affected canonical section or sections needed to reflect that
  approval, then continue the completion checkpoint.
- Do not use this route to approve a deviation that changes approved
  `Scenario` meaning, approved `Glossary` meaning, shared domain
  terms, glossary usage constraints, or — when no explicit project
  glossary exists — current domain language derived from `Research`
  and the existing codebase. Use route C instead.
- That explicit post-implementation approval is sufficient authority
  only for deviations that stay outside those route-C domain-language
  boundaries.
- If the user refuses, revise the implementation to match the approved
  task before review.

After such approval, return to PLAN only if the user explicitly asks
for replanning, redesign, or further changes beyond approving the
implemented result.

### E. Reach `review`

Use this route when the work is implemented, required automated
verification and project-level checks have passed, and it is ready for
user review.

Reaching `review` means the current implemented state matches the
approved task and has passed the automated tests that implement the
`Test specification` plus any required project-level checks at that
checkpoint. Optional `Manual tests` listed as human-review hints need
not be performed before `review`. The path companion defines how that
state is expressed.

After route E is expressed, this skill is done for that increment.
Later implementation follow-up returns to `spec-loop-plan-task`.

## 3. Canonical task updates during implementation

Change canonical task sections only when explicit authority exists. Do
not silently normalize the task to the code.

Only these sources may override earlier canonical task wording or
decisions:
- an explicit user clarification statement; or
- explicit post-implementation user approval of keeping an implemented
  deviation.

Update only the minimal affected canonical section or sections.
Do not rewrite canonical task sections for:
- ordinary internal implementation choices;
- unapproved implementation deviations; or
- history that belongs in `Implementation notes`.

After implementation starts, extend `Research` only with newly
revealed relevant facts about the original pre-implementation state.
Later `Research` entries should say when they amend earlier findings.
Do not use canonical `Research` to record repository states created
during the current increment.
Any edit that makes `Research` reflect state created by work on
this task is forbidden.

Patch other changed canonical sections to current truth when explicit
authority exists, including `Scenario`, `Glossary`, `Constraints`,
`Design`, and `Test specification` as applicable.

When project glossary file work happens during implementation, use it
only to record approved task `Glossary` meaning. If no explicit
project glossary exists and the approved task does not require
creating one, keep glossary meaning in the task artifact rather than
inventing a project glossary edit.

Record the authorized change as the path companion requires.

Keep accepted clarifications and approved deviations in canonical task
sections rather than logging that conversation history in
`Implementation notes`.

## 4. `Implementation notes`

`Implementation notes` is not part of the canonical task definition.
It is checked only at the post-implementation checkpoint.

Use it when it might help later review or development. If present, it
describes the current implementation state, not the target state in
canonical sections. It records implementation-time decisions and
reasons above code level.

It is not a live worklog during active coding. After implementation and
verification, and before reaching `review`, preparing or proposing a
commit, or otherwise implying implementation closure, perform the
mandatory `Implementation notes` check.

If relevant `Interpretations`, `Tradeoffs`, or `Open questions` exist,
record them. If none exist, omit the section.

Use these fixed subheadings when notes are present:
- `Interpretations`
- `Tradeoffs`
- `Open questions`

Omit empty subheadings instead of placeholders.

Meaning of the subheadings:
- `Interpretations` = ambiguity resolved without changing the approved
  contract;
- `Tradeoffs` = meaningful alternatives considered and why the chosen
  path won; and
- `Open questions` = explicit non-blocking deferred decisions.

Record policy:
- keep `Implementation notes` as a durable implementation record, not a
  scratchpad;
- record only the model's own implementation-time interpretations,
  trade-offs, and open questions;
- do not use it for user clarification history, review feedback, or
  general conversation history;
- do not require chronological ordering;
- after each implementation phase, update `Implementation notes` to
  match the current implementation state and remove superseded notes; and
- during later review or planning phases, keep `Implementation notes`
  aligned to the current implementation state rather than rewriting it
  to match the current target.

`Open questions` may remain only when all of the following are true:
- they do not block the current increment;
- they are explicitly left deferred; and
- they do not prevent the work from reaching `review`.

If an implementation question blocks the current increment, do not
leave it unresolved in `Implementation notes`. Clarify it in chat.
If an explicit user clarification can be kept without returning to
PLAN, update the affected canonical section or sections instead of
recording that clarification in `Implementation notes`.

## 5. Completion checkpoint

Compare the implementation with the approved task and its verification
expectations.

For each finding:
- if it is an implementation-side interpretation, trade-off, or open
  question that does not violate the approved task content and matters
  to later review or development, record it in `Implementation notes`;
- if it shows that the implementation violates the approved task
  content, follow route D before changing canonical task sections or
  reaching `review`;
- otherwise, if it reveals blocking uncertainty, needs explicit user
  clarification, or affects the route-C boundaries, raise it in chat
  and do not reach `review`.

Before reaching `review`, ensure that:
- the approved scope for the current increment is implemented;
- `Scenario`, `Glossary`, `Constraints`, `Design`, and `Test
  specification` are implemented as applicable for that increment;
- any canonical task-section changes have explicit authority from an
  explicit user clarification or explicit post-implementation user
  approval of keeping an implemented deviation;
- automated tests that implement `Test specification` and any required
  project-level checks pass, unless the user waived them;
- any `Manual tests` listed as optional human-review hints are not
  treated as blockers for `review`, and are not claimed as performed
  unless they were actually performed;
- if `Scenario` or `Glossary` is present, implementation and
  verification still match their approved behavior and domain-language
  contract;
- if task `Glossary` is present, `Design`, `Test specification`, and
  any project glossary file changes still match its approved terms;
- required glossary work is complete, including any planned project
  glossary creation or update;
- the mandatory `Implementation notes` check has been performed;
- when relevant `Implementation notes` content exists,
  `Implementation notes` is recorded;
- when `Implementation notes` is present, it matches the current
  implementation state and outdated notes have been removed;
- any remaining `Open questions` are non-blocking and explicitly left
  deferred; and
- no obvious leftovers remain, such as TODOs, placeholders, example
  names, temporary comments, or unused imports.

If any check fails, do not reach `review`. Complete the missing work or
follow the appropriate route from Section 2.

## 6. Handoff triggers

Use these handoffs only when the trigger occurs. Otherwise stay in this
implementation flow for the approved current increment.

- If there is no approved active task, stop and use
  [spec-loop-plan-task](../spec-loop-plan-task/SKILL.md).
- If implementation uncovers a change that needs renewed planning or
  execution approval under route C, return to
  [spec-loop-plan-task](../spec-loop-plan-task/SKILL.md). For task-file
  work, execution can resume only after
  [approval preparation](../spec-loop-prepare-execution-approval/SKILL.md)
  prepares renewed approval and the User approves it.
- If additional implementation work appears outside the approved
  current increment before route E, do not continue in execution by
  inertia. Return it to
  [spec-loop-plan-task](../spec-loop-plan-task/SKILL.md) for
  planning-form selection.
