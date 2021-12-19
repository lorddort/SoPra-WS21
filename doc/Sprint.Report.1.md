# Sprint Report [1]

In diesem Sprint wurden die bis zum [Meilenstein 2](meilenstein-url) erledigten User Stories abgearbeitet.


## Verbesserte Dokumente [optional]

- Domain-Model
- System Context Diagramm
- Product Backlog


## Beschreibung der wichtigsten implementierten Features
 - Krypto-API:
    - Anbindung an CoinGecko
    - Abrufen von Kryptodaten wie Kurse, Volumen, Marktkapitalisierung
    - Abrufen von Kryptodaten wie Beschreibung, Logo-URL
 - Korrelation-API:
    - Pearson Korrelationsrechner
    - Ausrechnen der Korrelation von 2 Kryptowährungen (mit Preisdaten des letzten Jahres)
 - GUI Main View:
    - Ein Liniendiagramm wurde implementiert, welche per default die Bitcoin und Ethereum Kurse der letzten 24 Stunden anzeigt.
    - Es können verschiedene vordefinierte Zeitrahmen ausgewählt werden, in welchem das Diagramm die Kurse anzeigt.
    - Die zehn besten Kurse stehen zur Auswahl.
    - Kurse die geladen wurden können ein- und ausgeblendet werden.
    - Das Diagramm lässt sich exportieren 

## Tests/Testprotokolle/Nachweis der Testabdeckung

- API:
    - die gesamte API wurde verstärkt durch manuelles testen getestet, da MockMvc keine Verbindung zu coinGecko API aufbauen konnte und damit nicht mit Realdaten getestet werden konnte
- GUI Main View:
    - Es wurde ausgiebiges exploratives Testing der Benutzeroberfläche betrieben. Es verbleibt im Moment noch ein Bug, bei welchem beim Wechsel des Zeitrahmen nicht alle ausgewählten Währungen direkt an den neuen Rahmen angepasst werden. Im Moment lässt sich dieser Bug noch umgehen indem man den Zeitrahmen zwei mal hintereinander wählt.
