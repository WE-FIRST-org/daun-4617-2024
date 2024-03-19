package frc.robot;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Intake {

    // right and left as if you were standing BEHIND the robot
    private VictorSPX leftMotor;
    private VictorSPX rightMotor;
    public boolean shootMode;

    public Intake() {
        leftMotor = new VictorSPX(5);
        leftMotor.configFactoryDefault();
        leftMotor.setNeutralMode(NeutralMode.Coast);
        
        rightMotor = new VictorSPX(6);
        rightMotor.configFactoryDefault();
        rightMotor.setNeutralMode(NeutralMode.Coast);
        rightMotor.follow(leftMotor);
    }

    public void loop(double speed) {
        if (shootMode) {
            leftMotor.set(VictorSPXControlMode.PercentOutput, -1);
        } else {
            leftMotor.set(VictorSPXControlMode.PercentOutput, speed);
        }
    }

    public void shoot(boolean toggleShooter) {
        if (toggleShooter) shootMode = !shootMode;
    }
}
