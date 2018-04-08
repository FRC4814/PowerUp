package com.team4814.frc2018.autocommands;

import com.team4814.frc2018.commands.DriveBothPIDCommand;
import com.team4814.frc2018.commands.ToggleIntakeSolenoidCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSlot1ToRightScale extends CommandGroup
{

	public AutoSlot1ToRightScale()
	{
		addParallel(new ToggleIntakeSolenoidCommand(true));

		addSequential(new DriveBothPIDCommand(150.0, 150.0, 0.9), 4.0);

		//		addSequential(new DriveBothPIDCommand(100.0, -10.0)); // rotate
		//
		//		addSequential(new AutoIntakeCommand(-1.0, -1.0), 1.0);
	}

	//	private void doWait()
	//	{
	//		addSequential(new WaitCommand(1.0));
	//	}
}
