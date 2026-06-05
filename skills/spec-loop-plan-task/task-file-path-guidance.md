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

After implementation approval on the task-file path, follow
`../spec-loop-implementation-flow/SKILL.md` and its task-file path
companion for implementation-time handling, task-file updates,
`Implementation notes`, and the move into `review`.

## Task-file path readiness

On the task-file path, the task file is the source of truth for the
current increment.

Backlog tasks may keep Research and Design high-level or `To be
done` until current. Omit Analysis until final clarification
decisions exist.

In tasks with subtasks, the shared readiness rules apply to the active
subtask and any task-level context it depends on, not to future
subtasks that are not yet current.

During clarification on the task-file path, the active task file is
the governing artifact. Follow
`../spec-loop-clarify-task/SKILL.md` for clarification batching and
recording rules.

Keep the task file aligned with the current final clarification
state, including the `Analysis` section and every affected canonical
section. Sync at a clean checkpoint before that state would be hard
to reconstruct safely from chat alone, and always before
clarification returns control to another workflow.

These sync edits preserve state. Do not ask the User to review them
separately during clarification. If unresolved questions remain after
a sync, continue clarification from the updated task file.

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

Later executable follow-up from a task or subtask already in
`review` or `done` returns to PLAN and follows the shared follow-up
rule in [common-task-guidance.md](./common-task-guidance.md) before
more implementation.

If that shared rule keeps the same task or subtask from `review`, it
stays in place by default during that planning and renewed-approval
work. Do not require a move back to `in-progress` or separate
tracking solely because the earlier implementation created an
intermediate state. Briefly mention that the User may instead request
a new task or subtask, a task move, or a subtask status change.

Tasks in `done` stay in place. If the User has not already specified
whether to reuse the same task or subtask or use separate tracking,
ask. Do not substantially rework `done` sections or subtasks unless
the User asks.

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
- Tasks in `review` or `done` stay there by default.
- Later executable follow-up from `review` or `done` returns to PLAN.
  During that planning and renewed-approval work, a `review` item may
  stay in place without a new subtask. A `done` item is reused only
  when the User chooses that.
- Only subtasks have status fields. The task status itself is
  indicated only by its folder.
- Subtask lifecycle: `- **Status:** <status>`.

Lifecycle definitions:
- **backlog** — planned or deferred. New tasks default here.
- **in-progress** — active research, design, implementation, or
  verification.
- **review** — in the User review cycle. By default, a task or
  subtask stays in `review` once that cycle is reached, including
  same-task executable follow-up that planning keeps on that task or
  subtask, unless the User explicitly asks for more formal tracking.
- **done** — User-verified completion.

Lifecycle and transition rules:
- Same transition guards as `SKILL.md` and the shared readiness rules.
- Allowed task-file moves: `backlog` <-> `in-progress` -> `review` ->
  `done`, plus explicit User-requested `review` -> `in-progress`.
- If `in-progress` is empty and only one new task is being created,
  place it in `in-progress`, otherwise in `backlog`.
- LLM moves `in-progress` -> `review` when implementation and local
  verification are complete under `spec-loop-implementation-flow`.
- Later executable follow-up from `review` or `done` returns to PLAN
  before more implementation.
- During that planning and renewed-approval work, a `review` item
  stays in `review` by default. The LLM must not move a task back to
  `in-progress` or change a subtask from `review` to `in-progress`
  unless the User explicitly asks.
- For follow-up from `done`, if the User has not already chosen
  whether to reuse the same task or subtask or use separate tracking,
  ask before revising the artifact.
- When the User explicitly changes a subtask from `review` to
  `in-progress` during that follow-up, the overall task may stay in
  `review` unless the User also requests a task-level move.
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
- main-task Research, Analysis, Design, Test specification, and
  empty Implementation notes may be omitted;
- omitted Scenario, Constraints, Analysis, or empty Implementation
  notes keep the remaining sections in order.
- For later executable follow-up from `review` or `done`, first apply
  the shared follow-up rule in
  [common-task-guidance.md](./common-task-guidance.md).
- Do not create a new subtask by default when that shared rule keeps
  the same task or subtask.
- An implementation-created intermediate state alone does not force a
  new subtask.
- If the shared rule keeps the same overall task but the User wants
  separate tracking or a separate governed starting state is actually
  required, recommend a new subtask and wait for explicit User
  confirmation before creating it.
- When a new subtask is created to preserve a separate governed
  starting state or history-preserving intermediate state, that state
  may appear only in the new subtask's Research as its starting
  state.
- If a task without subtasks needs its first separately tracked
  follow-up within the same overall task, convert it to subtask form
  by default.
- During that conversion, keep only shared context at task level and
  move subtask-specific Research, Analysis, Design, and Test
  specification into the relevant subtask.
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
  conditional Analysis, and conditional Implementation notes,
- must represent a separately tracked work unit within the same
  overall task; for implementation tasks it must include executable
  work; this is usually a functional increment, but a
  history-preserving review follow-up may also justify a subtask,
- is not assumed to be self-sufficient; before working from a
  subtask, read the relevant task-level sections and diagrams needed
  to understand it correctly;
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
  lightweight until current. Analysis may stay omitted or minimal
  until final clarification decisions exist. Placeholders like `To be
  done` or `See main task` are allowed for Research, Design, and Test
  specification.
- Current implementation subtask must have detail needed for review
  and execution.
- Once a decision is made, remove obsolete or superseded
  alternatives.
- Repeat diagrams, types, payloads, or prose only when it adds local
  reasoning value or shows genuinely different
  behavior, ownership, or contract.

### Task Compaction

Use `../spec-loop-compact-task-file/SKILL.md` when:
- converting a task from no-subtask form to subtask form;
- adding a new subtask after earlier subtasks already contain full
  section content; or
- the active task file has become too large to use safely.

## Diagrams

Diagrams are task-file-only planning artifacts. If diagrams would
materially help because research or design is no longer simple,
promote chat-only work to the task-file path before using them.

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
