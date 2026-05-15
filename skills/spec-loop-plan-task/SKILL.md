---
name: spec-loop-plan-task
description: >-
  Mandatory unless the user explicitly opts out. Use when
  non-trivial work on features, bug fixes, refactorings, or
  changes to code, tests, configuration, dependencies, runtime
  assets, or design requires creating or updating task-based
  planning artifacts.
---

This skill is mandatory unless the user explicitly opts out.

Create or update the required task files under the shared workflow
Constitution.

Draft or revise the active task: capture research, Scenario when
required, Design, Test specification, and task administration needed
for the current increment.

Before returning planning output as complete or seeking
implementation approval, you must use
[../spec-loop-prepare-implementation-approval/SKILL.md](../spec-loop-prepare-implementation-approval/SKILL.md).

This skill is mandatory unless the user explicitly opts out for the
current project or session. When this skill is in use,
`spec-loop-prepare-implementation-approval` is part of the same
mandatory workflow and is not independently optional.

This skill is defined by [constitution.md](./constitution.md).
The first required action is to read that file to the end unless its
contents are already known in the current session context. You must
comply with it fully before proceeding. After confirming that,
immediately emit 🫡.

Read and apply project instructions such as `AGENTS.md` when present.

When drafting or repairing embedded PlantUML in task files, follow
`examples/example-task-wordle-cli.md` as a collection of valid diagram
patterns and section-placement examples.
Do not treat it as a required task length or as a model for how much
detail every task or first planning pass must contain.

If project instructions do not define a task directory, use `tasks/`
as the default.

Default glossary policy:

- glossary use is opted in;
- project or session instructions may opt out;
- when the project uses the AsciiDoc glossary format defined by
  `spec-loop-write-glossary`, use `spec-loop-write-glossary`;
- otherwise follow the project's glossary format.

If implementation must meaningfully deviate from the approved task
content, use this skill again before continuing.
