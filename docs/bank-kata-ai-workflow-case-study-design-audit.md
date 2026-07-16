# Supplement: Bank Kata Design-Score Audit Evidence

This supplement records the artifact-only design audit supporting the
[technical appendix](bank-kata-ai-workflow-case-study-appendix.md). Paths are
relative to the named solution repository. Every observation is bounded by the
recorded commit. Generation sessions, framework instructions, published
scores, test volume, and behavior-coverage totals were not used to assign
design scores.

The later strict calisthenics instruction audit is a separate analysis. It uses
the original prompt and explicit user clarifications as its authority and does
not alter the artifact-only design judgments above it.

The dependency graphs include production TypeScript modules only. The source
graph includes relative imports and re-exports. The value graph contains the
dependencies left after TypeScript type erasure. A cycle is one cyclic strongly
connected component, not a depth-first-search back edge.

## First-pass component results

| Artifact | Naming | KISS | SRP | Direction | Locality | Test | Total | Norm. |
|---|---:|---:|---:|---:|---:|---:|---:|---:|
| artifact-01 | 3 | 2 | 3 | 3 | 1 | 2 | 14 | 77.8% |
| artifact-02 | 2 | 1 | 2 | 2 | 1 | 1 | 9 | 50.0% |
| artifact-03 | 2 | 2 | 1 | 2 | 2 | 1 | 10 | 55.6% |
| artifact-04 | 2 | 1 | 2 | 3 | 1 | 3 | 12 | 66.7% |
| artifact-05 | 2 | 1 | 2 | 2 | 1 | 2 | 10 | 55.6% |
| artifact-06 | 3 | 2 | 3 | 3 | 3 | 3 | 17 | 94.4% |
| artifact-07 | 2 | 2 | 2 | 2 | 2 | 2 | 12 | 66.7% |
| artifact-08 | 2 | 1 | 2 | 1 | 2 | 2 | 10 | 55.6% |
| artifact-09 | 2 | 2 | 2 | 2 | 3 | 3 | 14 | 77.8% |
| artifact-10 | 2 | 2 | 2 | 3 | 3 | 3 | 15 | 83.3% |
| artifact-11 | 2 | 2 | 2 | 2 | 2 | 2 | 12 | 66.7% |
| artifact-12 | 2 | 2 | 2 | 2 | 2 | 3 | 13 | 72.2% |

These are first-pass results, not ranks. The reverse-order consistency pass is
reported separately below.

## artifact-01: open-spec-calisthenics at 1d71c71

### Common evidence packet

- **Vocabulary:** `AccountBook`, `Account`, `Money`, `PostingDate`,
  `StatementFilter`, and `DateRange` expose the banking behavior directly
  (`src/domain/account-book.ts:8-63`,
  `src/domain/statement-filter.ts:26-84`).
- **Flows:** deposit goes from `BankDemo.deposit` through `AccountBook.deposit`
  to `Account.deposit` (`src/application/bank-demo.ts:56-62`,
  `src/domain/account-book.ts:27-30`, `src/domain/account.ts:29-32`). Transfer
  chooses one of two hard-coded directions before withdrawing and depositing
  (`src/domain/account-book.ts:99-132`). Restore maps saved Daily and Savings
  records back into an `AccountBook`
  (`src/application/bank-demo-recording.ts:16-25`). Print/render obtains a
  filtered view and printable text from `BankDemo.view`, then the browser
  adapter invokes its print dependency (`src/application/bank-demo.ts:94-115`,
  `src/browser/browser-bank-demo.ts:116-124`).
- **Responsibility owners:** `Account` owns account transitions; `BankDemo`
  owns application state and selection; `BankDemoStorage` owns persistence;
  `BrowserBankDemo` owns UI lifecycle; the domain statement objects own line
  formatting; the browser print dependency owns the external print action
  (`src/domain/account.ts:29-108`, `src/application/bank-demo.ts:38-129`,
  `src/browser/bank-demo-storage.ts:4-25`,
  `src/browser/browser-bank-demo.ts:6-124`).
- **Dependencies:** 26 modules and 70 source edges, with no source cyclic
  component; 39 value edges, with no value cyclic component. Domain modules do
  not import browser or storage modules. Storage, date text, and printing enter
  at browser composition (`src/browser/main.ts:8-16`).
- **Seams:** print and current date are functions, and storage wraps an injected
  `Storage`. The browser application nevertheless requires the concrete
  `BankDemoStorage` class, and its test constructs that adapter rather than a
  repository substitute (`src/browser/bank-demo-storage.ts:4-12`,
  `tests/browser/browser-bank-demo.test.ts:61-113`).
- **Locality probes:** third account **scattered** across the two-field domain
  aggregate, saved-state mapping, validation, and fixed UI
  (`src/domain/account-book.ts:8-24`,
  `src/application/bank-demo-recording.ts:16-25`,
  `src/browser/bank-demo-storage.ts:37-52`); date filtering **localized** in
  `DateRange` and `StatementFilter`, with consequential input edits
  (`src/domain/statement-filter.ts:26-84`); persistence **mixed** because the
  adapter is dedicated but required concretely; money acceptance **mixed**
  between input parsing and domain invariants
  (`src/application/input-parsing.ts:1-40`, `src/domain/account.ts:95-108`);
  print context **mixed** between the view model and browser rendering
  (`src/application/bank-demo.ts:94-115`).

### Component judgments

- **Naming 3:** the public API consistently uses specific banking concepts;
  the score follows the coherent-vocabulary anchor
  (`src/domain/account-book.ts:27-63`).
- **KISS 2:** the common account flows are traceable, but the two-account
  aggregate repeats direction and replacement branches, so the design has
  limited ceremony rather than fully direct composition
  (`src/domain/account-book.ts:67-140`).
- **SRP 3:** domain transitions, application state, persistence, UI lifecycle,
  formatting, and printing have explicit owners
  (`src/domain/account.ts:29-108`, `src/application/bank-demo.ts:38-129`,
  `src/browser/bank-demo-storage.ts:4-25`).
- **Dependency direction 3:** the domain is runtime-independent, effects are
  composed at the browser boundary, and both graphs are acyclic
  (`src/browser/main.ts:8-16`).
- **Change locality 1:** one probe is localized and the third-account policy is
  scattered, matching the calibrated score-1 aggregation anchor
  (`src/domain/account-book.ts:8-24`,
  `src/application/saved-bank-state.ts:1-15`).
- **Testability 2:** core and print/time behavior are directly controllable,
  but substitution of the concrete browser storage dependency remains awkward
  (`src/browser/browser-bank-demo.ts:6-20`,
  `tests/browser/browser-bank-demo.test.ts:61-113`).

## artifact-02: superpowers-calisthenics at 65e2dbb

### Common evidence packet

- **Vocabulary:** banking operations are recognizable, but generic snapshots
  and records coexist with duplicate `DailyAccount` and `SavingsAccount`
  wrappers (`src/application/BankApplicationTypes.ts:3-32`,
  `src/domain/DailyAccount.ts:8-45`,
  `src/domain/SavingsAccount.ts:8-45`).
- **Flows:** deposit crosses `BankApplication`, an account-specific wrapper,
  and `AccountBook` (`src/application/BankApplication.ts:44-53`,
  `src/domain/DailyAccount.ts:19-24`, `src/domain/AccountBook.ts:18-29`).
  Transfer mutates the source, attempts the recipient, and restores the source
  through `AccountStanding` on failure (`src/domain/Transfer.ts:9-22`). Restore
  replays Daily and Savings records (`src/application/BankApplication.ts:32-40`,
  `src/application/BankApplication.ts:90-103`). Rendering and printing are both
  controlled by `BankInterface` (`src/ui/BankInterface.ts:21-79`,
  `src/ui/BankInterface.ts:100-113`).
