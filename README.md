# Template de projet pour le TP JPA 2021 UniR

**TODO**
- Ajouter profs au git

---

Diagram (work in progress) :

```mermaid
classDiagram
    Patient "1" <--> "0..*" Appointment  
    Appointment "0..*" <--> "1" Doctor
    Doctor "*" *-- "1" Specialization
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

    class Specialization {
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
