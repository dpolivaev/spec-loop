# Task: Setup Gradle Java project configuration based on Kotlin Gradle variant

- **Task Identifier:** 2026-01-25-gradle-setup
- **Scope:** Define and implement a Gradle Java project structure for the
  Wordle example that mirrors the conventions of the Kotlin Gradle variant
  (single-module, Gradle wrapper, Kotlin DSL build scripts, and standard
  source layout).
- **Motivation:** Provide a consistent Java build setup so the Wordle
  implementation can proceed without build friction and stay aligned with
  the Kotlin variant conventions.
- **Briefing:** Start with the Gradle wrapper files, project naming, and Java
  toolchain wiring. Later tasks assume tests and dependencies run without
  extra bootstrap work.
- **Research:** The Wordle example initially contained documentation and
  tasks only. No wrapper scripts, build script, or Java source tree existed.
  The latest Gradle release at task time was 9.3.0, so wrapper configuration
  should target that version.
- **Design:**

  ```plantuml
  @startuml
  set separator none
  package "wordle" {
    package "build" {
      class "settings.gradle.kts" as SettingsFile
      class "build.gradle.kts" as BuildFile
      class "gradle-wrapper.properties" as WrapperProperties
      class "gradlew / gradlew.bat" as WrapperScripts
      class "src/main/java" as MainSource
      class "src/test/java" as TestSource
      class "src/main/resources" as ResourceSource
    }
  }
  
  SettingsFile --> BuildFile : declares project name
  BuildFile --> MainSource : compiles production code
  BuildFile --> TestSource : runs JUnit Platform tests
  BuildFile --> ResourceSource : packages runtime resources
  WrapperScripts --> WrapperProperties : resolves Gradle 9.3.0
  @enduml
  ```

  The build stays single-module and uses the Java and application plugins,
  Java 21 toolchain, UTF-8 compilation, and JUnit 5 test execution with
  `useJUnitPlatform()`.
- **Test specification:**
  - Automated tests:
    - Run `./gradlew test` to verify JUnit wiring and test task execution.
    - Run `./gradlew tasks` to verify wrapper and project configuration load.
  - Manual tests:
    - Run `./gradlew run` after adding an entry point and confirm startup.
