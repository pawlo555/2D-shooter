package Utilities;

import Game.Gun;

import javax.print.DocFlavor;

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
            case EASY -> "src/Images/easySoldier.png";
            case MEDIUM -> "src/Images/mediumSoldier.png";
            case HARD -> "src/Images/hardSoldier.png";
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
