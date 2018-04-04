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

		//		System.out.println("Automatic Auto:\nGame Message: " + gameMessage + "\nStart Position: " + startPosition.name() + "\nDestination: " + destination.name() + "\nSwitch State" + switchState.name());

		// Drive to switch instead
		// AutoSlot1ToLeftSwitch
		// AutoSlot1ToLeftSwitchScore
		// AutoSlot2ToLeftSwitchScore
		// AutoSlot2ToRightSwitchScore
		// AutoSlot3ToRightSwitch
		// AutoSlot3ToRightSwitchScore
		//		if (destination == AutoGoal.kSwitch)
		//		{
		//		if (startPosition == AutoPosition.kLeft)
		//		{
		//			addSequential(new AutoSlot1ToLeftSwitch(switchState));
		//		}
		//		else if (startPosition == AutoPosition.kCenter)
		//		{
		if (switchState == AutoGoalState.kLeft)
			addSequential(new AutoSlot2ToLeftSwitch());
		else
			addSequential(new AutoSlot2ToRightSwitch());
		//		}
		//		else if (startPosition == AutoPosition.kRight)
		//		{
		//			addSequential(new AutoSlot3ToRightSwitch(switchState));
		//		}
		//		}
		//		else if (destination == AutoGoal.kScale)
		//		{
		//		}
	}
}
