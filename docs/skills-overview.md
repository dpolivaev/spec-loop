# Skills Overview

## Included Skills

This repository currently ships these skills:
- The core task-workflow bundle is [spec-loop-plan-task](../skills/spec-loop-plan-task/),
  [spec-loop-plan-work-breakdown](../skills/spec-loop-plan-work-breakdown/),
  [spec-loop-clarify-task](../skills/spec-loop-clarify-task/),
  [spec-loop-prepare-implementation-approval](../skills/spec-loop-prepare-implementation-approval/),
  [spec-loop-implementation-flow](../skills/spec-loop-implementation-flow/), and
  [spec-loop-write-adr](../skills/spec-loop-write-adr/).
- [spec-loop-write-glossary](../skills/spec-loop-write-glossary/) is required when a
  project uses a project glossary in the Spec Loop AsciiDoc format.
- [spec-loop-setup-doc-rendering](../skills/spec-loop-setup-doc-rendering/) and
  [spec-loop-assess-pull-request](../skills/spec-loop-assess-pull-request/) are
  optional.

1. **[spec-loop-plan-task](../skills/spec-loop-plan-task/)**
   - the planning and task-administration skill for non-trivial work;
   - defined by
     [skills/spec-loop-plan-task/SKILL.md](../skills/spec-loop-plan-task/SKILL.md),
     [skills/spec-loop-plan-task/common-task-guidance.md](../skills/spec-loop-plan-task/common-task-guidance.md),
     [skills/spec-loop-plan-task/scenario-and-glossary-guidance.md](../skills/spec-loop-plan-task/scenario-and-glossary-guidance.md)
     when Scenario or task Glossary work is needed,
     [skills/spec-loop-plan-task/chat-only-path-guidance.md](../skills/spec-loop-plan-task/chat-only-path-guidance.md)
     on the chat-only path, and
     [skills/spec-loop-plan-task/task-file-path-guidance.md](../skills/spec-loop-plan-task/task-file-path-guidance.md)
     on the task-file path.

2. **[spec-loop-plan-work-breakdown](../skills/spec-loop-plan-work-breakdown/)**
   - the file-based work breakdown skill; turns large project,
     feature, or initiative ideas into task-file tasks or subtasks,
     enforces independently acceptable items and releasable
     implementation increments by default, and requires explicit user
     opt-out for non-releasable items;
   - defined by
     [skills/spec-loop-plan-work-breakdown/SKILL.md](../skills/spec-loop-plan-work-breakdown/SKILL.md).

3. **[spec-loop-clarify-task](../skills/spec-loop-clarify-task/)**
   - the clarification skill for underspecified task creation, task
     updates, design updates, and ADR decisions; preferred over
     generic grill-me variants in Spec Loop workflows;
   - defined by
     [skills/spec-loop-clarify-task/SKILL.md](../skills/spec-loop-clarify-task/SKILL.md).

4. **[spec-loop-prepare-implementation-approval](../skills/spec-loop-prepare-implementation-approval/)**
   - the approval-preparation skill used only on the task-file path
     before the agent asks for implementation approval within the
     [spec-loop-plan-task](../skills/spec-loop-plan-task/) workflow;
   - defined by
     [skills/spec-loop-prepare-implementation-approval/implementation-approval-guidance.md](../skills/spec-loop-prepare-implementation-approval/implementation-approval-guidance.md).

5. **[spec-loop-implementation-flow](../skills/spec-loop-implementation-flow/)**
   - the mandatory implementation-flow skill used after
     implementation approval on either planning path when
     implementation deviates from the approved task, when uncertainty
     or blocking questions arise, or before handing implemented work
     over for review or ready-state presentation;
   - defined by the shared core
     [skills/spec-loop-implementation-flow/implementation-flow-guidance.md](../skills/spec-loop-implementation-flow/implementation-flow-guidance.md),
     plus the path-specific companions
     [skills/spec-loop-implementation-flow/chat-only-path-guidance.md](../skills/spec-loop-implementation-flow/chat-only-path-guidance.md)
     and
     [skills/spec-loop-implementation-flow/task-file-path-guidance.md](../skills/spec-loop-implementation-flow/task-file-path-guidance.md).

