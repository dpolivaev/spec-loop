# Security Evaluation Guidance

Security evaluation is mandatory in both assessment and walk-through
modes. Perform it during the existing inspection of changed files and
relevant call paths, not only while filling in the report. The Trust
rules protect the reviewer from untrusted instructions; they do not
replace evaluation of the software's security behavior.

Keep the evaluation relative to the reviewed change and its affected
contracts. Inspect each changed file for security consequences,
including files considered mechanical or ancillary. Follow relevant
unchanged callers and dependencies when needed to establish a reachable
path or an existing protection; do not turn this into an unrestricted
repository audit.

Distinguish:

- **Malfunction:** unintended behavior such as a crash, data loss, or
  resource exhaustion. Review it under implementation correctness even
  when no security consequence is established.
- **Vulnerability:** behavior that can compromise confidentiality,
  integrity, or availability under identified conditions. This includes
  vulnerabilities in added dependencies and their transitive dependencies.
  State who or what controls the input or operation and which protection
  is missing or bypassed.
- **Possible backdoor:** observed concealed or undocumented access,
  data transfer, execution, or bypass of security controls that needs
  explanation. Describe the behavior and evidence; do not infer
  malicious intent from authorship, AI use, or unfamiliar code alone.

Explain the concrete harm before naming the security category: what
information could be exposed, what could be changed, or what could stop
working, and under which conditions.

Clearly distinguish an established security consequence, an ordinary bug,
and a possible security consequence that still needs evidence. A bug found
during security evaluation is not automatically a vulnerability. Do not
describe an executed helper check as an executed application-level failure.

For applicable surfaces, check:

- untrusted inputs, parsers, serialized data, file paths, and generated
  output: validation, escaping, injection, unsafe loading, size limits,
  allocation, recursion, and processing bounds;
- authentication, authorization, permissions, read-only boundaries,
  secrets, and sensitive data: who can read, change, or disclose what;
- network communication, subprocesses, dynamic code, file writes, and
  logging: destinations, payloads, triggers, privileges, and unexpected
  side effects;
- hidden triggers, undocumented endpoints or access, unexpected data
  collection or transfer, and bypasses of security controls; and
- dependencies, build and installation scripts, launch options,
  configuration, packaging, and distribution changes that affect trust,
  execution, or protections.

For added dependencies, check published vulnerabilities with this
procedure:

1. Identify the exact resolved package/version combinations introduced
   by the additions, including transitive dependencies and versions
   changed by dependency resolution. Use available lockfiles, SBOMs,
   or dependency graphs. Do not guess resolved versions from declared
   ranges or recheck unrelated unchanged dependencies.
2. Use the [OSV API](https://google.github.io/osv.dev/api/), preferably
   its [batch endpoint](https://api.osv.dev/v1/querybatch), with ecosystem,
   package name, and exact version. For an unsupported ecosystem, use
   one supported existing scanner or the
   [GitHub Advisory Database](https://github.com/advisories).
   Do not routinely repeat the same lookup across multiple services.
3. Retrieve advisory details only for matches. Verify affected version
   ranges and relevant usage, exposure, and existing protections. Use
   publisher advisories when needed to resolve ambiguity. An advisory
   match establishes a reported affected dependency, not by itself a
   reachable exploit in the application.
4. In `Security evaluation`, record the checked package versions,
   evidence source and date, advisory identifiers, applicability, and
   unresolved concerns. State `No published advisory found` only for
   the versions and database evidence actually checked; it does not
   establish absence of unknown vulnerabilities or possible backdoors.
5. If resolved versions, service coverage, or lookup results are
   unavailable, record the limitation rather than guessing or claiming
   no vulnerability. Do not install tools, execute reviewed build
   scripts, or upload source code or private package metadata without
   explicit user approval.

For each finding or unresolved concern, trace the input or trigger
through relevant validation and control checks to the consequential
operation. Record exact code references, reachable conditions, impact,
existing protections, and what evidence confirms or remains needed to
resolve the concern. Source tracing can establish a finding without a
live exploit; distinguish it from an executed reproduction. Prefer safe,
bounded checks and do not perform destructive or resource-exhaustion
experiments on the user's environment.

Include a mandatory `Security evaluation` section with:

- **Inspected surface:** the security-sensitive paths and protections
  inspected. For a change without a security-sensitive surface, explain
  what was checked to reach that conclusion rather than writing `N/A`.
- **Findings and unresolved concerns:** references to identified
  vulnerabilities, possible backdoors, security-relevant malfunctions,
  and unresolved concerns. Distinguish established behavior from
  suspicion and missing evidence.
- **Verification and limits:** checks performed, their results and
  boundaries, checks not performed, and any security-sensitive paths
  that could not be assessed and why.

Write `No security issue identified within the inspected scope` only
when the stated inspection supports it and no unresolved security
concern remains. Do not claim that the change is secure or contains no
backdoor merely because none was identified. Incomplete inspection or
missing evidence must remain explicit, not become a clearance statement.

Keep each finding's explanation in one place under the canonical review
blocks and reference it from `Security evaluation`. When both modes are
produced, keep the overall security evaluation in the assessment and
reference it from the walk-through; retain file-level evidence in the
owning walk-through file sections.

Security findings affect the same `Implementation`, `Verification`, and
requested `Review outcome` as other findings. Do not add a competing
security verdict. Keep ordinary malfunctions without an established
security consequence in the correctness findings rather than relabeling
them as vulnerabilities.
