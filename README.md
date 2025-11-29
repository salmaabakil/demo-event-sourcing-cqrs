# 🧩 CQRS & Event Sourcing – Activité Pratique

**Développé dans le cadre du module d’Architecture Microservices – Pr. Mohamed Youssfi**

Ce projet met en œuvre une architecture micro-services basée sur les patterns **CQRS (Command Query Responsibility Segregation)** et **Event Sourcing**, en utilisant **Spring Boot**, **Axon Framework**, et une base d’événements.

L’objectif est de reproduire et d’approfondir la démo présentée par le Pr. Youssfi :
👉 *"CQRS avec Event Sourcing (Spring, Axon)"*

---

## 🎯 Objectifs du projet

* Implémenter une architecture **basée sur Axon Framework**.
* Séparer clairement les responsabilités **Command** et **Query** dans le domaine bancaire.
* Stocker l'état de l’application grâce au **Event Sourcing** (AccountCreatedEvent, CreditEvent, DebitEvent…).
* Gérer les commandes : créer un compte, débiter, créditer.
* Alimenter le **read model** via Axon (projections).
* Exposer des API REST pour interagir avec l’aggregate "Account".

---

## 🏗️ Stack technique

* **Java 17+**
* **Spring Boot**
* **Axon Framework (Command Bus, Event Store, Query Bus)**
* **H2 / MySQL** (read model)
* **Axon Server** (event store)
* **Maven**

---

## 🔧 Fonctionnalités principales

### ✔️ Côté Command

* Créer un compte avec un solde initial
* Créditer un compte
* Débiter un compte (avec validation du solde)

### ✔️ Côté Query

* Consulter un compte
* Consulter l’historique des opérations
* Projections synchronisées par Axon

---

## 📦 Structure du projet

```
src/
 ├─ command/
 │   ├─ commands/
 │   ├─ aggregates/
 │   └─ controllers/
 ├─ query/
 │   ├─ entities/
 │   ├─ repositories/
 │   ├─ controllers/
 │   └─ handlers/
```

---

## ▶️ Exécution

1. Lancer Axon Server
2. Démarrer l’application Spring Boot
3. Tester les API via Swagger/Postman

---

## 📹 Source de la démo

Projet inspiré de la démonstration de Pr. Mohamed Youssfi :
[https://www.youtube.com/watch?v=iV2UirD3kjQ](https://www.youtube.com/watch?v=iV2UirD3kjQ)

