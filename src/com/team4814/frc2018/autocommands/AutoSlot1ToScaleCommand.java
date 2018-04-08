package com.team4814.frc2018.autocommands;

import com.team4814.frc2018.auto.AutoGoalState;

import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 */
public class AutoSlot1ToScaleCommand extends DelayedCommandGroup
{
	@Override
	public void buildCommands()
	{
		String gameMessage = DriverStation.getInstance().getGameSpecificMessage();
		//		AutoPosition startPosition = Robot.getAutoPosition();
		//		AutoGoal destination = Robot.getAutoGoal();

		//		AutoGoalState switchState = AutoGoalState.fromChar(gameMessage.charAt(0));
		AutoGoalState scaleState = AutoGoalState.fromChar(gameMessage.charAt(1));

		if (scaleState == AutoGoalState.kLeft)
			addSequential(new AutoSlot1ToLeftScale());
		else
			addSequential(new AutoSlot1ToRightScale());
	}
}
