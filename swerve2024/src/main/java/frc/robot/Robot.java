// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.swervedrive.arm;
import frc.robot.subsystems.swervedrive.intake;
import frc.robot.subsystems.swervedrive.shooter;

//import frc.robot.subsystems.swervedrive.test;

import java.io.File;
import java.io.IOException;
import swervelib.parser.SwerveParser;

import edu.wpi.first.wpilibj.PS4Controller;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as
 * described in the TimedRobot documentation. If you change the name of this class or the package after creating this
 * project, you must also update the build.gradle file in the project.
 */
public class Robot extends TimedRobot
{
  intake Intake = new intake(11);
  shooter Shooter = new shooter(12, 13);
  arm Arm = new arm(9, 10, 0);

  private static Robot   instance;
  private        Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private Timer disabledTimer;
  private Timer elapsedTime = new Timer();;
  private double time=0;
  PS4Controller controller = new PS4Controller(0);
  //test a = new test(0);

  public Robot()
  {
    instance = this;
  }

  public static Robot getInstance()
  {
    return instance;
  }

  /**
   * This function is run when the robot is first started up and should be used for any initialization code.
   */
  @Override
  public void robotInit()
  {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    // Create a timer to disable motor brake a few seconds after disable.  This will let the robot stop
    // immediately when disabled, but then also let it be pushed more 
    disabledTimer = new Timer();
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics that you want ran
   * during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic()
  {
    time+=1;
    if(time>=(1000.0/50))
    {
      //System.out.println("a.encoderVal()");
      System.out.println(Arm.encoderVal());
      time=0;
    }
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    //a.testb(IWKMS.getL2Button());
    //a.testc(IWKMS.getR2Button());
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit()
  {
    m_robotContainer.setMotorBrake(true);
    disabledTimer.reset();
    disabledTimer.start();
  }

  @Override
  public void disabledPeriodic()
  {
    if (disabledTimer.hasElapsed(Constants.DrivebaseConstants.WHEEL_LOCK_TIME))
    {
      m_robotContainer.setMotorBrake(false);
      disabledTimer.stop();
    }
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit()
  {
    
    m_robotContainer.setMotorBrake(true);
    // schedule the autonomous command (example)
    /*if (m_autonomousCommand != null)
    {
      m_autonomousCommand.schedule();
      
    }*/
    

    elapsedTime.reset();
    elapsedTime.start();
    

    
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic()
  {

    if (elapsedTime.get() < 2.2){
      arm.manuel(false, true, false, false, false, false);
    } else {
      arm.manuel(false, false, false, false, false, false);
    }

    if(elapsedTime.get() > 2 && elapsedTime.get() < 4){
      shooter.shoot(true);
    } else {
      shooter.shoot(false);
    }

    if(elapsedTime.get() > 3 && elapsedTime.get() < 4){
      intake.griper(true, false);
    } else {
      intake.griper(false, false);
    }
    if(elapsedTime.get()>4 && elapsedTime.get() < 6)
    {
      m_autonomousCommand = RobotContainer.getAutonomousCommand();
      m_autonomousCommand.schedule();
    }
    /*if(elapsedTime.get()>4 && elapsedTime.get() < 6)
    {
      SwerveSubsystem.auton(-0.0,0.0,0.0,0.0);
    }
    else
    {
      SwerveSubsystem.auton(0.0,0.0,0.0,0.0);
    }*/

  }

  @Override
  public void teleopInit()
  {
    //System.out.println(a.encoderVal());
    //a.testc(IWKMS.getR2Button());
    //a.testb(IWKMS.getL2Button());
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null)
    {
      m_autonomousCommand.cancel();
    }
    m_robotContainer.setDriveMode();
    m_robotContainer.setMotorBrake(true);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic()
  {
    //System.out.println(a.encoderVal());
    Shooter.shoot(controller.getL2Button());
    
    Intake.griper(controller.getR2Button(), controller.getR1Button());

    Arm.manuel(controller.getOptionsButton(), controller.getShareButton(), controller.getTriangleButton(), controller.getCircleButton(), controller.getSquareButton(), controller.getCrossButton());
    
  }

  @Override
  public void testInit()
  {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
    try
    {
      new SwerveParser(new File(Filesystem.getDeployDirectory(), "swerve"));
    } catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic()
  {

  }

  /**
   * This function is called once when the robot is first started up.
   */
  @Override
  public void simulationInit()
  {
  }

  /**
   * This function is called periodically whilst in simulation.
   */
  @Override
  public void simulationPeriodic()
  {
  }
}
