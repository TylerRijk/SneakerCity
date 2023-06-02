package listener;

import model.*;
import persistence.PersistenceKlant;
import persistence.PersistenceMedewerker;
import persistence.PersistenceSneaker;
import security.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Sneaker sneaker1 = new Sneaker(1, "Adidas", "Zwart", 42, "Coole sneaker", 99.99, "sneaker1.png", 1);
        Sneaker sneaker2 = new Sneaker(2, "Nike", "Wit", 44, "Sportieve sneaker", 89.99, "sneaker2.png", 1);
        //Klant klant1 = new Klant(1, "tyler@test.nl", "tyler");
        //User admin = new User("Tyler", "Tyler", "admin");
        //User klant = new User("Sjaak", "Sjaak", "klant");

        Medewerker admin = new Medewerker(1, "admin@admin.nl", "Tyler", (new User("Tyler", "Tyler", "admin")));
        Klant testKlant = new Klant(1, "testKlant@klant.nl", "Mitchell", (new User("Mitchell", "Mitchell", "klant")));

        PersistenceSneaker.saveSneaker(sneaker1);
        PersistenceSneaker.saveSneaker(sneaker2);

        PersistenceMedewerker.saveMedewerker(admin);

        PersistenceKlant.saveKlant(testKlant);
    }
}
