package org.usfirst.frc.team1058.robot.subsystems;

import org.usfirst.frc.team1058.robot.RobotMap;
import org.usfirst.frc.team1058.robot.commands.SetCycloneSpeed;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Cyclone extends Subsystem {
//CANTalon cycloneTalon = new CANTalon(RobotMap.CYCLONE_TALON_ID);
//CANTalon hopperTalon = new CANTalon(RobotMap.HOPPER_MOTOR_TALON_ID);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new SetCycloneSpeed(0,0));
    	
    }
    public void setCycloneSpeed(double speed){
    //	cycloneTalon.set(-speed);
    }
    public void setHopperSpeed(double speed){
    //	hopperTalon.set(-speed);
    }
   
}

