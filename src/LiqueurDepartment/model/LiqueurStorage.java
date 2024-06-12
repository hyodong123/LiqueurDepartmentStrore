package LiqueurDepartment.model;

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
            br.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    private void saveLiqueurListToFile() throws IOException {
        FileWriter fw;
        try {
            fw = new FileWriter(liqueurFilename);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Liqueur liqueur : liqueurList) {
                bw.write(String.valueOf(liqueur.getLiqueurId()));
                bw.newLine();
                bw.write(liqueur.getLiqueurName());
                bw.newLine();
                bw.write(liqueur.getTypeCategory());
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
            bw.close();
        } catch (IOException e) {
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

 // 이름 또는 종류로 검색
    public ArrayList<Liqueur> searchLiqueurByNameOrType(String keyword) {
        ArrayList<Liqueur> results = new ArrayList<>();
        keyword = keyword.toLowerCase(); // 대소문자 구분 없이 검색
        for (Liqueur liqueur : liqueurList) {
            String name = liqueur.getName().toLowerCase();
            String typeCategory = liqueur.getTypeCategory().toLowerCase();
            if (name.contains(keyword) || typeCategory.contains(keyword)) {
                results.add(liqueur);
            }
        }
        return results;
    }

    public ArrayList<Liqueur> searchLiqueurByCategory(String category) {
        ArrayList<Liqueur> results = new ArrayList<>();
        for (Liqueur liqueur : liqueurList) {
            if (liqueur.getTypeCategory().equalsIgnoreCase(category)) {
                results.add(liqueur);
            }
        }
        return results;
    }

    public void addLiqueur(Liqueur liqueur) {
        liqueur.setLiqueurId(findNextAvailableId());
        liqueurList.add(liqueur);
        try {
            saveLiqueurListToFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private int findNextAvailableId() {
        Set<Integer> usedIds = new HashSet<>();
        for (Liqueur liqueur : liqueurList) {
            usedIds.add(liqueur.getLiqueurId());
        }
        int id = 1;
        while (usedIds.contains(id)) {
            id++;
        }
        return id;
    }

    public boolean deleteLiqueur(int liqueurId) {
        Liqueur liqueur = getLiqueurById(liqueurId);
        if (liqueur != null) {
            liqueurList.remove(liqueur);
            try {
                saveLiqueurListToFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return true;
        }
        return false;
    }

    public boolean updateLiqueur(int liqueurId, Liqueur updatedLiqueur) {
        Liqueur liqueur = getLiqueurById(liqueurId);
        if (liqueur != null) {
            updatedLiqueur.setLiqueurId(liqueurId); // 유지 기존 ID를
            liqueurList.set(liqueurList.indexOf(liqueur), updatedLiqueur);
            try {
                saveLiqueurListToFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return true;
        }
        return false;
    }

    public int getNextLiqueurId() {
        return findNextAvailableId();
    }
}
