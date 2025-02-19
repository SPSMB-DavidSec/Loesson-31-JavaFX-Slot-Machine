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
            Reel reel = reels[i];
            List<Symbol> symbols = reel.getSymbols();
            int symbolsSize = symbols.size();
            y = (y + reel.getSpeed()) % (symbolsSize * IMAGE_SIZE);

            if (reel.getSpeed() > 0.5){
                reel.setSpeed(Math.max(0.5,reel.getSpeed()-0.05));
            }

            for (int j = 0; j < symbols.size() + 3 ; j++) {
               int index = j % symbolsSize;
               gc.drawImage(symbols.get(index).getImage(), x + (i*IMAGE_SIZE), y + (j*IMAGE_SIZE)-(5*IMAGE_SIZE), IMAGE_SIZE, IMAGE_SIZE);
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
        reels[0] = new Reel(List.of(Symbol.CHERRIES, Symbol.LEMON, Symbol.CLOVER,Symbol.GRAPES,Symbol.GRAPES));//,Symbol.STAR,Symbol.MELON,Symbol.ORANGE,
        //Symbol.PLUM,Symbol.CHERRIES));

    }

}