#Sujet (un doctolib pour voir les profs)

On souhaite développer l'application suivante de type prise de RDV pour tout professionnel. L’idée est de pouvoir connecter l’application à un agenda externe (zimbra, google) pour identifier les plages disponibles pour un utilisateur donné afin de pouvoir prendre un RDV. Chaque professionnel de l’application pourra se créer un compte, se loguer, mettre à disposition l’url de récupération des créneaux disponibles, définir la durée nominal d’un RDV et les intitulés possibles d’un RDV. 
Un utilisateur souhaitant consulter un professionnel pourra aussi se créer un compte, se loguer et consulter la liste des créneaux disponibles et réserver un créneau.


*TP 1 Mapping Relationnel*

Q1:
Diagramme UML - 

Par rapport au sujet de l'énoncé, nous avions identifier 3 classes métiers principales à développer : 

- Classe Utilisateur
-> Consulter la liste des RDV (collection de RDV)
-> Réserver un RDV
-> Consulter la liste des professionnels

Classe Professionnel
-> Définir la durée des RDV
-> Définir l'intutilé des RDV
-> Mettre à disposition l'url de récupération des créneaux disponibles 

- Classe RDV
-> Intitulé du RDV
-> Durée du RDV (date de début et date de fin) 

Q2 : 

Pour démarrer, nous avons créer un premier Package Domain contenant les classes suivantes :
- Classe Departement
- Classe Profesionnel
- Classe Rdv
- Classe Utilisateur

Ces 4 classes sont annotés @Entity afin de permettre la création de notre base de données relationnelles.

Q3 :

Nous avons créer le package dao afin de pouvoir créer et consulter des données dans la base de données relationnelles.
Nous avons créer 4 classes : 
- Classe DepartementDao
- Classe ProfessionnelDao
- Classe UtilisateurDao
- Classe RdvDao

Ces classes contiennent les requêtes qui permettent d'interagir avec la base de données.

Q4 : 
Test de liaison à une base de données sql. Pour cela il faut modifier le fichier persistance.xml.
Nous l'avons modifier avec nos données afin de se connecter à notre base sql.
Pour accéder à la base de données mysql (il faut être sur le réseau de ISTIC):
base = base_sgadrey
login = user_sgadrey
mdp = puccini

Q5 : 
Afin de travailler sur l'héritage des classes, nous avons créé la classe Personne. Nous avons créer la classe abstraite Personne dans le package domain puis nous avons créé la classe PersonneDao dans le package dao afin d'utiliser la table associée dans notre base de données relationnelles.

Nous avons également travailler sur les requêtes faites via JPA, nous avons créer une @namedQuery dans la classe Professionnel et nous l'avons utilisé dans la classe ProfessionnelDao pour lister tous les profesionnels par nom.

-> Problème de l'injection de dépendances - en utilisant l'héritage, on a crée une dépendance entre notre classe Personne et nos classes filles Utilisateur et Professionnel.
