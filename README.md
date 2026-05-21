# Spec Loop — Design-First AI-Assisted Development

<p align="center">
  <a href="docs/infographics.svg">
    <img src="docs/infographics.svg" alt="Spec-Loop infographic" width="80%" style="border: 1px solid rgba(127, 127, 127, 0.35); border-radius: 12px; box-shadow: 0 1px 2px rgba(0,0,0,0.08);">
  </a>
</p>

There are two common ways people use AI for coding.

**Vibecoding:** you describe intent, the model fills in the gaps, and you get a
large diff with undocumented decisions. Review becomes archaeology. Tests are
optional by accident.

**Waterfall:** you try to avoid that by writing a complete spec first. You
can’t. Constraints appear during implementation. The spec inflates, then it
either blocks change or gets ignored.

Spec Loop avoids both: write the next small spec, review it, then implement it
with tests. Keep the spec local to the next step. Repeat until done.

Spec Loop is a framework of reusable skills.

Its main governing rules live in the `spec-loop-plan-task` bundle and
its task-file companions.

The planning bundle starts with
**[SKILL.md](skills/spec-loop-plan-task/SKILL.md)**
and, when the task-file path is chosen,
**[task-file-constitution.md](skills/spec-loop-plan-task/task-file-constitution.md)**.
That bundle governs plan-first work, the short planning path in chat,
the task-file path when needed, ADR and documentation routing,
glossary triggers, and the approval gate before implementation.

On the task-file path, pre-implementation readiness is prepared by
`spec-loop-prepare-implementation-approval`, and approved
implementation is then carried out under
`spec-loop-implementation-flow`.

The `spec-loop-write-glossary` skill defines the Spec Loop AsciiDoc glossary
format in
**[glossary-format.md](skills/spec-loop-write-glossary/glossary-format.md)**.

The `spec-loop-setup-doc-rendering` skill helps users prepare and
troubleshoot rendering for task files and glossary files.

The `spec-loop-assess-pull-request` skill is optional. It reconstructs
retrospective review files from trusted pull requests, merge requests,
or commit ranges and can generate GitHub-friendly Mermaid variants
from them when needed.

The model uses these skills while drafting and updating plans, task, or
review artifacts; you review and approve either a short chat plan or a
task-file plan before implementation. On task-file work, approved
implementation then continues under `spec-loop-implementation-flow`,
which governs implementation-time routing, the
`Implementation notes` checkpoint, and the move to `review`. When the
code already exists, you inspect retrospective review files instead.

Spec Loop also defines explicit work phases: plan, implementation, and done.
Any transitions to implementation and to done require explicit user approval.

