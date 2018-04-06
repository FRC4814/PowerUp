package com.team4814.frc2018;

public class RobotConstants
{
	// Arm
	public static final double ARM_DEFAULT_HEIGHT = 0; // constant
	public static final double ARM_HOLD_HEIGHT = 8.4375; // 15
	public static final double ARM_SWITCH_HEIGHT = 27.1875; // 45
	public static final double ARM_SCALE_HEIGHT = 57.1875; // 93
	public static final double ARM_DUMP_HEIGHT = 50.9375; // 83
	// new encoder values are 100 clicks per rotation instead of previous 160. Below are all previous values:
	/*
	 * Arm default: 0
	 * Arm hold: 13.5
	 * Arm switch: 43.5
	 * Arm scale: 91.5
	 * Arm dump: 81.5
	 * Arm lift: 80.0
	 */

	// Auto constant
	public static final double ARM_LIFT_HEIGHT = 50.0;

	// Intake
	public static final double[] INTAKE_OUTAKE_DEFAULT_SPEED = { 0.0, 0.0 };
	public static final double[] INTAKE_OUTAKE_SWITCH_SPEED = { 0.0, 0.0 };
	public static final double[] INTAKE_OUTAKE_SCALE_SPEED = { 0.0, 0.0 };
}