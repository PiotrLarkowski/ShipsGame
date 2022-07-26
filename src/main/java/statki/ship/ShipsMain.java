package statki.ship;

import org.hibernate.SessionFactory;
import statki.Hibernate.HibernateFactory;
import statki.dao.UserShipsDao;
import statki.model.UserShipsGame;

import java.util.Scanner;

public class ShipsMain {

    private MessagesPrinter messagesPrinter = new MessagesPrinter();
    private Scanner scanner = new Scanner(System.in);
    private String userName;
    private boolean mainLoop = true;
    private String choice = "";
    private String choiceUser = "";

    public void start() {

        messagesPrinter.printStartingMenuLabel();

        do {
            messagesPrinter.printStartingMenu();
            choice = scanner.next();
            switch (choice) {
                case "1": {
                    setNewPlayboard();
                    messagesPrinter.printSingleLine("Tworzenie drugiego gracza");
                    setNewPlayboard();
                    messagesPrinter.printSingleLine("Rozpoczynam rozgrywke!!!");
                    beginOfTheGame();
                    break;
                }
                case "2": {
                    //TODO wyswietlanie list gier z gazy danych
                    //TODO jezeli lista gier z bazy danych będzie różna od 0 możliwość wyboru gry przez uzytkownika
                    messagesPrinter.printSingleLine("Wczytywanie");
                    beginOfTheGame();
                    break;
                }
                case "3": {
                    mainLoop = false;
                    break;
                }
                default: {
                    messagesPrinter.printSingleLine("Podano błedna wartosc");
                    break;
                }
            }
        } while (mainLoop == true);
        messagesPrinter.printSingleLine("Koniec gry");
    }

    private void setNewPlayboard() {
        messagesPrinter.haveYouPlayed();
        choiceUser = scanner.next();
        if (choiceUser.equals("y")) {
            //TODO wyswietlanie listy uzytkownikow z bazy danych
            //TODO jezeli lista uzytkownikow z bazy danych będzie różna od 0 możliwość wyboru gry przez uzytkownika
        } else {
            messagesPrinter.creatingNewUser();
            creatingNewUser();
            messagesPrinter.placingTheShips();
            ShipService shipService = new ShipService();
            shipService.creatingShip();
            //TODO tworzenie nowego statku
        }
    }

    private void creatingNewUser() {
        userName = scanner.next();
        UserShipsGame userShipsGame = new UserShipsGame(userName);
        userShipsGame = new UserShipsGame(userName);
        SessionFactory sessionFactory = new HibernateFactory().getSessionFactory();
        UserShipsDao userShipsDao = new UserShipsDao(sessionFactory);
        userShipsDao.save(userShipsGame);
        sessionFactory.close();

    }

    private void beginOfTheGame() {
        //TODO stworzenie rozgrywki
    }
}
