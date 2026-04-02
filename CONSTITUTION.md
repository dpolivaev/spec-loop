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
  in context. Use it to drive decisions.
- Re-read the full Constitution only if the active digest is missing
  from context or the User says the Constitution changed.

### Core Invariants

- Task-first: research and design live in task files.
- Follow task formatting rules.

### Decision Tables (Operational Shortcuts)

Use these tables as the primary quick path. When a row applies, follow
it. For phase transitions, **Spec Loop Phases and Transitions** is
authoritative. Task/subtask lifecycle rules are defined later in
**Task States**.

| Situation | Required action | Resulting phase |
| --- | --- | --- |
| Request changes executable behavior (code/test/config/deps/runtime assets) | Enter PLAN: update Research/Scenario/Design as needed and ask for explicit approval to start implementation | PLAN |
| Refactoring that changes code, tests, or configuration | Enter PLAN: update Design for the refactor and ask for explicit implementation approval | PLAN |
| User explicitly approves implementation (`implement`, `go ahead`, `proceed`, equivalent explicit instruction) | Start implementation according to approved Design | IMPLEMENTATION |
| Request is research/analysis/docs only | Edit non-executable artifacts only | PLAN |
| Task file changed but no implementation directive exists | Stop and ask for review/approval before code/test/config edits | PLAN |
| Scope drifts beyond approved Design (new flow/type/dependency/behavior) | Stop, update Research/Scenario/Design, request approval | PLAN |
| Implementation completed and local verification passed | Move `in-progress` -> `review` for task/subtask | REVIEW |
| User explicitly confirms completion | Move item to `done` | DONE |

| Scenario usage decision | Rule |
| --- | --- |
| Behavior or domain terms are introduced/changed | Scenario is required |
| Work only implements existing shared domain terms | Scenario may be omitted |
| Purely technical change without behavior/term change | Scenario may be omitted |
| Scenario omitted | Do not introduce new domain terms in Design; add Scenario first if terms change |

## Spec Loop Phases and Transitions

Phases:

- **PLAN** - research, design/spec changes, test specification.
- **IMPLEMENTATION** - code and test code, strictly following approved
  design.
- **DONE** - verified and accepted completion.

In PLAN, edits are allowed only for non-executable artifacts used for
research, design, planning, or governance, including task files, ADRs,
documentation, diagrams, and instruction files (for example,
AGENTS.md and CONSTITUTION.md). Command execution is allowed for
research/verification, but it must not change repository contents
outside those non-executable artifacts. If it would, treat it as
IMPLEMENTATION and request explicit User approval first.

Any change that affects executable behavior, tests, build/configuration,
dependencies, packaging, or runtime assets is IMPLEMENTATION and
requires explicit User instruction to enter IMPLEMENTATION.

Work starts in **PLAN** and returns to **PLAN** after each completed
work item unless the User explicitly specifies another flow.

Do not continue IMPLEMENTATION by inertia across work items; each new
item requires a fresh PLAN -> IMPLEMENTATION approval.

Phases are exclusive unless the User explicitly allows planning and
implementation together.

The following transitions require **explicit User instruction or
approval**: PLAN -> IMPLEMENTATION, IMPLEMENTATION -> DONE.

IMPLEMENTATION -> PLAN may be initiated by the LLM when required by
this Constitution (for example, scope drift, unclear classification,
rule conflict, or missing approved Design). In that case, the LLM must
immediately state the reason and what must be checked before
IMPLEMENTATION resumes (updated Design, Scenario/term alignment when
applicable, clear classification, and explicit User approval).

This phase model governs task-scoped implementation work; ADR-only,
research-only, and analysis-only requests stay in PLAN unless the User
says otherwise.

## Task-first planning

Task-first workflow is mandatory for work that changes code, tests, or
configuration.

For ADR-only/research-only/analysis-only requests with no code, test,
or configuration changes, a task file is optional unless the User asks
for task-based tracking.

When task-first applies and no suitable task file exists, propose
creating one before research/design.

Before IMPLEMENTATION, approved Design decisions and required
Constraints must be present in the task file.

Chat is a coordination channel, not a design artifact.

## Workflow

Rules in this section complement, and do not override,
**Spec Loop Phases and Transitions**.

### 1. Task files

