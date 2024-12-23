
# Projet Vélos Libre Service

## Présentation

Le projet **Vélos Libre Service** est une simulation d’un système de location de vélos en libre-service, inspiré des dispositifs tels que les Vélib ou V’Lille. Ce projet a été développé dans le cadre de l’UE **Conception Orientée Objet**, une UE de L3 Informatique l’Université de Lille, durant l’année universitaire 2024-2025. 

## Rubrique HowTo

### Récupérer les sources du projet  
   
```
git clone https://github.com/sidahmedf/velib/
```

### Génération de la documentation : 

```
javadoc -sourcepath src -subpackages project -d docs
```

La documentation se trouve dans le dossier `./docs`

### Compilation des sources : 

```
javac -sourcepath src src/project/Main.java -d classes
```

### Compilation des test (différent selon windows ou linux)

#### Pour windows
```
javac -classpath "classes;junit-console.jar" test/project/controlCenterTest/*.java \test/project/decoratorTest/bikeDecoratorTest/*.java \test/project/decoratorTest/scooterDecoratorTest/*.java \test/project/roleTest/*.java \test/project/stationTest/*.java \test/project/strategyTest/*.java \test/project/vehiculeTest/*.java \test/project/vehiculeTest/bikeTest/*.java \test/project/vehiculeTest/electricalScooterTest/*.java 
```
#### Pour linux 
```
javac -classpath classes:junit-console.jar test/project/controlCenterTest/*.java \test/project/decoratorTest/bikeDecoratorTest/*.java \test/project/decoratorTest/scooterDecoratorTest/*.java \test/project/roleTest/*.java \test/project/stationTest/*.java \test/project/strategyTest/*.java \test/project/vehiculeTest/*.java \test/project/vehiculeTest/bikeTest/*.java \test/project/vehiculeTest/electricalScooterTest/*.java 
```

### Exécution des tests (différent selon windows ou linux)

#### Pour windows

```
java -jar junit-console.jar -classpath "test;classes" -scan-classpath
```

#### Pour linux 

```
java -jar junit-console.jar -classpath test:classes -scan-classpath
```

### Création du jar 

```
jar cvfe project.jar project.Main -C classes project
```

### Exécution du jar

```
java -jar project.jar
```
## Principaux éléments de conception

### Choix de conception  

#### Type `ControlCenter`
La classe **ControlCenter** représente le centre de contrôle du système, coordonnant les opérations entre les stations et supervisant les interactions principales entre les composants. Elle est responsable de la gestion du réseau de stations, notamment en maintenant une liste des stations, en gérant les stratégies de redistribution des véhicules entre les stations, et en recevant les notifications concernant les arrivées ou départs de véhicules.

#### Type `Station`
La classe **Station** représente une station de vélos en libre-service, et elle est chargée de gérer les véhicules disponibles ainsi que les interactions liées à leur prise ou dépôt. Parmi ses responsabilités, on trouve l'ajout et le retrait de véhicules tout en respectant les contraintes de capacité, la notification des changements d'état au centre de contrôle (arrivées ou départs), et la vérification des états de la station (pleine ou vide).

#### Type `Vehicule`
La classe **Vehicule** est une classe abstraite représentant un véhicule dans le système de location. Elle gère les informations de base sur le véhicule, telles que son identifiant, son poids, et son état de service (disponible ou hors service). Elle permet également de suivre l’utilisation du véhicule et son association avec une station spécifique. Les responsabilités de cette classe incluent la gestion des attributs du véhicule, l'interaction avec la station, et la gestion de son état. Cette classe est étendue par des classes spécifiques de véhicules, telles que des vélos ou d'autres types de véhicules, qui peuvent ajouter des fonctionnalités spécifiques.


#### Type `Human`
La classe **Human** est une classe abstraite représentant un rôle dans le système. Chaque rôle est caractérisé par un identifiant, un nom, et un véhicule associé. Cette classe permet de gérer l’interaction des rôles avec les stations, mais la méthode d’interaction est abstraite et doit être définie dans les sous-classes concrètes. Elle est responsable de la gestion du véhicule d’un rôle et de son interaction avec une station de vélos.

#### Type `RedistributionStrategy`
L'interface **RedistributionStrategy** définit une méthode pour la redistribution des vélos entre les stations. Elle permet de spécifier différentes stratégies de gestion des véhicules en fonction des besoins du système. La méthode `redistribute` prend en paramètre une liste de stations et peut lancer des exceptions si les stations sont pleines ou vides.

---
### Design patterns mis ou ouvre

#### Singleton dans `ControlCenter` et classes concrètes `DistributionStrategy`
Le design pattern **Singleton** a été utilisé dans ce projet pour garantir qu'il n'existe qu'une seule instance des classes **ControlCenter** et **RandomStrategy** (ou toute autre stratégie concrète) tout au long de l'exécution du programme. Cela est essentiel pour centraliser la gestion des stations et de la stratégie de redistribution des véhicules, afin d’assurer une gestion cohérente et sans duplications des ressources dans l'ensemble du système.

