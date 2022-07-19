package statki.dao;

import org.hibernate.SessionFactory;
import statki.model.BoardShipsGame;

public class BoardShipsDao extends EntityDao<BoardShipsGame> {

    public BoardShipsDao(SessionFactory sessionFactory) {
        super(sessionFactory, BoardShipsGame.class);
    }
}
