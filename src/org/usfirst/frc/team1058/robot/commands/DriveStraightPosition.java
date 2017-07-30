package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;
import org.usfirst.frc.team1058.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightPosition extends Command {
	double kPosition;
	double kMaxSpeedFps;
	boolean initType;
	double driveDuration;
	boolean a_onTarget, l_onTarget, r_onTarget;
    public DriveStraightPosition(double positionInFeet, double maxSpeedFps) {
    	kPosition = positionInFeet*1.04;
    	kMaxSpeedFps = maxSpeedFps;//(maxSpeedFps/((Math.pi*4)/12))*60
    	requires(Robot.drivebase);
    
    
    }
    
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	a_onTarget = false;
    	 l_onTarget = false;
    	r_onTarget = false;
    	try {
			Robot.drivebase.initDriveBase(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.drivebase.driveTank(-kMaxSpeedFps+Robot.drivebase.driveGyro.getAngle()/5, -kMaxSpeedFps-Robot.drivebase.driveGyro.getAngle()/5);
    	
    	//Robot.drivebase.driveTank(-kMaxSpeedFps, -kMaxSpeedFps);
    	if(-Robot.drivebase.leftDrive1.getPosition() < (kPosition+RobotMap.DRIVEBASE_POSITION_TOLERANCE)
    			&& -Robot.drivebase.leftDrive1.getPosition() > (kPosition-RobotMap.DRIVEBASE_POSITION_TOLERANCE)){
    		l_onTarget = true;
    	}
    	if(Robot.drivebase.rightDrive1.getPosition() < (kPosition+RobotMap.DRIVEBASE_POSITION_TOLERANCE)
    			&& Robot.drivebase.rightDrive1.getPosition() > (kPosition-RobotMap.DRIVEBASE_POSITION_TOLERANCE)){
    		r_onTarget = true;
    	}
    	if(l_onTarget == true && r_onTarget == true){
    		a_onTarget = true;
    	}
    	else{
    		a_onTarget = false;
    	}
    	if(Robot.debugging){
    	SmartDashboard.putNumber("Drivebase Setpoint", kPosition);
    	SmartDashboard.putNumber("Drivebase leftSpeed", Robot.drivebase.leftDrive1.getSpeed());
    	SmartDashboard.putNumber("Drivebase rightSpeed", Robot.drivebase.rightDrive1.getSpeed());
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return a_onTarget;
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
