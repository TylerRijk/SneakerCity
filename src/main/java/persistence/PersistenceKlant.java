package persistence;

import model.Klant;

import java.io.*;

public class PersistenceKlant {
    private static final String PERSISTENCE_DIRECTORY = "C:\\Users\\tyler\\IdeaProjects\\IPASS-SneakerCity\\src\\main\\resources\\data\\klanten";
    private static final String FILE_EXTENSION = ".obj";

    public static void saveKlant(Klant klant) {
        File dir = new File(PERSISTENCE_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = PERSISTENCE_DIRECTORY + "/klant" + klant.getID() + FILE_EXTENSION;
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
}
