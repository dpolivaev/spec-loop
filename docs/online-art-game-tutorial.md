# Online Art Game Tutorial: You Send, You See

This tutorial uses public data from the Art Institute of Chicago (AIC).
This project is not affiliated with or endorsed by AIC.

## Bootstrap

### B1. Create an empty `museum-tutorial-project`

Run this from a workspace directory of your choice:

```bash
mkdir -p museum-tutorial-project
cd museum-tutorial-project
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

Open `museum-tutorial-project` in your coding tool.

### B4. Select the model explicitly

For this tutorial, select the model explicitly instead of relying on
automatic model choice. With an unknown model, poor instruction
following is more likely.

Continue with Step 1 from the `museum-tutorial-project` root. Send the
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
I am following the Spec Loop online art game tutorial from my browser.
Please work in this project according to the Spec Loop workflow defined by the installed skills.

Tutorial-specific goals:
- use the normal planning workflow for non-trivial work,
- later tutorial steps will create and maintain `glossary.adoc`,
- rendering setup help is only needed again if a later step requires
  it,
- tell me how you will work here and restate the
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
  `museum-tutorial-project`, so prompts must still carry the context it
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

We are building a small website with two parts:
1) a museum overview page based on Art Institute of Chicago data,
2) a game called Progressive Timeline.

Data source attribution:
- Art Institute of Chicago (AIC): https://www.artic.edu/
- Attribution must be preserved in generated outputs.
- This project is an educational exercise and should clearly attribute
  AIC as the source of museum content and artwork metadata.

In Progressive Timeline, the player must order artworks by year
from earliest to latest.

Level progression:
- Level 1: 2 artworks
- Level 2: 3 artworks
- Level 3: 4 artworks
- each next level adds one artwork

Data rule:
- use only artworks with a clearly extractable year
- exclude artworks with ambiguous years

The game includes a leaderboard sorted by:
1) reached level (desc)
2) total completion time (asc) for ties

Please write `README.md` for this repository based on the project brief.
Include the project brief verbatim in the README under a "Project Brief"
section. The README must preserve the AIC attribution requirements from
the brief and clearly describe the two parts (museum overview page +
Progressive Timeline game), the core rules, and the leaderboard sorting.
Keep the README concise and practical.

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

### Your intent

- Turn the project brief into durable project files before
  implementation starts.
- Lock in the shared vocabulary, attribution rules, and the rule that
  every later code change needs a task file.

### You see

- `README.md`:
  - Exists and captures the project brief requirements.
  - Includes the project brief text under "Project Brief".
- `glossary.adoc`:
  - Exists and defines the canonical project terms from the brief.
  - Uses wording consistent with the brief so later tasks can reuse it.
- `.gitignore`:
  - Exists if harness-specific or IDE-specific configuration files were
    found.
- Project instructions file:
  - Explicitly points the assistant to `README.md` as the
    source of the project brief and requirements.
  - States that the fileless planning path is never allowed in this
    project and that any code change requires a task file.

### After completion (commit)

- After you accept this work item as done: ask the assistant to
  `commit the README, glossary.adoc, .gitignore (if created), and instructions-file changes`.

### You learned (this step)

- The assistant can create documentation, add lasting instructions that
  point to the project brief, establish `glossary.adoc` as the
  project vocabulary, and (after you accept it) commit without
  creating a task file.

## Step 3: Museum Overview Page (`site/index.html`) + Just-Enough API Research

Optional note: Playwright MCP or Playwright CLI can be helpful later if
you want the assistant to navigate, check, and debug the web pages and
scripts it produces. Depending on your tool, you can discuss with the
assistant whether to install one of them now or later. Playwright MCP:
https://github.com/microsoft/playwright-mcp#getting-started
This is helpful, but not important for finishing the tutorial.

### You send

```text
Ensure a sibling `data-aggregator` checkout exists at
`../data-aggregator` relative to this repository.

