# VS Code Setup Reference for `install-spec-loop`

Use this reference when `install-spec-loop` is helping a user who works in
Visual Studio Code or in a VS Code-compatible environment.

## Decision rules

- PlantUML support is mandatory unless the user explicitly opts out of
  PlantUML.
- Explain the consequence of opting out: Spec Loop diagrams in Markdown will
  remain plain code blocks or will not preview correctly.
- AsciiDoc support is mandatory when Spec Loop glossary format guidance is
  installed, when an existing project glossary uses `glossary.adoc`, or when
  the configured default format for new glossaries is `adoc`.
- Explain the consequence of skipping AsciiDoc support: `glossary.adoc` will
  lose normal editing and preview support.
- Ask for confirmation before changing extension state or editor settings.

## Detect before changing anything

Check whether the environment already has:

- PlantUML preview support for Markdown, and
- AsciiDoc support whenever `glossary.adoc` is active or configured as the
  default format for new glossaries.

If both are already sufficient, report that no editor setup change is needed.

## PlantUML setup options

### Option A: server-based Markdown preview

Use this when the user is fine with a PlantUML server dependency.

Install:

- `myml.vscode-markdown-plantuml-preview`

Optional setting:

- `markdown.plantuml.server`
- default: `https://www.plantuml.com/plantuml`

Use the built-in VS Code Markdown preview after installation.

### Option B: local-only Markdown preview

Use this when the user wants local rendering.

Install:

- `shd101wyy.markdown-preview-enhanced`

Require:

- Java
- Graphviz
- local `plantuml.jar`

Required setting:

- `markdown-preview-enhanced.plantumlJarPath`

Use Markdown Preview Enhanced preview rather than the built-in preview.

## AsciiDoc setup

Install:

- `asciidoctor.asciidoctor-vscode`

This is required whenever `glossary.adoc` is active or configured as the
default format for new glossaries.

## What `install-spec-loop` should do

1. Detect whether VS Code support is already sufficient.
2. If not, explain what is missing and why it matters.
3. Offer server-based vs local PlantUML setup unless the user already has a
   preference.
4. Show the exact planned extension or settings changes.
5. Apply them after confirmation when the harness can do so.
6. Otherwise, tell the user exactly which extension IDs or settings they must
   install or change.
