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
//        Sneaker sneaker1 = new Sneaker(1, "Adidas", "Zwart", 42, "Coole sneaker", 99.99, "sneaker1.png", 1);
//        Sneaker sneaker2 = new Sneaker(2, "Nike", "Wit", 44, "Sportieve sneaker", 89.99, "sneaker2.png", 1);
        Klant testKlant = new Klant("Test", "Test", "klant", 1, "testKlant@klant.nl");
        Medewerker admin = new Medewerker("Tyler", "Tyler", "admin", 0, "admin@admin.nl");


//        PersistenceSneaker.saveSneaker(sneaker1);
//        PersistenceSneaker.saveSneaker(sneaker2);

        PersistenceMedewerker.saveMedewerker(admin);

        PersistenceKlant.saveKlant(testKlant);

        User.loadUsersFromFiles();
    }
}
