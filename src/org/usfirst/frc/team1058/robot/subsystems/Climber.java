package org.usfirst.frc.team1058.robot.subsystems;

import org.usfirst.frc.team1058.robot.Robot;
import org.usfirst.frc.team1058.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public CANTalon climberTalon = new CANTalon(RobotMap.CLIMBER_TALON_ID);
	public CANTalon climberTalon2 = new CANTalon(RobotMap.HOPPER_MOTOR_TALON_ID);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	climberTalon.enableBrakeMode(true);
    	climberTalon2.enableBrakeMode(true);

    }
    
   public void setClimberOutput(double voltage){
	   if(Robot.oi.operatorGamepad.getStartButton()){
		   climberTalon.set(-voltage);
		   climberTalon2.set(voltage);
	   }
	   else{
	   climberTalon.set(voltage);
	   climberTalon2.set(-voltage);
	   }
   }
   
   public double getClimberCurrent(){
	   return climberTalon.getOutputCurrent();
   }
}

