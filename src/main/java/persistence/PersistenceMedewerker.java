package persistence;

import model.Medewerker;

import java.io.*;
import java.util.ArrayList;

public class PersistenceMedewerker {
    private static final String PERSISTENCE_DIRECTORY = "medewerkers";
    private static final String FILE_EXTENSION = ".obj";

    public static void saveMedewerker(Medewerker medewerker) {
        // Directory instellen
        File dir = new File(PERSISTENCE_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Medewerker opslaan als .obj
        String fileName = PERSISTENCE_DIRECTORY + "/medewerker" + medewerker.getId() + FILE_EXTENSION;
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(medewerker);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Medewerker loadMedewerker(int medewerkerID) {
        // Medewerker ophalen van bijbehorende id
        String fileName = PERSISTENCE_DIRECTORY + "/medewerker" + medewerkerID + FILE_EXTENSION;
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }

        // Bestand lezen en deserialiseren om een medewerker terug te geven
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Medewerker) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Medewerker> loadAllMedewerkers() {
        ArrayList<Medewerker> medewerkers = new ArrayList<>();
        // Directory ophalen
        File directory = new File(PERSISTENCE_DIRECTORY);
        if (!directory.exists()) {
            return medewerkers;
        }

        // Alle bestanden lezen en toevoegen aan de lijst met alle medewerkers
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                    Medewerker medewerker = (Medewerker) inputStream.readObject();
                    medewerkers.add(medewerker);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return medewerkers;
    }

}
