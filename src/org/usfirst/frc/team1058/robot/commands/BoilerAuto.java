package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;
import org.usfirst.frc.team1058.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class BoilerAuto extends CommandGroup {

    public BoilerAuto(boolean red) {
      addParallel(new SetShooterRPM(-4100));
      addSequential(new WaitCommand(2));
      addSequential(new SetCycloneSpeed(-0.4, 0.35),6);
      addSequential(new SetCycloneSpeed(0,0),0.4);
      addSequential(new SetShooterRPM(0),0.2);
      if(!red){
      addSequential(new DriveToPosition(0, 3.7, 6), 3);
      addSequential(new PrepareGearManipulator(), 3.5);
  	addSequential(new DriveGearPivot(RobotMap.INTAKE_PIVOT_VERTICAL_POSITION, 10),0.2);

      addSequential(new DriveStraightPosition(4, 6), 2);

      }
      else{
          addSequential(new DriveToPosition(3.7, 0, 6), 3);
          addSequential(new PrepareGearManipulator(), 3.5);
      	addSequential(new DriveGearPivot(RobotMap.INTAKE_PIVOT_VERTICAL_POSITION, 10),0.2);

          addSequential(new DriveStraightPosition(4, 6), 2);

      }
    }

}
