---
name: spec-loop-walk-through
description: >-
  Prepare a file-wise review walk-through for an existing branch diff,
  pull request, commit range, or multi-repository change from a local
  or user-trusted source. Use when the user wants review blocks,
  per-file ownership, review reading order, and inline findings
  instead of a merge verdict.
---

# Spec Loop Walk-Through

Use this skill to prepare a human review walk-through for an existing
diff. The output is a durable walk-through document that explains the
changed files in a useful review order and records findings where they
arise.

Before planning or writing the walk-through, read and follow
[walk-through-guidance.md](./walk-through-guidance.md).

If the review depends on provider-backed or other remote evidence and
the source is not already user-trusted, stop and ask before fetching
it.

Keep the code diff as the source of truth. Treat task files, subtasks,
design notes, tickets, and prior discussions as historical orientation
only unless the user explicitly chooses a different review source.

Default route:

1. Identify the branch base and changed-file inventory for every
   involved repository.
2. Read historical context only far enough to understand the review.
3. Propose review blocks, block order, and one owning review block for
   every changed file.
4. Create the file ownership table and all review-block and file-level
   headings before detailed review text.
5. Review each file exactly once inside its owning review block.
6. Keep findings integrated into the walk-through and update the
   document after validated fixes.

Use [spec-loop-clarify-task](../spec-loop-clarify-task/SKILL.md) when a
decision could materially change the walk-through scope, block
assignment, review criteria, or output structure.