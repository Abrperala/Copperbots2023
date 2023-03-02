package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

  public class TopRoller extends SubsystemBase {
  
  final CANSparkMax topIntake = new CANSparkMax(17, MotorType.kBrushed);


  public void rollTopIntake(double speed){
  topIntake.set(speed);
 }

}
