/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g53960.demojavafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class HelloWorldTextArea
        extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My First JavaFX App");
        BorderPane root = new BorderPane();

        TextArea txaUserName = new TextArea();
        txaUserName.setPrefColumnCount(15);
        txaUserName.setPrefRowCount(3);
        txaUserName.setWrapText(true);

        Button btnPrint = new Button("Print");
        btnPrint.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println(txaUserName.getText());
            }
        });

        //Alignment
        root.setTop(txaUserName);
        BorderPane.setAlignment(txaUserName, Pos.CENTER);
        root.setCenter(btnPrint);

        Scene scene = new Scene(root, 250, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

