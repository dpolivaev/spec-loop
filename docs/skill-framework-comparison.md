# AI Workflow Framework Comparison

> Relative-fit comparison of AI workflow frameworks. Rows are
> **actions or workflow goals** ordered roughly by when they appear in
> the software-development cycle.
>
> The stars show how strongly the reviewed materials support each
> activity for LLM use, based on the specificity, operational clarity,
> and enforcement visible in the documents. They are not formal
> benchmark results or a full real-world performance score.
>
> - **★★★★★** = strongest support
> - **★★★★** = strong support
> - **★★★** = meaningful secondary support
> - **★★** = limited but real support
> - **★** = weak / indirect support
> - **-** = not a purpose there, or not evidenced in the materials
>   reviewed

## Frameworks compared

- [OpenSpec](https://github.com/Fission-AI/OpenSpec) — repo-local change/spec system with proposal, specs, design, tasks, verify, and archive flow.
- [Superpowers](https://github.com/obra/superpowers) — full coding-agent methodology with design gates, task planning, TDD, subagents, worktrees, and closeout workflows.
- [Spec Loop](https://github.com/dpolivaev/spec-loop) — governed task/increment workflow with explicit research, context-building, approval, and implementation control.
- [grill-with-docs](https://github.com/mattpocock/skills/tree/main/skills/engineering/grill-with-docs) — clarification skill with strong domain-language pressure, contradiction surfacing, and optional inline glossary/ADR capture.
- [agent-skills](https://github.com/addyosmani/agent-skills) — broad engineering workflow library with strong anti-rationalization and verification patterns.

Some start with a broad problem or idea. Others start with a
specific task that is already chosen.

- **OpenSpec** and **Superpowers** start earlier from a broader
  **problem, idea, or change** and then move toward
  spec/design/tasks/implementation.
- **Spec Loop** is strongest once work has a concrete direction and
  you want explicit planning-form selection, research, alignment,
  approval, and governed implementation. It can split larger work into
  subtasks or multiple task files / backlog items, but it is still
  less focused on broad problem discovery than OpenSpec or
  Superpowers.
- **grill-with-docs** is mainly a **clarification and shared-language**
  component, not a full end-to-end SDLC framework.
- **agent-skills** spans many stages, but less as one integrated
  artifact model.

## 1. Upstream discovery and scoping

| Action / purpose | OpenSpec | Superpowers | Spec Loop | grill-with-docs | agent-skills |
|:---|:---:|:---:|:---:|:---:|:---:|
| Analyze a broad problem area before choosing implementation work | ★★★★★ | ★★★★★ | ★★ | ★★ | ★★★★ |
| Clarify a specific requested task or increment before implementation | ★★★ | ★★★★ | ★★★★★ | ★★★★★ | ★★★★ |
| Split a broad initiative or change into smaller deliverable slices/tasks | ★★★★ | ★★★★★ | ★★★★ | ★ | ★★★★ |

## 2. Shared language and durable decision context

| Action / purpose | OpenSpec | Superpowers | Spec Loop | grill-with-docs | agent-skills |
|:---|:---:|:---:|:---:|:---:|:---:|
| Challenge proposed terms against existing shared language and surface terminology conflicts | ★ | ★ | ★★★★ | ★★★★★ | ★ |
| Maintain a shared project glossary / terminology | ★ | - | ★★★★★ | ★★★★★ | ★ |
| Model multiple domains/contexts and their boundaries | ★★ | - | ★ | ★★★★★ | - |
| Surface and record architecture decisions that need durable rationale | ★★★ | - | ★★★ | ★★★★ | ★★★★ |

## 3. Define the intended change

| Action / purpose | OpenSpec | Superpowers | Spec Loop | grill-with-docs | agent-skills |
|:---|:---:|:---:|:---:|:---:|:---:|
| Create durable spec/change artifacts that remain the source of truth | ★★★★★ | ★★★★ | ★★★★ | ★★ | ★★★★ |
| Write detailed technical design before implementation | ★★★★★ | ★★★★★ | ★★★★★ | ★★ | ★★★ |
| Make current and target structure/behavior explicit with reviewable diagrams | ★ | ★ | ★★★★★ | ★ | ★ |
| Maintain brownfield deltas between current and proposed behavior | ★★★★★ | - | ★ | - | - |

## 4. Make the next increment implementation-ready

| Action / purpose | OpenSpec | Superpowers | Spec Loop | grill-with-docs | agent-skills |
|:---|:---:|:---:|:---:|:---:|:---:|
| Make one implementation increment ready by explicitly capturing research, constraints, design, and test expectations | ★★★ | ★★★★ | ★★★★★ | ★★★ | ★★★ |
| Break approved work into actionable implementation tasks/checklists | ★★★★★ | ★★★★★ | ★★★★ | ★ | ★★★★ |
| Support lightweight planning for one simple increment without opening a full formal artifact workflow | ★★ | ★ | ★★★★★ | ★★ | ★ |

## 5. Govern implementation while coding

| Action / purpose | OpenSpec | Superpowers | Spec Loop | grill-with-docs | agent-skills |
|:---|:---:|:---:|:---:|:---:|:---:|
| Keep implementation constrained to the approved increment/task/change during coding | ★★★★ | ★★★★★ | ★★★★★ | - | ★★★ |
| Use explicit guardrails against rationalization and unjustified confidence during execution | ★ | ★★★★★ | ★★★ | ★ | ★★★★★ |
| Treat test-first development as a required implementation method | ★ | ★★★★★ | ★★ | - | ★ |
| Require root-cause analysis before fixes when debugging | - | ★★★★★ | - | - | - |
| Use subagents plus review loops as a primary implementation strategy | - | ★★★★★ | - | - | ★ |
| Use isolated development workspaces/branches as part of the normal implementation flow | ★ | ★★★★★ | - | - | - |
| Coordinate implementation across multiple repos or linked workspaces | ★★★★★ | - | - | - | - |

## 6. Verify and close out

| Action / purpose | OpenSpec | Superpowers | Spec Loop | grill-with-docs | agent-skills |
|:---|:---:|:---:|:---:|:---:|:---:|
| Check implemented work against the agreed artifacts before calling it done | ★★★★ | ★★★★★ | ★★★★★ | - | ★★★★ |
| Assess pull requests, merge requests, or diffs and prepare review artifacts | ★ | ★★★★ | ★★★★★ | - | - |
| Drive merge or branch-closeout as an operational workflow step | - | ★★★★★ | - | - | - |
| Preserve completed change context in an archive or other durable historical record | ★★★★★ | ★ | ★★ | - | - |

## 7. Workflow costs

This is a different kind of comparison. Lower is not automatically
better: a framework can be cheaper here because it covers less of the
job, or because it keeps less written state.

These cost labels are comparative judgments based on the reviewed
materials, not measurements or benchmark results.

### Activities

- **Before coding** = the cost of clarification, specification,
  design, planning artifacts, and approvals before implementation
  starts.
- **Coding and testing** = the cost once the increment is already
  chosen: coding mechanics, testing method, review loops, and
  implementation-time clarification.
- **Maintaining authoritative written artifacts as the system grows**
  = the cost of keeping specs, glossary files, ADRs, or similar
  written artifacts believable as the codebase and behavior evolve.
- **Repeated research and re-alignment per increment** = the cost of
  re-checking current truth and rebuilding enough local context for
  each new increment.

### Analysis

| Activity | OpenSpec | Superpowers | Spec Loop | grill-with-docs | agent-skills |
|:---|:---:|:---:|:---:|:---:|:---:|
| Before coding | High | Very high | Medium | Low | High |
| Coding and testing | High | Very high | Medium | High | High |
| Maintaining authoritative written artifacts as the system grows | High | Medium | Low | Medium | Medium |
| Repeated research and re-alignment per increment | Medium | Medium | Medium | High | Medium |

- **Spec Loop:** low artifact-maintenance cost because it keeps the
  authoritative written state relatively narrow, but medium repeated
  re-alignment cost because it re-checks current system truth through
  codebase research for each increment.
- **OpenSpec:** higher artifact-maintenance cost because it asks a
  larger enduring spec set to stay believable as the system grows.
- **Superpowers:** very high upfront and execution-phase cost because
  it wants design, planning, TDD, and strong execution controls before
  and during coding.
- **grill-with-docs:** low upfront cost mainly because it covers the
  clarification/shared-language slice, not the whole end-to-end
  workflow, but repeated re-alignment cost is higher because it does
  not carry the later implementation workflow itself.
- **agent-skills:** scored here as a representative spec -> plan ->
  implement -> verify path, not as the whole catalog abstractly.


## 8. References used

This comparison is based on the following materials.

- **Spec Loop:** current repository skills and docs, especially
  [skills/spec-loop-plan-task/](../skills/spec-loop-plan-task/),
  [planning-form-selection-guidance.md](../skills/spec-loop-plan-task/planning-form-selection-guidance.md),
  [skills/spec-loop-plan-work-breakdown/](../skills/spec-loop-plan-work-breakdown/),
  [skills/spec-loop-clarify-task/](../skills/spec-loop-clarify-task/),
  [skills/spec-loop-implementation-flow/](../skills/spec-loop-implementation-flow/),
  [docs/review-responsibility-and-traceability.md](review-responsibility-and-traceability.md),
  and [README.md](../README.md).
- **grill-with-docs:** `SKILL.md`, `CONTEXT-FORMAT.md`, and
  `ADR-FORMAT.md`.
- **OpenSpec:** `README.md`, `docs/concepts.md`, and
  `docs/workflows.md`.
- **agent-skills:** `README.md`, `AGENTS.md`,
  `docs/getting-started.md`, `docs/skill-anatomy.md`, and selected
  skills including `interview-me`, `idea-refine`,
  `spec-driven-development`, `planning-and-task-breakdown`,
  `documentation-and-adrs`, and `doubt-driven-development`.
- **Superpowers:** `README.md`, `AGENTS.md`, and selected workflow
  skills including `brainstorming`, `writing-plans`,
  `executing-plans`, `subagent-driven-development`,
  `test-driven-development`, `systematic-debugging`,
  `verification-before-completion`, `using-git-worktrees`,
  `requesting-code-review`, `receiving-code-review`,
  `finishing-a-development-branch`, `using-superpowers`, and
  `writing-skills`.

This is a comparison of representative core materials and selected
skills, not a full-repository audit of every compared project.
