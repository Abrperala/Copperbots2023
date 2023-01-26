package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;

public class Piston extends SubsystemBase {

    private DoubleSolenoid m_DoubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 6, 7);
    public boolean pistonState;
    DigitalInput limitSwitch = new DigitalInput(9);

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
   /*public void periodic() {
    
    if (limitSwitch.get()) {
      System.out.println(limitSwitch.get());
    //  m_DoubleSolenoid.set(Value.kReverse);
    }
    else {
      System.out.println(limitSwitch.get());
    }  
   }*/














}

