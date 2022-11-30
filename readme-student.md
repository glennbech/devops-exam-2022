
# Devops Exam
## Del 1 DevOps-prinsipper




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
