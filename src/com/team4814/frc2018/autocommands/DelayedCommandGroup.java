package com.team4814.frc2018.autocommands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public abstract class DelayedCommandGroup extends CommandGroup
{
	protected DelayedCommandGroup()
	{
	}

	public abstract void buildCommands();

	@Override
	public synchronized void start()
	{
		buildCommands();

		super.start();
	}
}
