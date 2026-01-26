# Task: Use AssertJ in tests
- **Task Identifier:** 2026-01-26-assertj
- **Scope:** Replace JUnit assertions with AssertJ in test code and add AssertJ dependency.
- **Motivation:** Improve test readability and consistency across the suite.
- **Developer Briefing:** This task introduces AssertJ and updates existing tests to use its fluent assertions. The work is confined to test code and build configuration, with no production code changes.
- **Research:** The current tests use JUnit Jupiter assertions in `wordle.domain`, `wordle.words`, and `wordle.engine` test packages. Build uses Gradle Kotlin DSL and currently depends on JUnit Jupiter only.
- **Design:** Add `org.assertj:assertj-core` as a test dependency in `examples/wordle/build.gradle.kts`. Replace JUnit assertions with AssertJ:
  - `assertTrue(condition)` -> `assertThat(condition).isTrue()`
  - `assertNotNull(value)` -> `assertThat(value).isNotNull()`
  - `assertThrows` -> `assertThatThrownBy(...)` or `assertThatExceptionOfType(...)`
  - Stream `allMatch`/`anyMatch` -> `assertThat(collection).allMatch(...)` / `anyMatch(...)` where clearer
  Keep test logic identical; only assertion style changes.
- **Test specification:**
  1. Run `./gradlew test` and ensure all tests pass.
  2. Verify no JUnit assertion static imports remain in test files.
