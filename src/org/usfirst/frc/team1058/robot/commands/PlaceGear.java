package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;
import org.usfirst.frc.team1058.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class PlaceGear extends CommandGroup {
public static boolean placingGear;
    public PlaceGear(boolean useVision, boolean driveAwayAfter) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
    	
    	addSequential(new DriveGearPivot(-10581058, 10));
    	addSequential(new DriveAndPivotGearRoller(-0.4, RobotMap.INTAKE_PIVOT_GEARINTAKE_POSITION-400), 2);
    	addSequential(new DriveGearPivot(RobotMap.INTAKE_PIVOT_GEARINTAKE_POSITION-400,4));
    	
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
