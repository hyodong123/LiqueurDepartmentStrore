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

    // Getter and Setter methods
    public int getLiqueurId() {
        return liqueurId;
    }

    public void setLiqueurId(int liqueurId) {
        this.liqueurId = liqueurId;
    }

    public String getLiqueurName() {
        return liqueurName;
    }

    public void setLiqueurName(String liqueurName) {
        this.liqueurName = liqueurName;
    }

    public String getTypeCategory() {
        return typeCategory;
    }

    public void setTypeCategory(String typeCategory) {
        this.typeCategory = typeCategory;
    }

    public double getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(double alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() { // 새로운 메서드 추가
        return getLiqueurId();
    }

    public String getName() { // 새로운 메서드 추가
        return getLiqueurName();
    }

    @Override
    public String toString() {
        return liqueurId + ", " + liqueurName + ", " + typeCategory + ", " + alcoholContent + "%, " + origin + ", " + volume + "ml, " + price + "원";
    }
}