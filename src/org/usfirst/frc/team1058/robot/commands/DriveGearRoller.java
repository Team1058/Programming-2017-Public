package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveGearRoller extends Command {
double rollerVelocity;
boolean done;
    public DriveGearRoller(double velocity) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	rollerVelocity = velocity;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	done = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        	Robot.gearManipulator.setRollerSpeed(rollerVelocity);
        
        
        
      
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gearManipulator.setRollerSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.gearManipulator.setRollerSpeed(0);
    	
    }
}
