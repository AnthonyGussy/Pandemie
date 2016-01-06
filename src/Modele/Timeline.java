package Modele;

import javafx.application.Platform;

/**
 * Classe qui va faire progresser "l'infection" et générer les événements aléatoires
 */
public class Timeline extends Thread {
    Modele.Jeu jeu;
    boolean End;


    public Timeline(String nom, Modele.Jeu jeu) {
        super(nom);
        this.jeu = jeu;
        this.start();
    }
    public void run() {
        while(!End) {
            try {
                this.sleep(1000);

            }
            catch(java.lang.InterruptedException e) {
                e.printStackTrace();
            }
            catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
            if (jeu.getDepartements().size() > 0) {
                jeu.getDepartements().get(0).infection(jeu);
                /*jeu.setPopUp();
                if(jeu.getPopUp() != null)
                    Platform.runLater(() -> jeu.getPopUp().getVue().affichage(jeu));*/
            }


        }
    }

    public void setEnd(boolean end) {
        End = end;
    }
}
