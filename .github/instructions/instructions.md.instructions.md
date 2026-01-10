---
applyTo: 'This repo (Java + Maven)'
description: 'Set of instructions and guidelines for AI agents contributing to this Java + Maven project.'
version: '1.1'
lastUpdated: '2026-01-10'
status: 'Active'
---

# AI Agent Implementation Guide (Consolidated)

---

## 1) Non‑Negotiable Operating Rules

1. **WIP limit = 1**: Work on only one task/issue at a time.
2. **No auto‑progress**: Do not proceed to the next task or phase without asking for confirmation.
3. **Acceptance criteria required**: A task is “Done” only when all acceptance criteria are met.
4. **Testing required**: New or changed behavior must include unit tests. Target **≥80% coverage** where applicable.

---

## 2) Core Engineering Principles

1. **Null-safety**: Do not accept or return `null` in Java. Use `Optional<T>` where absence is valid.
2. **Immutability first**: Prefer `final` fields/locals, records for DTOs, and immutable collections.
3. **Pure logic where possible**: Keep business logic deterministic and testable; isolate side effects at boundaries.
4. **Simple over clever**: Prefer clear, maintainable designs and loose coupling.
5. **No deprecated APIs**: Do not introduce deprecated APIs (especially those marked for removal). Use the recommended replacement.
6. **Prefer built-in provider/library functionality**: Use maintained framework/library capabilities over custom implementations.

---

## 3) Java Style & Conventions

### Language & idioms
- Prefer Java 21 features where appropriate (records, pattern matching, etc.).
- Prefer `final var` for locals when it improves readability.
- Prefer immutable data structures.

### Collections
- Prefer `List.of()`, `Map.of()`, `Set.of()`.
- Avoid mutable collections inside domain objects.

### Naming
- Classes: `PascalCase`
- Methods: `camelCase`
- Constants: `UPPER_SNAKE_CASE`
- Packages: `lowercase`

### Formatting (Google Java Style)
- Line length: **120**
- Indentation: **2 spaces**
- Braces: K&R
- Add a blank line after the opening `{` of a class/interface/enum/record.

### Imports (strict)
- Remove unused imports immediately.
- Remove unused code immediately (commented-out blocks, unused members, unused locals).
- Ordering:
    1. Static imports (alphabetical)
    2. Non-static imports (alphabetical)
- No wildcard imports unless importing **>99** classes from the same package.

---

## 4) Constants

- Reusable **application-level** constants used across multiple components must be centralized in `AppConstants.java`.
- Component-specific constants stay in the component.
- Single-use constants should remain local.

---

## 5) Configuration

### Priority (high → low)
1. Environment variables (especially secrets)
2. `application-{profile}.yml`
3. `application.yml`

### Rules
- All configuration must support environment variable overrides (use `${ENV_VAR:default}` when appropriate).
- Profile files should contain only true environment differences (e.g., log level, feature flags).
- Never commit secrets.

---

## 6) Testing Strategy (Unit Tests)

### Unit test requirements
- Mock all external systems (HTTP, DB, filesystem, third-party APIs).
- No network calls. Tests must be fast and deterministic.
- Do not add framework-startup annotations to unit tests.

### Maven verification command (required)
```bash
mvn clean compile && mvn test-compile && mvn test
```

### Mockito matcher hygiene
- Use primitive matchers (`anyInt()`, `anyLong()`, etc.) for primitives.
- Mock all constructor dependencies.

---

## 7) Documentation

- Keep code self-documenting; comment “why” for complex logic.
- Document required environment variables in `README.md`.
- Record material architectural decisions in `docs/adr/`.
- When a task is completed, put verification results (build/tests) in the related GitHub issue comment (do not add separate verification files).
