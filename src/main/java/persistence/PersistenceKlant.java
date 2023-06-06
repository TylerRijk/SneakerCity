package persistence;

import model.Klant;

import java.io.*;
import java.util.ArrayList;

public class PersistenceKlant {
    private static final String PERSISTENCE_DIRECTORY = "klanten";
    private static final String FILE_EXTENSION = ".obj";

    public static void saveKlant(Klant klant) {
        File dir = new File(PERSISTENCE_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = PERSISTENCE_DIRECTORY + "/klant" + klant.getId() + FILE_EXTENSION;
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(klant);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Klant loadKlant(int id) {
        String fileName = PERSISTENCE_DIRECTORY + "/klant" + id + FILE_EXTENSION;
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Klant) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Klant> loadAllKlanten() {
        ArrayList<Klant> klanten = new ArrayList<>();
        File directory = new File(PERSISTENCE_DIRECTORY);
        if (!directory.exists()) {
            return klanten;
        }

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                    Klant klant = (Klant) inputStream.readObject();
                    klanten.add(klant);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return klanten;
    }
}
