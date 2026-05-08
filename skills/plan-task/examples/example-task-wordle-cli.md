# Example task: Wordle CLI adapter with embedded PlantUML

This compact example task shows valid embedded PlantUML for four common
task-diagram needs.
It is primarily a collection of valid diagram patterns in a realistic
task context, not a required minimum task size and not a signal that
every first planning pass should be this detailed.

This example task shows valid embedded PlantUML for four common
task-diagram needs:

- filesystem / project structure
- component interaction
- class structure
- sequence flow

Keep different concerns in separate PlantUML blocks instead of mixing
diagram types.

## Scope

Add a CLI adapter for Wordle that starts a game, reads guesses from
standard input, renders feedback text, and keeps gameplay rules in the
existing engine and domain classes.

## Motivation

This example demonstrates current task structure and PlantUML patterns
that render reliably in Markdown task files.

## Briefing

The project already contains a reusable game engine and a packaged word
list. The CLI adapter must depend on the application and domain layers
without moving gameplay logic into the UI path.

## Research

Current repository structure relevant to the change:

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

Current runtime boundary:

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

## Design

Target project structure for the CLI path:

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

Class structure for the CLI adapter and engine collaboration:

```plantuml
@startuml
set separator none

package "wordle.tutorial" {
  package "app" {
    class WordleApplication
    class CommandLineOptions
  }

  package "cli" {
    class CliGameLoop
    class FeedbackRenderer
  }

  package "domain" {
    class Game
    class GameEngine
    class Feedback
    class Word
  }
}

WordleApplication --> CommandLineOptions : parses ~--cli, ~--wordlist, ~--attempts
WordleApplication --> CliGameLoop : starts
CliGameLoop --> GameEngine : uses
CliGameLoop --> FeedbackRenderer : uses
GameEngine --> Game : creates / updates
GameEngine --> Word : validates
Game --> Feedback : records
@enduml
```

Guess submission flow:

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
FeedbackRenderer --> Player : feedback text
@enduml
```

Design notes:

- Keep filesystem, component, class, and sequence diagrams in separate
  PlantUML blocks.
- Do not mix `file` / `folder` elements with classes in the same
  diagram unless there is a strong reason.
- Use the class diagram for structural changes and the sequence diagram
  for runtime behavior.

## Constraints

- Gameplay rules remain in domain classes.
- The CLI adapter may format output, but it must not reimplement
  evaluation logic.
- The UI path must stay replaceable by a later Swing adapter.

## Test Specification

- automated tests for CLI argument parsing defaults and validation
- automated tests for feedback rendering text
- automated tests proving the CLI path delegates to the existing engine
- verification commands:
  - `./gradlew test`
  - `./gradlew run --args='--cli'`