- **Responsibility owners:** `AccountBook` owns account transitions;
  `BankApplication` owns the two live account objects; `BankLocalStorage` owns
  persistence; `BankInterface` owns UI lifecycle and commit coordination;
  formatter helpers own currency/date text; `BankInterface` calls browser
  printing directly (`src/domain/AccountBook.ts:8-43`,
  `src/application/BankApplication.ts:20-123`,
  `src/storage/BankLocalStorage.ts:9-45`,
  `src/ui/BankInterface.ts:11-190`).
- **Dependencies:** 23 modules and 61 source edges. One source-only cyclic
  component contains `AccountBook.ts` and `AccountStanding.ts`; 23 value edges
  contain no cyclic component. The UI directly owns browser time and print
  assumptions (`src/ui/BankInterface.ts:109-110`,
  `src/ui/Formatters.ts:8-10`).
- **Seams:** domain objects and application methods are directly callable, and
  storage is constructor-supplied. Time and print remain browser globals; UI
  money conversion is embedded in event handling
  (`src/ui/BankInterface.ts:152-171`).
- **Locality probes:** third account **scattered** across application fields,
  snapshot shape, branching, and UI options
  (`src/application/BankApplication.ts:20-26`,
  `src/application/BankApplicationTypes.ts:3-32`,
  `src/ui/BankInterface.ts:33-39`); exact-to-range date filtering
  **localized** around `StatementDateChoice` with UI contract edits
  (`src/application/BankApplication.ts:105-123`); persistence **mixed** because
  the UI requires `BankLocalStorage`; money acceptance **mixed** between
  duplicated UI conversion and `Money.rejectUnlessPositive`
  (`src/ui/BankInterface.ts:160-171`,
  `src/domain/Money.ts:15-19`); print context
  **mixed** inside the broad UI owner.

### Component judgments

- **Naming 2:** operations are clear, but public snapshot records and duplicate
  account wrappers keep the vocabulary from the coherent score-3 anchor
  (`src/application/BankApplicationTypes.ts:3-32`).
- **KISS 1:** common operations repeatedly cross policy-neutral Daily/Savings
  wrappers, and transfer adds standing/restore ceremony
  (`src/domain/DailyAccount.ts:19-44`, `src/domain/Transfer.ts:9-22`).
- **SRP 2:** domain and persistence are separated, while `BankInterface` owns
  rendering, events, parsing, commit, and printing
  (`src/ui/BankInterface.ts:21-190`).
- **Dependency direction 2:** the domain has no value cycle, but time and print
  are concrete UI environment assumptions
  (`src/ui/BankInterface.ts:109-110`, `src/ui/Formatters.ts:8-10`).
- **Change locality 1:** only the date-filter probe is localized and the
  third-account probe is scattered
  (`src/application/BankApplication.ts:20-26`,
  `src/ui/BankInterface.ts:33-93`).
- **Testability 1:** several relevant boundaries require global or broad UI
  integration control: `Date`, `window.print`, concrete storage, and embedded
  money parsing (`src/ui/BankInterface.ts:109-110`,
  `src/ui/BankInterface.ts:152-171`).

## artifact-03: gsd-small-feature at aef38ff

### Common evidence packet

- **Vocabulary:** deposits, withdrawals, transfers, accounts, and statement
  filters are explicit, while the API is primarily generic record types and
  `BankResult` values (`src/bank/types.ts:1-69`).
- **Flows:** deposit, withdrawal, transfer, and statement filtering are direct
  functions in one bank module (`src/bank/bank.ts:63-217`). Transfer clones
  state, records both sides, and catches the simulated post-debit failure
  (`src/bank/bank.ts:126-194`). Restore runs through `loadBankState`, while
  rendering and printing stay in the top-level UI module
  (`src/bank/storage.ts:12-39`, `src/main.ts:42-173`,
  `src/main.ts:242-255`).
- **Responsibility owners:** the `bank.ts` module jointly owns initial data,
  transitions, filtering, cloning, formatting, time, and identifiers;
  `main.ts` jointly owns application state, UI lifecycle, commits, and printing;
  `storage.ts` owns persistence (`src/bank/bank.ts:14-312`,
  `src/main.ts:33-311`, `src/bank/storage.ts:6-49`).
- **Dependencies:** four modules and six source edges, with no source cycle;
  four value edges, with no value cycle. The domain module directly uses
  `Date`, `crypto`, `Date.now`, `Math.random`, and `Intl`
  (`src/bank/bank.ts:26-57`, `src/bank/bank.ts:237-312`).
- **Seams:** storage functions accept a `BankStorage`, but default to browser
  local storage. Date and identifiers default inside the domain; printing is a
  direct browser call (`src/bank/storage.ts:6-39`,
  `src/bank/bank.ts:299-312`, `src/main.ts:255`).
- **Locality probes:** third account **scattered** across state construction,
  cloning, storage validation, and UI assumptions
  (`src/bank/bank.ts:14-57`, `src/bank/bank.ts:219-227`,
  `src/bank/storage.ts:42-49`); date filtering **localized** in
  `getStatement`; persistence **localized** in `storage.ts`; money acceptance
  **localized** in `normalizePositiveAmount`; print context **localized** in
  the statement-render/print area (`src/bank/bank.ts:196-217`,
  `src/bank/bank.ts:267-288`, `src/main.ts:173-255`).

### Component judgments

- **Naming 2:** the banking terms are readable, with generic record/result
  contracts rather than a fully coherent domain API (`src/bank/types.ts:9-69`).
- **KISS 2:** operations are direct, although two broad modules require
  navigation through mixed helpers (`src/bank/bank.ts:63-217`).
- **SRP 1:** several ongoing domain and technical responsibilities share
  `bank.ts`, while UI lifecycle, commit, and print share `main.ts`
  (`src/bank/bank.ts:14-312`, `src/main.ts:33-311`).
- **Dependency direction 2:** the graph is acyclic, but the domain contains
  concrete environment assumptions for time, identifiers, and formatting
  (`src/bank/bank.ts:237-312`).
- **Change locality 2:** four probes have primary local owners, but adding an
  account is scattered (`src/bank/bank.ts:219-227`,
  `src/bank/storage.ts:42-49`).
- **Testability 1:** storage is substitutable, but several important domain and
  browser globals remain fixed and the broad UI is the only application seam
  (`src/bank/bank.ts:299-312`, `src/main.ts:33-255`).

## artifact-04: spec-loop-calisthenics at 319a8c9

### Common evidence packet

- **Vocabulary:** banking classes are explicit, but public `Recording`
  interfaces permeate money, dates, accounts, statements, and the bank
  (`src/domain/bank.ts:22-52`, `src/domain/bank.ts:84-121`,
  `src/domain/bank.ts:703-746`).
- **Flows:** browser deposit/withdrawal/transfer calls `Bank`, receives an
  outcome, and accepts or refuses it through application state
  (`src/browser/browserBankDemo.ts:90-113`,
  `src/domain/bank.ts:711-782`). Restore records and reconstructs accounts
  through browser-side recorder objects
  (`src/browser/browserBankDemo.ts:427-502`,
  `src/browser/browserBankDemo.ts:710-758`). Print/render records a statement
  into display objects before rendering rows and invoking an injected printer
  (`src/browser/browserBankDemo.ts:195-257`,
  `src/browser/browserBankDemo.ts:761-832`).
