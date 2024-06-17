package liqueurDepartment.model;

public class Liqueur {
    private int liqueurId;
    private String liqueurName;
    private String type; // 주류 종류 (예: 위스키, 와인, 보드카 등)
    private double alcoholContent; // 알코올 도수
    private String origin; // 원산지
    private int volume; // 용량 (ml)
    private int price; // 가격

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

    public void setLiqueurId(int liqueurId) {
        this.liqueurId = liqueurId;
    }

    public String getLiqueurName() {
        return liqueurName;
    }

    public void setLiqueurName(String liqueurName) {
        this.liqueurName = liqueurName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return liqueurId + ", " + liqueurName + ", " + type + ", " + alcoholContent + "%, " + origin + ", " + volume + "ml, " + price + "원";
    }
}
