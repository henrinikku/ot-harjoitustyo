# FlashCardApp

Sovelluksen avulla rekisteröityneet käyttäjät voivat luoda ja hallinnoida muistikortteja sekä niistä koostuvia pakkoja. Muistikortit ja -pakat ovat käyttäjäkohtaisia.

## Huomioita

Käytössä Java 11. Tietokantatiedosto generoidaan kansion _data_ alle, kun sovellus ajetaan ensimmäisen kerran.

## Dokumentaatio

- [Arkkitehtuurikuvaus](dokumentointi/arkkitehtuuri.md)
- [Vaatimusmäärittely](dokumentointi/vaatimusmaarittely.md)
- [Työaikakirjanpito](dokumentointi/tuntikirjanpito.md)

## Releaset

Vielä ei kai oo pakko

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
java -jar target/FlashCardApp-1.0-SNAPSHOT.jar
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
