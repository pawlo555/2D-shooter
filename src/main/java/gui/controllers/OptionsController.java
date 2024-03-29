package gui.controllers;
import gui.MapVisualizer;
import game.*;
import utilities.Settings;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        initEngine.addObserver(visualizer);
        initEngine.addObserver(gameController);
        primaryStage.setScene(new Scene(gameRoot, initMap.getWidth(), initMap.getHeight()+ 50));
        gameController.setElements(init.isPVPorPVE());
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
