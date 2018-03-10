package com.team4814.frc2018.commands;

import com.team4814.frc2018.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveArmPIDCommand extends Command
{
	double startSetpoint;
	double targetSetpoint;
	double currentSetpoint;
	double speed;
	boolean onTarget;
	boolean overrideSpeedLimit;

	public MoveArmPIDCommand(double targetSetpoint)
	{
		this(targetSetpoint, 1.25);
	}

	public MoveArmPIDCommand(double targetSetpoint, double speed)
	{
		this(targetSetpoint, speed, false);
	}

	public MoveArmPIDCommand(double targetSetpoint, double speed, boolean overrideSpeedLimit)
	{
		requires(Robot.pidArm);

		this.targetSetpoint = targetSetpoint;
		this.speed = speed;
		this.overrideSpeedLimit = overrideSpeedLimit;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		startSetpoint = Robot.pidArm.armEncoder.getDistance();
		currentSetpoint = startSetpoint;
		onTarget = false;
		Robot.pidArm.enable();
		if (overrideSpeedLimit)
			Robot.pidArm.enableSpeedLimit(false);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		double delta = (startSetpoint < targetSetpoint) ? speed : -speed;

		onTarget = (startSetpoint < targetSetpoint) ? currentSetpoint >= targetSetpoint : currentSetpoint <= targetSetpoint;

		if (!onTarget)
		{
			currentSetpoint = currentSetpoint + delta;
		}
		else
		{
			currentSetpoint = targetSetpoint;
		}

		Robot.pidArm.setSetpoint(currentSetpoint);

		System.out.println(Robot.pidArm.armEncoder.getDistance() + " " + startSetpoint + " " + currentSetpoint + " " + targetSetpoint + " " + delta + " " + onTarget);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return Robot.pidArm.onTarget() && onTarget;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		//if (isFinished())
		//	Robot.pidArm.disable();
		if (overrideSpeedLimit)
			Robot.pidArm.enableSpeedLimit(true);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}
