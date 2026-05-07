# Glossary Guidance

A project glossary defines the shared domain language above individual
tasks and the code. Its purpose is to keep Scenario, Design, tests,
code symbols, and commit text aligned on the same terms across the
project.

This file defines how to create and update a project glossary in
AsciiDoc.

This document is plain repository guidance and does not depend on any
specific tool.

## When to Use

Use this guidance when:

- a project glossary already exists and must be updated,
- the user asks to create a glossary,
- a task changes, clarifies, or implements shared domain terms within
  approved scope.

## Relationship to Spec Loop

- Scenario remains the source of approved domain and behavior language
  in task files.
- Design and implementation must stay aligned with approved terms.
- If `glossary.adoc` exists, it defines the project's shared domain
  language above individual tasks. It is a project-level supporting
  artifact, not a replacement for task files.
- Plan required glossary updates during PLAN.
- Perform planned glossary updates during IMPLEMENTATION.
- If glossary work would change approved meaning rather than record it,
  return to PLAN first and update the task file before continuing.

## File Naming

- Use `glossary.adoc` as the glossary file name.
- Keep the glossary near the relevant project root unless the user asks
  for a different location.

## Reference Structures

Use the multi-area structure when the glossary spans multiple true
subsystem areas.

### Multi-area glossary

```adoc
= Domain Glossary

Short intro.

== Overview

[#overview-table]
[cols="3,9",options="header"]
|===
| Subsystem | Usage

| <<subsystem-example,Example Area>>: Short subsystem definition with
<<term-example,linked terms>>.
a|
* The user works with <<subsystem-example,Example Area>>.
* <<subsystem-example,Example Area>> relates to
  <<subsystem-other,Other Area>> through
  <<term-example,linked terms>>.

|===

[mermaid]
----
graph LR
  user["User"]

  subgraph subsystems["Domain"]
    example["Example Area"]
    other["Other Area"]
  end

  user -->|uses| example
  example -->|relates to| other
----

[#subsystem-example]
== <<overview-table,Example Area>>

[cols="6,10",options="header"]
|===
| Term | Usage

| [[term-example]]link:src/main/java/example/Example.java[Example Term]:
Short definition of the term.
a|
* One <<term-example,Example Term>> relates to another
  <<term-other,Other Term>>.
* The <<term-example,Example Term>> can appear in a boundary case.

|===
```

If the glossary covers only one area, the overview section is optional.

### Single-area glossary

Use the structure below when a self-contained, single-area glossary is
enough:

```adoc
= Purchase Approval Glossary

This glossary defines the canonical language for purchase approval.

[cols="6,10",options="header"]
|===
| Term | Usage

| [[term-approval-rule]]link:src/main/java/example/ApprovalRule.java[Approval Rule]:
Business rule deciding whether a request needs approval.
a|
* An <<term-approval-rule,Approval Rule>> evaluates a
  <<term-purchase-request,Purchase Request>>.
* One <<term-approval-rule,Approval Rule>> can require a specific
  <<term-reviewer-group,Reviewer Group>>.

| [[term-purchase-request]]link:src/main/java/example/PurchaseRequest.java[Purchase Request]:
Request to buy one or more items.
a|
* A <<term-purchase-request,Purchase Request>> can trigger an
  <<term-approval-rule,Approval Rule>>.
* A <<term-purchase-request,Purchase Request>> is reviewed by a
  <<term-reviewer-group,Reviewer Group>> when its amount exceeds a
  threshold.

| [[term-reviewer-group]]link:src/main/java/example/ReviewerGroup.java[Reviewer Group]:
Named set of reviewers for business approval.
a|
* A <<term-reviewer-group,Reviewer Group>> receives a
  <<term-purchase-request,Purchase Request>> selected by an
  <<term-approval-rule,Approval Rule>>.
* One <<term-reviewer-group,Reviewer Group>> can review many
  <<term-purchase-request,Purchase Requests>>.

|===
```

