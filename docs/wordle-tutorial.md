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

- Markdown task files with embedded PlantUML diagrams and Mermaid
  visual glossaries
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
  review,
- suggests small Markdown and AsciiDoc probe files when an end-to-end
  rendering check is useful; those probes should include a class
  diagram and the other relevant diagram types.

### Verification

- your editor is ready to review Markdown task files with embedded
  PlantUML diagrams and Mermaid visual glossaries,
- your editor is ready to review AsciiDoc glossary files with
  embedded diagrams.

## ⚠️ Default rule for later clarification questions

For the rest of this tutorial, if the assistant asks a clarification
question and gives a recommendation, follow the recommendation unless
you intentionally want a different path.

If the assistant starts asking too many separate clarification
questions and you want to speed the rest up, tell it:
`Please prefer decision batches over separate questions for the rest of this clarification round.`

## Step 1: Confirm Spec Loop in the tutorial project

### You send

```text
I am following the Spec Loop Wordle tutorial from my browser.
Please work in this project according to the Spec Loop workflow defined by the installed skills.

Tell me how you will work here and restate the
`PLAN -> IMPLEMENTATION` approval rule in one sentence.
```

### Your intent

- Confirm that the assistant is actually following the installed Spec
  Loop workflow in this repository.
- Make it restate the planning-before-implementation approval boundary
  before any real work starts.

### You see

Read the assistant's final response carefully, even if you skip
intermediate reasoning. Before continuing, confirm these points:

- the assistant says it will follow the Spec Loop workflow defined by the installed skills
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

## ⚠️ Useful prompts

The installed skills usually choose the workflow automatically, but you
can name the needed behavior when the assistant drifts:

- Say `clarify`, `discuss this`, `discuss the criteria`,
  `compare the options`, `stress-test this`, or `resolve the open
  questions` when you want general discussion or material open-decision
  resolution. If a task file or ADR governs the discussion, final
  decisions are recorded there. If not, decisions stay in chat until
  you say where they should be captured. This activates the
  [spec-loop-clarify-task](../skills/spec-loop-clarify-task/) skill.
- Say `is this task ready for implementation?`, `prepare this task for
  implementation approval`, or `review this task before I approve
  implementation` when you want task-file readiness checked and
  repaired before implementation approval. If important open decisions
  remain, clarification runs first. This activates the
  [spec-loop-prepare-implementation-approval](../skills/spec-loop-prepare-implementation-approval/) skill.
- Say `implement it` only after you approve the plan, when you want the
  approved increment implemented, verified, reflected in the task file,
  and moved to `review`. This activates the
  [spec-loop-implementation-flow](../skills/spec-loop-implementation-flow/) skill.

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
- later, add a minimal UI that reuses the same core logic

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
and UI paths, and the word-list expectations. Keep the README concise
and practical.

Also create `glossary.adoc` from the approved project brief. It should
define the canonical project terms needed for this tutorial and keep
their wording consistent with the brief.

Also create `.gitignore` if you find any harness-specific or IDE-specific
configuration files in this repository.

Also update the active project instructions file (for example
`AGENTS.md`) so it explicitly tells the assistant to:
- read `README.md` and follow the "Project Brief" section there for
  project requirements unless I explicitly override it;
- strictly follow the installed Spec Loop skills;
- never use the chat-only/fileless planning path in this project; and
- use the task-file workflow for every executable change, including
  code, tests, build/configuration, dependencies, and runtime assets.

This is documentation-only work, we do not need a task file for it.
```

### Your intent

- Turn the project brief into durable project files before
  implementation starts.
- Lock in the shared vocabulary and the rule that every later code
  change needs a task file.

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
  - says the installed Spec Loop skills must be followed strictly,
  - disables the chat-only/fileless planning path for this project,
  - requires the task-file workflow for every executable change.

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

### Your intent

- Start with a small implementation task that proves the normal
  plan-review-implement loop.
- Keep scope tight: just enough Gradle and Java setup to build, test,
  and run.

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
does not have the required form, or an embedded PlantUML diagram or
Mermaid visual glossary does not render correctly, correct it before
approving anything.
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
- subtasks containing Scope and Motivation each,
- implementation subtasks that are vertical gameplay slices and can
  each reach `review` with the tests for that slice,
- no separate model-only or logic-only subtasks.
```

### Your intent

- Make the assistant decompose the core gameplay model into reviewable
  subtasks instead of over-designing everything at once.
- Establish domain terms and boundaries that later engine and
  interface work will reuse.

### You see (plan)

- A task file is created automatically with a task header and an
  ordered subtask breakdown, and it is waiting for your review.
- Task file:
  - has clear overall Scope, Motivation, and Scenario,
  - includes task `Glossary` when shared terms are introduced,
    changed, or redefined,
  - keeps future subtasks lightweight,
  - uses vertical gameplay slices rather than model/logic buckets,
  - uses `glossary.adoc` terms consistently.

### ⚠️ Attention point: inspect the subtask split

- Check the initial subtask breakdown carefully.
- If the model proposes a layer split such as `define domain
  objects` and `implement evaluation rules`, reject it.
- Correct it immediately: ask for vertical gameplay slices where each
  implementation subtask delivers reviewable behavior and the tests
  for that slice.
- A good correction prompt is:

```text
Reject this split. Re-plan the task into vertical gameplay slices.
Each implementation subtask must deliver reviewable behavior with the
needed tests for that slice. Do not create model-only or logic-only
subtasks.
```

### Subtask-by-subtask workflow

- Review the task header and the task breakdown first.
- If the breakdown needs adjustment, ask the assistant to revise it before
  any implementation starts.
