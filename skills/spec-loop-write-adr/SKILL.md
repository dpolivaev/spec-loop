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

If material unresolved questions remain about the decision, decision
criteria, or governing boundaries, create or update the ADR draft
enough to hold its required sections, then use
`../spec-loop-clarify-task/SKILL.md` before finalizing the ADR.

When a task artifact exists, use its `Research` and `Analysis`
sections as the primary ADR inputs. Otherwise use the confirmed chat
clarification and other current evidence.
