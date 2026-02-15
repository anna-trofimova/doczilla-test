
package org.example;

public class App {
    public static void main(String[] args) {
        Tube t1 = new Tube(4, new String[]{"02", "10", "04", "04"});
        Tube t2 = new Tube(4);
        Tube t3 = new Tube(4);

        Tube[] tubes = {t1, t2, t3};

        GameState gameState = new GameState(tubes);

        System.out.println("Начальное состояние:");
        gameState.printState();

        System.out.println("Is solved? " + gameState.isSolved());

        if (gameState.canPour(0, 1)) {
            gameState.pour(0, 1);
        }

        System.out.println("После переливания 0 -> 1:");
        gameState.printState();
    }

}
