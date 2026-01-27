# Spec Loop — Conceptual Overview

## Introduction

Modern coding models can generate useful code quickly, but they also
tend to:

- expand scope without being asked,
- “helpfully” invent requirements,
- implement before design is stable,
- treat tests as optional or secondary,
- produce changes that are difficult to review or trace,
- apply partial edits when something goes wrong.

These behaviors make AI risky in real projects, especially in team
environments where work must remain reviewable, explainable, and
verifiable.

Spec Loop defines a **process framework** for AI-assisted development
that remains compatible with professional engineering practice:
explicit scope, clear review boundaries, reproducible outcomes, and
tests as part of completion.

The framework is defined by a single governing document, the
**[Constitution](../CONSTITUTION.md)**, which fully describes the
workflow and its approval boundaries and is intentionally kept compact
enough to remain fully visible within a single model context window.

---

## The Problem Spec Loop Solves

Without strong process boundaries, AI-assisted development tends to
drift into failure modes that are familiar from poorly controlled
projects:

- requirements expand implicitly,
- design decisions are made inside generated code,
- partial implementations accumulate,
- review becomes difficult because intent is unclear,
- tests lag behind or are omitted.

These problems are amplified by coding models because models strongly
prefer to “complete” work in one pass and smooth over uncertainty
instead of stopping at decision boundaries.

Spec Loop exists to **reintroduce explicit decision points** into
AI-assisted work.

---

## Why Specification-Driven Development Often Turns into Waterfall

Specification-driven development (SDD) is appealing because it promises
clarity: specification first, implementation later. In practice, it
often reproduces classic waterfall failure modes:

- **Specifications are written too early.**  
  Important constraints are discovered only during implementation.

- **Specifications grow too large.**  
  They attempt to describe an entire system instead of the next
  meaningful increment.

- **Change becomes expensive.**  
  When reality diverges from the specification, the process resists
  correction.

- **Specifications lose authority.**  
  They either block progress or get ignored.

When AI is added to the loop, these problems intensify. Models tend to
treat specifications as starting points for completion rather than as
boundaries for execution.

---

## Design per Step: Incremental Specification

Spec Loop preserves the core benefit of specification-driven work —
explicitness — without inheriting its waterfall behavior.

Instead of one large upfront specification, it uses **incremental,
step-local specifications**:

- scoped to the next meaningful increment,
- written close to implementation,
- explicitly reviewed before execution,
- completed only when verification is implemented.

This is **design per step**, not design up front.

A task file is not a historical record.  
It is a living description of the *current understanding required to
act correctly*.

History belongs in version control.  
The task file represents the present truth.

---

## Non-goals

Spec Loop does **not** attempt to:

- make the model autonomous,
- replace developer judgment,
- eliminate review,
- optimize for maximum speed,
- generate complete systems in one pass.

The framework is explicitly designed to keep work:

- scoped and reviewable,
- anchored in explicit understanding,
- verifiable through tests,
- traceable through version control.

## Notes on Model Behavior

In practice, this approach works best with models that reliably follow
explicit instructions, e.g. properly update subtask status, stop at
Plan Review, and wait for approval before implementing.

So far, the most consistent results have been observed with
GPT-5.2-Codex.

---

## The Human Developer’s Role

A central assumption of Spec Loop is that **the human developer remains
the primary source of understanding and intent**.

The model is treated as a powerful implementation and reasoning aid
that operates under explicit constraints — not as an independent
decision-maker.

### Working at the Level of Understanding

In this process, the developer primarily operates at higher levels:

- clarifying *what problem is being solved*,
- defining *boundaries and non-goals*,
- identifying *risks and unknowns*,
- shaping *design intent*,
- validating *correctness and completeness*.

Implementation details are delegated to the model **only after**
understanding, scope, and verification expectations are explicit.

### Maintaining Visibility Across Levels

The structure of task files allows the developer to work on the same
problem at multiple levels simultaneously:

- conceptual intent,
- architectural constraints,
- concrete design,
- verification strategy.

This prevents the common failure mode where focusing on low-level code
causes the overall picture to be lost.

### Developer Briefing as a Soft Entry Point

Each task includes a **Developer Briefing** section that serves as a
soft entry point:

- for reviewers unfamiliar with the codebase,
- for the developer returning to the task after time has passed,
- for onboarding new contributors.

The briefing explains what matters, where to look first, and why the
design looks the way it does.

---

## Team Work and Traceability

Spec Loop is designed to integrate cleanly with common team practices
without requiring new tooling or ceremonies.

### Iterative Delivery

Work is sliced into vertical increments. Each increment has its own
scope, design, and test specification.

### Tickets and Task Files

Task files may reference an external ticket identifier (for example, a
Jira ID).

When a ticket ID exists, it becomes the primary identifier for
traceability (including commit messages).  
When it does not, Spec Loop defines its own identifier conventions.

### Commit Messages

Commit messages are part of traceability and are treated as structured
artifacts, not informal notes.

Canonical rules for commit messages live in the Constitution.

### Review Boundaries

Spec Loop defines explicit review boundaries:

- **Plan Review** aligns with refinement and design review.
- **Implementation Review** aligns with code review readiness.

The goal is to keep AI-assisted work explainable and verifiable within
normal review processes.

---

## Workflow Summary (Non-normative)

This section summarizes how the workflow *feels* in practice.  
The **canonical rules** live in
[CONSTITUTION.md](../CONSTITUTION.md).

### Task Files as Source of Truth

All scope, research, design decisions, and execution status live in
individual task files.

The task file is authoritative for:

- scope and non-goals,
- required research,
- design constraints,
- test expectations.

### Status and Approval Gates

Tasks move through status folders that reflect current focus.

A central guardrail is explicit approval:

- the model drafts or updates the task file,
- the developer reviews and approves,
- approval authorizes implementation.

### Definition of Done

An increment is considered “done” only when it:

- matches the approved design,
- satisfies the test specification.

Working code without tests is not a finished increment unless tests are
explicitly waived.

### Iteration Inside Tasks

Iteration is expected inside a task or subtask.

It is normal to iterate over:

- research,
- design,
- representation.

The task file should reflect the **current stabilized understanding**,
not the path taken to reach it.

---

## Where the Rules Live

- **This document** explains *what Spec Loop is and why it works*.
- **[CONSTITUTION.md](../CONSTITUTION.md)** defines the *normative,
  enforceable rules*.

The Constitution is authoritative.  
This document provides rationale and orientation.
