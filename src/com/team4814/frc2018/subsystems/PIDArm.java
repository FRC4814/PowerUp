package com.team4814.frc2018.subsystems;

import com.team4814.frc2018.RobotMap;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class PIDArm extends PIDSubsystem
{
	static final double kP = 0.05;
	static final double kI = 0.0;
	static final double kD = 0.03;

	public boolean pidEnabled = true;

	public Talon[] armMotors;
	public Encoder armEncoder;

	// Initialize your subsystem here
	public PIDArm()
	{
		super("PIDArm", kP, kI, kD, 0.0, 0.05);

		armMotors = new Talon[RobotMap.ARM_MOTORS.length];
		for (int i = 0; i < armMotors.length; i++)
		{
			armMotors[i] = new Talon(RobotMap.ARM_MOTORS[i]);
			armMotors[i].setName(this.getName(), "Motor" + i);
			armMotors[i].setInverted(true);
		}

		// https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599717-encoders-measuring-rotation-of-a-wheel-or-other-shaft
		// http://www.cui.com/product/resource/amt10-v.pdf
		armEncoder = new Encoder(RobotMap.ARM_ENCODERS[0], RobotMap.ARM_ENCODERS[1], false, EncodingType.k4X);
		armEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		armEncoder.setMaxPeriod(.1);
		armEncoder.setMinRate(10);
		armEncoder.setDistancePerPulse(0.054); // distance = degrees
		armEncoder.setSamplesToAverage(7);
		armEncoder.reset();

		setAbsoluteTolerance(0.1);
		getPIDController().setContinuous(false);
		getPIDController().setInputRange(0.0, 95.0);

		enableSpeedLimit(true);
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
	}

	public void enableSpeedLimit(boolean enable)
	{
		if (enable)
			setOutputRange(-0.3, 0.3);
		else
			setOutputRange(-1.0, 1.0);
	}

	public void togglePID(boolean toggle)
	{
		this.pidEnabled = toggle;
		if (!toggle)
		{
			this.disable();
		}
	}

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	protected double returnPIDInput()
	{
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		double rVal = armEncoder.pidGet();

		//System.out.println("PID INPUT: " + rVal);

		return rVal;
	}

	protected void usePIDOutput(double output)
	{
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);

		setSpeed(output);
	}

	public void setSpeed(double speed)
	{
		//System.out.println("PID OUTPUT: " + speed);

		for (int i = 0; i < armMotors.length; i++)
		{
			armMotors[i].set(speed);
		}
	}
}