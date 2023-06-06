package persistence;

import model.Medewerker;

import java.io.*;
import java.util.ArrayList;

public class PersistenceMedewerker {
    private static final String PERSISTENCE_DIRECTORY = "medewerkers";
    private static final String FILE_EXTENSION = ".obj";

    public static void saveMedewerker(Medewerker medewerker) {
        File dir = new File(PERSISTENCE_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = PERSISTENCE_DIRECTORY + "/medewerker" + medewerker.getId() + FILE_EXTENSION;
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(medewerker);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Medewerker loadMedewerker(int medewerkerID) {
        String fileName = PERSISTENCE_DIRECTORY + "/medewerker" + medewerkerID + FILE_EXTENSION;
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Medewerker) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Medewerker> loadAllMedewerkers() {
        ArrayList<Medewerker> medewerkers = new ArrayList<>();
        File directory = new File(PERSISTENCE_DIRECTORY);
        if (!directory.exists()) {
            return medewerkers;
        }

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
