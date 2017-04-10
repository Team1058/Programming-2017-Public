package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;
import org.usfirst.frc.team1058.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveGearPivot extends Command {
double kMaxVoltage, kSetpoint;
boolean onTarget;
    public DriveGearPivot(double setpoint, double maxVoltage) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearManipulator);
    	kMaxVoltage = maxVoltage;
    	kSetpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	PivotJoystickPID.justInitialized = false;
    	Robot.gearManipulator.setIntakeMaxSpeed(kMaxVoltage);
    	onTarget = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(kSetpoint != -10581058){
    	Robot.gearManipulator.setIntakePosition(kSetpoint);
    	PlaceGear.placingGear = true;
       	if(Robot.gearManipulator.gearManipulatorPivot.getPosition() < (kSetpoint+RobotMap.DRIVEBASE_POSITION_TOLERANCE)
    	&& Robot.gearManipulator.gearManipulatorPivot.getPosition() > (kSetpoint-RobotMap.DRIVEBASE_POSITION_TOLERANCE)){
    		onTarget = true;
    	}
    	}
    	else{
    		onTarget = true;
    	}
    	if(kSetpoint == RobotMap.INTAKE_PIVOT_GEARINTAKE_POSITION-400){
    		PlaceGear.placingGear = false;
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
