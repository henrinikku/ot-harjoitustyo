# Käyttöohje

Lataa tiedosto [FlashCardApp-1.0-SNAPSHOT.jar](https://github.com/henrinikku/ot-harjoitustyo/releases/download/v1.1/FlashCardApp-1.0-SNAPSHOT.jar).

## Konfigurointi

Sovellus ei vaadi konfigurointia. Varmista varmuuden vuoksi, että samassa kansiossa ei ole _data_-nimistä kansiota. Jos haluat vaikuttaa tietokantatiedoston nimeen tai sijaintiin, muokkaa tiedostoa [spring-config.xml](https://github.com/henrinikku/ot-harjoitustyo/blob/master/FlashCardApp/src/main/resources/spring-config.xml) ja käännä tai aja ohjelma uudestaan. Testeihin vaikuttavat tietokanta-asetukset löytyvät vastaavasti tiedostosta [spring-config-test.xml](https://github.com/henrinikku/ot-harjoitustyo/blob/master/FlashCardApp/src/main/resources/spring-config-test.xml).

## Ohjelman käynnistäminen

Aja komento

```
java -jar FlashCardApp-1.0-SNAPSHOT.jar
```

## Kirjautuminen ja käyttäjän luominen

Sovellus käynnistyy näkymään, jossa voi luoda uusia käyttäjiä tai kirjautua jollain jo luodulla käyttäjällä.

![kirjautumisnakyma](https://raw.githubusercontent.com/henrinikku/ot-harjoitustyo/master/dokumentointi/kaaviot/Image%2028-04-2020%20at%2016.53.jpg)

Kirjautuaksesi täytä vasemmanpuoleiset kentät ja paina _Log in_.

Rekisteröityäksesi täytä oikeanpuoleiset kentät ja paina _Register_.

## Pakkojen hallinnointi

Kirjauduttuasi pääset näkymään, jossa näet pakkasi.

![indexnakyma](https://raw.githubusercontent.com/henrinikku/ot-harjoitustyo/master/dokumentointi/kaaviot/Image%2028-04-2020%20at%2016.58.jpg)

Jos haluat kirjautua ulos, paina _Log out_.

## Uuden pakan luominen

Jos haluat luoda uuden pakan, paina _Create a new deck_, täytä avautuvan lomakkeen kentät ja paina _Create_.

![uusipakka](https://raw.githubusercontent.com/henrinikku/ot-harjoitustyo/master/dokumentointi/kaaviot/Image%2028-04-2020%20at%2017.05.jpg)

## Pakan poistaminen

Mene tervetuloa-näkymään ja valitse haluamasi pakka listasta. Paina avautuvassa näkymässä _Delete deck_.

![pakanpoisto](https://raw.githubusercontent.com/henrinikku/ot-harjoitustyo/master/dokumentointi/kaaviot/Image%2028-04-2020%20at%2017.12.jpg)

## Uuden kortin luominen

Kun olet yksittäisen pakan näkymässä, paina _Add card_. Täytä kentät avautuvassa näkymässä ja paina _Create_.

![uusikortti](https://raw.githubusercontent.com/henrinikku/ot-harjoitustyo/master/dokumentointi/kaaviot/Image%2028-04-2020%20at%2017.09.jpg)

## Uuden kortin muokkaaminen ja poistaminen

Kun olet yksittäisen pakan näkymässä, valitse haluamasi kortti listasta.

Avautuvassa näkymässä:

- Jos haluat muokata korttia, kirjoita kenttiin haluamasi arvot ja paina _Save_.
- Jos haluat poistaa kortin, paina _Delete_.

![editkortti](https://raw.githubusercontent.com/henrinikku/ot-harjoitustyo/master/dokumentointi/kaaviot/Image%2028-04-2020%20at%2017.14.jpg)
