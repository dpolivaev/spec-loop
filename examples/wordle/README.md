# Wordle Example

This directory contains a worked example used to demonstrate Spec Loop
end-to-end. The **primary documentation of the example is its commit history**,
which shows how specifications, design, implementation, and tests evolve
step by step.

## Purpose

The goal of this example is to demonstrate:

* step-local specification (design per step),
* explicit research and design,
* test-driven completion,
* disciplined use of the model under explicit review boundaries.

## Game rules (brief)

Wordle is a word-guessing game with the following rules:

* The game selects a secret word of fixed length.
* The player submits guesses of the same length.
* For each guess, letters are evaluated position by position:

  * `=` correct letter in the correct position,
  * `~` correct letter in the wrong position,
  * `.` letter not present in the word.
* The player has a limited number of attempts to guess the word.
* The game ends when the word is guessed correctly or attempts are exhausted.

These rules are intentionally simple but contain enough edge cases
(letter repetition, ordering, termination conditions) to require careful
specification and testing.

## Why Wordle is a suitable demonstration task

Wordle is small enough to build in clear, reviewable steps, but rich enough
to require explicit rules, research into edge cases, and verification through
tests. This makes it a good fit for demonstrating step-local specifications
and approval gates.

## Build and run

Build and run locally using Gradle.

### CLI mode

```
./gradlew run --args="--cli --wordlist wordlist.txt --attempts 6"
```

Arguments:

* `--wordlist <source>`: file path or URL for the word list; omit to use the
  internal list.
* `--attempts <n>`: number of attempts before losing (default: 6).
* `--cli`: force CLI mode.

### Swing UI

When a display is available, running without `--cli` starts the Swing UI:

```
./gradlew run
```

The UI accepts the same arguments as the CLI.

## Console output format

One portable output format used in the example is:

`C. R~ A= N. E.`

Where:

* `=` means correct letter in the correct position,
* `~` means correct letter in the wrong position,
* `.` means letter not present.
