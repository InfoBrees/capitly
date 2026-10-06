# Entwurfsdokumentation der Datenbank für den capitly.auth Service

Das capily.auth Modul erhält zur Verwaltung der Benutzer, deren Rollen und Rechte eine Datenbank. Diese Datenbank soll hier einen Erstentwurf erhalten.
Dafür werden Entitäten definiert und deren Beziehungen dargestellt. Ziel ist die Definition einer ersten, erweiterbaren Auth-Datenbank, die mit Flyway umgesetzt werden kann.

Die Vorarbeit zur Rollendefinition, bzw. Clearance und Scopes ist in [roles.md](./roles.md) zu finden.

## Entitäten und Beziehungen

| Entität | Fachliche Beschreibung | Attribute (Entwurf) | Beziehungen | Hinweise |
|---------|------------------------|---------------------|--------------|----------|
| **User** | Bestimmt einen Benutzer der Anwendung. Muss einen Benutzer eindeutig identifizierbar machen. | user_id, username, email, passwort_hash | Muss eine Beziehung zu Rolle haben. | |
| **Role** | Verwaltungskombination aus Clearance und Scopes, weist einem `USER` seine Rechte zu. | role_id, rolename, clearance_id | Muss eine Beziehung zu Clearance und Scope haben. | Clearance ist 1:n Ding, kann da mit rein |
| **Clearance** | Clearance‑Level sind organisatorische Sicherheitsstufen, die ergänzend zu den feingranularen Scopes gelten. Sie schützen sensible, systemweite oder administrative Aktionen (z. B. Konfigurationsendpoints, Benutzermanagement) und werden nach Scope‑Checks zur finalen Zugriffsentscheidung sowie für Audit‑ und Freigabeprozesse herangezogen. | clearance_id, level, label | Wird von `Role` genutzt. | `level` ist der numerische Berechtigungsrang. |
| **Scope** | `Scopes` beschreiben Tätigkeiten oder Tätigkeitsbereiche als Recht. Sie führen zusammen mit `Clearance` zur Bildung von Rollen. | scope_id, label | Wird von `Role` genutzt. | |
| **RefreshToken** | Repräsentiert ein langlebiges Token, mit dem ein neuer kurzlebiger JWT-Access-Token ausgestellt werden kann. | id, user_id, token_hash, expires_at, created_at, revoked_at | Gehört zu genau einem `User`. | Refresh Tokens werden nur gehasht gespeichert und können widerrufen werden. Rotation sollte unterstützt werden. |


## ER-Diagramm

```mermaid
erDiagram
    USERS {
        UUIDv7 user_id PK
        string username UK
        string email UK
        string password_hash
    }

    ROLES {
        int role_id PK
        string rolename UK
        int clearance_id FK
    }

    CLEARANCES {
        smallint clearance_id PK
        smallint level UK
        string label UK
    }

    SCOPES {
        int scope_id PK
        string label UK
    }

    USER_ROLES {
        UUIDv7 user_id PK, FK
        int role_id PK, FK
    }

    ROLE_SCOPES {
        int role_id PK, FK
        int scope_id PK, FK
    }

    REFRESH_TOKENS {
        UUIDv7 id PK
        UUIDv7 user_id FK
        string token_hash UK
        timestamp expires_at
        timestamp created_at
        timestamp revoked_at
    }

    USERS ||--o{ USER_ROLES : "has"
    ROLES ||--o{ USER_ROLES : "assigned to"
    ROLES }o--|| CLEARANCES : "has"
    ROLES ||--o{ ROLE_SCOPES : "contains"
    SCOPES ||--o{ ROLE_SCOPES : "assigned to"
    USERS ||--o{ REFRESH_TOKENS : "owns"
```

## Indizes

Identifikation von Spalten, die aufgrund häufiger Abfragen, Joins oder Filteroperationen schnell auffindbar sein müssen.

Dokumentation für den 1. Sprint.

> Alle Indexe sind bereist durch die Contraints automatisch gesetzt. [LINK](https://www.postgresql.org/docs/18/sql-createtable.html)

| Tabelle       | Feld          | Index erforderlich | Bereits abgedeckt durch  | Begründung                                                                          |
| ------------- | ------------- | ------------- | ----------------------------- | ----------------------------------------------------------------------------------- |
| USERS         | user_id       | Ja            | PRIMARY KEY                   | Identifikation eines Users und Zugriff über die User-ID.                            |
| USERS         | username      | Ja            | UNIQUE                        | Einstiegspunkt im Login-Flow über den Username.                                     |
| USERS         | email         | Nein          | –                             | Im 1. Sprint für keinen Auth-Flow benötigt.                                         |
| USERS         | password_hash | Nein          | –                             | Wird zu keiner Suche verwendet.                                                     |
| | | | | |
| ROLES         | role_id       | Ja            | PRIMARY KEY                   | Technische Identifikation einer Rolle beim Aufbau der Berechtigungen.               |
| ROLES         | rolename      | Nein          | UNIQUE                        | Keine Suche über den Rollennamen aktuell geplant, Index wäre aber vorhanden.        |
| ROLES         | clearance_id  | Nein          | –                             | Eine Suche nach Rollen über die Clearance ist im aktuellen Flow nicht erforderlich. |
| | | | | |
| CLEARANCES    | clearance_id  | Ja            | PRIMARY KEY                   | Technische Identifikation einer Clearance.                                          |
| CLEARANCES    | level         | Ja            | UNIQUE                        | Numerischer Rang für Vergleiche wie `actor_level >= required_level`.               |
| CLEARANCES    | label         | Nein          | UNIQUE                        | Suche über das label nicht geplant, Index wäre aber vorhanden                       |
| SCOPES        | scope_id      | Ja            | PRIMARY KEY                   | Technische Identifikation eines Scopes beim Aufbau des JWT.                         |
| SCOPES        | label         | Nein          | UNIQUE                        | Suche über das label nicht geplant, Index wäre aber vorhanden                       |
| | | | | |
| USER_ROLES    | user_id       | Ja            | PRIMARY KEY (user_id, role_id) | Ermittlung aller Rollen eines Users beim Aufbau des JWT.                           |
| USER_ROLES    | role_id       | Nein          | –                             | Ermittlung von Usern anhand einer Rolle im aktuellen Auth-Flow nicht benötigt.     |
| | | | | |
| ROLE_SCOPES   | role_id       | Ja            | PRIMARY KEY (role_id, scope_id) | Ermittlung aller Scopes einer Rolle beim Aufbau des JWT.                          |
| ROLE_SCOPES   | scope_id      | Nein          | –                             | Ermittlung von Rollen anhand eines Scopes im aktuellen Auth-Flow nicht benötigt.   |
| | | | | |
| REFRESH_TOKENS | id            | Ja            | PRIMARY KEY                   | Identifikation eines Refresh Tokens.                                                |
| REFRESH_TOKENS | user_id       | Nein          | –                             | Im aktuellen Auth-Flow keine Abfrage über user_id notwendig.                        |
| REFRESH_TOKENS | token_hash    | Ja            | UNIQUE                        | Für den Refresh-Token-Flow.                                                         |
| REFRESH_TOKENS | expires_at    | Nein          | –                             | Cleanup bzw. Validierung von Refresh Tokens ist im 1. Sprint nicht vorgesehen.      |
| REFRESH_TOKENS | created_at    | Nein          | –                             | Im aktuellen Auth-Flow nicht zur Suche oder Filterung benötigt.                     |
| REFRESH_TOKENS | revoked_at    | Nein          | –                             | Im aktuellen Auth-Flow nicht zur Suche oder Filterung benötigt.                     |
