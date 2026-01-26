# Introduction

Modern coding models can generate useful code quickly, but they also tend to:

- expand scope without being asked,
- “helpfully” invent requirements,
- implement before design is stable,
- treat tests as optional or secondary,
- produce changes that are difficult to review or trace to a ticket,
- apply partial edits when something goes wrong.

These behaviors make AI risky in real projects, especially in team environments where work must remain reviewable, explainable, and verifiable.

This repository defines a process framework for using a coding model (for example, Codex) in a way that remains compatible with professional software development: explicit scope, review boundaries, reproducible outcomes, and tests as part of completion. The goal is to make AI-assisted development behave like disciplined engineering work: small vertical increments, explicit decision points, and a clear definition of “done”.

## Why specification-driven development often turns into waterfall

Specification-driven development (SDD) is appealing because it promises clarity: write the specification first, implement later. In practice, it often reproduces classic waterfall failure modes:

- **Specifications are written too early.** Important constraints are discovered only during implementation.
- **Specifications grow too large.** They try to describe an entire system instead of the next meaningful step.
- **Change becomes expensive.** When reality diverges from the spec, the process resists correction.
- **Specifications lose authority.** They either block progress or get ignored.

When AI is added to the loop, this problem becomes more severe. Models strongly prefer to “complete” designs and implementations in one pass, amplifying scope creep and premature commitment.

## Agile-compatible specification: design per step

This framework preserves the main benefit of SDD — explicitness — without inheriting its waterfall behavior.

Instead of one large upfront specification, it uses **incremental, step-local specifications**:

- scoped to the next meaningful increment,
- written close to implementation,
- explicitly reviewed before execution,
- and completed only when tests are implemented.

This is **design per step**, not design up front.

A task file is not a historical log. It is a living description of the *current* understanding required to act correctly.

History is stored in version control. The task file represents the present truth.

## Non-goals

This framework does not aim to make the model autonomous. It is designed to keep work:

- scoped and reviewable,
- anchored in explicit understanding,
- verifiable (tests are part of completion),
- and traceable through version control.

The canonical workflow rules live in [CONSTITUTION.md](../CONSTITUTION.md).

## License

This work is licensed under the **Creative Commons Attribution 4.0 International (CC BY 4.0)** license. See [LICENSE](../LICENSE).
