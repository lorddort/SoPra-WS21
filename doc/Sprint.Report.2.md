# Sprint Report [2]

In diesem Sprint wurden die bis zum [Meilenstein 3](meilenstein-url) erledigten User Stories abgearbeitet.

## Beschreibung der wichtigsten implementierten Features
- GUI:
    - Es stehen mehr Einstellungen für die Diagramm-Ansicht zur Verfügung
    - Es können bis zu vier einzeln konfigurierbare Diagramme nebeneinander verglichen werden
    - Es wurde ein Hauptseite hinzugefügt, wo man Kryptowährungen vorabladen kann.
    - Es besteht die Möglichkeit Details zu einer Kryptowährung anzuschauen. Allerdings ist die Seite noch nicht fertig implementiert
    - Korrelationkoeffizienten werden nun für Kryptowährungen richtig angezeigt, auch für den richtigen Zeitbereich.

- Krypto-API:
    - Preis-, Volumen- und Marktkapitalisierungsraten aus verschiedenen Zeitabschnitten herausgeben (year, month, last_month, week, 24h, today)

 - Korrelation-API:
    - Korrelation aus verschiedenen Zeitabschnitten herausgeben (year, month, last_month, week, 24h, today)

## Tests/Testprotokolle/Nachweis der Testabdeckung
- GUI:
    - Es wurden ausgiebig explorative Tests durchgeführt

- API:
    - Die gesamte API wurde verstärkt durch manuelles Testen getestet, da MockMvc keine Verbindung zur CoinGecko API aufbauen konnte und damit nicht mit Realdaten getestet werden konnte