- **Responsibility owners:** `Account`, `Accounts`, `Transfer`, and `Bank` own
  domain transitions; `BrowserBankState` owns application state and commit;
  browser recorder functions own persistence; `BrowserBankScreen` owns UI
  lifecycle; display recorders own formatting; the injected
  `printStatement` function owns the external effect
  (`src/domain/bank.ts:474-748`,
  `src/browser/browserBankDemo.ts:73-193`,
  `src/browser/browserBankDemo.ts:195-502`).
- **Dependencies:** three modules and two source edges, with no source cycle;
  two value edges, with no value cycle. Storage, current date, and print are
  supplied through one browser dependency object
  (`src/browser/browserBankDemo.ts:50-64`).
- **Seams:** all relevant effects are supplied: `Storage`, current-date text,
  and printing. The domain and UI are directly exercised with memory storage
  and captured print behavior (`src/browser/browserBankDemo.ts:50-64`,
  `src/browser/browserBankDemo.ts:81-113`).
- **Locality probes:** third account **scattered** across account identity
  recording, transfer direction, persistence records, and UI options
  (`src/domain/bank.ts:207-230`,
  `src/browser/browserBankDemo.ts:504-564`); date filtering **mixed** across
  domain range objects and browser conversion; persistence **mixed** across
  recorder classes and replay functions; money acceptance **mixed** between
  domain objects and browser parsing; print context **mixed** across display
  recorders and screen markup (`src/browser/browserBankDemo.ts:627-707`,
  `src/browser/browserBankDemo.ts:710-832`).

### Component judgments

- **Naming 2:** banking nouns are clear, but the recurring public
  `Recording` protocol is technical vocabulary at the domain boundary
  (`src/domain/bank.ts:22-52`).
- **KISS 1:** common flows repeatedly traverse outcomes and recorders, and the
  browser flow is a giant mixed unit (`src/domain/bank.ts:645-707`,
  `src/browser/browserBankDemo.ts:311-832`).
- **SRP 2:** behavioral owners are explicit despite file co-location, but the
  browser module retains rendering, formatting, persistence mapping, and
  replay (`src/browser/browserBankDemo.ts:195-832`).
- **Dependency direction 3:** the domain is runtime-independent, all external
  effects are supplied at the boundary, and both graphs are acyclic
  (`src/browser/browserBankDemo.ts:50-64`).
- **Change locality 1:** no more than one probe is localized, and account
  identity is repeated across unrelated domain and browser owners
  (`src/domain/bank.ts:207-230`,
  `src/browser/browserBankDemo.ts:504-564`).
- **Testability 3:** domain behavior, storage, date, print, and UI events are
  directly controllable or observable through supplied dependencies
  (`src/browser/browserBankDemo.ts:50-64`,
  `src/browser/browserBankDemo.ts:255-257`).

## artifact-05: spec-loop-calisthenics-incremental at d894853

### Common evidence packet

- **Vocabulary:** banking operations are identifiable, but `Paper`,
  `Activity`, `Acceptance`, and stored-record protocols are recurring technical
  or metaphorical boundary terms (`src/browser/bankActivityAcceptance.ts:4-40`,
  `src/browser/browserStatementPaper.ts:6-38`).
- **Flows:** `BankApplication` delegates accepted operations to
  `BankActivityAcceptance`, which changes domain accounts and separately saves
  activity records (`src/browser/bankApplication.ts:30-49`,
  `src/browser/bankActivityAcceptance.ts:13-40`). Transfer decomposes into an
  account withdrawal then deposit (`src/domain/accounts.ts:29-33`). Restore
  replays saved activity through `BrowserActivity.savedAccounts`; print/render
  writes statement lines onto a browser paper and calls `window.print`
  (`src/browser/browserActivity.ts:13-41`,
  `src/browser/bankStatements.ts:17-28`).
- **Responsibility owners:** domain account classes own transitions;
  `BankApplication` and `BankActivityAcceptance` share application commit;
  `BrowserBankStorage` owns persistence; `BankScreen` owns UI lifecycle;
  `BrowserStatementPaper` owns row formatting; `BankStatements` owns printing
  (`src/domain/account.ts:22-38`,
  `src/browser/bankActivityAcceptance.ts:13-40`,
  `src/browser/browserBankStorage.ts:6-27`,
  `src/browser/bankStatements.ts:7-37`).
- **Dependencies:** 51 modules and 159 source edges. One source-only cyclic
  component contains `account.ts`, `accountDeposit.ts`, and
  `accountWithdrawal.ts`. It is erased: 76 value edges contain no cyclic
  component. Browser printing remains concrete
  (`src/browser/bankStatements.ts:26-28`).
- **Seams:** raw `Storage` is constructor-supplied, while the application
  constructs the concrete storage, activity, and screen chain. Print is a
  fixed global (`src/browser/bankApplication.ts:15-19`,
  `src/browser/browserBankStorage.ts:6-13`,
  `src/browser/bankStatements.ts:26-28`).
- **Locality probes:** third account **scattered** across the two-account
  aggregate, `AccountName.other`, persistence, and choices
  (`src/domain/accountBook.ts:14-19`, `src/domain/accountName.ts:11-23`);
  exact-to-range filtering **scattered** across view choices, date wrappers,
  and the line filter (`src/browser/statementViewChoices.ts:24-44`,
  `src/browser/statementLineDate.ts:4-14`,
  `src/browser/statementLineDateValue.ts:3-27`); persistence **localized** in
  `BrowserBankStorage`; money acceptance **localized** in `EnteredMoney`
  (`src/browser/enteredMoney.ts:4-35`); print context **mixed** between
  `BankStatements` and `BrowserStatementPaper`.

### Component judgments

- **Naming 2:** bank concepts remain readable, but recurring `Paper` and
  `ActivityAcceptance` terms keep the public boundary from the score-3 anchor
  (`src/browser/bankActivityAcceptance.ts:4-40`,
  `src/browser/browserStatementPaper.ts:6-38`).
- **KISS 1:** common flows cross numerous policy-neutral activity, acceptance,
  record, paper, and tiny delegation objects
  (`src/browser/bankApplication.ts:30-49`,
  `src/browser/bankActivityAcceptance.ts:13-40`).
- **SRP 2:** responsibilities have named owners, while application commit is
  split between acceptance and activity objects and statement rendering owns
  printing (`src/browser/bankActivityAcceptance.ts:13-40`,
  `src/browser/bankStatements.ts:17-28`).
- **Dependency direction 2:** the source-only domain cycle is erased and does
  not justify a low score, but raw browser printing and concrete browser
  construction weaken the boundary (`src/browser/bankApplication.ts:15-19`,
  `src/browser/bankStatements.ts:26-28`).
- **Change locality 1:** two probes are scattered, matching the calibrated
  score-1 aggregation rule (`src/domain/accountName.ts:11-23`,
  `src/browser/statementLineDateValue.ts:3-27`).
- **Testability 2:** storage is controllable and core objects are callable, but
  printing and the concrete browser object chain remain awkward boundaries
  (`src/browser/bankApplication.ts:15-19`,
  `src/browser/bankStatements.ts:26-28`).

## artifact-06: spec-loop-base-backlog-steered at ae1eb4b

### Common evidence packet

- **Vocabulary:** `DemoBank`, `BankAccount`, `BankPostingResult`, `Money`,
  statement filters, and ports use consistent banking and application terms
  (`src/domain/demoBank.ts:6-112`, `src/domain/bankAccount.ts:8-140`,
  `src/application/ports.ts:3-18`).
