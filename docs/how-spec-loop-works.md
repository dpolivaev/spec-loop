# How Spec Loop Works

Spec Loop follows this workflow:
- **clarify** - [spec-loop-clarify-task](../skills/spec-loop-clarify-task/) resolves material unresolved
  questions before or during planning.
- **plan** - the [spec-loop-plan-task](../skills/spec-loop-plan-task/) bundle governs plan-first
  work, including planning-form selection, the fileless planning path
  in chat, the task-file path when needed, ADR and other documentation
  routing, Scenario and task Glossary triggers, and the gate before
  execution.
- **break down work** - after planning-form selection chooses subtasks
  or multiple task files / backlog items,
  [spec-loop-plan-work-breakdown](../skills/spec-loop-plan-work-breakdown/) governs
  file-based decomposition and requires implementation tasks and
  subtasks to be releasable by default.
- **approve** - you approve either a fileless task in chat or a
  task-file plan; on the task-file path,
  [spec-loop-prepare-execution-approval](../skills/spec-loop-prepare-execution-approval/) prepares the task for
  that approval step.
- **execute implementation** - after execution approval for
  implementation work on either planning path,
  [spec-loop-implementation-flow](../skills/spec-loop-implementation-flow/) governs
  code, tests, and approved actions for affected living project
  documents.
- **execute investigation** - after execution approval for
  investigation work, the active task records reviewed output in
  `Findings` and is presented or moved to `review`.
- **review/ready** - [spec-loop-implementation-flow](../skills/spec-loop-implementation-flow/) governs
  implementation work's move to `review` on the task-file path and
  readiness reporting on the fileless path.

The planning and approval rules for that workflow live in the
[spec-loop-plan-task](../skills/spec-loop-plan-task/) bundle and its
companion files. File-based work breakdown rules live in
[spec-loop-plan-work-breakdown](../skills/spec-loop-plan-work-breakdown/).

The planning bundle starts with
**[SKILL.md](../skills/spec-loop-plan-task/SKILL.md)**,
**[planning-form-selection-guidance.md](../skills/spec-loop-plan-task/planning-form-selection-guidance.md)**,
and **[common-task-guidance.md](../skills/spec-loop-plan-task/common-task-guidance.md)**.
When Scenario or task Glossary work is needed, it also uses
**[scenario-and-glossary-guidance.md](../skills/spec-loop-plan-task/scenario-and-glossary-guidance.md)**,
plus **[chat-only-path-guidance.md](../skills/spec-loop-plan-task/chat-only-path-guidance.md)**
on the chat-only path and
**[task-file-path-guidance.md](../skills/spec-loop-plan-task/task-file-path-guidance.md)**
on the task-file path.

The [spec-loop-write-glossary](../skills/spec-loop-write-glossary/) skill defines the Spec Loop AsciiDoc project glossary
format in
**[glossary-format.md](../skills/spec-loop-write-glossary/glossary-format.md)**.

The [spec-loop-setup-doc-rendering](../skills/spec-loop-setup-doc-rendering/) skill helps users prepare and
troubleshoot rendering for task files and glossary files. If a user
does not want to use the skill directly, see
**[vscode-setup.md](../skills/spec-loop-setup-doc-rendering/vscode-setup.md)**
and
**[jetbrains-setup.md](../skills/spec-loop-setup-doc-rendering/jetbrains-setup.md)**
for manual editor-specific setup references.

The [spec-loop-review-change](../skills/spec-loop-review-change/) skill is optional. It reviews existing
changes from local or trusted sources. It can produce a high-level
assessment, a file-wise walk-through, or both.

The model uses these skills while drafting and updating plans, task,
or review artifacts; you review and approve either a fileless chat
task or a task-file plan before execution. Approved implementation
then continues under [spec-loop-implementation-flow](../skills/spec-loop-implementation-flow/).
On task-file implementation work, it governs implementation-time
routing, `Implementation notes`, and the move to `review`. On the
fileless path, it governs canonical chat-task maintenance, recovery
re-emission or promotion, and readiness reporting. When the code
already exists, you inspect a retrospective walk-through or assessment
instead.

