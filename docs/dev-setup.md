## ✅ Prerequisites

* **JDK 21** (Temurin/Zulu)
* **Node 20+** (npm or pnpm)
* **Docker**
* **kind** and **Tilt** for local K8s (optional)

---

## 🚀 Getting Started

### Option A — Local K8s (kind + Tilt)

```bash
# 1) Create a local cluster
kind create cluster --name giftilist

# 2) Start dev workflow (from repo root)
# (Requires Tiltfile + k8s manifests in infra/k8s)
tilt up

# 3) Open services
# Web: http://localhost:3000 (or via Ingress)
# API Swagger: http://localhost:8080/swagger-ui.html
```

---

### Option B — Local Docker + Apps (recommended)

#### 1) Start infrastructure (from repo root)

```bash
docker compose up -d
```

This will start:

* PostgreSQL
* RabbitMQ

RabbitMQ UI:
👉 http://localhost:15672
(user: `guest`, pass: `guest` — dev only)

---

#### 2) Run backend

```bash
cd apps/backend
./gradlew bootRun
```

* API: http://localhost:8080
* Swagger: http://localhost:8080/swagger-ui.html

---

#### 3) Run frontend

```bash
cd apps/web-react
npm install
npm run dev
```

* App: http://localhost:3000

---

## 🔌 OpenAPI / Clients

* Live spec: `GET /v3/api-docs`
* Swagger UI: `/swagger-ui.html`
* (Optional) Generate a TS client into `packages/api-client` using openapi-generator

---

## 🐇 RabbitMQ (local)

* Broker: `localhost:5672`
* Management UI: http://localhost:15672

Used for:

* asynchronous events (e.g. item reserved)
* decoupled side effects (notifications, audit, analytics)

---

## 🌐 i18n

* Source language: English
* Translations: Dutch (`public/locales/en|nl/*.json`)
* Library:

    * Web: `react-i18next`
    * Mobile: `react-i18next` + `react-native-localize`

---

## 🔐 Env & Secrets (dev)

* Keep secrets out of git (see root `.gitignore`)
* Web: `.env.local` (in `apps/web-react/`)
* Backend: `application-local.yml` (in `apps/backend/`)
* K8s: `infra/k8s/secrets/` (do not commit raw secrets)

---

## Observability (local)

Backend endpoints:
- Health: `http://localhost:8080/actuator/health`
- Prometheus metrics: `http://localhost:8080/actuator/prometheus`

RabbitMQ metrics:
- `http://localhost:15692/metrics`

These endpoints are used later by Prometheus/Grafana for monitoring.
---

## 🧪 Testing

* See [docs/testing.md](./testing.md)

---

## 🤝 Contributing / Conventions

* Conventional commits (`feat:`, `fix:`, `chore:`…)
* Web: Prettier / ESLint
* Backend: Checkstyle / Spotless (TBD)
* PRs target `main` with GitHub Actions checks

---

## 📜 License

* MIT
