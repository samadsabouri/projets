/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g53960.demojavafx;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;


/**
 *
 * @author Samad
 */
public class HelloWorldCheckBox
        extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My First JavaFX App");
        BorderPane root = new BorderPane();

        CheckBox checkBox1 = new CheckBox();
        checkBox1.setText("First");
        checkBox1.setSelected(true);
        
        CheckBox checkBox2 = new CheckBox("Second");
        checkBox2.setIndeterminate(true);
        
        CheckBox checkBox3 = new CheckBox("Third");

        checkBox3.setAllowIndeterminate(true);
        
        //Alignment
        root.setLeft(checkBox1);
        BorderPane.setAlignment(checkBox1, Pos.CENTER);
        root.setCenter(checkBox2);
        root.setRight(checkBox3);
        BorderPane.setAlignment(checkBox3, Pos.CENTER);
        
        Scene scene = new Scene(root,300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

