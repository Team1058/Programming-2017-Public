package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;
import org.usfirst.frc.team1058.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveToPosition extends Command {
	double leftDriveSpeed;
	double rightDriveSpeed;
	double kMaxSpeedVolts;
	boolean initType;
	double driveDuration;
	boolean a_onTarget, l_onTarget, r_onTarget;
    public DriveToPosition(double leftPosition, double rightPosition, double maxSpeedVolts) {
       // left speed and right speed -1 to 1, desired angle for the robot to turn at the start and preserve throughout the movement
    	// set desiredAngle to -1 for no heading preservation
    	// lengthIsEncCounts - true if the length is in encoder counts, false for time (seconds)
       // desiredLength - how long you want the code to run, in either encoder counts or time (seconds) depending on lengthIsEncCounts
    	// this method only drives for time TODO: add enc counts and heading preservation
    	// eg. requires(chassis);
    	 leftDriveSpeed = leftPosition;
    	 rightDriveSpeed = rightPosition;
    	 kMaxSpeedVolts = maxSpeedVolts;
    	requires(Robot.drivebase);
    
    
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	a_onTarget = false;
    	 l_onTarget = false;
    	r_onTarget = false;
    	try {
			Robot.drivebase.initDriveBase(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Robot.drivebase.leftDrive1.configMaxOutputVoltage(kMaxSpeedVolts);
    	Robot.drivebase.rightDrive1.configMaxOutputVoltage(kMaxSpeedVolts);
    	Robot.drivebase.leftDrive1.setPosition(0);
    	
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivebase.driveTank(leftDriveSpeed, -rightDriveSpeed);
    	
    	if(Robot.drivebase.leftDrive1.getPosition() < (leftDriveSpeed+RobotMap.DRIVEBASE_POSITION_TOLERANCE)
    			&& Robot.drivebase.leftDrive1.getPosition() > (leftDriveSpeed-RobotMap.DRIVEBASE_POSITION_TOLERANCE)){
    		l_onTarget = true;
    	}
    	if(Robot.drivebase.rightDrive1.getPosition() < (rightDriveSpeed+RobotMap.DRIVEBASE_POSITION_TOLERANCE)
    			&& Robot.drivebase.rightDrive1.getPosition() > (rightDriveSpeed-RobotMap.DRIVEBASE_POSITION_TOLERANCE)){
    		r_onTarget = true;
    	}
    	if(l_onTarget == true && r_onTarget == true){
    		a_onTarget = true;
    	}
    	else{
    		a_onTarget = false;
    	}
    	if(Robot.debugging){

    	SmartDashboard.putNumber("Drivebase Setpoint", leftDriveSpeed);
    	SmartDashboard.putNumber("Drivebase Position", Robot.drivebase.leftDrive1.getPosition());
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return a_onTarget;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
