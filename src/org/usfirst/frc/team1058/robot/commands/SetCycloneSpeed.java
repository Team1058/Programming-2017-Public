package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetCycloneSpeed extends Command {
 double kspeed, khopspeed;
public static boolean done, cycloneOn;
    public SetCycloneSpeed(double speed, double hopperspeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.cyclone);
    	kspeed = speed;
    	khopspeed = hopperspeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	done = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.operatorGamepad.getStartButton()){
        	Robot.cyclone.setCycloneSpeed(-kspeed);

    	}
    	else{
        	Robot.cyclone.setCycloneSpeed(kspeed);

    	}
    	Robot.cyclone.setHopperSpeed(khopspeed);
    	if(khopspeed != 0 || kspeed != 0){
    		cycloneOn = true;
    	}
    	else{
    		cycloneOn = false;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.cyclone.setCycloneSpeed(0);
		cycloneOn = false;

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.cyclone.setCycloneSpeed(0);
		cycloneOn = false;

    }
}
