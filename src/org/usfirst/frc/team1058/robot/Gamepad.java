package org.usfirst.frc.team1058.robot;

/**
 * Class for using the logitech F310 gamepad in Xinput mode - ensure the triggers are reading axes, not booleans
 * written for FRC team 3467
 * @author Emile Hamwey
 *
 */
public class Gamepad extends edu.wpi.first.wpilibj.Joystick {

	
	public Gamepad(int port) {
		super(port);
	}
	
	public double getLeftStickX() {
		return getRawAxis(RobotMap.leftStick_xAxis);
	}
	
	public double getLeftStickY() {
		return getRawAxis(RobotMap.leftStick_yAxis);
	}
	
	public boolean getLeftAxisButton() {
		return getRawButton(RobotMap.leftStickPress);
	}
	
	public double getRightStickX() {
		return getRawAxis(RobotMap.rightStick_xAxis);
	}
	
	public double getRightStickY() {
		return getRawAxis(RobotMap.rightStick_yAxis);
	}
	
	public boolean getRightAxisButton() {
		return getRawButton(RobotMap.rightStickPress);
	}
	
	public boolean getLeftBumper() {
		return getRawButton(RobotMap.leftBumper);
	}
	
	public boolean getRightBumper() {
		return getRawButton(RobotMap.rightBumper);
	}
	
	public boolean getLeftTrigger() {
		boolean leftTrigger = false;
		if(getRawAxis(RobotMap.leftTrigger_Axis) > .8 ){
			leftTrigger = true;
		}
		else{
			leftTrigger = false;
		}
		return leftTrigger;
	}
	
	public boolean getRightTrigger() {
		boolean rightTrigger = false;
		if(getRawAxis(RobotMap.rightTrigger_Axis) > .8 ){
			rightTrigger = true;
		}
		else{
			rightTrigger = false;
		}
		return rightTrigger;
	}
	
	public boolean getXButton() {
		return getRawButton(RobotMap.xButton);
	}
	
	public boolean getAButton() {
		return getRawButton(RobotMap.aButton);
	}
	
	public boolean getBButton() {
		return getRawButton(RobotMap.bButton);
	}
	
	public boolean getYButton() {
		return getRawButton(RobotMap.yButton);
	}
	
	public boolean getStartButton() {
		return getRawButton(RobotMap.startButton);
	}
	
	public boolean getBackButton() {
		return getRawButton(RobotMap.backButton);
	}
	
	public boolean getDpadUp() {
		return(getPOV(0) == 0 ? true : false);
	}
	
	public boolean getDpadRight() {
		return(getPOV(0) == 90 ? true : false);
	}
	
	public boolean getDpadDown() {
		return(getPOV(0) == 180 ? true : false);
	}

	public boolean getDpadLeft() {
		return(getPOV(0) == 270 ? true : false);
	}
	
	
}