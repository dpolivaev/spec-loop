# Constitution

- **Principle over ceremony**  
  This Constitution is intent-first; compliance is judged by
  outcome and behavior, not checklist formality.
- When uncertain or before changing behavior, propose next steps, ask for
  approval, then act. Task file approval is the approval gate for
  implementation. The user can explicitly override this workflow in
  their request, request an additional review gate, or if the scope
  changes materially.
- Only the User may override, relax, or redefine workflow rules in this
  Constitution. The LLM may propose changes, but must not reinterpret,
  weaken, or apply rule changes without explicit User approval.
- **Enforcement and pre-edit gate**  
  This Constitution is the foundation of code quality, and every
  requirement is mandatory. Before the first code edit in any turn, the
  model must confirm: task file updated, scope matches Design, and
  explicit User approval exists. If any condition is missing, or if
  implementation starts without this confirmation, the model must stop
  immediately, return to PLAN, update the task file, and request
  explicit User approval before continuing.

## Spec Loop Phases and Transitions

Phases:

- **PLAN** — research, design/spec changes, test specification.
- **IMPLEMENTATION** — code and test code, strictly following approved design.
- **DONE** — verified and accepted completion.

Work always starts in **PLAN**.

After completing any phase, the next phase is **PLAN** unless the User
explicitly specifies a different flow.
Any new work, change, extension, refinement, or follow-up
automatically resets the process to **PLAN**.

The model MUST NOT continue IMPLEMENTATION by inertia.

Phases are exclusive unless the User explicitly allows planning and
implementation together in special cases.

No transition between phases
(PLAN → IMPLEMENTATION, IMPLEMENTATION → PLAN, IMPLEMENTATION → DONE)
is allowed without **explicit User instruction or approval**.

Within a phase, the model may act freely.
At phase boundaries, the model MUST stop and ask.

## Task-first planning

PLAN work is performed **inside a task file**. The User may explicitly
choose to perform selected planning work outside task files.

If no suitable task file exists for the current request,
the model should propose creating a new task file
before performing research or design.

Design and research MUST NOT be developed directly in chat
unless the User explicitly allows planning without a task file.

All current design decisions for the scoped work MUST be written
in the task file Design section before IMPLEMENTATION starts.

Chat is a coordination channel, not a design artifact.

## Workflow

1. **Task files as source of truth**  
   All tasks, design, and execution status live as individual Markdown
   files under the project task directory, organized by status folders.
   Task file names must not use ticket IDs or task
   identifiers as filename prefixes, and must avoid prefixes and
   abbreviations (use readable, descriptive words).
   Task-first workflow from the section above applies unless the User
   explicitly chooses otherwise.

2. **Research first**  
   Start with research unless the user explicitly waives it. Record
   findings in the task **Research**. Record observations, constraints,
   and verified facts only; do not include planned actions or steps.
   Plans and changes belong in **Design**. Prefer PlantUML diagrams and
   place notes inside diagrams; use text when a diagram is not
   sufficient.

3. **Iterative discovery**  
   Research broadly across connected subtasks and iterate between
   research and design as needed; design decisions are connected, so
   continuous research during design is encouraged to capture context.

4. **Task file and approval boundary**  
   You may edit task files without prior approval. If task files were
   edited and there is no implementation directive, request user review
   before making code, test, or configuration changes. Explicit
   directives such as "implement", "implement it now", "go ahead", or
   "proceed" count as implementation approval. After approval (explicit
   or implicit), proceed without extra approval unless the user asks for
   another review gate. If implementation scope drifts beyond Design
   (e.g. new type, flow, dependency, or behavior-affecting method change),
   stop, update Design, request approval, then continue.

5. **Implementation completeness**  
   Implementation is complete only when both the design and the test
   specification are implemented, unless the user explicitly waives
   tests.

6. **Design and approval**  
   Draft design during research, then request approval. Do not modify
   code, tests, or configuration until design is approved. Any new
   class, responsibility move, or behavior-affecting method change
   requires a Design update and approval before code.
   Design sections must use PlantUML diagrams (class/component/sequence).
   Do not use PlantUML notes. If mixing class and non-class elements
   (for example `database`), add `allowmixing`.
   Formatting: keep the diagram in its own paragraph under Design
   (blank line before code fence). Put explanatory text in a separate
   paragraph under the diagram. For class diagrams, use one outer package with nested
   inner packages and add `set separator none`.
   Include meaningful dependency labels; use at most one connector per
   class pair.

