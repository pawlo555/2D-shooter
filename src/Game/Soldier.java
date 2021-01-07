package Game;

public class Soldier {
    private final int maxHP;
    private int currentHP;
    private double speed = 5.0;
    private Gun gun;

    public Soldier(int HP) {
        maxHP = HP;
        currentHP = HP;
    }


}
