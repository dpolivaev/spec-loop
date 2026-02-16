# Constitution

- **Principle over ceremony**  
  This Constitution is intent-first. Compliance is judged by outcome and
  behavior, not checklist formality.
- When uncertain or before changing behavior, propose next steps, ask
  for approval, then act. For task-scoped implementation, approval may
  come from task-file approval or explicit directives such as
  "implement", "implement it now", "go ahead", or "proceed". The
  User may override this workflow in their request, request an
  additional review gate, or require a new gate when scope changes.
- Only the User may override, relax, or redefine workflow rules in this
  Constitution. The LLM may propose changes, but must not reinterpret,
  weaken, or apply rule changes without explicit User approval.
- If `AGENTS.md` and this Constitution conflict, the LLM must stop and
  ask the User to clarify before proceeding.
- **Enforcement, pre-edit gate, and LLM stewardship**  
  This Constitution is mandatory. The User is not required to know it.
  The LLM is fully responsible for enforcement and must not shift that
  responsibility to the User.

## Constitution Handling (Global)

- This Constitution is global and identical across all directories.
- If the Constitution content is already injected or attached in the
  current session, do not re-read it.
- Otherwise, read it once and keep a short active digest (3-5 lines)
  in working context. Use that digest to drive decisions.
- Re-read the full Constitution only if the active digest is missing
  from context or the User says the Constitution changed.

### Active Rules Digest

- PLAN is the starting mode for all work.
- Phase gates: PLAN -> IMPLEMENTATION requires explicit approval.
- Task-first: research and design live in task files.
- Follow task formatting rules.
- No code/test/config changes without approval.
- Stop and ask when scope or design changes.

## Spec Loop Phases and Transitions

Phases:

- **PLAN** - research, design/spec changes, test specification.
- **IMPLEMENTATION** - code and test code, strictly following approved
  design.
- **DONE** - verified and accepted completion.

In PLAN, edits are allowed only for non-executable artifacts used for
research, design, planning, or governance, including task files, ADRs,
documentation, diagrams, and instruction files (for example,
AGENTS.md and CONSTITUTION.md).

Any change that affects executable behavior, tests, build/configuration,
dependencies, packaging, or runtime assets is IMPLEMENTATION and
requires explicit User instruction to enter IMPLEMENTATION.

If classification is unclear, stop and ask the User before editing.

Work starts in **PLAN** and returns to **PLAN** after each completed
work item unless the User explicitly specifies another flow.

The model MUST NOT continue IMPLEMENTATION by inertia across work
items; each new item requires a fresh PLAN -> IMPLEMENTATION approval.

Phases are exclusive unless the User explicitly allows planning and
implementation together.

The following transitions require **explicit User instruction or
approval**: PLAN -> IMPLEMENTATION, IMPLEMENTATION -> PLAN,
IMPLEMENTATION -> DONE.

This phase model governs task-scoped implementation work; ADR-only,
research-only, and analysis-only requests remain in PLAN unless the
User requests otherwise.

Implementation approval gate:

- Design approval is mandatory before any code, test, or configuration
  change.
- Refactoring that changes code, tests, or configuration is
  implementation and requires an explicit Design update and approval.
- If task files were edited and there is no implementation directive,
  request user review before making code, test, or configuration
  changes.
- Explicit directives such as "implement", "implement it now",
  "go ahead", or "proceed" count as implementation approval.
- After approval, proceed without extra approval
  unless the user asks for another review gate.
- If implementation scope drifts beyond Design (for example, new type,
  flow, dependency, or behavior-affecting method change), stop, update
  Design, request approval, then continue.

## Task-first planning

Task-first workflow is mandatory for work that changes code, tests, or
configuration.

For ADR-only, research-only, or analysis-only requests with no code,
test, or configuration changes, task-first workflow does not apply and
no task file is required unless the User explicitly requests task-based
tracking.

If such non-code work later leads to implementation, the process must
enter PLAN in a task file before any code, test, or configuration
change.

When task-first workflow applies and no suitable task file exists for
the current request, the model should propose creating a new task file
before performing research or design.

Design and research for task-scoped implementation MUST NOT be
developed directly in chat unless the User explicitly allows planning
without a task file.

