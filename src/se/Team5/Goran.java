package se.Team5;
import robocode.AdvancedRobot;
import robocode.*;
import robocode.Robot;
import robocode.util.Utils;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;
import java.awt.geom.*;
import java.util.Vector;
import robocode.ScannedRobotEvent;

public class Goran extends AdvancedRobot {
    //boolean movingFor;
    boolean move = true;
    private double oldEnemyHeading;

    @Override
    public void onHitByBullet(HitByBulletEvent event) {

        setTurnRight(90);
        setBack(150);

        out.println("OUCH! " + event.getName() + "hit me for " + event.getPower() + " damage! I'm down to " + getEnergy());
    }

    @Override
    public void onBulletHit(BulletHitEvent event) {
        out.println("I hit " + event.getName() + " he is down to " + event.getEnergy() + " energy");
        out.println("My energy is now " + getEnergy());
    }

    @Override
    public void onRobotDeath(RobotDeathEvent event) {
        setTurnGunRight(Double.POSITIVE_INFINITY);
    }

    @Override
    public void scan() {
        super.scan();
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {

        double stayOnTarget = getHeadingRadians() + event.getBearingRadians() - getGunHeadingRadians();
        setTurnGunRightRadians(Utils.normalRelativeAngle(stayOnTarget));

        if(event.getEnergy() < 50 || event.getVelocity() < 3){

            fire(3);

        }else if (event.getDistance() < 400){

            fire(2);

        }else{

            fire(1);

        }

    }

    @Override
    public void run() {
        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(false);
        setAdjustRadarForRobotTurn(false);

        setTurnGunRight(Double.POSITIVE_INFINITY);
        setColors(Color.YELLOW, Color.BLACK, Color.MAGENTA);


        while (true) {
            scan();
            normalMove();

        }
    }

    int counter = 0;
    public void normalMove() {
        int toCloseToBorder;
        double directionFromBorder;

        counter = (counter + 1) % 3;
        Random rnd = new Random();

        double minSpeedForward = 75;
        double randomSpeedForward = 25;
        double maxSpeedForward = minSpeedForward + randomSpeedForward + 36;

        double minLeftTurn = 30;
        double randomLeftTurn = 90;
        //double maxLeftTurn = minLeftTurn + randomLeftTurn;

        double minRightTurn = 10;
        double randomRightTurn = 60;
        //double maxRightTurn = minRightTurn + randomRightTurn;

        toCloseToBorder = 0;

        if (this.getX() < maxSpeedForward) {
            toCloseToBorder = 1;
        } else if (this.getX() > this.getBattleFieldWidth() - maxSpeedForward) {
            toCloseToBorder = 2;
        }
        if (this.getY() < maxSpeedForward) {
            toCloseToBorder = toCloseToBorder | 4;
        } else if (this.getY() > this.getBattleFieldHeight() - maxSpeedForward) {
            toCloseToBorder = toCloseToBorder | 8;
        }

        switch (toCloseToBorder) {
            case 1 -> directionFromBorder = 90;
            case 2 -> directionFromBorder = 270;
            case 4 -> directionFromBorder = 0;
            case 8 -> directionFromBorder = 180;
            case 5 -> directionFromBorder = 90 - 45;
            case 9 -> directionFromBorder = 90 + 45;
            case 10 -> directionFromBorder = 270 - 45;
            case 6 -> directionFromBorder = 270 + 45;
            default -> directionFromBorder = 666;
        }

        if (directionFromBorder == 666) {
            setAhead(rnd.nextDouble() * randomSpeedForward + minSpeedForward);
            if (counter == 1) {
                setTurnLeft(rnd.nextDouble() * randomLeftTurn + minLeftTurn);
            } else {
                setTurnRight(rnd.nextDouble() * randomRightTurn + minRightTurn);
            }
        } else {
            double newDirection = directionFromBorder - getHeading() + (rnd.nextDouble() * 100) - 50;
            setAhead(maxSpeedForward + 50);
            setTurnRight(newDirection);
        }
        execute();
    }
}


