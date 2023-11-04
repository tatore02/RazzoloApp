package it.unisa.razzolo.model;

public class Point {
    private final int i;
    private final int j;
    private final char value;
    private final int index;

    public Point(int i, int j, char value, int index) {
        this.i = i;
        this.j = j;
        this.value = value;
        this.index = index;
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

    public int getIndex() {
        return index;
    }
}
