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

## Käyttöliittymä

Kaikkien näkymien ulkoasu fxml-tiedostoilla, joiden tiedostopoluista [Views-luokka](https://github.com/henrinikku/ot-harjoitustyo/blob/master/FlashCardApp/src/main/java/flashcardapp/view/Views.java) pitää kirjaa.

Luokka [FlashCardUi](https://github.com/henrinikku/ot-harjoitustyo/blob/master/FlashCardApp/src/main/java/flashcardapp/view/FlashCardUi.java) vastaa näkymien ja niihin liittyvien [Controller-luokkien](https://github.com/henrinikku/ot-harjoitustyo/tree/master/FlashCardApp/src/main/java/flashcardapp/controller) lataamisesta.

[Controller-luokat](https://github.com/henrinikku/ot-harjoitustyo/tree/master/FlashCardApp/src/main/java/flashcardapp/controller) vastaavat yksittäisten näkymien logiikasta (esim. tapahtumankäsittelijät). Sovellus noudattaa kerrosarkkitehtuuria, ja sovelluslogiikasta vastaava koodi on eriytetty [Service-pakkaukseen](https://github.com/henrinikku/ot-harjoitustyo/tree/master/FlashCardApp/src/main/java/flashcardapp/service), jonka luokkia kutsutaan Controller-luokista tarvittaessa.

## Päätoiminnallisuudet

### Kirjautuminen

Kun kirjautumaton käyttäjä kirjoittaa olemassaolevan käyttäjän käyttäjänimen ja salasanan kirjautumislomakkeelle:

![kirjautuminen_sekvenssikaavio](https://raw.githubusercontent.com/henrinikku/ot-harjoitustyo/master/dokumentointi/kaaviot/kayttajan_kirjautuminen.png)

### Pakan luominen

Kun kirjautunut käyttäjä painaa aloitusnäkymästä _New deck_, kirjoittaa avautuvassa näkymässä pakan tiedot ja painaa _Create_:

![pakan_luominen_kaavio](https://raw.githubusercontent.com/henrinikku/ot-harjoitustyo/master/dokumentointi/kaaviot/Pakan%20luominen.png)

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

## Tietojen pysyväistallennus

Tietojen pysyväistallennuksesta vastaavat luokat (ja rajapinnat joiden kautta luokkia käytetään) löytyvät pakkauksesta _flashcardapp.dao_. Sovellus (ja nyt viikolla 7 myös kaikki testit) käyttää Hibernate-kirjastoa tietojen tallennukseen ja hakemiseen tietokannasta. 

Dao-luokkien toteutus olisi periaatteessa vaihdettavissa johonkin toiseen. Tällöin jouduttaisiin varmaankin muokkaamaan myös Model-luokissa käytettyjä annotaatioita, jotka tällä hetkellä toimivat nimenomaan Hibernate-toteutuksen kanssa. 

Sovellus käyttää tällä hetkellä H2-tietokantaa tietojen tallentamiseen. Tietokannan vaihtaminen mihin tahansa Hibernate-kirjaston tukemaan tietokantaan onnistuu helposti konfiguraatiotiedostoja muokkaamalla.

## Ohjelman rakenteeseen liittyvät heikkoudet

### Dao- ja Service-luokat
Service-luokat sisältävät jonkin verran metodeja, jotka eivät käytännössä tee muuta kuin välittävät saamansa parametrit edelleen jollekin Dao-luokalle. Kehittämistä voisikin sujuvoittaa, jos joustaisi arkkitehtuurin suhteen sen verran, että käyttöliittymän logiikasta vastaavat Controller-luokat voisivat jossain tilanteissa kutsua Dao-luokkia suoraan. Jos näin tehtäisiin, myös Controller-luokille pitäisi ehdottomasti kirjoittaa yksikkötestejä. Tässäkin ratkaisussa olisi varmasti myös huonoja puolia.
