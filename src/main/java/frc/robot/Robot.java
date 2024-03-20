// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
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
  }

  @Override
  public void robotPeriodic() {
    arm.updatePosSMDB();
  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    // intake
    intake.loop(operator.getLeftTriggerAxis());
    
    // shoot
    intake.shoot(operator.getRightTriggerAxis() > 0.5);

    arm.shootPos(operator.getLeftBumper(), operator.getAButtonPressed(), operator.getBButtonPressed(), 
      operator.getXButtonPressed(), operator.getYButtonPressed());
    arm.loop();
    spinner.loop(operator.getLeftTriggerAxis());
    
    // drive
    drivetrain.loop(-joystickDeadband(driver.getLeftY() * 0.7),joystickDeadband(driver.getRightX() * 0.7));
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
