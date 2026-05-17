# 🧪 Testing

This page explains the test architecture I chose, and how I plan to use it in practice.

---

# 🎯 Goals

* Fast, deterministic feedback locally and in CI.
* No accidental dependency on a running Postgres for most tests.
* Realistic database coverage where it matters (repositories/integration).
* Minimal external dependencies for most tests.
* Encourage myself to do TDD.
* Support both synchronous and asynchronous (event-driven) flows.

---

# ⚙️ Profiles

**How I expect the environments to behave**

* Default (`application.properties`) – no DB config; app can start anywhere.
* Local dev (`application-local.properties`) – uses Docker (Postgres + RabbitMQ).
* Tests (`application-test.properties`) – in-memory H2 for speed and isolation.

CI and `./gradlew test` run with the test profile.

* Most tests use H2.
* Tests that need Postgres opt-in via Testcontainers.
* Messaging tests should avoid requiring a running RabbitMQ unless explicitly needed.

---

# 🔄 Day-to-day workflow

Run all tests regularly:

```bash
./gradlew test
```

---

## When adding a feature

* Controller → fast controller test (MockMvc, no DB)
* Service → plain unit tests (no Spring)
* Repository → Testcontainers Postgres if needed
* Messaging → test publisher/consumer behavior appropriately (see below)

---

# 🧠 When to write which test

* Pure logic → Service unit test
* HTTP behavior → Controller test (no DB)
* DB mapping/queries → Repository / Testcontainers test
* Business-critical flow → Integration test (few only)
* Async/event behavior → Messaging test (see below)

---

# 🐇 Messaging (RabbitMQ)

Event-driven behavior is tested at different levels depending on intent.

## 1. Publisher tests (unit)

* Mock `RabbitTemplate`
* Verify that the correct message is sent

```java
verify(rabbitTemplate).convertAndSend("gift-events", expectedMessage);
```

👉 Fast and deterministic
👉 No broker required

---

## 2. Consumer tests (unit-ish)

* Call the listener method directly
* Mock dependencies (e.g. services)

```java
listener.handle(event);
```

👉 Treat listener like a normal class
👉 Do not require RabbitMQ

---

## 3. Messaging integration tests (optional)

Use only when needed:

* Verify end-to-end publish → consume flow
* Use Testcontainers (RabbitMQ) or embedded setup

👉 Keep these **few**
👉 Focus on wiring, not business logic

---

## Rule of thumb

> Most messaging behavior should be tested without RabbitMQ running.

Only test the broker when:

* wiring is complex
* serialization matters
* critical flows depend on it

---

# 🔴🟢 TDD strategy

Use Red → Green → Refactor loops while keeping cycles fast:

* Start with a failing unit test for service logic
* Add controller tests for API behavior
* Add repository tests for DB behavior
* Add messaging tests when introducing async behavior

For messaging:

* Start by testing publisher intent (was event sent?)
* Then test consumer logic independently
* Only add integration tests if necessary

---

# 🤖 CI behavior

* GitHub Actions runs with test profile
* H2 for most tests
* Testcontainers used for DB-specific tests
* Messaging tests should not require external services by default

---

## Observability checks

Basic observability endpoints should be verifiable locally:

* `/actuator/health`
* `/actuator/prometheus`

These are mostly configuration/infrastructure checks rather than business tests.

---

# 🐳 Docker smoke testing

The application can also be verified as a fully containerized stack.

## Purpose

These checks validate that:

* the backend Docker image builds successfully
* the backend container starts correctly
* PostgreSQL and RabbitMQ connectivity work
* the application exposes health endpoints correctly

This is primarily an infrastructure/runtime verification rather than a business logic test.

---

## Start the full stack

From the repository root:

```bash
docker compose up --build
```

Or run in the background:

```bash
docker compose up -d --build
```

---

## Verify backend health

```bash
curl -f http://localhost:8080/actuator/health
```

Expected response:

```json
{"status":"UP"}
```

This verifies that:

* Spring Boot started successfully
* the backend is reachable on port `8080`
* the container networking works
* datasource and RabbitMQ configuration are valid

---

## Useful Docker debugging commands

View backend logs:

```bash
docker compose logs -f backend
```

Stop the stack:

```bash
docker compose down
```

Reset local volumes completely:

```bash
docker compose down -v
```

---

## Notes

Inside Docker Compose, services communicate using Compose service names:

```text
postgres
rabbitmq
```

not `localhost`.

The backend Docker image is defined in:

```text
apps/backend/Dockerfile
```

The full stack is orchestrated by:

```text
compose.yaml
```

---

# 📁 Tasks & layout (current)

All tests live under `src/test/java`.

Run everything with:

```bash
./gradlew test
```

Testcontainers-based tests:

* Spin up dependencies only when required
* Should remain limited in number

---

# ✅ Why this setup works well

* Speed: unit tests dominate
* Reliability: no hidden dependency on local services
* Realism: Postgres tested where needed
* Scalability: messaging can be added without slowing feedback loop
* Consistency: local = CI behavior
