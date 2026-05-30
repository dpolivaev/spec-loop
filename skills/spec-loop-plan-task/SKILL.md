---
name: spec-loop-plan-task
description: >-
  Mandatory unless the user opts out. Opt-out includes approving a
  suggested concrete modification or giving an explicit and precise
  instruction for a concrete and clear action. Use when non-trivial
  work on features, bug fixes, refactorings, or changes to code,
  tests, configuration, dependencies, runtime assets, or design
  requires explicit planning before implementation.
---

This skill is mandatory unless the user opts out. Opt-out includes
approving a suggested concrete modification or giving an explicit and
precise instruction for a concrete and clear action.

Read this file fully unless it is already loaded in the current
session context.

Apply project instructions such as `AGENTS.md` when present.

After loading the applicable rules, immediately emit 🫡.

For task creation and task updates,
`../spec-loop-clarify-task/SKILL.md` is the default clarification path
when material unresolved questions remain.

If a new request or active task is already obviously underspecified or
exposes material open alternatives, do only enough research to frame
the clarification well. Then use `../spec-loop-clarify-task/SKILL.md`
before drafting or revising planning content further.

Before moving beyond Research for a non-trivial task, run the
unresolved-questions scan required by this skill's phase rules. If
that scan shows material unresolved questions that require
clarification, use `../spec-loop-clarify-task/SKILL.md` before
continuing.

## Shared workflow rules

- **Principle over ceremony**
- Intent-first. Judge by outcome, not checklist.
- Whenever the LLM stops or pauses, it must explain the reason
  explicitly.
- If `AGENTS.md` and these rules conflict, stop and ask the User.
- **Enforcement, pre-edit gate, and LLM stewardship**
- These rules are mandatory. The LLM enforces them.
- Only the User may override these rules.
- Treat either of the following as an implicit User opt-out from the
  task-based planning procedure for the current work item unless the
  User says to keep that procedure:
  - approval of a suggested concrete modification;
  - an explicit and precise instruction for a concrete and clear
    action.
- This opt-out still allows direct verification coupled to the
  requested action, such as running relevant tests after a dependency
  version change.

## Read and enforcement model

- Already injected or attached: don't re-read.
- Else: read once, keep a 3-5 line digest in context.
- Re-read only if the digest is missing or the User says the rules
  changed.

## Phase model

Phases:

- **PLAN** - research, design/spec, and test specification for
  executable work.
- **IMPLEMENTATION** - approved executable changes and their coupled
  updates, including code, tests, config, dependencies, packaging,
  runtime assets, and any required documentation updates.
- **DONE** - verified and accepted.

PLAN allows edits only to planning artifacts for the current
executable work item, especially task files. Commands are allowed for
research or verification only if they do not change repository
contents outside those artifacts. If they would, treat that work as
IMPLEMENTATION and get explicit User approval first.

Anything touching executable behavior, tests, build or config,
dependencies, packaging, runtime assets, or documentation coupled to
those changes is IMPLEMENTATION and needs explicit User instruction.

Work starts in **PLAN** and returns to **PLAN** after each work item
unless the User says otherwise.

- During PLAN, if task creation or task updates are already
  obviously underspecified or expose material open alternatives, do
  only enough Research to frame the clarification well. Then use
  `../spec-loop-clarify-task/SKILL.md` before drafting or revising
  planning content further.
- Otherwise, once Research for the current increment is
  sufficient to expose the main open questions, and before drafting
  or hardening planning content beyond Research, perform an
  unresolved-questions scan and make the result explicit in the
  conversation or active planning artifact. Treat materially
  different code design solutions as design questions for this
  scan. If no material unresolved question remains that is
  user-preference-sensitive or could materially change scope,
  constraints, design, or test specification, continue planning.
  Otherwise, stop planning, use `../spec-loop-clarify-task/SKILL.md`,
  and resume planning only after the clarification result is
  incorporated.
- No permission questions for already requested work.
- Starting PLAN artifacts, entering IMPLEMENTATION, and marking DONE
  require explicit User instruction.
- After implementation approval on either planning path, follow
  `../spec-loop-implementation-flow/SKILL.md` for
  implementation-time handling, clarification routing, fileless
  recovery or promotion, `Implementation notes` checks, and any
  return-to-PLAN routing.
- Phases are exclusive unless the User allows combined
  planning-plus-implementation.

Phase model governs executable work and documentation coupled to that
work. Standalone documentation work is outside it unless the User or
project instructions say otherwise.

## ADR and documentation routing

Ceremony follows executable impact, not file type.

