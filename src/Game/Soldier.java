package Game;

import Utilities.Angle;
import Utilities.CircleCollider;
import Utilities.Collider;
import Utilities.Vector2D;

public class Soldier extends MovableElement {
    private final int maxHP;
    private int currentHP;
    private double speed = 5.0;
    private Gun gun = new Gun(1,1,1);
    private Angle angle = new Angle();

    public Soldier(int HP, Vector2D position) {
        super(position);
        maxHP = HP;
        currentHP = HP;
        collider = new CircleCollider(position, 25);
    }

    @Override
    public String getPathToJPG() {
        return "C:\\Users\\spawe\\OneDrive\\Pulpit\\Studia\\ProgramowanieObiektowe\\Strzelanka2D\\src\\Images\\soldier.png";
    }

    @Override
    public Vector2D getUpperLeftCorner() {
        return center.add(new Vector2D(-28.75,-50));
    }

    @Override
    public Vector2D getLowerRightCorner() {
        return center.add(new Vector2D(28.75,27.5));
    }
}
