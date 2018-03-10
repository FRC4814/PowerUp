package com.team4814.frc2018.autocommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterSwitchAutoCommand extends CommandGroup
{

	public CenterSwitchAutoCommand()
	{
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
			addSequential(new CenterSwitchLAutoCommand());
		else
			addSequential(new CenterSwitchRAutoCommand());
	}
}
