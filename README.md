# SneakerCity

<img src="src/main/webapp/images/Sneakercity.png" alt="SneakerCity Logo" width="200px" height="200px">

Dit is een webapplicatie voor de cursus "Individuele Propedeuse Assessment" van de Hogeschool Utrecht.

## Inhoudsopgave

- Over
- Installatie
- Gebruik

### Over

De opdracht van dit assessment was om een zelfgekozen casus te verzinnen en uit te werken in een webapplicatie.
Hierdoor is het idee van SneakerCity bedacht en uitgewerkt. 

SneakerCity is een nieuw online bedrijf wat zich richt op de verkoop van exclusieve sneakers. 
Op deze webapplicatie kunnen klanten sneakers bekijken en bestellen. Hiernaast is er ook een administrator functionaliteit om de sneakers bij te houden.

### Installatie
De webapplicatie is gemaakt in een Maven project structure via IntelliJ IDEA.

Om de webapplicatie te laten werken heb je onderstaande requirements nodig:

- Een IDE (Het liefst IntelliJ IDEA)
- Java SDK 17 of hoger
- Tomcat 9

Hieronder staat stapsgewijs hoe je de webapplicatie werkend krijgt:

1. Clone de repository in je IntelliJ IDEA of andere IDE
2. Check de `pom.xml` of onderstaande dependencies erin staan: 
   - org.junit.jupiter | version 5.8.2
   - javax.servlet | version 4.0.1
   - org.apache.tomcat | version 9.0.73
   - org.glassfish | version 1.1.4
   - org.glassfish.jersey.containers | 2.35
   - org.glassfish.jersey.injects | 2.35
   - org.glassfish.jersey.media | 2.35
   - io.jsonwebtoken | 0.9.1
3. Download alle dependencies en wacht totdat IntelliJ IDEA het project heeft geconfigureerd
4. Maak een nieuwe Tomcat-configuratie:
   - Klik op "Edit Configurations" in de werkbalk.
   - Klik op het plusteken (+) en selecteer "Tomcat Server" en "Local".
   - Selecteer de Tomcat-versie (9.0.74) en configureer de serverlocatie.
   - Stel "Deployment" in op "IPASS-SneakerCity:war exploded".
   - Klik op "OK" om de configuratie op te slaan.
5. Start de Tomcat server:
   - Klik op de groene "Run" knop in de werkbalk bovenaan en selecteer de Tomcat-configuratie die je zojuist hebt gemaakt.
   - Wacht tot de server is gestart en het project is geïmplementeerd.
6. Open je webbrowser en ga naar https://localhost:8080/ 

### Gebruik

Als de installatie gelukt is kom je op de homepagina terecht. Hiervandaan kun je naar de sneakers pagina om alle sneakers te bekijken of naar je accountpagina.
Om een bestelling te kunnen plaatsen heb je een account nodig, op de registreer pagina kun je dan een account aanmaken.
Voor dit project is er geen betaalsysteem geïntegreerd.
Als klant heb je de mogelijkheid om al je bestellingen in te zien op de mijn bestellingen pagina.

Hiernaast kan je ook als "admin" inloggen. Als admin kan je nieuwe sneakers toevoegen en bestaande sneakers wijzigen of verwijderen.
Standaard admin account:

<b>Username</b>: Tyler 
<br>
<b>Password</b>: Tyler

### Studentgegevens

<b>Naam</b>: Tyler de Rijk
<br>
<b>Studentnummer</b>: 1756327
<br>
<b>Richting</b>: Software Development
<br>
<b>School</b>: Hogeschool Utrecht