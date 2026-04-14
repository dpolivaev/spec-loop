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
  in context and use it to drive decisions.
- Re-read the full Constitution only if the digest is missing from
  context or the User says the Constitution changed.

### Core Invariants

- Task-based work must be plan-first.
- Follow task formatting rules.

### Decision Tables (Operational Shortcuts)

Use these tables as quick lookup only. **Task-based Phases and
Transitions** and **Task States** remain authoritative.

| Situation | Required action | Resulting phase |
| --- | --- | --- |
| Request executable changes (code/test/config/deps/runtime assets) | Enter PLAN: update Research/Scenario/Design as needed and ask for explicit approval to start implementation | PLAN |
| Refactoring that changes code, tests, or configuration | Enter PLAN: update Design and ask for explicit implementation approval | PLAN |
| User explicitly approves implementation (`implement`, `go ahead`, `proceed`, equivalent explicit instruction) | Start implementation according to approved Design | IMPLEMENTATION |
| Task file changed but no implementation directive exists | Stop and ask for review/approval before code/test/config edits | PLAN |
| Scope drifts beyond approved Design (new flow/type/dependency/behavior) | Stop, update Research/Scenario/Design, request approval | PLAN |

| Scenario usage | Rule |
| --- | --- |
| Behavior or domain terms are introduced or changed | Scenario is required |
| Work only implements existing shared domain terms | Scenario may be omitted |
| Change is purely technical and does not change behavior or domain terms | Scenario may be omitted |
| Scenario is omitted | Design must not introduce new domain terms; add Scenario first if terms change |

## When work is task-based

Work that changes code, tests, or configuration must be task-based.
For work that does not change code, tests, or configuration, a task
file is not required unless the User asks for task-based tracking.

When work is task-based and no suitable task file exists,
propose creating one before research/design.

Chat is a coordination channel, not a design artifact.

## Task-based Phases and Transitions

Phases:

- **PLAN** - research, design/spec changes, test specification.
- **IMPLEMENTATION** - code and required tests, strictly following
  approved Design and Test specification unless the User explicitly
  waives tests.
- **DONE** - verified and accepted completion.

In PLAN, edits are allowed only for non-executable artifacts used for
research, design, planning, or governance, including task files, ADRs,
documentation, diagrams, and instruction files. Command execution is
allowed for research/verification, but it must not change repository
contents outside those non-executable artifacts. If it would, treat it
as IMPLEMENTATION and request explicit User approval first.

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

Backlog tasks may keep Research and Design high-level or mark them
`To be done` until they become current.
Before IMPLEMENTATION, the active task must have any required
Research, any required Constraints, required Scenario work, an
implementation-ready Design, and a Test specification for the increment
being implemented, even when the user allows PLAN and IMPLEMENTATION together.

IMPLEMENTATION -> PLAN may be initiated by the LLM when required by
this Constitution (for example, scope drift, unclear classification,
rule conflict, or missing approved Design). In that case, state the
reason immediately and what must be checked before IMPLEMENTATION
resumes (updated Design, Scenario/term alignment when applicable,
clear classification, and explicit User approval).

This phase model governs task-scoped implementation work only.
Work that does not change code, tests, or configuration is outside
this phase model unless the User says otherwise.

## Task Artifacts and Administration

Rules in this section define task artifacts, section content, and task
administration. Phase entry, exit, and approval rules remain in
**Task-based Phases and Transitions**.

### 1. Task files

Task files live under the project task directory.

Top-level task folders are `backlog`, `in-progress`, `review`, and
`done`. These folder names are the task lifecycle states.

- Only `backlog` may contain additional subfolders for organization.
- Names of backlog subfolders are organizational only and need not
  match lifecycle states.
- Backlog numbering is optional.
- If used, backlog numbering is a readable three-digit prefix local to
  the containing backlog folder.
- `done` uses the required three-digit completion-order prefix and
  remains one global sequence within `done`.

Task base names must not use ticket IDs, task identifiers, or
abbreviations; use readable, descriptive words.

Backlog and done numbering are independent. The same number may appear
in multiple backlog folders and once in `done`.

### 2. Planning artifacts

#### Research

Start with research unless waived. Record observations, constraints,
and verified facts only; plans belong in **Design**. Research
documents the current system: behavior, current implementation,
legacy architecture, reverse-engineered flows, data structures,
characterization findings, verified constraints, and as-is diagrams.

#### Scenario

