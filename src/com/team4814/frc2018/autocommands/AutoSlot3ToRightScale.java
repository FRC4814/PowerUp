package com.team4814.frc2018.autocommands;

import com.team4814.frc2018.RobotConstants;
import com.team4814.frc2018.commands.DriveBothPIDCommand;
import com.team4814.frc2018.commands.MoveArmPIDCommand;
import com.team4814.frc2018.commands.ToggleIntakeSolenoidCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSlot3ToRightScale extends CommandGroup
{

	public AutoSlot3ToRightScale()
	{
		addParallel(new ToggleIntakeSolenoidCommand(true));

		addSequential(new DriveBothPIDCommand(304.0, 315.0, 1.0));

		addSequential(new DriveBothPIDCommand(-25.0, 25.0, 0.8), 2.0); // rotate

		addParallel(new MoveArmPIDCommand(RobotConstants.ARM_SCALE_HEIGHT));

		addSequential(new DriveBothPIDCommand(29.0, 29.0, 0.8), 1.0);

		addSequential(new AutoIntakeCommand(-1.0, -1.0));

		doWait();

		addSequential(new AutoIntakeCommand(0.0, 0.0));

	}

	private void doWait()
	{
		addSequential(new WaitCommand(1.0));
	}
}
