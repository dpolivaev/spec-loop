# Review: Settings loader extraction, validation, and preview server
- **Ticket:** github:example/acme-widget#42
- **Review outcome:** request changes
  - **Intent:** Settings loader extraction and schema normalization are
    independently justified. Stricter validation is conditionally
    justified only if migration strategy and failure policy are narrowed.
    The embedded preview server is not justified by the reviewed
    evidence.
  - **Implementation:** The loader extraction is coherent, but the
    branch couples it to a breaking validation change and a
    default-enabled preview server.
  - **Verification:** Unit tests cover parser happy paths and invalid-key
    rejection. Missing evidence remains for migration compatibility,
    disabled-server startup, and end-to-end preview behavior.
  - **Complexity:** Justified complexity is concentrated in the
    extracted settings loader, schema, and normalization path.
    Accidental complexity appears in the fail-fast validation rollout
    and the local preview server.
  - **Blockers:**
    - legacy settings with unknown keys now fail without migration or
      compatibility fallback
    - preview server starts on application boot without explicit opt-in
  - **Required improvements:**
    - split the settings loader extraction from the preview server work
    - narrow the validation rollout or add migration support
    - defer the preview server until activation model and security
      boundary are accepted, or add explicit opt-in and missing evidence
  - **Non-blocking improvements:**
    - document the normalized config contract more explicitly
  - **Net benefits or complexity reductions:**
    - config parsing moves out of UI bootstrap
    - defaults and schema handling become easier to test
  - **Net costs or complexity increases:**
    - stricter config semantics can break existing installs
    - local HTTP surface adds runtime and security complexity
  - **Area-wise justification summary:**
    - justified complexity worth keeping: settings loader extraction and
      schema normalization
    - conditionally justified complexity: stricter validation, if
      compatibility policy is clarified
    - accidental complexity whose justification depends on disputed
      direction: embedded preview server
    - accidental complexity to defer or drop pending discussion:
      default-enabled localhost preview startup
  - **Keep/simplify/split/defer recommendation:**
    - keep the settings loader extraction and schema normalization
    - simplify validation rollout until compatibility handling is clear
    - split preview-server work into a separate PR
  - **Suggested first follow-up PR slice:** settings loader extraction
    and schema normalization, with compatibility-preserving tests for
    legacy configs and defaults

- **Scope:**
  This PR extracts configuration parsing from the Swing bootstrap, adds
  schema-based validation, and introduces an embedded HTTP preview
  server.

- **Motivation:**
  Reconstructed from the PR description and commit messages: make
  settings logic testable, centralize defaults, and prepare for
  browser-based preview.

- **Scenario:**
  A user upgrades the desktop application, keeps an existing config
  file, and optionally opens a browser preview of the current document.

- **Briefing:**
  Review URL: `<trusted-review-url>`

  This review is split into two areas because the configuration
  refactor has independent value, while the preview server introduces a
  separate product and runtime direction.

  Read the configuration loader first, then the preview server.

- **Research:**
  Before this PR, the UI bootstrap read properties directly, unknown
  keys were ignored, and no local HTTP server existed.

- **Design:**
  The head branch introduces a `SettingsLoader` / `SettingsSchema` /
  `SettingsValidator` path plus a `PreviewServer` that exposes rendered
  content over localhost.

  ```mermaid
  flowchart LR
      Bootstrap[UI bootstrap] --> Loader[SettingsLoader]
      Loader --> Schema[SettingsSchema]
      Loader --> Validator[SettingsValidator]
      Schema --> Config[NormalizedConfig]
      Desktop[Desktop app] --> Preview[PreviewServer]
      Browser[Browser] --> Preview
      Preview --> Renderer[DocumentRenderer]
  ```

- **Test specification:**
  - **Automated tests:**
    - `SettingsLoaderTest`
      - `loadsUpgradedConfigWithDefaults`: upgraded-config
        compatibility and default normalization.
      - `handlesInvalidKeysAccordingToMigrationPolicy`: invalid-key
        policy and migration behavior.
    - `PreviewServerTest`
      - `startsOnlyWhenPreviewEnabled`: preview server activation
        respects opt-in / opt-out behavior.
      - `servesRenderedPreviewRequest`: browser preview requests reach
        the rendering path end to end.

