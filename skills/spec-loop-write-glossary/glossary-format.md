# Glossary Guidance

A project glossary defines the shared domain language above individual
tasks and the code. Its purpose is to keep Scenario, Design, tests,
code symbols, and commit text aligned on the same terms across the
project.

This file defines how to create, update, or supersede a project
glossary in AsciiDoc.

This document is plain repository guidance and does not depend on any
specific tool.

## When to Use

Use this guidance when:

- creating an AsciiDoc project glossary;
- updating an AsciiDoc project glossary;
- superseding an AsciiDoc project glossary with an AsciiDoc successor;
  or
- recording shared domain terms changed, clarified, or implemented by
  a task.

## Relationship to Spec Loop

- Scenario remains the source of approved domain and behavior language
  in task files.
- Design and implementation must stay aligned with approved terms.
- If `glossary.adoc` exists, it defines the project's shared domain
  language above individual tasks. It is a project-level supporting
  artifact, not a replacement for task files.
- For task-based work, plan required glossary actions during PLAN and
  perform them during EXECUTION.
- If task-based glossary work would change approved meaning rather than
  record it, return to PLAN first and update the active task.

## File Naming

- Use `glossary.adoc` as the glossary file name.
- Keep the glossary near the relevant project root unless the user asks
  for a different location.

## Supersession

Supersession requires an approved replacement, not just a second file.
The successor becomes canonical. The predecessor may remain as
historical material under project lifecycle rules.

Use this guidance when the successor is AsciiDoc. For a non-AsciiDoc
successor, follow its project format instead.

## Reference Structures

Use the multi-area structure when the glossary spans multiple true
subsystem areas.

### Multi-area glossary

A complete multi-area example lives in
[examples/multi-area-glossary.adoc](examples/multi-area-glossary.adoc).

Use this shape:

```adoc
= Domain Glossary

== Overview
...

== <<overview-table,Example Area>>
...
```

If the glossary covers only one area, the overview section is optional.

### Single-area glossary

Use the structure below when a self-contained, single-area glossary is
enough.
A complete single-area example lives in
[examples/single-area-glossary.adoc](examples/single-area-glossary.adoc).

Use this shape:

```adoc
= Purchase Approval Glossary

[cols="6,10",options="header"]
|===
| Term | Usage
...
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

Determine identity by meaning, not spelling. Before adding a term,
check whether an existing abstraction represents the same concept. If
it does, either use that abstraction's current name or require an
approved refactoring proposal that names:

- the current abstraction;
- the target term; and
- the intended rename or restructuring.

Do not add competing glossary language while leaving the same
abstraction unchanged.

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
- In glossary diagrams, keep Mermaid node labels as ordinary plain
  words. Put exact code, API, method, field, or serialized names in the
  glossary text instead of diagram labels.
- Label every Mermaid connection.
- Do not rely on Mermaid click or href links for document navigation;
  use AsciiDoc anchors and cross-references instead.
