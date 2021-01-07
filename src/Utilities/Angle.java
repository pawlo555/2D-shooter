package Utilities;

public class Angle {
    private int angle;

    public Angle() {
        angle = 0;
    }

    public Angle(int angle) {
        this.angle = angle%360;
    }

    public void turnByAngle(int angle) {
        this.angle = (this.angle+angle)%360;
    }

    public int getAngle() {
        return angle;
    }
}
