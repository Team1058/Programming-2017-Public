package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SetShooterRPM extends Command {
double k_rpm;
boolean done, ledstatus;
    public SetShooterRPM(double rpm) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    	k_rpm = rpm;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(!SetCycloneSpeed.cycloneOn){
    		if(k_rpm != 0){
    		Robot.numToSend = 9;
    		}
    	}
    	if(Robot.debugging){
    	SmartDashboard.putNumber("Desired Speed", 0);
    	}
    	done = false;
    	ledstatus = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(Robot.debugging){
    		Robot.shooter.setShooterSpeed(SmartDashboard.getNumber("Desired Speed", 0));
    	}
    	else{
    	Robot.shooter.setShooterSpeed(k_rpm);
    	}
    	if(Robot.shooter.shooter1.getSetpoint() != 0){
    	if((Robot.shooter.shooter1.getSpeed() > Robot.shooter.shooter1.getSetpoint()-20) && 
    			(Robot.shooter.shooter1.getSpeed() < Robot.shooter.shooter1.getSetpoint()+20)){
        	
    		ledstatus = !ledstatus;
    	}
    	else{
    		ledstatus = true;
    	}
    	}
    	else{
    		ledstatus = false;
    	}
    	Robot.drivebase.shooterledring.set(ledstatus);
   // 	System.out.println(Robot.shooter.shooter1.getSpeed());
    }

    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.setShooterSpeed(0);
    	Robot.numToSend = 3; 
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.setShooterSpeed(0);
    	Robot.numToSend = 3;
    }
}
