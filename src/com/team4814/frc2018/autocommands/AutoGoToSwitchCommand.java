package com.team4814.frc2018.autocommands;

import com.team4814.frc2018.auto.AutoGoalState;

import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 */
public class AutoGoToSwitchCommand extends DelayedCommandGroup
{
	@Override
	public void buildCommands()
	{
		String gameMessage = DriverStation.getInstance().getGameSpecificMessage();
		//		AutoPosition startPosition = Robot.getAutoPosition();
		//		AutoGoal destination = Robot.getAutoGoal();

		AutoGoalState switchState = AutoGoalState.fromChar(gameMessage.charAt(0));
		//AutoGoalState scaleState = AutoGoalState.fromChar(gameMessage.charAt(1));

		if (switchState == AutoGoalState.kLeft)
			addSequential(new AutoSlot2ToLeftSwitch());
		else
			addSequential(new AutoSlot2ToRightSwitch());
	}
}
