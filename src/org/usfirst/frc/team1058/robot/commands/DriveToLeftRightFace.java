package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DriveToLeftRightFace extends CommandGroup {

    public DriveToLeftRightFace(boolean placeGear, boolean placeright, boolean driveOut, boolean right) {
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
    	addSequential(new WaitCommand(2.5));
    	addParallel(new DriveGearPivot(RobotMap.INTAKE_PIVOT_VERTICAL_POSITION, 6),1);
    	addSequential(new DriveToPosition(-8.5, -8.5, 4),3);
    	if (placeGear){
    		addSequential(new WaitCommand(0.5));
    		addSequential(new PlaceGear(false, false),2);
    		addParallel(new DriveGearPivot(RobotMap.INTAKE_PIVOT_GEARINTAKE_POSITION-400, 6));
    		addParallel(new DriveGearRoller(-0.3),2);

    		addSequential(new DriveToPosition(-3.84, -3.84, 5));
    	}
    }
    	
}

