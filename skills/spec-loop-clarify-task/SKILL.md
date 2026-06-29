---
name: spec-loop-clarify-task
description: >-
  Engage the User to resolve important open decisions in Spec Loop
  work, including tasks, plans, design updates, work breakdowns,
  implementation checkpoints, and ADR drafting. Use when the user asks
  to clarify, compare options, discuss criteria, or stress-test, or
  when different answers could materially change scope, behavior,
  policy, constraints, route, Design, Test specification, or ADR
  content. Provides mandatory decision screening for workflows that
  require it before dependent Spec Loop content is written or revised.
  Also use for general grilling when explicitly selected.
---

Use this skill for any important clarification with the user:
discuss, analyze, compare options, stress-test, and resolve important
open decisions. Do not run important Spec Loop clarification as an
unstructured side discussion inside another workflow. A work item is
optional. If no task file, ADR, or invoking workflow exists, run
clarification as a chat-only discussion.

Use it during ADR work for criteria, alternatives, and decision-boundary
clarification whenever different answers could materially change the
ADR decision or its justification.

An important open decision is any open question, choice, criterion,
trade-off, credible option, or boundary where different answers could
materially change the user's intended conclusion, scope, behavior,
policy, conceptual model, conceptual contract boundaries, constraints,
route, acceptance logic, verification expectations, Design, Test
specification, or the ADR Decision or its justification.

Outside mandatory decision screening required by an invoking workflow,
if the current discussion or work item is clearly free of important
open decisions, do not use this skill.

This skill is not just for asking questions. Resolve what evidence
already determines, record final decisions in the governing artifact
when one exists, and ask the user only when user input is still needed.

Prefer this skill over a generic grill-me variant or free-form
discussion for Spec Loop task creation, task updates, design updates,
and clarification during ADR work.

## Resolution scope

This skill resolves decisions by checking existing evidence, engaging
the User for remaining choices, and recording final answers. It does
not create ADRs, create tasks, or choose durable work routes.

If evidence and User engagement cannot resolve the decision because
ADR work, implementation work, or research, spike, prototype, catalog, or
proof work is needed, state the blocker and return control to the
invoking workflow. The invoking workflow decides whether to route ADR
work, create or update a task or subtask, or pause until accepted
evidence exists.

If no invoking workflow exists, stop with the blocker and ask the User
which workflow or artifact should own it. Do not continue a workflow
step that depends on the unresolved result.

## Phase and handoff

Clarification stays in the invoking workflow phase when one exists:
- planning clarification stays in PLAN;
- clarification during ADR work stays in ADR work;
- implementation-time clarification follows
  [spec-loop-implementation-flow/SKILL.md](../spec-loop-implementation-flow/SKILL.md).

If there is no invoking workflow, clarification stays in chat. Do not
create or edit a task file, ADR, glossary, or other durable artifact
unless the user explicitly says where the result should be captured.

During PLAN or ADR work, revise the governing artifact in place as
needed. Executable changes are not allowed.

When this skill ends:
- if an invoking workflow exists, return control to it;
- otherwise, stop with the resolved chat state and any remaining open
  questions clearly stated.

Workflow-specific returns:
- task planning resumes [spec-loop-plan-task/SKILL.md](../spec-loop-plan-task/SKILL.md);
- work breakdown resumes
  [spec-loop-plan-work-breakdown/SKILL.md](../spec-loop-plan-work-breakdown/SKILL.md);
- clarification during ADR work resumes
  [spec-loop-write-adr/SKILL.md](../spec-loop-write-adr/SKILL.md);
- approval-preparation clarification resumes
  [spec-loop-prepare-execution-approval/SKILL.md](../spec-loop-prepare-execution-approval/SKILL.md);
- implementation-time clarification resumes
  [spec-loop-implementation-flow/SKILL.md](../spec-loop-implementation-flow/SKILL.md).

## Mandatory decision screening

When another workflow says to apply this skill before writing or
revising content, screen each material decision point that would affect
that content using the decision tree and allowed screening outcomes
below. Do not replace this with an intuitive "no questions needed"
judgment.

A material decision point is any choice the assistant would need to
make, imply, or write as final content, where a different answer could
become an important open decision under this skill.

Every material decision point must be classified as exactly one allowed
screening outcome before dependent final content is written.

Mandatory decision screening means classifying each material decision
point, not asking about each one. Only the `pending User answer as a
clarification question` outcome requires a clarification question.

## Decision tree

### 1. Check whether clarification is needed

If no important open decision remains:
- if an invoking workflow exists, return control to it promptly;
- otherwise, end the chat-only clarification promptly; and
- do not start clarification batching or durable-state recording for
  this skill.

When this skill is used for mandatory decision screening, return at
this step only after every material decision point for the screened
content is classified as resolved from determining evidence or outside
clarification.

### 2. Answer simple cases inline

