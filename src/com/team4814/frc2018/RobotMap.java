/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team4814.frc2018;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
	// PWM
	public static final int[] LEFT_MOTORS = { 5, 6, 7 };
	public static final int[] RIGHT_MOTORS = { 2, 3, 4 };
	public static final int ARM_MOTOR = 8;
	public static final int CLIMB_MOTOR = 9;
	public static final int[] INTAKE_MOTORS = { 0, 1 };

	// PCM

	// USB
	public static final int DRIVER_CONTROLLER = 0;

	// DIO
	public static final int[] LEFT_MOTOR_ENCODERS = { 0, 1 };
	public static final int[] RIGHT_MOTOR_ENCODERS = { 2, 3 };
	public static final int[] ARM_ENCODERS = { 4, 5 };
	//	public static final int CLIMB_SWITCH = 6;
	public static final int INTAKE_SWITCH = 9;

}
