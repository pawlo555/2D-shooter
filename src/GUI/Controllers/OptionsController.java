package GUI.Controllers;

import GUI.MapVisualizer;
import Game.*;
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

    @FXML private ToggleGroup versionButtons;
    @FXML private ToggleGroup mapChanger;
    @FXML private ToggleGroup firstOpponentsButtons;
    @FXML private ToggleGroup secondOpponentsButtons;
    @FXML private ToggleGroup thirdOpponentsButtons;
    @FXML private RadioButton firstAidKit;
    @FXML private RadioButton fasterReload;
    @FXML private RadioButton strongerBullets;
    @FXML private RadioButton fasterMovement;


    private Stage primaryStage;

    public void startGame() throws IOException {
        System.out.println("Start a game");
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("../fxml/Game.fxml"));
        Parent gameRoot = gameLoader.load();
        GameController gameController = gameLoader.getController();
        Initialization init = new Initialization(new Settings(this));
        Engine initEngine = init.getEngine();
        Map initMap = init.getMap();
        MapVisualizer visualizer = gameController.getVisualizer();
        visualizer.setMap(initMap);
        gameController.setEngine(initEngine);
        gameController.setEvents(init.getEventsToHandle());
        initEngine.setVisualizer(visualizer);
        primaryStage.setScene(new Scene(gameRoot, 700, 600));
    }

    public void setStage(Stage currentStage) {
        primaryStage = currentStage;
    }

    public ToggleGroup getMapChanger() {
        return mapChanger;
    }

    public ToggleGroup getFirstOpponentsButtons() {
        return firstOpponentsButtons;
    }

    public ToggleGroup getSecondOpponentsButtons() {
        return secondOpponentsButtons;
    }

    public ToggleGroup getThirdOpponentsButtons() {
        return thirdOpponentsButtons;
    }

    public RadioButton getFasterReload() {
        return fasterReload;
    }

    public RadioButton getFirstAidKit() {
        return firstAidKit;
    }

    public RadioButton getStrongerBullets() {
        return strongerBullets;
    }

    public ToggleGroup getVersionButtons() {
        return versionButtons;
    }

    public RadioButton getFasterMovement() {
        return fasterMovement;
    }
}
