package com.team4814.frc2018.subsystems;

import com.team4814.frc2018.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem
{
	protected Talon climbMotor;
	//	protected DigitalInput climbSwitch;

	public Climber()
	{
		super("Climber");

		climbMotor = new Talon(RobotMap.CLIMB_MOTOR);
		climbMotor.setName("Motor" + 0);

		//		climbSwitch = new DigitalInput(RobotMap.CLIMB_SWITCH);
	}

	public void setSpeed(double speed)
	{
		climbMotor.set(speed);
	}

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		//		setDefaultCommand(new ClimbCommand());
	}
}
