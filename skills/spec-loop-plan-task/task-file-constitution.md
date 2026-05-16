# Task-file Constitution

This file applies only on the task-file path of
`spec-loop-plan-task`.

It defines task-file structure, lifecycle, sections, diagrams,
testing policy, and administration.

## Task-file path readiness

On the task-file path, the task file is the source of truth for the
current increment.

Backlog tasks may keep Research/Design high-level or `To be done`
until current. Before IMPLEMENTATION, the active task needs: required
Research, required Constraints, required Scenario,
implementation-ready Design, and Test specification for the current
increment — even when the User allows combined phases.

Before asking the User to approve a task file for IMPLEMENTATION, the
LLM must self-check that the content for the current implementation
increment meets all applicable requirements of this Task-file
Constitution and is correct, internally consistent, and compliant with
`AGENTS.md` and applicable glossary rules.

In tasks with subtasks, this applies to the active subtask and any
task-level context it depends on, not to future subtasks that are not
yet current.

If asking the User to review a draft instead, say so explicitly and
list the known gaps, open questions, and unresolved decisions.

## Task Artifacts and Administration

### 1. Task files

Task files live under the project task directory.

Top-level folders: `backlog`, `in-progress`, `review`, `done`.

- Only `backlog` may have subfolders.
- Backlog subfolder names are organizational only.
- Backlog numbering optional; if used, readable three-digit prefix local to containing folder.
- `done` uses required three-digit completion-order prefix, one global sequence.

Task base names: no ticket IDs, abbreviations; use readable descriptive words.

Backlog and done numbering independent. Same number may appear in multiple backlog folders, once in `done`.

### 2. Planning artifacts

#### Research

Start with research unless waived. Record observations, constraints, verified facts only. Plans go in **Design**. Documents current system: behavior, implementation, legacy arch, flows, data structures, findings, constraints, as-is diagrams.

#### Scenario

Anchors behavior and domain terms before implementation. Use when behavior or terms introduced/changed; else omit. Keep concise, implementation-free.

Scenario = source of domain/behavior language. When terms introduced/changed: create/update Scenario first, use those terms in Design, tests, code, commits. No parallel synonyms.

Research may mention legacy terms. Design uses only canonical Scenario terms except explicit legacy-to-target mapping tables. If code uses different names, align incrementally and document intentional mismatch in task file.

#### Project glossary

A project glossary is optional until the project creates one.
Recognize `glossary.adoc` and `glossary.md` as project glossary files.
If both exist, ask which one is canonical before updating either.

Once a project glossary exists, use it as shared task language.
Do not add helper names, implementation details, framework terms, or
terms not needed to explain project rules, behavior, or subsystem
boundaries.

If the current task requires glossary work:

- reflect it in the task plan,
- perform it during IMPLEMENTATION with traceability links,
- use `spec-loop-write-glossary` for the Spec Loop AsciiDoc format;
  otherwise update the project's active format.

If the task plan is missing required glossary work, return to PLAN,
update the task, get approval, and continue.

#### Design

Documents target system: architecture, data structures, data flow, interactions, implementation boundaries. Draft from validated Research and Scenario behavior.

Design = implementation contract. Must be reviewable and implementation-ready.

Use only final intended names for design-owned terms, units, config keys, tool/API names, request/response fields, enum values, etc. No placeholders, temp names, candidate names, example names. Undecided name/unit/boundary = not ready for implementation.

When production structure changes, Design must make the structural
inventory explicit for every planned new or changed top-level
production class, interface, enum, and every new or changed
externally meaningful identifier in scope. Use the class diagram as
the primary structural inventory when class diagrams are required.
Companion prose, lists, or tables may capture exact ownership,
responsibilities, collaborators, methods, fields, or identifiers that
the diagram cannot express clearly enough for review. Examples of
externally meaningful identifiers include persisted file names,
serialized field names, config keys, action keys, menu placeholder
names, and shared session/state flags.

Local variables, private methods, private fields contained within one
class, and other purely internal implementation details are excluded
from this inventory unless the User explicitly requests lower-level
review.

Test-only elements go in **Test specification**, not Design, unless task changes test infrastructure.

When changing tools/APIs/serialized payloads, Design must show full target request/response structures and enums. Examples supplement, don't replace spec.

When Scenario exists, Design uses canonical Scenario terms. Diagram must show relevant units/names in diagram itself, not only prose.

