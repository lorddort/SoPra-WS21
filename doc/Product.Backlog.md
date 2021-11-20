# Product Backlog

Hier werden **alle** Anforderungen in Form von **User Stories** geordnet aufgelistet.

##Epic 1 *Datenabrufen*
>Als *Benutzer* möchte ich Informationen über Kryptowährungen finden, damit ich z.B besser in kryptowährung investieren kann.

Es werden über Börsen-API Daten abgerufen. Diese Daten enthalten z.B. den Wert einer Kryptowährung oder das Wachstum 
einer Kryptowährung über einen bestimmten Zeitraum.

###Feature 1.1 *Verfügbarkeit der Daten*
>Als *Benutzer* möchte ich bei Bedarf die Daten einer Kryptowährung einsehen, damit ich Informationen über eine Kryptowährung habe.

- Aufwandsschätzung: S
- Akzeptanztest:
  - Es wird bei Bedarf die Daten einer Kryptowährung abgerufen.
  - Es werden immer die neusten Daten einer Kryptowährung zur Verfügung gestellt.
  - Beliebte Kryptowährungen werden priorisiert beim Abrufen.





##Epic 2 *Statistik*
>Als *Benutzer* möchte ich, dass es *Daten über Kryptowährungen* gibt und auch ausgewertet werden. 

Es werden mit Daten über Kryptowährung gearbeitet, die man durch verfügbare Börsen-APIs abrufen kann. Diese werden dann 
hauptsächlich für die Korrelationskoeffizienten genutzt, damit man verschiedenen Kryptowährungen vergleichen kann.

###Feature 2.1 *Korrelationskoeffizienten*
>Als *Benutzer* möchte ich, den Korrelationskoeffizienten zwischen den verschiedenen Kryptowährungen ausgerechnet bekommen.

- Aufwandsschätzung: L
- Akzeptanztests:
    - Der berechnete Korrelationskoeffizient gibt das richtige Ergebnis aus.
    - Der Korrelationskoeffizient muss bei Veränderung von z.B. Zeiträumen neu berechnet werden


####Story 2.1.3 *Korrelationskoeffizienten berechnen*
>Als *Benutzer* möchte ich den *Korrelationskoeffizienten berechnet* bekommen, damit ich Kryptowährungen vergleichen kann

- Aufwandsschätzung: 5
- Akzeptanztests: 
  - Der berechnete Korrelationskoeffizient ist korrekt programmiert

#####Task 2.1.3.1 *Korrelationskoeffizient programmieren*

- Aufwandsschätzung: 6 Stunden











##Epic 3 *Visualisierung*
>Als *Benutzer* möchte ich die ausgewählten Daten in *verschiedensten Formen und Übersichten* sehen.



###Feature 3.1 *Tabellen und Diagramme*
>Als *Benutzer* möchte ich für einen besseren Überblick Tabellen und Diagramme sehen.

- Aufwandsschätzung: L
- Akzeptanztests:
  - Tabellen sind skalierbar
  - Diagramme sind skalierbar
  - Es sind mehrere Diagramme zum Anzeigen möglich

####Story 3.1.1 *Tabellen darstellen*
>Als *Benutzer* möchte ich eine Tabelle nutzen, damit ich einen besseren Überblick über Kryptowährungen habe.

- Aufwandsschätzung: 5
- Akzeptanztests:
  - Die Tabelle ist skalierbar

#####Task 3.1.1.1 *Tabelle erstellen*

- Aufwandsschätzung: 4 Stunden

####Story 3.1.2 *Diagramm darstellen*
>Als *Benutzer* möchte ich Diagramme sehen für einen schnelleren Vergleich und bessere Übersicht

- Aufwandsschätzung: 6
- Akzeptanztests: 
  - Das Diagramm ist skalierbar

####Task 3.1.2.1 *Diagramm erstellen*

- Aufwandsschätzung: 5 Stunden


###Feature 3.2 *Einstellungen von Visualisierungen*
>Als *Benutzer* möchte ich die Visualisierung selber anpassen können, damit ich die maximale Anzahl an Informationen herauslesen kann.

- Aufwandsschätzung: M
- Akzeptanztests:
  - Filter funktionieren wie vorhergesehen
  - Geforderten Filter sind vorhanden

####Story 3.2.1 *Filter erstellen*
>Als *Benutzer* möchte ich haben, damit die Diagramme/Tabelle gegeben anpassen kann, um schneller Informationen zu entschlüsseln

- Aufwandsschätzung: 5
- Akzeptanztest:
  - Es stehen verschiedene Filter zur Verfügung

#####Task 3.2.1.1 *Datumsbereich für Diagramme*

- Aufwandsschätzung: 2 Stunden

#####Task 3.2.1.2 *Währung*

- Aufwandsschätzung: 1 Stunde

#####Task 3.2.1.3 *verschiedene Kryptowährungen*

- Aufwandsschätzung: 2 Stunden

###Feature 3.3 *Anzeigen vom Korrelationskoeffizienten*
>Als *Benutzer* möchte ich den ausgerechneten Korrelationskoeffizienten visuell in einem Diagramm oder Tabelle sehen.

- Aufwandsschätzung: M
- Akzeptanztests:
  - Der Korrelationskoeffizient wird eindeutig in einer Tabelle/Diagramm dargestellt

####Story 3.3.1 *Korrelationskoeffizienten im Diagramm*