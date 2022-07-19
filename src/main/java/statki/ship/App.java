package statki.ship;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import statki.Hibernate.HibernateFactory;


public class App {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new HibernateFactory().getSessionFactory();
        Session session = sessionFactory.openSession();

        ShipsMain shipsMain = new ShipsMain();
        shipsMain.start();

        session.close();
        sessionFactory.close();
    }
}