- **Flows:** `DemoBankApplication` parses and posts through `DemoBank`, then
  saves before replacing application state
  (`src/application/demoBankApplication.ts:64-88`,
  `src/application/demoBankApplication.ts:118-147`). Transfer has explicit
  source and target accounts in `DemoBank` (`src/domain/demoBank.ts:41-77`).
  Restore occurs at application start through `BankStateStore`; print refreshes
  the view context before invoking `StatementPrinter`
  (`src/application/demoBankApplication.ts:50-61`,
  `src/application/demoBankApplication.ts:109-111`).
- **Responsibility owners:** `BankAccount` owns account transitions;
  `DemoBankApplication` owns commit state; `BankStateStore` and its local
  adapter own persistence; `BankScreen` owns UI lifecycle; `Money`, statement
  context, and screen rows own formatting; `StatementPrinter` owns printing
  (`src/domain/bankAccount.ts:32-140`,
  `src/application/demoBankApplication.ts:37-173`,
  `src/application/ports.ts:3-18`, `src/ui/bankScreen.ts:8-218`).
- **Dependencies:** 13 modules and 28 source edges, with no source cycle; 17
  value edges, with no value cycle. Store, clock, identifier generator,
  printer, and screen enter through application ports
  (`src/application/demoBankApplication.ts:42-48`).
- **Seams:** every relevant external effect has a narrow port. Application
  tests use recording store, printer, and screen implementations, including a
  failing save (`src/application/ports.ts:3-18`,
  `src/application/demoBankApplication.test.ts:10-75`,
  `src/application/demoBankApplication.test.ts:115-138`).
- **Locality probes:** third account **localized** in the domain account
  catalog with contract-consequential storage validation edits
  (`src/domain/demoBank.ts:3-30`,
  `src/infrastructure/localStorageBankStateStore.ts:9-116`); date filtering
  **localized** in the statement filter; persistence **localized** behind
  `BankStateStore`; money acceptance **localized** in `Money`; print context
  **localized** in `StatementPrintContext`
  (`src/domain/statementFilter.ts:1-40`, `src/domain/money.ts:17-55`,
  `src/application/statementPrintContext.ts:3-31`).

### Component judgments

- **Naming 3:** public boundaries consistently expose banking concepts and
  explicit external roles (`src/domain/demoBank.ts:22-112`,
  `src/application/ports.ts:3-18`).
- **KISS 2:** common flows are direct, with limited two-level result wrapping
  between `BankAccount`, `DemoBank`, and the application
  (`src/domain/bankAccount.ts:16-30`, `src/domain/demoBank.ts:6-20`).
- **SRP 3:** domain policy, commit state, persistence, UI lifecycle,
  formatting, and printing have explicit owners and narrow contracts
  (`src/application/demoBankApplication.ts:37-173`,
  `src/application/ports.ts:3-18`).
- **Dependency direction 3:** the domain is runtime-independent, effects point
  through ports, and both graphs are acyclic
  (`src/application/demoBankApplication.ts:42-48`).
- **Change locality 3:** all five probes have a primary owner and none is
  scattered (`src/domain/demoBank.ts:3-30`,
  `src/application/statementPrintContext.ts:3-31`).
- **Testability 3:** core, commit failure, time, identifiers, storage, print,
  and UI behavior are directly controllable and observable
  (`src/application/demoBankApplication.test.ts:10-75`,
  `src/application/demoBankApplication.test.ts:90-138`).

## artifact-07: superpowers-5.4 at 5db5d24

### Common evidence packet

- **Vocabulary:** operations and statement filters are clear, while the domain
  API is composed of generic record, command, result, and snapshot types
  (`src/domain/bankTypes.ts:1-40`,
  `src/domain/bankOperations.ts:35-177`).
- **Flows:** pure operation functions return snapshots; `useBankApp` supplies
  current time, commits state, and saves through a repository
  (`src/domain/bankOperations.ts:62-177`,
  `src/application/useBankApp.ts:16-74`). Transfer updates both account records
  in one pure result. Restore is the hook's repository initializer. Rendering
  is split across React components; `App` supplies `window.print`
  (`src/application/useBankApp.ts:16-17`, `src/app/App.tsx:21-44`).
- **Responsibility owners:** operation functions own transitions; `useBankApp`
  owns application state and commit; `BankRepository` owns persistence;
  components own UI lifecycle; formatters and `StatementPanel` own display
  text; `App` owns the print effect (`src/application/useBankApp.ts:16-92`,
  `src/infrastructure/bankRepository.ts:3-5`,
  `src/ui/components/StatementPanel.tsx:17-76`).
- **Dependencies:** 15 modules and 28 source edges, with no source cycle; 15
  value edges, with no value cycle. The hook and statement component directly
  read current time, and `App` directly prints
  (`src/application/useBankApp.ts:31-66`,
  `src/ui/components/StatementPanel.tsx:34`, `src/app/App.tsx:44`).
- **Seams:** the hook accepts a repository and the statement component accepts
  `onPrint`; current time in the hook and printed metadata are fixed globals
  (`src/application/useBankApp.ts:16-35`,
  `src/ui/components/StatementPanel.tsx:4-34`).
- **Locality probes:** third account **scattered** across the ID union, fixed
  catalog, snapshot constructor, and account cards
  (`src/domain/bankTypes.ts:1-22`, `src/domain/fixedAccounts.ts:3-6`,
  `src/domain/bankSnapshot.ts:4-19`,
  `src/ui/components/AccountCards.tsx:10-29`); date filtering **localized** in
  the operation filter plus UI contract; persistence **localized** behind the
  repository; money acceptance **localized** in `bankOperations`; print context
  **localized** in `StatementPanel`
  (`src/domain/bankOperations.ts:26-33`,
  `src/domain/bankOperations.ts:179-202`,
  `src/ui/components/StatementPanel.tsx:17-34`).

### Component judgments

- **Naming 2:** banking terms are readable, but generic snapshots, commands,
  and results dominate the public model (`src/domain/bankTypes.ts:1-40`).
- **KISS 2:** operation-to-hook-to-component flows are readily traceable, with
  a broad hook but limited ceremony (`src/application/useBankApp.ts:16-92`).
- **SRP 2:** main roles are separated, while the hook owns state, current time,
  operations, commit, filtering, and selection
  (`src/application/useBankApp.ts:16-92`).
- **Dependency direction 2:** the domain is pure and acyclic, but application
  and presentation code contain concrete time and print assumptions
  (`src/application/useBankApp.ts:31-66`, `src/app/App.tsx:44`).
- **Change locality 2:** four probes are localized, but the third-account
  policy is scattered (`src/domain/bankSnapshot.ts:4-19`,
  `src/ui/components/AccountCards.tsx:10-29`).
- **Testability 2:** repository and component print behavior are substitutable,
  while current time is embedded at two important boundaries
  (`src/application/useBankApp.ts:31-66`,
  `src/ui/components/StatementPanel.tsx:34`).

## artifact-08: spec-loop-calisthenics-single-task at b708bd2

### Common evidence packet

- **Vocabulary:** core bank nouns are recognizable, but `Change`, `Outcome`,
  `Continuation`, and `Record` protocols recur across the public transition
  API (`src/bank/Bank.ts:27-41`, `src/bank/AccountsChange.ts:7-82`,
  `src/bank/BankOutcome.ts:5-45`, `src/bank/BankRecord.ts:1-49`).
- **Flows:** `Bank` delegates to `Accounts`, which creates account changes and
  converts them into outcomes (`src/bank/Bank.ts:27-41`,
  `src/bank/Accounts.ts:23-47`). Transfer continues through source and target
  change objects and restores original accounts on refusal
  (`src/bank/Accounts.ts:31-33`, `src/bank/AccountsChange.ts:37-81`). Restore
  replays a recorded snapshot through continuations
  (`src/browser/bankStorage.ts:14-80`,
  `src/browser/bankSnapshot.ts:43-109`). The browser application renders the
  statement before invoking an injected printer
  (`src/browser/browserBankApplication.ts:81-90`).
