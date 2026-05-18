---
name: spec-loop-plan-task
description: >-
  Mandatory unless the user explicitly opts out. Use when
  non-trivial work on features, bug fixes, refactorings, or
  changes to code, tests, configuration, dependencies, runtime
  assets, or design requires explicit planning before
  implementation.
---

This skill is mandatory unless the user explicitly opts out.

Read this file fully unless it is already loaded in the current
session context.

Apply project instructions such as `AGENTS.md` when present.

After loading the applicable rules, immediately emit 🫡.

## Shared workflow rules

- **Principle over ceremony**
- Intent-first. Judge by outcome, not checklist.
- Whenever the LLM stops or pauses, it must explain the reason
  explicitly.
- If `AGENTS.md` and these rules conflict, stop and ask the User.
- **Enforcement, pre-edit gate, and LLM stewardship**
- These rules are mandatory. The LLM enforces them.
- Only the User may override these rules.

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

- Ask questions before PLAN whenever scope, constraints, domain
  language, expected behavior, priorities, or other essential
  uncertainties remain.
- During PLAN, ask targeted User questions as soon as essential doubts
  appear in Research or Design. Do not guess through material
  ambiguity just because work is not yet formally blocked.
- No permission questions for already requested work.
- Starting PLAN artifacts, entering IMPLEMENTATION, and marking DONE
  require explicit User instruction.
- If scope, design, naming, or logic changes, request re-approval. No
  IMPLEMENTATION inertia across items.
- After approval, new top-level production types, renamed planned
  structural elements, new cross-class boundaries, and new externally
  meaningful identifiers require a return to PLAN and renewed
  approval. Purely internal work inside an approved class boundary does
  not, unless the User asked for lower-level review.
- Phases are exclusive unless the User allows combined
  planning-plus-implementation.

If implementation must meaningfully deviate from the approved plan, use
this skill again before continuing.

Phase model governs executable work and documentation coupled to that
work. Standalone documentation work is outside it unless the User or
project instructions say otherwise.

## Planning paths

Executable changes require explicit planning before implementation.

Choose between:

- the short planning path documented in chat, and
- the task-file path documented in task artifacts.

Take the short planning path when all of the following are true:

- this is the first planning pass for the task in the current
  conversation;
- the required research is lightweight;
- the design has a single clear implementation path;
- the required verification is lightweight and easy to track in chat;
- no task file exists yet for this task.

In that case, document the plan in chat only and present it to the
user as a request to approve both:

- using the short planning path without creating a task file, and
- implementation from that chat plan.

Project instructions or the user may still require the task-file path
for work that would otherwise match the short planning path.

Use the task-file path otherwise.

Once a task file exists for a task, continue using that task file for
that task instead of moving planning back into chat.

If the short path later needs heavier research, more than one
plausible implementation path, or heavier verification, use this skill
again and promote the task to a task file before continuing.

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

- include the glossary update in the plan, including short-path work;
- perform the glossary update during IMPLEMENTATION;
- use `spec-loop-write-glossary` when the glossary uses the Spec Loop
  AsciiDoc glossary format;
- otherwise update the glossary directly in the project's active
  format.

## Task-file path

When the task-file path is in use:

- read [task-file-constitution.md](./task-file-constitution.md) fully;
- create or update the required task files under it;
- draft or revise the active task file to capture Research, Scenario
  when required, Design, Test specification, and task administration
  needed for the current increment;
- if no suitable task file exists yet, create one before executable
  changes;
- chat stays for coordination and approvals, but the task file is the
  durable planning artifact for that task;
- before treating task-file planning as complete or asking for
  implementation approval from a task file, use
  [../spec-loop-prepare-implementation-approval/SKILL.md](../spec-loop-prepare-implementation-approval/SKILL.md).

If project instructions do not define a task directory, use `tasks/`
as the default.

When drafting or repairing embedded PlantUML in task files, follow
`examples/example-task-wordle-cli.md` as a collection of valid diagram
patterns and section-placement examples.
Do not treat it as a required task length or as a model for how much
detail every task or first planning pass must contain.
