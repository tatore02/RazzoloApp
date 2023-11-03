package it.unisa.razzolo.model;

public class Point {
    private final int i;
    private final int j;
    private final char value;

    public Point(int i, int j, char value) {
        this.i = i;
        this.j = j;
        this.value = value;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public char getValue() {
        return value;
    }
}
