package com.team4814.frc2018.commands;

import com.team4814.frc2018.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleIntakeSolenoidCommand extends Command
{
	private boolean isClamp;

	public ToggleIntakeSolenoidCommand(boolean isClamp)
	{
		this.isClamp = isClamp;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		if (isClamp) // secure cube
			Robot.intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
		else // release cube
			Robot.intakeSolenoid.set(DoubleSolenoid.Value.kForward);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Robot.intakeSolenoid.set(DoubleSolenoid.Value.kOff);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
	}
}