#### Test specification

Documents verification structure and concrete test coverage.

#### Iterative discovery

Iterate across Research, Scenario (if used), Design, Test spec until coherent. Record intermediate alternatives only when they aid reasoning/review. No implementation during this loop.

### 3. Task administration

#### Status moves

Move task files between folders to reflect lifecycle state.

Backlog subfolder moves: organizational only. Adjust backlog prefixes to fit target. Remove backlog prefix only when moving out of `backlog`. Moving into `done`: assign next global `done` prefix independently.

Tasks in `review` or `done` stay in place. Perform minor review adjustments without a separate subtask, but do not substantially rework finished sections/subtasks unless the User asks. Append new subtasks for substantial rework or extension.

#### Tracked moves

Use `git mv` for tracked task files, stage move immediately before editing. Preserves rename tracking. Don't unstage until ready to review and commit. For new untracked files: move in filesystem, then `git add -A`.

#### Commit checks

Update subtask status on lifecycle state change. Before each commit: check relevant task files, propose needed status/folder changes. Apply only after explicit User confirmation, except LLM applies `in-progress` -> `review` directly when implementation and local verification are complete.

No generated or local-only artifacts in commits. If accidentally tracked: untrack, add/update ignore rule before continuing, unless intentionally versioned.

Before writing commit message: review full change set and purpose. Message must accurately describe purpose unless User says otherwise. No misleading commit messages. Task commits: start with **Primary Identifier** (Ticket ID if present, else full Task Identifier). Non-task updates may omit identifiers if `AGENTS.md` allows. If User asks to skip identifiers, honor it.

After code or config changes: run relevant module tests before reporting.

#### Done cleanup

Keep done tasks under `done` with global three-digit prefix (independent of backlog prefix). Delete from working tree after release tag created.

## Context Preservation

- Re-read relevant task sections before implementation or when requirements unclear.
- Active task/subtask = working source of truth for current item.
- Older task files = historical records; need not stay consistent with active task when superseded.
- Keep only relevant task content in active context.

## Formatting

- Wrap prose ~72-80 chars; no horizontal scrolling.
- Preserve semantic line breaks and consistent list indentation.
- Fenced code blocks: unindented (except internal structure), start/end with backticks.
- Standalone paragraphs unindented. List continuation lines may indent to align.
- Renders correctly on GitHub and GitLab.

Intent: readable in plain text editors (vim, less, nano) and rendered views.

## Task States

Tasks and subtasks share one lifecycle: `backlog`, `in-progress`, `review`, `done`.

Phases = what work may happen now. Lifecycle states = where tracked work sits.

Representation:
- Task state = top-level folder until the file reaches `review` or `done`.
- Files in `review` or `done` stay there. Later work uses the newest follow-up subtask status.
- Subtask lifecycle: `- **Status:** <status>`.

Lifecycle definitions:
- **backlog** — planned or deferred. New tasks default here.
- **in-progress** — active research, design, implementation, or verification.
- **review** — implementation complete, locally verified, awaiting User review/acceptance.
- **done** — User-verified completion.

Lifecycle and transition rules:
- Same transition guards as `SKILL.md` and the readiness rules above.
- Allowed task-file moves: `backlog` <-> `in-progress` -> `review` -> `done`.
- If `in-progress` is empty and only one new task is being created, place it in `in-progress`, otherwise in `backlog`.
- LLM moves `in-progress` -> `review` when implementation and local verification complete.
- Task (not in done) with subtasks: move task to `review` only when every subtask is `review`.
- Moving into `done` requires explicit User request.

## Task Structure

Each task uses this exact order and layout:

- Title line: `# Task: <title>`.
- One identifier (mutually exclusive):
  - `- **Ticket:**` Ticket ID, preferred.
  - `- **Task Identifier:**` if no Ticket; `YYYY-MM-DD-<slug>` where `<slug>` is 1-2 keywords from filename.
  - Value present = **Primary Identifier** for commit messages.
- Main task sections as bold-label list items in this order:
  - `- **Scope:**`
  - `- **Motivation:**`
  - `- **Scenario:**` (conditional; only when behavior/terms introduced or refined)
  - `- **Constraints:**` (optional; important limits Design and implementation must obey)
  - `- **Briefing:**`
  - `- **Research:**`
  - `- **Design:**`
  - `- **Test specification:**`
  In tasks with subtasks, main-task Research, Design, and Test specification may be omitted.
  Omitted Scenario or Constraints: keep remaining sections in order.
