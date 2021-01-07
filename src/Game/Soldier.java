package Game;

import Utilities.Angle;

public class Soldier {
    private final int maxHP;
    private int currentHP;
    private double speed = 5.0;
    private Gun gun = new Gun();
    private Angle angle = new Angle();

    public Soldier(int HP) {
        maxHP = HP;
        currentHP = HP;
    }


}
