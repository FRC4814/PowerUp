package com.team4814.frc2018.autocommands;

import com.team4814.frc2018.RobotConstants;
import com.team4814.frc2018.commands.DriveBothPIDCommand;
import com.team4814.frc2018.commands.MoveArmPIDCommand;
import com.team4814.frc2018.commands.ToggleIntakeSolenoidCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSlot2ToLeftSwitch extends CommandGroup
{

	public AutoSlot2ToLeftSwitch()
	{
		// deliver cube
		addParallel(new ToggleIntakeSolenoidCommand(true));

		addParallel(new MoveArmPIDCommand(RobotConstants.ARM_LIFT_HEIGHT));

		addSequential(new DriveBothPIDCommand(45.0, 80.0, 0.9), 3.5); // 3.5

		addParallel(new MoveArmPIDCommand(RobotConstants.ARM_SWITCH_HEIGHT));

		addSequential(new DriveBothPIDCommand(80.0, 50.0, 0.9), 3.5); // 3.5

		// addParallel(new ToggleIntakeSolenoidCommand(false));

		addSequential(new AutoIntakeCommand(-0.7, -0.7));

		doWait();

		addSequential(new AutoIntakeCommand(0.0, 0.0));

		// back up and lower arm after delivering cube
		addParallel(new ToggleIntakeSolenoidCommand(false));

		addSequential(new DriveBothPIDCommand(-50.0, -50.0), 2.0);

		doWait();

		addSequential(new MoveArmPIDCommand(RobotConstants.ARM_DEFAULT_HEIGHT));

		//		addSequential(new TestAutoLeftCube2(true));

	}

	private void doWait()
	{
		addSequential(new WaitCommand(1.0));
	}
}
