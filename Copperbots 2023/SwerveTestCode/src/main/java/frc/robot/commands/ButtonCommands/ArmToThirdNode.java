package frc.robot.commands.ButtonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

public class ArmToThirdNode extends CommandBase{
  TrapezoidProfile.Constraints constraints = new TrapezoidProfile.Constraints(30000, 70);
  ProfiledPIDController pid = new ProfiledPIDController(.03, 0.0006, 0.00, constraints);
  private final Arm m_arm;

  public ArmToThirdNode(Arm arm) {
    this.m_arm = arm;
    addRequirements(m_arm);
  }


  @Override
  public void initialize() {
    pid.reset(m_arm.getEncoderDistance());
  }

  @Override
  public void execute() {
  m_arm.setArmPosition(pid.calculate(m_arm.getEncoderDistance(), 100));
   }
  


  @Override
  public boolean isFinished() {
    Boolean result = false;
    if (Math.abs(m_arm.getEncoderDistance()) > 95){
      result = true;
    } 
    return result;
  }



  @Override
  public void end(boolean interrupted) {
  m_arm.setArmPosition(0);
  }
}
