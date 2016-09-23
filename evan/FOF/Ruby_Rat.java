package hitchings.evan.FOF;
import robocode.*;
import java.awt.Color;
import java.util.HashMap;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * Rocketdoll - a robot by (your name here)
 */
public class Ruby_Rat extends TeamRobot 
{
	private byte scanDirection = 1;

	/**
	 * run: Rocketdoll's default behavior
	 */
	public void run() {
		setAdjustRadarForRobotTurn(true);
	
		
		// Initialization of the robot should be put here
		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:
		setBodyColor(Color.magenta);
		setGunColor(Color.pink);
		setBulletColor(Color.black);
	
		

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		while(true) {
			turnRadarRight(360 * scanDirection);
			execute();
			ahead(100);
			turnRight(5);
			
			
			// Replace the next 4 lines with any behavior you would like
			//ahead(10);
			
			
			
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		if(isTeammate(e.getName())){
			return;
		}
	
		if((e.getName().contains("FOF")) && getOthers() >= 4) {
            return;
        }
		turnRight(e.getBearing());
		setTurnRadarRight(getHeading() - getRadarHeading() + e.getBearing());
		if(e.getEnergy() < 15.0){
			setMaxVelocity(8);
			ahead(e.getDistance() * 2);
			
		}
			
		fire(400 / e.getDistance());
		ahead(e.getDistance() / 1.5);
		scanDirection *= -1;
		
		//fire(10);
		
		
		
		
		// Replace the next line with any behavior you would like
		
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		//setTurnRadarRight(getHeading() - getRadarHeading() + e.getBearing());
		
		//turnRight(e.getBearing());
		//fire(3);
		//ahead(e.getDistance() / 2);
	}
		// Replace the next line with any behavior you would like
		
	
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		back(20);
	
		// Replace the next line with any behavior you would like
		turnRight(e.getBearing()); 
	}

	public void onHitRobot(HitRobotEvent j){
		setTurnRadarRight(getHeading() - getRadarHeading() + j.getBearing());
		turnRight(j.getBearing());
		
		if (j.getEnergy() > 16) {
			fire(3);
		} else if (j.getEnergy() > 10) {
			fire(2);
		} else if (j.getEnergy() > 4) {
			fire(1);
		} else if (j.getEnergy() > 2) {
			fire(.5);
		} else if (j.getEnergy() > .4) {
			fire(.1);
		}
		ahead(40);
	}
	
	public void onWin(WinEvent event){
		turnRight(9001);
	}
	
		
	
	
}
