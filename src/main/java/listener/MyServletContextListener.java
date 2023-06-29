package listener;

import model.*;
import persistence.PersistenceKlant;
import persistence.PersistenceMedewerker;
import persistence.PersistenceSneaker;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Standaard sneakers instellen
        Sneaker sneaker1 = new Sneaker(1, "Adidas", "Zwart", 44, "Ultra Boost Oreo", 199.99, "oreo.png", 1);
        PersistenceSneaker.saveSneaker(sneaker1);

        Sneaker sneaker2 = new Sneaker(2, "Jordan", "Rood", 45, "Chicago Lost and Found", 374.99, "jordanChicago.png", 1);
        PersistenceSneaker.saveSneaker(sneaker2);

        Sneaker sneaker3 = new Sneaker(3, "Yeezy", "Oranje", 45, "Boost 350 V2 MX Oat", 399.99, "yeezyOat.png", 1);
        PersistenceSneaker.saveSneaker(sneaker3);

        Sneaker sneaker4 = new Sneaker(4, "Nike", "Bruin", 43, "Air Max 1 Travis Scott Cactus", 299.99, "nikeTravisScott.png", 1);
        PersistenceSneaker.saveSneaker(sneaker4);

        Sneaker sneaker5 = new Sneaker(5, "Yeezy", "Grijs", 44, "Boost 350 V2 Carbon Beluga", 249.99, "yeezyCarbon.png", 1);
        PersistenceSneaker.saveSneaker(sneaker5);

        Sneaker sneaker6 = new Sneaker(6, "Adidas", "Oranje", 45, "NMD Pharell Pale Nude", 219.99, "adidasNMD.png", 1);
        PersistenceSneaker.saveSneaker(sneaker6);

        Sneaker sneaker7 = new Sneaker(7, "Nike", "Wit", 43, "Air Force 1 Mid Off-White Graffiti", 249.99, "nikeOffWhite.png", 1);
        PersistenceSneaker.saveSneaker(sneaker7);

        Sneaker sneaker8 = new Sneaker(8, "Adidas", "Rood", 45, "Ozweego 3 Raf Simons Burgundy", 339.99, "adidasRafSimons.png", 1);
        PersistenceSneaker.saveSneaker(sneaker8);

        Sneaker sneaker9 = new Sneaker(9, "Jordan", "Geel", 44, "4 Retro Thunder", 249.99, "jordanThunder.png", 1);
        PersistenceSneaker.saveSneaker(sneaker9);

        Sneaker sneaker10 = new Sneaker(10, "Vans", "Oranje", 42, "SK8-Hi One Piece Nami", 94.99, "vansNami.png", 1);
        PersistenceSneaker.saveSneaker(sneaker10);

        Sneaker sneaker11 = new Sneaker(11, "Nike", "Wit", 43, "Air Force 1 Low Supreme", 159.99, "nikeSupreme.png", 1);
        PersistenceSneaker.saveSneaker(sneaker11);

        Sneaker sneaker12 = new Sneaker(12, "Yeezy", "Wit", 44, "Boost 350 V2 Zebra", 279.99, "yeezyZebra.png", 1);
        PersistenceSneaker.saveSneaker(sneaker12);

        // Klant en admin account instellen
        Klant testKlant = new Klant("Test", "Test", "klant", 1, "testKlant@klant.nl");
        Medewerker admin = new Medewerker("Tyler", "Tyler", "admin", 0, "admin@admin.nl");

        PersistenceKlant.saveKlant(testKlant);
        PersistenceMedewerker.saveMedewerker(admin);

        // Alle gebruikers ophalen
        User.loadUsersFromFiles();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
