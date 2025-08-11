@echo off
echo ========================================
echo   DEMARRAGE DES MICROSERVICES
echo ========================================
echo.

echo 1. Verification de Docker...
docker --version >nul 2>&1
if errorlevel 1 (
    echo ERREUR: Docker n'est pas installe ou n'est pas accessible
    echo Veuillez installer Docker Desktop et le demarrer
    pause
    exit /b 1
)

echo 2. Verification de Docker Compose...
docker-compose --version >nul 2>&1
if errorlevel 1 (
    echo ERREUR: Docker Compose n'est pas accessible
    pause
    exit /b 1
)

echo 3. Arret des conteneurs existants...
docker-compose down

echo 4. Nettoyage des images...
docker-compose down --rmi all

echo 5. Compilation des services...
echo.

echo Compilation du serveur Eureka...
cd eureka-server
call mvn clean package -DskipTests
if errorlevel 1 (
    echo ERREUR: Compilation du serveur Eureka echouee
    pause
    exit /b 1
)
cd ..

echo Compilation du service Department...
cd department-service
call mvn clean package -DskipTests
if errorlevel 1 (
    echo ERREUR: Compilation du service Department echouee
    pause
    exit /b 1
)
cd ..

echo Compilation du service Team...
cd team-service
call mvn clean package -DskipTests
if errorlevel 1 (
    echo ERREUR: Compilation du service Team echouee
    pause
    exit /b 1
)
cd ..

echo Compilation de l'API Gateway...
cd api-gateway
call mvn clean package -DskipTests
if errorlevel 1 (
    echo ERREUR: Compilation de l'API Gateway echouee
    pause
    exit /b 1
)
cd ..

echo.
echo 6. Demarrage avec Docker Compose...
echo.

docker-compose up --build

echo.
echo ========================================
echo   MICROSERVICES DEMARRES
echo ========================================
echo.
echo URLs d'acces:
echo - Eureka Server: http://localhost:8761
echo - API Gateway: http://localhost:9090
echo - Department Service: http://localhost:8081
echo - Team Service: http://localhost:8082
echo.
echo Base de donnees MariaDB: localhost:3306
echo.
pause 