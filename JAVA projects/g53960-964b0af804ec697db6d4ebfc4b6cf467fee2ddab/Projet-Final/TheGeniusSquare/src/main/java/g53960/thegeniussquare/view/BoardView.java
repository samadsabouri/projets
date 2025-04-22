package g53960.thegeniussquare.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * The graphical representation of the game board. Displays a grid of
 * SquareBoardView objects to represent the squares on the board. Also includes
 * labels for rows (A-F) and columns (1-6).
 *
 * @author Samad
 */
public class BoardView extends GridPane {

    private final SquareBoardView[][] rectangles;

    /**
     * Constructs a new BoardView object. Initializes the grid of
     * SquareBoardView objects and sets up the labels for rows and columns.
     */
    public BoardView() {
        this.rectangles = new SquareBoardView[6][6];

        createLabelsForRows();
        createLabelForColumns();
        CreatingSquares();
        setAlignementNumbersCol();
    }

    /**
     * Creates the labels for the rows (A-F) and adds them to the grid.
     */
    private void createLabelsForRows() {
        // Création des étiquettes pour les lignes (A-F)
        for (int row = 0; row < 6; row++) {
            Label label = new Label(Character.toString((char) ('A' + row)));
            label.setPrefWidth(50);
            label.setAlignment(Pos.CENTER);
            add(label, 0, row + 1);
        }
    }

    /**
     * Creates the labels for the columns (1-6) and adds them to the grid.
     */
    private void createLabelForColumns() {
        // Création des étiquettes pour les colonnes (1-6)
        for (int col = 0; col < 6; col++) {
            Label label = new Label("      " + Integer.toString(col + 1));
            label.setPrefHeight(50);
            label.setAlignment(Pos.CENTER);
            add(label, col + 1, 0);
        }
    }

    /**
     * Sets the alignment of the numbers 1-6 in relation to the squares.
     */
    private void setAlignementNumbersCol() {
        // Centrage des chiffres de 1 à 6 par rapport aux cases
        for (int i = 1; i < 7; i++) {
            Label label = (Label) getChildren().get(i);
            label.setAlignment(Pos.CENTER);
        }
    }

    /**
     * Creates the squares and adds them to the grid.
     */
    private void CreatingSquares() {
        // Création des cases
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {

                SquareBoardView rectangle = new SquareBoardView();
                rectangle.setMinSize(45, 45);
                rectangle.setMaxSize(45, 45);
                rectangle.setFocusTraversable(false);
                this.setMargin(rectangle, new Insets(1));
                rectangles[row][col] = rectangle;
                this.setAlignment(Pos.CENTER);
                add(rectangle, col + 1, row + 1);

            }
        }
    }

    /**
     * Retrieves the 2D array of SquareBoardView objects representing the board.
     *
     * @return The board as a 2D array of SquareBoardView objects.
     */
    public SquareBoardView[][] getBoard() {
        return rectangles;
    }

}
