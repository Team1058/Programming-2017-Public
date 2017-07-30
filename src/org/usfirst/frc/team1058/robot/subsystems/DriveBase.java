package org.usfirst.frc.team1058.robot.subsystems;

import org.usfirst.frc.team1058.robot.Robot;
import org.usfirst.frc.team1058.robot.RobotMap;
import org.usfirst.frc.team1058.robot.commands.DriveTankDefault;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveBase extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
		int dtControlMode;
		public AnalogGyro driveGyro = new AnalogGyro(0);
		public Solenoid flashlight = new Solenoid(4);
		//Create the Talon objects
		public com.ctre.CANTalon leftDrive1 = new com.ctre.CANTalon(RobotMap.LEFT_DRIVE_1_TALON_ID);
		com.ctre.CANTalon leftDrive2 = new com.ctre.CANTalon(RobotMap.LEFT_DRIVE_2_TALON_ID);
		public com.ctre.CANTalon rightDrive1 = new com.ctre.CANTalon(RobotMap.RIGHT_DRIVE_1_TALON_ID);
		com.ctre.CANTalon rightDrive2 = new com.ctre.CANTalon(RobotMap.RIGHT_DRIVE_2_TALON_ID);
	
	//Create the new "solenoid" object for the pcm channel that the LED ring is plugged into
	public Solenoid gearledring = new Solenoid(RobotMap.GEAR_LED_RING_CHANNEL);
	public Solenoid shooterledring = new Solenoid(RobotMap.SHOOTER_LED_RING_CHANNEL);
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveTankDefault());

	}
	
	/*
	 * Initialize the drive base in one of two modes.
	 * 
	 * MODE 0: Default Voltage Control Mode
	 * When driving using driveTank, supply left and right variables as -1 to 1 for voltage speed
	 * 
	 * MODE 1: Speed Control Mode
	 * When driving using driveTank, supply left and right variables as -1 to 1 for max speed for each wheel 
	 * controlled by RPM management
	 */
	public void initDriveBase(int mode) throws Exception{
		dtControlMode = mode;
		
		if(mode == 0){
			//if db mode is zero, initialize the voltage mode
			initDefaultMode();
		}
		
		if(dtControlMode == 1){
			leftDrive1.enableBrakeMode(true);
			leftDrive2.enableBrakeMode(true);
			rightDrive1.enableBrakeMode(true);
			rightDrive2.enableBrakeMode(true);
			leftDrive1.setSafetyEnabled(false);
			leftDrive2.setSafetyEnabled(false);
			rightDrive1.setSafetyEnabled(false);
			rightDrive2.setSafetyEnabled(false);
			//if the control mode is speed based
			//set the modes of each drive motor - speed (PID) for the master of each side, follower for the slave of each side
			leftDrive1.changeControlMode(com.ctre.CANTalon.TalonControlMode.Speed);
			leftDrive2.changeControlMode(com.ctre.CANTalon.TalonControlMode.Follower);
			rightDrive1.changeControlMode(com.ctre.CANTalon.TalonControlMode.Speed);
			rightDrive2.changeControlMode(com.ctre.CANTalon.TalonControlMode.Follower);
			//set the PID values and other parameters of each drive motor MASTER only
			leftDrive1.setPID(RobotMap.DRIVEBASE_kP, RobotMap.DRIVEBASE_kI, RobotMap.DRIVEBASE_kD, RobotMap.DRIVEBASE_kF, 0, 0, 0);
			rightDrive1.setPID(RobotMap.DRIVEBASE_kP, RobotMap.DRIVEBASE_kI, RobotMap.DRIVEBASE_kD, RobotMap.DRIVEBASE_kF, 0, 0, 0);
			
			leftDrive1.configMaxOutputVoltage(12);
			rightDrive1.configMaxOutputVoltage(12);

			//enable control on both drive motors
			leftDrive1.enableControl();
			rightDrive1.enableControl();
			leftDrive1.reverseSensor(true);
			rightDrive1.reverseSensor(true);
			//tell each motor how many encoder ticks are on it for each revolution of the encoder shaft
			leftDrive1.configEncoderCodesPerRev(256);
			rightDrive1.configEncoderCodesPerRev(256);
			//set the feedback device of the drivebase motors (in this case, it is an encoder)
			leftDrive1.setFeedbackDevice(com.ctre.CANTalon.FeedbackDevice.QuadEncoder);
			rightDrive1.setFeedbackDevice(com.ctre.CANTalon.FeedbackDevice.QuadEncoder);
		}
		if(dtControlMode == 2){
			leftDrive1.setSafetyEnabled(false);
			leftDrive2.setSafetyEnabled(false);
			rightDrive1.setSafetyEnabled(false);
			rightDrive2.setSafetyEnabled(false);
			//if the control mode is speed based
			//set the modes of each drive motor - speed (PID) for the master of each side, follower for the slave of each side
			leftDrive1.changeControlMode(com.ctre.CANTalon.TalonControlMode.Position);
			leftDrive2.changeControlMode(com.ctre.CANTalon.TalonControlMode.Follower);
			rightDrive1.changeControlMode(com.ctre.CANTalon.TalonControlMode.Position);
			rightDrive2.changeControlMode(com.ctre.CANTalon.TalonControlMode.Follower);
			//set the PID values and other parameters of each drive motor MASTER only
			leftDrive1.setPID(0.52, 0, RobotMap.DRIVEBASE_kD, 0, 0, 500, 0);
			rightDrive1.setPID(0.52, 0, RobotMap.DRIVEBASE_kD, 0, 0, 500, 0);
			
			leftDrive1.configMaxOutputVoltage(4);
			rightDrive1.configMaxOutputVoltage(4);
			//enable control on both drive motors
			leftDrive1.enableControl();
			rightDrive1.enableControl();
			leftDrive1.reverseSensor(true);
			rightDrive1.reverseSensor(true);
			//tell each motor how many encoder ticks are on it for each revolution of the encoder shaft
			leftDrive1.configEncoderCodesPerRev(256);
			rightDrive1.configEncoderCodesPerRev(256);
			//set the feedback device of the drivebase motors (in this case, it is an encoder)
			leftDrive1.setFeedbackDevice(com.ctre.CANTalon.FeedbackDevice.QuadEncoder);
			rightDrive1.setFeedbackDevice(com.ctre.CANTalon.FeedbackDevice.QuadEncoder);
		}
		else{
			//if you don't specify the correct mode (0,1);
			throw new Exception("Invalid Drivebase Mode Specified");
		}
	}
	
	public void initDriveBase(){
		initDefaultMode();
		dtControlMode = 0;

	}
	
	void initDefaultMode(){
		
		//initialize the drivebase in voltage control mode - percentvbus for the masters, follower for the slaves
		leftDrive1.changeControlMode(com.ctre.CANTalon.TalonControlMode.PercentVbus);
		leftDrive2.changeControlMode(com.ctre.CANTalon.TalonControlMode.Follower);
		rightDrive1.changeControlMode(com.ctre.CANTalon.TalonControlMode.PercentVbus);
		rightDrive2.changeControlMode(com.ctre.CANTalon.TalonControlMode.Follower);
		
		//turn on brake mode on all motors for better stopping performance
		leftDrive1.enableBrakeMode(false);
		leftDrive2.enableBrakeMode(false);
		rightDrive1.enableBrakeMode(false);
		rightDrive2.enableBrakeMode(false);

	}
	
	//method called repeatedly to set each drive motors' speed 
	public void driveTank(double leftSpeed, double rightSpeed){
		//supply leftdrivespeed and right drivespeed (-1 to 1 fwd back each)
		//if the drivebase is in voltage mode
		if(dtControlMode == 0){
			leftDrive1.set(leftSpeed);
			leftDrive2.set(leftDrive1.getDeviceID());
			rightDrive1.set(-rightSpeed);
			rightDrive2.set(rightDrive1.getDeviceID());
			if(Robot.debugging){
			SmartDashboard.putNumber("Left Encoder Output", leftDrive1.getPosition());
			SmartDashboard.putNumber("Right Encoder Output", rightDrive1.getPosition());
			}
		}
		//Dave was here <3
		//if the drivebase is in speed PID mode
		if(dtControlMode == 1){
			if(Robot.debugging){
			SmartDashboard.putNumber("Left Encoder Output", leftDrive1.getPosition());
			SmartDashboard.putNumber("Right Encoder Output", rightDrive1.getPosition());
			}
			leftDrive1.setSetpoint(leftSpeed*60);
			leftDrive2.set(leftDrive1.getDeviceID());
			rightDrive1.setSetpoint(-rightSpeed*60);
			rightDrive2.set(rightDrive1.getDeviceID());

		}

		if(dtControlMode == 2){
			if(Robot.debugging){
			SmartDashboard.putNumber("Left Encoder Output", leftDrive1.getPosition());
			SmartDashboard.putNumber("Right Encoder Output", rightDrive1.getPosition());
			}
			leftDrive1.setSetpoint(leftSpeed);
			leftDrive2.set(leftDrive1.getDeviceID());
			rightDrive1.setSetpoint(rightSpeed);
			rightDrive2.set(rightDrive1.getDeviceID());

		}
	}
	public void setMaxOutputVoltage(double voltage){
		leftDrive1.configMaxOutputVoltage(voltage);
		rightDrive1.configMaxOutputVoltage(voltage);

	}
	public void resetGyro(){
		driveGyro.reset();
	}
	public void calibrateGyro(){
		driveGyro.calibrate();
	}
	

}
