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
