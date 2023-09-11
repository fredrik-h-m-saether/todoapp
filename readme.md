## A simple TODO API 

**Oppdragsgiver:** John Mikael Lindbakk
**Produkteier:** Fredrik H. M. Sæther

### Oppdrag
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
- [X] Opprette nye todo objekter ( POST http://localhost:8080/new med JSON {"title", "description") )
- [X] Slette todo (POST http://localhost:8080/delete?id=...)
- [X] Oppdatere todo (POST http://localhost:8080/markfinished?id=...)
- [X] Finne enkelt todo (POST http://localhost:8080/find?id=...)
- [ ] Dokumenter API i bedre rammeverk
- [ ] Lag automatiske tester av API
- [ ] Endre navn på lokal database til LocalDAO, eller LocalRepository
- [ ] Bekreft interface med kunden(John Mikael)
- [ ] Split i 3 filer: Fasade, service, persistance
- [ ] Skriv 1 test...!
- [ ] Lagre i database


## Spør: 
- [ ] Status er bool, endre til ["DONE", "NOT STARTED", osv.]?

