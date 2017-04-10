package org.usfirst.frc.team1058.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class TestEncoderAutp extends CommandGroup {

    public TestEncoderAutp() {
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
    	addSequential(new DriveToPosition(20,20,12));
    	addSequential(new WaitCommand(1));

    	//OLDAUTO
    	/*addSequential(new ZeroEncoders());
    	addSequential(new DriveTimedTank(0.25, 0.265, 3));
    	addSequential(new DriveTimedTank(-0.25, 0.15, 0.5));
    	addSequential(new WaitCommand(1));
    	addSequential(new DriveTimedTank(0.25, 0.265, 2));
    	addSequential(new DriveTimedTank(0.1, 0.12, 2));
    	addSequential(new WaitCommand(1));

    	
    	addSequential(new WaitCommand(2));
    	addSequential(new DriveTimedTank(-0.4,-0.4,0.5));
    	addSequential(new GearManipulatorMoveIn());*/

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
