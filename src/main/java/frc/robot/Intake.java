package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Intake {
    private XboxController controller;

    // right and left as if you were standing BEHIND the robot
    private VictorSPX leftMotor;
    private VictorSPX rightMotor;

    public Intake() {
        controller = new XboxController(0);

        leftMotor = new VictorSPX(5);
        leftMotor.configFactoryDefault();
        leftMotor.setNeutralMode(NeutralMode.Coast);
        rightMotor = new VictorSPX(6);
        rightMotor.configFactoryDefault();
        rightMotor.setNeutralMode(NeutralMode.Coast);
    }

    public void loop() {
        // checking if the X button is pressed on the controller
        if (controller.getXButtonPressed()) {
            leftMotor.set(VictorSPXControlMode.PercentOutput, 1);
            rightMotor.set(VictorSPXControlMode.PercentOutput, -1);
        }
    }
}
