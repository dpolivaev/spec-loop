---
name: install-spec-loop
description: >-
  Bootstrap or update Spec Loop by installing plan-task and optional
  write-glossary skills or one instruction file, then guide required editor
  setup.
---

This skill bootstraps or updates the rest of Spec Loop. It does not install
itself.

## Operating rules

- Recommend installing skills over instruction-file installs unless the
  harness makes that impractical.
- Installation form is mutually exclusive: install either skills or one
  instruction file, never both in the same run.
- For fresh installs, recommend linked mode.
- Suggest `tasks/` as the default task directory.
- Recommend PlantUML unless there is a good reason to choose Mermaid.
- Recommend installing Spec Loop glossary format guidance as an optional
  capability.
- For updates, preserve the detected installation form, content mode, and
  glossary defaults unless the user explicitly asks to migrate or change
  them.
- Never search the whole computer or the user's home directory for
  `spec-loop` or related files.
- Only inspect the current project, active harness instructions, and
  user-confirmed locations.
- Ask only the missing questions.
- Use clear, concise, easy-to-understand language.
- Avoid unnecessary technical terms. If a technical term is needed,
  explain it briefly in plain language.
- Help the user understand the practical differences between options and why
  they matter so the user can make informed decisions.
- Do not overload the user with too much information at once. Give only the
  information needed for the current decision, and expand only when helpful
  or requested.
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
   - an instruction file that references or embeds Spec Loop governance,
   - copied governance files from a snapshot install,
   - active higher-level harness instructions that already load equivalent
     Spec Loop governance.
4. If detection is uncertain, infer the most likely setup and present that
   guess for user confirmation.
5. For updates, identify:
   - installation form: skills or instruction file,
   - content mode: linked, snapshot, or embedded,
   - scope: global user config or project config,
   - whether Spec Loop glossary format guidance is installed,
   - missing glossary creation default,
   - default glossary format when creating, if Spec Loop glossary format
     guidance is not installed,
   - task directory.

## 2. Resolve the source repository

1. First check whether this `SKILL.md` itself lives inside a git
   repository whose `origin` points to the Spec Loop repository
   (`https://github.com/dpolivaev/spec-loop` or an equivalent git/ssh
   remote).
2. If it does, use that repository location automatically as the Spec
   Loop source.
3. In that case, do not ask to clone Spec Loop and do not suggest
   pulling or updating it as part of this step.
4. If this skill is being run from the Spec Loop repository itself and
   the target project is unclear, ask which project should be modified.
5. Only if this skill is not being run from the Spec Loop repository,
   ask the user whether a local Spec Loop repository already exists.
6. If it exists, ask for the exact path before using it.
7. If it exists, suggest updating it to the latest version before using
   it.
8. If it does not exist, ask whether to clone
   `https://github.com/dpolivaev/spec-loop`.
9. If cloning is needed, ask for the target directory first and keep the
   clone outside the target project directory.

## 3. Choose the installation form first

Ask whether the remaining installation should be:

1. additional skills, or
2. an instruction file / system-message file.

Recommend skills.

Before the user chooses skills, surface any lack of automatic skill discovery
or skill support in the current harness so the user can decide knowingly.

### Skill form

- Install `plan-task` as mandatory.
- Install `write-glossary` as optional Spec Loop glossary format guidance for
  `glossary.adoc`.
- `plan-task` may point to `write-glossary` for Spec Loop `glossary.adoc`
  work using the phrase `if available`.
- Write the confirmed task directory and missing glossary creation default
  into the installed `plan-task` skill.
- If `write-glossary` is not installed, also write the default glossary
  format when creating into the installed `plan-task` skill.
- Do not duplicate those defaults in `write-glossary`.

### Instruction-file form

Choose exactly one target:

- `AGENTS.md`
- `CLAUDE.md` using the AGENTS template content
- `.github/copilot-instructions.md`

Write the confirmed task directory and missing glossary creation default into
the chosen file.
If Spec Loop glossary format guidance is not installed, also write the default
glossary format when creating into the chosen file.
If Spec Loop glossary format guidance is installed, the file may reference it
even when no project glossary exists yet.
Make clear that existing `glossary.md` projects stay on their current format
unless the user explicitly asks to migrate to `glossary.adoc`.

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
2. Ask whether Spec Loop glossary format guidance should be installed and
   recommend it as an optional capability.
