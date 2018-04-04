package com.team4814.frc2018.autocommands;

import com.team4814.frc2018.RobotConstants;
import com.team4814.frc2018.commands.DriveBothPIDCommand;
import com.team4814.frc2018.commands.MoveArmPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSlot1CrossStartLine extends CommandGroup
{

	public AutoSlot1CrossStartLine()
	{
		doWait();

		addParallel(new MoveArmPIDCommand(RobotConstants.ARM_SWITCH_HEIGHT));

		addSequential(new DriveBothPIDCommand(150.0, 150.0, 0.9));

	}

	private void doWait()
	{
		addSequential(new WaitCommand(0.0));
	}
}
