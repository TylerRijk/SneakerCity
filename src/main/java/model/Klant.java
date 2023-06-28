package model;

import java.util.ArrayList;

public class Klant extends User {
    private String email;
    private ArrayList<Bestelling> bestellingen;

    public Klant(String username, String password, String role, int id, String email) {
        super(username, password, role, id);
        this.email = email;
        this.bestellingen = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void voegBestellingToe(Bestelling bestelling) {
        bestellingen.add(bestelling);
    }

    public ArrayList<Bestelling> getBestellingen() {
        return bestellingen;
    }

    public static int getKlantIdByEmail(String email) {
        for (User user : alleUsers) {
            if (user instanceof Klant && ((Klant) user).getEmail().equals(email)) {
                return user.getId();
            }
        }
        return -1;
    }

//    @Override
//    public boolean equals(Object andereObject) {
//        boolean gelijkeObjecten = false;
//        if (andereObject instanceof Klant) {
//            Klant andereKlant = (Klant) andereObject;
//            if (andereKlant.getId() == this.id) {
//                gelijkeObjecten = true;
//            }
//        }
//        return gelijkeObjecten;
//    }
}
