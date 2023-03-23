package frc.robot.commands.ButtonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import frc.robot.subsystems.Arm;
public class ArmToIndex extends CommandBase {
  
  TrapezoidProfile.Constraints constraints = new TrapezoidProfile.Constraints(30000, 70);
  ProfiledPIDController pid = new ProfiledPIDController(.03, 0.0006, 0.00, constraints);
   
  private final Arm m_arm;

  public ArmToIndex(Arm arm) {
    this.m_arm = arm;
    addRequirements(m_arm);
  }


  @Override
  public void initialize() {
    pid.reset(m_arm.getEncoderDistance());
    
  }

  @Override
  public void execute() {
  m_arm.setArmPosition(pid.calculate(m_arm.getEncoderDistance(), 0)* .1);
  if (m_arm.getLimitSwitch() == false) {
    m_arm.setArmPosition(0);
  }
   }
  


  @Override
  public boolean isFinished() {
    Boolean result = false;
    if (Math.abs(m_arm.getEncoderDistance()) < 1){
      result = true;
    } 
    return result;
  }



  @Override
  public void end(boolean interrupted) {
  m_arm.setArmPosition(0);
  }
}
