# 🎁 Gift App – Overview

A family-friendly gift-sharing app for organizing wishlists, occasions, and Secret Santa games.  
Built as a monorepo with scalable architecture, event-driven workflows, and clean developer experience using Kubernetes, Tilt, and OpenAPI.

---

## ✅ Core Features

### 📝 Wishlists
- Create and manage personal wishlists
- Add items manually or via external links
- Mark items as “purchased” (hides from owner)

### 🎉 Occasions
- Create gift-giving occasions (e.g. Christmas, birthdays)
- Invite family & friends
- Associate wishlists with each occasion

### 🎅 Secret Santa
- Auto-generate anonymous gift assignments
- Optional exclusion rules (e.g. spouses don’t match)
- Share secure join links with participants

### 👨‍👩‍👧‍👦 Family & Group Sharing
- Organize users into family/friend groups
- Share wishlists and occasions within a group
- Flexible visibility and permission settings

### 🔔 Notifications
- Real-time alerts when events occur (e.g. item added, gift reserved)
- Configurable delivery via email or push (future)

### 🌐 Language Support
- Default in English, with Dutch translations available
- Language switcher in UI (or auto-detect based on locale)

---

## 🛠 Developer Experience

### 📄 Monorepo Architecture
- `apps/web-react` → React + Tailwind CSS (start)
- `apps/web-angular` → Angular + Tailwind CSS (future)
- `apps/mobile` → React Native + NativeWind (future)
- `apps/backend` → Spring Boot + OpenAPI + PostgreSQL
- `packages/ui` → Shared UI components
- `packages/tokens` → Design tokens (colors, spacing, typography)

### 🚀 Local Dev Workflow
- **Kubernetes** via `kind` for local clusters
- **Tilt** for live-reload, image builds & manifest deploys
- Manifests kept in `infra/k8s/`

### 🌐 PWA & Responsive Design
- Progressive Web App setup with service worker and caching
- Mobile-responsive layouts out-of-the-box via Tailwind CSS

### 📜 API Documentation
- Auto-generated OpenAPI spec at `/v3/api-docs`
- Interactive Swagger UI at `/swagger-ui.html`
- TS-client generated for frontend from the spec

### 🔄 Event-Driven & Analytics
- Domain events: `OccasionCreated`, `WishlistItemAdded`, `GiftReserved`
- Spring in-process events → audit entries in Postgres
- RabbitMQ broker for decoupled messaging
- Notifications service consuming events
- Metabase for analytics dashboards reading audit logs

---

## 🔮 Optional & Future Enhancements

- Mobile app (React Native + NativeWind)
- Angular web client (Angular + Tailwind CSS)
- Cloud deployment via GKE / Fly.io / DigitalOcean
- Group gifting payments (Stripe integration)