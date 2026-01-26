Title: CLI argument parsing approach for Wordle CLI
Date: 2026-01-26
Status: Accepted

Context:
The Wordle CLI requires a small number of flags (`--wordlist`, `--attempts`, `--help`). We want a simple, low-dependency solution suitable for an example project.

Alternatives considered:
1. Manual parsing (no dependency)
   - Pros: No additional dependencies; easy to follow for a small CLI; minimal build impact.
   - Cons: Requires custom parsing code; grows in complexity as options expand.
2. Use a library (picocli)
   - Pros: Mature parsing, help generation, validation, and clearer option definitions.
   - Cons: Adds a dependency and extra API surface for a small example.

Decision:
Use a CLI parsing library (picocli) for the Wordle CLI. This avoids custom parsing logic, provides help output, and scales if options grow.

Consequences:
We add a dependency and follow picocli conventions in the CLI entry point. In exchange, we get validated option parsing and automatic help output with less custom code.
