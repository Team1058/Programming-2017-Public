package org.usfirst.frc.team1058.robot.subsystems;

import org.usfirst.frc.team1058.robot.Robot;
import org.usfirst.frc.team1058.robot.RobotMap;
import org.usfirst.frc.team1058.robot.commands.PivotJoystickPID;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class GearManipulator extends Subsystem {
	public Solenoid gearSolenoid = new Solenoid(RobotMap.GEAR_MANIPULATOR_SOLENOID);
	public CANTalon gearManipulatorPivot = new CANTalon(RobotMap.GEAR_MANIPULATOR_PIVOT_TALON_ID);
	public CANTalon gearManipulatorRoller = new CANTalon(RobotMap.CYCLONE_TALON_ID);
	double kP = 2;
	double kI = 0;
	double kD = 0.001;

	boolean ledState = false;
	boolean timesRun = false;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	initializePivotMotors(true);
    	setDefaultCommand(new PivotJoystickPID(RobotMap.INTAKE_PIVOT_VERTICAL_POSITION, 10));
    }
    
    public void setRollerSpeed(double speed){
    	if(speed == 0){
    		ledState = false;    		
    	}
    	else{
    		ledState = !ledState;
    	
    	}
    		SmartDashboard.putBoolean(" ", ledState);
    	if(Robot.oi.operatorGamepad.getStartButton()){
        	gearManipulatorRoller.set(-speed);

    	}
    	else{
    	gearManipulatorRoller.set(speed);
    	}
    }
    
    public void initializePivotMotors(boolean pidmode){
    	if(pidmode){
    	gearManipulatorPivot.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	gearManipulatorPivot.changeControlMode(CANTalon.TalonControlMode.Position);
    	//gearManipulatorPivot.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	gearManipulatorPivot.setPID(kP, kI, kD);
    	gearManipulatorPivot.enableControl();
    	gearManipulatorPivot.setPosition(0);
    	gearManipulatorPivot.reverseOutput(false);
    	gearManipulatorPivot.reverseSensor(false);
    	gearManipulatorPivot.setForwardSoftLimit(0);
    	}
    	else{
    		gearManipulatorPivot.setPosition(0);
    		gearManipulatorPivot.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        	gearManipulatorPivot.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
        	
    	}
    }
    
    public void setIntakeMaxSpeed(double maxVoltage){
    	gearManipulatorPivot.configMaxOutputVoltage(maxVoltage);
    }
    public void setIntakePosition(double setpoint){
    	if(setpoint>0 || Robot.oi.operatorGamepad.getStartButton()){
    	gearManipulatorPivot.setSetpoint(-setpoint);
    	}
    }
    
    public void drivePivotManual(double output){
    	gearManipulatorPivot.set(output);
    }
}

