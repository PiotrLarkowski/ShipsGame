package statki.ship;


import statki.model.PointShipsGame;

import java.util.ArrayList;
import java.util.List;

public class MessagesPrinter {

    public void printStartingMenuLabel(){
        printLineOfSeparator();
        System.out.println("----------------MENU - STATKI-------------- \n");
        printLineOfSeparator();
    }

    public void printStartingMenu(){
        printLineOfSeparator();
        System.out.println("Wybierz od 1 - 3 by rozpoczac gre \n" +
                "1 - Nowa Gra \n" +
                "2 - Wczytaj gre \n" +
                "3 - Zakoncz \n");
        printLineOfSeparator();
    }

    public void printSingleLine(String text){
        printLineOfSeparator();
        System.out.println(text);
        printLineOfSeparator();
    }

    public void haveYouPlayed(){
        printLineOfSeparator();
        System.out.println("Czy juz grales w gre statki na naszym portalu? \n" +
                "y - Tak, n - Nie");
        printLineOfSeparator();
    }

    public void creatingNewUser(){
        printLineOfSeparator();
        System.out.println("Tworzenie nowego uzytkownika\n");
        printLineOfSeparator();
        System.out.println("Podaj swoj nick w grze: \n");
    }

    public void placingTheShips(){
        printLineOfSeparator();
        System.out.println("W grze statki kazdy z graczy ma 10 statkow do rozstawienia na swojej planszy \n" +
                "Podaj rodzaj statku od 1 do 4 ktory chcesz umiescic na swojej planszy \n");

        printLineOfSeparator();
    }

    public void lastShipsToPlace(int fourSizeShip, int threeSizeShip, int twoSizeShip, int oneSizeShip){
        printLineOfSeparator();
        System.out.println("Statki do rozstawienia: \n" +
                "Wybierz rodzaj statku wybierajac przyporzadkowana im wartosc: \n" +
                "(1) Jedno-masztowce - pozostala ilosc: " + oneSizeShip + "\n" +
                "(2) Dwu-masztowce - pozostala ilosc: " + twoSizeShip + "\n" +
                "(3) Trzy-masztowce - pozostala ilosc: " + threeSizeShip + "\n" +
                "(4) Cztero-masztowce - pozostala ilosc: " + fourSizeShip + "\n");
    }

    public void printLineOfSeparator(){
        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.print("\n");
    }

    public void dontHaveAnyMoreShipsOfThatSize(){
        printLineOfSeparator();
        System.out.println("Nie posidasz juz statkow tej wielkosci do rozstawienia \n");
        printLineOfSeparator();
    }

    public void chooseShipDirection(){
        printLineOfSeparator();
        System.out.println("W ktora strone ma zostac skierowany statek? \n" +
                "1 - W gore \n" +
                "2 - W prawo \n" +
                "3 - W dol \n" +
                "4 - W lewo \n");
    }
    public void selectedStartingPointOfCreatingShip(){
        printLineOfSeparator();
        System.out.println("W jakim punkcie chcesz umiescic statek? \n" +
                "Podaj wartosc wiersza (1 - 10): ");
    }

    public void errorInputValue() {
        printLineOfSeparator();
        System.out.println("Wprowadzono bledna wartosc + \n");
        printLineOfSeparator();
    }

    public void printSingleBoard(List <PointShipsGame> bussyPoints) {
        int[][] boaardTable = new int[10][10];
        for (int i = 0; i < bussyPoints.size(); i++) {// Zamiana listy na tablice
            boaardTable[bussyPoints.get(i).getX()-1][bussyPoints.get(i).getY()-1] = 1;
        }
        System.out.println("   1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) { // Wy[isujemy tablice
            if(i!=9) {
                System.out.print(i + 1 + "  ");
            }else{
                System.out.print(i + 1 + " ");
            }
            for (int j = 0; j < 10; j++) {
                if(boaardTable[i][j] == 1){
                    System.out.print("X ");
                }else{
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}
