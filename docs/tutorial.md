# Spec Loop Tutorial: Develop Chicago Art Institute Website with a Game

This tutorial is about building a small website together with AI.

The website has two core parts:

- a museum overview page,
- and a playable game where the user orders artworks by year.

## Project Brief

```text
We are building a small website with two parts:
1) a museum overview page based on Art Institute of Chicago data,
2) a game called Progressive Timeline.

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
```

## Learning outcomes

By the end of this tutorial, you should be able to:

- enforce PLAN/IMPLEMENTATION boundaries with explicit approval gates,
- keep task and subtask scopes small and reviewable,
- use ADRs for architectural decisions with clear rationale,
- verify behavior using concrete evidence, not assumptions,
- apply lightweight process for non-behavioral repo housekeeping.

## How to think while running this tutorial

Keep the process meaningful, not bureaucratic.
The goal is to improve decisions and reduce defects, not to maximize
documents.

Repository housekeeping may be done directly and committed with the
current step when it is non-behavioral and low-risk
(for example: `.gitignore`, documentation typo fixes, editor settings).
A separate software task is not required for this kind of maintenance.

Common anti-patterns:

- implementation starts before explicit approval,
- unrelated changes are mixed into one subtask,
- implementation subtask is updated without testing,
- task or subtask is moved to `done` without explicit user confirmation.

## Setup (before Step 1)

This repository is a tutorial source, not the participant workspace.
You should run the tutorial in your own new local project repository.

### 1. Create your own local project repository

- Create a new empty folder for the tutorial implementation.
- Initialize a new git repository there:

```bash
git init
```

### 2. Configure assistant guardrails in your new repository

Set up your new repository with the standard Spec Loop governance files:

- `CONSTITUTION.md`
- `AGENTS.md`
- optional: `.github/copilot-instructions.md` if your setup uses it

These governance files are shared guardrails for both sides.
The LLM is expected to know and follow the Constitution by default.
You do not need to re-explain it in every prompt.
You still keep control and can remind or correct the LLM at any time.

### 3. Clone AIC `data-aggregator`

Clone the API source repository locally (or reuse an existing checkout):

```bash
git clone https://github.com/art-institute-of-chicago/data-aggregator.git
```

Save the local path to that checkout. You will provide this path in Step 1.

### 4. Enable Playwright MCP for browser checks

Enable Playwright MCP in your assistant environment before starting the
implementation steps.

Use it when needed so the LLM can verify:

- `site/index.html` rendering and basic page behavior,
- game page flow and interactions during gameplay checks.

## Steps

Each step follows the Constitution interaction model:

- In chat, you ask the LLM to **create a task**.
- First, ask the LLM to write the task only (Scope + Research + Design + Test Spec).
- The LLM should stop after planning and ask for implementation approval.
- You give feedback in chat and explicitly approve or reject implementation.
- Only after explicit approval should the LLM implement (code/docs/tests).
- Tasks should include automated tests for their deliverables, except research-only tasks.
- In large implementation steps, explicitly ask the LLM to decompose work
  into smaller implementation subtasks before approval.
- Every implementation subtask must include both implementation and a
  testing block.
- When subtasks exist, require separate status updates per subtask
  (each subtask is tracked independently).
- After a user explicitly accepts a subtask as `done`, require a git
  commit for that subtask before moving on.
- You give feedback in chat.

By default, this tutorial uses task files for planning work. If you
explicitly choose to run selected planning work outside task files, that
is allowed. Only the user can change or relax Constitution workflow
rules; the LLM may propose changes but cannot apply them on its own.

This is expected default behavior. You usually do not need to remind the
LLM about it. If the LLM starts implementation before explicit approval,
flag it and ask it to return to the correct phase boundary.

### Step 1: API Recon — repository-first AIC API research baseline

**Spec-loop principle being trained:** Explicit legacy code research before
implementation.

**What we want to get**

- A practical orientation document: `docs/api-cheat-sheet.md`.
- It must cover:
  - a brief map of the `data-aggregator` project structure (where endpoints live, how requests are handled, where data models are shaped),
  - API endpoints and HTTP methods,
  - key fields/data structures that will later enable building the site and the game (at minimum: extractable year and image access),
  - image retrieval rules.