Le code pour implémenter le Singleton dans la classe **ControlCenter** pourrait ressembler à ceci :

```java
public class ControlCenter {
    private static ControlCenter INSTANCE = new ControlCenter;

    private ControlCenterSingleton() {
        // Constructeur privé pour éviter l'instanciation externe 
    }

    public static ControlCenter getInstance() {
        return INSTANCE;
    }
}
```

Le pattern Singleton est également appliqué à la classe **RandomStrategy**, qui implémente l'interface **RedistributionStrategy**. Le code pour implémenter le Singleton dans **RandomStrategy** est le suivant :

```java
public class RandomStrategy implements RedistributionStrategy {

    /** Private instance of RandomStrategy */
    private static final RandomStrategy INSTANCE = new RandomStrategy();

    /** Private constructor to prevent external instantiation */
    private RandomStrategy() {
        // Prevent instantiation
    }

    /** Public static method to get the instance of RandomStrategy */
    public static RandomStrategy getInstance() {
        return INSTANCE;
    }
}
```

L'utilisation du Singleton dans ces deux classes garantit qu'il n'y aura jamais qu'une seule instance de **ControlCenter** et **RandomStrategy** dans le système, permettant ainsi de maintenir une logique de gestion cohérente pour les stations et la stratégie de redistribution des vélos entre elles.


#### Observer entre `Station` et `ControlCenter`

Le **pattern Observer** est utilisé ici pour permettre à la classe `ControlCenter` de suivre l'état des stations et de réagir aux événements tels que l'arrivée ou le départ des véhicules. Dans ce cas, `Station` est l'observable et notifie son observateur, `ControlCenter`, chaque fois qu'un véhicule arrive ou part. Cela permet à `ControlCenter` de gérer les stations de manière cohérente sans avoir à interroger constamment leur état. Le code dans `ControlCenter` utilise la méthode `update` pour recevoir les notifications et réagir en affichant un message, tandis que dans la classe `Station`, la méthode `notifyObserver` est utilisée pour informer `ControlCenter` de l'arrivée ou du départ d'un véhicule.


Dans la classe `ControlCenter` :

```java
public void update(Station station, Vehicule vehicule, boolean isArrival) {
    String action = isArrival ? "arrived at" : "departed from";
    System.out.println("Vehicule " + vehicule.getId() + " has " + action + " station " + station.getId());
}
```
Dans la classe `Station` :
```java
private void notifyObserver(Vehicule vehicule, boolean isArrival) {
    observer.update(this, vehicule, isArrival); // Notifies the observer (ControlCenter)
}
```

#### Strategy dans `ControlCenter`
le **Pattern Strategy** est utilisé dans ControlCenter pour permettre une redistribution flexible des véhicules entre les stations. Cela permet de changer facilement la stratégie de redistribution sans modifier le code de la classe ControlCenter

Dans la classe `ControlCenter` :
```java
private RedistributionStrategy redStrategy;

public void setRedStrategy(RedistributionStrategy strategy){
    this.redStrategy = strategy;
}
```

Dans l'interface `RedistributionStrategy` :
```java
public interface RedistributionStrategy {
    public void redistribute(List<Station> stations) throws StationEmptyException, StationFullException;
}
```

#### Decorator dans `Vehicle`
le pattern **Decorator** est utilisé dans le projet pour ajouter dynamiquement des fonctionnalités supplémentaires à un objet Bike sans modifier sa structure de base. Cela permet de créer des objets Bike avec des caractéristiques variées (comme un panier ou un autre équipement) tout en gardant la flexibilité d'ajouter ou de retirer ces fonctionnalités au moment de l'exécution

Dans la classe `BikeDecorator` :
```java
protected Bike decoratedBike;

public BikeDecorator(Bike Decoratedbike) {
    super(Decoratedbike.getId(), Decoratedbike.getWeight(), Decoratedbike.getSeatHeight());
    this.decoratedBike = Decoratedbike;
}
public String getDescription() {
    return decoratedBike.getDescription() + decoratorDescription;
}
public int getWeight() {
    return decoratedBike.getWeight() + decoratorWeight;
}
```

#### Factory pour `VehicleTest` et `HumanTest`
Le pattern **Factory method** est utilisé dans ce projet pour créer des objets de manière flexible, en permettant de définir une méthode de création dans une classe de base (AbstractVehiculeTest), tout en laissant les classes dérivées définir la façon de créer des objets spécifiques (ElectricalBikeTest). Cela permet d'écrire des tests unitaires plus génériques et d'éviter la duplication de code.

Dans la classe `AbstractVehiculeTest` :
```java
public abstract Vehicule createVehicule();
```

Dans la classe `ElectricalBikeTest` :
```java
public Vehicule createVehicule(){ return new ElectricalBike(1,1);}
```

---

### Extensibilité du Projet

Le projet est conçu de manière à offrir une grande flexibilité et une extensibilité aisée, permettant l'ajout ou la suppression de fonctionnalités, de types de véhicules, de décorateurs, de stratégies de redistribution ou de types de métiers sans perturber la structure existante. Voici les principaux axes d'extensibilité du projet :

