# Task: Add CLI game interface

- **Task Identifier:** 2026-01-25-cli-interface
- **Scope:** Implement a command-line experience for playing Wordle,
  including input loop, feedback rendering, and CLI usage documentation.
- **Motivation:** Provide a simple interface that exercises the core engine
  and offers clear operational instructions.
- **Scenario:** A user launches the Wordle command, enters guesses in a
  terminal, receives feedback after each guess, and sees a final game
  result when the session ends.
- **Briefing:** CLI work spans entry-point wiring, interactive loop behavior,
  output rendering, and README guidance. Read the subtasks in order because
  later documentation assumes the runtime path already exists.
- **Research:** The codebase had engine and domain layers but no CLI entry
  point, no argument parsing, and no terminal feedback formatter.
- **Design:**

  ```plantuml
  @startuml
  set separator none
  package "wordle" {
    package "cli" {
      class Main
      class CliOptions
      class CliGameRunner
      class FeedbackRenderer
    }
    package "engine" {
      class GameEngine
      class GameState
      enum GameStatus
    }
  }
  
  Main --> CliOptions : parses command options
  Main --> CliGameRunner : starts interactive session
  CliGameRunner --> GameEngine : submits guesses
  CliGameRunner --> GameState : tracks state transitions
  CliGameRunner --> FeedbackRenderer : formats rows
  CliGameRunner --> GameStatus : reports final result
  @enduml
  ```

  CLI mode supports configurable attempts and optional external word-list
  source while preserving engine-centric gameplay logic.
- **Test specification:** Covered by subtask test specifications.

## Subtask: Implement CLI parsing and game loop

- **Status:** done
- **Scope:** Add picocli-based command parsing and an interactive game loop.
- **Motivation:** Provide a working CLI entry point with configurable input
  sources and attempt limits.
- **Scenario:** The user runs the command with or without options. The
  command starts a game, repeatedly reads guesses, and exits after printing
  `Result: WON`, `Result: LOST`, or `Result: INTERRUPTED`.
- **Briefing:** Start at `Main` and the CLI entry path, then trace into the
  runner that owns input and result reporting. Invalid guesses should be
  handled in the loop without consuming attempts.
- **Research:** There was no existing command parser or loop abstraction in
  the project before this subtask.
- **Design:**

  ```plantuml
  @startuml
  set separator none
  package "wordle" {
    package "cli" {
      class Main
      class CliOptions
      class CliGameRunner
    }
    package "engine" {
      class GameEngine
      class GameState
      enum GameStatus
    }
  }
  
  Main --> CliOptions : parse args
  Main --> CliGameRunner : execute session
  CliGameRunner --> GameEngine : start game and submit guess
  CliGameRunner --> GameState : receive updated state
  CliGameRunner --> GameStatus : determine terminal output
  @enduml
  ```

  CLI options include attempts and optional word-list source. Interactive
  input is read from standard input, and run completion always returns
  process exit code `0` after printing final result text.
- **Test specification:**
  - **Automated tests:**
    - `CliOptionsTest`
      - `defaultsApplyWhenNoArgsProvided`: verify option defaults are
        applied when no CLI args are passed.
      - `emptyInputLineIsRejectedAndGameContinues`: verify empty input
        is reported, does not decrement attempts, and the game can
        still finish with `Result: WON`.
      - `invalidGuessIsRejectedAndDoesNotConsumeAttempt`: verify
        invalid guesses are reported and do not decrement attempts.
      - `wordlistUrlIsAccepted`: verify `--wordlist` URL source is
        accepted and loaded.
      - `inputEndIsReportedAsInterrupted`: verify ended input reports
        `Result: INTERRUPTED`.
  - **Manual tests:**
    - Start the CLI and confirm interactive terminal input works.

## Subtask: Implement feedback rendering

- **Status:** done
- **Scope:** Add deterministic feedback formatting for CLI output rows.
- **Motivation:** Make game feedback readable in terminal sessions.
- **Scenario:** After each valid guess, the CLI prints a row that encodes
  letter statuses in a stable textual format.
- **Briefing:** This subtask is isolated to CLI presentation. Follow the
  feedback model produced by the engine and keep the output format stable
  and readable.
- **Research:** No renderer utility existed before this subtask.
- **Design:**

  ```plantuml
  @startuml
  set separator none
  package "wordle" {
    package "cli" {
      class FeedbackRenderer
    }
    package "engine" {
      class GameState
    }
  }
  
  FeedbackRenderer --> GameState : reads latest feedback entries
  @enduml
  ```

  Renderer output is deterministic for a given `GameState` so tests can
  assert exact expected strings.
- **Test specification:**
  - **Automated tests:**
    - `FeedbackRendererTest`
      - `renderLastFeedbackWithMarkers`: verify renderer outputs
        expected status markers for known feedback inputs.
      - `renderEmptyHistoryReturnsEmptyString`: verify empty history
        renders as an empty string.

## Subtask: Document CLI build and usage

- **Status:** done
- **Scope:** Document CLI build, run, and usage steps in the Wordle README.
- **Motivation:** Ensure users can run the CLI without guessing commands.
- **Briefing:** This is README orientation for the sections that describe CLI
  build, run, and command usage after the CLI path exists.
- **Research:** Documentation existed but did not include complete CLI usage.
- **Design:**

  ```plantuml
  @startuml
  set separator none
  package "wordle" {
    package "docs" {
      class "README.md" as ReadmeFile
    }
    package "cli" {
      class CliOptions
    }
  }
  
  ReadmeFile --> CliOptions : documents arguments and examples
  @enduml
  ```

  README content is the only artifact changed in this subtask.
- **Test specification:** N/A

## Subtask: Package distribution

- **Status:** done
- **Scope:** Document Gradle application distribution archives and runtime
  scripts.
- **Motivation:** Provide a shareable package workflow beyond direct Gradle
  `run` usage.
- **Briefing:** This is README packaging orientation layered on top of the
  application plugin outputs under `build/distributions`.
- **Research:** Gradle application plugin produces distributions under
  `build/distributions` with `bin/` and `lib/` contents.
- **Design:**

  ```plantuml
  @startuml
  set separator none
  package "wordle" {
    package "docs" {
      class "README.md" as ReadmeFile
    }
    package "distribution" {
      class DistZip
      class DistTar
      class BinScript
    }
  }
  
  ReadmeFile --> DistZip : documents build command
  ReadmeFile --> DistTar : documents build command
  ReadmeFile --> BinScript : documents runtime invocation
  @enduml
  ```

  This subtask is documentation-only and does not modify runtime behavior.
- **Test specification:** N/A
