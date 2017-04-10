package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RotateToGyroAngle extends Command {
double kMaxOutput, kDesiredAngle, angleDifferential, prevAngleDifferential, desiredDriveOutput;
boolean isOnTarget;
    public RotateToGyroAngle(double desiredAngle, double maxOutputVolts) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	kMaxOutput = maxOutputVolts;
    	kDesiredAngle = desiredAngle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	try {
			Robot.drivebase.initDriveBase(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	isOnTarget = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	prevAngleDifferential = angleDifferential;
    	angleDifferential = kDesiredAngle-Robot.drivebase.driveGyro.getAngle();
    	
    	
    	if(angleDifferential > kMaxOutput*12){
    		desiredDriveOutput = kMaxOutput*12;
    	}
    	else if(angleDifferential < -kMaxOutput*12){
    		desiredDriveOutput = -kMaxOutput*12;
    	}
    	else{
    		desiredDriveOutput = angleDifferential;
    	}
    	
    	
    	Robot.drivebase.driveTank(desiredDriveOutput/40, -desiredDriveOutput/40);
    	if(angleDifferential == 0){
    		isOnTarget = true;
    	}
    	SmartDashboard.putNumber("Desired Drive Output" , desiredDriveOutput);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isOnTarget;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivebase.driveTank(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivebase.driveTank(0, 0);

    }
}
