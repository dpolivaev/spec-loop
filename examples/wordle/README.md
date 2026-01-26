# Wordle Example

This directory contains a worked example used to demonstrate the framework end-to-end.

## Purpose

The goal of the example is to show:

- incremental specification (design per step),
- explicit research and design,
- test-driven completion,
- and disciplined use of the model under review boundaries.

## Why Wordle is a suitable demonstration task

Wordle is small enough to build in clear increments, but rich enough to require careful rules, edge cases, and verification.

## High-level structure

The example is intended to grow incrementally (e.g., parsing input, evaluating guesses, formatting output, then optional enhancements as separate increments).

## CLI build and run

Build and run the CLI locally:

```
./gradlew run --args="--wordlist wordlist.txt --attempts 6"
```

Optional Arguments:

- `--wordlist <source>`: file path or URL for the word list; omit to use the internal list.
- `--attempts <n>`: number of attempts before losing (default 6).
- `--cli`: force CLI mode even when the UI is available.

## Swing UI

By default, `./gradlew run` launches the Swing UI when a display is available:

```
./gradlew run
```

To run the CLI instead (or when you want to force terminal mode):

```
./gradlew run --args="--cli"
```

The UI also accepts the same options as the CLI:

```
./gradlew run --args="--wordlist wordlist.txt --attempts 6"
```

When `--wordlist` is provided, the UI loads that file path or URL; otherwise it uses the internal list.

## Distribution package

Create a distributable ZIP/TAR:

```
./gradlew distZip
./gradlew distTar
```

Outputs are written to `build/distributions/`. Unpack the archive and run:

```
./bin/wordle
./bin/wordle --wordlist wordlist.txt --attempts 6
```

## Console output format

One portable output format is:

`C. R~ A= N. E.`

Where:

- `=` means correct letter in correct position,
- `~` means correct letter in wrong position,
- `.` means letter not present.

## Notes on incremental development and testing

Each increment should be considered complete only when its tests are implemented and passing, unless tests are explicitly waived by the human developer.

The canonical workflow rules are in [CONSTITUTION.md](../../CONSTITUTION.md).
