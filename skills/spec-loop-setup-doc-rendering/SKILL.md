---
name: spec-loop-setup-doc-rendering
description: >-
  Help set up and troubleshoot rendering for task files and glossary
  files. Use when the user asks about PlantUML, AsciiDoc, VS Code,
  Cursor, other VS Code-based IDEs, JetBrains, preview rendering,
  Java, or Graphviz needed to review rendered task or glossary files.
---

Use for setting up or fixing rendering of task files and glossary
files.

Keep agent-only behavior in this file. Treat [vscode-setup.md](vscode-setup.md) and
[jetbrains-setup.md](jetbrains-setup.md) as shared setup references rather than the place
for agent-policy instructions.

Do not just restate the setup references. First gather evidence
yourself from commands and accessible project-local config, then
report:

- what is already working,
- what is missing or broken, and
- what you still cannot verify directly.

After the direct checks, suggest creating small Markdown and AsciiDoc
probe files to verify rendering end to end. Those probes should cover
the diagram kinds the current workflow expects to use and must include
at least one class diagram. Include other relevant kinds too, such as
sequence, component, and Mermaid glossary diagrams.

Prefer direct checks over assigning routine checks to the user.

Follow the selected setup path:

- On the JetBrains path, check Graphviz with `command -v dot` and
  `dot -V` as part of PlantUML verification.
- On the VS Code local-only PlantUML path, check Java with
  `command -v java` and `java -version`, Graphviz with
  `command -v dot` and `dot -V`, and any configured local
  `plantuml.jar` path with `test -f`.
- On the VS Code server-based path, prefer the helper-script check
  path and do not run Java, Graphviz, or local `plantuml.jar` checks
  unless troubleshooting points to the local-only path.

Determine whether AsciiDoc support is required from the current project
state, including `glossary.adoc` or explicit use of the Spec Loop
AsciiDoc glossary format.

Use project-local evidence first, such as `.idea/`, `.vscode/`, and
workspace files when present.

For VS Code, Cursor, and other VS Code-based IDEs, prefer the helper
script check path when the server-based setup path, this skill
directory, and a supported editor CLI are available.

Stay inside the project directory unless the user explicitly approves
inspection of editor state outside the repository. If plugin or IDE
settings cannot be verified from project-local files, ask before
inspecting external editor config directories.

Ask for confirmation before changing plugins, extensions, IDE settings,
or editor settings.

When the user clearly means an obvious typo fix or a trivial one-word
ambiguity, confirm it plainly. Do not invoke formal clarification
framing for that.

For VS Code, Cursor, and other VS Code-based IDEs, read
[vscode-setup.md](vscode-setup.md).
For JetBrains IDEs, read [jetbrains-setup.md](jetbrains-setup.md).

Use only the setup documents that actually exist here.
