# Review guidance for `assess-pull-request`

This skill reuses the `plan-task` skill bundle for shared writing
conventions, glossary policy, section semantics, diagram separation, and
supporting examples.

This file defines the retrospective review guidance and overrides.
It is the authoritative source for review behavior, output structure,
section semantics, assessment style, tone, translation rules, diagram
handling, and sharing variant rules for this skill.

## 0. Purpose and reconstruction model

Write the review in English. When the reviewed change contains relevant
non-English comments, names, labels, or terms, translate them into
English in the review. Retain the original wording only when it helps
traceability or avoids ambiguity.

Use professional, factual, non-inflammatory language suitable for
possible direct posting on the provider. State strong negative findings
clearly, but prefer measured phrasing such as `not ready to merge`,
`not yet complete`, `not yet supported by evidence`, or
`does not currently demonstrate` over harsher labels when those softer
phrases preserve accuracy.

The primary output is a human review-preparation artifact, not a terse
verdict memo. Reconstruct the reviewed change as if you were
retrospectively rebuilding the `plan-task` artifact that should have
existed for the implemented work, then add AI assessment and
recommendations to support human review and decision making.

Global sections describe the reviewed change itself:

- `Scope` = the pull request / merge request / diff scope
- `Motivation` = the change motivation
- `Scenario` = the reconstructed use cases or operational flows
- `Briefing` = reviewer onboarding and review entry guidance
- `Research` = the reconstructed base state and context
- `Design` = the reconstructed implemented target state
- `Test specification` = the reconstructed verification needs,
  actual test evidence, and sufficiency judgment
- `Assessment` = the AI-side analysis, concerns, reviewer guidance,
  and explicit intent-versus-implementation judgment

`Scenario` and `Briefing` are especially important both at the global
review level and within each `Review Area`. Globally, `Scenario` should
usually be present unless the PR is so trivially technical that no
meaningful use case, workflow, or operational situation can be
reconstructed. Global `Briefing` should help the human reviewer start
efficiently by summarizing the main change areas, suggested reading
order, hotspots, and strategic questions.

Within each `Review Area`, use `Scenario` and `Briefing` with the same
seriousness for that local slice of the change. Local `Scenario` should
explain the area's specific user flow, workflow change, or operational
consequence. Local `Briefing` should explain how to enter that area,
what dependencies and hotspots matter, and which local strategic
questions the reviewer should keep in mind. Keep them concise when the
area is narrow, but do not treat them as filler or lower-significance
sections.

For substantial or multi-area changes, decompose the review into
`Review Area` sections aligned with logical work areas, similar to
`plan-task` subtasks. Each area should help the human reviewer
understand in detail what that area adds, changes, or removes and why.

## 1. Evidence modes

### Provider-backed mode

Use an explicit pull request or merge request page, provider review ID,
or equivalent reference.

Detect the provider from the explicit review reference first. If that is
not enough, inspect the repository origin / remote host. Support at
least:

- GitHub
- GitLab, including self-hosted GitLab instances

If the provider type is still unclear, stop and ask instead of
guessing.

Minimum provider-backed evidence contract:

- provider metadata including at least title, body/description, URL,
  base ref, head ref, author, and current state
- provider discussion / comments
- implemented diff
- all commits in the reviewed provider range, including commit messages
- optional read-only provider API requests only when the previous
  commands do not expose enough review metadata

GitHub access path:

- `gh pr view <pr> --json number,title,body,url,baseRefName,headRefName,author,state,isDraft`
- `gh pr view <pr> --comments`
- `gh pr diff <pr>`
- optional GET-only `gh api` requests

GitLab access path:

- `glab mr view <mr> --json ...` when available
- `glab mr diff <mr>` when available
- optional read-only GitLab API requests when the CLI commands do not
  expose enough review metadata

Use the provider metadata, full diff, all commits and commit messages in
the reviewed range, and available discussion/comments as review
evidence. The diff and implemented code remain the source of truth for
what changed. Provider text, discussion, and commit messages help
interpret motivation, risks, and logical change clusters.

