# Product Backlog

Hier werden **alle** Anforderungen in Form von **User Stories** geordnet aufgelistet.

## Epic 1 *Datenabrufen*
>Als *Benutzer* möchte ich *Informationen über Kryptowährungen* finden, damit ich z.B *besser in kryptowährung investieren* kann.

Es werden über Börsen-API Daten abgerufen. Diese Daten enthalten z.B. den Wert einer Kryptowährung oder das Wachstum 
einer Kryptowährung über einen bestimmten Zeitraum.

### Feature 1.1 *Verfügbarkeit der Daten von Kryptowährungen*
>Als *Benutzer* möchte ich bei Bedarf die *Daten einer Kryptowährung einsehen*, damit ich *Informationen über eine Kryptowährung habe*.

- Aufwandsschätzung: M
- Akzeptanztest:
  - Es wird bei Bedarf die Daten einer Kryptowährung abgerufen.
  - Es werden immer die neusten Daten einer Kryptowährung zur Verfügung gestellt.
  - Beliebte Kryptowährungen werden priorisiert beim Abrufen.

#### Story 1.1.1 *Daten von Krypto-API bekommen*
>Als *Benutzer* möchte ich die *Daten einer Krypto-API laden*, damit ich *Informationen über eine Kryptowährung habe*.

- Aufwandsschätzung: 3SP
- Akzeptanztests: 
  - Die App verbindet sich mit einer Krypto-API
  - Historische Daten einer ausgewählten Kryptowährung wurden geladen (Preis)
  - Historische Daten einer ausgewählten Kryptowährung wurden geladen (Volumen)
  - Historische Daten einer ausgewählten Kryptowährung wurden geladen (Marktkapitalisierung)

##### Task 1.1.1.1 *URL und Zugang für Krypto-API bereitstellen*
- Aufwandsschätzung: 15min

##### Task 1.1.1.2 *GET-Request an Krypto-API stellen*
- Aufwandsschätzung: 30min

#### Story 1.1.2 *Daten von Kryptos speichern*
>Als *Benutzer* möchte ich die *Daten einer geladenen Kryptowährung speichern*, damit ich *Informationen über eine Kryptowährung nutzen kann. ohne erneut laden zu müssen*.

- Aufwandsschätzung: 3SP
- Akzeptanztests: 
  - Die geladenen Daten einer Kryptowährung sind mind. während der Laufzeit verfügbar
  - Das zugehörige Objekt hat historische Daten über Preis, Volumen und Marktkapitalisierung

##### Task 1.1.2.1 *Preisdaten aus GET-Request in richtiges Format bringen*
- Aufwandsschätzung: 15min

##### Task 1.1.2.2 *Volumendaten aus GET-Request in richtiges Format bringen*
- Aufwandsschätzung: 15min

##### Task 1.1.2.3 *Marktkapitalisierungsdaten aus GET-Request in richtiges Format bringen*
- Aufwandsschätzung: 15min

##### Task 1.1.2.4 *Alle Daten aus GET-Request dem Krypto-objekt hinzufügen*
- Aufwandsschätzung: 10min

### Feature 1.2 *Verfügbarkeit der Daten über weitere Indizes*
>Als *Benutzer* möchte ich weitere *Daten über andere Indizes* habe, um diese mit den *Daten von Kryptowährungen zu vergleichen*.
- Aufwandsschätzung: M
- Akzeptanztest:
  - Es wird mindestens Index abgerufen

#### Story 1.2.1 *Daten von Index-API bekommen*
>Als *Benutzer* möchte ich die *Daten einer Index-API laden*, damit ich *Informationen über einen Index habe um ihn mit einer Kryptowährung zu vergleichen*.

- Aufwandsschätzung: 3SP
- Akzeptanztests: 
  - Die App verbindet sich mit einer Index-API
  - Historische Daten eines ausgewählten Indizes wurden geladen (Preis)

##### Task 1.2.1.1 *URL und Zugang für Index-API bereitstellen*
- Aufwandsschätzung: 15min

##### Task 1.2.1.2 *GET-Request an Index-API stellen*
- Aufwandsschätzung: 30min

#### Story 1.2.2 *Daten von Indizes speichern*
>Als *Benutzer* möchte ich die *Daten eines geladenen Indizes speichern*, damit ich *Informationen über einen Index nutzen kann, ohne erneut laden zu müssen*.

- Aufwandsschätzung: 3SP
- Akzeptanztests: 
  - Die geladenen Daten eines Index sind mind. während der Laufzeit verfügbar
  - Das zugehörige Objekt hat historische Daten über Preis

##### Task 1.2.2.1 *Preisdaten aus GET-Request in richtiges Format bringen*
- Aufwandsschätzung: 15min

##### Task 1.2.2.2 *Alle Daten aus GET-Request dem Krypto-objekt hinzufügen*
- Aufwandsschätzung: 10min

