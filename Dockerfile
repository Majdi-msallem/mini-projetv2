# Utilise une image de base OpenJDK 17 avec Alpine Linux
FROM openjdk:17-alpine

# Crée un répertoire pour l'application
RUN mkdir /app

# Copie le fichier JAR de l'application dans le répertoire /app dans l'image Docker
COPY target/mini-projet-0.0.1-SNAPSHOT.jar /app/mini-projet.jar

# Indique que l'application va écouter sur le port 8080
EXPOSE 8080

# Commande pour exécuter l'application Spring Boot lorsque le conteneur Docker démarre
CMD ["java", "-jar", "/app/mini-projet.jar"]
