package org.example;

import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {

        Tube t1 = new Tube(4, new String[]{"02", "10", "04", "04"});
        Tube t2 = new Tube(4, new String[]{"01", "08", "12", "08"});
        Tube t3 = new Tube(4, new String[]{"10", "07", "05", "09"});
        Tube t4 = new Tube(4, new String[]{"05", "03", "02", "05"});
        Tube t5 = new Tube(4, new String[]{"06", "11", "08", "07"});
        Tube t6 = new Tube(4, new String[]{"12", "12", "01", "02"});
        Tube t7 = new Tube(4, new String[]{"04", "07", "08", "11"});
        Tube t8 = new Tube(4, new String[]{"10", "11", "03", "01"});
        Tube t9 = new Tube(4, new String[]{"10", "07", "09", "09"});
        Tube t10 = new Tube(4, new String[]{"06", "02", "06", "11"});
        Tube t11 = new Tube(4, new String[]{"04", "06", "09", "03"});
        Tube t12 = new Tube(4, new String[]{"05", "03", "12", "01"});


        Tube tEmpty1 = new Tube(4);
        Tube tEmpty2 = new Tube(4);

        Tube[] tubes = {
                t1,
                t2,
                t3,
                t4,
                t5,
                t6,
                t7,
                t8,
                t9,
                t10,
                t11,
                t12,
                tEmpty1,
                tEmpty2
        };

        GameState gameState = new GameState(tubes);

        Solver solver = new Solver(0); // depth не используется в iterative

        long start = System.currentTimeMillis();

        List<int[]> moves = solver.solveIterative(gameState);

        long end = System.currentTimeMillis();

        if (moves.isEmpty()) {
            System.out.println("Решение не найдено");
        } else {
            System.out.println("Найдено решение:");
            for (int[] move : moves) {
                System.out.println(Arrays.toString(move));
            }
        }

        System.out.println("Время выполнения: " + (end - start) + " ms");
    }
}
