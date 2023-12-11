package xoanaraujo.examenuf1.model;

public class ExampleItem {
    private static int increment = 0;
    private int incId;
    private String name;

    public ExampleItem(String name) {
        incId = increment++;
        this.name = name;
    }

    public static int getIncrement() {
        return increment;
    }

    public int getIncId() {
        return incId;
    }

    public String getName() {
        return name;
    }
}
