package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnTillPerpVision extends Command {
    public TurnTillPerpVision() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivebase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	try {
			Robot.drivebase.initDriveBase(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Robot.drivebase.gearledring.set(true);
    	/*
    	if (Robot.visionNetworkTable.gearDistanceInches > 30){
    		mod = Robot.visionNetworkTable.gearDistanceFromCenter*.005;	
    		Robot.drivebase.driveTank(.5, .5+mod);
    	}*/
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double mod;
    	Robot.visionNetworkTable.getGearData();
    	//Robot.visionNetworkTable.showGearData();
    	/*if (Robot.visionNetworkTable.gearDistanceFromCenter > 30 ||Robot.visionNetworkTable.gearDistanceFromCenter < 20){
    		Robot.visionNetworkTable.getGearData();
    		if (right){
    			Robot.drivebase.driveTank(-.1, .1);
    		}else{
    			Robot.drivebase.driveTank(.1, -.1);
    		}
    	}*/
    	if (Robot.visionNetworkTable.gearDistanceInches > 10){
    		mod = Robot.visionNetworkTable.gearDistanceFromCenter*.005;	
    		//Robot.drivebase.driveTank(-.2+mod, -.2-mod);
    	}
    	
       	if (Robot.visionNetworkTable.gearDistanceInches <= 15){
    		mod = Robot.visionNetworkTable.gearDistanceFromCenter*.005;	
    		Robot.drivebase.driveTank(0,0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivebase.gearledring.set(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivebase.gearledring.set(false);
    }
}
