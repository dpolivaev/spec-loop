# Implementation-flow guidance for `spec-loop-implementation-flow`

This file is the shared implementation-flow core.

It reuses the full `spec-loop-plan-task` bundle, including
`shared-task-semantics.md` on both planning paths.
Use it with one of these path-specific companions:

- [fileless-path-guidance.md](./fileless-path-guidance.md); or
- [task-file-path-guidance.md](./task-file-path-guidance.md).

This core defines:

- implementation-time routes;
- authority to change canonical sections;
- the meaning of `review`;
- `Implementation notes`; and
- the completion checklist.

The path companion says how those shared rules work on the active
path.

## 0. Shared implementation-flow ownership

Read this file with exactly one path companion, chosen by
`SKILL.md`.

The path companion does not override this file.

When this file requires recording a change, `Implementation notes`,
or `review`, use the path companion to decide how to record it.

## 1. Shared implementation-time routes

When implementation reaches a decision point, only these routes are
allowed.

### A. Continue implementation

Use this route when implementation stays inside the approved design
boundary and no blocking ambiguity remains. Adding tests beyond `Test
specification` is allowed as long as those tests stay within the
approved increment.

### B. Pause the affected implementation and ask targeted User questions

Use this route when implementation reveals a business-logic ambiguity
or another issue that cannot be resolved safely from the approved task
alone, but no redesign is yet known.

Pause only the affected implementation area. Explain the exact reason
for the pause.

After the User clarifies the issue, update the canonical task
sections. If the clarification still fits inside the approved design,
the User's chat clarification plus the canonical task update is
sufficient authority to continue. No separate go-ahead step is
required.

### C. Return to PLAN and seek renewed approval

Use this route when the clarification or discovered issue changes the
approved contract or needs redesign.

Return to PLAN when the change affects any of the following:

- approved scope;
- business behavior or scenario contract;
- constraints or non-goals;
- new or renamed planned top-level production types;
- new or renamed planned structural elements with externally relevant
  meaning;
- new cross-class boundaries;
- new externally meaningful identifiers;
- serialized, persisted, or configuration contracts; or
- dependency decisions not already approved.

When this route applies, return to `spec-loop-plan-task`.
Use the path companion for the path-specific next steps before more
executable work continues.

### D. Seek post-implementation User approval of an implemented deviation

Use this route when the completion comparison shows that the
implementation violates the approved task content.

Describe the deviation exactly. Do not rewrite canonical task
sections before the User responds.

If the User approves keeping the deviation, update only the minimal
affected canonical task sections needed to reflect that explicit
approval and continue the completion checkpoint. This explicit post-
implementation approval is sufficient authority even if the approved
deviation would otherwise fall inside Section 1C. If the User refuses,
revise the implementation to match the approved task before review.

After such approval, return to PLAN only if the User explicitly asks
for replanning, redesign, or further changes beyond approving the
implemented result.

### E. Reach `review`

Use this route when the work is implemented, locally verified, and
ready for User review.

On both planning paths, `review` means the work matches the approved
task and has passed the required local checks.

The path companion defines how that state is expressed.

## 2. When canonical task sections may change

Change canonical task sections only when explicit authority exists.
Do not silently normalize the task to the code.

The existing approved task specification remains controlling by
default. Only one of the following may override earlier task wording
or decisions:

- an explicit User clarification statement;
- accepted review feedback; or
- explicit post-implementation User approval of keeping an
  implemented deviation.

Update only the minimal affected canonical section or sections. Do not
rewrite canonical task sections for ordinary internal implementation
choices, for unapproved implementation deviations, or for history that
belongs in `Implementation notes`.

Update whichever canonical section or sections the explicit
clarification, accepted review feedback, or explicit post-
implementation approval actually changes, including `Research`,
`Scenario`, `Constraints`, `Design`, or `Test specification`.

Record the authorized change as the path companion requires.

Keep accepted clarifications, approved deviations, and review
adjustments in canonical task sections rather than logging that
conversation history in `Implementation notes`.

## 3. Completion, `Implementation notes`, and `review`

Compare the implementation with the approved task and its
verification expectations.

