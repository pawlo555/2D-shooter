package gui;

import gui.controllers.OptionsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Options.fxml"));
        Parent root = loader.load();
        OptionsController optionsController = loader.getController();
        optionsController.setStage(primaryStage);
        primaryStage.setTitle("2DShooter");
        Scene optionsScene = new Scene(root, 600, 450);
        primaryStage.setScene(optionsScene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
