---
name: install-spec-loop
description: >-
  Bootstrap or update Spec Loop by installing plan-task and optional
  write-glossary skills or one fallback instruction file, then guide required
  editor setup.
---

This skill bootstraps or updates the rest of Spec Loop. It does not install
itself.

## Operating rules

- Recommend installing skills over fallback instruction files unless the
  harness makes that impractical.
- Installation form is mutually exclusive: install either skills or one
  fallback instruction file, never both in the same run.
- For fresh installs, recommend linked mode.
- Suggest `tasks/` as the default task directory.
- Recommend PlantUML unless there is a good reason to choose Mermaid.
- Recommend installing glossary support as an optional capability.
- For updates, preserve the detected installation form and content mode unless
  the user explicitly asks to migrate.
- Never search the whole computer or the user's home directory for
  `spec-loop` or related files.
- Only inspect the current project, active harness instructions, and
  user-confirmed locations.
- Ask only the missing questions.
- Always show the planned commands, file changes, config changes, and
  extension changes before editing files.
- Always ask for explicit confirmation before editing files or
  configuration.
- If equivalent governance is already active in the harness, default to no
  injection and explain that no file changes are needed unless the user wants
  an update or migration.
- If edits cannot be applied automatically, provide exact manual follow-up
  steps.

## 1. Determine whether this is an install or an update

1. Ask the user whether they want a fresh install, an update, or a detected
   recommendation based on the current setup.
2. Only treat Spec Loop as already installed when you find explicit evidence
   in user-confirmed target locations or in active harness instructions.
3. Explicit evidence includes:
   - installed `plan-task` or `write-glossary` skills,
   - a fallback instruction file that references or embeds Spec Loop
     governance,
   - copied governance files from a snapshot install,
   - active higher-level harness instructions that already load equivalent
     Spec Loop governance.
4. If detection is uncertain, infer the most likely setup and present that
   guess for user confirmation.
5. For updates, identify:
   - installation form: skills or fallback instruction file,
   - content mode: linked, snapshot, or embedded,
   - scope: global user config or project config,
   - glossary support state,
   - task directory.

## 2. Resolve the source repository

1. Ask the user whether a local Spec Loop repository already exists.
2. If it exists, ask for the exact path before using it.
3. If it exists, suggest updating it to the latest version before using it.
4. If it does not exist, ask whether to clone
   `https://github.com/dpolivaev/spec-loop`.
5. If cloning is needed, ask for the target directory first and keep the
   clone outside the target project directory.
6. If this skill is being run from the Spec Loop repository itself and the
   target project is unclear, ask which project should be modified.

## 3. Choose the installation form first

Ask whether the remaining installation should be:

1. additional skills, or
2. a fallback instruction file / system-message file.

Recommend skills.

Before the user chooses skills, surface any lack of automatic skill discovery
or skill support in the current harness so the user can decide knowingly.

### Skill form

- Install `plan-task` as mandatory.
- Install `write-glossary` as optional.
- `plan-task` may point to `write-glossary` for glossary-related work using
  the phrase `if available`.
- Write the confirmed task directory and glossary state into the installed
  skill files.

### Fallback instruction-file form

Choose exactly one target:

- `AGENTS.md`
- `CLAUDE.md` using the AGENTS template content
- `.github/copilot-instructions.md`

Write the confirmed task directory and glossary state into the chosen file.
If glossary support is installed, the file may reference it even when glossary
use is currently opted out. Make clear that glossary creation remains a
separate user decision unless the user explicitly opts in or `glossary.adoc`
already exists.

## 4. Choose the content mode after the form is known

Explain the trade-offs and ask the user to choose one mode:

1. linked
   - references the shared governance files in a Spec Loop clone,
   - best default for updates,
   - prefer relative paths for project-local installs when possible,
   - use absolute paths for global installs.
2. snapshot
   - copies governance files into the target layout,
   - more self-contained,
   - updates refresh copied files.
