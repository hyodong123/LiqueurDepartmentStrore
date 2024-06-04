package LiqueurDepartment.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LiqueurStorage {
    ArrayList<Liqueur> liqueurList = new ArrayList<>();
    final int MAX_QUANTITY = 10;
    private String liqueurFilename = "liqueurlist.txt";

    public LiqueurStorage() throws IOException {
        loadLiqueurListFromFile();        
    }

    private void loadLiqueurListFromFile() throws IOException {
        FileReader fr;
        try {
            fr = new FileReader(liqueurFilename);
            BufferedReader br = new BufferedReader(fr);
            String idStr;
            while ((idStr = br.readLine()) != null) {
                int id = Integer.parseInt(idStr);
                String name = br.readLine();
                String type = br.readLine();
                double alcoholContent = Double.parseDouble(br.readLine());
                String origin = br.readLine();
                int volume = Integer.parseInt(br.readLine());
                int price = Integer.parseInt(br.readLine());
                liqueurList.add(new Liqueur(id, name, type, alcoholContent, origin, volume, price));
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getNumLiqueurs() {
        return liqueurList.size();
    }

    public String getLiqueurInfo(int i) {
        return liqueurList.get(i).toString();
    }

    public boolean isValidLiqueur(int liqueurId) {
        for (Liqueur liqueur : liqueurList) {
            if (liqueur.getLiqueurId() == liqueurId) return true;
        }
        return false;
    }

    public Liqueur getLiqueurById(int liqueurId) {
        for (Liqueur liqueur : liqueurList) {
            if (liqueur.getLiqueurId() == liqueurId)
                return liqueur;
        }
        return null;
    }

    public int getMaxQuantity() {
        return MAX_QUANTITY;
    }
}
