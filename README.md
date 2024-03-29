# FlashCardApp

Sovelluksen avulla rekisteröityneet käyttäjät voivat luoda ja hallinnoida muistikortteja sekä niistä koostuvia pakkoja. Muistikortit ja -pakat ovat käyttäjäkohtaisia.

## HUOM!!!

Tietokantatiedosto generoidaan kansion _data_ alle, kun sovellus ajetaan ensimmäisen kerran. Mikäli oikean niminen tietokanta on jo olemassa, skeema ei välttämättä päivity oikein. Jos sovellusta testataan eri viikkoina samalla tietokoneella, täytyy kansio _data_ poistaa välissä.

## Dokumentaatio

- [Arkkitehtuurikuvaus](dokumentointi/arkkitehtuuri.md)
- [Vaatimusmäärittely](dokumentointi/vaatimusmaarittely.md)
- [Työaikakirjanpito](dokumentointi/tuntikirjanpito.md)
- [Käyttöohje](dokumentointi/kayttoohje.md)
- [Testausdokumentti](dokumentointi/testaus.md)

## Releaset

- [Viikko 5](https://github.com/henrinikku/ot-harjoitustyo/releases/tag/v1.0)
- [Viikko 6](https://github.com/henrinikku/ot-harjoitustyo/releases/tag/v1.1)
- [Loppupalautus](https://github.com/henrinikku/ot-harjoitustyo/releases/tag/v1.2)

## Komentorivikomennot

Komennot tulee suorittaa kaniossa FlashCardApp.

### Asennus

Riippuvuuksien asennus

```
mvn clean install
```

### Suoritus

Suoritus komentoriviltä

```
mvn compile exec:java -Dexec.mainClass=flashcardapp.view.Main
```

Suoritettavan jar-tiedoston generointi kansion _target_ alle

```
mvn package
```

Jar-tiedoston suoritus

```
java -jar target/FlashCardApp.jar
```

### Testaus

Huom! Testejä ajettaessa tulostuu virheilmoituksia. Ne eivät ole merkki testien kaatumisesta, vaan ne tulostetaan, kun odotettuja virhetilanteita käsitellään.

Testien suoritus

```
mvn test
```

Testikattavuusraportin generointi

```
mvn test jacoco:report
```

### Checkstyle

Checkstyle-raportin generointi

```
mvn jxr:jxr checkstyle:checkstyle
```

### Dokumentaatio

JavaDocin generointi

```
mvn javadoc:javadoc
```
