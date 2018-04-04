package com.team4814.frc2018.autocommands;

import com.team4814.frc2018.Robot;
import com.team4814.frc2018.auto.AutoPosition;

/**
 * Not used until left and right auto becomes different
 */
public class AutoCrossStartLineCommand extends DelayedCommandGroup
// put these in: (later)
// public class AutoCrossStartLineCommand extends CommandGroup
// public AutoCrossStartLineCommand() {
/**
 * doWait();
 * 
 * addParallel(new MoveArmPIDCommand(RobotConstants.ARM_SWITCH_HEIGHT));
 * 
 * addSequential(new DriveBothPIDCommand(200.0, 200.0, 0.9));
 * 
 */
// }
/**
 * private void doWait() { addSequential(new WaitCommand(10.0)); }
 */

{
	@Override
	public void buildCommands()
	{

		//		String gameMessage = DriverStation.getInstance().getGameSpecificMessage();
		AutoPosition startPosition = Robot.getAutoPosition();
		//		AutoGoal destination = Robot.getAutoGoal();

		//		AutoGoalState switchState = AutoGoalState.fromChar(gameMessage.charAt(0));
		//AutoGoalState scaleState = AutoGoalState.fromChar(gameMessage.charAt(1));

		//		System.out.println("Automatic Auto:\nGame Message: " + gameMessage + "\nStart Position: " + startPosition.name() + "\nDestination: " + destination.name() + "\nSwitch State" + switchState.name());

		if (startPosition == AutoPosition.kLeft)
		{
			addSequential(new AutoSlot1CrossStartLine());
		}
		else if (startPosition == AutoPosition.kCenter)
		{
			// addSequential(new AutoSlot2CrossStartLineCommand());
		}
		else if (startPosition == AutoPosition.kRight)
		{
			addSequential(new AutoSlot3CrossStartLine());
		}

	}
}