3. Ask for the missing glossary creation default:
   - `explicit-request-only`, or
   - `create-by-default`.
4. If Spec Loop glossary format guidance is not installed, ask for the
   default glossary format when creating:
   - `md`, or
   - `adoc`.
5. Make clear in the installed output that the user may ask to change these
   defaults later.

## 7. Apply the chosen form and mode

When a template contains `<OPTIONAL_DEFAULT_GLOSSARY_FORMAT_BLOCK>`:

- replace it with:
  - `- Default glossary format when creating: <DEFAULT_GLOSSARY_FORMAT>.`
  - `  Allowed values: md or adoc. This applies only when Spec Loop`
  - `  glossary format guidance is not installed, and the user may ask`
  - `  to change it later.`
- otherwise remove the placeholder entirely.

### Skills + linked

- Install `templates/plan-task.skill.template.md` as
  `<skill-root>/plan-task/SKILL.md`.
- If chosen, install `templates/write-glossary.template.md` as
  `<skill-root>/write-glossary/SKILL.md`.
- Replace the template placeholders with:
  - shared governance references,
  - the confirmed task directory,
  - the confirmed missing glossary creation default,
  - and `<OPTIONAL_DEFAULT_GLOSSARY_FORMAT_BLOCK>` only when
    `write-glossary` is not installed.
- Point `plan-task` to `CONSTITUTION.md` only.
- Point `write-glossary` to `glossary-skill.md` only.

### Skills + snapshot

- Install the same skill files and resolve the same placeholders.
- Copy `CONSTITUTION.md` next to `plan-task/SKILL.md`.
- If chosen, copy `glossary-skill.md` next to `write-glossary/SKILL.md`.
- Follow the standard skill directory structure.

### Skills + embedded

- Install the same skill files and resolve the same placeholders.
- Embed the full text of `CONSTITUTION.md` into `plan-task/SKILL.md`.
- If chosen, embed the full text of `glossary-skill.md` into
  `write-glossary/SKILL.md`.

### Instruction file + linked

- Start from `templates/AGENTS.template.md` or
  `templates/copilot-instructions.template.md`.
- For Claude targets, use the AGENTS template content in `CLAUDE.md`.
- Replace the template placeholders with the confirmed task directory,
  missing glossary creation default, and
  `<OPTIONAL_DEFAULT_GLOSSARY_FORMAT_BLOCK>` only when Spec Loop glossary
  format guidance is not installed.
- Point `CONSTITUTION.md` to the resolved shared path.
- If Spec Loop glossary format guidance is installed, point
  `glossary-skill.md` to the resolved shared path.

### Instruction file + snapshot

- Start from the same templates and resolve the same placeholders.
- For project-local installs, prefer copying governance files into the project
  root so relative references stay simple.
- For global installs, prefer copying governance files next to the chosen
  instruction file.
- Update the instruction file to reference the copied paths.

### Instruction file + embedded

- Start from the same templates and resolve the same placeholders.
- Embed the full text of `CONSTITUTION.md` verbatim.
- If Spec Loop glossary format guidance is installed, embed the full text of
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
- AsciiDoc support is mandatory when Spec Loop glossary format guidance is
  installed, when an existing project glossary uses `glossary.adoc`, or when
  the configured default format for new glossaries is `adoc`. Explain that
  `glossary.adoc` will otherwise lose normal editing and preview support.
- Do not handle Playwright MCP here. That belongs to tutorial-specific setup
  when a tutorial actually needs browser automation.

If possible, apply extension or configuration changes after confirmation.
Otherwise, tell the user exactly what to install or change.

## 9. Final execution pattern

1. Summarize the detected state.
2. Summarize the selected form, mode, paths, task directory, and glossary
   defaults.
3. Show the exact planned commands, file changes, config changes, and any
   optional extension changes.
4. Ask for explicit confirmation.
5. Apply minimal edits that preserve unrelated user text when updating
   existing files.
6. If the user asked for an initial setup commit, review the final setup
   change set and create that commit after the setup changes are accepted.
7. If edits cannot be applied automatically, provide exact manual follow-up
   steps.
