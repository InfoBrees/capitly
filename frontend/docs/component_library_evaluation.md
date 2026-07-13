# Evaluation von Svelte + TailwindCSS Component Libraries

## Ziel

Für die Frontend-Entwicklung soll eine geeignete Component Library ausgewählt werden, die mit Svelte, TailwindCSS und TypeScript kompatibel ist. Die Library soll als Grundlage für unser UI-System dienen und insbesondere Anforderungen einer Finanzanwendung unterstützen, also Tabellen und Grafiken.

**Wichtige Anforderungen:**

- Svelte 5 Kompatibilität
- TailwindCSS Integration
- freie Nutzung (z. B. MIT-Lizenz)
- aktive Weiterentwicklung und Community
- hohe Anpassbarkeit
- Unterstützung oder gute Erweiterbarkeit für:
  - Tabellen mit großen Datenmengen
  - Datenvisualisierung und Graphen
  - Dashboards und Analyseansichten


## Vergleich der Kandidaten

| Kriterium | shadcn-svelte | Skeleton UI | Melt UI |
|---|---|---|---|
| Typ | Tailwind-basierte Component Library mit kopierbaren Komponenten | Svelte UI Framework mit fertigen Komponenten und Themes | Headless Component Library |
| Svelte Kompatibilität | Sehr gut, unterstützt moderne Svelte-Versionen inklusive Svelte 5. Die Komponenten werden direkt in das Projekt übernommen und können an die eigene Architektur angepasst werden. | Sehr gut, für Svelte entwickelt und unterstützt aktuelle Svelte-Versionen. | Sehr gut, ist speziell für Svelte entwickelt und bietet moderne Svelte-Komponenten.|
| TailwindCSS Integration | Sehr gut, basiert vollständig auf TailwindCSS. Styling und Design Tokens können direkt über das Projekt gesteuert werden, kein zusätzlicher Styling-Layer. | Sehr gut, nutzt TailwindCSS als Basis und bietet zusätzlich eigene Design Tokens und Themes. Dadurch ist die Integration einfach, aber etwas stärker durch das Framework geprägt. | Sehr gut, funktioniert gut mit TailwindCSS, liefert jedoch hauptsächlich die Logik und Accessibility-Struktur. Das Styling muss stärker selbst aufgebaut werden. |
| Lizenz | MIT-Lizenz | MIT-Lizenz | MIT-Lizenz |
| Maintenance / Community Aktivität | Sehr aktiv | Aktiv | Aktiv |
| Anpassbarkeit | Sehr hoch. Komponenten werden in das eigene Projekt kopiert und können vollständig verändert werden. Es entsteht keine starke Abhängigkeit von der Library. | Hoch, aber geringer als bei shadcn. Das Theme-System erlaubt Anpassungen, jedoch gibt es stärkere Vorgaben durch das Framework. | Sehr hoch. Da Melt UI headless arbeitet, gibt es nahezu keine Designvorgaben. Dafür müssen viele visuelle Komponenten selbst entwickelt werden. |

## Bewertung für Tabellen

| Library | Bewertung Tabellen | Begründung |
|---|---|---|
| shadcn-svelte | Sehr gut | shadcn-svelte bietet eine Table-Komponente und eine Data Table Integration mit TanStack Table. Dadurch können komplexe Tabellen mit Sortierung, Filterung, Pagination, Spaltenverwaltung und Row Selection umgesetzt werden |
| Skeleton UI | Gut | Skeleton bietet grundlegende Tabellen- und UI-Komponenten. Für komplexe Finanzdaten mit vielen Zeilen, Filtern und dynamischen Spalten ist jedoch eine zusätzliche Lösung wie TanStack Table sinnvoll, erzeugt zusätzlichen Integrationsaufwand. |
| Melt UI | Mittel | Melt UI liefert keine fertige Tabellenlösung, sondern nur Headless-Komponenten. Eine Tabelle müsste vollständig mit einer zusätzlichen Library wie TanStack Table aufgebaut werden. |

## Bewertung für Graphen und Datenvisualisierung

| Library | Bewertung Graphen | Begründung |
|---|---|---|
| shadcn-svelte | Sehr gut | shadcn-svelte bietet Chart-Komponenten, die auf LayerChart basieren. Dadurch können Standarddiagramme wie Line Charts, Bar Charts oder Area Charts direkt integriert werden. |
| Skeleton UI | Mittel | Skeleton besitzt keine eigene umfangreiche Chart-Lösung. Müsste über Charts Libs gelöst werden |
| Melt UI | Niedrig | Melt UI enthält keine Visualisierungskomponenten. Müsste über Charts Libs gelöst werden |


## Zusammenfassung der Kandidaten

### shadcn-svelte

**Vorteile**

- Sehr gute Svelte 5 Unterstützung
- Native TailwindCSS Integration
- Hohe Anpassbarkeit
- Gute Basis für eigene Design-Systeme
- Gute Unterstützung für Tabellen durch TanStack Table
- Chart-Komponenten über LayerChart verfügbar
- Kein starker Lock-in

### Skeleton UI

**Vorteile**

- Viele fertige UI-Komponenten
- Schneller Entwicklungsstart
- Gute Svelte Integration
- Einheitliches Design-System

**Nachteile**

- Weniger flexibel bei komplett eigenen Designs, nur semi relevant
- Tabellen und Graphen benötigen zusätzliche Lösungen, relevant


### Melt UI

**Vorteile**

- Maximale Flexibilität
- Sehr gute Basis für ein eigenes Design-System
- Keine Designvorgaben
- Gute Accessibility-Basis

**Nachteile**

- Höherer Entwicklungsaufwand, da weniger fertige Komponenten, relevant
- Tabellen und Charts müssen stärker selbst integriert werden, relevant

## Finale Empfehlung

**Auswahl: shadcn-svelte**

- fertige, aber anpassbare Komponenten, 
- Digramm/Grafik- und Tabellen-Unterstützung
