package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class Intake extends SubsystemBase {
  
  final CANSparkMax intake1 = new CANSparkMax(16, MotorType.kBrushless);
  final CANSparkMax intake2 = new CANSparkMax(17, MotorType.kBrushless);
  public boolean pistonState;
  private DoubleSolenoid m_DoubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 8, 9);


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

 public void rollIntake(double speed){
  
  intake2.set(-speed);
  intake1.set(-speed);
 }

}
