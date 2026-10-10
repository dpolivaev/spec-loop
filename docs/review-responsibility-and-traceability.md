# Governance, Review, and Traceability

This document defines the governance, review, and traceability rules around Spec Loop work.

## What the workflow rules, common task guidance, and task-file path guidance enforce

The workflow rules are the normative contract between the human
developer and the model. `common-task-guidance.md` defines the full
task-file format and what each section means. Chat-only tasks follow the
same content rules in three sections. The task-file guide adds rules
that apply only to task files.
Together, they enforce at minimum:

* Explicit planning before implementation work.
* A fileless planning path in chat only for first-pass, straight-line
  work with lightweight research, a single clear implementation path,
  lightweight verification, no existing task file, and no need for
  subtasks or diagrams.
* Exactly three chat-only task sections: Scope (including constraints
  and relevant context), Design, and Test specification. Other planning
  content goes in Scope and keeps its existing meaning and rules.
  For tasks, only task files use the full format, subtasks, status
  folders, and diagrams.
* Task files as the source of truth for scope, constraints, research,
  design, test expectations, and execution status when the task-file
  path is in use, plus `Implementation notes` when meaningful
  implementation-time history must remain visible.
* Implementation tasks and subtasks are releasable by default: each
  one must be independently implementable, verifiable, reviewable,
  and acceptable unless the user explicitly opts out.
* The current chat-only task as the source of truth, with relevant
  context and final decisions in Scope. Update only changed sections.
  Repeat the complete three-section task if its current state is unclear.
* ADRs and other documentation may stand as their own planning artifacts
  when they are the requested work and no task-file rule overrides
  that.
* One living-project-document list in project instructions or the root
  README when the project has multiple living documents, with entries
  for individual documents or collections.
* A default approval gate before any code, test, or configuration
  changes, with either fileless-task approval on the fileless path or
  task-file approval on the task-file path.
* After approval, a separate implementation skill handles task-file and
  chat-only work. For task files, it handles clarification, task updates,
  and the move to `review`. For chat-only tasks, it handles clarification,
  updates to the current task, repeating it when unclear, moving it to a
  file when needed, and reporting when work is ready. Useful
  Implementation notes go in the review message, outside the
  three-section task. File-only preparation steps apply only when the
  work uses a task file.
* Implementation completeness: the approved design is implemented,
  applicable constraints are met, required living-project-document
  actions are complete, and the test specification is implemented
  unless tests are waived.
* Review includes each completed living-project-document action, its
  resulting files, and required implementation-note traceability.
* Traceability discipline: identifiers in commit messages, and
  status/folder consistency where task files are in use.

If a local convention conflicts with the applicable workflow rules or
Task-file Path Guidance, the governing rule wins.

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

A task file is not a general historical narrative. It is the
stabilized description of what must be true now to implement the
active task correctly, or the current subtask plus needed task-level
context when subtasks are used.

Practically:

* Research records observations and verified facts only.
* Constraints record binding limits for the active task, or for the
  current subtask plus needed task-level context when subtasks are
  used.
* Design records the approved target design intent for the active
  task, or for the current subtask plus needed task-level context
  when subtasks are used.
* Test specification defines the verification that must exist for completion.
* Implementation notes, when present, keep only the bounded
  implementation-time decision trail that later review needs.

History still belongs primarily in version control. `Implementation
notes` is the narrow exception for implementation-time decisions that
would otherwise be lost. The task file still represents the current
intent.

### Constraints as a control layer

When a task includes **Constraints**, they capture the limits that the target
design and implementation must obey.

Typical examples are semantic invariants, non-goals, compatibility limits,
identity rules, performance limits, and forbidden simplifications.

If **Design** conflicts with **Constraints**, **Constraints** wins.

### Briefing as a soft entry point

Each current task file, or task file ready for implementation, includes a
**Briefing** section to help the reader get started. Chat-only tasks keep
that information in Scope. Initial backlog tasks and
subtasks created by
[spec-loop-plan-work-breakdown](../skills/spec-loop-plan-work-breakdown/)
may omit Briefing until they become current.

Briefing is for:

* someone unfamiliar with the codebase,
* the contributor returning to the task after time has passed,
* onboarding new contributors.

The briefing explains what matters, where to look first, and which
modules, classes, and stack decisions orient a newcomer quickly.

It is not a summary of the task history. It is a guide for understanding the
current intent.

## Approval boundaries

Spec Loop has two planning approval surfaces:

* Fileless planning-path approval in chat.
* Task-file approval on the task-file path.

