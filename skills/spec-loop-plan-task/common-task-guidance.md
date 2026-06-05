# Common Task Guidance

This file applies to executable task planning on both planning paths of
`spec-loop-plan-task`.

It defines the shared no-subtask main-task form, section meanings,
current-increment readiness rules, testing policy,
context-preservation rules, and formatting conventions used by both
planning paths, with any task-file-only formatting rules called out
explicitly:

- the chat-only planning path kept in chat; and
- the task-file path.

Task-file-only lifecycle, folders, tracked moves, subtasks, diagrams,
and other task-file administration stay in
[task-file-path-guidance.md](./task-file-path-guidance.md).

## Current-increment readiness

The active task artifact is the source of truth for the current
increment. Design-first allows learning during implementation while
the approved Design stays authoritative. Findings that change the
intended target lead to updates to the active task artifact and
renewed approval before implementation continues.

- On the chat-only path, that artifact is the current canonical
  chat-only task in chat.
- On the task-file path, that artifact is the active task file.

Before IMPLEMENTATION, the active task needs: Scope, Motivation,
Briefing, implementation-ready Design, Test specification, and any
required Research, Scenario, Analysis, or Constraints for the
current increment — even when the User allows combined phases.

Before asking the User to approve a task for IMPLEMENTATION, the LLM
must self-check that the content for the current implementation
increment meets all applicable requirements of this file and any
path-specific companion rules, and is correct, internally consistent,
and compliant with `AGENTS.md` and applicable glossary rules.

At any point while drafting or revising the active task for the
current increment, if any content would depend on an unresolved
material branch about scope, behavior, policy, conceptual model,
conceptual contract boundaries, constraints, migration, acceptance
logic, or verification expectations, it must return to clarification
instead of guessing.

If drafting or reviewing Design exposes a new material boundary
decision, return to clarification before continuing.

If asking the User to review a draft instead, say so explicitly and
list the known gaps, open questions, and unresolved decisions.

## Later executable follow-up from `review` or `done`

On the chat-only path, the equivalent starting point is a task
already presented as ready for User review.

When a task or subtask already in `review` or `done`, or a chat-only
task already presented as ready for User review, receives a later
executable change request, return to PLAN before more
implementation.

From `review`, or from a chat-only task already presented as ready
for User review, keep the same task or subtask by default when the
User's message or later clarification shows that the follow-up is
still the same overall task and the work is not a distinct work item.

From `done`, ask whether to reuse the same task or subtask or use
separate tracking unless the User already specified that choice.

Otherwise ask only when that relation is unclear.

Significant scope change or widening is evidence that the work may be
a distinct work item. If that boundary is unclear, clarify in PLAN
before choosing whether to keep the same task or use separate
tracking.

Discard implementation-created intermediate states from canonical
planning artifacts by default. An implementation-created intermediate
state alone is not a reason to force separate tracking.

Preserve such a state only when the User explicitly wants
history-preserving separate tracking or when a separate governed
starting state is actually required.

If separate tracking is needed while the work is still part of the
same overall task, recommend a new subtask and wait for explicit User
confirmation before creating it. If the work is a distinct work item,
recommend a new task and wait for explicit User confirmation before
creating it.

If the same task or subtask is kept, update the governing artifact in
PLAN, discard any implementation-created intermediate state from
canonical planning sections, and seek renewed implementation approval
before more executable work.

## Shared planning and execution sections

### Research

Start with research unless waived. Record observations, constraints,
verified facts, and current-state findings only. Plans go in
**Design**. Documents the original pre-implementation system state
for the governed increment: behavior, implementation, legacy arch,
flows, data structures, findings, and constraints.

Do not record repository states created during the current increment
in canonical Research. If later clarification, implementation, or
review reveals new relevant facts about the original
pre-implementation state, extend Research with those facts only.

Do not repeat `Analysis` points here in decision-and-reason form.

### Scenario

Anchors behavior and domain terms before implementation. Use when
behavior or terms introduced/changed; else omit. Keep concise,
implementation-free.

Scenario = source of domain/behavior language. When terms
introduced/changed: create/update Scenario first, use those terms in
Design, tests, code, commits. No parallel synonyms.

