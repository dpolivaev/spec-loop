# Common Task Guidance

This file applies to executable task planning on both planning paths of
`spec-loop-plan-task`.

It defines the shared no-subtask main-task form, section meanings,
current-increment readiness rules, context-preservation rules,
formatting conventions, and the required use of detailed Test
specification guidance for both planning paths, with any
task-file-only formatting rules called out explicitly:

- the chat-only planning path kept in chat; and
- the task-file path.

Task-file-only lifecycle, folders, tracked moves, subtasks, diagrams,
and other task-file administration stay in
[task-file-path-guidance.md](task-file-path-guidance.md).

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
required Research, Scenario, Glossary, Analysis, or Constraints for
the current increment — even when the User allows combined phases.

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

## Task artifact structure and sections

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
  - `- **Scenario:**` (conditional; use when behavior, flow,
    boundaries, or user-visible outcomes need grounding)
  - `- **Glossary:**` (conditional; use with Scenario when shared
    domain terms are introduced, changed, or redefined)
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
  When a conditional or optional section is omitted, omit it entirely
  and keep the remaining sections in the order listed above.

The `###` headings below are guidance-document headings only. In task
artifacts, these sections must be bold-label list items such as
`- **Design:**`, not Markdown headings. This keeps task files with
subtasks from creating extra heading levels under each subtask.

Task-file-only subtask rules stay in
[task-file-path-guidance.md](task-file-path-guidance.md).

Task-section descriptions below follow this order. Extra cross-section
guidance appears near the sections it affects. This is not drafting
order. Draft sections in whatever order gives accurate content. For
example, finalize `Briefing` after research when research identifies
relevant modules, conventions, or risks.

### Scope

Defines the current increment's boundaries: what is included, what is
excluded, and which user-visible or system behavior is in scope.

### Motivation

Explains why the change is needed. Use confirmed user intent,
observed defects, project goals, or clearly marked inference.

### Scenario

`Scenario` explains what happens and grounds domain language before
implementation. Keep it concise, implementation-free, and
behavior-first.

Use `Scenario` when behavior, flow, boundaries, or user-visible
outcomes are introduced, changed, or clarified.

If behavior changes but shared vocabulary does not, keep `Scenario`
only. If neither behavior nor shared vocabulary changes, omit both
`Scenario` and `Glossary`. Prefer `Scenario` over `Glossary` when
only one section is needed. Do not use `Glossary` without `Scenario`
by default.

Across the whole task artifact, use domain-related words for planned
behavior and structure. When referring to planned or changed
production types, use exact intended class, interface, and enum
names, not stand-ins or generic role placeholders.

Research may mention legacy terms. Analysis and Design use only
canonical Scenario and Glossary terms except explicit
legacy-to-target mapping tables. If code uses different names, align
incrementally and document intentional mismatch in the active task
artifact.

### Glossary

Use task `Glossary` when the current increment introduces or changes
review-relevant shared domain terms, or exact external technical terms
whose precise type or API is part of the reviewed contract, relative to
the current shared domain-language source.

Before approval seeking, if the term-reduction and classification
pass finds such a qualifying term delta and task `Glossary` is
absent, add `Scenario` and task `Glossary` before continuing.

When used:
- include `Scenario` too;
- write or update both together;
- place `Glossary` immediately after `Scenario`; and
- follow
  [scenario-and-glossary-guidance.md](scenario-and-glossary-guidance.md).

Task `Glossary` is delta-only relative to the current shared
domain-language source. Do not redefine unchanged terms from that
source.

For glossary sources, extension points, and candidate-term selection
rules, follow
[scenario-and-glossary-guidance.md](scenario-and-glossary-guidance.md).

#### Project glossary policy

Follow [SKILL.md](SKILL.md) for glossary-file recognition and
glossary-format routing.

Current shared domain-language source:
- if a project glossary exists, use it;
- otherwise use `Research` plus the existing codebase until a project
  glossary is created.

