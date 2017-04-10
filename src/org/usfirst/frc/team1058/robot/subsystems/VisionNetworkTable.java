package org.usfirst.frc.team1058.robot.subsystems;

import org.usfirst.frc.team1058.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionNetworkTable extends Subsystem {
	public double gearDiffArea;
	public double gearRect1Area;
	public double gearRect2Area;
	public double gearDistanceFromCenter;
	public double gearDistanceInches;
	public double gearMatNum;
	public double highDistanceInches;
	public double highDiffArea;
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static NetworkTable table;
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	table = NetworkTable.getTable("PirateVisionReport");
    }
    public void getGearData(){
    	gearDiffArea = table.getNumber("gearDiffArea", 11);
    	gearDistanceFromCenter = table.getNumber("gearDistanceFromCenter", 10);
		gearDistanceInches = table.getNumber("gearDistanceInches", 10);
		gearMatNum = table.getNumber("gearMatNum", 0);
		gearRect1Area = table.getNumber("rect1Area", 0);
		gearRect2Area = table.getNumber("rect2Area", 0);

    }
    public void showGearData(){
		SmartDashboard.putNumber("diffArea ", gearDiffArea);
		SmartDashboard.putNumber("distanceFromCenter ", gearDistanceFromCenter);
		SmartDashboard.putNumber("distanceInches ", gearDistanceInches);
		SmartDashboard.putNumber("matNum", gearMatNum);
		SmartDashboard.putNumber("rect1Area", gearRect1Area);
		SmartDashboard.putNumber("rect2Area", gearRect2Area);

    	
    }
    public void getHighData(){
    	highDistanceInches = table.getNumber("highDistanceInches", 11);
    	highDiffArea = table.getNumber("highDiffArea", 15);
    }
    public void showHighData(){
    	if(Robot.debugging){
		SmartDashboard.putNumber("highDistanceInches", highDistanceInches);
		SmartDashboard.putNumber("highDiffArea", highDiffArea);
    	}
    }
}