Across the whole task artifact, use domain-related words for planned
behavior and structure. When referring to planned or changed
production types, use exact intended class, interface, and enum
names, not stand-ins or generic role placeholders.

Research may mention legacy terms. Analysis and Design use only
canonical Scenario terms except explicit legacy-to-target mapping
tables. If code uses different names, align incrementally and
document intentional mismatch in the active task artifact.

### Project glossary

Follow `SKILL.md` for glossary-file recognition and glossary-format
routing.

Once a project glossary exists, use it as shared task language.
Do not add helper names, implementation details, framework terms, or
terms not needed to explain project rules, behavior, or subsystem
boundaries.

If the current task requires glossary work:

- reflect it in the task plan;
- perform it during IMPLEMENTATION with traceability links.

If the task plan is missing required glossary work, return to PLAN,
update the task, get approval, and continue.

### Analysis

Authoritative ledger of final clarification decisions and reasons.

Use one short bullet per final decision:
`- <decision> because <reason>.`

Keep only final decisions here. Move structural, behavioral,
contract, and verification consequences into the affected canonical
sections and diagrams.

`Analysis` is read together with the other sections:
- do not repeat `Analysis` points in other narrative text; but
- the affected sections and diagrams must still contain names,
  structures, contracts, lists, tables, diagram content, and test
  cases when those are the actual consequences of the decision.
Do not put open questions, options, confidence values, tentative
assumptions, or transient working notes here.

When a final clarification decision changes another section or
diagram, update that section or diagram to reflect the resulting
state. Diagrams should reflect any relevant textual decisions they
own. Clarification workflow and recording procedure belong to
`spec-loop-clarify-task`.

### Design

Documents target system: architecture, data structures, data flow,
interactions, implementation boundaries. Draft from validated
Research and Scenario behavior.

Design = implementation contract. Must be reviewable and
implementation-ready.

Design must describe only the current intended end state for the
governed increment. Do not describe repository states created during
the current increment, including staged refactor states or
transformations such as `S1 -> S2`. If such an intermediate state
must be preserved, use a new task or subtask where that state can
appear as **Research**. Implementation detours that do not belong in
canonical sections may go in **Implementation notes** when relevant.

Use only final intended names for design-owned terms, units, config
keys, tool/API names, request/response fields, enum values, etc.
Prefer domain-related words and exact intended class names. No
placeholders, temp names, candidate names, example names, or generic
stand-ins such as `Controller`, `Collaborator`, `Helper`, `Manager`,
or `Processor` unless they are established domain or framework terms
that the design explicitly depends on. Undecided name/unit/boundary =
not ready for implementation.

If finding an exact name is hard, treat that as a design defect.
Change the design until responsibilities and boundaries admit precise
names, and immediately plan the required refactoring in the active
artifact. Resolving precise domain language and exact structural names
is the highest priority during design work.

When production structure changes, Design must make the structural
inventory explicit for every planned new or changed top-level
production class, interface, enum, and every new or changed
externally meaningful identifier in scope. In class diagrams, put
review-relevant methods and fields of displayed types in the diagram
itself. Companion prose, lists, or tables may capture exact ownership,
responsibilities, collaborators, or external identifiers that the
diagram cannot express clearly enough for review.
Examples of externally meaningful identifiers include persisted file
names, serialized field names, config keys, action keys, menu
placeholder names, and shared session/state flags.

Local variables, private methods, private fields contained within one
class, and other purely internal implementation details are excluded
from this inventory unless the User explicitly requests lower-level
review.

Test-only elements go in **Test specification**, not Design, unless
task changes test infrastructure.

When changing tools/APIs/serialized payloads, Design must show full
target request/response structures and enums. Examples supplement,
don't replace spec.

When Scenario exists, Design uses canonical Scenario terms.

### Test specification

Documents verification structure and concrete test coverage.
Do not restate `Analysis` points here in decision-and-reason form;
record the resulting verification consequences instead.

### Implementation notes

Conditional execution-phase notes. This section is filled only at the
post-implementation checkpoint under the
`spec-loop-implementation-flow` skill. It is not part of the
canonical planning truth, but its content may be relevant context for
follow-up tasks and, on the task-file path, subtasks. Detailed
behavior is governed by that skill and is not repeated here.

