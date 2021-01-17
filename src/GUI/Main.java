package GUI;

import GUI.Controllers.OptionsController;
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
        System.out.println(optionsController);
        optionsController.setStage(primaryStage);
        primaryStage.setTitle("Strzelanka2D");
        primaryStage.setScene(new Scene(root, 500, 200));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
