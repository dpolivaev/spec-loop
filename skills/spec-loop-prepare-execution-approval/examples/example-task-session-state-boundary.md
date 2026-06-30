# Example task: Session-state boundary after approval preparation

This compact example shows a task after `spec-loop-prepare-execution-approval`
polishes it for execution approval seeking for implementation work.

It demonstrates:
- diagram-first structural review;
- a class diagram plus a sequence diagram; and
- a compact identifier list instead of a repeated responsibility table.

Use it as a pattern collection, not as a required task size. Test
anchors below are intended verification anchors for the approval-ready
task, not an inventory of observed tests.

- **Scope:** Add a transient background session path that does not
  enter the persisted live-session registry.
- **Motivation:** Keep the visible session model simple while allowing
  one-off background execution.
- **Scenario:** A user starts one visible session and also triggers a
  background export. The background export runs separately and should
  not appear in the persisted session list.
- **Briefing:** The change touches one session manager, one transient
  runner, and one persistence boundary.
- **Research:**

  ```plantuml
  @startuml
  component "UI" as ui
  component "LiveSessionManager" as live
  component "PersistenceStore" as store

  ui --> live : start visible work
  live --> store : persist session data
  @enduml
  ```

  - The current design stores all work through the visible session
    registry, so even one-off background execution would be persisted
    and listed unless a second boundary is introduced.

- **Design:**

  ```plantuml
  @startuml
  set separator none

  package "example" {
    class SessionController {
      + startVisibleSession()
      + triggerBackgroundExport()
    }
    class LiveSessionManager {
      + createSession()
    }
    class BackgroundRunner {
      + runOnce()
    }
    class SessionStore {
      + save(session)
    }
  }

  SessionController --> LiveSessionManager : visible session
  SessionController --> BackgroundRunner : transient run
  LiveSessionManager --> SessionStore : persist visible session
  @enduml
  ```

  ```plantuml
  @startuml
  actor User
  participant "SessionController" as controller
  participant "LiveSessionManager" as live
  participant "BackgroundRunner" as background
  participant "SessionStore" as store

  User -> controller : start visible session
  controller -> live : createSession()
  live -> store : save(session)

  User -> controller : trigger background export
  controller -> background : runOnce()
  background --> controller : success / failure
  @enduml
  ```

  Externally meaningful identifiers:
  - `visibleSessions.json`
  - `backgroundExportEnabled`


- **Test specification:**
  - **Automated tests:**
    - `SessionVisibilityPersistenceTest`
      - `visibleCompletionPersistsSessionInVisibleSessionList`: verify
        visible session completion persists the session in the
        visible-session list.
      - `backgroundRunCompletionDoesNotPersistVisibleSession`: verify
        background run completion does not add the run to the
        visible-session list.
    - `BackgroundSessionFailureTest`
      - `backgroundPathFailureLeavesVisibleSessionsUnchanged`: verify
        background path failure leaves the visible-session list
        unchanged.
  - **Manual tests:**
    - Run one visible session and confirm it appears in the session
      list.
    - Trigger one background export and confirm it stays out of the
      persisted session list.
