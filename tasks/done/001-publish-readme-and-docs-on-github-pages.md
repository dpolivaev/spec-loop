# Task: Publish README and docs on GitHub Pages with mdBook

- **Task Identifier:** 2026-06-02-github-pages-docs

- **Scope:**
  Add GitHub Pages publishing for this repository so the root
  `README.md` and current `docs/` content are built into a static site
  and deployed from GitHub Actions. Keep the existing Markdown source
  files in place. Use a small committed mdBook scaffold under `site/`
  if needed, but do not create a duplicate content tree for the
  published docs. Provide a committed local build script under `site/`
  and reuse it from GitHub Actions.

- **Motivation:**
  The project already has repository documentation in `README.md` and
  `docs/`, but it is only readable in the source tree. We want a
  published documentation site on GitHub Pages using the same general
  approach as `/Users/dimitry/git-repo/freeplane/docs`: GitHub Actions
  plus mdBook.

- **Scenario:**
  A GitHub visitor opens the published site and lands on a home page
  derived from `README.md`. From there the visitor can navigate the
  current documents from `docs/`, open internal links without broken
  paths, and view static assets such as `docs/infographics.svg`.

- **Constraints:**
  - Keep the existing `README.md` and `docs/*.md` files where they are.
  - Use `site/` rather than a repo-root `src/` directory for the
    committed mdBook scaffold.
  - A small committed mdBook site scaffold is allowed, but it must not
    duplicate the existing published Markdown content.
  - Keep published navigation curated through committed
    `site/src/SUMMARY.md`.
  - Base the published site on the full `README.md` plus the current
    `docs/` content.
  - Publish only `README.md` and `docs/**` from the repository content
    scope.
  - In source prose, use repo-relative links for this repository's
    out-of-site artifacts rather than absolute GitHub URLs.
  - Rewrite any repo-relative link outside the published scope to the
    corresponding GitHub repository URL wherever it appears in
    published content: directory targets use `tree/<ref>/...`, file
    targets use `blob/<ref>/...`.
  - Keep the implementation close in spirit to the existing
    `/Users/dimitry/git-repo/freeplane/docs` setup and reuse the same
    mdBook options except the GitHub edit-link behavior, which this
    site should omit.
  - Do not commit generated site output.
  - Preserve working links from the README-derived home page.

- **Briefing:**
  Relevant files are `README.md`, `docs/*.md`, `docs/infographics.svg`,
  `.github/workflows/push-to-gitlab.yml`, and the new `site/` scaffold
  to be added by this task. The repo currently has no mdBook config and
  no GitHub Pages workflow. The nearest local reference is
  `/Users/dimitry/git-repo/freeplane/docs/.github/workflows/mdbook-github-pages.yml`
  together with that repo's mdBook config under `docs/src/`. This task
  should keep the current Markdown files in place, add only a small
  committed site scaffold under `site/`, preferably using `site/src/`
  for mdBook-owned files such as `SUMMARY.md`, and provide one
  committed local build script reused by GitHub Actions.

- **Research:**
  Current state:

  ```plantuml
  @startuml
  component "Repository" as Repo
  artifact "README.md" as Readme
  artifact "docs/*.md" as Docs
  artifact "docs/infographics.svg" as Asset
  component "GitHub Actions" as Actions
  component "GitHub Pages" as Pages

  Repo --> Readme
  Repo --> Docs
  Repo --> Asset
  Actions ..> Repo : current workflows only
  Actions ..x Pages : no Pages publishing workflow
  @enduml
  ```

  Verified observations:
  - The repository currently contains one GitHub Actions workflow:
    `.github/workflows/push-to-gitlab.yml`.
  - The content to publish currently lives in `README.md` plus four
    markdown files in `docs/`: `online-art-game-tutorial.md`,
    `review-responsibility-and-traceability.md`,
    `skill-framework-comparison.md`, and `wordle-tutorial.md`, plus the
    static asset `docs/infographics.svg`.
  - `README.md` links both to `docs/*.md` files and to local files
    outside `docs/`, including many `skills/*.md` files and `LICENSE`.
  - The current `docs/*.md` files do not currently link to
    `examples/**`, `skills/**`, or other out-of-scope repo files, but
    future docs could.
  - The docs tutorials link both to `../README.md` and to
    `../README.md#...` section anchors.
  - A local mdBook behavior check shows that links from a docs page to
    `../README.md` or `../README.md#...` are rendered as
    `../README.html` or `../README.html#...`, not as `../index.html`.
    Those backlinks would break unless rewritten during staging.
  - The same mdBook check shows that links to `index.md` are rendered
    as `index.html`, so staged docs can safely link to `../index.md`
    and `../index.md#...`.
  - The same mdBook check shows that links from the home page to
    `docs/*.md` are rendered correctly as `docs/*.html`, and asset
    links such as `docs/infographics.svg` remain asset links.
  - The reference Freeplane setup uses a workflow at
    `/Users/dimitry/git-repo/freeplane/docs/.github/workflows/mdbook-github-pages.yml`
    and a committed mdBook tree with `book.toml`, `SUMMARY.md`, and a
    `src/` directory.

