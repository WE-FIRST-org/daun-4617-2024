package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Drivetrain {
    private CANSparkMax rightMotor1;
    private CANSparkMax rightMotor2;
    private CANSparkMax leftMotor1;
    private CANSparkMax leftMotor2;
    // i don't know if i did this correct :(
    private SparkPIDController rightDrivetrainController;
    private RelativeEncoder rightDrivetrainEncoder;
    private SparkPIDController leftDrivetrainController;
    private RelativeEncoder leftDrivetrainEncoder;
    public boolean disabled;

    // they were declared public in Arm.java, will that be an issue here?
    public static final double KP = 0.05;
    public static final double KI = 0;
    public static final double KD = 0;
    public static final double KIZ = 0;
    public static final double KFF = 0;

    public static final double ROTATIONS_PER_INCH = 0.449;

    public Drivetrain() {
        // Right Motors
        rightMotor1 = new CANSparkMax(2, MotorType.kBrushless);
        rightMotor1.restoreFactoryDefaults();
        rightMotor1.setIdleMode(IdleMode.kBrake);

        rightMotor2 = new CANSparkMax(4, MotorType.kBrushless);
        rightMotor2.restoreFactoryDefaults();
        rightMotor2.setIdleMode(IdleMode.kBrake);
        rightMotor2.follow(rightMotor1);

        // Left Motors
        leftMotor1 = new CANSparkMax(1, MotorType.kBrushless);
        leftMotor1.restoreFactoryDefaults();
        leftMotor1.setIdleMode(IdleMode.kBrake);

        leftMotor2 = new CANSparkMax(3, MotorType.kBrushless);
        leftMotor2.restoreFactoryDefaults();
        leftMotor2.setIdleMode(IdleMode.kBrake);
        leftMotor2.follow(leftMotor1);

        rightMotor1.setInverted(true);

        // PID
        // i don't know if i did this correct (the sequel)
        rightDrivetrainController = rightMotor1.getPIDController();
        rightDrivetrainEncoder = rightMotor1.getEncoder();
        leftDrivetrainController = leftMotor1.getPIDController();
        leftDrivetrainEncoder = leftMotor1.getEncoder();

        rightDrivetrainController.setP(KP);
        rightDrivetrainController.setI(KI);
        rightDrivetrainController.setD(KD);
        rightDrivetrainController.setIZone(KIZ);
        rightDrivetrainController.setFF(0);
        rightDrivetrainController.setOutputRange(-1, 1);

        rightDrivetrainEncoder.setPosition(0);
        SmartDashboard.putNumber("drivetrain right", 0);

        leftDrivetrainController.setP(KP);
        leftDrivetrainController.setI(KI);
        leftDrivetrainController.setD(KD);
        leftDrivetrainController.setIZone(KIZ);
        leftDrivetrainController.setFF(0);
        leftDrivetrainController.setOutputRange(-1, 1);

        leftDrivetrainEncoder.setPosition(0);
        SmartDashboard.putNumber("drivetrain right", 0);
    }

    public void auto(Timer timer) {
        // if (timer.get() < 13) {
        //     rightDrivetrainController.setReference(13 * ROTATIONS_PER_INCH, ControlType.kPosition);
        //     leftDrivetrainController.setReference(-13 * ROTATIONS_PER_INCH, ControlType.kPosition);
        // } else 
        if (timer.get() < 15) {
            rightDrivetrainController.setReference(-110 * ROTATIONS_PER_INCH, ControlType.kPosition);
            leftDrivetrainController.setReference(-110 * ROTATIONS_PER_INCH, ControlType.kPosition);
        }
    }

    public void loop(double throttle, double turn) {
        rightMotor1.set(throttle - turn);
        leftMotor1.set(throttle + turn);
    }
}
