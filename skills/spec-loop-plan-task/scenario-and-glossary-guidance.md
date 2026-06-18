# Scenario and Glossary Guidance

This file applies only after [common-task-guidance.md](common-task-guidance.md) has already
established that the current task or subtask needs `Scenario` or
`Glossary`.

This file covers only the extra drafting guidance. It does not repeat:
- when these sections are required;
- task section order; or
- project glossary update timing.

## Working method

1. Draft `Scenario` first.
2. If `Glossary` is present or becomes required, draft or update it
   using the `Glossary drafting` rules below.
3. Before approval seeking, complete the term-reduction,
   term-classification, and glossary-repair pass defined in `Glossary
   drafting`.
4. Re-read `Scenario`, `Glossary`, `Research`, `Design`, and `Test
   specification` together and remove term drift.

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
- Reuse existing canonical terms from the shared domain-language
  source whenever they fit. If none fits, use ordinary prose unless
  the design introduces a new review-relevant design element that
  needs a stable exact name. If a new term is still needed, add it to
  task `Glossary` when it qualifies under the glossary rules.
- Build glossary candidates from:
  - `Scenario`;
  - `Design` and `Test specification` when they introduce shared
    domain terms needed to understand or verify behavior; and
  - `Research` and `Design` when they reference exact external types
    or APIs that are part of the reviewed contract.
- For this guidance, review-relevant excludes local variables, private
  members contained within one class, and other implementation-local
  details already excluded from `Design` review.
- Term reduction decides whether a coined term should remain.
  Classification answers what a kept term is.
- Before approval seeking, explicitly classify every new
  review-relevant term kept in `Design`, diagrams, or `Test
  specification` as exactly one of:
  - shared domain term = stable behavior or contract concept shared
    across `Scenario`, `Design`, or `Test specification`;
  - exact external technical term = exact external type or API; or
  - internal implementation term.
- Record that classification by repairing the canonical sections,
  rather than by adding a separate classification table, unless the
  User asks for one:
  - shared domain term = ensure `Scenario` uses the term; when the
    current increment introduces or changes it relative to the shared
    source, include it in task `Glossary`;
  - exact external technical term = include it in task `Glossary` only
    when the exact external type or API is part of the reviewed
    contract;
  - internal implementation term = keep it only in
    implementation-level design such as class-level structure. Do not
    use it in `Scenario`, task `Glossary`, or behavior-level diagrams
    and prose.
- Task `Glossary` may also include task-specific usage constraints not
  yet canonicalized project-wide.
- If a new review-relevant term in `Design`, diagrams, or `Test
  specification` is missing this classification or the classification
  is ambiguous after that pre-approval pass, the task is not
  approval-ready.
- For each entry, write:
  - the term;
  - a short meaning; and
  - usage bullets grounded in the reviewed behavior or contract.
- For an exact external technical term added to task `Glossary`, keep
  the term text unchanged. In the short meaning, use the words
  `shared term from <source>`, where `<source>` names the external
  library, API, or contract. Do not put the source in the term label
  itself.

## Task-file visual glossary

- On the task-file path, when `Glossary` is present, include at least
  one focused Mermaid visual glossary directly under it.
- Apply the Mermaid diagram rules from
  [spec-loop-write-glossary/glossary-format.md](../spec-loop-write-glossary/glossary-format.md) to task visual
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

Consistent reuse of approved terms across the shared glossary source,
`Scenario`, `Design`, and `Test specification` keeps meaning,
behavior, design contracts, and verification aligned.

- `Scenario` and `Glossary` use one vocabulary, not parallel synonyms.
- `Glossary` terms are grounded in the reviewed behavior or contract.
- `Design` and `Test specification` reuse approved terms.
- `Design` does not redefine `Glossary` terms.
- Task visual glossaries follow the Mermaid rules above.
