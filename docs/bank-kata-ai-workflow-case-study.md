# Bank Kata AI Workflow Case Study

This document compares 12 completed AI-assisted implementations of the same browser bank kata, based on Sandro Mancuso's [Bank Kata](https://github.com/sandromancuso/Bank-kata). It is an observational comparison: the runs shared the same core exercise, but prompt details, adapters, and interaction patterns differed, so those differences are treated as context when interpreting outcomes.

The main outcome is a framework-fit conclusion: different workflows served different working styles and control points. The best fit depends on whether the goal is compact specification review, design control before implementation, a detailed implementation script, or low-interaction momentum.

## Main outcome: observed workflow fit

| Workflow goal | Best observed fit | Evidence from the bank-kata runs | Caveat |
|---|---|---|---|
| Compact capability/specification review | [OpenSpec](https://github.com/Fission-AI/OpenSpec) | Produced the shortest generated design artifacts: `open-spec` 5 files / 214 lines; `open-spec-calisthenics` 6 files / 310 lines. The proposal/design/spec split was easy to scan. | Weaker at exposing detailed data, UI, and failure-behavior decisions before implementation. |
| Design-level control before code | [Spec Loop](https://github.com/dpolivaev/spec-loop) | The strongest code/test outcomes came from Spec Loop runs with backlog or incremental decomposition: `spec-loop-base-backlog-steered`, `spec-loop-base-backlog-prompted`, and `spec-loop-incremental` each had explicit automated-test evidence for 13 of 15 behavior categories, partial evidence for 1 category, and no evidence for 1 category. Design scores were 17/18, 14/18, and 13/18. | More ceremony. It requires the user to review task files and enforce planning checkpoints. |
| Detailed implementation recipe | [Superpowers](https://github.com/obra/superpowers) | Produced long, concrete specs/plans and surfaced many product choices in conversation. Useful when the goal is a step-by-step implementation path. | Plans were long and weaker as final audit artifacts. Final code/test evidence was below the strongest Spec Loop runs. |
| Low-interaction, fast momentum | [OpenSpec](https://github.com/Fission-AI/OpenSpec); GSD Small Feature in [GSD Pi](https://opengsd.net) for a more execution-oriented lightweight path | The OpenSpec runs, `open-spec` and `open-spec-calisthenics`, had the fewest visible clarification gates and shortest specification artifacts. `gsd-small-feature` also had low clarification burden and produced a working app with committed scope, plan, state, and summary artifacts. | The OpenSpec runs made more unreviewed choices. `gsd-small-feature` had weaker durable behavior evidence and committed decision analysis than the strongest runs. |
| Future auditability of old code changes | [Spec Loop](https://github.com/dpolivaev/spec-loop), with [OpenSpec](https://github.com/Fission-AI/OpenSpec) as the strongest compact alternative | Spec Loop task files repeatedly preserved research, analysis, design, glossary/terms, constraints, and expected tests. OpenSpec preserved proposal/design/spec rationale, including decisions and alternatives, in a more compact form. | This measures what a future reviewer can reconstruct from Git, not whether the app works today. Artifacts only count when committed. |

Practical conclusion: in this case study, Spec Loop fit experienced-user design control before code and future auditability of implementation reasoning; OpenSpec fit compact scope/specification review and low-interaction momentum; Superpowers fit collaborative planning and detailed execution recipes; GSD Small Feature in GSD Pi fit a lighter scope/plan/verify path when its `.gsd/workflows` artifacts were committed. Object-calisthenics/domain-language constraints are discussed separately because their effect on final design quality is a different question from workflow fit.

## Kata attribution and scope

The kata requirements are not original to this study. They come from the Bank Account / Bank Kata used in software-craftsmanship practice, commonly attributed to Sandro Mancuso / Codurance. The user prompts reused that kata wording, especially:

> Think of your personal bank account experience. Requirements: Deposit and Withdrawal; Transfer; Account statement (date, amount, balance); Statement printing; Statement filters (just deposits, withdrawal, date).

The object-calisthenics constraints used in the calisthenics runs are also not original to this study. They come from Jeff Bay's Object Calisthenics exercise in *The ThoughtWorks Anthology*. The domain-language boundary rule was added alongside those rules for this bank-kata comparison.

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

## Compared workflows

### [OpenSpec](https://github.com/Fission-AI/OpenSpec)

OpenSpec structures work as a change proposal: proposal, design, tasks, and capability specs. Its observed strength here was concise intent review. Its observed weakness was less visible negotiation of detailed implementation choices.

### [Spec Loop](https://github.com/dpolivaev/spec-loop)

Spec Loop structures work around task files, subtasks, backlog items, approval gates, design sections, and test specifications. Its observed strength here was review of each implementation contract before code: scope, design, and expected tests. Its observed weakness was process weight and sensitivity to the chosen decomposition form.

### [Superpowers](https://github.com/obra/superpowers)

Superpowers emphasizes brainstorming, clarifying questions, design approval, detailed implementation plans, TDD-style execution, and verification. Its observed strength here was conversation and implementation planning. Its observed weakness was that long plans were not as reliable as final audit artifacts unless reconciled with the implemented code.

### [GSD](https://opengsd.net)

GSD has multiple runtime paths. The completed GSD run included here used the GSD Small Feature workflow through GSD Pi. That workflow was selected after the standard GSD workflow appeared too heavy for this kata: it offered a lighter scope/plan/verify path while still producing reviewable workflow artifacts. It produced scope, plan, state, and summary artifacts under `.gsd/workflows`. Those artifacts were later committed to the solution repository. This run is not evidence for GSD core quick behavior, and it is not the same condition as the aborted standard GSD run discussed under token/cost observations.

## Runs, prompts, and steering

Solution repositories are public in the GitLab subgroup [skill-assessment/bank-kata](https://gitlab.com/skill-assessment/bank-kata). Each repository contains `main` and tag `analysis-2026-06-30` at the listed commit. The `Solution` column links to the repository and uses the repository path name.

Initial prompt and in-session steering are grouped together because both affect attribution. A run improved by user steering is still a real artifact, but that improvement cannot be credited to the workflow alone.

Two additional GSD runs are not included: the standard GSD run was aborted before completing the kata, and `gsdpi-quick` produced only the result, with no generated documentation, design artifact, discussion, or steering checkpoint.

| Solution | Workflow / app / model | Commit | Initial prompt condition | Relevant in-session steering | Attribution effect |
|---|---|---|---|---|---|
| [open-spec](https://gitlab.com/skill-assessment/bank-kata/open-spec) | OpenSpec / pi / GPT-5.5 xhigh | `81ce8ab5a1b9` | Base bank-kata prompt via OpenSpec proposal flow. This prompt variant included the transfer rollback wording: “Transfer (transactional, rollback on failures).” | No material user steering found beyond normal proceed/apply flow. | Good evidence for compact OpenSpec proposal/spec review on the base kata. |
| [open-spec-calisthenics](https://gitlab.com/skill-assessment/bank-kata/open-spec-calisthenics) | OpenSpec / pi / GPT-5.5 xhigh | `1d71c713d61c` | Expanded prompt: TypeScript/Vite, Daily/Savings, transfer rollback, browser local storage, and all object-calisthenics/domain-language constraints listed above. | No material user steering found beyond applying the generated change. | Good evidence for OpenSpec with a constraint-heavy prompt; weaker evidence for interactive decision shaping. |
| [spec-loop-base-backlog-steered](https://gitlab.com/skill-assessment/bank-kata/spec-loop-base-backlog-steered) | Spec Loop / pi / GPT-5.5 xhigh | `ae1eb4bb896d` | Base bank-kata prompt. It did not initially require separate backlog tasks, localStorage, or Daily/Savings. | User asked for a “proper multitask backlog,” opted into `glossary.adoc`, and later asked whether transactions and rollback had been considered. | Strongest final artifact as built, but the backlog structure and deeper rollback/storage-write-failure handling were materially user-steered. |
| [spec-loop-base-backlog-prompted](https://gitlab.com/skill-assessment/bank-kata/spec-loop-base-backlog-prompted) | Spec Loop / pi / GPT-5.5 xhigh | `4b9f8aa9776a` | Expanded prompt: TypeScript/Vite, Daily/Savings, transfer rollback, browser local storage, separate backlog tasks, and design of each following task only after the previous task was implemented and committed. | During the final solution session, steering was mostly approvals and moving completed tasks to `done`. | Cleaner evidence for the backlog prompt than the steered run. |
| [spec-loop-incremental](https://gitlab.com/skill-assessment/bank-kata/spec-loop-incremental) | Spec Loop / pi / GPT-5.5 xhigh | `4cd947ec1e42` | Expanded prompt: TypeScript/Vite, Daily/Savings, transfer rollback, browser local storage, and subtask design-after-commit sequencing. | No material corrective steering found. | Cleanest non-calisthenics evidence for Spec Loop sequential-subtask work. |
| [spec-loop-calisthenics](https://gitlab.com/skill-assessment/bank-kata/spec-loop-calisthenics) | Spec Loop / pi / GPT-5.5 xhigh | `319a8c9d4c24` | Expanded calisthenics prompt with TypeScript/Vite, Daily/Savings, transfer rollback, browser local storage, and all constraints listed above. | User corrected the process: requested task with subtasks, objected that clarification instructions were not followed, and questioned whether the assistant knew the breakdown skill. | Partly evidence of user repair of workflow misuse. The final artifact cannot be attributed to the prompt/framework alone. |
| [spec-loop-calisthenics-incremental](https://gitlab.com/skill-assessment/bank-kata/spec-loop-calisthenics-incremental) | Spec Loop / pi / GPT-5.5 xhigh | `d8948538ead4` | Expanded calisthenics prompt plus subtask design-after-commit sequencing. | No material corrective steering found beyond normal approvals. | Cleanest calisthenics evidence for Spec Loop sequential-subtask work. |
| [spec-loop-calisthenics-single-task](https://gitlab.com/skill-assessment/bank-kata/spec-loop-calisthenics-single-task) | Spec Loop / pi / GPT-5.5 xhigh | `b708bd2c7d97` | Expanded calisthenics prompt without explicit backlog/subtask sequencing. | No material outcome-relevant steering retained. | Evidence for the broad single-task calisthenics condition. |
| [superpowers](https://gitlab.com/skill-assessment/bank-kata/superpowers) | Superpowers / Codex / GPT-5.5 xhigh | `58bcb54d6478` | Base bank-kata prompt with transactional rollback wording. | User supplied important clarifications: `localStorage`, two internal accounts like Daily/Savings, and plain TypeScript + Vite. | Strong communication evidence, but product fit was materially improved by user answers. |
| [superpowers-5.4](https://gitlab.com/skill-assessment/bank-kata/superpowers-5.4) | Superpowers / Codex / GPT-5.4 high | `5db5d24f5f27` | Base bank-kata prompt with transactional rollback wording. | User supplied browser local storage, two fixed accounts, browser-only display, and later changed direction to include printing. User also answered “ts, vite, browser only,” which the assistant did not clarify before choosing React. | Substantial user steering, plus a communication weakness around stack choice. This weakens the final design-quality interpretation. |
| [superpowers-calisthenics](https://gitlab.com/skill-assessment/bank-kata/superpowers-calisthenics) | Superpowers / Codex / GPT-5.5 xhigh | `65e2dbb20a9d` | Expanded calisthenics prompt with TypeScript/Vite, Daily/Savings, transfer rollback, browser local storage, and all constraints listed above. | User said domain tests were enough, later asked not to be bothered with trivial questions, and later challenged date-control design and statement sorting. | Strong interaction/correction evidence, but test scope and UX correction were user-shaped; final constraint preservation is judged from code/tests. |
| [gsd-small-feature](https://gitlab.com/skill-assessment/bank-kata/gsd-small-feature) | GSD Small Feature / GSD Pi / GPT-5.5 xhigh | `aef38ffcae7b` | GSD Small Feature workflow started without a description; the assistant asked for one, then the user supplied the expanded TypeScript/Vite, Daily/Savings, rollback, statement, print, filter, and localStorage prompt. | Scope and plan approval gates selected the recommended options. The assistant did not ask separate gray-area questions; it packaged defaults into the scope approval. `.gsd/workflows` artifacts were committed after the run. | Evidence for the GSD Small Feature workflow as run through GSD Pi, not for GSD core quick or standard GSD. Final code was working, but durable tests and committed decision analysis were thinner than the strongest runs. |

## How conclusions were derived

The review used this chain of evidence:

1. **Prompt condition** — what the run was asked to do.
2. **Generated design/planning artifacts** — what the workflow made reviewable before or during implementation.
3. **Committed artifact auditability** — what a later reviewer can reconstruct from Git without the chat log.
4. **Session communication** — which material choices were surfaced to the user and accepted before coding.
5. **Source code structure** — whether the final implementation preserved clean boundaries and localized change.
6. **Tests** — which required behaviors and failure modes were actually asserted.
7. **Static metrics** — supporting diagnostics for file size, complexity, duplication, dependency cycles, and risky patterns.

The strongest conclusions require agreement between several evidence types. Example: a good plan without tests is not enough; many tests without reviewable design is also not enough.

UI references in this study are limited to factual evidence: generated UI/display sections, browser-flow checks, print/filter/storage behavior, and separation of UI code from domain/application code. The study does not compare visual design quality, usability, accessibility, or overall UI quality.

## Generated specification structure

Here, “specification” means the generated written structure that described, constrained, or guided implementation, including task files, plans, and workflow state files.

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
| [superpowers](https://gitlab.com/skill-assessment/bank-kata/superpowers) | - Design document (148 lines): overview, goals/non-goals, architecture, domain model, banking operations, statement filtering, persistence, UI, validation/errors, testing, and boundaries.<br>- Implementation plan (1840 lines): file structure and 8 tasks from scaffold through browser verification. |
| [superpowers-5.4](https://gitlab.com/skill-assessment/bank-kata/superpowers-5.4) | - Design document (320 lines): overview, goals/non-goals, architecture, data model, transaction behavior, UI, filtering, validation, failure handling, testing, module boundaries, and acceptance criteria.<br>- Implementation plan (1741 lines): file structure and 9 tasks from Vite/React/Vitest bootstrap through verification and cleanup. |
| [superpowers-calisthenics](https://gitlab.com/skill-assessment/bank-kata/superpowers-calisthenics) | - Design document (120 lines): goal, scope, constraints, architecture, domain model, application flow, statements, persistence, UI, error handling, testing, and settled decisions.<br>- Implementation plan (2162 lines): file structure, domain rules, and 7 tasks from scaffold through final verification. |
| [gsd-small-feature](https://gitlab.com/skill-assessment/bank-kata/gsd-small-feature) | - Context (52 lines): feature description, decisions made or proposed, scope boundaries, and gray areas.<br>- Plan (120 lines): 4 implementation tasks and final workflow verification.<br>- State (33 lines): workflow template, branch, phases, timestamps, and artifact directory.<br>- Summary (73 lines): built behavior, changed files, commits, run instructions, and test/use notes. |

## Future auditability of generated artifacts

A separate review criterion emerged after the first publication: what future reviewers can reconstruct from Git after the chat log is gone. This is not the same as implementation quality. It measures whether intent, research, trade-offs, decisions, and test expectations are preserved with the code change.

| Workflow/run type | What was preserved in generated artifacts | Auditability interpretation |
|---|---|---|
| Spec Loop | Generated task files repeatedly contained `Research`, `Analysis`, and `Design` sections. Across the completed Spec Loop solutions in this study, `Analysis` sections contained between 6 and 28 bullet items per solution with explicit `because` or `so that` reasoning. | Strongest retrospective explanation of why implementation choices were made, especially when task files were committed. |
| OpenSpec | Generated proposal/design/spec files preserved goals, decisions, rationale, alternatives considered, and risks/trade-offs. | Strong compact rationale. Less task-centered than Spec Loop, but good for reconstructing design intent. |
| Superpowers | Generated specs/plans preserved detailed implementation recipes and TDD expectations, including many expected-failure notes. | Useful execution trail, but less consistently structured as final decision analysis and harder to audit when long plans diverge from final code. |
| GSD Small Feature in GSD Pi | Committed `.gsd/workflows` artifacts preserve scope, key decisions made or proposed, plan, state, and summary. | Useful lightweight audit trail. Weaker because gray areas are listed but not resolved in the artifacts, and verification details rely partly on the session log. |
| GSD Pi quick run | No generated documentation or design artifact comparable to the other workflows; no discussion or clarification gate; only the resulting repository. | Excluded from the completed comparison because there was no design-review or steering surface to evaluate. |

This criterion changes one interpretation: Spec Loop was not unique in documenting rationale, but it was distinctive in preserving implementation reasoning inside task files alongside research, glossary/terms, constraints, design, and test expectations. OpenSpec was the closest alternative for compact decision rationale.

## Up-front design, token use, and focus

Runs without sequential design gates concentrated design before implementation. That was visible across workflows: OpenSpec created proposal/design/spec artifacts before applying the change; Superpowers created a design and implementation plan before execution; Spec Loop single-task or whole-task runs designed the broad task before implementation.

Token data supports a narrower cost observation, not a universal cross-framework law. For the original eleven runs, the table uses integrated totals from `comparison.md`. For `gsd-small-feature`, it uses usage counters from the GSD Pi session. The totals are dominated by cached input: previously seen context re-read by the model. There was no session compaction, so longer incremental sessions repeatedly read a growing context, mostly from cache. Treat the totals as interaction and cached-context-read volume, not as mostly fresh input.

| Solution(s) and condition | Token totals / source | Supported interpretation |
|---|---:|---|
| `open-spec`, `open-spec-calisthenics` — OpenSpec single-change plans | 2.18M and 4.51M from `comparison.md` | Cheapest overall among the original eleven runs; concise up-front artifacts. |
| `spec-loop-calisthenics`, `spec-loop-calisthenics-single-task` — up-front/single-task Spec Loop calisthenics | 7.74M and 10.39M from `comparison.md` | Cheaper than the matched calisthenics incremental run. |
| `spec-loop-calisthenics-incremental` — incremental Spec Loop calisthenics | 18.29M from `comparison.md` | More expensive, with more focused subtask-by-subtask decisions. |
| `spec-loop-base-backlog-steered`, `spec-loop-incremental`, `spec-loop-base-backlog-prompted` — non-calisthenics Spec Loop backlog/incremental work | 19.69M, 20.22M, and 36.09M from `comparison.md` | More review boundaries; token volume depended heavily on implementation loops and repeated cached-context reads, not only planning form. |
| `superpowers`, `superpowers-5.4`, `superpowers-calisthenics` — Superpowers single up-front plans | 16.65M, 18.19M, and 22.26M from `comparison.md` | Single up-front plans were detailed, but not always cheaper than Spec Loop incremental runs. |
| `gsd-small-feature` — GSD Small Feature in GSD Pi | 6.58M total tokens and about $5.69 from the `.gsd` session log | No evidence of subagents: the repo-specific session directory contained one JSONL file and no subagent/delegation tool calls. Lower cost than aborted standard GSD, with scope and plan gates, but also much lighter durable test and analysis coverage. |

The supported conclusion is: single-task or up-front design tends to reduce repeated design/context reads inside comparable conditions, especially within the Spec Loop calisthenics runs, but it is less focused on the details of each implementation slice. Incremental design consumes more repeated context because it has more design/implementation checkpoints, but it exposes decisions later, when previous implementation evidence exists.

### Aborted [GSD](https://opengsd.net) run

A standard GSD run was started after the original eleven completed runs and before the later GSD Small Feature run in GSD Pi was added, using Codex with GPT-5.5 xhigh. It is not included in the completed-solution comparison or ranking because it was cancelled for budget/process reasons before completing the kata. The user selected GSD's standard granularity, interactive mode, sequential execution, committed planning docs, research, plan check, verifier, adaptive models, drift guard, and vertical MVP. Those are defensible framework-evaluation choices: standard was the middle granularity option, not a fine-grained stress setting. Under that configuration, GSD decomposed the small kata into six phases: account walking skeleton, deposit, withdrawal, transfer/rollback, persistence, and statement filters/printing. By cancellation, it had completed a tested account/deposit slice and had started Phase 3 planning, but withdrawals, transfers, rollback, local storage, filters, and printing had not started in the source code. Available parent-plus-subagent counters were roughly 76M tokens by cancellation, mostly cached input. A narrow source/test check found the completed deposit slice technically decent: 25 tests passed and typecheck passed. The observed issue was not code quality for the completed slice. The standard GSD path appeared to constrain the model more than it helped it: reasonable local engineering judgments were repeatedly routed through required artifacts and gates such as discussion, UI spec, research, pattern mapping, planning, plan checking, UAT, verification, state transitions, and recovery of framework metadata. Although GSD has separate fast and quick modes, the standard/autonomous path did not visibly adapt by collapsing the kata into a shorter route after the task size and budget pressure were clear. The result was a clean partial implementation, but too little model freedom to choose a simpler sufficient delivery path.

### Excluded GSD Pi quick run

A separate completed `gsdpi-quick` repository was deliberately excluded from the completed-solution comparison. It produced the result without generated documentation, design artifacts, discussion, or clarification gates. Because it had no design-review trail and no steering surface, it is not comparable to workflows that expose artifacts or decisions before or during coding.

## Communication outcomes

Communication was evaluated only where it affected decision quality. A different acceptable product choice was not treated as a defect if it was visible and accepted. Silent choices were weaker because the user had no review point.

| Solution(s) | Significant communication pattern | Effect on outcome |
|---|---|---|
| `open-spec`, `open-spec-calisthenics` | Efficient, but few visible clarification points. `open-spec` did not visibly probe persistence, strict filter semantics, or exact-date versus date-range behavior in depth. | Good momentum; weaker record of which decisions were made and why. |
| `spec-loop-base-backlog-steered` | Surfaced concrete decisions with reasons and confidence levels; later repaired missing rollback and storage-write-failure decisions after user challenge. | Strong review trail; quality partly user-steered. |
| `spec-loop-base-backlog-prompted` | Final session surfaced framework, storage model, schema compatibility, ledger contracts, transfer semantics, filters, and printing. | Strong final communication. |
| `spec-loop-incremental` | Exposed decisions at each subtask boundary before implementation. | Strong incremental control without separate backlog files. |
| `spec-loop-calisthenics-single-task` | Used one broad task rather than sequential backlog/subtask decomposition. | Less incremental review surface. |
| `superpowers` | Asked many relevant product/design questions and got acceptance for localStorage, Daily/Savings, stack, transfer visibility, date filtering, and test scope. | Strong clarification; final artifacts still needed source/test audit. |
| `superpowers-5.4` | Asked many questions, but did not adequately resolve ambiguous `ts, vite, browser only` direction before choosing React. | Communication volume did not prevent a questionable stack/design choice. |
| `superpowers-calisthenics` | Strong correction behavior after user challenged date-control design. | Good interaction; constraints still did not survive strongly enough into final code. |
| `gsd-small-feature` | Asked for the missing feature description, then used scope and plan approval gates. The selected answers were the recommended approval options. Gray areas such as UI stack, rollback demonstration, date filtering shape, and transfer filtering were not asked separately. | Better communication than the excluded GSD Pi quick run, but still approval-driven rather than clarification-heavy. |

Conclusion: useful communication was not the number of questions. It was whether the right material decisions were visible at the point where they could still change the design. `gsd-small-feature` made decisions visible enough for approval, but not as independently resolved questions.

## Code and test outcomes

All completed comparison projects passed their own tests and build at tag `analysis-2026-06-30`. Behavior evidence is shown as full / partial / missing across 15 behavior categories. The `gsd-small-feature` row was added after the original metric sweep and is based on the later manual audit plus fresh `npm test -- --run`, `npm run build`, and `npm run lint` checks.

| Solution | Tests | Behavior evidence (full / partial / missing) | Design score | Code/test conclusion |
|---|---:|---:|---:|---|
| `spec-loop-base-backlog-steered` | 60/60 | 13 full / 1 partial / 1 missing | 17/18 | Strongest final artifact as built: clean boundaries, broad behavior tests, strongest localStorage validation and rollback depth. User intervention materially helped. |
| `spec-loop-base-backlog-prompted` | 58/58 | 13 / 1 / 1 | 14/18 | Strong backlog-style repeat: broad tests and clear modules; weaker validation of restored localStorage data and less complete handling of storage-write failure during deposit/withdrawal than the steered run. |
| `spec-loop-incremental` | 30/30 | 13 / 1 / 1 | 13/18 | Strong non-backlog incremental control; less granular planning trace than separate backlog files. |
| `open-spec-calisthenics` | 12/12 | 13 / 1 / 1 | 15/18 | High design score from source review and broad test evidence; OpenSpec did not expose as much interactive record of design decisions. No automated test simulated localStorage failing while saving state. |
| `open-spec` | 19/19 | 11 / 0 / 1 | 12/18 | Solid compact implementation for base scope; weaker visible decision shaping. |
| `superpowers` | 17/17 | 11 / 1 / 3 | 12/18 | Strong accepted decisions; weaker UI test evidence, localStorage validation, print assertions, and tests for storage-write failure. |
| `spec-loop-calisthenics` | 7/7 | 11 / 1 / 3 | 10/18 | Good vocabulary; weaker simplicity and change locality due to very large files. |
| `spec-loop-calisthenics-single-task` | 17/17 | 11 / 1 / 3 | 10/18 | Automated tests checked source-code constraints; broad single-task design and dependency cycles remained problems. |
| `spec-loop-calisthenics-incremental` | 16/16 | 10 / 3 / 2 | 11/18 | Best calisthenics incremental result; still fragmented and weaker on storage/print evidence. |
| `superpowers-calisthenics` | 13/13 | 8 / 3 / 4 | 11/18 | Good communication; weaker final constraint preservation and behavior evidence. |
| `superpowers-5.4` | 16/16 | 7 / 5 / 3 | 11/18 | Readable pieces, but weakest base-run behavior evidence. |
| `gsd-small-feature` | 5/5 | 7 / 2 / 6 | 11/18 | Working lightweight app with durable tests for deposits, withdrawal rejection, successful transfer, simulated rollback, and statement filters. Browser smoke in the session covered UI flow, print invocation, localStorage reload, and clean diagnostics, but those checks were not committed as executable tests. Weak on durable print, storage fallback, storage-write-failure, and UI regression evidence. |

Behavior evidence counted test assertions over the relevant behavior categories: money validation, deposit, withdrawal, insufficient-funds safety, transfer success, rejected-transfer no-change behavior, statement date/amount/balance, type filters, date filters, print behavior, UI/browser flow, restoring data from localStorage, fallback/validation for invalid stored data, storage-write-failure safety, and source-code constraint checks. Storage-write-failure safety means a failed save does not advance visible, in-memory, or persisted state.

Design score used a 0–3 rubric for naming/domain language, simplicity (KISS), single responsibility (SRP), dependency direction, change locality, and testability.

## Static metric outcomes

Static metrics did not choose the winner. They explained specific risks. The facts below come from the original eleven-run static-metric sweep; `gsd-small-feature` was added later and was not included in those generated metric result files.

Relevant facts:

- Duplication was low everywhere. Highest duplicated-line percentage was about 2.84% in `superpowers-calisthenics`.
- Maximum cyclomatic complexity was moderate. Higher values mostly appeared in validation or normalization functions.
- Import cycles were material in three runs: `spec-loop-calisthenics-incremental` had 2, `spec-loop-calisthenics-single-task` had 32, and `superpowers-calisthenics` had 1.
- `spec-loop-calisthenics` had two very large production files: `src/domain/bank.ts` at 728 nonblank LOC and `src/browser/browserBankDemo.ts` at 712 nonblank LOC.

Interpretation: static metrics were useful diagnostics, but behavior evidence and design locality explained the meaningful quality differences better.

## Why the strongest outcomes happened

### Decomposition was the clearest positive influence

The clearest positive pattern was explicit vertical decomposition with later design after earlier implementation existed.

Current Spec Loop guidance does not say “always split.” It filters among `task file`, `task file with subtasks`, and `multiple task files / backlog items`. It keeps backlog items when separate release decisions are advisable, keeps subtasks when vertical slices can stand alone but should normally be released together, and keeps a plain task file when no useful releasable slice split is visible or the user has rejected subtasks. If more than one form remains, it asks the user to choose.

That current rule was added after the first solutions in this study. It should not be retroactively treated as a rule those early runs failed to follow. Its current relevance is narrower: it explains the later Spec Loop design intent, which is to force explicit consideration of decomposition and avoid default single-task plans for complex work unless a plain task is justified.

Evidence:

- `spec-loop-base-backlog-prompted` required separate backlog tasks from the initial prompt.
- `spec-loop-incremental` required subtask design after previous committed subtasks.
- Both had explicit automated-test evidence for 13 of 15 behavior categories, partial evidence for 1 category, and no evidence for 1 category.
- The steered backlog run also reached the top tier after the user corrected the planning route to a backlog.

Conclusion: decomposition helped when it created reviewable implementation contracts, not just more documentation.

### User intervention materially changed some outcomes

`spec-loop-base-backlog-steered` should not be read as evidence of the workflow alone. The user corrected the planning route and challenged rollback handling. Those actions improved the final artifact.

This matters because it changes the conclusion from:

> Spec Loop automatically wins.

To:

> Spec Loop gave an experienced user the best leverage points for steering design quality.

That is a narrower and more useful conclusion.

### Long plans did not guarantee stronger final artifacts

Superpowers produced detailed plans and strong conversations. The final artifacts still had weaker behavior evidence than the top Spec Loop runs. GSD Small Feature in GSD Pi produced a shorter plan and a working app, but the durable tests and committed rationale were also thinner.

Conclusion: implementation plans are useful before execution, but they are not proof that the final code preserved the plan. For auditability, the final source/tests and committed, updated task/workflow state mattered more.

### Object-calisthenics constraints had mixed value

The constraint set improved vocabulary and boundary awareness in some artifacts. It did not reliably improve maintainability.

Observed costs included large files, more indirection, import cycles, and weak preservation of constraints in final production code.

Conclusion: domain-language constraints are useful only if paired with simplicity/change-locality review and enforceable tests.

## Ranking used in this study

This ranking is artifact-oriented. It is not a universal tool ranking.

1. Shared top tier: `spec-loop-base-backlog-steered` and `spec-loop-base-backlog-prompted`.  
   Stronger final artifact as built: `spec-loop-base-backlog-steered`. Cleaner evidence for the backlog-prompt condition: `spec-loop-base-backlog-prompted`.
2. `spec-loop-incremental`.
3. `spec-loop-calisthenics-incremental`.
4. `spec-loop-calisthenics`.
5. `spec-loop-calisthenics-single-task`.
6. `superpowers`.
7. `open-spec`.
8. `open-spec-calisthenics`.
9. `superpowers-calisthenics`.
10. `superpowers-5.4`.
11. `gsd-small-feature`.

This ranking gives more weight to behavior evidence, implementation safety, decision visibility, and design reviewability than to compactness or conversation quality alone.

A design-quality-first ranking differs slightly: `open-spec-calisthenics` rises because its domain language and boundaries are strong. That does not override the broader artifact ranking because behavior evidence and storage-write-failure tests still matter.

## What not to conclude

Do not conclude that one framework is universally best.

Supported conclusions are narrower:

- OpenSpec was strongest for compact specification review.
- Spec Loop was strongest for experienced-user design control and implementation contracts.
- Superpowers was strongest for collaborative clarification and detailed implementation plans.
- Explicit decomposition improved the Spec Loop results.
- Object-calisthenics constraints were not sufficient to improve final design quality.
- Static metrics were useful but not decisive.

## Reproduction notes

Use the solution repositories in the GitLab subgroup [skill-assessment/bank-kata](https://gitlab.com/skill-assessment/bank-kata). Each completed comparison repository contains `main` and the analysis tag. The later-added `gsd-small-feature` repository also has its GSD workflow artifacts committed under `.gsd/workflows`.

Use tag:

```text
analysis-2026-06-30
```

Standard verification commands in each solution repository:

```sh
npm test
npm run build
```

The behavior and design conclusions require reading source, tests, generated design artifacts, and relevant session communication. They are not produced solely by running commands.