## Epic 2 *Statistik*
>Als *Benutzer* möchte ich, dass es *Daten über Kryptowährungen* gibt, damit *mittels Statistik ausgewertet* werden. 

Es werden mit Daten über Kryptowährung gearbeitet, die man durch verfügbare Börsen-APIs abrufen kann. Diese werden dann 
hauptsächlich für die Korrelationskoeffizienten genutzt, damit man verschiedenen Kryptowährungen vergleichen kann.

### Feature 2.1 *Korrelationskoeffizienten*
>Als *Benutzer* möchte ich, den *Korrelationskoeffizienten zwischen den verschiedenen Kryptowährungen ausgerechnet* bekommen, um einen *Vergleich* zu bekommen.

- Aufwandsschätzung: L
- Akzeptanztests:
    - Der berechnete Korrelationskoeffizienten gibt das richtige Ergebnis aus.
    - Der Korrelationskoeffizienten muss bei Veränderung von z.B. Zeiträumen neu berechnet werden


#### Story 2.1.1 *Korrelationskoeffizienten berechnen*
>Als *Benutzer* möchte ich den *Korrelationskoeffizienten berechnet* bekommen, damit ich *Kryptowährungen vergleichen* kann.

- Aufwandsschätzung: 5
- Akzeptanztests: 
  - Der berechnete Korrelationskoeffizienten ist korrekt programmiert
  
##### Task 2.1.1.1 *Korrelationskoeffizient programmieren*

- Aufwandsschätzung: 6 Stunden

#### Story 2.1.2 *Korrelationskoeffizienten aktualisieren*
>Als *Benutzer* möchte ich den *Korrelationskoeffizienten* berechnet haben lassen für verschiedensten Umständen, damit ich die *Veränderung des Korrelationskoeffizienten* sehe.

- Aufwandsschätzung: 4
- Akzeptanztests: 
  - Der Korrelationskoeffizient wird neu berechnet beim Verändern des Datumsbereiches
  - Der Korrelationskoeffizient wird new berechnet beim Verändern der Kryptowährung

##### Task 2.1.2.1 *Korrelationskoeffizienten neu berechnen*

- Aufwandschätzung: 2

#### Story 2.1.3 *Korrelationskoeffizienten beschreiben*
>Als *Benutzer* möchte ich, dass der Korrelationskoeffizient mit einem *lesbaren Text angezeigt* wird für eine *deutliche Verständlichkeit* über den Vergleich der ausgewählten Kryptowährungen.

- Aufwandsschätzung: 2
- Aktzeptanztests:
  - Es steht mindestens einen Satz, der den Korrelationskoeffizienten zur gegebenen Kryptowährung beschreibt

##### Task 2.1.3.1 *Korrelationskoeffizienten beschriften für bestimmten Größen*

- Aufwandschätzung: 2

### Feature 2.2 *Datenaufbereitung*
>Als *Benutzer* möchte ich, dass die *Daten zu Kryptowährung oder Index sinnvoll aufbereitet/gefiltert* werden, um *korrekte Zeiträume untersuchen* zu können und Vergleichbarkeit herzustellen.

- Aufwandsschätzung: L
- Akzeptanztests:
  - Daten werden nach gegebenen Zeitraum gefiltert
  - Daten sind in einer Form, sodass sie von Korrelationrechner verwertet werden können

#### Story 2.2.1 *Zeitraum filtern*
>Als *Benutzer* möchte ich, dass alle *Daten über den korrekten Zeitraum* handeln, um das *anzuzeigen/auszurechen nachdem gesucht* ist.

- Aufwandsschätzung: 9SP
- Akzeptanztests:
  - Daten werden nach gegebenen Zeitraum gefiltert
  - mindestens 2 Datenpunkte sind noch vorhanden

#### Story 2.2.2 *Daten vergleichbar machen*
>Als *Benutzer* möchte ich, dass alle *Daten aus verschiedenen Quellen gleich aussehen*, um *Berechnungen unabhängig von der Quelle der Daten* auzuführen.

- Aufwandsschätzung: 9SP
- Akzeptanztests:
  - alle Datensätze haben die gleiche Form
  - mindestens 2 Datenpunkte sind noch vorhanden


### Feature 2.3 *Weitere Korrelationsmaße*
>Als *Benutzer* möchte ich, dass die *Berechnung der Korrelation für andere Maße* als den Wechselkurs unterstützt wird, um *genauere Zusammenhänge erforschen* zu können.

- Aufwandsschätzung: L
- Akzeptanztests:
  - Es wird ein Korrelationskoeffizient für das Handelsvolumen berechnet
  - Es wird ein Korrelationskoeffizient für relative Hoch- und Tiefpunkte berechnet (zeitliche Korrelation sowie Korrelation der Wertänderung).