For each finding:

- if it is an implementation-side interpretation, trade-off, or open
  question that does not violate the approved task content and is
  relevant to later review or development, record it in
  `Implementation notes`;
- if it shows that the implementation violates the approved task
  content, follow Section 1D before changing canonical task sections
  or reaching `review`;
- otherwise, if it reveals blocking uncertainty, needs explicit User
  clarification, or affects the Section 1C boundaries, raise it in
  chat and do not reach `review`.

### `Implementation notes` checkpoint

`Implementation notes` is not part of the canonical task definition.
It is checked only at the post-implementation checkpoint described
here.

Use `Implementation notes` only when they help later review.
They capture implementation decisions and reasons above code level.

It is not a live worklog during active coding. After implementation
and verification, before reaching `review`, preparing or proposing a
commit, or otherwise implying implementation closure, perform the
mandatory `Implementation notes` check.

If relevant `Interpretations`, `Tradeoffs`, or `Open questions` exist,
record them. If none exist, omit the section.
Use the path companion to place or express the section.

Use these fixed canonical subheadings when notes are present:

- `Interpretations`
- `Tradeoffs`
- `Open questions`

Omit empty subheadings instead of adding placeholders.

Meaning of the subheadings:

- `Interpretations` - choices made where the approved task definition
  was ambiguous but the approved contract remained intact;
- `Tradeoffs` - meaningful alternatives considered during
  implementation and why the chosen path won; and
- `Open questions` - explicit non-blocking deferred decisions. Each
  item should contain the deferred question and may include
  supplementary information before or after it.

Record policy:

- `Implementation notes` is a durable implementation record, not a
  temporary scratchpad.
- It records meaningful implementation-time decisions about
  interpretations, trade-offs, and any open questions relevant to
  later review or development.
- Record only the model's own implementation-time interpretations,
  trade-offs, and open questions.
- Do not use it as a log of User clarification, review feedback, or
  general conversation history.
- Do not require chronological ordering.
- Prefer organizing notes by meaning rather than by timestamp.
- Allow only light cleanup such as removing obvious noise,
  duplication, wording defects, or unhelpful ordering that does not
  erase the decision trail.

`Open questions` may remain only when all of the following are true:

- they do not block the current increment;
- they are explicitly left deferred; and
- they therefore do not prevent the current work from reaching
  `review`.

If an implementation question blocks the current increment, do not
leave it unresolved in `Implementation notes`. Clarify it in chat.
If an explicit User clarification statement can be kept without
returning to PLAN, update the affected canonical task section or
sections instead of recording that clarification in
`Implementation notes`.

### Completion checklist

Before reaching `review`, ensure that:

- the approved scope for the current increment is implemented;
- `Constraints`, `Design`, and `Test specification` are implemented as
  applicable for that increment;
- any canonical task-section changes have explicit authority from an
  explicit User clarification, accepted review feedback, or explicit
  post-implementation User approval of keeping an implemented
  deviation;
- required tests or other local verification from `Test
  specification` pass, unless the User waived them;
- required glossary work is complete;
- the mandatory `Implementation notes` check has been performed;
- when relevant `Implementation notes` content exists,
  `Implementation notes` is recorded;
- any remaining `Open questions` are non-blocking and explicitly left
  deferred; and
- no obvious leftovers remain, such as TODOs, placeholders, example
  names, temporary comments, or unused imports.

If any check fails, do not reach `review`.
Either complete the missing checkpoint work or follow the appropriate
route from Section 1.

## 4. Interaction with the other task skills

`spec-loop-plan-task` owns workflow routing, planning, fileless-path
maintenance before implementation approval, and approval gates.

`spec-loop-prepare-implementation-approval` owns pre-implementation
Task-file readiness and approval-seeking preparation.

After approval, this shared core plus the active path companion
govern route handling, authorized canonical updates,
`Implementation notes`, the path-specific expression of `review`, and
any needed fileless recovery or promotion.

Do not replace the planning or approval-preparation skills. Reuse them
when implementation uncovers a change that needs renewed planning or
approval under Section 1C outside the post-implementation approval
case handled by Section 1D.
