---
name: spec-loop-clarify-task
description: Clarify a proposed task, plan, or design update by interrogating the highest-value unresolved decisions until the inputs are ready for task creation, task planning, or task/design updates. Use this as the default clarification path for Spec Loop task creation, task updates, and any planning, approval, or implementation step that encounters material unresolved questions that are user-preference-sensitive or could materially change scope, constraints, design, or test specification. When clarification ends, resume the invoking workflow. It may also be used for general grilling when explicitly selected or when no other default grilling skill is available.
---

Use this skill when a new task, task update, or design update is
underspecified, when materially different code design solutions
remain open, or when the user wants to stress-test a plan or be
grilled on a design.

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
- approval-preparation clarification resumes
  `../spec-loop-prepare-implementation-approval/SKILL.md`;
- implementation-time clarification resumes
  `../spec-loop-implementation-flow/SKILL.md`.

Select the next unresolved branch by descending importance and uncertainty. Once a branch is selected, traverse it depth first, resolving dependencies one-by-one. Start with a brief, provisional overview of the most important currently visible unresolved branches and which branch you will address first. This overview is a map, not a commitment to an exact final question list.

Treat unresolved branches as including both behavior-level
alternatives and materially different code design solutions. When
relevant, explore not only logical alternatives but also
alternative code design solutions such as module boundaries,
interfaces, state ownership, data flow, extension points, and
testable implementation shapes.

Treat existing glossary language, task materials, and code as
first-class evidence during clarification:
- If the user's wording conflicts with existing glossary or task
  language, call it out immediately and force the term choice to be
  resolved.
- Stress-test both behavior-level and code-design alternatives with
  concrete scenarios, edge cases, and boundary cases.
- When the user states current behavior, boundaries, or design
  constraints, compare that claim with the codebase and existing
  task materials when possible. Surface contradictions explicitly
  instead of smoothing them over.

If clarification resolves or changes shared domain terms, record that glossary follow-up is required through the normal Spec Loop glossary path. Put the note in the active task when one exists or is being prepared.

For each unresolved decision in the active branch, clarification
proceeds by deciding what can be decided and asking only what must be
asked.

First use existing evidence such as prior confirmed user decisions,
current task materials, glossary terms, code, and docs. Do not
present facts, findings, or implications as standalone items. Use
them only inside decision reasons.

If existing evidence fully determines the answer, resolve it directly,
queue it, and later present it in the decision batch using the
standard decision format with `(100%)`.

If unresolved user goals, priorities, or risk tolerance could
materially change the answer, ask the user.

Otherwise estimate confidence in the current best answer:
- If confidence is above 85%, resolve it directly and queue it for
  batch presentation.
- If confidence is 85% or below, do not ask by default. Ask only when
  the decision is material, hard to reverse, needed to choose the
  next decision path, or a wrong inference would likely cause
  meaningful rework. Otherwise resolve it directly and queue it for
  batch presentation.

Use the Question/Options/Recommendation form only when additional
user input is actually required.

If your confidence is above 85% and no additional user input is
required, present a Decision in the decision batch instead of using
the Question/Options/Recommendation form.

If your confidence is above 85% and you still use the
Question/Options/Recommendation form, first state the specific reason
additional user input is required. If you cannot name one, present a
Decision in the decision batch instead.

If you present a Decision in the decision batch with confidence below
99%, show the main alternatives in compact form.

If the decision corresponds to explicit lettered options, include a
brief `Options:` list that shows the chosen option and the relevant
distinctions immediately.

For non-lettered decisions, you may omit `Alternatives` only when
confidence is 99% or above, or when existing evidence fully determines
the answer and confidence is 100%.

Treat confidence as an operational estimate used to force a decision,
not as a calibrated statistical probability.

Prefer direct resolution for workflow, routing, and editorial
decisions unless user goals, priorities, or risk tolerance could
materially change the answer.

When you ask about a high-impact, non-trivial trade-off that would be
hard to reverse or surprising without context, you may suggest
creating an ADR as part of that question, but do not create one unless
the user requests it or explicitly approves.

Add each directly resolved decision to a queue in resolution order.

Do not present the queue immediately. Keep clarifying until one of
these happens:
- the queue reaches 6 decisions;
- the next step would require one question with a Recommendation; or
- clarification for the current branch is complete.

The queue must never exceed 6 decisions.

When one of those boundaries is reached, present one decision batch:
- if the queue reached 6, present exactly those 6 decisions;
- otherwise, present all queued decisions in one shorter batch.

Do not emit standalone decision lines outside decision batches. Do not
silently apply directly resolved decisions. Every directly resolved
decision must be presented to the user in a decision batch before it
is treated as confirmed state, recorded in the task, used as context
for later branch conclusions, or before clarification ends and control
returns to the invoking workflow.

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

For each decision presented in a batch, use a compact form.

Start each decision item with a brief opening line:

Topic: <brief decision topic>

If the decision corresponds to explicit lettered options, use:

Topic: <brief decision topic>
Decision: <letter> (<N>%)
Options:
- A. <brief option summary>
- B. <brief option summary>
- C. <brief option summary>
Reason: <brief reason>

Include the chosen option in `Options`. Keep the `Topic:` line and
each option summary as short as possible while still showing the real
distinction. Do not repeat the full chosen option text in the
Decision line.

Otherwise use:

Topic: <brief decision topic>
Decision: <single-word answer> (<N>%)
Alternatives:
- <brief alternative>
- <brief alternative>
Reason: <brief reason>

The only allowed non-letter Decision or Recommendation answer is a
single-word answer. If the answer is not naturally a single word,
define explicit lettered options and use the selected or recommended
letter instead.

For non-lettered decisions, include `Alternatives` whenever
confidence is below 99%. Omit it when confidence is 99% or above.
Keep `Topic:` and `Reason:` brief and non-redundant.

After the user confirms a presented decision, it becomes confirmed
state.

Use the task-file path for durable clarification state, because
confirmed clarification decisions kept only in chat can be lost
through compaction or context loss.

If clarification starts on the chat-only path, promote it to the
task-file path before chat-only storage becomes unsafe. After that,
chat-only is no longer allowed for that task.

However, you may accumulate multiple confirmed clarification results
before syncing them into the task file.

Do not let the active task file fall materially behind the confirmed
clarification state. Sync it at a clean checkpoint before confirmed
state would be hard to reconstruct safely from chat alone, and always
before clarification returns control to another workflow.

These task-file sync edits are internal state-preservation steps. Do
not ask the User to review them separately during clarification. If
unresolved questions remain after a sync, continue clarification from
the updated task file.

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
Recommendation: <single-word answer> (<N>%)
Reason: <brief reason>

If you ask the user to choose among alternatives, enumerate the
options in the same turn with letters like A, B, C, D and use the
option letter as the recommendation answer. Do not use an option
letter in the Recommendation line unless that lettered option is
explicitly listed in the same turn. Keep `Question:` and `Reason:`
brief and non-redundant.

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

Do not restate the substance of a confirmed choice in later turns
unless the User asked for a recap, reopened it, or one brief reminder
is strictly necessary to keep the current question clear and easy to
answer.

At each step, either present one decision batch or ask one question
with its Recommendation.

## Clarification exit check

Before handing work back to the invoking workflow, confirm that:

- no material unresolved question remains for the current branch;
- any glossary conflict has been resolved or explicitly surfaced;
- any code/docs-vs-claim contradiction has been surfaced;
- any required glossary follow-up has been noted in the active task
  when one exists.
