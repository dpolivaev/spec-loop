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
npx skills add dpolivaev/spec-loop
```

This recommended path requires Node.js because it uses `npx`.
For global or agent-specific installation details, including
`-g -a <agent>`, see https://github.com/vercel-labs/skills.

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

- Markdown task files with embedded PlantUML
- AsciiDoc glossary files with embedded diagrams

### You send

```text
Please use the `setup-task-and-glossary-rendering` skill to help me
prepare my editor for reviewing rendered Spec Loop task files and
glossary files.

My coding harness may run in a terminal, but I review files in
<VS Code or JetBrains>.
```

### You see

- Chat:
  - uses the `setup-task-and-glossary-rendering` skill,
  - reads the setup document for your editor,
  - guides you through the rendering setup needed for task and glossary
    review.
- Verification:
  - your editor is ready to review Markdown task files with embedded
    PlantUML,
  - your editor is ready to review AsciiDoc glossary files with
    embedded diagrams.

## Step 1: Confirm Spec Loop in the tutorial project

### You send

```text
I am following the Spec Loop online art game tutorial from my browser.
Please work in this project and use these skills as needed:
the `plan-task` skill, the `write-glossary` skill, and the
`setup-task-and-glossary-rendering` skill.

Tutorial-specific goals:
- use the `plan-task` skill for non-trivial work,
- use the `write-glossary` skill for the Spec Loop `glossary.adoc`
  glossary because later tutorial steps will create and maintain
  `glossary.adoc`,
- use the `setup-task-and-glossary-rendering` skill again only if
  rendering setup help is needed later,
- tell me which of these skills you will use here and restate the
  `PLAN -> IMPLEMENTATION` approval rule in one sentence.
```

### You see

- Chat:
  - confirms that the `plan-task`, `write-glossary`, and
    `setup-task-and-glossary-rendering` skills are available,
  - treats the `plan-task` skill as the mandatory planning skill for
    non-trivial work,
  - explains that the `write-glossary` skill will be used for the
    tutorial's `glossary.adoc` work,
  - keeps the `setup-task-and-glossary-rendering` skill for later
    rendering help if needed.
- Project setup:
  - Spec Loop governance is available through the installed skills,
  - `tasks/` is used when task files are needed,
  - later glossary work uses the Spec Loop `glossary.adoc` format.
- Tooling:
  - PlantUML is recommended unless there is a good reason to choose
    Mermaid,
  - AsciiDoc support matters because the tutorial will use
    `glossary.adoc`,
  - editor rendering support was prepared in B5 unless you knowingly
    skipped that step.
- Verification:
  - the LLM correctly restates the `PLAN -> IMPLEMENTATION` approval
    rule,
  - the LLM can explain which skill it will use for planning, glossary
    work, and rendering setup.

### You learned (this step)

- Setup is now package installation plus skill selection, with a
  separate editor-rendering step when needed.
- The tutorial may be open in your browser while the LLM only sees the
  `museum-tutorial-project`, so prompts must still carry the context it
  needs.
- This tutorial later uses public data from the Art Institute of
  Chicago (AIC). The project is not affiliated with or endorsed by AIC.

## If setup seems wrong

1. Ask the LLM which of the `plan-task`, `write-glossary`, and
   `setup-task-and-glossary-rendering` skills are active.
2. Ask it to restate the `PLAN -> IMPLEMENTATION` approval rule.
3. If that still looks wrong, reinstall the skills with:

```bash
npx skills add dpolivaev/spec-loop
```

4. For global or agent-specific installation details, including
   `-g -a <agent>`, check
   https://github.com/vercel-labs/skills.
5. If `npx` is not available or does not help, copy the needed part of
   the `skills/` directory from
   `https://github.com/dpolivaev/spec-loop` into the harness-specific
   skills directory.
6. Continue only when the LLM clearly understands the setup and the
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

Also update the active project governance entry point so it
explicitly tells the LLM to read `README.md` and follow the
"Project Brief" section there for project requirements unless I
explicitly override it.

