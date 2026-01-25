# Team Work and Traceability

This framework is designed to integrate cleanly with common agile team practices.

## Scrum and iterative delivery

Work is sliced into vertical increments. Each increment has its own scope, design, and test specification.

## Jira integration

Task files may reference an existing **Ticket ID**.

## Ticket IDs vs task identifiers

When a Ticket ID exists, it becomes the primary identifier for traceability (including commit messages). When it does not, use the identifier conventions defined in the workflow.

See [CONSTITUTION.md](../CONSTITUTION.md) for the canonical identifier rules.

## Commit message conventions

Commit message conventions are defined by the workflow and are part of traceability. See [CONSTITUTION.md](../CONSTITUTION.md).

## Review boundaries and collaboration

- **Plan Review** aligns with refinement and design review.
- **Implementation Review** aligns with code review readiness.

The goal is to keep AI-assisted work reviewable, explainable, and verifiable within normal team workflows.
