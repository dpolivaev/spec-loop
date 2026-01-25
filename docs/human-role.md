# The Human Developer’s Role

A central assumption of this framework is that **the human developer remains the primary source of understanding and intent**.

The model is not treated as a replacement for the developer, but as a powerful implementation and reasoning aid that operates under explicit constraints.

## Working at the level of understanding, not implementation detail

In this process, the human developer primarily operates at higher levels:

- clarifying *what problem is being solved*,
- defining *boundaries and non-goals*,
- identifying *risks and unknowns*,
- shaping *design intent*,
- and validating *correctness and completeness*.

Details of implementation are delegated to the model **only after** understanding, scope, and verification expectations are explicit.

## Maintaining visibility across levels

The structure of task files (scope, research, design, test specification, developer briefing) allows the developer to work on the same problem simultaneously at multiple levels:

- conceptual intent,
- architectural constraints,
- concrete design,
- verification strategy.

This prevents the failure mode where focusing on low-level code causes the overall picture to be lost.

## Developer Briefing as a soft entry point

Each task includes a **Developer Briefing** section to provide a gentle entry point:

- for reviewers unfamiliar with the codebase,
- for the developer validating design decisions outside daily context,
- and for onboarding new team members.

The briefing explains what matters, where to look first, and why the design looks the way it does.
