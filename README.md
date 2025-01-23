Capco Test de recrutement pour Dimitar Angelov

# Case Study:

Développer un programme permettant de calculer le
panier d’un consommateur.
Il existe deux types de clients :

1. Des clients particuliers identifié par un ID client, un nom et un prénom
2. Des clients professionnels identifiés par un id client, une raison sociale, un numéro de
   TVA intracommunautaire (qui est optionnel), un SIREN, et un chiffre d’affaires annuel
   Pour les particuliers, le modèle de téléphone haut de gamme coute 1500 euros, le moyen de
   gamme coûte 800 euros, et le laptop 1200 euros.
   Pour les clients professionnels ayant un chiffre d’affaires supérieur à 10 millions d’euros, le
   modèle de téléphone haut de gamme coute 1000 euros, le moyen de gamme coûte 550 euros,
   et le laptop 900 euros.
   Pour les clients professionnels ayant un chiffre d’affaires inférieur à 10 millions d’euros, le
   modèle de téléphone haut de gamme coute 1150 euros, le moyen de gamme coûte 600 euros,
   et le laptop 1000 euros.
   Calculer pour un client donné le cout de son panier, sachant que celui-ci peut contenir les 3 produits en plusieurs
   exemplaires.

# Solution

Architecture héxagonale.

Use case principal:
class ComputeShoppingCart: Calcule la valeur du panier pour un client en devise.

Choix de design:

1. Produit: Correspondance directe entre entité du domain et table JPA
2. Client: le modèle de données JPA utilise single table inhéritance pour plus de simplicité dans ce cas précis.
3. Prix: Modeliser le prix par un objet Money. Persister le prix basic par produit et devise.
4. Modeliser la différence de
   prix par produit et client par la notion de discount. Cela pour pouvoir utiliser multiples stratégies de pricing (
   cumulant
   plusieurs discounts par (client,produit), par ex. client particulier a 10% sur un produit et encore 5% si son
   anniversaire est dans le mois courant)

Bootstrap:

1. DB: MySQL dans un conteneur docker
2. Console Application: Lancer SpringBoot main class










