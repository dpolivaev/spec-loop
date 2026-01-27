# Task: Setup Gradle Java project configuration based on Kotlin Gradle variant

- **Task Identifier:** 2026-01-25-gradle-setup
- **Scope:** Define and implement a Gradle Java project structure
  for the Wordle example that mirrors the conventions of the Kotlin
  Gradle variant (single-module, Gradle wrapper, Kotlin DSL build
  scripts, standard source layout).
- **Motivation:** Provide a consistent Java build setup so the Wordle
  implementation can proceed without build friction and stays aligned
  with the Kotlin variant conventions.
- **Developer Briefing:** The repo currently has no Gradle
  configuration for Wordle, so we need to add a Java-oriented Gradle
  setup that follows the Kotlin-variant conventions (wrapper,
  `settings.gradle.kts`, `build.gradle.kts`, standard `src/main/java`
  and `src/test/java` layout). The design below specifies the files,
  plugins, latest Gradle wrapper version (9.3.0), Java toolchain, and
  test setup to enable later implementation tasks.
- **Research:** The Wordle example currently contains documentation and
  tasks only; there is no existing Gradle wrapper, build script, or
  source tree in `examples/wordle`. The Kotlin Gradle variant
  referenced by the task is not present in this repository, so the
  configuration must be derived from typical Gradle Kotlin-DSL
  conventions and documented explicitly. The current Gradle release is
  9.3.0, released January 16, 2026, so the wrapper should target 9.3.0.
- **Design:** Create a single-module Gradle project under
  `examples/wordle/` using Kotlin DSL build files. Add `gradlew`,
  `gradlew.bat`, and `gradle/wrapper/` to mirror Kotlin variant
  ergonomics. Use `settings.gradle.kts` to set a clear project name
  (for example, `wordle-java`). In `build.gradle.kts`, apply the `java`
  and `application` plugins, configure a Java toolchain (JDK 21), set
  UTF-8 encoding, and add JUnit 5 with `useJUnitPlatform()` plus the
  JUnit Platform launcher runtime dependency. Keep dependencies minimal
  and aligned with the Kotlin variant (no extra frameworks unless
  required). Establish the standard layout: `src/main/java`,
  `src/test/java`, and `src/main/resources`. Set the Gradle wrapper
  distribution URL to
  `https://services.gradle.org/distributions/gradle-9.3.0-bin.zip` so
  the project stays on the latest release. Document how the project is
  run (Gradle `run` task) and how tests are executed (`test`).
- **Test specification:** Verification is via Gradle tasks:
  `./gradlew test` runs a minimal placeholder test (or at least a
  compile-only test class) to confirm JUnit wiring; `./gradlew run`
  executes a minimal entry point when added in later tasks. Ensure the
  Gradle wrapper and JDK 21 toolchain resolution are validated by a
  clean build.
