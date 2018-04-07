package com.team4814.frc2018.commands;

import com.team4814.frc2018.InputManager;
import com.team4814.frc2018.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCommand extends Command
{
	public IntakeCommand()
	{
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		double intakeSpeed[] = { 0.0, 0.0 };

		if (InputManager.driverController.getTriggerAxis(Hand.kRight) >= 0.1) // Intake
		{
			intakeSpeed[0] = 0.7;
			intakeSpeed[1] = 1.0;
		}
		else if (InputManager.driverController.getTriggerAxis(Hand.kLeft) >= 0.1) // Outake
		{
			if (Robot.pidArm.armEncoder.getDistance() <= -35) // Ground outake
			{
				intakeSpeed[0] = -1.0;
				intakeSpeed[1] = -1.0;
			}
			else if (Robot.pidArm.armEncoder.getDistance() >= 0) // Scale outtake
			{
				intakeSpeed[0] = -1.0;
				intakeSpeed[1] = -1.0;
			}
			else // Switch outtake
			{
				intakeSpeed[0] = -0.65;
				intakeSpeed[1] = -0.65;
			}
		}

		Robot.intake.setSpeed(intakeSpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		double intakeSpeed[] = { 0.0, 0.0 };
		Robot.intake.setSpeed(intakeSpeed);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}