- **Design:**
  Target structure:

  ```plantuml
  @startuml
  component "GitHub Actions workflow\n.github/workflows/mdbook-github-pages.yml" as Workflow
  artifact "site/book.toml" as BookToml
  artifact "site/src/SUMMARY.md" as Summary
  folder "Temporary staged mdBook source\nrunner-local / local-build workspace" as Stage
  artifact "index.md\n(generated from root README.md)" as Home
  artifact "docs/*.md" as StagedDocs
  artifact "docs/* assets" as StagedAssets
  folder "GitHub Pages artifact\nbuild/gh-pages/" as Output

  Workflow --> BookToml
  Workflow --> Summary
  Workflow --> Stage
  Stage --> Home
  Stage --> StagedDocs
  Stage --> StagedAssets
  Workflow --> Output : mdbook build
  @enduml
  ```

  Build flow:

  ```plantuml
  @startuml
  actor Developer
  participant "GitHub Actions" as Actions
  participant "temporary staging workspace" as Stage
  participant "mdBook + mdbook-toc" as Book
  participant "GitHub Pages" as Pages

  Developer -> Actions : push main / workflow_dispatch
  Actions -> Actions : checkout repository
  Actions -> Actions : install mdBook v0.4.35 and mdbook-toc 0.14.1
  Actions -> Stage : invoke shared local build script
  Stage -> Stage : write index.md from root README.md
  Stage -> Stage : copy docs/*.md and docs assets
  Stage -> Stage : rewrite README and docs backlinks
  Stage -> Book : mdbook build against temp workspace
  Book -> Pages : upload and deploy Pages artifact
  @enduml
  ```

  File inventory:
  - Committed files:
    - `.github/workflows/mdbook-github-pages.yml`
    - `site/book.toml`
    - `site/src/SUMMARY.md`
    - one local build script under `site/`
  - Temporary generated files before build:
    - workspace `index.md`
    - workspace `docs/*.md`
    - workspace `docs/*` for published static assets such as
      `infographics.svg`
  - Build output:
    - local run output directory and GitHub Pages artifact output

  Design decisions:
  - Keep `README.md` and `docs/*.md` as the canonical content sources
    in their current locations.
  - Commit a small mdBook scaffold under `site/`, with mdBook-owned
    files under `site/src/`, instead of using a repo-root `src/`
    directory.
  - Use committed mdBook control files `site/book.toml` and curated
    `site/src/SUMMARY.md`.
  - Use mixed sidebar structure in `site/src/SUMMARY.md`: unnumbered
    prefix entries for `Overview`, `Review, Responsibility, and
    Traceability`, and `AI Workflow Framework Comparison`, followed by
    a `Tutorials` part heading with numbered tutorial chapters.
  - Add one committed local build script under `site/` that performs
    the staging, link rewriting, and `mdbook build` steps inside a
    temporary workspace, and have the GitHub Actions workflow install
    mdBook v0.4.35 plus mdbook-toc 0.14.1 and call that same script.
  - Keep committed mdBook control files in `site/`, but copy them into
    the temporary workspace before build so generated files never touch
    tracked `site/src/`.
  - Configure `book.toml` with `src = "src"`, and configure
    `SUMMARY.md` to use `index.md` as the unnumbered `Overview` entry,
    so mdBook emits `index.html` from generated workspace `index.md`.
  - Generate workspace `index.md` from the full root `README.md`
    during the build rather than maintaining a separate hand-written
    site home page.
  - Publish the current `docs/` markdown files as curated book
    chapters in `site/src/SUMMARY.md` rather than auto-publishing the
    whole `docs/` tree by filesystem discovery.
  - Keep the current published entry order explicit in
    `site/src/SUMMARY.md`: unnumbered `Overview`, then unnumbered
    `review-responsibility-and-traceability`, then unnumbered
    `skill-framework-comparison`, then a `Tutorials` part with
    numbered `wordle-tutorial` and `online-art-game-tutorial`.
  - Copy `docs/infographics.svg` and any other published static assets
    under `docs/` into the temporary workspace `src/docs/` so asset
    links resolve.
  - Keep source prose links repo-relative where they refer to this
    repository: skill-name links point to skill directories, explicit
    file references point to files, and example code blocks stay
    literal.
  - Rewrite links while staging content:
    - keep links into the published scope as markdown links so mdBook
      converts them to site HTML paths;
    - in generated workspace `index.md`, rewrite any repo-relative
      link outside the published scope to the corresponding GitHub URL,
      using `tree/<ref>/...` for directories such as `skills/.../` and
      `blob/<ref>/...` for files such as `skills/.../*.md` or
      `LICENSE`;
    - in staged workspace `docs/*.md`, rewrite `../README.md` to
      `../index.md` and rewrite `../README.md#...` to
      `../index.md#...` so mdBook converts them to `index.html`
      backlinks;
    - in staged workspace `docs/*.md`, rewrite any other repo-relative
      link outside the published scope to the corresponding GitHub URL
      using the same directory/file rule;
    - leave external URLs unchanged.
  - Reuse the relevant Freeplane mdBook options where they fit this
    repo, specifically `mdbook-toc`, HTML fold behavior, and the
    mdBook top-bar Git repository link.
  - Omit `mdbook-open-on-gh`, the GitHub edit-link footer behavior,
    and any CSS or config used only to support that behavior.

