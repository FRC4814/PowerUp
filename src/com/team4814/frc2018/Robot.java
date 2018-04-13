/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team4814.frc2018;

import com.team4814.frc2018.auto.AutoGoal;
import com.team4814.frc2018.auto.AutoPosition;
import com.team4814.frc2018.autocommands.AutoCrossStartLineCommand;
import com.team4814.frc2018.autocommands.AutoGoToSwitchCommand;
import com.team4814.frc2018.autocommands.AutoSlot1ToScaleCommand;
import com.team4814.frc2018.autocommands.TestAutoLeftCube2;
import com.team4814.frc2018.autocommands.TestAutoLeftSideSwitchCommand;
import com.team4814.frc2018.autocommands.TestAutoRightSideSwitchCommand;
import com.team4814.frc2018.subsystems.Climber;
import com.team4814.frc2018.subsystems.DriveTrain;
import com.team4814.frc2018.subsystems.Intake;
import com.team4814.frc2018.subsystems.PIDArm;
import com.team4814.frc2018.utils.DashboardVariable;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot
{
	public static InputManager m_oi;
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Intake intake = new Intake();

	public static final PIDArm pidArm = new PIDArm();
	public static final Climber climber = new Climber();
	public static final Compressor intakeCompressor = new Compressor(0);

	public static final DoubleSolenoid intakeSolenoid = new DoubleSolenoid(0, 1);
	public static final DoubleSolenoid climberSolenoid = new DoubleSolenoid(2, 3);

	Command m_autonomousCommand;
	SendableChooser<String> m_autoModeChooser = new SendableChooser<>();
	static SendableChooser<AutoPosition> m_autoPositionChooser = new SendableChooser<>();
	static SendableChooser<AutoGoal> m_autoGoalChooser = new SendableChooser<>();

	public static AutoPosition getAutoPosition()
	{
		return m_autoPositionChooser.getSelected();
	}

	public static AutoGoal getAutoGoal()
	{
		return m_autoGoalChooser.getSelected();
	}

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		DashboardVariable.initDefaultVariables();

		m_oi = new InputManager();

		SmartDashboard.putData("Auto mode", m_autoModeChooser);
		m_autoModeChooser.addDefault("Center Switch", "AutoGoToSwitchCommand");
		m_autoModeChooser.addObject("Drive Forward/Cross Line", "AutoCrossStartLineCommand");
		m_autoModeChooser.addObject("Left Score Scale", "AutoSlot1ToScaleCommand");
		m_autoModeChooser.addObject("Right Score Scale", null);
		m_autoModeChooser.addObject("Test Left Center 2 cube", "Test2CubeLeft");
		m_autoModeChooser.addObject("Test Side Switch Left", "TestAutoLeftSideSwitchCommand");
		m_autoModeChooser.addObject("Test Side Switch Right", "TestAutoRightSideSwitchCommand");

		pidArm.armEncoder.reset();
		driveTrain.resetEncoders();
		//		CameraServer.getInstance().startAutomaticCapture();
		intakeCompressor.start();
		intakeSolenoid.set(DoubleSolenoid.Value.kOff);
	}

	@Override
	public void robotPeriodic()
	{
		driveTrain.updatePID();

		SmartDashboard.putData(driveTrain.leftEncoder);
		SmartDashboard.putData(driveTrain.rightEncoder);
		SmartDashboard.putData(pidArm.armEncoder);

	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit()
	{
		if (m_autonomousCommand != null)
		{
			m_autonomousCommand.cancel();
			m_autonomousCommand = null;
		}

		driveTrain.resetEncoders();
		driveTrain.disablePID();
		driveTrain.setSpeed(0.0, 0.0);
	}

	@Override
	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit()
	{
		driveTrain.setSpeed(0.0, 0.0);
		driveTrain.resetEncoders();

		String autoCommandName = m_autoModeChooser.getSelected();
		if (autoCommandName.equals("AutoGoToSwitchCommand"))
			m_autonomousCommand = new AutoGoToSwitchCommand();
		else if (autoCommandName.equals("AutoCrossStartLineCommand"))
			m_autonomousCommand = new AutoCrossStartLineCommand();
		else if (autoCommandName.equals("AutoSlot1ToScaleCommand"))
			m_autonomousCommand = new AutoSlot1ToScaleCommand();
		else if (autoCommandName.equals("Test2CubeLeft"))
			m_autonomousCommand = new TestAutoLeftCube2();
		else if (autoCommandName.equals("TestAutoLeftSideSwitchCommand"))
			m_autonomousCommand = new TestAutoLeftSideSwitchCommand();
		else if (autoCommandName.equals("TestAutoRightSideSwitchCommand"))
			m_autonomousCommand = new TestAutoRightSideSwitchCommand();
		// schedule the autonomous command (example)
		if (m_autonomousCommand != null)
		{
			m_autonomousCommand.start();
		}

		driveTrain.enablePID();
	}

	void waitForGameMessage()
	{
		while (true)
		{
			String message = DriverStation.getInstance().getGameSpecificMessage();
			if (message != null && message.length() > 0)
				break;

			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit()
	{
		if (m_autonomousCommand != null)
		{
			m_autonomousCommand.cancel();
		}

		driveTrain.setSpeed(0.0, 0.0);
		driveTrain.disablePID();
		driveTrain.resetEncoders();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
	}

	@Override
	public void testInit()
	{
		if (m_autonomousCommand != null)
		{
			m_autonomousCommand.cancel();
		}

		driveTrain.setSpeed(0.0, 0.0);
		driveTrain.disablePID();
		driveTrain.resetEncoders();
		pidArm.armEncoder.reset();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic()
	{
	}
}
