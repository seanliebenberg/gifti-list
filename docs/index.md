# 🎁 Gift App – Overview

A family-friendly gift-sharing app for organizing wishlists, occasions, and Secret Santa games.

Built as a monorepo with a **Spring Boot backend**, **PostgreSQL**, and evolving toward an **event-driven architecture** with RabbitMQ.

---

## 🚧 Status

> This project is actively under development.
> Some features and architecture components described below are planned but not yet implemented.

---

## ✅ Core Features (Planned)

### 📝 Wishlists

* Create and manage personal wishlists
* Add items manually or via external links
* Mark items as “purchased” (hides from owner)

### 🎉 Occasions

* Create gift-giving occasions (e.g. Christmas, birthdays)
* Invite family & friends
* Associate wishlists with each occasion

### 🎅 Secret Santa

* Auto-generate anonymous gift assignments
* Optional exclusion rules (e.g. spouses don’t match)
* Share secure join links with participants

### 👨‍👩‍👧‍👦 Family & Group Sharing

* Organize users into family/friend groups
* Share wishlists and occasions within a group
* Flexible visibility and permission settings

### 🔔 Notifications (future)

* Alerts when events occur (e.g. item added, gift reserved)
* Delivery via email or push notifications

---

## 🛠 Developer Experience

### 📄 Monorepo Architecture

* `apps/web-react` → React + Tailwind CSS (start)
* `apps/web-angular` → Angular + Tailwind CSS (future)
* `apps/mobile` → React Native + NativeWind (future)
* `apps/backend` → Spring Boot + PostgreSQL + RabbitMQ
* `packages/ui` → Shared UI components
* `packages/tokens` → Design tokens (colors, spacing, typography)

---

### 🚀 Local Dev Workflow

Two main options:

* **Docker Compose (recommended)** → Postgres + RabbitMQ
* **Kubernetes (kind + Tilt)** → full local cluster (optional)

---

### 📜 API Documentation

* OpenAPI spec: `/v3/api-docs`
* Swagger UI: `/swagger-ui.html`
* Optional TS client generation for frontend

---

## 🔄 Event-Driven Architecture (In Progress)

The backend uses **RabbitMQ** to support asynchronous workflows.

### Current

* Basic publish/consume flow implemented
* Queue-based messaging verified via RabbitMQ UI

### Target

* Domain events such as:

    * `WishlistItemAdded`
    * `ItemReserved`
    * `OccasionCreated`
* Events published after successful DB operations
* Consumers handle side effects:

    * notifications
    * audit logging
    * analytics

👉 Principle:

> Core logic is synchronous, side effects are asynchronous

---

## 📊 Analytics (Future)

* Audit and event data stored in PostgreSQL
* Dashboards via Metabase
* Business metrics exposed via Prometheus + Grafana

---

## 🌐 Language Support

* English (default)
* Dutch translations available
* Locale-aware UI (future)

---

## 🔮 Future Enhancements

* Notifications service (email/push)
* Mobile app (React Native)
* Angular web client
* Observability (Prometheus, Grafana, tracing)
* Cloud deployment (Kubernetes)
* Group gifting payments (Stripe)

---

## 🧠 Architecture Direction

* Modular monolith backend
* Event-driven extensions via RabbitMQ
* Clear separation of concerns (API / domain / messaging)
* Designed for evolution toward distributed systems if needed
