/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2017. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1058.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * A {@link Button} that gets its state from a {@link GenericHID}.
 */
public class GamepadTrigger extends Button {

  private final Gamepad m_joystick;
  private final String m_triggerSide;
	 boolean buttonTrue;


  public GamepadTrigger(Gamepad joystick, String triggerSide) {
    m_joystick = joystick;
    m_triggerSide = triggerSide;
    buttonTrue = false;
  }

  /**
   * Gets the value of the joystick button.
   *
   * @return The value of the joystick button
   */
  public boolean get() {
	  if(m_triggerSide.equals("left")){
		  
		  buttonTrue = m_joystick.getLeftTrigger();
		  
	  }
	  else if(m_triggerSide.equals("right")){
		    buttonTrue = m_joystick.getRightTrigger();

	  }
		 return buttonTrue;
  }
}
