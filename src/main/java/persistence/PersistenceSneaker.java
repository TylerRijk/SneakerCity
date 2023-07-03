package persistence;

import model.Sneaker;

import java.io.*;
import java.util.ArrayList;

public class PersistenceSneaker {
    private static final String PERSISTENCE_DIRECTORY = "sneakers";
    private static final String FILE_EXTENSION = ".obj";


    public static void saveSneaker(Sneaker sneaker) {
        // Directory instellen
        File dir = new File(PERSISTENCE_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Sneaker opslaan als .obj
        String fileName = PERSISTENCE_DIRECTORY + "/sneaker" + sneaker.getArtikelnummer() + FILE_EXTENSION;
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(sneaker);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Sneaker loadSneaker(int artikelnummer) {
        // Sneaker ophalen van bijbehorende artikelnummer
        String fileName = PERSISTENCE_DIRECTORY + "/sneaker" + artikelnummer + FILE_EXTENSION;
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }

        // Bestand lezen en deserialiseren om de sneaker terug te geven
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Sneaker) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Sneaker> loadAllSneakers() {
        ArrayList<Sneaker> sneakers = new ArrayList<>();
        // Directory ophalen
        File directory = new File(PERSISTENCE_DIRECTORY);
        if (!directory.exists()) {
            return sneakers;
        }

        // Alle bestanden lezen en toevoegen aan de lijst met alle sneakers in het systeem
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

    public static void deleteSneaker(int artikelnummer) {
        // Sneaker ophalen van bijbehorende artikelnummer
        String fileName = PERSISTENCE_DIRECTORY + "/sneaker" + artikelnummer + FILE_EXTENSION;
        // Bestand verwijderen als deze bestaat
        File file = new File(fileName);
        if(file.exists()) {
            file.delete();
        }
    }
}
