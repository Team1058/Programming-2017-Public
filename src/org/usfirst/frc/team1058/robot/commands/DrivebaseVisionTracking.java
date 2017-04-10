package org.usfirst.frc.team1058.robot.commands;

/*import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
*/
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import org.usfirst.frc.team1058.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivebaseVisionTracking extends Command {
double distanceFromCenter;
double distanceInches;
double lspd, rspd = 0;
double x_lspd, x_rspd = 0;
double yspd = 0;
NetworkTable table;
    public DrivebaseVisionTracking() {
    	
    	//send a 1 to itemToTrack for the goal tracking mode, send a 2 for the gear tracking mode
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivebase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	table = NetworkTable.getTable("PirateVisionReport");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Your code needs to put two doubles here: distanceFromCenter, distanceInches and update them everytime the loop runs.
    	/*
    	String hostName = "10.10.58.2";
    	int portNumber = 1111;
    	Socket soc;
    	try {
    		soc= new Socket(hostName,portNumber);
    		
    		DataInputStream dis;
    		DataOutputStream dos;
    		StringBuffer jta=new StringBuffer("");
    		dis = new DataInputStream(soc.getInputStream());
    		dos = new DataOutputStream(soc.getOutputStream());
    		Scanner scan = new Scanner(dis);
    			try{
    				dos.writeChars("I'm Mr Meeseecks look at me! Can I get Distance?");
    				String line = scan.nextLine();
    				
    			}catch(Exception e){}
    		
    	}catch(IOException e1){}*/
    	distanceFromCenter = table.getNumber("distanceFromCenter",0);
    	distanceInches = table.getNumber("distanceInches",7);
    	if(distanceFromCenter != 0){
    	 x_lspd = ((distanceFromCenter/160));
    	 

    	 x_rspd = ((distanceFromCenter/160) + distanceFromCenter/200);
    	}
    	if(distanceFromCenter == 0){
    		x_rspd = 0;
    		x_lspd = 0;
    	}
    
    	if(distanceInches > 48){
    		yspd = -distanceInches/150;
    		
    	}
    	if(distanceInches < 41){
    		yspd = distanceInches/150;
    	}
    	if(distanceInches >= 41 || distanceInches <= 48){
    		yspd = 0;
    	}
    	Robot.drivebase.driveTank((x_lspd/1.7+yspd), (-x_rspd/1.7+yspd));
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
