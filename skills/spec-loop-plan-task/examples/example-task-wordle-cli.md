# Example task: Wordle CLI adapter with embedded diagrams

This compact example task shows valid embedded Mermaid and PlantUML
for common task-diagram needs. It is primarily a collection of valid
diagram patterns in a realistic task context, not a required minimum
task size and not a signal that every first planning pass should be
this detailed.

It covers:

- task `Glossary` with Mermaid visual glossary
- filesystem / project structure
- component interaction
- class structure
- sequence flow

It also demonstrates the Markdown-sensitive case where task sections
are list items, diagrams stay inside those list items, and more
bullets follow the diagrams without rendering as code blocks.

Keep different concerns in separate diagram blocks instead of mixing
diagram types.

PlantUML writing hints:

- Follow the PlantUML-specific rules in
  [task-file-path-guidance.md](../task-file-path-guidance.md).
- This example keeps line-oriented PlantUML statements on one physical
  line.
- If rendering fails, fix the diagram before treating the task as ready
  for review.

- **Scope:** Add a CLI adapter for Wordle that starts a game, reads
  guesses from standard input, renders CLI feedback, and keeps
  gameplay rules in the existing engine and domain classes.
- **Motivation:** This example demonstrates current task structure and
  diagram patterns that render reliably in Markdown task files.
- **Scenario:** A player starts the application in CLI mode, enters
  guesses one line at a time, sees CLI feedback after each guess, and
  the session ends when the word is solved or attempts are exhausted.
  Optional flags choose the word list path and attempt count.
- **Glossary:**
  - **CLI mode:** application mode where the player enters guesses
    through standard input and reads CLI feedback in the terminal.
    - In CLI mode, the player enters one guess per input line.
    - In CLI mode, the session ends when the word is solved or
      attempts are exhausted.
  - **CLI feedback:** terminal-visible rendering of one guess result.
    - CLI feedback is shown after each guess.
    - CLI feedback belongs to the CLI adapter, not to the gameplay
      rules.
  - **Word list path:** optional CLI input selecting the source word
    list.
    - The word list path is chosen through a CLI flag.
  - **Attempt count:** optional CLI input selecting the allowed number
    of guesses.
    - The attempt count is chosen through a CLI flag.

  ```mermaid
  flowchart LR
      subgraph actor[Actor]
          P[Player]
      end

      subgraph cli[CLI concepts]
          CM[CLI mode]
          FT[CLI feedback]
          WLP[Word list path]
          AC[Attempt count]
      end

      P -->|uses| CM
      CM -->|shows| FT
      CM -->|selects| WLP
      CM -->|selects| AC
  ```
- **Constraints:**
  - Gameplay rules remain in domain classes.
  - The CLI adapter may format output, but it must not reimplement
    evaluation logic.
  - The UI path must stay replaceable by a later Swing adapter.
- **Briefing:** The project already contains a reusable game engine and
  a packaged word list. The CLI adapter must depend on the application
  and domain layers without moving gameplay logic into the UI path.
- **Research:**

  ```plantuml
  @startuml
  left to right direction

  folder "wordle-tutorial" {
    file "settings.gradle.kts"
    file "build.gradle.kts"
    folder "src/main/java" {
      folder "wordle/tutorial/app" {
        file "WordleApplication.java"
      }
      folder "wordle/tutorial/domain" {
        file "Game.java"
        file "GameEngine.java"
        file "Guess.java"
        file "Feedback.java"
        file "Word.java"
      }
    }
    folder "src/main/resources" {
      file "wordlist.txt"
    }
    folder "src/test/java" {
      folder "wordle/tutorial/domain" {
        file "GameEngineTest.java"
      }
    }
  }
  @enduml
  ```

  ```plantuml
  @startuml
  component "CLI Adapter" as cli
  component "Application Entry Point" as app
  component "Game Engine" as engine
  component "Word List Loader" as loader
  database "Packaged Word List" as words

  cli --> app : start game / submit guess
  app --> engine : create game / evaluate guess
  app --> loader : load candidate words
  loader --> words : read resource
  @enduml
  ```

  Research notes:
  - The repository already separates application wiring from domain
    logic.
  - No CLI-specific adapter classes exist yet.

- **Analysis:**
  - CLI mode stays a thin adapter because gameplay logic must remain
    reusable in the existing engine.
  - CLI feedback is rendered in adapter classes because presentation
    belongs in the UI path, not in domain classes.
  - Only `--cli`, `--wordlist`, and `--attempts` are supported because
    the example should keep the external CLI contract minimal and
    reviewable.
- **Design:**

  ```plantuml
  @startuml
  left to right direction

  folder "src/main/java" {
    folder "wordle/tutorial/app" {
      file "WordleApplication.java"
      file "CommandLineOptions.java"
    }
    folder "wordle/tutorial/cli" {
      file "CliGameLoop.java"
      file "FeedbackRenderer.java"
    }
    folder "wordle/tutorial/domain" {
      file "Game.java"
      file "GameEngine.java"
      file "Feedback.java"
      file "Word.java"
    }
  }
  @enduml
  ```

  ```plantuml
  @startuml
  set separator none

  package "wordle.tutorial" {
    package "app" {
      class WordleApplication {
        + main(args)
      }
      class CommandLineOptions {
        + cliMode : boolean
        + wordListPath : String
        + attempts : int
      }
    }

    package "cli" {
      class CliGameLoop {
        + run()
      }
      class FeedbackRenderer {
        + render(feedback) : String
      }
    }

    package "domain" {
      class Game {
        + applyGuess(guess)
      }
      class GameEngine {
        + submitGuess(guess) : Feedback
      }
      class Feedback
      class Word
    }
  }

  WordleApplication --> CommandLineOptions : parses CLI options
  WordleApplication --> CliGameLoop : starts
  CliGameLoop --> GameEngine : uses
  CliGameLoop --> FeedbackRenderer : uses
  GameEngine --> Game : creates / updates
  GameEngine --> Word : validates
  Game --> Feedback : records
  @enduml
  ```

  ```plantuml
  @startuml
  actor Player
  participant "CliGameLoop" as CliGameLoop
  participant "GameEngine" as GameEngine
  participant "Game" as Game
  participant "FeedbackRenderer" as FeedbackRenderer

  Player -> CliGameLoop : enter guess
  CliGameLoop -> GameEngine : submitGuess(guess)
  GameEngine -> Game : applyGuess(guess)
  Game --> GameEngine : updated state + feedback
  GameEngine --> CliGameLoop : game state + feedback
  CliGameLoop -> FeedbackRenderer : render(feedback)
  FeedbackRenderer --> Player : CLI feedback
  @enduml
  ```

  Externally meaningful identifiers:
  - `--cli`
  - `--wordlist`
  - `--attempts`

- **Test specification:**
  - Automated tests:
    - Missing optional CLI arguments use default wordlist and attempt
      values.
    - Invalid CLI argument values are rejected with user-facing
      feedback.
    - CLI feedback rendering matches the engine guess result.
    - CLI mode delegates valid guess validation and scoring to the
      existing engine.
  - Manual tests:
    - Start the CLI and complete one short game from the terminal.
