/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g53960.demojavafx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 *
 * @author Samad
 */
public class HelloWorldTextField extends Application {
    
     public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My First JavaFX App");
        BorderPane root = new BorderPane();

        Label userName = new Label("User Name");
        TextField tfdUserName = new TextField();
        tfdUserName.setPrefColumnCount(15);
        tfdUserName.setAlignment(Pos.CENTER_LEFT);
        
//        PasswordField tfdUserName = new PasswordField();
//        tfdUserName.setPrefColumnCount(15);
//        tfdUserName.setAlignment(Pos.CENTER_LEFT);
        
        //Alignment
        root.setTop(userName);
        BorderPane.setAlignment(userName, Pos.CENTER);
        root.setCenter(tfdUserName);
        
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
}
