---
name: spec-loop-plan-task
description: >-
  Choose the planning form for new work and plan it before execution.
  Use this skill to decide whether the work is taskless, chat-only, a
  task file, a task file with subtasks, or multiple task files /
  backlog items, and to create the needed plan for non-trivial implementation
  work or investigation work that needs its own task.
---

Use this skill for planning-form selection and task planning before
execution.

Read this file fully unless it is already loaded in the current
session context. Apply project instructions such as `AGENTS.md` when
present.

If these rules are already in context, keep a short digest and re-read
only if the digest is missing or the user says the rules changed.

## Core rules

- Work starts in PLAN.
- PLAN may change planning artifacts only.
- A Spec Loop task is a planned, approval-gated, reviewable unit of
  work.
- Non-trivial implementation work requires a task.
- Investigation work requires a task only when it needs explicit
  planning, separate tracking, or a reviewable output.
- Starting EXECUTION requires explicit user approval. A directive such
  as `implement`, `investigate`, `go ahead`, `proceed`, or `apply`
  counts as approval only when the active task is ready and the
  directive clearly refers to that task. If readiness or the referent
  is unclear, ask.
- Only the user may override these rules.

If `AGENTS.md` conflicts with these rules, stop and ask the user.
If the assistant stops or pauses, explain why.

If the user explicitly asks for a planning form, planning procedure,
or sectioned task format, do not simplify it unless the user
explicitly agrees.

After execution approval for implementation work, follow
[spec-loop-implementation-flow/SKILL.md](../spec-loop-implementation-flow/SKILL.md).
After execution approval for investigation work, perform only the
approved investigation, record final output in `Findings`, satisfy any
`Test specification`, and present or move the task/subtask to
`review` using the active path.

## First classification

Classify each work item as one of:
- standalone documentation;
- trivial implementation work;
- non-trivial implementation work; or
- investigation work.

Treat implementation work as non-trivial when any of these hold:
- it spans multiple files and is not a straightforward mechanical
  edit;
- it needs research or exploration before editing;
- it spans modules, packages, or plugins;
- it changes public or shared APIs;
- it needs targeted test design or verification beyond a mechanical
  edit; or
- more than one plausible implementation path exists.

Investigation work means research, spike, prototype, catalog, proof,
or similar evidence-gathering whose output is knowledge or a
reviewable artifact rather than product change. It needs a task only
when the investigation itself needs explicit planning, separate
tracking, or a reviewable output. Ordinary planning research stays in
PLAN inside the current task or chat state. Investigation work must
not ship product, test, build, config, runtime, or coupled
documentation changes; plan that scope as implementation work.

## Planning-form selection

After first classification, read and follow
[planning-form-selection-guidance.md](planning-form-selection-guidance.md).

## Phase model

Phases:
- **PLAN** = research, design/spec, test specification, and
  investigation planning for tasks.
- **EXECUTION** = approved task work. For implementation work, this
  means implementation changes and their coupled updates.
- **DONE** = verified and accepted.

Rules:
- work starts in PLAN and returns to PLAN after each work item unless
  the user says otherwise;
- during PLAN, commands may be used for research or verification only
  if they do not change repository contents outside planning
  artifacts;
- any implementation change to behavior, tests, build or config,
  dependencies, packaging, runtime assets, or coupled documentation is
  EXECUTION and needs explicit user instruction;
- phases are exclusive unless the user allows combined planning and
  execution.

Standalone documentation work is outside this phase model unless the
user or project instructions say otherwise.

## Mandatory clarification gate

Before creating or revising task or current-subtask planning content,
apply the clarification skill's mandatory decision screening from
[spec-loop-clarify-task/SKILL.md](../spec-loop-clarify-task/SKILL.md).
Use that skill's definition of important open decision.

Apply this gate proactively; do not wait for the User to ask for
clarification.

For subtasks, apply this gate only to the current subtask and required
task-level context. Apply it to future subtasks when they become
current.

If clarification returns an unresolved blocker that needs ADR work or
separate investigation work, route that work before continuing the
blocked task plan.

If drafting later exposes an important open decision, stop and clarify
before continuing.

## Planning content

For a Spec Loop task, planning uses the same task sections for
implementation and investigation. It must cover current scope,
material clarified decisions, relevant research and constraints,
Design, review expectations, and needed verification.

Use the shared task guidance from
[common-task-guidance.md](common-task-guidance.md).

## Planning-form-specific handling

### Chat-only

When this planning form is in use:
- read [chat-only-path-guidance.md](chat-only-path-guidance.md) fully; and
- follow it together with [common-task-guidance.md](common-task-guidance.md).

If durable state becomes unsafe to keep only in chat, switch to the
task-file planning form before continuing.

### Task-file

When this planning form is in use:
- read [task-file-path-guidance.md](task-file-path-guidance.md) fully;
- use it together with [common-task-guidance.md](common-task-guidance.md);
- create or update the active task file before EXECUTION starts; and
- use `tasks/` as the default task directory when project
  instructions do not define one.

Before requesting execution approval for task-file work, use
[spec-loop-prepare-execution-approval/SKILL.md](../spec-loop-prepare-execution-approval/SKILL.md).

## ADR routing

For ADR-only work, use
[spec-loop-write-adr/SKILL.md](../spec-loop-write-adr/SKILL.md) for ADR location, naming,
structure, and update rules.

If task planning or accepted investigation findings show that a
durable architecture or policy decision is needed, route ADR work
through [spec-loop-write-adr/SKILL.md](../spec-loop-write-adr/SKILL.md).
Do not hide ADR work inside clarification.

If ADR work is part of a larger implementation change, keep it in
that task and use [spec-loop-write-adr/SKILL.md](../spec-loop-write-adr/SKILL.md) during
EXECUTION.

## Glossary policy

Default glossary policy:
- glossary use is opted in;
- project or session instructions may opt out;
- when the project uses the Spec Loop AsciiDoc glossary format, use
  [spec-loop-write-glossary/SKILL.md](../spec-loop-write-glossary/SKILL.md);
- otherwise follow the project's glossary format.

Recognize `glossary.adoc` and `glossary.md` as project glossary files.
If both exist, ask which one is canonical before updating either.

Once a project glossary exists, treat it as the shared domain-language
source above individual tasks and code. If no explicit project
glossary exists, use `Research` plus the existing codebase as the
source of current domain language until a project glossary is created.

The presence or absence of a project glossary does not by itself
determine whether a task or subtask needs task `Glossary`. It
determines only which shared domain-language source task vocabulary
and task-glossary deltas are compared against.

When approved work changes, clarifies, or implements shared domain
terms, include any required glossary work in the plan. Perform project
glossary file edits during EXECUTION. If no project glossary
exists, create one during EXECUTION only when the task plan,
project instructions, or the User requires project-level glossary
work.

When a task or subtask needs `Scenario` or `Glossary`, read
[scenario-and-glossary-guidance.md](scenario-and-glossary-guidance.md) and follow it. Task
`Glossary` sections are planning artifacts; project glossary file
edits happen only during EXECUTION.

## Related skills

Use [spec-loop-setup-doc-rendering/SKILL.md](../spec-loop-setup-doc-rendering/SKILL.md) for rendering setup.
