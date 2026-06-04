# Refinement guidance for `refine-skill-bundle`

Use this guidance to improve skill quality without relying on hidden
conversation history.

## 1. Classify file roles first

For each file, decide which role it serves:

- **Kernel skill file** = `SKILL.md` with mandatory routing,
  approvals, source-of-truth rules, thresholds, and handoffs.
- **Workflow guidance** = procedural branch order, checkpoints,
  allowed outcomes, and path-specific mechanics.
- **Reference or format doc** = normative structure, exact allowed
  forms, or reusable formatting rules.
- **Example or overview doc** = patterns, discoverability, and install
  or maintenance guidance.

Do not compact all roles the same way.

## 2. Target shape by file role

### Kernel skill file

Keep only the shortest form that still preserves safe behavior.

Keep in `SKILL.md`:
- route selection and handoffs;
- approval and phase boundaries;
- source-of-truth rules;
- stop conditions;
- durable-state rules; and
- important thresholds.

Move out of `SKILL.md` when possible:
- long procedures;
- repeated edge-case prose;
- formatting templates;
- examples; and
- explanatory repetition.

### Workflow guidance

Make it procedural.

Prefer:
- explicit branch order;
- named outcomes or routes;
- clear checkpoints;
- compact lists; and
- one authoritative explanation of each policy.

Keep the real mechanics here, not in examples.

### Reference or format doc

Prefer exactness over brevity.

Compact duplication, but do not erase:
- normative structure;
- required forms;
- constraints;
- allowed variants; or
- review-relevant examples.

### Example or overview doc

Keep it current with the actual bundle structure.

If a skill is optional, say so plainly.
If a file name or skill name changes, update linked overview and
install docs.

## 3. Simplification moves that are usually good

- Merge repeated ownership statements.
- Name a concept once, then reuse the shorter name.
- Convert repetitive prose into compact bullets.
- Promote hidden thresholds, limits, and batch sizes into visible
  bullets or headings.
- Separate agent-only behavior from user-facing reference docs.
- Keep distinctions explicit: route vs phase, symptom vs root cause,
  canonical state vs notes, blocking vs non-blocking.
- Keep examples only when they show a non-obvious pattern.

## 4. Simplification moves that are usually wrong

- Deleting meaningful constraints just to shorten the file.
- Merging distinct routes, outcomes, or checkpoints.
- Hiding approval boundaries inside prose where they become easy to
  miss.
- Moving normative format rules into examples.
- Claiming behavior is preserved when thresholds, route logic,
  approval gates, or source-of-truth rules changed.
- Putting agent-policy rules into docs intended to be shared
  human-facing references.

## 5. Verification checklist

Before finishing, verify that:

- file roles are clear;
- source-of-truth rules are still explicit;
- allowed routes or outcomes are still explicit;
- approval boundaries are still explicit;
- thresholds and limits are still easy to find;
- stop conditions still exist where needed;
- linked docs are updated when names or locations changed; and
- no rule became ambiguous just because the prose got shorter.

If you are not sure whether a simplification preserved behavior, say so
plainly and treat it as unresolved instead of smoothing it over.
