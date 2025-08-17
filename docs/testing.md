testing.md

This page explains the test architecture I chose, and how I plan to use it in practice. 

# Goals

-   Fast, deterministic feedback locally and in CI.

-   No accidental dependency on a running Postgres for most tests.

-   Realistic database coverage where it matters (repositories/integration).

-   Minimal external dependencies (no Docker, no Postgres).
  
-   encourage myself to do tdd

# Profiles 
**How I expect the environments to behave**

-   Default (application.properties) – no DB config; the app can start anywhere.

-   Local dev (application-local.properties) – points to Docker Postgres for day‑to‑day development.

-   Tests (application-test.properties) – in‑memory H2 for speed and isolation.

**CI and ./gradlew test run with the test profile, so most tests use H2. Tests that need Postgres opt‑in via Testcontainers and override the datasource dynamically.**

# Day‑to‑day workflow

Run all tests regularly (./gradlew test) — behaves the same locally and in CI.

**When adding a feature:**

-   Controller layer → write a fast controller test that does not touch the DB (standalone MockMvc style with a mocked service). Assert routing, status, headers, and JSON shape.

-   Service layer → write plain unit tests (no Spring context). Focus on business rules and edge cases.

-   Repository layer → if behavior depends on SQL/ORM mapping, add a focused repository/integration test that uses Testcontainers Postgres.

# When to write which test

-   Pure logic (transformations, rules) → Service unit test.

-   HTTP behavior (routes, validation, error mapping) → Controller test (no DB).

-   Custom queries/mappings/constraints → Repository / Testcontainers test.

-   Business‑critical flow → Integration test (small number only).


# TDD strategy

-   Use Red → Green → Refactor loops while keeping cycles fast:

-   Start with a failing unit test for a service rule. Implement the smallest code to pass. Refactor. Repeat.

-   For endpoints, start with a failing controller test that describes the expected HTTP behavior (status, payload). Implement controller/service collaboration to pass. Refactor.

-   When persistence behavior matters (e.g., query semantics, unique constraints), write a failing repository test that runs against Postgres via Testcontainers. Keep these tests focused and few to preserve fast feedback.

-   Add at most one integration test per critical user journey as a final safety net.

# CI behavior

-   GitHub Actions runs with the test profile → H2 for most tests; no external services needed.

-   Repository/integration tests spin up Postgres in a container automatically and override datasource settings at runtime.

-   Path filters keep backend CI runs limited to relevant changes.

# Tasks & layout (current)

All tests live under src/test/java.

Run everything with: ./gradlew test (uses the test profile).

Container‑backed tests (Testcontainers) can stay here for now; they will start Docker automatically when needed.


# Why this setup works well

-   Speed: unit/slice tests avoid Spring and the DB; smoke tests are few; container tests are targeted.

-   Reliability: no hidden dependency on a developer’s local Postgres.

-   Realism where needed: DB‑dependent parts run against real Postgres.

-   Consistency: the way tests run locally matches CI.


