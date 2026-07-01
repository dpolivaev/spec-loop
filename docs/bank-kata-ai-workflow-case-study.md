# Bank Kata AI Workflow Case Study

This document compares eleven AI-assisted implementations of the same browser bank kata, based on Sandro Mancuso's [Bank Kata](https://github.com/sandromancuso/Bank-kata). It is an observational comparison: the runs shared the same core exercise, but prompt details and interaction patterns differed, so those differences are treated as context when interpreting outcomes.

The main outcome is a framework-fit conclusion: different workflows served different working styles and control points. The best fit depends on whether the goal is compact specification review, design control before implementation, a detailed implementation script, or low-interaction momentum.

## Main outcome: observed workflow fit

| Workflow goal | Best observed fit | Evidence from the bank-kata runs | Caveat |
|---|---|---|---|
| Compact capability/specification review | [OpenSpec](https://github.com/Fission-AI/OpenSpec) | Produced the shortest generated design artifacts: `open-spec` 5 files / 214 lines; `open-spec-calisthenics` 6 files / 310 lines. The proposal/design/spec split was easy to scan. | Weaker at exposing detailed data, UI, and failure-behavior decisions before implementation. |
| Design-level control before code | [Spec Loop](https://github.com/dpolivaev/spec-loop) | The strongest code/test outcomes came from Spec Loop runs with backlog or incremental decomposition: `spec-loop-base-backlog-steered`, `spec-loop-base-backlog-prompted`, and `spec-loop-incremental` each had explicit automated-test evidence for 13 of 15 behavior categories, partial evidence for 1 category, and no evidence for 1 category. Design scores were 17/18, 14/18, and 13/18. | More ceremony. It requires the user to review task files and enforce planning checkpoints. |
| Detailed implementation recipe | [Superpowers](https://github.com/obra/superpowers) | Produced long, concrete specs/plans and surfaced many product choices in conversation. Useful when the goal is a step-by-step implementation path. | Plans were long and weaker as final audit artifacts. Final code/test evidence was below the strongest Spec Loop runs. |
| Low-interaction, fast momentum | [OpenSpec](https://github.com/Fission-AI/OpenSpec) | Fewest visible clarification gates and shortest artifacts. | The assistant makes more unreviewed choices. |

Practical conclusion: in this case study, Spec Loop fit experienced-user design control before code; OpenSpec fit compact scope/specification review and low-interaction momentum; Superpowers fit collaborative planning and detailed execution recipes. Object-calisthenics/domain-language constraints are discussed separately because their effect on final design quality is a different question from workflow fit.

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

## Runs, prompts, and steering

Solution repositories are public in the GitLab subgroup [skill-assessment/bank-kata](https://gitlab.com/skill-assessment/bank-kata). Each repository contains `main` and tag `analysis-2026-06-30` at the listed commit. The `Solution` column links to the repository and uses the repository path name.

Initial prompt and in-session steering are grouped together because both affect attribution. A run improved by user steering is still a real artifact, but that improvement cannot be credited to the workflow alone.

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

## How conclusions were derived

The review used this chain of evidence:

1. **Prompt condition** — what the run was asked to do.
2. **Generated design/planning artifacts** — what the workflow made reviewable before or during implementation.
3. **Session communication** — which material choices were surfaced to the user and accepted before coding.
4. **Source code structure** — whether the final implementation preserved clean boundaries and localized change.
5. **Tests** — which required behaviors and failure modes were actually asserted.
6. **Static metrics** — supporting diagnostics for file size, complexity, duplication, dependency cycles, and risky patterns.

The strongest conclusions require agreement between several evidence types. Example: a good plan without tests is not enough; many tests without reviewable design is also not enough.

## Specification and planning artifact outcomes

| Workflow/run type | Generated design artifacts | What this meant in review |
|---|---:|---|
| OpenSpec base | 5 files / 214 lines | Fast to review; good for capability-level scope. |
| OpenSpec calisthenics | 6 files / 310 lines | Still concise; added architecture/boundary intent. |
| Spec Loop backlog/incremental | 1–5 task files / 862–1598 lines for the strongest non-calisthenics runs | Heavier, but exposed design, scenarios, and test expectations as implementation contracts. |
| Spec Loop calisthenics variants | 1 task file / 460–1052 lines | Reviewable intent, but final code quality depended heavily on decomposition discipline. |
| Superpowers | 2 generated docs / about 1988–2282 lines | Very detailed planning; harder to use as a concise review artifact. |

Observed effect: the best artifacts were not the shortest or longest. The best artifacts were the ones where the planning document constrained and explained the implementation slice being built.

## Up-front design, token use, and focus

Runs without sequential design gates concentrated design before implementation. That was visible across workflows: OpenSpec created proposal/design/spec artifacts before applying the change; Superpowers created a design and implementation plan before execution; Spec Loop single-task or whole-task runs designed the broad task before implementation.

Token data supports a narrower cost observation, not a universal cross-framework law. The integrated totals are dominated by cached input: previously seen context re-read by the model. There was no session compaction, so longer incremental sessions repeatedly read a growing context, mostly from cache. Treat the totals as interaction and cached-context-read volume, not as mostly fresh input.

| Solution(s) and condition | Integrated token totals from `comparison.md` | Supported interpretation |
|---|---:|---|
| `open-spec`, `open-spec-calisthenics` — OpenSpec single-change plans | 2.18M and 4.51M | Cheapest overall; concise up-front artifacts. |
| `spec-loop-calisthenics`, `spec-loop-calisthenics-single-task` — up-front/single-task Spec Loop calisthenics | 7.74M and 10.39M | Cheaper than the matched calisthenics incremental run. |
| `spec-loop-calisthenics-incremental` — incremental Spec Loop calisthenics | 18.29M | More expensive, with more focused subtask-by-subtask decisions. |
| `spec-loop-base-backlog-steered`, `spec-loop-incremental`, `spec-loop-base-backlog-prompted` — non-calisthenics Spec Loop backlog/incremental work | 19.69M, 20.22M, and 36.09M | More review boundaries; token volume depended heavily on implementation loops and repeated cached-context reads, not only planning form. |
| `superpowers`, `superpowers-5.4`, `superpowers-calisthenics` — Superpowers single up-front plans | 16.65M, 18.19M, and 22.26M | Single up-front plans were detailed, but not always cheaper than Spec Loop incremental runs. |

The supported conclusion is: single-task or up-front design tends to reduce repeated design/context reads inside comparable conditions, especially within the Spec Loop calisthenics runs, but it is less focused on the details of each implementation slice. Incremental design consumes more repeated context because it has more design/implementation checkpoints, but it exposes decisions later, when previous implementation evidence exists.

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

Conclusion: useful communication was not the number of questions. It was whether the right material decisions were visible at the point where they could still change the design.

## Code and test outcomes

All projects passed their own tests and build at tag `analysis-2026-06-30`. Behavior evidence is shown as full / partial / missing across 15 behavior categories.

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

Behavior evidence counted test assertions over the relevant behavior categories: money validation, deposit, withdrawal, insufficient-funds safety, transfer success, rejected-transfer no-change behavior, statement date/amount/balance, type filters, date filters, print behavior, UI/browser flow, restoring data from localStorage, fallback/validation for invalid stored data, storage-write-failure safety, and source-code constraint checks. Storage-write-failure safety means a failed save does not advance visible, in-memory, or persisted state.

Design score used a 0–3 rubric for naming/domain language, simplicity (KISS), single responsibility (SRP), dependency direction, change locality, and testability.

## Static metric outcomes

Static metrics did not choose the winner. They explained specific risks.

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

Superpowers produced detailed plans and strong conversations. The final artifacts still had weaker behavior evidence than the top Spec Loop runs.

Conclusion: implementation plans are useful before execution, but they are not proof that the final code preserved the plan. For auditability, the final source/tests and updated task state mattered more.

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

Use the solution repositories in the GitLab subgroup [skill-assessment/bank-kata](https://gitlab.com/skill-assessment/bank-kata). Each repository contains `main` and the analysis tag.

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
