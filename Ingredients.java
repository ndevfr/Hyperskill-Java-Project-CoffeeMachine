package machine;

public enum Ingredients {
    WATER("ml of water"),
    MILK("ml of milk"),
    BEANS("g of coffee beans"),
    CUPS("disposable cups");

    private final String legend;

    Ingredients(String legend) {
        this.legend = legend;
    }

    public String getLegend() {
        return legend;
    }
}

