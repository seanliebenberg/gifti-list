# Roadmap — Gifti List (Backend)

My living checklist.  ✅ for done, ⏳ for in‑progress, and ⏭️ for next.

## Scope

Backend service (Spring Boot). But want to track my overall progress over everything.

---

## Backend API

* ✅ Boot project skeleton (Spring Boot 3, Java 21)
* ✅ Wishlist endpoints: `GET /api/wishlists`, `POST /api/wishlists`
* ⏭️ `GET /api/wishlists/{id}`
* ⏭️ `DELETE /api/wishlists/{id}`
* ⏭️ `PUT/PATCH /api/wishlists/{id}` (update)
* ⏭️ Pagination & sorting for list

## DTOs, Validation & Errors

* ✅ Request/response DTOs (`CreateWishlistItemRequest`, `WishlistItemResponse`)
* ✅ Bean Validation on request DTOs (`@Valid`, `@NotBlank`, `@Size`)
* ✅ Global `ApiExceptionHandler` with consistent error body
* ✅ `201 Created` + `Location` header on create
* ✅ Explicit `produces/consumes: application/json`
* ⏭️ 404 mapping for not‑found (service throws; handler maps)
* ⏭️ Input normalization (trim, basic URL validation)

## Persistence

* ✅ `WishlistItem` JPA entity & repository
* ⏭️ Flyway migrations (baseline schema, future updates)
* ⏭️ Constraints/indexes (e.g., per‑user uniqueness when users land)

## Testing

* ✅ Controller tests (standalone `MockMvc` + validator + advice; service mocked)
* ✅ Repository integration test (Postgres via Testcontainers)
* ✅ `test` profile uses H2 by default for speed
* ⏭️ Service unit tests (business rules)
* ⏭️ Separate `integrationTest` source set & Gradle task
* ⏭️ JaCoCo coverage and CI report
* ⏭️ Optional: Spring REST Docs or Contract tests for API

## Local Dev & Docker

* ✅ Docker Compose for Postgres 
* ✅ `application-local` profile points to Compose Postgres
* ⏭️ Build backend container image (Spring Buildpacks: `bootBuildImage`)
* ⏭️ Alternative Dockerfile (multi‑stage) if preferred
* ⏭️ Publish image to GHCR on tags

## CI/CD

* ✅ GitHub Actions: build & test backend on push/PR (path‑filtered to `apps/backend/**`)
* ✅ CodeQL security scanning (default setup)
* ✅ Removed Qodana from pipeline (too noisy/blocked)
* ⏭️ Upload test reports & JaCoCo coverage as artifacts
* ⏭️ Build & push Docker image on `main` and tags
* ⏭️ Staging deploy job (env‑protected)
* ❄️ Release workflow (tag + changelog notes)

## Security

* ✅ `SECURITY.md` policy
* ⏭️ Dependabot for Gradle & GitHub Actions
* ⏭️ Minimal branch protections (even if you push to `main`, protect from force‑push & deletion)
* ⏭️ Secret scanning alerts (enable in repo settings)

## Observability

* ⏭️ JSON logging format
* ⏭️ Micrometer metrics via Actuator
* ⏭️ Prometheus scrapable endpoint & basic Grafana dashboard
* ❄️ OpenTelemetry tracing (later)

## Kubernetes (later)

* ⏭️ Container image readiness (liveness/readiness endpoints)
* ⏭️ K8s manifests (Deployment, Service, ConfigMap/Secret)
* ⏭️ Ingress (NGINX/KinD/Minikube first)
* ⏭️ External Postgres connection (managed DB) or dev‑only Helm Postgres
* ❄️ Helm chart / Kustomize
* ❄️ CD from GitHub Actions to cluster (kubectl or ArgoCD)

## Documentation & DX

* ✅ `docs/testing.md` (test strategy)
* ✅ `SECURITY.md`
* ⏭️ Update `README.md` with run commands, profiles, and API link
* ⏭️ Document local Docker Compose workflow
* ⏭️ Link to OpenAPI UI (`springdoc`) in README

## Product/Domain (future)

* ❄️ User accounts & ownership of wishlist items
* ❄️ Authentication/authorization (scopes/roles)
* ❄️ Sharing & permissions model

---

### Notes

* Keep tests in a pyramid: many unit tests, fewer web/controller tests, very few container/integration tests.
* Local dev: run with `--spring.profiles.active=local` to use Compose Postgres.
* CI: tests default to H2; Postgres‑specific tests use Testcontainers automatically.
