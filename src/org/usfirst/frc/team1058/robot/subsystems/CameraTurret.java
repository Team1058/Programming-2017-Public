package org.usfirst.frc.team1058.robot.subsystems;

import org.usfirst.frc.team1058.robot.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 *
 */
public class CameraTurret extends Subsystem {
	Servo servo = new Servo(RobotMap.CAMERA_SERVO);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void cameraLook(int position) {
    	servo.setAngle(position);
    }
}

