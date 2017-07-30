package org.usfirst.frc.team1058.robot.commands;

import org.usfirst.frc.team1058.robot.Robot;

/**
 * Credit to the Cheesy Poofs for the code. Here is their explanation for what the code does:
 * 
 * Helper class to implement "Cheesy Drive". "Cheesy Drive" simply means that
 * the "turning" stick controls the curvature of the robot's path rather than
 * its rate of heading change. This helps make the robot more controllable at
 * high speeds. Also handles the robot's quick turn functionality - "quick turn"
 * overrides constant-curvature turning for turn-in-place maneuvers.
 * 
 * Obviously there have been some slight modifications to make their code usable on our robot.
 */
class CheesyDrive extends DriveTankDefault{
	private double mQuickStopAccumulator;
    private static final double kThrottleDeadband = 0.02;
    private static final double kWheelDeadband = 0.02;
    private static final double kTurnSensitivity = 1.0;
    
    public CheesyDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivebase);
    }

    @Override
    protected void execute() {
    	double wheel = handleDeadband(Robot.oi.driverGamepad.getRightStickX(), kWheelDeadband);
        double throttle = handleDeadband(Robot.oi.driverGamepad.getLeftStickY(), kThrottleDeadband);
        boolean isQuickTurn = false; //TODO: BIND TO CONTROLLER
        
        double overPower;

        double angularPower;

        if (isQuickTurn) {
            if (Math.abs(throttle) < 0.2) {
                double alpha = 0.1;
                mQuickStopAccumulator = (1 - alpha) * mQuickStopAccumulator + alpha * limit(wheel) * 2;
            }
            overPower = 1.0;
            angularPower = wheel;
        } else {
            overPower = 0.0;
            angularPower = Math.abs(throttle) * wheel * kTurnSensitivity - mQuickStopAccumulator;
            if (mQuickStopAccumulator > 1) {
                mQuickStopAccumulator -= 1;
            } else if (mQuickStopAccumulator < -1) {
                mQuickStopAccumulator += 1;
            } else {
                mQuickStopAccumulator = 0.0;
            }
        }

        double rightPwm = throttle - angularPower;
        double leftPwm = throttle + angularPower;
        
        if (leftPwm > 1.0) {
            rightPwm -= overPower * (leftPwm - 1.0);
            leftPwm = 1.0;
        } else if (rightPwm > 1.0) {
            leftPwm -= overPower * (rightPwm - 1.0);
            rightPwm = 1.0;
        } else if (leftPwm < -1.0) {
            rightPwm += overPower * (-1.0 - leftPwm);
            leftPwm = -1.0;
        } else if (rightPwm < -1.0) {
            leftPwm += overPower * (-1.0 - rightPwm);
            rightPwm = -1.0;
        }
        
        if(Robot.oi.driverGamepad.getLeftBumper()){
	    	rightPwm *= .5;
	    	leftPwm *= .5;
		}
        
        Robot.drivebase.driveTank(rightPwm, leftPwm);
    }
    private double handleDeadband(double val, double deadband) {
        return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
    }
    private static double limit(double v) {
        return (Math.abs(v) < 1.0) ? v : 1.0 * (v < 0 ? -1 : 1);
    }

 
}
