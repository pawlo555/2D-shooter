package Utilities;

import Game.CollisionEngine;
import Game.Engine;
import Game.Soldier;

public class BotAI {
    private final Engine engine;
    private final CollisionEngine collisionEngine;
    private final Soldier bot;
    private final Soldier player;

    public BotAI(Engine engine, CollisionEngine collisionEngine, Soldier bot, Soldier player) {
        this.engine = engine;
        this.collisionEngine = collisionEngine;
        this.bot = bot;
        this.player = player;
    }

    private double angle() {
        Vector2D botPosition = bot.getCenter();
        Vector2D secondPosition = botPosition.add(bot.getAngle().toUnitVector());
        Vector2D playerPosition = player.getCenter();
        double a  = botPosition.distanceBetweenPoints(secondPosition);
        double b = botPosition.distanceBetweenPoints(playerPosition);
        double c = secondPosition.distanceBetweenPoints(playerPosition);
        double cos = (a*a+b*b-c*c)/(2*a*b);
        return Math.acos(cos);
    }

    private double turnLeftOrRight() {
        bot.moveBy(25);
        double firstDistance = getDistanceBetweenBotAndPlayer();
        bot.moveBy(-25);
        bot.turnBy(5);
        bot.moveBy(25);
        double secondDistance = getDistanceBetweenBotAndPlayer();
        bot.moveBy(-25);
        bot.turnBy(350);
        bot.moveBy(25);
        double thirdDistance = getDistanceBetweenBotAndPlayer();
        bot.moveBy(-25);
        bot.turnBy(5);
        if (firstDistance < secondDistance && firstDistance < thirdDistance) {
            return 0;
        }
        else if (secondDistance <= thirdDistance + 0.01) {
            return -1;
        }
        else
            return 1;
    }

    private void turn() {
        if (turnLeftOrRight() > 0) {
            bot.turnBy(358);
        }
        else if (turnLeftOrRight() < 0) {
            bot.turnBy(2);
        }
    }

    private void move() {
        if (turnLeftOrRight() == 0) {
            bot.moveBy(bot.getSpeed());
            if (collisionEngine.isCollision(bot) || engine.getMap().isInMap(bot)) {
                bot.moveBy(-bot.getSpeed());
            }
        }
    }

    private void shoot() {
        if (turnLeftOrRight() == 0) {
            engine.gunShoot(bot);
        }
    }

    public void moveBot() {
        turn();
        move();
        shoot();
    }

    private double getDistanceBetweenBotAndPlayer() {
        return bot.getCenter().distanceBetweenPoints(player.getCenter());
    }


    public Soldier getBot() {
        return bot;
    }
}
