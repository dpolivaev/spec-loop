## SPEC LOOP PRE-FLIGHT

<CONSTITUTION_BLOCK>

<OPTIONAL_GLOSSARY_BLOCK>

## SPEC LOOP PROJECT SETTINGS

- Task directory: `<TASK_DIR>`
- Installation form: instruction file
- Content mode: `<INSTALL_MODE>`
- Missing glossary creation default: `<MISSING_GLOSSARY_CREATION_DEFAULT>`.
  Allowed values: `explicit-request-only` or `create-by-default`. The user may
  ask to change this default later.
<OPTIONAL_DEFAULT_GLOSSARY_FORMAT_BLOCK>

## SPEC LOOP OPERATING RULES

- Treat the loaded Constitution as mandatory governance.
- Keep this instruction file only when equivalent higher-level harness
  instructions are not already active.
- Preserve the current installation form and content mode during updates
  unless the user explicitly asks to migrate them.
- If a project glossary already exists, maintain it under the Constitution.
- Recognize `glossary.adoc` and `glossary.md` as project glossary files. If
  both exist, ask the user which one is canonical before updating either.
- If no project glossary exists and the missing glossary creation default is
  `create-by-default`, create one when approved work first needs shared
  domain terms. Use the Spec Loop `glossary.adoc` format when this file also
  loads Spec Loop glossary format guidance; otherwise use the configured
  default format when creating without imposing the Spec Loop
  `glossary.adoc` structure.
- If this file includes Spec Loop glossary format guidance, apply it only to
  `glossary.adoc`. If the project uses `glossary.md`, maintain it directly
  unless the user explicitly asks to migrate.
