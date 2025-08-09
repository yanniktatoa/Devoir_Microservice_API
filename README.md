# Devoir_Microservice_API
# Prérequis
    Java 17 
    Docker 
    maven
    maria_db
# Test
Lancer le fichier start.bat

<img width="1483" height="521" alt="Capture d'écran 2025-08-09 145710" src="https://github.com/user-attachments/assets/ad7b3b1d-4b8e-4172-a34e-044e8f901e7f" />

_____________________________________________________________________

<img width="1463" height="169" alt="Capture d'écran 2025-08-09 145752" src="https://github.com/user-attachments/assets/06f8d0ec-bbe5-44c4-b6d7-a4397c223bcd" />

_____________________________________________________________________

Ou faites

    mvn spring-boot:run

<img width="1463" height="216" alt="Capture d'écran 2025-08-09 151001" src="https://github.com/user-attachments/assets/207b5047-901e-409a-9cf6-5b6fe94a8fe0" />

_____________________________________________________________________

# Test avec Postman
Copier et coller ce json file pour importer une collection de test des routes de l'Api

    {
    "info": {
      "name": "Catalogue Entreprise API",
      "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
    },
    "item": [
      {
        "name": "Départements",
        "item": [
          {
            "name": "Liste tous les départements",
            "request": {
              "method": "GET",
              "url": "http://localhost:8080/api/departments"
            }
          },
          {
            "name": "Créer un département",
            "request": {
              "method": "POST",
              "url": "http://localhost:8080/api/departments",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n  \"name\": \"Nouveau Département\"\n}"
              }
            }
          },
          {
            "name": "Obtenir un département",
            "request": {
              "method": "GET",
              "url": "http://localhost:8080/api/departments/1"
            }
          },
          {
            "name": "Modifier un département",
            "request": {
              "method": "PUT",
              "url": "http://localhost:8080/api/departments/1",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n  \"name\": \"Département Modifié\"\n}"
              }
            }
          },
          {
            "name": "Supprimer un département",
            "request": {
              "method": "DELETE",
              "url": "http://localhost:8080/api/departments/1"
            }
          }
        ]
      },
      {
        "name": "Équipes",
        "item": [
          {
            "name": "Liste toutes les équipes",
            "request": {
              "method": "GET",
              "url": "http://localhost:8080/api/teams"
            }
          },
          {
            "name": "Créer une équipe",
            "request": {
              "method": "POST",
              "url": "http://localhost:8080/api/teams",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n  \"teamName\": \"Nouvelle Équipe\",\n  \"description\": \"Description de la nouvelle équipe\",\n  \"departmentId\": 1\n}"
              }
            }
          },
          {
            "name": "Obtenir une équipe",
            "request": {
              "method": "GET",
              "url": "http://localhost:8080/api/teams/1"
            }
          },
          {
            "name": "Équipes d'un département",
            "request": {
              "method": "GET",
              "url": "http://localhost:8080/api/teams/by-department/1"
            }
          },
          {
            "name": "Modifier une équipe",
            "request": {
              "method": "PUT",
              "url": "http://localhost:8080/api/teams/1",
              "header": [
                {
                  "key": "Content-Type",
                  "value": "application/json"
                }
              ],
              "body": {
                "mode": "raw",
                "raw": "{\n  \"teamName\": \"Équipe Modifiée\",\n  \"description\": \"Description modifiée\",\n  \"departmentId\": 1\n}"
              }
            }
          },
          {
            "name": "Supprimer une équipe",
            "request": {
              "method": "DELETE",
              "url": "http://localhost:8080/api/teams/1"
            }
          }
      ]
    }
    ]
    }

________________________________________________________________________

<img width="1498" height="493" alt="Capture d'écran 2025-08-09 152405" src="https://github.com/user-attachments/assets/43e45168-5784-4f2a-be58-d15712ce9df1" />

________________________________________________________________________

Quelques résultats attendus

<img width="1485" height="608" alt="Capture d'écran 2025-08-09 152651" src="https://github.com/user-attachments/assets/cb193b92-a068-40ff-9082-e67ad48eede1" />

________________________________________________________________________

<img width="1452" height="497" alt="Capture d'écran 2025-08-09 152844" src="https://github.com/user-attachments/assets/5355b033-7a5a-4693-a79a-19a70012eecd" />

________________________________________________________________________

<img width="1475" height="691" alt="Capture d'écran 2025-08-09 152951" src="https://github.com/user-attachments/assets/9b2ebb5e-a6c5-469e-861c-6856df7e659d" />


