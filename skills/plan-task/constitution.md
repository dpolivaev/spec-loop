# Constitution

- **Principle over ceremony**
- Intent-first. Judge by outcome, not checklist.
- Whenever the LLM stops or pauses, it must explain the reason explicitly.
- If `AGENTS.md` and this Constitution conflict, stop and ask the User.
- **Enforcement, pre-edit gate, and LLM stewardship**
- Constitution mandatory. LLM enforces it. Not User's job.
- Only the User may override workflow rules.

## Constitution Handling (Global)

- Constitution is global, identical across all dirs.
- Already injected/attached: don't re-read.
- Else: read once, keep 3-5 line digest in context.
- Re-read only if digest missing or User says it changed.

### Core Invariants

- Task work: plan-first.
- Follow task formatting rules.

### Decision Tables (Operational Shortcuts)

Quick lookup only. **Task-based Phases and Transitions** and **Task States** are authoritative.

| Situation | Required action | Resulting phase |
| --- | --- | --- |
| Request executable changes (code/test/config/deps/runtime assets) | Enter PLAN: update Research/Scenario/Design as needed | PLAN |
| Refactoring that changes code, tests, or configuration | Enter PLAN: update Design | PLAN |
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

Code/test/config changes: must be task-based. Other work: task file optional unless User asks. No suitable task file: propose creating one first. Chat is coordination only, not design artifact.

## Task-based Phases and Transitions

Phases:

- **PLAN** - research, design/spec, test spec.
- **IMPLEMENTATION** - code + required tests, follow approved Design/Test spec unless User waives tests.
- **DONE** - verified, accepted.

PLAN: edits only to non-executable artifacts (task files, ADRs, docs, diagrams, instruction files). Commands allowed for research/verification only if they don't change repo contents outside those artifacts. If they would, treat as IMPLEMENTATION and get User approval first.

Anything touching executable behavior, tests, build/config, deps, packaging, or runtime assets = IMPLEMENTATION. Needs explicit User instruction.

Work starts in **PLAN**, returns to **PLAN** after each work item unless User says otherwise.

- Ask questions before PLAN only to clarify scope or constraints.
- No permission questions for already requested work.
- Starting PLAN artifacts, entering IMPLEMENTATION and marking DONE require explicit User instruction.
- If scope, design, naming, or logic changes, request re-approval. No IMPLEMENTATION inertia across items.
- Phases exclusive unless User allows combined planning+implementation.

Backlog tasks may keep Research/Design high-level or `To be done` until current. Before IMPLEMENTATION, active task needs: required Research, required Constraints, required Scenario, implementation-ready Design, Test spec for increment — even when User allows combined phases.

Before asking the User to approve a task file for IMPLEMENTATION, the
LLM must self-check that the content for the current implementation
increment meets all applicable requirements of this Constitution and is
correct, internally consistent, and compliant with `AGENTS.md` and
applicable glossary rules.

In tasks with subtasks, this applies to the active subtask and any
task-level context it depends on, not to future subtasks that are not
yet current.

If asking the User to review a draft instead, say so explicitly and
list the known gaps, open questions, and unresolved decisions.

IMPLEMENTATION -> PLAN: LLM may initiate if required (scope drift, unclear classification, rule conflict, missing approved Design). State reason and what must be resolved before IMPLEMENTATION resumes.

Phase model governs task-scoped work only. Non-code/test/config work is outside it unless User says otherwise.

## Task Artifacts and Administration

Phase entry/exit/approval rules stay in **Task-based Phases and Transitions**.

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

A project glossary is optional until the project creates one. Recognize
`glossary.adoc` and `glossary.md` as project glossary files. If both exist,
ask the user which one is canonical before updating either. Once a project
glossary exists, it defines shared domain language; use it during planning as
reference. Creating the first project glossary from approved info = docs-only
work, no task file needed unless User asks. No helper names, impl details,
framework terms, or terms not needed to understand project
rules/behavior/subsystem boundaries.

When a recognized project glossary exists, LLM must:

- check if approved work changes/clarifies/implements shared domain terms,
- plan glossary updates during PLAN,
- perform glossary updates during IMPLEMENTATION with traceability links,
- use `write-glossary` if available when the project glossary uses the
  AsciiDoc glossary format defined by the `write-glossary` skill;
  otherwise update the glossary directly in the active project format.

If required glossary updates not planned: stop, return to PLAN, update task,
get approval, continue.

#### Design

Documents target system: architecture, data structures, data flow, interactions, implementation boundaries. Draft from validated Research and Scenario behavior.

Design = implementation contract. Must be reviewable and implementation-ready.

Use only final intended names for design-owned terms, units, config keys, tool/API names, request/response fields, enum values, etc. No placeholders, temp names, candidate names, example names. Undecided name/unit/boundary = not ready for implementation.

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
- Same transition guards as **Task-based Phases and Transitions**.
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
- Class diagrams: show only elements needed for change or structural interaction, meaningful dependency labels, at most one connector per class pair.

#### PlantUML-specific rules

- Prefer separate diagrams over `allowmixing`.
- File/folder tree diagrams, component diagrams, class diagrams, and sequence diagrams should normally be separate.
- Use `allowmixing` only when class elements combined with non-class elements in one diagram are genuinely required.
- When syntax or rendering is uncertain, follow `examples/example-task-wordle-cli.md`.
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

## Architecture Decision Records

- Record architecture decisions in `architecture-decisions/` as one file per decision, meaningful names.
- ADRs may be created directly without a task file unless User requests task-linked ADR work.
- ADR file names: no prefixes (including numbers), no abbreviations; readable descriptive words.
- Short template: Title, Date, Status, Context, Decision, Consequences.
- Use ADRs for decisions affecting public behavior, dependencies, or long-term design.