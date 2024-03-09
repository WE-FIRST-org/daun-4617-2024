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

    private double left;
    private double right;

    public Drivetrain() {

        controller = new XboxController(0);

        //Right Motors
        rightMotor1 = new CANSparkMax(1, MotorType.kBrushless);
        rightMotor1.restoreFactoryDefaults();
        rightMotor1.setIdleMode(IdleMode.kCoast);

        rightMotor2 = new CANSparkMax(2, MotorType.kBrushless);
        rightMotor2.restoreFactoryDefaults();
        rightMotor2.setIdleMode(IdleMode.kCoast);
        rightMotor2.follow(rightMotor1);

        //Left Motors
        leftMotor1 = new CANSparkMax(3, MotorType.kBrushless);
        leftMotor1.restoreFactoryDefaults();
        leftMotor1.setIdleMode(IdleMode.kCoast);
        leftMotor1.follow(rightMotor1);

        leftMotor2 = new CANSparkMax(4, MotorType.kBrushless);
        leftMotor2.restoreFactoryDefaults();
        leftMotor2.setIdleMode(IdleMode.kCoast);

        leftMotor1.setInverted(true);
        leftMotor2.setInverted(true);
        }

        public void loop() {
            right = controller.getRightY() - controller.getLeftX();
            left = controller.getRightY() + controller.getLeftX();

            rightMotor1.set(right);
            leftMotor1.set(left);

        }



    }