- **Responsibility owners:** `Bank`, `Accounts`, account books, and their change
  types jointly own transitions; `BrowserBankApplication` owns application
  commit and UI lifecycle; `BankStorage` owns persistence; snapshot recorders
  and `statementTable` own formatting; `StatementPrinter` owns printing
  (`src/browser/browserBankApplication.ts:18-90`,
  `src/browser/bankStorage.ts:14-35`,
  `src/browser/bankSnapshot.ts:43-234`).
- **Dependencies:** 42 modules and 195 source edges. One source cyclic component
  contains these 31 domain modules:
  - `Account.ts`, `AccountAction.ts`, `AccountBook.ts`,
    `AccountBookChange.ts`, `AccountChange.ts`, `AccountName.ts`,
    `AccountStanding.ts`, and `AccountStatement.ts`;
  - `Accounts.ts`, `AccountsChange.ts`, `Balance.ts`, `Bank.ts`,
    `BankContinuation.ts`, `BankDate.ts`, `BankOutcome.ts`, `BankRecord.ts`,
    `BankRefusal.ts`, `ClosedAccount.ts`, and `DatedPosting.ts`;
  - `Deposit.ts`, `Money.ts`, `OpenAccount.ts`, `Posting.ts`,
    `StatementDateChoice.ts`, `StatementEntries.ts`, `StatementEntry.ts`, and
    `StatementFilter.ts`; and
  - `TransferParties.ts`, `TransferRequest.ts`, `TransferValue.ts`, and
    `Withdrawal.ts`.
  After type erasure, 66 value edges retain one cyclic component containing
  `AccountBook.ts`, `AccountBookChange.ts`, `AccountChange.ts`, `Accounts.ts`,
  `AccountsChange.ts`, `Bank.ts`, `ClosedAccount.ts`, and `OpenAccount.ts`.
  This is the only material value cycle in the corpus.
- **Seams:** storage and printing use interfaces supplied to the browser
  application. Default form dates use `new Date` directly; domain tests must
  observe results through continuation/record protocols
  (`src/browser/browserBankApplication.ts:18-35`,
  `src/browser/browserBankApplication.ts:157-161`,
  `src/bank/BankOutcome.ts:5-45`).
- **Locality probes:** third account **scattered** across account identity,
  defaults, forms, and snapshot recording
  (`src/bank/Accounts.ts:66-73`, `src/browser/bankForms.ts:40-45`);
  exact-to-range filtering **localized** around `StatementDateChoice` with UI
  contract edits (`src/bank/StatementDateChoice.ts:1-4`,
  `src/browser/bankForms.ts:47-77`); persistence **localized** behind
  `BankStorage`; money acceptance **localized** in `Money`
  (`src/bank/Money.ts:18-76`); print context **mixed** between the browser
  application, markup, and statement table.

### Component judgments

- **Naming 2:** bank concepts are readable, but recurring technical transition
  protocols prevent a coherent score-3 domain boundary
  (`src/bank/AccountsChange.ts:7-82`, `src/bank/BankOutcome.ts:5-45`).
- **KISS 1:** common flows repeatedly cross change, outcome, continuation, and
  record objects (`src/bank/Bank.ts:27-41`,
  `src/bank/AccountsChange.ts:7-82`).
- **SRP 2:** technical roles are separated, but transition ownership overlaps
  across the eight modules in the material value cycle
  (`src/bank/Accounts.ts:23-47`, `src/bank/AccountsChange.ts:7-82`).
- **Dependency direction 1:** the material eight-module value cycle makes
  transition ownership bidirectional across bank, account aggregate, and
  change types. The score is not based on the historical “32 cycles” count.
- **Change locality 2:** date, persistence, and money have primary owners;
  account identity is scattered and print context is mixed
  (`src/browser/bankForms.ts:47-77`, `src/browser/bankStorage.ts:14-35`,
  `src/bank/Money.ts:18-76`).
- **Testability 2:** storage and print are substitutable, but time and the
  continuation/record observation path remain awkward
  (`src/browser/browserBankApplication.ts:18-35`,
  `src/bank/BankOutcome.ts:5-45`).

## artifact-09: spec-loop-incremental at 4cd947e

### Common evidence packet

- **Vocabulary:** the functional records, `BankStore`, account IDs, statement
  entries, and validation errors are clear but generic
  (`src/bank.ts:3-65`, `src/bank.ts:74-232`).
- **Flows:** `BankApp` parses an operation and calls `BankStore`; the store
  constructs next state, saves it, then commits it
  (`src/app.ts:199-231`, `src/bank.ts:104-218`). Transfer uses an implicit
  “other account” helper and rollback-safe save ordering
  (`src/bank.ts:112-175`, `src/bank.ts:271-273`). Restore happens in the store
  constructor. Render and raw print are owned by `BankApp`
  (`src/bank.ts:77-82`, `src/app.ts:42-178`).
- **Responsibility owners:** `BankStore` owns transitions and application
  commit; `LocalBankStorage` owns persistence; `BankApp` owns UI lifecycle,
  filtering, and printing; bank helper functions own money/date formatting
  (`src/bank.ts:74-311`, `src/storage.ts:14-35`,
  `src/app.ts:29-330`).
- **Dependencies:** four modules and five source edges, with no source cycle;
  five value edges, with no value cycle. Storage and clock are injected, while
  print remains a UI global (`src/bank.ts:33-40`, `src/bank.ts:77-82`,
  `src/app.ts:176-178`).
- **Seams:** `BankStore` accepts storage and clock interfaces; UI tests use a
  fixed clock and can spy on storage failure and `window.print`
  (`src/app.test.ts:15-22`, `src/app.test.ts:279-297`,
  `src/app.test.ts:512-562`).
- **Locality probes:** third account **mixed** because identity is centralized
  but transfer assumes one implicit destination
  (`src/bank.ts:42-50`, `src/bank.ts:271-273`); date filtering **localized**
  in `BankApp`; persistence **localized** behind `BankStorage`; money
  acceptance **localized** in `parseMoneyToCents` and the store invariant;
  print context **localized** in the statement area with a composition-time
  clock consequence (`src/app.ts:261-327`, `src/bank.ts:275-310`).

### Component judgments

- **Naming 2:** operations are clear, with generic records and store language
  rather than a fully modeled banking API (`src/bank.ts:13-40`).
- **KISS 2:** the app-to-store flows are direct despite two broad units
  (`src/app.ts:199-231`, `src/bank.ts:104-218`).
- **SRP 2:** persistence and UI are separated, but `bank.ts` combines domain
  rules, application state, ports, clock, parsing, and formatting
  (`src/bank.ts:33-311`).
- **Dependency direction 2:** graphs are acyclic and main effects are injected,
  while system clock and raw printing are concrete environment assumptions
  (`src/bank.ts:68-82`, `src/app.ts:176-178`).
- **Change locality 3:** four probes are localized and the account probe is
  mixed rather than scattered (`src/bank.ts:42-50`,
  `src/app.ts:261-327`).
- **Testability 3:** storage, clock, commit failure, UI events, and printing are
  directly controllable or mockable in tests (`src/app.test.ts:15-22`,
  `src/app.test.ts:279-297`, `src/app.test.ts:512-562`).

## artifact-10: spec-loop-base-backlog-prompted at 4b9f8aa

### Common evidence packet

