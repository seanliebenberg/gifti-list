# 🔐 Security Policy

Thanks for helping keep **Gifti List** safe for everyone.

---

## 🧭 Supported Versions

This project is in active development.

* Security fixes are applied to the `main` branch
* Fixes are included in the next tagged release
* We generally support the latest version on `main`

---

## 🚨 Reporting a Vulnerability

Please **do not open public issues** for security problems.

### Preferred method

Use **GitHub Security Advisories (private)**:

* Go to the repository → **Security** → **Advisories**
* Click **"Report a vulnerability"**

This creates a private thread with the maintainer.

---

## 📋 What to include

* Clear description of the issue and impact
* Minimal reproduction steps (PoC)
* Affected versions / commit SHAs
* Environment details (if relevant)

---

## ⏱ Disclosure & Timelines

* Acknowledgement within **48 hours**
* Initial assessment within **5 business days**
* Fix/mitigation timeline shared after triage

Please **withhold public disclosure** until a fix is available.

We will credit reporters (or keep you anonymous if preferred).

---

## 🧱 Scope

We welcome reports for vulnerabilities in:

* Application code (Spring Boot backend)
* Messaging (RabbitMQ configuration, event handling)
* Database usage (PostgreSQL)
* Docker / container configuration
* CI/CD pipelines (GitHub Actions, build scripts)
* Documentation that could lead to insecure usage

---

## ⚠️ Out of Scope

* Issues in third-party dependencies where fixes must land upstream
  (you can still report — we will track and update dependencies)

---

## 🔑 Secrets & Configuration

* Secrets must **never be committed to the repository**
* Use:

    * `.env.local` (frontend)
    * `application-local.yml` (backend)
* For Kubernetes: use `infra/k8s/secrets/` (do not commit raw secrets)

If you discover exposed credentials, please report immediately.

---

## 📦 Dependencies

We aim to keep dependencies up to date:

* Gradle dependencies (Spring Boot, libraries)
* Docker images (Postgres, RabbitMQ)
* GitHub Actions

Dependency-related vulnerabilities are handled via updates.

---

## 🌍 Contact & Language

Reports in **English or Dutch** are welcome.

If you need an alternative contact channel, propose it in your initial message.

---

## 🙏 Thank You

Responsible disclosure helps improve the security of this project for everyone.
