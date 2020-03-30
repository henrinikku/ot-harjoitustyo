# FlashCardApp

Sovelluksen avulla rekisteröityneet käyttäjät voivat luoda ja hallinnoida muistikortteja sekä niistä koostuvia pakkoja. Muistikortit ja -pakat ovat käyttäjäkohtaisia.

## Huomioita

Käytössä Java 11. Tietokantatiedosto generoidaan kansion _data_ alle, kun sovellus ajetaan ensimmäisen kerran.

## Dokumentaatio

- [Vaatimusmäärittely](dokumentointi/vaatimusmaarittely.md)
- [Työaikakirjanpito](dokumentointi/tuntikirjanpito.md)

## Releaset

- Viikko 3 TODO

## Komentorivikomennot

Komennot tulee suorittaa kaniossa FlashCardApp.

### Suoritus

Suoritus komentoriviltä

```
mvn compile exec:java -Dexec.mainClass=flashcardapp.view.Main
```

Suoritettavan jar-tiedoston generointi kansion _target_ alle

```
mvn package
```

### Testaus

Testien suoritus

```
mvn test
```

Testikattavuusraportin generointi

```
mvn jacoco:report
```
