# Task-file Path Guidance

This file applies only on the task-file path of
`spec-loop-plan-task`.

Read [common-task-guidance.md](./common-task-guidance.md) first.
That file defines the shared no-subtask main-task form, section
semantics, current-increment readiness rules, testing policy,
context-preservation rules, and formatting conventions used on both
planning paths.

This file adds only task-file-specific mechanics: task files,
lifecycle, tracked moves, subtasks, task-file-only testing additions,
and diagram rules.

## Task-file path readiness

On the task-file path, the task file is the source of truth for the
current increment.

Backlog tasks may keep Research/Design high-level or `To be done`
until current.

In tasks with subtasks, the shared readiness rules apply to the active
subtask and any task-level context it depends on, not to future
subtasks that are not yet current.

## Task Artifacts and Administration

### 1. Task files

Task files live under the project task directory.

Top-level folders: `backlog`, `in-progress`, `review`, `done`.

- Only `backlog` may have subfolders.
- Backlog subfolder names are organizational only.
- Backlog numbering optional; if used, readable three-digit prefix
  local to containing folder.
- `done` uses required three-digit completion-order prefix, one
  global sequence.

Task base names: no ticket IDs, abbreviations; use readable
descriptive words.

Backlog and done numbering independent. Same number may appear in
multiple backlog folders, once in `done`.

### 2. Task administration

#### Status moves

Move task files between folders to reflect lifecycle state.

Backlog subfolder moves: organizational only. Adjust backlog prefixes
to fit target. Remove backlog prefix only when moving out of
`backlog`. Moving into `done`: assign next global `done` prefix
independently.

Tasks in `review` or `done` stay in place. Perform minor review
adjustments without a separate subtask, but do not substantially
rework finished sections or subtasks unless the User asks. Append new
subtasks for substantial rework or extension.

#### Tracked moves

Use `git mv` for tracked task files, stage move immediately before
editing. Preserves rename tracking. Don't unstage until ready to
review and commit. For new untracked files: move in filesystem, then
`git add -A`.

#### Commit checks

Update subtask status on lifecycle state change. Before each
task-scoped commit: check relevant task files. If relevant task files
are modified, those modifications must be staged and included in the
same commit. Do not invent synthetic task-file edits solely to satisfy
this coupling. Propose needed status or folder changes. Apply only
after explicit User confirmation, except LLM applies `in-progress` ->
`review` directly when implementation and local verification are
complete under `spec-loop-implementation-flow`.

No generated or local-only artifacts in commits. If accidentally
tracked: untrack, add or update ignore rule before continuing, unless
intentionally versioned.

Before writing commit message: review full change set and purpose.
Message must accurately describe purpose unless User says otherwise.
No misleading commit messages. Task commits: start with **Primary
Identifier** (Ticket ID if present, else full Task Identifier).
Non-task updates may omit identifiers if `AGENTS.md` allows. If User
asks to skip identifiers, honor it.

After code or config changes: run relevant module tests before
reporting.

#### Done cleanup

Keep done tasks under `done` with global three-digit prefix
(independent of backlog prefix). Delete from working tree after
release tag created.

## Task States

Tasks and subtasks share one lifecycle: `backlog`, `in-progress`,
`review`, `done`.
These status values are an exact enum. No other task or subtask status
values are allowed.

Phases = what work may happen now. Lifecycle states = where tracked
work sits.

Representation:
- Task state = top-level folder.
- Tasks in `review` or `done` stay there. Later work may add follow-up
  subtasks; the newest follow-up subtask status carries the active
  work.
- Only subtasks have status fields. The task status itself is
  indicated only by its folder.
- Subtask lifecycle: `- **Status:** <status>`.

Lifecycle definitions:
- **backlog** — planned or deferred. New tasks default here.
- **in-progress** — active research, design, implementation, or
  verification.
- **review** — implementation complete, locally verified, awaiting
  User review or acceptance.
- **done** — User-verified completion.

Lifecycle and transition rules:
- Same transition guards as `SKILL.md` and the shared readiness rules.
- Allowed task-file moves: `backlog` <-> `in-progress` -> `review` ->
  `done`.
- If `in-progress` is empty and only one new task is being created,
  place it in `in-progress`, otherwise in `backlog`.
- LLM moves `in-progress` -> `review` when implementation and local
  verification are complete under `spec-loop-implementation-flow`.
- Subtask status changes apply only to that subtask unless the User
  explicitly says otherwise.
- Task with subtasks: move task to `review` when no unfinished
  task-level or subtask-level work remains and at least one subtask is
  in `review`.
- Task with subtasks: move task to `done` only when every subtask is
  `done` and the User explicitly requests moving the task to `done`.
- The LLM may propose a task-level move when its guard becomes true,
  but must not assume an unrequested task-level `done` move.
- Moving into `done` is user-only, always. The LLM must never move a
  task or subtask to `done` without explicit User request.

## Task Structure on the task-file path

Use the shared no-subtask main-task structure from
[common-task-guidance.md](./common-task-guidance.md).

When a task uses subtasks:

- keep subtasks after all global task sections;
- main-task Research, Design, Test specification, and empty
  Implementation notes may be omitted;