If it is missing, clone
`https://github.com/art-institute-of-chicago/data-aggregator.git`
into a parallel directory first.

If the clone fails because you do not have the needed access, stop and
ask me either to run the clone myself or to give you the needed access.

After the correct location is confirmed, add it to the active
project instructions file so future work can reuse it without
re-asking.
```

### Your intent

- Resolve the external sibling dependency up front instead of letting
  later steps guess or re-ask.
- Record the confirmed path in project instructions so later work can
  reuse it.

### You see

- `../data-aggregator` exists as a sibling checkout.
- If the assistant had enough access, it performed the clone itself.
- If the clone could not be performed automatically, the assistant stopped and
  told you exactly what to do before continuing.

### You send

```text
Let us work on the museum overview page in this repository by creating
`site/index.html`.

Requirements:
- run real HTTP checks with curl (or equivalent) against the public AIC
  API; do not run a local instance
- introduce AIC as the data source
- show departments
- show exactly 20 representative artworks with title, artist,
  department, and image for each item
- use API data and image URLs programmatically without manual downloads
- add automated checks that prove the page can be served and opened
- report the exact local serve command in chat
```

### Your intent

- Force real external API research before implementation instead of
  invented or local-only assumptions.
- Keep the page task reviewable with exact serve/open verification
  requirements.

### You see (plan)

- A task file is created automatically, and implementation still
  waits for explicit approval.
- Instructions: the active project instructions file is updated to
  record the confirmed sibling `data-aggregator` path.
- Task file:
  - Contains Scope, Motivation, Research, Design, and Test specification
    (and other required sections, for example Scenario and task
    `Glossary` when applicable).
  - Research includes curl verification evidence and practical rules
    needed for the museum page (including image URL rules) and any
    relevant reference notes from `data-aggregator`.

Approve only after the task definition looks correct.
If the assistant does not create the task automatically, the task content
does not have the required form, or an embedded PlantUML diagram or
Mermaid visual glossary does not render correctly, correct it before
approving anything.
If needed, send the error text or a screenshot and ask the assistant to fix
the diagram.

### You see (after implementation is completed)

- Verification evidence includes the exact local serve/open command
  and its result.
- `site/index.html`: exists and shows exactly 20 artworks with title,
  artist, department, and image.
- The task file is in `review`.
- The task file may include `Implementation notes` when relevant; if
  present, review them as part of the reviewer-facing task artifact.

### After acceptance (move to done / commit)

- After you accept this work item as done: tell the assistant 
  `move the task to done and commit`.

### You learned (this step)

- Implementation starts only after explicit approval and is verified with
  concrete evidence.

For all work items below that include implementation: the assistant is
expected to follow the Spec Loop workflow rules automatically; direct
manual guidance is the exception. If the assistant starts implementation
before planning and explicit approval boundaries, or over-designs
future work too early, first check whether it remembers the workflow
rules (for example ask it to restate the PLAN -> IMPLEMENTATION
approval gate), then tell it to stop and follow those workflow rules
strictly.

## Step 4: Architecture Decision Record (ADR) for Game Stack and Core Design Style

This step is intentionally more explicit than many real prompts for
an initial implementation. Its purpose is to demonstrate architectural
decision capture, tooling selection, reviewable design expectations,
and later task alignment in a single example. In a smaller or
lower-risk project, a lighter ADR prompt may be sufficient.

### You send

```text
Please create one ADR for stack selection and core design style for the
initial game implementation in `architecture-decisions/`.

First discuss the criteria with me. We want a stack and design approach
for the initial game implementation that support a clean, layered,
class-based design: the game rules should live in explicit domain
classes, should not be tied to the UI, the design should stay visible
and reviewable with a class diagram, and most core logic should be
testable without the browser. Persistence stack decisions are deferred.

