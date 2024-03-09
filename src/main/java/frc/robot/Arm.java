package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Arm{
    private CANSparkMax Motor1;

    public Arm() {
        Motor1 = new CANSparkMax(1, MotorType.kBrushless);
        Motor1.restoreFactoryDefaults();
        Motor1.setIdleMode(IdleMode.kCoast);
     
    }

    public void loop() {
        
       controller 


    }

}
