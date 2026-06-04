---
name: spec-loop-clarify-task
description: >-
  Clarify a proposed task, plan, or design update by resolving the
  highest-value unresolved decisions until the inputs are ready for
  task creation, task planning, task/design updates, or safe
  implementation continuation. Use this as the default clarification
  path for Spec Loop task creation, task updates, and any planning,
  approval, or implementation step that encounters material unresolved
  questions. When clarification ends, resume the invoking workflow. It
  may also be used for general grilling when explicitly selected or
  when no other default grilling skill is available.
---

Use this skill when a new task, task update, or design update is
underspecified, when material boundary decisions remain open, when
planning or implementation cannot continue safely because the
relevant task context is still unclear after rereading the needed
task sections and diagrams, or when the user wants to stress-test a
plan or be grilled on a design.

For Spec Loop task creation and task updates, this is the default
clarification path whenever material unresolved questions remain.

When both this skill and a generic grill-me variant are available,
prefer this skill for Spec Loop task creation, task updates, and
design updates. If no default general grilling skill is available, it
may also be used for general grilling.

When clarification ends, hand control back to the workflow that
invoked this skill:
- task creation and task updates normally resume
  `../spec-loop-plan-task/SKILL.md`;
- ADR-writing clarification resumes
  `../spec-loop-write-adr/SKILL.md`;
- approval-preparation clarification resumes
  `../spec-loop-prepare-implementation-approval/SKILL.md`;
- implementation-time clarification resumes
  `../spec-loop-implementation-flow/SKILL.md`.

When this skill is invoked from planning or approval preparation, it
still runs in PLAN. It does not freeze the governing artifact.

During PLAN, revise the governing artifact in place as needed for the
current work item. Planning-artifact changes are allowed. Executable
changes are not.

When this skill is invoked from ADR writing, revise the ADR draft in
place as needed for the current work item. Documentation-artifact
changes are allowed. Executable changes are not.

When this skill is invoked from implementation, phase handling
remains governed by `../spec-loop-implementation-flow/SKILL.md`.

## Clarification grill level

Clarification is the goal. It combines two methods:
- reasoning = resolve what existing evidence already determines
  without new user input; and
- grilling = use decision batches and direct questions to expose,
  test, or confirm remaining uncertainty.

The current clarification grill level controls how strongly
clarification relies on grilling rather than reasoning for the
current work item.

Use the current clarification grill level already in force for the
current work item when one exists. If the user has not set one for
the work item and no session or project default is already in force,
default to `medium`.

Levels:
- `light` = stronger bias toward reasoning and lighter grilling:
  show fewer final decisions in chat through decision batches, ask
  fewer direct questions during the clarification session, and exit
  earlier once clarification is safe;
- `medium` = balanced default; and
- `heavy` = stronger bias toward grilling: show more final decisions
  in chat through decision batches, ask more direct questions during
  the clarification session, and exit later.

On the first clarification turn for a task or work item in the
current conversation, state the current clarification grill level and
briefly explain what it changes: `light` means fewer questions and
fewer decisions shown in chat; `heavy` means more. Do not repeat
this notice on later clarification turns for the same task or work
item unless the user asks or the level changes. When clarification
starts for a different task or work item, give the notice again.

A user-facing clarification step is either one presented decision
batch or one `Question:` block that requires new user input.

After every clarification step, re-run the exit check. Spend another
clarification step only when the remaining unresolved point is
material enough to justify slowing the User down at the current
clarification grill level.

The clarification grill level affects chat-time surfacing and
question frequency during the clarification session. It does not
change batch size, question format, the definition of a material
unresolved question, or whether final clarification decisions must be
preserved in the durable record.

Select the next unresolved branch by descending importance and uncertainty. Once a branch is selected, traverse it depth first, resolving dependencies one-by-one. Start with a brief, provisional overview of the most important currently visible unresolved branches and which branch you will address first. This overview is a map, not a commitment to an exact final question list.

Treat unresolved branches as including both behavior-level
alternatives and material boundary distinctions that the design is
not allowed to guess.

During clarification, ask only when different answers would
materially change scope, behavior, policy, conceptual model,
conceptual contract boundaries, acceptance logic, route, Design, or
Test specification, and the answer is not already determined by
confirmed user choices, existing evidence, or a low-risk mechanical
consequence of those choices.

If a distinction would change whether two concepts are the same
thing, different concepts, or governed by different rules, treat it
as a material boundary decision and clarify it.

Do not use clarification for exact names, wording, labels, field
names, enum names, or other cheap-to-change design text when the
boundary is already settled. Put that in Design and let the User
review it there.

Stop clarification when the remaining unresolved points would mainly
change the shape of the design draft rather than those boundaries.

If drafting or reviewing Design exposes a new material boundary
decision, return to clarification before continuing.

