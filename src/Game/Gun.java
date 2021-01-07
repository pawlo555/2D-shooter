package Game;

import java.util.Timer;
import java.util.TimerTask;

public class Gun {
    private Timer timer = new Timer();
    boolean canShoot = true;
    private int timeBetweenShoots = 10;
    private int reloadTime = 2000;
    private final int maxAmmoInMagazine = 30;
    private int currentAmmoInMagazine = 30;
    private int damageDealt = 10;
    private double bulletsVelocity = 35.0;

    public Gun(int reloadTime, int damageDealt, double bulletsVelocity) {
        this.reloadTime = reloadTime;
        this.damageDealt = damageDealt;
        this.bulletsVelocity = bulletsVelocity;
    }

    public void shoot() {
        canShoot = false;
        System.out.println("Bum");
        int shootingRest;
        if (currentAmmoInMagazine > 1) {
            shootingRest = timeBetweenShoots;
            currentAmmoInMagazine -= 1;
        } else {
            shootingRest = reloadTime;
            currentAmmoInMagazine = maxAmmoInMagazine;
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                canShoot = true;
                System.out.println("Gun ready to fire");
            }
        }, reloadTime);
    }

}