If provider-backed mode is requested but the required read-only provider
commands fail, stop and ask whether to retry, provide the missing
evidence manually, or fall back to Git-only mode.

### Git-only mode

Use explicit base and head commits, an explicit merged branch, or a
user-approved branch diff to define the reviewed range.

Analyze the full diff and all commits inside that range. Use commit
messages as supplementary evidence for motivation, intent, and logical
change clustering, but do not let them override the implemented diff.

If the comparison range is unclear, stop and ask instead of guessing.

## 2. Review file location and naming

Write review artifacts under `reviews/`.

Provider-backed review files:
- `reviews/github-owner-repo-123.md`
- `reviews/gitlab-group-project-456.md`

Git-only review files:
- `reviews/YYYY-MM-DD-<slug>.md`

Target-specific sharing variants when needed:
- `reviews/<review-base-name>.github.md`
- `reviews/<review-base-name>.gitlab.md`

## 3. Review file format

Use this exact order and layout:

- Title line: `# Review: <title>`
- One identifier line:
  - `- **Ticket:** github:owner/repo#123` for GitHub-backed mode
  - `- **Ticket:** gitlab:group/project!456` for GitLab-backed mode
  - `- **Task Identifier:** YYYY-MM-DD-<slug>` for Git-only mode
- One global outcome line immediately after the identifier:
  - `- **Review outcome:** <value>`
- Main sections in this order:
  - `- **Scope:**`
  - `- **Motivation:**`
  - `- **Scenario:**` (conditional)
  - `- **Constraints:**` (optional)
  - `- **Briefing:**`
  - `- **Research:**`
  - `- **Design:**`
  - `- **Test specification:**`
  - `- **Assessment:**`

`Assessment` must be the last main section.

## 4. Review outcome and review areas

`Review outcome` is the only place for the global recommendation, for
example:

- `merge`
- `merge after minor improvements`
- `request changes`
- `do not merge`

It should also include concise supporting bullets such as:
- `Intent judgment`
- `Implementation judgment`
- `Verification judgment`
- blockers
- required improvements
- non-blocking improvements
- net benefits or complexity reductions
- net costs or complexity increases
- area-wise justification summary, including:
  - independently justified keep candidates
  - conditionally justified areas
  - areas whose justification depends on a disputed direction
  - areas to defer or drop pending discussion
- whether major parts should be kept, simplified, split, deferred, or
  dropped
- when recommending a split, the suggested first follow-up PR slice to
  start with

When the verdict is clear, say so clearly. When the evidence is mixed,
differentiate the outcome accordingly. Do not manufacture symmetry or
invent offsetting pros and cons that the evidence does not support.
When recommending that a reviewed branch be split into multiple PRs,
state that explicitly and suggest the first slice that appears most
coherent, valuable, and reviewable on its own.

`Verification judgment` should summarize the outcome-level confidence
from the reconstructed `Test specification`: what verification should
exist, what evidence is actually present, how strong that evidence is,
and whether the current verification is sufficient for merge confidence.

`Intent judgment` should not rely on generic aggregate praise when the
review areas differ materially. Summarize the intent area by area: which
parts are independently justified, which are only conditionally
justified, which depend on a disputed broader direction, and which
should be deferred or dropped pending discussion.

When a refactoring mainly serves a questioned direction, say so
explicitly. Do not recommend keeping that refactoring merely because it
looks cleaner unless it also has clear independent value for the current
system.

Do not repeat the overall verdict inside `Assessment` sections.

For substantial or multi-area reviewed changes, review areas are
expected. Organize them like retrospective `plan-task` subtasks around
logical work areas such as modules, features, layers, or coherent
commit clusters. For small single-area changes, one global review may
suffice.

Keep the global sections focused on PR-wide scope, motivation,
cross-cutting context, and overall assessment. Use the global
`Assessment` and `Review outcome` to judge the whole PR's net benefit,
net complexity shift, and whether major parts should be kept,
simplified, split, deferred, or dropped. Do that from two explicit
perspectives: intent and implementation.