Treat existing glossary language, task materials, and code as
first-class evidence during clarification:
- If the user's wording conflicts with existing glossary or task
  language in a way that could change the conceptual model or rules,
  call it out immediately and resolve the distinction.
- Stress-test behavior-level alternatives and material boundary
  distinctions with concrete scenarios, edge cases, and boundary
  cases.
- When the user states current behavior, boundaries, or design
  constraints, compare that claim with the codebase and existing
  task materials when possible. Surface contradictions explicitly
  instead of smoothing them over.

If clarification resolves or changes shared domain terms, record that glossary follow-up is required through the normal Spec Loop glossary path. Put the note in the active task when one exists or is being prepared.

Clarification always runs against one governing artifact for the
current work item.
- When invoked from task planning, approval preparation, or
  implementation, the governing artifact is the active task artifact.
- When invoked from ADR writing, the governing artifact is the ADR
  being drafted or updated.

Keep final clarification decisions in the governing artifact's
`Analysis` section.

In both artifact types, keep accepted final clarification results
only. Do not put open questions, options, confidence values,
tentative assumptions, or transient working notes there.

- In task artifacts, follow
  `../spec-loop-plan-task/common-task-guidance.md`: place `Analysis`
  immediately after `Research` and record each final clarification
  decision as `- <decision> because <reason>.`
- In ADRs, follow `../spec-loop-write-adr/adr-format.md`: keep
  `Analysis` as compact ADR-relevant bullets supporting the chosen
  decision, not as the full task ledger.

Clarification is cross-cutting. A final clarification decision may
affect any section of the governing artifact. When a clarification
decision becomes final, update the governing artifact's `Analysis`
section and every other affected section of that artifact.

Clarification uses these state terms:
- **Open question** = not final and still needs user input.
- **Pending surfaced decision** = queued or presented in a decision
  batch, but not yet accepted and not yet recorded in the governing
  artifact's `Analysis` section.
- **Final clarification decision** = settled and recorded in the
  governing artifact's `Analysis` section.
- **Final clarification state** = the set of final clarification
  decisions currently recorded in the governing artifact's
  `Analysis` section.

For each unresolved decision in the active branch, clarification
proceeds by resolving directly what is already determined and asking
only what still requires user input.

First use existing evidence such as prior confirmed user decisions,
current task materials, glossary terms, code, and docs. Do not
present facts, findings, or implications as standalone items. Use
them only inside decision reasons.

If confirmed user choices, existing evidence, or a direct consequence
of those choices fully determine the answer, resolve it directly. If
you later present that directly resolved answer in a decision batch,
mark it with `(100%)`.

Otherwise, resolve the decision directly unless at least one of these
is true:
- confidence is below 80%;
- the decision would be hard to revert once implemented; or
- the downstream decisions or implementation it would unlock would be
  hard to revert once implemented.

Use the Question/Options/Recommendation form only when user input is
still required under those conditions.

Treat confidence as an operational estimate used to force a decision,
not as a calibrated statistical probability.

Prefer direct resolution for workflow, routing, editorial, and other
questions already determined by confirmed choices or existing
evidence.

When you ask about a high-impact, non-trivial trade-off that would be
hard to reverse or surprising without context, you may suggest
creating an ADR as part of that question, but do not create one unless
the user requests it or explicitly approves.

For each directly resolved decision, choose one of these handling
paths:
- record it immediately in the governing artifact's `Analysis`
  section as a final clarification decision;
- queue it for clarification presentation as a pending surfaced
  decision; or
- leave it open and ask the User.

A decision may be recorded directly without a decision batch only
when it is fully determined by confirmed user choices, existing
evidence, or a low-risk mechanical consequence of those choices; is
not likely to surprise the User if left unsurfaced; is not needed as
explicit shared state for later clarification; and would be easy to
revise before approval or implementation if later evidence changes
it.

Queue a pending surfaced decision when it is material enough that the
User should see it during clarification, when later clarification
depends on it as explicit shared state, when it would be surprising if
left implicit, or when artifact-only review would be a poor surface
for catching errors.

Bias this choice by clarification grill level:
- `light` = stronger bias toward direct recording and fewer
  questions;
- `medium` = balance direct recording, decision batches, and direct
  questions; and
- `heavy` = stronger bias toward surfacing decisions and asking more
  before clarification ends.

The clarification grill level may change whether an eligible final
clarification decision is surfaced, but it does not relax the
direct-recording gate above.

A pending surfaced decision stays outside the final clarification
state until the User accepts the batch that presents it. It must not
yet be recorded in the governing artifact's `Analysis` section or
used as explicit shared state for later material branch conclusions.
If later clarification would materially depend on a pending surfaced
decision, present the batch and wait for the User's response first.

Add each directly resolved decision chosen for clarification
presentation to a queue in resolution order.

Do not present the queue immediately. Keep clarifying until one of
these happens:
- the queue reaches 6 decisions;
- the next step would require one question with a Recommendation; or
- clarification for the current branch is complete.

