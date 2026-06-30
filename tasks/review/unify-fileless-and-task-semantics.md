# Task: Unify fileless and task-file task semantics

- **Task Identifier:** 2026-05-24-fileless-semantics

- **Scope:**
  Replace the current short planning path with a fileless planning
  path that reuses the full task section semantics of the task-file
  path. Extract the shared task rules from task-file-only mechanics,
  extend implementation-time handling to fileless work, and update the
  affected skill and documentation text without changing the existing
  lightweight eligibility and escalation intent except where a verified
  consistency fix is required.

- **Motivation:**
  The current short planning path is lighter than the task-file path
  but too structurally loose. We want one disciplined task shape for
  both paths without forcing small single-path work into file
  lifecycle overhead. A shared semantics split also reduces drift
  between planning, approval, and implementation-time rules.

- **Scenario:**
  A lightweight executable task begins on the fileless path with one
  full task-shaped chat artifact. The fileless path has one active
  task at a time, uses no subtasks, uses no diagrams, and stays in
  chat unless escalation promotes it to the task-file path. If
  clarification or an approved implementation deviation changes the
  active fileless task, the assistant updates only the changed
  sections in chat for that same active task.

- **Constraints:**
  - Preserve the existing lightweight eligibility and escalation
    criteria unless a verified consistency fix is required.
  - Keep fileless work chat-only unless promotion is required.
  - Do not allow subtasks or diagrams on the fileless path.
  - If diagrams would materially help because research or design is no
    longer simple, promote the task to the task-file path.
  - Treat more than one active fileless task, or ambiguous fileless
    task identity, as a promotion trigger to the task-file path.
  - Do not lose existing normative content when splitting shared task
    semantics from task-file-only mechanics.

- **Briefing:**
  Relevant files currently span planning, implementation flow, and
  supporting documentation:
  `skills/spec-loop-plan-task/SKILL.md`,
  `skills/spec-loop-plan-task/task-file-path-guidance.md`,
  `skills/spec-loop-implementation-flow/SKILL.md`,
  `skills/spec-loop-implementation-flow/implementation-flow-guidance.md`,
  `skills/spec-loop-prepare-execution-approval/SKILL.md`,
  `README.md`, and
  `docs/review-responsibility-and-traceability.md`.
  The current constitution is task-file-only and mixes reusable
  section semantics with task-file administration.

- **Research:**
  Current planning and implementation routing:

  ```plantuml
  @startuml
  component User
  component "spec-loop-plan-task" as PlanTask
  artifact "Short chat plan" as ShortChatPlan
  artifact "Task file" as TaskFile
  component "Task-file Path Guidance" as TaskFileGuidance
  component "spec-loop-prepare-execution-approval" as PrepareApproval
  component "spec-loop-implementation-flow" as ImplementationFlow

  User --> PlanTask : request planning
  PlanTask --> ShortChatPlan : short planning path
  PlanTask --> TaskFile : task-file path
  TaskFile --> TaskFileGuidance : governed by
  TaskFile --> PrepareApproval : execution-approval readiness
  PrepareApproval --> ImplementationFlow : after task-file execution approval
  @enduml
  ```

  Verified observations:
  - `spec-loop-plan-task/SKILL.md` currently defines a short planning
    path in chat and a task-file path in task artifacts.
  - The current short planning path is allowed only on the first
    planning pass, with lightweight research, a single clear
    implementation path, lightweight verification, and no existing
    task file.
  - `task-file-path-guidance.md` explicitly applies only on the
    task-file path and combines section semantics with task-file-only
    mechanics such as folders, tracked moves, subtasks, and diagrams.
  - `spec-loop-implementation-flow/SKILL.md` currently says it is
    mandatory only on the task-file path and must not be used when
    short-path planning is in use.
  - `spec-loop-prepare-execution-approval/SKILL.md` is also
    task-file-only.
  - `README.md` and
    `docs/review-responsibility-and-traceability.md` currently describe
    the two planning paths with short-path/task-file terminology.

