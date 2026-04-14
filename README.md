# Spec Loop — Design-First AI-Assisted Development

<p align="center">
  <a href="docs/infographics.jpg">
    <img src="docs/infographics.jpg" alt="Spec-Loop infographic" width="80%" style="border: 1px solid rgba(127, 127, 127, 0.35); border-radius: 12px; box-shadow: 0 1px 2px rgba(0,0,0,0.08);">
  </a>
</p>

There are two common ways people use AI for coding.

**Vibecoding:** you describe intent, the model fills in the gaps, and you get a
large diff with undocumented decisions. Review becomes archaeology. Tests are
optional by accident.

**Waterfall:** you try to avoid that by writing a complete spec first. You
can’t. Constraints appear during implementation. The spec inflates, then it
either blocks change or gets ignored.

Spec Loop avoids both: write the next small spec, review it, then implement it
with tests. Keep the spec local to the next step. Repeat until done.

The rules are defined in **[CONSTITUTION.md](CONSTITUTION.md)**.
The model drafts and updates task files; you review and approve at the
task-file gate before implementation.

Spec Loop also defines explicit work phases: plan, implementation and done.
Any transitions to implementation and to done require explicit user approval.

When a project maintains a
[glossary document](CONSTITUTION.md#project-glossary), that glossary
defines the shared domain language above individual tasks and the code.
It keeps design documents, tests, code symbols, and commit text
aligned on the same terms across the whole project.

## Why This Works with Large Codebases

Spec Loop is designed to work with existing codebases at scale.
Before any design or implementation step, the model captures relevant
knowledge in the Research section of the task file: existing behavior,
constraints, APIs, interfaces, and established code practices.

It follows the classic research–plan–implement approach, broken down
into small, incremental sub-tasks.

The research is explicitly scoped to the next increment. It captures only
what is required to implement that increment correctly, and is intentionally
partial. The result is a bounded, reviewable understanding whose size
remains manageable.

For large codebases, the glossary is especially useful because it
keeps domain terms stable across many increments, files, and
subsystems.

Because the scope can be kept reasonably small and the research is
written down, you can verify that the model examined the right parts of
the codebase, identified the correct interfaces, and aligned with
existing practices before any code is written. This is especially
valuable in legacy systems: it prevents clean-room redesigns and makes
incremental change safer.

## Document Roles and Lifetimes

Spec Loop uses more than one document type on purpose. They do not have
the same job or the same lifetime.

- Task files are short-lived working artifacts for the next concrete
  slice of work. They exist to drive research, review, implementation,
  and testing of that slice.
- ADRs capture durable decisions and the reasons behind them.
- A glossary captures stable shared language across tasks, design,
  tests, code symbols, and commits.
- Living project documents capture current truth that should remain
  useful after the task is accepted, such as technical shape,
  operations, or other stable project knowledge.

Historical task files do not need to be kept mutually consistent across
time. The active task, however, should stay aligned with the glossary,
living project documents, and implemented code for its scope.

If a project maintains a technical design document, its purpose is to
describe the current technical shape, stable boundaries, and important
flows. It should not become a second glossary or a catalog of transient
implementation detail.

## Getting started

Apply the process to your repository.

### Governance files

Preferred setup:
- Keep **[CONSTITUTION.md](CONSTITUTION.md)** in one stable shared or
  central location outside your project repositories. It does not need
  to live inside the project at all.
- Keep **[glossary-skill.md](glossary-skill.md)** next to that shared
  Constitution file.
- The shared Constitution directory may be anywhere convenient.
  Projects and user-level instruction files can reference it by
  absolute path.

Fallback setup:
- Copy the governance files into the project repository only when a
  shared external location is not practical.
- When the governance files are present in the project, relative paths
  are usually sufficient.

Glossary setup:
- A project opts in to glossary maintenance by creating
  `glossary.adoc` or by instructions that require it; otherwise it is
  opted out.
- Once `glossary.adoc` exists, it is part of the project workflow and
  must be maintained by the AI according to the active rules.
- `glossary.adoc` uses AsciiDoc because it provides stronger document
  structure and better support for cross-references and links than
  plain Markdown. AsciiDoc support in your IDE or editor is therefore
  required when `glossary.adoc` exists, and not required otherwise.

### Instruction loading options

There are three ways to make the LLM follow the Constitution.

1. Manual prompting

   Reference `CONSTITUTION.md` explicitly in your prompts so it is in
   the model context for the session.

2. User-level or global instructions

   Put the pre-flight rule that loads the Constitution into the
   tool-specific user instructions that apply across projects. In this
   setup, those global instructions typically reference the shared
   `CONSTITUTION.md` by absolute path.

3. Project instruction files

   Put the pre-flight rule into project-local instruction files such as
   `AGENTS.md`, `CLAUDE.md`, or `.github/copilot-instructions.md`.
   These instructions may reference `CONSTITUTION.md` by relative or by
   absolute path.

These approaches may be used independently or together. A common setup
is:
- user-level instructions enforce reading the shared Constitution,
- project instruction files add project-specific guidance.

This repo includes example project instruction files you can adapt:
[AGENTS.md](AGENTS.md) and
[.github/copilot-instructions.md](.github/copilot-instructions.md).

### Project-specific settings

- Define your task directory in project-specific instructions, for
  example by replacing `<TASK_DIR>` with a real path such as `tasks`.
- Keep project requirements and local repository guidance in the
  project instruction files even if Constitution loading is enforced
  globally.
- Then ask the model to create and update task files there and follow
  the workflow defined by the Constitution.


### Claude setup note

If you use Claude, `CLAUDE.md` can be used either as a project-local
instruction file or as a user-level instruction file, just like
`AGENTS.md`.

If you need a project-local `CLAUDE.md`, you can use the content of
`AGENTS.md` as the starting point and then add any extra
project-specific guidance you need. If Constitution loading is already
enforced by user-level instructions, `CLAUDE.md` can focus on
project-specific settings such as `<TASK_DIR>` and local requirements.

## Documentation

1. Check the Constitution briefly.

   * **[CONSTITUTION.md](CONSTITUTION.md)** defines the normative rules: task
     files, research/design discipline, approval gates, traceability
     requirements, and definition of done.

2. Study the Wordle example by commit history.

   * The [Wordle commit history](https://gitlab.com/dpolivaev/spec-loop/-/commits/main/examples/wordle)
     shows the workflow under real version-control pressure: how task
     specifications evolve step by step, and how implementation and tests
     follow approved design.

3. Check **[Review, Responsibility, and Traceability](docs/review-responsibility-and-traceability.md)**.
   It explains how task files and the Constitution map to team development practice: boundaries,
   responsibility, commit linking, and status discipline.

4. Follow one of the hands-on tutorials.

   * **[Wordle Tutorial](docs/wordle-tutorial.md)** walks through a
     compact Java example with staged planning, approvals,
     implementation, glossary maintenance, and testing.
   * **[Online Art Game Tutorial](docs/online-art-game-tutorial.md)**
     walks through a complete browser-oriented example with staged
     planning, approvals, implementation, and testing.
   * The two tutorials teach the same Spec Loop workflow: planning
     first, explicit approval before implementation, small reviewable
     tasks or subtasks, verification, and user correction when the LLM
     misses a supporting update. The main difference is the technical
     setting: Wordle is a compact Java path, while the online art game
     is browser-oriented. You can choose either tutorial.

5. Project glossary conventions.

   * See `Glossary setup` above.
   * **[glossary-skill.md](glossary-skill.md)** is the shared glossary
     guidance file and includes its own embedded example.

Recommended quick-check order:
- `README.md`
- `CONSTITUTION.md`
- `docs/review-responsibility-and-traceability.md`
- `docs/online-art-game-tutorial.md`
- `docs/wordle-tutorial.md`

## Diagrams: PlantUML default, Mermaid fallback

Spec Loop treats diagrams as specification artifacts: they make design
intent reviewable at the same boundary as the surrounding text.

Where the Constitution requires diagrams in task files, use PlantUML by
default.

Mermaid is a poorer but still possible alternative when the User or
another governing instruction explicitly prefers Mermaid, for example
when GitHub or similar environments are used and PlantUML is not
rendered.

PlantUML remains the recommended default in practice because it is
usually easier to keep precise and reviewable for the structural and
behavioral design work used in Spec Loop.

For inline PlantUML rendering in Markdown on the web, view the repo on
[GitLab](https://gitlab.com/dpolivaev/spec-loop). GitHub does not
render PlantUML embedded in Markdown natively, so reading there can
degrade the intended experience.

For local preview setup, use one of these guides:

* **[VS Code Setup (PlantUML and AsciiDoc)](docs/vscode-setup.md)**
* **[JetBrains Setup (PlantUML and AsciiDoc)](docs/jetbrains-setup.md)**

## License

Licensed under the MIT License. See [LICENSE](LICENSE).

## Origin

This framework was developed and applied in
Freeplane.
