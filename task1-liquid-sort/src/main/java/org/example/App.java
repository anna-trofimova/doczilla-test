
package org.example;

public class App {
    public static void main(String[] args) {
        Tube t1 = new Tube(4, new String[]{"02", "10", "04", "04"});
        Tube t2 = new Tube(4);

        System.out.println("До переливания:");
        System.out.println("t1: " + t1);
        System.out.println("t2: " + t2);

        t1.pourInto(t2);

        System.out.println("После переливания:");
        System.out.println("t1: " + t1);
        System.out.println("t2: " + t2);
    }
}