This step is intentionally a research task. Keep the boundary clear:

- The task describes what to do and how to verify it.
- The cheat sheet stores the actual findings.
- Do not duplicate full API findings in both places.

**How this step works**

1. Send the Project Brief from the beginning of this tutorial.
2. Ask the LLM to create and plan the first task for API documentation.
3. Review the planned task.
4. The LLM asks for implementation approval.
5. Approve implementation explicitly, then let the LLM implement.
6. Review the output and verification report.

If the LLM starts coding before approval, stop it and require it to
continue only after your explicit approval boundary is restored.

**What to tell the LLM**

In the first message:

- Ask the LLM to create the task: API documentation for the Art Institute data.
- Provide the Project Brief.
- Provide the local path to your implementation project.
- Provide the local path to your `data-aggregator` checkout.
- Clarify that this documentation is needed for both:
  - the museum page,
  - and the game.
- Clarify that the output file is `docs/api-cheat-sheet.md`.
- Clarify that the cheat sheet must include:
  - a brief map of the `data-aggregator` project structure,
  - a list of endpoints and HTTP methods,
  - key response fields/data structures needed for the site and game,
  - rules for getting image URLs.
- Clarify that verification should include real HTTP requests (`curl` or equivalent) and a short report of what was checked and what worked.
- Clarify that API checks should run against live public Art Institute
  endpoints by default.
- If a local `data-aggregator` instance is used instead, require the LLM
  to state that explicitly and include the base URL and startup command.
- Clarify that for research-only tasks, tests are not required.

**What to check before moving on**

- The task was created.
- The LLM explicitly requested implementation approval after planning.
- `docs/api-cheat-sheet.md` exists and contains the required sections.
- The LLM reported real request checks and outcomes.
- The API target is explicit (live public by default, or local instance
  with base URL and startup command).
- Implementation started only after explicit approval.
- After you confirm the step is complete, explicitly ask the LLM to move the task file to the `done` folder.
- After that explicit user request, renaming the filename with the required three-digit prefix is the LLM's responsibility (`001-` for the first done task).
- If any checkpoint is not met, tell the LLM what is missing and ask it to fix the issue before moving on.

After the move to `done` and required rename, ask the LLM to create a
git commit for this step.

### Step 2: Museum overview page (static HTML)

Use the same chat session as in Step 1. Context from Step 1 is assumed.

**Spec-loop principle being trained:** implementation only after approval,
then iterative refinement with design kept in sync.

**What we want to get**

- A simple static HTML page that introduces the Art Institute of Chicago as the data source for the site.
- The page should give a quick impression of the museum by showing
  departments and exactly 20 representative artworks.
- For each shown artwork, include: title, artist, year, department, and an image.
- The page location in the project should be `site/index.html`.
- The page should be viewable in a browser via a local server.

**How this step works**

1. Ask the LLM to create and plan a new task for this step.
2. Review the task.
3. The LLM asks for implementation approval.
4. Approve implementation explicitly, then let the LLM implement.
5. Open the page in a browser and review the first working version.
6. Run one or more improvement iterations:
   - you share feedback on content/design/details,
   - the LLM updates the implementation immediately,
   - and the LLM updates the task/design description in sync.
7. Stop when the page is good enough for this stage.

**What to tell the LLM**

- Ask the LLM to create the task: museum overview page implementation.
- Use `site/index.html` as the deliverable path.
- Reuse context and artifacts from Step 1, especially `docs/api-cheat-sheet.md`.
- The page should fetch real data via `docs/api-cheat-sheet.md` and show:
  - departments,
  - exactly 20 representative artworks with images,
  - and for each artwork: title, artist, year, department.
- Explore the API first and decide what to show based on actual API responses.
- Do not manually download data or images; get museum data from the API and images via IIIF/API URLs.
- For this implementation task, include automated tests that verify the
  page can be served and opened successfully.
- Define local serving using a sensible command chosen for the selected
  stack and report the exact command used for verification.

After the first working version is ready, continue with iterative refinements based on user feedback.

- In each refinement iteration, allow to update both implementation and task/design description together.

**What to check before moving on**

