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

If existing evidence fully determines the answer, resolve it directly
as a decision with `Confidence: 100%`.

If unresolved user goals, priorities, or risk tolerance could
materially change the answer, ask the user.

Otherwise estimate confidence in the current best answer:
- If confidence is above 80%, resolve it directly and queue it for
  batch presentation.
- If confidence is 80% or below, do not ask by default. Ask only when
  the decision is material, hard to reverse, needed to choose the
  next decision path, or a wrong inference would likely cause
  meaningful rework. Otherwise resolve it directly and queue it for
  batch presentation.

If you resolve a decision directly with confidence below 90%, include
the main alternatives in the decision presentation.

Treat confidence as an operational estimate used to force a decision,
not as a calibrated statistical probability.

Prefer direct resolution for workflow, routing, and editorial
decisions unless user goals, priorities, or risk tolerance could
materially change the answer.

When you ask about a high-impact, non-trivial trade-off that would be
hard to reverse or surprising without context, you may suggest
creating an ADR as part of that question, but do not create one unless
the user requests it or explicitly approves.

Whenever you resolve one or more decisions directly, add them to a
queue of newly resolved but not yet presented decisions, preserving
resolution order. Present only that unpresented queue in batches of at
most 6. A batch may contain one decision. Present at most one decision
batch in a single response. After presenting a decision batch, stop
and wait for the user's response before presenting another batch or
asking the next question. If more than 6 unpresented decisions remain,
present only the first batch and keep the rest queued until the user
responds. Do not emit standalone decision lines outside those batches.
Do not silently apply directly resolved decisions. Every directly
resolved decision must be presented to the user in a decision batch
before it is treated as confirmed state, recorded in the task, used as
settled context for later branch conclusions, or before clarification
ends and control returns to the invoking workflow. You may present a
batch at any time. You must present all newly resolved decisions
before the next question that offers alternatives or depends on those
decisions for context, and in all cases before clarification ends and
control returns to the invoking workflow. Single questions and
decision batches may be mixed as the depth-first traversal proceeds.
After a batch is presented, do not repeat those decisions before later
single questions unless the user asks for a recap, reopens a decision,
or a later decision changes it.

For each decision presented in a batch, use this format:

Decision: <answer>
Confidence: <N>%
Alternatives:
- A. <short alternative>
- B. <short alternative>
Reason: <short reason>

Include `Alternatives` whenever confidence is below 90%. Omit it when
confidence is 90% or above.

If the decision corresponds to an explicit option, you may include the
option letter in the Decision line, for example `Decision: B - Reuse
the current chat`.

Let the user confirm, question, or disagree with any presented
decision before you continue deeper into that branch. Record
confirmed decisions in the task file when one exists or is being
prepared.

For each question you ask, provide a recommended answer. The
Recommendation line must be exactly one line and use this format:

Recommendation: <answer> (<N>%) - <short reason>

If you ask the user to choose among alternatives, enumerate the
options in the same turn with letters like A, B, C, D and use the
option letter as the answer. Do not use an option letter in the
Recommendation line unless that lettered option is explicitly listed
in the same turn.

For yes/no questions, use `yes` or `no` as the answer text unless you
explicitly enumerate them as options. For other questions, use the
shortest precise answer text.

If the user's answer does not cleanly select one presented option,
restate your understanding and require explicit user confirmation
before moving to the next question.

When the user cleanly selects or confirms a presented option,
acknowledge it briefly in the same turn with a minimal confirmation
such as `B recorded`, `yes recorded`, or `no recorded`. Then treat
that choice as internal state. Do not restate the substance of the
choice in later turns unless the user asks for a recap, reopens the
choice, or one brief reminder is strictly necessary to keep the
current question clear and easy to answer.

At each step, either present a decision batch or ask one question.
Ask one question at a time whenever direct user input is required.

## Clarification exit check

Before handing work back to the invoking workflow, confirm that:

- no material unresolved question remains for the current branch;
- any glossary conflict has been resolved or explicitly surfaced;
- any code/docs-vs-claim contradiction has been surfaced;
- any required glossary follow-up has been noted in the active task
  when one exists.
