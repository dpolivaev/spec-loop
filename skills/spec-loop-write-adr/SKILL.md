---
name: spec-loop-write-adr
description: >-
  Create, update, or supersede architecture decision records under
  `architecture-decisions/`. Use when the user asks for ADR work or a
  Spec Loop workflow routes ADR writing.
---

Use this skill for ADR writing only.

Before doing that work, read [adr-format.md](adr-format.md) and
apply it.

Read and apply project instructions such as `AGENTS.md` when present.

This skill writes ADRs. It does not decide by itself whether an ADR
is required. ADR work starts when the User asks for it or when a Spec
Loop workflow routes to it.

The ADR being drafted or updated is the governing artifact for this
skill.

Use the same ADR format whether or not a task artifact also exists.

## Mandatory clarification gate

Before drafting or revising the ADR as current truth, check whether
any important open decision remains about the decision, its criteria,
trade-offs, credible options, or the boundaries of the decision.

If yes, create only enough ADR structure to hold the topic and
current evidence safely, then use
[spec-loop-clarify-task/SKILL.md](../spec-loop-clarify-task/SKILL.md)
to engage the User before continuing. Do not wait for the user to ask
for clarification explicitly, and do not replace this handoff with
unstructured criteria discussion or brainstorming.

Re-run this gate whenever later ADR drafting exposes a new important
open decision.

Before writing or revising, classify each input item into its proper
section:
- `Context` = pre-decision facts that would still be true under
  another credible alternative, such as requirements, constraints,
  current-state findings, scope boundaries, and explicit deferrals;
- `Alternatives` = credible competing options;
- `Analysis` = the clarified reasoning, accepted criteria,
  trade-offs, option comparisons, and brief rationale for why the
  chosen option won; and
- `Decision` = the chosen outcome as a concise TL;DR, including any
  explicit deferrals or major retained boundaries that are part of the
  outcome.

Treat `Analysis` as the ADR's authoritative decision-and-rationale
ledger.
Do not repeat an `Analysis` point elsewhere in decision-and-rationale
form.

When a task artifact exists, use its `Research`, `Analysis`, and
other ADR-relevant sections such as `Constraints`, `Design`, and,
when relevant, `Scenario` as the primary ADR inputs. Otherwise use
confirmed chat clarification and other current evidence.
