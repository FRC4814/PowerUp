package com.team4814.frc2018.autocommands;

import com.team4814.frc2018.RobotConstants;
import com.team4814.frc2018.commands.DriveBothPIDCommand;
import com.team4814.frc2018.commands.MoveArmPIDCommand;
import com.team4814.frc2018.commands.ToggleIntakeSolenoidCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestAutoLeftCube2 extends CommandGroup
{

	public TestAutoLeftCube2()
	{
		addParallel(new ToggleIntakeSolenoidCommand(true));

		addParallel(new MoveArmPIDCommand(RobotConstants.ARM_LIFT_HEIGHT));

		addSequential(new DriveBothPIDCommand(45.0, 80.0, 0.9), 3.5); // 3.75

		addParallel(new MoveArmPIDCommand(RobotConstants.ARM_SWITCH_HEIGHT));

		addSequential(new DriveBothPIDCommand(80.0, 50.0, 0.9), 3.5); // 2.5

		// addParallel(new ToggleIntakeSolenoidCommand(false));

		addSequential(new AutoIntakeCommand(-0.7, -0.7));

		doWait();

		addSequential(new AutoIntakeCommand(0.0, 0.0));

		// back up and lower arm after delivering cube
		addParallel(new ToggleIntakeSolenoidCommand(false));

		addSequential(new DriveBothPIDCommand(-50.0, -50.0), 1.5);

		doWait();

		addSequential(new MoveArmPIDCommand(RobotConstants.ARM_DEFAULT_HEIGHT), 1.0);

		//		if (isLeft)
		//		{
		addSequential(new DriveBothPIDCommand(10, -10), 1.0); // rotate
		//		}
		//		else
		//		{
		//			addSequential(new DriveBothPIDCommand(-10, 10), 1.0); // rotate
		//		}
		addSequential(new DriveBothPIDCommand(29.0, 29.0, 0.8), 1.0);

		addSequential(new AutoIntakeCommand(1.0, 1.0));

		doWait();

		addSequential(new AutoIntakeCommand(0.0, 0.0));

		addParallel(new ToggleIntakeSolenoidCommand(true));

		addParallel(new MoveArmPIDCommand(RobotConstants.ARM_SWITCH_HEIGHT));

		addSequential(new DriveBothPIDCommand(29.0, 29.0, 0.8), 1.0);

		addSequential(new DriveBothPIDCommand(-10, 10), 1.0);

		addSequential(new DriveBothPIDCommand(50.0, 50.0), 2.0);

		addSequential(new AutoIntakeCommand(-0.7, -0.7));

		doWait();

		addSequential(new AutoIntakeCommand(0.0, 0.0));

	}

	private void doWait()
	{
		addSequential(new WaitCommand(1.0));
	}
}
