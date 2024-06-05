## A simple TODO API

**Oppdragsgiver:** John Mikael Lindbakk

## Oppgave

### Del 1:  
(Hentet fra slack)
Som nevnt skal dere lage et TODO api. Her må man kunne liste ut sine TODOs, opprette TODOs og Slette dem. Man må også
kunne markere oppgaver som gjennomført. Man skal også kunne hente ut en spesifikk todo.
En todo har disse feltene (men dere kan legge til andre om dere vil være kreative):
En ID
En tittel
En beskrivelse
En status som sier om den er gjennomført eller ikke
Det er noen regler som man må følge her:
I dette tilfellet er det applikasjonen sin jobb å opprette IDer. Dvs at når man lager en todo så skal man ikke tillate
at man sender inn ID via APIet (det er derfor vi må returnere det i responsen).
Man kan ikke opprette nye todos med status "Done".
Dere trenger ikke å stresse med database. Lag en "DAO" eller "Repository" som inneholder en liste der dere manipulerer :
slightly_smiling_face:
Er det noen spørsmål så rop ut!

###### Også 
Linken til de forskjellige guidelinesa: https://wiki.finods.com/display/CI/Ace+-+Project+guidelines  
Her har dere en nyttig
link: https://kotlinlang.org/docs/jvm-create-project-with-spring-boot.html#create-a-spring-boot-project  
Starte med et enkelt API, og etterhvert legge på en database og litt slik

### Del 2:

Yo, bare så dere har det skriftlig: Neste steg av oppgaven er å legge til støtte for en database. Dere har et eksempel
på hvordan det kan gjøres her: https://github.com/tietoevryfs/ace-pet-demo  
Det viktigste er kanskje å se på POM filen for å se hva dere trenger. Jeg har lagt en kommentar ved det viktige så se
igjennom hele pom filen :slightly_smiling_face:  
Om dere har lite å gjøre, så gjerne gå igjennom denne her også: https://www.w3schools.com/sql/ (edited)

### Del 3:

Nå har dere laget en fungerende TODO app med støtte for database. Neste steg er da:
Legge støtte for flere brukere
Hver todo må kunne knyttes til en bruker
API kall må oppdateres for å støtte å hente ut TODOs for en enkelt bruker
Det er ikke nødvendig å måtte støtte autentifisering
Skal dere opprette en ny tabell så ikke bak det inn i den eksisterende flyway sql filen dere har. Jeg forventer minst
sql filer.
Bestemor Gudfrid, som er kunden dere jobber for, har bestemt at hun ikke liker at man kan ha stygge ord i oppgaven.
Implementer en blacklist som Gudfrid kan oppdatere med ord hun ikke liker. Når nye todos blir laget så må det sjekkes at
ingen stygge ord eksisterer.

## Fremgang:

###### Del 1

- [ ] Liste alle todo (GET http://localhost:8080/)
- [ ] Opprette nye todo objekter ( POST http://localhost:8080/ med JSON {"title", "description") )
- [ ] Slette todo (POST http://localhost:8080/delete?id=...)
- [ ] Oppdatere todo (POST http://localhost:8080/markfinished?id=...)
- [ ] Finne enkelt todo (POST http://localhost:8080/find?id=...)


### Eksempler

- https://github.com/tietoevryfs/ace-pet-demo/blob/main/src/main/kotlin/com/github/atomfinger/demo/repository/PetRepository.kt
- https://github.com/tietoevryfs/lmt-core/blob/master/lmt-core-srv-test/src/test/kotlin/com/tietoevry/banking/ace/lmt/core/srv/test/billingaccountlimit/BillingAccountLimitTest.kt
