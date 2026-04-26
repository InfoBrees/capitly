# Rollen & Zugriffsübersicht

Zur Verwaltung der Zugriffe auf unsere APIs sollen Rollen für die Benutzer (und evtl. Systeme in der Zunkunft) definiert werden. Um Flexibilität zu erhalten, sind Rollen nur Aggregationen von einem Clearance Level in Kombination mit Scopes für erlaubte Aktionsbereiche. Damit können leicht neue Rollen hinzugefügt werden beziehungsweise bestehende angepasst werden indem die internen Scopes verändert werden.

## Clearance

Clearance‑Level sind organisatorische Sicherheitsstufen, die ergänzend zu den feingranularen Scopes gelten. Sie schützen sensible, systemweite oder administrative Aktionen (z. B. Konfigurationsendpoints, Benutzermanagement) und werden nach Scope‑Checks zur finalen Zugriffsentscheidung sowie für Audit‑ und Freigabeprozesse herangezogen.

| Level | Label | Kurzbeschreibung |
| --- | --- | --- |
| 2 | HIGH | höchste Sicherheitsklasse, für kritische, systemübergreifende Aktionen |
| 1 | LOW | Standard-Sicherheitsklasse ohne Erlaubnis für besondere Aktionen |

## Scopes

Ziel ist es, Rollen granular bestimmte Aktion zu erlauben ohne neue Clearance Level erfinden zu müssen. So kann an den APIs kurz geprüft werden, ob die Aktion von Caller ausgeführt werden darf.

### Standard Scopes

| Standard Scopes | Beschreibung |
| --- | --- |
| openid | OpenID Connect‑Scope — fordert ein ID‑Token (Authentifizierung) |
| profile | Liefert Standard‑Profilclaims (name, picture, locale) |
| email | Liefert E‑Mail‑Adresse und verified‑Flag |


### Domänenspezifische Scopes

Die Scopes sind grob nach dem folgednen Schema aufgebaut: resource.action.

| Scope | Beschreibung | Owner Check |
| --- | --- | --- | 
| health.view | benötigter Scope für das System-Health-Dashboard | |
|  |  | |
| portfolio.read.public | benötigt für das Lesen von Daten im Portfolio Kontext | |
| portfolio.read.private | benötigt für das Lesen von Einträgen im Portfolio Kontext | x |
| portfolio.write | benötigt für das Anlegen von Einträgen im Portfolio Kontext | x |
| portfolio.delete | benötigt für das Löschen von Eintragen im Portfolio Kontext | x |
|  |  | |
| accounts.read | Lesen von Account‑Daten | |
| accounts.write | Änderung eigener Account‑Daten; Admins dürfen fremde Accounts ändern | x |
| accounts.delete | Löschen eigener Accounts; Admins dürfen fremde Accounts löschen | x |
|  |  | |

Weiter Scopes können hinzugfügt werden.

## Mapping: Externe Rolle → Clearance + Scopes

Eine Rolle besteht aus einem Clearance Level und der ihr zugeordneten Scopes. Die Standard Scopes gehören zum OIDC Flow und sind prinzipell in jeder Rolle enthalten, gehören aber nicht zum API-Zugriffsmodell für den core.

| Externe Rolle | Beschreibung | Clearance (Level) | Scopes | Anmerkungen |
| --- | --- | --- | --- | --- |
| ADMIN | | HIGH (2) | health.view, portfolio.read.public, portfolio.read.private, portfolio.write, portfolio.delete, accounts.read, accounts.write, accounts.delete | Admins dürfen Owner‑Checks umgehen |
| USER  | | LOW (1)  | portfolio.read.public, portfolio.read.private, portfolio.write, portfolio.delete, accounts.read, accounts.write, accounts.delete | |
| GUEST | | LOW (1)  | portfolio.read.public | |


Weiter Rollen können hinzugefüht werden.

## Angabe im JWT
Beispiel für die Darstellung der Rollen/Scopes/Clearance im JWT:

Header (JSON, Signaturalgorithmus: `PS512` — RSA‑PSS mit SHA‑512):
```json
{
  "alg": "PS512",
  "typ": "JWT",
  "kid": "auth-server-key-1"
}
```

Payload (JSON, Beispiel‑Claims):

*Hinweis: `scope` ist die OAuth/OIDC-konforme, space-getrennte Stringliste (für Clients/Interoperabilität); `scp` ist ein JSON‑Array, das serverseitige Verarbeitung und Policy‑Checks vereinfacht. Beide müssen konsistent gehalten werden.*

```json
{
  "iss": "https://auth.example.com",
  "sub": "user-123",
  "aud": "capitly-core",
  "iat": 1714458000,
  "exp": 1714461600,
  "jti": "f47ac10b-58cc-4372-a567-0e02b2c3d479",

  "scope": "openid profile email portfolio.read.private portfolio.write accounts.read accounts.write",
  "scp": [
    "portfolio.read.private",
    "portfolio.write",
    "accounts.read",
    "accounts.write"
  ],

  "roles": ["USER"],
  "clearance": { "level": 1, "label": "LOW" },

  "owner_id": "user-123"
}
```

Wichtige Hinweise
- **Dummydaten**: Die obenstehenden Werte sind Beispielwerte. Produktionsdaten, Ablaufzeiten und IDs müssen sicher gewählt werden.
- **Key‑Management**: Publiziere öffentliche Schlüssel über eine JWKS‑URI und benutze `kid` im Header zur Schlüsselwahl.
- **Owner‑Checks**: Vergleiche `owner_id` mit der Ressource, wenn `x-owner-check: true` gesetzt ist. Rollen mit `clearance.level: 2` können Owner‑Checks gemäß Richtlinie umgehen.

## Muster zur Angabe der nötigen Berechtigungen für eine API

Siehe https://swagger.io/docs/specification/v3_0/authentication/oauth2/ für genaue Notation.

```yaml
/accounts/{id}:
    delete:
      summary: "Account löschen"
      security:
        - oauth2: ["accounts.delete"]
      x-owner-check: true
      x-admin-bypass: true
      responses:
        '204':
          description: Deleted
```