All tasks, design, and execution status live as individual Markdown
files under the project task directory, organized by status folders.
Task base names must not use ticket IDs, task identifiers, or
abbreviations; use readable, descriptive words. Numeric prefixes are
used only as folder-local ordering markers:

- `backlog` uses a readable three-digit backlog-order prefix.
- `done` uses the required three-digit completion-order prefix defined
  in this Constitution.

Backlog numbering and done numbering are independent sequences. The
same number may appear once in `backlog` and once in `done` without
conflict. Task-first workflow from the section above applies unless the
User explicitly chooses otherwise.

### 2. Research

Start with research unless waived. Record observations, constraints,
and verified facts only; plans belong in **Design**. Research
documents the current system: behavior, legacy architecture,
reverse-engineered flows, data structures, initialization details,
characterization findings, and verified constraints. Current or as-is
design belongs in **Research**.

### 3. Scenario

Scenario anchors behavior and terms before implementation. Use it when
behavior/terms are introduced or changed; otherwise it can be skipped
(see Decision Tables). Keep Scenario concise and implementation-free.

**Naming principle (canonical terms):**
Scenario is the source of domain and behavior language. When behavior
or terms are introduced or changed, create or update Scenario first,
then use those terms consistently in Design, tests, code symbols, and
commit text. Do not keep parallel synonyms for the same domain concept.
If existing code uses different names, align naming incrementally in
the current scope and document any intentional temporary mismatch in
the task file. Research may mention legacy terms and synonyms. Design
must use only canonical Scenario terms, except for explicit
legacy-to-target mapping tables.

**Project glossary:**
`glossary.adoc` is optional until a project opts in by creating it or
by instructions that require it. Once present, it defines the
project's shared domain language and must be used during planning as
the reference for project terms and definitions. Creating the first
`glossary.adoc` from already approved information is documentation-only
work and does not require a task file unless the User asks for
task-based tracking. Do not add helper names, implementation details,
framework terms, or other terms that are not needed to understand
project rules, behavior, or true subsystem boundaries.

When `glossary.adoc` exists, the LLM must:

- check whether approved work changes, clarifies, or implements shared
  domain terms,
- plan the related `glossary.adoc` updates during PLAN,
- perform those updates during IMPLEMENTATION with relevant
  implementation traceability links.

If required glossary updates were not planned, stop, return to PLAN,
update the task, request approval, then continue. Before creating or
updating `glossary.adoc`, read `glossary-skill.md` next to this
Constitution if available.

### 4. Design

Document the target system in **Design**: architecture, data
structures, data flow, interactions, and implementation boundaries.
Draft it from validated **Research** and required behavior from
**Scenario** when Scenario exists. Current implementation, legacy
structure, reverse-engineered flows, and as-is diagrams belong in
**Research** unless they are intentionally retained in the target
design.

Design must make intended implementation structure reviewable. Keep
verification structure in **Test specification**, not in Design. Do not
model test suites, test doubles, harnesses, or other test-only
elements in Design unless the task itself changes test infrastructure.
Design diagrams must not include test classes, test fixtures, or
test-only helpers. When changing tools, APIs, or serialized payloads,
**Design** must show the full target request/response structures and
enums; examples may supplement but must not replace the class
specification.

Show important interactions and implementation boundaries clearly and
concisely. When Scenario exists, Design must use Scenario language for
design-owned names.

Design must show where each design element will live: in an existing
implementation unit or in a concretely named new one with a clear
boundary. If that is not yet decided, the design is not ready for
implementation. Use domain language for design-owned names.

Formatting: if used, keep the diagram in its own paragraph under
Design. Put explanatory text below it only for context, rationale,
constraints, or clarifications it cannot express.

### 5. Test specification

Document verification structure and concrete test coverage.

### 6. Iterative discovery

Iterate across **Research**, **Scenario** (if used), **Design**, and
**Test specification** until decisions and verification are coherent.
Record intermediate design alternatives when they help reasoning,
review, or discussion. No implementation starts during this loop.

### 7. Implementation

Implementation is complete only when design and test specification are
implemented, unless the user explicitly waives tests. Any glossary work
required by **Project glossary** must also be complete.

### 8. Status updates