All current design decisions for scoped work MUST be written in the
task-file Design section before IMPLEMENTATION starts. Start refactoring
with an explicit Design update in the existing task; if needed, create
another task or subtask.

Chat is a coordination channel, not a design artifact.

Phase transitions and implementation approval gates are defined in
**Spec Loop Phases and Transitions**.

## Workflow

Rules in this section complement, and do not override,
**Spec Loop Phases and Transitions**.

1. **Task files as source of truth**  
   All tasks, design, and execution status live as individual Markdown
   files under the project task directory, organized by status folders.
   New task file names must not use ticket IDs, task identifiers, or
   numeric prefixes, and must avoid abbreviations (use readable,
   descriptive words). Done tasks use the required three-digit
   completion prefix defined in this Constitution. Task-first workflow
   from the section above applies unless the User explicitly chooses
   otherwise.

2. **Research baseline**  
   Start with research unless the user explicitly waives it. Record
   findings in task **Research**. Record observations, constraints, and
   verified facts only; do not include planned actions or steps. Plans
   and changes belong in **Design**.

3. **Design specification**  
   Document architecture, data flow, class/component interactions, and
   test-impacting decisions in **Design**. Draft design from validated
   **Research** findings. Designs may describe file scope broadly when
   it remains unambiguous.

   Design sections must use PlantUML diagrams
   (class/component/sequence). Do not use PlantUML notes. If mixing
   class and non-class elements (for example `database`), add
   `allowmixing`.

   Formatting: keep the diagram in its own paragraph under Design
   (blank line before code fence). Put explanatory text in a separate
   paragraph under the diagram. For class diagrams, use one outer
   package with nested inner packages and add `set separator none`.
   Include meaningful dependency labels and use at most one connector
   per class pair.

4. **Iterative discovery**  
   After drafting **Design**, iterate between **Research** and
   **Design** until decisions are supported and testable. Update both
   sections when new findings appear. No implementation starts during
   this loop.

5. **Implementation**  
   Implementation is complete only when both design and test
   specification are implemented, unless the user explicitly waives
   tests.

6. **Status updates**  
   Move task files between status folders within the project task
   directory to reflect current focus (for example, done back to
   in-progress or backlog). When reopening a task from done, keep the
   existing three-digit prefix to preserve traceability. Avoid moving
   unrelated tasks; move them only when actively worked on.

7. **Move workflow for diffs**  
   When moving tracked task files, use `git mv` and stage the move
   immediately before editing. This preserves rename tracking in diff
   tools that are not rename-aware (for example, VS Code). Do not
   unstage the rename until ready to review and commit. For new
   untracked task files, move in filesystem (not `git mv`), then run
   `git add -A`.

8. **Status validation before commits**  
   Update subtask status whenever task-file lifecycle state changes.
   Before each commit, check relevant task files and propose any status
   or folder changes needed for consistency. Apply those status changes
   only after explicit user confirmation, then proceed with the commit.
   For task-related commits, start the message with the **Primary
   Identifier**:
   - Ticket ID if present (for example `TICKET-123: ...`).
   - Otherwise full Task Identifier
     (for example `2025-01-15-research: ...`).
   For non-task updates, commit messages may omit identifiers when
   `AGENTS.md` policy allows it. If the user explicitly asks to skip
   identifiers for a commit, honor that request.
   After code or configuration changes, run relevant module tests before
   reporting.

9. **Done task cleanup**  
   Keep done tasks in the task directory under the done status folder
   with a three-digit prefix based on order moved into done. Delete them
   from the working tree after a release tag is created.

## Context Preservation

- **Task sections are source of truth**  
  Re-read relevant task sections (Scope, Research, Design, Test Spec)
  before implementation or whenever requirements are unclear. Keep only
  relevant task content in active context and avoid carrying unrelated
  content.

## Formatting

- Wrap prose at approximately 72-80 characters; avoid horizontal
  scrolling.
- Preserve semantic line breaks and consistent list indentation.
- Fenced code blocks must be unindented (except internal structure) and
  must start/end with backticks.
- Keep standalone paragraphs unindented. Continuation lines in list
  items may be indented to align with list formatting.
