package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.LifeStyle;

/**
 * * The InputGrid class represents a grid containing input fields for user
 * data. It includes fields for size, weight, age, sex, and lifestyle.
 *
 * @author Samad
 */
public class InputGrid extends GridPane {

    private final TextField size;
    private final TextField weight;
    private final TextField year;
    private final RadioButton women;
    private final RadioButton man;
    private final ChoiceBox<LifeStyle> lifestyle;

    /**
     * Constructor for InputGrid class.
     * Creates an InputGrid object with default values for all fields.
     */
    public InputGrid() {
        size = new TextField();
        weight = new TextField();
        year = new TextField();
        women = new RadioButton("Femme");
        man = new RadioButton("Homme");
        lifestyle = new ChoiceBox<>();
        viewInputGrid();

    }

    /**
     * Sets the layout and content for the input grid.
     */
    private void viewInputGrid() {
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_CENTER);
        setHgap(10);
        setVgap(15);// la distance verticale entre chaque ligne
        Label firstTitle = new Label("Données");
        firstTitle.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        firstTitle.setUnderline(true);
        add(firstTitle, 0, 0);

        Label sizeLabel = new Label("Taille (cm) ");
        add(sizeLabel, 0, 3);
        size.setPromptText("Taille en cm");
        size.setEditable(true);
        add(size, 1, 3);

        Label weightLabel = new Label("Poids (kg) ");
        add(weightLabel, 0, 4);
        weight.setPromptText("Poids en kg");
        weight.setEditable(true);
        add(weight, 1, 4);

        Label yearLabel = new Label("Age (années) ");
        add(yearLabel, 0, 6);
        year.setPromptText("Age en années ");
        year.setEditable(true);
        add(year, 1, 6);

        Label sexLabel = new Label("Sexe ");
        add(sexLabel, 0, 8);
        radioButton();

        Label lifestyleLabel = new Label("Style de vie ");
        add(lifestyleLabel, 0, 10);
        lifestyle.getItems().setAll(LifeStyle.values());
        add(lifestyle, 1, 10);

    }

    /**
     * Sets up the radio buttons for selecting sex
     */
    public void radioButton() {
        // Créer un groupe de boutons pour s'assurer qu'un seul bouton est sélectionné
        ToggleGroup sexToggleGroup = new ToggleGroup();
        women.setToggleGroup(sexToggleGroup);
        man.setToggleGroup(sexToggleGroup);

        HBox womenManButton = new HBox(); // Créer VBOX ou HBOX si on utilise un radiobutton
        womenManButton.getChildren().add(women);
        womenManButton.getChildren().add(man);

        add(womenManButton, 1, 8);

    }

    /**
     * Gets the size input field.
     *
     * @return the size input field.
     */
    public TextField getSize() {
        return size;
    }

    /**
     * Gets the weight input field.
     *
     * @return the weight input field.
     */
    public TextField getWeight() {
        return weight;
    }

    /**
     * Gets the age input field.
     *
     * @return the age input field.
     */
    public TextField getYear() {
        return year;
    }

    /**
     * Gets the choice box for selecting lifestyle.
     *
     * @return the choice box for selecting lifestyle.
     */
    public ChoiceBox<LifeStyle> getLifestyle() {
        return lifestyle;
    }

    /**
     * Checks if the woman is selected
     *
     * @return true if the woman is selected, otherwise false
     */
    public boolean isWomanSelected() {
        return women.isSelected();
    }

}
