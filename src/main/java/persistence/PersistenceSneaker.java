package persistence;

import model.Sneaker;

import java.io.*;
import java.util.ArrayList;

public class PersistenceSneaker {
    private static final String PERSISTENCE_DIRECTORY = "C:\\Users\\tyler\\IdeaProjects\\IPASS-SneakerCity\\src\\main\\resources\\data\\sneakers";
    private static final String FILE_EXTENSION = ".obj";

    public static void saveSneaker(Sneaker sneaker) {
        File dir = new File(PERSISTENCE_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = PERSISTENCE_DIRECTORY + "/sneaker" + sneaker.getArtikelnummer() + FILE_EXTENSION;
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(sneaker);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Sneaker loadSneaker(int artikelnummer) {
        String fileName = PERSISTENCE_DIRECTORY + "/sneaker" + artikelnummer + FILE_EXTENSION;
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Sneaker) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Sneaker> loadAllSneakers() {
        ArrayList<Sneaker> sneakers = new ArrayList<>();
        File directory = new File(PERSISTENCE_DIRECTORY);
        if (!directory.exists()) {
            return sneakers;
        }

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                    Sneaker sneaker = (Sneaker) inputStream.readObject();
                    sneakers.add(sneaker);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return sneakers;
    }

    public static String getPersistenceDirectory(int artikelnummer) {
        return PERSISTENCE_DIRECTORY + "\\sneaker" + artikelnummer;
    }

    private static String getAbsolutePath() {
        String currentDirectory = System.getProperty("user.dir");
        return currentDirectory + File.separator + PERSISTENCE_DIRECTORY;
    }
}
