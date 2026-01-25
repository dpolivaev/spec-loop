# Agile Design-first AI-Assisted Development

Modern coding models can generate useful code quickly, but they also tend to:

* expand scope without being asked,
* “helpfully” invent requirements,
* implement before design is stable,
* treat tests as optional or secondary,
* produce changes that are difficult to review or trace to a ticket,
* apply partial edits when something goes wrong.

These behaviors make AI risky in real projects, especially in team environments 
where work must remain reviewable, explainable, and verifiable.

This repository defines a process framework for using a coding model (for example, Codex) in a way 
that remains compatible with professional software development: explicit scope, review boundaries, 
reproducible outcomes, and tests as part of completion. The goal of this framework is 
to make AI-assisted development behave like disciplined engineering work: 
small vertical increments, explicit decision points, and a clear definition of “done”.

---

## Why Specification-Driven Development Often Turns Into Waterfall

Specification-driven development (SDD) is appealing because it promises clarity: write the specification first, 
implement later. In practice, it often reproduces classic waterfall failure modes:

* **Specifications are written too early.** Important constraints are discovered only during implementation.
* **Specifications grow too large.** They try to describe an entire system instead of the next meaningful step.
* **Change becomes expensive.** When reality diverges from the spec, the process resists correction.
* **Specifications lose authority.** They either block progress or get ignored.

When AI is added to the loop, this problem becomes more severe. 
Models strongly prefer to “complete” designs and implementations in one pass, amplifying scope creep and premature commitment.

---

## Agile-Compatible Specification: Design per Step

This framework preserves the main benefit of SDD — explicitness — without inheriting its waterfall behavior.

Instead of one large upfront specification, it uses **incremental, step-local specifications**:

* scoped to the next meaningful increment,
* written close to implementation,
* explicitly reviewed before execution,
* and completed only when tests are implemented.

This is **design per step**, not design up front.

A task file is not a historical log.
It is a living description of the *current* understanding required to act correctly.

History is stored in version control.
The task file represents the present truth.

---

## The Role of the Human Developer

A central assumption of this framework is that **the human developer remains the primary source of understanding and intent**.

The model is not treated as a replacement for the developer, but as a powerful implementation and reasoning aid that operates under explicit constraints.

### Working at the Level of Understanding, Not Implementation Detail

In this process, the human developer primarily operates at higher levels:

* clarifying *what problem is being solved*,
* defining *boundaries and non-goals*,
* identifying *risks and unknowns*,
* shaping *design intent*,
* and validating *correctness and completeness*.

Details of implementation are delegated to the model **only after** understanding, scope, and verification expectations are explicit.

### Simultaneous Visibility Across Levels

The structure of task files (scope, research, design, test specification, developer briefing)
allows the developer to work on the same problem simultaneously at multiple levels:

* conceptual intent,
* architectural constraints,
* concrete design,
* verification strategy.

This prevents the common failure mode where focusing on low-level code causes the overall picture to be lost.

### Leveraging Human Strengths

By externalizing details into explicit artifacts, the framework frees the developer to focus on their strongest contributions:

* judgment,
* prioritization,
* trade-off evaluation,
* and responsibility for correctness.

The model executes within the space defined by these decisions; it does not define that space on its own.

---

## Core Principles

### Task Files as Source of Truth

All tasks, research, design decisions, and execution status live in individual Markdown task files, organized by status folders.

The model must treat the task file as authoritative for:

* scope and non-goals,
* required research,
* design constraints,
* test expectations.

### Research First

Unless explicitly waived, work starts with research into the existing codebase and constraints. 
Findings are recorded in the **Research** section.

This anchors the model in reality and prevents speculative architecture.

### Explicit Approval Gates

After any task file edits (research, design, test specification), the user reviews and approves.
Approval authorizes implementation.

This is the primary guardrail against uncontrolled AI behavior.

### Tests Are Part of “Done”

Implementation is complete only when both the design and the test specification are implemented, unless tests are explicitly waived.

“Working code without tests” is not a finished increment.

### No Partial Changes on Failure

If an edit cannot be applied, the result must be an error without partial modifications.

Consistency is preferred over partial progress.

### Run Only on Explicit Requests

Tools and workflows are activated only by explicit user requests. 
There is no opportunistic refactoring or silent behavior.

---

## Internal Iterations and Design Refinement

This framework is intentionally **iterative inside a task or subtask**.

Iteration here is not a deviation from the process — it is a first-class feature.

### Internal Iterations Are Expected

Within a single task or subtask, it is normal to iterate multiple times over:

* research,
* design,
* and design representation.

Finished subtasks may be refined, and new subtasks may be added as understanding improves. 
The task file always reflects the *current stabilized state*, not the path taken to reach it.

### Dialogue With the Model Is Part of Research and Design

The model is used not only for implementation, but also to:

* clarify design decisions,
* surface implicit assumptions,
* focus attention on risky or fragile parts of legacy code.

Important insights discovered in these discussions are written down explicitly in **Research** and **Design**,
turning personal knowledge into shared, reviewable context.

### Working With Legacy Code

This framework is particularly suited for legacy codebases.

The developer can explicitly guide the model’s attention:

* to specific files or modules,
* to known fragile areas,
* to historical constraints or architectural quirks.

The model works within real constraints instead of inventing a clean-slate architecture.

### Developer Briefing: A Soft Entry Point

Each task includes a **Developer Briefing** section.

Its purpose is to provide a gentle entry point:

* for reviewers unfamiliar with the codebase,
* for the developer validating design decisions outside daily context,
* and for onboarding new team members.

The briefing explains what matters, where to look first, and why the design looks the way it does.

### Visual Design and PlantUML

Design refinement often benefits from visual representations.

This framework encourages the use of PlantUML diagrams to reason about structure and verify assumptions. 
While GitHub does not render PlantUML natively, diagrams can be:

* rendered in GitLab,
* viewed via IDE plugins,
* or included as pre-rendered images.

Rendering limitations do not reduce the value of diagrams as a thinking and validation tool.

---

## Team Work: Scrum, Jira, and Ticketed Development

This protocol integrates cleanly with common agile team practices.

### Scrum and Iterative Delivery

Work is sliced into vertical increments. Each increment has its own scope, design, and test specification.

### Jira and Ticket IDs

Task files may reference an existing **Ticket ID**. When present, 
the ticket ID becomes the primary identifier for commit messages and traceability.

This allows AI-assisted work to fit naturally into Jira-based workflows.

### Review Boundaries

* **Plan Review** aligns with refinement and design review.
* **Implementation Review** aligns with code review readiness.

---

## Example: End-to-End Demonstration (Console Wordle)

This repository includes a complete worked example: a small console Wordle implementation.

The example demonstrates:

* incremental specification,
* explicit research and design,
* test-driven completion,
* and disciplined use of the model.

One portable output format is:

`C. R~ A= N. E.`

Where:

* `=` means correct letter in correct position,
* `~` means correct letter in wrong position,
* `.` means letter not present.

Optional color output can be added as a separate increment.

---

## Design Philosophy in One Sentence

This protocol keeps the human developer at the level of understanding and responsibility, 
while delegating implementation details to the model under explicit, reviewable constraints.

---

## License

This work is licensed under the **Creative Commons Attribution 4.0 International (CC BY 4.0)** license.

You are free to:

* share,
* adapt,
* and reuse this framework,

as long as appropriate attribution is given.

See the `LICENSE` file for details.

