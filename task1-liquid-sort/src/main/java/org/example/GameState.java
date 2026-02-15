package org.example;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private Tube[] tubes;

    public GameState(Tube[] tubes) {
        this.tubes = new Tube[tubes.length];
        for (int i = 0; i < tubes.length; i++) {
            this.tubes[i] = tubes[i].cloneTube();
        }
    }

    // Checking if game is solved
    public boolean isSolved() {
        for (Tube t : tubes) {
            if (!t.isUniformOrEmpty()) {
                return false;
            }
        }
        return true;
    }

    // Checking if we can pour to this tube
    public boolean canPour(int from, int to) {
        Tube tFrom = tubes[from];
        Tube tTo = tubes[to];

        if (tFrom.isEmpty()) return false;
        if (tTo.isFull()) return false;

        String color = tFrom.topColor();
        return tTo.isEmpty() || tTo.topColor().equals(color);

    }

    public int pour(int from, int to) {
        return tubes[from].pourInto(tubes[to]);
    }

    // Clone state to use it in backtracking
    public GameState cloneState() {
        return new GameState(this.tubes);
    }

    public void printState() {
        for (int i = 0; i < tubes.length; i++) {
            System.out.println("Пробирка " + i + ": " + tubes[i]);
        }
        System.out.println();
    }

}