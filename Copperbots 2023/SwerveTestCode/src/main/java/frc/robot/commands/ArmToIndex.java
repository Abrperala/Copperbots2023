package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.subsystems.Arm;
public class ArmToIndex extends CommandBase {
  
  PIDController pid = new PIDController(.005, 0.005, 0);
 
  private final Arm m_arm;

  public ArmToIndex(Arm arm) {
    this.m_arm = arm;
    
    addRequirements(m_arm);
  }


  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
  m_arm.setArmPosition(pid.calculate(m_arm.getEncoderDistance(), 0));
   }
  


  @Override
  public boolean isFinished() {
    Boolean result = false;
    if (Math.abs(m_arm.getEncoderDistance()) < 0.5){
      result = true;
    } 
    return result;
  }



  @Override
  public void end(boolean interrupted) {
  m_arm.setArmPosition(0);
  }
}
