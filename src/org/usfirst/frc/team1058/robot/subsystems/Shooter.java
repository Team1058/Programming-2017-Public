package org.usfirst.frc.team1058.robot.subsystems;

import org.usfirst.frc.team1058.robot.Robot;
import org.usfirst.frc.team1058.robot.RobotMap;
import org.usfirst.frc.team1058.robot.commands.SetShooterRPM;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public CANTalon shooter1 = new CANTalon(RobotMap.SHOOTER1_TALON_ID);
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	Robot.shooter.initShooterPIDMode();
    	setDefaultCommand(new SetShooterRPM(0));
    }
    
    public void initShooterPIDMode(){
    	shooter1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	shooter1.changeControlMode(TalonControlMode.Speed);
    	shooter1.setPID(0.2, 0, 0.1, 0.8,0,1000,0);
    	shooter1.setPosition(0);
    	shooter1.enableControl();
    	shooter1.reverseSensor(false);
    	
    
    }
    public void setShooterSpeed(double rpm){
    	shooter1.setSetpoint(-rpm);
    	if(Robot.debugging){
    	SmartDashboard.putNumber("RPM - Shooter 1", shooter1.getSpeed());
    	SmartDashboard.putNumber("Voltage - Shooter 1", shooter1.getOutputVoltage());
    	}
    	
    }
    
}

