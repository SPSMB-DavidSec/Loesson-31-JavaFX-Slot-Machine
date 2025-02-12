package cz.spsmb.sec.slot_machine;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    GraphicsContext gc;
    @FXML
    Canvas canvas;

    final int IMAGE_SIZE = 120;

    Reel[] reels = new Reel[1];
    double x = 0;
    double y = IMAGE_SIZE*3;


    @FXML
    protected void onHelloButtonClick() {

    }


    private void tick() {
        for (int i = 0; i < reels.length; i++) {
            List<Symbol> symbols = reels[i].getSymbols();
            for (int j = 0; j < symbols.size() ; j++) {
                y = y+reels[i].getSpeed();

                if (j == symbols.size() ){
                    j = 0;
                }
                gc.drawImage(symbols.get(j).getImage(), x, y - ((j+1)*120),120,120);
                System.out.println(j);
                if(y >= (IMAGE_SIZE * symbols.size())){
                    y = IMAGE_SIZE*3;
                }

            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        initSlots();
        AnimationTimer animationTimer = new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {
                if (now - lastTick > 1000000) {
                    lastTick = now;
                    gc.clearRect(0, 0, 360, 360);
                    tick();
                }
            }
        };
        animationTimer.start();
    }

    private void initSlots() {
        reels[0] = new Reel(List.of(Symbol.CLOVER, Symbol.GRAPES, Symbol.GRAPES,Symbol.LEMON,Symbol.LEMON, Symbol.PLUM,Symbol.CLOVER, Symbol.GRAPES,Symbol.CLOVER, Symbol.GRAPES, Symbol.GRAPES));//,Symbol.STAR,Symbol.MELON,Symbol.ORANGE,
        //Symbol.PLUM,Symbol.CHERRIES));

    }

}