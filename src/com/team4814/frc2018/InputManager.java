/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team4814.frc2018;

import com.team4814.frc2018.commands.MoveArmCommand;
import com.team4814.frc2018.commands.MoveArmPIDCommand;
import com.team4814.frc2018.commands.ToggleIntakeSolenoidCommand;
import com.team4814.frc2018.utils.CustomXboxController;
import com.team4814.frc2018.utils.DPadButton;
import com.team4814.frc2018.utils.DPadButton.Direction;
import com.team4814.frc2018.utils.XboxButton;
import com.team4814.frc2018.utils.XboxControllerButton;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class InputManager
{
	public static CustomXboxController driverController = new CustomXboxController(RobotMap.DRIVER_CONTROLLER);

	public InputManager()
	{
		driverController.setDeadzone(0.03);

		Button presetA = new XboxControllerButton(driverController, XboxButton.kButtonA);
		Button presetB = new XboxControllerButton(driverController, XboxButton.kButtonB);
		Button presetX = new XboxControllerButton(driverController, XboxButton.kButtonX);
		Button presetY = new XboxControllerButton(driverController, XboxButton.kButtonY);
		Button presetStart = new XboxControllerButton(driverController, XboxButton.kButtonStart);
		presetA.whenPressed(new MoveArmPIDCommand(RobotConstants.ARM_DEFAULT_HEIGHT, 2.5, true)); // Idle
		presetB.whenPressed(new MoveArmPIDCommand(RobotConstants.ARM_DUMP_HEIGHT, 2.5, true)); // Launch to switch
		presetX.whenPressed(new MoveArmPIDCommand(RobotConstants.ARM_SWITCH_HEIGHT, 2.5, true)); // Switch
		presetY.whenPressed(new MoveArmPIDCommand(RobotConstants.ARM_SCALE_HEIGHT, 2.5, true)); // Scale
		presetStart.whenPressed(new MoveArmPIDCommand(RobotConstants.ARM_HOLD_HEIGHT, 2.5, true)); // Hold

		Button bumperL = new XboxControllerButton(driverController, XboxButton.kBumperLeft);
		Button bumperR = new XboxControllerButton(driverController, XboxButton.kBumperRight);
		bumperL.whileHeld(new ToggleIntakeSolenoidCommand(false));
		bumperR.whileHeld(new ToggleIntakeSolenoidCommand(true));

		Button DPadUp = new DPadButton(driverController, Direction.Up);
		Button DPadDown = new DPadButton(driverController, Direction.Down);
		DPadUp.whileHeld(new MoveArmCommand(true));
		DPadDown.whileHeld(new MoveArmCommand(false));

		//		Button startClimb = new XboxControllerButton(driverController, XboxButton.kButtonBack);
		//		startClimb.whenPressed(new ClimbCommand());
	}
}