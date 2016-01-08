package Modele;

import javafx.application.Platform;

/**
 * Classe qui va faire progresser "l'infection" et générer les événements aléatoires
 */
public class Timeline extends Thread {
    Modele.Jeu jeu;
    boolean end;

    public Timeline(String nom, Modele.Jeu jeu) {
        super(nom);
        this.jeu = jeu;
        this.start();
    }
    public void run() {
        while(!end) {
            try {
                Modele.Timeline.sleep(1000);
            }
            catch(java.lang.InterruptedException e) {
                e.printStackTrace();
            }
            catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }

            if (jeu.getDepartements().size() > 0) {
                jeu.setTemps(-1);
                if(jeu.getDepartements().get(0).getTaches().get(0).getAvancement() == 0) {
                    //jeu.victoire();
                }
                int depComplet = 0;
                for(Modele.Departement departement : jeu.getDepartements()) {
                    if(departement.getNbActif() == departement.getNbPersonne()) depComplet++;
                    if(departement.getTaches().size() > 0) {
                        departement.infection(jeu);
                    }
                    departement.supprimerTache(jeu);
                    departement.affichage(jeu);
                    for(Modele.Tache tache : departement.getTaches()) {
                        tache.setAvancement(-1);
                    }
                }
                if(depComplet == 5 || jeu.getTemps() == 0) {
                    //jeu.gameOver();
                }
                jeu.afficherCompte();
                if(jeu.getPopUps().size() != 0){
                    jeu.getVue().affichagePopUp(2);
                }
                if ((int)(Math.random() * 15) == 0){
                    jeu.ajoutPopUp();
                }
                if(jeu.getEvenements().size() == 0) {
                    for(Modele.Departement departement : jeu.getDepartements()) {
                        departement.supprimerTache(jeu);
                    }
                    if(jeu.getEvenements().size() == 0 && (int)(Math.random() * 15) == 0) {
                        if((int)(Math.random() * 3) == 0) {
                            int depAlea = (int) (Math.random() * jeu.getDepartements().size());
                            Modele.Departement departement = jeu.getDepartements().get(depAlea);
                            if(departement.getTaches().size() < 4) {
                                departement.creerTache();
                                Modele.Tache tache = departement.getTaches().get(departement.getTaches().size() - 1);
                                String nom = tache.getNom();
                                jeu.ajoutEvenement("Vous avez demandé de l'aide au département " + departement.getNom() + " :", nom);
                            }
                        }
                        else {
                            jeu.ajoutEvenement();
                        }
                    }
                }
                jeu.getVue().affichageEvenement(2);
            }
        }
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
