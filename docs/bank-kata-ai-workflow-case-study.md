# Comparing AI-Assisted Software Workflows on the Bank Kata

## An exploratory artifact study of OpenSpec, Spec Loop, Superpowers, and GSD

### Abstract

AI coding workflows differ in when they ask questions, what they write before code, how they divide work, and what remains reviewable in Git. This exploratory mixed-method study compares 12 completed implementations of the same browser Bank Kata across OpenSpec, Spec Loop, Superpowers, and GSD Small Feature. The evidence comprises generated planning artifacts, visible user–assistant messages, tagged source and tests, fresh test/build runs, 14 common behavior-evidence categories, a conditional source-constraint category, static code metrics, reviewer-assigned source/test design scores, and recorded token use.

The strongest results came from two Spec Loop backlog conditions: both had 13 full and 1 partial behavior checks among 14 applicable categories, and they preserved task-specific design and test specifications before implementation. A Spec Loop incremental-subtask condition produced the same behavior-check totals with a less granular Git record. OpenSpec produced much more compact specification sets and, after a matched non-calisthenics rerun, strong implementation evidence with few user interruptions. Superpowers surfaced many product choices but generated the longest plans. GSD Small Feature produced a working app and a compact workflow record, but fewer committed tests and less committed decision analysis. Domain-language/object-calisthenics constraints improved vocabulary and boundary visibility in some runs but did not produce a consistent net quality improvement.

These observations do not establish that one workflow is generally superior. The sample contains one artifact per condition, prompt and interaction differences, post-hoc scoring, and an author who maintains one of the compared workflows. The results support narrower claims about the artifacts and sessions studied.

**Keywords:** AI-assisted software development, specification-driven development, software design, code review, Bank Kata, object calisthenics

The [technical appendix](bank-kata-ai-workflow-case-study-appendix.md) contains the full condition matrix, behavior matrix, scoring anchors, per-solution evidence, metrics, token accounting, and protocol deviations.

## 1. Introduction

Agentic coding workflows do more than generate code. They structure the conversation, decide when implementation may start, create different review artifacts, and direct attention toward different engineering risks. Comparing only the final source therefore misses part of their effect; comparing only their documentation misses whether the resulting software preserved the documented intent.

