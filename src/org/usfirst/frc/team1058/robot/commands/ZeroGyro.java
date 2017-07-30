package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;
import org.usfirst.frc.team1058.robot.subsystems.DriveBase;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ZeroGyro extends Command {

    public ZeroGyro() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	setTimeout(1);
    	requires(Robot.drivebase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebase.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
