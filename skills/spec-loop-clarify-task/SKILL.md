---
name: spec-loop-clarify-task
description: >-
  Analyze, clarify, and resolve important open decisions in a proposed
  task, plan, design update, or ADR. Use this skill when the user asks
  to analyze, clarify, discuss criteria, compare options, or
  stress-test a design, or when the current workflow may have reached
  an important open question or choice. First check whether any open
  decision or question could still change scope, behavior, policy,
  constraints, route, Design, Test specification, or the ADR decision
  or its justification. If yes, resolve what is already clear from the
  existing evidence, ask focused questions only for what remains,
  record the final result in the governing artifact, and then return
  control to the invoking workflow. If not, return control promptly.
  It may also be used for general grilling when explicitly selected or
  when no other default grilling skill is available.
---

Use this skill when analysis is needed to check whether any important
open decision remains for the current work item, or to resolve one once
identified.

An important open decision includes any open question, choice,
decision criterion, trade-off, credible option, or boundary that could
materially change scope, behavior, policy, conceptual model,
conceptual contract boundaries, constraints, route, acceptance logic,
verification expectations, Design, Test specification, or the ADR
decision or its justification.

If the current work item is already clearly free of such points, do not
use this skill.

This skill is not limited to asking the user questions. It also
analyzes the open decision, resolves what is already determined
directly, records the final result in the governing artifact, and asks
questions only when user input is still required.

Prefer this skill over a generic grill-me variant for Spec Loop task
creation, task updates, and design updates.

When this skill ends, return control to the invoking workflow:
- task planning resumes [spec-loop-plan-task/SKILL.md](../spec-loop-plan-task/SKILL.md);
- ADR clarification resumes [spec-loop-write-adr/SKILL.md](../spec-loop-write-adr/SKILL.md);
- approval-preparation clarification resumes
  [spec-loop-prepare-implementation-approval/SKILL.md](../spec-loop-prepare-implementation-approval/SKILL.md);
- implementation-time clarification resumes
  [spec-loop-implementation-flow/SKILL.md](../spec-loop-implementation-flow/SKILL.md).

Clarification stays in the phase of the invoking workflow:
- planning clarification stays in PLAN;
- ADR clarification stays in ADR work;
- implementation-time clarification follows
  [spec-loop-implementation-flow/SKILL.md](../spec-loop-implementation-flow/SKILL.md).

During PLAN or ADR work, revise the governing artifact in place as
needed for the current work item. Executable changes are not allowed.

## Plain confirmation exclusion

Handle obvious typo fixes, trivial one-word disambiguations, and
simple factual confirmations inline when they would not materially
change scope, behavior, policy, conceptual model, conceptual contract
boundaries, acceptance logic, route, Design, or Test specification.

These are outside the clarification protocol: no scope preface,
involvement question, decision batch, question-with-alternatives, or
`Question:` / `Recommendation:` format.

If such a point turns out to have an important design consequence,
resume normal clarification.

## Entry assessment

First check whether any important open decision remains for the current
work item.

Treat a point as important only if different answers would materially
change scope, behavior, policy, conceptual model, conceptual contract
boundaries, constraints, route, acceptance logic, verification
expectations, Design, Test specification, or the ADR decision or its
justification.

If none remain:
- return control to the invoking workflow promptly; and
- do not start clarification batching or durable-state recording for
  this skill.

If one or more important open decisions remain, continue with the core
method.

## Core method

For each important open decision:

1. Check whether the answer is already determined by confirmed user
   choices, existing task materials, glossary language, code, docs,
   or a low-risk mechanical consequence of those choices.
2. If yes, resolve it directly.
3. If no, ask only if different answers would materially change
   scope, behavior, policy, conceptual model, conceptual contract
   boundaries, acceptance logic, route, Design, or Test
   specification.
4. If no material change would result, do not clarify it here.

Treat conflicts between user wording, glossary language, task
materials, and code as evidence to surface explicitly.

If a distinction would change whether two concepts are the same thing,
different things, or governed by different rules, treat it as
important.

Do not use clarification for exact names, wording, labels, field
names, enum names, or other cheap-to-change design text when the
boundary is already settled. Put that in Design instead.

If drafting or reviewing Design exposes a new important open decision,
return to clarification before continuing.

## How to work through open decisions

This skill uses three tools:
- direct recording of fully determined decisions;
- surfaced decision batches for important decisions the user should
  explicitly see; and
- explicit questions when user input is still required.

Choose open decisions by descending importance and uncertainty. Work
through the chosen issue depth first.

Clarification can start without asking the user how involved they want
to be. For short clarification, ask the needed questions and make the
needed decisions visible. Prefer the normal `Question:` /
`Recommendation:` / `Reason:` form when it helps precision.

Clarification effort is unset until the involvement question has been
answered or defaulted.

Ask the involvement question only when clarification is likely to take
more than two substantive steps, or when that becomes clear later. Ask
earlier when the visible decision space is broad enough that user
control over involvement matters.

A substantive clarification step is either:
- one decision batch; or
- one substantive question about the work item.

Before the involvement question, briefly say what needs clarification
and why. The scope statement and involvement question do not count as
substantive steps.

The involvement question may come before any substantive step or after
one or more steps. These are all valid:
- involvement question, 1, 2, 3
- 1, involvement question, 2, 3
- 1, 2, involvement question, 3, 4, 5

Numbers are substantive steps.

Do not ask the involvement question when clarification is expected to
finish within two substantive steps.