## Epic 3 *Visualisierung*
>Als *Benutzer* möchte ich die ausgewählten Daten in *verschiedensten Formen und Übersichten* sehen, um deren *zeitliche Verlauf und Vergleich anderen mit Kryptowährungen* deutlich sehen kann.

Visualisierung werden in Form von Diagrammen und Tabellen dargestellt. Sie dienen dem Vergleich von ausgewählten Kryptowährungen über einen bestimmten Zeitraumes.
Es hilft den Benutzer die geforderten Daten zu veranschaulichen.

### Feature 3.1 *Tabellen und Diagramme*
>Als *Benutzer* möchte ich für *einen besseren Überblick* *Tabellen und Diagramme* sehen.

- Aufwandsschätzung: L
- Akzeptanztests:
  - Tabellen sind skalierbar
  - Diagramme sind skalierbar

#### Story 3.1.1 *Tabellen darstellen*
>Als *Benutzer* möchte ich eine *Tabelle* nutzen, damit ich einen *besseren Überblick über Kryptowährungen* habe.

- Aufwandsschätzung: 5
- Akzeptanztests:
  - Die Tabelle ist skalierbar

##### Task 3.1.1.1 *Tabelle erstellen*

- Aufwandsschätzung: 4 Stunden

#### Story 3.1.2 *Diagramm darstellen*
>Als *Benutzer* möchte ich *Diagramme* sehen für einen *schnelleren Vergleich und bessere Übersicht*.

- Aufwandsschätzung: 6
- Akzeptanztest: 
  - Das Diagramm ist skalierbar

##### Task 3.1.2.1 *Diagramm erstellen*

- Aufwandsschätzung: 5 Stunden

#### Story 3.1.3 *Anzeigen mehrerer Visualisierungen*
>Als *Benutzer* möchte ich *mehrere Visualisierungen* sehen, damit *ein Vergleich* gezogen wird.

- Aufwandsschätzung: 7
- Akzeptanztest:
  - Es ist möglich sich mehrere Visualisierungen anzeigen zu lassen
  - Die Filtereinstellungen können bei jeden Diagramm anders sein


### Feature 3.2 *Einstellungen von Visualisierungen*
>Als *Benutzer* möchte ich die *Visualisierung selber anpassen* können, damit ich die *maximale Anzahl an Informationen herauslesen* kann.

- Aufwandsschätzung: M
- Akzeptanztests:
  - Filter funktionieren wie vorhergesehen
  - Geforderten Filter sind vorhanden

#### Story 3.2.1 *Filter erstellen*
>Als *Benutzer* möchte ich Filter haben, damit die *Diagramme/Tabelle meinen Wünschen anpasst* werden können, um *schneller Informationen* zu entschlüsseln.

- Aufwandsschätzung: 5
- Akzeptanztest:
  - Es stehen verschiedene Filter zur Verfügung

##### Task 3.2.1.1 *Datumsbereich für Diagramme*

- Aufwandsschätzung: 2 Stunden

##### Task 3.2.1.2 *Währung*

- Aufwandsschätzung: 1 Stunde

##### Task 3.2.1.3 *verschiedene Kryptowährungen*

- Aufwandsschätzung: 2 Stunden

### Feature 3.3 *Überblick aller Kryptowährungen*
>Als *Benutzer* möchte ich eine Tabelle mit den *Korrelationen aller Paare für einen bestimmten Zeitraum* haben, damit ich sie nach ihrem *Korrelationswert ordnen* kann.

- Aufwandsschätzung: M
- Akzeptanztests:
  - Es gibt eine seperate Ansicht für diesen Überblick
  - Es werden mindestens die Top 10 der Kryptowährungen angezeigt
  - Kryptowährungen können aus- und abgewählt werden für den Überblick
  - Ein Schwellenwert für eine Mindest-Korrelation kann ausgewählt werden

### Feature 3.4 *Speicherung der vom Benutzer eingestellten Filter*
>Als *Benutzer* möchte ich, dass meine *vorgenommenen Einstellungen für die Visualisierung über den Browserstart hinaus gespeichert* werden können, um nicht jedes Mal die *gleichen Einstellungen vornehmen* zu müssen.

- Aufwandsschätzung: L
- Akzeptanztests:
  - Der Datumsbereich wird gespeichert
  - Ausgewählte Kryptowährungen werden gesichert

### Feature 3.5 *Exportieren von Visualisierungen*
>Als *Benutzer* möchte ich die *visualisierten Daten exportieren* können, um sie für *andere Funktionen* zu nutzen z.B. Präsentationen.

- Aufwandsschätzung: M
- Akzeptanztests:
  - Tabellen können exportiert werden
  - Diagramme können exportiert werden
  - Das Format für Tabellen ist mindestens ".csv"
  - Das Format für Diagrammen ist mindestens ".png"