Then compare 3-5 realistic stack options for the initial game
implementation with pros and cons. Include at least one simpler option
and at least one option that is a strong fit for clean or hexagonal
architecture.

Record one final choice with rationale. In the same ADR:
- define the practical test tooling
- define the exact test command(s)
- define the browser-based tooling for gameplay and design checks
- define the expected high-level architecture for the initial game
  implementation
- require a class-based core design with explicit domain classes and
  clear UI-adapter boundaries
- state that later task Design should be reviewable with a class
  diagram
- explain why the chosen stack and design style are a good fit for
  clean, reviewable design
- mark persistence as out of scope and deferred to the leaderboard work
```

### Your intent

- Ask for the criteria discussion in a way that should make the
  assistant use the normal `spec-loop-clarify-task` flow instead of
  free-form brainstorming.
- Capture stack, design style, tooling, and the persistence deferral
  in one durable ADR.

### You see

- The ADR is preceded by a decision-criteria discussion in the normal
  `spec-loop-clarify-task` format.
- If the assistant starts an unstructured discussion instead, stop it
  and say:
  `Use the spec-loop-clarify-task skill for the criteria discussion before writing the ADR.`
- ADR:
  - Compares realistic stack options for the initial game implementation and records the chosen one with rationale.
  - Records the required core design style, not only the implementation stack.
  - Explains the choice in terms of clean/layered class-based design,
    not only implementation speed.
  - Includes test tooling and the exact test command(s).
  - Includes browser-based tooling for gameplay and design checks.
  - Defines the expected high-level architecture for the initial game
    implementation.
  - Requires explicit domain classes for core gameplay logic and clear
    boundaries to UI/browser code.
  - Makes later class-diagram-based design review an explicit
    expectation.
  - Marks persistence as out of scope and deferred to the leaderboard work.

### After completion (commit)

- After you accept the ADR as done: ask the assistant to `commit the ADR`.
  This step is ADR-only and does not involve moving anything to `done`.

### You learned (this step)

- ADRs capture long-lived decisions (including the exact test command)
  without requiring a task file.

## Step 5: Core Gameplay (Subtasks)

### You send

```text
Starting point: reuse relevant AIC API research already recorded in
this repository and follow the ADR.

Let us work on core gameplay in this repository.

The scope must include a Level 1 playable flow with 2 artworks,
progressive levels where each next level adds one artwork, and strict
year eligibility that accepts only standalone 4-digit years like 1879
and rejects ranges, circa/ca., decades, null or unknown values, and
mixed text values. Ensure the game page is reachable from a link on
site/index.html.

For the initial task creation, do not fully design every future
subtask. Create only:
- the overall task,
- subtasks containing Scope and Motivation each,
- implementation subtasks that are vertical gameplay slices and can
  each reach `review` with the tests for that slice,
