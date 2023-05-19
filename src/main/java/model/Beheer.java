package model;

import java.util.ArrayList;

public class Beheer {
    private ArrayList<Sneaker> sneakers;
    private Medewerker medewerker;

    public Beheer(Medewerker medewerker) {
        this.medewerker = medewerker;
        this.sneakers = new ArrayList<>();
    }

    public void voegSneakerToe(Sneaker sneaker) {
        sneakers.add(sneaker);
    }

    public void verwijderSneaker(Sneaker sneaker) {
        sneakers.remove(sneaker);
    }

    public ArrayList<Sneaker> getSneakers() {
        return sneakers;
    }

    public Medewerker getMedewerker() {
        return medewerker;
    }
}