- The task was created.
- The LLM explicitly requested implementation approval after planning.
- `site/index.html` exists and shows the required content.
- The page shows exactly 20 representative artworks.
- The page is viewable in a browser via a local server.
- Tests for page serving/opening were added and passed.
- Requested refinement iterations were implemented and reflected in task/design description.
- After you confirm the step is complete, explicitly ask the LLM to move the task file to the `done` folder.
- After that explicit user request, renaming the filename with the required three-digit prefix is the LLM's responsibility.
- This reminder will not be repeated in later steps.
- If any checkpoint is not met, tell the LLM what is missing and ask it to fix the issue before moving on.

After the move to `done` and required rename, ask the LLM to create a
git commit for this step.

### Step 3: Create ADR for implementation stack selection

**Spec-loop principle being trained:** explicit architectural decision
recording with clear rationale.

**What we want to get**

- One ADR that defines the MVP implementation stack and records one decision with rationale.
- The ADR should cover stack choices for frontend, runtime/language, and minimal backend shape for the game flow.
- Persistence is out of scope in this step and will be decided later.

**How this step works**

1. Ask the LLM to create an ADR (this is not a task file).
2. Discuss and agree on decision criteria with the LLM.
3. Let the LLM analyze candidate stacks using those criteria.
4. Review the ADR and iterate if needed.
5. Finalize one selected stack with explicit rationale.

**What to tell the LLM**

- Ask the LLM to create the ADR: stack selection.
- Create one ADR in the project ADR folder (`architecture-decisions/`).
- Communicate explicitly: persistence is out of scope in this step and will be decided later.
- First discuss with me which criteria we will use for the decision.
- Then evaluate 3-5 realistic full-stack MVP options against those criteria.
- For each option, provide clear pros and cons.
- Record one final choice and explain why it was selected.
- In the same ADR, pick a sensible test tooling approach for this
  tutorial stack and record the chosen test command.
- No alternatives analysis is required for the test tooling decision.
- In this ADR, explicitly mark persistence as out of scope for now and defer that decision to Step 5.
- At the end of this step, ask the LLM to add a practical project
  `.gitignore` directly, without creating a separate task for it.

**What to check before moving on**

- The ADR exists in `architecture-decisions/`.
- Criteria were explicitly discussed and used.
- Multiple options were compared with pros/cons.
- The ADR records a sensible test tooling choice and the exact test
  command for this tutorial stack.
- One final decision is documented with rationale.
- The ADR explicitly states that persistence is out of scope in this step and deferred to Step 5.
- A practical `.gitignore` was added before later implementation commits.

### Step 4: Implement core gameplay (with subtasks)

**Spec-loop principle being trained:** subtask decomposition and
per-subtask approval/verification.

**What we want to get**

- One implementation task for core gameplay with LLM-planned subtasks.
- The planned subtasks must cover all required gameplay areas:
  - Level 1 playable flow (2 artworks),
  - progressive levels (each next level adds one artwork),
  - strict year eligibility filtering.
- The result should include passing automated tests for all planned
  implementation subtasks.
- The game page should be reachable by a link from the first page.

**How this step works**

1. The LLM creates one parent task and proposes a subtask plan.
2. You review the parent task and subtask plans.
3. By default, subtasks are implemented one by one.
4. Before each subtask implementation, the LLM asks for explicit approval.
5. After each subtask implementation, you review results and tests.
6. Without explicit user consent for parallel execution, move to the
   next subtask only after the current one is complete.
7. If needed, run refinement iterations for the current subtask before moving on.

The LLM may plan multiple subtasks in parallel. It may also implement
multiple already approved subtasks in parallel when you explicitly allow
that. If it starts implementing an unapproved subtask, stop it and
return to that subtask approval boundary.

**What to tell the LLM**

- Ask the LLM to create the task: core gameplay implementation.
- Create one parent task for core gameplay implementation.
- Ask the LLM to propose the subtask breakdown for this step.
- Require that the subtask plan fully covers:
  - Level 1 gameplay with pass/fail transition,
  - progressive level scaling,
  - strict year eligibility filtering.
- Define strict year eligibility precisely: accept only standalone
  4-digit numeric years (for example `1879`).
- Reject non-exact years such as ranges (`1900-1905`), circa/ca.,
  decade labels, unknown/null, or mixed text values.