Move task files between status folders within the project task
directory to reflect current focus (for example, `in-progress` to
`review`, or `done` back to `in-progress` or `backlog`). Remove the
backlog-order prefix only when a task is moved from `backlog`. When
moving a task into `done`, assign the next done-folder completion
prefix independently of any former backlog prefix.

When reopening a task from done, keep the existing three-digit prefix
to preserve traceability. Avoid moving unrelated tasks; move them only
when actively worked on.

### 9. Move workflow for diffs

When moving tracked task files, use `git mv` and stage the move
immediately before editing. This preserves rename tracking in diff
tools that are not rename-aware (for example, VS Code). Do not unstage
the rename until ready to review and commit. For new untracked task
files, move in filesystem (not `git mv`), then run `git add -A`.

### 10. Status validation before commits

Update subtask status whenever task-file lifecycle state changes.
Before each commit, check relevant task files and propose any status or
folder changes needed for consistency. Apply those status changes only
after explicit user confirmation, except that the LLM should apply
`in-progress` -> `review` transitions directly when implementation and
local verification are complete.

Generated and local-only artifacts must not be committed. If such files
are accidentally tracked, untrack them and add or update the
appropriate ignore rule before continuing, unless they are
intentionally versioned.

Before writing the commit message, review the full change set being
committed and its purpose. The commit message must accurately describe
the purpose, unless the User explicitly requests otherwise. Do not
write a commit message that is misleading about what changed. Then
proceed with the commit.

For task-related commits, start the message with the **Primary
Identifier**:

- Ticket ID if present (for example `TICKET-123: ...`).
- Otherwise full Task Identifier (for example
  `2025-01-15-research: ...`).

For non-task updates, commit messages may omit identifiers when
`AGENTS.md` policy allows it. If the user explicitly asks to skip
identifiers for a commit, honor that request.

After code or configuration changes, run relevant module tests before
reporting.

### 11. Done task cleanup

Keep done tasks in the task directory under the done status folder with
a three-digit prefix based on order moved into done. This done
numbering is independent from any three-digit backlog-order prefix.
Delete them from the working tree after a release tag is created.

## Context Preservation

- Re-read relevant task sections before implementation or whenever
  requirements are unclear.
- Treat the active task or subtask as the working source of truth for
  the current work item.
- The active task or subtask does not need to be kept mutually
  consistent with already finished tasks. Older task files are
  historical working records and need not be retroactively updated when
  later tasks supersede them.
- Keep only relevant task content in active context and avoid carrying
  unrelated content.

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

Tasks and subtasks share one lifecycle with the same status values:
`backlog`, `in-progress`, `review`, `done`.

Phases govern what work may be performed now. Lifecycle states describe
where the tracked item sits in the workflow.

Representation:
- Task lifecycle state is represented by the task file directory.
- Subtask lifecycle state is represented by `- **Status:** <status>`.

Lifecycle definitions:
- **backlog**  
  Planned or deferred work. New tasks default to `backlog`.
- **in-progress**  
  Active work in research, design, implementation, or verification.
- **review**  
  Implementation is complete and locally verified, and the item awaits
  user review or acceptance.
- **done**  
  User-verified completion.

Lifecycle and transition rules:
- Tasks and subtasks use the same transition guards from
  **Spec Loop Phases and Transitions**.
- LLM should move `in-progress` -> `review` when implementation and
  local verification are complete.
- For a task with subtasks, move the task itself to `review` only when
  every subtask status is `review`.
- Moving to `done` requires an explicit User request.
- Exception for initial placement: if `in-progress` contains no tasks
  and only one new task is being created, place it in `in-progress`.

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
  - `- **Scenario:**` (conditional; include only when behavior is
    introduced/clarified or terms are introduced/refined)
  - `- **Constraints:**` (optional; include when the task has
    important limits that the target `Design` and implementation must
    obey)
  - `- **Briefing:**`
  - `- **Research:**`
  - `- **Design:**`
  - `- **Test specification:**`
  In tasks with subtasks, the main-task `Research`, `Design`, and
  `Test specification` sections may be omitted.
  If `Scenario` or `Constraints` is omitted, keep the remaining
  sections in order.
- Subtasks (if the task contains any) must be placed
  after all global task sections.

### Every Subtask
- must start with level 2 heading having pattern
  `## Subtask: <title>` 
  followed by the status line `- **Status:** <status>`,
