# Template de projet pour le TP JPA 2021 UniR

**TODO**
- Ajouter profs au git

**Pour le rapport** :
- ajouter l'archi UML
- description rapide des classes et des choix spécifiques
- description de l'API (sauf si swagger bien annoté)
- exemple de requête (ou fournir export de la confid postman)
- si front, expliquer comment le lancer et l'utiliser
- config KeyClock

*RAPPORT A RENDRE LE : 09/10*

---

Connect to DB :
jdbc:hsqldb:hsql://localhost/

---

Diagram (work in progress) :

```mermaid
classDiagram
    Patient "1" <--> "0..*" Appointment  
    Appointment "0..*" <--> "1" Doctor
    Doctor "*" *-- "1" Specialisation
    Person <|-- Patient
    Person <|-- Doctor

    class Person {
       String firstName    
       String lastName
    }

    class Patient {
        Long id
        Long numSS
    }

    class Specialisation {
        Long id
        String name
    }

    class Doctor {
        Long id
    }

    class Appointment {
        Long id
        Date date
    }

```

Edited on Mermaid :
[Mermaid Doc](https://mermaid-js.github.io/mermaid/#/classDiagram)  | 
[Mermaid Live Editor](https://mermaid.live)

---
