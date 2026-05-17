# Development Setup

## ✅ Prerequisites

* **JDK 21** (Temurin/Zulu)
* **Node 20+** (npm or pnpm)
* **Docker**
* **kind** and **Tilt** for local K8s (optional)

---

# 🚀 Getting Started

## Option A — Infrastructure in Docker + backend in IntelliJ/Gradle (recommended)

Recommended for day-to-day development.

### 1) Start infrastructure

From repo root:

```bash
docker compose up -d postgres rabbitmq prometheus grafana
```

This starts:

* PostgreSQL
* RabbitMQ
* Prometheus
* Grafana

RabbitMQ UI:
👉 [http://localhost:15672](http://localhost:15672)

* user: `guest`
* password: `guest`

Grafana:
👉 [http://localhost:3001](http://localhost:3001)

Prometheus:
👉 [http://localhost:9090](http://localhost:9090)

---

### 2) Run backend locally

```bash
cd apps/backend
./gradlew bootRun
```

The backend runs on your machine and connects to:

* PostgreSQL: `localhost:5432`
* RabbitMQ: `localhost:5672`

API:
👉 [http://localhost:8080](http://localhost:8080)

Swagger UI:
👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Health endpoint:
👉 [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)

Prometheus metrics:
👉 [http://localhost:8080/actuator/prometheus](http://localhost:8080/actuator/prometheus)

---

### 3) Run frontend

```bash
cd apps/web-react
npm install
npm run dev
```

Frontend:
👉 [http://localhost:3000](http://localhost:3000)

---

## Option B — Full Docker Compose stack

Use this to verify the whole system runs in containers.

From repo root:

```bash
docker compose up --build
```

This starts:

* Backend app
* PostgreSQL
* RabbitMQ
* Prometheus
* Grafana

The backend runs inside Docker and connects to services using Docker Compose service names:

* PostgreSQL: `postgres:5432`
* RabbitMQ: `rabbitmq:5672`

---

## 🐳 Docker Commands

### Start stack

```bash
docker compose up
```

### Start stack in background

```bash
docker compose up -d
```

### Rebuild after backend or Dockerfile changes

```bash
docker compose up --build
```

### View backend logs

```bash
docker compose logs -f backend
```

### Stop stack

```bash
docker compose down
```

### Reset local database volumes

```bash
docker compose down -v
```

> `-v` removes Docker volumes, including local Postgres data.

---

## 🐳 Docker Structure

Backend Docker image:

```text
apps/backend/Dockerfile
```

Full local stack definition:

```text
compose.yaml
```

Important Docker networking concept:

Inside Docker Compose:

```text
localhost = the current container
```

Containers communicate using Compose service names:

```text
postgres
rabbitmq
backend
```

not `localhost`.

---

## Option C — Local K8s (kind + Tilt)

Experimental / optional local Kubernetes workflow.

### 1) Create local cluster

```bash
kind create cluster --name giftilist
```

### 2) Start Tilt

```bash
tilt up
```

### 3) Open services

* Web: [http://localhost:3000](http://localhost:3000)
* API Swagger: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

# 🔌 OpenAPI / Clients

* Live spec: `GET /v3/api-docs`
* Swagger UI: `/swagger-ui.html`
* Optional: generate a TypeScript client into `packages/api-client`

---

# 🐇 RabbitMQ (local)

Broker:

```text
localhost:5672
```

Management UI:

👉 [http://localhost:15672](http://localhost:15672)

Used for:

* asynchronous events
* decoupled side effects
* notifications
* audit/event pipelines

---

# 🌐 i18n

* Source language: English
* Translations: Dutch (`public/locales/en|nl/*.json`)

Libraries:

* Web: `react-i18next`
* Mobile: `react-i18next` + `react-native-localize`

---

# 🔐 Env & Secrets (dev)

Keep secrets out of git.

Web:

```text
apps/web-react/.env.local
```

Backend:

```text
apps/backend/application-local.yml
```

Kubernetes:

```text
infra/k8s/secrets/
```

Do not commit raw secrets.

---

# 📊 Observability (local)

Backend endpoints:

* Health:
  [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)

* Prometheus metrics:
  [http://localhost:8080/actuator/prometheus](http://localhost:8080/actuator/prometheus)

RabbitMQ metrics:

* [http://localhost:15692/metrics](http://localhost:15692/metrics)

These endpoints are consumed by Prometheus/Grafana.

---

# 🧪 Testing

See:

```text
docs/testing.md
```

---

# 🤝 Contributing / Conventions

* Conventional commits (`feat:`, `fix:`, `chore:`…)
* Web: Prettier / ESLint
* Backend: Checkstyle / Spotless (TBD)
* PRs target `main` with GitHub Actions checks

---

# 📜 License

* MIT
