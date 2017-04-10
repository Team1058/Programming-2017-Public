package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToAngleGyro extends Command {
double kDesiredAngle, angleDelta, currAngle, angleSetpoint, degreeTolerance;
boolean onTarget;
    public TurnToAngleGyro(double desiredAngle, double degreesTolerance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	 kDesiredAngle = desiredAngle;
    	 degreeTolerance = degreesTolerance;
    	requires(Robot.drivebase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	currAngle = Robot.drivebase.driveGyro.getAngle();
    	angleDelta = kDesiredAngle;
    	angleSetpoint = currAngle + angleDelta;
    	onTarget = false;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currAngle = Robot.drivebase.driveGyro.getAngle();
    	double setpointDelta = angleSetpoint-currAngle;
    	double allowableRangeLow = angleSetpoint-degreeTolerance;
    	double allowableRangeHigh = angleSetpoint+degreeTolerance;
    	if((currAngle <= (allowableRangeHigh)) && (currAngle >= (allowableRangeLow))){
    		Robot.drivebase.driveTank(0, 0);
    		onTarget = true;
    	}
    	if(currAngle < allowableRangeLow){
    		Robot.drivebase.driveTank(setpointDelta/50 +0.1, -setpointDelta/50 - 0.1);
    	}
    	if(currAngle > allowableRangeHigh){
    		Robot.drivebase.driveTank(setpointDelta/50 -0.1, -setpointDelta/50 + 0.1 );
    	}
    	if(Robot.debugging){
    	SmartDashboard.putNumber("setpointDelta", setpointDelta);
    	SmartDashboard.putNumber("currAngle", currAngle);
    	SmartDashboard.putNumber("angleSetpoint", angleSetpoint);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return onTarget;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
