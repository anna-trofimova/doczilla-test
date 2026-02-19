package org.example;

public class GameState {
    private Tube[] tubes;

    public GameState(Tube[] tubes) {
        this.tubes = new Tube[tubes.length];
        for (int i = 0; i < tubes.length; i++) {
            this.tubes[i] = tubes[i].cloneTube();
        }
    }

    public Tube[] getTubes() {
        return tubes;
    }

    public boolean isSolved() {
        for (Tube t : tubes) {
            if (!t.isUniformOrEmpty()) return false;
        }
        return true;
    }

    public boolean canPour(int from, int to) {
        Tube tFrom = tubes[from];
        Tube tTo = tubes[to];
        if (tFrom.isEmpty() || tTo.isFull()) return false;
        return tTo.isEmpty() || tTo.topColor().equals(tFrom.topColor());
    }

    public int pour(int from, int to) {
        return tubes[from].pourInto(tubes[to]);
    }

    public GameState cloneState() {
        return new GameState(this.tubes);
    }

    public String encodeState() {
        StringBuilder sb = new StringBuilder();
        for (Tube t : tubes) {
            sb.append(t.getDrops().toString()); // <- исправлено
            sb.append("|");
        }
        return sb.toString();
    }


}
