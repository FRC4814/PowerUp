package com.team4814.frc2018.autocommands;

import com.team4814.frc2018.commands.DrivePIDCommand;
import com.team4814.frc2018.commands.DrivePIDRotateCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleRAutoCommand extends CommandGroup
{

	public RightScaleRAutoCommand()
	{
		addSequential(new DrivePIDRotateCommand(-9.0, 0.7)); // turn to left

		doWait();

		addSequential(new DrivePIDCommand(5.0, 0.5)); // drive forward

		doWait();

		addSequential(new AutoIntakeCommand(-0.5, -0.5)); // release cube

		doWait();

		addSequential(new AutoIntakeCommand(0.0, 0.0)); // set intake speed to 0
	}

	private void doWait()
	{
		addSequential(new WaitCommand(0.0));
	}
}