- **Assessment:**
  - **Intent:**
    The loader refactor is independently justified. The validation
    rollout and preview server need stronger justification, and the
    preview server is a product expansion rather than a necessary
    consequence of the refactor.
  - **Implementation:**
    The branch couples the useful refactor to a breaking validation
    change and a default-enabled runtime expansion.
  - **Verification:**
    Parser-focused unit tests, including invalid-key rejection, are
    present. Migration/regression tests for existing configs,
    application startup proof with preview disabled, and end-to-end
    preview verification are missing or unclear, so verification is not
    sufficient for merge confidence.
  - **Complexity:**
    Most lasting value is in the loader path. Most new burden is in the
    fail-fast validation rollout and preview-server runtime surface.

## Review Area: Configuration loader and validation
- **Status:** needs-changes
- **Scope:**
  This area extracts configuration parsing into dedicated loader and
  schema types and adds stricter validation for unknown or malformed
  keys.

- **Motivation:**
  Make configuration behavior testable and consistent instead of
  leaving it embedded inside UI startup.

- **Scenario:**
  A user launches the application with an older config file and expects
  the application either to accept it safely or explain the migration
  clearly.

- **Briefing:**
  Start with the old bootstrap reader, then inspect `SettingsLoader`,
  `SettingsSchema`, and `SettingsValidator`. Focus on unknown-key
  handling, default normalization, and compatibility policy.

- **Research:**
  Previously the UI bootstrap read raw properties directly and
  tolerated extra keys.

- **Design:**
  The new flow centralizes parsing, normalization, and validation.

  ```mermaid
  flowchart LR
      Bootstrap --> SettingsLoader
      SettingsLoader --> SettingsSchema
      SettingsLoader --> SettingsValidator
      SettingsSchema --> NormalizedConfig
  ```

- **Test specification:**
  - **Automated tests:**
    - `SettingsLoaderTest`
      - `loadsKnownKeysWithDefaults`: known-key parsing and default
        normalization.
      - `handlesLegacyConfigCompatibility`: compatibility behavior for
        legacy keys and previously tolerated configs.
      - `handlesUnknownKeysAccordingToRolloutPolicy`: unknown-key
        validation follows the approved migration or warning policy.

- **Assessment:**
  - **Intent:**
    Extracting config logic from UI bootstrap is independently
    justified. Stricter validation is plausible, but its rollout policy
    needs clearer user-impact reasoning.
  - **Implementation:**
    The loader extraction looks coherent. The validation rollout is too
    abrupt because it turns previously tolerated configs into failures
    without migration or warning-first handling.
  - **Verification:**
    Parser happy-path and invalid-key rejection tests are present. The
    tests support the refactor itself, but regression coverage for
    real-world legacy configs and migration or warning-first behavior is
    missing or weak.
  - **Complexity:**
    Parsing complexity moves into explicit types, which is justified.
    The fail-fast compatibility policy is accidental complexity in this
    branch and should be phased until compatibility and migration are
    covered.

## Review Area: Embedded preview server
- **Status:** needs-changes
- **Scope:**
  This area adds a localhost HTTP server that exposes rendered preview
  content to a browser.

- **Motivation:**
  Support browser-based preview without moving the whole application
  away from the desktop runtime.

- **Scenario:**
  A user opens a browser preview while editing a document in the
  desktop application.

- **Briefing:**
  Inspect server startup first, then routing and renderer integration.
  Focus on opt-in behavior, runtime boundary, and whether this
  direction is justified independently of the configuration refactor.

- **Research:**
  No local HTTP server existed before this PR; preview behavior
  remained inside the desktop UI.

- **Design:**
  The application now starts a local server and routes browser requests
  to the existing renderer.

  ```mermaid
  flowchart LR
      DesktopApp --> PreviewServer
      Browser --> PreviewServer
      PreviewServer --> DocumentRenderer
  ```

- **Test specification:**
  - **Automated tests:**
    - `PreviewServerTest`
      - `doesNotStartWhenPreviewDisabled`: server opt-out behavior.
      - `startsAndStopsPreviewServer`: startup and shutdown lifecycle.
      - `servesRenderedPreviewRequest`: request routing reaches the
        renderer and returns rendered preview content.
      - `keepsPreviewExposureLocalAndOptIn`: exposure and default
        security boundary remain constrained.

- **Assessment:**
  - **Intent:**
    This direction is not independently justified by the reviewed
    evidence. It should be accepted, if at all, as a separate product
    decision.
  - **Implementation:**
    Starting the server automatically without explicit opt-in is not a
    safe default.
  - **Verification:**
    Narrow route-level coverage is present. Startup proof with preview
    disabled, end-to-end preview request verification, and
    security-boundary checks around exposure and defaults are missing or
    weak, so verification is not strong enough for a new runtime
    surface.
  - **Complexity:**
    This area adds networking and lifecycle complexity and should be
    deferred to a separate PR unless a clear product need is accepted.
