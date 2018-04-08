package com.team4814.frc2018.autocommands;

import com.team4814.frc2018.commands.DriveBothPIDCommand;
import com.team4814.frc2018.commands.ToggleIntakeSolenoidCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSlot3ToLeftScale extends CommandGroup
{

	public AutoSlot3ToLeftScale()
	{
		addParallel(new ToggleIntakeSolenoidCommand(true));

		addSequential(new DriveBothPIDCommand(300.0, 300.0, 1.0));

		addSequential(new DriveBothPIDCommand(-10, 10)); // rotate

		addSequential(new AutoIntakeCommand(-1.0, -1.0), 1.0);

	}

	//	private void doWait()
	//	{
	//		addSequential(new WaitCommand(1.0));
	//	}
}
