package g53960.client;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class ContactFormPane extends VBox {

    private final TextField nameField = new TextField();
    private final TextArea messageArea = new TextArea();
    private final Label resultLabel = new Label();
    private final ContactService contactService = new ContactService();

    public ContactFormPane() {
        setSpacing(10);
        setPadding(new Insets(10));

        Label nameLabel = new Label("Nom :");
        Label messageLabel = new Label("Message :");

        Button sendButton = new Button("Envoyer");
        sendButton.setOnAction(e -> sendData());

        getChildren().addAll(
            nameLabel, nameField,
            messageLabel, messageArea,
            sendButton, resultLabel
        );
    }

    private void sendData() {
        String name = nameField.getText();
        String message = messageArea.getText();
        String response = contactService.sendMessage(name, message);
        resultLabel.setText(response);
    }
}
