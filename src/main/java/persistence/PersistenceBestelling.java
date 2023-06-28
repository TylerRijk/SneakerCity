package persistence;

import model.Bestelling;

import java.io.*;
import java.util.ArrayList;

public class PersistenceBestelling {
    private static final String PERSISTENCE_DIRECTORY = "bestelling";
    private static final String FILE_EXTENSION = ".obj";

    public static void saveBestelling(Bestelling bestelling) {
        File dir = new File(PERSISTENCE_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = PERSISTENCE_DIRECTORY + "/bestelling" + bestelling.getBestellingId() + FILE_EXTENSION;
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(bestelling);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bestelling loadBestelling(int bestellingId) {
        String fileName = PERSISTENCE_DIRECTORY + "/bestelling" + bestellingId + FILE_EXTENSION;
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Bestelling) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Bestelling> loadAllBestellingen() {
        ArrayList<Bestelling> bestellingen = new ArrayList<>();
        File directory = new File(PERSISTENCE_DIRECTORY);
        if (!directory.exists()) {
            return bestellingen;
        }

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                    Bestelling bestelling = (Bestelling) inputStream.readObject();
                    bestellingen.add(bestelling);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return bestellingen;
    }
}
