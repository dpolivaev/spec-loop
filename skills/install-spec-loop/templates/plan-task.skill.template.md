---
name: plan-task
description: >-
  Create or verify an implementation-ready Spec Loop task for non-trivial
  coding work. Use this skill for clarification, decomposition, design,
  task-file updates, and readiness checks before implementation.
---

<PLAN_GOVERNANCE_BLOCK>

- Task files live under `<TASK_DIR>`.
- Missing glossary creation default: `<MISSING_GLOSSARY_CREATION_DEFAULT>`.
  Allowed values: `explicit-request-only` or `create-by-default`. The user may
  ask to change this default later.
<OPTIONAL_DEFAULT_GLOSSARY_FORMAT_BLOCK>
- If a project glossary already exists, maintain it under the Constitution.
- Recognize `glossary.adoc` and `glossary.md` as project glossary files. If
  both exist, ask the user which one is canonical before updating either.
- If no project glossary exists and the missing glossary creation default is
  `create-by-default`, create one when approved work first needs shared
  domain terms. Use the Spec Loop `glossary.adoc` format when
  `write-glossary` is installed; otherwise use the configured default format
  when creating without imposing the Spec Loop `glossary.adoc` structure.
- If glossary-related work uses the Spec Loop `glossary.adoc` format, use
  `write-glossary` if available.
- This skill plans or updates task files only. It does not implement unless
  the user explicitly approves implementation.
