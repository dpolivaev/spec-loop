# Wordle Tutorial: You Send, You See

This tutorial is intentionally compact and execution-focused.
It uses the same staged interaction style as the main tutorial, but it
targets a small Java Wordle project.

It assumes the current Constitution rules are in effect:

- work starts in PLAN,
- executable changes need explicit `PLAN -> IMPLEMENTATION` approval,
- `glossary.adoc` becomes part of the workflow once it exists,
- larger tasks should start with lightweight future subtasks,
- only the current subtask should be fully designed before implementation,
- moving work to `done` still requires explicit user confirmation.

This path uses one focused ADR before CLI implementation.
The rest of the work can stay task-based unless a later design choice
should outlive a single task.

- Read [README.md](../README.md) first.
- Then quickly check [CONSTITUTION.md](../CONSTITUTION.md) and
  [docs/review-responsibility-and-traceability.md](review-responsibility-and-traceability.md).
- Then run this tutorial.
- For every step, validate progress from AI output in chat.
- Send all LLM messages from your project root directory.
- If you use Claude, save project instructions as `CLAUDE.md`.
  You can use [AGENTS.md](../AGENTS.md) content as the preamble and add
  any other guidance needed for your project.

The setup recommendations below do not all have the same weight:

- PlantUML is the default where the Constitution requires diagrams.
- Mermaid is a poorer but possible alternative.
- `glossary.adoc` is used throughout this tutorial.
- Browser automation is not needed for this path.

## Step 0: Setup (manual) + Constitution sanity ping

Do this yourself before sending your first substantive request to the
LLM:

1. Read [README.md](../README.md) first, then this tutorial.
   - Before this tutorial, quickly check
     [CONSTITUTION.md](../CONSTITUTION.md) and
     [docs/review-responsibility-and-traceability.md](review-responsibility-and-traceability.md).
2. Create your own project repository.
3. Set up governance files and a concrete task directory path:
   - Preferred: keep [CONSTITUTION.md](../CONSTITUTION.md) in a stable
     shared or central location outside your project.
   - Keep [glossary-skill.md](../glossary-skill.md) in the same
     directory as [CONSTITUTION.md](../CONSTITUTION.md), regardless of
     whether the current project uses a glossary.
   - If the governance files live outside the project, reference the
     shared `CONSTITUTION.md` from your instruction file by absolute
     path instead of copying the files into the repository.
   - Fallback: if a shared location is not practical, copy these files
     into your project instead.
   - Then copy the instruction file your LLM tool uses:
     - most tools: [AGENTS.md](../AGENTS.md)
     - GitHub Copilot:
       [.github/copilot-instructions.md](../.github/copilot-instructions.md)
     - Claude Code: save [AGENTS.md](../AGENTS.md) content as
       `CLAUDE.md` (or keep both if useful)
   - In the instruction file(s), replace `<TASK_DIR>` with a real path
     such as `tasks`.
4. Run the command block below to create `wordle-project`.

```bash
mkdir -p ~/git-repo/ai
cd ~/git-repo/ai
mkdir -p wordle-project
cd wordle-project
git init
mkdir -p tasks/in-progress tasks/backlog tasks/done
mkdir -p architecture-decisions docs
```

5. Configure PlantUML preview in your editor using:
   - [docs/vscode-setup.md](vscode-setup.md), or
   - [docs/jetbrains-setup.md](jetbrains-setup.md).

   PlantUML is the default diagram language for this tutorial.
   Mermaid is a poorer but possible fallback when the User or another
   governing instruction explicitly prefers Mermaid (for example when
   GitHub or similar environments are used and PlantUML is not
   rendered).

6. This tutorial uses `glossary.adoc` throughout, so also configure
   AsciiDoc support in your editor or IDE.
   See:
   - [docs/vscode-setup.md](vscode-setup.md), or
   - [docs/jetbrains-setup.md](jetbrains-setup.md).

