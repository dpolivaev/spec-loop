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

### Core Invariants

- Task-first: research and design live in task files.
- Follow task formatting rules.

### Decision Tables (Operational Shortcuts)

Use these tables as the primary quick path. When a row applies, follow
it directly. For phase transitions, **Spec Loop Phases and
Transitions** is authoritative. Task/subtask lifecycle rules are
defined later in **Task States**.

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
research-only, and analysis-only requests remain in PLAN unless the
User requests otherwise.

Implementation approval details are defined by the Decision Tables and
remain mandatory.

## Task-first planning

Task-first workflow is mandatory for work that changes code, tests, or
configuration.

For ADR-only/research-only/analysis-only requests with no code, test,
or configuration changes, a task file is optional unless the User asks
for task-based tracking.

If non-code work later leads to implementation, enter PLAN in a task
file before any executable change.

When task-first applies and no suitable task file exists, propose
creating one before research/design. Do not keep implementation design
only in chat unless the User explicitly allows that mode.

Before IMPLEMENTATION, approved Design decisions and required
Constraints must be present in the task file.

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

2. **Research**  
  Start with research unless waived. Record observations,
  constraints, and verified facts only; plans belong in **Design**.
  Research documents the current system: current behavior, legacy
  architecture, reverse-engineered flows, current data structures,
  initialization details, characterization findings, and verified
  constraints. Current or as-is design belongs in **Research**.

3. **Scenario**  

  Scenario anchors behavior and terms before implementation. Use it
  when behavior/terms are introduced or changed; otherwise it can be
  skipped (see Decision Tables). Keep Scenario concise and
  implementation-free.

    **Naming principle (canonical terms):**
    Scenario is the source of domain and behavior language. When
    behavior or terms are introduced or changed, create or update
    Scenario first, then use those terms consistently in Design, tests,
    code symbols, and commit text. Do not keep parallel synonyms for
    the same domain concept.
    If existing code uses different names, align naming incrementally in
    the current scope and document any intentional temporary mismatch in
    the task file.
    Research may mention legacy terms and synonyms. Design must use only
    canonical Scenario terms, except for explicit legacy-to-target
    mapping tables.

    **Project glossary:**
    A project glossary in `glossary.adoc` is optional.
    If `glossary.adoc` exists, consider it during planning as the
    reference for project ubiquitous-language terms and definitions.
    Creating the first `glossary.adoc` from already approved information
    is documentation-only work and does not require a task file unless
    the User asks for task-based tracking.
    If a task changes or adds domain terms, the required
    `glossary.adoc` updates must be planned during PLAN.
    During IMPLEMENTATION, perform the planned `glossary.adoc`
    updates. They may only reflect approved Design.
    If required glossary updates were not planned, stop, return to
    PLAN, update the task, request approval, then continue.
    If `glossary-skill.md` exists next to this Constitution, consult it
    when creating or updating `glossary.adoc`. It is not required during
    PLAN and does not create an additional approval gate.


4. **Design**  
   Document implementation architecture, data flow, interactions, and
   responsibility boundaries in **Design**. Draft it from validated
   **Research** and required behavior from **Scenario** when Scenario
   exists. Design documents the approved target system only. Current
   implementation, legacy structure, reverse-engineered flows, and
   as-is diagrams must not appear in **Design** unless they are
   intentionally retained in the target design.

   Design must make intended implementation structure reviewable.
   Keep verification structure in **Test specification**, not in
   Design. Do not model test suites, test doubles, harnesses, or other
   test-only elements in Design unless the task itself changes test
   infrastructure. Design diagrams must not include test classes, test
   fixtures, or test-only helpers. When changing tools, APIs, or
   serialized payloads, **Design** must show the full target
   request/response structures and enums; examples may supplement but
   must not replace the class specification.
   Important responsibilities, interactions, and architectural
   boundaries should appear in the diagram whenever PlantUML can
   express them. When Scenario exists, Design must reflect Scenario
   language in implementation-oriented abstractions and responsibility
   boundaries.

   Design sections must use PlantUML diagrams
   (class/component/sequence). Do not use PlantUML notes. If mixing
   class and non-class elements, add `allowmixing`. For component or
   sequence diagrams, declare elements with explicit PlantUML
   keywords. For class diagrams, use one outer package with nested
   inner packages and add `set separator none`. Use meaningful
   dependency labels and at most one connector per class pair.

   Formatting: keep the diagram in its own paragraph under Design, with
   explanatory text in a separate paragraph below it only for context,
   rationale, constraints, or clarifications PlantUML cannot express.

