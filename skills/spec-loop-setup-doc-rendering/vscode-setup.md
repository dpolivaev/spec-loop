# VS Code-Based IDE Setup Reference for `spec-loop-setup-doc-rendering`

Use this reference when `spec-loop-setup-doc-rendering` is helping a user
who works in Visual Studio Code, Cursor, or another VS Code-based
environment.

The extension IDs and settings below apply to VS Code, Cursor, and other
VS Code-based IDEs. Where this document says "VS Code", read it as the
user's VS Code-based IDE unless a step explicitly narrows the scope to the
helper script.

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

- PlantUML preview support for Markdown,
- AsciiDoc support whenever `glossary.adoc` is active or configured as the
  default format for new glossaries, and
- for the local-only Markdown preview path, Java, Graphviz (`dot` on
  `PATH`), and the local `plantuml.jar`.

If the required pieces for the selected path are already sufficient, report
that no editor setup change is needed.

## PlantUML setup options

### Option A: server-based Markdown preview

Use this when the user is fine with a PlantUML server dependency.

Install:

- `myml.vscode-markdown-plantuml-preview`

Optional setting:

- `markdown.plantuml.server`
- default: `https://www.plantuml.com/plantuml`

Use the IDE's built-in Markdown preview after installation.

The helper script
[scripts/setup-vscode-server-based.sh](./scripts/setup-vscode-server-based.sh)
is stored in this skill's scripts/ subdirectory, both in a repository
checkout and in an installed skill bundle. If you want this
server-based path and can access the skill directory, you can run the
script directly instead of using the interactive skill flow.
For example, from this directory:

```bash
bash ./scripts/setup-vscode-server-based.sh --check
bash ./scripts/setup-vscode-server-based.sh --apply
```

If you are in a different working directory, run the same script by its
full path.

The helper requires a supported editor CLI command on `PATH`
(`code`, `code-insiders`, `cursor`, `code.cmd`, `code-insiders.cmd`, or
`cursor.cmd`) and is intended for macOS, Linux, WSL, and Git Bash for
Windows. It covers only the server-based path described here. In other
VS Code-based IDEs, apply the same extension IDs and settings manually.
It does not configure the local-only PlantUML path, and it does not edit
the optional `markdown.plantuml.server` setting.

### Option B: local-only Markdown preview

Use this when the user wants local rendering.

Install:

- `shd101wyy.markdown-preview-enhanced`

Require:

- Java
- Graphviz (`dot` executable on `PATH`)
- local `plantuml.jar`

Here, Graphviz means the `dot` command must be installed and available on
`PATH`.

Required setting:

- `markdown-preview-enhanced.plantumlJarPath`

Use Markdown Preview Enhanced preview rather than the built-in preview.

## AsciiDoc setup

Install:

- `asciidoctor.asciidoctor-vscode`

This is required whenever `glossary.adoc` is active or configured as the
default format for new glossaries.