- no separate scaffolding, model, logic, or UI subtasks.
```

### Your intent

- Make the assistant break gameplay into reviewable subtasks instead
  of designing the whole feature in one pass.
- Reuse the approved ADR and earlier research rather than
  rediscovering those decisions inside the task.

### You see (plan)

- A task file is created automatically with a task header and an
  ordered subtask breakdown, and it is waiting for your review.
- Task file:
  - Overall Scope and Motivation are clear.
  - Each subtask has Scope and Motivation, but future subtasks are not
    fully designed yet.
  - Implementation subtasks are vertical gameplay slices rather than
    scaffolding/model/logic/UI buckets.
  - Relevant earlier task-file research is referenced where needed.
  - Task and subtask terminology aligns with `glossary.adoc`.

### ⚠️ Attention point: inspect the subtask split

- Check the initial subtask breakdown carefully.
- If the model proposes `scaffolding`, `model`, `logic`, `UI`, or any
  other layer split, reject it.
- Correct it immediately: ask for vertical gameplay slices where each
  implementation subtask delivers reviewable behavior and the tests
  for that slice.
- A good correction prompt is:

```text
Reject this split. Re-plan the task into vertical gameplay slices.
Each implementation subtask must deliver reviewable behavior with the
needed tests for that slice. Do not create scaffolding/model/logic/UI
subtasks.
```

### Subtask-by-subtask workflow

- Review the task header and the task breakdown first.
- If the breakdown needs adjustment, ask the assistant to revise it before
  any implementation starts.
- Keep the split vertical: each implementation subtask must stay a
  reviewable gameplay slice with its own tests.
- If it looks good, ask the assistant to `fully design only the first subtask`.
- Review that current subtask detail. If it looks good, ask the assistant to
  `implement only that subtask`.
- After each implemented subtask reaches `review`, either ask for
  changes or accept it and ask the assistant to move that subtask to
  `done`.
- Then ask it to `create a separate commit` and only after that ask it to
  `design the next subtask`.

### You see (current subtask design)

- Only the current subtask is fully designed, and implementation
  still waits for explicit approval.
- Task file: the current subtask includes fully specified class
  diagrams for the slice; future subtasks remain lightweight.
- Test specification: every required check for the slice is listed
  explicitly.
- The current subtask Design and Constraints, when present,
  use glossary terms from `glossary.adoc` consistently and make any
  glossary term change explicit before approval.

### ⚠️ Attention point: inspect the current subtask design

- Reject the subtask if the class diagrams are partial or vague.
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
- Code: game is reachable from `site/index.html` and playable (after
  relevant subtasks complete).
- `glossary.adoc`: expands to cover the core gameplay
  terms introduced by the implementation and links those terms to the
  relevant code.

### After acceptance (move to done / commit)

- After you accept an earlier subtask as done: ask the assistant to
  move that subtask to `done`, then commit.
- After you accept the final subtask as done: ask the assistant to
  move that subtask to `done`; if no more work remains, also move the
  overall task to `done`, then commit.

### You learned (this step)

- Keep future subtasks lightweight until you reach them: review the
  current subtask in detail, implement it, verify it, commit it, then
  move on.

## Step 6: Leaderboard Clarification (In-Memory, Then Persistence)

### You send

```text
Let us work on the leaderboard in this repository.

I want you to fully design the new leaderboard task in the backlog.
```

### Your intent

- Intentionally leave the leaderboard under-specified so the assistant
  must surface the missing persistence decision.
- Once that branch is resolved, keep the work staged: in-memory first,
  persistence later.

### You see (clarification)

- If the assistant starts fully designing the leaderboard task instead
  of clarifying first, stop it and say:
  `Use the spec-loop-clarify-task skill before designing this task.`
- The assistant does not fully design the task immediately.
- It first surfaces the material unresolved branch or branches and asks
  clarifying questions in the normal `spec-loop-clarify-task` format:
  - `Question:`
  - `Recommendation:`
  - `Options:` when explicit options are needed
  - `Reason:`

If the assistant's first clarification is about persistence scope,
reply exactly with:

```text
Break the implementation work down in this order:
1. in-memory leaderboard implementation
2. persistence implementation

Design only the in-memory leaderboard subtask fully.