At the global level, synthesize the review areas rather than flattening
then into generic praise or criticism. Make clear which areas are
independently worth keeping, which should be narrowed or simplified,
which are justified only if a disputed broader direction is accepted,
and which should be deferred or dropped. Use each review area to
explain that area's local scope, motivation, scenario, briefing,
research, design, test specification, and assessment in the detail a
human reviewer needs, again separating local intent from local
implementation.

Place review areas after all global sections. Each one must use:

- `## Review Area: <title>`
- `- **Status:** <status>`

Recommended area status values:
- `acceptable`
- `needs-changes`
- `needs-information`
- `out-of-scope`

Each review area uses the same section ordering as the main review,
ends with its own `Assessment` section, and should make clear what that
area adds, changes, or removes and why.

## 5. Section mapping

- `Scope` — actual pull request / merge request / diff scope: major
  additions, modifications, removals, affected modules, boundaries, and
  explicit non-goals. This is the reviewed change scope, not the scope
  of the review activity.
- `Motivation` — pull request / change motivation from provider
  description, linked tickets, commit messages, and clearly marked
  inference when explicit statements are missing. This is the
  motivation of the change, not the reason for performing the review.
- `Scenario` — reconstructed use cases, user flows, or operational
  situations the reviewed work appears to enable or change. At the
  global review level, include it unless the PR is so narrowly
  technical that any such scenario would be fake filler. In each
  review area, treat it with equal seriousness for that local slice and
  use it to explain the area's specific behavior, workflow, or
  operational change.
- `Constraints` — explicit non-goals, compatibility limits, review
  boundaries, or restrictions discovered in the change or provider
  discussion
- `Briefing` — reviewer onboarding and orientation; in
  provider-backed mode also store the full review URL here. Globally,
  explain the main change areas, suggested reading order, hotspots, and
  strategic questions that should frame the human review. When
  recommending a split, identify the suggested first follow-up PR slice
  here or in `Review outcome` if that is the most natural place. In
  each review area, treat it with equal seriousness and explain how to
  enter that area, what dependencies and hotspots matter, and which
  local strategic questions should frame review of that slice. When the
  review is decomposed into areas, briefly explain why that
  decomposition was chosen.
- `Research` — original state and bounded context reconstruction for
  the reviewed change or review area. Start from the changed files
  and changed symbols revealed by the diff. Inspect their base-state
  versions, direct relationships, and relevant unchanged neighbors.
  Compare both the old version and the new version of the changed files
  against similar patterns within the same module to identify
  conventions the change follows, reuses, or breaks. Explain enough of
  the prior state for a human reviewer to understand what the PR adds,
  changes, or removes. By default, keep this pattern search within the
  changed module. Do not scan across multiple modules unless the diff
  itself spans them or the user explicitly approves that broader scope.
- `Design` — implemented target state at the head commit for the
  reviewed change or review area. Focus on the resulting target
  structure, behavior, interfaces, interactions, and changed
  relationships. Explain how the area now works and how the new or
  changed pieces fit together, rather than repeating the wider pattern
  hunt from `Research`.
- `Test specification` — first reconstruct what should be tested from
  the reviewed behavior, design, risks, and integration points. Then
  compare that expected verification scope with the changed tests,
  claimed verification, executed verification commands, coverage gaps,
  and missing verification evidence that actually exist.

  Do not reduce this section to a list of test classes or commands.
  Explain which behaviors, contracts, regressions, integrations,
  boundaries, or invariants should be covered, what evidence is present,
  what evidence is missing, whether the current tests appear sufficient,
  and whether their assertions appear to validate the intended behavior
  rather than only exercise code paths. Apply that approach both
  globally and within each review area. At the global review level,
  distill this analysis into a concise `Verification judgment` inside
  `Review outcome`.
