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
From `capitly`:
> Recommended way: run everything via Docker Compose.  
> You can also start individual modules with Maven, but the environment (DB, networking, config) may not match the Docker setup and can lead to inconsistencies.

```bash
# run the full dev environment
docker compose -f docker-compose.dev.yml up --build  

# stop it
docker compose -f docker-compose.dev.yml down

# stop it with wuth reset
docker compose -f docker-compose.dev.yml down -v

# optional via maven (not recommended)
mvn -pl auth spring-boot:run
mvn -pl core spring-boot:run
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