If any other unresolved decisions remain, please prefer decision
batches over separate questions for the rest of this clarification
round.
```

If the assistant asks any other clarification question, or presents a
decision batch, accept the recommended options unless you intentionally
want a different path. If it includes persistence scope again and
recommends something else, correct that answer to the in-memory-then-
persistence path above.

### You see (plan after clarification)

- A separate leaderboard backlog task is created automatically and is
  waiting for your review.
- Task file:
  - exists with ordered implementation subtasks,
  - keeps future implementation subtasks lightweight,
  - requires a separate persistence ADR before persistence
    implementation is fully designed, and
  - has the in-memory leaderboard subtask fully designed.

### You send

```text
Implement it.
```

### You see (in-memory implementation)

- Verification evidence is provided for the in-memory leaderboard
  subtask.
- The in-memory leaderboard subtask is in `review`.
- Behavior: leaderboard sorting matches the required rules.
- `glossary.adoc`: links the leaderboard terms to the
  implemented code.

### You send

```text
Please create the persistence ADR. The ADR must define the
chosen persistence approach, storage location, reset procedure for local
development and tests with an exact command, and practical verification
commands.
```

### You see (persistence ADR)

- ADR: records the chosen persistence approach, storage location
  expectations, reset procedure expectations, and practical verification
  commands before the persistence implementation subtask is fully designed.

### You send

```text
Please design the remaining subtask.
```

### You see (persistence subtask design)

- Task file: the persistence implementation subtask is fully designed.

### You send

```text
Implement it.
```

### You see (persistence implementation)

- Verification evidence is provided for the persistence implementation
  subtask.
- The persistence implementation subtask is in `review`.
- If no more work remains, the overall task is in `review` too.
- Docs: storage location and reset procedure are documented with an exact
  command.
- Behavior: leaderboard sorting matches the required rules and data
  survives restart.

### After acceptance (move to done / commit)

- After you accept the in-memory leaderboard subtask as done: ask the
  assistant to move that subtask to `done`, then commit.
- After you accept the persistence ADR: ask the assistant to commit the ADR
  change.
- After you accept the persistence implementation subtask as done: ask
  the assistant to move that subtask to `done`; if no more work
  remains, also move the overall task to `done`, then commit.

### You learned (this step)

- Intentionally incomplete prompts can trigger proactive clarification
  before task drafting.
- Ordered delivery reduces risk: get the in-memory behavior working
  first, make the persistence decision explicitly, then implement
  persistence.

## You learned

Each step follows the Spec Loop workflow model:

- In chat, you ask the assistant to work on a feature or long-lived design
  decision.
- For implementation work, the assistant should create the needed task
  automatically before making executable changes.
- For larger tasks, the first planning pass may stop at the task
  header and an ordered subtask breakdown; only the current subtask is
  designed in detail before implementation.
- You approve or reject implementation explicitly.
- Only after explicit approval should the assistant make executable changes
  (code/tests/config/runtime assets).
- Tasks should include automated tests for their deliverables.
- In large implementation steps, ask the assistant to decompose work into
  smaller implementation subtasks before detailed design and
  implementation approval.
- Every implementation subtask includes both implementation and testing.
- When subtasks exist, require separate status updates per subtask
  (each subtask is tracked independently).
- Review-ready implementation moves the current task or subtask to
  `review`; after you accept it, you may ask the assistant to move it
  to `done`.
- If the assistant plans too much, skips needed file updates, or starts
  implementation too early, correct it and ask it to return to the
  expected workflow.
- After you explicitly accept a work item as `done`, ask the assistant to
  commit before moving on.
- Depending on your tool, you may be asked to confirm the commit
  command (review the commit message there), or the commit may happen
  immediately (review the commit message right after). If it does not
  match the work item's purpose, or it is misleading about what
  changed, ask the assistant to improve the message and amend the commit.
- When a step is implemented via subtasks: move the overall task to
  `done` only after the last subtask is done.

Learning outcomes:

- Keep task and subtask scopes small and reviewable.
- Use ADRs for architectural decisions with clear rationale.
- Verify behavior using concrete evidence, not assumptions.

How to think while running this tutorial:

- Keep the process meaningful, not bureaucratic.
- Low-risk, small cleanup that does not change behavior may be done
  and (after you accept it) you can ask the assistant to commit it as part
  of a step when appropriate (for example: `.gitignore`,
  documentation typo fixes).
- Chat is for coordination and approvals; task files and ADRs are
  the long-lived specification files.
- Trust the installed skills to choose the workflow, and correct the
  assistant explicitly if it skips planning, over-designs future work, or
  misses a required file update.
- Only the user may relax or override these workflow rules.
