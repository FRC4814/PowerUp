package com.team4814.frc2018;

public class RobotConstants
{
	// Arm
	public static final double ARM_DEFAULT_HEIGHT = -46.02; // constant
	public static final double ARM_HOLD_HEIGHT = -37.5825; // 15
	public static final double ARM_SWITCH_HEIGHT = -13.8325; // 45
	public static final double ARM_SCALE_HEIGHT = 14.1675; // 93
	public static final double ARM_DUMP_HEIGHT = 4.9175; // 83
	//	public static final double ARM_DEFAULT_HEIGHT = -300.0;
	//	public static final double ARM_HOLD_HEIGHT = -200.5825; // 15
	//	public static final double ARM_SWITCH_HEIGHT = -100.8325; // 45
	// new encoder values are 100 clicks per rotation instead of previous 160. Below are all previous values:
	/*
	 * Arm default: 0
	 * Arm hold: 13.5
	 * Arm switch: 43.5
	 * Arm scale: 91.5
	 * Arm dump: 81.5
	 * Arm lift: 80.0
	 */
	// using bottom as zero instead of auto contraption:
	/*
	 * 0
	 * 8.4375
	 * 27.1875
	 * 57.1875
	 * 50.9375
	 * 50
	 */

	// Auto constant
	public static final double ARM_LIFT_HEIGHT = 7.98;

	// Intake
	public static final double[] INTAKE_OUTAKE_DEFAULT_SPEED = { 0.0, 0.0 };
	public static final double[] INTAKE_OUTAKE_SWITCH_SPEED = { 0.0, 0.0 };
	public static final double[] INTAKE_OUTAKE_SCALE_SPEED = { 0.0, 0.0 };
}