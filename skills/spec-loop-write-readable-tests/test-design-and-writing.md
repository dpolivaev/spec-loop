# Test design and writing

Test design specifies how the work's requirements and modified code
paths are verified. A case's purpose and name belong to that design.
This guide also covers readable test bodies and works without Spec Loop.

## Coverage

Prefer automated tests.

Aim for broad relevant coverage: cover the described requirements,
changed behavior, affected contracts, important edge cases, regressions,
and modified decision branches with meaningful assertions for each
expected outcome where practical.

When formal numbered requirements exist, tests may reference them.
Otherwise, the requirements are the behavior, contracts, constraints,
edge cases, and risks described in the work.

Before choosing tests for verification, inspect relevant existing tests:
their assertions, fixtures, and verification boundaries. Add or extend
tests for relevant coverage gaps; unchanged production code may still
need new verification for a new caller. Test presence alone does not
establish coverage.

Automated tests must verify each acceptance behavior at the closest
supported relevant boundary practical for the work. Direct service,
application, handler, or helper tests may supplement coverage, but they
do not satisfy an acceptance case whose behavior is defined at a
broader boundary. For rendered UI behavior, the acceptance case is not
satisfied unless a test, at any test level, exercises the rendered
control or event path, such as submit, click, change, navigation, or
keyboard input, unless the work states why that boundary is
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
through the supported external or relevant integration boundary.
Test invalid internal inputs only when they are reachable,
intentionally handled, or part of the approved internal contract.

## Choose tests and names

- Test behavior and contracts, not passive structure. Do not add
  standalone tests for trivial constructors, getters, setters, or
  exception subclasses with no custom behavior. Test required failure
  behavior at the caller instead.
- Skip standalone tests of trivial default serialization or
  deserialization. When you customize either, test the custom behavior,
  such as JSON field mappings, conversions, or handling rules. Required
  validation and custom equality are behavior too.
- Base cases on required behavior and supported states, not merely
  states a fixture can manufacture.
- Names describe purpose and expected behavior in plain domain
  language, not internal calls. Do not encode every asserted interaction
  in the name.
- Name only what the test actually proves. A mocked conflict checker
  can prove that checking is requested, not that purchases match.
- For naming before implementation, use the intended scenario and
  outcome. Ask only when the expected behavior is materially unclear.
  If a name is rejected, reconsider its meaning rather than swap words.

## Identify test cases

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

For hierarchical test frameworks, do not add arbitrary extra bullet
levels for every hierarchy level. Flatten hierarchy into the case
anchor.

## Keep the scenario visible

- Let the reader see the setup, direct operation, and essential result
  without decoding a helper or dispatcher.
- Arrange mock outcomes explicitly when they determine the branch or
  expected result. Defaults are fine for irrelevant behavior.
- Parameterize data when the scenario stays clear. Separate cases when
  numeric or boolean dispatch hides different setup, actions, or
  outcomes. Booleans as ordinary input data are not the problem.
- Share fixture noise and substantial reusable assertions only when
  the test's purpose and expected result remain visible.
- Preserve meaningful assertions when simplifying, including ordering
  and absence of side effects when those are required behavior.

Examples:
- Prefer `repeatedConfirmationDoesNotSaveOptimizations` to
  `checksIdempotentWriteForPurchaseConfirmationWithoutSavingOptimizations`.
- Parameterize price values for one comparison scenario. Do not hide
  different public methods behind `storeThroughEntryPoint(0/1/2, ...)`.

## Review your changes

Before handoff, re-read every test and name you added or changed.
Check that its name matches its assertions, its important mock outcomes
are explicit, and simplification has not removed coverage. Align
related test references through the active workflow. Leave unrelated
existing tests alone unless the User includes them in scope.
