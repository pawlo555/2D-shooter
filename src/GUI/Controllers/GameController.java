package GUI.Controllers;

import GUI.MapVisualizer;
import Game.Engine;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.FileNotFoundException;

public class GameController {
    @FXML MapVisualizer visualizer;
    @FXML private Button startButton;
    Timeline timeline;
    Engine engine;
    Scene scene;
    EventHandler<KeyEvent> events;

    public MapVisualizer getVisualizer() {
        return visualizer;
    }

    public void startGame() {
        System.out.println(startButton.getScene());
        scene = startButton.getScene();
        //System.out.println("Start game");
        setEvents();
        this.timeline = new Timeline(new KeyFrame(
                Duration.millis(100),
                ae -> {
                    try {
                        //System.out.println("New Turn");
                        engine.nextTurn();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        startButton.setDisable(true);
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setEvents() {
        System.out.println("Setting Events");
        System.out.println(events);
        scene.addEventHandler(KeyEvent.KEY_PRESSED,events );
        scene.addEventHandler(KeyEvent.KEY_RELEASED,events );
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setEvents(EventHandler<KeyEvent> events) {
        this.events = events;
    }
}