This is documentation-only work, we do not need a task file for it.
```

### You see

- [README.md](../README.md):
  - Exists and captures the project brief requirements.
  - Includes the project brief text under "Project Brief".
- `glossary.adoc`:
  - Exists and defines the canonical project terms from the brief.
  - Uses wording consistent with the brief so later tasks can reuse it.
- Project governance entry point:
  - Explicitly points the LLM to [README.md](../README.md) as the
    source of the project brief and requirements.

### After completion (commit)

- After you accept this work item as done: ask the LLM to
  `commit the README, glossary.adoc, and governance-entry changes`.

### You learned (this step)

- The LLM can create documentation, wire persistent instructions to the
  canonical project brief, establish `glossary.adoc` as the project
  vocabulary, and (after you accept it) commit without creating a task
  file.

## Step 3: Museum Overview Page (`site/index.html`) + Just-Enough API Research

Optional note: Playwright MCP or Playwright CLI can be helpful later if
you want the LLM to navigate, check, and debug the web pages and
scripts it produces. Depending on your harness, you can discuss with
the LLM whether to install one of them now or later. Playwright MCP:
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

Once the sibling checkout is available, continue with the next step.
```

### You see

- `../data-aggregator` exists as a sibling checkout.
- If the LLM had enough access, it performed the clone itself.
- If the clone could not be performed automatically, the LLM stopped and
  told you exactly what to do before continuing.

### You send

```text
A sibling `data-aggregator` checkout exists at `../data-aggregator`
relative to this repo root (parallel directory, not inside this repo).
Use it for reverse engineering only.

After the correct location is confirmed, add it to the active project
governance entry point so future tasks can reuse it without re-asking.

Please create a task for the museum overview page in this repository to
create the page at `site/index.html`. The task must include just-enough
AIC API research directly inside the task file `Research` section. Run
real HTTP checks with curl (or equivalent) against the public AIC API
(do not run a local instance) and record verification evidence
(commands + observed results) inside the task file. The page should
introduce AIC as the data source, show departments, and show exactly 20
representative artworks with title, artist, department, and image for
each item. Include the rules for retrieving artwork images in the task
research. Use API data and image URLs programmatically without manual
downloads, add automated checks that prove the page can be served and
opened, and report the exact local serve command in chat.
```

### You see (plan)

- Chat: reports that a task file was created and asks for explicit
  implementation approval.
- Governance: the active project governance entry point is updated to
  record the confirmed sibling `data-aggregator` path.
- Task file:
  - Contains Scope, Motivation, Research, Design, and Test specification
    (and other required sections, for example Scenario when applicable).
  - Research includes curl verification evidence and practical rules
    needed for the museum page (including image URL rules) and any
    relevant reverse engineering notes from `data-aggregator`.

Approve only after the task definition and subtask breakdown look correct.

### You see (after implementation is completed)

- Chat: reports the exact local serve/open verification command and the
  result.
- `site/index.html`: exists and shows exactly 20 artworks with title,
  artist, department, and image.

### After completion (move to done / commit)

- After you accept this work item as done: ask the LLM to 
  `move the task to done`, then ask it to commit.

### You learned (this step)

- Implementation starts only after explicit approval and is verified with
  concrete evidence.

For all work items below that include implementation: the LLM is
expected to follow the Constitution automatically; "manual control" is
the exception. If the LLM starts implementation before planning and
explicit approval boundaries, first check whether it remembers the
Constitution (for example ask it to restate the PLAN -> IMPLEMENTATION
approval gate), then tell it to stop and follow the Constitution
strictly.

## Step 4: ADR for Game Stack and Core Design Style

This step is intentionally more explicit than many real MVP prompts.
Its purpose is to demonstrate architectural decision capture, tooling
selection, reviewable design expectations, and later task alignment in
a single example. In a smaller or lower-risk project, a lighter ADR
prompt may be sufficient.

### You send

