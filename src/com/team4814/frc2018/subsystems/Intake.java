package com.team4814.frc2018.subsystems;

import com.team4814.frc2018.RobotMap;
import com.team4814.frc2018.commands.IntakeCommand;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem
{
	protected Victor[] intakeMotors;
	protected DigitalInput intakeSwitch;

	public Intake()
	{
		super("Intake");

		intakeMotors = new Victor[RobotMap.INTAKE_MOTORS.length];
		for (int i = 0; i < intakeMotors.length; i++)
		{
			intakeMotors[i] = new Victor(RobotMap.INTAKE_MOTORS[i]);
			intakeMotors[i].setName(this.getName(), "Motor" + i);
		}
		intakeSwitch = new DigitalInput(RobotMap.INTAKE_SWITCH);
	}

	public void setSpeed(double speed[])
	{
		for (int i = 0; i < intakeMotors.length; i++)
		{
			intakeMotors[i].set(speed[i]);
		}
	}

	public boolean getSwitch()
	{
		return intakeSwitch.get();
	}

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new IntakeCommand());
	}
}
