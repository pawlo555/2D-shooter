package Game;

import Utilities.Angle;
import Utilities.Vector2D;

public class Soldier extends MovableElement {
    private final int maxHP;
    private int currentHP;
    private double speed = 5.0;
    private Gun gun = new Gun(1,1,1);
    private Angle angle = new Angle();

    public Soldier(int HP) {
        maxHP = HP;
        currentHP = HP;
    }

    @Override
    public String getPathToJPG() {
        return null;
    }
}
