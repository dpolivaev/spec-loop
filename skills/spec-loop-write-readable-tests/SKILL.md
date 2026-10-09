---
name: spec-loop-write-readable-tests
description: >-
  Choose behavioral names and keep automated tests readable when
  designing, writing, changing, or reviewing them. Use for naming
  alone, even before test bodies exist. Make each test's setup,
  action, and expected result easy to see. Works with or without
  Spec Loop.
---

# Write readable tests

Follow approved scope and project rules. This skill does not grant
editing permission or replace coverage and verification requirements.
It works with or without Spec Loop.

## Choose tests and names

- Inspect existing tests' assertions and boundaries before adding
  tests. A new caller may justify testing unchanged code.
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
