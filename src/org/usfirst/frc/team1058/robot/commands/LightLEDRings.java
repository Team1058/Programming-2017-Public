package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LightLEDRings extends Command {
boolean gearlightState = false;
boolean shooterlightState = false;
String k_ringToToggle;
    public LightLEDRings(String ringToToggle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	k_ringToToggle = ringToToggle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(k_ringToToggle == "shooter"){
        	Robot.drivebase.shooterledring.set(!shooterlightState);
        	shooterlightState = !shooterlightState;
        	this.end();
    	}
    	if(k_ringToToggle == "gearmanipulator"){
    		Robot.drivebase.gearledring.set(!gearlightState);
    		gearlightState = !gearlightState;
    		this.end();
    	}
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
