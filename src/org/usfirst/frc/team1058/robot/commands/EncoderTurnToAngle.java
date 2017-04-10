package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;
import org.usfirst.frc.team1058.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class EncoderTurnToAngle extends Command {
	double degreesToTurn,setPoint;
	boolean done, a_onTarget, r_onTarget, l_onTarget;
    public EncoderTurnToAngle(double degrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivebase);
    	degreesToTurn = degrees;

    	try {
			Robot.drivebase.initDriveBase(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	setPoint = degrees*1;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebase.driveTank(Robot.drivebase.leftDrive1.getPosition() + setPoint, 
    			Robot.drivebase.rightDrive1.getPosition() - setPoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
     	if(Robot.drivebase.leftDrive1.getPosition() < (setPoint+RobotMap.DRIVEBASE_POSITION_TOLERANCE)
    			&& Robot.drivebase.leftDrive1.getPosition() > (setPoint-RobotMap.DRIVEBASE_POSITION_TOLERANCE)){
    		l_onTarget = true;
    	}
    	if(Robot.drivebase.rightDrive1.getPosition() < (-setPoint+RobotMap.DRIVEBASE_POSITION_TOLERANCE)
    			&& Robot.drivebase.rightDrive1.getPosition() > (-setPoint-RobotMap.DRIVEBASE_POSITION_TOLERANCE)){
    		r_onTarget = true;
    	}
    	if(l_onTarget == true && r_onTarget == true){
    		a_onTarget = true;
    	}
    	else{
    		a_onTarget = false;
    	}
    	if(Robot.debugging){

    	SmartDashboard.putNumber("Drivebase Rotation Setpoint", setPoint);
    	SmartDashboard.putNumber("Drivebase Left Position", Robot.drivebase.leftDrive1.getPosition());    
    	SmartDashboard.putNumber("Drivebase Right Position", Robot.drivebase.rightDrive1.getPosition());   
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
