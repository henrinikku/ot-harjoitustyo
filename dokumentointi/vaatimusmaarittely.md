# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla rekisteröityneet käyttäjät voivat luoda ja hallinnoida muistikortteja sekä niistä koostuvia pakkoja. Muistikorttipakat ovat ainakin aluksi käyttäjäkohtaisia.

## Käyttäjät

Aluksi ainoa käyttäjärooli on _normaali käyttäjä_. On mahdollista, että sovellukseen lisätään myöhemmin jonkinlaisia pääkäyttäjiä. Myöhemmin, jos sovellukseen lisätään mahdollisuus katsella ja muokata muiden käyttäjien muistikorttipakkoja, voi olla tarpeen luoda käyttäjille pakkakohtaisia rooleja, kuten esimerkiksi _katselija_, _muokkaaja_ ja _omistaja_.

## Perusversion suunnitellut toiminnallisuudet

### Ennen kirjautumista

- [x] käyttäjä voi luoda käyttäjätunnuksen
  - käyttäjänimen tulee olla uniikki ja vähintään 3 merkkiä pitkä
  - salasanan tulee olla vähintään 8 merkkiä pitkä
- [x] käyttäjä voi kirjautua järjestelmään
  - kirjautuminen onnistuu, jos syötetty käyttäjänimi ja salasana löytyvät tallennetuista tiedoista ja täsmäävät
  - käyttäjälle ilmoitetaan epäonnistuneesta kirjautumisesta, mutta syytä tai syitä ei ainakaan perusversiossa yksilöidä

### Kirjautumisen jälkeen

- [ ] käyttäjä voi luoda tyhjän muistikorttipakan
  - pakalle on pakko antaa uniikki nimi
  - pakalle voi halutessaan antaa kuvauksen
- [ ] käyttäjä näkee luomansa muistikorttipakat
  - jos pakkoja ei ole vielä luotu, pakkojen tilalla lukee joku tilannetta kuvaava teksti
- [ ] käyttäjä voi poistaa muistikorttipakkoja
- [ ] käyttäjä voi muokata muistikorttipakkoja
  - muokkaus tapahtuu yksi pakka kerrallaan
  - voi lisätä, muokata sekä poistaa kortteja
    - kortit ovat ainakin aluksi pakkakohtaisia
    - kortille on pakko antaa kysymys ja vastaus
- [ ] käyttäjä voi opiskella muistikorttipakkoja
  - kortteja näytetään käyttäjälle näennäisen satunnaisessa järjestyksessä
  - kortista näytetään aluksi kysymys
  - vastaus paljastetaan, kun käyttäjä painaa nappia
- [ ] käyttäjä voi kirjautua ulos

## Jatkokehitysideoita

- [ ] korttien uudelleenkäyttö useassa eri pakassa
- [ ] pakkojen merkitseminen julkiseksi
- [ ] muiden käyttäjien tarkastelu
- [ ] muiden käyttäjien luomien pakkojen tarkastelu
- [ ] salasanan vaihto
- [ ] käyttäjän poistaminen
- [ ] tarkemmat virheilmoitukset esim. epäonnistuneen kirjautumisyrityksen yhteydessä
