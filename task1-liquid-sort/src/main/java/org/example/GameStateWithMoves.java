package org.example;

import java.util.ArrayList;
import java.util.List;

public class GameStateWithMoves {
    public GameState state;
    public List<int[]> movesSoFar;

    public GameStateWithMoves(GameState state) {
        this.state = state;
        this.movesSoFar = new ArrayList<>();
    }

    public GameStateWithMoves(GameState state, List<int[]> movesSoFar) {
        this.state = state;
        this.movesSoFar = new ArrayList<>(movesSoFar);
    }
}
