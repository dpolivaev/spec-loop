# Agile Design-first AI-Assisted Development

Modern coding models can generate useful code quickly, but they also tend to expand scope, invent requirements, implement before design is stable, and treat tests as optional. In real projects, those failure modes make AI output hard to review, hard to trace, and risky to ship.

This repository is a **process framework** for AI-assisted development (not a prompt library): explicit scope, review boundaries, reproducible outcomes, and tests as part of completion.

## Why specification-driven development often turns into waterfall

Specification-driven development is appealing because it promises clarity (spec first, implementation later). In practice, specifications are often written too early, grow too large, and resist correction when reality diverges.

This framework stays agile by using **incremental, step-local specifications** (“design per step”): scoped to the next meaningful increment, reviewed before execution, and complete only when tests are implemented.

## Getting started

1. Copy these files into the target repo (keep paths and names the same):
   - [CONSTITUTION.md](CONSTITUTION.md)
   - [AGENTS.md](AGENTS.md)
   - [.github/copilot-instructions.md](.github/copilot-instructions.md)
2. Choose a task directory and keep all task files there, organized by status folders.
   - Example task directory in this repo: [examples/wordle/tasks](examples/wordle/tasks)
   - Replace the `<TASK_DIR>` placeholder in `AGENTS.md` and `.github/copilot-instructions.md` with your chosen path (for example, `tasks`).
3. Start work by creating a task file in that directory and follow the workflow in [docs/workflow.md](docs/workflow.md).

## Documentation

- [Overview](docs/overview.md)
- [Workflow](docs/workflow.md)
- [Human role](docs/human-role.md)
- [Team and traceability](docs/team-and-traceability.md)
- [Tooling integration](docs/tooling-integration.md)
- [Diagrams and PlantUML](docs/diagrams-and-plantuml.md)

## Example

- Wordle: [examples/wordle/](examples/wordle/)


## License

Licensed under Creative Commons Attribution 4.0 (CC BY 4.0). See [LICENSE](LICENSE).

## Origin

This framework was developed and applied in [Freeplane](https://github.com/freeplane/freeplane).
