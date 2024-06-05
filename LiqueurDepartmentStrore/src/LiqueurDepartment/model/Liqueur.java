package LiqueurDepartment.model;

public class Liqueur {
    private int liqueurId;
    private String liqueurName;
    private String typeCategory;
    private double alcoholContent;
    private String origin;
    private int volume;
    private int price;

    public Liqueur(int liqueurId, String liqueurName, String typeCategory, double alcoholContent, String origin, int volume, int price) {
        this.liqueurId = liqueurId;
        this.liqueurName = liqueurName;
        this.typeCategory = typeCategory;
        this.alcoholContent = alcoholContent;
        this.origin = origin;
        this.volume = volume;
        this.price = price;
    }

    public int getLiqueurId() {
        return liqueurId;
    }

    public void setLiqueurId(int liqueurId) {
        this.liqueurId = liqueurId;
    }

    public String getLiqueurName() {
        return liqueurName;
    }

    public String getTypeCategory() {
        return typeCategory;
    }

    public double getAlcoholContent() {
        return alcoholContent;
    }

    public String getOrigin() {
        return origin;
    }

    public int getVolume() {
        return volume;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return liqueurId + ", " + liqueurName + " (" + typeCategory + "), " + alcoholContent + "%, " + origin + ", " + volume + "ml, " + price + "원";
    }
}
