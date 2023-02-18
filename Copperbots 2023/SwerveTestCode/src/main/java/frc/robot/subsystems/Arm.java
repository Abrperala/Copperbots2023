package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase;

public class Arm extends SubsystemBase {

final CANSparkMax arm1 = new CANSparkMax(13, MotorType.kBrushless);
final CANSparkMax arm2 = new CANSparkMax(14, MotorType.kBrushless);
final MotorControllerGroup armMotorControllerGroup = new MotorControllerGroup(arm1, arm2);
private final Encoder armEncoder = new Encoder(8, 7, false, CounterBase.EncodingType.k4X);

private DoubleSolenoid m_DoubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 10, 11);
public boolean pistonState;

public Arm(){
  armEncoder.setDistancePerPulse(360/4096.0);
}

public void setArmPosition(double speed){
armMotorControllerGroup.set(speed);
}

public void resetEncoder(){
  armEncoder.reset();
}

public double getEncoderDistance(){
 return armEncoder.getDistance();
}
  public void extend() {
    m_DoubleSolenoid.set(Value.kForward);
    pistonState = true;
}

public void retract() {
    m_DoubleSolenoid.set(Value.kReverse);
    pistonState = false;
}

public void togglePiston() {
  if(pistonState) {
    retract();
  } 
  else {
    extend();
  }
 }


@Override
 public void periodic(){
  SmartDashboard.putNumber("arm encoder", armEncoder.getDistance());
 }

}

