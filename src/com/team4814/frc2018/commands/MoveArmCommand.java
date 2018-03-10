package com.team4814.frc2018.commands;

import com.team4814.frc2018.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveArmCommand extends Command
{
	private boolean isUp;

	public MoveArmCommand(boolean isUp)
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.pidArm);
		this.isUp = isUp;

	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		Robot.pidArm.disable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		double speed = isUp ? 0.25 : -0.25;
		Robot.pidArm.setSpeed(speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Robot.pidArm.setSpeed(0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}