7. Constitution check + initial governance commit:
   - Ensure your LLM tool can read the shared
     [CONSTITUTION.md](../CONSTITUTION.md) location
     (or the project copy if you use the fallback setup).
   - Keep [glossary-skill.md](../glossary-skill.md) in the same
     directory as [CONSTITUTION.md](../CONSTITUTION.md) so projects
     that use `glossary.adoc` can consult it later.
   - Send this now as your first LLM message
     (before any other request):

```text
Before we start: tell me which instruction/governance files you have
already read for this repository (filenames if known). Then restate the
PLAN -> IMPLEMENTATION approval gate in one sentence.

Then create an initial commit containing the instruction/governance
files already present in this project, and any copied governance files
that belong to this repository setup.
```

   - On the first LLM response, you should see a leading `🫡` without
     asking for it.
   - If you do not see it, or the tool reports
     [CONSTITUTION.md](../CONSTITUTION.md) is unavailable or unreadable,
     stop and fix file access or instruction loading before proceeding.
   - You should also see an initial commit that includes the relevant
     governance files for your tool setup.

----------

Each following tutorial step uses the same structure:

- `You send` shows a suitable message to send to the LLM.
  Any equivalent wording is fine.
- This tutorial assumes `glossary.adoc` is created in Step 1 and then
  maintained throughout the later steps. Once it exists, the LLM
  should treat it as ordinary project context rather than needing that
  reminder in every later prompt.
- Governance and workflow gates from the copied [AGENTS.md](../AGENTS.md)
  and [CONSTITUTION.md](../CONSTITUTION.md) are expected to be loaded by
  your LLM tool automatically.
- The Constitution, not repeated prompt wording, defines approval and
  implementation boundaries.
- Before implementation, you can always ask the LLM to revise the
  current task, subtask, or design instead of proceeding directly.
- `You see` describes the expected outcome and typical artifacts for
  each step.
- If implementation changes or clarifies shared domain language,
  `You see` should also include the corresponding `glossary.adoc`
  update.
- The LLM should usually try to keep related artifacts in sync on its
  own, but sometimes it will miss a supporting update such as a glossary
  change or a status move. When that happens, ask it to correct the
  omission before you accept the step.
- `After completion` describes move-to-done and commit expectations.
- `You learned (this step)` is the takeaway after the step is done.

If the LLM deviates, decide whether the deviation is acceptable.
If it matters to you, ask the LLM to adjust and re-verify until the
step matches what you consider important.
This includes small but important omissions such as missing glossary
entries, missing task status changes, or missing documentation follow-up.

If you suspect the task structure or section order is wrong, ask the
LLM to check the task or subtask against
[CONSTITUTION.md](../CONSTITUTION.md) before proceeding.

## Possible misalignment

If one of these happens, interrupt the flow and ask the LLM to correct
it before you continue:

- Implementation starts before explicit approval.
- Future subtasks are fully designed before they become current.
- Implementation changes are made without verification evidence.
- A supporting artifact such as `glossary.adoc` or task status was not
  updated when the change clearly requires it.
- A task or subtask is moved to `done` without explicit user
  confirmation.

## Step 1: Project README ([README.md](../README.md))

### You send

```text
Project brief:

We are building a small Java implementation of Wordle.

Gameplay rules:
- the system selects one hidden five-letter solution word
- the player submits five-letter guesses
- each guessed letter produces feedback:
  - `=` correct letter in the correct position
  - `~` correct letter in the wrong position
  - `.` letter not present in the solution
- duplicate letters must be evaluated deterministically
- the player has a limited number of attempts; default 6

Interaction modes:
- CLI mode is required
- later, add a minimal Swing UI that reuses the same core logic

Word list rules:
- keep an internal packaged word list
- later, allow overriding the word list source with a file path or URL

Technical direction:
- use Java with Gradle
- keep gameplay rules in explicit domain classes that are not tied to
  the UI

Please write `README.md` for this repository based on the project brief.
Include the project brief verbatim in the README under a "Project Brief"
section. The README must clearly describe the game rules, the later CLI
and Swing paths, and the word-list expectations. Keep the README concise
and practical.

Also create `glossary.adoc` from the approved project brief. It should
define the canonical project terms needed for this tutorial and keep
their wording consistent with the brief.

Also update `AGENTS.md` so it explicitly tells the LLM to read
`README.md` and follow the "Project Brief" section there for project
requirements unless I explicitly override it.

This is documentation-only work, we do not need a task file for it.
```

