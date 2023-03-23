package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Index extends SubsystemBase{
  
  CANSparkMax indexMotor = new CANSparkMax(18, MotorType.kBrushless);
  DigitalInput beamBreak = new DigitalInput(1);
  
  public void controlIndex(double speed) {
    indexMotor.set(speed);

  }
  
  public boolean getBeamBreak(){
   return beamBreak.get();
  }


  @Override
  public void periodic(){
    SmartDashboard.putBoolean("BeamBreak", beamBreak.get());
  }
}
