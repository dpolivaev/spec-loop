# Walk-Through Guidance

This file defines the low-level file-wise walk-through.

It covers:

- file ownership by review block;
- reading order;
- human checkpoints;
- walk-through structure;
- inline findings; and
- review discipline.

Shared trust, naming, and writing-style rules stay in
[review-core-guidance.md](./review-core-guidance.md).

## Review unit

Review file-wise.

Each changed file belongs to exactly one review block.
These review blocks are the canonical file grouping for the whole
review, not only for the walk-through.
A file may touch multiple concerns, but it still has one review home.
Review the whole file in that block at the smallest useful level, such
as type, function, method, field, query, migration, config entry, or
test expectation.
Keep all changed elements of that file in the same block even when some
support another topic.

If a file also matters for another topic, mention that briefly in
prose or link to the other section. Do not place the same file in
multiple review blocks.

## Review block ownership

Review blocks are file-owning buckets, not perfect semantic
partitions.

Choose the review block by the main reason the file changed or by the
main question the reviewer must answer for that file.
If one changed element belongs conceptually to another topic, still
review it in the file's own block. If that secondary topic matters,
mention it briefly in prose or link to the other section.

For renamed or moved files, review the old and new path as one file
slot in the block that explains the move or changed responsibility.

For deleted files, review them once in the block that explains the
removed behavior or removed public surface.

## Method

Use this sequence by default:

1. Identify the branch base and changed-file inventory for each
   involved repository.
2. Read task, subtask, ticket, pull request or merge request
   description, or other intent material only far enough to create
   background for the review.
3. Propose the review blocks as the canonical file grouping, their
   order, and the exact file assignment where every changed file
   belongs to exactly one block.
4. Inside each block, order files top-down: from the controlling entry
   point or public surface through the collaborators, data types,
   helpers, deletions, and tests that explain it.
5. Create the file ownership table and all review-block and file-level
   headings before writing detailed review text.
6. For a large or mixed change, present the proposed block order and
   file assignment for human review before writing detailed review
   content.
7. Review each assigned file exactly once.
8. Inside each file, review in source order unless another local order
   explains the changed elements and their relationships better.
9. At the smallest useful level, record:
   - what changed;
   - why it appears to exist;
   - which historical intent, context, or final code behavior it
     appears to support;
   - what contract, runtime path, or rule it affects;
   - relevant tests or missing tests; and
   - findings, open evidence questions, or reviewer attention points
     where they arise.
10. Keep a findings list as part of the walk-through, not as a separate
    afterthought.
11. End each review block with durable notes such as open risks,
    unresolved evidence, reviewer attention points, and useful next
    review focus.
12. End the document with final verification notes or other final
    notes only when they help the reviewer.

Apply the shared review criteria where they help.
Do not turn them into a global merge verdict unless the assessment mode
is also active or the user explicitly asks for one.

## Human checkpoints

For large or mixed changes, prefer an iterative block-wise workflow over
generating the full walk-through in one pass.

The first checkpoint should propose all review blocks, their order, and
one owning block for every changed file.
The human may then accept the proposal, ask to change the order, move
files, split or merge blocks, or discuss the trade-off before detailed
review continues.

Use each checkpoint to validate file ownership, review depth, review
block boundaries, and whether the current findings are useful.

Checkpoints are conversation steps, not sections in the review file.
Update the walk-through file only with the durable outcome of that
discussion when it affects the document.

## Walk-through file shape

Write one walk-through file by default.
Split it into multiple files only when the user explicitly asks for
that or one file would become impractical to review.

Keep the file ownership table readable.
Prefer file names only.
Add only as many parent folders as needed to disambiguate files with
the same name.
Use explicit rename arrows and compact review-block labels.
Avoid long full paths inside table cells.

Link both columns in the file ownership table.
File labels should link to the corresponding file-level headings, and
review-block labels should link to the corresponding review-block
headings.
Use stable explicit anchors when Markdown's generated section links
would be unclear, too long, or fragile.

