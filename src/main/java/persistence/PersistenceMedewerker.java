package persistence;

import model.Medewerker;

import java.io.*;

public class PersistenceMedewerker {
    private static final String PERSISTENCE_DIRECTORY = "C:\\Users\\tyler\\IdeaProjects\\IPASS-SneakerCity\\src\\main\\resources\\data\\medewerkers";
    private static final String FILE_EXTENSION = ".obj";

    public static void saveMedewerker(Medewerker medewerker) {
        File dir = new File(PERSISTENCE_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = PERSISTENCE_DIRECTORY + "/medewerker" + medewerker.getMedewerkerID() + FILE_EXTENSION;
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
}
