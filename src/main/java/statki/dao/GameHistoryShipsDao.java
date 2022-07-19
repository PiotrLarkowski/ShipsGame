package statki.dao;

import org.hibernate.SessionFactory;
import statki.model.GameHistoryShips;

public class GameHistoryShipsDao extends EntityDao<GameHistoryShips> {
    public GameHistoryShipsDao(SessionFactory sessionFactory) {
        super(sessionFactory, GameHistoryShips.class);
    }
}