- **Design:**
  Target rule split and path interaction:

  ```plantuml
  @startuml
  component "spec-loop-plan-task" as PlanTask
  component "Shared task semantics" as SharedSemantics
  component "Task-file mechanics" as TaskFileMechanics
  artifact "Fileless chat task" as FilelessTask
  artifact "Task file" as TaskFile
  component "spec-loop-prepare-execution-approval" as PrepareApproval
  component "spec-loop-implementation-flow" as ImplementationFlow

  PlanTask --> SharedSemantics : plan against
  PlanTask --> FilelessTask : fileless planning path
  PlanTask --> TaskFile : task-file path
  TaskFile --> TaskFileMechanics : governed by
  TaskFile --> PrepareApproval : execution-approval readiness
  FilelessTask --> ImplementationFlow : approved implementation
  PrepareApproval --> ImplementationFlow : approved implementation
  @enduml
  ```

  ```plantuml
  @startuml
  actor User
  participant "spec-loop-plan-task" as PlanTask
  participant "Fileless chat task" as FilelessTask
  participant "spec-loop-implementation-flow" as ImplementationFlow

  User -> PlanTask : request lightweight executable change
  PlanTask -> FilelessTask : emit full task-shaped chat artifact
  PlanTask -> User : request fileless-path + execution approval
  User -> ImplementationFlow : approve and request implementation
  ImplementationFlow -> FilelessTask : update changed sections only when needed
  ImplementationFlow -> User : report implementation notes and readiness
  @enduml
  ```

  Design decisions:
  - Rename the current short planning path to the fileless planning
    path.
  - Introduce
    `skills/spec-loop-plan-task/common-task-guidance.md` as the
    shared source for the no-subtask main-task form used by both
    paths: task header, identifier rules, section order, section
    meanings, section conditionality, testing policy, and
    `Implementation notes` semantics.
  - Reorder `skills/spec-loop-plan-task/SKILL.md` so the executable
    path-routing material stays contiguous after the phase model and
    documentation routing. Use this sequence:
    `ADR and documentation routing`, `Shared task semantics`,
    `Planning paths`, explicit `Fileless path`, `Task-file path`, and
    `Glossary policy`.
  - Keep `Planning paths` focused on path selection and eligibility.
    Move fileless-path-specific operating rules from `Planning paths`
    into the explicit `Fileless path` section.
  - Reduce
    `skills/spec-loop-plan-task/task-file-path-guidance.md` to
    task-file-only mechanics plus references to the shared semantics
    source.
  - In `common-task-guidance.md`, make the readiness summary reflect
    the full required shared task form instead of starting ambiguously
    at Research. The summary should explicitly cover Scope,
    Motivation, Briefing, implementation-ready Design, Test
    specification, and any required Research, Scenario, or
    Constraints.
  - In `common-task-guidance.md`, remove wording that appears to ban
    bold-label section labels, because the shared task structure
    below requires those labels.
  - Keep subtask semantics task-file-only. The fileless path is
    limited to the shared no-subtask main-task form.
  - Limit the fileless path to a single active task in one
    conversation. Do not allow fileless subtasks, fileless diagrams,
    or fileless folder-derived statuses.
  - Require one canonical fileless task artifact before
    execution approval. Allow the initial canonical fileless
    task to include only the sections already established, while
    requiring title and identifier. During approved fileless
    implementation, later canonical chat updates may re-emit only
    the changed sections for the single active fileless task and
    rely on chat context rather than repeated title or identifier
    lines.
  - Add a compact inline fileless example directly to the fileless
    guidance. It should show both an initial canonical fileless
    task and a section-only fileless update, and it should make
    clear why the task is a good fit for the fileless path.
  - When new work appears after a fileless task, use the User's
    message or later clarification to decide whether it is a subtask
    or extension of the earlier task, or a new follow-up task. Ask
    only when that relation is not clear. If the User's message or
    later clarification shows it is a subtask or extension, use the
    task-file path because fileless work has no subtasks. If it shows
    a new follow-up task, a new fileless task may start in chat
    without creating a task file when the earlier fileless task is no
    longer active and the new task independently qualifies for the
    fileless path. Do not allow two active fileless tasks at once.
  - Canonical changed-section updates on the fileless path must use an
    explicit update marker and exact shared section labels so they are
    distinguishable from ordinary discussion.
  - Fileless canonical-section changes during implementation use the
    same authority rules as task-file work: explicit User
    clarification, accepted review feedback, or explicit
    post-implementation User approval of keeping an implemented
    deviation.
  - The assistant should remain aware that the fileless path carries
    real context-compaction and alignment-loss risk. That risk is
    acceptable only for simple work and is one reason to promote when
    reconstruction confidence drops.
  - When proposing the fileless path to a User who may not already be
    informed, the assistant should include one brief inline note that
    fileless avoids task-file overhead for simple tasks but carries
    higher chat-alignment risk and may require full task re-emission
    or promotion if confidence drops. Do not repeat that note when the
    assistant is confident the User is already informed unless the
    risk basis materially changes.
  - If the assistant cannot confidently reconstruct the current
    canonical fileless task, it must first re-emit a fresh full
    current task in chat with title, identifier, and all current
    sections before continuing implementation or promoting to the
    task-file path.
  - Promotion from fileless to task-file reconstructs the task file
    from the current canonical fileless task state and then continues
    under normal task-file rules.
  - Refactor `spec-loop-implementation-flow` into a shared
    implementation-flow core plus fileless-path and task-file-path
    companions. The skill entry point should detect the active path,
    read the shared core, and then read exactly one path companion.
    Shared implementation-flow rules should own route semantics,
    canonical-section authority, common `review` meaning,
    `Implementation notes` semantics, and the full semantic
    completion checklist. Path companions should own only path-
    specific expression of those rules.
  - Keep `spec-loop-prepare-execution-approval` task-file-only.
    Fileless approval stays in chat under `spec-loop-plan-task`.
  - Audit existing constitution content by section so every current
    normative rule is either preserved in the shared semantics source,
    preserved in the task-file-only source, delegated to
    `spec-loop-implementation-flow`, or intentionally deleted with an
    explicit reason. The audit must at least cover:
    - task-file path readiness;
    - Research, Scenario, Design, Test specification, and
      `Implementation notes` semantics;
    - task administration and task states;
    - task structure and subtask rules;
    - diagram rules;
    - testing policy;
    - formatting and context-preservation rules.