When a project maintains a glossary described by the task-file
constitution's
[project glossary section](skills/spec-loop-plan-task/task-file-constitution.md#project-glossary),
that glossary defines the shared domain language above individual tasks
and the code. It keeps design documents, tests, code symbols, and
commit text aligned on the same terms across the whole project.

## Why This Works with Large Codebases

Spec Loop is designed to work with existing codebases at scale.
Before any design or implementation step, the model captures relevant
knowledge in the Research section of the task file: existing behavior,
constraints, APIs, interfaces, and established code practices.

It follows the classic research–plan–implement approach, broken down
into small, incremental sub-tasks.

The research is explicitly scoped to the next increment. It captures only
what is required to implement that increment correctly, and is intentionally
partial. The result is a bounded, reviewable understanding whose size
remains manageable.

For large codebases, the glossary is especially useful because it
keeps domain terms stable across many increments, files, and
subsystems.

Because the scope can be kept reasonably small and the research is
written down, you can verify that the model examined the right parts of
the codebase, identified the correct interfaces, and aligned with
existing practices before any code is written. This is especially
valuable in legacy systems: it prevents clean-room redesigns and makes
incremental change safer.

## Document Roles and Lifetimes

Spec Loop uses more than one document type on purpose. They do not have
the same job or the same lifetime.

- Task files are short-lived working artifacts for the next concrete
  slice of work when the task-file path is in use. They exist to drive
  research, review, implementation, and testing of that slice.
- ADRs capture durable decisions and the reasons behind them.
- Documentation-only work may stand on its own when no executable
  change is involved and no project rule requires a task file.
- A glossary captures stable shared language across tasks, design,
  tests, code symbols, and commits.
- Review files reconstruct and assess already-implemented work from
  trusted pull requests, merge requests, or commit ranges. When needed,
  they may also produce GitHub-friendly Mermaid variants for sharing
  the review.
- Living project documents capture current truth that should remain
  useful after the task is accepted, such as technical shape,
  operations, or other stable project knowledge.

Historical task files do not need to be kept mutually consistent across
time. The active task, however, should stay aligned with the glossary,
living project documents, and implemented code for its scope.

If a project maintains a technical design document, its purpose is to
describe the current technical shape, stable boundaries, and important
flows. It should not become a second glossary or a catalog of transient
implementation detail.

## Getting Started

Apply the process to your repository.

### Install the skills with `npx skills`

Recommended path:

Install the core task-workflow skills together.
`spec-loop-plan-task`, `spec-loop-prepare-implementation-approval`,
`spec-loop-implementation-flow`, `spec-loop-write-glossary`, and
`spec-loop-setup-doc-rendering` hand off to each other, reuse the
shared `spec-loop-plan-task` bundle, and support the same planning
artifacts.

`spec-loop-assess-pull-request` is optional. Install it only if you
need retrospective review of pull requests, merge requests, or commit
ranges from repositories you trust. It fetches provider or Git content
as review evidence and is not required for the main planning workflow.

1. Ensure Node.js is available so `npx` works.
2. If you do not need `spec-loop-assess-pull-request`, use a selective
   installation variant from the skills tool documentation and install
   only the five core task-workflow skills listed above.
3. If you want the full bundle, including optional
   `spec-loop-assess-pull-request`, install all Spec Loop skills
   interactively for the current project:

```bash
npx skills add dpolivaev/spec-loop -s '*'
```

4. For global installation of the full bundle for all agents,
   non-interactively:

```bash
npx skills add dpolivaev/spec-loop -g --all
```

Those full-bundle commands install `spec-loop-assess-pull-request`
too. If you do not need it, use a selective installation variant
instead.

`--all` installs all skills for all supported agents. For selective,
single-agent, or other installation variants, see
https://github.com/vercel-labs/skills.

### Prepare task and glossary rendering

Spec Loop task files use embedded PlantUML diagrams, and Spec Loop
glossaries may include embedded diagrams. Prepare your editor for
reviewing rendered task files and glossary files before continuing.

Ask the agent to use the `spec-loop-setup-doc-rendering` skill to
prepare your editor preview setup.

For example:

```text
Please use the `spec-loop-setup-doc-rendering` skill to help me
prepare my editor for reviewing rendered Spec Loop task files and
glossary files.

My coding harness may run in a terminal, but I review files in
<VS Code or JetBrains>.
```

If you review files in VS Code, you can also run the helper script
directly instead of asking an agent to use the skill. You can either
run it from a local checkout or download the current main-branch copy
directly from
[`setup-vscode-server-based.sh`](https://raw.githubusercontent.com/dpolivaev/spec-loop/refs/heads/main/skills/spec-loop-setup-doc-rendering/scripts/setup-vscode-server-based.sh).
The script requires a VS Code CLI command on `PATH` (`code`,
`code-insiders`, `code.cmd`, or `code-insiders.cmd`) and is intended
for macOS, Linux, WSL, and Git Bash for Windows.

The helper automates only the VS Code server-based PlantUML preview
path together with the AsciiDoc extension used by Spec Loop glossaries.
It does not automate the local-only PlantUML path or JetBrains setup.

From a local checkout:

```bash
bash skills/spec-loop-setup-doc-rendering/scripts/setup-vscode-server-based.sh --check
bash skills/spec-loop-setup-doc-rendering/scripts/setup-vscode-server-based.sh --apply
```

Without a local checkout:

```bash
curl -fsSLO https://raw.githubusercontent.com/dpolivaev/spec-loop/refs/heads/main/skills/spec-loop-setup-doc-rendering/scripts/setup-vscode-server-based.sh
bash setup-vscode-server-based.sh --check
bash setup-vscode-server-based.sh --apply
```

### Update installed skills

Project-level update:

```bash
npx skills update
```

Global update:

```bash
npx skills update -g
```

### Manual fallback when `npx` is unavailable

If `npx` is not available, clone or download this repository and copy
the core task-workflow skills from `skills/` into your agent's skills
directory. Keep that core bundle together.

Install `spec-loop-assess-pull-request` only if you need retrospective
review of trusted repositories.

Which directory your agent uses is agent-specific. See
https://github.com/vercel-labs/skills for agent-specific installation details.

### If your harness does not automatically apply installed skills

Some coding harnesses expose installed skills to the model but do not
reliably apply them unless the user prompt or project instructions make
their use explicit.

If your harness behaves that way, add a project instruction such as:

```text
Use the `spec-loop-plan-task` skill for all non-trivial work unless I explicitly
opt out.
On the task-file path, use `spec-loop-prepare-implementation-approval`
before asking for implementation approval and
`spec-loop-implementation-flow` after task-file implementation
approval.
Use the `spec-loop-write-glossary` skill for `glossary.adoc` glossary work.
Follow the workflow rules loaded through the `spec-loop-plan-task`
skill, including the short-planning-path routing and the
PLAN -> IMPLEMENTATION explicit approval gate.
```

If your harness supports project instruction files such as `AGENTS.md`,
put the rule there. That is more reliable than relying on ordinary chat
context alone.

## Included Skills

This repository currently ships these skills.
The first five form the core task-workflow bundle and should be
installed together. `spec-loop-assess-pull-request` is optional and
intended only for retrospective review of trusted repositories:

1. **`spec-loop-plan-task`**
   - the planning and task-administration skill for non-trivial work;
   - defined by
     [skills/spec-loop-plan-task/SKILL.md](skills/spec-loop-plan-task/SKILL.md)
     and, on the task-file path,
     [skills/spec-loop-plan-task/task-file-constitution.md](skills/spec-loop-plan-task/task-file-constitution.md).

2. **`spec-loop-prepare-implementation-approval`**
   - the approval-preparation skill used only on the task-file path
     before the agent asks for implementation approval within the
     `spec-loop-plan-task` workflow;
   - defined by
     [skills/spec-loop-prepare-implementation-approval/implementation-approval-guidance.md](skills/spec-loop-prepare-implementation-approval/implementation-approval-guidance.md).

3. **`spec-loop-implementation-flow`**
   - the mandatory task-file-path implementation-flow skill used after
     implementation approval when implementation deviates from the
     approved task, when uncertainty or blocking questions arise, or
     before handing implemented work over for review;
   - defined by
     [skills/spec-loop-implementation-flow/implementation-flow-guidance.md](skills/spec-loop-implementation-flow/implementation-flow-guidance.md).

4. **`spec-loop-write-glossary`**
   - the Spec Loop AsciiDoc glossary-format skill;
   - defined by
     [skills/spec-loop-write-glossary/glossary-format.md](skills/spec-loop-write-glossary/glossary-format.md).

5. **`spec-loop-setup-doc-rendering`**
   - the setup and troubleshooting skill for rendering task files and
     glossary files.

6. **`spec-loop-assess-pull-request`**
   - the optional retrospective review skill for existing pull requests,
     branch diffs, or commit ranges from trusted repositories;
   - defined by
     [skills/spec-loop-assess-pull-request/review-guidance.md](skills/spec-loop-assess-pull-request/review-guidance.md).

## Documentation

1. Check the planning and implementation-flow skills briefly.

   * **[skills/spec-loop-plan-task/SKILL.md](skills/spec-loop-plan-task/SKILL.md)**
     defines planning-path selection, approval and escalation rules,
     ADR and documentation routing, glossary triggers, and phase rules.
   * **[skills/spec-loop-plan-task/task-file-constitution.md](skills/spec-loop-plan-task/task-file-constitution.md)**
     defines the task-file-specific rules: task files, research/design
     discipline, lifecycle and traceability requirements, section
     structure, and testing policy.
   * **[skills/spec-loop-prepare-implementation-approval/implementation-approval-guidance.md](skills/spec-loop-prepare-implementation-approval/implementation-approval-guidance.md)**
     defines task-file approval-readiness preparation before
     implementation approval.
   * **[skills/spec-loop-implementation-flow/implementation-flow-guidance.md](skills/spec-loop-implementation-flow/implementation-flow-guidance.md)**
     defines post-approval implementation-time routing,
     `Implementation notes`, and `review` transition checks.

2. Study the Wordle example by commit history.

   * The [Wordle commit history](https://gitlab.com/dpolivaev/spec-loop/-/commits/main/examples/wordle)
     shows the workflow under real version-control pressure: how task
     specifications evolve step by step, and how implementation and tests
     follow approved design.

3. Check
   **[Review, Responsibility, and Traceability](docs/review-responsibility-and-traceability.md)**.
   It explains how short chat planning, task files, workflow rules, and
   the task-file constitution map to team development practice:
   boundaries, responsibility, commit linking, and status discipline.

4. Follow one of the hands-on tutorials.

   * **[Wordle Tutorial](docs/wordle-tutorial.md)** walks through a
     compact Java example with staged planning, approvals,
     implementation, glossary maintenance, and testing.
   * **[Online Art Game Tutorial](docs/online-art-game-tutorial.md)**
     walks through a complete browser-oriented example with staged
     planning, approvals, implementation, and testing.
   * The two tutorials teach the same Spec Loop workflow: planning
     first, explicit approval before implementation, small reviewable
     tasks or subtasks, verification, and user correction when the LLM
     misses a supporting update. The main difference is the technical
     setting: Wordle is a compact Java path, while the online art game
     is browser-oriented. You can choose either tutorial.

5. Project glossary conventions.

   * See the task-file constitution's project glossary section above.
   * **[skills/spec-loop-write-glossary/glossary-format.md](skills/spec-loop-write-glossary/glossary-format.md)**
     is the shared glossary-format guidance file and includes its own
     embedded example.

Recommended quick-check order:
- `README.md`
- `skills/spec-loop-plan-task/SKILL.md`
- `skills/spec-loop-plan-task/task-file-constitution.md`
- `skills/spec-loop-prepare-implementation-approval/implementation-approval-guidance.md`
- `skills/spec-loop-implementation-flow/implementation-flow-guidance.md`
- `docs/review-responsibility-and-traceability.md`
- `docs/online-art-game-tutorial.md`
- `docs/wordle-tutorial.md`

## Diagrams: PlantUML Default, Mermaid Fallback

Spec Loop treats diagrams as specification artifacts: they make design
intent reviewable at the same boundary as the surrounding text.

Where the task-file constitution requires diagrams in task files, use
PlantUML by default.

Mermaid is a poorer but still possible alternative when the User or
another governing instruction explicitly prefers Mermaid, for example
when GitHub or similar environments are used and PlantUML is not
rendered.

PlantUML remains the recommended default in practice because it is
usually easier to keep precise and reviewable for the structural and
behavioral design work used in Spec Loop.

For inline PlantUML rendering in Markdown on the web, view the repo on
[GitLab](https://gitlab.com/dpolivaev/spec-loop). GitHub does not
render PlantUML embedded in Markdown natively, so reading there can
degrade the intended experience.

For local preview setup, use the
`spec-loop-setup-doc-rendering` skill.

## License

Licensed under the MIT License. See [LICENSE](LICENSE).

## Origin

This framework was developed and applied in
Freeplane.
