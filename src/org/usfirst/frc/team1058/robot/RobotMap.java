package org.usfirst.frc.team1058.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	
	//TALON IDS
	public static final int SHOOTER1_TALON_ID = 5;
	public static final int HOPPER_MOTOR_TALON_ID = 0;
	public static final int GEAR_MANIPULATOR_PIVOT_TALON_ID = 6;
	public static final int LEFT_DRIVE_1_TALON_ID = 7;
	public static final int LEFT_DRIVE_2_TALON_ID = 8;
	public static final int RIGHT_DRIVE_1_TALON_ID = 3;
	public static final int RIGHT_DRIVE_2_TALON_ID = 2;
	public static final int CLIMBER_TALON_ID = 4;
	public static final int CYCLONE_TALON_ID = 1;
	
	//PWM IDS
	public static final int GEAR_MANIPULATOR_ROLLER_VICTOR_ID = 1;
	public static final int CAMERA_SERVO = 0;

	//MISC IDS:
	public static final int GEAR_MANIPULATOR_SOLENOID = 6;
	public static final int GEAR_LED_RING_CHANNEL = 0;
	public static final int SHOOTER_LED_RING_CHANNEL = 1;
	//DONT USE PCM CHANNEL 7 IT THROWS ERRORS
	
	//CONSTANT VALUES
	public static final double DRIVEBASE_kP = 0.35;
	public static final double DRIVEBASE_kI = 0;
	public static final double DRIVEBASE_kD = 0;
	public static final double DRIVEBASE_kF = 1;

	public static final double DRIVEBASE_POSITION_TOLERANCE = 0.5;
	public static final double DRIVEBASE_MAX_RPM = 653;
	public static final double DRIVEBASE_COUNTS_PER_REV = 256;
	
	public static final double BOILER_EDGE_SHOOTER_RPM = -4100;//run at -4100 for nominal speed
	
	public static final double INTAKE_PIVOT_ENCODER_TOLERANCE = 50;
	public static final double INTAKE_PIVOT_STOW_POSITION = 0;
	public static final double INTAKE_PIVOT_VERTICAL_POSITION = 1800; //1750 for normal position, 1950 for home peg
	public static final double INTAKE_PIVOT_GEARINTAKE_POSITION = 3750;
	public static final double INTAKE_PIVOT_BALLINTAKE_POSITION = 982;
	//  you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	//GAMEPAD PORTS
	
	
	public static final int xButton = 3;
	public static final int aButton = 1;
	public static final int bButton = 2;
	public static final int yButton = 4;
	public static final int leftBumper = 5;
	public static final int rightBumper = 6;
	public static final int backButton = 7;
	public static final int startButton = 8;
	public static final int leftStickPress = 9;
	public static final int rightStickPress = 10;
	
	public static final int leftStick_xAxis = 0;
	public static final int leftStick_yAxis = 1;
	public static final int leftTrigger_Axis = 2;
	public static final int rightTrigger_Axis = 3;
	public static final int rightStick_xAxis = 4;
	public static final int rightStick_yAxis = 5;

	
	
	
	
	
	
}
