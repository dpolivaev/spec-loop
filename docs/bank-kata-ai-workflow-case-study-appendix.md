# Technical Appendix: Bank Kata AI Workflow Study

This appendix supports [Comparing AI-Assisted Software Workflows on the Bank Kata](bank-kata-ai-workflow-case-study.md). It records the corpus, prompts, revision boundaries, classification rules, full behavior matrix, design-score anchors and summarized evidence, static metrics, interaction evidence, token accounting, ranking procedure, exclusions, and reproduction limits. The separate [design-score audit supplement](bank-kata-ai-workflow-case-study-design-audit.md) publishes the complete per-artifact evidence packets and consistency pass.

## A. Corpus and revision boundaries

### A.1 Primary solutions

All primary repositories use the shared tag `analysis-2026-06-30`. The tag is a stable cross-repository snapshot label; it is not the generation date of every solution.

| Solution | Workflow / harness / model | Evaluated commit | Condition summary |
|---|---|---|---|
| [open-spec](https://gitlab.com/skill-assessment/bank-kata/open-spec) | OpenSpec / Pi / GPT-5.5 xhigh | `8c980c0feecc4cc0f35fc9f455fb3039d69549d3` | Matched non-calisthenics control: TypeScript/Vite, Daily/Savings, rollback, statements, print, filters, localStorage. |
| [open-spec-calisthenics](https://gitlab.com/skill-assessment/bank-kata/open-spec-calisthenics) | OpenSpec / Pi / GPT-5.5 xhigh | `1d71c713d61c63963078d5f6276fc24d0536ad37` | Expanded localStorage/Daily-Savings prompt plus domain-language/object-calisthenics constraints. TypeScript/Vite were selected in the artifacts but were not explicit in the retained initial arguments. |
| [spec-loop-base-backlog-steered](https://gitlab.com/skill-assessment/bank-kata/spec-loop-base-backlog-steered) | Spec Loop / Pi / GPT-5.5 xhigh | `ae1eb4bb896d3871daa2a825a022eac9d67e6a50` | Base prompt; assistant proposed localStorage; user redirected one task to a proper backlog and later challenged rollback/persistence failure handling. |
| [spec-loop-base-backlog-prompted](https://gitlab.com/skill-assessment/bank-kata/spec-loop-base-backlog-prompted) | Spec Loop / Pi / GPT-5.5 xhigh | `4b9f8aa9776ad0a5d864ed75de5944a1cdd84c47` | Expanded prompt requiring separate backlog tasks and design of each later task after the previous task was implemented and committed. |
| [spec-loop-incremental](https://gitlab.com/skill-assessment/bank-kata/spec-loop-incremental) | Spec Loop / Pi / GPT-5.5 xhigh | `4cd947ec1e427872c3599794c6aa6edf0d23d224` | Expanded prompt requiring sequential subtask design after the previous subtask was implemented and committed. |
| [spec-loop-calisthenics](https://gitlab.com/skill-assessment/bank-kata/spec-loop-calisthenics) | Spec Loop / Pi / GPT-5.5 xhigh | `319a8c9d4c24c8ff9c055b09fb508fbf02beb98f` | Expanded calisthenics prompt; subtask form selected during the session. |
| [spec-loop-calisthenics-incremental](https://gitlab.com/skill-assessment/bank-kata/spec-loop-calisthenics-incremental) | Spec Loop / Pi / GPT-5.5 xhigh | `d8948538ead401e651d5cc6da3aeea23e8ade543` | Expanded calisthenics prompt plus sequential subtask design-after-commit instruction. |
| [spec-loop-calisthenics-single-task](https://gitlab.com/skill-assessment/bank-kata/spec-loop-calisthenics-single-task) | Spec Loop / Pi / GPT-5.5 xhigh | `b708bd2c7d977a1839874325d8d457665192ca9d` | Expanded calisthenics scope retained as one broad task without tracked subtasks. |
| [superpowers](https://gitlab.com/skill-assessment/bank-kata/superpowers) | Superpowers / Codex / GPT-5.5 xhigh | `58bcb54d64785e1f3741b44bcc717bfbd3962e24` | Base prompt; user selected localStorage, Daily/Savings-style accounts, and plain TypeScript/Vite during clarification. |
| [superpowers-5.4](https://gitlab.com/skill-assessment/bank-kata/superpowers-5.4) | Superpowers / Codex / GPT-5.4 high | `5db5d24f5f275065d9c3c0824c9445bcc210aeb1` | Base prompt; user selected browser local storage and two accounts; assistant later chose React after an ambiguous stack answer. |
| [superpowers-calisthenics](https://gitlab.com/skill-assessment/bank-kata/superpowers-calisthenics) | Superpowers / Codex / GPT-5.5 xhigh | `65e2dbb20a9d4655bf87c4ee2ff325b65a2e5f98` | Expanded TypeScript/Vite/localStorage/Daily-Savings prompt plus calisthenics constraints. |
| [gsd-small-feature](https://gitlab.com/skill-assessment/bank-kata/gsd-small-feature) | GSD Small Feature / GSD Pi / GPT-5.5 xhigh | `aef38ffcae7bb4c4a07f0debd699a7f46b7b4634` | Expanded prompt executed through the Small Feature workflow; recommended scope/plan options accepted. |

At each evaluated local repository, `main` and `analysis-2026-06-30` resolved to the same commit and the working tree was clean when the revision audit was performed.

### A.2 OpenSpec control replacement

The original non-calisthenics OpenSpec run used the base prompt, did not discuss persistence in visible messages, and generated a design that explicitly excluded persistence. Its task file implemented UI state “without backend persistence,” and data were lost on refresh. The first evaluator later accepted the author’s statement that local storage had been chosen in every solution without rechecking this artifact. A subsequent artifact audit found the contradiction.

The correction was:

1. freeze the expanded non-calisthenics prompt;
2. regenerate OpenSpec using GPT-5.5 xhigh;
3. require TypeScript/Vite, Daily/Savings, rollback, statements, printing, filters, and browser local storage;
4. run the same test/build, behavior, source/test, artifact, message, and token checks; and
5. use the new result as the primary OpenSpec control.

The replacement occurred on 15 July 2026, after the earlier outcomes were known. It therefore improves scope comparability but is a post-hoc protocol correction, not a preregistered rerun.

The primary remote now points `main` and `analysis-2026-06-30` to replacement commit `8c980c0`. The original result is preserved on branch `pilot/base-prompt` at [commit `81ce8ab`](https://gitlab.com/skill-assessment/bank-kata/open-spec/-/tree/81ce8ab5a1b92c82a81fc05b13c48e9171f59bee). It is excluded from the primary tables and ranking.

### A.3 Other excluded attempts

- **Standard GSD attempt:** cancelled after completing an account/deposit slice and beginning later planning. Withdrawals, transfer/rollback, persistence, filters, and printing were incomplete. Available parent-plus-subagent usage was roughly 76M tokens, mostly cached input. It is discussed only as process evidence, not ranked.
- **GSD Pi quick result (`gsdpi-quick`):** completed code existed, but no comparable generated design, discussion, or steering checkpoint was retained. It is excluded because the study evaluates workflow review artifacts as well as code.
- **Earlier abandoned or superseded Spec Loop sessions:** not treated as completed solution units. Where an aborted session affected interpretation, it is described as a threat rather than counted as another solution.

## B. Prompts and conditions

### B.1 Matched non-calisthenics OpenSpec prompt

The replacement run used exactly:

```text
/opsx-propose Create a browser bank demo app using TypeScript and Vite. Requirements: - deposits and withdrawals - transfers between Daily and Savings accounts with rollback on failure - account statement with date, amount, and balance - statement printing - filters for deposits, withdrawals, and date - browser local storage
```

### B.2 Base prompt family

The earliest base conditions used this task shape:

```text
Create demo browser app with clean code design and implementation.
Think of your personal bank account experience.
Requirements

Deposit and Withdrawal
Transfer (transactional, rollback on failures)
Account statement (date, amount, balance)
Statement printing
Statement filters (just deposits, withdrawal, date)
```

In the first Spec Loop backlog run, the retained prompt omitted the parenthetical rollback wording, but the assistant later planned transactional transfer behavior and the user explicitly challenged rollback handling.

### B.3 Expanded common prompt family

Expanded conditions made the following scope explicit:

- TypeScript and Vite in most runs;
- fixed Daily and Savings accounts;
- rollback on transfer failure;
- statement date, amount, and balance;
- statement printing;
- deposit, withdrawal, and date filters; and
- browser local storage.

The OpenSpec calisthenics initial arguments did not explicitly say TypeScript/Vite, although the generated proposal/design and final implementation selected them. This prompt-explicitness asymmetry is retained rather than silently normalized.

### B.4 Domain-language/object-calisthenics condition

The calisthenics prompt added:

```text
Domain code must use only bank-domain concepts and names. Keep UI,
browser, storage, framework, rendering, and technical orchestration
concepts out of the domain model.

One level of indentation per method
Don't use the ELSE keyword
Wrap all primitives and Strings
First class collections
One dot per line
Don't abbreviate
Keep all entities small (50 lines)
No classes with more than two instance variables
No getters/setters/properties
```

The study evaluates both the design pressure created by this condition and whether the resulting production source/tests preserved it. It does not assume that compliance itself proves maintainability.

### B.5 Decomposition conditions

Two additional instructions were used:

- **Backlog:** “Breakdown the project in separate tasks in backlog and design each following task only after previous task is implemented and committed.”
- **Sequential subtasks:** “Breakdown task in subtasks and design each following subtask only after previous subtask is implemented and committed.”

`spec-loop-base-backlog-steered` reached the backlog form through user correction rather than through the initial prompt. That distinction is retained throughout the paper.

## C. Evaluation procedure

### C.1 Revision and executable verification

For each solution, the evaluator checked the tag/branch revision, test command, build command, and working-tree state. All primary projects passed their own tests and build at the evaluated revision. The replacement OpenSpec result was rerun after publication setup:

```text
npm test      -> 4 files, 19 tests passed
npm run build -> TypeScript check and Vite build passed
```

The generation session separately records the same results and a user-confirmed manual browser check.

### C.2 Workflow-process evidence anchors

The three workflow-process dimensions are applied identically to every solution. Artifact file count, line count, and fenced-block count describe review volume only. They do not raise or lower the pre-execution reviewability label by themselves.

| Dimension | Strong | Mixed | Limited |
|---|---|---|---|
| Interactive steering | Material product/design choices are surfaced before affected code, individually or in a bounded batch of clearly separated decisions, with reasons or alternatives; the user can accept, challenge, or redirect them. | Some material choices or approval gates are visible, but choices are difficult to evaluate separately, inconsistently surfaced, or left unresolved. | Little direct choice-level evidence; product/design questions are absent or interaction is primarily command approval. |
| Pre-execution reviewability | Before affected code, the artifacts give sufficiently clear, consistent, and navigable scope, design consequences, alternatives/risks, and behavior-specific verification expectations at a useful execution-unit granularity. | Meaningful design/planning content exists, but a material detail, consistency, navigability, granularity, or verification gap limits evaluation. | The pre-code record is mostly high-level, thin, difficult to navigate, or unavailable until after implementation. |
| Durable decision traceability | Committed workflow artifacts connect selected decisions, rationale, execution units, and verification expectations strongly enough to reconstruct the development path. | Committed artifacts preserve meaningful intent, but decision provenance, implementation reconciliation, or execution-state trace is materially incomplete. | Little committed rationale or execution linkage remains, or the record is primarily retrospective. |

The labels are categorical reviewer judgments. “Strong” does not mean complete or correct. Question count is contextual evidence only, and a forward-looking plan is not treated as proof of executed work.

### C.3 Behavior evidence anchors

- **Full (`✓`):** the behavior is directly asserted with meaningful state/output checks, or a retained manual verification record is specific enough to establish the check performed.
- **Partial (`△`):** only one part is asserted, the check is indirect, or a weaker proxy such as print invocation is present without content verification.
- **Missing (`—`):** no adequate test or retained check was found.

All 14 categories apply to every primary solution after the OpenSpec control correction. Automated source checks are instruction-following evidence, not functional behavior, and are evaluated separately in Section C.5. The non-calisthenics behavior-evidence rank in RQ4 orders profiles by Full categories descending and then Partial categories descending. Equal profiles share a dense rank. Raw passed/total test count is contextual and does not affect the rank. The five calisthenics solutions remain unranked by behavior evidence.

### C.4 Full behavior matrix

| Solution | Money | Deposit | Withdrawal | Insufficient safe | Transfer | Reject/no change | Statement | Type filter | Date filter | Print | UI flow | Restore | Bad storage | Save failure |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| `open-spec` | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | △ | ✓ | ✓ | ✓ | — |
| `open-spec-calisthenics` | △ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | — |
| `spec-loop-base-backlog-steered` | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | △ |
| `spec-loop-base-backlog-prompted` | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | △ |
| `spec-loop-incremental` | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | △ |
| `spec-loop-calisthenics` | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | △ | ✓ | ✓ | — | — |
| `spec-loop-calisthenics-incremental` | △ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | △ | ✓ | ✓ | — | △ |
| `spec-loop-calisthenics-single-task` | — | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | △ | ✓ | — | — |
| `superpowers` | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | — | △ | ✓ | ✓ | — |
| `superpowers-5.4` | △ | ✓ | — | ✓ | ✓ | ✓ | △ | ✓ | △ | △ | △ | ✓ | ✓ | — |
| `superpowers-calisthenics` | — | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | — | △ | △ | △ | — |
| `gsd-small-feature` | — | ✓ | — | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | △ | △ | — | — | — |

Column meanings:

- **Money:** rejects malformed, zero, negative, or over-precise input rather than silently rounding unsafe input.
- **Reject/no change:** rejected or failed transfer leaves the relevant state unchanged.
- **Statement:** date, amount, and resulting/running balance evidence.
- **Print:** current statement print trigger and content; trigger-only evidence is partial.
- **UI flow:** rendered/browser-facing operation evidence rather than domain-only tests.
- **Restore:** saved data are restored after reload/new repository instance.
- **Bad storage:** malformed or unsupported persisted data are validated and handled safely.
- **Save failure:** failed persistence does not advance visible, in-memory, or persisted state.

### C.5 Calisthenics instruction-following audit

The exact prompt in Section B.4 plus explicit user clarifications is the source of truth. Solution-authored designs, exceptions, and verifiers can provide evidence but cannot weaken a requested rule. The common boundary is production domain code. Wrapper internals may store raw values and adapters may construct wrappers, but public domain methods and ports may not expose raw domain-valued numbers or strings. Boolean predicates are treated as control decisions. Accessor-shaped methods count as getters even without TypeScript `get` syntax. “One dot per line” is literal within domain expressions. One nested control or callback indentation level is allowed; deeper nesting fails.

Each rule is binary: `✓` means no material violation was found and `✗` means at least one material violation was found. Dense rank uses the unweighted pass count. Equal totals remain tied; design scores, behavior evidence, and violation breadth are not tie-breakers.

| Rank | Solution | Domain-only names | Indentation | No `else` | Wrapped values | First-class collections | One dot | Full names | Entities at most 50 lines | At most 2 fields | No accessors | Passed |
|---:|---|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|---:|
| 1 | `open-spec-calisthenics` | ✓ | ✓ | ✓ | ✗ | ✓ | ✓ | ✓ | ✓ | ✓ | ✗ | 8/10 |
| 1 | `spec-loop-calisthenics-single-task` | ✗ | ✓ | ✓ | ✗ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | 8/10 |
| 2 | `spec-loop-calisthenics` | ✗ | ✓ | ✓ | ✗ | ✓ | ✓ | ✓ | ✓ | ✓ | ✗ | 7/10 |
| 2 | `spec-loop-calisthenics-incremental` | ✓ | ✓ | ✓ | ✗ | ✓ | ✗ | ✓ | ✓ | ✓ | ✗ | 7/10 |
| 2 | `superpowers-calisthenics` | ✓ | ✓ | ✓ | ✗ | ✓ | ✗ | ✓ | ✓ | ✓ | ✗ | 7/10 |

Violation evidence:

- **`open-spec-calisthenics`:** `AccountName.text()` and `Money.centsAmount()` expose stored raw values (`src/domain/account-name.ts:48-49`, `src/domain/money.ts:72-73`), and account APIs repeat the accessor pattern (`src/domain/account-book.ts:45-56`). Its architecture verifier does not test indentation and recognizes only TypeScript accessor syntax, so a passing verifier does not establish full compliance.
- **`spec-loop-calisthenics-single-task`:** generic `Change`, `Outcome`, `Continuation`, and `Record` protocols occur in the public domain API (`src/bank/AccountChange.ts:8-43`, `src/bank/BankContinuation.ts:4-6`, `src/bank/BankOutcome.ts:5-45`, `src/bank/BankRecord.ts:12-57`). Recorder methods expose raw names, dates, money, and refusal text (`src/bank/BankRecord.ts:44-57`). Its source test permits recorder-method primitive crossings, an exception absent from the prompt.
- **`spec-loop-calisthenics`:** `Recording` protocols are technical domain-boundary concepts, and two accept raw values (`src/domain/bank.ts:22-50`). `AccountOutcome.accountAfterOutcome()` and `BankOutcome.bankAfterOutcome()` return stored state (`src/domain/bank.ts:645-689`).
- **`spec-loop-calisthenics-incremental`:** representation accessors include `AccountNameText.asString()` and `Cents.asNumber()` (`src/domain/accountNameText.ts:14-16`, `src/domain/cents.ts:40-42`); other accessor-shaped methods include `Entry.endingBalance()` and `DatedMoney.date()` (`src/domain/entry.ts:14-16`, `src/domain/datedMoney.ts:24-26`). Literal one-dot violations include `this.name.other()` and `this.accounts.map(...)` (`src/domain/accountTransfer.ts:24`, `src/domain/accountBook.ts:32-42`).
- **`superpowers-calisthenics`:** `Money.cents()` and `StatementRecord` expose raw values (`src/domain/Money.ts:40-42`, `src/domain/StatementEntry.ts:5-9`), while account and statement methods repeat those accessors (`src/domain/AccountBook.ts:34-41`). Literal one-dot violations include `this.balance.cents()` and `this.entries.map(...)` (`src/domain/AccountBook.ts:34-41`, `src/domain/StatementEntries.ts:20-25`).

All five project test suites passed, but no artifact fully followed the prompt. Automated source checks remain useful evidence; they are not a separate behavior category and do not override the manual prompt-level audit.

### C.6 Replacement OpenSpec behavior evidence

The replacement control has 19 tests across four files:

- `test/domain/transactions.test.ts`: fixed accounts, deposit, strict amount parsing, withdrawal, overdraft, type/date filters;
- `test/domain/transfers.test.ts`: transfer entries, invalid amount, insufficient funds, same-account rejection, injected destination failure and rollback;
- `test/storage/bankStorage.test.ts`: versioned save, no save after rejected operation, restore, corrupt JSON fallback, unsupported-version fallback; and
- `test/ui/browserController.test.ts`: deposit/withdraw/transfer browser flow, persistence and rerender, startup restore, type/date filter rendering, and print invocation.

Print is partial because the automated test checks only invocation. Storage-write-failure safety is missing because `BrowserBankController.applyOperation` lets an operation replace `this.state` before `storage.save(this.state)`. If `save` throws, the immediate render is skipped, but a later filter render can expose the advanced in-memory state.

## D. Source/test design scoring

### D.1 Score anchors

Each category is scored independently from 0 to 3.

#### Naming and domain language

- **0:** public names obscure or misrepresent bank concepts.
- **1:** generic names, abbreviations, or inconsistent terminology make the domain difficult to infer.
- **2:** banking terms are mostly consistent, with some generic records/functions or primitives in public APIs.
- **3:** public APIs consistently read as deposits, withdrawals, transfers, statements, and filters.

#### Simplicity (KISS)

- **0:** control flow or abstraction is difficult to follow for the kata size.
- **1:** avoidable indirection, many tiny objects, giant mixed files, or similar structure makes simple changes tedious.
- **2:** mostly direct, with one or a few large files, templates, state handlers, or heavier-than-needed abstractions.
- **3:** direct for the kata size, without avoidable abstraction, giant mixed flow, or needless object splitting.

#### Single responsibility (SRP)

- **0:** domain, UI, storage, formatting, or printing are mixed so broadly that unrelated changes cross the same code.
- **1:** several responsibilities share central files or classes.
- **2:** main responsibilities are separated, but one orchestration, UI, or domain unit still combines several tasks.
- **3:** domain transitions, application orchestration, storage, UI/rendering, formatting, and printing have clear ownership.

#### Dependency direction

- **0:** domain code depends on browser, storage, UI, or framework APIs.
- **1:** side effects and domain rules are coupled, or runtime details leak materially into core behavior.
- **2:** dependency direction mostly holds, with boundary leakage or hard-coded runtime assumptions.
- **3:** domain code is runtime-independent and side effects are isolated behind adapters or injected functions.

#### Change locality

- **0:** likely changes require scattered edits because of cycles, duplicated rules, or hard-coded concepts.
- **1:** ordinary changes cross unrelated layers/files, repeated assumptions, cycles, or large aggregate units.
- **2:** most changes are localized, but common changes still touch a central unit or several fixed-account mappings.
- **3:** expected changes touch few expected files and boundaries make the location clear.

#### Testability

- **0:** essential behavior requires manual/browser setup or brittle paths; side effects are uncontrollable.
- **1:** some core behavior is tested, but many rules require integration setup or hard-to-substitute runtime services.
- **2:** core behavior is directly tested; boundary evidence is thinner or some side effects remain awkward to substitute.
- **3:** core and boundary behavior are directly testable; time, storage, identifiers, and printing are injectable or mockable where relevant.

### D.2 Operational audit and consistency procedure

Each artifact was bounded by the commit in Section A. The evaluator used a
fixed shuffled, name-masked order, although prior corpus knowledge means the
review was not blinded. Before assigning components, every solution received
the same evidence packet:

- public domain/API vocabulary;
- deposit, transfer, restore, and print/render flow traces;
- owners of domain transitions, application commit state, persistence, UI
  lifecycle, formatting, and printing;
- source and runtime-value dependency graphs;
- controllability and observability seams for time, storage, identifiers,
  printing, application commits, and UI events; and
- predicted edit surfaces for the five locality probes.

The source graph contains static relative imports and re-exports from
production TypeScript modules. The runtime-value graph contains only the
dependencies that remain after TypeScript type erasure under the project
compiler options. A cycle is one cyclic strongly connected component: a
mutually reachable group of more than one module, or a self-loop. Source-only
components describe compile-time coupling; runtime-value cycles receive more
weight because they can make ownership bidirectional. Historical depth-first
search back-edge counts are reported only as context and never determine a
score.

File, class, and test counts are also contextual rather than mechanical score
inputs. Naming measures accuracy and consistency rather than the number of
named types. KISS follows the common flows and requires a cited source of
avoidable ceremony for a low score. SRP follows responsibility and state
ownership rather than file separation. Dependency direction combines domain
independence, side-effect boundaries, and runtime-value cycles. Testability
measures controllability and observability through substitutable seams; test
volume and behavior-category breadth remain separate evidence.

Change locality uses the same five forecasts for every artifact: adding a
third account, switching exact-date and date-range filtering, replacing browser
persistence, changing money-acceptance rules, and adding filter/generated-date
context to printed output. A localized probe has one policy owner plus only
contract-consequential adapter or UI edits. A mixed probe has a primary owner
but repeated mappings or a central aggregate require coordinated edits. A
scattered probe has no clear primary owner, duplicates policy across unrelated
owners, or has cycles that obscure the edit surface. Locality is 0 when at
least four probes are scattered and no stable owner is visible; 1 when at
least two are scattered or no more than one is localized; 2 when at least
three are localized, or two are localized with none scattered; and 3 when at
least four are localized and none is scattered.

An exact component score is retained when one anchor fits clearly. Reasonable
readings that cross an anchor produce an adjacent range. The same evaluator
then repeated all 72 component judgments in reverse artifact order without
consulting published totals. A disagreement is resolved only when cited source
contradicts one reading; otherwise the union remains an uncertainty range. A
difference greater than one point is unstable and cannot support an exact
rank.

The reverse pass reproduced 70/72 judgments and 10/12 totals. Both
disagreements were one point and were resolved by re-reading the cited source:
`spec-loop-incremental` locality changed to 2 because money acceptance has two
policy sites, and `gsd-small-feature` dependency direction changed to 1 because
identifier generation is embedded in domain transitions. No final component
required a range.

The complete evidence packets, dependency graphs, locality probes, first pass,
reverse pass, and reconciliations are in the published
[design-score audit supplement](bank-kata-ai-workflow-case-study-design-audit.md).
This is same-evaluator stability evidence, not independent or human
validation.

### D.3 Non-calisthenics component scores and rank

Only the seven non-calisthenics solutions receive a conventional design rank. Dense ranks use total score; ties remain ties.

| Rank | Solution | Naming | KISS | SRP | Dependencies | Locality | Testability | Total |
|---:|---|---:|---:|---:|---:|---:|---:|---:|
| 1 | `spec-loop-base-backlog-steered` | 3 | 2 | 3 | 3 | 3 | 3 | 17 |
| 2 | `spec-loop-base-backlog-prompted` | 2 | 2 | 2 | 3 | 3 | 3 | 15 |
| 3 | `open-spec` | 2 | 2 | 2 | 2 | 2 | 3 | 13 |
| 3 | `spec-loop-incremental` | 2 | 2 | 2 | 2 | 2 | 3 | 13 |
| 4 | `superpowers` | 2 | 2 | 2 | 2 | 2 | 2 | 12 |
| 4 | `superpowers-5.4` | 2 | 2 | 2 | 2 | 2 | 2 | 12 |
| 5 | `gsd-small-feature` | 2 | 2 | 1 | 1 | 2 | 1 | 9 |

### D.4 Calisthenics component scores, unranked

These scores describe conventional design pressure but do not rank the constrained artifacts or break instruction-following ties.

| Solution | Naming | KISS | SRP | Dependencies | Locality | Testability | Total |
|---|---:|---:|---:|---:|---:|---:|---:|
| `open-spec-calisthenics` | 3 | 2 | 3 | 3 | 1 | 2 | 14 |
| `spec-loop-calisthenics` | 2 | 1 | 2 | 3 | 1 | 3 | 12 |
| `spec-loop-calisthenics-incremental` | 2 | 1 | 2 | 2 | 1 | 2 | 10 |
| `spec-loop-calisthenics-single-task` | 2 | 1 | 2 | 1 | 2 | 2 | 10 |
| `superpowers-calisthenics` | 2 | 1 | 2 | 2 | 1 | 1 | 9 |

The constrained group has mean 11.0 and median 10, compared with mean and median 13.0 for the unconstrained group. The difference is concentrated in simplicity and locality. This is descriptive only: there is one artifact per condition, prompts and interaction vary, and every constrained artifact has at least two compliance failures. OpenSpec is the counterexample to a within-every-framework claim: its constrained artifact scores 14 versus 13 for its control while failing wrapped values and no accessors.

### D.5 Per-solution evidence summary

- **`open-spec`:** pure domain modules, strict integer-cent parsing, storage and print injection, and acyclic graphs support testability 3. Default time and identifier globals inside domain transactions limit dependency direction to 2; fixed account mappings and a broad controller limit SRP/locality.
- **`open-spec-calisthenics`:** explicit `AccountBook`, `Money`, `Balance`, statement, application, browser, and storage boundaries support naming/SRP/direction scores of 3. Many small objects reduce KISS, and adding an account remains scattered, giving locality 1.
- **`spec-loop-base-backlog-steered`:** domain transitions, application commit, storage, clock, identifiers, printing, and screen adapters are separated. Save-before-state-replacement is explicit and independently tested. Extra ports add some ceremony.
- **`spec-loop-base-backlog-prompted`:** clean functional modules and injected storage/time/identifier/print boundaries give dependency, locality, and testability scores of 3; rendering and orchestration remain concentrated in larger units.
- **`spec-loop-incremental`:** compact and highly testable, but the central bank/store and app units combine several responsibilities. Money acceptance has two coordinated policy sites, so locality is 2.
- **`spec-loop-calisthenics`:** explicit owners and supplied effects support dependency and testability scores of 3, but technical recording vocabulary, recorder/outcome ceremony, two very large files, and scattered account identity limit naming, KISS, and locality.
- **`spec-loop-calisthenics-incremental`:** small methods and named owners coexist with 51 production modules, policy-neutral activity/acceptance/paper objects, source-only cycles, concrete print, and scattered account/date changes.
- **`spec-loop-calisthenics-single-task`:** continuation/outcome/record protocols make common flows indirect, and an eight-module value cycle makes ownership bidirectional. Date, persistence, and money still have identifiable owners, giving locality 2 rather than a cycle-count-based penalty.
- **`superpowers`:** pure domain operations and a separate repository are sound; a large UI controller owns rendering, events, parsing, persistence, reset, print, and messages, and it advances in-memory state before save.
- **`superpowers-5.4`:** readable components and an application hook keep every component at 2; the hook combines state, time, operations, commit, filtering, and selection, while time and printing remain concrete assumptions.
- **`superpowers-calisthenics`:** recognizable layers remain, but policy-neutral Daily/Savings wrappers, transfer restore ceremony, fixed time/print boundaries, scattered account identity, and raw value accessors reduce KISS, locality, and testability.
- **`gsd-small-feature`:** operations are direct, but broad domain and UI modules mix responsibilities; time and identifier generation occur in domain flows, third-account changes are scattered, and important globals remain hard to substitute.

No component is scored 0. Relative to the earlier publication table, the reconciled audit changes 17 of 72 component judgments across nine artifacts and changes eight totals.

## E. Artifact and static metrics

### E.1 Generated workflow artifacts

Line counts are physical lines in generated planning/workflow Markdown or AsciiDoc files. “Fenced blocks” counts embedded code/config/command fences, not source files in the solution.

| Solution | Generated files | Lines | Fenced blocks | Main structure |
|---|---:|---:|---:|---|
| `open-spec` | 7 | 311 | 0 | Proposal, design, four capability specs, tasks |
| `open-spec-calisthenics` | 6 | 310 | 0 | Proposal, design, three capability specs, tasks |
| `spec-loop-base-backlog-steered` | 4 | 1491 | 0 | Four backlog task files |
| `spec-loop-base-backlog-prompted` | 5 | 1598 | 0 | Five backlog task files |
| `spec-loop-incremental` | 1 | 929 | 0 | One task with four subtasks |
| `spec-loop-calisthenics` | 1 | 625 | 0 | One task with three subtasks |
| `spec-loop-calisthenics-incremental` | 1 | 1067 | 0 | One task with three sequential subtasks |
| `spec-loop-calisthenics-single-task` | 1 | 466 | 0 | One broad task |
| `superpowers` | 2 | 1988 | 32 | Design document and implementation plan |
| `superpowers-5.4` | 2 | 2061 | 48 | Design document and implementation plan |
| `superpowers-calisthenics` | 2 | 2282 | 90 | Design document and implementation plan |
| `gsd-small-feature` | 4 | 278 | 2 | Context, plan, state, summary; fences contain commands only |

The GSD summary is post-implementation, so its 278-line total is not directly equivalent to a wholly pre-code specification set.

### E.2 Source and test shape

LOC is nonblank physical TypeScript/TSX lines. CC is the approximate cyclomatic-complexity range across production functions. Storage/repository files are grouped with application+UI. Configuration, CSS, HTML, generated declarations, and tests are excluded from production LOC.

| Solution | Prod domain files / LOC / CC | Prod app+UI files / LOC / CC | Test domain files / LOC | Test app+UI files / LOC |
|---|---:|---:|---:|---:|
| `open-spec` | 6 / 343 / 1–7 | 4 / 537 / 1–11 | 2 / 180 | 2 / 186 |
| `open-spec-calisthenics` | 16 / 821 / 1–3 | 10 / 602 / 1–6 | 2 / 90 | 2 / 173 |
| `spec-loop-base-backlog-steered` | 4 / 295 / 1–7 | 9 / 598 / 1–10 | 4 / 271 | 5 / 645 |
| `spec-loop-base-backlog-prompted` | 6 / 537 / 1–6 | 3 / 529 / 1–5 | 0 / 0 | 8 / 1472 |
| `spec-loop-incremental` | 1 / 253 / 1–4 | 3 / 431 / 1–10 | 0 / 0 | 1 / 623 |
| `spec-loop-calisthenics` | 1 / 728 / 1–5 | 2 / 724 / 1–4 | 0 / 0 | 2 / 380 |
| `spec-loop-calisthenics-incremental` | 27 / 631 / 1–2 | 24 / 934 / 1–4 | 0 / 0 | 1 / 276 |
| `spec-loop-calisthenics-single-task` | 36 / 1099 / 1–3 | 6 / 612 / 1–5 | 1 / 161 | 2 / 263 |
| `superpowers` | 5 / 243 / 1–8 | 4 / 453 / 1–9 | 3 / 203 | 2 / 96 |
| `superpowers-5.4` | 4 / 236 / 1–7 | 11 / 387 / 1–5 | 1 / 107 | 5 / 124 |
| `superpowers-calisthenics` | 17 / 379 / 1–2 | 6 / 387 / 1–5 | 3 / 141 | 2 / 79 |
| `gsd-small-feature` | 2 / 327 / 1–10 | 2 / 426 / 1–7 | 1 / 136 | 0 / 0 |

The replacement OpenSpec static audit found 10 production source files, 880 nonblank production TypeScript lines, 366 nonblank test lines, no import cycles, and no detected production clone at the shared jscpd threshold. Across the other measured solutions, duplication remained low; the earlier maximum was approximately 2.84% duplicated production lines in `superpowers-calisthenics`.

Static metrics identify review risks; they do not establish correctness or maintainability.

## F. Workflow-process evidence

Only user-visible assistant text and user responses were used. Tool calls, tool results, hidden reasoning, and evaluator speculation were excluded.

### F.1 Workflow-process matrix

| Solution | Interactive steering | Pre-execution reviewability | Durable traceability |
|---|---|---|---|
| `open-spec` | Limited | Strong | Strong |
| `open-spec-calisthenics` | Limited | Strong | Strong |
| `spec-loop-base-backlog-steered` | Strong | Strong | Strong |
| `spec-loop-base-backlog-prompted` | Strong | Strong | Strong |
| `spec-loop-incremental` | Strong | Strong | Strong |
| `spec-loop-calisthenics` | Strong | Strong | Strong |
| `spec-loop-calisthenics-incremental` | Strong | Strong | Strong |
| `spec-loop-calisthenics-single-task` | Strong | Strong | Strong |
| `superpowers` | Strong | Strong | Mixed |
| `superpowers-5.4` | Mixed | Mixed | Mixed |
| `superpowers-calisthenics` | Strong | Strong | Mixed |
| `gsd-small-feature` | Mixed | Mixed | Mixed |

The OpenSpec reviewability result is based on explicit goals/non-goals, decisions with rationales and alternatives, risks, scenario requirements, and implementation/test tasks—not on compactness. Its steering is limited because no product or design choices were discussed with the user. The excluded original pilot corroborates this pattern under the minimal base prompt: it also asked no product/design questions and selected a design that excluded persistence. That pilot is process evidence only, not part of the matrix or ranking. OpenSpec traceability is strong because the committed primary artifacts preserve selected decisions, rationale, scenarios, verification expectations, and completed tasks; lack of user acceptance is not counted again in this separate dimension. All six Spec Loop rows connect choices that the user was asked to approve or select with task/current-subtask design and test expectations. The single-task run presented six material choices with reasons in one pre-code decision batch and invited the user to confirm, question, or disagree. This is a supported clarification form, so using a batch rather than individual questions does not lower its steering rating. All six Spec Loop rows are also Strong for pre-execution reviewability and durable traceability. The single-task run's approved task contains scope, scenarios, constraints, rationale, three design diagrams, implementation boundaries, and behavior-specific tests; its committed final form also preserves implementation notes, verification, and completion.

The two GPT-5.5 Superpowers runs provide strong elicitation and strong pre-code design/implementation-test review content. Their high volume is a separate descriptive fact, while their forward-looking, unreconciled plans keep traceability mixed. The GPT-5.4 run is mixed because it left an ambiguous stack answer unresolved. GSD exposed bundled scope/plan choices, but part of its durable record was post-implementation and some choices remained proposed.

Artifact volume remains separate and unranked: OpenSpec has small structured artifact sets, Spec Loop distributes moderate-to-large totals across task/subtask files and named sections, and Superpowers has the largest plans. GSD's 278-line total is smaller than OpenSpec's, but includes post-implementation material. Those sizes do not determine the labels above, and human review effort was not measured.

### F.2 Visible decision evidence

| Solution or group | Visible decision evidence | Limitation |
|---|---|---|
| `open-spec` | Five user messages: propose, apply, manual-check confirmation, commit, staging approval. Generated design exposed choices. | No product/design clarification; choices were assistant-selected. |
| `open-spec-calisthenics` | Apply approval and concise workflow progress. | No product/design questions; choices were assistant-selected. |
| `spec-loop-base-backlog-steered` | Assistant proposed stack, persistence, accounts, statement, and printing decisions; user redirected backlog form and later challenged rollback. | Several advantages are user-steered rather than workflow-default evidence. |
| Other Spec Loop | The assistant requested user choice or approval for material task/subtask decisions before requesting execution approval. | Clarification used individual questions or bounded decision batches, so raw question counts are not comparable measures of steering quality. |
| `superpowers` conditions | Many one-at-a-time product/design questions and explicit user answers. | Long discussion/plan did not guarantee preservation in final code/tests. |
| `gsd-small-feature` | Recommended scope and plan choices shown at approval gates. | Gray areas were bundled rather than separately resolved; some remained “proposed” in committed context. |

The original session extraction for the first 11 solutions counted clarification-like messages with a heuristic. Those counts are descriptive, not validated measures of question quality. The paper therefore uses qualitative decision evidence rather than treating the count as an outcome.

## G. Token accounting

### G.1 Method

For Pi and Codex sessions, “integrated total” sums the usage attached to every assistant response in the retained solution-development session:

```text
integrated total = fresh input + cached/read input + output
```

Reasoning output, when reported, is a subset/detail of output and is not added again. Costs use the recorded or price-derived model rates used during the original evaluation. GSD Pi usage came from its retained workflow session counters and is not available at the same field granularity.

### G.2 Results

| Solution | Model | Usage events | Integrated total | Fresh input | Cached/read | Output | Reasoning detail | Cost |
|---|---|---:|---:|---:|---:|---:|---:|---:|
| `open-spec` | GPT-5.5 xhigh | 88 | 4,181,993 | 160,812 | 3,979,264 | 41,917 | 14,314 | $4.05 |
| `open-spec-calisthenics` | GPT-5.5 xhigh | 74 | 4,510,679 | 220,449 | 4,231,680 | 58,550 | not reported | $4.97 |
| `spec-loop-base-backlog-steered` | GPT-5.5 xhigh | 205 | 19,691,666 | 692,873 | 18,857,472 | 141,321 | not reported | $17.13 |
| `spec-loop-base-backlog-prompted` | GPT-5.5 xhigh | 279 | 36,086,791 | 1,074,928 | 34,851,840 | 160,023 | not reported | $27.60 |
| `spec-loop-incremental` | GPT-5.5 xhigh | 184 | 20,217,063 | 897,879 | 19,221,504 | 97,680 | not reported | $17.03 |
| `spec-loop-calisthenics` | GPT-5.5 xhigh | 114 | 7,740,813 | 421,853 | 7,225,344 | 93,616 | not reported | $8.53 |
| `spec-loop-calisthenics-incremental` | GPT-5.5 xhigh | 185 | 18,287,051 | 575,911 | 17,609,216 | 101,924 | not reported | $14.74 |
| `spec-loop-calisthenics-single-task` | GPT-5.5 xhigh | 139 | 10,385,765 | 403,485 | 9,892,864 | 89,416 | not reported | $9.65 |
| `superpowers` | GPT-5.5 xhigh | 144 | 16,649,374 | 475,317 | 16,100,992 | 73,065 | 23,354 | $12.62 |
| `superpowers-5.4` | GPT-5.4 high | 143 | 18,193,566 | 449,954 | 17,684,096 | 59,516 | 16,943 | $6.44 |
| `superpowers-calisthenics` | GPT-5.5 xhigh | 199 | 22,260,119 | 667,982 | 21,492,096 | 100,041 | 37,502 | $17.09 |
| `gsd-small-feature` | GPT-5.5 xhigh | not comparable | 6.58M | not comparable | not comparable | not comparable | not comparable | about $5.69 |

The replacement OpenSpec values come from the retained private session file `2026-07-15T19-17-33-042Z_019f6736-80f2-758c-b409-92f3eb60aeeb.jsonl`. The file is not a public data link.

Cached input dominates every fully decomposed row. The totals should be interpreted as recorded context-processing volume, not as independent fresh tokens or a normalized measure of engineering productivity.

## H. Ranking details and sensitivity

The secondary overall ranking applies only to the seven non-calisthenics solutions. Adjacent decisions were based on:

1. the three workflow-process dimensions and their material limitations;
2. the identity of partial/missing safety categories, not only totals;
3. source/test design components; and
4. static/token/document evidence only as supporting facts.

The resulting dense ranks are:

1. `spec-loop-base-backlog-steered` and `spec-loop-base-backlog-prompted`;
2. `spec-loop-incremental`;
3. `open-spec`;
4. `superpowers`;
5. `superpowers-5.4`; and
6. `gsd-small-feature`.

Key boundaries:

- The two backlog solutions share rank 1 because both have strong workflow-process profiles and matching behavior totals. The steered run has a stronger design score and deeper save-failure design, but user intervention is a larger confound; the prompted run is cleaner evidence for the planned backlog condition.
- `spec-loop-incremental` follows because it also has a strong workflow-process profile and the same behavior totals, but a coarser source/task review structure.
- `open-spec` follows because it combines strong reviewability and traceability with a 12/1/1 common-behavior profile, but asked no product/design questions and lacks storage-write-failure evidence.
- `superpowers` has stronger interactive steering than OpenSpec but mixed durable traceability and two missing behavior categories. A steering-dominated synthesis could reverse these two positions.
- `superpowers-5.4` and GSD have mixed process profiles; the former has fewer missing behavior categories and stronger conventional design evidence.

The five calisthenics solutions are excluded from that synthesis. Their only rank is strict instruction following: `open-spec-calisthenics` and `spec-loop-calisthenics-single-task` share rank 1 at 8/10, while the other three share rank 2 at 7/10. Conventional design scores and common behavior evidence do not break those ties.

Sensitivity remains material. A ranking dominated by interactive steering would move Superpowers upward; one dominated by durable execution traceability would strengthen Spec Loop. This is why the paper reports dimension results before ranking.

## I. Evaluation independence and author role

The author created and maintains Spec Loop and selected the study conditions and prompts. During solution generation, the author completed approvals required by the workflows. Except for substantive interventions explicitly reported as steering, these approvals were procedural confirmations rather than author-selected implementation decisions. During evaluation and paper revision, the author supplied source facts, identified factual or methodological problems, and requested explicit criteria and scores. The author did not assign scores, choose ranks, or direct the evaluator toward a preferred winner. The AI evaluator defined and applied the criteria and made the evaluative judgments.

The reconciled source/test design audit was artifact-only: it used tagged source, tests, and dependency measurements and did not use session transcripts, workflow-process labels, behavior totals, or Spec Loop skills to assign scores. Later session-communication and framework-influence analysis did inspect visible messages and current workflow guidance to understand process shape. The latter was used for interpretation, not as proof that an implementation was good.

The behavior rubric and design anchors were formalized retrospectively. Steering, reviewability, and traceability were present in the original comparison criteria, but their separation and categorical anchors were formalized during paper revision after outcomes were known. Separating calisthenics instruction rank from conventional design rank was another post-hoc correction made after compliance review. Consequently:

- the implementation artifacts were not optimized against the final scoring rubric;
- neither the implementation nor workflow-process rubric was preregistered;
- condition selection and the substantive steering disclosed for individual runs could influence which dimensions the generated solutions addressed;
- author-evaluator discussion could influence which judgments were reexamined, although the evaluator retained the scoring and ranking decisions; and
- AI reviewer judgments are not independent human ratings.

The mitigations are evidence links, component scores, full anchors, a full behavior matrix, explicit protocol deviations, and narrow conclusions.

## J. Reproduction notes

1. Clone the public solution repositories listed in Section A.
2. Check out `analysis-2026-06-30` and verify it resolves to the listed commit.
3. Install each repository’s locked dependencies.
4. Run the repository’s own test and build scripts without skip flags.
5. Inspect generated workflow artifacts committed in the repository.
6. Apply the workflow-process anchors in Section C to generated artifacts and visible session evidence.
7. Apply the behavior anchors in Section C to tests and any published verification record.
8. Apply the six independent design anchors in Section D to tagged source/tests.
9. For each calisthenics solution, apply the ten binary source constraints in Section C.5 to production domain code without importing solution-authored exceptions.

The static analysis used TypeScript/TSX production source under `src/`, excluded tests/config/generated declarations, counted nonblank physical LOC, estimated per-function cyclomatic complexity, checked import cycles, and used jscpd on production `src/` with minimum 5 lines and 50 tokens in weak mode.

Full reproduction of session-message and token analysis requires the private JSONL files and is therefore not currently possible for an external reader. The public paper reports the extraction boundary and derived values rather than implying that the sessions are public.
