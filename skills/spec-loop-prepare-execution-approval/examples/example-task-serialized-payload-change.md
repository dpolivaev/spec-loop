# Example task: Serialized payload change after approval preparation

This compact example shows an approval-prepared task whose main risk is
an external payload contract.

It demonstrates:
- a task `Glossary` with a focused Mermaid visual glossary;
- a small component diagram;
- explicit final payload names and removed request fields;
- **-** annotations for removed fields and database columns; and
- a compact identifier list/table where diagrams alone would be weak.

Use it as a pattern collection, not as a required task size. The
starting-state facts below are illustrative, not observations of a real
repository. Test anchors show planned verification for that context.

- **Scope:** Normalize an export request payload so the server receives
  one stable structure for selected items and export options. Remove the
  legacy `selectedItems` request field and duplicate `selected_items_json`
  database column; keep `selected_item_ids_json`.
- **Motivation:** Remove parallel request shapes and make the Export API
  contract explicit before implementation.
- **Scenario:** A user selects several items, triggers export, and the
  client sends one normalized export request payload to the Export API.
- **Glossary:**
  - **export request payload:** shared term from the `/api/export` API
    contract for the normalized request body sent by the client.
    - The export request payload contains `selectedItemIds`,
      `selectionCount`, `includeMetadata`, and `format`.
    - The client sends one export request payload shape to the Export
      API per export action.
  - **Export API:** shared term from the `/api/export` API contract
    that receives the export request payload.
    - The Export API receives one normalized export request payload.
    - The new path removes the legacy parallel request shape at this
      boundary.

  ```mermaid
  flowchart LR
      subgraph client[Client contract]
          ERP[export request payload]
      end

      subgraph api[External API]
          EA[Export API]
      end

      ERP -->|sent to| EA
  ```
- **Briefing:** The change affects one client composer, one Export API,
  and one serialized export request payload shape.
- **Research:**

  ```plantuml
  @startuml
  component "Selection UI" as ui
  component "ExportRequestComposer" as composer
  component "Export API" as api

  ui --> composer : selected items + options
  composer --> api : request variant A
  composer --> api : request variant B
  @enduml
  ```

  - The current client sends two similar request variants, which
    forces the server to branch on partially duplicated payloads.
    Both request variants contain `selectionCount`, `includeMetadata`,
    and `format`. One carries `selectedItems: list[str]`, the other
    `selectedItemIds: list[str]`. The existing `export_request` table
    has both `selected_items_json: TEXT NOT NULL` and
    `selected_item_ids_json: TEXT NOT NULL` columns; both currently store
    the same ordered identifiers. This task removes the first column
    without changing saved identifier values.
  - `ExportRequestCompositionTest` already contains
    `keepsSelectedIdentifierOrder`, which checks ordering independently
    of request shape, and `selectionCountEqualsSelectedIdentifierCount`,
    which currently checks both legacy request variants.
    `acceptsLegacySelectedItemsField` verifies the old field being removed.
    `emitsNormalizedRequestShape`, `rejectsLegacySelectedItemsField`, and
    `ExportRequestStorageTest` do not exist yet.
  - The living-project-document list in `README.md` includes
    `docs/export-api.md` as the current Export API payload contract. No
    project glossary exists.

- **Design:**

  ```plantuml
  @startuml
  component "Selection UI" as ui
  component "ExportRequestComposer" as composer
  component "Export API" as api

  ui --> composer : selected items + options
  composer --> api : normalized export request payload
  @enduml
  ```

  ```plantuml
  @startuml
  set separator none
  package "export" {
    class "<plain>**±**</plain> export request payload" as Payload {
      + <plain>**=**</plain> selectedItemIds : list[str]
      + <plain>**=**</plain> selectionCount : int
      + <plain>**=**</plain> includeMetadata : bool
      + <plain>**=**</plain> format : str
      + <plain>**-**</plain> selectedItems : list[str]
    }
    class "<plain>**±**</plain> export_request" as Storage <<table>> {
      <plain>**=**</plain> selected_item_ids_json : TEXT NOT NULL
      <plain>**-**</plain> selected_items_json : TEXT NOT NULL
    }
    Payload --> Storage : persists ordered identifiers
  }
  @enduml
  ```

  Remove `selected_items_json` after verifying it equals the retained
  `selected_item_ids_json` for every saved row; conflicting values block
  migration rather than discarding data. The **-** field and column show
  starting-state declarations, not members of the normalized request or
  resulting table.

  Export request payload:

  ```json
  {
    "selectedItemIds": ["a-1", "a-2"],
    "selectionCount": 2,
    "includeMetadata": true,
    "format": "markdown"
  }
  ```

  Externally meaningful identifier outside the class diagram:
  - `/api/export` — normalized export request endpoint.

  - **Living project documents:**
    - `docs/export-api.md` — Export API contract; `update`: normalized
      payload fields and removal of the legacy request shape.

- **Test specification:**
  - **Automated tests:**
    - **±** `ExportRequestCompositionTest`
      - **+** `emitsNormalizedRequestShape`: verify export request
        composition emits the normalized request shape.
      - **=** `keepsSelectedIdentifierOrder`: verify multiple selected
        identifiers keep their order in the emitted request.
      - **±** `selectionCountEqualsSelectedIdentifierCount`: verify
        `selectionCount` equals the emitted selected-identifier count
        in the normalized request.
      - **-** `acceptsLegacySelectedItemsField`: remove acceptance of
        the legacy request field; `rejectsLegacySelectedItemsField`
        verifies its absence in the accepted contract.
      - **+** `rejectsLegacySelectedItemsField`: the Export API rejects
        the removed request field, including requests with both fields.
    - **+** `ExportRequestStorageTest`
      - **+** `columnRemovalPreservesOrderedIdentifiers`: migration removes
        the duplicate column only after exact equality verification;
        retained identifiers remain unchanged and conflicting rows
        prevent migration without writes.
  - **Manual tests:**
    - Export two selected items and inspect the outgoing request.
    - Verify that the server receives only the normalized payload
      shape.
