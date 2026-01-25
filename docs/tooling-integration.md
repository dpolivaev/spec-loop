# Tooling Integration

This framework is designed to be tool-agnostic: the same Constitution and workflow can be applied across different environments.

The canonical rules live in [CONSTITUTION.md](../CONSTITUTION.md) and are referenced rather than duplicated.

## One Constitution, multiple integration paths

Typical integration paths include:

- agent-based environments (for example, Codex agents),
- instruction-based environments (for example, GitHub Copilot instructions),
- cloud / hosted chat environments.

The key requirement is that the environment reliably enforces the same review boundaries and completion criteria described in the Constitution.

## Why `CONSTITUTION.md` lives at the repository root

Keeping the Constitution at the repository root makes it the stable, canonical reference for both humans and tools.

## How examples reference the Constitution

Examples link to the Constitution instead of copying it, so that the rules remain single-sourced and consistent.