### You see

- [README.md](../README.md):
  - exists and captures the project brief requirements,
  - includes the project brief text under `Project Brief`.
- `glossary.adoc`:
  - exists and defines the canonical project terms from the brief,
  - uses wording consistent with the brief so later tasks can reuse it.
- [AGENTS.md](../AGENTS.md):
  - explicitly points the LLM to [README.md](../README.md) as the
    source of the project brief and requirements.

### After completion (commit)

- After you accept this work item as done:
  ask the LLM to `commit the README, glossary.adoc, and AGENTS.md changes`.

### You learned (this step)

- The LLM can create documentation, wire persistent instructions to the
  canonical project brief, and establish `glossary.adoc` as the project
  vocabulary without creating a task file.

## Step 2: Gradle Java project setup

### You send

```text
Please create a task for the initial Gradle Java project setup in this
repository.

The scope must include:
- a single-module Gradle project,
- Gradle wrapper files,
- Kotlin DSL build scripts,
- Java 21 toolchain configuration,
- application plugin wiring,
- standard `src/main/java`, `src/test/java`, and `src/main/resources`
  layout,
- just enough code to prove the application can build, test, and run.

Record just-enough research directly inside the task `Research`
section, including the chosen Gradle wrapper version and any relevant
build/test wiring decisions.

Include a design diagram (PlantUML by default; Mermaid only when
explicitly preferred), concrete automated tests, and the exact build
and test commands you plan to run during verification.
```

### You see (plan)

- Chat: reports that a task file was created and asks for explicit
  implementation approval.
- Task file:
  - contains Scope, Motivation, Briefing, Research, Design, and Test
    specification,
  - records the chosen Gradle wrapper version in Research,
  - includes a build-layout diagram (PlantUML by default; Mermaid
    only when explicitly preferred).

Approve only after the task definition looks correct.
Then ask the LLM to `implement it`.

### You see (after implementation is completed)

- Build files exist and load as planned.
- The project has wrapper scripts, Kotlin DSL build files, and the
  standard source layout.
- Chat reports the exact verification commands and the result.

### After completion (move to done / commit)

- After you accept this work item as done:
  ask the LLM to `move the task to done`, then ask it to commit.

### You learned (this step)

- Initial build setup is still task-based work:
  it is planned first, then implemented after explicit approval.

## Step 3: Wordle domain model and evaluation rules

### You send

```text
Please create one task for the Wordle domain model and evaluation rules
in this repository.

The scope must include:
- UI-agnostic domain objects for words and feedback,
- deterministic duplicate-aware letter evaluation,
- immutable model boundaries suitable for later engine and interface
  work.

For the initial task creation, do not fully design every future
subtask. Create only:
- the overall task,
- subtasks containing Scope and Motivation each.

Use these subtasks:
1. define domain objects
2. implement guess evaluation rules
```

### You see (plan)

- Chat: reports that a task file was created with a task header and an
  ordered subtask breakdown, then stops for review.
- Task file:
  - has clear overall Scope, Motivation, and Scenario,
  - keeps future subtasks lightweight,
  - uses `glossary.adoc` terms consistently.

### Subtask-by-subtask workflow

- Review the task header and the task breakdown first.
- If the breakdown needs adjustment, ask the LLM to revise it before
  any implementation starts.
- If it looks good, ask the LLM to
  `completely design only the first subtask`.
- Review that current-subtask detail.
  If it looks good, ask the LLM to `implement only that subtask`.
