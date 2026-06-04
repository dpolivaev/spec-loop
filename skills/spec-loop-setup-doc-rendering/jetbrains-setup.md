# JetBrains Setup Reference for `spec-loop-setup-doc-rendering`

Use this reference when `spec-loop-setup-doc-rendering` is helping a user
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
- the Markdown PlantUML extension path available,
- Graphviz available as the `dot` command on `PATH`, and
- AsciiDoc support whenever `glossary.adoc` is active or configured as the
  default format for new glossaries.

If the required pieces are already in place, no JetBrains setup change is
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

If PlantUML preview shows `graphviz not found`, install Graphviz and ensure
that the `dot` command is available on `PATH`.

Graphviz here means the `dot` executable. Observed field evidence shows
that some JetBrains PlantUML rendering cases, including at least one class
diagram case, fail with `graphviz not found` when Graphviz is missing or
not configured. This reference does not claim that every JetBrains
PlantUML preview always requires Graphviz, only that it must be checked on
this path and treated as required when that error appears.

## AsciiDoc setup

Install the **AsciiDoc** plugin.

If `glossary.adoc` is active or configured as the default format for new
glossaries, also ensure the preview path needed by the plugin works in the
current IDE/runtime.
