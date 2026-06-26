---
name: spec-loop-compact-task-file
description: >-
  Compact an active task file without changing meaning. Use when
  converting a no-subtask task to subtask form, when adding a new
  subtask after earlier subtasks already contain full section content,
  when duplicated section content should be compacted before approval
  seeking, or when a task file has become too large to use safely.
---

Task-file path only.

## Source of truth

Use the active task file as the source of truth.

Before compacting, re-read the current subtask and the task-level
sections and diagrams it depends on.

## Rules

Compaction must preserve meaning exactly.

It may move, rewrite, or reorganize section content to remove
duplication and shrink the active task file, but it must preserve:
- decisions;
- dependencies;
- constraints;
- review-relevant facts;
- findings; and
- implementation and test expectations.

Keep shared context at task level.
Keep one authoritative location for each piece of section content.
Keep brief references or restatements only when remaining work still
needs them to understand the current subtask safely.

## Allowed changes

- Move subtask-specific Research, Design, Test specification, and
  Findings into the relevant subtask.
- Rewrite duplicated section content into a shorter equivalent form.
- Reorganize section content to remove duplication and make the
  active task file smaller.
- Compact duplicated section content instead of repeating it.

## Forbidden changes

Do not use this skill to:
- make new design decisions;
- narrow or expand scope;
- remove still-needed detail; or
- split the task into multiple files.

## Stop conditions

If no compaction is needed under these rules, make no task changes and
return control to the invoking workflow.

If compaction reveals a material unresolved question, stop and use
[spec-loop-clarify-task/SKILL.md](../spec-loop-clarify-task/SKILL.md).

When compaction is complete, return control to the workflow that
invoked this skill.
