# How Spec Loop Works

Spec Loop follows this workflow:
- **clarify** - [spec-loop-clarify-task](../skills/spec-loop-clarify-task/) resolves material unresolved
  questions before or during planning.
- **plan** - the [spec-loop-plan-task](../skills/spec-loop-plan-task/) bundle governs plan-first
  work, including the fileless planning path in chat, the task-file
  path when needed, ADR and documentation routing, glossary
  triggers, and the gate before implementation.
- **approve** - you approve either a fileless task in chat or a
  task-file plan; on the task-file path,
  [spec-loop-prepare-implementation-approval](../skills/spec-loop-prepare-implementation-approval/) prepares the task for
  that approval step.
- **implement** - after implementation approval on either planning
  path, [spec-loop-implementation-flow](../skills/spec-loop-implementation-flow/) governs
  implementation-time work.
- **review/ready** - [spec-loop-implementation-flow](../skills/spec-loop-implementation-flow/) also governs
  the move to `review` on the task-file path and readiness
  reporting on the fileless path.

The planning and approval rules for that workflow live in the [spec-loop-plan-task](../skills/spec-loop-plan-task/) bundle and its companion files.

The planning bundle starts with
**[SKILL.md](../skills/spec-loop-plan-task/SKILL.md)** and
**[common-task-guidance.md](../skills/spec-loop-plan-task/common-task-guidance.md)**,
plus **[fileless-path-guidance.md](../skills/spec-loop-plan-task/fileless-path-guidance.md)**
on the fileless path and
**[task-file-path-guidance.md](../skills/spec-loop-plan-task/task-file-path-guidance.md)**
on the task-file path.

The [spec-loop-write-glossary](../skills/spec-loop-write-glossary/) skill defines the Spec Loop AsciiDoc glossary
format in
**[glossary-format.md](../skills/spec-loop-write-glossary/glossary-format.md)**.

The [spec-loop-setup-doc-rendering](../skills/spec-loop-setup-doc-rendering/) skill helps users prepare and
troubleshoot rendering for task files and glossary files.

The [spec-loop-assess-pull-request](../skills/spec-loop-assess-pull-request/) skill is optional. It reconstructs
retrospective review files from trusted pull requests, merge requests,
or commit ranges and can generate GitHub-friendly Mermaid variants
from them when needed.

The model uses these skills while drafting and updating plans, task,
or review artifacts; you review and approve either a fileless chat
task or a task-file plan before implementation. Approved
implementation then continues under [spec-loop-implementation-flow](../skills/spec-loop-implementation-flow/).
On task-file work, it governs implementation-time routing,
`Implementation notes`, and the move to `review`. On the fileless
path, it governs canonical chat-task maintenance, recovery re-emission
or promotion, and readiness reporting. When the code already exists,
you inspect retrospective review files instead.

Spec Loop also defines explicit work phases: plan, implementation, and done.
Any transitions to implementation and to done require explicit user approval.

When a project maintains a glossary described by the shared task
semantics
[project glossary section](../skills/spec-loop-plan-task/common-task-guidance.md#project-glossary),
that glossary defines the shared domain language above individual
tasks and the code. It keeps design documents, tests, code symbols,
and commit text aligned on the same terms across the whole project.

Spec Loop is designed to work with existing codebases at scale.
Before any design or implementation step, the model captures relevant
knowledge in the Research section of the active task artifact:
existing behavior, constraints, APIs, interfaces, and established
code practices.

It follows the classic research–plan–implement approach, broken down
into small, incremental sub-tasks.

The research is explicitly scoped to the next increment. It captures only
what is required to implement that increment correctly, and is intentionally
partial. The result is a bounded, reviewable understanding whose size
remains manageable.

For large codebases, the glossary is especially useful because it
keeps domain terms stable across many increments, files, and
subsystems.

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
  slice of work when the task-file path is in use. They exist to drive
  research, review, implementation, and testing of that slice.
- ADRs capture durable decisions and the reasons behind them.
- Documentation-only work may stand on its own when no executable
  change is involved and no project rule requires a task file.
- A glossary captures stable shared language across tasks, design,
  tests, code symbols, and commits.
- Review files reconstruct and assess already-implemented work from
  trusted pull requests, merge requests, or commit ranges. When needed,
  they may also produce GitHub-friendly Mermaid variants for sharing
  the review.
- Living project documents capture current truth that should remain
  useful after the task is accepted, such as technical shape,
  operations, or other stable project knowledge.

Historical task files do not need to be kept mutually consistent
across time. The active task artifact, however, should stay aligned
with the glossary, living project documents, and implemented code for
its scope.

If a project maintains a technical design document, its purpose is to
describe the current technical shape, stable boundaries, and important
flows. It should not become a second glossary or a catalog of transient
implementation detail.
