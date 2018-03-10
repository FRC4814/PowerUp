package com.team4814.frc2018.commands;

import com.team4814.frc2018.InputManager;
import com.team4814.frc2018.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HaloDriveCommand extends Command
{
	public HaloDriveCommand()
	{
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		double forwardPower = Math.pow(-InputManager.driverController.getY(Hand.kLeft), 3);
		double turnPower = Math.pow(InputManager.driverController.getX(Hand.kRight), 5);
		double leftPower = forwardPower + turnPower;
		double rightPower = forwardPower - turnPower;

		Robot.driveTrain.setSpeed(leftPower, rightPower);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Robot.driveTrain.setSpeed(0.0, 0.0); // stop motors when not using joystick
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}