- **Test specification:**
  - **Manual tests:**
    - Compare the current `task-file-path-guidance.md` against the new
      shared/task-file split and verify that no normative section
      content is lost.
    - Run a lightweight fileless planning prompt and verify that the
      assistant emits one full task-shaped chat artifact with no
      diagrams.
    - During a follow-up clarification on that same fileless task,
      verify that the assistant uses an explicit update marker and
      updates only the changed sections in chat.
    - Verify that the inline fileless example stays concise and
      clearly shows why its task fits the fileless path.
    - Verify that when new work appears after a fileless task, the
      assistant infers from the User's message whether it is a subtask
      or extension of the earlier task, or a new follow-up task, and
      asks only when that relation is not clear.
    - Verify that fileless guidance allows a new follow-up fileless
      task only when the earlier fileless task is no longer active and
      the new task independently still fits the fileless criteria.
    - Verify that `skills/spec-loop-plan-task/SKILL.md` exposes an
      explicit `Fileless path` section and that fileless operating
      rules no longer live only inside `Planning paths`.
    - Verify that fileless tasks use the same shared section
      conditionality as the task-file path instead of placeholder-only
      fileless rules.
    - Verify that the common-task-guidance readiness summary covers
      the full required shared task form instead of implying that the
      required content starts only at Research.
    - Verify that the common-task-guidance task-structure wording no
      longer conflicts with the required bold-label section format.
    - Run a task that needs diagrams, subtasks, or a second active
      task and verify promotion to the task-file path.
    - Simulate lost reconstruction confidence and verify that the
      assistant re-emits the full current fileless task before
      continuing or promoting.
    - Promote a fileless task and verify that the created task file is
      reconstructed from the current canonical fileless state.
    - Verify that a User who may not already be informed receives one
      brief inline fileless-risk note with the approval request, and
      that the note is not repeated once the assistant is confident
      the User is already informed unless the risk basis changes.
    - Run a fileless task through implementation and verify that
      `spec-loop-implementation-flow` handles canonical-section
      authority, `Implementation notes`, and readiness without a task
      file.

