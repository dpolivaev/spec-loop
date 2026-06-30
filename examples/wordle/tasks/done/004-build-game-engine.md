# Task: Build game engine

- **Task Identifier:** 2026-01-25-game-engine
- **Scope:** Implement game loop state, attempt limits, win and lose
  conditions, and turn progression.
- **Motivation:** Provide a reusable core engine that CLI and UI layers can
  drive without duplicating gameplay logic.
- **Scenario:** A game starts with a hidden solution and fixed attempts.
  Each guess produces feedback and updates attempts and history until the
  player wins or exhausts attempts.
- **Briefing:** This task connects the domain model to runtime game flow.
  The first subtask defines immutable state; the second adds engine
  behavior on top of that state.
- **Research:** Before this task, the project had domain records, rule
  evaluation, and random word selection. No game state, status tracking, or
  turn progression logic existed.
- **Design:**

  ```plantuml
  @startuml
  set separator none
  package "wordle" {
    package "engine" {
      class GameEngine
      class GameState
      enum GameStatus
    }
    package "domain" {
      class Feedback
      class Word
    }
    package "rules" {
      class WordleRules
    }
    package "words" {
      class WordListLoader
    }
  }
  
  GameEngine --> WordListLoader : starts game with solution source
  GameEngine --> WordleRules : evaluates guess
  GameEngine --> GameState : returns new state
  GameState --> Feedback : stores feedback history
  GameState --> Word : stores solution
  GameState --> GameStatus : stores lifecycle status
  @enduml
  ```

  Engine operations are pure state transitions: `startGame` initializes
  `IN_PROGRESS`, and `submitGuess` returns a new `GameState` with updated
  history, attempts, and terminal status when applicable.
- **Test specification:** Covered by subtask test specifications.

## Subtask: Define game state model

- **Status:** done
- **Scope:** Introduce immutable game state types for attempts, history, and
  status.
- **Motivation:** Establish a stable state model before implementing engine
  behavior.
- **Scenario:** After each guess, the engine returns a new state object that
  contains updated attempts, feedback history, and status while keeping
  prior state immutable.
- **Briefing:** Relevant types belong in `wordle.engine`. Start with state
  and status types that carry solution, attempts remaining, feedback
  history, and lifecycle state without mutation.
- **Research:** No game state types existed at subtask start.
- **Design:**

  ```plantuml
  @startuml
  set separator none
  package "wordle" {
    package "engine" {
      class GameState
      enum GameStatus
    }
    package "domain" {
      class Feedback
      class Word
    }
  }
  
  GameState --> Word : solution value
  GameState --> Feedback : ordered guess history
  GameState --> GameStatus : status value
  @enduml
  ```

  `GameState` is immutable and becomes the single payload exchanged between
  gameplay engine and presentation layers.
- **Test specification:**
  - **Automated tests:**
    - `GameStateTest`
      - `gameStateStoresProvidedValues`: verify `GameState` stores
        provided solution, attempts, history, and status.

## Subtask: Implement game engine logic

- **Status:** done
- **Scope:** Implement game start and guess submission logic using the state
  model.
- **Motivation:** Provide reusable gameplay behavior for CLI and UI layers.
- **Scenario:** A new game starts from a selected solution. Each submitted
  guess updates the state until either all letters are correct or attempts
  run out.
- **Briefing:** Engine behavior composes `WordListLoader` and `WordleRules`.
  Follow the state model from the previous subtask before changing start
  and guess flow.
- **Research:** No engine behavior existed; integration points were ready in
  rules and word list components.
- **Design:**

  ```plantuml
  @startuml
  set separator none
  package "wordle" {
    package "engine" {
      class GameEngine
      class GameState
    }
    package "rules" {
      class WordleRules
    }
    package "words" {
      class WordListLoader
    }
  }
  
  GameEngine --> WordListLoader : startGame(resourcePath)
  GameEngine --> WordleRules : submitGuess(state, guess)
  GameEngine --> GameState : emits next immutable state
  @enduml
  ```

  `startGame` initializes attempts and empty history. `submitGuess` applies
  rule feedback, appends history, updates attempts, and transitions to `WON`
  or `LOST` when conditions are met.
- **Test specification:**
  - **Automated tests:**
    - `GameEngineTest`
      - `startGameInitializesState`: verify starting a game sets
        `maxAttempts`, empty history, and `IN_PROGRESS`.
      - `correctGuessWinsWithoutDecrement`: verify a correct guess
        sets status to `WON`.
      - `incorrectGuessDecrementsAttempts`: verify an incorrect guess
        decrements attempts and appends feedback.
      - `incorrectGuessEndsGameAtZeroAttempts`: verify `maxAttempts`
        incorrect guesses transition to `LOST`.
      - `submitGuessAfterWonReturnsSameState`: verify submissions
        after `WON` return the same state.
      - `submitGuessAfterLostReturnsSameState`: verify submissions
        after `LOST` return the same state.
      - `submitGuessUsesProvidedSolution`: verify submitted guesses are
        evaluated against the provided solution.