#### 1. **Ajout ou suppression de types de véhicules**
   
   L'architecture actuelle utilise un type commun **Vehicle** pour la création des véhicules. Cette approche permet d'ajouter facilement de nouveaux types de véhicules ou d'en supprimer. Par exemple, si un nouveau type de véhicule (comme une trottinette électrique) doit être ajouté, il suffira de :
   - Créer une nouvelle classe qui étend la classe abstraite `Vehicule` et implémente ses méthodes spécifiques.
   - Créer une nouvelle classe de test pour ce type de véhicule.
   - Ajouter une nouvelle implémentation de la méthode `createVehicule()` dans la classe correspondante de test (comme dans `ElectricalBikeTest`).
   
   De même, si un type de véhicule doit être supprimé, il suffira de retirer la classe correspondante et de mettre à jour les tests associés, sans affecter les autres parties du projet.

#### 2. **Appliquer plusieurs décorateurs à n'importe quel type de véhicule**
   
   Le projet intègre le pattern **Decorator**, qui permet d'ajouter dynamiquement des fonctionnalités supplémentaires à un véhicule sans en modifier la classe de base. Cela ouvre de nombreuses possibilités :
   
   - **Application de plusieurs décorateurs** : Vous pouvez appliquer plusieurs décorateurs sur un véhicule en enchaînant les objets décorateurs. Par exemple, un vélo électrique peut être décoré avec un `BasketDecorator` pour ajouter un panier, puis avec un `LightDecorator` pour ajouter une lumière.
   - **Ajout de nouveaux décorateurs** : Si de nouvelles fonctionnalités doivent être ajoutées (par exemple, un porte-bagages, une sonnette, ou une protection antivol), il suffit de créer un nouveau décorateur qui hérite de la classe `BikeDecorator` ou du décorateur de base de n'importe quel véhicule. Ce nouveau décorateur peut être ajouté au véhicule de manière transparente sans toucher au code existant.

   Cette modularité permet de combiner facilement de nouvelles fonctionnalités sur n'importe quel type de véhicule.

#### 3. **Ajout ou modification de stratégies de redistribution**

   Le projet peut être facilement étendu pour intégrer de nouvelles stratégies de redistribution ou modifier les algorithmes existants. Par exemple, si vous souhaitez changer la manière dont les véhicules sont redistribués entre les stations (par exemple, selon une autre logique de priorité ou de disponibilité), il est possible de :
   
   - **Ajouter de nouvelles stratégies** : Chaque stratégie de redistribution peut être modélisée comme une classe qui implémente une interface `RedistributionStrategy`. Il suffit de créer une nouvelle stratégie qui implémente cette interface, puis de l'intégrer dans le système en l'utilisant dans la gestion des stations ou des véhicules.
   - **Modifier les stratégies existantes** : L'algorithme de redistribution actuel peut être ajusté ou étendu pour tenir compte de nouveaux critères. Par exemple, la logique de priorité pour déplacer les vélos entre stations peut être ajustée pour inclure des paramètres tels que la météo, les horaires de pointe, ou les préférences des utilisateurs.

   Ces modifications peuvent être réalisées sans affecter les autres parties du code grâce à l'encapsulation des stratégies et à leur gestion dynamique.

#### 4. **Ajout de nouveaux types de métiers**
   
   Le système peut être facilement étendu pour inclure de nouveaux types de métiers ou rôles, comme par exemple un gestionnaire de flotte de véhicules, un responsable de maintenance, ou un opérateur de station. L'architecture actuelle permet de modéliser ces rôles de manière indépendante grâce à l’utilisation de classes spécifiques pour chaque type de métier :
   
   - **Ajouter de nouveaux types de métiers** : Il suffira de créer une nouvelle classe qui implémente un rôle ou une interface associée à un métier spécifique. Par exemple, un `FleetManager` pourrait être ajouté pour gérer la disponibilité des véhicules et l'optimisation de la flotte. Cette classe pourrait contenir des méthodes de gestion et d'optimisation des véhicules.
   - **Modifier les rôles existants** : Si un métier déjà existant (comme un gestionnaire de station) doit être modifié, il est possible de le faire en étendant ou en modifiant les classes de ce métier sans affecter le reste du système.

   Cette approche permet une gestion flexible des différents rôles dans le système, tout en maintenant une séparation claire des responsabilités.

### Conclusion

Ce projet a été conçu de manière modulaire et extensible, ce qui permet d'ajouter ou de modifier facilement des types de véhicules, des décorateurs, des stratégies de redistribution ou des métiers. En respectant des principes SOLID tels que **Single responsability** et le principe **Open closed**, chaque classe est dédiée à une tâche spécifique, et le système est conçu pour permettre des extensions sans toucher aux fonctionnalités existantes. Cette approche garantit que le projet peut évoluer au fil du temps pour répondre à de nouvelles exigences tout en maintenant une architecture claire et robuste.