- After each implemented subtask, either ask for changes or accept it
  and ask the LLM to `move it to done`.
- Then ask it to `create a separate commit` and only after that ask it
  to `design the next subtask`.

### You see (current subtask design)

- Chat: designs only the current subtask and asks for explicit
  implementation approval.
- Task file:
  - the current subtask includes Research, Design, and Test
    specification,
  - future subtasks remain lightweight,
  - the current subtask uses glossary terms consistently.

### You see (during subtask implementation)

- Chat: implements only the approved current subtask and stops.
- Tests: separate verification evidence is provided per implemented
  subtask.
- Git: there is a separate commit per accepted subtask.
- `glossary.adoc`: expands to cover shared gameplay terms and links
  those terms to the implemented code.

### After completion (move to done / commit)

- After you accept the first subtask as done:
  ask the LLM to move that subtask to `done`, then commit.
- After you accept the second subtask as done:
  ask the LLM to move that subtask and the overall task to `done`,
  then commit.

### You learned (this step)

- Keep future subtasks lightweight until you reach them:
  review the current subtask in detail, implement it, verify it,
  commit it, then move on.

## Step 4: Word list loader and validation

### You send

```text
Please create one task for the internal word list loader and validation.

The scope must include:
- a packaged `wordlist.txt` resource,
- a loader that reads the declared count header from the file,
- random selection of one candidate entry from the declared list,
- conversion of the selected value into the existing validated word
  type,
- no separate dictionary-membership checks beyond loading and existing
  validation.

Record the file-format rules directly in the task Research section and
include the exact automated checks needed to verify the loader.
```

### You see (plan)

- Chat: reports that a task file was created and asks for explicit
  implementation approval.
- Task file:
  - documents the word-list file format in Research,
  - includes a loader-to-resource flow diagram (PlantUML by default;
    Mermaid only when explicitly preferred),
  - defines concrete automated tests for loader behavior.

Approve only after the task definition looks correct.
Then ask the LLM to `implement it`.

### You see (after implementation is completed)

- `src/main/resources/wordlist.txt` exists.
- Loader code exists and returns validated words from the packaged list.
- Tests prove header parsing, normalization, and selection behavior.
- If the loader work stabilizes a shared term such as `Word List` and
  the glossary was not updated, ask the LLM to add that missing glossary
  update before accepting the step.

### After completion (move to done / commit)

- After you accept this work item as done:
  ask the LLM to `move the task to done`, then ask it to commit.

### You learned (this step)

- Infrastructure-facing work such as resource loading still benefits
  from explicit file-format research and testable design.

## Step 5: Game engine

### You send

```text
Starting point: build on the relevant research already recorded in this
repository.

Please create one task for the game engine in this repository.

The scope must include:
- immutable game state,
- explicit game status values,
- attempt limits,
- feedback history,
- game start logic,
- guess submission logic,
- win and lose termination behavior.

For the initial task creation, do not fully design every future
subtask. Create only:
- the overall task,
- subtasks containing Scope and Motivation each.

Use these subtasks:
1. define game state model
2. implement game engine logic
```

### You see (plan)

- Chat: reports that a task file was created with a task header and an
  ordered subtask breakdown, then stops for review.
- Task file:
  - keeps future subtasks lightweight,
  - aligns with existing glossary terms,
  - clearly separates state modeling from engine behavior.

### Subtask-by-subtask workflow

- Review the task header and breakdown first.
- If it looks good, ask the LLM to
  `completely design only the first subtask`.
- Review that design and, if acceptable, ask it to
  `implement only that subtask`.
- Accept, move to `done`, and commit before asking for the next
  subtask design.

### You see (during subtask implementation)

- State-model work and engine-behavior work are implemented in separate
  accepted increments.
- Tests prove start state, guess progression, attempt decrement, and
  win/lose transitions.
- `glossary.adoc` stays aligned with `Game`, `Game Engine`,
  `Game State`, and `Game Status` terminology.

### After completion (move to done / commit)

