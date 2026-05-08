---
name: plan-task
description: >-
  Use to begin non-trivial work on features, bug fixes,
  refactorings, and changes to code, tests, configuration,
  dependencies, runtime assets, or design. This skill creates,
  manages, and verifies the required task files. It covers
  implementation preparation, readiness checks, and the explicit user
  approval gate before implementation. Mandatory unless the user
  explicitly opts out for the current project or session.
---

Use this skill to begin non-trivial work on features, bug fixes,
refactorings, and changes to code, tests, configuration,
dependencies, runtime assets, or design.

It creates, manages, and verifies the required task files.

It covers implementation preparation, readiness checks, and the
explicit user approval gate before implementation.

This skill is mandatory unless the user explicitly opts out for the
current project or session.

This skill is defined by [constitution.md](./constitution.md).

The very first required action before using this skill is to read
that file to the end, unless its contents are already known in the
current session context.

You must comply with it fully before proceeding. After confirming
that, immediately emit 🫡.

Read and apply project instructions such as `AGENTS.md` when present.

If project instructions do not define a task directory, use `tasks/`
as the default.

Default glossary policy:

- glossary use is opted in;
- project or session instructions may opt out;
- when the project uses the Spec Loop AsciiDoc glossary, use
  `write-glossary`;
- otherwise follow the project's glossary format.

If implementation must meaningfully deviate from the approved task
content, use this skill again before continuing.
