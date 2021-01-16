package Game;

import Utilities.Angle;
import Utilities.CircleCollider;
import Utilities.Vector2D;

public class Soldier extends MovableElement {
    private final int maxHP;
    private int currentHP;
    private double speed = 5.0;
    private Gun gun = new Gun(2000,5);

    public Soldier(int HP, Vector2D position) {
        super(position, new Angle());
        maxHP = HP;
        currentHP = HP;
        collider = new CircleCollider(position, 30);
    }

    @Override
    public String getPathToJPG() {
        return "C:\\Users\\spawe\\OneDrive\\Pulpit\\Studia\\ProgramowanieObiektowe\\Strzelanka2D\\src\\Images\\soldier.png";
    }

    @Override
    public Vector2D getUpperLeftCorner() {
        return center.add(new Vector2D(-30,-30));
    }

    @Override
    public Vector2D getLowerRightCorner() {
        return center.add(new Vector2D(30,30));
    }

    public Gun getGun() {
        return gun;
    }

    public void looseHP(int HP) {
        currentHP = currentHP - HP;
        System.out.println("Current HP: " + currentHP);
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void fasterMovement() {
        speed = speed + 3;
    }

    public void firstAidKit() {
        currentHP = maxHP;
    }

    public void strongerBullets() {
        gun.setDamageDealt(gun.getDamage()+3);
    }

    public void fasterReload() {
        gun.setReloadTime(3000);
    }
}
