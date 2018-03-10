package com.team4814.frc2018.commands;

import com.team4814.frc2018.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivePIDCommand extends Command
{
	double targetSetpoint;
	double currentSetpoint;
	double speed;
	boolean onTarget;

	public DrivePIDCommand(double targetSetpoint)
	{
		this(targetSetpoint, 0.4);
	}

	public DrivePIDCommand(double targetSetpoint, double speed)
	{
		requires(Robot.driveTrain);

		this.targetSetpoint = targetSetpoint;
		this.speed = (targetSetpoint > 0) ? speed : -speed;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		currentSetpoint = 0.0;
		onTarget = false;

		Robot.driveTrain.resetEncoders();

		//Robot.driveTrain.setSetpoint(currentSetpoint, currentSetpoint);
		//
		//		if (targetSetpoint > 0)
		//			Robot.driveTrain.setSpeedLimit(0.3, 0.3);
		//		else
		//			Robot.driveTrain.setSpeedLimit(-0.3, -0.3);

		Robot.driveTrain.enablePID();

		System.out.println("Drive Init");
	}

	double speedMod = 1.0;

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		if (currentSetpoint > targetSetpoint)
			speedMod *= 0.7;
		speedMod = Math.max(speedMod, 0.1);

		currentSetpoint = currentSetpoint + speed * speedMod;

		Robot.driveTrain.setSetpoint(currentSetpoint, currentSetpoint);

		System.out.println(currentSetpoint);

		//System.out.println("Drive Execute " + Robot.driveTrain.leftEncoder.getDistance() + " " + Robot.driveTrain.rightEncoder.getDistance());
		//System.out.println(Robot.driveTrain.leftDrivePID.onTarget() + " " + Robot.driveTrain.rightDrivePID.onTarget() + " " + currentSetpoint);
		//System.out.println(Robot.driveTrain.leftDrivePID.isEnabled() + " " + Robot.driveTrain.rightDrivePID.isEnabled());
		//System.out.println(Robot.pidArm.armEncoder.getDistance() + " " + startSetpoint + " " + currentSetpoint + " " + targetSetpoint + " " + delta + " " + onTarget);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		if (targetSetpoint > 0)
			return Robot.driveTrain.leftEncoder.getDistance() >= targetSetpoint && Robot.driveTrain.rightEncoder.getDistance() >= targetSetpoint;
		else
			return Robot.driveTrain.leftEncoder.getDistance() <= targetSetpoint && Robot.driveTrain.rightEncoder.getDistance() <= targetSetpoint;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		//if (isFinished())
		//	Robot.pidArm.disable();

		Robot.driveTrain.resetSpeedLimit();
		Robot.driveTrain.setSpeed(0.0, 0.0);

		Robot.driveTrain.setSetpoint(targetSetpoint, targetSetpoint);

		//Robot.driveTrain.disablePID();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}
