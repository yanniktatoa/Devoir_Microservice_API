# Devoir_Microservice_API
# Prérequis
    Java 17 
    Docker/ Docker desktop
    Spring boot
    maven
    maria_db
# Test
Lancer le fichier start-microservices.bat

<img width="1475" height="368" alt="Capture d'écran 2025-08-11 143253" src="https://github.com/user-attachments/assets/d0341de5-70ad-49f8-bd5f-decdcdac0cca" />

_____________________________________________________________________

<img width="1472" height="337" alt="Capture d'écran 2025-08-11 143529" src="https://github.com/user-attachments/assets/2dee5da9-e1d5-4830-a272-81ecf0800cc1" />

_____________________________________________________________________

Ou faites

    docker-compose up --build

<img width="1589" height="546" alt="Capture d'écran 2025-08-11 143721" src="https://github.com/user-attachments/assets/62ed517e-6ad1-4cc9-84f5-b775b0c553bb" />

_____________________________________________________________________

# Test avec Postman
Copier et coller ce json file pour importer une collection de test des routes de l'Api

    {
      "info": {
        "name": "Microservices Catalogue API",
        "description": "Collection pour tester les microservices Department et Team",
        "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
      },
      "item": [
        {
          "name": "Department Service",
          "item": [
            {
              "name": "Get All Departments",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "http://localhost:8080/api/departments",
                  "protocol": "http",
                  "host": ["localhost"],
                  "port": "8080",
                  "path": ["api", "departments"]
                }
              }
            },
            {
              "name": "Get Department by ID",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "http://localhost:8080/api/departments/1",
                  "protocol": "http",
                  "host": ["localhost"],
                  "port": "8080",
                  "path": ["api", "departments", "1"]
                }
              }
            },
            {
              "name": "Create Department",
              "request": {
                "method": "POST",
                "header": [
                  {
                    "key": "Content-Type",
                    "value": "application/json"
                  }
                ],
                "body": {
                  "mode": "raw",
                  "raw": "{\n  \"name\": \"Développement\"\n}"
                },
                "url": {
                  "raw": "http://localhost:8080/api/departments",
                  "protocol": "http",
                  "host": ["localhost"],
                  "port": "8080",
                  "path": ["api", "departments"]
                }
              }
            },
            {
              "name": "Update Department",
              "request": {
                "method": "PUT",
                "header": [
                  {
                    "key": "Content-Type",
                    "value": "application/json"
                  }
                ],
                "body": {
                  "mode": "raw",
                  "raw": "{\n  \"name\": \"Développement Logiciel\"\n}"
                },
                "url": {
                  "raw": "http://localhost:8080/api/departments/1",
                  "protocol": "http",
                  "host": ["localhost"],
                  "port": "8080",
                  "path": ["api", "departments", "1"]
                }
              }
            },
            {
              "name": "Delete Department",
              "request": {
                "method": "DELETE",
                "header": [],
                "url": {
                  "raw": "http://localhost:8080/api/departments/1",
                  "protocol": "http",
                  "host": ["localhost"],
                  "port": "8080",
                  "path": ["api", "departments", "1"]
                }
              }
            },
            {
              "name": "Search Departments",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "http://localhost:8080/api/departments/search?name=développement",
                  "protocol": "http",
                  "host": ["localhost"],
                  "port": "8080",
                  "path": ["api", "departments", "search"],
                  "query": [
                    {
                      "key": "name",
                      "value": "développement"
                    }
                  ]
                }
              }
            },
            {
              "name": "Count Departments",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "http://localhost:8080/api/departments/count",
                  "protocol": "http",
                  "host": ["localhost"],
                  "port": "8080",
                  "path": ["api", "departments", "count"]
                }
              }
            }
          ]
        },
        {
          "name": "Team Service",
          "item": [
            {
              "name": "Get All Teams",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "http://localhost:8080/api/teams",
                  "protocol": "http",
                  "host": ["localhost"],
                  "port": "8080",
                  "path": ["api", "teams"]
                }
              }
            },
            {
              "name": "Get Team by ID",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "http://localhost:8080/api/teams/1",
                  "protocol": "http",
                  "host": ["localhost"],
                  "port": "8080",
                  "path": ["api", "teams", "1"]
                }
              }
            },
            {
              "name": "Create Team",
              "request": {
                "method": "POST",
                "header": [
                  {
                    "key": "Content-Type",
                    "value": "application/json"
                  }
                ],
                "body": {
                  "mode": "raw",
                  "raw": "{\n  \"teamName\": \"Équipe Frontend\",\n  \"description\": \"Équipe spécialisée dans le développement frontend\",\n  \"departmentId\": 1\n}"
                },
                "url": {
                  "raw": "http://localhost:8080/api/teams",
                  "protocol": "http",
                  "host": ["localhost"],
                  "port": "8080",
                  "path": ["api", "teams"]
                }
              }
            },
            {
              "name": "Update Team",
              "request": {
                "method": "PUT",
                "header": [
                  {
                    "key": "Content-Type",
                    "value": "application/json"
                  }
                ],
                "body": {
                  "mode": "raw",
                  "raw": "{\n  \"teamName\": \"Équipe Frontend React\",\n  \"description\": \"Équipe spécialisée dans React et technologies modernes\",\n  \"departmentId\": 1\n}"
                },
                "url": {
                  "raw": "http://localhost:8080/api/teams/1",
                  "protocol": "http",
                  "host": ["localhost"],
                  "port": "8080",
                  "path": ["api", "teams", "1"]
                }
              }
            },
            {
              "name": "Delete Team",
              "request": {
                "method": "DELETE",
                "header": [],
                "url": {
                  "raw": "http://localhost:8080/api/teams/1",
                  "protocol": "http",
                  "host": ["localhost"],
                  "port": "8080",
                  "path": ["api", "teams", "1"]
                }
              }
            },
            {
              "name": "Get Teams by Department",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "http://localhost:8080/api/teams/by-department/1",
                  "protocol": "http",
                  "host": ["localhost"],
                  "port": "8080",
                  "path": ["api", "teams", "by-department", "1"]
                }
              }
            },
            {
              "name": "Search Teams",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "http://localhost:8080/api/teams/search?name=frontend",
                  "protocol": "http",
                  "host": ["localhost"],
                  "port": "8080",
                  "path": ["api", "teams", "search"],
                  "query": [
                    {
                      "key": "name",
                      "value": "frontend"
                    }
                  ]
                }
              }
            },
            {
              "name": "Count Teams",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "http://localhost:8080/api/teams/count",
                  "protocol": "http",
                  "host": ["localhost"],
                  "port": "8080",
                  "path": ["api", "teams", "count"]
                }
              }
            },
            {
              "name": "Count Teams by Department",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "http://localhost:8080/api/teams/count/by-department/1",
                  "protocol": "http",
                  "host": ["localhost"],
                  "port": "8080",
                  "path": ["api", "teams", "count", "by-department", "1"]
                }
              }
            }
          ]
        },
        {
          "name": "Direct Service Access",
          "item": [
            {
              "name": "Department Service Direct",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "http://localhost:8081/api/departments",
                  "protocol": "http",
                  "host": ["localhost"],
                  "port": "8081",
                  "path": ["api", "departments"]
                }
              }
            },
            {
              "name": "Team Service Direct",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "http://localhost:8082/api/teams",
                  "protocol": "http",
                  "host": ["localhost"],
                  "port": "8082",
                  "path": ["api", "teams"]
                }
              }
            }
          ]
        },
        {
          "name": "Eureka Server",
          "item": [
            {
              "name": "Eureka Dashboard",
              "request": {
                "method": "GET",
                "header": [],
                "url": {
                  "raw": "http://localhost:8761",
                  "protocol": "http",
                  "host": ["localhost"],
                  "port": "8761",
                  "path": [""]
                }
              }
            }
          ]
        }
      ],
      "variable": [
        {
          "key": "base_url",
          "value": "http://localhost:8080",
          "type": "string"
        },
        {
          "key": "department_service_url",
          "value": "http://localhost:8081",
          "type": "string"
        },
        {
          "key": "team_service_url",
          "value": "http://localhost:8082",
          "type": "string"
        },
        {
          "key": "eureka_url",
          "value": "http://localhost:8761",
          "type": "string"
        }
      ]
    }

________________________________________________________________________

<img width="1492" height="800" alt="Capture d'écran 2025-08-11 144118" src="https://github.com/user-attachments/assets/5fa88e9a-2443-4759-8d7e-033f466901f5" />

________________________________________________________________________

Quelques résultats attendus

<img width="1481" height="642" alt="Capture d'écran 2025-08-11 144245" src="https://github.com/user-attachments/assets/06c79c5c-b039-491a-ae92-92fd25800256" />

________________________________________________________________________

<img width="1499" height="745" alt="Capture d'écran 2025-08-11 144503" src="https://github.com/user-attachments/assets/bea226ea-9586-4d5f-8e16-9ea674887065" />

________________________________________________________________________

<img width="1493" height="716" alt="Capture d'écran 2025-08-11 144643" src="https://github.com/user-attachments/assets/f02d2662-4b1b-4d14-b28c-7d1beeb3af1c" />


