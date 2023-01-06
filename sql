




In einer relationalen Datenbank (Postgres, MariaDB oder Oracle) sollen zwei Tabellen erstellt
werden, die folgende Datenfelder enthalten sollen:
Tabelle 1:
MESSAGEID eindeutige NachrichtenID
FILENAME Dateiname der eingelesenen Datei
ORDERNO Bestellnummer
TRANSMISSION Übertragungsdatum und –zeit
SUPPLIER Lieferantennummer
CUSTOMER Kundennummer
REFERENCENO Kennnummer des Bauteils
COMMODITY Teileartgruppe des Bauteils



Tabelle 2:
MESSAGEID eindeutige NachrichtenID
POSITION fortlaufende Positionsnummer zur NachrichtenID (ab 1)
ITEMNO Artikelnummer
QUANTITY bestellte Menge
ITEMTYPE Teileart
Beide Tabellen sollen über das Feld MESSAGEID in einer 1:n-Relation verbunden 
