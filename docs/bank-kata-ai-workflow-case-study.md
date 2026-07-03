# Bank Kata AI Workflow Case Study

We compared 12 solutions from 4 skill frameworks on the same browser bank kata, based on Sandro Mancuso's [Bank Kata](https://github.com/sandromancuso/Bank-kata). This is an observational comparison: the solutions shared the same core exercise, but prompt details, adapters, and interaction patterns differed, so those differences are treated as context when interpreting results.

The study does not choose one framework for all goals. It records which framework or workflow matched each goal: compact specification review, design control before implementation, detailed implementation steps, or few user interruptions.

Terminology used below: **required-behavior checks** means a category-by-category finding for 15 bank-kata behaviors. A category is marked full, partial, or missing based on committed tests and explicitly recorded checks. This is not line, branch, or function coverage.

## Main result by goal

| Goal | Framework or workflow | Evidence from the bank-kata solutions | Caveat |
|---|---|---|---|
| Compact specification review | [OpenSpec](https://github.com/Fission-AI/OpenSpec) | The OpenSpec solutions, `open-spec` and `open-spec-calisthenics`, had the shortest generated specification files: 5 files / 214 lines and 6 files / 310 lines. The proposal/design/spec split was easy to scan. | OpenSpec documented fewer detailed data, UI, and failure-behavior decisions before implementation. |
| Design-level control before code | [Spec Loop](https://github.com/dpolivaev/spec-loop) | Spec Loop writes task files with scope, scenarios, analysis, design, and test specifications before implementation. `spec-loop-base-backlog-steered`, `spec-loop-base-backlog-prompted`, and `spec-loop-incremental` each had 13 of 15 behavior categories fully checked. | It asks for more specification and design review before implementation. |
| Detailed implementation steps | [Superpowers](https://github.com/obra/superpowers) | Superpowers generated design documents and implementation plans. The implementation plans contained 7 to 9 tasks, depending on the solution, and the sessions showed many product choices. | The generated Superpowers documents were 1988 to 2282 lines. The Superpowers solutions had fewer fully checked behavior categories than the two Spec Loop backlog solutions. |
| Few user interruptions | [OpenSpec](https://github.com/Fission-AI/OpenSpec); GSD Small Feature | The OpenSpec solutions, `open-spec` and `open-spec-calisthenics`, had the fewest visible clarification gates and shortest generated specification files. `gsd-small-feature` also used few clarification gates and committed scope, plan, state, and summary files. | The OpenSpec solutions made more unreviewed choices. `gsd-small-feature` had fewer committed automated tests for required behavior and less committed decision analysis than the Spec Loop backlog solutions. |
| What future reviewers can reconstruct from Git | [Spec Loop](https://github.com/dpolivaev/spec-loop), with [OpenSpec](https://github.com/Fission-AI/OpenSpec) as the closest compact alternative | Spec Loop task files preserved research, analysis, design, glossary/terms, constraints, and test specifications. OpenSpec preserved proposal/design/spec rationale, including decisions and alternatives. | This measures what a future reviewer can reconstruct from Git, not whether the app works today. Files only count when committed. |

In this case study, Spec Loop gave the clearest user-reviewed design before code and the most complete Git record of implementation reasoning. OpenSpec produced the shortest specification files and needed few visible clarification gates. Superpowers produced the longest implementation plans and asked many product questions. GSD Small Feature in GSD Pi produced a shorter scope/plan/state/summary file set and a working app. Object-calisthenics/domain-language constraints are discussed separately because their effect on final source structure is a different question from workflow choice.

See [Ranking by study criteria and limits](#ranking-by-study-criteria-and-limits) for the full ordering and rank-specific evidence.

## Kata attribution and scope

The kata requirements are not original to this study. They come from the Bank Account / Bank Kata used in software-craftsmanship practice, commonly attributed to Sandro Mancuso / Codurance. The user prompts reused that kata wording, especially:

> Think of your personal bank account experience. Requirements: Deposit and Withdrawal; Transfer; Account statement (date, amount, balance); Statement printing; Statement filters (just deposits, withdrawal, date).

The object-calisthenics constraints used in the calisthenics solutions are also not original to this study. They come from Jeff Bay's Object Calisthenics exercise in *The ThoughtWorks Anthology*. The domain-language boundary rule was added alongside those rules for this bank-kata comparison.

This study compares AI workflow outputs on those existing exercises. It does not claim authorship of the kata requirements or the object-calisthenics rule set.

Common functional requirements across the bank-kata prompts:

- deposit and withdrawal;
- transfer / rollback behavior when the prompt included it explicitly;
- account statement with date, amount, and balance;
- statement printing;
- statement filters for deposits, withdrawals, and date.

Additional requirements in expanded prompts:

- TypeScript and Vite;
- browser local storage;
- fixed Daily and Savings accounts;
- rollback on failed Daily/Savings transfer;
- object-calisthenics and domain-language constraints.

Object-calisthenics/domain-language constraints used in all calisthenics prompts:

1. Domain code must use only bank-domain concepts and names. UI, browser, storage, framework, rendering, and technical orchestration concepts must stay out of the domain model.
2. One level of indentation per method.
3. Do not use the `else` keyword.
4. Wrap all primitives and strings.
5. Use first-class collections.
6. Use one dot per line.
7. Do not abbreviate.
8. Keep all entities small: 50 lines.
9. No classes with more than two instance variables.
10. No getters, setters, or properties.

## Workflows and compared solutions

### Workflow mechanics

- [OpenSpec](https://github.com/Fission-AI/OpenSpec) structures work as a change proposal: proposal, design, tasks, and capability specs.
- [Spec Loop](https://github.com/dpolivaev/spec-loop) writes task files, subtasks, backlog items, approval gates, design sections, and test specifications.
- [Superpowers](https://github.com/obra/superpowers) uses brainstorming, clarifying questions, design approval, detailed implementation plans, TDD-style execution, and verification.
- [GSD](https://opengsd.net) has multiple runtime paths. The completed GSD solution included here used the GSD Small Feature workflow through GSD Pi.

The frameworks differ in how they direct the model. OpenSpec and GSD use fixed workflow prompts or runtimes. Superpowers uses skills plus procedural instructions. Spec Loop is distributed as cross-referencing skills, without a separate fixed workflow prompt. Its skills reference related skills so planning, clarification, work breakdown, approval, implementation, ADR, and glossary guidance can be loaded when the corresponding trigger is reached.

This gives Spec Loop more flexibility in ordinary communication and makes installation simpler in agents that already support skills. Spec Loop planning guidance gives the model criteria for choosing among taskless work, chat-only tasks, task files, task files with subtasks, and multiple task files / backlog items. The user can state a preferred form, but the workflow does not require the user to choose the form up front. For case-study purposes, a specific form can still be specified in the prompt or selected by later user input. In ordinary use, the model can choose from the criteria.

### Solution conditions and in-session choices

Solution repositories are public in the GitLab subgroup [skill-assessment/bank-kata](https://gitlab.com/skill-assessment/bank-kata). Each repository contains `main` and tag `analysis-2026-06-30` at the listed commit. The `Solution` column links to the repository and uses the repository path name.

Initial prompt and in-session choices are grouped together because both affect how the row should be read. The table records when a planning form or product decision came from the prompt, the workflow, or later user input.

The standard GSD attempt is not included because it was aborted before completing the kata. The completed `gsdpi-quick` solution is not included because it produced only the result, with no generated documentation, design file, discussion, or steering checkpoint.

| Solution | Workflow / app / model | Commit | Initial prompt condition | Relevant in-session choices | What this row shows |
|---|---|---|---|---|---|
| [open-spec](https://gitlab.com/skill-assessment/bank-kata/open-spec) | OpenSpec / pi / GPT-5.5 xhigh | `81ce8ab5a1b9` | Base bank-kata prompt via OpenSpec proposal flow. This prompt variant included the transfer rollback wording: “Transfer (transactional, rollback on failures).” | Only proceed/apply approvals were found. | Evidence for compact OpenSpec proposal/spec review on the base kata. |
| [open-spec-calisthenics](https://gitlab.com/skill-assessment/bank-kata/open-spec-calisthenics) | OpenSpec / pi / GPT-5.5 xhigh | `1d71c713d61c` | Expanded prompt: TypeScript/Vite, Daily/Savings, transfer rollback, browser local storage, and all object-calisthenics/domain-language constraints listed above. | Only an apply approval was found. | Evidence for OpenSpec with a constraint-heavy prompt; few design decisions were discussed with the user. |
| [spec-loop-base-backlog-steered](https://gitlab.com/skill-assessment/bank-kata/spec-loop-base-backlog-steered) | Spec Loop / pi / GPT-5.5 xhigh | `ae1eb4bb896d` | Base bank-kata prompt. It did not initially specify separate backlog tasks, localStorage, or Daily/Savings. | User asked for a “proper multitask backlog,” opted into `glossary.adoc`, and later asked whether transactions and rollback had been considered. | Highest final design score; observed path includes user-selected backlog and later rollback/storage-write-failure review. |
| [spec-loop-base-backlog-prompted](https://gitlab.com/skill-assessment/bank-kata/spec-loop-base-backlog-prompted) | Spec Loop / pi / GPT-5.5 xhigh | `4b9f8aa9776a` | Expanded prompt: TypeScript/Vite, Daily/Savings, transfer rollback, browser local storage, separate backlog tasks, and design of each following task only after the previous task was implemented and committed. | During the final solution session, user input was mostly approvals and moving completed tasks to `done`. | Evidence for the backlog form specified in the initial prompt. |
| [spec-loop-incremental](https://gitlab.com/skill-assessment/bank-kata/spec-loop-incremental) | Spec Loop / pi / GPT-5.5 xhigh | `4cd947ec1e42` | Expanded prompt: TypeScript/Vite, Daily/Savings, transfer rollback, browser local storage, and subtask design-after-commit sequencing. | No later planning-route change found. | Evidence for Spec Loop sequential-subtask work. |
| [spec-loop-calisthenics](https://gitlab.com/skill-assessment/bank-kata/spec-loop-calisthenics) | Spec Loop / pi / GPT-5.5 xhigh | `319a8c9d4c24` | Expanded calisthenics prompt with TypeScript/Vite, Daily/Savings, transfer rollback, browser local storage, and all constraints listed above. | Subtask path selected during the session. | Evidence for Spec Loop with the expanded calisthenics prompt and subtask path. |
| [spec-loop-calisthenics-incremental](https://gitlab.com/skill-assessment/bank-kata/spec-loop-calisthenics-incremental) | Spec Loop / pi / GPT-5.5 xhigh | `d8948538ead4` | Expanded calisthenics prompt plus subtask design-after-commit sequencing. | Only approval responses were found. | Evidence for Spec Loop calisthenics sequential-subtask work. |
| [spec-loop-calisthenics-single-task](https://gitlab.com/skill-assessment/bank-kata/spec-loop-calisthenics-single-task) | Spec Loop / pi / GPT-5.5 xhigh | `b708bd2c7d97` | Expanded calisthenics prompt without explicit backlog/subtask sequencing. | No later planning-route change appears in the retained session evidence. | Evidence for the broad single-task calisthenics condition. |
| [superpowers](https://gitlab.com/skill-assessment/bank-kata/superpowers) | Superpowers / Codex / GPT-5.5 xhigh | `58bcb54d6478` | Base bank-kata prompt with transactional rollback wording. | User supplied important clarifications: `localStorage`, two internal accounts like Daily/Savings, and plain TypeScript + Vite. | User answers set product scope and stack choices. |
| [superpowers-5.4](https://gitlab.com/skill-assessment/bank-kata/superpowers-5.4) | Superpowers / Codex / GPT-5.4 high | `5db5d24f5f27` | Base bank-kata prompt with transactional rollback wording. | User supplied browser local storage, two fixed accounts, browser-only display, and later changed direction to include printing. User also answered “ts, vite, browser only,” which the assistant did not clarify before choosing React. | Evidence includes user-provided product choices and an ambiguous stack choice before the assistant selected React. |
| [superpowers-calisthenics](https://gitlab.com/skill-assessment/bank-kata/superpowers-calisthenics) | Superpowers / Codex / GPT-5.5 xhigh | `65e2dbb20a9d` | Expanded calisthenics prompt with TypeScript/Vite, Daily/Savings, transfer rollback, browser local storage, and all constraints listed above. | User said domain tests were enough, later asked not to be bothered with trivial questions, and later challenged date-control design and statement sorting. | User answers shaped test scope; date-control design changed after user challenge. Final constraint preservation is judged from code/tests. |
| [gsd-small-feature](https://gitlab.com/skill-assessment/bank-kata/gsd-small-feature) | GSD Small Feature / GSD Pi / GPT-5.5 xhigh | `aef38ffcae7b` | GSD Small Feature workflow started without a description; the assistant asked for one, then the user supplied the expanded TypeScript/Vite, Daily/Savings, rollback, statement, print, filter, and localStorage prompt. | Scope and plan approval gates selected the recommended options. The assistant did not ask separate gray-area questions; it packaged defaults into the scope approval. `.gsd/workflows` files were committed after completion. | Evidence for the GSD Small Feature workflow through GSD Pi, not for the one-shot GSD Pi quick path or standard GSD. The app passed its tests, but it had fewer committed tests for required behavior and less committed decision analysis than the Spec Loop backlog solutions. |

### Communication patterns

Question count did not determine whether decisions were checked. The relevant fact was whether important decisions were shown early enough to change the design.

- OpenSpec and GSD Small Feature used fewer clarification points. OpenSpec left fewer recorded reasons for data, UI, and failure-behavior decisions. GSD Small Feature showed decisions for approval, but did not ask gray-area questions separately.
- Spec Loop recorded decisions at task or subtask boundaries. In `spec-loop-base-backlog-steered`, user review changed rollback and storage-write-failure handling.
- Superpowers asked many product/design questions. Source and tests were still needed to check whether those answers reached the final code. In `superpowers-5.4`, the ambiguous `ts, vite, browser only` answer was not resolved before React was selected.

## How conclusions were derived

The study used this chain of evidence:

1. **Prompt condition** — what the solution was asked to do.
2. **Generated specification files** — what the workflow wrote before or during implementation.
3. **Committed files** — what a later reviewer can reconstruct from Git without the chat log.
4. **Session communication** — which important choices were shown to the user and accepted before coding.
5. **Source code structure** — whether the final implementation preserved clean boundaries and localized change.
6. **Tests** — which required behaviors and failure modes were actually asserted.
7. **Static metrics** — supporting diagnostics for file size, complexity, duplication, dependency cycles, and risky patterns.

Conclusions require agreement between several evidence types. Example: a plan without tests is not enough; many tests without written design are also not enough.

UI references in this study are limited to generated UI/display sections, browser-flow checks, print/filter/storage behavior, and separation of UI code from domain/application code. The study does not compare visual design, usability, or accessibility.

## Generated specification files and Git reconstruction

Here, “specification” means the generated written structure that described, constrained, or guided implementation, including task files, plans, and workflow state files.

The table also records fenced code blocks embedded in generated planning files, because those blocks mix specification with implementation detail. In this study, the OpenSpec and Spec Loop generated files listed below had no fenced source/test code blocks. The Superpowers implementation plans embedded source, test, configuration, UI, CSS, and shell-command fragments. The GSD Small Feature summary embedded shell commands only.

| Solution | Generated specification structure |
|---|---|
| [open-spec](https://gitlab.com/skill-assessment/bank-kata/open-spec) | - Proposal (28 lines): why, changes, capabilities, and impact.<br>- Design (55 lines): context, goals/non-goals, decisions, and risks/trade-offs.<br>- Specs (2 files / 91 lines): bank-account transaction and account-statement requirements with scenarios.<br>- Tasks (40 lines): 6-step implementation checklist. |
| [open-spec-calisthenics](https://gitlab.com/skill-assessment/bank-kata/open-spec-calisthenics) | - Proposal (31 lines): why, changes, capabilities, and impact.<br>- Design (74 lines): context, goals/non-goals, decisions, and risks/trade-offs for TypeScript, boundaries, rollback, persistence, and printing.<br>- Specs (3 files / 152 lines): account operations, account statements, and browser persistence requirements with scenarios.<br>- Tasks (53 lines): 7-step implementation checklist. |
| [spec-loop-base-backlog-steered](https://gitlab.com/skill-assessment/bank-kata/spec-loop-base-backlog-steered) | - Task files (4 files / 1491 lines): deposit/withdrawal/statement, account transfers, statement filters, and statement printing.<br>- Each task file uses task-style sections: scope, motivation, scenario, glossary, constraints, briefing, research, analysis, design, and test specification. |
| [spec-loop-base-backlog-prompted](https://gitlab.com/skill-assessment/bank-kata/spec-loop-base-backlog-prompted) | - Task files (5 files / 1598 lines): app shell, deposits/withdrawals/statement ledger, transfers with rollback, statement filters, and statement printing.<br>- Each task file uses task-style sections: scope, motivation, scenario, glossary, constraints, briefing, research, analysis, design, and test specification. |
| [spec-loop-incremental](https://gitlab.com/skill-assessment/bank-kata/spec-loop-incremental) | - Task file (929 lines): top-level scope, motivation, scenario, glossary, constraints, briefing, and analysis.<br>- Nested subtasks (4, inside the same task file): deposits/withdrawals/statements/persistence, transfers with rollback, statement filters, and statement printing.<br>- Each subtask uses task-style sections: scope, motivation, scenario, briefing, research, analysis, design, and test specification. |
| [spec-loop-calisthenics](https://gitlab.com/skill-assessment/bank-kata/spec-loop-calisthenics) | - Task file (625 lines): top-level scope, motivation, scenario, glossary, constraints, briefing, research, analysis, and design.<br>- Nested subtasks (3, inside the same task file): persisted deposits/withdrawals/statements, transfers with rollback, and filtered/printable statements.<br>- Each subtask uses task-style sections: scope, motivation, scenario, briefing, research, design, and test specification. |
| [spec-loop-calisthenics-incremental](https://gitlab.com/skill-assessment/bank-kata/spec-loop-calisthenics-incremental) | - Task file (1067 lines): top-level scope, motivation, scenario, glossary, constraints, and briefing.<br>- Nested subtasks (3, inside the same task file): deposits/withdrawals with persisted statements, transfers with rollback, and statement filters/printing.<br>- Subtask sections include scope, motivation, scenario, constraints, briefing, research, design, and test specification; the two later subtasks also include analysis. |
| [spec-loop-calisthenics-single-task](https://gitlab.com/skill-assessment/bank-kata/spec-loop-calisthenics-single-task) | - Task file (466 lines): one broad task with no nested subtask sections.<br>- The file uses task-style sections: scope, motivation, scenario, glossary, constraints, briefing, research, analysis, design, and test specification. |
| [superpowers](https://gitlab.com/skill-assessment/bank-kata/superpowers) | - Design document (148 lines): overview, goals/non-goals, architecture, domain model, banking operations, statement filtering, persistence, UI, validation/errors, testing, and boundaries.<br>- Implementation plan (1840 lines): file structure and 8 tasks from scaffold through browser verification.<br>- Embedded fenced code blocks (32): `.gitignore`, JSON config/package files, HTML, CSS, TypeScript source, Vitest tests, and shell/git commands. |
| [superpowers-5.4](https://gitlab.com/skill-assessment/bank-kata/superpowers-5.4) | - Design document (320 lines): overview, goals/non-goals, architecture, data model, transaction behavior, UI, filtering, validation, failure handling, testing, module boundaries, and acceptance criteria.<br>- Implementation plan (1741 lines): file structure and 9 tasks from Vite/React/Vitest bootstrap through verification and cleanup.<br>- Embedded fenced code blocks (48): JSON config/package files, Vite/TypeScript config, HTML, CSS, TypeScript/TSX source, React Testing Library/Vitest tests, and shell/git commands. |
| [superpowers-calisthenics](https://gitlab.com/skill-assessment/bank-kata/superpowers-calisthenics) | - Design document (120 lines): goal, scope, constraints, architecture, domain model, application flow, statements, persistence, UI, error handling, testing, and settled decisions.<br>- Implementation plan (2162 lines): file structure, domain rules, and 7 tasks from scaffold through final verification.<br>- Embedded fenced code blocks (90): package/config files, HTML, CSS, TypeScript source, Vitest tests, object-calisthenics validation commands, and shell/git commands. |
| [gsd-small-feature](https://gitlab.com/skill-assessment/bank-kata/gsd-small-feature) | - Context (52 lines): feature description, decisions made or proposed, scope boundaries, and gray areas.<br>- Plan (120 lines): 4 implementation tasks and final workflow verification.<br>- State (33 lines): workflow template, branch, phases, timestamps, and output directory.<br>- Summary (73 lines): built behavior, changed files, commits, usage instructions, and test/use notes.<br>- Embedded fenced code blocks (2): shell commands for install, dev server, tests, build, and lint; no source/test code blocks. |

### What Git preserves

A separate question is what future reviewers can reconstruct from Git after the chat log is gone. This is not the same as checking whether the app works.

- Spec Loop task files preserved research, analysis, design, glossary/terms, constraints, and test specifications. Across the completed Spec Loop solutions in this study, `Analysis` sections contained between 6 and 28 bullet items per solution with explicit `because` or `so that` reasoning.
- OpenSpec proposal/design/spec files preserved goals, decisions, rationale, alternatives considered, and risks/trade-offs in a compact form.
- Superpowers specs/plans preserved implementation order and expected tests, including many expected-failure notes. Decision analysis was less consistently separated from the long plan.
- GSD Small Feature in GSD Pi preserved scope, key decisions made or proposed, plan, state, and summary in committed `.gsd/workflows` files. Gray areas remained unresolved in the committed files, and some verification details rely on the session log.

Interpretation: Spec Loop was not unique in documenting rationale, but it preserved implementation reasoning inside task files alongside research, glossary/terms, constraints, design, and test specifications. OpenSpec was the closest compact alternative for decision rationale.

## Code, tests, and static metrics

All completed comparison projects passed their own tests and build at tag `analysis-2026-06-30`. Required-behavior checks use the 15 behavior categories listed below and are shown as full / partial / missing. Categories that did not apply to the prompt and implemented scope are not counted in those three numbers; this is why `open-spec` sums to 12 instead of 15. The `gsd-small-feature` row was added after the first metric pass over the original 11 solutions and is based on the later manual audit plus fresh `npm test -- --run`, `npm run build`, and `npm run lint` checks.

Design score is a source/test reviewer score assigned by GPT-5.5 xhigh acting as an expert code reviewer. It is not a static metric and not a UI score. It sums six criteria scored from 0 to 3: naming/domain language, simplicity (KISS), single responsibility (SRP), dependency direction, change locality, and testability. Maximum score is 18. The component scores are shown below because the total alone is not enough evidence.

The score scale was applied independently per criterion:

- Naming/domain language:
  - 0: public names obscure or misrepresent bank concepts.
  - 1: many generic names, abbreviations, or inconsistent account/transaction terms make the domain hard to infer.
  - 2: banking terms are mostly consistent, with some generic records/functions or primitive values leaking into public APIs.
  - 3: banking concepts are named consistently; public APIs read as deposits, withdrawals, transfers, statements, and filters.
- KISS:
  - 0: control flow or abstraction is hard to follow for the kata size.
  - 1: avoidable indirection, many tiny objects for simple rules, or giant mixed files make simple changes tedious.
  - 2: structure is mostly direct, with one or a few large files, templates, state handlers, or abstractions heavier than needed.
  - 3: implementation is direct for the kata size, with no avoidable abstraction, giant mixed flow, or needless object split.
- SRP:
  - 0: domain rules, UI, storage, formatting, or printing are mixed so broadly that unrelated changes cross the same files.
  - 1: several responsibilities share central files or classes.
  - 2: main responsibilities are separated, but one orchestration, UI, or domain file still combines several tasks.
  - 3: domain rules, application orchestration, storage, UI/rendering, formatting, and printing are separated.
- Dependency direction:
  - 0: domain code depends on browser, storage, UI, or framework APIs.
  - 1: side effects and domain rules are coupled, or browser/storage details leak into core behavior.
  - 2: dependency direction mostly holds, with some domain/application boundary leakage or hard-coded runtime assumptions.
  - 3: domain code is independent of browser APIs, and side effects are isolated behind adapters or injected functions.
- Change locality:
  - 0: likely changes require scattered edits because of cycles, duplicated rules, or hard-coded concepts in many files.
  - 1: ordinary changes touch unrelated layers/files, repeated account/type assumptions, cycles, or large aggregate files.
  - 2: most changes are localized, but common changes still touch a central file or several fixed-account mappings.
  - 3: likely changes touch few expected files; boundaries make the change location clear.
- Testability:
  - 0: essential behavior can only be checked through manual/browser setup or brittle DOM paths; side effects are not controllable.
  - 1: some core behavior is tested, but many rules require UI/integration setup; time, storage, or printing is hard to substitute.
  - 2: core behavior is directly tested; boundary/side-effect tests are thinner or some runtime details are hard to substitute.
  - 3: core and boundary behavior can be tested directly; side effects such as time, storage, and printing are injectable or mockable.

No component in the final table scored 0.

The four code/test metric columns split TypeScript/TSX files into domain and application+UI buckets. These metrics show where code and tests are concentrated. They do not prove correctness or design quality by themselves; use them with the required-behavior checks, design score, and conclusion table below.

Result metrics:

| Solution | Tests | Required checks | Design score |
|---|---:|---:|---:|
| `spec-loop-base-backlog-steered` | 60/60 | 13 / 1 / 1 | 17/18 |
| `spec-loop-base-backlog-prompted` | 58/58 | 13 / 1 / 1 | 14/18 |
| `spec-loop-incremental` | 30/30 | 13 / 1 / 1 | 13/18 |
| `open-spec-calisthenics` | 12/12 | 13 / 1 / 1 | 15/18 |
| `open-spec` | 19/19 | 11 / 0 / 1 | 12/18 |
| `superpowers` | 17/17 | 11 / 1 / 3 | 12/18 |
| `spec-loop-calisthenics` | 7/7 | 11 / 1 / 3 | 10/18 |
| `spec-loop-calisthenics-single-task` | 17/17 | 11 / 1 / 3 | 10/18 |
| `spec-loop-calisthenics-incremental` | 16/16 | 10 / 3 / 2 | 11/18 |
| `superpowers-calisthenics` | 13/13 | 8 / 3 / 4 | 11/18 |
| `superpowers-5.4` | 16/16 | 7 / 5 / 3 | 11/18 |
| `gsd-small-feature` | 5/5 | 7 / 2 / 6 | 11/18 |

Design-score breakdown:

| Solution | Naming | KISS | SRP | Dependency direction | Change locality | Testability | Total |
|---|---:|---:|---:|---:|---:|---:|---:|
| `spec-loop-base-backlog-steered` | 3 | 2 | 3 | 3 | 3 | 3 | 17/18 |
| `spec-loop-base-backlog-prompted` | 2 | 2 | 2 | 3 | 2 | 3 | 14/18 |
| `spec-loop-incremental` | 2 | 2 | 2 | 2 | 2 | 3 | 13/18 |
| `open-spec-calisthenics` | 3 | 2 | 3 | 3 | 2 | 2 | 15/18 |
| `open-spec` | 2 | 2 | 2 | 2 | 2 | 2 | 12/18 |
| `superpowers` | 2 | 2 | 2 | 2 | 2 | 2 | 12/18 |
| `spec-loop-calisthenics` | 3 | 1 | 1 | 2 | 1 | 2 | 10/18 |
| `spec-loop-calisthenics-single-task` | 3 | 1 | 2 | 1 | 1 | 2 | 10/18 |
| `spec-loop-calisthenics-incremental` | 3 | 1 | 2 | 2 | 1 | 2 | 11/18 |
| `superpowers-calisthenics` | 2 | 2 | 2 | 2 | 1 | 2 | 11/18 |
| `superpowers-5.4` | 2 | 2 | 2 | 2 | 1 | 2 | 11/18 |
| `gsd-small-feature` | 2 | 2 | 2 | 2 | 1 | 2 | 11/18 |

Code-size and complexity metrics:

| Solution | Prod domain files/LOC/CC | Prod app+UI files/LOC/CC | Test domain files/LOC | Test app+UI files/LOC |
|---|---:|---:|---:|---:|
| `spec-loop-base-backlog-steered` | 4 / 295 / 1–7 | 9 / 598 / 1–10 | 4 / 271 | 5 / 645 |
| `spec-loop-base-backlog-prompted` | 6 / 537 / 1–6 | 3 / 529 / 1–5 | 0 / 0 | 8 / 1472 |
| `spec-loop-incremental` | 1 / 253 / 1–4 | 3 / 431 / 1–10 | 0 / 0 | 1 / 623 |
| `open-spec-calisthenics` | 16 / 821 / 1–3 | 10 / 602 / 1–6 | 2 / 90 | 2 / 173 |
| `open-spec` | 6 / 322 / 1–6 | 4 / 454 / 1–5 | 3 / 208 | 2 / 131 |
| `superpowers` | 5 / 243 / 1–8 | 4 / 453 / 1–9 | 3 / 203 | 2 / 96 |
| `spec-loop-calisthenics` | 1 / 728 / 1–5 | 2 / 724 / 1–4 | 0 / 0 | 2 / 380 |
| `spec-loop-calisthenics-single-task` | 36 / 1099 / 1–3 | 6 / 612 / 1–5 | 1 / 161 | 2 / 263 |
| `spec-loop-calisthenics-incremental` | 27 / 631 / 1–2 | 24 / 934 / 1–4 | 0 / 0 | 1 / 276 |
| `superpowers-calisthenics` | 17 / 379 / 1–2 | 6 / 387 / 1–5 | 3 / 141 | 2 / 79 |
| `superpowers-5.4` | 4 / 236 / 1–7 | 11 / 387 / 1–5 | 1 / 107 | 5 / 124 |
| `gsd-small-feature` | 2 / 327 / 1–10 | 2 / 426 / 1–7 | 1 / 136 | 0 / 0 |

Abbreviations: LOC means nonblank physical lines. CC means approximate cyclomatic complexity range across production functions in that bucket. Test buckets omit CC. Production TypeScript excludes tests, config files, and `vite-env.d.ts`. Test TypeScript includes `.test.`/`.spec.` files and files under `test` or `tests` directories. Domain means explicit `src/domain`, `src/bank`, `tests/domain`, `tests/bank`, or root-level banking-domain files such as `money`, `transfer`, `statementFilters`, or `bankState`. Storage and repository files are counted with application+UI because they connect domain behavior to the browser/runtime.

Duplication note: all 12 solutions were measured with jscpd using production files under `src/`, excluding tests and `vite-env.d.ts`. The highest duplicated-line percentage was about 2.84% in `superpowers-calisthenics`.

Code/test conclusions:

| Solution | Conclusion |
|---|---|
| `spec-loop-base-backlog-steered` | Highest design score in this comparison. Tests covered broad behavior, localStorage validation, and rollback depth. User review contributed to rollback/storage-write-failure coverage. |
| `spec-loop-base-backlog-prompted` | 58 passing tests and 13 fully checked behavior categories. Restored localStorage validation and storage-write-failure handling during deposit/withdrawal were less complete than in `spec-loop-base-backlog-steered`. |
| `spec-loop-incremental` | 30 passing tests and 13 fully checked behavior categories. Planning was kept in one task file rather than separate backlog files. |
| `open-spec-calisthenics` | Second-highest design score. No automated test simulated localStorage failing while saving state. |
| `open-spec` | Base-scope implementation with 19 passing tests and 11 fully checked behavior categories. Few design decisions were visible in session. |
| `superpowers` | Accepted product decisions were visible in session. Tests did not cover UI flow, localStorage validation, print assertions, or storage-write failure as fully as the solutions with the most fully checked behavior categories. |
| `spec-loop-calisthenics` | Domain vocabulary was clear. Very large files reduced the simplicity and change-locality scores. |
| `spec-loop-calisthenics-single-task` | Automated tests checked source-code constraints; broad single-task design and dependency cycles remained problems. |
| `spec-loop-calisthenics-incremental` | 16 passing tests. Storage and print evidence was partial or missing in some behavior categories. |
| `superpowers-calisthenics` | Session included a date-control design change after user challenge. Final tests/code preserved fewer requested constraints and required behaviors than the solutions with the most fully checked behavior categories. |
| `superpowers-5.4` | 16 passing tests, but only 7 fully checked behavior categories. |
| `gsd-small-feature` | 5 passing tests covering deposits, withdrawal rejection, successful transfer, simulated rollback, and statement filters. Browser smoke checks in the session covered UI flow, print invocation, localStorage reload, and clean diagnostics, but those checks were not committed as executable tests. Committed tests did not cover print, storage fallback, storage-write failure, or UI regression. |

Required-behavior checks counted whether tests or explicitly recorded checks covered these behavior categories: money validation, deposit, withdrawal, insufficient-funds safety, transfer success, rejected-transfer no-change behavior, statement date/amount/balance, type filters, date filters, print behavior, UI/browser flow, restoring data from localStorage, fallback/validation for invalid stored data, storage-write-failure safety, and source-code constraint checks. Storage-write-failure safety means a failed save does not advance visible, in-memory, or persisted state.


## Patterns in the higher-ranked results

### Task decomposition was the clearest pattern

Some Spec Loop solutions used a decomposition form specified by the user or the initial prompt. This was deliberate: the study compared backlog tasks, sequential subtasks, and a broad single task.

The clearest pattern was not decomposition by itself. It was vertical decomposition with design and tests for each part, written before that part was implemented.

The observed cases were:

- In `spec-loop-base-backlog-prompted`, the initial prompt asked for separate backlog tasks.
- In `spec-loop-incremental`, the initial prompt asked for each subtask to be designed only after the previous subtask had been implemented and committed.
- Both had 13 of 15 behavior categories fully checked, 1 category partially checked, and 1 category missing.
- `spec-loop-base-backlog-steered` also shared first place after the user redirected planning to a backlog.

Conclusion: in these solutions, decomposition helped when task files specified scope, design, and tests for each implementation part. More documentation alone was not enough.

### Long plans did not guarantee more tested behavior

Superpowers produced detailed plans and asked many product questions. The final solutions still had fewer fully checked behavior categories than the Spec Loop backlog solutions. GSD Small Feature in GSD Pi produced a shorter plan and a working app, but it also had fewer committed tests and less committed rationale.

Conclusion: implementation plans help before execution, but they do not prove that the final code preserved the plan. For later review, the final source/tests and committed, updated task/workflow state mattered more.

### Object-calisthenics constraints had mixed value

The constraint set improved vocabulary and boundary awareness in some generated files. It did not reliably improve maintainability.

Observed costs included large files, more indirection, import cycles, and incomplete preservation of constraints in final production code.

Conclusion: domain-language constraints helped only when paired with simplicity/change-locality checks and enforceable tests.

## Ranking by study criteria and limits

The list ranks the 12 bank-kata solutions under the study criteria. It does not say that one framework is always better than another. Because first place is shared, the table has 11 ranks.

The ranking is not a formula that sums the result-metrics table. The table is one input. The ordering was applied this way:

1. Group solutions by required-behavior checks, with the missing or partial category considered, not only the count.
2. Give more weight to safety categories such as money validation, transfer rollback, persistence validation, storage-write-failure safety, and print/UI evidence than to static shape signals.
3. Use source/test design score to break or adjust close comparisons, but do not let it override missing safety evidence.
4. Use generated specification files and session communication to check whether important decisions were visible before or during coding.
5. Treat static metrics, token use, and compactness as supporting facts, not ranking drivers.

| Rank | Solution | Evidence affecting rank |
|---:|---|---|
| 1 | `spec-loop-base-backlog-steered` and `spec-loop-base-backlog-prompted` | Both had 13 / 1 / 1 required-behavior checks. Their missing category was source-code constraint checks, which were not part of their non-calisthenics prompt condition. Both preserved detailed task-level design and test specifications before implementation. `spec-loop-base-backlog-steered` had the higher source/test design score and deeper localStorage validation. `spec-loop-base-backlog-prompted` was cleaner evidence for the backlog form because the backlog was specified in the initial prompt, but it had less deposit/withdrawal save-failure handling evidence. |
| 2 | `spec-loop-incremental` | Also had 13 / 1 / 1 required-behavior checks with source-code constraint checks missing outside its prompt condition. It had broad rendered-app tests for deposits, withdrawals, invalid input, transfers, persistence restore, malformed storage, filters, and printing. It ranked below the first tier because its design record was one task file with subtasks rather than separate backlog task files, and its source layering was coarser. |
| 3 | `spec-loop-calisthenics-incremental` | Ranked above the other calisthenics Spec Loop solutions because it had fewer missing behavior categories, visible subtask-by-subtask decisions, transfer persistence-failure rollback evidence, and committed checkpoints after each slice. It ranked below the non-calisthenics Spec Loop rows because money validation and print evidence were partial, storage validation was less complete, and source/test design score was lower. |
| 4 | `spec-loop-calisthenics` | Had broad domain and browser-flow evidence, clear domain vocabulary, and a committed task file with design and test specifications. It ranked below `spec-loop-calisthenics-incremental` because print evidence was partial, bad-storage fallback and save-failure safety were missing, and the final source had two very large production files. |
| 5 | `spec-loop-calisthenics-single-task` | Had source-constraint tests and exact-date/filter/print/storage evidence, but it used one broad task. It ranked below the other Spec Loop calisthenics solutions because money validation, bad-storage fallback, and save-failure safety were missing or less complete, browser-flow evidence was partial, and dependency cycles reduced the source/test design score. |
| 6 | `superpowers` | Had visible accepted product decisions and a 12/18 source/test design score. It ranked below the Spec Loop rows because UI/browser-flow tests, print assertions, and save-failure behavior were less complete. It ranked above `open-spec` because more product choices were visibly asked, answered, and accepted. |
| 7 | `open-spec` | Had concise generated specification files, robust core money/rollback tests, and 11 / 0 / 1 checks inside its base scope. It ranked below `superpowers` because important persistence/filter/date/UI choices were less visibly resolved with the user. Persistence categories were outside the base prompt and implemented scope rather than counted as failures. |
| 8 | `open-spec-calisthenics` | Had 13 / 1 / 1 required-behavior checks and a 15/18 source/test design score, so the result table alone would rank it higher. It ranks here because its partial category was money validation, its missing category was storage-write-failure safety, and fewer detailed implementation/test decisions were user-visible before coding than in the Spec Loop task files. |
| 9 | `superpowers-calisthenics` | Had complete feature intent and visible correction behavior, but only 8 full required-behavior checks. Money validation, persistence fallback, UI flow, print behavior, and source-constraint preservation were less complete than in the higher-ranked solutions. |
| 10 | `superpowers-5.4` | Had readable React structure, but only 7 full required-behavior checks. Successful withdrawal evidence, money precision validation, filtered-print evidence, persistence validation depth, and save-failure safety had less test evidence than in the higher-ranked solutions. |
| 11 | `gsd-small-feature` | Passed its committed tests and build, but had only 5 committed tests and 7 full required-behavior checks. Browser smoke checks existed in the session, but print, storage fallback, storage-write failure, and UI regression checks were not committed as executable tests. The committed GSD files preserved less decision analysis than the higher-ranked generated specification files. |

The study criteria give more weight to required-behavior checks, implementation safety, written or discussed decisions before code, and source/test design score than to compact files or conversation volume alone.

### Limits

Do not conclude that one framework is universally superior.

Supported conclusions are narrower:

- OpenSpec produced the shortest generated specification files and used few visible clarification gates.
- Spec Loop wrote the most explicit task files for design and test specifications before implementation.
- Superpowers asked the most product/design questions and wrote the longest implementation plans.
- Explicit task decomposition was associated with higher-ranked Spec Loop results.
- Object-calisthenics constraints were not sufficient to improve final source structure.
- Static metrics identified risks but did not decide the ranking.

## Up-front design and token use

Workflows without sequential design gates concentrated design before implementation. OpenSpec created proposal/design/spec files before applying the change. Superpowers created a design document and implementation plan before execution. Spec Loop single-task or whole-task solutions designed the broad task before implementation.

Token data supports a narrower cost observation, not a universal cross-framework law. For the original eleven solutions, the table uses integrated totals from `comparison.md`. For `gsd-small-feature`, it uses usage counters from the GSD Pi session. The totals are dominated by cached input: previously seen context re-read by the model. There was no session compaction, so longer incremental sessions repeatedly read a growing context, mostly from cache. Treat the totals as interaction and cached-context-read volume, not as mostly fresh input.

| Solution(s) and condition | Token totals / source | Supported interpretation |
|---|---:|---|
| `open-spec`, `open-spec-calisthenics` — OpenSpec single-change plans | 2.18M and 4.51M from `comparison.md` | Lowest token totals among the original eleven solutions; concise up-front files. |
| `spec-loop-calisthenics`, `spec-loop-calisthenics-single-task` — up-front/single-task Spec Loop calisthenics | 7.74M and 10.39M from `comparison.md` | Lower token total than the matched calisthenics incremental solution. |
| `spec-loop-calisthenics-incremental` — incremental Spec Loop calisthenics | 18.29M from `comparison.md` | More expensive, with subtask-by-subtask decisions. |
| `spec-loop-base-backlog-steered`, `spec-loop-incremental`, `spec-loop-base-backlog-prompted` — non-calisthenics Spec Loop backlog/incremental work | 19.69M, 20.22M, and 36.09M from `comparison.md` | More review boundaries; token volume depended heavily on implementation loops and repeated cached-context reads, not only planning form. |
| `superpowers`, `superpowers-5.4`, `superpowers-calisthenics` — Superpowers single up-front plans | 16.65M, 18.19M, and 22.26M from `comparison.md` | Single up-front plans were detailed, but did not always use fewer tokens than Spec Loop incremental solutions. |
| `gsd-small-feature` — GSD Small Feature in GSD Pi | 6.58M total tokens and about $5.69 from the `.gsd` session log | No evidence of subagents: the repo-specific session directory contained one JSONL file and no subagent/delegation tool calls. Lower recorded cost than the aborted standard GSD attempt, with scope and plan gates, but fewer committed tests and less committed analysis. |

The supported conclusion is narrower: single-task or up-front design reduced repeated design/context reads inside comparable conditions, especially within the Spec Loop calisthenics solutions. It also gave less attention to each implementation part. Incremental design consumed more repeated context because it had more design/implementation checkpoints, but it made decisions later, when previous implementation evidence existed.

### Aborted [GSD](https://opengsd.net) attempt

A standard GSD attempt was started after the original eleven completed solutions and before the GSD Small Feature solution in GSD Pi was added, using Codex with GPT-5.5 xhigh. It is not included in the completed-solution comparison or ranking because it was cancelled for budget/process reasons before completing the kata. The user selected GSD's standard granularity, interactive mode, sequential execution, committed planning docs, research, plan check, verifier, adaptive models, drift guard, and vertical MVP. Those choices were reasonable for evaluating GSD: standard was the middle granularity option, not a fine-grained stress setting. Under that configuration, GSD decomposed the small kata into six phases: account walking skeleton, deposit, withdrawal, transfer/rollback, persistence, and statement filters/printing. By cancellation, it had completed a tested account/deposit slice and had started Phase 3 planning, but withdrawals, transfers, rollback, local storage, filters, and printing had not started in the source code. Available parent-plus-subagent counters were roughly 76M tokens by cancellation, mostly cached input. A narrow source/test check found that the completed deposit part passed 25 tests and typecheck. The observed issue was not the completed part's source or tests. The standard GSD path appeared to constrain the model more than it helped it: reasonable local engineering judgments were repeatedly routed through required files and gates such as discussion, UI spec, research, pattern mapping, planning, plan checking, UAT, verification, state transitions, and recovery of framework metadata. Although GSD has separate fast and quick modes, the standard path did not visibly adapt by collapsing the kata into a shorter route after the task size and budget pressure were clear. At cancellation, it had spent high token volume before most required behavior was implemented.

## Reproduction notes

Use the solution repositories in the GitLab subgroup [skill-assessment/bank-kata](https://gitlab.com/skill-assessment/bank-kata). Each completed comparison repository contains `main` and the analysis tag. The `gsd-small-feature` repository also has its GSD workflow files committed under `.gsd/workflows`.

Use tag:

```text
analysis-2026-06-30
```

Standard verification commands in each solution repository:

```sh
npm test
npm run build
```

The behavior and design conclusions require reading source, tests, generated specification files, and relevant session communication. Commands alone do not produce these conclusions.
