# Capitly 

**Capitly** is a modern finance overview app that helps you track, analyze, and manage your personal finances — all in one place.

It combines the flexibility of spreadsheets with a clean, user-friendly interface, giving you full control over your financial data without the complexity of traditional tools.

---

## Tech Stack

* **Backend:** Java + Spring Boot (Maven)
* **Frontend:** Svelte + TypeScript
* **Data Handling:** CSV import/export

---

## Vision

Capitly aims to bridge the gap between:

* Powerful spreadsheet tools
* Simple finance tracking apps

Giving you the best of both worlds.

---

## Build & Run

### Prerequisites

* Java 25
* Maven 3.9+
* Node.js 20+

### Backend (multi-module)

From `capitly/capitly`:

```bash
# Build all backend modules
mvn clean verify

# Run core module
mvn -pl core spring-boot:run

# Run auth module
mvn -pl auth spring-boot:run
```

### Frontend

From `capitly/frontend`:

```bash
# Install dependencies
npm ci

# Start dev server
npm run dev

# Production build
npm run build
```
