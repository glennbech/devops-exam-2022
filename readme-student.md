
# Devops Exam
## Del 1 DevOps-prinsipper


Det er en del utfordringer med slik de jobber idag.. De deployer kun første mandag i kvartalet. Dette fører til mye waste, det er mye tryggere og bedre om de heller hadde lastet opp kontinuerlig, dette hjelper det stakkarse devops teamet som må jobbe overtid på å innstalere og legge ut serverene som de får tilsendt. Dette er en stor waste med at De heller kan ha en kontonurlig utlvereing gjennom automatiske teseter og byggninger med foreksempel github actions. Om de hadde automatisert tester ville de heller ikke hatt bru bruk for å ansettes så mange testere, da kunne personen som pushet det fikset testene med en gnang. 

De bryter prinsippet over kontinuerlig integrasjon, med at de samler alt sammen på en gang og håper det virker. Dette vil føre til mye waste av arbeidskraft hvor de da må finne feilene i en nøste av problemer. Hadde de lastet opp kontinuerlig ville det mest sannynligvis vært lettere å finne de ulike feilene. Derfor burde de ha en devops pipeline hvor koden skal flyte igjennom flere kontrollere, sånn som tester, kompilering og builds. Deretter går det automatisk ut til kundenen.

De overleverer kode til en annen avdeling, devops prinsippene for flow går imot det. prinsippene vil også ha mye mindre overleveringer, det er ingen som kjenner koden som har blitt skrevet bedre enn den personen som har utviklet det. Det er da veldig dumt at de overleverer det til en annen avdeling.  Derfor er det mye bedre om de er med i prossessen med å utlvere det til produksjon. Det kan også bety at avdelingen må waste tiden sin på å vente på overleveringen fra den andre avdelingen. Den tiden kunne de ha brukt på noe mye mer nyttig.


Release kode ofte kan by på noen utfordringer. Det kan oppstå flere problemer med prosjektet som du ikke kunne forutse, dermed er det veldig bra at devops har et prinsipp som forebygger. Overvåking av systmene er veldig viktig slik at du kan se problemene før de oppstår eller blir værre. Om de hadde brukt noe feedback, så kan det hende at de ikke måtte rerolle mange ganger. Om kvaliteten blir sjekket ved kilden trenger det ikke å bli sjekket lengere ned i pipelinene med QA og tester for dårlig kode. 

De kan lage et cloudwatch dashboard for å kunne observere når noe feiler eller tidlig finne noen usedvanlige oppførsler. De kan lage alarmer som vekker devops folkene på natten om de trenger. 
 


## Del 2 - CI

### Oppgave 1
![image](https://user-images.githubusercontent.com/72222999/204775670-354fcc08-b48f-4858-97cf-574894d3d2f6.png)

For at den skal lages på pull request og for hvert push til main legger jeg til dette i CI.yml istedenfor workflow_dispatch. 

#### Oppgave 2



![branch](https://user-images.githubusercontent.com/72222999/204315102-14fa75f3-2027-40a8-95c9-8b78f333b758.PNG)
For å kunne beskytte arbeidsprossesen er det lurt å sette opp branch-protection. Dette vil gjøre at vi kan legge til noen regler for å begrene hva som blir pushet opp til main. For å kunne gjøre det må du først gå inn på settings -> Branches -> Add Branch Protection Rule. 


![2](https://user-images.githubusercontent.com/72222999/204315098-1f935b99-45d6-4a08-aa7a-c1875a6dae09.PNG)

Her krysset jeg av på "Require a pull request before merging" som gjør at alle commits som blir gjort må bli akseptert med en pull request før de blir merget inn i main. 
Med dette kan ikke en singel person ødelegge hele applikasjonen. Jeg la også til "Require Approval" som bare sier at noen må godkjenne commiten. 

Litt lenger ned på siden finner du Require status checks to pass before merging, jeg aktiverte den nedenfor som heter Require branches to be up to date before merging. Der valgte jeg "build" som er en jobb i ci.yml som sørge for at mvn bygger applikasjonen med grønne tester. Om noen prøver å pushe uten at noen tester eller applikasjone ikke vil bygge, så vil denne reglen stoppe commiten av å komme inn i main. 

### Oppgave 3


### DOCKER

## Oppgave 1

Grunnen til at docker feiler for meg er at jeg ikke har ritkig secrets som de har på docker.yml. Når jeg la til docker brukernavnet mitt som DOCKER_HUB_USERNAME og passordet mitt som DOCKER_HUB_TOKEN kjørte docker jobben grønt. Det skal også sies at denne jobben gjør containeren deres public for alle til å kjøre.

## Oppgave 2
Først må jeg endre på dockerfilen slik at den bygger en container som har alle verktøyene applikasjon trenger for å kjøre, derfor må jeg også kopiere med pom.xml filen og kjøre mvn package. sier jeg til dockerfilen at han skal bruke den ferdig lagde applikasjon som er en JAR fil.

Etter det måtte jeg endre på workflowen, jeg antok at oppgaven mente at alt skulle forstette å være public kun på docker. Derfor logget jeg inn på docker i yml og bygde en shopifly, når jeg nå skriver docker run thonems/shopifly laster den ned containeren og kjører den uten problemer. Med at docker skal bygge den trenger jeg ikke å ha med steps ved navn set up JDK 11, siden dockerfile sier hvilken jdk den skal bruke. Jeg fjernet også "with - context" hvor de sier hvor dockerfilen er og hva tagen skal hete. 
Jeg tolket oppgaven som at containeren skulle vært i java jdk 11 og ikke 8, derfor valgte jeg 11 i dockerfilen hvor du kan velge hvilken jdk du trenger. Vitkigste er at alt kjører!

## Oppgave 3

bildet av ECR!
Jeg opprettet et repository ved navn 1022 som er kandidatnr. Her blir det autmoatisk lastet opp fra github actions hvor navnet er git commit iden. I bilde er det 2 andre tags som heter latest og untagget, som er fra litt tildigere når jeg prøvde ut. I oppgaven stod det bare at den skulle ha tag til commit id.

### MICROMETER


### Terraform and Cloudwatch

## Oppgave 1

Problemene til gaffel kommer fra at jim allerede har opprettet en s3 bucket med terraform fra før. Når terraform oppretterer noe i miljøet vil den lage en state-fil hvor den holder kontroll på hva som har blitt laget eller slettet. Når folkene i gaffel har kjørt "terraform apply" ble de filene opprettet. Om state filene ikke hadde blitt slettet ville terraform visst hva den skulle gjøre, men nå vet den ikke hva som er i miljøet, derfor prøver den å lage s3 bucketen på nytt. De har mest sannsynligvis slettet denne bucketen fra før, men det vet ikke terraform uten statefiles.