If the point is an obvious typo fix, trivial one-word disambiguation,
or simple factual confirmation, answer directly when the answer would
not materially change scope, behavior, policy, boundaries, acceptance
logic, route, Design, or Test specification.

Do not use the clarification protocol for these cases.

If the point turns out to matter for the design, continue with normal
clarification.

### 3. Resolve what evidence already determines

For each important open decision, first check whether the answer is
already determined by confirmed user choices, current discussion
context, existing task materials, glossary language, code, docs, or a
low-risk mechanical consequence of those choices.

Do not ask the User for information that the current codebase, docs, or
governing artifact already determines. Use those sources first, and ask
only when they are absent, ambiguous, conflicting, or insufficient for
the decision.

Defaults, "reasonable defaults", common practice, conventions,
framework habit, implementation convenience, assistant preference, and
assistant instinct are not existing determining evidence. Do not use
them to silently resolve a material decision point.

They may support a surfaced decision or recommended option. User
confirmation or answer then settles the decision.

Treat conflicts between user wording, glossary language, task
materials, and code as evidence to surface explicitly.

If a distinction would change whether two concepts are the same thing,
different things, or governed by different rules, treat it as
important.

Do not use clarification for exact names, wording, labels, field names,
enum names, or other cheap-to-change design text when the boundary is
already settled. Put that in Design when a design artifact exists;
otherwise handle it inline.

If drafting or reviewing Design exposes a new important open decision,
return to clarification before continuing.

### 4. Confirm settled decisions

For settled decisions, use chat confirmation only when the choice should
be explicit in the conversation. Artifact storage is handled in Where
to store the result.

Use this for user-made choices, mechanical consequences of user input,
choices later clarification depends on, or choices where confirmation
reduces ambiguity. Do not add confidence or model reasoning. Use:

Recorded: <choice>.

Do not put settled decisions in a decision batch.

### 5. Propose decisions or ask questions for unsettled decisions

For an important decision not settled by the user or existing
materials, choose one outcome:

- Surfaced decision batch: use when the agent can propose a clear
  choice and user check or agreement is enough.
- Clarification question: use when user input is still needed before a
  choice can be made.

A surfaced decision batch must contain at most 6 proposed decisions.
For each proposed decision, use:

Decision: <brief answer> (<N>%)
Reason: <brief reason>

Keep both lines brief. The `Decision:` line must contain the proposed
answer.

After a decision batch, ask the user to confirm, question, or disagree,
then wait.

Every clarification question must list alternatives. Use this format:

Question: <brief question>
Recommended option: <LETTER> (<N>%)
Options:
- A. <brief option summary>
- B. <brief option summary>
- C. <brief option summary>
Reason: <brief reason>

For clarification questions:
- `Recommended option:` must contain only the recommended option letter
  and confidence;
- option summaries and explanations belong only in `Options:` and
  `Reason:`;
- option lines use `- <LETTER>. <summary>`;
- do not put confidence values on individual option lines;
- for yes/no choices, use option summaries such as `A. yes` and
  `B. no`.

Before sending any response containing `Options:`, validate that:
- the `Recommended option:` line has the exact shape
  `Recommended option: <LETTER> (<N>%)`;
- the recommended letter exists in the option list;
- option lines have the exact shape `- <LETTER>. <summary>`; and
- no option line contains a confidence value.

When the user cleanly confirms a presented option, acknowledge it
briefly, such as `B recorded` or `yes recorded`.

### Decision screening outcomes

Mandatory decision screening may produce only these outcomes for each
material decision point:
- resolved from determining evidence, with confirmation when needed;
- outside clarification because screening shows the choice does not
  meet the important open decision definition and does not need User
  check under this skill;
- pending User check in a surfaced decision batch;
- pending User answer as a clarification question; or
- blocked and returned under Resolution scope.

The outside-clarification outcome covers wording or label choices when
substantive meaning is settled, and Design/Test choices that only
implement already settled scope, behavior, constraints, route, and
acceptance expectations.

Outside-clarification choices are not determining evidence and are not
User-confirmed decisions. Return them to the invoking workflow and
record them only where that workflow owns them.

Only resolved and outside-clarification outcomes allow dependent final
content to be written immediately. Pending User-check or User-answer
outcomes must be resolved before writing dependent final content.
Blocked outcomes return control to the invoking workflow.

## Clarification effort question

Clarification effort controls the balance between proposed decision
batches and clarification questions for decisions not settled by the
user or existing materials. It does not control whether a question
lists alternatives; every clarification question lists alternatives. It
does not change what counts as an important open decision or the need
to preserve the final result.

Ask the clarification effort question only when clarification is likely
to take more than two substantive steps, or when the decision space is
broad enough that user control over detail matters. Do not ask it when
clarification is expected to finish within two substantive steps.

A substantive clarification step is either:
- one decision batch; or
- one substantive question about the discussion or work item.

