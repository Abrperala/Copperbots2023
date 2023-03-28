package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

public class AutonArmToHigh extends CommandBase{
  TrapezoidProfile.Constraints constraints = new TrapezoidProfile.Constraints(30000, 70);
  ProfiledPIDController pid = new ProfiledPIDController(.022, 0.0001, 0.0003, constraints);
  private final Arm m_arm;

  public AutonArmToHigh(Arm arm) {
    this.m_arm = arm;
    addRequirements(m_arm);
  }


  @Override
  public void initialize() {
    pid.reset(m_arm.getEncoderDistance());
  }

  @Override
  public void execute() {
  m_arm.setArmPosition(pid.calculate(m_arm.getEncoderDistance(), 110));
   }
  


  @Override
  public boolean isFinished() {
    Boolean result = false;
    if (Math.abs(m_arm.getEncoderDistance()) > 105){
      result = true;
    } 
    return result;
  }



  @Override
  public void end(boolean interrupted) {
  
  }
}

