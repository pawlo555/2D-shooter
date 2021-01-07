package Game;

import java.util.Timer;
import java.util.TimerTask;

public class Gun {
    private Timer timer = new Timer();
    boolean canShoot = true;
    private int reloadTime;
    private int damageDealt;
    private double bulletsVelocity;

    public Gun(int reloadTime, int damageDealt, double bulletsVelocity) {
        this.reloadTime = reloadTime;
        this.damageDealt = damageDealt;
        this.bulletsVelocity = bulletsVelocity;
    }

    public void shoot() {
        canShoot = false;
        System.out.println("Bum");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                canShoot = true;
                System.out.println("Gun ready to fire");
            }
        }, reloadTime);
    }

}
