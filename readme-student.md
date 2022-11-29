
### Devops Exam
![branch](https://user-images.githubusercontent.com/72222999/204315102-14fa75f3-2027-40a8-95c9-8b78f333b758.PNG)
For å kunne beskytte arbeidsprossesen er det lurt å sette opp branch-protection. Dette vil gjøre at vi kan legge til noen regler for å begrene hva som blir pushet opp til main. For å kunne gjøre det må du først gå inn på settings -> Branches -> Add Branch Protection Rule. 


![2](https://user-images.githubusercontent.com/72222999/204315098-1f935b99-45d6-4a08-aa7a-c1875a6dae09.PNG)

Her krysset jeg av på "Require a pull request before merging" som gjør at alle commits som blir gjort må bli akseptert med en pull request før de blir merget inn i main. 
Med dette kan ikke en singel person ødelegge hele applikasjonen. Jeg la også til "Require Approval" som bare sier at noen må godkjenne commiten. 

Litt lenger ned på siden finner du Require status checks to pass before merging, jeg aktiverte den nedenfor som heter Require branches to be up to date before merging. Der valgte jeg "build" som er en jobb i ci.yml som sørge for at mvn bygger applikasjonen med grønne tester. Om noen prøver å pushe uten at noen tester eller applikasjone ikke vil bygge, så vil denne reglen stoppe commiten av å komme inn i main. 


### DOCKER

## Oppgave 1

Grunnen til at docker feiler for meg er at jeg ikke har ritkig secrets som de har på docker.yml. Når jeg la til docker brukernavnet mitt som DOCKER_HUB_USERNAME og passordet mitt som DOCKER_HUB_TOKEN kjørte docker jobben grønt. Det skal også sies at denne jobben gjør containeren deres public for alle til å kjøre.

## Oppgave 2
Først må jeg endre på dockerfilen slik at den bygger en container som har alle verktøyene applikasjon trenger for å kjøre, derfor må jeg også kopiere med pom.xml filen og kjøre mvn package. sier jeg til dockerfilen at han skal bruke den ferdig lagde applikasjon som er en JAR fil.

Etter det måtte jeg endre på workflowen, jeg antok at oppgaven mente at alt skulle forstette å være public kun på docker. Derfor logget jeg inn på docker i yml og bygde en shopiflytestingdocker, jeg ga den et dumt navn med at jeg visste at den skulle bli tatt ned fra public uansett. Jeg måtte fjerne litt unødvendig kodei yml. (mulig jeg logger inn 2 ganger, får sjekke dette til slutt).

## Oppgave 3
