# Scenario and Glossary Guidance

This file applies only after `common-task-guidance.md` has already
established that the current task or subtask needs `Scenario` or
`Glossary`.

This file covers only the extra drafting guidance. It does not repeat:
- when these sections are required;
- task section order; or
- project glossary update timing.

## Working method

1. Draft `Scenario` first.
2. If `Glossary` is required, derive it directly from `Scenario`.
3. Keep only terms that the task's behavior, `Design`, or `Test
   specification` must treat as stable concepts.
4. Re-read `Scenario`, `Glossary`, `Design`, and `Test specification`
   together and remove term drift.

## Scenario drafting

- Write behavior, boundaries, and outcomes.
- Keep it concise and implementation-free.
- Make terms understandable through usage in the scenario.
- Do not describe review workflow, document workflow, or code
  structure there.
- Reuse unchanged current domain terms instead of inventing local
  synonyms.
- If no explicit project glossary exists, use `Research` plus the
  existing codebase as the source of current domain terms.

## Glossary drafting

- Place `Glossary` immediately after `Scenario`.
- Keep it delta-only relative to the current shared domain-language
  source:
  - the project glossary, when one exists; otherwise
  - `Research` plus the existing codebase.
- Do not redefine unchanged terms from that source.
- Include only:
  - new or changed shared domain terms;
  - named abstractions that the task's behavior or `Design`/`Test
    specification` depends on as stable concepts; and
  - task-specific usage constraints not yet canonicalized project-wide.
- For each entry, write:
  - the term;
  - a short meaning; and
  - usage bullets grounded in `Scenario` behavior.
- Do not put helper names, framework terms, or purely local algorithm
  conveniences into task `Glossary` unless they are part of approved
  domain language.

## Task-file visual glossary

- On the task-file path, when `Glossary` is present, include at least
  one focused Mermaid visual glossary directly under it.
- Apply the Mermaid diagram rules from
  `../spec-loop-write-glossary/glossary-format.md` to task visual
  glossaries too.
- In particular:
  - keep the diagram consistent with the glossary text;
  - update the diagram in the same change when glossary
    relationships, boundaries, actors, or flows change;
  - use the diagram as focused visual context, not as the source of
    truth;
  - prefer several small diagrams over one crowded graph;
  - if the diagram has nodes outside the main area, group them into
    semantically correct subgraphs;
  - label every Mermaid connection;
  - keep Mermaid node labels as plain text; and
  - do not rely on Mermaid click or href links for navigation.

## Quality checks

- `Scenario` and `Glossary` use one vocabulary, not parallel synonyms.
- `Glossary` terms are grounded in `Scenario` behavior.
- `Design` and `Test specification` reuse approved terms.
- `Design` does not redefine `Glossary` terms.
- Task visual glossaries follow the Mermaid rules above.
