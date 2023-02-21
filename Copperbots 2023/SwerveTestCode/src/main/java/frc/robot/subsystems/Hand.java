package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class Hand extends SubsystemBase{
  
  final CANSparkMax handMotor = new CANSparkMax(15, MotorType.kBrushed);

  public void controlHand(double speed) {
    handMotor.set(speed);
  }







}