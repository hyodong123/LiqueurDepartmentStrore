package LiqueurDepartment.model;

public class Liqueur {
    private int liqueurId;
    private String liqueurName;
    private String type; // 주류 종류 (예: 위스키, 와인, 보드카 등)
    private double alcoholContent; // 알코올 도수
    private String origin; // 원산지
    private int volume; // 용량 (ml)
    private int price;

    public Liqueur(int liqueurId, String liqueurName, String type, double alcoholContent, String origin, int volume, int price) {
        this.liqueurId = liqueurId;
        this.liqueurName = liqueurName;
        this.type = type;
        this.alcoholContent = alcoholContent;
        this.origin = origin;
        this.volume = volume;
        this.price = price;
    }

    public int getLiqueurId() {
        return liqueurId;
    }

    public String getLiqueurName() {
        return liqueurName;
    }

    public String getType() {
        return type;
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
        return liqueurId + ", " + liqueurName + ", " + type + ", " + alcoholContent + "%, " + origin + ", " + volume + "ml, " + price + "원";
    }
}
