# aschwartz.ottotask

## Task
Wir brauchen ein System, welches eine Aggregation von Artikeldaten per Kafka veröffentlicht. Dazu muss das System drei verschiedene Daten einsammeln, zusammenfügen und ausleiten.

Entwerfe das System unter folgenden Gesichtspunkten:
- Wie gehen wir damit um, dass die Nachrichten in beliebiger Reihenfolge kommen (z.B. Paketddaten, ohne das wir einen Artikel kennen)
- Wie können wir darauf reagieren, dass andere Anforderungen an das Ausgabeformat gestellt werden? z.B soll ab jetzt auch das `validFrom` Feld aus dem Preis dabei sein, oder die Definition von `heavyPackingUnits` ändert sich (siehe unten)

Der Detaillevel der Lösung (Deployfähiger Code, Proof Of Concept Code, Diagramme, ...) und sonstige Annahmen bleiben dir überlassen - Hauptsache, wir haben was zum Diskutieren :-)


### Eingangsdaten:
#### Preise
Die Nachrichten kommen über ein Kafka Topic.
```json
{
	"articleId": String,
	"sellingPrice": Double
	"validFrom": DateTime
}
```

**Paketdaten**
Die Paketdaten kommen über einen HTTP Feed (REST Endpunkt)
```json
[
	{
		"packingUnitId": String,
		"width": Double,
		"height": Double,
		"lenght": Double,
		"weight": Double
	}, ...
]
```

**Artikeldaten**
Die Nachrichten kommen über ein Kafka Topic.
```json
{
	"type": CHANGE/DELETED
	"articleId": String,
	"packingUnits": [
		Liste von packingUnitId
	],
}
```

### Ausgangsdaten
Die Anwendung soll Consumern folgende Nachricht schicken, wenn ein DELETED Event von Artikeln empfangen wurde:
```json
{
"type": DELETED,
"articleId": String
}
```

Ansonsten:
```json
{
"type": CHANGED,
"articleId": String,
"packingUnits": leeres Array oder alle PackingUnits, die zum Artikel gehören,
"heavyPackingUnits": Integer, Anzahl aller Packingunits mit Weight > 35
"sellingPrice": null oder Preis, der zum Artikel gehört.
}
```


```
