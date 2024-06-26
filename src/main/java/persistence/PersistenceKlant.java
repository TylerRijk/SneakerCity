package persistence;

import model.Klant;

import java.io.*;
import java.util.ArrayList;

public class PersistenceKlant {
    private static final String PERSISTENCE_DIRECTORY = "klanten";
    private static final String FILE_EXTENSION = ".obj";

    public static void saveKlant(Klant klant) {
        // Directory instellen
        File dir = new File(PERSISTENCE_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Klant opslaan als .obj
        String fileName = PERSISTENCE_DIRECTORY + "/klant" + klant.getId() + FILE_EXTENSION;
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(klant);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Klant loadKlant(int id) {
        // Klant ophalen van bijbehorende id
        String fileName = PERSISTENCE_DIRECTORY + "/klant" + id + FILE_EXTENSION;
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }

        // Bestand lezen en deserialiseren om de klant terug te geven
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Klant) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Klant> loadAllKlanten() {
        ArrayList<Klant> klanten = new ArrayList<>();
        // Directory ophalen
        File directory = new File(PERSISTENCE_DIRECTORY);
        if (!directory.exists()) {
            return klanten;
        }

        // Alle bestanden lezen en toevoegen aan de lijst met alle klanten
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
