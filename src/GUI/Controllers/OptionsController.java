package GUI.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class OptionsController {

    @FXML private Button nextButton;
    @FXML private ToggleGroup versionButtons;
    @FXML private ToggleGroup firstOpponentsButtons;
    @FXML private ToggleGroup secondOpponentsButtons;
    @FXML private ToggleGroup thirdOpponentsButtons;

    private Stage primaryStage;

    public void startGame() throws IOException {
        System.out.println("Start a game");
        Parent gameRoot = FXMLLoader.load(getClass().getResource("/GUI/fxml/Game.fxml"));
        primaryStage.setScene(new Scene(gameRoot, 300, 275));
    }

    public void setStage(Stage currentStage) {
        primaryStage = currentStage;
    }
}