This study examines both sides using a small, recognizable task: a browser implementation of the [Bank Kata](https://github.com/sandromancuso/Bank-kata). Twelve completed solutions were produced with four workflow families and several prompt or decomposition conditions. The study is exploratory rather than a controlled benchmark: the runs were not replicated, prompts were not identical, and user interaction varied. Its purpose is to identify observable patterns, expose trade-offs, and define claims that the collected evidence can support.

The research questions are:

1. **RQ1 — Pre-code reviewability:** What artifacts did each workflow make available for review before implementation, and what did Git preserve afterward?
2. **RQ2 — Decision visibility:** Which material product and design decisions were visible to the user rather than silently selected by the assistant?
3. **RQ3 — Resulting evidence and design:** How much required-behavior evidence did the tagged tests or recorded checks provide, and how did the resulting source/tests score against an explicit six-part design rubric?
4. **RQ4 — Decomposition and calisthenics:** What patterns were associated with backlog/incremental decomposition and with domain-language/object-calisthenics constraints?
5. **RQ5 — Interaction and cost trade-offs:** What relationships appeared among document size, interaction shape, token consumption, and resulting evidence?

The contribution is an evidence-linked comparison of workflow outputs, not a general causal claim about the frameworks.

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

The matched conditions used TypeScript/Vite and fixed Daily and Savings accounts. Five calisthenics conditions additionally required bank-domain language, a domain boundary, and the listed object-calisthenics source constraints. Source-constraint verification is therefore applicable only to those five conditions.

### 2.2 Compared workflows

- [OpenSpec](https://github.com/Fission-AI/OpenSpec) organizes a change into proposal, design, capability specifications, and implementation tasks before an apply step.
- [Spec Loop](https://github.com/dpolivaev/spec-loop) supports task files, subtasks, or multiple backlog tasks with scope, analysis, design, test specification, and execution approval.
- [Superpowers](https://github.com/obra/superpowers) uses brainstorming, design approval, a detailed implementation plan, and test-driven execution skills.
- [GSD](https://opengsd.net) provides several execution paths. The completed GSD solution studied here used the Small Feature path through GSD Pi.

These descriptions explain workflow mechanics; they are not treated as outcome evidence.

## 3. Method

### 3.1 Study design and corpus

The unit of analysis is one completed, tagged solution repository plus its retained generation session. The primary corpus contains 12 solutions: two OpenSpec, six Spec Loop, three Superpowers, and one GSD Small Feature implementation. All primary solutions include persistence. The non-calisthenics OpenSpec control was regenerated on 15 July 2026 after an audit found that the original base-prompt run had silently excluded persistence. The original run remains public as an excluded pilot; Section 6 and the appendix discuss this post-hoc correction.

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

Tool calls and hidden reasoning were excluded from decision-visibility analysis. Raw session files are not published; this limits independent reproduction of the interaction findings. The solution artifacts and evaluated revisions are public.

### 3.3 Behavior-evidence classification

Fourteen categories apply to every primary solution: money validation; deposit; withdrawal; insufficient-funds safety; transfer success; rejected-transfer no-change behavior; statement date/amount/balance; type filters; date filters; print behavior; UI/browser flow; persistence restore; invalid-storage fallback/validation; and storage-write-failure safety. A fifteenth category, automated source-constraint checks, applies only to calisthenics conditions.

Each applicable category was classified as:

- **full:** direct automated evidence, or a sufficiently specific retained verification record, covers the expected behavior;
- **partial:** only part of the behavior or a weaker proxy is checked; or
- **missing:** no adequate evidence was found.

`N/A` is not counted as missing. The classification measures evidence, not proof of correctness.

### 3.4 Source/test design score

The reviewer assigned 0–3 points independently for:

1. naming and domain language;
2. simplicity (KISS);
3. single responsibility (SRP);
4. dependency direction;
5. change locality; and
6. testability.

The maximum score is 18. The rubric was formalized after initial solution review rather than preregistered, and the implementation agents did not receive it. The matched OpenSpec rerun was scored later using the same anchors. The component table is reported so readers need not trust the total alone; full anchors and evidence appear in the appendix.

### 3.5 Synthesis and ranking

Observations by research question are primary. A secondary overall ranking was produced using an explicit qualitative procedure:

1. compare applicable behavior categories, with greater weight on money, rollback, persistence, print, and browser-flow safety;
2. use the six-component design score for close results, without letting it conceal missing safety evidence;
3. consider whether important decisions and verification expectations were reviewable before or during coding; and
4. treat document size, static metrics, test count, and token use as supporting rather than decisive evidence.

The ranking is intentionally not a sum of unrelated columns.

## 4. Results

### 4.1 RQ1 — Pre-code reviewability and Git reconstruction

| Workflow | Generated artifact shape | Observed review trade-off |
|---|---|---|
| OpenSpec | 310–311 lines across proposal, design, capability specs, and tasks | Compact complete change sets; the assistant asked no product or design questions. |
| Spec Loop | 466–1598 lines in one task, subtasks, or separate backlog task files | Most explicit task-level analysis, design, and test expectations; greater review volume. |
| Superpowers | 1988–2282 lines across design documents and implementation plans | Detailed execution instructions, but 32–90 fenced code/config/command blocks made plans long and implementation-like. |
| GSD Small Feature | 278 lines across context, plan, state, and summary | Compact workflow record, but part of the record was post-implementation and contained less committed decision analysis. |

Spec Loop was not unique in preserving rationale. OpenSpec designs recorded decisions, alternatives, and risks in a much smaller space. The distinctive Spec Loop result was the placement of research, final decisions, design, and behavior-specific test expectations inside the task or current subtask that governed execution. The two backlog solutions preserved separate review units for deposits/withdrawals, transfer, filtering, and printing.

Superpowers preserved the most detailed implementation order, including expected test code, configuration, CSS, and shell commands. This made implementation intent reconstructable but reduced the distinction between specification and generated implementation draft.

### 4.2 RQ2 — Decision visibility

The sessions showed four different interaction patterns:

- **OpenSpec:** both primary runs proceeded with proposal/apply approvals and asked no product or design questions. The matched control had five user messages: proposal, apply, manual-check confirmation, commit, and staging approval. Important choices were visible in generated artifacts, but not negotiated in chat.
- **Spec Loop:** decisions appeared in batches at task or subtask boundaries. In `spec-loop-base-backlog-steered`, the assistant proposed `localStorage`; the user redirected planning to a multi-task backlog and later challenged transfer and persistence-failure handling. The final task files record the resulting decisions.
- **Superpowers:** the assistant asked many product and architecture questions. The user explicitly chose persistence, accounts, filter behavior, layout, and other details. Those accepted decisions were strong session evidence, although later source/test inspection was still necessary to see what survived implementation.
- **GSD Small Feature:** scope and plan approvals exposed bundled defaults, but gray-area choices were not asked one at a time and some remained listed as proposed in committed context.

Question count alone did not determine reviewability. OpenSpec exposed choices through compact files; Superpowers exposed many through conversation; Spec Loop connected decisions to execution-governing task sections. The study found no evidence that long questioning alone guaranteed stronger final behavior checks.

### 4.3 RQ3 — Behavior evidence and resulting design

All 12 primary repositories passed their project tests and build at the evaluated revision. Test count is reported only as context. Both tables below are sorted by descending design score; ties retain corpus order.

| Solution | Tests | Full | Partial | Missing | Applicable | Design score |
|---|---:|---:|---:|---:|---:|---:|
| `spec-loop-base-backlog-steered` | 60/60 | 13 | 1 | 0 | 14 | 17/18 |
| `open-spec-calisthenics` | 12/12 | 13 | 1 | 1 | 15 | 15/18 |
| `open-spec` | 19/19 | 12 | 1 | 1 | 14 | 14/18 |
| `spec-loop-base-backlog-prompted` | 58/58 | 13 | 1 | 0 | 14 | 14/18 |
| `spec-loop-incremental` | 30/30 | 13 | 1 | 0 | 14 | 13/18 |
| `superpowers` | 17/17 | 11 | 1 | 2 | 14 | 12/18 |
| `spec-loop-calisthenics-incremental` | 16/16 | 10 | 3 | 2 | 15 | 11/18 |
| `superpowers-5.4` | 16/16 | 7 | 5 | 2 | 14 | 11/18 |
| `superpowers-calisthenics` | 13/13 | 8 | 3 | 4 | 15 | 11/18 |
| `gsd-small-feature` | 5/5 | 7 | 2 | 5 | 14 | 11/18 |
| `spec-loop-calisthenics` | 7/7 | 11 | 1 | 3 | 15 | 10/18 |
| `spec-loop-calisthenics-single-task` | 17/17 | 11 | 1 | 3 | 15 | 10/18 |

The design-score components were:

| Solution | Naming | KISS | SRP | Dependencies | Locality | Testability | Total |
|---|---:|---:|---:|---:|---:|---:|---:|
| `spec-loop-base-backlog-steered` | 3 | 2 | 3 | 3 | 3 | 3 | 17 |
| `open-spec-calisthenics` | 3 | 2 | 3 | 3 | 2 | 2 | 15 |
| `open-spec` | 2 | 2 | 2 | 3 | 2 | 3 | 14 |
| `spec-loop-base-backlog-prompted` | 2 | 2 | 2 | 3 | 2 | 3 | 14 |
| `spec-loop-incremental` | 2 | 2 | 2 | 2 | 2 | 3 | 13 |
| `superpowers` | 2 | 2 | 2 | 2 | 2 | 2 | 12 |
| `spec-loop-calisthenics-incremental` | 3 | 1 | 2 | 2 | 1 | 2 | 11 |
| `superpowers-5.4` | 2 | 2 | 2 | 2 | 1 | 2 | 11 |
| `superpowers-calisthenics` | 2 | 2 | 2 | 2 | 1 | 2 | 11 |
| `gsd-small-feature` | 2 | 2 | 2 | 2 | 1 | 2 | 11 |
| `spec-loop-calisthenics` | 3 | 1 | 1 | 2 | 1 | 2 | 10 |
| `spec-loop-calisthenics-single-task` | 3 | 1 | 2 | 1 | 1 | 2 | 10 |

The two backlog Spec Loop runs and `spec-loop-incremental` had no missing applicable category; their partial category was storage-write-failure safety. The matched OpenSpec control had full money, browser-flow, persistence-restore, and bad-storage evidence, partial print evidence, and missing storage-write-failure safety. Its application controller updates in-memory state before saving, so a failed write can leave an advanced state that becomes visible after a later render.

The strongest source/test design score, 17/18, belonged to `spec-loop-base-backlog-steered`. Its ports separated domain transitions, application commit ordering, storage, time, identifiers, printing, and UI. The result also reflects user intervention: the user requested backlog decomposition and challenged persistence-failure semantics. It is therefore not clean evidence for workflow defaults alone.

### 4.4 RQ4 — Decomposition and calisthenics

#### Decomposition

The clearest repeated pattern was vertical decomposition combined with a design and test specification for the current slice:

- the two backlog conditions and the non-calisthenics incremental condition each achieved 13 full and 1 partial check with no missing applicable category;
- the broad single-task calisthenics condition had more dependency cycles and weaker browser evidence than the incremental/backlog leaders; and
- documentation volume alone did not explain the result: some longer plans produced fewer full checks.

This is an association within a small, non-random sample. The first backlog result was user-steered; the second used an initial prompt that required backlog sequencing; and the Spec Loop skills had changed between some runs.

#### Domain-language/object-calisthenics constraints

The matched framework comparisons were mixed:

- **OpenSpec:** on the 14 common categories, both runs were 12 full / 1 partial / 1 missing. The calisthenics run added full source-constraint evidence and scored one design point higher, but money validation was partial; the non-calisthenics run had full money validation but only partial print evidence.
- **Spec Loop:** the closest incremental comparison favored the non-calisthenics condition on behavior evidence and simplicity. Calisthenics conditions improved domain vocabulary but sometimes produced very large files, many small objects, or import cycles.
- **Superpowers:** the non-calisthenics solution had more full common-behavior evidence and a slightly higher design score. The calisthenics intent was only partly preserved in final production source.

The revised evidence does **not** support the earlier formulation that calisthenics lowered quality within every framework. It supports a narrower conclusion: the constraint set reliably increased attention to vocabulary and boundaries, but its net effect depended on whether simplicity, change locality, and behavioral safety were preserved.

### 4.5 RQ5 — Interaction, document size, and token use

Recorded integrated token totals varied widely:

- OpenSpec: 4.18M for the matched control and 4.51M for the calisthenics run;
- Spec Loop: 7.74M–36.09M across six conditions;
- Superpowers: 16.65M–22.26M across three conditions; and
- GSD Small Feature: 6.58M.

The totals are dominated by cached input: long sessions repeatedly re-read an expanding context. They measure interaction and context-processing volume, not mostly new prompt text. Cross-harness cost figures also depend on recorded usage semantics and model prices, so they are supporting evidence rather than a quality-normalized efficiency measure.

Within comparable conditions, more checkpoints generally meant more repeated context. The incremental Spec Loop calisthenics run used more tokens than the up-front/single-task Spec Loop calisthenics runs. However, the extra checkpoints also allowed later design decisions to use evidence from earlier implemented slices. The data therefore show a review/cost trade-off, not that either up-front or incremental work is universally more efficient.

## 5. Secondary overall ranking

The ranking applies only the synthesis procedure in Section 3.5. Shared ranks indicate that the evidence did not justify a forced ordering.

| Rank | Solution | Main reason |
|---:|---|---|
| 1 | `spec-loop-base-backlog-steered`; `spec-loop-base-backlog-prompted` | 13 full, 1 partial, no missing applicable checks; detailed task-level design/test records. The steered run has the strongest design score, while the prompted run is cleaner evidence for the backlog condition. |
| 2 | `spec-loop-incremental` | Same behavior totals as rank 1 with a compact one-task/subtask record, but coarser source layering and less granular Git review units. |
| 3 | `open-spec`; `open-spec-calisthenics` | Strong common behavior evidence and compact specification sets. The base run has stronger money evidence; the calisthenics run has stronger vocabulary, boundaries, print, and source-constraint evidence. |
| 4 | `superpowers` | Strong accepted decision trace and acceptable source design, but missing print and storage-write-failure evidence and only partial UI coverage. |
| 5 | `spec-loop-calisthenics-incremental` | Fewer missing categories than the other Spec Loop calisthenics runs and partial save-failure evidence, but weaker money/print evidence and over-decomposition. |
| 6 | `spec-loop-calisthenics` | Broad core behavior and clear vocabulary, but missing storage validation, save-failure, and source-constraint evidence; two very large production files. |
| 7 | `spec-loop-calisthenics-single-task` | Full source-constraint evidence and broad core behavior, but missing money, storage-validation, and save-failure evidence plus many import cycles. |
| 8 | `superpowers-calisthenics` | Complete feature intent and visible correction behavior, but weaker money, print, UI, persistence, and constraint evidence. |
| 9 | `superpowers-5.4` | Readable React structure, but weaker money, withdrawal, print, and save-failure evidence. |
| 10 | `gsd-small-feature` | Working app and compact workflow record, but only five committed tests and less committed evidence for withdrawal, persistence robustness, printing, and UI regression. |

Small changes to the weighting of money safety, design language, or reviewability can change adjacent positions. The rank is a summary of the stated criteria, not an interval-scale measurement.

## 6. Limitations

### Measurement limitations

Behavior categories measure evidence in tests or retained checks, not complete correctness. Test count is not a quality measure. The six design scores are reviewer judgments, even with explicit anchors. UI visual quality, accessibility, real banking semantics, and long-term maintainability were not evaluated.

### Comparability limitations

Conditions differed in prompts, user intervention, workflow versions, harnesses, and one model setting. There was one run per condition, so stochastic model variation cannot be separated from workflow effects. Several Spec Loop runs used explicit decomposition prompts, and one was materially steered by the user.

The matched OpenSpec control was generated after the original results had been inspected. It corrects a real scope mismatch—persistence had been silently excluded—but introduces a post-hoc replacement risk and a later execution date. The original pilot is preserved publicly, the replacement prompt is reported verbatim, and the replacement is used only as the primary matched control.

### Researcher and evaluator bias

The author created and maintains Spec Loop, designed the conditions, approved many assistant decisions, and challenged some interpretations. The artifact/design audit was performed by an AI reviewer without using Spec Loop instructions, but the criteria were refined during the evaluation sessions and were not preregistered. The author’s involvement and the reviewer’s single-model judgments can favor familiar concepts or post-hoc explanations.

### Generalizability limitations

The task is a small TypeScript browser kata. Results may not transfer to legacy systems, teams, other languages, regulated software, or longer projects. The workflows also evolve; these artifacts represent the recorded versions and sessions, not permanent framework characteristics.

### Reproducibility

Tagged repositories, prompts, commit identifiers, scoring anchors, and derived matrices are reported. Raw private session JSONL files are not published, so independent readers cannot fully reproduce token accounting or decision-message extraction. The shared tag name resembles a date but is intentionally a stable cross-repository snapshot label.

## 7. Discussion

The study suggests that workflow value is multi-dimensional:

- OpenSpec offered the best compactness-to-evidence trade-off in the matched pair, but it did not actively negotiate product choices.
- Spec Loop’s strongest advantage was not document volume; it was execution-governing design and test expectations attached to backlog items or current subtasks.
- Superpowers made alternatives visible through conversation, but its very long implementation plans did not guarantee more complete final evidence.
- GSD Small Feature completed the kata with a compact operational record, while the excluded standard GSD attempt showed that a heavier path could be disproportionate for this task.

For practitioners, the choice depends on the desired review surface. A developer wanting a concise change proposal may prefer OpenSpec. A developer wanting explicit task-level design alignment and a Git record of decisions may prefer Spec Loop. A developer wanting extended interactive design exploration may prefer Superpowers. This study provides no basis for selecting one workflow without considering those preferences.

The most actionable cross-workflow finding is that specifications and plans should be checked against final source/tests. Reviewable intent helped, but behavior-specific evidence and safe state/persistence boundaries still determined many rank differences.

## 8. Conclusion

Across these 12 Bank Kata implementations, the best-supported pattern was vertical decomposition with reviewable design and test expectations for the current unit of work. The two Spec Loop backlog solutions produced the strongest combined evidence under the study criteria, while OpenSpec produced much more compact specification sets and a strong matched pair. Superpowers provided the richest product discussion but the longest plans. GSD Small Feature produced a working compact result with thinner committed verification.

Domain-language/object-calisthenics constraints improved vocabulary and boundary attention, but the matched evidence was mixed and did not show a consistent overall quality gain or loss. The study therefore supports an artifact-level conclusion, not a universal framework ranking: workflow structure changed what was visible, reviewable, and tested, and those effects were most useful when final implementation evidence remained part of the evaluation.

## Data availability and disclosure

The evaluated repositories are linked in the [technical appendix](bank-kata-ai-workflow-case-study-appendix.md). Use tag `analysis-2026-06-30`; the appendix also gives exact commit identifiers. The original excluded OpenSpec pilot is preserved at commit `81ce8ab5a1b92c82a81fc05b13c48e9171f59bee` on branch `pilot/base-prompt`.

The author is the creator and maintainer of Spec Loop. No claim in this paper should be read as an independent product endorsement.

## References

1. Sandro Mancuso. [Bank Kata](https://github.com/sandromancuso/Bank-kata).
2. Fission AI. [OpenSpec](https://github.com/Fission-AI/OpenSpec).
3. Dimitry Polivaev. [Spec Loop](https://github.com/dpolivaev/spec-loop).
4. Jesse Vincent. [Superpowers](https://github.com/obra/superpowers).
5. GSD. [Get Shit Done](https://opengsd.net).
6. Per Runeson and Martin Höst. [Guidelines for conducting and reporting case study research in software engineering](https://doi.org/10.1007/s10664-008-9102-8). *Empirical Software Engineering* 14, 131–164 (2009).
