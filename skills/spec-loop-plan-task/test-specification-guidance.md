# Test specification guidance

Read and follow this file just before drafting or revising a task
`Test specification`. Return to [common-task-guidance.md](common-task-guidance.md) afterward.

This file defines the testing policy and what belongs in `Automated
tests` and `Manual tests`. It is not a separate workflow and does not
change route, phase, or approval rules.

## Purpose

`Test specification` specifies how the current task's requirements and
task-relevant modified code paths are verified.

Aim for broad task-relevant coverage: cover the described requirements,
changed behavior, affected contracts, important edge cases, regressions,
and modified decision branches with meaningful assertions for each
expected outcome where practical.

When formal numbered requirements exist, tests may reference them.
Otherwise, the requirements are the behavior, contracts, constraints,
edge cases, and risks described in the task.

## Testing Policy

- Keep Test specification in each current or implementation-ready task
  without subtasks. Initial backlog tasks and subtasks created by
  [spec-loop-plan-work-breakdown/SKILL.md](../spec-loop-plan-work-breakdown/SKILL.md)
  may omit Test specification until current. No-code tasks being made
  ready for approval: set `Automated tests: N/A` and
  `Manual tests: N/A`.
- Separate test-focused tasks allowed when adding or extending
  coverage as standalone scope.
- Prefer automated tests and automated checks. `Manual tests` are
  optional human-reader hints for post-handoff review. Use them only
  when the same verification purpose cannot be covered adequately by
  automated tests.
- Do not list test execution commands, framework names, or pass/fail
  status in `Test specification`.
- Each implementation task without subtasks: include an explicit
  `Automated tests` sublist of task-specific verification cases.
  Include a `Manual tests` sublist only for useful optional
  human-reader checks that cannot be automated enough for the task;
  otherwise set `Manual tests: N/A`.
- Run and fix the automated tests that implement the specified cases
  and any required project-level checks before moving a task-file task
  to `review`, before presenting chat-only work as ready, or before
  otherwise implying implementation closure, unless the User waives
  them.

## Automated tests

`Automated tests` is a list of task-specific automated verification
cases. It is not a test execution plan, test log, framework inventory,
or pass/fail report.

For example-based automated tests, use concise behavior/assertion
bullets. Each bullet must make the tested condition and expected
observable result clear enough to implement meaningful assertions.
Use `Given / When / Then` only when it makes the case clearer, such as
for multi-step flows, state transitions, or otherwise ambiguous cases.

Good examples:

- Visible session completion persists the session in the
  visible-session list.
- Background run completion does not add the run to the
  visible-session list.
- Background path failure leaves the visible-session list unchanged.

For property-based, stateful, model-based, or similar automated tests,
state the properties, invariants, generated input spaces, state
transitions, or contracts being checked.

For supported external contracts, such as user-facing APIs, CLIs,
serialized formats, configuration, persistence, plugin extension
points, documented integration points, and compatibility promises,
include relevant invalid input, boundary, compatibility, and expected
error-handling cases.

For internal implementation code, even when classes or methods are
language-public, do not over-specify tests for impossible or
unsupported illegal arguments. Focus on valid reachable states, happy
paths, important logic branches, invariants, and behavior observable
through the supported external or task-relevant integration boundary.
Test invalid internal inputs only when they are reachable,
intentionally handled, or part of the approved internal contract.

Do not list test execution commands, framework names, or statements
such as "tests pass" in `Test specification`. Those are project-level
execution details or implementation-time verification results, not the
task-level Test specification.

If a check can be automated enough for the task, put it under
`Automated tests`, not `Manual tests`.

## Manual tests

`Manual tests` are optional human-review hints after handoff. They do
not block `review` and must not be reported as done unless actually
performed.

Use `Manual tests` only for useful human checks that cannot be
automated enough for the task. Otherwise set `Manual tests: N/A`.

Do not move automatable verification into `Manual tests`.

## Relation to other task sections

Do not restate `Analysis` points in decision-and-reason form. Record
the resulting verification consequences instead.

When task `Glossary` exists, `Test specification` must reuse approved
terms from `Scenario`, `Glossary`, and `Design`.
