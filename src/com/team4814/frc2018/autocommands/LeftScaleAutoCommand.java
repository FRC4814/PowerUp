package com.team4814.frc2018.autocommands;

import com.team4814.frc2018.RobotConstants;
import com.team4814.frc2018.commands.DrivePIDCommand;
import com.team4814.frc2018.commands.MoveArmPIDCommand;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleAutoCommand extends CommandGroup
{
	@Override
	public void start()
	{
	}

	public LeftScaleAutoCommand()
	{
		addSequential(new WaitForGameDataCommand());

		addParallel(new MoveArmPIDCommand(RobotConstants.ARM_SCALE_HEIGHT));
		addSequential(new DrivePIDCommand(80.0, 0.5)); // drive forward

		while (true)
		{
			String message = DriverStation.getInstance().getGameSpecificMessage();
			if (message != null && message.length() > 0)
				break;

			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		boolean goLeft = DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L';
		if (goLeft)
			addSequential(new LeftSwitchLAutoCommand());
		else
			addSequential(new LeftSwitchRAutoCommand());
	}
}
