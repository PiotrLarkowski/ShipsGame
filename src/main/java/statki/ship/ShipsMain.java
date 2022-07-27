package statki.ship;

import statki.model.BoardShipsGame;
import statki.model.PointShipsGame;
import statki.model.UserShipsGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShipsMain {

    private MessagesPrinter messagesPrinter = new MessagesPrinter();
    private Scanner scanner = new Scanner(System.in);

    public List<PointShipsGame> userOneBusyPointsOnBoard = new ArrayList<>();
    public List<PointShipsGame> userTwoBusyPointsOnBoard = new ArrayList<>();
    public UserShipsGame userOneName;
    public UserShipsGame userTwoName;
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
                    BoardShipsGame boardShipsGame = new BoardShipsGame();

                  setNewPlayboard();

                    boardShipsGame.setPlayBoardUserOne(userOneBusyPointsOnBoard);
                    boardShipsGame.setUserOne(userOneName);

                    messagesPrinter.printSingleLine("Tworzenie drugiego gracza");
                   setNewPlayboard();

                    boardShipsGame.setPlayBoardUserTwo(userTwoBusyPointsOnBoard);
                    boardShipsGame.setUserTwo(userTwoName);
                    ShipService shipService = new ShipService();
                    shipService.addBoardShipsGameToDataBase(boardShipsGame);

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
            ShipService shipService = new ShipService();
            messagesPrinter.creatingNewUser();
            shipService.creatingNewUser();
            messagesPrinter.placingTheShips();
            shipService.creatingShip();
        }
    }



    private void beginOfTheGame() {
        //TODO stworzenie rozgrywki
    }
}