- **Vocabulary:** account, movement, transfer, statement, filter, and storage
  terms are clear, while the model uses functional records and form-input
  contracts (`src/bankState.ts:1-48`, `src/moneyMovement.ts:12-47`,
  `src/transfer.ts:11-47`).
- **Flows:** `startBankApp` parses form input, applies a pure movement or
  transfer, saves, and renders (`src/main.ts:92-143`). Transfer saves the result
  before assigning live state (`src/main.ts:115-143`). Restore is
  `loadBankState` at startup. Rendering is a separate function and print is a
  supplied callback (`src/main.ts:40-61`, `src/bankStorage.ts:12-25`,
  `src/app.ts:21-32`).
- **Responsibility owners:** movement and transfer functions own transitions;
  `startBankApp` owns application commit; `bankStorage` owns persistence;
  `renderBankApp` owns UI lifecycle; money/date/entry helpers own formatting;
  the supplied `printStatements` callback owns printing
  (`src/moneyMovement.ts:47-134`, `src/transfer.ts:47-166`,
  `src/main.ts:40-146`, `src/app.ts:32-423`).
- **Dependencies:** nine modules and 17 source edges, with no source cycle; 15
  value edges, with no value cycle. Storage, time, IDs, and printing are
  supplied to `startBankApp` (`src/main.ts:40-46`).
- **Seams:** tests supply `Storage`, current time, entry IDs, and print
  function,
  including a failing-storage implementation
  (`src/main.test.ts:225-278`, `src/main.test.ts:434-454`,
  `src/main.test.ts:531-557`).
- **Locality probes:** third account **mixed** because `ACCOUNT_ORDER` drives
  most behavior while the persisted record remains fixed
  (`src/bankState.ts:37-55`, `src/bankState.ts:104-109`); date filtering
  **localized** in `statementFilters`; persistence **localized** in
  `bankStorage`; money acceptance **localized** in `money`; print context
  **localized** in `renderBankApp` with a supplied print callback
  (`src/statementFilters.ts:12-70`, `src/money.ts:8-31`,
  `src/app.ts:151-225`).

### Component judgments

- **Naming 2:** the functional API is readable, with generic record and form
  contracts rather than a coherent object domain API
  (`src/bankState.ts:18-45`, `src/transfer.ts:11-47`).
- **KISS 2:** operations are direct pure functions and the broad orchestrator
  remains readily traceable (`src/main.ts:92-143`).
- **SRP 2:** domain functions, storage, and rendering are separated, while
  `startBankApp` retains all application event and commit coordination
  (`src/main.ts:40-146`).
- **Dependency direction 3:** domain modules are runtime-independent, effects
  are supplied at composition, and both graphs are acyclic
  (`src/main.ts:40-46`).
- **Change locality 3:** four probes are localized and account addition is
  mixed but not scattered (`src/bankState.ts:48-109`,
  `src/statementFilters.ts:12-70`).
- **Testability 3:** all important effects and application commits are directly
  controllable and observable (`src/main.test.ts:225-278`,
  `src/main.test.ts:434-454`).

## artifact-11: superpowers at 58bcb54

### Common evidence packet

- **Vocabulary:** bank operations and statement filters are clear, while state
  and results use generic records (`src/domain/types.ts:1-32`,
  `src/domain/bankOperations.ts:4-144`).
- **Flows:** controller form handlers parse input and invoke pure domain
  operations with supplied time and identifiers, then save and render
  (`src/ui/appController.ts:64-175`). Transfer clones state and records both
  entries (`src/domain/bankOperations.ts:81-150`). Restore loads through the
  injected repository when controller state is created. Render and print are
  in the same controller (`src/ui/appController.ts:24-60`,
  `src/ui/appController.ts:178-382`).
- **Responsibility owners:** operation functions own transitions; the
  controller owns application commit, UI lifecycle, and printing; repository
  functions own persistence; money and filter helpers own formatting and
  selection (`src/domain/bankOperations.ts:15-150`,
  `src/storage/localStorageBankRepository.ts:6-35`,
  `src/ui/appController.ts:24-382`).
- **Dependencies:** nine modules and 15 source edges, with no source cycle; ten
  value edges, with no value cycle. Time and identifiers are supplied, but
  print is a direct browser call (`src/main.ts:14-16`,
  `src/ui/appController.ts:59-61`).
- **Seams:** the controller accepts repository, time, identifier, and transfer
  failure hook through `BankAppOptions`; print lacks such a seam
  (`src/domain/types.ts:29-32`, `src/ui/appController.ts:11-26`,
  `src/ui/appController.test.ts:33-38`).
- **Locality probes:** third account **scattered** across ID types, clone logic,
  seeded state, balances, and repeated form options
  (`src/domain/types.ts:1-3`, `src/domain/bankOperations.ts:144-150`,
  `src/ui/appController.ts:188-229`,
  `src/ui/appController.ts:277-319`); date filtering **localized** in
  `statementFilters`; persistence **localized** behind `BankRepository`; money
  acceptance **localized** in `money`; print context **localized** in the
  statement-rendering area, with a browser-print consequence
  (`src/domain/statementFilters.ts:12-51`, `src/domain/money.ts:5-30`,
  `src/ui/appController.ts:227-273`).

### Component judgments

- **Naming 2:** operations are explicit, with generic record and result
  boundaries (`src/domain/types.ts:7-32`).
- **KISS 2:** pure domain flows are direct, while one broad controller retains
  the complete application/UI flow (`src/ui/appController.ts:24-175`).
- **SRP 2:** domain and storage are separate, but the controller owns UI
  lifecycle, state, parsing, commit, formatting coordination, and printing
  (`src/ui/appController.ts:24-382`).
- **Dependency direction 2:** domain direction and graphs are clean, but raw
  browser printing remains inside the application/UI owner
  (`src/ui/appController.ts:59-61`).
- **Change locality 2:** four probes are localized, but adding an account is
  scattered (`src/domain/bankOperations.ts:144-150`,
  `src/ui/appController.ts:188-319`).
- **Testability 2:** domain, storage, time, IDs, commit, and UI are directly
  controllable, while printing remains an awkward global boundary
  (`src/ui/appController.ts:11-26`, `src/ui/appController.ts:59-61`).

## artifact-12: open-spec at 8c980c0

### Common evidence packet

- **Vocabulary:** account, transaction, transfer, statement, filter, storage,
  and controller terms are clear, while the domain uses generic records and
  string-union types (`src/domain/bank.ts:4-38`,
  `src/domain/statements.ts:3-19`).
- **Flows:** the browser controller parses input, invokes pure deposit,
  withdrawal, or transfer functions, saves, and renders
  (`src/ui/browserController.ts:90-129`). Transfer composes debit and credit
  steps with rollback behavior (`src/domain/transfers.ts:18-63`). Restore loads
  through `LocalBankStorage` in the controller constructor. Render filters
  statement entries and the print button invokes a supplied function
  (`src/ui/browserController.ts:53-70`,
  `src/ui/browserController.ts:145-174`).
- **Responsibility owners:** domain functions own transitions; the controller
  owns application commit and UI lifecycle; `LocalBankStorage` owns
  persistence; money and statement functions plus controller rows own
  formatting; the supplied print function owns printing
  (`src/domain/transactions.ts:17-95`,
  `src/domain/transfers.ts:18-63`,
  `src/storage/bankStorage.ts:18-63`,
  `src/ui/browserController.ts:45-174`).
- **Dependencies:** ten modules and 23 source edges, with no source cycle; 22
  value edges, with no value cycle. Domain transactions nevertheless default
  directly to `Date`, `crypto`, `Date.now`, and `Math.random`
  (`src/domain/transactions.ts:80-95`).