```text
Please create one ADR for MVP stack selection and core design style for
the game implementation in `architecture-decisions/`.

First discuss the criteria with me. We want an MVP stack and design
approach that support a clean, layered, class-based design: the game
rules should live in explicit domain classes, should not be tied to the
UI, the design should stay visible and reviewable with a class diagram,
and most core logic should be testable without the browser.
Persistence stack decisions are deferred.

Then compare 3-5 realistic MVP stack options with pros and cons. Include
at least one simpler option and at least one option that is a strong fit
for clean or hexagonal architecture.

Record one final choice with rationale. In the same ADR:
- define the practical test tooling
- define the exact test command(s)
- define the browser-based tooling for gameplay and design checks
- define the expected high-level architecture for the MVP
- require a class-based core design with explicit domain classes and
  clear UI-adapter boundaries
- state that later task Design should be reviewable with a class
  diagram
- explain why the chosen stack and design style are a good fit for
  clean, reviewable design
- mark persistence as out of scope and deferred to the leaderboard work
```

### You see

- Chat: discusses decision criteria before presenting the final ADR.
- ADR:
  - Compares realistic MVP stack options and records the chosen one with rationale.
  - Records the required core design style, not only the implementation stack.
  - Explains the choice in terms of clean/layered class-based design,
    not only implementation speed.
  - Includes test tooling and the exact test command(s).
  - Includes browser-based tooling for gameplay and design checks.
  - Defines the expected high-level architecture for the MVP.
  - Requires explicit domain classes for core gameplay logic and clear
    boundaries to UI/browser code.
  - Makes later class-diagram-based design review an explicit
    expectation.
  - Marks persistence as out of scope and deferred to the leaderboard work.

### After completion (commit)

- After you accept the ADR as done: ask the LLM to `commit the ADR change`.
  This step is ADR-only and does not involve moving anything to `done`.

### You learned (this step)

- ADRs capture long-lived decisions (including the exact test command)
  without requiring a task file.

## Step 5: Core Gameplay (Subtasks)

### You send

```text
Starting point: reuse relevant AIC API research already recorded in
earlier task files in this repo and follow the ADR.

Please create one task for core gameplay in this repository. The scope
must include a Level 1 playable flow with 2 artworks, progressive
levels where each next level adds one artwork, and strict year
eligibility that accepts only standalone 4-digit years like 1879 and
rejects ranges, circa/ca., decades, null or unknown values, and mixed
text values. Ensure the game page is reachable from a link on
site/index.html.

For the initial task creation, do not fully design every future
subtask. Create only:
- the overall task
- subtasks containing Scope and Motivation each

Reuse earlier task-file research where relevant, but keep future
subtasks lightweight. We will flesh out only the current subtask before
implementation.

```

### You see (plan)

- Chat: reports that a task file was created with a task header and an
  ordered subtask breakdown, then stops for review.
- Task file:
  - Overall Scope and Motivation are clear.
  - Each subtask has Scope and Motivation, but future subtasks are not
    fully designed yet.
  - Relevant earlier task-file research is referenced where needed.
  - Task and subtask terminology aligns with `glossary.adoc`.

### Subtask-by-subtask workflow

- Review the task header and the task breakdown first.
- If the breakdown needs adjustment, ask the LLM to revise it before any
  implementation starts.
- If it looks good, ask the LLM to `completely design only the first subtask`.
- Review that current-subtask detail. If it looks good, ask the LLM to
  `implement only that subtask`.
- After each implemented subtask, either ask for changes or accept it
  and ask the LLM to `move it to done`.
- Then ask it to `create a separate commit` and only after that ask it to
  `design the next subtask`.

### You see (current subtask design)

- Chat: designs out only the current subtask and asks for explicit
  implementation approval.
- Task file: the current subtask is designed with all class diagrams;
  future subtasks remain lightweight.
- The current subtask Design and Constraints, when present,
  use glossary terms from `glossary.adoc` consistently and make any
  glossary term change explicit before approval.

### You see (during subtask implementation)

- Chat: implements only the approved current subtask and stops.
- Tests: separate verification evidence is provided per implemented
  subtask.
- Git: there is a separate commit per accepted subtask; the overall
  task is moved to `done` only after the last subtask is done.
- Code: game is reachable from `site/index.html` and playable (after
  relevant subtasks complete).
- `glossary.adoc`: expands to cover the gameplay-core
  terms introduced by the implementation and links those terms to the
  relevant code.

### You learned (this step)

