package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;
import org.usfirst.frc.team1058.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveAndPivotGearRoller extends Command {
double kRollerSpeed, kIntakeSetpoint;
boolean done;
    public DriveAndPivotGearRoller(double rollerSpeed, double intakeSetpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearManipulator);
    	kRollerSpeed = rollerSpeed;
    	kIntakeSetpoint = intakeSetpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(kIntakeSetpoint == RobotMap.INTAKE_PIVOT_GEARINTAKE_POSITION){
    		Robot.numToSend = 5;
    	}
    	if(kIntakeSetpoint == RobotMap.INTAKE_PIVOT_BALLINTAKE_POSITION){
    		Robot.numToSend = 6;
    	}
    	done = false;
    	Robot.gearManipulator.setIntakePosition(kIntakeSetpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!Robot.oi.operatorGamepad.getRightAxisButton()){
    	Robot.gearManipulator.setRollerSpeed(kRollerSpeed);
    	}
    	else{
    		Robot.gearManipulator.setRollerSpeed(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gearManipulator.setIntakePosition(RobotMap.INTAKE_PIVOT_VERTICAL_POSITION);
    	Robot.gearManipulator.setRollerSpeed(0);
    	Robot.numToSend = 3;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.gearManipulator.setRollerSpeed(0);
    	Robot.gearManipulator.setIntakePosition(RobotMap.INTAKE_PIVOT_VERTICAL_POSITION);
    	done = true;
    	Robot.numToSend = 3;
    }
}
