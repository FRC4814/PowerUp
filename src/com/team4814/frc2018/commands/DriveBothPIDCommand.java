package com.team4814.frc2018.commands;

import com.team4814.frc2018.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveBothPIDCommand extends Command
{
	double targetSetpointL, targetSetpointR;
	double currentSetpointL, currentSetpointR;
	double speedL, speedR;
	boolean onTarget;

	public DriveBothPIDCommand(double targetSetpointL, double targetSetpointR)
	{
		this(targetSetpointL, targetSetpointR, 0.4);
	}

	public DriveBothPIDCommand(double targetSetpointL, double targetSetpointR, double speed)
	{
		requires(Robot.driveTrain);

		this.targetSetpointL = targetSetpointL;
		this.targetSetpointR = targetSetpointR;

		this.speedL = (targetSetpointL > 0) ? speed : -speed;
		this.speedR = this.speedL;

		if (targetSetpointL > targetSetpointR)
			this.speedR *= targetSetpointR / targetSetpointL;
		else
			this.speedL *= targetSetpointL / targetSetpointR;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		currentSetpointL = 0.0;
		currentSetpointR = 0.0;

		onTarget = false;

		Robot.driveTrain.resetEncoders();

		Robot.driveTrain.enablePID();

		System.out.println("Drive Init");
	}

	double speedMod = 1.0;

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		if (currentSetpointL > targetSetpointL)
			speedMod *= 0.7;
		speedMod = Math.max(speedMod, 0.1);

		currentSetpointL = currentSetpointL + speedL * speedMod;
		currentSetpointR = currentSetpointR + speedR * speedMod;

		Robot.driveTrain.setSetpoint(currentSetpointL, currentSetpointR);

		System.out.println(currentSetpointL + currentSetpointR);

		//System.out.println("Drive Execute " + Robot.driveTrain.leftEncoder.getDistance() + " " + Robot.driveTrain.rightEncoder.getDistance());
		//System.out.println(Robot.driveTrain.leftDrivePID.onTarget() + " " + Robot.driveTrain.rightDrivePID.onTarget() + " " + currentSetpoint);
		//System.out.println(Robot.driveTrain.leftDrivePID.isEnabled() + " " + Robot.driveTrain.rightDrivePID.isEnabled());
		//System.out.println(Robot.pidArm.armEncoder.getDistance() + " " + startSetpoint + " " + currentSetpoint + " " + targetSetpoint + " " + delta + " " + onTarget);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		if (targetSetpointL > 0)
			return Robot.driveTrain.leftEncoder.getDistance() >= targetSetpointL && Robot.driveTrain.rightEncoder.getDistance() >= targetSetpointR;
		else
			return Robot.driveTrain.leftEncoder.getDistance() <= targetSetpointL && Robot.driveTrain.rightEncoder.getDistance() <= targetSetpointR;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		//if (isFinished())
		//	Robot.pidArm.disable();

		Robot.driveTrain.resetSpeedLimit();
		Robot.driveTrain.setSpeed(0.0, 0.0);

		Robot.driveTrain.setSetpoint(targetSetpointL, targetSetpointR);

		//Robot.driveTrain.disablePID();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}
