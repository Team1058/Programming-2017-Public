package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DriveStraightSide extends CommandGroup {

    public DriveStraightSide(boolean turnAfter, boolean leftTurnAfter) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new ZeroEncoders());
    	addParallel(new PrepareGearManipulator());
    	addSequential(new WaitCommand(3.5));
    	addParallel(new DriveGearPivot(RobotMap.INTAKE_PIVOT_VERTICAL_POSITION, 6),1);
    	addSequential(new DriveStraightPosition(5.8,3),5);
    	if(!leftTurnAfter){
        	addSequential(new RotateToGyroAngle(-50, 2),2);

    	}
    	else{
        	addSequential(new RotateToGyroAngle(50, 2),2);

    	}
    	addSequential(new DriveTimedTank(-0.3,-0.3, 1.6));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new PlaceGear(false,false), 1);
    	addSequential(new DriveGearPivot(RobotMap.INTAKE_PIVOT_GEARINTAKE_POSITION-400, 6), 0.5);
    	addParallel(new DriveGearRoller(0.7),2);
    	addSequential(new DriveTimedTank(0.5,0.5,0.5));
    	addSequential(new DriveGearPivot(RobotMap.INTAKE_PIVOT_VERTICAL_POSITION, 6), 0.5);
    	if(turnAfter){
    		if(!leftTurnAfter){
    			addSequential(new RotateToGyroAngle(0,2), 1);
    		}
    		else{
    			
    			addSequential(new RotateToGyroAngle(0,2),1);
    		}
    		addSequential(new DriveStraightPosition(20,6));
    	}


    	
    }
}
