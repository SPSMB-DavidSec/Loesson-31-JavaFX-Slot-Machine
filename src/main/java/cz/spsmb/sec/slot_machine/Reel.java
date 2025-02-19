package cz.spsmb.sec.slot_machine;

import java.util.ArrayList;
import java.util.List;

public class Reel {
    private double speed = 5d;
    private double minSpeed = 0.5d;
    private double slowDown = 0.5d;


    private List<Symbol> symbols = new ArrayList<>();

    public Reel(List<Symbol> symbols){
        addSymbols(symbols);
    }

    public Reel(double speed, double minSpeed, List<Symbol> symbols){
        this.speed = speed;
        this.minSpeed = minSpeed;
        addSymbols(symbols);
    }

    public double getSpeed() {
        return speed;
    }

    public List<Symbol> getSymbols() {
        return symbols;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(double minSpeed) {
        this.minSpeed = minSpeed;
    }

    public double getSlowDown() {
        return slowDown;
    }

    public void setSlowDown(double slowDown) {
        this.slowDown = slowDown;
    }

    private void addSymbols(List<Symbol> symbols) {
        if (symbols.size() >= 3) {
            this.symbols.addAll(symbols);
        }
        else {
            System.out.println("You have to add at least 3 symbols to the reel.");
        }
    }
}
