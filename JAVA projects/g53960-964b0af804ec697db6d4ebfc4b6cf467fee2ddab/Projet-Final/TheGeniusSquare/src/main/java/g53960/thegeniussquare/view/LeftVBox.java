package g53960.thegeniussquare.view;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static javafx.scene.layout.VBox.setMargin;
import javafx.util.Duration;

/**
 * Represents the left VBox in the game view. Extends the VBox class and
 * contains various UI elements.
 *
 * @author Samad
 */
public class LeftVBox extends VBox {

    private final Label displayInfo;
    private final BoardView board;
    private final Button buttonPlay;
    private final Button undoButton;
    private final Button redoButton;
    private final Label timerLabel;

    /**
     * Constructs a new LeftVBox object. Initializes and configures the UI
     * elements
     */
    public LeftVBox() {
        this.displayInfo = new Label("Welcome to THE GENIUS SQUARE!\n Click 'Play' to start the game.");
        displayInfo.setPrefWidth(800);
        displayInfo.setPrefHeight(100);
        displayInfo.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");
        displayInfo.setAlignment(Pos.CENTER);
        setMargin(displayInfo, new Insets(0, 0, 0, 38)); // Ajoute une marge à droite 

        this.board = new BoardView();

        this.buttonPlay = new Button("Play");
        buttonPlay.setMinSize(250, 50);
        setMargin(buttonPlay, new Insets(0, 0, 0, 38)); // Ajoute une marge à droite 

        this.undoButton = new Button("Undo");
        undoButton.setMinSize(120, 50);
        undoButton.setDisable(true);
        
        this.redoButton = new Button("Redo");
        redoButton.setMinSize(120, 50);
        redoButton.setDisable(true);

        this.timerLabel = new Label();
        timerLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
        timerLabel.setVisible(false);

        // Création d'un HBox pour les boutons Undo et Redo
        HBox undoRedoBox = new HBox(11); // Espace de 10 entre les boutons
        undoRedoBox.setAlignment(Pos.CENTER);
        undoRedoBox.getChildren().addAll(undoButton, redoButton);
        setMargin(undoRedoBox, new Insets(0, 0, 0, 40));

        this.getChildren().addAll(displayInfo, timerLabel, board, buttonPlay, undoRedoBox);

        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);
    }

    /**
     * Updates the timer label with the specified timer value.
     *
     * @param timer The timer value to be displayed.
     */
    public void updateTimerLabel(int timer) {
        this.timerLabel.setVisible(true);
            this.timerLabel.setText(Integer.toString(timer) + " s");
    }

    /**
     * Plays a win animation on the displayInfo label.
     */
    public void playWinAnimation() {
        // Sélectionnez le nœud sur lequel vous souhaitez appliquer l'animation (par exemple, displayInfo)
        Node node = displayInfo;

        // Créez une transition d'échelle
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), node);
        scaleTransition.setFromX(1.0); // Facteur d'échelle initial
        scaleTransition.setFromY(1.0); // Facteur d'échelle initial
        scaleTransition.setToX(1.5); // Facteur d'échelle final
        scaleTransition.setToY(1.5); // Facteur d'échelle final
        scaleTransition.setAutoReverse(true); // Animation de retour en arrière
        scaleTransition.setCycleCount(7); // Nombre de cycles d'animation

        // Démarrez l'animation
        scaleTransition.play();
        // Ajoutez l'emoji à la fin de la chaîne du label displayInfo
        String emoji = "\uD83C\uDFC6"; // Emoji 
            displayInfo.setText(displayInfo.getText() + " " + emoji);
    }

    /**
     * Retrieves the Undo button.
     *
     * @return The Undo button.
     */
    public Button getUndoButton() {
        return undoButton;
    }

    /**
     * Retrieves the Redo button.
     *
     * @return The Redo button.
     */
    public Button getRedoButton() {
        return redoButton;
    }

    /**
     * Retrieves the displayInfo label.
     *
     * @return The displayInfo label.
     */
    public Label getDisplayInfo() {
        return displayInfo;
    }

    /**
     * Retrieves the game board.
     *
     * @return The game board.
     */
    public SquareBoardView[][] getBoard() {
        return board.getBoard();
    }

    /**
     * Retrieves the Play button.
     *
     * @return The Play button.
     */
    public Button getButtonPlay() {
        return buttonPlay;
    }
}