The queue must never exceed 6 decisions.

When one of those boundaries is reached, present one decision batch:
- if the queue reached 6, present exactly those 6 decisions;
- otherwise, present all queued decisions in one shorter batch.

Do not emit standalone decision lines outside decision batches. A
pending surfaced decision must not be recorded in the governing
artifact's `Analysis` section or used as explicit shared state for
later material branch conclusions before the User accepts the batch
that presents it.

Do not ask a question before presenting queued decisions it depends on
or needs for context.

Do not ask the next clarification question in the same response as a
decision batch.

After every decision batch, ask the user to confirm, question, or
disagree with it, then wait for the user's response. That confirmation
prompt is part of the decision batch, not a separate clarification
question.

After a batch is presented, do not repeat those decisions before later
questions unless the user asks for a recap, reopens a decision, or a
later decision changes it.

For each decision presented in a batch, use this form:

Decision: <brief answer> (<N>%)
Reason: <brief reason>

Keep `Decision:` and `Reason:` brief and non-redundant. The
`Decision:` line must contain the actual answer text. Do not use a
separate `Topic:` line, option letters, `Options:`, or
`Alternatives:` in direct decisions.

After the User accepts a presented decision batch, each accepted
pending surfaced decision becomes a final clarification decision.
Record those decisions in the governing artifact's `Analysis`
section and update every other affected section of that artifact.

For task-controlled work, use the task-file path for durable
clarification state, because final clarification decisions kept only
in chat can be lost through compaction or context loss.

If clarification starts on the chat-only path, promote it to the
task-file path before chat-only storage becomes unsafe. After that,
chat-only is no longer allowed for that task.

However, you may accumulate multiple final clarification decisions
before syncing them into the task file.

Do not let the active task file fall materially behind the final
clarification state. Sync it at a clean checkpoint before that state
would be hard to reconstruct safely from chat alone, and always
before clarification returns control to another workflow.

These task-file sync edits are internal state-preservation steps. Do
not ask the User to review them separately during clarification. If
unresolved questions remain after a sync, continue clarification from
the updated task file.

Before clarification returns, keep the governing artifact's
`Analysis` section and every affected section aligned with the
current final clarification state.

For each clarification question you ask, start with a brief opening
line and then provide the recommendation in compact form.

Question: <brief question>

If the question asks the user to choose among explicit lettered
options, use:

Question: <brief question>
Recommendation: <letter> (<N>%)
Options:
- A. <brief option summary>
- B. <brief option summary>
- C. <brief option summary>
Reason: <brief reason>

Include the recommended option in `Options`. Keep the `Question:`
line and each option summary as short as possible while still showing
the real distinction. Do not repeat the full recommended option text
in the Recommendation line.

Otherwise use:

Question: <brief question>
Recommendation: <answer> (<N>%)
Reason: <brief reason>

If you ask the user to choose among alternatives, enumerate the
options in the same turn with letters like A, B, C, D and use the
option letter as the recommendation answer. Do not use an option
letter in the Recommendation line unless that lettered option is
explicitly listed in the same turn. For non-lettered
Recommendations, use the shortest precise answer. Keep `Question:`
and `Reason:` brief and non-redundant.

For yes/no questions, use `yes` or `no` unless you explicitly
enumerate them as options.

If the user's answer does not cleanly select one presented option,
restate your understanding and require explicit user confirmation
before moving to the next question.

When the user cleanly selects or confirms a presented option,
acknowledge it briefly in the same turn with a minimal confirmation
such as `B recorded`, `yes recorded`, or `no recorded`. Then treat
that choice as internal state.

An explicit prior user choice or a previously confirmed decision is
already confirmed state for the same unchanged decision. Reuse it
silently. Do not present it again as a new decision batch item unless
the User reopened it or you are not sure it still applies.

If the User asks for a recap, restate it as a recap, not as a new
decision batch item.

If the current decision is inferred from prior confirmed choices but
was not itself explicitly chosen, state that inference in `Reason`. Do
not say the User chose it unless the User actually chose it.

If you are not sure whether a prior user choice or previously
confirmed decision still applies, state what is uncertain instead of
treating it as already settled.

Do not restate the substance of a confirmed choice or ask again about
its direct consequences in later turns unless the User asked for a
recap, reopened it, or one brief reminder is strictly necessary to
keep the current question clear and easy to answer.

At each step, either present one decision batch or ask one question
with its Recommendation.

## Clarification exit check

Before handing work back to the invoking workflow, confirm that:

- no material unresolved question remains for the current branch;
- any glossary conflict has been resolved or explicitly surfaced;
- any code/docs-vs-claim contradiction has been surfaced;
- any required glossary follow-up has been noted in the active task
  when one exists;
- every final clarification decision is recorded in the governing
  artifact's `Analysis` section with a brief reason; and
- every affected section of the governing artifact reflects the
  recorded clarification decisions.
