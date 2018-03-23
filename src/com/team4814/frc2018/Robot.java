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
import com.team4814.frc2018.subsystems.Climber;
import com.team4814.frc2018.subsystems.DriveTrain;
import com.team4814.frc2018.subsystems.Intake;
import com.team4814.frc2018.subsystems.PIDArm;
import com.team4814.frc2018.utils.DashboardVariable;

import edu.wpi.first.wpilibj.CameraServer;
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

	Command m_autonomousCommand;
	SendableChooser<Command> m_autoModeChooser = new SendableChooser<>();
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
		m_autoModeChooser.addDefault("No Scoring", new AutoCrossStartLineCommand());
		m_autoModeChooser.addObject("Score ", new AutoGoToSwitchCommand());
		//m_autoModeChooser.addObject("Drive Forward Test", new DrivePIDCommand(100.0, 0.4));
		//m_autoModeChooser.addObject("Center Switch R", new AutoSlot2ToRightSwitch());
		//m_autoModeChooser.addObject("Center Switch L", new AutoSlot2ToLeftSwitch());
		//m_autoModeChooser.addObject("Center Scale L", new CenterScaleLAutoCommand());
		//m_autoModeChooser.addObject("Center Scale R", new CenterScaleRAutoCommand());

		SmartDashboard.putData("Auto Position", m_autoPositionChooser);
		m_autoPositionChooser.addDefault("Center", AutoPosition.kCenter);
		m_autoPositionChooser.addObject("Left", AutoPosition.kLeft);
		m_autoPositionChooser.addObject("Right", AutoPosition.kRight);

		SmartDashboard.putData("Auto Goal", m_autoGoalChooser);
		m_autoGoalChooser.addDefault("Switch", AutoGoal.kSwitch);
		m_autoGoalChooser.addObject("Scale", AutoGoal.kScale);

		// Some test commands for the PID Arm
		//		Command resetArmCommand = new MoveArmPIDCommand(0.0);
		//		SmartDashboard.putData("Reset Arm", resetArmCommand);
		//		Command raiseArmCommand = new MoveArmPIDCommand(5.0);
		//		SmartDashboard.putData("Raise Arm", raiseArmCommand);

		pidArm.armEncoder.reset();
		driveTrain.resetEncoders();
		CameraServer.getInstance().startAutomaticCapture();
	}

	@Override
	public void robotPeriodic()
	{
		driveTrain.updatePID();

		SmartDashboard.putData(driveTrain.leftEncoder);
		SmartDashboard.putData(driveTrain.rightEncoder);

		SmartDashboard.putData(driveTrain.leftDrivePID);
		SmartDashboard.putData(driveTrain.rightDrivePID);

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
		if (m_autonomousCommand != null)
		{
			m_autonomousCommand.cancel();
		}

		driveTrain.setSpeed(0.0, 0.0);
		driveTrain.resetEncoders();

		m_autonomousCommand = m_autoModeChooser.getSelected();

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null)
		{
			m_autonomousCommand = new AutoGoToSwitchCommand();
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
