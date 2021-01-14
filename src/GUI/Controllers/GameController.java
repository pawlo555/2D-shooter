package GUI.Controllers;

import GUI.MapVisualizer;
import Game.Engine;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class GameController {
    @FXML MapVisualizer visualizer;
    @FXML private Button startButton;
    Timeline timeline;
    Engine engine;
    Scene scene;

    public GameController() {

    }

    public MapVisualizer getVisualizer() {
        return visualizer;
    }

    public void startGame() {
        engine = new Engine();
        System.out.println(startButton.getScene());
        scene = startButton.getScene();
        System.out.println("Start!!");
        this.timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> engine.nextTurn()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        setPlayer1Events();
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setPlayer1Events() {
        EventHandler<KeyEvent> events = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.W && e.getEventType() == KeyEvent.KEY_PRESSED)
                    engine.wPressed();
                if (e.getCode() == KeyCode.S && e.getEventType() == KeyEvent.KEY_PRESSED)
                    engine.sPressed();
                if (e.getCode() == KeyCode.A && e.getEventType() == KeyEvent.KEY_PRESSED)
                    engine.aPressed();
                if (e.getCode() == KeyCode.D && e.getEventType() == KeyEvent.KEY_PRESSED)
                    engine.dPressed();
            }
        };
        System.out.println("Here");
        scene.addEventHandler(KeyEvent.KEY_PRESSED,events );
    }
}
