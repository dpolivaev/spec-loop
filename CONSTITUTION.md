# Constitution

When uncertain or before changing behavior, propose next steps, ask for approval, then act. Task file approval is the default approval gate for implementation unless the user requests an additional review gate or the scope changes materially.

## Workflow
1.  **Task files as source of truth**: All tasks, design, and execution status live as individual Markdown files under the project designated task directory, organized by status folders.
2.  **Research first**: Start with research unless the user explicitly waives it. Record findings in the task **Research**. Prefer PlantUML diagrams and place notes inside diagrams; use text when a diagram is not sufficient.
3.  **Iterative Discovery**: You are free to research broadly across connected subtasks and iterate between research and design as needed. Design decisions are often connected, so continuous research during the design phase is encouraged to capture full context.
4.  **Task file and approval boundary**: You may edit task files without prior approval. After any task file edits, request user review and approval before making any code, test, or configuration changes. Task file approval authorizes implementation. Approval can be explicit or implicit; user instructions such as "implement it now", "go ahead", or "proceed" after task file updates count as approval to implement. After the user approves the task file (research, design, and test specification), proceed to implement the corresponding code, test, and configuration changes without seeking an additional approval, unless the user explicitly requests another review gate. No exceptions.
5.  **Implementation completeness**: Implementation is complete only when both the design and the test specification are implemented, unless the user explicitly waives tests.
6.  **Design and approval**: Draft the design while research is in progress, then request approval. Do not modify code, tests, or configuration until the design is approved. Prefer PlantUML diagrams and place notes inside diagrams; use text when a diagram is not sufficient.
7.  **Status updates**: Move task files between status folders within the project task directory to reflect the current work focus (for example, from finished back to in-progress). Keep any existing numeric prefix to preserve traceability; new tasks must not use numeric prefixes until they move to finished. Avoid moving unrelated tasks; move them only when they are actively being worked on.
8.  **Status validation before commits**: Before each commit, check the relevant task files and propose any status or folder changes needed for consistency. Apply those status changes only after explicit user confirmation, then proceed with the commit.
9.  **Move workflow for diffs**: When moving tracked task files, use `git mv` and stage the move immediately before editing. This keeps rename tracking intact in diff tools that are not rename-aware (for example, Visual Studio Code). Do not unstage the rename until you are ready to review and commit. For new, untracked task files, use a regular move and then `git add`.
10. **Finished task cleanup**: Keep finished tasks in the project task directory under the finished status folder with a three digit prefix in the file name based on the order they are moved into finished. Delete them from the working tree after a release tag is created.

## Workflow Checklist
- Before any task file edit, update subtask status if it changes (Planning or Implementing) before editing.
- Before reporting to the user, update subtask status if it changes (Planning -> Plan Review, Implementing -> Implementation Review).
- Before commit: verify task status and folder changes, stage renames; confirm with user unless they explicitly instructed to commit.
- Before commit: confirm the commit message starts with the **Primary Identifier** for task-related changes:
    - If a Ticket ID exists, use it alone (e.g., `TICKET-123: ...`).
    - If no Ticket ID exists, use the full Task Identifier (e.g., `2025-01-15-research: ...`).
- For updates not related to any task, commit messages may omit task identifiers when the repository policy (defined in `AGENTS.md`) allows it.
- If the user explicitly requests skipping task or ticket identifiers for a specific commit, honor the request and use a message without identifiers for that commit.
- After signature changes: run the relevant module tests before reporting completion.

## Context Preservation
- **Task Sections Are Source of Truth**: Re-read the relevant task sections (Scope, Research, Design, Test Spec) before implementation or whenever requirements are unclear. Keep only the relevant task content in active context; avoid carrying unrelated content. These sections are the basis for coding and ensure consistency.

## Task States
Tasks must use one of these status folders within the project task directory:
*   **backlog**: New, planned, or deferred work. Research and design belong here until the design is approved; include ideas or deferred tasks.
*   **in-progress**: Active work in either planning or implementation. Subtask status must indicate the current phase (Planning/Plan Review/Implementing/Implementation Review) to disambiguate intent; the folder reflects that the task is actively being worked on. Tasks may stay here while waiting for user confirmation before moving to finished.
*   **finished**: The user has verified completion; move the task here with the required prefix before releasing.

## Scope and Safeguards
*   **Clarity**: Designs may describe file scope broadly when it stays unambiguous.
*   **No unapproved logic changes**: Do not change business logic unless explicitly instructed.
*   **Task to commit linking**: Every task-related commit message must include the Task Identifier (or Ticket ID when used). Updates not related to any task may omit task or ticket identifiers when allowed by repository policy in `AGENTS.md`.
*   **Refactor tracking**: When refactoring, document it by updating the design section of the existing task or creating a new task.

## Task Structure
The purpose of this rigorous structure is to capture all relevant context (current state, future design, verification plan) within the task file itself. This ensures that the coding agent has immediate access to all necessary information during the implementation phase without needing to re-derive context.

