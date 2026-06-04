# Wordle Tutorial: You Send, You See

## Bootstrap

### B1. Create an empty `wordle-tutorial-project`

Run this from a workspace directory of your choice:

```bash
mkdir -p wordle-tutorial-project
cd wordle-tutorial-project
git init
```

### B2. Install the Spec Loop skills

```bash
npx skills add dpolivaev/spec-loop -s '*'
```

This recommended path requires Node.js because it uses `npx`.
For global installation for all agents, use:

```bash
npx skills add dpolivaev/spec-loop -g --all
```

`--all` installs all skills for all supported agents. For other
installation variants, see https://github.com/vercel-labs/skills.

### B3. Open the project

Open `wordle-tutorial-project` in your coding tool.

### B4. Select the model explicitly

For this tutorial, select the model explicitly instead of relying on
automatic model choice. With an unknown model, poor instruction
following is more likely.

Continue with Step 1 from the `wordle-tutorial-project` root. Send the
tutorial prompts from there unless a later step says otherwise.

### B5. Prepare task and glossary rendering in your editor

Run this step unless you already know your editor is prepared to render:

- Markdown task files with embedded PlantUML
- AsciiDoc glossary files with embedded diagrams

If you review in VS Code, Cursor, or another VS Code-based IDE and
want to run the helper script directly instead of using the skill, use
the instructions in [README.md: Prepare task and glossary
rendering](../README.md#prepare-task-and-glossary-rendering). Then skip
the `You send` prompt below. Use [Verification](#verification) to
confirm the expected editor state.

If you do not want to use the skill, use these editor-specific
references instead:
[VS Code-Based IDE Setup](../skills/spec-loop-setup-doc-rendering/vscode-setup.md)
and
[JetBrains Setup Reference](../skills/spec-loop-setup-doc-rendering/jetbrains-setup.md).

### You send

```text
Please use the `spec-loop-setup-doc-rendering` skill to help me
prepare my editor for reviewing rendered Spec Loop task files and
glossary files.

My coding tool may run in a terminal, but I review files in
<VS Code, Cursor, another VS Code-based IDE, or JetBrains>.
```

### You see

- uses the [spec-loop-setup-doc-rendering](../skills/spec-loop-setup-doc-rendering/) skill,
- reads the setup document for your editor,
- guides you through the rendering setup needed for task and glossary
  review.

### Verification

- your editor is ready to review Markdown task files with embedded
  PlantUML,
- your editor is ready to review AsciiDoc glossary files with
  embedded diagrams.

## Step 1: Confirm Spec Loop in the tutorial project

### You send

```text
I am following the Spec Loop Wordle tutorial from my browser.
Please work in this project according to the installed Spec Loop setup.

Tutorial-specific goals:
- use the normal planning workflow for non-trivial work,
- later tutorial steps will create and maintain `glossary.adoc`,
- rendering setup help is only needed again if a later step requires
  it,
- browser automation setup is not needed for this tutorial,
- tell me how you will work here and restate the
  `PLAN -> IMPLEMENTATION` approval rule in one sentence.
```

### You see

Read the assistant's final response carefully, even if you skip
intermediate reasoning. Before continuing, confirm these points:

- the assistant says it will follow the installed Spec Loop workflow
  in this project;
- the assistant makes clear that non-trivial work will go through the
  normal planning path before implementation;
- the assistant correctly restates the `PLAN -> IMPLEMENTATION`
  approval rule.

### You learned (this step)

- Setup is now package installation, with a separate
  editor-rendering step when needed.
- The tutorial may be open in your browser while the assistant only sees the
  `wordle-tutorial-project`, so prompts must still carry the context it
  needs.

## If setup seems wrong

1. Ask the assistant which installed skills are active.
2. Ask it to restate the `PLAN -> IMPLEMENTATION` approval rule.
3. If that still looks wrong, reinstall the skills with:

```bash
npx skills add dpolivaev/spec-loop -s '*'
```

4. For global installation for all agents, use `-g --all`.
   For other installation variants, check
   https://github.com/vercel-labs/skills.
5. If `npx` is not available or does not help, copy the needed part of
   the [skills/](../skills/) directory from
   `https://github.com/dpolivaev/spec-loop` into the tool-specific
   skills directory.
6. If the tool still does not automatically apply the expected
   workflow, explicitly ask for the needed skill by name.
7. Continue only when the assistant clearly understands the setup and the
   workflow rules.

## From here on

- each `You send` block is a prompt to adapt and send,
- each `You see` block describes the expected outcome,
- if you want to finish the tutorial in minimum time, send the next
  prompt first and then read it and think about it while the assistant works,
  because the assistant also needs time to act and respond,
- validate progress from the changed files and the assistant's final
  response before continuing,
- for routine steps, you can usually skip intermediate reasoning
  and read the assistant's final response carefully once it finishes,
- if the assistant misses a required setup, project instructions, glossary,
  or status update, ask it to fix that before continuing,
- if the setup or workflow rules seem wrong, use the recovery steps
  above before continuing.

## Possible misalignment

If one of these happens, interrupt the flow and ask the assistant to correct
it before continuing:

- it starts changing files or config before showing the plan and
  getting approval,
- it cannot clearly explain which Spec Loop setup is active or restate
  the `PLAN -> IMPLEMENTATION` approval rule,
- it ignores the installed workflow rules,
- it starts implementation before explicit approval,
- unrelated changes are mixed into one subtask,
- implementation changes are made without verification evidence,
- it misses required supporting updates such as glossary, task status,
  or ignore rules,
- the assistant's final response does not match the actual changed
  files,
- a task or subtask is moved to `done` without explicit user
  confirmation.

## Step 2: Project README (`README.md`)

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

Also create `.gitignore` if you find any harness-specific or IDE-specific
configuration files in this repository.

Also update the active project instructions file (for example
`AGENTS.md`) so it explicitly tells the assistant to read `README.md`
and follow the "Project Brief" section there for project requirements
unless I explicitly override it. The instructions file must also say
that this project never uses the fileless planning path: any code change
requires creation of a task file.

This is documentation-only work, we do not need a task file for it.
```

### You see

- `README.md`:
  - exists and captures the project brief requirements,
  - includes the project brief text under `Project Brief`.
- `glossary.adoc`:
  - exists and defines the canonical project terms from the brief,
  - uses wording consistent with the brief so later tasks can reuse it.
- `.gitignore`:
  - exists if harness-specific or IDE-specific configuration files were
    found.
- Project instructions file:
  - explicitly points the assistant to `README.md` as the
    source of the project brief and requirements,
  - states that the fileless planning path is never allowed in this
    project and that any code change requires a task file.

### After completion (commit)

- After you accept this work item as done:
  ask the assistant to `commit the README, glossary.adoc, .gitignore (if created), and instructions-file changes`.

### You learned (this step)

- The assistant can create documentation, add lasting instructions that
  point to the project brief, and establish `glossary.adoc` as the
  project vocabulary without creating a task file.

## Step 3: Gradle Java project setup

### You send

```text
Let us work on initial Gradle Java project setup in this repository.

The scope must include:
- a single-module Gradle project,
- Gradle wrapper files,
- Kotlin DSL build scripts,
- Java 21 toolchain configuration,
- application plugin wiring,
- standard `src/main/java`, `src/test/java`, and `src/main/resources`
  layout,
- just enough code to prove the application can build, test, and run.
```

### You see (plan)

- A task file is created automatically, and implementation still
  waits for explicit approval.
- Task file:
  - contains Scope, Motivation, Briefing, Research, Design, and Test
    specification,
  - records the chosen Gradle wrapper version in Research,
  - includes a build-layout diagram (PlantUML by default; Mermaid
    only when explicitly preferred).

Approve only after the task definition looks correct.
If the assistant does not create the task automatically, the task content
does not have the required form, or embedded PlantUML does not render
correctly, correct it before approving anything.
If needed, send the error text or a screenshot and ask the assistant to fix
the diagram.
Then ask the assistant to `implement it`.

### You see (after implementation is completed)

- Build files exist and load as planned.
- The project has wrapper scripts, Kotlin DSL build files, and the
  standard source layout.
- Verification evidence includes the exact verification commands and
  their result.
- The task file is in `review`.
- The task file may include `Implementation notes` when relevant; if
  present, review them as part of the reviewer-facing task artifact.

### After acceptance (move to done / commit)

- After you accept this work item as done:
  tell the assistant to `move the task to done and commit`.

### You learned (this step)

- Initial build setup is still task-based work:
  it is planned first, then implemented after explicit approval.

## Step 4: Wordle domain model and evaluation rules

### You send

```text
Let us work on the Wordle domain model and evaluation rules in this
repository.

The scope must include:
- domain objects for words and feedback that are not tied to the UI,
- deterministic duplicate-aware letter evaluation,
- immutable model boundaries suitable for later engine and interface
  work.

Break the work down into subtasks.
 
For the initial task creation, do not fully design every future
subtask. Create only:
- the overall task,
- subtasks containing Scope and Motivation each.
```

### You see (plan)

- A task file is created automatically with a task header and an
  ordered subtask breakdown, and it is waiting for your review.
- Task file:
  - has clear overall Scope, Motivation, and Scenario,
  - keeps future subtasks lightweight,
  - uses `glossary.adoc` terms consistently.

### Subtask-by-subtask workflow

- Review the task header and the task breakdown first.
- If the breakdown needs adjustment, ask the assistant to revise it before
  any implementation starts.
- If it looks good, ask the assistant to
  `fully design only the first subtask`.
- Review that current subtask detail.
  If it looks good, ask the assistant to `implement only that subtask`.
- After each implemented subtask reaches `review`, either ask for
  changes or accept it and ask the assistant to move that subtask to
  `done`.
- Then ask it to `create a separate commit` and only after that ask it
  to `design the next subtask`.

### You see (current subtask design)

- Only the current subtask is fully designed, and implementation
  still waits for explicit approval.
- Task file:
  - the current subtask includes Research, Design, and Test
    specification,
  - future subtasks remain lightweight,
  - the current subtask uses glossary terms consistently.

### You see (during subtask implementation)

- Only the approved current subtask is implemented before the next
  review step.
- The implemented current subtask moves to `review` when local
  verification is complete.
- When the last remaining unfinished subtask reaches `review` and no
  more work remains, the overall task moves to `review` too.
- Tests: separate verification evidence is provided per implemented
  subtask.
- Git: there is a separate commit per accepted subtask.
- `glossary.adoc`: expands to cover shared gameplay terms and links
  those terms to the implemented code.

### After acceptance (move to done / commit)

- After you accept the first subtask as done:
  ask the assistant to move that subtask to `done`, then commit.
- After you accept the second subtask as done:
  ask the assistant to move that subtask to `done`; if no more work
  remains, also move the overall task to `done`, then commit.

### You learned (this step)

- Keep future subtasks lightweight until you reach them:
  review the current subtask in detail, implement it, verify it,
  commit it, then move on.

## Step 5: Word list loader and validation

### You send

```text
Let us work on the internal word list loader and validation.

The scope must include:
- a packaged `wordlist.txt` resource,
- a loader that reads the declared count header from the file,
- random selection of one candidate entry from the declared list,
- conversion of the selected value into the existing validated word
  type,
- no separate dictionary-membership checks beyond loading and existing
  validation.
```

### You see (plan)

- A task file is created automatically, and implementation still
  waits for explicit approval.
- Task file:
  - documents the word-list file format in Research,
  - includes a loader-to-resource flow diagram (PlantUML by default;
    Mermaid only when explicitly preferred),
  - defines concrete automated tests for loader behavior.

Approve only after the task definition looks correct.
Then ask the assistant to `implement it`.

### You see (after implementation is completed)

- `src/main/resources/wordlist.txt` exists.
- Loader code exists and returns validated words from the packaged list.
- Tests prove header parsing, normalization, and selection behavior.
- The task file is in `review`.
- If the loader work stabilizes a shared term such as `Word List` and
  the glossary was not updated, ask the assistant to add that missing glossary
  update before accepting the step.

### After acceptance (move to done / commit)

- After you accept this work item as done:
  tell the assistant `move the task to done, commit`.

### You learned (this step)

- Infrastructure-facing work such as resource loading still benefits
  from explicit file-format research and testable design.

## Step 6: Game engine

### You send

```text
Starting point: build on the relevant research already recorded in this
repository.

Let us work on the game engine in this repository.

The scope must include:
- immutable game state,
- explicit game status values,
- attempt limits,
- feedback history,
- game start logic,
- guess submission logic,
- win and lose termination behavior.

Break the work down into these subtasks:
1. define game state model
2. implement game engine logic
```

### You see (plan)

- A task file is created automatically with a task header and an
  ordered subtask breakdown, and it is waiting for your review.
- Task file:
  - keeps future subtasks lightweight,
  - aligns with existing glossary terms,
  - clearly separates state modeling from engine behavior.

### Subtask-by-subtask workflow

- Review the task header and breakdown first.
- If it looks good, ask the assistant to
  `fully design only the first subtask`.
- Review that design and, if acceptable, ask it to
  `implement only that subtask`.
- When the first subtask reaches `review` and you accept it, ask the
  assistant to move that subtask to `done`, then commit before asking
  for the next subtask design.

### You see (during subtask implementation)

- State-model work and engine-behavior work are implemented in separate
  reviewable increments.
- Each implemented current subtask moves to `review` when local
  verification is complete.
- When the last remaining unfinished subtask reaches `review` and no
  more work remains, the overall task moves to `review` too.
- Tests prove start state, guess progression, attempt decrement, and
  win/lose transitions.
- `glossary.adoc` stays aligned with `Game`, `Game Engine`,
  `Game State`, and `Game Status` terminology.

### After acceptance (move to done / commit)

- After you accept the first subtask as done:
  ask the assistant to move that subtask to `done`, then commit.
- After you accept the second subtask as done:
  ask the assistant to move that subtask to `done`; if no more work
  remains, also move the overall task to `done`, then commit.

### You learned (this step)

- Separate the stable state shape from the state-transition behavior:
  it keeps the engine reviewable and the test coverage focused.

## Step 7: AssertJ test migration

### You send

```text
Let us migrate the existing tests in this repository to AssertJ and add
the required dependency.

The scope must include:
- replacing JUnit assertion helpers with AssertJ,
- updating build configuration as needed,
- keeping existing production APIs unchanged,
- verifying that the full test suite still passes.
```

### You see (plan)

- A task file is created automatically, and implementation still
  waits for explicit approval.
- Task file:
  - keeps scope limited to test sources and test dependency
    configuration,
  - includes concrete verification for the full test suite.

Approve only after the task definition looks correct.
Then ask the assistant to `implement it`.

### You see (after implementation is completed)

- Test code uses AssertJ consistently.
- Build configuration includes the AssertJ dependency.
- Verification evidence includes the exact test command and its
  passing result.
- The task file is in `review`.

### After acceptance (move to done / commit)

- After you accept this work item as done:
  tell the assistant `move the task to done, commit`.

### You learned (this step)

- Technical cleanup that changes build configuration and tests is still
  implementation work and still needs a task, verification, and review.

## Step 8: Architecture Decision Record (ADR) for CLI argument parsing

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

- The final ADR is preceded by a criteria discussion.
- ADR:
  - compares realistic options,
  - records the chosen parsing approach with rationale,
  - explains the tradeoff between small-project simplicity and future
    CLI growth,
  - records a practical verification command for the parsing path.

### After completion (commit)

- After you accept the ADR as done:
  ask the assistant to `commit the ADR change`.

### You learned (this step)

- ADRs are useful for long-lived tooling or design choices that
  should not be rediscovered inside a later implementation task.

## Step 9: CLI game interface

### You send

```text
Starting point: build on the existing gameplay logic in this repository
and follow the approved CLI argument parsing ADR.

Let us work on the CLI game interface in this repository.

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
```

### You see (plan)

- A task file is created automatically with a task header and an
  ordered subtask breakdown, and it is waiting for your review.
- Task file:
  - uses an ordered subtask flow,
  - keeps future subtasks lightweight,
  - treats the documentation subtasks as part of the same accepted
    delivery path.

### Subtask-by-subtask workflow

- Review the overall task and ordered subtasks first.
- Ask the assistant to `fully design only the first subtask`.
- Review that current subtask design.
  If it looks correct, ask the assistant to `implement only that subtask`.
- After each implemented subtask reaches `review`, either ask for
  changes or accept it and ask the assistant to move that subtask to
  `done`.
- Create a separate commit before moving to the next subtask.

### You see (during subtask implementation)

- Each implemented current subtask moves to `review` when local
  verification is complete.
- When the final unfinished subtask reaches `review` and no more work
  remains, the overall task moves to `review` too.
- CLI parsing and the interactive loop are delivered first.
- Feedback rendering is delivered as a separate increment with exact
  output tests.
- README usage and distribution packaging docs are delivered as later
  accepted subtasks.
- Verification evidence includes exact manual and automated
  verification commands for the CLI path.

### After acceptance (move to done / commit)

- After each accepted non-final subtask:
  ask the assistant to move that subtask to `done`, then commit.
- After you accept the final subtask as done:
  ask the assistant to move that subtask to `done`; if no more work
  remains, also move the overall task to `done`, then commit.

### You learned (this step)

- Even when one feature spans runtime behavior and documentation,
  keeping the increments ordered and separately accepted preserves
  reviewability.

## Step 10: Minimal Swing UI

### You send

```text
Starting point: build on the existing gameplay logic in this
repository.

Let us work on a minimal Swing UI in this repository.

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
```

### You see (plan)

- A task file is created automatically with a task header and an
  ordered subtask breakdown, and it is waiting for your review.
- Task file:
  - keeps future subtasks lightweight,
  - makes the CLI/UI boundary explicit,
  - uses glossary terms consistently.

### Subtask-by-subtask workflow

- Review the task header and the breakdown first.
- Ask the assistant to `fully design only the first subtask`.
- Review that design and, if acceptable, ask it to
  `implement only that subtask`.
- After each implemented subtask reaches `review`, either ask for
  changes or accept it and ask the assistant to move that subtask to
  `done`.
- Create a separate commit before moving on.

### You see (during subtask implementation)

- Each implemented current subtask moves to `review` when local
  verification is complete.
- When the final unfinished subtask reaches `review` and no more work
  remains, the overall task moves to `review` too.
- Shared input validation lands before the UI itself.
- Swing UI behavior is delivered as a separate accepted increment.
- README UI usage updates land as the final subtask.
- Verification evidence includes exact commands for UI launch, CLI
  override, and headless fallback behavior.

### After acceptance (move to done / commit)

- After each accepted non-final subtask:
  ask the assistant to move that subtask to `done`, then commit.
- After you accept the final subtask as done:
  ask the assistant to move that subtask to `done`; if no more work
  remains, also move the overall task to `done`, then commit.

### You learned (this step)

- The interface layer can stay small and reviewable when shared
  validation and engine behavior are separated first.

## You learned

Each step follows the Spec Loop workflow model:

- In chat, you ask the assistant to work on a feature, approved
  documentation change, or long-lived design decision.
- For implementation work, the assistant should create the needed task
  automatically before making executable changes.
- For larger tasks, the first planning pass may stop at the task
  header and an ordered subtask breakdown; only the current subtask is
  designed in detail before implementation.
- You approve or reject implementation explicitly.
- Only after explicit approval should the assistant make executable changes.
- Tasks should include automated tests for their deliverables.
- Every implementation subtask includes both implementation and
  testing.
- When subtasks exist, require separate status updates per subtask.
- Review-ready implementation moves the current task or subtask to
  `review`; after you accept it, you may ask the assistant to move it
  to `done`.
- If `glossary.adoc` exists, later planning and implementation must
  keep it aligned with the approved shared terms.
- Use ADRs for long-lived decisions such as the CLI parsing approach,
  then make later tasks follow that decision.
- If the assistant plans too much, skips needed file updates, or starts
  implementation too early, correct it and ask it to return to the
  expected workflow.
- After you explicitly accept a work item as `done`, ask the assistant to
  commit before moving on.
- When a step is implemented via subtasks: move the overall task to
  `done` only after the last subtask is done.

Learning outcomes:

- Keep task and subtask scopes small and reviewable.
- Use ADRs for long-lived decisions and tasks for incremental delivery.
- Use the glossary as the stable shared language across the project.
- Verify behavior using concrete evidence, not assumptions.

How to think while running this tutorial:

- Keep the process meaningful, not bureaucratic.
- Chat is for coordination and approvals; task files and the glossary
  are the long-lived specification files.
- Trust the installed skills to choose the workflow, and correct the
  assistant explicitly if it skips planning, over-designs future work, or
  misses a required file update.
- Only the user may relax or override these workflow rules.
