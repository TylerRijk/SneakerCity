package listener;

import model.*;
import persistence.PersistenceSneaker;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Medewerker medewerker1 = new Medewerker(1, "admin@sneakercity.nl", "admin");
        Sneaker sneaker1 = new Sneaker(1, "Adidas", "Zwart", 42, "Coole sneaker", 99.99, "sneaker1.png", 1);
        Sneaker sneaker2 = new Sneaker(2, "Nike", "Wit", 44, "Sportieve sneaker", 89.99, "sneaker2.png", 1);
        Klant klant1 = new Klant(1, "tyler@test.nl", "tyler");

        PersistenceSneaker.saveSneaker(sneaker1);
        PersistenceSneaker.saveSneaker(sneaker2);
    }
}
