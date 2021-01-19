package GUI.Controllers;

import GUI.MapVisualizer;
import Game.Engine;
import Utilities.EngineObserver;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static java.lang.System.exit;

public class GameController implements EngineObserver {
    @FXML MapVisualizer visualizer;
    @FXML private Button startButton;
    @FXML private Label result;
    Timeline timeline;
    Engine engine;
    Scene scene;
    EventHandler<KeyEvent> events;

    @FXML private HBox soldiersHP;
    @FXML private Label player1HP;
    @FXML private Label player2HP;
    @FXML private Label bot1HP;
    @FXML private Label bot2HP;
    @FXML private Label bot3HP;

    public MapVisualizer getVisualizer() {
        return visualizer;
    }

    public void startGame() {
        scene = startButton.getScene();
        System.out.println(startButton.getScene());
        setEvents();
        startButton.setVisible(false);
        this.timeline = new Timeline(new KeyFrame(
            Duration.millis(100),
            ae -> {
                 try {
                      engine.nextTurn();
                 } catch (FileNotFoundException e) {
                      e.printStackTrace();
                      exit(0); }
                 catch (IllegalStateException e) {
                     timeline.pause();
                     setResults(e.getMessage());
                     System.out.println("Game ended");
                 }
            }
            ));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
            startButton.setDisable(true);
    }

    private void setResults(String message) {
        result.setText(message);
        result.setVisible(true);
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setEvents() {
        scene.addEventHandler(KeyEvent.KEY_PRESSED,events );
        scene.addEventHandler(KeyEvent.KEY_RELEASED,events );
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setEvents(EventHandler<KeyEvent> events) {
        this.events = events;
    }

    public void setElements(boolean PVPorPVE) {
        double height = startButton.getScene().getHeight();
        double width = startButton.getScene().getWidth();
        result.setLayoutX(width/2);
        result.setLayoutY(height/2-25);
        startButton.setLayoutX(width/2);
        startButton.setLayoutY(height/2-25);
        soldiersHP.setLayoutY(height-40);
        if (PVPorPVE) {
            player2HP.setVisible(true);
        }
        else {
            bot1HP.setVisible(true);
            bot2HP.setVisible(true);
            bot3HP.setVisible(true);
        }
    }

    @Override
    public void nextEpochRendered() {

        player1HP.setText("Player1 HP: " + engine.getPlayer1HPInfo());
        if (player2HP.isVisible()) {
            player2HP.setText("Player2 HP: " + engine.getPlayer2HPInfo());
        }
        else {
            ArrayList<String> hpInfo = engine.getBotsHPInfo();
            bot1HP.setText("Bot1 HP: " + hpInfo.get(0));
            bot2HP.setText("Bot2 HP: " + hpInfo.get(1));
            bot3HP.setText("Bot3 HP: " + hpInfo.get(2));
        }
    }
}
