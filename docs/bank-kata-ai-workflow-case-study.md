# Comparing AI-Assisted Software Workflows on the Bank Kata

## An exploratory artifact study of OpenSpec, Spec Loop, Superpowers, and GSD

### Abstract

AI coding workflows differ in when they ask questions, what they make reviewable before code, and what decision history remains after implementation. This exploratory mixed-method study compares 12 completed implementations of the same browser Bank Kata across OpenSpec, Spec Loop, Superpowers, and GSD Small Feature. It treats interactive steering, pre-execution reviewability, and durable decision traceability as co-primary workflow-process outcomes alongside implementation evidence. The evidence comprises generated planning artifacts, visible user–assistant messages, tagged source and tests, fresh test/build runs, 14 common behavior-evidence categories, a separate ten-rule calisthenics-compliance audit, static code metrics, reviewer-assigned source/test design scores, and recorded token use.

No workflow led every process dimension. Superpowers produced the strongest conversational elicitation of product and design choices. OpenSpec produced small, structured pre-execution artifact sets with explicit decisions, alternatives, risks, scenarios, and completed tasks, but asked no product or design questions. Spec Loop produced the deepest task-level design/test review and the most detailed durable execution trace among the studied runs. Its larger total artifact volume was divided across tasks, subtasks, and named sections rather than presented as one review unit. The strongest combined process-and-implementation results among the non-calisthenics conditions came from two Spec Loop backlog runs: both had 13 full and 1 partial behavior checks among 14 categories. A Spec Loop incremental-subtask condition produced the same behavior-check totals with a less granular Git record. GSD Small Feature produced a working app and a 278-line workflow record, part of it post-implementation, but fewer committed tests and less committed decision analysis. The calisthenics group had lower conventional design scores overall, especially for simplicity and locality, but compliance was incomplete in every run. To avoid rewarding noncompliance or penalizing faithful constraint application, those five runs are ranked only by strict instruction following, not by the conventional design rubric or the secondary overall synthesis.

These observations do not establish that one workflow is generally superior. The sample contains one artifact per condition, prompt and interaction differences, post-hoc scoring, and an author who maintains one of the compared workflows. The results support narrower claims about the artifacts and sessions studied.

**Keywords:** AI-assisted software development, specification-driven development, interactive steering, reviewability, decision traceability, software design, Bank Kata

The [technical appendix](bank-kata-ai-workflow-case-study-appendix.md) contains the full condition matrix, behavior matrix, scoring anchors, summarized evidence, metrics, token accounting, and protocol deviations. The [design-score audit supplement](bank-kata-ai-workflow-case-study-design-audit.md) contains the complete artifact evidence packets and reverse-order consistency pass.

## 1. Introduction

Agentic coding workflows do more than generate code. They structure the conversation, decide when implementation may start, create different review artifacts, and direct attention toward different engineering risks. Comparing only the final source therefore misses part of their effect; comparing only their documentation misses whether the resulting software preserved the documented intent.

