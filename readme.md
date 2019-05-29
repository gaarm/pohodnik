# 1 Aplikacija `hiking`
Aplikacija `hiking` je sistem za vodenje evidence pohodnikov ter njihovega udeleževanja na pohodih.

## 1.1 Namen

Aplikacija je spisana za študijske potrebe na FOV MAG IS, pri predmetu OOARIS.

## 1.2 Tehnologija

Aplikacija je spisana v programskem jeziku Java. Gre za desktop aplikacijo, kjer je uporabljena knjižnica JavaFX. Projekt uporablja Maven packet manager in tako olajša upravljanje z zahtevanimi komponentami. Priložena sta dve `pom` datoteteki (Project Object Model). Za uporabnike Jave 8 - `pom.xml`, za uporabnike Jave 11 - `pom-11.xml`.  

> V kolikor uporabljate Javo 11, je potrebno poleg same Java namestiti JavaFX SDK knjžnico, ker ni več vključena v osnovno instalacijo Jave, kot je bilo to pri verziji Jave 8.

## 1.3 Baza
Aplikacija uporablja `sqlite` podatkovno bazo za shranjevanje podatkov. Imate dve opcijo.

- Skreirajte bazo sami - strukturo baze lahko najdete pod `resources/database.sql`. S pomočje te datoteke skreirate podatkovno bazo.

- Uporabiti že kreirano sqlite podatkovno bazo, ki jo najdete pod `dist/database.db` in že vsebuje same podatkovne tabele.

Aplikacija pričakuje da se baza nahaja na uporabnikovem domačem direktoriju po direktorijem `sqlite`:
`c:\Users\<User>\sqlite\database.db`.



