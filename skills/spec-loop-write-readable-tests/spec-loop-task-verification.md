# Spec Loop task verification

Use this file when drafting, revising, or reconstructing a Spec Loop
`Test specification`. It defines how the task records designed tests
and checks, and the requirements for execution and review readiness.
It does not change workflow routes, phases, or approvals.

## Task requirements

- Keep Test specification in each current or implementation-ready task
  without subtasks. Initial backlog tasks and subtasks created by
  `spec-loop-plan-work-breakdown` may omit Test specification until
  current. No-code tasks being made
  ready for approval: set `Test specification` to `N/A` unless there
  is useful automated or manual verification content.
- Use `Automated checks` only for concrete
  repository-owned non-test automation anchors. `Manual tests` are
  optional human-reader hints for post-handoff review. Use them only
  when the same verification purpose cannot be covered adequately by
  automated tests or concrete automated checks.
- Do not list generic test execution commands, framework names, or
  pass/fail status in `Test specification`.
- Each implementation task without subtasks: include explicit
  task-specific verification content using the structure below.
- Run and fix the automated tests and automated checks that implement
  retained or added verification anchors plus any required project-level
  checks before moving a task-file task to `review`, before presenting
  chat-only work as ready, or before otherwise implying implementation
  closure, unless the User waives them. For anchors marked **-**, verify
  removal and required replacement coverage instead of executing the
  removed tests.

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

Preferred shape, using illustrative file paths as containers:

- **Automated tests:**
  - **+** `tests/new-feature.test.ts`
    - **+** `newTestCase`: required behavior.
  - **±** `tests/existing-feature.test.ts`
    - **+** `additionalTestCase`: additional required behavior.
    - **=** `unchangedTestCase`: existing verification reused unchanged.
    - **±** `modifiedTestCase`: verification to adapt or extend.
    - **-** `obsoleteTestCase`: obsolete expectation removed; replacement
      verification is `additionalTestCase`.
  - **-** `tests/removed-feature.test.ts`
    - **-** `removedFeatureTestCase`: removed feature's verification is
      removed with that feature.
  - **=** `tests/unchanged-feature.test.ts`
    - **=** `reusedTestCase`: existing verification reused unchanged.

For current or implementation-ready work, the task must identify exact
verification containers and case anchors. If those references are not
yet known, the task is not fully designed. For tasks with subtasks,
apply this readiness check to the current subtask plus needed
task-level context.

Prefix every verification container (test-file path, test class, or
other framework-natural container) and case anchor with one bold literal
text symbol, immediately before its code-formatted identity and outside
its backticks:

- **+** — add the element.
- **=** — reuse the existing element unchanged.
- **±** — modify the existing element.
- **-** — remove the existing element.

Classify containers and cases independently against the task's starting
state. An existing file or other container that gains, modifies, or loses
cases is **±**, not **+**; its new cases are **+**, unchanged cases remain
**=**, and removed cases are **-**. A removed container and its listed
cases are **-**. Establish that starting state from Research or relevant
context; merely listing an element does not establish that it already
exists.

Keep removed containers and cases explicit with their exact starting-state
identities. State which removed requirement makes the verification obsolete
or identify replacement verification that preserves the required coverage.
Removed anchors are deletion expectations; apply the execution and
review-readiness checks under [Task requirements](#task-requirements).
Do not remove still-required coverage merely to make a test suite pass.

These annotations are not part of paths or executable test names and do
not indicate implementation progress or test results. Preserve the
container/case hierarchy and behavior descriptions. Do not add legends
or worded status labels to the task's `Test specification`. This rule
applies only to Spec Loop `Automated tests` entries, not standalone test
code, `Automated checks`, or `Manual tests`.

If an existing unchanged test is listed, it is listed because the task
intentionally relies on it for verification, not merely because it
exists or seems related.

Existing tests belong in `Research` when their current presence affects
design, placement, naming, ownership, coverage gaps, or reuse
constraints.

Illustrative starting context: `OptimizationTaskModificationTest` and its
listed cases exist: one needs modification and one is removed with the
obsolete optimization behavior. The JSON spec file and its case are new;
the feature file and its case are reused unchanged.

- **Automated tests:**
  - **±** `OptimizationTaskModificationTest`
    - **±** `changedScopedFieldsModifyReferenceTask`: changing scoped
      fields marks the reference optimization task as modified.
    - **-** `everyEditMarksReferenceTaskModified`: remove the expectation
      that edits outside the scoped fields modify the reference task;
      `changedScopedFieldsModifyReferenceTask` covers the scoped behavior.
  - **+** `catalog-offers-json-modification.spec.ts`
    - **+** `Catalog offers JSON > serialization > orders JSON`:
      catalog-offer JSON serialization orders properties and collection
      elements.
  - **=** `catalog-offer-modification.feature`
    - **=** `ONLY_MODIFIED omits catalog offers`: `ONLY_MODIFIED` omits
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