3. embedded
   - embeds the full source text verbatim into the installed output,
   - strongest compatibility fallback,
   - updates must refresh embedded text.

For fresh installs, recommend linked mode.
For updates, preserve the detected mode by default.

## 5. Resolve scope and concrete paths

Ask whether the target is:

- global user configuration, or
- one specific project.

For project installs, operate from inside the target project directory.
Confirm the concrete paths before editing.

If a path is known confidently from the harness, show it and ask for
confirmation.
If a path is unknown, ask the user for it.

## 6. Resolve task and glossary settings

1. Suggest `tasks/` as the project task directory and let the user confirm or
   override it.
2. Ask whether glossary support should be installed and recommend it as an
   optional capability.
3. Ask whether glossary use is currently opted in or opted out.
4. If glossary use is opted out, keep glossary creation as a separate user
   decision.

## 7. Apply the chosen form and mode

### Skills + linked

- Install `templates/plan-task.skill.template.md` as
  `<skill-root>/plan-task/SKILL.md`.
- If chosen, install `templates/write-glossary.template.md` as
  `<skill-root>/write-glossary/SKILL.md`.
- Replace the template placeholders with references to the shared governance
  files:
  - `plan-task` -> `CONSTITUTION.md` only,
  - `write-glossary` -> `glossary-skill.md` only.

### Skills + snapshot

- Install the same skill files.
- Copy `CONSTITUTION.md` next to `plan-task/SKILL.md`.
- If chosen, copy `glossary-skill.md` next to `write-glossary/SKILL.md`.
- Follow the standard skill directory structure.

### Skills + embedded

- Install the same skill files.
- Embed the full text of `CONSTITUTION.md` into `plan-task/SKILL.md`.
- If chosen, embed the full text of `glossary-skill.md` into
  `write-glossary/SKILL.md`.

### Fallback instruction file + linked

- Start from `templates/AGENTS.template.md` or
  `templates/copilot-instructions.template.md`.
- For Claude targets, use the AGENTS template content in `CLAUDE.md`.
- Point `CONSTITUTION.md` to the resolved shared path.
- If glossary support is installed, point `glossary-skill.md` to the resolved
  shared path.

### Fallback instruction file + snapshot

- Start from the same templates.
- For project-local installs, prefer copying governance files into the project
  root so relative references stay simple.
- For global installs, prefer copying governance files next to the chosen
  instruction file.
- Update the instruction file to reference the copied paths.

### Fallback instruction file + embedded

- Start from the same templates.
- Embed the full text of `CONSTITUTION.md` verbatim.
- If glossary support is installed, embed the full text of
  `glossary-skill.md` verbatim.

## 8. Editor setup

Use the moved setup references under `docs/`:

- `docs/vscode-setup.md`
- `docs/jetbrains-setup.md`

Rules:

- Recommend PlantUML unless there is a good reason to choose Mermaid.
- PlantUML support is mandatory unless the user explicitly opts out of
  PlantUML. Explain that task and design diagrams will otherwise remain plain
  code blocks or render poorly.
- AsciiDoc support is mandatory when glossary support is installed and the
  user has not opted out of glossary workflow. Explain that `glossary.adoc`
  will otherwise lose normal editing and preview support.
- Do not handle Playwright MCP here. That belongs to tutorial-specific setup
  when a tutorial actually needs browser automation.

If possible, apply extension or configuration changes after confirmation.
Otherwise, tell the user exactly what to install or change.

## 9. Final execution pattern

1. Summarize the detected state.
2. Summarize the selected form, mode, paths, task directory, and glossary
   state.
3. Show the exact planned commands, file changes, config changes, and any
   optional extension changes.
4. Ask for explicit confirmation.
5. Apply minimal edits that preserve unrelated user text when updating
   existing files.
6. If the user asked for an initial setup commit, review the final setup
   change set and create that commit after the setup changes are accepted.
7. If edits cannot be applied automatically, provide exact manual follow-up
   steps.
