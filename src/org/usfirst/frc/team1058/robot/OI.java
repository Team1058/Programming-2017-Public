package org.usfirst.frc.team1058.robot;

import org.usfirst.frc.team1058.robot.commands.BoilerAuto;
import org.usfirst.frc.team1058.robot.commands.CameraLookForward;
import org.usfirst.frc.team1058.robot.commands.DriveAndPivotGearRoller;
import org.usfirst.frc.team1058.robot.commands.DriveGearPivot;
import org.usfirst.frc.team1058.robot.commands.DriveGearRoller;
import org.usfirst.frc.team1058.robot.commands.DrivebaseVisionTracking;
import org.usfirst.frc.team1058.robot.commands.LightLEDRings;
import org.usfirst.frc.team1058.robot.commands.ManualClimberDrive;
import org.usfirst.frc.team1058.robot.commands.PlaceGear;
import org.usfirst.frc.team1058.robot.commands.SetCycloneSpeed;
import org.usfirst.frc.team1058.robot.commands.SetShooterRPM;
import org.usfirst.frc.team1058.robot.commands.ZeroGearManipEncoders;
import org.usfirst.frc.team1058.robot.triggers.GetDPad;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);
	public Gamepad driverGamepad = new Gamepad(0);
	public Gamepad operatorGamepad = new Gamepad(1);
	
	
	
	public OI(){
	 new JoystickButton(driverGamepad, 7).whenPressed(new LightLEDRings("gearmanipulator"));
	 new JoystickButton(operatorGamepad, 3).whileHeld(new ManualClimberDrive(-1));
	 new JoystickButton(operatorGamepad, 2).whileHeld(new ManualClimberDrive(-0.45));
	 new JoystickButton(operatorGamepad, 4).whenPressed(new PlaceGear(false, false));
	 new JoystickButton(operatorGamepad, 6).toggleWhenPressed(new SetShooterRPM(RobotMap.BOILER_EDGE_SHOOTER_RPM));
	 new JoystickButton(operatorGamepad, 5).whileHeld(new SetCycloneSpeed(-0.4, 0.4));
	 new JoystickButton(operatorGamepad, 9).whileHeld(new SetCycloneSpeed(-1, 0.4));

	 new GamepadTrigger(operatorGamepad, "left").whenPressed(new DriveGearPivot(RobotMap.INTAKE_PIVOT_VERTICAL_POSITION, 8));
	 new JoystickButton(operatorGamepad, 1).whileHeld(new DriveGearRoller(-0.5));
	 new JoystickButton(operatorGamepad, 7).whenPressed(new ZeroGearManipEncoders());
	 new GetDPad(operatorGamepad, 270).whileActive(new DriveAndPivotGearRoller(
			 1,RobotMap.INTAKE_PIVOT_GEARINTAKE_POSITION));
	 new GetDPad(operatorGamepad, 225).whileActive(new DriveAndPivotGearRoller(
			 1,RobotMap.INTAKE_PIVOT_GEARINTAKE_POSITION));
	 new GetDPad(operatorGamepad, 315).whileActive(new DriveAndPivotGearRoller(
			1,RobotMap.INTAKE_PIVOT_GEARINTAKE_POSITION));
	 new GetDPad(operatorGamepad, 90).whileActive(new DriveAndPivotGearRoller(
			 1,RobotMap.INTAKE_PIVOT_BALLINTAKE_POSITION));
	 new GetDPad(operatorGamepad, 45).whileActive(new DriveAndPivotGearRoller(
			 1,RobotMap.INTAKE_PIVOT_BALLINTAKE_POSITION));
	 new GetDPad(operatorGamepad, 135).whileActive(new DriveAndPivotGearRoller(
			 1,RobotMap.INTAKE_PIVOT_BALLINTAKE_POSITION));
	 
	}
	 
	
	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	
}
