---
name: spec-loop-clarify-task
description: Clarify a proposed task, plan, or design update by interrogating the highest-value unresolved decisions until the inputs are ready for task creation, task planning, or task/design updates. Use this as the default clarification path when Spec Loop planning, approval, or implementation skills encounter material unresolved questions that are user-preference-sensitive or could materially change scope, constraints, design, or test specification. It may also be used for general grilling when explicitly selected or when no other default grilling skill is available.
---

Use this skill when a new task, task update, or design update is
underspecified, when materially different code design solutions
remain open, or when the user wants to stress-test a plan or be
grilled on a design.

When both this skill and a generic grill-me variant are available, prefer this skill for Spec Loop task creation, task updates, and design updates. If no default general grilling skill is available, it may also be used for general grilling.

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
  constraints, compare that claim with the repository and existing
  task materials when possible. Surface contradictions explicitly
  instead of smoothing them over.

If clarification resolves or changes shared domain terms, record that glossary follow-up is required through the normal Spec Loop glossary path. Put the note in the active task when one exists or is being prepared.

For each unresolved decision in the active branch, first decide whether to resolve it directly or ask the user:
- If it can be resolved from prior user decisions or by exploring the repository or existing task materials, resolve it directly.
- If you have a strong, evidence-based recommendation that does not primarily depend on user preference, tentatively resolve it directly.
- Otherwise, ask the user one question at a time when the decision is material, depends on user goals or risk tolerance, or remains meaningfully uncertain. If it exposes a high-impact, non-trivial trade-off that would be hard to reverse or surprising without context, you may suggest creating an ADR as part of that question, but do not create one unless the user requests it or explicitly approves.

Whenever you resolve one or more decisions directly, add them to a queue of newly resolved but not yet presented decisions, preserving resolution order. Present only that unpresented queue in batches of at most 6. You may present a batch at any time, and you must present all newly resolved decisions before asking the next question that offers alternatives or depends on those decisions for context. Single questions and decision batches may be mixed as the depth-first traversal proceeds. After a batch is presented, do not repeat those decisions before later single questions unless the user asks for a recap, reopens a decision, or a later decision changes them.

For each decision presented in a batch, include the chosen answer, a brief rationale or key trade-off, and whether it is repository-derived or assistant-proposed. Let the user confirm or disagree with any presented decision before you continue deeper into that branch. Record confirmed decisions in the task file when one exists or is being prepared.

For each question you ask, provide a recommended answer. Usually present it as a tentative default rather than a strong preference. If you do have a strong preference, say so explicitly.

If you provide options, enumerate them with letters like A, B, C, D.

If the user's answer does not cleanly select one presented option, restate your understanding and require explicit user confirmation before moving to the next question.

Ask one question at a time whenever direct user input is required.

## Clarification exit check

Before handing work back to planning, confirm that:

- no material unresolved question remains for the current branch;
- any glossary conflict has been resolved or explicitly surfaced;
- any code/docs-vs-claim contradiction has been surfaced;
- any required glossary follow-up has been noted in the active task
  when one exists.
