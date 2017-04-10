package org.usfirst.frc.team1058.robot;

/**
 * Class for using the logitech F310 gamepad in Xinput mode - ensure the triggers are reading axes, not booleans
 * written for FRC team 3467
 * @author Emile Hamwey
 *
 */

public class Gamepad2 extends edu.wpi.first.wpilibj.Joystick {
		//GAMEPAD STEEL SERIES PORTS
	
		public static final int xButton = 3;
		public static final int aButton = 2;
		public static final int bButton = 1;
		public static final int yButton = 0;
		public static final int leftBumper = 6;
		public static final int rightBumper = 7;
		public static final int backButton = 8;
		public static final int startButton = 9;
		public static final int leftStickPress = 10;
		public static final int rightStickPress = 11;
		
		public static final int leftStick_xAxis = 0;
		public static final int leftStick_yAxis = 1;
		public static final int leftTrigger_Axis = 4;
		public static final int rightTrigger_Axis = 5;
		public static final int rightStick_xAxis = 3;
		public static final int rightStick_yAxis = 4;
		
	
	public Gamepad2(int port) {
		super(port);
	}
	
	public double getLeftStickX() {
		return getRawAxis(leftStick_xAxis);
	}
	
	public double getLeftStickY() {
		return getRawAxis(leftStick_yAxis);
	}
	
	public boolean getLeftAxisButton() {
		return getRawButton(leftStickPress);
	}
	
	public double getRightStickX() {
		return getRawAxis(rightStick_xAxis);
	}
	
	public double getRightStickY() {
		return getRawAxis(rightStick_yAxis);
	}
	
	public boolean getRightAxisButton() {
		return getRawButton(rightStickPress);
	}
	
	public boolean getLeftBumper() {
		return getRawButton(leftBumper);
	}
	
	public boolean getRightBumper() {
		return getRawButton(rightBumper);
	}
	
	public boolean getLeftTrigger() {
		return getRawButton(leftTrigger_Axis);
	}
	
	public boolean getRightTrigger() {
		return getRawButton(rightTrigger_Axis);
	}
	
	public boolean getXButton() {
		return getRawButton(RobotMap.xButton);
	}
	
	public boolean getAButton() {
		return getRawButton(aButton);
	}
	
	public boolean getBButton() {
		return getRawButton(bButton);
	}
	
	public boolean getYButton() {
		return getRawButton(yButton);
	}
	
	public boolean getStartButton() {
		return getRawButton(startButton);
	}
	
	public boolean getBackButton() {
		return getRawButton(backButton);
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