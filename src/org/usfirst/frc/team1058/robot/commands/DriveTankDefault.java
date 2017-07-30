package org.usfirst.frc.team1058.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1058.robot.Robot;

/**
 *
 */
public class DriveTankDefault extends Command {
	
	double lspd, rspd, deadbandrotation, deadbandfwdback;
	public DriveTankDefault() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivebase);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		try {
			Robot.drivebase.initDriveBase(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
    	if(Robot.debugging){

		SmartDashboard.putNumber("DriveBase Voltage Output", Robot.drivebase.leftDrive1.getOutputVoltage());
    	}
		if(Robot.isTeleoperated){
			
        
			if(Math.abs(Robot.oi.driverGamepad.getRightStickX()) < 0.15){
				deadbandrotation = 0;
			}
			else{
				deadbandrotation = Robot.oi.driverGamepad.getRightStickX();
			}
			if(Math.abs(Robot.oi.driverGamepad.getLeftStickY()) < 0.15){
				deadbandfwdback = 0;
			}
			else{
				deadbandfwdback = Robot.oi.driverGamepad.getLeftStickY();
			}
			lspd = deadbandfwdback+deadbandrotation;
			rspd = deadbandfwdback-deadbandrotation;

			if(Robot.oi.driverGamepad.getLeftBumper() == true){
		    	Robot.drivebase.driveTank(0.5*rspd, 0.5*lspd);
			}
			else{
		    	Robot.drivebase.driveTank(rspd, lspd);
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
