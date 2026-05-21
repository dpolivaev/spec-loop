# Example task: Session-state boundary after approval preparation

This compact example shows a task after `spec-loop-prepare-implementation-approval`
polishes it for implementation approval seeking.

It demonstrates:
- final decisions before detail;
- a class diagram plus a sequence diagram;
- a compact identifier list instead of a repeated responsibility table.

Use it as a pattern collection, not as a required task size.

- **Scope:** Add a transient background session path that does not
  enter the persisted live-session registry.
- **Motivation:** Keep the visible session model simple while allowing
  one-off background execution.
- **Scenario:** A user starts one visible session and also triggers a
  background export. The background export runs separately and should
  not appear in the persisted session list.
- **Briefing:** The change touches one session manager, one transient
  runner, and one persistence boundary.
- **Research:** The current design stores all work through the visible
  session registry, so even one-off background execution would be
  persisted and listed unless a second boundary is introduced.

  ```plantuml
  @startuml
  component "UI" as ui
  component "LiveSessionManager" as live
  component "PersistenceStore" as store

  ui --> live : start visible work
  live --> store : persist session data
  @enduml
  ```

- **Design:**
  Final structural decisions:
  1. Keep one visible session path inside the live-session registry.
  2. Add one transient background path outside the registry.
  3. Persist only the visible path.

  ```plantuml
  @startuml
  set separator none

  package "example" {
    class SessionController
    class LiveSessionManager
    class BackgroundRunner
    class SessionStore
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

  No separate responsibility table is kept here because the diagrams
  already carry the structural review load.

- **Test specification:**
  - Automated tests:
    - visible sessions are persisted;
    - background runs are not persisted;
    - failures in the background path do not alter the visible-session
      list.
  - Manual tests:
    - run one visible session and confirm it appears in the session
      list;
    - trigger one background export and confirm it stays out of the
      persisted session list.