### Iterative discovery

Iterate across Research, Scenario (if used), Analysis (if used),
Design, and Test specification until coherent. Record intermediate
alternatives only when they aid reasoning or review. No
implementation during this loop.

## Context Preservation

- Re-read relevant task sections before implementation or when
  requirements are unclear.
- When a task uses subtasks, re-read the relevant main-task sections
  and diagrams before working from the active subtask. Do not assume
  the subtask repeats all required context.
- Active task artifact = working source of truth for the current
  item.
- Older task files or superseded chat-only task states = historical
  records; they need not stay consistent with the active artifact when
  superseded.
- Keep only relevant task content in active context.

## Formatting

- Task-file artifacts must wrap prose to roughly 72-80 characters and
  avoid horizontal scrolling. Chat-only work in chat does not need a
  fixed wrap width unless the User asks for it, but it must remain
  readable and structurally clear.
- Preserve semantic line breaks and consistent list indentation.
- Fenced code blocks: start/end with backticks. For top-level content,
  use flush-left fenced code blocks. When a fenced code block belongs
  to a list item, indent the opening fence, block content, and
  closing fence to that list item's content indentation so the block
  remains inside the list item.
- Standalone paragraphs unindented. List continuation lines may
  indent to align. After a fenced code block inside a list item,
  resume either the same list-content indentation or true top-level
  indentation.
- Render correctly in chat markdown and in GitHub and GitLab task
  views.

Intent: readable in plain text editors (vim, less, nano), chat views,
and rendered markdown views.

## Task Structure

Each task without subtasks uses this exact order and layout.
Do not add extra metadata fields or custom readiness markers unless
the User explicitly requests them. Use the exact bold-label section
labels below.

- Title line: `# Task: <title>`.
- One identifier (mutually exclusive):
  - `- **Ticket:**` Ticket ID, preferred.
  - `- **Task Identifier:**` if no Ticket;
    `YYYY-MM-DD-<slug>` where `<slug>` is 1-2 keywords from the
    task title or intended filename.
  - Value present = **Primary Identifier** for commit messages.
- Main task sections as bold-label list items in this order:
  - `- **Scope:**`
  - `- **Motivation:**`
  - `- **Scenario:**` (conditional; only when behavior or terms are
    introduced or refined)
  - `- **Constraints:**` (optional; important limits Design and
    implementation must obey)
  - `- **Briefing:**`
  - `- **Research:**`
  - `- **Analysis:**` (conditional; include it when final
    clarification decisions exist for the current increment)
  - `- **Design:**`
  - `- **Test specification:**`
  - `- **Implementation notes:**` (conditional; include it when the
    post-implementation checkpoint finds meaningful notes content)
  Omitted Scenario, Constraints, Analysis, or empty
  Implementation notes: keep the remaining sections in order.

Task-file-only subtask rules stay in
[task-file-path-guidance.md](./task-file-path-guidance.md).

## Constraints (optional)

- Use for important limits Design and implementation must obey.
- Typical content: semantic invariants, non-goals, compatibility
  limits, performance limits, identity rules, forbidden
  simplifications.
- If Design conflicts with Constraints, Constraints wins.

## Briefing

Short orientation for someone unfamiliar with the codebase: relevant
modules, important classes, framework context, repo conventions, risk
areas.

## Testing Policy

- Keep Test specification in each task without subtasks. No-code
  tasks: set `Automated tests: N/A` and `Manual tests: N/A`.
- Separate test-focused tasks allowed when adding or extending
  coverage as standalone scope.
- Prefer automated tests. Manual tests are optional and should be
  used only when the same verification purpose cannot be covered
  adequately by automated tests.
- If a command's purpose is to run automated tests or automated
  checks, list it under `Automated tests`, not `Manual tests`, even
  though a person invokes the command.
- Each implementation task without subtasks: include an explicit
  `Automated tests` sublist. Include a `Manual tests` sublist only
  when non-automatable verification remains; otherwise set
  `Manual tests: N/A`.
- Run and fix all required tests before moving a task-file task to
  `review`, before presenting chat-only work as ready, or before
  otherwise implying implementation closure, unless the User waives
  tests.
