package utilities;

public enum BonusType {
    FIRST_AID_KIT,
    FASTER_MOVEMENT,
    STRONGER_BULLETS,
    FASTER_RELOAD;

    public String getPath() {
        return switch (this) {
            case FIRST_AID_KIT -> this.getClass().getResource("../images/firstAidKit.png").getPath();
            case FASTER_MOVEMENT -> this.getClass().getResource("../images/fasterMovement.png").getPath();
            case STRONGER_BULLETS -> this.getClass().getResource("../images/strongerBullets.png").getPath();
            case FASTER_RELOAD -> this.getClass().getResource("../images/fasterReload.png").getPath();
        };
    }
}
