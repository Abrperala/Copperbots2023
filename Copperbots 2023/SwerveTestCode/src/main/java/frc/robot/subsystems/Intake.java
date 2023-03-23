package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Intake extends SubsystemBase {
  
  public boolean pistonState;
  private DoubleSolenoid m_DoubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH,12, 13);
  
  public Intake(){
    retract();
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


}
