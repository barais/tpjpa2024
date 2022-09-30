# Template de projet pour le TP JPA 2021 UniR

**TODO**
- Ajouter profs au git

**Pour le rapport** :
- ajouter l'archi UML
- description rapide des classes et des choix spécifiques
- description de l'API (sauf si swagger bien annoté)
- exemple de requête (ou fournir export de la config postman)
- si front, expliquer comment le lancer et l'utiliser
- config KeyClock
- config ResponseEntity pour chaque methode du controller

*RAPPORT A RENDRE LE : 09/10*

---

Connect to DB :
jdbc:hsqldb:hsql://localhost/

---

Access the API documentation :
http://localhost:8080/swagger-ui.html

---

Diagram (work in progress) :

```mermaid
classDiagram
    Patient "1" <--> "0..*" TimeSlot  
    TimeSlot "0..*" <--> "1" Doctor
    Doctor "*" *-- "1" Specialisation
    Person <|-- Patient
    Person <|-- Doctor

    class Person {
       Long id
       String firstName    
       String lastName
    }

    class Patient {
        Long numSS
    }

    class Specialisation {
        <<enumeration>>
        DENTIST
        NEUROLOGIST
        GENERALIST
        CARDIOLOGIST
    }

    class Doctor {
        Specialisation specialisation
    }

    class TimeSlot {
        Long id
        Date date
        Doctor doctor
        Patient patient
    }
```

Edited on Mermaid :
[Mermaid Doc](https://mermaid-js.github.io/mermaid/#/classDiagram)  | 
[Mermaid Live Editor](https://mermaid.live)

---
