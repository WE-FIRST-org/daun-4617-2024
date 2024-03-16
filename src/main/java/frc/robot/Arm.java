package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Arm {
    public static final double FORWARD_POS = 2.55; 
    public static final double BACKWARD_POS = -1.43;
    private CANSparkMax motor1;
    private CANSparkMax motor2;
    private SparkPIDController armController;
    private RelativeEncoder armEncoder;
    public double kP, kI, kD, kIz, kFF, target;

    public Arm() {
        motor1 = new CANSparkMax(7, MotorType.kBrushless);
        motor1.restoreFactoryDefaults();
        motor1.setIdleMode(IdleMode.kBrake);

        motor2 = new CANSparkMax(8, MotorType.kBrushless);
        motor2.restoreFactoryDefaults();
        motor2.setIdleMode(IdleMode.kBrake);
        motor2.follow(motor1);

        armController = motor1.getPIDController();
        armEncoder = motor1.getEncoder();

        kP = 0;
        kI = 0;
        kD = 0;
        kIz = 0;
        kFF = 0;
        target = 0;

        armController.setP(kP);
        armController.setI(kI);
        armController.setD(kD);
        armController.setIZone(kIz);
        armController.setFF(0);
        armController.setOutputRange(-1, 1);

        armEncoder.setPosition(FORWARD_POS);

        SmartDashboard.setDefaultNumber("p", 0);
        SmartDashboard.setDefaultNumber("i", 0);
        SmartDashboard.setDefaultNumber("d", 0);
        SmartDashboard.setDefaultNumber("iz", 0);
        SmartDashboard.setDefaultNumber("ff", 0);
        SmartDashboard.putNumber("position", 0); 
        SmartDashboard.setDefaultNumber("target", 0);
    }

    public void updatePosSMDB() {
        SmartDashboard.putNumber("position", armEncoder.getPosition());
    }

    public void loop() {
        double p = SmartDashboard.getNumber("p", 0);
        double i = SmartDashboard.getNumber("i", 0);
        double d = SmartDashboard.getNumber("d", 0);
        double iz = SmartDashboard.getNumber("iz", 0);
        double ff = SmartDashboard.getNumber("ff", 0);

        SmartDashboard.putNumber("position", armEncoder.getPosition());
        target = SmartDashboard.getNumber("target", 0);
        double arbFF = ff * Math.sin(Math.PI * armEncoder.getPosition() / (2 * FORWARD_POS));
        System.out.println(arbFF);
        armController.setReference(target, CANSparkMax.ControlType.kPosition, 0, arbFF);

        // if PID coefficients on SmartDashboard have changed, write new values to controller
        if ((p != kP)) {
            armController.setP(p);
            kP = p;
        }
        if ((i != kI)) {
            armController.setI(i);
            kI = i;
        }
        if ((d != kD)) {
            armController.setD(d);
            kD = d;
        }
        if ((iz != kIz)) {
            armController.setIZone(iz);
            kIz = iz;
        }
        if ((ff != kFF)) {
            kFF = ff;
        }
    }

}