Standalone documentation work may stay outside the task-file path when
all of the following are true:

- the requested work is confined to documentation artifacts;
- it does not require executable changes; and
- no project rule requires a task file.

ADRs and instruction files, including skill files, are normally treated
as standalone documentation when the requested work is confined to
those artifacts.

ADR-only work is not implementation-task planning by default. When the
requested work is only to create, revise, or compare an ADR, do not
create a task file unless the user explicitly asks for one or the ADR
work is part of a larger task already being tracked in a task file.

Use ADRs for decisions affecting public behavior, dependencies, or
long-term design.

- Record architecture decisions in `architecture-decisions/` as one
  file per decision with meaningful names.
- ADR file names should use readable descriptive words, without
  prefixes, numbering, or abbreviations.
- Use the short template: Title, Date, Status, Context, Decision,
  Consequences.

If documentation is part of a larger executable change, keep it in that
task. Plan the documentation update during PLAN and perform it during
IMPLEMENTATION.

## Common task guidance

For executable task planning on either planning path:

- read [common-task-guidance.md](./common-task-guidance.md)
  fully;
- use it as the shared source for the no-subtask main-task form,
  section order and conditionality, section meanings, current-
  increment readiness, testing policy, context preservation, and
  formatting that apply equally in chat and task files;
- on the fileless path, use that shared main-task form without
  subtasks or diagrams; and
- on the task-file path, combine it with
  [task-file-path-guidance.md](./task-file-path-guidance.md).

## Planning paths

Executable changes require explicit planning before implementation.

Choose between:

- the fileless planning path documented in chat, and
- the task-file path documented in task artifacts.

Take the fileless planning path when all of the following are true:

- this is the first planning pass for the task in the current
  conversation;
- the required research is lightweight;
- the design has a single clear implementation path;
- the required verification is lightweight and easy to track in chat;
- no task file exists yet for this task.

Use the task-file path instead if subtasks are already needed or if
research or design is complex enough that diagrams would materially
help clarify the plan.

Project instructions or the User may still require the task-file path
for work that would otherwise match the fileless planning path.

Use the task-file path otherwise.

Once a task file exists for a task, continue using that task file for
that task instead of moving planning back into chat.

## Fileless path

When the fileless planning path is in use:

- read [fileless-path-guidance.md](./fileless-path-guidance.md)
  fully;
- keep the work in chat only; and
- follow that file together with
  [common-task-guidance.md](./common-task-guidance.md).

## Task-file path

When the task-file path is in use:

- read [task-file-path-guidance.md](./task-file-path-guidance.md)
  fully;
- create or update the required task files under it;
- draft or revise the active task file to capture the shared task
  sections needed for the current increment, plus any task-file
  administration and subtask structure required;
- if no suitable task file exists yet, create one before executable
  changes;
- chat stays for coordination and approvals, but the task file is the
  durable planning artifact for that task;
- before treating task-file planning as complete or asking for
  approval to move from planning into implementation on the
  task-file path, use
  [../spec-loop-prepare-implementation-approval/SKILL.md](../spec-loop-prepare-implementation-approval/SKILL.md);
- after task-file implementation approval, use
  [../spec-loop-implementation-flow/SKILL.md](../spec-loop-implementation-flow/SKILL.md)
  for implementation-phase routing, clarification handling,
  `Implementation notes` checks, and `in-progress` -> `review`
  checks.

If project instructions do not define a task directory, use `tasks/`
as the default.

When drafting or repairing embedded PlantUML in task files, follow
`examples/example-task-wordle-cli.md` as a collection of valid diagram
patterns and section-placement examples.
Do not treat it as a required task length or as a model for how much
detail every task or first planning pass must contain.

## Glossary policy

Default glossary policy:

- glossary use is opted in;
- project or session instructions may opt out;
- when the project uses the AsciiDoc glossary format defined by
  `spec-loop-write-glossary`, use `spec-loop-write-glossary`;
- otherwise follow the project's glossary format.

Recognize `glossary.adoc` and `glossary.md` as project glossary files.
If both exist, ask which one is canonical before updating either.

Once a project glossary exists, it defines shared domain language above
individual tasks and code.

Creating the first project glossary from approved information is
normally standalone documentation work and does not require a task file
unless the user or project rules require one.

When approved work changes, clarifies, or implements shared domain
terms:

- include the glossary update in the plan, including fileless-path
  work;
- perform the glossary update during IMPLEMENTATION;
- use `spec-loop-write-glossary` when the glossary uses the Spec Loop
  AsciiDoc glossary format;
- otherwise update the glossary directly in the project's active
  format.
