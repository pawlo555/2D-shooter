package Game;

import Utilities.Angle;
import Utilities.CircleCollider;
import Utilities.Vector2D;

public class Bullet extends MovableElement{
    private final double velocity;
    private final int damage;

    public Bullet(Vector2D position, Angle angle, double velocity, int damage) {
        super(position, angle);
        this.velocity = velocity;
        collider = new CircleCollider(position,8);
        this.damage = damage;
    }

    @Override
    public String getPathToJPG() {
        return "C:\\Users\\spawe\\OneDrive\\Pulpit\\Studia\\ProgramowanieObiektowe\\Strzelanka2D\\src\\Images\\bullet.png";
    }

    @Override
    public Vector2D getUpperLeftCorner() {
        return getCenter().add(new Vector2D(-10,-10));
    }

    @Override
    public Vector2D getLowerRightCorner() {
        return getCenter().add(new Vector2D(10,10));
    }

    public double getVelocity() {
        return velocity;
    }

    public int getDamage() {
        return damage;
    }
}