- Keep future subtasks lightweight until you reach them: review the
  current subtask in detail, implement it, verify it, commit it, then
  move on.

## Step 6: Leaderboard (In-Memory, Then Persistence)

### You send

```text
Please create one task for the leaderboard in this repository. Break
the implementation work down in this order:
1. in-memory leaderboard implementation
2. persistence implementation

The sorting must be reached level descending and total completion time
ascending for ties. Persistence acceptance criteria are that data
survives restart, the storage location is documented, and the reset
procedure for local development and tests is documented with an exact
command.

For the initial task creation, do not fully design every future
subtask. Create only:
- the overall task header
- an ordered breakdown of the implementation subtasks above
- Scope and Motivation for each subtask

Keep future implementation subtasks lightweight. We will flesh out only
the current subtask before implementation.

Keep leaderboard terminology aligned with the established project
language.
```

### You see (plan)

- Task file:
  - Exists with ordered implementation subtasks.
  - Requires a separate persistence ADR before persistence
    implementation is fleshed out.

### You send

```text
Please flesh out only the in-memory leaderboard subtask.
```

### You see (in-memory subtask design)

- Task file: the in-memory leaderboard subtask is fleshed out; future
  implementation subtasks remain lightweight.

### You send

```text
Implement it.
```

### You see (in-memory implementation)

- Verification evidence is provided for the in-memory leaderboard
  subtask.
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
  commands before the persistence implementation subtask is fleshed out.

### You send

```text
Please design the remaining subtask.
```

### You see (persistence subtask design)

- Task file: the persistence implementation subtask is fleshed out.

### You send

```text
Implement it.
```

### You see (persistence implementation)

- Verification evidence is provided for the persistence implementation
  subtask.
- Docs: storage location and reset procedure are documented with an exact
  command.
- Behavior: leaderboard sorting matches the required rules and data
  survives restart.

### After completion (move to done / commit)

- After you accept the in-memory leaderboard subtask as done: ask the
  LLM to move it to `done`, then commit.
- After you accept the persistence ADR: ask the LLM to commit the ADR
  change.
- After you accept the persistence implementation subtask as done: ask
  the LLM to move that subtask and the overall task to `done`, then
  commit.

### You learned (this step)

- Ordered delivery reduces risk: get the in-memory behavior working
  first, make the persistence decision explicitly, then implement
  persistence.

## You learned

Each step follows the Constitution interaction model:

- In chat, you ask the LLM to create a task or ADR.
- First, the LLM writes the task/ADR content needed for the current
  review step. For larger tasks, start with the task header and an
  ordered subtask breakdown, then flesh out Research, Scenario, Constraints when
  needed, Design, and Test specification only for the current
  subtask before implementation.
- You approve or reject implementation explicitly.
- Only after explicit approval should the LLM make executable changes
  (code/tests/config/runtime assets).
- Tasks should include automated tests for their deliverables.
- In large implementation steps, ask the LLM to decompose work into
  smaller implementation subtasks before detailed design and
  implementation approval.
- Every implementation subtask includes both implementation and testing.
- When subtasks exist, require separate status updates per subtask
  (each subtask is tracked independently).
- After you explicitly accept a work item as `done`, ask the LLM to
  commit before moving on.
- Depending on your tool, you may be asked to confirm the commit
  command (review the commit message there), or the commit may happen
  immediately (review the commit message right after). If it does not
  match the work item's purpose, or it is misleading about what
  changed, ask the LLM to improve the message and amend the commit.
- When a step is implemented via subtasks: move the overall task to
  `done` only after the last subtask is done.

Learning outcomes:

- Keep task and subtask scopes small and reviewable.
- Use ADRs for architectural decisions with clear rationale.
- Verify behavior using concrete evidence, not assumptions.

How to think while running this tutorial:

- Keep the process meaningful, not bureaucratic.
- Low-risk, non-behavioral housekeeping may be done and (after you
  accept it) you can ask the LLM to commit it as part of a step when
  appropriate (for example: `.gitignore`, documentation typo fixes).
- Chat is for coordination and approvals; task files and ADRs are the
  durable specification artifacts.
- Only the user may relax or override Constitution workflow rules.
