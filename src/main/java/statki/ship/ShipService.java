package statki.ship;

import org.hibernate.SessionFactory;
import statki.Hibernate.HibernateFactory;
import statki.dao.PointShipsDao;
import statki.dao.ShipShipsDao;
import statki.model.PointShipsGame;
import statki.model.ShipShipsGame;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ShipService {

    private ShipDirection shipDirectionEnum;
    private boolean isShipPositionOk = false;
    private int choiceShipSize = 1;
    protected int fourSizeShip = 1;
    protected int threeSizeShip = 2;
    protected int twoSizeShip = 3;
    protected int oneSizeShip = 4;
    public int shipDirection;
    private boolean endOfPlacesTheShips = false;
    private PointShipsGame firstPointShip;
    private int userPointY;
    private int userPointX;
    private boolean endLoopCreatingShip;
    public List<PointShipsGame> busyPointsOnBoard = new ArrayList<>();
    private MessagesPrinter messagesPrinter = new MessagesPrinter();
    private Scanner scanner = new Scanner(System.in);

    // Pokauzje infomracje ile statkow zostalo do postawienia przez uzytkownika
    public void creatingShip() {
        boolean haveShip = true;
        do {
            do {
                messagesPrinter.lastShipsToPlace(fourSizeShip, threeSizeShip, twoSizeShip, oneSizeShip);
                try { // Czy jest jeszcze dostepny wybrany statek przez uzytkownika do postawienia na planszy
                    choiceShipSize = scanner.nextInt();
                    if (choiceShipSize > 0 && choiceShipSize < 5 && checkAvilabilityShips()) {
                        placesTheSingleShip(choiceShipSize);
                        if (fourSizeShip == 0 && threeSizeShip == 0 && twoSizeShip == 0 && oneSizeShip == 0) {
                            endOfPlacesTheShips = true;
                        }
                    }
                } catch (InputMismatchException e) {
                    messagesPrinter.errorInputValue();
                }
            } while (endOfPlacesTheShips == false);
        } while (endOfPlacesTheShips == false);
    }

    private boolean checkAvilabilityShips() { // To samo co u gory
        boolean haveShip = true;
        if (choiceShipSize == 1) {
            if (oneSizeShip != 0) {
                haveShip = true;
            } else {
                haveShip = false;
                messagesPrinter.dontHaveAnyMoreShipsOfThatSize();
            }
        } else if (choiceShipSize == 2) {
            if (twoSizeShip != 0) {
                haveShip = true;
            } else {
                haveShip = false;
                messagesPrinter.dontHaveAnyMoreShipsOfThatSize();
            }
        } else if (choiceShipSize == 3) {
            if (threeSizeShip != 0) {
                haveShip = true;
            } else {
                haveShip = false;
                messagesPrinter.dontHaveAnyMoreShipsOfThatSize();
            }
        } else if (choiceShipSize == 4) {
            if (fourSizeShip != 0) {
                haveShip = true;
            } else {
                haveShip = false;
                messagesPrinter.dontHaveAnyMoreShipsOfThatSize();
            }
        }
        return haveShip;
    }

    private void placesTheSingleShip(int sizeOfShip) {
        messagesPrinter.selectedStartingPointOfCreatingShip();
        userPointX = scanner.nextInt();
        messagesPrinter.printSingleLine("Podaj numer kolumny (1 - 10): ");
        userPointY = scanner.nextInt();
        firstPointShip = new PointShipsGame(userPointX, userPointY);
        do {
            messagesPrinter.chooseShipDirection();
            shipDirection = scanner.nextInt(); // Wybieramy kierunek uiejscowanienia statku
            switch (shipDirection) {
                case 1: {
                    shipDirectionEnum = ShipDirection.isTop;
                    endLoopCreatingShip = canShipBePlaces(sizeOfShip, firstPointShip);
                    break;
                }
                case 2: {
                    shipDirectionEnum = ShipDirection.isRight;
                    endLoopCreatingShip = canShipBePlaces(sizeOfShip, firstPointShip);
                    break;
                }
                case 3: {
                    shipDirectionEnum = ShipDirection.isDown;
                    endLoopCreatingShip = canShipBePlaces(sizeOfShip, firstPointShip);
                    break;
                }
                case 4: {
                    shipDirectionEnum = ShipDirection.isLeft;
                    endLoopCreatingShip = canShipBePlaces(sizeOfShip, firstPointShip);
                    break;
                }
                default: {
                    messagesPrinter.printSingleLine("Podano bledna wartosc");
                    endLoopCreatingShip = false;
                }
            }
            if (isShipPositionOk) {
                placesShipOnPointsList(sizeOfShip, firstPointShip, shipDirectionEnum);
            }else{
                break;
            }
        } while (!endLoopCreatingShip);
    }
    
//Metoda sprawdza czy jest mozliwosc postawienia statku pod wzgleden punktu poczatkowego i dlugosci statku oraz kierunku
    private boolean canShipBePlaces(int shipSize /*1, 2, 3, 4*/, PointShipsGame point) {
        isShipPositionOk = true;
        System.out.println(shipDirectionEnum);
        switch (shipDirectionEnum) {
            case isTop: {//Stawiamy statek do gory
                //Wartosc X okresla litery od A - J (wiersze) a wartosc Y okresla cyfry od 1 - 10 (kolumny)
                if ((point.getX() - (shipSize - 1)) > 0) 
                {
                    for (int i = 0; i < busyPointsOnBoard.size(); i++) {
                        if(isShipPositionOk == false)
                        {
                            break;
                        }
                        for (int j = 0; j < shipSize; j++) {
                            PointShipsGame pointShipsGame = new PointShipsGame(point.getX() - j, point.getY());
                            if (pointShipsGame.equals(busyPointsOnBoard.get(i))) { //Czy punkt jest juz zajety
                                isShipPositionOk = false;
                                messagesPrinter.printSingleLine("W tym miejscu nie moze stac kolejny statek");
                                break;
                            } else {
                                isShipPositionOk = true;
                            }
                        }
                    }
                    break;
                } else {
                    messagesPrinter.printSingleLine("Postawienie statku niemozliwe");
                    break;
                }
            }
            case isRight: {//Stawiamy statek w prawo
                //Wartosc X okresla litery od A - J (wiersze) a wartosc Y okresla cyfry od 1 - 10 (kolumny)
                if ((point.getY() + (shipSize - 1)) > 0) // int
                {
                    for (int i = 0; i < busyPointsOnBoard.size(); i++) {
                        if(isShipPositionOk == false)
                        {
                            break;
                        }
                        for (int j = 0; j < shipSize; j++) {
                            PointShipsGame pointShipsGame = new PointShipsGame(point.getX(), point.getY() + j);
                            if (pointShipsGame.equals(busyPointsOnBoard.get(i))) { //Czy punkt jest juz zajety
                                isShipPositionOk = false;
                                messagesPrinter.printSingleLine("W tym miejscu nie może stać kolejny statek");
                                break;
                            } else {
                                isShipPositionOk = true;
                            }
                        }
                    }
                    break;
                } else {
                    messagesPrinter.printSingleLine("Postawienie statku niemozliwe");
                    break;
                }
            }
            case isDown: {//Stawiamy statek w dół
                //Wartosc X okresla litery od A - J (wiersze) a wartosc Y okresla cyfry od 1 - 10 (kolumny)
                if ((point.getX() + (shipSize - 1)) > 0) // int
                {
                    for (int i = 0; i < busyPointsOnBoard.size(); i++) {
                        if(isShipPositionOk == false)
                        {
                            break;
                        }
                        for (int j = 0; j < shipSize; j++) {
                            PointShipsGame pointShipsGame = new PointShipsGame(point.getX() + j, point.getY());
                            if (pointShipsGame.equals(busyPointsOnBoard.get(i))) { //Czy punkt jest juz zajety
                                isShipPositionOk = false;
                                messagesPrinter.printSingleLine("W tym miejscu nie może stać kolejny statek");
                                break;
                            } else {
                                isShipPositionOk = true;
                            }
                        }
                    }
                    break;
                } else {
                    messagesPrinter.printSingleLine("Postawienie statku niemozliwe");
                    break;
                }
            }
            case isLeft: {//Stawiamy statek w lewo
                if ((point.getY() - (shipSize - 1)) > 0) // int
                {
                    for (int i = 0; i < busyPointsOnBoard.size(); i++) {
                        if(isShipPositionOk == false)
                        {
                            break;
                        }
                        for (int j = 0; j < shipSize; j++) {
                            PointShipsGame pointShipsGame = new PointShipsGame(point.getX(), point.getY() - j);
                            if (pointShipsGame.equals(busyPointsOnBoard.get(i))) { //Czy punkt jest juz zajety
                                isShipPositionOk = false;
                                messagesPrinter.printSingleLine("W tym miejscu nie może stać kolejny statek");
                                break;
                            } else {
                                isShipPositionOk = true;
                            }
                        }
                    }
                    break;
                } else {
                    messagesPrinter.printSingleLine("Postawienie statku niemozliwe");
                    break;
                }
            }
        }
        if (isShipPositionOk) {
            messagesPrinter.printSingleLine("Statek zostal postawiony");
        } else {
            messagesPrinter.printSingleLine("Statek nie moze zostac umieszczony w tym miejscu");
        }
        return (isShipPositionOk);
    }

    private void placesShipOnPointsList(int shipSize, PointShipsGame point, ShipDirection shipDirectionEnum) {
        switch (shipSize) {
            case 1: {
                busyPointsOnBoard.add(point); // Dodajemy punkt do listy zajetych punktow
                oneSizeShip--;
                break;
            }
            case 2: {
                settingThePointWhichTheShipIsOccupaing(shipSize, point, shipDirectionEnum);
                twoSizeShip--;
                break;
            }
            case 3: {
                settingThePointWhichTheShipIsOccupaing(shipSize, point, shipDirectionEnum);
                threeSizeShip--;
                break;
            }
            case 4: {
                settingThePointWhichTheShipIsOccupaing(shipSize, point, shipDirectionEnum);
                fourSizeShip--;
                break;
            }
        }
//      TODO tworzenie obiektu statek
        messagesPrinter.printSingleBoard(busyPointsOnBoard);
    }

    private void settingThePointWhichTheShipIsOccupaing(int shipSize, PointShipsGame point, ShipDirection shipDirectionEnum) {
        busyPointsOnBoard.add(new PointShipsGame(point.getX(), point.getY()));
        for (int i = 0; i < shipSize-1; i++) {
            if (shipDirectionEnum == ShipDirection.isTop) {
                point.setX(point.getX() - 1);
            } else if (shipDirectionEnum == ShipDirection.isRight) {
                point.setY(point.getY() + 1);
            } else if (shipDirectionEnum == ShipDirection.isDown) {
                point.setX(point.getX() + 1);
            } else {
                point.setY(point.getY() - 1);
            }
            busyPointsOnBoard.add(new PointShipsGame(point.getX(), point.getY()));
            addPointToDataBase(new PointShipsGame(point.getX(), point.getY()));
        }

        addShipToDataBase(shipSize, point, shipDirectionEnum);

    }

    private void addShipToDataBase(int shipSize, PointShipsGame point, ShipDirection shipDirectionEnum){
        ShipShipsGame shipShipsGame = new ShipShipsGame(shipSize, point, true, shipDirectionEnum);
        SessionFactory sessionFactory = new HibernateFactory().getSessionFactory();
        ShipShipsDao shipShipsDao = new ShipShipsDao(sessionFactory);
        shipShipsDao.save(shipShipsGame);
        sessionFactory.close();
    }

    private void addPointToDataBase(PointShipsGame pointShipsGame){
        SessionFactory sessionFactory = new HibernateFactory().getSessionFactory();
        PointShipsDao pointShipsDao = new PointShipsDao(sessionFactory);
        pointShipsDao.save(pointShipsGame);
        sessionFactory.close();
    }

}
