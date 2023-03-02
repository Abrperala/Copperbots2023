package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BottomRoller extends SubsystemBase{
  
  final CANSparkMax bottomIntake = new CANSparkMax(16, MotorType.kBrushed);


  public void rollBottomIntake(double speed){
    bottomIntake.set(-speed);
   }
   
}
