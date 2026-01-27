# Spec Loop — Design-First AI-Assisted Development

Modern coding models can generate useful code quickly, but they also tend to
expand scope, invent requirements, implement before design is stable, and
treat tests as optional. In real projects, those failure modes make AI output
hard to review, hard to trace, and risky to ship.

This repository defines a **process framework** for AI-assisted development
(not a prompt library and not a tool): explicit scope, review boundaries,
reproducible outcomes, and tests as part of completion.

## Why specification-driven development often turns into waterfall

Specification-driven development is appealing because it promises clarity
(spec first, implementation later). In practice, specifications are often
written too early, grow too large, and resist correction when reality diverges.

Spec Loop avoids this by using **incremental, step-local specifications**
(“design per step”): scoped to the next meaningful increment, reviewed before
execution, and complete only when tests are implemented.

## Getting started

1. The Constitution defines the process for both the human developer and the AI.
   - This repo’s Constitution: [CONSTITUTION.md](CONSTITUTION.md) is the only
     content required to execute the process.
   - For rationale and conceptual context, see the
     [Conceptual Overview](docs/overview.md).

2. How to apply it (keep it in the AI context):
   - Manual: reference `CONSTITUTION.md` in your prompts when you work with the
     AI.
   - Integrated: use instruction files that automatically inject the
     Constitution and task directory into every session and user message. The
     [AGENTS.md](AGENTS.md) and
     [.github/copilot-instructions.md](.github/copilot-instructions.md) files in
     this repo are examples you can adapt.

3. Define your task directory in those instruction files (`<TASK_DIR>`), then
   create a task file and follow the workflow described in
   [docs/workflow.md](docs/workflow.md).

## Documentation

- **[Conceptual Overview](docs/overview.md)**  
  What Spec Loop is, why it exists, and how the pieces fit together.

- **[Constitution](CONSTITUTION.md)**  
  The canonical, normative rules that define the process and approval
  boundaries.

- **[Wordle](examples/wordle/)**
  A sample showing incremental specifications, output format, and tests.
  The evolution of the example can be followed 
  through its [commit history](commits/main/examples/wordle),
  which demonstrates the Spec Loop workflow in real development conditions.


## Diagrams and PlantUML

Design refinement often benefits from visual representations.
This framework encourages the use of PlantUML diagrams to reason about
structure, validate assumptions, and make design intent explicit.

Diagrams here are **part of the specification**, not decoration.
They are meant to be read together with the surrounding Markdown text.

To read these documents properly, a Markdown viewer that can render PlantUML
diagrams inline is required.
GitHub does not render PlantUML embedded in Markdown natively.

For a better reading experience with inline diagrams, you can view this
repository on GitLab:

- **GitLab mirror:** https://gitlab.com/dpolivaev/spec-loop — GitLab renders
  PlantUML diagrams in Markdown directly in the browser.

Alternatively, if you work locally:

- Use an editor or viewer with PlantUML support (for example, in VS Code with an
  appropriate extension) so that diagrams are rendered inline while reading or
  editing the Markdown files.

The intent is that diagrams remain close to the text they explain, so design
decisions can be understood without switching tools or contexts.

## License

Licensed under the MIT License. See [LICENSE](LICENSE).

## Origin

This framework was developed and applied in
[Freeplane](https://github.com/freeplane/freeplane).
