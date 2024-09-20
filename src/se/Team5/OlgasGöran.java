
package se.Team5;

import robocode.*;
import robocode.Robot;

import java.awt.*;

public class OlgasGöran extends AdvancedRobot{

    int turnDirection = 1;

    public void avoidWalls() {
        double borderRangeWidth = getBattleFieldWidth() - 100;
        double borderRangeHeight = getBattleFieldHeight() - 100;
        if (getY() > borderRangeHeight || getY() < getBattleFieldHeight() - borderRangeHeight ||
                getX() > borderRangeWidth || getX() < getBattleFieldWidth() - borderRangeWidth) {
            turnRight(180);
        }
    }

    public void onScannedRobot(ScannedRobotEvent event){

setTurnRadarRight(getHeading()-getRadarHeading()+event.getBearing());
setTurnGunRight(getHeading()-getGunHeading()+event.getBearing());


        if (event.getBearing() >= 0) {
            turnDirection = 1;
        } else {
            turnDirection = -1;
        }
        turnRight(event.getBearing());
        ahead(event.getDistance() + 5);
        scan();

        /*if (event.getDistance() < 100)
            back(event.getDistance());*/

        if (event.getDistance()<300){
            fire(3);}
        else{
            fire(1);}
        System.out.println(event.getDistance());
    }

    public void onHitRobot(HitRobotEvent event) {
        if (event.getBearing() >= 0) {
            turnDirection = 1;
        } else {
            turnDirection = -1;
        }
        turnRight(event.getBearing());
        if (event.getEnergy() > 16) {
            fire(3);
        } else if (event.getEnergy() > 10) {
            fire(2);
        } else if (event.getEnergy() > 4) {
            fire(1);
        } else if (event.getEnergy() > 2) {
            fire(.5);
        } else if (event.getEnergy() > .4) {
            fire(.1);
        }
        back(30);
        ahead(100);
    }



    public void run() {
        setColors(Color.white,Color.red,Color.white);
        setAdjustRadarForGunTurn(true);
        setAdjustGunForRobotTurn(true);
        while (true) {
            avoidWalls();
            //turnRight(5 * turnDirection);
            ahead(100);
            turnRadarRight(360);
            execute();

        }
    }
}













































