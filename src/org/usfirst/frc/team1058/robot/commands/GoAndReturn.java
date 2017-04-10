package org.usfirst.frc.team1058.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GoAndReturn extends CommandGroup {

    public GoAndReturn() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
    	addSequential(new DriveTimedTank(-0.3,-0.3, 3));
    	addSequential(new RotateToGyroAngle(40,2), 2);
    	addSequential(new TurnTillPerpVision());
    //	addSequential(new DriveTimedTank(-0.3,-0.3, 1.5));
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
