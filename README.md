# Devoir_Microservice_API
# 🚀Prérequis
    Java 17 
    Docker/ Docker desktop
    Spring boot
    maven
    maria_db
# 📁Structure du Projet

    Examen_Microservice_API/
    ├── 🔍 eureka-server/         # Service de découverte (port 8761)
    ├── 📦 api-gateway/           # Passerelle API (port 8080)
    ├── 🏢 department-service/    # Gestion des départements (port 8081)
    ├── 👥 team-service/          # Gestion des équipes (port 8082)
    ├── 🐳 docker-compose.yml     # Orchestration des services
    ├── 📄 README.md              # Documentation
# ✨Fonctionnalités

    🏢 Gestion des Départements
    - CRUD complet : Création, lecture, mise à jour et suppression des départements
    - Recherche : Recherche de départements par nom
    - Validation : Contrôle de l'unicité des noms de départements
    - Intégrité : Protection contre la suppression de départements contenant des équipes

    👥 Gestion des Équipes
    - CRUD complet : Création, lecture, mise à jour et suppression des équipes
    - Association : Liaison automatique avec les départements
    - Recherche : Recherche d'équipes par nom et par département
    - Validation : Vérification de l'existence du département parent

    🔍 Fonctionnalités Avancées
    - API REST : Endpoints RESTful complets avec codes de statut HTTP appropriés
    - Validation des données : Validation automatique des entrées utilisateur
    - Gestion d'erreurs : Messages d'erreur détaillés et personnalisés
    - Base de données : Support H2 en mémoire pour le développement
    - Documentation : Collection Postman 
# 🧪Test
Lancer le fichier start-microservices.bat

<img width="1475" height="368" alt="Capture d'écran 2025-08-11 143253" src="https://github.com/user-attachments/assets/d0341de5-70ad-49f8-bd5f-decdcdac0cca" />

_____________________________________________________________________

<img width="1472" height="337" alt="Capture d'écran 2025-08-11 143529" src="https://github.com/user-attachments/assets/2dee5da9-e1d5-4830-a272-81ecf0800cc1" />

_____________________________________________________________________

Ou faites

    docker-compose up --build

<img width="1589" height="546" alt="Capture d'écran 2025-08-11 143721" src="https://github.com/user-attachments/assets/62ed517e-6ad1-4cc9-84f5-b775b0c553bb" />

_____________________________________________________________________

# 🧪Test avec Postman
Copier et coller le fichier json Microservices_Postman_Collection.json pour importer la collection

________________________________________________________________________

<img width="1492" height="800" alt="Capture d'écran 2025-08-11 144118" src="https://github.com/user-attachments/assets/5fa88e9a-2443-4759-8d7e-033f466901f5" />

________________________________________________________________________

Quelques résultats attendus

<img width="1481" height="642" alt="Capture d'écran 2025-08-11 144245" src="https://github.com/user-attachments/assets/06c79c5c-b039-491a-ae92-92fd25800256" />

________________________________________________________________________

<img width="1499" height="745" alt="Capture d'écran 2025-08-11 144503" src="https://github.com/user-attachments/assets/bea226ea-9586-4d5f-8e16-9ea674887065" />

________________________________________________________________________

<img width="1493" height="716" alt="Capture d'écran 2025-08-11 144643" src="https://github.com/user-attachments/assets/f02d2662-4b1b-4d14-b28c-7d1beeb3af1c" />

________________________________________________________________________

<img width="1495" height="791" alt="Capture d'écran 2025-08-11 151744" src="https://github.com/user-attachments/assets/3c0584dd-3e4b-411c-adae-ac5be2bedde9" />




