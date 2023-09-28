
package g53960.demojavafx;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author Samad
 */
public class HelloWorld extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("My First JavaFX App");
       primaryStage.initStyle(StageStyle.TRANSPARENT);
        
        BorderPane root = new BorderPane();
        Text helloText = new Text("Hello World");
        Text BienvenuedText = new Text("Bienvenue");
        
        root.setCenter(helloText);
        root.setTop(BienvenuedText);
        
        
        Scene scene = new Scene(root, 300,300);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    
}
