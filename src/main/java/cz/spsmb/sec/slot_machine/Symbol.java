package cz.spsmb.sec.slot_machine;

import javafx.scene.image.Image;

public enum Symbol {
    LEMON("citron.png", 2),
    CLOVER("ctyrlistek.png",25),
    GRAPES("hrozno.png",10),
    STAR("hvězda.png",50),
    MELON("meloun.png",10),
    ORANGE("pomeranc.png",5),
    PLUM("svestka.png",5),
    CHERRIES("tresne.png",2);

    private Image image;
    private int multiplication;

    Symbol(String textureName ,int multiplication) {
        this.image = new Image(getClass().getResource("/images/"+ textureName).toExternalForm());
        this.multiplication = multiplication;
    }

    public Image getImage() {
        return image;
    }

    public int getMultiplication() {
        return multiplication;
    }
}
