package com.example.contactmanagementsystem;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.css.converter.FontConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import java.util.List;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {

        //creates menu
        Menu menu = new Menu("Menu");

        //creates menu items
        MenuItem addContact = new MenuItem("Add Contact");
        MenuItem searchContact = new MenuItem("Search Contact");
        MenuItem deleteContact = new MenuItem("Delete Contact");
        MenuItem exit = new MenuItem("Exit");

        //adds menu items to menu
        menu.getItems().add(addContact);
        menu.getItems().add(searchContact);
        menu.getItems().add(deleteContact);
        menu.getItems().add(exit);

        //creates contactList label and creates listView to display contacts
        Label contactListLabel = new Label("Contact List");
        contactListLabel.setFont(Font.font("calibri", FontWeight.BOLD, 14));
        ListView contactList = new ListView();

        //creates menuBar, adds menu to menuBar, and adds menuBar to VBox
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);
        VBox vBox = new VBox(menuBar, contactListLabel, contactList);

        //add contact event handler
        addContact.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //creates labels, aligns them, and puts them in a vbox
                Label contactNameLabel = new Label("Contact Name: ");
                contactNameLabel.setAlignment(Pos.BASELINE_LEFT);
                Label phoneNumLabel = new Label("Phone Number: ");
                phoneNumLabel.setAlignment(Pos.BASELINE_LEFT);
                VBox labelVBox = new VBox(contactNameLabel, phoneNumLabel);
                labelVBox.setSpacing(20);

                //creates textfields, puts them in a vBox and aligns them
                TextField addNameTextField = new TextField();
                TextField addNumTextField = new TextField();
                Button addButton = new Button("Add");
                VBox textFieldVBox = new VBox(addNameTextField, addNumTextField, addButton);
                textFieldVBox.setSpacing(10);

                //puts vBoxes into hBoxes and aligns everything
                HBox hBox = new HBox(labelVBox, textFieldVBox);
                hBox.setSpacing(25);
                Scene addContactScene = new Scene(hBox, 300, 300);
                Stage addContactStage = new Stage();
                addContactStage.setTitle("Add a Contact");
                addContactStage.setScene(addContactScene);
                addContactStage.show();

                //add button event handling
                addButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Contact contact = new Contact();
                        contact.setName(addNameTextField.getText());
                        contact.setPhoneNumber(addNumTextField.getText());
                        contactList.getItems().add(contact.getName() + ":    " + contact.getPhoneNumber());

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Success");
                        alert.setContentText("Contact " + addNameTextField.getText() + " added!");
                        alert.showAndWait();
                        addContactStage.close();
                    }
                });
            }
        });

        //search contact event handler
        searchContact.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //creates name label and name vbox to align
                Label nameLabel = new Label("Contact Name: ");
                VBox nameLabelVBox = new VBox(nameLabel);

                //creates searchNameTextField, search button, and aligns them
                TextField searchNameTextField = new TextField();
                Button searchButton = new Button("Search");
                VBox nameTextFieldVBox = new VBox(searchNameTextField, searchButton);
                nameTextFieldVBox.setSpacing(10);

                //puts everything in and HBox and aligns it
                HBox hBox = new HBox(nameLabelVBox, nameTextFieldVBox);
                hBox.setSpacing(10);
                Scene searchContactScene = new Scene(hBox, 300, 300);
                Stage searchContactStage = new Stage();
                searchContactStage.setTitle("Search a contact");
                searchContactStage.setScene(searchContactScene);
                searchContactStage.show();

                //search button event handler
                searchButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        ObservableList<String> list = contactList.getItems();
                        for(String item : list){
                            if(item.contains(searchNameTextField.getText())) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText("Contact Found!");
                                alert.setContentText(item);
                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText("Error");
                                alert.setContentText("Contact " + searchNameTextField.getText() + " doesn't exist.");
                                alert.showAndWait();
                            }
                        }
                    }
                });
            }
        });

        //delete contact event handler
        deleteContact.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //creates name label and name vbox to align
                Label nameLabel = new Label("Contact Name: ");
                VBox nameLabelVBox = new VBox(nameLabel);

                //creates deleteNameTextField, search button, and aligns them
                TextField deleteNameTextField = new TextField();
                Button deleteButton = new Button("Delete");
                VBox nameTextFieldVBox = new VBox(deleteNameTextField, deleteButton);
                nameTextFieldVBox.setSpacing(10);

                //puts everything in and HBox and aligns it
                HBox hBox = new HBox(nameLabelVBox, nameTextFieldVBox);
                hBox.setSpacing(10);
                Scene deleteContactScene = new Scene(hBox, 300, 300);
                Stage deleteContactStage = new Stage();
                deleteContactStage.setTitle("Delete Contact");
                deleteContactStage.setScene(deleteContactScene);
                deleteContactStage.show();

                //delete button event handler
                deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        ObservableList<String> list = contactList.getItems();
                        for(String item : list) {
                            if(item.contains(deleteNameTextField.getText())){
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText("Success");
                                alert.setContentText("Contact " + deleteNameTextField.getText() + " deleted!");
                                alert.showAndWait();
                                contactList.getItems().remove(item);
                                deleteContactStage.close();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText("Error");
                                alert.setContentText("Contact " + deleteNameTextField.getText() + " doesn't exist.");
                                alert.showAndWait();
                            }
                        }
                    }
                });
            }
        });

        //exit button event handler
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });

        //creates main stage
        Scene scene = new Scene(vBox, 400, 400);
        scene.getStylesheets().add("style.css");
        stage.setTitle("Contact Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}