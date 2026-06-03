# ADR format

Store ADR files under `architecture-decisions/`, one file per
decision.

ADR file names must use readable descriptive words, without
prefixes, numbering, or abbreviations.

Use this exact top-level section order:

- `Title:`
- `Date:`
- `Status:`
- `Decision:`
- `Context:`
- `Alternatives:`
- `Analysis:`
- `Consequences:`

Use plain label lines with colon suffixes, as in existing ADR files.

## Section meaning

### Title

Short decision topic.

### Date

ADR date in `YYYY-MM-DD` format.

### Status

Use the project's status words when they exist. Otherwise use plain
statuses such as `Proposed`, `Accepted`, or `Superseded`.

### Decision

Put the chosen outcome first.

Keep it short, direct, and reviewable. The reader should understand
the answer without reading the rest of the document.

### Context

Record the factual background, constraints, current-state findings,
and other conditions that shape the decision.

When a task artifact exists, draw this section mainly from its
`Research` section.

Do not turn `Context` into a transcript or argument section.

### Alternatives

Record the credible competing options.

Use a compact numbered list when more than one alternative matters.
Keep weak or obviously non-credible options out.

When there is only one meaningful non-chosen alternative, record it
briefly rather than inflating the section.

### Analysis

Record the accepted clarification decisions and brief reasons that
support the chosen decision.

Use a compact bullet list, not a prose paragraph.

Keep only the ADR-relevant subset here.

When a task artifact exists, draw this section from the ADR-relevant
subset of its `Analysis` section. Do not copy the whole task ledger.

Do not duplicate the full alternatives here. Do not put open
questions, confidence values, transient notes, chain-of-thought,
chat history, or step-by-step internal reasoning here.

### Consequences

Record the concrete effects of the decision.

Include costs, follow-on constraints, migration implications,
operational consequences, and other meaningful trade-offs that remain
after the choice.

## Update and supersede rules

- Update an ADR in place when the decision is still the same and the
  change only clarifies, corrects, or completes that ADR.
- Create a new ADR when the architectural decision is materially new
  or when an accepted ADR is being replaced.
- When one ADR supersedes another, mark the older ADR as
  `Superseded` and mention the replacement in the older ADR's
  `Consequences:` section or equivalent nearby prose.
