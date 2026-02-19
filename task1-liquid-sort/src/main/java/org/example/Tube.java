package org.example;
import java.util.Stack;

public class Tube {
    private Stack<String> drops;
    private final int capacity;

    // for empty tube
    public Tube(int capacity) {
        this.capacity = capacity;
        this.drops = new Stack<>();
    }

    // for filled tube
    public Tube(int capacity, String[] intialDrops) {
        this.capacity = capacity;
        this.drops = new Stack<>();
        for (int i = intialDrops.length - 1; i >= 0; i--) {
            if (intialDrops[i] != null) {
                drops.push(intialDrops[i]);
            }
        }
    }

    public boolean isEmpty() {
        return drops.isEmpty();
    }

    public boolean isFull() {
        return drops.size() == capacity;
    }

    public String topColor() {
        return drops.isEmpty() ? null : drops.peek();
    }

    public int countTopColor() {
        if (drops.isEmpty()) return 0;
        String color = drops.peek();
        int count = 0;
        for (int i = drops.size() - 1; i >= 0; i--) {
            if (drops.get(i).equals(color)) count++;
            else break;
        }
        return count;
    }

    public int spaceLeft() {
        return capacity - drops.size();
    }

    // to pour from one tube to another
    public int pourInto(Tube other) {
        if (this.isEmpty()) return 0;
        String color = this.topColor();

        if (!other.isEmpty() && !other.topColor().equals(color)) return 0;
        int canPour = Math.min(this.countTopColor(), other.spaceLeft());
        for (int i = 0; i < canPour; i++) {
            other.drops.push(this.drops.pop());
        }
        return canPour;
    }

    // to check if tube has only one color or it's empty
    public boolean isUniformOrEmpty() {
        if (drops.isEmpty()) return true;
        String color = drops.peek();
        for (String d : drops) {
            if (!d.equals(color)) return false;
        }
        return true;
    }
    public Tube cloneTube() {
        Tube clone = new Tube(this.capacity);
        clone.drops = (Stack<String>) this.drops.clone();
        return clone;
    }

    @Override
    public String toString() {
        return drops.toString();
    }

    public Stack<String> getDrops() {
        return (Stack<String>) drops.clone();
    }


}