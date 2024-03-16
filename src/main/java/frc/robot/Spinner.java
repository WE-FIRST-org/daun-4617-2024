package frc.robot;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Spinner {
    
    private VictorSPX motor;

    public Spinner() {
        motor = new VictorSPX(9);
        motor.configFactoryDefault();
        motor.setNeutralMode(NeutralMode.Coast);
    }

    public void loop(double speed) {
        motor.set(VictorSPXControlMode.PercentOutput, speed);
    }
}
