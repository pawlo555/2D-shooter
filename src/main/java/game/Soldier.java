package game;

import utilities.*;

public class Soldier extends MovableElement {
    private final int maxHP;
    private int currentHP;
    private double speed = 2.0;
    private final Gun gun;
    private final SoldierLevel level;

    public Soldier(SoldierLevel level, Vector2D position) {
        super(position.add(new Vector2D(0,10)), new Angle());
        maxHP = level.getSoldierHP();
        currentHP = level.getSoldierHP();
        collider = new CircleCollider(position.add(new Vector2D(0,10)), 23);
        this.level = level;
        gun = level.getGun();
    }

    @Override
    public String getPathToJPG() {
        return level.getPath();
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
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void fasterMovement() {
        speed = speed + 0.5;
    }

    public void firstAidKit() {
        currentHP = maxHP;
    }

    public void strongerBullets() {
        gun.setDamageDealt(gun.getDamage()+3);
    }

    public void fasterReload() {
        gun.setReloadTime(5000);
    }

    public void getBonus(Bonus bonus) {
        BonusType bonusType = bonus.getBonusType();
        switch (bonusType) {

            case FIRST_AID_KIT -> firstAidKit();
            case FASTER_MOVEMENT -> fasterMovement();
            case STRONGER_BULLETS -> strongerBullets();
            case FASTER_RELOAD -> fasterReload();
        }
    }

    public String getHPInfo() {
        return currentHP + "/" + maxHP;
    }

    public double getSpeed() {
        return speed;
    }
}