5. **Test specification**
   Document verification structure and concrete test coverage.

6. **Iterative discovery**  
  Iterate across **Research**, **Scenario** (if used), **Design**, and
  **Test specification** until decisions and verification are
  coherent. No implementation starts during this loop.

7. **Implementation**  
   Implementation is complete only when both design and test
   specification are implemented, unless the user explicitly waives
   tests.

8. **Status updates**  
   Move task files between status folders within the project task
   directory to reflect current focus (for example, `in-progress` to
   `review`, or `done` back to `in-progress` or `backlog`).
   When reopening a task from done, keep the existing three-digit
   prefix to preserve traceability. Avoid moving unrelated tasks; move
   them only when actively worked on.

9. **Move workflow for diffs**  
   When moving tracked task files, use `git mv` and stage the move
   immediately before editing. This preserves rename tracking in diff
   tools that are not rename-aware (for example, VS Code). Do not
   unstage the rename until ready to review and commit. For new
   untracked task files, move in filesystem (not `git mv`), then run
   `git add -A`.

10. **Status validation before commits**  
   Update subtask status whenever task-file lifecycle state changes.
   Before each commit, check relevant task files and propose any status
   or folder changes needed for consistency. Apply those status changes
   only after explicit user confirmation, except that the LLM should
   apply `in-progress` -> `review` transitions directly for both
   task-folder status and subtask status when implementation and local
   verification are complete.
   Generated and local-only artifacts must not be committed. If such
   files are accidentally tracked, untrack them and add or update the
   appropriate ignore rule before continuing, unless they are
   intentionally versioned.
   Before writing the commit message, review the full change set being
   committed and its purpose. The commit message must accurately
   describe the purpose, unless the User explicitly requests otherwise.
   Do not write a commit message that is misleading about what changed.
   Then proceed with the commit.
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

11. **Done task cleanup**  
   Keep done tasks in the task directory under the done status folder
   with a three-digit prefix based on order moved into done. Delete them
   from the working tree after a release tag is created.

## Context Preservation

- **Task sections are source of truth**  
  Re-read relevant task sections (Scope, Motivation, Scenario,
  Constraints, Research, Design, Test Spec) before implementation or whenever
  requirements are unclear. Keep only relevant task content in active
  context and avoid carrying unrelated content.

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

Phases and lifecycle are different:

- Phases govern what work may be performed now.
- Task and subtask lifecycle states describe where the tracked item
  sits in the workflow.

Representation:
- Task lifecycle state is represented by the task file directory.
- Subtask lifecycle state is represented by `- **Status:** <status>`.

Lifecycle definitions:
- **backlog**  
  Planned or deferred work. Research and design belong here until
  design is approved. New tasks default to `backlog`.
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
  If `Scenario` or `Constraints` is omitted, keep the remaining
  sections in order.

**Constraints** (optional)

- Use when the task has important limits that the target `Design` and
  implementation must obey.
- Typical content: semantic invariants, non-goals, compatibility
  limits, performance limits, identity rules, and forbidden
  simplifications.
- If `Design` conflicts with `Constraints`, `Constraints` wins.

**Briefing**

- Short orientation for someone unfamiliar with the codebase,
  stack, subsystem boundaries, or entry points.
- Use this section for practical context such as relevant modules,
  important classes, framework context, repository conventions, and
  risk areas.

Subtasks (if any):

- Appear only at the end as `## Subtask: <title>` sections; append new
  subtasks unless the user explicitly requests different ordering.
- Each subtask:
  - starts with `- **Status:** <status>`,
  - uses the same list-item labels and ordering rules as the main task
    (including conditional `Scenario` and optional `Constraints`),
  - represents a functional increment unless explicitly marked
    otherwise.

Subtask `Status` values and transitions use the same lifecycle rules as
defined in **Task States**.

**Definition of Done for LLM:**

Before setting a task or subtask to **review**:

1. **Research**: legacy state is documented as needed.
2. **Scenario**: when applicable, expected behavior is documented as a
   clear story in natural language.
3. **Design**: architecture/data flow/class interactions are defined.
4. **Scope**: Scope, Design, Constraints, and Test specification are
   fully implemented as applicable.
5. **Verification**: new and relevant existing tests pass locally.
6. **Cleanliness**: no TODOs, placeholders, temp comments, unused
   imports.
7. **Documentation**: design deviations are documented in the task
   file. If implementation differs from the approved target design, the
   deviation and rationale must be documented in the task file before
   review.

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
- Before moving a subtask to **review**, required tests must be
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
