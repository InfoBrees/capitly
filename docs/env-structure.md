## ENV File Structure

The project uses a root-level `.env` file for variables required by Docker Compose interpolation (especially for production deployments).

Each module has its own environment file (e.g. `auth.env`) containing the configuration required by the Spring Boot application.

For module-specific databases, shared environment files are used (e.g. `auth-shared.env`). These contain database credentials and connection settings required by both the database container and the corresponding Spring Boot service.

All environment files are stored in the project root alongside the Compose files.