Before the clarification effort question, briefly say what needs
clarification and why. That scope sentence and the clarification effort
question do not count as substantive steps.

The clarification effort question may come before any substantive step
or after one or more steps. These are all valid:
- clarification effort question, 1, 2, 3;
- 1, clarification effort question, 2, 3;
- 1, 2, clarification effort question, 3, 4, 5.

The clarification effort question is a special case of the required
alternatives format. Use this fixed template; vary only the recommended
option, confidence value, and reason:

Question: How involved do you want to be in this clarification session?
Recommended option: <A/B/C/D> (<N>%)
Options:
- A. Low effort: prefer proposed decision batches where safe; ask only when user input is needed.
- B. Normal effort: propose clear choices; ask when uncertain or preference-sensitive.
- C. High effort: ask when uncertain or when the decision is among the most important; use batches for straightforward choices.
- D. Exhaustive: ask about every important unsettled decision; record settled decisions directly.
Reason: <brief reason for the recommended option>
You can change this later by saying "ask fewer questions", "ask more questions", or "ask about every important decision".

Recommend the option that best fits the situation. Use Normal effort
when there is no stronger reason for another recommendation.

After asking the clarification effort question, wait for the user's
next reply. If that reply does not answer it, use the recommended
option, say that you are doing so, and continue.

Once set, clarification effort applies to the current clarification run
unless the user changes it. For later clarification in the same chat
session, ask again if the question is warranted; you may recommend the
last selected preference or a different option. Do not silently reuse a
previous effort setting as active for a new clarification run. If the
user asks to remember the preference, available memory or project
instructions may be used.

Treat Low, Normal, High, and Exhaustive as named anchors on a
continuum, not as the only possible settings. Interpret "ask fewer
questions" and "ask more questions" as relative adjustments along that
continuum. Interpret "ask about every important decision" as
Exhaustive.

Natural wording such as "grill me more", "grill me heavier", "grill
me less", or "grill me lighter" may be interpreted as involvement
adjustments, but do not advertise those phrases in user-facing text.

## Step order during clarification

Choose open decisions by descending importance and uncertainty. Work
through the chosen issue depth first.

At each substantive clarification step, present exactly one of:
- one decision batch; or
- one clarification question.

Do not ask a question before presenting queued decisions it depends on.
Do not ask a new question in the same response as a decision batch.

When a response contains a clarification question or decision batch,
put that question or decision batch at the end of the message. Put any
needed context, evidence, explanation, or status before it. After a
decision batch, only the short confirm/question/disagree prompt may
follow.

Use `Decision:` batches only for agent-proposed decisions that need
user check or agreement. Do not use them for choices already made by
the user or settled by existing materials.

## Where to store the result

Clarification may have a governing artifact, or it may be chat-only.

Use these storage rules:
- Task-governed clarification: store final resolved decisions in the
  task artifact.
- Clarification during ADR work: store final resolved decisions in the
  ADR.
- Chat-only clarification: keep decisions in chat until the user says
  where to capture them. At the end of chat-only clarification, ask
  whether the outcome should be saved and where.

For chat-only clarification, do not create, select, or infer a durable
storage destination. Before writing the outcome anywhere, ask the user
where to capture it.

Store only final resolved content in the governing artifact when one
exists. Do not store open questions, confidence values, or transient
notes.

In task artifacts:
- keep final resolved decisions in `Analysis`;
- follow [spec-loop-plan-task/common-task-guidance.md](../spec-loop-plan-task/common-task-guidance.md);
- place `Analysis` immediately after `Research`; and
- record final clarification decisions using the `Analysis` bullet
  rules from that guidance.

In ADRs, do not force all final resolved content into `Analysis`.
Follow [spec-loop-write-adr/adr-format.md](../spec-loop-write-adr/adr-format.md):
- put pre-decision facts in `Context`;
- put credible competing options in `Alternatives`;
- put the chosen outcome in `Decision`; and
- keep only the ADR-relevant reasoning subset in `Analysis` as compact
  bullets supporting the chosen decision.

For ADRs, keep credible alternatives in `Alternatives`, not duplicated
inside `Analysis`.

This skill is cross-cutting. When a decision becomes final and a
governing artifact exists, update the correct section or sections above
and every affected section of the governing artifact.

If clarification resolves or changes shared domain terms, note the
required glossary follow-up in the active task when one exists or is
being prepared.

For task-controlled work, do not let final resolved state live only in
chat once that becomes unsafe. Promote to the task-file path before
chat-only storage becomes unsafe, and always sync the final resolved
state before returning control. This rule does not convert standalone
chat-only discussion into task-controlled work.

## Exit

Before handing work back to the invoking workflow, confirm that:
- no important open decision remains in the part of the work item being
  handed back;
- glossary conflicts and code/docs contradictions have been resolved or
  explicitly surfaced;
- required glossary follow-up has been noted when relevant; and
- the governing artifact matches the final resolved state.
