# Sprint Report [1]

In diesem Sprint wurden die bis zum [Meilenstein 2](meilenstein-url) erledigten User Stories abgearbeitet.

TODO

## Verbesserte Dokumente [optional]

- Domain-Model


## Beschreibung der wichtigsten implementierten Features

 - GUI Main View:
    - Ein Liniendiagramm wurde implementiert, welche per default die Bitcoin und Ethereum Kurse der letzten 24 Stunden anzeigt.
    - Es können verschiedene vordefinierte Zeitrahmen ausgewählt werden, in welchem das Diagramm die Kurse anzeigt.
    - Die zehn besten Kurse stehen zur Auswahl.
    - Kurse die geladen wurden können ein- und ausgeblendet werden.
    - Das Diagramm lässt sich exportieren 

## Tests/Testprotokolle/Nachweis der Testabdeckung

*TODO: Testprotokolle und Abdeckung hier einfügen/verlinken.*

*TODO: Begründungen, ob die fertigen Features für ihre Komplexität/Relevanz ausreichend getestet wurden (z.B. durch Unit Tests oder manuelle Tests)*
- GUI Main View:
    - Es wurde ausgiebiges exploratives Testing der Benutzeroberfläche betrieben. Es verbleibt im Moment noch ein Bug, bei welchem beim Wechsel des Zeitrahmen nicht alle ausgewählten Währungen direkt an den neuen Rahmen angepasst werden. Im Moment lässt sich dieser Bug noch umgehen indem man den Zeitrahmen zwei mal hintereinander wählt.
