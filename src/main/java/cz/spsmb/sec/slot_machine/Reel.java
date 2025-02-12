package cz.spsmb.sec.slot_machine;

import java.util.ArrayList;
import java.util.List;

public class Reel {
    private double speed = 0.2;
    private List<Symbol> symbols = new ArrayList<>();

    public Reel(List<Symbol> symbols){
        if (symbols.size() >= 3) {
            this.symbols.addAll(symbols);
        }
        else {
            System.out.println("You have to add at least 3 symbols to the reel.");
        }
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
}
