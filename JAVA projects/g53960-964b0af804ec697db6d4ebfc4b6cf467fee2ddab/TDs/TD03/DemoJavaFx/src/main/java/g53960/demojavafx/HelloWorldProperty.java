
package g53960.demojavafx;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;



/**
 *
 * @author Samad
 */
public class HelloWorldProperty extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("My First JavaFX App");
        BorderPane root = new BorderPane();
        Label helloText = new Label("Hello World");
        helloText.setTextFill(Color.BROWN);
        helloText.setFont(Font.font("Verdana", 20));
        
        System.out.println("Le message du libellé est " + helloText.getText());
        System.out.println("La police du libellé est " + helloText.getFont());
        System.out.println("La couleur du libellé est " + helloText.getTextFill());
        
        root.setCenter(helloText);
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
 
    }
    
}
