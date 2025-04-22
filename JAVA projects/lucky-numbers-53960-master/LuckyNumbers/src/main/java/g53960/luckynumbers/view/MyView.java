package g53960.luckynumbers.view;

import g53960.luckynumbers.model.Game;
import g53960.luckynumbers.model.Model;
import g53960.luckynumbers.model.Position;
import g53960.luckynumbers.model.Tile;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Represents all differnts methods which interact whith players.
 *
 * @author Samad(53960)
 */
public class MyView implements View {

    private Model model;

    public MyView(Model model) {
        this.model = model;
    }

    @Override
    public void displayWelcome() {
        System.out.println("          Welcome to the game: LUCKYNUMBERS");
        System.out.println("      The author is : SABOURI ABDESSAMAD -53960-");
        System.out.println("               Version : ITERATION 2021");
    }

    @Override
    public int askTileValueToPick() {
        System.out.println("Which value you want to pick from the face up tiles");
        int answer;
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            System.out.println("The value inputed is not a number , please input a valid number.");
            sc.next();
        }
        answer = sc.nextInt();
        if (answer < 1 || answer > 20) {
            System.out.println("This value don't exist in the face up list !");
            answer = askTileValueToPick();
        }
        return answer;
    }

    @Override
    public void displayPickedTile() {
        System.out.println("\033[46;38;1m The picked tile to place is " + this.model.getPickedTile().getValue() + "\033[0m\n");
    }

    @Override
    public void displayCurrentPlayer() {
        int currentPlayerNumber = this.model.getCurrentPlayerNumber();
        System.out.println("\033[43;38;1m The current player is number : " + (currentPlayerNumber + 1) + "\033[0m\n");

    }

    @Override
    public void displayGame() {
        int currentPlayerNumber = this.model.getCurrentPlayerNumber();
        int boardSize = this.model.getBoardSize();
        System.out.println("                  1    2    3    4");
        System.out.println("            -----------------------");
        for (int i = 0; i < boardSize; i++) {
            System.out.print("            " + (i + 1) + "|    ");
            for (int j = 0; j < boardSize; j++) {
                Position tilePosition = new Position(i, j);
                if (this.model.getTile(currentPlayerNumber, tilePosition) == (null)) {
                    System.out.print(".  " + "  ");
                } else {
                    if (this.model.getTile(currentPlayerNumber, tilePosition).getValue() < 10) {
                        System.out.print(this.model.getTile(currentPlayerNumber, tilePosition).getValue() + "    ");
                    } else {
                        System.out.print(this.model.getTile(currentPlayerNumber, tilePosition).getValue() + "   ");
                    }
                }
            }
            System.out.println("");
        }
        System.out.println("            -----------------------");
        System.out.println("");
    }

    @Override
    public void DisplayTilesToChoice(int CountFaceDownTiles, List<Tile> faceUpTiles) {
        System.out.println("The number of face down tiles available is " + CountFaceDownTiles);
        if (faceUpTiles.isEmpty()) {
            System.out.println("The deck of face up tiles is empty");
            System.out.println("");
        } else {
            System.out.println("You can choice one tile from the face up tiles :");
            for (Tile tileToDisplay : faceUpTiles) {
                System.out.print(tileToDisplay.getValue() + "  ");
            }
        }
        System.out.println("");
    }

    @Override
    public int askDropTile() {
        int answer = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("To drop the tile please input 0\nto put the tile please input 1");
        while (!sc.hasNextInt()) {
            System.out.println("The value inputed is not a number , please input a valid number.");
            sc.next();
        }
        answer = sc.nextInt();
        if (answer != 0 && answer != 1) {
            answer = askDropTile();
        }
        return answer;
    }

    @Override
    public int askChoiceToPickTile() {
        int answer = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("To pick a face down tile please input 0");
        if (model.faceUpTileCount() > 0) {
            System.out.println("To pick a face up tile please input 1");

        }
        while (!sc.hasNextInt()) {
            System.out.println("The value inputed is not a number , please input a valid number.");
            sc.next();
        }
        answer = sc.nextInt();
        if (answer != 0 && answer != 1) {
            answer = askChoiceToPickTile();
        }
        return answer;
    }

    @Override
    public void displayWinners() {
        System.out.println("The game is finsh !\n the winners are :");
        for (int i = 0; i < model.getWinners().size(); i++) {
            System.out.print((model.getWinners().get(i)) + 1 + " ");
        }
        System.out.println("");
    }

    @Override
    public int askPlayerCount() {
        Scanner sc = new Scanner(System.in);
        String value = "";
        int result = 0;
        System.out.println("Input the number of players to start the game : Min 2 Max 4");
        value = sc.nextLine();

        while (this.checkNumericalString(value) && (Integer.parseInt(value) < 2 || Integer.parseInt(value) > 4)) {
            System.out.println("The number must be included between 2 and 4");
            value = sc.nextLine();
        }
        try {
            result = Integer.parseInt(value);
        } catch (Exception e) {
            displayError("The value must be a positive number included between 2 and 4!\n");
            result = askPlayerCount();
        }

        return result;

    }

    @Override
    public int askReplayGame() {
        int answer = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("To replay another game please put 1 outherwise put 0");
        while (!sc.hasNextInt()) {
            System.out.println("The value inputed is not a number , please input a valid number.");
            sc.next();
        }
        answer = sc.nextInt();
        if (answer != 0 && answer != 1) {
            answer = askReplayGame();
        }
        return answer;
    }

    @Override
    public Position askPosition() {

        int boardSize = this.model.getBoardSize();
        int row = 0;
        int column = 0;
        Scanner sc = new Scanner(System.in);
        try {
            for (int i = 0; i < 2; i++) {
                if (i == 0) {
                    System.out.println("Input the row please : the row must be bigger than 1 and lower than " + (boardSize));
                } else {
                    System.out.println("Good!");
                    System.out.println("Input the column now please : the row must bigger than 1 and lower than " + (boardSize));
                }
                int value = sc.nextInt();

                while (value < 0 || value >= boardSize + 1) {
                    System.out.println("The number must be included between 1 and " + (boardSize));
                    value = sc.nextInt();
                }
                if (i == 0) {
                    row = value - 1;
                } else {
                    column = value - 1;
                }
            }
        } catch (InputMismatchException exception) {
            displayError("The value must be a valid number !\n");
            askPosition();
        }
        return new Position(row, column);
    }

    @Override
    public void displayError(String Message) {
        System.out.println(Message);
    }

    /**
     *     *
     * Checks if the string input is a valid number
     *
     * @param input the given string.
     * @return true if the input is in {0,1,2,3,4,5,6,7,8,9} otherwise return
     * false.
     */
    public boolean checkNumericalString(String input) {
        if (input.length() != 1) {
            return false;
        }
        switch (input) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                return true;

        }
        return false;
    }
}
