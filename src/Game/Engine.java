package Game;

import GUI.MapVisualizer;

import java.io.FileNotFoundException;

public class Engine {
    public boolean isWPressed = false;
    public boolean isSPressed = false;
    public boolean isAPressed = false;
    public boolean isDPressed = false;
    public boolean isUpPressed = false;
    public boolean isDownPressed = false;
    public boolean isLeftPressed = false;
    public boolean isRightPressed = false;
    private Soldier player1;
    private Soldier player2;


    private MapVisualizer visualizer;
    private CollisionEngine collisionEngine;

    public Engine(Map map) {
    }

    public void nextTurn() throws FileNotFoundException {
        if (isWPressed) {
            System.out.println("Move Forward");
            this.player1.moveBy(5);
        }

        if (isSPressed) {
            System.out.println("Move Backward");
            this.player1.moveBy(-5);
        }
        if (isAPressed) {
            System.out.println("Move Left");
            player1.turnBy(350);
        }
        if (isDPressed) {
            System.out.println("Move Right");
            player1.turnBy(10);
        }

        visualizer.Visualize();
    }

    public void setVisualizer(MapVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    public void setPlayer1(Soldier player) {
        System.out.println(player);
        this.player1 = player;
        System.out.println(this.player1);
        this.player1.moveBy(-100);
        System.out.println(player1.getLowerRightCorner().toString());
    }

    public Soldier getPlayer1() {
        return this.player1;
    }
}