Scenario anchors behavior and domain terms before implementation. Use
it when behavior or domain terms are introduced or changed;
otherwise it may be omitted. Keep it concise and
implementation-free.

Scenario is the source of domain and behavior language. When behavior
or domain terms are introduced or changed, create or update Scenario
first, then use those terms consistently in Design, tests, code
symbols, and commit text. Do not keep parallel synonyms for the same
domain concept.
Research may mention legacy terms and synonyms. Design must use only
canonical Scenario terms, except for explicit legacy-to-target mapping
tables. If existing code uses different names, align incrementally in
scope and document any intentional temporary mismatch in the task
file.

#### Project glossary

`glossary.adoc` is optional until a project opts in by creating it or
by instructions that require it. Once present, it defines the
project's shared domain language and must be used during planning as
the reference for project terms and definitions. Creating the first
`glossary.adoc` from already approved information is
documentation-only work and does not require a task file unless the
User asks for task-based tracking. Do not add helper names,
implementation details, framework terms, or other terms not needed to
understand project rules, behavior, or real subsystem boundaries.

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

#### Design

Document the target system in **Design**: architecture, data
structures, data flow, interactions, and implementation boundaries.
Draft it from validated **Research** and required behavior from
**Scenario** when Scenario exists.

Design is the implementation contract. It must make intended
implementation structure reviewable and implementation-ready.

Use only final intended names for design-owned terms, implementation
units, configuration keys, tool/API names, request/response fields,
enum values, and similar identifiers. Do not use placeholders,
temporary names, candidate names, or example names. If a name, unit,
or boundary is not decided, the design is not ready for
implementation.

Keep test-only elements in **Test specification**, not in Design,
unless the task changes test infrastructure.

When changing tools, APIs, or serialized payloads, **Design** must
show the full target request/response structures and enums; examples
may supplement but must not replace the specification.

When Scenario exists, Design must use canonical Scenario terms for
design-owned names. If a design diagram is used, the relevant units
and names must appear in the diagram itself, not only in prose.

#### Test specification

Document verification structure and concrete test coverage.

#### Iterative discovery

Iterate across **Research**, **Scenario** (if used), **Design**, and
**Test specification** until decisions and verification are coherent.
Record intermediate alternatives only when they help reasoning,
review, or discussion. No implementation starts during this loop.

### 3. Task administration

#### Status moves

Move task files between status folders to reflect lifecycle state.

Moves between backlog subfolders are organizational only. When tasks
move between backlog subfolders, adjust backlog prefixes to fit the
target. Remove a backlog prefix only when moving a task out of
`backlog`. When moving a task into `done`, assign the next global
`done` prefix independently of any former backlog prefix.

When reopening a task from `done`, keep the task file in
`done` by default and append a new subtask for the new increment.
Do not modify already finished main sections or completed subtasks unless
the User explicitly requests those edits.

#### Tracked moves

When moving tracked task files, use `git mv` and stage the move
immediately before editing. This preserves rename tracking in diff
tools that are not rename-aware. Do not unstage the rename until ready
to review and commit. For new untracked task files, move in
filesystem (not `git mv`), then run `git add -A`.

#### Commit checks

Update subtask status whenever task-file lifecycle state changes.
Before each commit, check relevant task files and propose any needed
status or folder changes. Apply those changes only after explicit User
confirmation, except that the LLM should apply `in-progress` ->
`review` transitions directly when implementation and local
verification are complete.

Generated and local-only artifacts must not be committed. If such
files are accidentally tracked, untrack them and add or update the
appropriate ignore rule before continuing, unless intentionally
versioned.

Before writing the commit message, review the full change set and its
purpose. The message must accurately describe that purpose unless the
User explicitly requests otherwise. Do not use a misleading commit
message. For task-related commits, start the message with the
**Primary Identifier**: Ticket ID if present, otherwise full Task
Identifier. For non-task updates, commit messages may omit identifiers
when `AGENTS.md` policy allows it. If the User explicitly asks to skip
identifiers for a commit, honor that request.

After code or configuration changes, run relevant module tests before
reporting.

#### Done cleanup

Keep done tasks under `done` with a global three-digit completion
prefix. This numbering is independent of any optional backlog prefix.
Delete them from the working tree after a release tag is created.

## Context Preservation

- Re-read relevant task sections before implementation or whenever
  requirements are unclear.
- Treat the active task or subtask as the working source of truth for
  the current work item.
- Older task files are historical working records and need not be kept
  mutually consistent with the active task when later tasks supersede
  them.
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
where tracked work sits in the workflow.

