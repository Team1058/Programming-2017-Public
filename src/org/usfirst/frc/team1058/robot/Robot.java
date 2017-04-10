
package org.usfirst.frc.team1058.robot;

import org.opencv.core.Mat;
import org.usfirst.frc.team1058.robot.commands.BoilerAuto;
import org.usfirst.frc.team1058.robot.commands.DriveStraightCenter;
import org.usfirst.frc.team1058.robot.commands.DriveToLeftRightFace;
import org.usfirst.frc.team1058.robot.commands.GoAndReturn;
import org.usfirst.frc.team1058.robot.commands.PlaceGear;
import org.usfirst.frc.team1058.robot.commands.PrepareGearManipulator;
import org.usfirst.frc.team1058.robot.commands.SetCycloneSpeed;
import org.usfirst.frc.team1058.robot.commands.TurnTillPerpVision;
import org.usfirst.frc.team1058.robot.subsystems.CameraTurret;
import org.usfirst.frc.team1058.robot.subsystems.Climber;
import org.usfirst.frc.team1058.robot.subsystems.Cyclone;
import org.usfirst.frc.team1058.robot.subsystems.DriveBase;
import org.usfirst.frc.team1058.robot.subsystems.GearManipulator;
import org.usfirst.frc.team1058.robot.subsystems.LEDs;
import org.usfirst.frc.team1058.robot.subsystems.Shooter;
import org.usfirst.frc.team1058.robot.subsystems.VisionNetworkTable;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;

	private VisionThread visionThread;
	private double centerX = 0.0;
	private double centerY = 0.0;
	byte numToSend;

	private RobotDrive drive;
	public static boolean debugging = false;
	private final Object imgLock = new Object();
	public static final DriveBase drivebase = new DriveBase();
	public static final Shooter shooter = new Shooter();
	public static final Climber climber = new Climber();
	public static final GearManipulator gearManipulator = new GearManipulator();
	public static final GoalTrackingPipeline goalPipeline = new GoalTrackingPipeline();
	public static final GearTrackingPipeline gearPipeline = new GearTrackingPipeline();
	public static final CameraTurret cameraTurret = new CameraTurret();
	public static final Cyclone cyclone = new Cyclone();
	public static final VisionNetworkTable visionNetworkTable= new VisionNetworkTable();
	public static final LEDs leds = new LEDs();
	public static OI oi;
	public static boolean isAutonomous = false;
	public static boolean isEnabled = false;
	public static boolean isTeleoperated = false;
	String dfile;
	String ofile;
	Mat goaloutputmat;
	Mat gearoutputmat;
	Command autonomousCommand;
	CameraServer gearcamera;
	SendableChooser<Command> chooser = new SendableChooser<>();
	SendableChooser<Command> shooterModeChooser = new SendableChooser<>();

	//SendableChooser<Command> autoBuilder = new SendableChooser<>();
	//SendableChooser<Command> sOrP = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		drivebase.driveGyro.calibrate();

		chooser.addDefault("Default Prepare Gear Auto", new PrepareGearManipulator());
		//shooterModeChooser.addDefault("Shooter at Nominal Preset when Pressed", object);
		/*chooser.addObject("Left Gear Auto", new LeftGearGroup());
		chooser.addObject("Center Gear Auto", new CenterGearGroup());*/
	//	chooser.addObject("Test Vision Auto", new TurnTillPerpVision(true));
	//	chooser.addObject("Testing turn gyro", new RotateToGyroAngle(90,4));
		chooser.addObject("DriveStraightTest", new GoAndReturn());
		 //UNNECESSARY AUTOS FOR TESTING ^
		chooser.addObject("Center Gear Encoder Auto (place)", new DriveStraightCenter());
		chooser.addObject("Vision center TESTING!!!", new TurnTillPerpVision());
		chooser.addObject("Boiler Auto RED", new BoilerAuto(true));
		chooser.addObject("Boiler Auto BLUE", new BoilerAuto(false));

	//	chooser.addObject("Center Gear Encoder Auto (do not place )", new DriveToFace(false, false, false));
		chooser.addObject("Side Gear Auto (do not place)", new DriveToLeftRightFace(false,false,false,false));
		//chooser.addObject("Side Gear Auto (place)", new DriveToLeftRightFace(true,false,false,false));
		//chooser.addObject("Test DriveStraight Auto", new DriveStraightPosition(10,5));
		//chooser.addObject("Center Gear Encoder Auto (place)", new DriveStraightCenter());
		/*InterpreterGroup  interp = new InterpreterGroup();
		if(interp.init()){
			chooser.addObject("Interpreter",interp);
		}*/
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto Chooser", chooser);

		Robot.drivebase.leftDrive1.setPosition(0);
    	Robot.drivebase.rightDrive1.setPosition(0);
    	
		
    	Robot.leds.initializei2cBus();
    	Timer.delay(3);
    	gearcamera = CameraServer.getInstance();

		gearcamera.startAutomaticCapture();


		//NetworkTable.setClientMode();
    	//NetworkTable.setIPAddress("raspberrypi.local");
		
		
		/*
		 * new Thread(() -> { UsbCamera goalcamera =
		 * CameraServer.getInstance().startAutomaticCapture();
		 * goalcamera.setResolution(320, 240); goalcamera.setFPS(30);
		 * goalcamera.setBrightness(1); goalcamera.setExposureManual(1);
		 * 
		 * CvSink goalCvSink = CameraServer.getInstance().getVideo();
		 * 
		 * Mat source = new Mat();
		 * 
		 * while(!Thread.interrupted()) { goalCvSink.grabFrame(source);
		 * goaloutputmat = source; } }).start();
		 * goalPipeline.process(goaloutputmat);
		 * 
		 * new Thread(() -> { UsbCamera gearcamera =
		 * CameraServer.getInstance().startAutomaticCapture();
		 * gearcamera.setResolution(320, 240); gearcamera.setFPS(30);
		 * gearcamera.setBrightness(1); gearcamera.setExposureManual(1);
		 * 
		 * CvSink gearCvSink = CameraServer.getInstance().getVideo();
		 * 
		 * Mat source = new Mat();
		 * 
		 * while(!Thread.interrupted()) { gearCvSink.grabFrame(source);
		 * gearoutputmat = source; } }).start();
		 * gearPipeline.process(gearoutputmat);
		 */
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {


	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		isAutonomous = false;
		isTeleoperated = false;
		isEnabled = false;
		updateLedState();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//SmartDashboard.getData("Driver File");
		//SmartDashboard.getData("Operator File");
		autonomousCommand = chooser.getSelected();
		isAutonomous = true;
		isTeleoperated = false;
		isEnabled = true;
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		Robot.gearManipulator.gearManipulatorPivot.setPosition(0);
		Robot.gearManipulator.gearManipulatorPivot.setSetpoint(0);
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		SmartDashboard.putNumber("Gyro", Robot.drivebase.driveGyro.getAngle());

		Scheduler.getInstance().run();
		isAutonomous = true;
		isTeleoperated = false;
		isEnabled = true;
		visionNetworkTable.getGearData();
		visionNetworkTable.showGearData();
		updateLedState();
		//visionNetworkTable.getHighData();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		isAutonomous = false;
		isTeleoperated = true;
		isEnabled = true;
		try {
			drivebase.initDriveBase(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		isAutonomous = false;
		isTeleoperated = true;
		isEnabled = true;
		updateLedState();
		SmartDashboard.putNumber("Gyrooo", Robot.drivebase.driveGyro.getAngle());
		//visionNetworkTable.getGearData();
	//	visionNetworkTable.showGearData();
		if(Robot.debugging){			
			SmartDashboard.putNumber("Shooter1RPM  Setpoint", shooter.shooter1.getSetpoint());
	    	SmartDashboard.putNumber("Intake Pivot Encoder Position", Robot.gearManipulator.gearManipulatorPivot.getPosition());
	
			SmartDashboard.putNumber("Gear Manipulator Setpoint", gearManipulator.gearManipulatorPivot.getSetpoint());
		}
		//	visionNetworkTable.getGearData();
		//visionNetworkTable.getHighData();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
		isAutonomous = false;
		isTeleoperated = true;
		isEnabled = true;
	}

public void updateLedState(){
	 if(PlaceGear.placingGear == true){
 		if(DriverStation.getInstance().getAlliance() == Alliance.Blue){
 			numToSend = 3;
 		}
 		if(DriverStation.getInstance().getAlliance() == Alliance.Red){
 			numToSend = 4;
 		}
 		if(DriverStation.getInstance().getAlliance() == Alliance.Invalid){
 			numToSend = 4;
 		}
 	}
 	else if(Robot.gearManipulator.gearManipulatorPivot.getSetpoint() == RobotMap.INTAKE_PIVOT_GEARINTAKE_POSITION){
 		numToSend = 5;
 		
 	}
 	else if(Robot.gearManipulator.gearManipulatorPivot.getSetpoint() == RobotMap.INTAKE_PIVOT_BALLINTAKE_POSITION){
 		numToSend = 6;
 	}
 	else if(Robot.climber.climberTalon.getOutputVoltage() != 0){
 		numToSend = 7;
 	}
 	
 	else if(Robot.shooter.shooter1.getSetpoint() != 0 && SetCycloneSpeed.cycloneOn){
 		numToSend = 8;
 	}
 	else{
 		if(DriverStation.getInstance().isEnabled()){
 		 	if(Robot.gearManipulator.gearManipulatorPivot.getSetpoint() == RobotMap.INTAKE_PIVOT_VERTICAL_POSITION){
 	    		if(DriverStation.getInstance().getAlliance() == Alliance.Blue){
 	    			numToSend = 2;
 	    		}
 	    		if(DriverStation.getInstance().getAlliance() == Alliance.Red){
 	    			numToSend = 1;
 	    		}
 	    		if(DriverStation.getInstance().getAlliance() == Alliance.Invalid){
 	    			numToSend = 1;
 	    		}
 	    	}
 		}
 		if(DriverStation.getInstance().isDisabled()){
 		numToSend = 0;
 		}
 	}
		Robot.leds.setMode(numToSend);
		System.out.println("LED State " + numToSend);
}
}
