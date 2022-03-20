package com.example.banktellergui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 This class activates the BankTeller GUI through the launch() command in the main method, and by creating an FXMLLoader
 object and a Scene object in the start method that is displayed to the user through a Stage object.
 @author Karan Patel, Azaan Siddiqi
 */
public class BankTellerApplication extends Application {


    /**
     Creates an FXMLLoader object and a Scene object which is then used by the Stage object in order to display the
     BankTeller GUI.
     @param stage the Stage object that displays the Scene object that is created in the method.
     @throws IOException this exception is thrown if there is an error with the input and output operations.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BankTellerApplication.class.getResource("BankTellerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 700);
        stage.setTitle("Bank Teller");
        stage.setScene(scene);
        stage.show();
    }


    /**
     Calls the launch() method which begins the BankTeller GUI application.
     * @param args Array to store the java command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}