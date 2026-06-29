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

Include explicit deferrals or major retained boundaries here when they
are part of the chosen outcome.

### Context

Record only pre-decision facts: background, constraints,
current-state findings, scope boundaries, explicit deferrals, and
other conditions that were already true before the choice.

A `Context` item must still be true if another credible alternative
had been chosen.

When a task artifact exists, draw this section mainly from its
`Research` section.

Do not put accepted option choices, ranking outcomes, clarified
preference statements, or decision rationale here.

### Alternatives

Record the credible competing options.

Use a compact numbered list when more than one alternative matters.
Keep weak or obviously non-credible options out.

When there is only one meaningful non-chosen alternative, record it
briefly rather than inflating the section.

### Analysis

Record the accepted decision criteria, accepted clarification
decisions, and brief rationale that supports the chosen decision.

Use a compact bullet list, not a prose paragraph.

Keep only the ADR-relevant subset here.

When a task artifact exists, draw this section from the ADR-relevant
subset of its `Analysis` section. Do not copy the whole task ledger.

`Analysis` is the ADR's authoritative decision-and-rationale ledger.
Do not repeat its points elsewhere in decision-and-rationale form.
Do not duplicate the full alternatives here.
Do not put open questions, confidence values, transient notes,
chain-of-thought, chat history, or step-by-step internal reasoning
here.

## Update and supersede rules

- Update an ADR in place when the decision is still the same and the
  change only clarifies, corrects, or completes that ADR.
- Create a new ADR when the architectural decision is materially new
  or when an accepted ADR is being replaced.
- When one ADR supersedes another, mark the older ADR as
  `Superseded` and mention the replacement in nearby prose.
