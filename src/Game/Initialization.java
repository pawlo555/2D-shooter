package Game;

import GUI.MapVisualizer;
import Utilities.Settings;
import Utilities.Vector2D;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Initialization {
    private Map map;
    private Engine engine;
    private EventHandler<KeyEvent> eventsToHandle;

    public Initialization(Settings settings) {
        this.map = new Map(settings);
        this.engine = new Engine(map);
        if (settings.isPVPorPVE()) {
            InitializePVP();
        }
        else {
            InitializePVE();
        }
        SpawnSoldiers(settings);

    }

    private void SpawnSoldiers(Settings settings) {
        Soldier player1 = new Soldier(10,new Vector2D(100,200));
        map.addMovableElement(player1);
        engine.setPlayer1(player1);
    }

    private void InitializePVP() {
        System.out.println("PVP");
        setPVPEventHandler();
    }

    private void InitializePVE() {
        System.out.println("PVE");
        setPVEEventHandler();
    }

    private void setPVPEventHandler() {
        eventsToHandle = e -> {
            PressedEventsFirstPlayer(e);
            ReleasedEventsFirstPlayer(e);

            if (e.getCode() == KeyCode.W && e.getEventType() == KeyEvent.KEY_PRESSED) {
                engine.isWPressed = true;
                System.out.println("W pressed");
            }

            if (e.getCode() == KeyCode.UP && e.getEventType() == KeyEvent.KEY_PRESSED)
                engine.isUpPressed = true;
            if (e.getCode() == KeyCode.DOWN && e.getEventType() == KeyEvent.KEY_PRESSED)
                engine.isDownPressed = true;
            if (e.getCode() == KeyCode.LEFT && e.getEventType() == KeyEvent.KEY_PRESSED)
                engine.isLeftPressed = true;
            if (e.getCode() == KeyCode.RIGHT && e.getEventType() == KeyEvent.KEY_PRESSED)
                engine.isRightPressed = true;

            if (e.getCode() == KeyCode.UP && e.getEventType() == KeyEvent.KEY_RELEASED)
                engine.isWPressed = false;
            if (e.getCode() == KeyCode.DOWN && e.getEventType() == KeyEvent.KEY_RELEASED)
                engine.isSPressed = false;
            if (e.getCode() == KeyCode.LEFT && e.getEventType() == KeyEvent.KEY_RELEASED)
                engine.isAPressed = false;
            if (e.getCode() == KeyCode.RIGHT && e.getEventType() == KeyEvent.KEY_RELEASED)
                engine.isDPressed = false;
        };
    }

    private void setPVEEventHandler() {
        eventsToHandle = e -> {
            PressedEventsFirstPlayer(e);
            ReleasedEventsFirstPlayer(e);
        };
    }

    private void PressedEventsFirstPlayer(KeyEvent e) {
        if (e.getCode() == KeyCode.W && e.getEventType() == KeyEvent.KEY_PRESSED) {
            engine.isWPressed = true;
            System.out.println("W pressed");
        }
        if (e.getCode() == KeyCode.S && e.getEventType() == KeyEvent.KEY_PRESSED)
            engine.isSPressed = true;
        if (e.getCode() == KeyCode.A && e.getEventType() == KeyEvent.KEY_PRESSED)
            engine.isAPressed = true;
        if (e.getCode() == KeyCode.D && e.getEventType() == KeyEvent.KEY_PRESSED)
            engine.isDPressed = true;
    }

    private void ReleasedEventsFirstPlayer(KeyEvent e) {
        if (e.getCode() == KeyCode.W && e.getEventType() == KeyEvent.KEY_RELEASED)
            engine.isWPressed = false;
        if (e.getCode() == KeyCode.S && e.getEventType() == KeyEvent.KEY_RELEASED)
            engine.isSPressed = false;
        if (e.getCode() == KeyCode.A && e.getEventType() == KeyEvent.KEY_RELEASED)
            engine.isAPressed = false;
        if (e.getCode() == KeyCode.D && e.getEventType() == KeyEvent.KEY_RELEASED)
            engine.isDPressed = false;
    }

    public Map getMap() {
        return map;
    }

    public Engine getEngine() {
        return engine;
    }

    public EventHandler<KeyEvent> getEventsToHandle() {
        return eventsToHandle;
    }

}