- Subtasks (if any): after all global task sections.

### Every Subtask
- must start with `## Subtask: <title>` followed by `- **Status:** <status>`,
- must use same list-item labels and ordering as main task (including conditional Scenario and optional Constraints),
- must represent a functional increment; for implementation tasks must include executable work,
- must satisfy **Testing Policy**.
- No planning-only subtasks unless User explicitly asks.

### Task Context Hygiene

- No redundant duplication across main task and subtasks. Reused context: reference briefly, state only local adaptation/risk/decision.
- Future subtasks may keep Research, Design, Test spec lightweight until current. Placeholders like `To be done` or `See main task` allowed.
- Current implementation subtask must have detail needed for review and execution.
- Once decision made, remove obsolete/superseded alternatives.
- Repeat diagrams, types, payloads, or prose only when it adds local reasoning value or shows genuinely different behavior/ownership/contract.

### Constraints (optional)

- Use for important limits Design and implementation must obey.
- Typical content: semantic invariants, non-goals, compatibility limits, performance limits, identity rules, forbidden simplifications.
- If Design conflicts with Constraints, Constraints wins.

### Briefing

Short orientation for someone unfamiliar with codebase: relevant modules, important classes, framework context, repo conventions, risk areas.

### Diagrams

- Governs diagrams in task **Research** and **Design**.
- Use **PlantUML** by default.
- Use **Mermaid** only when User or governing instruction explicitly prefers it.
- **Research** = current state. **Design** = target state.
- **Research** must include diagram when analyzing current behavior, message flow, context selection, or component interaction.
- **Design** must include diagram when change affects structure or component interaction.
- Prefer diagrams over text when they can express research/design clearly.
- Omit diagrams only when task is confined to single method or trivially local change with no meaningful flow/interaction.
- No test classes, fixtures, or test-only helpers in diagrams.
- Each diagram in its own paragraph under owning section.
- No notes inside diagrams. Put explanation below diagram only when needed.
- Structure and behavior both matter: use separate diagrams.
- Declare component and sequence diagrams with explicit language keywords.
- Always use class diagrams when classes are added, removed, or structurally modified.
- When class diagrams are required, treat the class diagram as the
  primary structural inventory for planned new or changed top-level
  production types and their relationships.
- Add a companion responsibility table, compact list, or equivalent
  supporting artifact only when the diagram alone cannot make
  ownership, structure, or exact identifiers clear enough for review.
- Class diagrams: show only elements needed for change or structural interaction, meaningful dependency labels, at most one connector per class pair.

#### PlantUML-specific rules

- Prefer separate diagrams over `allowmixing`; keep file/folder tree, component, class, and sequence diagrams separate unless one mixed diagram is genuinely required.
- Class diagrams: one outer `package` with nested inner packages and `set separator none`.
- Use escape character `~` for text matching creole markup like `--`.

#### Mermaid-specific rules

- Class diagrams: use `classDiagram`.
- Only single-level `namespace` blocks; no nesting.
- Hierarchical boundaries: flatten namespace names instead of nesting.

## Definition of Done for LLM

Before setting task or subtask to **review**:

1. **Research**: legacy state documented as needed.
2. **Scenario**: expected behavior in natural language when applicable.
3. **Design**: architecture, data flow, classes, interactions defined.
4. **Implementation**: Scope, Design, Constraints, Test spec fully implemented as applicable.
5. **Verification**: required tests pass locally.
6. **Cleanliness**: no TODOs, placeholders, example names, temp comments, unused imports.
7. **Documentation**: any deviation from approved design documented in task file.
8. **Glossary**: required glossary work complete.

## Testing Policy

- Keep Test specification in each task without subtasks and in each subtask. No-code tasks: set `Automated tests: N/A` and `Manual tests: N/A`.
- Implementation subtasks must include testing. Don't split implementation and testing across separate subtasks for same functional increment.
- Separate test-focused tasks allowed when adding/extending coverage as standalone scope.
- Prefer automated tests.
- Each implementation task without subtasks and each implementation subtask: include explicit Automated tests and Manual tests sublists.
- Run and fix all required tests before moving to **review**, unless User waives tests.
