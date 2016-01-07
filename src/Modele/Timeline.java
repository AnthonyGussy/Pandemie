package Modele;

import javafx.application.Platform;

/**
 * Classe qui va faire progresser "l'infection" et g�n�rer les �v�nements al�atoires
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

        int numero = (int)(Math.random()*10);
        while(!end) {
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
                if(jeu.getPopUps().size() != 0){
                    jeu.getVue().affichagePopUp();
                }
                else if (numero == (int)(Math.random()*10)){

                    jeu.setPopUp(true);

                }

                jeu.getVue().affichageEvenement();
            }



        }
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
