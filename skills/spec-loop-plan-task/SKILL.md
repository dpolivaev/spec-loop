---
name: spec-loop-plan-task
description: >-
  Choose the route for new work and plan it before implementation.
  Use this skill to decide whether the work is taskless, chat-only,
  or task-file, and to create the needed plan for non-trivial
  executable work.
---

Use this skill for new work before implementation.

Read this file fully unless it is already loaded in the current
session context. Apply project instructions such as `AGENTS.md` when
present.

If these rules are already in context, keep a short digest and re-read
only if the digest is missing or the user says the rules changed.

## Core rules

- Work starts in PLAN.
- PLAN may change planning artifacts only.
- IMPLEMENTATION requires explicit user approval.
- Non-trivial executable work requires a Spec Loop task.
- Only the user may override these rules.

If `AGENTS.md` conflicts with these rules, stop and ask the user.
If the assistant stops or pauses, explain why.

If the user explicitly asks for a route, planning procedure, or
sectioned task format, do not simplify it unless the user explicitly
agrees.

After implementation approval, follow
`../spec-loop-implementation-flow/SKILL.md`.

## First classification

Classify each work item as one of:
- standalone documentation;
- trivial executable work; or
- non-trivial executable work.

Treat executable work as non-trivial when any of these hold:
- it spans multiple files and is not a straightforward mechanical
  edit;
- it needs research or exploration before editing;
- it spans modules, packages, or plugins;
- it changes public or shared APIs;
- it needs targeted test design or verification beyond a mechanical
  edit; or
- more than one plausible implementation path exists.

Standalone documentation work is taskless by default unless the user
or project rules require a task.

For executable work:
- if it looks trivial, ask: `This looks trivial. May I do this without
  a plan?`
- if it is non-trivial, ask: `This looks non-trivial. We should use a
  Spec Loop task. May it be chat-only, or do you want a task file?`

Before drafting a task or starting implementation, state the route:
- `Planning route: chat-only task`
- `Planning route: task-file`
- `Proposed route: taskless, pending your agreement`

Do not ask extra permission questions for already requested work,
except the route-selection prompts required here.

## Phase model

Phases:
- **PLAN** = research, design/spec, and test specification for
  executable work.
- **IMPLEMENTATION** = approved executable changes and their coupled
  updates.
- **DONE** = verified and accepted.

Rules:
- work starts in PLAN and returns to PLAN after each work item unless
  the user says otherwise;
- during PLAN, commands may be used for research or verification only
  if they do not change repository contents outside planning
  artifacts;
- any change to executable behavior, tests, build or config,
  dependencies, packaging, runtime assets, or coupled documentation is
  IMPLEMENTATION and needs explicit user instruction;
- phases are exclusive unless the user allows combined planning and
  implementation.

Standalone documentation work is outside this phase model unless the
user or project instructions say otherwise.

If planning depends on a material unresolved branch, do only enough
research to ask good clarification questions and use
`../spec-loop-clarify-task/SKILL.md` before continuing. Re-run this
check whenever later drafting exposes a new material branch.

## Route selection

Choose among:
- taskless handling;
- chat-only task; or
- task-file.

Non-trivial executable work must use either the chat-only route or the
task-file route.

Use task-file if any of these hold:
- an active task file already governs the same work item;
- subtasks are needed;
- final clarification or plan state would be unsafe to keep only in
  chat;
- research or design is complex enough that a durable artifact is
  safer;
- project rules require a task file; or
- the user prefers a task file.

Otherwise chat-only is allowed.

When a prior task file is in `review` or `done` and a new follow-up
work item appears, re-run route selection. Reuse the existing task
file only when it still governs the same active work item.

## Planning content

For executable work, planning must cover:
- current scope;
- material clarified decisions;
- design or implementation direction;
- acceptance logic; and
- verification approach.

Use the shared task guidance from
`./common-task-guidance.md`.

## Route-specific handling

### Taskless

Taskless by default:
- standalone non-executable documentation work; and
- ADR-only work.

For executable work, taskless is allowed only when the work is trivial
and the user explicitly agrees.

### Chat-only

Use the chat-only route only while canonical task state can safely
remain in chat.

When this route is in use:
- read `./chat-only-path-guidance.md` fully; and
- follow it together with `./common-task-guidance.md`.

If durable state becomes unsafe to keep only in chat, switch to the
task-file route before continuing.

### Task-file

When this route is in use:
- read `./task-file-path-guidance.md` fully;
- use it together with `./common-task-guidance.md`;
- create or update the active task file before executable changes; and
- use `tasks/` as the default task directory when project
  instructions do not define one.

Before requesting implementation approval on the task-file path, use
`../spec-loop-prepare-implementation-approval/SKILL.md`.

## Special routing

ADR-only work is taskless by default. Use
`../spec-loop-write-adr/SKILL.md` for ADR location, naming,
structure, and update rules.

If ADR work is part of a larger executable change, keep it in that
task and use `../spec-loop-write-adr/SKILL.md` during
IMPLEMENTATION.

## Glossary policy

Default glossary policy:
- glossary use is opted in;
- project or session instructions may opt out;
- when the project uses the Spec Loop AsciiDoc glossary format, use
  `../spec-loop-write-glossary/SKILL.md`;
- otherwise follow the project's glossary format.

Recognize `glossary.adoc` and `glossary.md` as project glossary files.
If both exist, ask which one is canonical before updating either.

Once a project glossary exists, treat it as the shared domain-language
source above individual tasks and code.

When approved work changes, clarifies, or implements shared domain
terms, include the glossary update in the plan and perform it during
IMPLEMENTATION.

## Related skills

Use `../spec-loop-setup-doc-rendering/SKILL.md` for rendering setup.
