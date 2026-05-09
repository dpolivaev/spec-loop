# Review: Settings loader extraction, validation, and preview server
- **Ticket:** github:example/acme-widget#42
- **Review outcome:** request changes
  - Intent judgment:
    - Independently justified keep candidate:
      - settings loader extraction and schema normalization
    - Conditionally justified area:
      - stricter validation, if existing-user migration and failure policy are clarified
    - Dependent on a disputed direction:
      - embedded preview server, which should wait for explicit product and security agreement
  - Implementation judgment:
    - The loader extraction is coherent, but the branch couples it to a breaking validation change
      and a default-on preview server.
  - Verification judgment:
    - Unit tests cover parser happy paths and invalid-key rejection.
    - Missing evidence remains for migration compatibility, disabled-server startup, and end-to-end
      preview behavior.
  - Blockers:
    - legacy settings with unknown keys now fail without migration or fallback
    - preview server starts on application boot without explicit opt-in
  - Net benefits or complexity reductions:
    - config parsing moves out of UI bootstrap
    - defaults and schema handling become easier to test
  - Net costs or complexity increases:
    - stricter config semantics can break existing installs
    - local HTTP surface adds runtime and security complexity
  - Keep / simplify / split / defer / drop:
    - split this branch
    - suggested first follow-up PR slice: settings loader extraction and schema normalization
    - keep validation only if narrowed to warnings first or backed by migration coverage
    - defer preview server until product need and security boundary are accepted
- **Scope:**
  This PR extracts configuration parsing from the Swing bootstrap, adds schema-based validation,
  and introduces an embedded HTTP preview server.
- **Motivation:**
  Reconstructed from the PR description and commit messages: make settings logic testable,
  centralize defaults, and prepare for browser-based preview.
- **Scenario:**
  A user upgrades the desktop app, keeps an existing config file, and optionally opens a browser
  preview of the current document.
- **Briefing:**
  Review URL: `https://github.com/example/acme-widget/pull/42`

  Decompose this review into two areas because the configuration refactor has independent value,
  while the preview server introduces a separate runtime and product direction.

  Read the configuration loader first, then the preview server.
- **Research:**
  Before this PR, the UI bootstrap read properties directly, unknown keys were ignored,
  and no local HTTP server existed.
- **Design:**
  The head branch introduces a `SettingsLoader` / `SettingsSchema` / `SettingsValidator` path plus
  a `PreviewServer` that exposes rendered content over localhost.
- **Test specification:**
  What should be tested:

  - upgraded-config compatibility
  - default normalization
  - invalid-key policy
  - preview server opt-in/opt-out behavior
  - startup/shutdown lifecycle
  - request handling and renderer integration

  Evidence present:

  - parser-focused unit tests
  - invalid-key rejection tests

  Missing or unclear evidence:

  - migration/regression tests for existing configs
  - application startup proof with preview disabled
  - end-to-end preview verification

  Sufficiency judgment:

  - The verification story is directionally useful but not yet sufficient for merge confidence.
- **Assessment:**
  - **Intent:**
    The PR combines one independently useful refactor with two more debatable follow-on directions.
    Extracting configuration loading is easy to justify on its own. Stricter validation is plausible,
    but only if its user impact is managed. The preview server is the weakest direction because it is
    a product expansion rather than a necessary consequence of the refactor.
  - **Implementation:**
    The branch does not yet realize the bundle safely. Validation changes existing behavior without
    migration support, and the preview server expands runtime surface without an explicit opt-in path.
  - **Pros:**
    - clearer separation between UI bootstrap and configuration logic
    - better foundation for focused parser tests
  - **Cons:**
    - backward-compatibility risk for existing settings files
    - additional runtime and security surface from the preview server
  - **Complexity shift:**
    Complexity moves from ad hoc UI bootstrap code into explicit configuration components, which is
    justified. The preview server adds a larger new complexity tier that is not yet justified by the
    reconstructed use case.
  - **Recommendation:**
    Keep the configuration extraction, narrow or phase the validation change, and defer the preview
    server until its product need and boundary are accepted.

## Review Area: Configuration loader and validation
- **Status:** needs-changes
- **Scope:**
  This area extracts configuration parsing into dedicated loader and schema types and adds stricter
  validation for unknown or malformed keys.
