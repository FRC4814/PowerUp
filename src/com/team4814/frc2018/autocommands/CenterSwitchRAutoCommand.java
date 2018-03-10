package com.team4814.frc2018.autocommands;

import com.team4814.frc2018.RobotConstants;
import com.team4814.frc2018.commands.DrivePIDCommand;
import com.team4814.frc2018.commands.DrivePIDRotateCommand;
import com.team4814.frc2018.commands.MoveArmPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchRAutoCommand extends CommandGroup
{

	public CenterSwitchRAutoCommand()
	{
		addSequential(new WaitForGameDataCommand());

		addParallel(new MoveArmPIDCommand(RobotConstants.ARM_SCALE_HEIGHT));
		addSequential(new DrivePIDCommand(20.0, 0.5));

		doWait();

		addSequential(new DrivePIDRotateCommand(9.0, 0.7));

		doWait();

		addParallel(new MoveArmPIDCommand(RobotConstants.ARM_SWITCH_HEIGHT));
		addSequential(new DrivePIDCommand(80.0, 0.5));

		doWait();

		addSequential(new DrivePIDRotateCommand(-9.0, 0.7));

		doWait();

		addSequential(new DrivePIDCommand(22.0, 0.5));

		doWait();

		addSequential(new AutoIntakeCommand(-0.5, -0.5));

		doWait();

		addSequential(new AutoIntakeCommand(0.0, 0.0));

	}

	private void doWait()
	{
		addSequential(new WaitCommand(0.0));
	}
}