Use this structure unless the change needs something more specific:

```markdown
# Branch Review Walk-Through

## Scope

## Intent Summary

## File Ownership Table

| File | Review block |
| --- | --- |
| [<minimal file label>](#file-<stable-id>) | [<Owning Theme>](#review-block-<stable-id>) |

## Review Findings

## Review Block 1: <Owning Theme>

<a id="file-<stable-id>"></a>

### <minimal file label>

**Primary reason:** <why this file belongs here>

#### <Type, function, test, or other changed element>

#### <Nested element when useful>

**Review finding:** <inline finding when present>

## Verification Notes
```

The `Review Findings` section should provide a compact list for quick
triage.
Keep the detailed explanation in the owning file section where the
finding was discovered.

In `Verification Notes` and any inline verification reference,
identify each cited test precisely enough that the reviewer can find it
directly.
Always include the owning test file and the complete in-file path to
that specific test case.
Use the language or test framework's normal separator in that path.
If the outermost test class or suite name is the same as the file name
without the extension, omit that duplicate and start with the next
nested container.
For Java, use `.` between path segments, not `#`.
Do not cite only the leaf test name.

## Ordering inside each review block

Order files top-down, from entry point to dependencies.
Start with the block's controlling entry point, public contract,
orchestration owner, or first file a reviewer must understand for that
block.
Then follow the direct collaborators, data and value types, helpers,
deletions, and verification evidence needed to understand that owner.

For API or data-structure blocks, that usually means public contract
first, generated or serialized shape next, consuming or persistence
surfaces after that, and tests next to the production surface they
verify.

## Tests and evidence

Group tests with production files by default.
Place each test file immediately after the production file or production
group it verifies.

Use a separate test or evidence review block only for tests and
fixtures that cannot be naturally owned by one changed production
block, such as broad cross-cutting regression evidence or shared
fixture infrastructure.
Do not leave a test in a residual block merely because it is a test.

Review the test file as a changed file in its own right.
Walk through changed fixtures, helpers, renamed tests, new tests,
assertions, covered behavior, and blind spots at the level needed to
explain what the test proves.
When you cite a referenced test, include the owning test file and the
complete in-file path to that test case.
Use the language or test framework's normal separator in that path.
If the outermost test class or suite name duplicates the file name,
omit that duplicate and keep the rest of the path.
For Java, use `.` between path segments, not `#`.
Even when tests live in a separate block, cite relevant test evidence
inline in production-file sections.

## Findings

Integrate findings into the walk-through where the evidence appears.

For each finding, include:

- severity or review priority;
- affected file and element;
- observed evidence from the diff;
- expected behavior source: final code shape, existing contract, or
  historical context;
- why it matters; and
- the smallest useful follow-up question or fix direction.

Prefer concrete findings over broad suspicion.
If the evidence is not yet enough to call something a defect, mark it as
an open question and state what would confirm or disconfirm it.

When a finding is fixed during the walk-through process and the fix is
validated, delete that finding from the walk-through document.
If a finding is accepted, deferred, or intentionally left unfixed,
keep it in the walk-through document and update its wording only as
needed to reflect that current state.
Do not keep fixed findings in place as history.
Rewrite nearby prose only when needed so the remaining walk-through
still matches the current state.

## Review discipline

Do not let semantic grouping hide file-wise review.

Do not duplicate files across review blocks.

Do not split one file's changes across review blocks.
Once a file is attached to an owning review block, discuss every changed
element of that file in that review block, even when the element
belongs to another cross-cutting concern.

Do not treat task or subtask text as final-design authority.
It is background unless the current review explicitly establishes that
the final code still follows it.

Do not turn refactoring into a behavior claim without checking the old
and new call paths.

Do not review tests only as changed files.
Map each meaningful test change back to the behavior, contract, or
removed surface it protects.

Do not defer findings until the end if the finding is easier to
understand next to the changed element.
