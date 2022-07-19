package statki.dao;

import org.hibernate.SessionFactory;
import statki.model.ShipShipsGame;

public class ShipShipsDao extends EntityDao<ShipShipsGame> {
    public ShipShipsDao(SessionFactory sessionFactory) {
        super(sessionFactory, ShipShipsGame.class);
    }
}
