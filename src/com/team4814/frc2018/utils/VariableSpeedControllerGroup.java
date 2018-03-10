package com.team4814.frc2018.utils;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class VariableSpeedControllerGroup extends SpeedControllerGroup
{
	public VariableSpeedControllerGroup(SpeedController... controllers)
	{
		super(controllers[0], getExtraControllers(controllers));
	}

	static SpeedController[] getExtraControllers(SpeedController[] controllers)
	{
		if (controllers.length == 1)
			return new SpeedController[0];

		SpeedController[] extras = new SpeedController[controllers.length - 1];

		for (int i = 0; i < extras.length; i++)
		{
			extras[i] = controllers[1 + i];
		}

		return extras;
	}
}
