package com.team4814.frc2018.autocommands;

import com.team4814.frc2018.RobotConstants;
import com.team4814.frc2018.auto.AutoGoalState;
import com.team4814.frc2018.commands.DriveBothPIDCommand;
import com.team4814.frc2018.commands.MoveArmPIDCommand;
import com.team4814.frc2018.commands.ToggleIntakeSolenoidCommand;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestAutoLeftSideSwitchCommand extends CommandGroup
{

	public TestAutoLeftSideSwitchCommand()
	{
		String gameMessage = DriverStation.getInstance().getGameSpecificMessage();
		AutoGoalState switchState = AutoGoalState.fromChar(gameMessage.charAt(0));

		addParallel(new ToggleIntakeSolenoidCommand(true));

		addParallel(new MoveArmPIDCommand(RobotConstants.ARM_LIFT_HEIGHT));

		addSequential(new DriveBothPIDCommand(150.0, 150.0, 0.9));

		if (switchState == AutoGoalState.kLeft)
		{
			addSequential(new DriveBothPIDCommand(25.0, -25.0, 0.7), 2.0);

			addParallel(new MoveArmPIDCommand(RobotConstants.ARM_SWITCH_HEIGHT));

			addSequential(new DriveBothPIDCommand(25.0, 25.0, 0.9), 1.0);

			addSequential(new AutoIntakeCommand(-0.7, -0.7));

			doWait();

			addSequential(new AutoIntakeCommand(0.0, 0.0));
		}

	}

	private void doWait()
	{
		addSequential(new WaitCommand(1.0));
	}
}
