# Arkkitehtuurikuvaus
## Rakenne
Sovellus noudattaa kerrosarkkitehtuuria: käyttöliittymän tapahtumankäsittelijöistä kutsutaan sovelluslogiikasta vastaavia service-luokkia, serviceistä kutsutaan tietojen pysyväistallennuksesta vastaavia DAO-luokkia.

Kaaviossa ei turhaan kuvata kaikkia itsestäänselviä "yhden suhde yhteen" -tyyppisiä riippuvuuksia (eikä pakettia _flashcardapp.util_), sillä ne lähinnä häiritsevät tärkeämpien tietojen havainnointia.

![pakkauskaavio](https://raw.githubusercontent.com/henrinikku/ot-harjoitustyo/master/dokumentointi/kaaviot/arkkitehtuuri.png)

- _flashcardapp.view_ sisältää FXML:llä määriteltyjen näkymien lataamisesta ja sovelluksen käynnistyksestä vastaavan koodin
- _flashcardapp.controller_ sisältää käyttöliittymän logiikasta (tapahtumankäsittelijät, ilmoitusten näyttö tms) vastaavan koodin
- _flashcardapp.service_ sisältää sovelluslogiikasta vastaavat service-luokat
- _flashcardapp.dao_ sisältää tietojen pysyväistallennuksesta vastaavat DAO-luokat
- _flashcardapp.model_ sisältää ohjelmassa käsiteltävät, tietokantaan tallennettavat entiteetit
- _flashcardapp.util_ sisältää apumetodeja esimerkiksi merkkijonojen käsittelyyn
