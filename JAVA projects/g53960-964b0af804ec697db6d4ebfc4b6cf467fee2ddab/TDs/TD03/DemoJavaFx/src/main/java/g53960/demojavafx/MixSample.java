
package g53960.demojavafx;

/**
 *
 * @author Samad
 */

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class MixSample
        extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("My First JavaFX App");
        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(200);
        GridPane root = new GridPane();

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setHgap(10);
        root.setVgap(15);

        Label lblTitle = new Label("JavaFX Course Login");
        lblTitle.setFont(Font.font("System", FontWeight.BOLD, 20));
        lblTitle.setTextFill(Color.RED);
        root.add(lblTitle, 0, 0, 2, 1);
        GridPane.setHalignment(lblTitle, HPos.CENTER);
        GridPane.setMargin(lblTitle, new Insets(0, 0, 10, 0));
        
        //position de "User Name or email" dans la grille 
        Label lblUserName = new Label("User Name or email");
        GridPane.setHalignment(lblUserName, HPos.RIGHT);
        root.add(lblUserName, 0, 1);

        TextField tfdUserName = new TextField();
        tfdUserName.setPrefColumnCount(20);
        root.add(tfdUserName, 1, 1);

        Label lblPassword = new Label("Password");
        root.add(lblPassword, 0, 2);

        PasswordField pwfPassword = new PasswordField();
        pwfPassword.setPrefColumnCount(12);
        root.add(pwfPassword, 1, 2);

        GridPane.setHalignment(lblPassword, HPos.RIGHT);
        GridPane.setFillWidth(pwfPassword, false);
        
        
        //Les bouttons "Login" et "Cancel" en utilisant HBox
        HBox btnPanel = new HBox(12);
        Button btnLogin = new Button("Login");
        Button btnCancel = new Button("Cancel");
        btnPanel.getChildren().addAll(btnLogin,btnCancel);
        btnPanel.setAlignment(Pos.CENTER_RIGHT);
        root.add(btnPanel,1,3);
        GridPane.setMargin(btnPanel, new Insets(10, 0, 0, 0));
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
