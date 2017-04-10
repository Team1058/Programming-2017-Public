package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ManualClimberDrive extends Command {
	boolean hitTop = false;
	boolean climberOn = false;
	boolean done;
	double k_speed;
    public ManualClimberDrive(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    	k_speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	done = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.debugging){

    	SmartDashboard.putBoolean("climberHitTop", hitTop);
    	SmartDashboard.putNumber("Current", Robot.climber.getClimberCurrent());
    	}
    	Robot.climber.setClimberOutput(k_speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climber.setClimberOutput(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.climber.setClimberOutput(0);
    	done = true;

    }
}