A task file is the default. Before using a lighter form, the model must
check that it meets the rules and ask the user's permission. If the user
has already chosen an allowed form, do not ask again. When the current
form stops meeting the rules, change form and follow the new form's
requirements. This does not approve execution.

For a chat-only task, the model must get explicit execution approval
once the task is ready. The three-section format does not remove that
requirement.

On the task-file path:

* The model may edit task files without prior approval.
* If task files were edited and there is no execution directive, the
  model must request user review before changing code, tests, or
  configuration.
* An explicit directive such as “implement”, “investigate”, “go ahead”,
  “proceed”, or “apply” counts as `PLAN -> EXECUTION` approval only
  when the active task is ready and the directive clearly refers to
  that task. If readiness or the referent is unclear, ask.
* After task-file execution approval for implementation work,
  [spec-loop-implementation-flow](../skills/spec-loop-implementation-flow/) governs implementation-time
  clarification, the post-implementation `Implementation notes`
  checkpoint, and the move to `review`.
* After task-file execution approval for investigation work, the active
  task records reviewed output in `Findings` and moves to `review`.

On the fileless path, after fileless execution approval for
implementation work,
[spec-loop-implementation-flow](../skills/spec-loop-implementation-flow/) governs implementation-time
clarification, canonical chat-task updates, complete-task recovery
re-emission when needed, promotion to the task-file path when fileless
simplicity no longer holds, and readiness reporting.

If implementation stays within the approved design and only bounded
clarification is needed, the canonical task artifact is updated in
place and work continues. If scope or another approved contract
changes materially, the model proposes next steps and requests renewed
approval before continuing.

## Phase model

Spec Loop defines work phases: **PLAN**, **EXECUTION** and **DONE**.

By default, phase transitions are constrained:

* `PLAN -> EXECUTION` requires explicit approval.
* `EXECUTION -> DONE` requires explicit approval.
* Any new request, refinement, extension, or follow-up resets work to `PLAN`.

This keeps the model aligned and prevents implementation from continuing by inertia
after scope changes.

## Review boundaries that map to normal practice

Spec Loop separates agreement on intent from review of implementation.
Even with simplified statuses, review gates still exist at the
execution-approval boundary, at the task-file-path move-to-review
boundary when that path is in use, and at final completion approval.

Reviewers assess correctness against approved intent.

## PlantUML as a design artifact

The task-file path guidance requires Design sections on the task-file
path to use PlantUML diagrams that model structure or flow (class,
component, sequence), with strict formatting rules.

Design remains reviewable as a first-class artifact and is not encoded only in
implementation.

## Reading planned-change markers

Automated test entries and task-file Design class diagrams use **+** for
additions, **=** for unchanged reuse, and **±** for modifications. These
show planned changes from the task's starting state, not progress or test
results.

Files and types are classified separately from their cases and members.
For example, an existing test file gaining a case is **±**, its new case
is **+**, and unchanged cases remain **=**.

For diagram formatting, see [Diagram and Rendering Policy](diagram-and-rendering-policy.md).

## Traceability mechanics

Spec Loop makes intent recoverable after the fact:

* Task files define the intent boundary for a set of commits when the
  task-file path is in use.
* Fileless tasks define the intent boundary in chat while the
  fileless path remains active.
* Commit messages are structured artifacts and must start with the Primary
  Identifier:

  * Ticket ID when present, otherwise the Task Identifier.

This links implementation changes to an explicit, reviewable specification.

## Status folders and lifecycle discipline

On the task-file path, work is organized by status folders in the
task directory:

* backlog: planned or deferred work; initial backlog tasks and
  subtasks created by
  [spec-loop-plan-work-breakdown](../skills/spec-loop-plan-work-breakdown/)
  may contain only title, Scope, and Motivation until they become
  current.
* in-progress: active research, design, implementation, or verification;
  subtasks carry explicit status.
* done: user-verified completion; prefix rules preserve ordering.

Before commits on the task-file path, the model validates task
status consistency and proposes folder or status updates. These are
applied only after explicit user confirmation, unless the user
explicitly instructed to commit.

## Definition of done in team context

Completion is not inferred from working code.

A task or subtask is considered done only when:

* the approved design is fully implemented,
* the test specification is implemented and passing,
* any deviations are documented in the active task artifact,
* required living-project-document actions are complete and included in
  review,
* the user explicitly approves the transition to **done**.

This applies equally to human-written and model-written code.

## Architecture Decision Records

Use ADRs for decisions that outlive a single task, such as public behavior,
dependencies, or long-term design.

ADRs capture context, decision, and consequences without turning task files into
long-lived design encyclopedias.
