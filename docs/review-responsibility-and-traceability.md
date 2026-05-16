# Spec Loop — Task Files, Review, Responsibility, and Traceability

This document captures what Spec Loop implies in team environments.

## What the workflow rules and task-file constitution enforce

The workflow rules are the normative contract between the human
developer and the model. When Spec Loop uses a task file, the task-file
constitution governs that artifact. Together, they enforce at minimum:

* Explicit planning before executable work.
* A short planning path in chat only for first-pass, straight-line work
  with lightweight research, a single clear implementation path,
  lightweight verification, and no existing task file.
* Task files as the source of truth for scope, constraints, research,
  design, and execution status when the task-file path is in use.
* ADRs and documentation may stand as their own planning artifacts when
  they are the requested work and no task-file rule overrides that.
* A default approval gate before any code, test, or configuration
  changes, with either chat-plan approval on the short path or
  task-file approval on the task-file path.
* Implementation completeness: design, constraints when present, and
  test specification implemented unless tests are explicitly waived.
* Traceability discipline: identifiers in commit messages, and
  status/folder consistency.

If a local convention conflicts with the applicable workflow rules or
Task-file Constitution, the governing rule wins.

## The human developer’s role

A central assumption of Spec Loop is that **the human developer remains the
primary source of understanding and intent**.

The model is treated as a powerful implementation and reasoning aid that
operates under explicit constraints, not as an independent decision-maker.

The developer is responsible for judging correctness, scope, and
relevance. The model operates within the boundaries defined by the
approved plan and requires explicit approval to cross implementation
gates. On the task-file path, the task file is the source of truth for
that approved plan.

## Task files as present truth

A task file is not a historical narrative. It is the stabilized description of
what must be true now to implement the next increment correctly.

Practically:

* Research records observations and verified facts only.
* Constraints record binding limits for the increment when needed.
* Design records the approved target design intent for the increment.
* Test specification defines the verification that must exist for completion.

History belongs in version control. The task file represents the current intent.

### Constraints as a control layer

When a task includes **Constraints**, they capture the limits that the target
design and implementation must obey.

Typical examples are semantic invariants, non-goals, compatibility limits,
identity rules, performance limits, and forbidden simplifications.

If **Design** conflicts with **Constraints**, **Constraints** wins.

### Briefing as a soft entry point

Each task includes a **Briefing** section that serves as a soft entry
point:

* for someone unfamiliar with the codebase,
* for the contributor returning to the task after time has passed,
* for onboarding new contributors.

The briefing explains what matters, where to look first, and which
modules, classes, and stack decisions orient a newcomer quickly.

It is not a summary of the task history. It is a guide for understanding the
current intent.

## Approval boundaries

Spec Loop has two planning approval surfaces:

* Short planning path approval in chat.
* Task-file approval on the task-file path.

On the short planning path, the model must ask the user to approve both
skipping task-file creation and implementing from the chat plan.

On the task-file path:

* The model may edit task files without prior approval.
* If task files were edited and there is no implementation directive,
  the model must request user review before changing code, tests, or
  configuration.
* An explicit directive such as “implement”, “go ahead”, or “proceed”
  counts as approval and must not trigger another approval request.

If scope changes materially, the model proposes next steps and requests
approval before continuing.

## Phase model

Spec Loop defines work phases: **PLAN**, **IMPLEMENTATION** and **DONE**.

By default, phase transitions are constrained:

* `PLAN -> IMPLEMENTATION` requires explicit approval.
* `IMPLEMENTATION -> DONE` requires explicit approval.
* Any new request, refinement, extension, or follow-up resets work to `PLAN`.

This keeps the model aligned and prevents implementation from continuing by inertia
after scope changes.

## Review boundaries that map to normal practice

Spec Loop separates agreement on intent from review of implementation.
Even with simplified statuses, review gates still exist at the task-file
approval boundary and at final completion approval.

Reviewers assess correctness against approved intent.

## PlantUML as a design artifact

The task-file constitution requires Design sections as PlantUML
diagrams that model structure or flow (class, component, sequence),
with strict formatting rules.

Design remains reviewable as a first-class artifact and is not encoded only in
implementation.

## Traceability mechanics

Spec Loop makes intent recoverable after the fact:

* Task files define the intent boundary for a set of commits.
* Commit messages are structured artifacts and must start with the Primary
  Identifier:

  * Ticket ID when present, otherwise the Task Identifier.

This links implementation changes to an explicit, reviewable specification.

## Status folders and lifecycle discipline

Work is organized by status folders in the task directory:

* backlog: planned or deferred work; research and design live here until design
  is approved.
* in-progress: active research, design, implementation, or verification;
  subtasks carry explicit status.
* done: user-verified completion; prefix rules preserve ordering.

Before commits, the model validates task status consistency and proposes folder
or status updates. These are applied only after explicit user confirmation,
unless the user explicitly instructed to commit.

## Definition of done in team context

Completion is not inferred from working code.

An increment is considered done only when:

* the approved design is fully implemented,
* the test specification is implemented and passing,
* any deviations are documented in the task file,
* the user explicitly approves the transition to **done**.

This applies equally to human-written and model-written code.

## Architecture Decision Records

Use ADRs for decisions that outlive a single task, such as public behavior,
dependencies, or long-term design.

ADRs capture context, decision, and consequences without turning task files into
long-lived design encyclopedias.