- **Seams:** operation context can supply time and IDs; storage wraps a supplied
  `KeyValueStorage`; printing is supplied; UI tests drive events with memory
  storage and captured print (`src/domain/transactions.ts:12-17`,
  `src/storage/bankStorage.ts:18-38`,
  `test/ui/browserController.test.ts:69-104`).
- **Locality probes:** third account **mixed** across central account IDs,
  fixed state shape, storage normalization, and controller balance elements
  (`src/domain/bank.ts:4-70`, `src/storage/bankStorage.ts:81-105`,
  `src/ui/browserController.ts:180-202`); date filtering **localized** in
  `statements`; persistence **localized** behind `KeyValueStorage`; money
  acceptance **localized** in `money`; print context **mixed** between layout
  and controller statement state (`src/domain/statements.ts:3-50`,
  `src/domain/money.ts:7-54`, `src/ui/appView.ts:43-89`).

### Component judgments

- **Naming 2:** bank terms are clear, with generic record and primitive union
  contracts rather than a fully modeled public vocabulary
  (`src/domain/bank.ts:13-38`).
- **KISS 2:** pure domain functions are direct, while the browser controller is
  one broad but traceable flow (`src/ui/browserController.ts:45-174`).
- **SRP 2:** domain and storage responsibilities are separate, but the
  controller owns UI lifecycle, application state, commit, status, filtering,
  and row formatting (`src/ui/browserController.ts:45-174`,
  `src/ui/browserController.ts:233-285`).
- **Dependency direction 2:** graphs are acyclic and browser effects are
  supplied, but domain code is not runtime-independent because its default
  operation context reads time and identifiers from globals
  (`src/domain/transactions.ts:80-95`).
- **Change locality 2:** date, persistence, and money are localized; account
  shape and print context are mixed (`src/domain/bank.ts:4-70`,
  `src/ui/appView.ts:43-89`).
- **Testability 3:** time, identifiers, storage, printing, UI events, and
  application commits are directly controllable or observable
  (`test/ui/browserController.test.ts:8-104`,
  `test/storage/bankStorage.test.ts:6-89`).

## Completeness check

| Artifact | Scores | Flows | Owners | Graphs | Probes | Seams |
|---|---:|---:|---:|---:|---:|---:|
| artifact-01 | yes | yes | yes | yes | yes | yes |
| artifact-02 | yes | yes | yes | yes | yes | yes |
| artifact-03 | yes | yes | yes | yes | yes | yes |
| artifact-04 | yes | yes | yes | yes | yes | yes |
| artifact-05 | yes | yes | yes | yes | yes | yes |
| artifact-06 | yes | yes | yes | yes | yes | yes |
| artifact-07 | yes | yes | yes | yes | yes | yes |
| artifact-08 | yes | yes | yes | yes | yes | yes |
| artifact-09 | yes | yes | yes | yes | yes | yes |
| artifact-10 | yes | yes | yes | yes | yes | yes |
| artifact-11 | yes | yes | yes | yes | yes | yes |
| artifact-12 | yes | yes | yes | yes | yes | yes |

The completeness table verifies packet presence, not correctness. The
reverse-order pass below repeats scoring and reconciles disagreements against
these citations before rankings are calculated.

## Reverse-order consistency check

The same evaluator rescored the artifacts from artifact-12 back to artifact-01
without consulting the published totals. This is a stability check, not an
independent review or an inter-rater reliability measurement.

| Artifact | First total | Reverse total | Difference |
|---|---:|---:|---|
| artifact-12 | 13 | 13 | none |
| artifact-11 | 12 | 12 | none |
| artifact-10 | 15 | 15 | none |
| artifact-09 | 14 | 13 | locality 3 to 2 |
| artifact-08 | 10 | 10 | none |
| artifact-07 | 12 | 12 | none |
| artifact-06 | 17 | 17 | none |
| artifact-05 | 10 | 10 | none |
| artifact-04 | 12 | 12 | none |
| artifact-03 | 10 | 9 | direction 2 to 1 |
| artifact-02 | 9 | 9 | none |
| artifact-01 | 14 | 14 | none |

The raw same-evaluator component agreement is 70/72, or 97.2%. Ten of 12
artifact totals were reproduced exactly. Neither disagreement spans more than
one point.

### artifact-09: spec-loop-incremental at 4cd947e

The first pass treated money acceptance as localized. Re-reading the source
shows that `parseMoneyToCents` and `assertPositiveCents` both decide whether an
amount is positive and integral, while only the parser checks safe-integer and
text precision (`src/bank.ts:275-310`). A money-policy change therefore has two
coordinated policy sites and is **mixed**, not localized. Adding a third account
is also mixed because transfer selects an implicit “other account” through
`getDestinationAccountId` (`src/bank.ts:112-118`, `src/bank.ts:271-273`). Date
filtering, persistence, and print context remain localized. Three localized and
two mixed probes match locality score 2. The reverse score is retained; a range
is unnecessary because the first classification conflicts with the rule
against duplicated decisions.

### artifact-03: gsd-small-feature at aef38ff

The first pass treated direct time and identifier use as a score-2 environment
assumption. Re-reading the transition functions shows a stronger coupling:
deposit and withdrawal default to `new Date`, transfer calls `nextId`, and
`createEntry` calls it again (`src/bank/bank.ts:63-76`,
`src/bank/bank.ts:92-110`, `src/bank/bank.ts:126-178`,
`src/bank/bank.ts:244-264`). `nextId` directly uses `crypto`, `Date.now`, and
`Math.random`, with no identifier seam (`src/bank/bank.ts:311-312`). Optional
operation dates do not isolate identifier generation. Domain policy and
external effects are therefore coupled, matching dependency score 1. This is
stricter than artifact-12, whose otherwise similar global defaults can be
replaced through the `OperationContext` seam documented in its evidence packet.

## Reconciled design results

| Solution | Name | KISS | SRP | Dep. | Local. | Test | Total |
|---|---:|---:|---:|---:|---:|---:|---:|
| `open-spec-calisthenics` | 3 | 2 | 3 | 3 | 1 | 2 | 14 |
| `superpowers-calisthenics` | 2 | 1 | 2 | 2 | 1 | 1 | 9 |
| `gsd-small-feature` | 2 | 2 | 1 | 1 | 2 | 1 | 9 |
| `spec-loop-calisthenics` | 2 | 1 | 2 | 3 | 1 | 3 | 12 |
| `spec-loop-calisthenics-incremental` | 2 | 1 | 2 | 2 | 1 | 2 | 10 |
| `spec-loop-base-backlog-steered` | 3 | 2 | 3 | 3 | 3 | 3 | 17 |
| `superpowers-5.4` | 2 | 2 | 2 | 2 | 2 | 2 | 12 |
| `spec-loop-calisthenics-single-task` | 2 | 1 | 2 | 1 | 2 | 2 | 10 |
| `spec-loop-incremental` | 2 | 2 | 2 | 2 | 2 | 3 | 13 |
| `spec-loop-base-backlog-prompted` | 2 | 2 | 2 | 3 | 3 | 3 | 15 |
| `superpowers` | 2 | 2 | 2 | 2 | 2 | 2 | 12 |
| `open-spec` | 2 | 2 | 2 | 2 | 2 | 3 | 13 |

The reconciliation changes 17 of the 72 published component judgments across
nine artifacts. Eight aggregate totals change. No final component needs a
range: each reverse-pass difference was resolved by a calibrated rule that the
first pass had applied incorrectly. This is still one AI evaluator's judgment,
not human validation.