## Overview Rules

- Use a separate overview table with the headers `Subsystem` and
  `Usage`.
- Put `[#overview-table]` directly on the overview table.
- In each left cell, use
  `<<subsystem-anchor,Section Name>>: short definition`.
- Use bullet items in overview usage cells.
- Link subsystem names in the left cells and in the usage bullets,
  including self-references.
- Also link glossary terms wherever they appear in overview usage text.
- If an interface is not an independent actor, describe the actor as
  `User` or `The user` rather than modeling the UI as its own
  subsystem.
- List only true subsystem areas in the overview. Do not promote setup
  flows to peer subsystems unless the source material shows an
  independent boundary.

## Section Rules

- Add an anchor such as `[#subsystem-example]` before each area
  heading.
- If an overview exists, link each section heading back to it with
  `== <<overview-table,Section Name>>`.
- Keep glossary terms in separate non-duplicating area tables.
- Place each area table directly before its matching Mermaid diagram.
- Sort glossary rows alphabetically within each area table.
- Keep setup or initialization flows inside the owning subsystem
  section unless they have their own ownership, lifecycle, or API
  boundary.

## Glossary Row Rules

- Use exactly two columns in glossary term tables: `Term` and `Usage`.
- Put the anchor in the term cell using `[[term-kebab-case]]`.
- Use the term text itself as the code link text.
- Put the short definition in the same cell, immediately after the
  linked term and colon.
- Do not add a separate code line.
- Use bullet items in every glossary `Usage` cell.
- Link glossary term mentions consistently.
- Link self-references when a term appears in its own usage bullets.
- Keep definitions short and let the usage bullets carry most of the
  meaning.

## Term Selection Rules

Include by default:

- domain concepts,
- aggregates,
- entities,
- value objects,
- identifiers,
- flags with explicit domain meaning,
- domain inputs and outputs,
- named flows,
- named subsystem areas when they are true boundaries,
- domain states when they are named concepts.

Exclude by default:

- CRUD operation names such as create, update, delete,
- generic HTTP or transport verbs,
- implementation-only helper terms,
- internal switches or storage flags that exist only to support the
  implementation,
- framework terms with no domain meaning,
- duplicate synonyms for the same concept,
- UI labels or screen names that are not independent domain actors.

## Class Linking Rules

1. Link each term to one representative code artifact when a
   stable anchor exists.
2. Prefer a true domain type when one exists.
3. If no domain type exists, use the nearest stable class that carries
   the concept, such as a controller, service, or persistence entity.
4. If no stable code anchor exists yet, omit the code link.
5. Choose the code artifact that best explains where the concept lives
   in the code base, not necessarily the first class found.
6. Keep code links in the `Term` column only.

## Diagram Rules

- Mermaid diagrams live inside the same `.adoc` file as the glossary.
- Every glossary area must include a focused Mermaid diagram.
- If an overview section exists, it must include an overview Mermaid
  diagram.
- Diagrams must be consistent with the glossary text and serve as a
  user-facing aid, not decorative content.
- When glossary text changes in a way that affects relationships,
  boundaries, actors, or flows shown in diagrams, update the diagrams
  in the same change.
- Use diagrams as focused visual context, not as the source of truth.
- Prefer one small overview Mermaid diagram and one focused Mermaid
  diagram after each area table.
- Prefer several small diagrams over one crowded graph.
- If a diagram has nodes outside the main area, group those external
  nodes into their own semantically correct subgraphs such as actor,
  shared context, error handling, or a neighboring bounded context.
- Keep those external subgraphs semantically correct. Do not place a
  term into a generic shared group when it belongs to a specific
  neighboring context.
- This structure keeps the main area focused while making external
  relationships explicit.
- If a diagram is still too crowded after that restructuring, split
  the content into smaller diagrams.
- Keep Mermaid node labels as plain text.
- Do not rely on Mermaid click or href links for document navigation;
  use AsciiDoc anchors and cross-references instead.
