Title: CLI argument parsing approach for Wordle CLI
Date: 2026-01-26
Status: Accepted

Decision:
Use a CLI parsing library (picocli) for the Wordle CLI.

Context:
The Wordle CLI requires a small number of flags (`--wordlist`,
`--attempts`, `--help`). We want a simple, low-dependency solution
suitable for an example project.

Alternatives:
1. Manual parsing (no dependency)
   - Pros: No additional dependencies; easy to follow for a small CLI;
     minimal build impact.
   - Cons: Requires custom parsing code; grows in complexity as
     options expand.
2. Use a library (picocli)
   - Pros: Mature parsing, help generation, validation, and clearer
     option definitions.
   - Cons: Adds a dependency and extra API surface for a small
     example.

Analysis:
- Prefer built-in help and validation because CLI flags are public
  behavior.
- Reject manual parsing because it pushes more parsing logic into the
  adapter and scales worse as options grow.
- Accept a small dependency increase because clearer option
  definitions and lower custom parsing cost matter more here.

