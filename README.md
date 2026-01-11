# Gifti List 

Family gift-sharing made simple: wishlists, occasions (birthdays/Christmas), Secret Santa, and family groups.

---

## Documentation

- **[Project overview & index](./docs/index.md):** Start here for a high-level view of the project.
- **[Architecture](./docs/architecture.md):** Learn about the system design and key components.
- **[Development setup](./docs/dev-setup.md):** Step-by-step guide to get your environment running.
- **[Testing](./docs/testing.md):** How to run, write, and understand tests.
- **[Roadmap](./docs/roadmap.md):** Upcoming features and progress tracking.

> 📚 Full docs live in [`/docs`](./docs):
> - Start here: [`docs/index.md`](./docs/index.md)
> - Architecture: [`docs/architecture.md`](./docs/architecture.md)
> - Dev setup: [`docs/dev-setup.md`](./docs/dev-setup.md)
> - Testing setup: [`docs/testing.md`](./docs/testing.md)
> - Roadmap -> track progress [`docs/roadmap.md`](./docs/roadmap.md)

---

## Security

See [SECURITY.md](./SECURITY.md) for our security policy and how to report vulnerabilities (via GitHub Security Advisories).

## Status
- ✅ Backend (Java 21 + Spring Boot) running locally
- 🛠 Web app (React) planned
- 🗺️ Angular + Mobile (React Native) later

---
## Quality assurance

Test coverage includes:
- **Integration test (JPA repository):** Uses Testcontainers with PostgreSQL to verify real DB integration.
- **Smoke test:** Ensures the Spring Boot application context loads without errors.
- **Unit & controller tests:** Fast tests using mocks or in-memory DB (H2), covering business logic and web endpoints.

See [docs/testing.md](./docs/testing.md) for details.

---

## Quick start (backend)

- start db (postgres container) from project root
```bash
docker compose up -d
```
- start app
```bash
cd apps/backend
./gradlew bootRun
```
