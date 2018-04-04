package com.team4814.frc2018.autocommands;

import com.team4814.frc2018.RobotConstants;
import com.team4814.frc2018.auto.AutoGoalState;
import com.team4814.frc2018.commands.DrivePIDCommand;
import com.team4814.frc2018.commands.MoveArmPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSlot1ToLeftSwitch extends CommandGroup
{

	public AutoSlot1ToLeftSwitch(AutoGoalState switchState)
	{
		// Add Commands here:
		addParallel(new MoveArmPIDCommand(RobotConstants.ARM_SWITCH_HEIGHT));

		addSequential(new DrivePIDCommand(50.0, 0.8)); // drive forward

		if (switchState == AutoGoalState.kLeft)
		{
			addSequential(new AutoIntakeCommand(-0.3, -0.3)); // release cube

			doWait();

			addSequential(new AutoIntakeCommand(0.0, 0.0));
		}

	}

	private void doWait()
	{
		addSequential(new WaitCommand(0.3));
	}
}
