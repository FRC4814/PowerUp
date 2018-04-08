package com.team4814.frc2018.autocommands;

import com.team4814.frc2018.auto.AutoGoalState;

import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 */
public class AutoSlot3ToScaleCommand extends DelayedCommandGroup
{
	@Override
	public void buildCommands()
	{
		String gameMessage = DriverStation.getInstance().getGameSpecificMessage();

		AutoGoalState switchState = AutoGoalState.fromChar(gameMessage.charAt(1));

		if (switchState == AutoGoalState.kLeft)
			addSequential(new AutoSlot3ToLeftScale());
		else
			addSequential(new AutoSlot3ToRightScale());
	}
}