- must use the same list-item labels and ordering rules as the main task
  (including conditional `Scenario` and optional `Constraints`),
- must represent a functional increment and, for implementation tasks,
  include executable work,
- must satisfy the testing requirements defined in **Testing Policy**.
- Do not create planning-only subtasks unless the User explicitly asks.

Subtask `Status` values and transitions use the same lifecycle rules as
defined in **Task States**.

### Task Context Hygiene

- Avoid redundant duplication across the main task and subtasks. When a
  subtask reuses earlier context, reference the existing section
  briefly and state only the local adaptation, risk, or decision.
- Future subtasks may keep `Research`, `Design`, and `Test
  specification` lightweight until they become current. Placeholders
  such as `To be done` or `See main task` are allowed.
- The current implementation subtask must contain the detail needed for
  review and execution.
- Once a design decision is made, remove obsolete or superseded
  alternatives from the task.
- Repeating diagrams, type structures, payloads, or prose is allowed
  only when it adds local reasoning value or shows a genuinely
  different behavior, ownership boundary, or contract.

### Constraints (optional)

- Use when the task has important limits that the target `Design` and
  implementation must obey.
- Typical content: semantic invariants, non-goals, compatibility
  limits, performance limits, identity rules, and forbidden
  simplifications.
- If `Design` conflicts with `Constraints`, `Constraints` wins.

### Briefing

- Short orientation for someone unfamiliar with the codebase,
  stack, subsystem boundaries, or entry points.
- Use this section for practical context such as relevant modules,
  important classes, framework context, repository conventions, and
  risk areas.

### PlantUML Diagrams

- This section defines when PlantUML diagrams are required in task
  files.
- Use **Research** for current state and **Design** for target state.
- **Research** must include a PlantUML diagram when current behavior,
  message flow, context selection, or component interaction is being
  analyzed.
- **Design** must include a PlantUML diagram when the change affects
  structure or component interaction.
- A diagram may be omitted only when the task is confined to a single
  method or a trivially local change with no meaningful flow or
  interaction to visualize.
- Do not use PlantUML notes. Put needed explanation below the
  diagram.
- Use `allowmixing` only when class elements are combined with
  non-class elements.
- Declare component and sequence diagrams with
  explicit PlantUML keywords.
- For class diagrams, use one outer
  `package` with nested inner packages and `set separator none`. Show
  only the class elements needed for the change or structural
  interaction, with meaningful dependency labels and at most one
  connector per class pair.

## Definition of Done for LLM

Before setting a task or subtask to **review**:

1. **Research**: legacy state is documented as needed.
2. **Scenario**: when applicable, expected behavior is documented as a
   clear story in natural language.
3. **Design**: architecture/data flow/class interactions are defined.
4. **Scope**: Scope, Design, Constraints, and Test specification are
   fully implemented as applicable.
5. **Verification**: tests required by **Testing Policy** pass locally.
6. **Cleanliness**: no TODOs, placeholders, temp comments, or unused
   imports remain.
7. **Documentation**: any implementation deviation from approved
   design is documented in the task file before review.
8. **Glossary**: any glossary work required by **Project glossary** is
   completed.

## Testing Policy

- Keep a Test specification in each task without subtasks and in each
  subtask.
- For no-code tasks or subtasks, set
  `Automated tests: N/A` and `Manual tests: N/A`.
- Implementation subtasks must include testing and must not split
  implementation and testing across separate subtasks for the same
  functional increment.
- Separate test-focused tasks are allowed when adding or extending
  coverage as a standalone scope.
- Automated tests should be preferred.
- In each implementation task without subtasks and each implementation
  subtask, include explicit Automated tests and Manual tests sublists.
- Implement, run, and fix all required tests before moving a task or
  subtask to **review**, unless the user explicitly waives tests.

## Architecture Decision Records

- Record architecture decisions in `architecture-decisions/` as one
  file per decision with meaningful names.
- ADRs may be created directly, without a task file, unless the User
  explicitly requests task-linked ADR work.
- ADR file names must avoid prefixes (including numbers) and abbreviations
  (use readable, descriptive words).
- Use a short template with Title, Date, Status, Context, Decision, and
  Consequences.
- Use ADRs for decisions affecting public behavior, dependencies, or
  long-term design.
