
## ✅ Prerequisites
- **JDK 21** (Temurin/Zulu)
- **Node 20+** (npm or pnpm)
- **Docker**
- **kind** and **Tilt** for local K8s

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
### Option B — Run services directly (no K8s)
```bash
# Backend (from apps/backend)
./gradlew bootRun
# Swagger → http://localhost:8080/swagger-ui.html

# Web (from apps/web-react)
npm install && npm run dev
# App → http://localhost:3000
```
🔌 OpenAPI / Clients

Live spec: GET /v3/api-docs

Swagger UI: /swagger-ui.html

(Optional) Generate a TS client into packages/api-client using openapi-generator.

🌐 i18n

Source language: English

Translations: Dutch (public/locales/en|nl/*.json)

Library: react-i18next (web), react-i18next/react-native-localize (mobile)

🔐 Env & Secrets (dev)

Keep secrets out of git (see root .gitignore)

Use .env.local for web, application-local.yml for backend

For K8s, use infra/k8s/secrets/ (do not commit raw secrets)

🤝 Contributing / Conventions

Conventional commits (feat:, fix:, chore:…)

Prettier/ESLint for web; Checkstyle/Spotless for backend (TBD)

PRs target main with checks via GitHub Actions

📜 License

MIT 