## A simple TODO API 

**Oppdragsgiver:** John Mikael Lindbakk
**Produkteier:** Fredrik H. M. Sæther

### Oppgave
**Beskrivelse:**
(Hentet fra slack)
Som nevnt skal dere lage et TODO api. Her må man kunne liste ut sine TODOs, opprette TODOs og Slette dem. Man må også kunne markere oppgaver som gjennomført. Man skal også kunne hente ut en spesifikk todo.
En todo har disse feltene (men dere kan legge til andre om dere vil være kreative):
En ID
En tittel
En beskrivelse
En status som sier om den er gjennomført eller ikke
Det er noen regler som man må følge her:
I dette tilfellet er det applikasjonen sin jobb å opprette IDer. Dvs at når man lager en todo så skal man ikke tillate at man sender inn ID via APIet (det er derfor vi må returnere det i responsen).
Man kan ikke opprette nye todos med status "Done".
Dere trenger ikke å stresse med database. Lag en "DAO" eller "Repository" som inneholder en liste der dere manipulerer :slightly_smiling_face:
Er det noen spørsmål så rop ut!

**Også**
Linken til de forskjellige guidelinesa: https://wiki.finods.com/display/CI/Ace+-+Project+guidelines
Her har dere en nyttig link: https://kotlinlang.org/docs/jvm-create-project-with-spring-boot.html#create-a-spring-boot-project
Starte med et enkelt API, og etterhvert legge på en database og litt slik


## Fremgang: 
- [X] Liste alle todo (GET http://localhost:8080/)
- [X] Opprette nye todo objekter ( POST http://localhost:8080/ med JSON {"title", "description") )
- [X] Slette todo (POST http://localhost:8080/delete?id=...)
- [X] Oppdatere todo (POST http://localhost:8080/markfinished?id=...)
- [X] Finne enkelt todo (POST http://localhost:8080/find?id=...)
- [ ] Dokumenter API i bedre rammeverk
- [ ] Endre navn på lokal database til LocalDAO, eller LocalRepository
- [X] Bekreft interface med kunden(John Mikael)
- [X] Split i 3 filer: Controller, service, repository
- [X] Skriv 1 test
- [ ] Skrive test for hvert lag
- [X] Feilmelding når ikke ukjent ID er git
- [X] Lagre i database
- [X] Legg til noe ekstra funksjonalitet (tester?)
- [ ] Status er bool, endre til enum ["DONE", "NOT STARTED", osv.]?

### Backlog:
- [ ] Lag automatiske tester av API
- [ ] Oppdater API itl OpenApi ACE standard



### Eksempler
- https://github.com/tietoevryfs/ace-pet-demo/blob/main/src/main/kotlin/com/github/atomfinger/demo/repository/PetRepository.kt
- https://github.com/tietoevryfs/lmt-core/blob/master/lmt-core-srv-test/src/test/kotlin/com/tietoevry/banking/ace/lmt/core/srv/test/billingaccountlimit/BillingAccountLimitTest.kt



### Spør: 

