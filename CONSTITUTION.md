# Constitution

- **Principle over ceremony**  
  This Constitution is intent-first; compliance is judged by
  outcome and behavior, not checklist formality.
- When uncertain or before changing behavior, propose next steps, ask for
approval, then act. Task file approval is the default approval gate for
implementation. The user can explicitly override this workflow in
their request, request an additional review gate, or if the scope
changes materially.

## Workflow

1. **Task files as source of truth**  
   All tasks, design, and execution status live as individual Markdown
   files under the project task directory, organized by status folders.
   Ideas for new tasks can be captured in `new-task-ideas.md` in the
   task directory. Task file names must not use ticket IDs or task
   identifiers as filename prefixes, and task file names must avoid
   prefixes and abbreviations (use readable, descriptive words).

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
   before making code, test, or configuration changes. An explicit
   directive such as "implement", "implement it now", "go ahead", or
   "proceed" counts as approval to implement and must not trigger
   another approval request. After approval (explicit or implicit),
   proceed to implement without additional approval unless the user
   asks for another review gate. No exceptions.
   If scope drifts (new type, flow, dependency, or behavior-affecting
   method change not in Design), stop, update Design, request
   approval, then continue.

5. **Implementation completeness**  
   Implementation is complete only when both the design and the test
   specification are implemented, unless the user explicitly waives
   tests.

6. **Design and approval**  
   Draft the design while research is in progress, then request
   approval. Do not modify code, tests, or configuration until the
   design is approved. Any new class, responsibility move, or
   behavior-affecting method change requires a Design update and
   approval before code. If design is outdated, reject
   implementation and request design update approval first. Design
   sections must be expressed as PlantUML
   diagrams that model structure or flow (class, component, sequence).
   Do not use PlantUML notes. Formatting: the diagram must be in its
   own paragraph under the Design label (blank line before the code
   fence). If explanatory text is needed, put it in a separate
   paragraph under the diagram, not within the list item. Use brief
   text only when a diagram cannot convey the design. For class
   diagrams, use one outer package and nest inner packages inside it.
   Add `set separator none` at the top of the PlantUML block to prevent
   automatic namespace nesting. Include all meaningful dependencies
   with labels; use at most one connector per class pair.

7. **Status updates**  
   Move task files between status folders within the project task
   directory to reflect current work focus (e.g., done back to
   in-progress). Keep any existing numeric prefix to preserve
   traceability; new tasks must not use numeric prefixes until they
   move to done. Avoid moving unrelated tasks; move them only when
   actively worked on.

8. **Status validation before commits**  
   Before each commit, check relevant task files and propose any status
   or folder changes needed for consistency. Apply those status changes
   only after explicit user confirmation, then proceed with the commit.

9. **Move workflow for diffs**  
   When moving tracked task files, use `git mv` and stage the move
   immediately before editing. This keeps rename tracking intact in
   diff tools that are not rename-aware (e.g., VS Code). Do not unstage
   the rename until you are ready to review and commit. For new,
   untracked task files, use a regular move and then `git add`.

10. **Done task cleanup**  
    Keep done tasks in the task directory under the done status
    folder with a three-digit prefix based on order moved into done.
    Delete them from the working tree after a release tag is created.

## Workflow Checklist

- Before any task file edit, update subtask status if it changes to
  match the current lifecycle state (see Subtask Status Definitions).
- After editing a task file, update subtask status to match the new
  lifecycle state; this update is mandatory before reporting to the
  user.
- Before commit: verify task status and folder changes, stage renames;
  confirm with user unless they explicitly instructed to commit.
- Before commit: confirm the commit message starts with the **Primary
  Identifier** for task-related changes:
  - If a Ticket ID exists, use it alone (e.g., `TICKET-123: ...`).
  - If no Ticket ID exists, use the full Task Identifier
    (e.g., `2025-01-15-research: ...`).
- For updates not related to any task, commit messages may omit task
  identifiers when the repository policy (defined in `AGENTS.md`)
  allows it.
- If the user explicitly requests skipping task or ticket identifiers
  for a specific commit, honor the request and use a message without
  identifiers for that commit.
- After signature changes: run the relevant module tests before
  reporting completion.

## Context Preservation

- **Task Sections Are Source of Truth**  
  Re-read the relevant task sections (Scope, Research, Design, Test
  Spec) before implementation or whenever requirements are unclear.
  Keep only the relevant task content in active context; avoid
  carrying unrelated content. These sections are the basis for coding
  and ensure consistency.

## Formatting

- Wrap prose at approximately 72–80 characters per line.
- Do not rely on horizontal scrolling to read paragraphs.
- Preserve semantic line breaks (new sentences or clauses may start on
  new lines when it improves readability).
- Lists and sublists must use consistent indentation.
- Fenced code blocks must have no indentation except for internal
  structure and must start and end with backticks.
- Standalone paragraphs must have no indentation. Continuation lines
  within list items are part of the list item and may be indented to
  align with list formatting.
- Markdown rendering must remain correct on GitHub and GitLab.

The intent is that all documents remain readable in plain text editors
(vim, less, nano) as well as in rendered views.

## Task States

Tasks must use one of these status folders within the project task
directory:

- **backlog**  
  New, planned, or deferred work. Research and design belong here until
  the design is approved; include ideas or deferred tasks.

- **in-progress**  
  Active work in research, design, implementation, or verification.
  Subtask status must use the allowed statuses defined below.
  LLMs must not set **done** unless the user explicitly requests it.
  Tasks may stay here while waiting for user confirmation before
  moving to done.

- **done**  
  The user has verified completion; move the task here with the
  required prefix before releasing.

## Scope and Safeguards

- **Clarity**  
  Designs may describe file scope broadly when it stays unambiguous.
- **Task to commit linking**  
  Every commit message must include the Task Identifier.
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
LLMs must not set **done** unless the user explicitly requests it.

**Subtask Status Definitions:**

- **backlog**  
  The subtask is planned, deferred, or not actively being worked.
  Research, design, and test specification can be drafted here.

- **in-progress**  
  The subtask is actively being worked, including research, design,
  implementation, and verification. Transition from backlog to
  implementation still requires User approval under the approval
  boundary rules.

- **done**  
  The subtask is complete, verified, and approved by the User.

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

- Tests are part of the same subtask as implementation; do not create
  separate subtasks solely for tests.
- Automated tests should be preferred.
- Group tests under explicit Automated tests and Manual tests sublists.
- When implementing a task, you must implement all specified tests,
  run them, and fix any failures before reporting completion, unless
  the user explicitly waives tests.
- Before moving a subtask to **done**, the subtask must have a complete
  **Test specification** and those tests must be implemented and
  passing, unless explicitly waived by the user.

**Status**

Status is implied by the folder for main tasks; subtasks still include
explicit status lines.

## Architecture Decision Records

- Record architecture decisions in `architecture-decisions/` as one
  file per decision with meaningful names.
- ADR file names must avoid prefixes and abbreviations (use readable,
  descriptive words).
- Use a short template with Title, Date, Status, Context, Decision, and
  Consequences.
- Use ADRs for decisions affecting public behavior, dependencies, or
  long-term design.
