package Game;

import java.util.Timer;
import java.util.TimerTask;

public class Gun {
    private final Timer timer = new Timer();
    private boolean canShoot = true;
    private int reloadTime;
    private int currentAmmoInMagazine = 4;
    private int damageDealt;

    public Gun(int reloadTime, int damageDealt) {
        this.reloadTime = reloadTime;
        this.damageDealt = damageDealt;
    }

    public void shoot() {
        if (canShoot) {
            canShoot = false;
            int shootingRest;
            if (currentAmmoInMagazine > 1) {
                shootingRest = 1000;
                currentAmmoInMagazine -= 1;
            } else {
                shootingRest = reloadTime;
                currentAmmoInMagazine = 4;
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
        return 3;
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