- Keep the split vertical: each implementation subtask must stay a
  reviewable gameplay slice with its own tests.
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
  - the current subtask includes fully specified class diagrams for
    the slice,
  - the current subtask Test specification lists every required check
    explicitly,
  - future subtasks remain lightweight,
  - the current subtask uses glossary terms consistently.

### ⚠️ Attention point: inspect the current subtask design

- Reject the subtask if class diagrams are partial or vague.
- The diagrams should already show the review-relevant classes,
  relationships, methods, and fields for this slice.
- Reject the subtask if the Test specification leaves checks implicit,
  vague, or missing.
- The Test specification should list every required automated check
  and any remaining manual check for this slice.
- A good correction prompt is:

```text
Do not implement this subtask yet. Complete the design first.
Make the class diagrams fully specified for this slice: include the
review-relevant classes, relationships, methods, and fields.
Make the Test specification explicit: list every required automated
check and any remaining manual check for this slice.
```

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

### Your intent

- Treat word-list loading as real planned work, not a quick hidden
  utility.
- Force explicit file-format research and automated tests before
  implementation.

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

Break the work down into vertical gameplay subtasks in this order:
1. start a game and expose immutable in-progress state
2. submit guesses and handle win/lose termination
```

### Your intent

- Keep the engine task behavior-oriented instead of splitting model
  and logic into separate buckets.
- Preserve ordered subtask review instead of merging the whole engine
  into one jump.

### You see (plan)

- A task file is created automatically with a task header and an
  ordered subtask breakdown, and it is waiting for your review.
- Task file:
  - keeps future subtasks lightweight,
  - aligns with existing glossary terms,
  - uses ordered vertical gameplay slices rather than model/logic
    buckets.

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

- Game-start behavior lands first as a reviewable increment.
- Guess submission, history updates, and win/lose behavior land in the
  next reviewable increment.
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

- Even inside engine work, keep subtasks behavior-oriented instead of
  splitting model structure from logic.

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

### Your intent

- Keep a testing-focused change narrow and reviewable.
- Require proof that the full suite still passes after the assertion
  migration.

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

### Your intent

- Ask for the criteria discussion in a way that should make the
  assistant use the normal `spec-loop-clarify-task` flow instead of
  free-form brainstorming.
- Record the parsing decision as a durable ADR with a real
  verification command.

### You see

- The assistant uses `spec-loop-write-adr` and the final ADR is
  preceded by a criteria discussion in the normal
  `spec-loop-clarify-task` format.
- If the assistant starts an unstructured discussion or drafts the ADR
  before criteria/options are clarified, stop it and say:
  `Use the spec-loop-clarify-task skill for the criteria discussion before writing the ADR.`
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

Break the implementation work down into vertical behavior slices in
this order:
1. implement a playable CLI flow from a local `--wordlist` path with
   deterministic textual feedback
2. add URL `--wordlist`, `--attempts`, and `--cli` behavior
3. document CLI build and usage
4. document application distribution packaging
```

### Your intent

- Make the CLI feature follow the approved ADR instead of
  rediscovering parsing choices inside the task.
- Keep the implementation slices behavior-oriented instead of
  splitting parsing from visible CLI behavior.
- Keep runtime behavior and docs in ordered increments.

### You see (plan)

- A task file is created automatically with a task header and an
  ordered subtask breakdown, and it is waiting for your review.
- Task file:
  - uses an ordered vertical subtask flow for implementation,
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
- A playable CLI flow from a local `--wordlist` path with
  deterministic feedback lands first.
- URL `--wordlist`, `--attempts`, and `--cli` behavior land in the
  next accepted increment.
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
  keep the implementation increments behavior-oriented and separately
  accepted.

## Step 10: UI Clarification and Minimal Swing UI

### You send

```text
Starting point: build on the existing gameplay logic in this
repository.

Let us work on a UI in this repository.

I want you to fully design the new UI task in the backlog.
```

### Your intent

- Leave the UI approach open so the assistant has to surface the
  missing framework decision.
- After that, steer it to Swing while keeping CLI fallback and
  launch-policy constraints explicit.

### You see (clarification)

- The UI approach is intentionally left open here.
- If the assistant asks what UI approach or framework this task should
  assume, choose Swing even if Swing is not the recommendation and is
  not listed in its options.
- If the assistant starts fully designing the task without first asking
  what UI approach/framework it should assume, stop it and say:
  `Use the spec-loop-clarify-task skill before designing this task.`
- If it still skips that question, say:
  `Before designing this task, ask which UI approach/framework this task should assume.`

If the assistant asks what UI approach/framework this task should
assume, reply exactly with:

```text
Use Swing.

Keep CLI availability.
When a display is available and `--cli` is not set, the application
should start the UI.
In headless mode or when `--cli` is set, the application should use
the CLI path.

Break the implementation work down into vertical UI slices in this
order:
1. launch the minimal Swing UI when a display is available and `--cli`
   is not set
2. complete UI input validation and preserve correct CLI/headless
   fallback behavior
3. document UI build and usage

If any other unresolved decisions remain, please prefer decision
batches over separate questions for the rest of this clarification
round.
```

If the assistant asks any other clarification question, or presents a
decision batch, follow the recommended options unless you intentionally
want a different path. If it includes the UI approach/framework
question again and recommends something else, correct that answer to
Swing.

### You see (plan after clarification)

- A task file is created automatically with a task header and an
  ordered subtask breakdown, and it is waiting for your review.
- Task file:
  - keeps future subtasks lightweight,
  - uses ordered vertical UI slices,
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
- Minimal Swing UI launch behavior lands first.
- UI input validation and correct CLI/headless fallback behavior land
  in the next accepted increment.
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

- Leaving the UI approach open can force the missing framework decision
  into a clarification round before task design.
- Once the UI direction is chosen, keep the interface work in
  reviewable vertical increments rather than shared-preparation
  subtasks.

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
