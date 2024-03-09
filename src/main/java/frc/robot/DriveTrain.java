package frc.robot;


import edu.wpi.first.wpilibj.XboxController;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class DriveTrain {
    private CANSparkMax RMotor2;
    private CANSparkMax RMotor4;

    private CANSparkMax LMotor1;
    private CANSparkMax LMotor3;
    private XboxController controller;

    private double left;
    private double right;

    private double ifhalf = 1;


    public DriveTrain() {

        controller = new XboxController(0);

        RMotor2 = new CANSparkMax(1, MotorType.kBrushless);
        RMotor2.restoreFactoryDefaults();
        RMotor2.setIdleMode(IdleMode.kCoast);

        RMotor4 = new CANSparkMax(2, MotorType.kBrushless);
        RMotor4.restoreFactoryDefaults();
        RMotor4.setIdleMode(IdleMode.kCoast);
        RMotor4.follow(RMotor2);

        LMotor1 = new CANSparkMax(3, MotorType.kBrushless);
        LMotor1.restoreFactoryDefaults();
        LMotor1.setIdleMode(IdleMode.kCoast);


        LMotor3 = new CANSparkMax(4, MotorType.kBrushless);
        LMotor3.restoreFactoryDefaults();
        LMotor3.setIdleMode(IdleMode.kCoast);
        LMotor3.follow(LMotor1);

        LMotor1.setInverted(true);
        LMotor3.setInverted(true);

    }

    public void loop() {


        
        right = controller.getRightY() - controller.getLeftX();
        left = controller.getRightY() + controller.getLeftX();
        
        RMotor2.set(right*ifhalf);
        LMotor1.set(left*ifhalf);
        //System.out.printf("Left: %f \t\t Right: %f", left, right);
        
        // \ and / are not the same!
        
    }
    
    public void halfspeed() {
        
         if (controller.getXButton()) {
            ifhalf = 0.5;
         }
         
         else {
            ifhalf = 1;
         }

         
    }
    
}