---
name: spec-loop-clarify-task
description: >-
  Clarify or discuss a proposed task, plan, design update, or ADR by
  resolving the highest-value unresolved decisions, decision criteria,
  trade-offs, and option boundaries until the inputs are ready for
  task creation, task planning, task/design updates, ADR writing, or
  safe implementation continuation. Use this as the default path when
  the user asks to clarify, discuss criteria, compare options,
  stress-test a design, or otherwise resolve material unresolved
  questions before proceeding. When clarification ends, resume the
  invoking workflow. It may also be used for general grilling when
  explicitly selected or when no other default grilling skill is
  available.
---

Use this skill only when material unresolved questions, decision
criteria, or option trade-offs block safe planning, approval, design
updates, ADR work, or implementation.

Prefer this skill over a generic grill-me variant for Spec Loop task
creation, task updates, and design updates.

When clarification ends, return control to the invoking workflow:
- task planning resumes `../spec-loop-plan-task/SKILL.md`;
- ADR clarification resumes `../spec-loop-write-adr/SKILL.md`;
- approval-preparation clarification resumes
  `../spec-loop-prepare-implementation-approval/SKILL.md`;
- implementation-time clarification resumes
  `../spec-loop-implementation-flow/SKILL.md`.

Clarification stays in the phase of the invoking workflow:
- planning clarification stays in PLAN;
- ADR clarification stays in ADR work;
- implementation-time clarification follows
  `../spec-loop-implementation-flow/SKILL.md`.

During PLAN or ADR work, revise the governing artifact in place as
needed for the current work item. Executable changes are not allowed.

## Plain confirmation exclusion

Handle obvious typo fixes, trivial one-word disambiguations, and
simple factual confirmations inline when they would not materially
change scope, behavior, policy, conceptual model, conceptual contract
boundaries, acceptance logic, route, Design, or Test specification.

These are outside the grilling protocol: no grill-level notice,
decision batches, or `Question:` / `Recommendation:` format.

If such a point turns out to have a material design consequence,
resume normal clarification.

## Core method

For each unresolved point:

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
material.

Do not use clarification for exact names, wording, labels, field
names, enum names, or other cheap-to-change design text when the
boundary is already settled. Put that in Design instead.

If drafting or reviewing Design exposes a new material boundary
question, return to clarification before continuing.

## Clarification surface

Clarification uses three tools:
- direct recording of fully determined decisions;
- surfaced decision batches for material decisions the user should
  explicitly see; and
- explicit questions when user input is still required.

Use the current clarification grill level already in force for the
work item when one exists. Otherwise, if no user, session, or project
default is in force, default to `medium`.

Keep the grill level implicit unless clarification is likely to
require more than 3 user-facing clarification steps. A clarification
step is one decision batch or one `Question:` block. Plain
confirmations do not count.

If the threshold is exceeded, state the current grill level once for
that work item:
- `light` = fewer questions and fewer surfaced decisions;
- `medium` = balanced default;
- `heavy` = more questions and more surfaced decisions.

If the need for more than 3 clarification steps becomes clear only
later, give the notice then before continuing. Do not repeat it unless
the user asks, the level changes, or clarification starts for a
different work item.

The grill level affects surfacing and question frequency only. It does
not change the definition of a material unresolved question or the
need to preserve final clarification state.

Select unresolved branches by descending importance and uncertainty.
Traverse the selected branch depth first. Start with a brief map of the
most important visible unresolved branches and which branch you will
address first.

For directly resolved decisions:
- record them immediately when they are fully determined, unsurprising
  if left implicit, and easy to revise before approval or
  implementation; or
- queue them for a surfaced decision batch when the user should see
  them explicitly, later clarification depends on them as explicit
  shared state, or leaving them implicit would be surprising.

### Decision batch size

A surfaced decision batch must contain at most 6 decisions.

At each clarification step, present either:
- one decision batch; or
- one question.

Do not ask a question before presenting queued decisions it depends on.
Do not ask a new question in the same response as a decision batch.

## Governing artifact and durable state

Clarification always works against one governing artifact:
- task artifact for planning, approval preparation, or implementation;
- ADR for ADR work.

Keep final clarification decisions in the governing artifact's
`Analysis` section.

In task artifacts, follow
`../spec-loop-plan-task/common-task-guidance.md`: place `Analysis`
immediately after `Research` and record each final clarification
decision as `- <decision> because <reason>.`

In ADRs, follow `../spec-loop-write-adr/adr-format.md`: keep
`Analysis` as compact ADR-relevant bullets supporting the chosen
decision. Treat it as the ADR's authoritative decision-and-reason
ledger.

Keep only accepted final decisions there. Do not keep open questions,
options, confidence values, or transient notes.

Clarification is cross-cutting. When a decision becomes final, update
`Analysis` and every affected section of the governing artifact.

If clarification resolves or changes shared domain terms, note the
required glossary follow-up in the active task when one exists or is
being prepared.

For task-controlled work, do not let final clarification state live
only in chat once that becomes unsafe. Promote to the task-file path
before chat-only storage becomes unsafe, and always sync the final
clarification state before returning control.

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
- no material unresolved question remains for the current branch;
- glossary conflicts and code/docs contradictions have been resolved or
  explicitly surfaced;
- required glossary follow-up has been noted when relevant; and
- the governing artifact matches the final clarification state.
