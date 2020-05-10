# Testausdokumentti
Järjestelmätason testaus on toteutettu manuaalisesti klikkailemalla. Yksikkö- ja integraatiotestit on toteutettu JUnit-kirjaston avulla. Käyttöliittymästä vastaava koodi on jätetty automaattisten testien ulkouolelle.

## Yksikkö- ja integraatiotestit

### Sovelluslogiikka / service-luokat

Testikattavuudesta suurin osa on seurausta sovelluslogiikasta vastaavia [Service-luokkia](https://github.com/henrinikku/ot-harjoitustyo/blob/master/FlashCardApp/src/main/java/flashcardapp/service) testaavista integraatiotesteistä. Testitapauksiksi on pyritty lähtökohtaisesti valitsemaan tilanteita, joita voi tulla vastaan sovellusta käytettäessä (esim. pakan luominen ilman nimeä).

Projektin alkuvaiheessa yllämainitut integraatiotestit käyttivät Dao-rajapintojen keskusmuistitoteutuksia, mutta myöhemmin otettiin käyttöön keskusmuistissa pidettävä H2-testitietokanta sovelluksessa käytettävät Dao-rajapinnan toteutukset.

### Tietojenpysyväistallennus / Dao-luokat

Dao-luokat tulevat testatuiksi integraatiotesteissä. Lisäksi niitä testataan yksikkötesteillä, joissa käytetään testitietokantaa integraatiotestien tapaan.


### Util- ja Model-luokat

Sovelluksessa käsiteltäville Model-luokille ja flashcardapp.util -paketin luokille on myös tehty muutamia yksittäisiä testejä. Näissä luokissa ei tosin ole hirveästi testattavaa.

### Testikattavuus

Sovelluksen testien rivikattavuus on 84% ja haarautumakattavuus 75%, kun mukaan ei lasketa käyttöliittymästä vastaavaa koodia, joka jäi testaamatta.

Testaamatta jääneissä osissa on kyse joidenkin sovelluslogiikan osien rajatapauksista.

![testikattavuus](https://raw.githubusercontent.com/henrinikku/ot-harjoitustyo/master/dokumentointi/kaaviot/Image%2010-05-2020%20at%2020.11.jpg)

## Järjestelmätestaus

Järjestelmätestausta tehtiin manuaalisesti mutta systemaattisesti sovelluksen ominaisuuksia läpikäyden.

### Asennus

Sovellus on asennettu [käyttöohjeessa](https://github.com/henrinikku/ot-harjoitustyo/blob/master/dokumentointi/kayttoohje.md) annettuja ohjeita seuraamalla.

### Ominaisuudet

Kaikki sovelluksen [vaatimusmäärittelyssä](https://github.com/henrinikku/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md) tehdyiksi merkityt ominaisuudet on testattu käsin sekä oikeilla että virheellisillä (esim. tyhjillä) arvoilla.

## Korjaamattomat ongelmat

Sovelluksen käyttäjälle antaman palautteen laatu vaihtelee riippuen näkymästä ja ongelmatilanteesta. Esimerkiksi kaikilla lomakkeilla ei eritellä syytä sille, miksi syötetty nimi ei käy.
