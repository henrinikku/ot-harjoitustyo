# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla rekisteröityneet käyttäjät voivat luoda ja hallinnoida muistikortteja sekä niistä koostuvia pakkoja. Muistikorttipakat ovat ainakin aluksi käyttäjäkohtaisia.

## Käyttäjät

Aluksi ainoa käyttäjärooli on _normaali käyttäjä_. On mahdollista, että sovellukseen lisätään myöhemmin jonkinlaisia pääkäyttäjiä. Myöhemmin, jos sovellukseen lisätään mahdollisuus katsella ja muokata muiden käyttäjien muistikorttipakkoja, voi olla tarpeen luoda käyttäjille pakkakohtaisia rooleja, kuten esimerkiksi _katselija_, _muokkaaja_ ja _omistaja_.

## Käyttöliittymä

Sovellus aukeaa näkymään, jossa voi luoda uusia käyttäjiä tai kirjautua sisään jo luodulla käyttäjällä. Kirjautunut käyttäjä ohjataan näkymään, jossa käyttäjä voi kirjautua ulos, tarkastella pakkojaan tai siirtyä luomaan uusia pakkoja.

Yksittäisen pakan näkymästä käsin on mahdollista poistaa tarkasteltava pakka (ja samalla siihen kuuluvat kortit), opiskella pakan kortteja (mikäli niitä on lisätty vähintään 1), luoda pakkaan uusia kortteja sekä muokata vanhoja.

Opiskelunäkymässä käyttäjälle näytetään satunnaisesti valittuja kortteja opiskeltavasta pakasta. Kortista näytetään aluksi pelkkä kysymys, ja vastauksen saa näkyviin nappia painamalla.

Kirjautumisnäkymää lukuunottamatta kaikista näkymistä on mahdollista siirtyä edeltävään näkymään.

## Perusversion suunnitellut toiminnallisuudet

### Ennen kirjautumista

- [x] käyttäjä voi luoda käyttäjätunnuksen
  - käyttäjänimen tulee olla uniikki ja vähintään 3 merkkiä pitkä
  - salasanan tulee olla vähintään 8 merkkiä pitkä
- [x] käyttäjä voi kirjautua järjestelmään
  - kirjautuminen onnistuu, jos syötetty käyttäjänimi ja salasana löytyvät tallennetuista tiedoista ja täsmäävät
  - käyttäjälle ilmoitetaan epäonnistuneesta kirjautumisesta, mutta syytä tai syitä ei ainakaan perusversiossa yksilöidä

### Kirjautumisen jälkeen

- [x] käyttäjä voi luoda tyhjän muistikorttipakan
  - pakalle on pakko antaa uniikki nimi
  - pakalle voi halutessaan antaa kuvauksen
- [x] käyttäjä näkee luomansa muistikorttipakat
  - jos pakkoja ei ole vielä luotu, pakkojen tilalla lukee joku tilannetta kuvaava teksti
- [x] käyttäjä voi poistaa muistikorttipakkoja
- [x] käyttäjä voi muokata muistikorttipakkoja
  - muokkaus tapahtuu yksi pakka kerrallaan
  - voi lisätä, muokata sekä poistaa kortteja
    - kortit ovat ainakin aluksi pakkakohtaisia
    - kortille on pakko antaa kysymys ja vastaus
- [x] käyttäjä voi opiskella muistikorttipakkoja
  - kortteja näytetään käyttäjälle näennäisen satunnaisessa järjestyksessä
  - kortista näytetään aluksi kysymys
  - vastaus paljastetaan, kun käyttäjä painaa nappia
- [x] käyttäjä voi kirjautua ulos

## Jatkokehitysideoita

- [ ] korttien uudelleenkäyttö useassa eri pakassa
- [ ] pakkojen merkitseminen julkiseksi
- [ ] muiden käyttäjien tarkastelu
- [ ] muiden käyttäjien luomien pakkojen tarkastelu
- [ ] salasanan vaihto
- [ ] käyttäjän poistaminen
- [ ] tarkemmat virheilmoitukset esim. epäonnistuneen kirjautumisyrityksen yhteydessä
