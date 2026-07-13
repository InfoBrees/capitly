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

# stop it with reset
docker compose -f docker-compose.dev.yml down -v

# run the prod environment (You need to build the auth image with the dev version first and you need the env files)
CAPITLY_AUTH_IMAGE=capitly-auth:latest CAPITLY_CORE_IMAGE=capitly-core:latest docker compose --env-file .env  -f docker-compose.prod.yml up

# stop it
CAPITLY_AUTH_IMAGE=capitly-auth:latest CAPITLY_CORE_IMAGE=capitly-core:latest docker compose --env-file .env  -f docker-compose.prod.yml down

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
