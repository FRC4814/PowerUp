package com.team4814.frc2018.autocommands;

import com.team4814.frc2018.commands.DriveBothPIDCommand;
import com.team4814.frc2018.commands.ToggleIntakeSolenoidCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCrossStartLineCommand extends CommandGroup
{

	public AutoCrossStartLineCommand()
	{
		doWait();

		//		addParallel(new MoveArmPIDCommand(RobotConstants.ARM_SWITCH_HEIGHT));
		addParallel(new ToggleIntakeSolenoidCommand(true));

		addSequential(new DriveBothPIDCommand(150.0, 150.0, 0.9));

	}

	private void doWait()
	{
		addSequential(new WaitCommand(0.0));
	}
}
