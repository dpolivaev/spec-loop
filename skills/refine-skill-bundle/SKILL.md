---
name: refine-skill-bundle
description: >-
  Refine SKILL.md files, workflow guidance, and related skill-bundle
  docs so they are clearer, better layered, and easier for later
  sessions to follow without losing required behavior.
---

Use this skill when asked to review, simplify, refactor, repair, or
structurally reorganize a skill bundle or instruction bundle.

This skill applies to maintenance artifacts such as:
- `SKILL.md`;
- workflow guidance files;
- format or reference docs;
- examples tightly coupled to a skill's behavior; and
- overview or install docs that must change with the skill bundle.

Do not use this skill for active work artifacts whose meaning must be
preserved exactly.

Before editing:
- read the current files that define the skill bundle behavior;
- read [refinement-guidance.md](./refinement-guidance.md);
- re-read any file the user says changed; and
- apply project instructions such as `AGENTS.md` when present.

First decide which target files actually need refinement. Do not
shorten files merely because they are in scope.

Preserve behavior unless the user explicitly asks for a policy change.
If a proposed simplification would change route logic, approval
boundaries, thresholds, source-of-truth rules, or stop conditions,
keep the rule or surface the change explicitly.

Because context is limited, shorter always-read skill files are
usually better. Prefer refinements that shrink, or at least do not
materially increase, the text the model usually must load for the
current route.

A small increase is justified only when it materially improves the
visibility of a safety-critical rule and does not materially enlarge
the normal working set. If a rewrite makes route logic, approval
boundaries, thresholds, source-of-truth rules, or stop conditions
harder to find, or increases the usual working-set size without
comparable safety benefit, treat that as a regression.

When public skill names, file names, or locations change, update
linked docs and overview or install references in the same work item.

This skill is different from exact-meaning compaction:
- exact-meaning compaction preserves artifact meaning exactly; while
- skill-bundle refinement may reorganize rules across kernel,
  guidance, and reference files as long as the resulting behavior is
  preserved or any intentional policy change is made explicit.

Then follow [refinement-guidance.md](./refinement-guidance.md).