- omitted Scenario, Constraints, or empty Implementation notes keep
  the remaining sections in order.
- Do not create a new subtask unless the work adds a separate
  functional increment.
- If the current increment changes without adding one, revise the
  active task or subtask in place.
- If a task without subtasks gains its first separate functional
  increment, convert it to subtask form by default.
- During that conversion, keep only shared context at task level and
  move increment-specific Research, Design, and Test specification
  into the relevant subtasks.
- If the original no-subtask task already describes a concrete
  functional increment, promote that increment into its own subtask.
- Do not create a synthetic "original task" subtask when the former
  top-level content was only broad shared context and not a distinct
  functional increment.

### Every Subtask

- must start with `## Subtask: <title>` followed by
  `- **Status:** <status>`,
- must use the same list-item labels and ordering as the shared main
  task form, including conditional Scenario, optional Constraints,
  and conditional Implementation notes,
- must represent a functional increment; for implementation tasks must
  include executable work,
- must satisfy **Testing Policy**.
- No planning-only subtasks unless User explicitly asks.

`Implementation notes` placement:
- without subtasks: task level;
- with subtasks: active implementation subtask, unless a genuine
  task-level note is needed.

### Task Context Hygiene

- No redundant duplication across main task and subtasks. Reused
  context: reference briefly, state only local adaptation, risk, or
  decision.
- Future subtasks may keep Research, Design, and Test specification
  lightweight until current. Placeholders like `To be done` or
  `See main task` allowed.
- Current implementation subtask must have detail needed for review
  and execution.
- Once a decision is made, remove obsolete or superseded
  alternatives.
- Repeat diagrams, types, payloads, or prose only when it adds local
  reasoning value or shows genuinely different
  behavior, ownership, or contract.

## Diagrams

Diagrams are task-file-only planning artifacts. If diagrams would
materially help because research or design is no longer simple,
promote fileless work to the task-file path before using them.

- Governs diagrams in task **Research** and **Design**.
- Use **PlantUML** by default.
- Use **Mermaid** only when User or governing instruction explicitly
  prefers it.
- **Research** = current state. **Design** = target state.
- **Research** must include diagram when analyzing current behavior,
  message flow, context selection, component interaction, or current
  class design.
- **Design** must include diagram when change affects structure,
  component interaction, or target class design.
- When Scenario exists, Design diagrams must show the relevant units
  and names in the diagram itself, not only in surrounding prose.
- Prefer diagrams over text when they can express research or design
  clearly.
- Omit diagrams only when task is confined to a single method or a
  trivially local change with no meaningful flow or interaction.
- No test classes, fixtures, or test-only helpers in diagrams.
- Each diagram in its own paragraph under the owning section.
- No notes inside diagrams. Put explanation below the diagram only
  when needed.
- Do not restate diagram-carried structure or behavior in surrounding
  prose unless the diagram cannot express it cleanly.
- Structure and behavior both matter: use separate diagrams.
- Declare component and sequence diagrams with explicit language
  keywords.
- Use class diagrams whenever current or target class design is
  materially relevant.
- In **Research**, show the current classes, interfaces, enums,
  relationships, and review-relevant members needed for review.
- In **Design**, show the target classes, interfaces, enums,
  relationships, and review-relevant members needed for review.
- Treat the class diagram as the primary structural inventory for those
  types, relationships, and review-relevant members.
- Put review-relevant operations in interface and class boxes.
- Put review-relevant fields in DTO, request, response, result,
  value-object, and similar data-type boxes.
- Do not restate those members in prose, lists, or tables unless the
  diagram would become unreadable or the information is not naturally
  owned by a class box.
- Add a companion responsibility table, compact list, or equivalent
  supporting artifact only when the diagram alone cannot make
  ownership, structure, or exact external identifiers clear enough for
  review.
- Class diagrams: show only elements needed for change or structural
  interaction, meaningful dependency labels, and at most one
  connector per class pair.

### PlantUML-specific rules

- Prefer separate diagrams over `allowmixing`; keep file or folder
  tree, component, class, and sequence diagrams separate unless one
  mixed diagram is genuinely required.
- Class diagrams: one outer `package` with nested inner packages and
  `set separator none`.
- Use escape character `~` for text matching creole markup like `--`.

### Mermaid-specific rules

- Class diagrams: use `classDiagram`.
- Only single-level `namespace` blocks; no nesting.
- Hierarchical boundaries: flatten namespace names instead of
  nesting.

## Testing Policy on tasks with subtasks

Use the shared Testing Policy from
[common-task-guidance.md](./common-task-guidance.md) for all
no-subtask tasks.

For task-file subtasks:

- Implementation subtasks must include testing. Don't split
  implementation and testing across separate subtasks for the same
  functional increment.
- Separate test-focused tasks allowed when adding or extending
  coverage as standalone scope.
- Each implementation subtask: include an explicit `Automated tests`
  sublist.
- Include a `Manual tests` sublist only when non-automatable
  verification remains; otherwise set `Manual tests: N/A`.
- Run and fix all required tests before moving a task or subtask to
  `review`, unless the User waives tests.
