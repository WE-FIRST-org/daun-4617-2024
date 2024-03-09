package frc.robot;
import edu.wpi.first.wpilibj.XboxController;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class IntakeSparkMax {
    private XboxController controller;

    // right and left as if you were standing BEHIND the robot
    private CANSparkMax leftMotor;
    private CANSparkMax rightMotor;

    public IntakeSparkMax() {
        controller = new XboxController(0);

        leftMotor = new CANSparkMax(5, MotorType.kBrushless);
        leftMotor.restoreFactoryDefaults();
        leftMotor.setIdleMode(IdleMode.kCoast);

        rightMotor = new CANSparkMax(6, MotorType.kBrushless);
        rightMotor.restoreFactoryDefaults();
        rightMotor.setIdleMode(IdleMode.kCoast);
    }

    public void loop() {
        // checking if the X button is pressed on the controller 
        if (controller.getXButtonPressed()) {
            leftMotor.set(1);
            rightMotor.set(-1);
        }
    }
}
