
## Configuration de la Base de Données

### Nomination des Entités
En raison d'un conflit de nommage avec les mots clés réservés SQL de la base de données H2, l'entité initialement nommée `Order` a été renommée en `OrderEntity`. Ce changement permet d'éviter les problèmes potentiels lors des opérations sur la base de données impliquant cette entité. Assurez-vous que toutes les requêtes ou interactions avec cette table utilisent le nom correct de l'entité `OrderEntity`.

## Construction et Lancement de l'Application

Suivez ces étapes pour construire et lancer l'application en utilisant Maven et Docker :

1. **Construction avec Maven** :
   Exécutez la commande suivante pour nettoyer le projet et construire les packages sans exécuter les tests :
   ```bash
   mvn clean install -DskipTests


2. **Construction des images Docker** :
   Construisez les images Docker nécessaires pour l'exécution du projet :
   ```bash
   docker-compose build

3. **Démarrage de l'application** :
   Lancez l'application en utilisant Docker Compose :
   ```bash
   docker-compose up


## Étapes de Test avec Postman

Pour tester l'application, utilisez les étapes suivantes dans Postman :

1. **Créer un nouveau magasin** :
    - Méthode : POST
    - URL : `http://localhost:8080/stores`
    - Body :
      ```json
      {
        "name": "Magasin 1"
      }
      ```

2. **Créer un deuxième magasin** :
    - Méthode : POST
    - URL : `http://localhost:8080/stores`
    - Body :
      ```json
      {
        "name": "Magasin 2"
      }
      ```

3. **Créer un troisième magasin** :
    - Méthode : POST
    - URL : `http://localhost:8080/stores`
    - Body :
      ```json
      {
        "name": "Magasin 3"
      }
      ```

4. **Créer un produit pour le premier magasin** :
    - Méthode : POST
    - URL : `http://localhost:8080/products`
    - Body :
      ```json
      {
        "name": "Produit 1",
        "description": "Description 1",
        "price": 100.0,
        "storeId": 1
      }
      ```

5. **Créer un deuxième produit pour le premier magasin** :
    - Méthode : POST
    - URL : `http://localhost:8080/products`
    - Body :
      ```json
      {
        "name": "Produit 2",
        "description": "Description 2",
        "price": 150.0,
        "storeId": 1
      }
      ```

6. **Créer un produit pour le deuxième magasin** :
    - Méthode : POST
    - URL : `http://localhost:8080/products`
    - Body :
      ```json
      {
        "name": "Produit 3",
        "description": "Description 3",
        "price": 200.0,
        "storeId": 2
      }
      ```

7. **Créer un deuxième produit pour le deuxième magasin** :
    - Méthode : POST
    - URL : `http://localhost:8080/products`
    - Body :
      ```json
      {
        "name": "Produit 4",
        "description": "Description 4",
        "price": 250.0,
        "storeId": 2
      }
      ```

8. **Créer un produit pour le troisième magasin** :
    - Méthode : POST
    - URL : `http://localhost:8080/products`
    - Body :
      ```json
      {
        "name": "Produit 5",
        "description": "Description 5",
        "price": 300.0,
        "storeId": 3
      }
      ```

9. **Créer un deuxième produit pour le troisième magasin** :
    - Méthode : POST
    - URL : `http://localhost:8080/products`
    - Body :
      ```json
      {
        "name": "Produit 6",
        "description": "Description 6",
        "price": 350.0,
        "storeId": 3
      }
      ```

10. **Créer une commande pour un produit spécifique** :
    - Méthode : POST
    - URL : `http://localhost:8080/orders`
    - Body :
      ```json
      {
        "productId": 1,
        "quantity": 10
      }
      ```

11. **Créer une deuxième commande pour un autre produit** :
    - Méthode : POST
    - URL : `http://localhost:8080/orders`
    - Body :
      ```json
      {
        "productId": 2,
        "quantity": 5
      }
      ```

12. **Récupérer la liste de tous les produits** :
    - Méthode : GET
    - URL : `http://localhost:8080/products`

13. **Récupérer la liste de toutes les commandes** :
    - Méthode : GET
    - URL : `http://localhost:8080/orders`

14. **Récupérer la liste de tous les magasins** :
    - Méthode : GET
    - URL : `http://localhost:8080/stores`

Suivez ces étapes pour tester les fonctionnalités principales de l'application.




## Visualisation des Messages Kafka

Pour visualiser les messages Kafka, suivez ces étapes :

1. **Lister les conteneurs Docker** :
   Utilisez cette commande pour afficher tous les conteneurs en cours d'exécution. Notez le nom ou l'ID du conteneur Kafka :
   ```bash
   docker ps

2. **Accéder au conteneur Kafka** :
   Remplacez mini-projet-kafka-1 par le nom correct de votre conteneur Kafka, trouvé à l'étape précédente :   ```bash
    ```bash
   docker exec -it mini-projet-kafka-1 /bin/bash


3. **Lire les messages du topic Kafka** :
   Utilisez la commande suivante pour lire les messages du topic test depuis le début :
   ```bash
   kafka-console-consumer --bootstrap-server kafka:9092 --topic mini-projet --from-beginning

Cette section vous permet de vérifier que les messages sont correctement produits et consommés à travers le topic spécifié dans votre cluster Kafka.



## Monitoring avec Actuator

Actuator de Spring Boot est utilisé pour exposer des métriques et surveiller l'état de l'application. Les endpoints Actuator suivants sont disponibles pour vous aider à comprendre et à surveiller le comportement de votre application en production :

- **Health Check** :
  Vérifiez l'état de santé de l'application en accédant à :
```
/actuator/health
```
Ce endpoint retourne l'état de santé de l'application, ce qui peut aider à identifier rapidement si l'application est opérationnelle ou si des problèmes doivent être résolus.


- **Métriques de l'Application** :
  Obtenez des métriques détaillées de l'application en accédant à :
```
/actuator/metrics
```
Ce endpoint expose diverses métriques qui peuvent être utilisées pour surveiller l'utilisation des ressources, les performances, et plus encore.



## Accès à Swagger UI

Swagger est utilisé pour documenter l'API de l'application et fournir une interface utilisateur interactive pour tester les endpoints API directement depuis votre navigateur.

Lorsque vous démarrez votre application, Swagger UI sera accessible via l'URL suivante :

```
http://{basUrl}/swagger-ui.html
```



## Tests de Performance avec Gatling

Les tests de performance de l'application sont réalisés en utilisant Gatling, un outil puissant pour tester la charge et la performance des applications web.

### Accéder aux Tests de Performance

Les scripts de test Gatling sont stockés dans le dossier suivant :
```
gatling-maven-plugin-demo-java-main
```

### Exécuter les Tests de Performance

Pour lancer les tests de performance, naviguez dans le dossier contenant les scripts Gatling et exécutez les tests via Maven en utilisant la commande suivante :

```bash
mvn gatling:test
```

### Résultats des Tests
Après l'exécution des tests, les résultats seront disponibles sous forme de rapports détaillés dans le répertoire
```
target/gatling. 
```
Ces rapports incluent des graphiques et des métriques détaillées sur la performance et la charge supportée par l'application pendant les tests.

Cela vous permettra de mesurer l'efficacité et la robustesse de votre application sous différentes charges, en identifiant les goulets d'étranglement et les performances maximales soutenables.


