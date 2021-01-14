package Game;

public class Engine {
    private boolean isWPressed;
    private boolean isSPressed;
    private boolean isAPressed;
    private boolean isDPressed;
    private Soldier player1;
    private Soldier player2;

    private Map map;
    private CollisionEngine collisionEngine;

    public Engine() {
    }

    public void nextTurn() {
        if (isWPressed) {
            System.out.println("Move Forward");
            isWPressed = false;
        }

        if (isSPressed) {
            System.out.println("Move Backward");
            isSPressed = false;
        }
        if (isAPressed) {
            System.out.println("Move Left");
            isAPressed = false;
        }
        if (isDPressed) {
            System.out.println("Move Right");
            isDPressed = false;
        }
    }

    public void wPressed() {
        isWPressed = true;
    }

    public void sPressed() {
        isSPressed = true;
    }

    public void aPressed() {
        isAPressed = true;
    }

    public void dPressed() {
        isDPressed = true;
    }
}
