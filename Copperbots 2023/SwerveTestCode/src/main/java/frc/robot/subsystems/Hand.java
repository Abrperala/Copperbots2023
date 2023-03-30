package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase;

public class Hand extends SubsystemBase{
  
  public boolean pistonState;
  private DoubleSolenoid m_DoubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 8, 9);
 // private CANSparkMax hand_SparkMax = new CANSparkMax(15, MotorType.kBrushless);
  private Encoder handEncoder = new Encoder(3, 4, true, CounterBase.EncodingType.k4X);
  
  public Hand(){
    retract();
    handEncoder.setDistancePerPulse(360/2048.0 * .35);
    handEncoder.reset();
  
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

  public void controlHand(double input){
  // hand_SparkMax.set(-input);
  }
  
  public double getEncoderDistance(){
    return handEncoder.getDistance();
  }


  @Override
  public void periodic(){
  SmartDashboard.putNumber("Hand Encoder", getEncoderDistance());

  }


}
