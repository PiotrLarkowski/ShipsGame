package statki.dao;

import org.hibernate.SessionFactory;
import statki.model.PointShipsGame;

public class PointShipsDao extends EntityDao<PointShipsGame>{
    public PointShipsDao(SessionFactory sessionFactory) {
        super(sessionFactory, PointShipsGame.class);
    }
}
