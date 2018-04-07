package com.team4814.frc2018.autocommands;

import com.team4814.frc2018.RobotConstants;
import com.team4814.frc2018.commands.DriveBothPIDCommand;
import com.team4814.frc2018.commands.DrivePIDCommand;
import com.team4814.frc2018.commands.MoveArmPIDCommand;
import com.team4814.frc2018.commands.ToggleIntakeSolenoidCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSlot2ToRightSwitch extends CommandGroup
{

	public AutoSlot2ToRightSwitch()
	{
		addParallel(new ToggleIntakeSolenoidCommand(true));

		addParallel(new MoveArmPIDCommand(RobotConstants.ARM_LIFT_HEIGHT));

		addSequential(new DriveBothPIDCommand(65.0, 37.0, 0.9));

		addParallel(new MoveArmPIDCommand(RobotConstants.ARM_SWITCH_HEIGHT));

		addSequential(new DriveBothPIDCommand(37.0, 65.0, 0.9));

		addSequential(new DrivePIDCommand(25.0, 0.7), 0.7);

		addSequential(new AutoIntakeCommand(-0.6, -0.6));

		doWait();

		addSequential(new AutoIntakeCommand(0.0, 0.0));

	}

	private void doWait()
	{
		addSequential(new WaitCommand(1.0));
	}
}