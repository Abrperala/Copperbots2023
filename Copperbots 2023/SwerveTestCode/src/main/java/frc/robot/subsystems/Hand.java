package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.CounterBase;
public class Hand extends SubsystemBase{
  
  final CANSparkMax handMotor = new CANSparkMax(15, MotorType.kBrushed);
  private final Encoder HandEncoder = new Encoder(4, 3, true, CounterBase.EncodingType.k4X);
 
  double current = handMotor.getOutputCurrent();
  
  public Hand(){
  HandEncoder.setDistancePerPulse(360/2048.0);
  //HandEncoder.reset();
  }
  public void controlHand(double speed) {
    handMotor.set(speed);
  }

  public double getCurrent(){
  return handMotor.getOutputCurrent();
  }

  public double getEncoderDistance(){
    return HandEncoder.getDistance();
  }

@Override
public void periodic(){

  if (current < handMotor.getOutputCurrent()){
    current = handMotor.getOutputCurrent();
  }

  SmartDashboard.putNumber("Highest Hand Current", current);
  SmartDashboard.putNumber("Hand Encoder", HandEncoder.getDistance());
}


}
