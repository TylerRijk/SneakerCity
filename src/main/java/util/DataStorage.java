package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    private String bestandsnaam;

    public DataStorage(String bestandsnaam) {
        this.bestandsnaam = bestandsnaam;
    }

    public void opslaan(Object object) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(bestandsnaam, true))) {
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Object> lezen() {
        List<Object> objects = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(bestandsnaam))) {
            while (true) {
                Object object = ois.readObject();
                objects.add(object);
            }
        } catch (EOFException e) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return objects;
    }

    public void verwijder(Object object) {
        List<Object> objects = lezen();
        objects.remove(object);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(bestandsnaam))) {
            for (Object obj : objects) {
                oos.writeObject(obj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
