@echo off
echo ========================================
echo   Catalogue Entreprise Microservice
echo ========================================
echo.
echo Configuration MariaDB :
echo - Hôte : localhost:3306
echo - Base de données : microservice_db
echo - Utilisateur : root
echo.
echo Démarrage de l'application...
echo.
echo L'application sera accessible à :
echo - API REST : http://localhost:8080
echo - Monitoring : http://localhost:8080/actuator/health
echo - Logs : logs/catalogue-app.log
echo.
echo Appuyez sur Ctrl+C pour arrêter l'application
echo.
mvn spring-boot:run
pause 