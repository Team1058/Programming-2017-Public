package org.usfirst.frc.team1058.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CenterGearGroup extends CommandGroup {

    public CenterGearGroup() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
    	addSequential(new ZeroEncoders());
    	addSequential(new DriveTimedTank(0.25, 0.25, 2.5));
    	addParallel(new DriveTimedTank(0.1, 0.1, 1.5));
    	addSequential(new WaitCommand(1));
    	
    	addParallel(new PlaceGear(false,true));
    

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