- After you accept the first subtask as done:
  ask the LLM to move that subtask to `done`, then commit.
- After you accept the second subtask as done:
  ask the LLM to move that subtask and the overall task to `done`,
  then commit.

### You learned (this step)

- Separate the stable state shape from the state-transition behavior:
  it keeps the engine reviewable and the test coverage focused.

## Step 6: AssertJ test migration

### You send

```text
Please create one task to migrate the existing tests in this repository
to AssertJ and add the required dependency.

The scope must include:
- replacing JUnit assertion helpers with AssertJ,
- updating build configuration as needed,
- keeping existing production APIs unchanged,
- verifying that the full test suite still passes.
```

### You see (plan)

- Chat: reports that a task file was created and asks for explicit
  implementation approval.
- Task file:
  - keeps scope limited to test sources and test dependency
    configuration,
  - includes concrete verification for the full test suite.

Approve only after the task definition looks correct.
Then ask the LLM to `implement it`.

### You see (after implementation is completed)

- Test code uses AssertJ consistently.
- Build configuration includes the AssertJ dependency.
- Chat reports the exact test command and the passing result.

### After completion (move to done / commit)

- After you accept this work item as done:
  ask the LLM to `move the task to done`, then ask it to commit.

### You learned (this step)

- Technical cleanup that changes build configuration and tests is still
  implementation work and still needs a task, verification, and review.

## Step 7: ADR for CLI argument parsing

### You send

```text
Please create one ADR for CLI argument parsing in
`architecture-decisions/`.

First discuss the criteria with me.
The CLI must support:
- `--wordlist` for file path or URL input,
- `--attempts` with default value 6,
- `--cli` for explicit terminal mode,
- standard help output.

Then compare realistic options for argument parsing, including:
- manual parsing without a library,
- using a CLI parsing library.

Record one final choice with rationale.
The ADR should explain why the chosen approach is a good fit for a
small project now and for modest CLI growth later.
Also record the practical verification command for checking the CLI help
or basic option parsing path.
```

### You see

- Chat: discusses the criteria before presenting the final ADR.
- ADR:
  - compares realistic options,
  - records the chosen parsing approach with rationale,
  - explains the tradeoff between small-project simplicity and future
    CLI growth,
  - records a practical verification command for the parsing path.

### After completion (commit)

- After you accept the ADR as done:
  ask the LLM to `commit the ADR change`.

### You learned (this step)

- ADRs are useful for durable tooling or design choices that should not
  be rediscovered inside a later implementation task.

## Step 8: CLI game interface

### You send

```text
Starting point: build on the existing gameplay logic in this repository
and follow the approved CLI argument parsing ADR.

Please create one task for the CLI game interface in this repository.

The CLI requirements are:
- interactive terminal play,
- `--wordlist` to accept a file path or URL,
- `--attempts` with default value 6,
- `--cli` to force terminal mode later when a UI also exists,
- deterministic textual feedback rendering.

Break the implementation work down in this order:
1. implement CLI parsing and game loop
2. implement feedback rendering
3. document CLI build and usage
4. document application distribution packaging

For the initial task creation, do not fully design every future
subtask. Create only:
- the overall task,
- subtasks containing Scope and Motivation each.

```

### You see (plan)

- Chat: reports that a task file was created with a task header and an
  ordered subtask breakdown, then stops for review.
- Task file:
  - uses an ordered subtask flow,
  - keeps future subtasks lightweight,
  - treats the documentation subtasks as part of the same accepted
    delivery path.

### Subtask-by-subtask workflow

- Review the overall task and ordered subtasks first.
- Ask the LLM to `completely design only the first subtask`.
- Review that current-subtask design.
  If it looks correct, ask the LLM to `implement only that subtask`.
- After each accepted subtask, ask the LLM to move it to `done`, then
  create a separate commit before moving to the next subtask.

### You see (during subtask implementation)

- CLI parsing and the interactive loop are delivered first.
- Feedback rendering is delivered as a separate increment with exact
  output tests.
