package org.usfirst.frc.team1058.robot.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.usfirst.frc.team1058.robot.Robot;
import org.usfirst.frc.team1058.robot.RobotMap;
import org.usfirst.frc.team1058.robot.subsystems.DriveBase;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;;
/**
 *
 */
public class LogAuto extends Command {
	Scanner driver;
	Scanner operator;
	String driverLog;
	String operatorLog;
    public LogAuto(String dfile, String ofile) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivebase);
    	requires(Robot.shooter);
    	requires(Robot.gearManipulator);
    	requires(Robot.cyclone);
    	driverLog = dfile;
    	operatorLog = ofile;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	String[] driverTokens;
    	String[] operatorTokens;
    	double deadbandrotation, deadbandfwdback;
    	try {
    		Scanner driver = new Scanner(new FileReader(driverLog));
    		Scanner operator = new Scanner(new FileReader(operatorLog));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
    	while (driver.hasNextLine() || operator.hasNextLine()){
    		driverTokens = driver.nextLine().split(",");
    		operatorTokens = operator.nextLine().split(",");
    		if (driver.hasNextLine()){
    			if(Math.abs(Double.parseDouble(driverTokens[2])) < 0.1){
    				deadbandrotation = 0;
    			}
    			else{
    				deadbandrotation = Double.parseDouble(driverTokens[2]);
    			}
    			if(Math.abs(Double.parseDouble(driverTokens[1])) < 0.1){
    				deadbandfwdback = 0;
    			}
    			else{
    				deadbandfwdback = Double.parseDouble(driverTokens[1]);
    			}
    			Robot.drivebase.driveTank(deadbandfwdback+deadbandrotation, deadbandfwdback-deadbandrotation);
    		}
    		//4,6
    		if (operator.hasNextLine()){
    			if (operatorTokens[4] == "T"){
    				new DriveAndPivotGearRoller(1,RobotMap.INTAKE_PIVOT_GEARINTAKE_POSITION);
    			}else{
    				
    			}
    			if (operatorTokens[6] == "T"){
    				new DriveAndPivotGearRoller(1,RobotMap.INTAKE_PIVOT_BALLINTAKE_POSITION);
    			}else{
    				
    			}
    			
    			if (operatorTokens[8] == "A=T"){
    				new DriveGearRoller(-0.5);
    			}else{
    				
    			}
    			if (operatorTokens[9] == "B=T"){
    				new ManualClimberDrive(-0.75);
    			}else{
    				
    			}
    			if (operatorTokens[10] == "Y=T"){
    				new PlaceGear(false,false);
    				
    			}else{
    				
    			}
    			if (operatorTokens[11] == "X=T"){
    				new ManualClimberDrive(-1);
    			}else{
    				
    			}
    			if (operatorTokens[12] == "L1=T"){
    				new SetCycloneSpeed(-0.9, 0.5);
    			}else{
    				
    			}
    			if (operatorTokens[14] == "R1=T"){
    				new SetShooterRPM(RobotMap.BOILER_EDGE_SHOOTER_RPM);
    			}else{
    				
    			}
    			Timer.delay(.02);
    		}
    	}
    	
		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