## Subtask: Split implementation-flow guidance into shared and path-specific companions
- **Status:** review
- **Scope:**
  Rework `spec-loop-implementation-flow` so implementation-time rules
  are separated into one shared governance core plus one fileless-path
  companion and one task-file-path companion.
- **Motivation:**
  The current implementation-flow guidance has become materially
  larger and harder to read because shared rules and both path
  mechanics are mixed in the same file.
- **Constraints:**
  - Keep one entry-point skill:
    `skills/spec-loop-implementation-flow/SKILL.md`.
  - Preserve one shared meaning of `review` on both paths.
  - Keep shared governance in the core and path-specific expression in
    the companions.
  - Keep task-file lifecycle, folder, and subtask ownership in
    `task-file-path-guidance.md`.
- **Briefing:**
  Main files for this subtask are:
  `skills/spec-loop-implementation-flow/SKILL.md`,
  `skills/spec-loop-implementation-flow/implementation-flow-guidance.md`,
  and the new companion guidance files to be introduced in the same
  directory.
- **Research:**
  The current implementation-flow guidance already separates some
  path-specific rules in prose, but the main routing, completion, and
  canonical-update sections still mix shared semantics with fileless
  and task-file mechanics.
- **Design:**
  - Keep
    `skills/spec-loop-implementation-flow/implementation-flow-guidance.md`
    as the shared implementation-flow core.
  - Add
    `skills/spec-loop-implementation-flow/fileless-path-guidance.md`
    for fileless-path implementation-time mechanics.
  - Add
    `skills/spec-loop-implementation-flow/task-file-path-guidance.md`
    for task-file-path implementation-time mechanics.
  - Update `skills/spec-loop-implementation-flow/SKILL.md` so it
    detects the active path, reads the shared core, and then reads
    exactly one path companion.
  - Keep the shared core artifact-agnostic. It should define shared
    implementation-time route semantics, canonical-section authority,
    post-implementation deviation handling, the shared meaning of
    `review`, `Implementation notes` semantics, and the full semantic
    completion checklist.
  - Define the shared implementation-time routes once in the core:
    continue implementation, ask targeted User questions, return to
    PLAN, seek post-implementation approval of an implemented
    deviation, and reach `review`. Path companions should define only
    the concrete path-specific actions for those same routes.
  - Keep one shared meaning of `review` on both paths. The fileless
    companion should express that review state by canonical chat
    updates and review-ready handoff in chat, while the task-file
    companion should express the same review state through the
    task-file update mechanics and the additional folder or subtask
    status move governed by `task-file-path-guidance.md`.
  - Keep the fileless companion implementation-focused but allow a
    short orientation preface with only the minimum fileless facts
    needed to interpret the path.
  - Keep the task-file companion limited to the implementation-time
    delta for task files. It should rely on
    `task-file-path-guidance.md` for task-file lifecycle, subtask, and
    folder rules.
- **Test specification:**
  - **Manual tests:**
    - Verify that the implementation-flow core no longer mixes
      fileless and task-file mechanics in its main sections.
    - Verify that the fileless companion contains `Fileless task
      update:` mechanics, recovery re-emission, promotion handling,
      and fileless review-state expression.
    - Verify that the task-file companion contains only the
      implementation-time task-file delta and defers lifecycle and
      subtask ownership to `task-file-path-guidance.md`.
    - Verify that `skills/spec-loop-implementation-flow/SKILL.md`
      reads the shared core and exactly one path companion based on
      the active path.
- **Implementation notes:**
  - **Interpretations:**
    - Treated the task's prompt-level manual checks as documentation
      audits against the changed skill files because this repository
      provides no local harness for end-to-end skill execution.
  - **Tradeoffs:**
    - Split implementation-flow into one shared core plus two path
      companions so `review`, canonical-section authority, and
      `Implementation notes` stay governed once while fileless and
      task-file mechanics stay separate.
    - Moved the concise fileless example into `spec-loop-plan-task`
      guidance instead of keeping a separate example file so fit,
      concision, and update shape are visible at the point of use.
