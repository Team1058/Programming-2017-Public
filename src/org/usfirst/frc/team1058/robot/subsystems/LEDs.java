package org.usfirst.frc.team1058.robot.subsystems;

import org.usfirst.frc.team1058.robot.commands.UpdateLEDs;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LEDs extends Subsystem {
SerialPort arduino;
byte[] arrayToSend;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new UpdateLEDs());
    }
    
    public void initializei2cBus(){
    	 arrayToSend = new byte[1];

    	arduino = new SerialPort(9600, Port.kUSB, 8);
    	
    
    }
    public void setMode(byte numToSend){
    	arrayToSend[0] = numToSend;
    	arduino.write(arrayToSend, 1);
    }
}