- **Motivation:**
  Make configuration behavior testable and consistent instead of leaving it embedded inside UI startup.
- **Scenario:**
  A user launches the application with an older config file and expects the app either to accept it
  safely or explain the migration clearly.
- **Briefing:**
  Start with the old bootstrap reader, then inspect `SettingsLoader`, `SettingsSchema`, and
  `SettingsValidator`. Focus on unknown-key handling, default normalization, and compatibility policy.
- **Research:**
  Previously the UI bootstrap read raw properties directly and tolerated extra keys.
- **Design:**
  The new flow centralizes parsing, normalization, and validation.

  ```mermaid
  flowchart LR
    Bootstrap --> SettingsLoader
    SettingsLoader --> SettingsSchema
    SettingsLoader --> SettingsValidator
    SettingsSchema --> NormalizedConfig
    SettingsValidator --> ValidationReport
  ```
- **Test specification:**
  What should be tested:

  - known-key parsing
  - default filling and normalization
  - unknown-key policy
  - migration from legacy key names or tolerated extras

  Evidence present:

  - unit tests for parser happy paths
  - tests that invalid keys are rejected

  Missing or weak evidence:

  - regression tests for previously tolerated real-world configs
  - tests proving warning-first or migration behavior

  Sufficiency judgment:

  - The present tests support the refactor itself, but not the compatibility impact of the new policy.
- **Assessment:**
  - **Intent:**
    Extracting config logic from UI bootstrap is independently justified. Stricter validation is also
    plausible, but its rollout policy needs clearer user-impact reasoning.
  - **Implementation:**
    The loader extraction looks coherent. The validation rollout is too abrupt because it turns
    previously tolerated configs into failures without migration or warning-first handling.
  - **Pros:**
    - easier focused testing
    - clearer ownership of defaults and schema
  - **Cons:**
    - compatibility risk is under-handled
    - stronger enforcement arrives before migration support
  - **Complexity shift:**
    Some parsing complexity moves into explicit types, but that shift is justified if compatibility is
    handled deliberately.
  - **Recommendation:**
    Keep the loader extraction. Simplify or phase the validation change until compatibility and
    migration are covered.

## Review Area: Embedded preview server
- **Status:** needs-changes
- **Scope:**
  This area adds a localhost HTTP server that exposes rendered preview content to a browser.
- **Motivation:**
  Support browser-based preview without moving the whole application away from the desktop runtime.
- **Scenario:**
  A user opens a browser preview while editing a document in the desktop app.
- **Briefing:**
  Inspect server startup first, then routing and renderer integration. Focus on opt-in behavior,
  runtime boundary, and whether this direction is justified independently of the config refactor.
- **Research:**
  No local HTTP server existed before this PR; preview behavior remained inside the desktop UI.
- **Design:**
  The application now starts a local server and routes browser requests to the existing renderer.

  ```mermaid
  flowchart LR
    DesktopApp --> PreviewServer
    Browser --> PreviewServer
    PreviewServer --> DocumentRenderer
  ```
- **Test specification:**
  What should be tested:

  - server opt-in / opt-out behavior
  - bind address and port policy
  - startup/shutdown lifecycle
  - request routing and rendering path
  - failure behavior when preview is unavailable

  Evidence present:

  - route-level unit coverage may exist

  Missing or weak evidence:

  - startup proof with preview disabled
  - end-to-end preview request verification
  - security-boundary checks around exposure and defaults

  Sufficiency judgment:

  - Verification is not yet strong enough for a new runtime surface.
- **Assessment:**
  - **Intent:**
    This direction is not independently justified by the reviewed evidence. It is a product and runtime
    expansion that should be accepted on its own merits, not smuggled in as a side effect of the
    configuration refactor.
  - **Implementation:**
    Starting the server automatically without explicit opt-in is not a safe default. The branch also
    does not yet show enough verification for lifecycle and exposure behavior.
  - **Pros:**
    - opens a path toward browser-based preview
  - **Cons:**
    - increases runtime and security surface
    - lacks a clearly accepted product boundary
  - **Complexity shift:**
    This area adds a new tier of networking and lifecycle complexity rather than simplifying the
    existing system.
  - **Recommendation:**
    Defer the preview server until product need, activation model, and security boundary are accepted,
    then review it as a separate PR.
