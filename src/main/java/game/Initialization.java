package game;

import utilities.BonusType;
import utilities.Settings;
import utilities.SoldierLevel;
import utilities.Vector2D;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Initialization {
    private final Map map;
    private final Engine engine;
    private EventHandler<KeyEvent> eventsToHandle;
    private final ArrayList<BonusType> possibleBonuses = new ArrayList<>();
    private final boolean isPVPorPVE;

    public Initialization(Settings settings) {
        fillPossibleBonuses(settings);
        this.map = new Map(settings);
        this.engine = new Engine(map, this);
        addPlayer1();
        if (settings.isPVPorPVE()) {
            InitializePVP();
        }
        else {
            InitializePVE(settings);
        }
        isPVPorPVE = settings.isPVPorPVE();
    }

    private void fillPossibleBonuses(Settings settings) {
        if (settings.isFasterMovement()) {
            possibleBonuses.add(BonusType.FASTER_MOVEMENT);
        }
        if (settings.isFasterReload()) {
            possibleBonuses.add(BonusType.FASTER_RELOAD);
        }
        if (settings.isFirstAidKit()) {
            possibleBonuses.add(BonusType.FIRST_AID_KIT);
        }
        if (settings.isStrongerBullets()) {
            possibleBonuses.add(BonusType.STRONGER_BULLETS);
        }
    }

    private void addPlayer1() {
        Soldier player1 = new Soldier(SoldierLevel.MEDIUM,new Vector2D(100,250));
        map.addMovableElement(player1);
        engine.setPlayer1(player1);
    }

    private void InitializePVP() {
        setPVPEventHandler();
        Soldier player2 = new Soldier(SoldierLevel.MEDIUM,new Vector2D(500,250));
        map.addMovableElement(player2);
        engine.setPlayer2(player2);
    }

    private void InitializePVE(Settings settings) {
        addBot(settings.getOpponentsLevels(0), new Vector2D(550,100));
        addBot(settings.getOpponentsLevels(1),new Vector2D(550,200));
        addBot(settings.getOpponentsLevels(2),new Vector2D(550,300));
        setPVEEventHandler();
    }

    private void addBot(SoldierLevel level, Vector2D vector) {
        Soldier bot = new Soldier(level, vector);
        map.addMovableElement(bot);
        engine.addBot(bot);
    }

    private void setPVPEventHandler() {
        eventsToHandle = e -> {
            PressedEventsFirstPlayer(e);
            ReleasedEventsFirstPlayer(e);

            if (e.getCode() == KeyCode.UP && e.getEventType() == KeyEvent.KEY_PRESSED)
                engine.isUpPressed = true;
            if (e.getCode() == KeyCode.DOWN && e.getEventType() == KeyEvent.KEY_PRESSED)
                engine.isDownPressed = true;
            if (e.getCode() == KeyCode.LEFT && e.getEventType() == KeyEvent.KEY_PRESSED)
                engine.isLeftPressed = true;
            if (e.getCode() == KeyCode.RIGHT && e.getEventType() == KeyEvent.KEY_PRESSED)
                engine.isRightPressed = true;
            if (e.getCode() == KeyCode.ENTER && e.getEventType() == KeyEvent.KEY_PRESSED)
                engine.isEnterPressed = true;

            if (e.getCode() == KeyCode.UP && e.getEventType() == KeyEvent.KEY_RELEASED)
                engine.isUpPressed = false;
            if (e.getCode() == KeyCode.DOWN && e.getEventType() == KeyEvent.KEY_RELEASED)
                engine.isDownPressed = false;
            if (e.getCode() == KeyCode.LEFT && e.getEventType() == KeyEvent.KEY_RELEASED)
                engine.isLeftPressed = false;
            if (e.getCode() == KeyCode.RIGHT && e.getEventType() == KeyEvent.KEY_RELEASED)
                engine.isRightPressed = false;
            if (e.getCode() == KeyCode.ENTER && e.getEventType() == KeyEvent.KEY_RELEASED)
                engine.isEnterPressed = false;
        };
    }

    private void setPVEEventHandler() {
        eventsToHandle = e -> {
            PressedEventsFirstPlayer(e);
            ReleasedEventsFirstPlayer(e);
        };
    }

    private void PressedEventsFirstPlayer(KeyEvent e) {
        if (e.getCode() == KeyCode.W && e.getEventType() == KeyEvent.KEY_PRESSED)
            engine.isWPressed = true;
        if (e.getCode() == KeyCode.S && e.getEventType() == KeyEvent.KEY_PRESSED)
            engine.isSPressed = true;
        if (e.getCode() == KeyCode.A && e.getEventType() == KeyEvent.KEY_PRESSED)
            engine.isAPressed = true;
        if (e.getCode() == KeyCode.D && e.getEventType() == KeyEvent.KEY_PRESSED)
            engine.isDPressed = true;
        if (e.getCode() == KeyCode.SPACE && e.getEventType() == KeyEvent.KEY_PRESSED)
            engine.isSpacePressed = true;
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
        if (e.getCode() == KeyCode.SPACE && e.getEventType() == KeyEvent.KEY_RELEASED)
            engine.isSpacePressed = false;
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

    public ArrayList<BonusType> getPossibleBonuses() {
        return possibleBonuses;
    }

    public boolean isPVPorPVE() {
        return isPVPorPVE;
    }
}
