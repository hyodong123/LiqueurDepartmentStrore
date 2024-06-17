package liqueurDepartment.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LiqueurStorage {
    ArrayList<Liqueur> liqueurList = new ArrayList<>();
    final int MAX_QUANTITY = 10;
    private String liqueurFilename = "liqueurlist.txt";
    private int nextLiqueurId = 1;

    public LiqueurStorage() {
        loadLiqueurListFromFile();        
    }

    private void loadLiqueurListFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(liqueurFilename))) {
            String idStr;
            while ((idStr = br.readLine()) != null) {
                int id = Integer.parseInt(idStr);
                String name = br.readLine();
                String typeCategory = br.readLine();
                double alcoholContent = Double.parseDouble(br.readLine());
                String origin = br.readLine();
                int volume = Integer.parseInt(br.readLine());
                int price = Integer.parseInt(br.readLine());
                liqueurList.add(new Liqueur(id, name, typeCategory, alcoholContent, origin, volume, price));
                if (id >= nextLiqueurId) {
                    nextLiqueurId = id + 1;
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading liqueur list from file: " + e.getMessage());
        }
    }

    private void saveLiqueurListToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(liqueurFilename))) {
            for (Liqueur liqueur : liqueurList) {
                bw.write(String.valueOf(liqueur.getLiqueurId()));
                bw.newLine();
                bw.write(liqueur.getLiqueurName());
                bw.newLine();
                bw.write(liqueur.getType());
                bw.newLine();
                bw.write(String.valueOf(liqueur.getAlcoholContent()));
                bw.newLine();
                bw.write(liqueur.getOrigin());
                bw.newLine();
                bw.write(String.valueOf(liqueur.getVolume()));
                bw.newLine();
                bw.write(String.valueOf(liqueur.getPrice()));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving liqueur list to file: " + e.getMessage());
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

    // 이름 또는 종류로 검색
    public ArrayList<Liqueur> searchLiqueurByNameOrType(String keyword) {
        ArrayList<Liqueur> results = new ArrayList<>();
        keyword = keyword.toLowerCase(); // 대소문자 구분 없이 검색
        for (Liqueur liqueur : liqueurList) {
            String name = liqueur.getLiqueurName().toLowerCase();
            String typeCategory = liqueur.getType().toLowerCase();
            if (name.contains(keyword) || typeCategory.contains(keyword)) {
                results.add(liqueur);
            }
        }
        return results;
    }

    public ArrayList<Liqueur> searchLiqueurByCategory(String category) {
        ArrayList<Liqueur> results = new ArrayList<>();
        for (Liqueur liqueur : liqueurList) {
            if (liqueur.getType().equalsIgnoreCase(category)) {
                results.add(liqueur);
            }
        }
        return results;
    }

    public void addLiqueur(Liqueur liqueur) {
        liqueur.setLiqueurId(nextLiqueurId++);
        liqueurList.add(liqueur);
        saveLiqueurListToFile();
    }

    public boolean deleteLiqueur(int liqueurId) {
        Liqueur liqueur = getLiqueurById(liqueurId);
        if (liqueur != null) {
            liqueurList.remove(liqueur);
            saveLiqueurListToFile();
            return true;
        }
        return false;
    }

    public boolean updateLiqueur(int liqueurId, Liqueur updatedLiqueur) {
        Liqueur liqueur = getLiqueurById(liqueurId);
        if (liqueur != null) {
            updatedLiqueur.setLiqueurId(liqueurId); // 유지 기존 ID를
            liqueurList.set(liqueurList.indexOf(liqueur), updatedLiqueur);
            saveLiqueurListToFile();
            return true;
        }
        return false;
    }

    public int getNextLiqueurId() {
        return nextLiqueurId;
    }
}