6. **[spec-loop-write-glossary](../skills/spec-loop-write-glossary/)**
   - the Spec Loop AsciiDoc project-glossary-format skill, required
     when the project uses that glossary format;
   - defined by
     [skills/spec-loop-write-glossary/glossary-format.md](../skills/spec-loop-write-glossary/glossary-format.md).

7. **[spec-loop-setup-doc-rendering](../skills/spec-loop-setup-doc-rendering/)**
   - the optional setup and troubleshooting skill for rendering task
     files and glossary files.

8. **[spec-loop-write-adr](../skills/spec-loop-write-adr/)**
   - the ADR-writing skill used when planning routes work to an
     architecture decision record or when the user asks for ADR work;
   - defined by
     [skills/spec-loop-write-adr/SKILL.md](../skills/spec-loop-write-adr/SKILL.md)
     and
     [skills/spec-loop-write-adr/adr-format.md](../skills/spec-loop-write-adr/adr-format.md).

9. **[spec-loop-assess-pull-request](../skills/spec-loop-assess-pull-request/)**
   - the optional retrospective review skill for existing pull requests,
     branch diffs, or commit ranges from trusted repositories;
   - defined by
     [skills/spec-loop-assess-pull-request/review-guidance.md](../skills/spec-loop-assess-pull-request/review-guidance.md).

## Documentation

1. Check the planning, clarification, and implementation-flow skills
   briefly.

   * **[skills/spec-loop-plan-task/SKILL.md](../skills/spec-loop-plan-task/SKILL.md)**
     defines planning-path selection, approval and escalation rules,
     ADR and documentation routing, Scenario/task Glossary triggers,
     and phase rules.
   * **[skills/spec-loop-plan-work-breakdown/SKILL.md](../skills/spec-loop-plan-work-breakdown/SKILL.md)**
     defines file-based work breakdown into task-file tasks or
     subtasks and the default releasable-increment rule.
   * **[skills/spec-loop-clarify-task/SKILL.md](../skills/spec-loop-clarify-task/SKILL.md)**
     defines how Spec Loop clarifies underspecified task creation,
     task updates, design updates, and ADR decisions during planning
     or ADR writing.
   * **[skills/spec-loop-plan-task/common-task-guidance.md](../skills/spec-loop-plan-task/common-task-guidance.md)**
     defines the shared no-subtask task form, section semantics,
     readiness rules, formatting, testing policy, and project-glossary
     policy used on both planning paths.
   * **[skills/spec-loop-plan-task/scenario-and-glossary-guidance.md](../skills/spec-loop-plan-task/scenario-and-glossary-guidance.md)**
     defines how task `Scenario` and task `Glossary` are drafted,
     how existing domain terms are reused, and how task visual
     glossaries should be written.
   * **[skills/spec-loop-plan-task/chat-only-path-guidance.md](../skills/spec-loop-plan-task/chat-only-path-guidance.md)**
     defines the chat-only-path planning mechanics: initial canonical
     chat-task expression, section-only updates, recovery
     re-emission, later-work relation handling, and promotion to the
     task-file path.
   * **[skills/spec-loop-plan-task/task-file-path-guidance.md](../skills/spec-loop-plan-task/task-file-path-guidance.md)**
     defines the task-file-specific rules: task files, lifecycle and
     traceability requirements, subtasks, and diagram rules.
   * **[skills/spec-loop-prepare-implementation-approval/implementation-approval-guidance.md](../skills/spec-loop-prepare-implementation-approval/implementation-approval-guidance.md)**
     defines task-file approval-readiness preparation before
     implementation approval.
   * **[skills/spec-loop-implementation-flow/implementation-flow-guidance.md](../skills/spec-loop-implementation-flow/implementation-flow-guidance.md)**
     defines the shared implementation-flow core: post-approval route
     semantics, canonical-section authority, shared `review` meaning,
     `Implementation notes`, and the semantic completion checklist.
   * **[skills/spec-loop-implementation-flow/chat-only-path-guidance.md](../skills/spec-loop-implementation-flow/chat-only-path-guidance.md)**
     defines the chat-only-path implementation-time mechanics:
     canonical chat updates, recovery re-emission, promotion, and
     chat-only expression of `review`.
   * **[skills/spec-loop-implementation-flow/task-file-path-guidance.md](../skills/spec-loop-implementation-flow/task-file-path-guidance.md)**
     defines the task-file-path implementation-time delta:
     authorized task-file edits, task-file `Implementation notes`
     expression, and task-file expression of `review`.

