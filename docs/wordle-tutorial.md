# Wordle Tutorial: You Send, You See

## Bootstrap

### B1. Create an empty `wordle-tutorial-project`

Run this from a workspace directory of your choice:

```bash
mkdir -p wordle-tutorial-project
cd wordle-tutorial-project
git init
```

### B2. Install `install-spec-loop`

```bash
npx skills add dpolivaev/spec-loop
```

This recommended path requires Node.js because it uses `npx`.
Add `-g` if you prefer global installation.

### B3. Open the project

Open `wordle-tutorial-project` in your coding tool.

### B4. Select the model explicitly

For this tutorial, select the model explicitly instead of relying on
automatic model choice. With an unknown model, poor instruction
following is more likely.

Continue with Step 1 from the `wordle-tutorial-project` root. Send the
tutorial prompts from there unless a later step says otherwise.

## Step 1: Install Spec Loop into the tutorial project

### You send

```text
I am following the Spec Loop Wordle tutorial from my browser.
Please use `install-spec-loop` skill to install Spec Loop governance and
workflow support for this project.

Tutorial-specific goals:
- install glossary support because later tutorial steps will create and
  maintain `glossary.adoc`,
- browser automation setup is not needed for this tutorial,
- after setup, tell me which Spec Loop setup is active and restate the
  `PLAN -> IMPLEMENTATION` approval rule in one sentence,
- after setup, summarize the chosen configuration and create an initial
  commit for the setup files that now belong to this project.
```

### You see

- Chat:
  - first asks whether a `spec-loop` checkout already exists,
  - does not try to search your whole machine for it,
  - recommends skills over fallback instructions unless the harness
    makes that impractical,
  - recommends linked mode for a fresh install,
  - shows a concrete plan before changing files or config.
- If `spec-loop` already exists:
  - the LLM asks for the exact path,
  - it suggests updating that checkout before using it.
- If `spec-loop` does not exist:
  - the LLM asks where to clone it,
  - the clone target is outside `wordle-tutorial-project`.
- Project setup:
  - Spec Loop governance is installed through `install-spec-loop`,
  - `tasks/` is suggested and then confirmed or replaced by your
    preferred task directory,
  - glossary support is installed so later steps can create and
    maintain `glossary.adoc`.
- Tooling:
  - PlantUML is recommended unless there is a good reason to choose
    Mermaid,
  - PlantUML support is configured unless you opted out,
  - AsciiDoc support is configured because glossary workflow is active,
  - browser automation setup is not required for this tutorial.
- Verification:
  - the LLM shows planned file/config changes before applying them,
  - the LLM confirms which installed governance path is now active,
  - the LLM correctly restates the `PLAN -> IMPLEMENTATION` approval
    rule,
  - an initial setup/governance commit is created for this project.

### You learned (this step)

- Setup is intentionally LLM-guided.
- You and the LLM make setup decisions together instead of manually
  copying governance files and editing instructions by hand.
- The tutorial may be open in your browser while the LLM only sees the
  `wordle-tutorial-project`, so prompts must carry the setup context the LLM
  needs.
- This tutorial does not need browser automation setup.

## If setup seems wrong

1. Ask the LLM which Spec Loop setup is currently active.
2. Ask it to restate the `PLAN -> IMPLEMENTATION` approval rule.
3. If the answer still looks wrong, ask it to run `install-spec-loop`
   again and repair the setup.
4. If that does not help, reinstall the skill with:

```bash
npx skills add dpolivaev/spec-loop
```

5. If `npx` is not available or does not help, ask the LLM to install
   from `https://github.com/dpolivaev/spec-loop/tree/main/skills` if
   your harness supports that.
6. As a last resort, copy the needed part of the `skills/` directory
   from the `spec-loop` repository into the harness-specific skills
   directory.
7. Continue only when the LLM clearly understands the setup and the
   workflow rules.

## From here on

- each `You send` block is a prompt to adapt and send,
- each `You see` block describes the expected outcome,
- if you want to finish the tutorial in minimum time, send the next
  prompt first and then read it and think about it while the LLM works,
  because the LLM also needs time to act and respond,
- validate progress from the LLM's chat output and the changed files
  before continuing,
- if the LLM misses a required setup, governance, glossary, or status
  update, ask it to fix that before continuing,
- if the setup or workflow rules seem wrong, use the recovery steps
  above before continuing.

## Possible misalignment

If one of these happens, interrupt the flow and ask the LLM to correct
it before continuing:

- it starts changing files or config before showing the plan and
  getting approval,
- it cannot clearly explain which Spec Loop setup is active or restate
  the `PLAN -> IMPLEMENTATION` approval rule,
- it ignores the installed governance,
- it starts implementation before explicit approval,
- unrelated changes are mixed into one subtask,
- implementation changes are made without verification evidence,
- it misses required supporting updates such as glossary, task status,
  or ignore rules,
- what it reports in chat does not match the actual changed files,
- a task or subtask is moved to `done` without explicit user
  confirmation.

## Step 2: Project README ([README.md](../README.md))

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

Also update the active project governance entry point so it
explicitly tells the LLM to read `README.md` and follow the
"Project Brief" section there for project requirements unless I
explicitly override it.

This is documentation-only work, we do not need a task file for it.
```

### You see

- [README.md](../README.md):
  - exists and captures the project brief requirements,
  - includes the project brief text under `Project Brief`.
- `glossary.adoc`:
  - exists and defines the canonical project terms from the brief,
  - uses wording consistent with the brief so later tasks can reuse it.
- Project governance entry point:
  - explicitly points the LLM to [README.md](../README.md) as the
    source of the project brief and requirements.

### After completion (commit)

- After you accept this work item as done:
  ask the LLM to `commit the README, glossary.adoc, and governance-entry changes`.

### You learned (this step)

- The LLM can create documentation, wire persistent instructions to the
  canonical project brief, and establish `glossary.adoc` as the project
  vocabulary without creating a task file.

## Step 3: Gradle Java project setup

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

## Step 4: Wordle domain model and evaluation rules

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

## Step 5: Word list loader and validation

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

## Step 6: Game engine

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

## Step 7: AssertJ test migration

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

## Step 8: ADR for CLI argument parsing

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

## Step 9: CLI game interface

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

## Step 10: Minimal Swing UI

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