The involvement question uses this fixed template; vary only the
recommendation and reason:

Question: How involved do you want to be in this clarification session?
Recommendation: <A/B/C/D> (<N>%)
Options:
- A. Low effort: show important chosen decisions with reasons; ask only when needed.
- B. Normal effort: show alternatives when the choice is uncertain.
- C. High effort: show alternatives when the choice is uncertain or the decision is among the most important.
- D. Exhaustive: show all credible alternatives for important decisions.
Reason: <brief reason for the recommended option>
You can change this later by saying "show fewer alternatives", "show more alternatives", or "show all credible alternatives".

Recommend the option that best fits the situation. Use Normal effort
when there is no stronger reason for another recommendation.

After asking the involvement question, wait for the user's next reply.
If that reply does not answer it, use the recommended option, say that
you are doing so, and continue.

Once set, clarification effort applies to the current clarification run
unless the user changes it. For later clarification in the same chat
session, the agent may recommend the last selected preference when
asking the involvement question again, or recommend a different option
when the situation warrants it. Do not silently reuse a previous effort
setting as active for a new clarification run. If the user asks to
remember the preference, available memory or project instructions may
be used.

Internally, use `clarification effort` for this setting. Treat Low,
Normal, High, and Exhaustive as named anchors on a continuum, not as
the only possible settings. Interpret "show fewer alternatives" and
"show more alternatives" as relative adjustments along that
continuum. Interpret "show all credible alternatives" as Exhaustive.

Natural wording such as "grill me more", "grill me heavier", "grill
me less", or "grill me lighter" may be interpreted as involvement
adjustments, but do not advertise those phrases in the user-facing
text.

Clarification effort affects both how often questions show alternatives
and how many chosen decisions are surfaced as decision batches. Higher
effort surfaces more chosen decisions. Lower effort leaves more safe
decisions implicit. It does not change what counts as an important
open decision or the need to preserve the final result.

For directly resolved decisions:
- record them immediately when they are fully determined, unsurprising
  if left implicit, and easy to revise before approval or
  implementation; or
- queue them for a surfaced decision batch when the user should see
  them explicitly, later clarification depends on them as explicit
  shared state, or leaving them implicit would be surprising.

A surfaced decision batch must contain at most 6 decisions.

Present surfaced decisions in batches by default. A single surfaced
decision may appear before a substantive question only when needed to
make that question clear.

At each substantive clarification step, present either one decision
batch or one question. Do not ask a question before presenting queued
decisions it depends on. Do not ask a new question in the same response
as a decision batch.

If the user has already made a clear and unambiguous choice, do not
present it again as a `Decision:` with `Reason:`. Acknowledge it as
recorded, for example: `Recorded: <choice>.`

Use `Decision:` batches only for agent-chosen or agent-resolved
decisions that need user visibility.

## Where to store the result

Clarification always works against one governing artifact:
- task artifact for planning, approval preparation, or implementation;
- ADR for ADR work.

Store the final result according to artifact type.

In task artifacts, keep final resolved decisions in `Analysis`.
Follow [spec-loop-plan-task/common-task-guidance.md](../spec-loop-plan-task/common-task-guidance.md): place
`Analysis` immediately after `Research` and record each final
clarification decision as `- <decision> because <reason>.`

In ADRs, do not force all final resolved content into `Analysis`.
Follow [spec-loop-write-adr/adr-format.md](../spec-loop-write-adr/adr-format.md): classify final
resolved content into `Decision`, `Context`, `Alternatives`, and
`Analysis`. Put pre-decision facts in `Context`, credible competing
options in `Alternatives`, the chosen outcome in `Decision`, and keep
only the ADR-relevant reasoning subset in `Analysis` as compact
bullets supporting the chosen decision.

Keep only final resolved content in those sections.
Do not keep open questions, confidence values, or transient notes.
For ADRs, keep credible alternatives in `Alternatives`, not duplicated
inside `Analysis`.

This skill is cross-cutting. When a decision becomes final, update the
correct section or sections above and every affected section of the
governing artifact.

If clarification resolves or changes shared domain terms, note the
required glossary follow-up in the active task when one exists or is
being prepared.

For task-controlled work, do not let the final resolved state live
only in chat once that becomes unsafe. Promote to the task-file path
before chat-only storage becomes unsafe, and always sync the final
resolved state before returning control.

## Response forms

For each surfaced decision in a batch, use:

Decision: <brief answer> (<N>%)
Reason: <brief reason>

Keep both lines brief. The `Decision:` line must contain the actual
answer.

After a decision batch, ask the user to confirm, question, or
disagree, then wait.

For explicit questions, use:

Question: <brief question>
Recommendation: <answer> (<N>%)
Reason: <brief reason>

If the question is a choice among listed alternatives, use:

Question: <brief question>
Recommendation: <letter> (<N>%)
Options:
- A. <brief option summary>
- B. <brief option summary>
- C. <brief option summary>
Reason: <brief reason>

For yes/no questions, use `yes` or `no` unless you explicitly list
lettered options.

When the user cleanly confirms a presented option, acknowledge it
briefly, such as `B recorded` or `yes recorded`.

## Exit

Before handing work back to the invoking workflow, confirm that:
- no important open decision remains in the part of the work item
  being handed back;
- glossary conflicts and code/docs contradictions have been resolved or
  explicitly surfaced;
- required glossary follow-up has been noted when relevant; and
- the governing artifact matches the final resolved state.
