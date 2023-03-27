package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TalonEfx extends SubsystemBase {

public static WPI_TalonFX tonysTalonFX = new WPI_TalonFX(10);
TalonFXConfiguration tonysTalonFXConfig = new TalonFXConfiguration();

    public TalonEfx(){
        tonysTalonFX.setSelectedSensorPosition(0);
       
        tonysTalonFX.configAllSettings(tonysTalonFXConfig);
    }



    public double getEncoderValueInRevolutions(){
        return tonysTalonFX.getSelectedSensorPosition() / 2048;
    }

    public static void zeroEncoder(){
    tonysTalonFX.setSelectedSensorPosition(0);
    }

@Override 
public void periodic(){
SmartDashboard.putNumber("talon encoder", getEncoderValueInRevolutions() );


}


}