# 1 Aplikacija ´hiking´
Aplikacija "hiking" je sistem za vodenje evidence pohodnikov ter njihovega udeleževanja na pohodih.

## 1.1 Tehnologija
Aplikacija je spisana v programskem jeziku JAVA (ver 11). Gre za desktop aplikacijo, kjer je uporabljena knjižnica javafx.
Projekt uporablja Maven packet manager in tako olajša upravljanje z zahtevanimi komponentami.

V kolikor imate nameščeno starejšo različico JAVE (ver 8), uporabite le-tega.

## 1.2 Namen
Aplikacija je spisana za študijske potrebe na FOV MAG IS, pri predmetu OOARIS.

## 1.3 Baza
Aplikacija uporablja sqlite bazo za shranjevanje podatkov. 
 - Strukturo baze lahko najdete pod `resources/database.sql`. S pomočje te datoteke skreirate podatkovno bazo.
 - Skreirano bazo lahko najdete pod `dist/database.db`. Ta datoteke že vsebuje podatkovne tabele.

## 1.4 Build
Po uspešno izvedenem buildu ste skreirali jar datoteko (`hiking-app-1.0-SNAPSHOT.jar`)

## 1.4 Zagon aplikacije
JAVA 11:
Za zagon aplikacije morate zadostiti nekaj predpogojev:
 - instalirati je potrebno JRE (Java Runtime Environment). Lahko instalirate tudi JDK (java Development Kit).
 - instalirati je potrebno JavaFX SDK.
 - na računalniku morate imeti sqlite bazo. Več informacij pod točko 1.3.
 
JAVA 8:
 - instalirati je potrebno JRE (Java Runtime Environment). Lahko instalirate tudi JDK (java Development Kit).
 - na računalniku morate imeti sqlite bazo. Več informacij pod točko 1.3.

 Zagon:
 ```$ java -jar hiking-app-1.0-SNAPSHOT.jar  --module-path="C:\Program Files\Java\javafx-sdk-11\lib" --add-modules=javafx.controls,javafx.fxml```