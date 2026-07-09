# Flyway Migration Guidelines

## Location

Store all migration files in:

```text
src/main/resources/db/migration
```

## Naming Convention

Use the following format:

```text
V<version>__<description>.sql
```

Examples:

```text
V1__initial_schema.sql
V2__create_user_table.sql
V3__add_email_index.sql
```

## Conventions

* Use an uppercase `V` for versioned migrations.
* Separate the version and description with **two underscores (`__`)**.
* Use lowercase, descriptive names separated by underscores.
* Each migration must have a unique, incrementing version.
* **Never modify a migration that has already been applied. Create a new migration instead.**
* Keep each migration focused on a single logical database change.
* Include `INSERT` statements only when required for essential seed or reference data.

## Example

```sql
CREATE TABLE test (
    a TEXT PRIMARY KEY,
    b TEXT
);
```
