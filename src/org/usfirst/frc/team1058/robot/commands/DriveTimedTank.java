package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTimedTank extends Command {
	double leftDriveSpeed;
	double rightDriveSpeed;
	boolean initType;
	double driveDuration;
    public DriveTimedTank(double leftSpeed, double rightSpeed, double desiredTime) {
       // left speed and right speed -1 to 1, desired angle for the robot to turn at the start and preserve throughout the movement
    	// set desiredAngle to -1 for no heading preservation
    	// lengthIsEncCounts - true if the length is in encoder counts, false for time (seconds)
       // desiredLength - how long you want the code to run, in either encoder counts or time (seconds) depending on lengthIsEncCounts
    	// this method only drives for time TODO: add enc counts and heading preservation
    	// eg. requires(chassis);
    	 leftDriveSpeed = leftSpeed;
    	 rightDriveSpeed = rightSpeed;
    	 driveDuration = desiredTime;
    	requires(Robot.drivebase);
    	setTimeout(desiredTime);
    	
    
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	try {
			Robot.drivebase.initDriveBase(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
  
    		Robot.drivebase.driveTank(leftDriveSpeed-Robot.drivebase.driveGyro.getAngle()/30, rightDriveSpeed+Robot.drivebase.driveGyro.getAngle()/30);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivebase.driveTank(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