- README usage and distribution packaging docs are delivered as later
  accepted subtasks.
- Chat reports exact manual and automated verification commands for the
  CLI path.

### After completion (move to done / commit)

- After each accepted subtask:
  ask the LLM to move that subtask to `done`, then commit.
- After you accept the final subtask as done:
  ask the LLM to move the overall task to `done`, then commit.

### You learned (this step)

- Even when one feature spans runtime behavior and documentation,
  keeping the increments ordered and separately accepted preserves
  reviewability.

## Step 9: Minimal Swing UI

### You send

```text
Starting point: build on the existing gameplay logic in this
repository.

Please create one task for a minimal Swing UI in this repository.

Requirements:
- keep CLI availability,
- when a display is available and `--cli` is not set, the application
  should start the UI,
- in headless mode or when `--cli` is set, the application should use
  the CLI path,
- the UI should reuse existing gameplay logic instead of duplicating it.

Break the implementation work down in this order:
1. prepare shared input validation for CLI and UI
2. implement the minimal Swing UI
3. document UI build and usage

For the initial task creation, do not fully design every future
subtask. Create only:
- the overall task,
- subtasks containing Scope and Motivation each.

```

### You see (plan)

- Chat: reports that a task file was created with a task header and an
  ordered subtask breakdown, then stops for review.
- Task file:
  - keeps future subtasks lightweight,
  - makes the CLI/UI boundary explicit,
  - uses glossary terms consistently.

### Subtask-by-subtask workflow

- Review the task header and the breakdown first.
- Ask the LLM to `completely design only the first subtask`.
- Review that design and, if acceptable, ask it to
  `implement only that subtask`.
- After each accepted subtask, ask the LLM to move it to `done`, then
  create a separate commit before moving on.

### You see (during subtask implementation)

- Shared input validation lands before the UI itself.
- Swing UI behavior is delivered as a separate accepted increment.
- README UI usage updates land as the final subtask.
- Chat reports exact verification commands for UI launch, CLI override,
  and headless fallback behavior.

### After completion (move to done / commit)

- After each accepted subtask:
  ask the LLM to move that subtask to `done`, then commit.
- After you accept the final subtask as done:
  ask the LLM to move the overall task to `done`, then commit.

### You learned (this step)

- The interface layer can stay small and reviewable when shared
  validation and engine behavior are separated first.

## You learned

Each step follows the Constitution interaction model:

- In chat, you ask the LLM to create a task or perform approved
  documentation work, or to create an ADR when a durable design choice
  needs to be recorded.
- First, the LLM writes the task content needed for the current review
  step.
- For larger tasks, start with the task header and an ordered subtask
  breakdown, then flesh out Research, Scenario, Constraints when
  needed, Design, and Test specification only for the current subtask
  before implementation.
- You approve or reject implementation explicitly.
- Only after explicit approval should the LLM make executable changes.
- Tasks should include automated tests for their deliverables.
- Every implementation subtask includes both implementation and
  testing.
- When subtasks exist, require separate status updates per subtask.
- If `glossary.adoc` exists, later planning and implementation must
  keep it aligned with the approved shared terms.
- Use ADRs for durable decisions such as the CLI parsing approach,
  then make later tasks follow that decision.
- After you explicitly accept a work item as `done`, ask the LLM to
  commit before moving on.

Learning outcomes:

- Keep task and subtask scopes small and reviewable.
- Use ADRs for durable decisions and tasks for incremental delivery.
- Use the glossary as the stable shared language across the project.
- Verify behavior using concrete evidence, not assumptions.

How to think while running this tutorial:

- Keep the process meaningful, not bureaucratic.
- Chat is for coordination and approvals; task files and the glossary
  are the durable specification artifacts.
- A good interaction feel is that the LLM tries to keep track of the
  surrounding artifacts for you, while you still notice and correct the
  occasional missed update.
- Only the user may relax or override Constitution workflow rules.
