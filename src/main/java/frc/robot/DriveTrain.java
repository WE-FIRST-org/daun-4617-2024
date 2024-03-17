package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Drivetrain {
    private CANSparkMax rightMotor1;
    private CANSparkMax rightMotor2;
    private CANSparkMax leftMotor1;
    private CANSparkMax leftMotor2;

    private XboxController controller;

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
    }

    public void loop(double throttle, double turn) {
            rightMotor1.set(((throttle < 0 ? -1 : 1 )*throttle*throttle)-turn);
            leftMotor1.set(((throttle < 0 ? -1 : 1 )*throttle*throttle)+turn);
   }
}
