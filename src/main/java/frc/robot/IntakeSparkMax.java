package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class IntakeSparkMax {

    // right and left as if you were standing BEHIND the robot
    private CANSparkMax leftMotor;
    private CANSparkMax rightMotor;

    public IntakeSparkMax() {
        leftMotor = new CANSparkMax(5, MotorType.kBrushless);
        leftMotor.restoreFactoryDefaults();
        leftMotor.setIdleMode(IdleMode.kCoast);

        rightMotor = new CANSparkMax(6, MotorType.kBrushless);
        rightMotor.restoreFactoryDefaults();
        rightMotor.setIdleMode(IdleMode.kCoast);
    }

    public void loop(double speed) {
        // checking if the X button is pressed on the controller 
            leftMotor.set(speed);
            rightMotor.set(speed); 
    }
}
