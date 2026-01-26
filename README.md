# Design-first Iterative AI-Assisted Development

Modern coding models can generate useful code quickly, but they also tend to expand scope, invent requirements, implement before design is stable, and treat tests as optional. In real projects, those failure modes make AI output hard to review, hard to trace, and risky to ship.

This repository is a **process framework** for AI-assisted development (not a prompt library): explicit scope, review boundaries, reproducible outcomes, and tests as part of completion.

## Why specification-driven development often turns into waterfall

Specification-driven development is appealing because it promises clarity (spec first, implementation later). In practice, specifications are often written too early, grow too large, and resist correction when reality diverges.

This framework stays agile by using **incremental, step-local specifications** (“design per step”): scoped to the next meaningful increment, reviewed before execution, and complete only when tests are implemented.

## Getting started

1. The Constitution defines the process for both the human developer and the AI.
   - This repo’s Constitution: [CONSTITUTION.md](CONSTITUTION.md) is the only content required. You need to understand it and make sure the AI knows it.
2. How to apply it (keep it in the AI context):
   - Manual: reference `CONSTITUTION.md` in your prompts when you work with the AI.
   - Integrated: use instruction files that automatically inject the Constitution and task directory into every session and user message. The [AGENTS.md](AGENTS.md) and [.github/copilot-instructions.md](.github/copilot-instructions.md) files in this repo are examples you can adapt.
3. Define your task directory in those instruction files (`<TASK_DIR>`), then create a task file and follow the workflow in [docs/workflow.md](docs/workflow.md).

## Documentation

- [Introduction](docs/introduction.md) — why the framework exists and how “design per step” avoids waterfall.
- [Workflow](docs/workflow.md) — task files, approvals, and definition of done.
- [Human role](docs/human-role.md) — what the developer owns and how to stay at the level of understanding.
- [Team and traceability](docs/team-and-traceability.md) — scrum, tickets, and review boundaries.
- [Tooling integration](docs/tooling-integration.md) — how to apply the Constitution across AI tools.
- [Diagrams and PlantUML](docs/diagrams-and-plantuml.md) — using diagrams and how to render them.

## Example

- Wordle: [examples/wordle/](examples/wordle/) — end-to-end sample showing incremental specs, output format, and tests.


## License

Licensed under the MIT License. See [LICENSE](LICENSE).

## Origin

This framework was developed and applied in [Freeplane](https://github.com/freeplane/freeplane).
