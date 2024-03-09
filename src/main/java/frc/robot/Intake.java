package frc.robot;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Intake {

    // right and left as if you were standing BEHIND the robot
    private VictorSPX leftMotor;
    private VictorSPX rightMotor;

    public Intake() {
        leftMotor = new VictorSPX(5);
        leftMotor.configFactoryDefault();
        leftMotor.setNeutralMode(NeutralMode.Coast);
        rightMotor = new VictorSPX(6);
        rightMotor.configFactoryDefault();
        rightMotor.setNeutralMode(NeutralMode.Coast);
    }

    public void loop(double speed) {
        // checking if the X button is pressed on the controller
        leftMotor.set(VictorSPXControlMode.PercentOutput, speed);
        rightMotor.set(VictorSPXControlMode.PercentOutput, speed);
    }
}