Representation:
- Task lifecycle state is determined by the top-level status folder.
- For follow-up work on tasks already in `done`, the file remains in
  `done`; active lifecycle is represented by the newest follow-up
  subtask status.
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
  **Task-based Phases and Transitions**.
- LLM should move `in-progress` -> `review` when implementation and
  local verification are complete.
- For tasks not in done with subtasks, move the task itself to `review` only when
  every subtask status is `review`.
- Moving to and from `done` requires an explicit User request.
- Exception for initial placement: if `in-progress` contains no tasks
  and only one new task is being created, place it in `in-progress`.

## Task Structure

Each task uses this exact order and layout:

- Title line: `# Task: <title>`.
- Include one of the following identifiers (mutually exclusive):
  - `- **Ticket:**` Ticket ID, preferred.
  - `- **Task Identifier:**` if no Ticket exists;
    `YYYY-MM-DD-<slug>` where `<slug>` is 1-2 keywords from the
    filename.
  - The value present becomes the **Primary Identifier** for commit
    messages.
- Main task sections are list items with bold labels in this order:
  - `- **Scope:**`
  - `- **Motivation:**`
  - `- **Scenario:**` (conditional; include only when behavior is
    introduced/clarified or terms are introduced/refined)
  - `- **Constraints:**` (optional; include for important limits the
    target `Design` and implementation must obey)
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
- must start with a level 2 heading `## Subtask: <title>`
  followed by the status line `- **Status:** <status>`,
- must use the same list-item labels and ordering rules as the main task
  (including conditional `Scenario` and optional `Constraints`),
- must represent a functional increment and, for implementation tasks,
  include executable work,
- must satisfy the testing requirements defined in **Testing Policy**.
- Do not create planning-only subtasks unless the User explicitly asks.

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

- Short orientation for someone unfamiliar with the codebase, stack,
  subsystem boundaries, or entry points. Use this section for practical
  context such as relevant modules, important classes, framework
  context, repository conventions, and risk areas.

### Diagrams

- This section governs diagram use and formatting for task
  **Research** and **Design**.
- Use **PlantUML** by default.
- Use **Mermaid** only when the User or another governing instruction
  explicitly prefers Mermaid.
- Use **Research** for current state and **Design** for target state.
- **Research** must include a diagram when current behavior,
  message flow, context selection, or component interaction is being
  analyzed.
- **Design** must include a diagram when the change affects structure
  or component interaction.
- Prefer diagrams over text when they can express the research or
  design clearly. Use text only for what a diagram cannot express
  well.
- Diagrams may be omitted only when the task is confined to a single
  method or a trivially local change with no meaningful flow or
  interaction to visualize.
- Diagrams must not include test classes, test fixtures, or test-only
  helpers.
- Keep each diagram in its own paragraph under the owning section.
- Do not use notes inside diagrams. Put needed explanation below a
  diagram only when needed for context, rationale, constraints, or
  clarifications it cannot express.
- If both structure and behavior matter, use separate diagrams instead
  of mixing them in one diagram block.
- Declare component and sequence diagrams with explicit language
  keywords.
- For class diagrams, show only the class elements needed for the
  change or structural interaction, with meaningful dependency labels
  and at most one connector per class pair.

#### PlantUML-specific rules

- Use `allowmixing` only when class elements are combined with
  non-class elements.
- For class diagrams, use one outer `package` with nested inner
  packages and `set separator none`.

#### Mermaid-specific rules

- For class diagrams, use `classDiagram`.
- Use only single-level `namespace` blocks; do not nest
  `namespace` blocks.
- When hierarchical boundaries must be shown, flatten namespace names
  instead of nesting.

## Definition of Done for LLM

Before setting a task or subtask to **review**:

1. **Research**: legacy state is documented as needed.
2. **Scenario**: when applicable, expected behavior is documented in
   natural language.
3. **Design**: architecture, data flow, classes, and interactions are defined.
4. **Implementation**: Scope, Design, Constraints, and Test
   specification are fully implemented as applicable.
5. **Verification**: tests required by **Testing Policy** pass locally.
6. **Cleanliness**: no TODOs, placeholders, example names, temp
   comments, or unused imports remain.
7. **Documentation**: any implementation deviation from approved
   design is documented in the task file before review.
8. **Glossary**: required glossary work is complete.

## Testing Policy

- Keep a Test specification in each task without subtasks and in each
  subtask. For no-code tasks or subtasks, set `Automated tests: N/A`
  and `Manual tests: N/A`.
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
