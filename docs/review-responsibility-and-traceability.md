# Spec Loop — Task Files, Review, Responsibility, and Traceability

This document captures what Spec Loop implies in team environments.

## What the Constitution enforces

The Constitution is the normative contract between the human developer and the
model. It enforces, at minimum:

* Task files as the source of truth for scope, research, design, and execution
  status.
* Research-first unless explicitly waived.
* A default approval gate at the task file boundary before any code, test, or
  configuration changes.
* Implementation completeness: design and test specification implemented unless
  tests are explicitly waived.
* Traceability discipline: identifiers in commit messages, and status/folder
  consistency.

If a local convention conflicts with the Constitution, the Constitution wins.

## The human developer’s role

A central assumption of Spec Loop is that **the human developer remains the
primary source of understanding and intent**.

The model is treated as a powerful implementation and reasoning aid that
operates under explicit constraints, not as an independent decision-maker.

The developer is responsible for judging correctness, scope, and relevance.
The model operates within the boundaries defined by the task file and requires
explicit approval to cross implementation gates.

## Task files as present truth

A task file is not a historical narrative. It is the stabilized description of
what must be true now to implement the next increment correctly.

Practically:

* Research records observations, constraints, and verified facts only.
* Design records the current design intent for the increment.
* Test specification defines the verification that must exist for completion.

History belongs in version control. The task file represents the current intent.

### Developer Briefing as a soft entry point

Each task includes a **Developer Briefing** section that serves as a soft entry
point:

* for reviewers unfamiliar with the codebase,
* for the developer returning to the task after time has passed,
* for onboarding new contributors.

The briefing explains what matters, where to look first, and why the design
looks the way it does.

It is not a summary of the task history. It is a guide for understanding the
current intent.

## Approval boundaries

The default approval gate is task file approval:

* The model may edit task files without prior approval.
* If task files were edited and there is no implementation directive, the model
  must request user review before changing code, tests, or configuration.
* An explicit directive such as “implement”, “go ahead”, or “proceed” counts as
  approval and must not trigger another approval request.

If scope changes materially, the model proposes next steps and requests approval
before continuing.

## Review boundaries that map to normal practice

Spec Loop separates agreement on intent from review of implementation.

* Plan Review aligns with refinement and design review: scope, design, risks,
  and test specification are approved before implementation.
* Implementation Review aligns with code review readiness: code is judged
  against already-approved intent.

Reviewers assess correctness against approved intent.

## PlantUML as a design artifact

The Constitution requires Design sections as PlantUML diagrams that model
structure or flow (class, component, sequence), with strict formatting rules.

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
* in-progress: active planning or implementation; subtasks carry explicit phase
  status.
* finished: user-verified completion; prefix rules preserve ordering.

Before commits, the model validates task status consistency and proposes folder
or status updates. These are applied only after explicit user confirmation,
unless the user explicitly instructed to commit.

## Definition of done in team context

Completion is not inferred from working code.

An increment is considered done only when:

* the approved design is fully implemented,
* the test specification is implemented and passing,
* any deviations are documented in the task file,
* the user explicitly approves the transition to **Finished**.

This applies equally to human-written and model-written code.

## Architecture Decision Records

Use ADRs for decisions that outlive a single task, such as public behavior,
dependencies, or long-term design.

ADRs capture context, decision, and consequences without turning task files into
long-lived design encyclopedias.
