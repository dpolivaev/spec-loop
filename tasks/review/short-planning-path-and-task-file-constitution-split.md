# Task: split short planning path rules from task-file constitution
- **Task Identifier:** 2026-05-16-short-planning-path-split
- **Status:** review
- **Scope:**
  Update the Spec Loop planning skills so straight-line changes may stay
  in chat under explicit criteria, while task-file-specific rules move
  behind a renamed task-file constitution.
- **Motivation:**
  The current `spec-loop-plan-task` workflow treats task-file creation
  as the default for executable changes. That is too heavy for some
  first-pass, straight-line changes, but the stronger task-file path is
  still valuable once research, design branching, or verification grow.
- **Scenario:**
  A user requests a narrow straight-line change. The agent takes a short
  planning path in chat, presents that chat plan as a request to both
  skip task-file creation and approve implementation, and only promotes
  the work to a task file if the task exceeds the short-path criteria.
- **Constraints:**
  - Planning remains mandatory before implementation.
  - The short planning path must require explicit user approval both to
    skip task-file creation and to implement from the chat plan.
  - Once a task file exists for a task, the same task stays on the
    task-file path.
  - Existing task-file workflow rules, formatting rules, and lifecycle
    rules remain available under the renamed task-file constitution.
  - `spec-loop-prepare-implementation-approval` must become task-file
    specific rather than universally mandatory.
- **Briefing:**
  The main open point is the final wording and structure of
  `skills/spec-loop-plan-task/SKILL.md` and the task-file-local wording
  in `task-file-constitution.md`. The user wants the skill to present
  its core rules in a clearer order, with the phase model earlier and
  task-file-path-specific follow-on reading only after path selection.
  The user also wants the constitution's project glossary section to be
  task-file-local and concise, without awkward back-references to the
  planning skill from inside that section.
- **Research:**
  Verified current state:
  - The user reverted the broad constitution rewrite and preserved the
    rename directly with `git mv`, so the final work should keep that
    direct rename and apply only targeted edits to
    `skills/spec-loop-plan-task/task-file-constitution.md`.
  - The separate `workflow-rules.md` file is not the chosen end state;
    its relevant content should live in
    `skills/spec-loop-plan-task/SKILL.md` instead.
  - The user wants all mandatory follow-on files to be directly
    reachable from `skills/spec-loop-plan-task/SKILL.md`, with a clear
    instruction to read them fully when relevant.
  - The user does not like the current section and paragraph order in
    `SKILL.md`; specifically, the phase model should appear earlier.
  - When the LLM reads `task-file-constitution.md`, it can assume the
    content of `skills/spec-loop-plan-task/SKILL.md` is already known.
  - ADR routing should be handled by the planning skill, not by the
    task-file constitution.
  - Glossary trigger rules may apply on both the short planning path
    and the task-file path, so they cannot live only in the
    task-file constitution.
  - The glossary skill defines the Spec Loop AsciiDoc glossary format,
    but not the trigger rules for when glossary updates are required.
  - `spec-loop-prepare-implementation-approval` is already narrowed to
    task-file-path approval preparation.
  - `spec-loop-assess-pull-request` must read the split shared files
    explicitly because retrospective review reuses task-file artifact
    structure even though it does not follow the normal planning-path
    routing or implementation-approval gate.
- **Design:**
  Final decisions for this increment:
  1. Keep `skills/spec-loop-plan-task/SKILL.md` as the entry point and
     home for the planning-path rules of `spec-loop-plan-task`.
  2. Merge the current `workflow-rules.md` content into
     `skills/spec-loop-plan-task/SKILL.md` instead of keeping a
     separate file.
  3. Keep the direct rename from `constitution.md` to
     `task-file-constitution.md`.
  4. Keep `task-file-constitution.md` as the task-file-path-specific
     artifact rules file.
  5. Move ADR routing and standalone ADR conventions into
     `skills/spec-loop-plan-task/SKILL.md`.
  6. Move glossary trigger rules that apply on both planning paths into
     `skills/spec-loop-plan-task/SKILL.md`, while keeping task-file
     glossary semantics in `task-file-constitution.md`.
  7. Teach `spec-loop-plan-task/SKILL.md` to route between:
     - short planning path in chat, and
     - task-file path governed by `task-file-constitution.md`.
  8. Define the short planning path criteria positively:
     - first planning pass in the current conversation,
     - lightweight research,
     - a single clear implementation path,
     - lightweight verification that is easy to track in chat,
     - no task file exists yet for the task.
  9. Require the chat plan to seek approval both to skip task-file
     creation and to implement from the chat plan.
  10. Update `spec-loop-prepare-implementation-approval` so it applies
      only when the task-file path is in use and no longer refers to a
      separate workflow-rules file.
  11. Keep tutorial implementation steps on the task-file path even
      after the short planning path is introduced; tutorials may still
      use explicit docs-only or ADR-only paths when stated.
  12. Update repository documentation to point to `SKILL.md` plus
      `task-file-constitution.md`, not to `workflow-rules.md`.
  13. Reorder `skills/spec-loop-plan-task/SKILL.md` so the phase model
      appears earlier and task-file-path-specific follow-on reading no
      longer appears before path selection.
  14. Keep glossary trigger logic in `skills/spec-loop-plan-task/SKILL.md`
      because it applies on both planning paths and is not owned by the
      glossary-format skill.
  15. Reduce the constitution's project glossary section to task-file
      consequences only, without back-referencing `SKILL.md` from that
      section.
  16. Update `spec-loop-assess-pull-request` so it reads the planning
      skill entry point plus `task-file-constitution.md` explicitly and
      states which planning-path rules do not apply to retrospective
      review generation.
- **Test specification:**
  - **Automated checks:**
    - run repository searches to confirm references point to the final
      files and wording consistently;
    - confirm `skills/spec-loop-plan-task/constitution.md` no longer
      exists;
    - confirm `skills/spec-loop-plan-task/workflow-rules.md` no longer
      exists.
  - **Manual verification:**
    - review `skills/spec-loop-plan-task/SKILL.md` to confirm the short
      planning path, sticky task-file path, ADR/docs routing, glossary
      trigger rules, and phase rules are stated there clearly and in a
      clearer order;
    - review `task-file-constitution.md` to confirm task-file-only
      rules remain available after the split and the project glossary
      section no longer back-references the planning skill;
    - review `spec-loop-assess-pull-request` to confirm it explicitly
      reads the planning skill entry point and
      `task-file-constitution.md` without depending on
      `workflow-rules.md`;
    - review both tutorials to confirm they explicitly keep
      implementation work on the task-file path.