- If any of these subtasks is still too large, ask the LLM to split it
  further before implementation approval.
- Include planning details and focused tests for each subtask.
- Ensure each implementation subtask has its own testing block.
- Keep one consistent gameplay design across all subtasks.
- Ensure the new game page is linked from the first page so the user can open it directly from there.
- Do not manually download data or images; use API/IIIF sources programmatically.
- Keep implementation focused on gameplay logic and tests.
- Implement subtasks one by one with separate implementation approval for each subtask.
- Report progress after each subtask before moving to the next.
- After a subtask is explicitly accepted as `done` by the user, update
  only that subtask status and create a git commit for that subtask.

**What to check before moving on**

- One parent task exists with an explicit LLM-proposed subtask plan.
- Any oversized subtask was further decomposed before implementation.
- The LLM requested implementation approval separately for each subtask.
- Every implementation subtask includes a testing block.
- Subtask statuses were updated separately (no batch status update).
- Each subtask accepted as `done` has its own git commit.
- All required gameplay areas are implemented and tested:
  - Level 1 flow,
  - progressive levels,
  - strict year eligibility filtering.
- The museum page includes a link to the game.
- You can open the game from that link and play the current version immediately.
- If any checkpoint is not met, tell the LLM what is missing and ask it to fix the issue before moving on.
- After you confirm the step is complete, explicitly ask the LLM to move the task file to the `done` folder.
- After that explicit user request, renaming the filename with the required three-digit prefix is the LLM's responsibility.

After the move to `done` and required rename, ask the LLM to create a
git commit for this step.

### Step 5: Implement leaderboard

**Spec-loop principle being trained:** phased delivery to reduce risk
(working baseline first, persistence second).

**What we want to get**

- A working leaderboard with passing tests.
- Leaderboard is required for tutorial completion.
- The implementation is done in two phases within the same step:
  - Phase 1: in-memory leaderboard implementation.
  - Phase 2: ADR for persistence strategy, then implementation of the selected persistence option.

**How this step works**

1. Create and plan one task for leaderboard work.
2. Ask the LLM to decompose this task into smaller implementation
   subtasks before implementation approval.
3. Ensure each implementation subtask includes testing in the same
   subtask.
4. Implement and test an in-memory leaderboard first.
5. Create and finalize an ADR for persistence strategy.
6. Implement and test the selected persistence approach from that ADR.
7. Run refinement iterations until leaderboard behavior and tests are stable.

**What to tell the LLM**

- Ask the LLM to create the task: leaderboard implementation.
- Ask the LLM to propose a smaller subtask breakdown for this step
  before implementation approval.
- First implement in-memory leaderboard behavior and tests.
- Require that each implementation subtask combines implementation and
  testing in the same subtask.
- If subtasks are used, require separate status updates per subtask and
  one git commit per subtask after user acceptance as `done`.
- Leaderboard sorting must be:
  1. reached level (descending),
  2. total completion time (ascending) for ties.
- After in-memory version is working, create an ADR for persistence strategy.
- In the same step, implement the persistence option selected in that ADR and update tests.
- Persistence acceptance criteria must include:
  - data survives process restart,
  - storage location is documented (file path or DB path),
  - reset behavior for local development/tests is defined with an exact
    command or procedure.
- Keep the implementation aligned with the project Constitution phase boundaries.

**What to check before moving on**

- In-memory leaderboard version was implemented and tested first.
- A subtask breakdown was proposed and reviewed before implementation.
- Every implementation subtask includes a testing block.
- Subtask statuses were updated separately when subtasks were used.
- Each subtask accepted as `done` has its own git commit.
- ADR for persistence strategy exists and records the selected option.
- Selected persistence option was implemented and tested.
- Persistence survives restart, storage location is documented, and reset
  behavior is defined.
- Leaderboard sorting behavior matches required rules.
- If any checkpoint is not met, tell the LLM what is missing and ask it to fix the issue before moving on.
- After you confirm the step is complete, explicitly ask the LLM to move the task file to the `done` folder.
- After that explicit user request, renaming the filename with the required three-digit prefix is the LLM's responsibility.

After the move to `done` and required rename, ask the LLM to create a
git commit for this step.
