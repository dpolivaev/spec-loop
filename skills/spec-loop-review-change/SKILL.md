---
name: spec-loop-review-change
description: >-
  Review an existing change from a local or user-trusted source, such
  as a pull request, merge request, branch diff, commit range, or
  agent-written code. Use when the user needs a merge verdict, a
  file-wise review walk-through, reconstruction of the change after the
  fact, or both.
---

# Spec Loop Review Change

Use this optional skill for work that is already implemented, not for
planning or executing new work.

If trust, the exact change to review, or the comparison range is
unclear, stop and ask before fetching GitHub, GitLab, or Git data.

Read [review-core-guidance.md](./review-core-guidance.md) first.

Then choose the review mode and evidence source together, and load only
the needed guidance files. Load both mode files only when the user
wants both outputs:

- **Assessment** — pick this when the user wants a verdict,
  recommendation, PR or MR review file, or reconstruction of the
  change after the fact. Load
  [assessment-guidance.md](./assessment-guidance.md).
- **Walk-through** — pick this when the user wants file ownership,
  review blocks, reading order, or inline file-wise explanation. Load
  [walk-through-guidance.md](./walk-through-guidance.md).

If the review target is a specific GitHub or GitLab pull request or
merge request page, number, or other remote review target, also load
[github-gitlab-evidence-guidance.md](./github-gitlab-evidence-guidance.md).
Do not load `github-gitlab-evidence-guidance.md` for branch diffs,
commit ranges, current local changes, or other local Git review
targets.

Do not load `spec-loop-plan-task` by default. Only the assessment mode
reuses parts of it, and only for the specific conventions it actually
needs.

Use [spec-loop-clarify-task](../spec-loop-clarify-task/SKILL.md) when
an unresolved choice could materially change trust, evidence source,
comparison range, review mode, document layout, or verdict
expectations.