This study examines both sides using a small, recognizable task: a browser implementation of the [Bank Kata](https://github.com/sandromancuso/Bank-kata). Twelve completed solutions were produced with four workflow families and several prompt or decomposition conditions. The study is exploratory rather than a controlled benchmark: the runs were not replicated, prompts were not identical, and user interaction varied. Its purpose is to identify observable patterns, expose trade-offs, and define claims that the collected evidence can support.

The research questions are:

1. **RQ1 — Interactive steering:** Which material product and design decisions were surfaced before the affected implementation, and where could the user accept, challenge, or redirect them?
2. **RQ2 — Pre-execution reviewability:** What scope, design, and verification expectations could be reviewed before the affected code was written, and how were those artifacts organized for review?
3. **RQ3 — Durable decision traceability:** What decision rationale, execution boundaries, and verification expectations remained reconstructable from the committed workflow artifacts?
4. **RQ4 — Resulting evidence and design:** How much required-behavior evidence did the tagged tests or recorded checks provide, and how did the non-calisthenics source/tests rank under an explicit six-part design rubric?
5. **RQ5 — Decomposition and calisthenics:** What patterns were associated with backlog/incremental decomposition, how faithfully did the calisthenics runs follow their source constraints, and what conventional design pressure was associated with that condition?
6. **RQ6 — Interaction and cost trade-offs:** What relationships appeared among document size, interaction shape, token consumption, and resulting evidence?

The contribution is an evidence-linked comparison of workflow-process qualities and resulting implementation evidence, not a general causal claim about the frameworks.

## 2. Background

### 2.1 Bank Kata scope

The Bank Kata is a software-craftsmanship exercise associated with Sandro Mancuso and Codurance. The original kata emphasizes deposits, withdrawals, and statement printing and also presents [Object Calisthenics](https://github.com/sandromancuso/Bank-kata#object-calisthenics) as a design constraint set.

The common browser-app scope in this study comprised:

- deposits and withdrawals;
- transfers with rejected-operation or rollback safety;
- account statements containing date, amount, and running balance;
- statement printing;
- filters for deposits, withdrawals, and date;
- browser `localStorage` persistence; and
- a user-visible browser flow.

The matched conditions used TypeScript/Vite and fixed Daily and Savings accounts. Five calisthenics conditions additionally required bank-domain language, a domain boundary, and the listed object-calisthenics source constraints. Their instruction following is evaluated separately from common functional behavior and conventional design ranking.

### 2.2 Compared workflows

- [OpenSpec](https://github.com/Fission-AI/OpenSpec) organizes a change into proposal, design, capability specifications, and implementation tasks before an apply step.
- [Spec Loop](https://github.com/dpolivaev/spec-loop) supports task files, subtasks, or multiple backlog tasks with scope, analysis, design, test specification, and execution approval.
- [Superpowers](https://github.com/obra/superpowers) uses brainstorming, design approval, a detailed implementation plan, and test-driven execution skills.
- [GSD](https://opengsd.net) provides several execution paths. The completed GSD solution studied here used the Small Feature path through GSD Pi.

These descriptions explain workflow mechanics; they are not treated as outcome evidence.

## 3. Method

### 3.1 Study design and corpus

The unit of analysis is one completed, tagged solution repository plus its retained generation session. The primary corpus contains 12 solutions: two OpenSpec, six Spec Loop, three Superpowers, and one GSD Small Feature implementation. All primary solutions include persistence. The non-calisthenics OpenSpec control was regenerated on 15 July 2026 after an audit found that the original base-prompt run had silently excluded persistence. The original run remains public as an excluded pilot. It is not used in implementation tables or ranking, but its question-free handling of a minimal prompt is retained as corroborating workflow-process evidence. Section 6 and the appendix discuss this post-hoc correction.

Most runs used GPT-5.5 with `xhigh` reasoning. `superpowers-5.4` used GPT-5.4 with `high` reasoning. The matched OpenSpec rerun also used GPT-5.5 `xhigh`; its retained session records that configuration.

Each primary repository exposes the evaluated state through the shared tag `analysis-2026-06-30`. The tag is a cross-repository snapshot label, not a claim that every run occurred on that date. Exact repository links and commit identifiers are in the appendix.

### 3.2 Evidence sources

The analysis used:

1. generated proposal, design, specification, task, plan, state, and summary files committed with each solution;
2. tagged production source and automated tests;
3. fresh project test and build runs;
4. static metrics from the same local analysis scripts;
5. user-visible assistant messages and user responses extracted from retained session JSONL files; and
6. session usage records for integrated token and cost accounting.

Tool calls and hidden reasoning were excluded from workflow-process analysis. Raw session files are not published; this limits independent reproduction of the interaction findings. The solution artifacts and evaluated revisions are public.

### 3.3 Workflow-process dimensions

The same three dimensions were applied to every solution:

- **interactive steering:** direct session evidence that material choices were surfaced before affected code, with an opportunity for the user to accept, challenge, or redirect them;
- **pre-execution reviewability:** the completeness, clarity, specificity, consistency, and navigability of the scope, design, alternatives, risks, and verification expectations available before affected implementation; and
- **durable decision traceability:** the extent to which committed artifacts preserve selected decisions, rationale, execution units, and verification expectations so that the development path can be reconstructed later.

Question count is contextual evidence, not the steering measure. A question-free run is limited on interactive steering even if its generated design is reviewable. Artifact size, file count, and fenced-block count describe review volume, not reviewability quality; they are reported separately. A forward-looking plan is not treated as proof of what was executed.

Each dimension was summarized as **strong**, **mixed**, or **limited** from direct session and artifact evidence. Strong means substantial evidence across the relevant run; mixed means meaningful evidence with a material gap or trade-off; limited means little direct evidence. These are comparative reviewer judgments, not numerical or permanent framework scores. Dimension-specific anchors and the full solution matrix are in the appendix.

### 3.4 Behavior-evidence classification

Fourteen categories apply to every primary solution: money validation; deposit; withdrawal; insufficient-funds safety; transfer success; rejected-transfer no-change behavior; statement date/amount/balance; type filters; date filters; print behavior; UI/browser flow; persistence restore; invalid-storage fallback/validation; and storage-write-failure safety.

Each applicable category was classified as:

- **full:** direct automated evidence, or a sufficiently specific retained verification record, covers the expected behavior;
- **partial:** only part of the behavior or a weaker proxy is checked; or
- **missing:** no adequate evidence was found.

The classification measures evidence, not proof of correctness. Automated source-constraint checks are reported as evidence in the separate instruction-following audit rather than counted as functional behavior.

### 3.5 Source/test design score

The reviewer assigned 0–3 points independently for:

1. naming and domain language;
2. simplicity (KISS);
3. single responsibility (SRP);
4. dependency direction;
5. change locality; and
6. testability.

The maximum score is 18. The rubric was formalized after initial solution review rather than preregistered, and the implementation agents did not receive it. A later artifact-only audit rescored all 12 repositories in a fixed name-masked order and then repeated the scoring in reverse order. The two one-point disagreements among 72 component judgments were reconciled against cited source evidence. The component table is reported so readers need not trust the total alone; full anchors, audit evidence, and consistency results appear in the appendix.

The score describes conventional source/test design. Object Calisthenics deliberately introduces design pressure that overlaps with simplicity and locality, and the five implementations differ in actual compliance. Their design scores are therefore retained as descriptive evidence but excluded from design ranking and tie-breaking.

### 3.6 Synthesis and ranking

Observations by research question are primary. The workflow-process profile and implementation profile are co-primary; neither is reduced to a numeric score. For the seven non-calisthenics solutions, a secondary overall ranking was produced using an explicit qualitative procedure:

1. compare interactive steering, pre-execution reviewability, and durable traceability, including material limitations in each;
2. compare applicable behavior categories, with greater weight on money, rollback, persistence, print, and browser-flow safety;
3. use the six-component design score to distinguish close implementation results without letting it conceal missing safety evidence; and
4. treat document size, static metrics, test count, and token use as supporting rather than decisive evidence.

No single process strength erases an implementation-safety gap, and strong source/tests do not erase the absence of user steering or a durable decision record. The ranking is intentionally not a sum of unrelated columns.

The five calisthenics solutions use a separate strict instruction-following rank. The audit applies the domain-language requirement and nine listed Object Calisthenics rules to production domain code, gives each rule one binary pass/fail result, and ranks by unweighted pass count. A solution-authored exception or incomplete verifier cannot weaken the original prompt. Equal pass counts remain tied; design scores and behavior evidence are not tie-breakers. These runs are excluded from the secondary overall ranking.

## 4. Results

### 4.1 RQ1 — Interactive steering

Interactive steering means that the assistant showed the user an important product, design, or planning choice before writing the affected code, and the user could accept or change it. An approval to continue was not counted as choice-level steering when the assistant had not shown the underlying choices.

- **OpenSpec asked no product or design questions.** In both primary runs, the user approved workflow steps such as proposal and apply, but the assistant selected the material choices. The matched control had five user messages: propose, apply, confirm manual checks, commit, and approve staging. The excluded original pilot behaved the same way even though its prompt was minimal; the assistant chose to exclude persistence without asking the user. OpenSpec therefore provided approval points, but little interactive steering.
- **Spec Loop requested user choice or approval for material decisions before requesting execution approval.** Depending on the run, the assistant used individual questions or bounded decision batches for the active task or subtask. In `spec-loop-base-backlog-steered`, the user changed the plan from one task to a multi-task backlog and later challenged the handling of transfer rollback and storage-write failure. The final task files recorded those changes. In `spec-loop-calisthenics-single-task`, the assistant presented six material choices with reasons in one pre-code decision batch and invited the user to confirm, question, or disagree. This meets the Strong steering anchor because each choice remained explicit and challengeable.
- **Superpowers asked the most individual product and design questions.** The user explicitly chose persistence, account structure, filter behavior, layout, rollback visibility, and other details. This gave the user the most opportunities to shape the planned product. One run still left an ambiguous stack answer unresolved and selected React without asking a follow-up question.
- **GSD Small Feature presented scope and plan recommendations for approval.** The user could accept or reject the proposed package of choices, but the assistant did not present every important choice separately. Some decisions also remained marked as proposed in the committed context.

Superpowers provided the strongest choice-by-choice steering. Spec Loop also provided strong steering and showed the clearest example of user feedback changing the plan and its recorded design. GSD provided broader approval of bundled choices. OpenSpec provided workflow approvals but no product- or design-question steering. The result is based on the content and timing of the interactions, not on question count alone.

### 4.2 RQ2 — Pre-execution reviewability

The table ranks the studied workflow families by pre-execution reviewability quality. The rank considers content and organization, not artifact volume.

| Rank | Workflow | Generated artifact structure | Evidence and boundary |
|---:|---|---|---|
| 1 | Spec Loop | One task with sections/subtasks or separate backlog task files | Deepest task-level analysis, final decisions, design, and behavior-specific test expectations. Tasks, current subtasks, and named sections let the user focus on the current decision boundary rather than review the complete corpus at every checkpoint. |
| 2 | OpenSpec | Proposal, design, capability specifications, and tasks | Explicit goals/non-goals, decisions with rationales and rejected alternatives, risks, scenario requirements, and implementation/test tasks. Less detailed as an execution-level design/test contract than the strongest Spec Loop artifacts. |
| 3 | Superpowers | Design document and detailed implementation plan | Strong approved design and behavior-specific implementation/test content in two runs, but specification and implementation draft were interleaved; one run retained an unresolved stack ambiguity. |
| 4 | GSD Small Feature | Context, plan, state, and summary | Useful compact planning record, but pre-code decision analysis was thinner, some decisions remained proposed, and part of the record was post-implementation. |

Artifact volume is reported descriptively and is not ranked. Low or high volume is not inherently good or bad. In particular, Spec Loop's 466–1598 total lines span different tasks, subtasks, and named sections, so the total does not equal the material reviewed at one checkpoint. OpenSpec used 310–311 lines; Superpowers used 1988–2282 lines; GSD used 278 lines, part of them post-implementation. No human review time or cognitive effort was measured.

This framework-level ranking summarizes the studied runs rather than permanent framework capability. The per-solution labels and evidence are in the appendix.

### 4.3 RQ3 — Durable decision traceability

The following workflow tables are descriptive comparisons, not rankings.

| Workflow | Evidence retained in Git | Observed traceability boundary |
|---|---|---|
| OpenSpec | Proposal, design, capability specifications, and completed tasks | Clear selected-design, rationale, risk, scenario, verification, and completion record; absence of user acceptance is assessed separately under steering. |
| Spec Loop | Execution-governing task or current-subtask files containing decisions, design, test expectations, and status | Strongest task-to-execution trace among the studied runs; committed records connected selected decisions, design, and verification expectations to implementation status and completion. |
| Superpowers | Design document and detailed implementation plan | Strong record of intended execution, but largely forward-looking and not a reconciled record of what ultimately changed. |
| GSD Small Feature | Context, plan, state, and summary | Compact reconstruction, but part was written after implementation and some decisions remained proposed rather than confirmed. |

The process-dimension summary is:

| Workflow | Interactive steering | Pre-execution reviewability | Durable traceability |
|---|---|---|---|
| OpenSpec | Limited | Strong | Strong |
| Spec Loop | Strong | Strong | Strong |
| Superpowers | Strong | Strong but variable | Mixed |
| GSD Small Feature | Mixed | Mixed | Mixed |

These labels summarize only the studied runs. Superpowers led conversational elicitation. OpenSpec combined strong pre-execution review content and traceability with low review volume, separately evidenced findings. Spec Loop produced the deepest review and the most detailed task/current-subtask execution trace. The full per-solution matrix and anchors are in the appendix.

### 4.4 RQ4 — Behavior evidence and resulting design

All 12 primary repositories passed their project tests and build at the evaluated revision. The raw test count is reported only as context. Common behavior evidence was classified in the same 14 categories for every run; the full matrix, including the five calisthenics runs, is in the appendix.

The two tables below rank the seven non-calisthenics solutions separately for behavior evidence and conventional design. The first table orders behavior profiles by Full categories descending and then Partial categories descending. Because all 14 categories apply, Missing is determined by those two counts. Equal profiles share a dense rank. `Tests passed/total` does not affect the rank.

| Rank | Solution | Tests passed/total | Full | Partial | Missing |
|---:|---|---:|---:|---:|---:|
| 1 | `spec-loop-base-backlog-prompted` | 58/58 | 13 | 1 | 0 |
| 1 | `spec-loop-base-backlog-steered` | 60/60 | 13 | 1 | 0 |
| 1 | `spec-loop-incremental` | 30/30 | 13 | 1 | 0 |
| 2 | `open-spec` | 19/19 | 12 | 1 | 1 |
| 3 | `superpowers` | 17/17 | 11 | 1 | 2 |
| 4 | `superpowers-5.4` | 16/16 | 7 | 5 | 2 |
| 5 | `gsd-small-feature` | 5/5 | 7 | 2 | 5 |

The second table uses dense ranks from the audited design total. Calisthenics design scores are reported descriptively in RQ5 but do not enter this rank.

| Rank | Solution | Naming | KISS | SRP | Dependencies | Locality | Testability | Total |
|---:|---|---:|---:|---:|---:|---:|---:|---:|
| 1 | `spec-loop-base-backlog-steered` | 3 | 2 | 3 | 3 | 3 | 3 | 17 |
| 2 | `spec-loop-base-backlog-prompted` | 2 | 2 | 2 | 3 | 3 | 3 | 15 |
| 3 | `open-spec` | 2 | 2 | 2 | 2 | 2 | 3 | 13 |
| 3 | `spec-loop-incremental` | 2 | 2 | 2 | 2 | 2 | 3 | 13 |
| 4 | `superpowers` | 2 | 2 | 2 | 2 | 2 | 2 | 12 |
| 4 | `superpowers-5.4` | 2 | 2 | 2 | 2 | 2 | 2 | 12 |
| 5 | `gsd-small-feature` | 2 | 2 | 1 | 1 | 2 | 1 | 9 |

The two backlog Spec Loop runs and `spec-loop-incremental` share behavior-evidence rank 1. They had no missing applicable category; their partial category was storage-write-failure safety. The matched OpenSpec control had full money, browser-flow, persistence-restore, and bad-storage evidence, partial print evidence, and missing storage-write-failure safety. Its application controller updates in-memory state before saving, so a failed write can leave an advanced state that becomes visible after a later render.

The strongest source/test design score, 17/18, belonged to `spec-loop-base-backlog-steered`. Its ports separated domain transitions, application commit ordering, storage, time, identifiers, printing, and UI. The result also reflects user intervention: the user requested backlog decomposition and challenged persistence-failure semantics. It is therefore not clean evidence for workflow defaults alone.

The audit changed 17 of 72 component judgments across nine artifacts relative to the earlier table. A reverse-order pass reproduced 70 of 72 judgments; both one-point disagreements were resolved by re-reading the cited source. This is same-evaluator stability evidence, not independent validation. The complete citations and reconciliations are in the [design-score audit supplement](bank-kata-ai-workflow-case-study-design-audit.md).

### 4.5 RQ5 — Decomposition and calisthenics

#### Decomposition

The clearest repeated pattern was vertical decomposition combined with
a design and test specification for the current task, or for the
current subtask when subtasks were used:

- the two backlog conditions and the non-calisthenics incremental condition each achieved 13 full and 1 partial check with no missing applicable category;
- the broad single-task calisthenics condition had more dependency cycles and weaker browser evidence than the incremental/backlog leaders; and
- documentation volume alone did not explain the result: some longer plans produced fewer full checks.

This is an association within a small, non-random sample. The first backlog result was user-steered; the second used an initial prompt that required backlog sequencing; and the Spec Loop skills had changed between some runs.

#### Domain-language/object-calisthenics constraints

The strict audit treated the original prompt plus explicit user clarifications as the authority. It applied the domain-language requirement and nine listed Object Calisthenics rules to production domain code. A solution-authored exception or incomplete verifier could not weaken a rule. Each rule received one binary result, and no design or behavior result was used to break ties.

| Rank | Solution | Rules passed | Failed requested constraints |
|---:|---|---:|---|
| 1 | `open-spec-calisthenics` | 8/10 | Wrapped primitives and strings; no getters/setters/properties |
| 1 | `spec-loop-calisthenics-single-task` | 8/10 | Domain-only concepts and names; wrapped primitives and strings |
| 2 | `spec-loop-calisthenics` | 7/10 | Domain-only concepts and names; wrapped primitives and strings; no getters/setters/properties |
| 2 | `spec-loop-calisthenics-incremental` | 7/10 | Wrapped primitives and strings; one dot per line; no getters/setters/properties |
| 2 | `superpowers-calisthenics` | 7/10 | Wrapped primitives and strings; one dot per line; no getters/setters/properties |

Every calisthenics repository passed its project tests, but no implementation fully followed the requested source constraints. In particular, all five exposed raw domain-valued strings or numbers through domain methods or recording ports. OpenSpec's verifier omitted indentation and semantic accessor checks; the single-task Spec Loop verifier permitted recorder-method primitive crossings. Passing those verifiers therefore did not establish full prompt compliance. The appendix gives the complete ten-rule matrix and source citations.

The conventional design scores are retained only to examine design pressure. The five calisthenics artifacts scored 14, 12, 10, 10, and 9, for a mean of 11.0 and median of 10. The seven non-calisthenics artifacts had mean and median 13.0. The constrained group lost most points in simplicity and change locality: implementations used recorder protocols, change/outcome/continuation chains, many small delegation objects, or large aggregate files to avoid ordinary accessors and primitive crossings.

This does not establish a causal effect. There is one run per condition, prompts and interaction differ, and compliance is incomplete. OpenSpec is also a direct counterexample to a framework-by-framework claim: its calisthenics artifact scored 14 versus 13 for its control, but failed wrapped-value and accessor rules, so it cannot estimate the effect of fully applying the constraint set. The defensible finding is narrower: the calisthenics group had lower conventional design scores overall, and the mechanisms used to pursue the constraints introduced visible simplicity and locality costs in several artifacts. For that reason, calisthenics solutions are ranked only for instruction following.

### 4.6 RQ6 — Interaction, document size, and token use

Recorded integrated token totals varied widely:

- OpenSpec: 4.18M for the matched control and 4.51M for the calisthenics run;
- Superpowers: 16.65M–22.26M across three conditions; and
- GSD Small Feature: 6.58M.

The six Spec Loop conditions separate into two checkpoint structures:

- Single task with up-front design: 7.74M for `spec-loop-calisthenics` and 10.39M for `spec-loop-calisthenics-single-task`. Both used one task artifact designed before execution; one had internal subtasks and one did not.
- Incremental subtasks or backlog tasks: 18.29M for `spec-loop-calisthenics-incremental`, 19.69M for `spec-loop-base-backlog-steered`, 20.22M for `spec-loop-incremental`, and 36.09M for `spec-loop-base-backlog-prompted`. These runs introduced later design or approval checkpoints through sequential subtasks or separate backlog task files.

The totals are dominated by cached input: long sessions repeatedly re-read an expanding context. They measure interaction and context-processing volume, not mostly new prompt text. Cross-harness cost figures also depend on recorded usage semantics and model prices, so they are supporting evidence rather than a quality-normalized efficiency measure.

Within these Spec Loop conditions, the two up-front single-task runs recorded the lowest totals. Every incremental-subtask or backlog run recorded a higher total, from 18.29M to 36.09M. This pattern is consistent with more checkpoints repeatedly processing an expanding context. The groups also differ in prompts, constraints, and user steering, so the comparison is descriptive rather than causal. Extra checkpoints allowed later design decisions to use evidence from earlier completed subtasks or backlog tasks; the data therefore show a review/cost trade-off, not that one planning form is universally more efficient.

## 5. Secondary overall synthesis and ranking

No workflow led every process dimension. This secondary ranking applies only to the seven non-calisthenics solutions, using the synthesis procedure in Section 3.6. The five calisthenics runs are excluded because their imposed source constraints overlap with the conventional design criteria and because their compliance differs. They have only the instruction-following rank reported in RQ5.

| Rank | Solution | Main reason |
|---:|---|---|
| 1 | `spec-loop-base-backlog-steered`; `spec-loop-base-backlog-prompted` | Strong steering, reviewability, and traceability plus 13 full, 1 partial, and no missing applicable checks. The steered run has the strongest design score; the prompted run is cleaner evidence for the backlog condition. |
| 2 | `spec-loop-incremental` | Strong steering at subtask boundaries and a durable current-subtask contract, with the same behavior totals as rank 1; the Git review units and source layering were less granular. |
| 3 | `open-spec` | Strong pre-code review content, durable selected-decision/task records, and strong implementation evidence, but no product/design questions and missing storage-write-failure evidence. |
| 4 | `superpowers` | Strongest conversational elicitation and direct domain flows, but mixed durable traceability, missing print evidence, and partial browser-flow evidence. |
| 5 | `superpowers-5.4` | Mixed steering and reviewability after an unresolved stack answer, with weaker money, withdrawal, statement, print, and save-failure evidence. |
| 6 | `gsd-small-feature` | Compact approvals and workflow record, but bundled steering, partly post-implementation traceability, only five committed tests, and five missing behavior categories. |

Small changes to the relative importance of interactive steering, traceability, or safety evidence can change adjacent positions. In particular, a steering-dominated synthesis could move Superpowers above OpenSpec. The rank is a summary of the stated criteria, not an interval-scale measurement.

## 6. Limitations

### Measurement limitations

Behavior categories measure evidence in tests or retained checks, not complete correctness. Test count is not a quality measure. The strong/mixed/limited workflow-process labels and the six design scores are reviewer judgments, even with explicit anchors. The calisthenics pass/fail audit also requires operational judgments about domain boundaries, primitive crossings, accessor-shaped methods, and indentation. A pass means that this audit found no material violation; it is not a formal proof. Artifact size measures review volume, not reviewability quality. UI visual quality, accessibility, real banking semantics, and long-term maintainability were not evaluated.

### Comparability limitations

Conditions differed in prompts, user intervention, workflow versions, harnesses, and one model setting. There was one run per condition, so stochastic model variation cannot be separated from workflow effects. Interactive steering evidence also depends on what the user chose to challenge. Several Spec Loop runs used explicit decomposition prompts, and one was materially steered by the user. Calisthenics compliance varied, so neither the group-score difference nor a matched pair isolates the effect of fully applying the constraint set.

The matched OpenSpec control was generated after the original results had been inspected. It corrects a real scope mismatch—persistence had been silently excluded—but introduces a post-hoc replacement risk and a later execution date. The original pilot is preserved publicly, the replacement prompt is reported verbatim, and the replacement is used only as the primary matched control.

### Researcher role and evaluator independence

The author created and maintains Spec Loop and selected the study conditions and prompts. During solution generation, the author completed approvals required by the workflows. Except for substantive interventions explicitly reported as steering, these approvals were procedural confirmations rather than author-selected implementation decisions. During evaluation and paper revision, the author supplied source facts, identified factual or interpretive problems, and requested explicit criteria and scores. The author did not assign scores, choose ranks, or direct the evaluator toward a preferred winner; the AI evaluator defined and applied the criteria and made the evaluative judgments.

These roles do not remove bias risk. The criteria were not preregistered. Steering, reviewability, and traceability were original evaluation concerns, but their separate categorical anchors were formalized during paper revision after the outcomes were known. Separating calisthenics instruction-following rank from conventional design rank was also a post-hoc correction after the compliance review. The remaining risks concern study framing, retrospective rubric design, and reliance on one AI evaluator rather than independent reviewers—not the mechanical approval steps themselves.

### Generalizability limitations

The task is a small TypeScript browser kata. Results may not transfer to legacy systems, teams, other languages, regulated software, or longer projects. The workflows also evolve; these artifacts represent the recorded versions and sessions, not permanent framework characteristics.

### Reproducibility

Tagged repositories, prompts, commit identifiers, scoring anchors, and derived matrices are reported. Raw private session JSONL files are not published, so independent readers cannot fully reproduce token accounting or decision-message extraction. The shared tag name resembles a date but is intentionally a stable cross-repository snapshot label.

## 7. Discussion

The study suggests that workflow value is multi-dimensional, and no framework led every process dimension:

- OpenSpec produced strong pre-execution review content and durable selected-decision/task records in small structured specification sets, plus strong matched implementations, but its interactive steering was limited.
- Spec Loop’s strongest advantage was not document volume; it was execution-governing design and test expectations in task or current-subtask files, which also created the strongest durable trace among the studied runs.
- Superpowers made the most product and design choices visible through conversation, but its implementation plans created the largest artifact surface and did not guarantee more complete final evidence.
- GSD Small Feature completed the kata with a compact operational record, while the excluded standard GSD attempt showed that a heavier path could be disproportionate for this task.

For practitioners, the choice depends on the desired intervention point. A developer wanting a small change proposal may prefer OpenSpec, but size alone does not establish reviewability. A developer wanting explicit task-level design alignment and a Git record of execution decisions may prefer Spec Loop. A developer wanting extended interactive design exploration may prefer Superpowers. This study provides no basis for selecting one workflow without considering those preferences.

The most actionable cross-workflow finding is that specifications and plans should be checked against final source/tests. Reviewable intent helped, but behavior-specific evidence and safe state/persistence boundaries still determined many rank differences.

The calisthenics audit sharpens that point. Automated source verifiers made some constraints visible, but passing a verifier did not mean that the original prompt had been followed. At the same time, conventional design criteria such as simplicity and locality can penalize the ceremony required by the constraint set. Reporting strict compliance separately from conventional design avoids treating either noncompliance or compliance costs as an unqualified quality advantage.

## 8. Conclusion

Across these 12 Bank Kata implementations, Superpowers provided the strongest interactive elicitation, Spec Loop provided the deepest pre-execution design/test review and the most detailed task/current-subtask trace, and OpenSpec combined strong reviewability and traceability with small structured specification sets. Artifact volume is a descriptive observation, not a quality result. The best-supported combined pattern was vertical decomposition with reviewable design and test expectations for the current unit of work. The two Spec Loop backlog solutions produced the strongest combined process-and-implementation evidence under the stated criteria; the non-calisthenics incremental solution followed closely. GSD Small Feature produced a working compact result with thinner committed verification.

The calisthenics group had lower conventional design scores overall, concentrated in simplicity and locality, but no run fully followed the requested constraints. OpenSpec's constrained run scored above its control while failing two central rules, so the study cannot claim that the constraints lowered design quality within every framework or estimate a full-treatment effect. The calisthenics runs are therefore ranked only by instruction following: OpenSpec and the Spec Loop single-task run share rank 1 at 8/10, and the other three share rank 2 at 7/10.

The study supports an artifact-level conclusion, not a universal framework ranking: workflow structure changed what was visible, reviewable, and tested, and those effects were most useful when final implementation evidence remained part of the evaluation.

## Data availability and disclosure

The evaluated repositories are linked in the [technical appendix](bank-kata-ai-workflow-case-study-appendix.md), and the complete design audit is published as a [separate supplement](bank-kata-ai-workflow-case-study-design-audit.md). Use tag `analysis-2026-06-30`; the appendix also gives exact commit identifiers. The original excluded OpenSpec pilot is preserved at commit `81ce8ab5a1b92c82a81fc05b13c48e9171f59bee` on branch `pilot/base-prompt`.

The author is the creator and maintainer of Spec Loop. No claim in this paper should be read as an independent product endorsement.

## References

1. Sandro Mancuso. [Bank Kata](https://github.com/sandromancuso/Bank-kata).
2. Fission AI. [OpenSpec](https://github.com/Fission-AI/OpenSpec).
3. Dimitry Polivaev. [Spec Loop](https://github.com/dpolivaev/spec-loop).
4. Jesse Vincent. [Superpowers](https://github.com/obra/superpowers).
5. GSD. [Get Shit Done](https://opengsd.net).
6. Per Runeson and Martin Höst. [Guidelines for conducting and reporting case study research in software engineering](https://doi.org/10.1007/s10664-008-9102-8). *Empirical Software Engineering* 14, 131–164 (2009).
