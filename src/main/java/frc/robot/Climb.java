package frc.robot;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Climb {
    private VictorSPX motor1;
    private VictorSPX motor2;

    public Climb() {
        motor1 = new VictorSPX(10);
        motor1.configFactoryDefault();
        motor1.setNeutralMode(NeutralMode.Brake);
        
        motor2 = new VictorSPX(11);
        motor2.configFactoryDefault();
        motor2.setNeutralMode(NeutralMode.Brake);
        motor2.follow(motor1);
    }
    public void loop(boolean button1, boolean button2) {
        if(button1) {
            motor1.set(VictorSPXControlMode.PercentOutput, 0.7);
        } else if (button2) {
            motor1.set(VictorSPXControlMode.PercentOutput, -0.7);
        }
        else {
            motor1.set(ControlMode.PercentOutput, 0);
        }
    }
}
