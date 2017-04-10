package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PivotJoystickPID extends Command {
double k_maxvoltage, k_initialsetpoint;
static boolean justInitialized;
    public PivotJoystickPID(double initialsetpoint, double maxvoltage) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearManipulator);
    	k_maxvoltage = maxvoltage;
    	k_initialsetpoint = initialsetpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(justInitialized == false){
        	Robot.gearManipulator.setIntakePosition(k_initialsetpoint);

    	}
    	Robot.gearManipulator.setIntakeMaxSpeed(k_maxvoltage);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.operatorGamepad.getStartButton()){
    	Robot.gearManipulator.setIntakePosition(-Robot.gearManipulator.gearManipulatorPivot.getPosition() + -400*Robot.oi.operatorGamepad.getLeftStickY());
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
