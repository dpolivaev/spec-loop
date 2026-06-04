---
name: spec-loop-write-adr
description: >-
  Create, update, or supersede architecture decision records under
  `architecture-decisions/`. Use when the user asks for ADR work or
  `spec-loop-plan-task` routes ADR writing.
---

Use this skill for ADR writing only.

Before doing that work, read [adr-format.md](./adr-format.md) and
apply it.

Read and apply project instructions such as `AGENTS.md` when present.

This skill writes ADRs. It does not decide whether an ADR is
required. Routing belongs to `../spec-loop-plan-task/SKILL.md`.

The ADR being drafted or updated is the governing artifact for this
skill.

Use the same ADR format whether or not a task artifact also exists.

## Mandatory clarification gate

Before drafting or revising the ADR as current truth, check whether
any material unresolved question remains about the decision, decision
criteria, trade-offs, credible options, or governing boundaries.

If yes, create only enough ADR structure to hold the topic and
current evidence safely, then use
`../spec-loop-clarify-task/SKILL.md` before continuing. Do not wait
for the user to ask for clarification explicitly.

Re-run this gate whenever later ADR drafting exposes a new material
unresolved branch.

Before writing or revising, classify each input item into its proper
section:
- `Context` = pre-decision facts that would still be true under
  another credible alternative, such as requirements, constraints,
  current-state findings, scope boundaries, and explicit deferrals;
- `Alternatives` = credible competing options;
- `Analysis` = the clarified reasoning, accepted criteria,
  trade-offs, option comparisons, and brief reasons the chosen option
  won; and
- `Decision` = the chosen outcome as a concise TL;DR, including any
  explicit deferrals or major retained boundaries that are part of the
  outcome.

Treat `Analysis` as the ADR's authoritative decision-and-reason
ledger.
Do not repeat an `Analysis` point elsewhere in decision-and-reason
form.

When a task artifact exists, use its `Research`, `Analysis`, and
other ADR-relevant sections such as `Constraints`, `Design`, and,
when relevant, `Scenario` as the primary ADR inputs. Otherwise use
confirmed chat clarification and other current evidence.
