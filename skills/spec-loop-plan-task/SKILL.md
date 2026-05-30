---
name: spec-loop-plan-task
description: >-
  Choose the route for new work and plan it before implementation.
  Use this skill to decide whether the work is taskless, chat-only,
  or task-file, and to create the needed plan for non-trivial
  executable work.
---

Use this skill to choose the route for new work and plan it before
implementation.

Read this file fully unless it is already loaded in the current
session context.

Apply project instructions such as `AGENTS.md` when present.

## Read model

If these rules are not already in context, read this file once and
keep a short digest. Re-read only if the digest is missing or the User
says the rules changed.

## Core rules

- First classify each new work item as trivial or non-trivial.
- If the work is non-trivial, use a Spec Loop task.
- If the work looks trivial, ask: `This looks trivial. May I do this
  without a plan?`
- If the User explicitly asks for a route, planning procedure, or
  sectioned task format, do not simplify it unless the User
  explicitly agrees.
- For task creation or task updates, use
  `../spec-loop-clarify-task/SKILL.md` when material unresolved
  questions remain.
- After implementation approval, follow
  `../spec-loop-implementation-flow/SKILL.md`.
- These rules are mandatory. Only the User may override them.
- If `AGENTS.md` conflicts with these rules, stop and ask the User.
- If the assistant stops or pauses, explain why.

## Route selection

- Treat the work as non-trivial when any of the following hold:
  - changes span multiple files and need more than a
    straightforward mechanical edit;
  - any exploration or research is needed before editing;
  - changes span modules, packages, or plugins;
  - public or shared API changes are involved;
  - targeted test design or verification is needed beyond a
    mechanical edit; or
  - more than one plausible implementation path exists.
- If the work is non-trivial, ask: `This looks non-trivial. We should
  use a Spec Loop task. May it be chat-only, or do you want a task
  file?`
- Before drafting a task or starting implementation, state the route:
  - `Planning route: chat-only task`
  - `Planning route: task-file`
  - `Proposed route: taskless, pending your agreement`
- No permission questions for already requested work, except the
  route-selection prompts required by this skill.

## Phase model

Phases:

- **PLAN** - research, design/spec, and test specification for
  executable work.
- **IMPLEMENTATION** - approved executable changes and their coupled
  updates, including code, tests, config, dependencies, packaging,
  runtime assets, and any required documentation updates.
- **DONE** - verified and accepted.

Rules:

- Work starts in **PLAN** and returns to **PLAN** after each work item
  unless the User says otherwise.
- PLAN may change only planning artifacts for the current work item.
- During PLAN, commands may be used for research or verification only
  if they do not change repository contents outside planning
  artifacts. If they would, treat that work as IMPLEMENTATION and get
  explicit User approval first.
- Any change to executable behavior, tests, build or config,
  dependencies, packaging, runtime assets, or coupled documentation is
  IMPLEMENTATION and needs explicit User instruction.
- Starting PLAN artifacts, entering IMPLEMENTATION, and marking DONE
  require explicit User instruction.
- If a new request or active task is already clearly underspecified or
  has material open alternatives, do only enough research to ask good
  clarification questions. Then use
  `../spec-loop-clarify-task/SKILL.md` before drafting or revising
  planning content further.
- Before planning past Research on non-trivial work, run an
  unresolved-questions scan. If material unresolved questions remain
  that are user-preference-sensitive or could materially change scope,
  constraints, design, or test specification, stop planning, use
  `../spec-loop-clarify-task/SKILL.md`, and resume planning only after
  the clarification result is incorporated.
- Phases are exclusive unless the User allows combined planning and
  implementation.

Standalone documentation work is outside this phase model unless the
User or project instructions say otherwise.

## ADR and documentation routing

Ceremony follows executable impact, not file type.

Standalone non-executable documentation work is taskless by default
when it stays confined to documentation artifacts and no project rule
requires a task file.

ADRs and instruction files, including skill files, are normally treated
as standalone documentation when the requested work is confined to
those artifacts.

ADR-only work is taskless by default. Use a task only when the User
explicitly asks for one, project rules require one, or the ADR work is
part of a larger executable change already being tracked in a task.

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
  formatting rules used on both task routes, with any task-file-only
  requirements called out there explicitly;
- on the chat-only path, use that shared main-task form without
  subtasks or diagrams; and
- on the task-file path, combine it with
  [task-file-path-guidance.md](./task-file-path-guidance.md).

## Planning routes

Choose among:

- the chat-only task route documented in chat;
- the task-file route documented in task artifacts; and
- taskless handling with no spec-loop task.

Non-trivial work must use either the chat-only route or the
task-file route.

Take the chat-only route when all of the following are true:

- this is the first planning pass for the work item in the current
  conversation;
- the required research is lightweight;
- the design has a single clear implementation path;
- the required verification is lightweight and easy to track in chat;
- no active task file controls this same work item; and
- no project rule or User preference requires a task file.

Use the task-file route instead if any of the following hold:

- an active task file already controls the current work item;
- subtasks are needed;
- research or design is complex enough that diagrams would materially
  help clarify the plan;
- project instructions require a task file; or
- the User prefers a task file.

When a prior task file is in `review` or `done` and a new follow-up
work item appears, do not stay in that task-file context by inertia.
Re-run route selection for the new work item. Reuse the existing task
file only when it still controls the same active work item.

## Taskless route

Taskless by default:

- ADR-only work; and
- standalone non-executable documentation work.

For executable work, use taskless only when the work is trivial and
the User explicitly agrees.

- If the work looks trivial, ask: `This looks trivial. May I do this
  without a plan?`
- If the User agrees, proceed taskless.
- Otherwise use a Spec Loop task.
- Direct verification coupled to the requested change may still follow
  taskless work.

## Chat-only path

When the chat-only planning path is in use:

- read [chat-only-path-guidance.md](./chat-only-path-guidance.md)
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

- include the glossary update in the plan, including chat-only
  work;
- perform the glossary update during IMPLEMENTATION;
- use `spec-loop-write-glossary` when the glossary uses the Spec Loop
  AsciiDoc glossary format;
- otherwise update the glossary directly in the project's active
  format.
