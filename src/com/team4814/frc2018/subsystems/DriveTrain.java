package com.team4814.frc2018.subsystems;

import com.team4814.frc2018.RobotMap;
import com.team4814.frc2018.commands.HaloDriveCommand;
import com.team4814.frc2018.utils.DashboardVariable;
import com.team4814.frc2018.utils.VariableSpeedControllerGroup;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class DriveTrain extends Subsystem
{
	public static final DashboardVariable<Double> driveP = new DashboardVariable<>("DriveP", 0.02);
	public static final DashboardVariable<Double> driveI = new DashboardVariable<>("DriveI", 0.0);
	public static final DashboardVariable<Double> driveD = new DashboardVariable<>("DriveD", 0.02);

	// why use a formula when you can use magic numbers?
	static final double DRIVE_DPP = 0.075;//calculateDistancePerPulse(20.0, 6.0, 10.71);

	public Encoder leftEncoder;
	public Encoder rightEncoder;

	public PIDController leftDrivePID;
	public PIDController rightDrivePID;

	public SpeedControllerGroup leftMotors;
	public SpeedControllerGroup rightMotors;

	static double calculateDistancePerPulse(double pulsesPerRevolution, double wheelSize, double gearRatio)
	{
		return (wheelSize * Math.PI) / (pulsesPerRevolution * gearRatio);
	}

	public DriveTrain()
	{
		super("DriveTrain");

		leftEncoder = new Encoder(RobotMap.LEFT_MOTOR_ENCODERS[0], RobotMap.LEFT_MOTOR_ENCODERS[1], true, EncodingType.k4X);
		leftEncoder.setName(this.getName(), "EncoderL");
		leftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		leftEncoder.setMaxPeriod(0.2);
		//leftEncoder.setMinRate(10);
		leftEncoder.setDistancePerPulse(DRIVE_DPP);
		leftEncoder.setSamplesToAverage(7);
		leftEncoder.reset();

		rightEncoder = new Encoder(RobotMap.RIGHT_MOTOR_ENCODERS[0], RobotMap.RIGHT_MOTOR_ENCODERS[1], false, EncodingType.k4X);
		rightEncoder.setName(this.getName(), "EncoderR");
		rightEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		rightEncoder.setMaxPeriod(0.2);
		//rightEncoder.setMinRate(10);
		rightEncoder.setDistancePerPulse(DRIVE_DPP);
		rightEncoder.setSamplesToAverage(7);
		rightEncoder.reset();

		Victor[] leftMotorInstances = new Victor[RobotMap.LEFT_MOTORS.length];
		for (int i = 0; i < leftMotorInstances.length; i++)
		{
			leftMotorInstances[i] = new Victor(RobotMap.LEFT_MOTORS[i]);
			leftMotorInstances[i].setName(this.getName(), "MotorL" + i);
		}

		leftMotors = new VariableSpeedControllerGroup(leftMotorInstances);

		Victor[] rightMotorInstances = new Victor[RobotMap.RIGHT_MOTORS.length];
		for (int i = 0; i < rightMotorInstances.length; i++)
		{
			rightMotorInstances[i] = new Victor(RobotMap.RIGHT_MOTORS[i]);
			rightMotorInstances[i].setName(this.getName(), "MotorR" + i);
			rightMotorInstances[i].setInverted(true);
		}

		rightMotors = new VariableSpeedControllerGroup(rightMotorInstances);

		double speedCap = 1.0;

		leftDrivePID = new PIDController(0.02, 0.0, 0.02, leftEncoder, leftMotors);
		leftDrivePID.setName(this.getName() + "PID", "LeftDrivePID");
		//leftDrivePID.setInputRange(-1, 1);
		//leftDrivePID.setContinuous(true);
		leftDrivePID.setOutputRange(-speedCap, speedCap);
		leftDrivePID.setAbsoluteTolerance(0.15);

		LiveWindow.add(leftDrivePID);

		rightDrivePID = new PIDController(0.02, 0.0, 0.02, rightEncoder, rightMotors);
		rightDrivePID.setName(this.getName() + "PID", "RightDrivePID");
		//rightDrivePID.setInputRange(-1, 1);
		//rightDrivePID.setContinuous(true);
		rightDrivePID.setOutputRange(-speedCap, speedCap);
		rightDrivePID.setAbsoluteTolerance(0.15);

		LiveWindow.add(rightDrivePID);
	}

	public void resetSpeedLimit()
	{
		leftDrivePID.setOutputRange(-1.0, 1.0);
		rightDrivePID.setOutputRange(-1.0, 1.0);
	}

	public void setSpeedLimit(double leftSpeed, double rightSpeed)
	{
		if (leftSpeed > 0.0)
			leftDrivePID.setOutputRange(leftSpeed, 1.0);
		else
			leftDrivePID.setOutputRange(-1.0, leftSpeed);

		if (rightSpeed > 0.0)
			rightDrivePID.setOutputRange(rightSpeed, 1.0);
		else
			rightDrivePID.setOutputRange(-1.0, rightSpeed);
	}

	public void resetEncoders()
	{
		this.disablePID();

		leftDrivePID.setSetpoint(0.0);
		rightDrivePID.setSetpoint(0.0);
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public void setSetpoint(double leftSetpoint, double rightSetpoint)
	{
		leftDrivePID.setSetpoint(leftSetpoint);
		rightDrivePID.setSetpoint(rightSetpoint);
	}

	public void updatePID()
	{
		leftDrivePID.setP(driveP.get());
		leftDrivePID.setI(driveI.get());
		leftDrivePID.setD(driveD.get());

		rightDrivePID.setP(driveP.get());
		rightDrivePID.setI(driveI.get());
		rightDrivePID.setD(driveD.get());
	}

	public void enablePID()
	{
		leftDrivePID.enable();
		rightDrivePID.enable();
	}

	public void disablePID()
	{
		leftDrivePID.disable();
		rightDrivePID.disable();
	}

	public void setSpeed(double speedLeft, double speedRight)
	{
		leftMotors.set(speedLeft);
		rightMotors.set(speedRight);
	}

	public void initDefaultCommand()
	{
		setDefaultCommand(new HaloDriveCommand());
	}
}
