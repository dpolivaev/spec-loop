# JetBrains Setup Reference for `setup-task-and-glossary-rendering`

Use this reference when `setup-task-and-glossary-rendering` is helping a user
who works in a JetBrains IDE.

## Decision rules

- PlantUML support in Markdown preview is mandatory unless the user
  explicitly opts out of PlantUML.
- Explain the consequence of opting out: Spec Loop diagrams in Markdown will
  stay as code blocks or will not preview correctly.
- AsciiDoc support is mandatory when Spec Loop glossary format guidance is
  installed, when an existing project glossary uses `glossary.adoc`, or when
  the configured default format for new glossaries is `adoc`.
- Explain the consequence of skipping AsciiDoc support: `glossary.adoc`
  will lose normal editing and preview support.
- Ask for confirmation before changing plugins or IDE settings.

## Detect before changing anything

Check whether the IDE already has:

- a working Markdown preview,
- the Markdown PlantUML extension path available, and
- AsciiDoc support whenever `glossary.adoc` is active or configured as the
  default format for new glossaries.

If those pieces are already in place, report that no JetBrains setup change is
needed.

## PlantUML setup

For most JetBrains IDEs:

1. Open **Settings / Preferences -> Languages & Frameworks -> Markdown**.
2. Ensure Markdown preview works.
3. Enable the **PlantUML** Markdown extension.

If Markdown preview is missing or blank, especially in Android Studio:

1. Fix the runtime or preview support first, typically by switching to a
   JetBrains Runtime with JCEF.
2. After preview works, enable the PlantUML Markdown extension.

## AsciiDoc setup

Install the **AsciiDoc** plugin.

If `glossary.adoc` is active or configured as the default format for new
glossaries, also ensure the preview path needed by the plugin works in the
current IDE/runtime.

## What `setup-task-and-glossary-rendering` should do

1. Detect whether the IDE already supports Markdown PlantUML preview and, if
   needed, AsciiDoc.
2. If support is missing, explain what is missing and why it matters.
3. Show the exact planned plugin or IDE-setting changes.
4. Apply them after confirmation when the harness can do so.
5. Otherwise, tell the user exactly which plugin or setting to enable.
