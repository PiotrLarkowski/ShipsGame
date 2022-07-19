package statki.dao;

import org.hibernate.SessionFactory;
import statki.model.UserShipsGame;

public class UserShipsDao extends EntityDao<UserShipsGame> {
    public UserShipsDao(SessionFactory sessionFactory) {
        super(sessionFactory, UserShipsGame.class);
    }
}
