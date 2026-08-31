# Test specification guidance

Read and follow this file just before drafting or revising a task
`Test specification`. Return to [common-task-guidance.md](common-task-guidance.md) afterward.

This file defines the testing policy and what belongs in `Automated
tests`, `Automated checks`, and `Manual tests`. It is not a separate
workflow and does not change route, phase, or approval rules.

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
  ready for approval: set `Test specification` to `N/A` unless there
  is useful automated or manual verification content.
- Separate test-focused tasks allowed when adding or extending
  coverage as standalone scope.
- Prefer automated tests. Use `Automated checks` only for concrete
  repository-owned non-test automation anchors. `Manual tests` are
  optional human-reader hints for post-handoff review. Use them only
  when the same verification purpose cannot be covered adequately by
  automated tests or concrete automated checks.
- Do not list generic test execution commands, framework names, or
  pass/fail status in `Test specification`.
- Each implementation task without subtasks: include explicit
  task-specific verification content using the structure below.
- Run and fix the automated tests and automated checks that implement
  the specified verification anchors plus any required project-level
  checks before moving a task-file task to `review`, before presenting
  chat-only work as ready, or before otherwise implying implementation
  closure, unless the User waives them.

## Structure

Use `Automated tests` for test-framework verification anchors,
`Automated checks` only for concrete repository-owned non-test
automation anchors, and `Manual tests` only for useful manual
verification hints.

Omit irrelevant subsections. If no verification content exists, set the
whole `Test specification` to `N/A`.

When more than one subsection exists, use this order:

1. `Automated tests`
2. `Automated checks`
3. `Manual tests`

## Automated tests

`Automated tests` is a list of task-specific automated verification
anchors. It is not a test execution plan, test log, framework
inventory, or pass/fail report.

Each automated test entry identifies:

- the verification container; and
- the verification case anchor plus the behavior, assertion, property,
  or contract the task relies on.

Preferred shape:

- **Automated tests:**
  - `VerificationContainer`
    - `verification case anchor`: expected behavior, assertion,
      property, or contract.

The verification container is the navigation-stable place where the
case lives, such as a test class, spec file, feature file, test file,
or stable suite/container when that is the project's natural identity.
The verification case anchor is the executable or reportable test
identity inside that container.

Use the framework-natural identity that lets a developer navigate to
the test with least ambiguity:

- JUnit/plain Java: test class plus method name.
- JUnit nested tests: test class plus `NestedClass > methodName` when
  the nested class is part of the navigable identity.
- JavaScript test frameworks such as Jest, Vitest, and Mocha: spec
  file plus flattened `describe > ... > it/test` path.
- Cucumber: feature file plus `Rule > Scenario`, scenario outline, or
  other stable scenario path.
- pytest: test file plus function, or test file plus
  `TestClass > function`.
- Playwright/Cypress: spec file plus flattened suite/test title.

Display names are allowed when they are the primary navigable or report
identity. Prefer code identifiers when both exist and code identifiers
are unambiguous.

Names are part of the design for current or implementation-ready work.
If exact verification containers or case anchors cannot be named for
the current work, the task is not fully designed yet.

A listed verification anchor may be newly added, modified, or
intentionally reused unchanged. Do not mark that status unless the
distinction affects scope. If an existing unchanged test is listed, it
is listed because the task intentionally relies on it for verification,
not merely because it exists or seems related.

Existing tests belong in `Research` when their current presence affects
design, placement, naming, ownership, coverage gaps, or reuse
constraints.

For hierarchical test frameworks, do not add arbitrary extra bullet
levels for every hierarchy level. Flatten hierarchy into the case
anchor.

Examples:

- **Automated tests:**
  - `OptimizationTaskModificationTest`
    - `checksIfItModifiesReferenceTask`: scoped fields affect
      `OptimizationTaskModification.modifiesReferenceOptimizationTask(...)`
      consistently with existing scoped modification fields.
  - `catalog-offers-json-modification.spec.ts`
    - `Catalog offers JSON > serialization > orders JSON`: catalog-offer
      JSON serialization orders properties and collection elements.
  - `catalog-offer-modification.feature`
    - `ONLY_MODIFIED omits catalog offers`: `ONLY_MODIFIED` omits
      catalog offers from the reference modification.

For property-based, stateful, model-based, generated, parameterized,
or scenario-outline tests, list the verification anchor once and state
the generated input space, examples-table intent, state transitions,
invariants, or contracts being checked. List individual rows or
examples only when their identity matters to scope or review.

For snapshot, golden-master, or approval tests, list the executable
verification anchor. Do not list snapshot, golden, or approved artifact
paths as separate anchors. If artifact content matters, describe the
approved or asserted behavior after the case anchor.

Automated tests must verify each acceptance behavior at the closest
supported task-relevant boundary practical for the task. Direct service,
application, handler, or helper tests may supplement coverage, but they
do not satisfy an acceptance case whose behavior is defined at a
broader boundary. For rendered UI behavior, the acceptance case is not
satisfied unless a test, at any test level, exercises the rendered
control or event path, such as submit, click, change, navigation, or
keyboard input, unless the task states why that boundary is
impractical. Do not require a broader end-to-end boundary when the
approved behavior is internal and no externally observable or
supported-contract behavior changes.

When behavior crosses UI, representation, rendering, persistence,
messaging, routing, process, or side-effect boundaries, automated tests
must assert the contract-relevant conversion, output/state, timing, and
payload/content as applicable.

For user-reported defects or review fixes, automated tests must include
a regression case that would have failed against the reported broken
behavior unless impossible; if impossible, state why and identify the
closest automated substitute.

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

If a check can be automated enough for the task, put it under
`Automated tests` or `Automated checks`, not `Manual tests`.

## Automated checks

`Automated checks` is an edge-case subsection for concrete
repository-owned automated verification that is not naturally a
test-framework case. Include it only when the task relies on a specific
owned check artifact.

Allowed anchors include repository-owned scripts, tools, custom check
tasks, or named CI/report artifacts, such as:

- `scripts/validate-doc-links.sh`
- `tools/check_generated_docs.py`
- `verifyOpenApiCompatibility`
- `openapi-compatibility` CI job

Do not put generic build, test, typecheck, or configuration-load
commands in `Automated checks`.

For build/setup/migration tasks, include `Automated tests` or
`Automated checks` only when there is a task-specific test or owned
check artifact to name.

If there is no task-specific verification anchor, use
`Test specification: N/A`.

Do not phrase automated checks as execution instructions or say "run
tests". State the automated condition being verified after the anchor.

Good shape:

- **Automated checks:**
  - `scripts/validate-doc-links.sh`: documentation links resolve.
  - `verifyOpenApiCompatibility`: generated API schema remains backward
    compatible.

## Manual tests

`Manual tests` are optional human-review hints after handoff. They do
not block `review` and must not be reported as done unless actually
performed.

Use `Manual tests` only for useful human checks that cannot be
automated enough for the task. Omit the subsection when there are no
real manual checks. Manual tests use plain bullets; they do not have
stable executable anchors.

Do not move automatable verification into `Manual tests`.

## Relation to other task sections

Do not restate `Analysis` points in decision-and-rationale form. Record
the resulting verification consequences instead.

When task `Glossary` exists, `Test specification` must reuse approved
terms from `Scenario`, `Glossary`, and `Design`.
