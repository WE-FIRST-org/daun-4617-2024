// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cameraserver.CameraServerShared;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  XboxController driver;
  XboxController operator;
  Intake intake;
  Arm arm;
  Spinner spinner;
  Drivetrain drivetrain;
  Climb climb;
  boolean armUp = true;


  private double joystickDeadband(double input) {
    if(input * input < 0.001) {
      return 0.0;
    }
    return input;
  }

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    intake = new Intake();
    arm = new Arm();
    driver = new XboxController(0);
    operator = new XboxController(1);
    spinner = new Spinner();
    drivetrain = new Drivetrain();
    climb = new Climb();
    CameraServer.startAutomaticCapture(0);
  }

  @Override
  public void robotPeriodic() {
    //arm.updatePosSMDB();
  }

  public Timer timer;
  @Override
  public void autonomousInit() {
    timer = new Timer();
    timer.start();
  }

  @Override
  public void autonomousPeriodic() {
    if(timer.get() < 2) {
      intake.loop(0.8);
    } else if(timer.get() < 2.5) {
      spinner.loop(1);
    } else if(timer.get() < 10) {
      intake.loop(0);
      spinner.loop(0);
      drivetrain.auto(timer);
    }
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    /*
    // intake
    intake.loop(driver.getLeftTriggerAxis() > 0.15 ? -driver.getLeftTriggerAxis() : 
        operator.getAButton() ? -0.2 : 0);
    
    // shoot
    intake.shoot(operator.getRightTriggerAxis() > 0.5);

    
    arm.shootPos(operator.getLeftBumper(), operator.getAButtonPressed(), operator.getBButtonPressed(), 
      operator.getXButtonPressed(), operator.getYButtonPressed());
    

    arm.loop(-operator.getRightY());
    spinner.loop(operator.getLeftTriggerAxis());
    */

    // operator controls
    double armSpeed = joystickDeadband(-operator.getLeftY() * (Math.abs(operator.getLeftY())));
    if(armSpeed > 0) {
      armSpeed *= 0.4;
      armUp = true;
    } else if(armSpeed < 0) {
      armSpeed *= 0.8;
      armUp = false;
    } if(armSpeed == 0) {
      // if(armUp) armSpeed = 0.12;
    }
    arm.loop(armSpeed);
    intake.loop(- 0.7 * joystickDeadband(operator.getRightTriggerAxis()));
    if(operator.getLeftTriggerAxis() > 0.2) {
      System.out.println(operator.getLeftTriggerAxis());
      intake.loop(joystickDeadband(operator.getLeftTriggerAxis()));
    }
    else if(operator.getYButton()) {
      intake.loop(0.3);
    } else if(operator.getAButton()) {
      intake.loop(0.6);
    }
    spinner.loop(operator.getXButton() ? 1 : 0);//operator.getLeftTriggerAxis());
    climb.loop(operator.getStartButton(), operator.getBackButton());

    // driver controlsq
    double reverse = driver.getLeftTriggerAxis() > 0.3 ? 1 : -1;
    double throttle = joystickDeadband(driver.getLeftY()) * Math.abs(driver.getLeftY());
    double steer = 0.55 * reverse * joystickDeadband(driver.getRightX()) * Math.abs(driver.getRightX());
    if(driver.getRightTriggerAxis() < 0.4) throttle *= 0.55; 
    drivetrain.loop(throttle,steer);
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {
    //arm.test(operator.getLeftY(), operator.getRightY());
  }

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
