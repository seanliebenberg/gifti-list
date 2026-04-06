# 🎁 Gifti List

Family gift-sharing made simple: wishlists, occasions (birthdays/Christmas), Secret Santa, and family groups.

---

## 📚 Documentation

* **[Project overview](./docs/index.md)** — high-level vision and features
* **[Architecture](./docs/architecture.md)** — system design and components
* **[Development setup](./docs/dev-setup.md)** — how to run locally
* **[Testing](./docs/testing.md)** — test strategy and usage
* **[Roadmap](./docs/roadmap.md)** — progress and upcoming work
* **[Health](`http://localhost:8080/actuator/health`)** — backend health status
* **[App metrics](`http://localhost:8080/actuator/prometheus`)** — backend operational metrics
* **[RabbitMQ metrics](`http://localhost:15692/metrics`)** — broker operational metrics

> Full docs live in [`/docs`](./docs)

---

## 🚧 Status

* ✅ Backend (Spring Boot + Java 21)
* ✅ PostgreSQL (Docker)
* ✅ RabbitMQ (basic messaging)
* ✅ Actuator + Prometheus metrics endpoints 
* 🛠 Prometheus + Grafana dashboard setup in progress

* 🛠 Web app (React) in progress
* 🗺️ Angular + Mobile planned

---

## ⚙️ Tech Stack

* **Backend:** Spring Boot, Java 21
* **Database:** PostgreSQL
* **Messaging:** RabbitMQ
* **Frontend:** React (planned), Angular (future), React Native (future)
* **Dev tooling:** Docker Compose, (optional) Kubernetes + Tilt

---

## 🚀 Quick Start (Local)

### 1. Start infrastructure

```bash id="qkmx98"
docker compose up -d
```

This starts:

* PostgreSQL
* RabbitMQ

RabbitMQ UI:
👉 http://localhost:15672
(user: `guest`, pass: `guest` — dev only)

---

### 2. Start backend

```bash id="8p0g3r"
cd apps/backend
./gradlew bootRun
```

* API: http://localhost:8080
* Swagger: http://localhost:8080/swagger-ui.html

---

### 3. (Optional) Start frontend

```bash id="f3ld7m"
cd apps/web-react
npm install
npm run dev
```

* App: http://localhost:3000

---

## 🧪 Quality Assurance

Test coverage includes:

* **Unit tests** → business logic (fast, no Spring)
* **Controller tests** → HTTP behavior (MockMvc, no DB)
* **Repository tests** → real Postgres via Testcontainers
* **Smoke test** → Spring Boot context loads

👉 See [docs/testing.md](./docs/testing.md)

---

## 🔄 Architecture Highlights

* Modular monolith backend
* PostgreSQL for core data
* RabbitMQ for asynchronous processing
* Event-driven side effects (notifications, audit, analytics)

👉 See [docs/architecture.md](./docs/architecture.md)

---

## 🔐 Security

See [SECURITY.md](./SECURITY.md) for reporting vulnerabilities.

---

## 🧠 Project Direction

This project evolves from a simple backend into a **fully observable, event-driven system**:

* Core logic: synchronous (API → DB)
* Side effects: asynchronous (events via RabbitMQ)
* Next step: observability (metrics, dashboards)

---

## 📜 License

MIT