- `Assessment` — AI-side analytic findings, uncertainties, unresolved
  concerns, recommendations, reviewer attention points, and trade-off
  analysis. Keep the overall verdict only in `Review outcome`, but use
  `Assessment` to surface the quality, risk, clarity, complexity,
  consistency, and follow-up questions that should inform the human
  decision.

  For the global review and each review area, analyze the change from
  two explicit perspectives:
  - **Intent** — is this the right thing to do? Is the problem worth
    solving this way? Are the intended feature, safety, performance,
    maintainability, or architectural benefits convincing?
  - **Implementation** — is the chosen intent realized correctly,
    coherently, safely, completely, and with adequate tests and
    integration?

  Use that split even when the resulting judgment is asymmetric, for
  example good intent with weak implementation, or questionable intent
  with locally competent implementation.

  Also discuss pros, cons, complexity increased, reduced, or shifted,
  and whether that trade-off appears justified. Where relevant, state
  whether the area should be kept, simplified, split, deferred, or
  dropped. For refactorings and infrastructure work, explain whether
  they have independent value or whether their justification mainly
  depends on another disputed direction in the PR. Do not force false
  balance: if the evidence is one-sided, say so plainly; when it is
  mixed, present differentiated pros and cons that match the resulting
  assessment. Keep the tone professional and measured even when the
  conclusion is strongly negative.

## 6. Diagram requirements in retrospective reviews

Apply the `plan-task` diagram rules fully in retrospective reviews.
Diagrams are not optional decoration; they are a required source of
review insight whenever the reviewed change or review area changes
structure, component interaction, runtime flow, or workflow in a
non-trivial way.

For substantial or multi-area reviewed changes:

- global diagrams do not replace review-area diagrams
- each materially changed review area should include at least a
  `Design` diagram
- include a `Research` diagram too when understanding the previous
  boundary, structure, or flow materially helps review
- omit diagrams only for trivially local changes or explicit user
  override

When a review area covers massive code changes across several
collaborating types or components, diagrams are must-have rather than
nice-to-have.

## 7. Diagram modes

Local review files support explicit review-diagram modes:
- `inherit`
- `plantuml`
- `mermaid`
- `none`

Default-resolution rules:
- if the user or project sets an explicit review-diagram mode, use it
- otherwise start from the project's normal task/review diagram default
- then adjust that inherited default by the detected provider
- if the provider is still unknown, keep the inherited project default
  for the local review file and ask before generating any target-
  specific sharing variant

Provider-aware inherited defaults:
- inherited `plantuml` + GitHub target => effective local default
  becomes `mermaid`
- inherited `plantuml` + GitLab target, including self-hosted GitLab =>
  effective local default stays `plantuml`
- inherited `mermaid` => effective local default stays `mermaid`
- inherited `none` => effective local default stays `none`

Explicit mode rules:
- if the effective or explicit mode is `plantuml`, follow the PlantUML
  patterns and examples from `plan-task`
- if it is `mermaid`, generate Mermaid directly in the local review
  file
- if it is `none`, omit diagrams unless the user overrides the mode

Mermaid guidance:
- prefer simple, reliable diagrams over clever or compact ones
- keep each Mermaid statement on one physical line
- if a relationship is hard to express clearly, simplify the diagram and
  explain the rest in prose below it
- use Mermaid only where it adds review value

## 8. Target-specific sharing variants

If the user asks for a provider-friendly or Mermaid-based sharing
variant, generate one only when the target provider cannot use the
canonical diagram format directly.

Provider-specific rules:
- GitHub target:
  - if the canonical local review file uses PlantUML, write a sibling
    Mermaid variant instead of rewriting the canonical review file
  - if the canonical review already uses Mermaid, or it contains no
    diagrams, no different version is needed
- GitLab target, including self-hosted GitLab:
  - if the canonical local review file uses PlantUML, reuse it directly
  - if the canonical review already uses Mermaid, or it contains no
    diagrams, no different version is needed
- Unknown target:
  - stop and ask the user which provider the sharing variant is for

In practice, when the effective local review default already reflects
both the project default and the detected provider, a separate sharing
variant is usually unnecessary.

When a sharing variant is generated:
- keep the same review structure and substantive content
- convert only what is needed for the target provider's rendering
  support
- keep the variant local; do not post it from this skill