2. Study the Wordle example by commit history.

   * The [Wordle commit history](https://gitlab.com/dpolivaev/spec-loop/-/commits/main/examples/wordle)
     shows the workflow under real version-control pressure: how task
     specifications evolve step by step, and how implementation and tests
     follow approved design.

3. Check
   **[Governance, Review, and Traceability](review-responsibility-and-traceability.md)**.
   It explains how fileless chat tasks, task files, workflow rules,
   common task guidance, and the task-file path guidance map to team
   development practice:
   boundaries, responsibility, commit linking, and status discipline.

4. Compare framework trade-offs.

   * **[AI Workflow Framework Comparison](skill-framework-comparison.md)**
     compares Spec Loop with OpenSpec, Superpowers, grill-with-docs,
     and agent-skills by workflow purpose and cost.

5. Follow one of the hands-on tutorials.

   * **[Wordle Tutorial](wordle-tutorial.md)** walks through a
     compact Java example with staged planning, approvals,
     implementation, glossary maintenance, and testing.
   * **[Online Art Game Tutorial](online-art-game-tutorial.md)**
     walks through a complete browser-oriented example with staged
     planning, approvals, implementation, and testing.
   * The two tutorials teach the same Spec Loop workflow: planning
     first, explicit approval before implementation, small reviewable
     tasks or subtasks, verification, and user correction when the LLM
     misses a supporting update. The main difference is the technical
     setting: Wordle is a compact Java path, while the online art game
     is browser-oriented. You can choose either tutorial.

6. Project glossary conventions.

   * See the common task guidance project glossary section above for
     the project-glossary policy and the fallback to `Research` plus
     the existing codebase when no project glossary exists yet.
   * **[skills/spec-loop-write-glossary/glossary-format.md](../skills/spec-loop-write-glossary/glossary-format.md)**
     is the shared project-glossary-format guidance file.
   * Companion glossary examples live under
     [skills/spec-loop-write-glossary/examples/](../skills/spec-loop-write-glossary/examples/).

Recommended quick-check order:
- [README.md](../README.md)
- [skills/spec-loop-plan-task/SKILL.md](../skills/spec-loop-plan-task/SKILL.md)
- [skills/spec-loop-plan-work-breakdown/SKILL.md](../skills/spec-loop-plan-work-breakdown/SKILL.md)
- [skills/spec-loop-clarify-task/SKILL.md](../skills/spec-loop-clarify-task/SKILL.md)
- [skills/spec-loop-plan-task/common-task-guidance.md](../skills/spec-loop-plan-task/common-task-guidance.md)
- [skills/spec-loop-plan-task/scenario-and-glossary-guidance.md](../skills/spec-loop-plan-task/scenario-and-glossary-guidance.md)
- [skills/spec-loop-plan-task/chat-only-path-guidance.md](../skills/spec-loop-plan-task/chat-only-path-guidance.md)
- [skills/spec-loop-plan-task/task-file-path-guidance.md](../skills/spec-loop-plan-task/task-file-path-guidance.md)
- [skills/spec-loop-prepare-implementation-approval/implementation-approval-guidance.md](../skills/spec-loop-prepare-implementation-approval/implementation-approval-guidance.md)
- [skills/spec-loop-implementation-flow/implementation-flow-guidance.md](../skills/spec-loop-implementation-flow/implementation-flow-guidance.md)
- [skills/spec-loop-implementation-flow/chat-only-path-guidance.md](../skills/spec-loop-implementation-flow/chat-only-path-guidance.md)
- [skills/spec-loop-implementation-flow/task-file-path-guidance.md](../skills/spec-loop-implementation-flow/task-file-path-guidance.md)
- [docs/review-responsibility-and-traceability.md](review-responsibility-and-traceability.md)
- [docs/skill-framework-comparison.md](skill-framework-comparison.md)
- [docs/online-art-game-tutorial.md](online-art-game-tutorial.md)
- [docs/wordle-tutorial.md](wordle-tutorial.md)
