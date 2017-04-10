package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonomousClimber extends Command {
	boolean hitTop = false;
	boolean climberOn = false;
	int timesSpiked = 0;
    public AutonomousClimber() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	hitTop = false;
    	climberOn = !climberOn;
    	timesSpiked = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.debugging){
    	SmartDashboard.putBoolean("climberHitTop", hitTop);
    	SmartDashboard.putNumber("Current", Robot.climber.getClimberCurrent());
    	}
    	if(climberOn == true){
    	while(Robot.climber.getClimberCurrent() < 10){
    		Robot.climber.setClimberOutput(0.5);
    	}
    	while(Robot.climber.getClimberCurrent() >= 10 && Robot.climber.getClimberCurrent() < 18){
    		Robot.climber.setClimberOutput(1);
    	}
    	if(Robot.climber.getClimberCurrent() >= 18){
    		timesSpiked++;
    		if(timesSpiked>2){
    		Robot.climber.setClimberOutput(0);
    		hitTop = true;
    		this.end();
    		}
    	}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return hitTop;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
