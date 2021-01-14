package GUI.Controllers;

import GUI.MapVisualizer;
import Game.Initialization;
import Game.Map;
import Game.Obstacle;
import Game.Soldier;
import Utilities.Settings;
import Utilities.Vector2D;
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
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("../fxml/Game.fxml"));
        Parent gameRoot = gameLoader.load();
        GameController gameController = gameLoader.getController();
        MapVisualizer visualizer = gameController.getVisualizer();
        Initialization init = new Initialization(new Settings());
        visualizer.setMap(init.getMap());

        visualizer.Visualize();
        primaryStage.setScene(new Scene(gameRoot, 700, 600));

    }

    public void setStage(Stage currentStage) {
        primaryStage = currentStage;
    }
}
