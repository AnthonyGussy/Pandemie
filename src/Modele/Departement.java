package Modele;

import Enumerations.CompteurType;
import Vue.Compteur;

import java.util.List;

public class Departement {
    Compteur c;
    Enumerations.DepartementNom nom;
    int nbPersonne;
    List<Compteur> compteurs;
    List<TacheModele> taches;
    ArbreDeCompetenceModele arbre;

    public Departement(){

        c = new Compteur(10,10, CompteurType.Efficacite);

    }
    void affichage(){}
    void creerTache(){}
    void infection(){}
    void supprimerTache(){}
}
