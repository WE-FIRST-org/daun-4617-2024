package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Arm {
    public static final double FORWARD_POS = 2.404;
    public static final double BACKWARD_POS = -1.12;
    private CANSparkMax motor1;
    private CANSparkMax motor2;
    private SparkPIDController armController;
    private RelativeEncoder armEncoder;
    public boolean disabled = true;
    public static final double KP = 0.6;
    public static final double KI = 0;
    public static final double KD = 20;
    public static final double KIZ = 0;
    public static final double KFF = -2.25;
    private static final double[] armPositions = new double[] {
            FORWARD_POS, -0.85, 0, BACKWARD_POS, 0, 0, 0, 0
            // a, b, (disable), y, [shifted (a, b, x, y)]
    };
    public static double target = 0;

    public Arm() {
        motor1 = new CANSparkMax(7, MotorType.kBrushed);
        motor1.restoreFactoryDefaults();
        motor1.setIdleMode(IdleMode.kBrake);

        motor2 = new CANSparkMax(8, MotorType.kBrushed);
        motor2.restoreFactoryDefaults();
        motor2.setIdleMode(IdleMode.kBrake);
        motor2.follow(motor1);
        
        armController = motor1.getPIDController();
        armEncoder = motor1.getEncoder();

        armController.setP(KP, 0);
        armController.setI(KI, 0);
        armController.setD(KD, 0);
        armController.setIZone(KIZ, 0);
        armController.setFF(0, 0);
        armController.setOutputRange(-1, 1, 0);

        armEncoder.setPosition(BACKWARD_POS);
        target = armEncoder.getPosition();
        SmartDashboard.putNumber("position", 0);
    }

    public void updatePosSMDB() {
        SmartDashboard.putNumber("position", armEncoder.getPosition());
        // SmartDashboard.putNumber("ahhh", motor1.getAppliedOutput());
    }

    public void loop(double speed) {
        // System.out.println(motor1.getAppliedOutput());
        if (!disabled) {
            double arbFF = KFF * Math.sin(Math.PI * armEncoder.getPosition() / (2 * FORWARD_POS));
            if(Math.abs(armEncoder.getPosition() - FORWARD_POS) < 0.1 && Math.abs(target - FORWARD_POS) > 0.1) arbFF = 0;
            armController.setReference(target, CANSparkMax.ControlType.kPosition, 0, arbFF);
        } else {
            armController.setReference(0, CANSparkMax.ControlType.kVoltage);
        }
        // if (!disabled) {
        //     motor1.set(0.9 * speed);
        // }
    }

    public void toggleDisable(boolean button) {
        if (button) {
            disabled = !disabled;
        }
    }

    // public void shootPos(boolean shift, boolean a, boolean b, boolean x, boolean y) {
    //     // is this supposed to be .setReference??
    //     // instead of target = 0??
    //     if (!shift && a) {
    //         target = armPositions[0];
    //     }
    //     if (!shift && b) {
    //         target = armPositions[1];
    //     }
    //     if (!shift && x) {
    //         toggleDisable(true);
    //         //target = armPositions[2];
    //     }
    //     if (!shift && y) {
    //         target = armPositions[3];
    //     }
    //     if (shift && a) {
    //         target = armPositions[4];
    //     }
    //     if (shift && b) {
    //         target = armPositions[5];
    //     }
    //     if (shift && x) {
    //         target = armPositions[6]; 
    //     }
    //     if (shift && y) {
    //         target = armPositions[7];
    //     }
    // }

    void test(double sp, double sp2) {
        //motor1.set(sp);
        //motor2.set(sp2);
    }
}
