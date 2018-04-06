package com.team4814.frc2018.autocommands;

import com.team4814.frc2018.commands.DriveBothPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSlot2ToLeftSwitch extends CommandGroup
{

	public AutoSlot2ToLeftSwitch()
	{
		//		addParallel(new MoveArmPIDCommand(RobotConstants.ARM_LIFT_HEIGHT));

		addSequential(new DriveBothPIDCommand(45.0, 80.0, 0.9), 3.75);

		//		addParallel(new MoveArmPIDCommand(RobotConstants.ARM_SWITCH_HEIGHT));

		addSequential(new DriveBothPIDCommand(80.0, 50.0, 0.9), 2.5);

		addSequential(new AutoIntakeCommand(-0.4, -0.4));

		doWait();

		addSequential(new AutoIntakeCommand(0.0, 0.0));

	}

	private void doWait()
	{
		addSequential(new WaitCommand(1.0));
	}
}
