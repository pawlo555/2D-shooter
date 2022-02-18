package utilities;

import game.Gun;

public enum SoldierLevel {
    EASY,
    HARD,
    MEDIUM;

    public int getSoldierHP() {
        return switch (this) {
            case EASY -> 10;
            case MEDIUM -> 20;
            case HARD -> 30;
        };
    }

    public String getPath() {
        return switch (this) {
            case EASY -> this.getClass().getResource("../images/easySoldier.png").getPath();
            case MEDIUM -> this.getClass().getResource("../images/mediumSoldier.png").getPath();
            case HARD -> this.getClass().getResource("../images/hardSoldier.png").getPath();
        };
    }

    public Gun getGun() {
        return switch (this) {
            case EASY -> new Gun(7000,3);
            case MEDIUM -> new Gun(5000, 5);
            case HARD -> new Gun(4000, 10);
        };
    }
}
