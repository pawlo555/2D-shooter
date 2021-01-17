package GUI.Controllers;

import GUI.MapVisualizer;
import Game.Engine;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.io.FileNotFoundException;

import static java.lang.System.exit;

public class GameController {
    @FXML MapVisualizer visualizer;
    @FXML private Button startButton;
    @FXML private Label result;
    Timeline timeline;
    Engine engine;
    Scene scene;
    EventHandler<KeyEvent> events;

    public MapVisualizer getVisualizer() {
        return visualizer;
    }

    public void startGame() {
        scene = result.getScene();
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

    public void setElements() {
        double height = startButton.getScene().getHeight();
        double width = startButton.getScene().getWidth();
        result.setLayoutX(width/2);
        result.setLayoutY(height/2-25);
        startButton.setLayoutX(width/2);
        startButton.setLayoutY(height/2-25);
    }
}