7. **Status updates**  
   Move task files between status folders within the project task
   directory to reflect current work focus (e.g., done back to
   in-progress). Keep any existing numeric prefix to preserve
   traceability; new tasks must not use numeric prefixes until they
   move to done. Avoid moving unrelated tasks; move them only when
   actively worked on.

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
   After signature changes, run relevant module tests before reporting.

9. **Move workflow for diffs**  
   When moving tracked task files, use `git mv` and stage the move
   immediately before editing. This keeps rename tracking intact in
   diff tools that are not rename-aware (e.g., VS Code). Do not unstage
   the rename until you are ready to review and commit. For new
   untracked task files, move in filesystem (not `git mv`), then run
   `git add -A`.

10. **Done task cleanup**  
    Keep done tasks in the task directory under the done status
    folder with a three-digit prefix based on order moved into done.
    Delete them from the working tree after a release tag is created.

## Context Preservation

- **Task Sections Are Source of Truth**  
  Re-read the relevant task sections (Scope, Research, Design, Test
  Spec) before implementation or whenever requirements are unclear.
  Keep only the relevant task content in active context; avoid
  carrying unrelated content. These sections are the basis for coding
  and ensure consistency.

## Formatting

- Wrap prose at approximately 72–80 characters; avoid horizontal
  scrolling.
- Preserve semantic line breaks and consistent list indentation.
- Fenced code blocks must be unindented (except internal structure) and
  must start/end with backticks.
- Keep standalone paragraphs unindented. Continuation lines in list
  items may be indented to align with list formatting.
- Ensure Markdown renders correctly on GitHub and GitLab.

The intent is that all documents remain readable in plain text editors
(vim, less, nano) as well as in rendered views.

## Task States

Tasks must use one of these status folders within the project task
directory:

- **backlog**  
  New, planned, or deferred work. Research and design belong here until
  the design is approved; include ideas or deferred tasks.
  When creating a new task, first check whether `in-progress` contains
  any tasks. If `in-progress` is empty, create the new task directly in
  `in-progress` instead of `backlog`.

- **in-progress**  
  Active work in research, design, implementation, or verification.
  Subtask status must use `backlog`, `in-progress`, or `done`.
  LLMs must not set **done** unless the user explicitly requests it.
  Tasks may stay here while waiting for user confirmation before
  moving to done.

- **done**  
  The user has verified completion; move the task here with the
  required prefix before releasing.

## Scope and Safeguards

- **Clarity**  
  Designs may describe file scope broadly when it stays unambiguous.
- **Refactor tracking**  
  When refactoring, document it by updating the design section of the
  existing task or creating a new task.

## Task Structure

Each task uses this exact order and layout:

- Title line: `# Task: <title>`.
- Include one of the following identifiers (mutually exclusive):
  - `- **Ticket:**` Ticket ID (e.g., `BSK-1234`), preferred.
  - `- **Task Identifier:**` if no Ticket exists; `YYYY-MM-DD-<slug>`
    where `<slug>` is 1–2 keywords from the filename
    (e.g., `implement-consent-flow.md` → `...-consent`).
  - **Commit Rule**: the value present becomes the **Primary Identifier**
    for commit messages.
- Main task sections are list items with bold labels in this order:
  - `- **Scope:**`
  - `- **Motivation:**`
  - `- **Developer Briefing:**`
  - `- **Research:**`
  - `- **Design:**`
  - `- **Test specification:**`

Subtasks (if any):

- Appear only at the end as `## Subtask: <title>` sections; append new
  subtasks unless the user explicitly requests a different ordering.
- Each subtask:
  - starts with `- **Status:** <status>`,
  - uses the same list item labels and ordering as the main task,
  - represents a functional increment unless explicitly marked
    otherwise.

Subtasks may use only the statuses `backlog`, `in-progress`, or `done`.

**DONE is a phase transition.**
Moving a task or subtask to **done** is not a status update,
but a transition to the DONE phase.
As with all phase transitions, this requires
**explicit User approval** (see Spec Loop Phases and Transitions).

LLMs must not set **done** on their own.

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
- Implementation subtasks without testing are not allowed.
- Avoid splitting implementation and testing across separate subtasks
  for the same functional increment.
- Separate test-focused tasks are allowed when adding or extending
  coverage as a standalone scope.
- Automated tests should be preferred.
- In each subtask, include a Test specification with
  explicit Automated tests and Manual tests sublists.
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
