package com.team4814.frc2018.autocommands;

import com.team4814.frc2018.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoIntakeCommand extends Command
{
	double leftSpeed, rightSpeed;

	public AutoIntakeCommand(double leftspeed, double rightspeed)
	{
		requires(Robot.intake);
		this.leftSpeed = leftspeed;
		this.rightSpeed = rightspeed;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		Robot.intake.setSpeed(new double[] { leftSpeed, rightSpeed });
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return true;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		//		double intakeSpeed[] = { 0.0, 0.0 };
		//		Robot.intake.setSpeed(intakeSpeed);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}
