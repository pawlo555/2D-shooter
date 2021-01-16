package Game;

import java.util.Timer;
import java.util.TimerTask;

public class Gun {
    private Timer timer = new Timer();
    private boolean canShoot = true;
    private int timeBetweenShoots = 1000;
    private int reloadTime = 5000;
    private final int maxAmmoInMagazine = 4;
    private int currentAmmoInMagazine = 30;
    private int damageDealt = 5;
    private double bulletsVelocity = 10;

    public Gun(int reloadTime, int damageDealt) {
        this.reloadTime = reloadTime;
        this.damageDealt = damageDealt;
    }

    public void shoot() {
        if (canShoot) {
            canShoot = false;
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
            }, shootingRest);
        }
    }

    public double getBulletsVelocity() {
        return bulletsVelocity;
    }

    public boolean isCanShoot() {
        return canShoot;
    }

    public int getDamage() {
        return damageDealt;
    }

    public void setDamageDealt(int newDamage) {
        damageDealt = newDamage;
    }

    public void setReloadTime(int newTime) {
        reloadTime = newTime;
    }
}
