package cz.spsmb.sec.slot_machine;

import javafx.scene.image.Image;

public enum Symbol {
    LEMON("citron.png"),
    CLOVER("ctyrlistek.png"),
    GRAPES("hrozno.png"),
    STAR("hvězda.png"),
    MELON("meloun.png"),
    ORANGE("pomeranc.png"),
    PLUM("svestka.png"),
    CHERRIES("tresne.png");

    private Image image;

    Symbol(String textureName) {
        this.image = new Image(getClass().getResource("/images/"+ textureName).toExternalForm());
    }

    public Image getImage() {
        return image;
    }
}
