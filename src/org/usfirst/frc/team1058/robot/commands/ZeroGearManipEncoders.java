package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ZeroGearManipEncoders extends Command {
boolean isFinished;
    public ZeroGearManipEncoders() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearManipulator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isFinished = false;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gearManipulator.gearManipulatorPivot.setPosition(0);
    	Robot.gearManipulator.setIntakePosition(0);
    	if(Robot.gearManipulator.gearManipulatorPivot.getPosition() == 0){
    		PivotJoystickPID.justInitialized = true;
    		isFinished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