- **Test specification:**
  - **Automated tests:**
    - Run the committed local build script and confirm it produces the
      site from the committed `site/` scaffold using a temporary staged
      workspace.
    - Run the workflow build commands locally or in CI and confirm the
      workflow reuses the same script successfully.
    - Verify the build output contains `index.html` plus HTML pages for
      each published doc and copied static assets.
    - Check that the generated home page contains no unresolved local
      links to unpublished repo paths such as `skills/*.md`,
      `examples/**`, or `LICENSE`.
    - Check that generated out-of-site directory links such as
      `skills/spec-loop-plan-task/` use GitHub `tree/<ref>/...` URLs.
    - Check that generated out-of-site file links such as
      `skills/spec-loop-plan-task/SKILL.md` and `LICENSE` use GitHub
      `blob/<ref>/...` URLs.
    - Check that staged docs backlinks to `README.md` are rewritten to
      `index.md` before build and rendered as links to `index.html`,
      not to nonexistent `README.html`.
  - **Manual tests:**
    - After deployment, open the GitHub Pages site and verify the home
      page renders from `README.md`.
    - Verify navigation to each published `docs/` page.
    - Verify the sidebar entry order matches the curated
      `site/src/SUMMARY.md` order.
    - Verify the sidebar shows unnumbered entries for `Overview`,
      `Review, Responsibility, and Traceability`, and
      `AI Workflow Framework Comparison`.
    - Verify the sidebar shows a standalone `Tutorials` part heading.
    - Verify the tutorial entries under `Tutorials` are numbered.
    - Verify the infographic renders from the home page.
    - Verify tutorial links back to the home page work.
    - Verify links that point outside the published scope, including
      skill directories, `skills/*.md`, `examples/**`, and `LICENSE`,
      open the corresponding GitHub tree or blob pages instead of 404s.
    - Verify the top bar shows a GitHub repository icon that opens the
      repository root.
    - Verify the site does not show GitHub edit-link footer UI.

- **Implementation notes:**
  - **Interpretations:**
    - Treated the approved out-of-scope link-rewrite rule as applying
      to both Markdown links and raw HTML `href` or `src` attributes in
      the generated home page and staged docs pages.
  - **Tradeoffs:**
    - Used one committed local build script plus a temporary staging
      workspace so local builds and GitHub Actions share the same logic
      without writing generated files into tracked `site/src/`.
    - Kept the Freeplane-style `mdbook-toc` and fold behavior, added
      the mdBook top-bar Git repository link, but left out
      `mdbook-open-on-gh` and its related config because the task
      explicitly rejected GitHub edit-link UI.
    - Accepted review feedback to pin the workflow to mdBook v0.4.35,
      which matches the locally verified setup and the installed
      mdbook-toc compatibility window observed during testing.
    - Kept source docs repo-relative and let the site generator rewrite
      out-of-site links to GitHub tree/blob URLs, so GitHub source
      rendering and the generated site both stay navigable from one set
      of Markdown files.
    - Kept the link-rewrite implementation regex-based because the
      current published files use simple inline Markdown links and raw
      HTML `href` or `src` attributes. If future docs add more complex
      link forms such as reference-style links or nested-parenthesis
      targets, this script may need a stronger parser.
    - Used mdBook prefix chapters plus one numbered `Tutorials` part to
      support unnumbered overview/reference pages while still allowing
      tutorial numbering without custom sidebar CSS.