- Ensure Markdown renders correctly on GitHub and GitLab.

The intent is readability in plain text editors (vim, less, nano) and
rendered views.

## Task States

Tasks must use one of these status folders within the project task
directory:

- **backlog**  
  New, planned, or deferred work. Research and design belong here until
  design is approved; include ideas or deferred tasks.
  New tasks default to `backlog`.
  Exception: if `in-progress` contains no tasks and only one new task
  is being created, place that task in `in-progress`.

- **in-progress**  
  Active work in research, design, implementation, or verification.
  Subtask status must use `backlog`, `in-progress`, or `done`.
  LLMs must not set **done** unless the user explicitly requests it.
  Tasks may stay here while waiting for user confirmation before moving
  to done.

- **done**  
  The user has verified completion; move the task here with the
  required prefix before releasing.

## Task Structure

Each task uses this exact order and layout:

- Title line: `# Task: <title>`.
- Include one of the following identifiers (mutually exclusive):
  - `- **Ticket:**` Ticket ID (for example `BSK-1234`), preferred.
  - `- **Task Identifier:**` if no Ticket exists;
    `YYYY-MM-DD-<slug>` where `<slug>` is 1-2 keywords from filename
    (for example `implement-consent-flow.md` -> `...-consent`).
  - **Commit Rule**: the value present becomes the **Primary
    Identifier** for commit messages.
- Main task sections are list items with bold labels in this order:
  - `- **Scope:**`
  - `- **Motivation:**`
  - `- **Developer Briefing:**`
  - `- **Research:**`
  - `- **Design:**`
  - `- **Test specification:**`

Subtasks (if any):

- Appear only at the end as `## Subtask: <title>` sections; append new
  subtasks unless the user explicitly requests different ordering.
- Each subtask:
  - starts with `- **Status:** <status>`,
  - uses the same list-item labels and ordering as the main task,
  - represents a functional increment unless explicitly marked
    otherwise.

Subtasks may use only `backlog`, `in-progress`, or `done`.

Tasks and subtasks share the same lifecycle states and transition
guards from **Spec Loop Phases and Transitions**. Moving either to
`done` requires an explicit User request.

**Subtask Status Definitions:**

- **backlog**: planned/deferred; research, design, and test
  specification may be drafted.
- **in-progress**: active work state for research, design,
  implementation, and verification.
- **done**: complete, verified, and explicitly approved by the User.

**Definition of Done:**

Before setting a subtask to **done**:

1. **Research**: legacy state and constraints are documented as needed.
2. **Design**: architecture/data flow/class interactions are defined.
3. **Scope**: Design and Test specification are fully implemented.
4. **Verification**: new and relevant existing tests pass locally.
5. **Cleanliness**: no TODOs, placeholders, temp comments, unused
   imports.
6. **Documentation**: design deviations are documented in the task
   file.
7. **Approval**: the User explicitly approves the transition to
   **done**.

**Testing Policy:**

- Every subtask must include a testing block.
- Tasks or subtasks without code changes do not require tests.
- For no-code tasks or subtasks, keep the testing block and set
  `Automated tests: N/A` and `Manual tests: N/A`.
- Implementation subtasks without testing are not allowed.
- Avoid splitting implementation and testing across separate subtasks
  for the same functional increment.
- Separate test-focused tasks are allowed when adding or extending
  coverage as a standalone scope.
- Automated tests should be preferred.
- In each subtask, include a Test specification with explicit
  Automated tests and Manual tests sublists.
- When implementing a task, you must implement all specified tests,
  run them, and fix any failures before reporting completion, unless
  the user explicitly waives tests.
- Before moving a subtask to **done**, required tests must be
  implemented and passing unless explicitly waived by the user.

## Architecture Decision Records

- Record architecture decisions in `architecture-decisions/` as one
  file per decision with meaningful names.
- ADRs may be created directly, without a task file, unless the User
  explicitly requests task-linked ADR work.
- ADR file names must avoid prefixes and abbreviations (use readable,
  descriptive words).
- Use a short template with Title, Date, Status, Context, Decision, and
  Consequences.
- Use ADRs for decisions affecting public behavior, dependencies, or
  long-term design.
