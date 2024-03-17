package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Arm {
    public static final double FORWARD_POS = 2.477; 
    public static final double BACKWARD_POS = -1.43;
    private CANSparkMax motor1;
    private CANSparkMax motor2;
    private SparkPIDController armController;
    private RelativeEncoder armEncoder;
    public boolean disabled;
    public double kP = 0.6;
    public double kI = 0;
    public double kD = 20;
    public double kIz = 0;
    public double kFF = -2.25;

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

        armController.setP(kP);
        armController.setI(kI);
        armController.setD(kD);
        armController.setIZone(kIz);
        armController.setFF(0);
        armController.setOutputRange(-1, 1);

        armEncoder.setPosition(FORWARD_POS);
        SmartDashboard.putNumber("position", 0); 
    }

    public void updatePosSMDB() {
        SmartDashboard.putNumber("position", armEncoder.getPosition());
    }

    public void loop(double target) {
        if (!disabled) {
        double arbFF = kFF * Math.sin(Math.PI * armEncoder.getPosition() / (2 * FORWARD_POS));
        armController.setReference(target, CANSparkMax.ControlType.kPosition, 0, arbFF);
        }
        if (disabled) {
        motor1.set(0);
        }
    }

    public void disable(boolean checker) {
        if(checker == true) {
            disabled = !disabled;
        }
    }

}
