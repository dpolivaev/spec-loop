# Workflow

This document summarizes the workflow concepts used by the framework. The canonical rules are defined in [CONSTITUTION.md](../CONSTITUTION.md).

## Task files as source of truth

All tasks, research, design decisions, and execution status live in individual Markdown task files. The task file is authoritative for:

- scope and non-goals,
- required research,
- design constraints,
- test expectations.

## Task states and status folders

Task files are organized by status folders to reflect the current work focus. Treat the folder status as the primary signal of where work stands.

See [CONSTITUTION.md](../CONSTITUTION.md) for the canonical status model and folder names.

## Approval gates (Plan Review / Implementation Review)

A central guardrail is explicit review boundaries:

- The model drafts or updates the task file (research, design, test specification).
- The human developer reviews and approves.
- Approval authorizes implementation.

See [CONSTITUTION.md](../CONSTITUTION.md) for the exact approval boundary and what counts as approval.

## Definition of done

An increment is “done” only when it matches the approved design *and* the verification expectations.

## Tests are mandatory for completion

Implementation is complete only when both the design and the test specification are implemented, unless tests are explicitly waived.

“Working code without tests” is not a finished increment.

## Internal iterations inside tasks and subtasks

Iteration is expected inside a task or subtask. It is normal to iterate over:

- research,
- design,
- and design representation.

The task file should reflect the current stabilized understanding required to act correctly, not the path taken to reach it.