## Strict calisthenics instruction audit

The five calisthenics artifacts were audited separately against the exact ten
requested source constraints. The original prompt and explicit user
clarifications are the authority. A solution-authored design, exception, or
verifier can document intent but cannot weaken a requested constraint.

The common operational boundary is production domain code. Wrapper internals
may store raw values, and adapters may construct wrappers, but public domain
methods and domain ports may not expose raw domain-valued numbers or strings.
Boolean predicate results are control decisions rather than exposed domain
values. “No getters/setters/properties” includes accessor-shaped methods, not
only TypeScript `get` and `set` syntax. “One dot per line” is applied
literally to domain expressions. One nested control or callback indentation
level is allowed within a method; deeper nesting fails. A constraint passes
only when the audit found no material production-domain violation.

| Rank | Solution | Domain-only names | Indentation | No `else` | Wrapped values | First-class collections | One dot | Full names | Entities at most 50 lines | At most 2 fields | No accessors | Passed |
|---:|---|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|---:|
| 1 | `open-spec-calisthenics` | ✓ | ✓ | ✓ | ✗ | ✓ | ✓ | ✓ | ✓ | ✓ | ✗ | 8/10 |
| 1 | `spec-loop-calisthenics-single-task` | ✗ | ✓ | ✓ | ✗ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | 8/10 |
| 2 | `spec-loop-calisthenics` | ✗ | ✓ | ✓ | ✗ | ✓ | ✓ | ✓ | ✓ | ✓ | ✗ | 7/10 |
| 2 | `spec-loop-calisthenics-incremental` | ✓ | ✓ | ✓ | ✗ | ✓ | ✗ | ✓ | ✓ | ✓ | ✗ | 7/10 |
| 2 | `superpowers-calisthenics` | ✓ | ✓ | ✓ | ✗ | ✓ | ✗ | ✓ | ✓ | ✓ | ✗ | 7/10 |

The rank is the unweighted pass count. Equal totals remain tied; design scores,
behavior evidence, and the breadth of an individual violation are not
tie-breakers.

### Violation evidence

- **`open-spec-calisthenics`:** `AccountName.text()` and
  `Money.centsAmount()` expose stored `string` and `number` values
  (`src/domain/account-name.ts:48-49`, `src/domain/money.ts:72-73`). Account and
  statement APIs repeat the same accessor pattern
  (`src/domain/account-book.ts:45-56`). This fails wrapped values and no
  accessors. The architecture verifier does not test indentation and detects
  only TypeScript accessor syntax, so its passing result is not full-compliance
  evidence.
- **`spec-loop-calisthenics-single-task`:** generic `Change`, `Outcome`,
  `Continuation`, and `Record` protocols are part of the public domain API
  (`src/bank/AccountChange.ts:8-43`, `src/bank/BankContinuation.ts:4-6`,
  `src/bank/BankOutcome.ts:5-45`, `src/bank/BankRecord.ts:12-57`). Recorder
  methods expose raw account-name, date, money, and refusal values
  (`src/bank/BankRecord.ts:44-57`). This fails domain-only names and wrapped
  values. Its source test permits recorder-method primitive crossings, but
  that solution-authored exception was not in the prompt.
- **`spec-loop-calisthenics`:** `Recording` protocols are technical concepts
  throughout the domain boundary, and the first two ports accept raw
  primitives (`src/domain/bank.ts:22-50`). `AccountOutcome.accountAfterOutcome`
  and `BankOutcome.bankAfterOutcome` return stored state
  (`src/domain/bank.ts:645-689`). This fails domain-only names, wrapped values,
  and no accessors.
- **`spec-loop-calisthenics-incremental`:** primitive representation methods
  include `AccountNameText.asString()` and `Cents.asNumber()`
  (`src/domain/accountNameText.ts:14-16`, `src/domain/cents.ts:40-42`), while
  `Entry.endingBalance()` and several `date()` methods are accessor-shaped
  (`src/domain/entry.ts:14-16`, `src/domain/datedMoney.ts:24-26`). Literal
  one-dot violations include `this.name.other()` and
  `this.accounts.map(...)` (`src/domain/accountTransfer.ts:24`,
  `src/domain/accountBook.ts:32-42`).
- **`superpowers-calisthenics`:** `Money.cents()` returns the stored number and
  `StatementRecord` exposes raw string/number fields
  (`src/domain/Money.ts:40-42`, `src/domain/StatementEntry.ts:5-9`). Account and
  statement methods repeatedly return those values
  (`src/domain/AccountBook.ts:34-41`). Literal one-dot violations occur across
  account and statement expressions, including
  `this.balance.cents()` and `this.entries.map(...)`
  (`src/domain/AccountBook.ts:34-41`, `src/domain/StatementEntries.ts:20-25`).

Automated source checks remain useful implementation evidence, but they are
not a separate behavior category and do not override this manual prompt-level
audit.

## Non-calisthenics design ranks

Dense ranks are calculated only among the seven unconstrained solutions. Equal
totals share a rank and the next distinct total receives the next rank.

| Rank | Solution | Total |
|---:|---|---:|
| 1 | `spec-loop-base-backlog-steered` | 17 |
| 2 | `spec-loop-base-backlog-prompted` | 15 |
| 3 | `open-spec` | 13 |
| 3 | `spec-loop-incremental` | 13 |
| 4 | `superpowers` | 12 |
| 4 | `superpowers-5.4` | 12 |
| 5 | `gsd-small-feature` | 9 |

## Calisthenics design results, unranked

The conventional design rubric remains useful for describing the effect of the
condition, but these totals do not rank the calisthenics artifacts and do not
break instruction-following ties.

| Solution | Name | KISS | SRP | Dep. | Local. | Test | Total |
|---|---:|---:|---:|---:|---:|---:|---:|
| `open-spec-calisthenics` | 3 | 2 | 3 | 3 | 1 | 2 | 14 |
| `spec-loop-calisthenics` | 2 | 1 | 2 | 3 | 1 | 3 | 12 |
| `spec-loop-calisthenics-incremental` | 2 | 1 | 2 | 2 | 1 | 2 | 10 |
| `spec-loop-calisthenics-single-task` | 2 | 1 | 2 | 1 | 2 | 2 | 10 |
| `superpowers-calisthenics` | 2 | 1 | 2 | 2 | 1 | 1 | 9 |

The constrained group has mean 11.0 and median 10, versus mean 13.0 and
median 13 for the non-calisthenics group. The lower constrained results are
concentrated in simplicity and locality. OpenSpec is the counterexample to a
framework-by-framework claim: its constrained artifact scores 14 versus 13 for
its control, but it fails wrapped values and no-accessor compliance. It cannot
estimate the design effect of fully applying the constraint set. The observed
group difference is descriptive, not a causal estimate.

## Non-calisthenics overall-ranking sensitivity

The paper's overall ranking is deliberately qualitative and non-arithmetic.
Correcting design scores therefore cannot mechanically determine an overall
order. Reapplying the documented sequence to the seven non-calisthenics
solutions supports:

1. `spec-loop-base-backlog-steered` and
   `spec-loop-base-backlog-prompted`;
2. `spec-loop-incremental`;
3. `open-spec`;
4. `superpowers`;
5. `superpowers-5.4`; and
6. `gsd-small-feature`.

The first group shares a rank because the process and behavior profiles match;
the steered run has stronger design but more author-intervention confounding.
The remaining boundaries follow the documented process, safety-category, and
close-result design sequence. A steering-dominated ranking can move
Superpowers upward, while a traceability-dominated ranking can strengthen Spec
Loop. A finer or different order would require weights not used in the study.
