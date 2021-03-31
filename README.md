# swipe

Für das Modul <b>PME</b> entwickeln wir in unserem Team eine Java-Applikation names SWIPE.ME.

### Projektteam
* **John Reinecke** - [Profil](https://github.com/JFuqX)
* **Marvin Hof** - [Profil](https://github.com/MarvinHof)
* **Raphael Freybe** - [Profil](https://github.com/rfreybe)
* **Thomas Konietzny** - [Profil](https://github.com/Hundewurst)

<details>
<summary> Projektbeschreibung </summary>
<br>
Für das Modul <b>PME</b> entwickeln wir in unserem Team eine Java-Applikation namens SWIPE.ME, dabei handelt es sich um eine Lern-App für Android Geräte, in der man Karteikarten erstellen kann. Diese besitzen eine Vorderseite mit einer Frage und einer Rückseite mit der passenden Antwort, diese kann mit Text,oder einem Bild, welches man vorher der Karteikarte hinzufügt oder einem Text bestehen. Die Karteikarten Verfügen über ein Bewertungssystem, durch den ein Algorithmus beim Lernen Karteikarten vorschlägt, welche eine schlechte Bewertung haben und deshalb noch geübt werden müssen. Außerdem gibt es eine Ordnerstruktur, so dass man die Karten in Fächer und Themen kategorisieren kann, diese Ordner kann man auch nach Namen, zuletzt verwendet oder personalisiert über Drag and Drop sortieren, um die gewünschten Kategorien möglichst schnell und intuitiv finden zu können.<br>
</details>

<details>
<summary> genutzte Programme </summary>
<br>

* [IntelliJ](https://www.jetbrains.com/de-de/idea/download/other.html) - IDE für JAVA
* [lucidchart](https://www.lucidchart.com) - Tool für die Erstellung der Diagramme
* [Office](https://www.office.com/) - Office Programm
* [Git](https://git-scm.com/) - Versionskontrolle
* [Discord](https://discord.com/) - Kommunikationsmittel
* [Telegram](https://telegram.org) - Kommunikationsmittel
</details>


<details>
<summary> ER-Modell der Datenbank </summary>
<br>

![alt text]()
</details>

<details>
<summary> Sollkriterien </summary>
<br>

-Erstellung von Karteikarten (Vorderseite -> Frage, Rückseite -> Antwort) & Karteikartenverwaltung (Löschen)<br>
-Import von Bildern, Texte<br>
-Leichte bedienbarkeit durch swipen<br>
-Kategorien mit Unterordnern, in denen sich die Karteikarten befinden<br>
-Anzeige der Karteikarten innerhalb eines Ordners geordnet oder geshuffelt<br>
-Sidebar->Ordner->Fächer->Karteikarte<br>
-Sidebar->Ordnereinstellung---Ordner Sortieren nach---Karteikarten Sortieren nach---Filtern nach<br>
-Plusbutton unten rechts--Welcher Ordner(Ordner hinzufügen,zurück)--Welche Kategorie(Kategorie hinzufügen,zurück)<br>
-Sidebar->Einstellungen(unten links)--Sprache<br>
-Einstellungen--Sounds--Sprache<br>
-Ordnerstruktur:  Ordnerauswahl--Kategorieauswahl--Karteiauswahl<br>


</details>

<details>
<summary> Wunschkriterien </summary>
<br>

-Import .txt anstatt Copy&Paste<br>
-Statistik anzeige<br>
-Wechsel der Sprache (Deutsch/Englisch)<br>
-Sidebar->Stats<br>
-Fotofunktion in der App<br>
-Skalierbarkeit der Bildern<br>
-Lernplan (Push Benachrichtigungen -> Weckerfunktion)<br>
-Erweiterte bedienbarkeit(Karteikarten und Ordner durch bewegungen bedienen kann)<br>
-Gutes UI design<br>
-.csv Fileformat für die Vereinfachung, der Speicherung von Nutzerdaten<br>

</details>

<details>
<summary> Abgrenzungskriterien </summary>
<br>

-keine Accounts, kein login nur Lokales speichern<br>

</details>

<details>
<summary> Programmfunktionalitäten </summary>
<br>

Die Hauptnavigation der App ist in drei Punkte unterteilt, diese bestehen aus “Statistics”, “Home” und “Settings”. In den “Statistics” werden einige Daten zur Bewertung von Karteikarten gesammelt. In den “Settings” kann man zwischen der normalen und der dunklen Ansicht wechseln. Im Unterpunkt “Home” findet man die Hauptfunktionalität der App, es werden mittels eines Recycler Views alle Ordner angezeigt, die der Benutzer angelegt hat. Man kann die Ordner nach verschiedenen Kriterien sortieren oder mittels Drag and Drop eine eigene Reihenfolge festlegen. Wenn man auf einen Ordner klickt, sieht man dessen Inhalt und kann sich seine erstellten Karteikarten ansehen und diese bewerten, dies geschieht durch ein Wischen nach links oder rechts oder das drücken der Daumen, tippt man die Karte an, so wird die Rückseite angezeigt. Klickt man auf das Plus Symbol unten rechts kommt man auf ein Fragment, bei dem man wählen kann, ob man einen Ordner oder eine Karte erstellen möchte. Je nach dem was man erstellen möchte kann man den Punkt antippen und dort seine Daten eingeben. Karteikarten können nur in Ordnern erstellt werden und man kann diesen ein Bild aus seinem explorer zuweisen oder ein neues mit der Kamera des Gerätes aufnehmen.<br>

</details>

