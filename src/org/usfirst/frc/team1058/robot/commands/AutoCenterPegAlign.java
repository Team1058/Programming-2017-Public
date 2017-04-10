package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoCenterPegAlign extends Command {
	boolean done;
	EncoderTurnToAngle turnToAngle;
    public AutoCenterPegAlign() {
    	requires(Robot.drivebase);
    	requires(Robot.cameraTurret);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	done = false;
		Robot.drivebase.driveGyro.reset();
		Robot.drivebase.gearledring.set(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double LDistanceAway,SDistanceAway,HDistanceAway;
    	Robot.visionNetworkTable.getGearData();
    	while (Robot.visionNetworkTable.gearDistanceInches> 27){
    		Robot.visionNetworkTable.getGearData();
    		Robot.drivebase.driveTank(-.4,-.4);
    		//need to find actau offsets
        	if(Robot.visionNetworkTable.gearDistanceFromCenter> 50 || Robot.visionNetworkTable.gearDistanceFromCenter< 40){
        		//formulas from excel
        		SDistanceAway = 1;
        		LDistanceAway = Math.sqrt(Math.pow(Robot.visionNetworkTable.gearDistanceInches,2)-Math.pow(SDistanceAway, 2)) - 3;
        		double degrees = Math.atan(SDistanceAway/LDistanceAway);
        		HDistanceAway = Math.sqrt(Math.pow(SDistanceAway,2) + Math.pow(LDistanceAway, 2));
        		turnToAngle = new EncoderTurnToAngle(degrees);
        		Robot.drivebase.driveTank(Robot.drivebase.leftDrive1.getPosition()-HDistanceAway, Robot.drivebase.rightDrive1.getPosition()-HDistanceAway);
        		turnToAngle = new EncoderTurnToAngle(-degrees);
        	}
    		
    		
    	}
    	Robot.drivebase.driveTank(0,0);
    	
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
