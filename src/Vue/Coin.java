package Vue;

import Constantes.Constantes;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 *
 */
public class Coin {
    private Image[] coinImagesStock;
    private Image[] coinImages;
    private ImageView coin;
    private boolean affiche;
    private Polygon polyMenu;
    private Polygon polyAffichage;

    public Coin(Modele.Jeu jeu) {
        Scene scene = jeu.getVue().getScene();
        coinImagesStock = new Image[]{ new Image("file:image\\FullScreen\\PandemieNoSelection.jpg"),
                new Image("file:image\\FullScreen\\PandemieMenuSelection.jpg"),
                new Image("file:image\\FullScreen\\PandemieAffichageSelection.jpg"),
                new Image("file:image\\Fenetre\\PandemieNoSelection.jpg"),
                new Image("file:image\\Fenetre\\PandemieMenuSelection.jpg"),
                new Image("file:image\\Fenetre\\PandemieAffichageSelection.jpg") };
        coinImages = new Image[]{coinImagesStock[0], coinImagesStock[1], coinImagesStock[2]};
        coin = new ImageView(coinImages[0]);
        coin.setX(0);
        coin.setY(0);

        polyMenu = new Polygon();
        polyMenu.getPoints().addAll(Constantes.POLYGONE_COIN_MENU_FS);
        //polyMenu.setFill(Color.TRANSPARENT);
        polyMenu.setOnMouseEntered(mouseEvent -> {
            coin.setImage(coinImages[1]);
            affichage(jeu, 2);
        });
        polyMenu.setOnMouseExited(mouseEvent -> {
            coin.setImage(coinImages[0]);
            affichage(jeu, 2);
        });
        polyMenu.setOnMouseClicked(mouseEvent -> {

        });
        polyAffichage = new Polygon();
        polyAffichage.getPoints().addAll(Constantes.POLYGONE_COIN_AFFICHAGE_FS);
        polyAffichage.setFill(Color.TRANSPARENT);
        polyAffichage.setOnMouseEntered(mouseEvent -> {
            coin.setImage(coinImages[2]);
            affichage(jeu, 2);
        });
        polyAffichage.setOnMouseExited(mouseEvent -> {
            coin.setImage(coinImages[0]);
            affichage(jeu, 2);
        });
        polyAffichage.setOnMouseClicked(mouseEvent -> {

        });
    }

    public void affichage(Modele.Jeu jeu, int afficher) {
        Scene scene = jeu.getVue().getScene();
        Group root = jeu.getVue().getRoot();
        switch(afficher) {
            case 0:
                affiche = true;
                coin.setFitWidth(scene.getWidth() * Constantes.LARGEUR_COIN);
                coin.setFitHeight(scene.getHeight() * Constantes.HAUTEUR_COIN);
                if(root.getChildren().contains(coin)) root.getChildren().remove(coin);
                if(root.getChildren().contains(polyAffichage)) root.getChildren().remove(polyAffichage);
                if(root.getChildren().contains(polyMenu)) root.getChildren().remove(polyMenu);
                root.getChildren().addAll(coin, polyAffichage, polyMenu);
                break;
            case 1:
                affiche = false;
                if(root.getChildren().contains(coin)) root.getChildren().remove(coin);
                if(root.getChildren().contains(polyAffichage)) root.getChildren().remove(polyAffichage);
                if(root.getChildren().contains(polyMenu)) root.getChildren().remove(polyMenu);
                break;
            default:
                if (affiche) {
                    if (jeu.getVue().getPrimaryStage().isFullScreen()) {
                        polyMenu.getPoints().clear();
                        polyMenu.getPoints().addAll(Constantes.POLYGONE_COIN_MENU_FS);
                        polyAffichage.getPoints().clear();
                        polyAffichage.getPoints().addAll(Constantes.POLYGONE_COIN_AFFICHAGE_FS);
                        coinImages = new Image[]{coinImagesStock[0], coinImagesStock[1], coinImagesStock[2]};
                    } else {
                        polyMenu.getPoints().clear();
                        polyMenu.getPoints().addAll(Constantes.POLYGONE_COIN_MENU_W);
                        polyAffichage.getPoints().clear();
                        polyAffichage.getPoints().addAll(Constantes.POLYGONE_COIN_AFFICHAGE_W);
                        coinImages = new Image[]{coinImagesStock[3], coinImagesStock[4], coinImagesStock[5]};
                    }
                    affichage(jeu, 0);
                }
        }
    }
}
