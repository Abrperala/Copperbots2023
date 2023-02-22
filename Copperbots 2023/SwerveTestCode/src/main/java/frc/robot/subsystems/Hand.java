package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class Hand extends SubsystemBase{
  
  final CANSparkMax handMotor = new CANSparkMax(15, MotorType.kBrushed);

  public void controlHand(double speed) {
    handMotor.set(speed*.4);
  }

  public double getCurrent(){
  return handMotor.getOutputCurrent();
  }


@Override
public void periodic(){

  double current = handMotor.getOutputCurrent();
  SmartDashboard.putNumber("Hand Current", current);

}


}
