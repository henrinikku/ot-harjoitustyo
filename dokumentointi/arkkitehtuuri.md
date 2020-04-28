# Arkkitehtuurikuvaus

## Rakenne

Sovellus noudattaa kerrosarkkitehtuuria: käyttöliittymän tapahtumankäsittelijöistä kutsutaan sovelluslogiikasta vastaavia service-luokkia, serviceistä kutsutaan tietojen pysyväistallennuksesta vastaavia DAO-luokkia.

Kaaviossa ei turhaan kuvata jokaista itsestäänselvää "yhden suhde yhteen" -tyyppistä riippuvuutta (eikä pakettia _flashcardapp.util_), sillä ne lähinnä häiritsevät tärkeämpien tietojen havainnointia.

![pakkauskaavio](https://raw.githubusercontent.com/henrinikku/ot-harjoitustyo/master/dokumentointi/kaaviot/arkkitehtuuri.png)

- _flashcardapp.view_ sisältää FXML:llä määriteltyjen näkymien lataamisesta ja sovelluksen käynnistyksestä vastaavan koodin
- _flashcardapp.controller_ sisältää käyttöliittymän logiikasta (tapahtumankäsittelijät, ilmoitusten näyttö tms) vastaavan koodin
- _flashcardapp.service_ sisältää sovelluslogiikasta vastaavat service-luokat
- _flashcardapp.dao_ sisältää tietojen pysyväistallennuksesta vastaavat DAO-luokat
- _flashcardapp.model_ sisältää ohjelmassa käsiteltävät, tietokantaan tallennettavat entiteetit
- _flashcardapp.util_ sisältää apumetodeja esimerkiksi merkkijonojen käsittelyyn

## Päätoiminnallisuudet

### Kirjautuminen

Kun kirjautumaton käyttäjä kirjoittaa olemassaolevan käyttäjän käyttäjänimen ja salasanan kirjautumislomakkeelle:
![kirjautuminen_sekvenssikaavio](https://raw.githubusercontent.com/henrinikku/ot-harjoitustyo/master/dokumentointi/kaaviot/kayttajan_kirjautuminen.png)

## Sovelluslogiikka

Sovelluksen keskeiset käsitteet ovat käyttäjä ([User](https://github.com/henrinikku/ot-harjoitustyo/blob/master/FlashCardApp/src/main/java/flashcardapp/model/User.java)), pakka ([Deck](https://github.com/henrinikku/ot-harjoitustyo/blob/master/FlashCardApp/src/main/java/flashcardapp/model/Deck.java)) ja kortti ([Card](https://github.com/henrinikku/ot-harjoitustyo/blob/master/FlashCardApp/src/main/java/flashcardapp/model/Card.java)). Käyttäjät omistavat ja voivat opiskella pakkoja. Pakkoihin kuuluu kortteja, joita käyttäjät voivat myös hallinnoida.
![luokkakaavio](https://raw.githubusercontent.com/henrinikku/ot-harjoitustyo/master/dokumentointi/kaaviot/luokkakaavio.png)

Sovelluslogiikasta vastaavat pakkauksessa _flashcardapp.service_ sijaitsevat service-luokat, jotka injektoidaan käyttöliittymän logiikasta vastaaville controller-luokille automaattisesti kunkin näkymän alustuksen yhteydessä.

- [SessionService](https://github.com/henrinikku/ot-harjoitustyo/blob/master/FlashCardApp/src/main/java/flashcardapp/service/DefaultSessionService.java)
  - hallinnoi istuntoon liittyviä tietoja
  - esim. kirjautunut käyttäjä
- [UserService](https://github.com/henrinikku/ot-harjoitustyo/blob/master/FlashCardApp/src/main/java/flashcardapp/service/DefaultUserService.java)
  - vastaa rekisteröitymiseen ja kirjautumiseen liittyvästä logiikasta
  - esim. kirjautumistietojen tarkistus, salasanan hashays
- [DeckService](https://github.com/henrinikku/ot-harjoitustyo/blob/master/FlashCardApp/src/main/java/flashcardapp/service/DefaultDeckService.java)
  - vastaa pakkoihin liittyvästä logiikasta
  - esim. pakan tietojen validointi, tieto valitusta/aktiivisesta pakasta
- [CardService](https://github.com/henrinikku/ot-harjoitustyo/blob/master/FlashCardApp/src/main/java/flashcardapp/service/DefaultCardService.java)
  - vastaa kortteihin liittyvästä logiikasta
  - esim. oikeiden omistaja- ja pakkatietojen liittäminen korttiin tallennuksen yhteydessä

Service-luokat käyttävät tietojen tallennukseen, muokkaukseen ja hakemiseen entiteettikohtaisia, tietojen pysyväistallennuksesta vastaavia Dao-luokkia niille määriteltyjen rajapintojen [UserDao](https://github.com/henrinikku/ot-harjoitustyo/blob/master/FlashCardApp/src/main/java/flashcardapp/dao/UserDao.java), [DeckDao](https://github.com/henrinikku/ot-harjoitustyo/blob/master/FlashCardApp/src/main/java/flashcardapp/dao/DeckDao.java) ja [CardDao](https://github.com/henrinikku/ot-harjoitustyo/blob/master/FlashCardApp/src/main/java/flashcardapp/dao/CardDao.java) kautta.
