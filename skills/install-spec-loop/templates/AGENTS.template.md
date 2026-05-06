## SPEC LOOP PRE-FLIGHT

<CONSTITUTION_BLOCK>

<OPTIONAL_GLOSSARY_BLOCK>

## SPEC LOOP PROJECT SETTINGS

- Task directory: `<TASK_DIR>`
- Installation form: fallback instruction file
- Content mode: `<INSTALL_MODE>`
- Glossary support installed: `<GLOSSARY_SUPPORT_INSTALLED>`
- Current glossary mode: `<GLOSSARY_STATE>`

## SPEC LOOP OPERATING RULES

- Treat the loaded Constitution as mandatory governance.
- Keep this fallback file only when equivalent higher-level harness
  instructions are not already active.
- Preserve the current installation form and content mode during updates
  unless the user explicitly asks to migrate them.
- If glossary support is installed but glossary mode is currently opted out,
  do not create or update `glossary.adoc` unless it already exists or the
  user explicitly asks to opt in or create it.
