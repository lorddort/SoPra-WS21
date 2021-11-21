# Entwurf

> Dieser Entwurf legt die prinzipielle Lösungsarchitektur fest und enthält alles, was man benötigt, um einem Außenstehenden den prinzipiellen Aufbau der App erklären zu können.
> Der Fokus liegt auf einem groben Überblick und vereinzelten wichtigen Details (**keep it simple**).

**TODO:** Beschreibung des grundlegenden Aufbaus

**TODO:** Verweis auf Standards wie zum Beispiel verwendete Entwurfsmuster (optional)

## System Context Diagram

![System Context Diagram](images/CryptoCorrelation_context-diagramv1.1.png)

Investor/ Krypto Enthusiast: Kunde, der die App zur Informationbeschaffung über Kryptowährungen nutzt.

REST API Krypto Preis Informationen: Quelle der historischen Daten für Kryptowährungen.

DB Markt Indizes: Quelle der historischen Daten für Marktindizes.

Settings Folder: Speicherort für Benutzereinstellungen.

## Domain Model

![Domain Model](images/domain-model.png)

**TODO:** Domain Model der zentralen Entitäten und deren Beziehungen erstellen

## Beschreibung der Java-Packages der API

Folgende Top-Level-Packages sind geplant während der Entwicklung zu nutzen:
```
api/
|- RESTController
|- database
|- statistic
|- export
|- Application.java
```
Im Package RESTController befinden sich die Controller der REST-API. Diese beinhaltet z.B. GET.
Das Package database kommuniziert mit der Datenbank und speichert die Daten, die persistent sein soll.
Das Package statistic beinhaltet mathematische Gleichungen, wie die Berechnung von Korrelationskoeffizienten.
Im Package export wird die Exportfunktion implementiert.

Im Falle von weiteren verwendeten Packages, die im Obrigen nicht genannt wurden, wird nach Definition of Done dieser Entwurf aktualisiert und hinzugefügt.


## Beschreibung der Frontend-Views

## GUI Beschreibung

### Hauptansicht
- Beim Starten der Software ist ein Diagramm von Bitcoin und Etherium geladen
- Über "Währungen" in der Sidebar kann man aus allen verfügbaren Währungen auswählen, welche die Website laden soll
- Sidebar hat ein Diagramm-Settings Dropdown:
    - Einstellen des Betrachteten Zeitraums
    - Einstellen der Angezeigten Währungen
    - Einstellen ob EUR oder USD angezeigt wird
- Export Button erlaubt das Herunterladen des aktuell ausgewählten Diagramms
- Rechte Sidebar erlaubt, weitere Diagramme hinzuzufügen, Miniaturvorschau der Diagramme verfügbar
    - Einklappbar
- Über den "Korrelationskoeffizientenansicht" Button unten links kommt man in die Detailansicht der Korrelationskoeffizienten
  
### Korrelationsansicht
- Über "Währungen" lassen sich auch hier die geladenen Währungen auswählen
- In Settings findet man:
    - Auswählen der angezeigten Währungen
    - Auswählen des betrachteten Zeitraums
- Export erlaubt, die aktuell konfigurierte Tabelle runterzuladen
- Andere Ideen:
    - Hinzufügen und entfernen von Zeilen und Spalten direkt in Tabelle, ohne Settings in der Sidebar.

## GUI-Skizze: Home-View

![GUI Sketch Home View](sketches/gui-sketch-home-view.jpg)

## GUI-Skizze: Korrelationskoeffizientenansicht

![GUI Sketch Detail View](sketches/gui-sketch-detail-view.jpg)
