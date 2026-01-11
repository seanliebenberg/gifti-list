This document describes the overall system architecture for the Gift List App, including event-driven features (audit logging, messaging, analytics, notifications).
```mermaid
graph LR
WebReact[React Web Frontend]
WebAngular[Angular Web Frontend]
Mobile[Mobile Frontend]
Backend[Backend Service]
DB[(PostgreSQL)]
AuditDB[(Audit Log Table)]
RabbitMQ[(RabbitMQ Broker)]
Notifications[Notifications Service]
Metabase[Metabase Analytics]

WebReact --> Backend
WebAngular --> Backend
Mobile --> Backend
Backend --> DB
Backend -->|writes audit entries| AuditDB
Backend -->|publishes domain events| RabbitMQ
RabbitMQ --> Notifications
AuditDB --> Metabase
```

Directory Layout
```
gifti-list/
├── .github/            # CI/CD workflows (GitHub Actions)
│   └── workflows/
├── apps/
│   ├── web-react/       # React (+ Next.js) + Tailwind CSS (start)
│   ├── web-angular/     # Angular + Tailwind CSS (future)
│   ├── mobile/          # React Native + NativeWind (future)
│   └── backend/         # Spring Boot + OpenAPI + PostgreSQL
├── packages/
│   ├── ui/              # Shared UI components (web + native)
│   ├── tokens/          # Design tokens (colors, spacing, typography)
│   └── utils/           # Shared business logic and helpers
├── infra/              # Production infrastructure definitions
│   ├── k8s/             # Kubernetes manifests (kind cluster for dev)
│   └── terraform/       # IaC for cloud deployments (optional)
├── docs/               # Markdown documentation files
│   ├── index.md
│   ├── dev-setup.md
│   └── architecture.md
├── dev-tools/          # Developer tooling configurations
│   └── Tiltfile         # Tilt config for local development
```