Each task uses the following exact order and layout:
*   Title line: `# Task: <title>`.
*   Include one of the following identifiers (mutually exclusive):
    *   `- **Ticket:**` The Ticket ID (e.g., `BSK-1234`). This is preferred.
    *   `- **Task Identifier:**` (Use only if no Ticket exists) A short, stable identifier (e.g., `2025-01-15-consent`).
        *   Construct as `YYYY-MM-DD-<slug>`, where `<slug>` is 1-2 keywords derived from the task filename (e.g., file `implement-consent-flow.md` -> ID `...-consent`).
    *   **Commit Rule**: The value present here becomes the **Primary Identifier** for commit messages.
*   Main task sections are list items with bold labels in this exact order:
    *   `- **Scope:**` what the task will deliver.
    *   `- **Motivation:**` explain why the task exists (backlog tasks or in-progress design stages only).
    *   `- **Developer Briefing:**` a concise technical overview enabling a developer unfamiliar with the codebase to understand the task's context, legacy constraints, and architectural approach. This section summarizes the key findings from Research and Design and must not introduce new findings; you must update it whenever the Research or Design sections are modified.
    *   `- **Research:**` facts and observations describing the *current state* of the code. Use PlantUML diagrams when they add clarity (class, sequence, or similar); do not use note-only diagrams. Otherwise use text. When using PlantUML, wrap it in fenced code blocks with the `plantuml` info string.
    *   `- **Design:**` structure, data flow, constraints, and decisions describing the *future state* to be achieved. Use PlantUML diagrams when they add clarity (class, sequence, or similar); do not use note-only diagrams. Otherwise use text. When using PlantUML, wrap it in fenced code blocks with the `plantuml` info string.
    *   `- **Test specification:**` brief description of planned tests before implementation.
*   Subtasks, if any, appear only at the end as their own `## Subtask: <title>` sections, with no `## Subtasks` section.
    *   Each subtask starts with `- **Status:** <status>`.
    *   Each subtask uses the same list item labels and ordering as the main task: Scope, Motivation, Developer Briefing, Research, Design, Test specification.
    *   **Functional Increments**: Subtasks must generally be vertical slices of functionality (increments that include their own research, design, and implementation).
    *   **Research/Design Subtasks**: Pure "Research" or "Design" subtasks are permitted **only** if they produce distinct artifacts outside the task file itself (e.g., creating a separate documentation file in `docs/`, a proof-of-concept prototype, or an Architecture Decision Record). Otherwise, research and design findings must be recorded in the `Research` and `Design` sections of the relevant functional subtask or the main task.
    *   Explicitly include **Developer Briefing**, **Design** (preferably with PlantUML), and **Test specification** sections in every subtask.

Subtasks should only use the statuses `Planning`, `Plan Review`, `Implementing`, `Implementation Review`, or `Finished`. The parent folder (Backlog/In-Progress/Finished) provides the broader lifecycle context.

**Subtask Status Definitions:**
*   **Planning**: The agent iterates on Research, Design, Test Specification, and Developer Briefing.
*   **Plan Review**: The agent sets this status when the design is complete and waiting for User approval. **Transition to Implementing requires User approval.**
*   **Implementing**: The agent iterates on implementation and verification (coding, testing, debugging, fixing). There is no separate verification step; verification is part of implementation.
*   **Implementation Review**: The agent sets this status when implementation **and** verification are complete. **Transition to Finished requires User approval.**
*   **Finished**: The task is complete and approved by the User.

**Definition of Done by Phase:**

*   **Planning Phase Definition of Done** (Required before moving to *Plan Review*):
    1.  **Research**: Legacy state (if applicable) and current constraints are fully analyzed and documented.
    2.  **Design**: Future architecture, data flow, and key class interactions are defined (PlantUML preferred).
    3.  **Test Spec**: Comprehensive list of test scenarios (positive, negative, edge cases) is written.
    4.  **Briefing**: The "Developer Briefing" is updated to be a standalone summary of the task context.
    5.  **No Code**: No production code or tests have been generated yet.

*   **Implementing Phase Definition of Done** (Required before moving to *Implementation Review*):
    1.  **Scope**: All items in the **Design** and **Test specification** sections are fully implemented.
    2.  **Verification**: All new tests (Unit, Integration) and relevant existing tests pass locally.
    3.  **Cleanliness**: Code is free of "TODOs", placeholders, temporary comments, and unused imports.
    4.  **Documentation**: Any deviations from the original design are documented in the task file.

**Testing Policy:**
*   Tests are always part of the same subtask as the implementation. Do not create separate subtasks solely for writing tests.
*   **Plan Review** requires a complete **Test specification** for each subtask.
*   **Implementation Review** requires tests to be implemented and passing as described in the subtask Test specification.

**Status** is implied by the folder for main tasks; subtasks still include explicit status lines.

## Architecture Decision Records
*   Record architecture decisions in `architecture-decisions/` as one file per decision with meaningful names.
*   Use a short template with Title, Date, Status, Context, Decision, and Consequences.
*   Use architecture decision records for decisions that affect public behavior, dependencies, or long term design.
