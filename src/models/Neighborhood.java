package models;

public enum Neighborhood {
    REGION_1("Niavaran", 1.8),
    REGION_2("Piroozi" , 1.4),
    REGION_3("Shosh"   , 1.1),
    REGION_4("Varamin" , 0.8);

    private final String name;
    private final double coefficient;

    Neighborhood(String name, double coefficient) {
        this.name = name;
        this.coefficient = coefficient;
    }

    public String getName() {
        return name;
    }

    public double getCoefficient() {
        return coefficient;
    }
}
