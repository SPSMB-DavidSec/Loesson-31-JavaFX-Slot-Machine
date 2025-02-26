package cz.spsmb.sec.slot_machine;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    GraphicsContext gc;
    @FXML
    Canvas canvas;

    final int IMAGE_SIZE = 120;

    Reel[] reels = new Reel[3];
    double x = 0;
    double[] y = new double[3];

    Random r = new Random();


    @FXML
    protected void onSpinButtonClick() {
        for (int i = 0; i < reels.length; i++) {
            reels[i].setSpeed(r.nextInt(60) + 30);
            reels[i].setSlowDown(r.nextDouble() + 0.5d);
        }
    }

    private void tick() {
        for (int i = 0; i < reels.length; i++) {
            Reel reel = reels[i];
            List<Symbol> symbols = reel.getSymbols();
            int symbolsSize = symbols.size();
            y[i] = (y[i] + reel.getSpeed()) % (symbolsSize * IMAGE_SIZE);

            if (reel.getSpeed() > reel.getMinSpeed()) {
                reel.setSpeed(Math.max(reel.getMinSpeed(), reel.getSpeed() - reel.getSlowDown()));
            }
            if (reel.getSpeed() == reel.getMinSpeed() && (int) y[i] % IMAGE_SIZE == 0) {
                reel.setSpeed(0);
                if (!isAnyReelSpinning(reels)) {
                    identifySymbols(y, reels);
                }
            }
            for (int j = 0; j < symbols.size() + 3; j++) {
                int index = j % symbolsSize;
                gc.drawImage(symbols.get(index).getImage(), x + (i * IMAGE_SIZE),
                        y[i] + (j * IMAGE_SIZE) - (symbolsSize * IMAGE_SIZE), IMAGE_SIZE, IMAGE_SIZE);
            }
        }
    }

    private boolean isAnyReelSpinning(Reel[] reels) {
        for (Reel reel : reels) {
            if (reel.getSpeed() > 0) {
                return true;
            }
        }
        return false;
    }

    private void identifySymbols(double[] y, Reel[] reels) {
        Symbol[][] symbols = new Symbol[3][3];
        for (int i = 0; i < reels.length; i++) {
            int index = (int) y[i] / IMAGE_SIZE;
            symbols[i][0] = reels[i].getSymbols().get(mapIndex(index - 1));
            symbols[i][1] = reels[i].getSymbols().get(mapIndex(index));
            symbols[i][2] = reels[i].getSymbols().get(mapIndex(index + 1));
        }
        for (int i = 0; i < symbols.length; i++) {
            System.out.println(symbols[2][i] + " " + symbols[1][i] + " " + symbols[0][i]);
        }

        isWinningCombination(symbols);
    }

    private int isWinningCombination(Symbol[][] symbols) {
        int multiplication = 1;
        for (int i = 0; i < symbols.length; i++) {
            if ((symbols[2][i].equals(symbols[1][i])) &&
                    (symbols[1][i].equals(symbols[0][i]))) {
                System.out.println("win in a row " + i +" WIN x" +symbols[0][0].getMultiplication());
            }
            if ((symbols[0][0].equals(symbols[1][1])) &&
                    (symbols[1][1].equals(symbols[2][2]))) {
                System.out.println("win in a diagonal. WIN x" +symbols[0][0].getMultiplication());
            }
            if ((symbols[0][2].equals(symbols[1][1])) &&
                    (symbols[1][1].equals(symbols[2][0]))) {
                System.out.println("win in a diagonal. WIN x" + symbols[0][2].getMultiplication());

            }
        }
        return multiplication;
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
        reels[0] = new Reel(r.nextInt(60) + 30, r.nextDouble() + 0.5d, List.of(Symbol.CHERRIES, Symbol.LEMON, Symbol.ORANGE, Symbol.STAR, Symbol.CLOVER
                , Symbol.GRAPES, Symbol.GRAPES));

        reels[1] = new Reel(r.nextInt(60) + 30, r.nextDouble() + 0.5d, List.of(Symbol.CHERRIES, Symbol.LEMON, Symbol.ORANGE, Symbol.STAR, Symbol.CLOVER
                , Symbol.GRAPES, Symbol.GRAPES));

        reels[2] = new Reel(r.nextInt(60) + 30, r.nextDouble() + 0.5d, List.of(Symbol.CHERRIES, Symbol.LEMON, Symbol.ORANGE, Symbol.STAR, Symbol.CLOVER
                , Symbol.GRAPES, Symbol.GRAPES));

    }

    public static int mapIndex(int x) {
        if (x < 2) {
            return 1 - x;
        } else {
            return 8 - x;
        }
    }

}