Spec Loop also defines explicit work phases: PLAN, EXECUTION, and
DONE. Transitions to EXECUTION and DONE require explicit user approval.

During planning, active task artifacts may use `Scenario` and task
`Glossary` sections to ground behavior and extract task-local terms.
On the task-file path this means task files. On the chat-only path
this means the canonical chat-only task kept in chat.

When a project maintains a glossary described by the shared task
semantics
[project glossary section](../skills/spec-loop-plan-task/common-task-guidance.md#project-glossary-policy),
that project glossary defines the shared domain language above
individual tasks and the code. It keeps design documents, tests, code
symbols, and commit text aligned on the same terms across the whole
project.

If no project glossary exists yet, current domain language comes from
`Research` plus the existing codebase until one is created.

Consistent reuse of approved terms across the shared glossary source,
`Scenario`, `Design`, and `Test specification` keeps meaning,
behavior, design contracts, and verification aligned. When a proposed
term means the same thing as an existing abstraction, Spec Loop reuses
the current name or requires an explicit proposal to rename or
restructure that abstraction.

Spec Loop is designed to work with existing codebases at scale.
Before detailed design or implementation, the model captures relevant
knowledge in Research for the current task, or for the current
subtask plus needed task-level context when subtasks are in use:
existing behavior, constraints, APIs, interfaces, and established code
practices.

It follows the classic research–plan–implement approach, broken down
into small, reviewable tasks and subtasks.

The research is explicitly scoped to the current task, or to the
current subtask plus needed task-level context when subtasks are in
use. It captures only what is required to implement that scope
correctly, and is intentionally partial. The result is a bounded,
reviewable understanding whose size remains manageable.

For large codebases, task `Glossary` sections and the project glossary
are especially useful because they keep domain terms stable across
many tasks, subtasks, files, and subsystems.

Because the scope can be kept reasonably small and the research is
written down, you can verify that the model examined the right parts of
the codebase, identified the correct interfaces, and aligned with
existing practices before any code is written. This is especially
valuable in legacy systems: it prevents clean-room redesigns and makes
incremental change safer.

## Document Types and Lifetimes

Spec Loop uses more than one document type on purpose. They do not have
the same job or the same lifetime.

- Fileless chat tasks are short-lived canonical chat artifacts for
  simple work on the fileless path. They exist to drive research,
  implementation, and verification without task-file overhead. If
  alignment becomes unsafe, they are re-emitted or promoted to task
  files.
- Task files are short-lived working artifacts for the next concrete
  task on the task-file path, or for the current subtask plus needed
  task-level context when a task uses subtasks. They exist to drive
  research, review, implementation, and testing for that scope. When
  needed, they may also include `Scenario` and task `Glossary`
  sections.
- Living project documents capture current truth that should remain
  useful after the task is accepted. They include specifications, API
  contracts, architecture and operations documents, current ADRs, and
  project glossaries. ADRs preserve durable decisions and their
  reasons. A project glossary preserves shared language across tasks,
  design, tests, code symbols, and commits.
- Documentation-only work may stand on its own when no implementation
  change is involved and no project rule requires a task file.
- Review files reconstruct and assess already-implemented work from
  trusted pull requests, merge requests, or commit ranges. When needed,
  they may also produce GitHub-friendly Mermaid variants for sharing
  the review.

Spec Loop does not require a project to have living project documents.
When none are otherwise needed, the project does not need to create
documents or a list for workflow compliance.

Projects with multiple living project documents keep one list named
`Living Project Documents` in an existing project instruction file or, by
default, the root README. The file containing the list is implicit. The
list may identify individual documents or collections such as an arc42
document set. If an existing project glossary is omitted while other
living documents are listed, the model asks how to handle it. When a
project glossary is the only living document, it remains in use without
requiring a list. Planned document actions update, create, or supersede;
ADR actions follow the ADR lifecycle.

Historical task files do not need to be kept mutually consistent
across time. The active task artifact, however, should stay aligned
with applicable living project documents and implemented code for its
scope.

If a project maintains a technical design document, its purpose is to
describe the current technical shape, stable boundaries, and important
flows. It should not become a second glossary or a catalog of transient
implementation detail.
