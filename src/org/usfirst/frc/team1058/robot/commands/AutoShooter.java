package org.usfirst.frc.team1058.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoShooter extends CommandGroup {

    public AutoShooter() {
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
    	//voltage should be 6
    	addSequential(new DriveToPosition(1,1,6));
    	addSequential(new EncoderTurnToAngle(90));
    	addSequential(new DriveToPosition(1,1,6));
    	addSequential(new DriveToPosition(-1,-1,6));
    	addSequential(new EncoderTurnToAngle(90));
    }
}