Once a project glossary exists, use it as shared task language above
individual tasks and code. Do not add helper names, implementation
details, framework terms, or terms not needed to explain project
rules, behavior, or subsystem boundaries.

If glossary work is required for the current task:
- reflect it in the task plan; and
- during IMPLEMENTATION, follow [SKILL.md](SKILL.md) rules for project glossary
  creation or update.

If a project glossary update would change approved meaning rather
than record it, return to PLAN first.

If the task plan is missing required glossary work, return to PLAN,
update the task, get approval, and continue.

### Constraints

Use for important limits Design and implementation must obey.
Typical content: semantic invariants, non-goals, compatibility
limits, performance limits, identity rules, forbidden
simplifications.

### Briefing

Short orientation for someone unfamiliar with the codebase: relevant
modules, important classes, framework context, repo conventions, risk
areas.

Finalize Briefing late in PLAN, shortly before seeking implementation
approval, after Research, Design, and Test specification are coherent.
Keep target-state plans in Design, not Briefing.

### Research

Research and clarification are iterative. Do enough clarification to
know the research scope, then research the current system and return
to clarification whenever findings expose material choices about
intent, scope, constraints, design, acceptance logic, or verification.

Record observations, constraints, verified facts, and findings about
the original pre-implementation state only. Plans go in **Design**.
Documents the original pre-implementation system state for the
governed increment: behavior, implementation, legacy arch, flows, data
structures, findings, and constraints.

Do not record repository states created during the current increment
in canonical Research. If later clarification, implementation, or
review reveals new relevant facts about the original
pre-implementation state, extend Research with those facts only.

Do not repeat `Analysis` points here in decision-and-reason form.

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
Research and Scenario behavior, using Glossary when present.

Design = implementation contract. Must be reviewable and
implementation-ready.

Design must follow Constraints and Analysis when they are present. If
Design conflicts with either, fix Design or return to clarification.

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
itself. On the task-file path, follow
[task-file-path-guidance.md](task-file-path-guidance.md) for the
rules that govern diagrams, allowed non-diagram text, and local
ordering between a diagram and its related text in **Research** and
**Design**.
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

When Scenario or Glossary exists, Design uses their canonical terms.
Do not redefine Glossary terms in Design.

Every new review-relevant term introduced in Design must remain
classifiable under
[scenario-and-glossary-guidance.md](scenario-and-glossary-guidance.md).
Before approval seeking, the active task must complete the
term-reduction and classification pass required there. If any such
term cannot be classified, treat it as a design defect.

Internal implementation terms may appear only in implementation-level
design such as class-level structure. Do not use them as if they were
domain language in `Scenario`, task `Glossary`, or behavior-level
diagrams and prose.

### Test specification

Specifies how the current task's requirements and task-relevant
modified code paths are verified.

Before drafting or revising `Test specification`, you must read and
follow [test-specification-guidance.md](test-specification-guidance.md).

Separate automated verification cases from optional human-reader hints:

- `Automated tests` = task-specific automated verification cases, not
  execution commands, framework names, or pass/fail status.
- `Manual tests` = optional checks a human reviewer may perform after
  handoff. They do not block `review` and must not be reported as done
  unless actually performed.

### Implementation notes

Conditional execution-phase notes. This section is filled only at the
post-implementation checkpoint under the
`spec-loop-implementation-flow` skill. It is not part of the
canonical planning truth. If present, it describes the current
implementation state, not the target state in canonical sections. Its
content may still be relevant context for follow-up tasks and, on the
task-file path, subtasks. Detailed behavior is governed by that skill
and is not repeated here.

### Iterative discovery

Iterate across Research, Scenario (if used), Glossary (if used),
Analysis (if used), Design, and Test specification until coherent.
Record intermediate alternatives only when they aid reasoning or
review. No implementation during this loop.

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


