package frc.robot.commands;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

public class ArmToPlayerStation extends CommandBase {
  
  TrapezoidProfile.Constraints constraints = new TrapezoidProfile.Constraints(1, 1);
  ProfiledPIDController pid = new ProfiledPIDController(.1, 0, 0, constraints);
 
  private final Arm m_arm;

  public ArmToPlayerStation(Arm arm) {
    this.m_arm = arm;
    
    addRequirements(m_arm);
  }


  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
  m_arm.setArmPosition(pid.calculate(m_arm.getEncoderDistance(), 90));
   }
  


  @Override
  public boolean isFinished() {
 
    return false;
  }



  @Override
  public void end(boolean interrupted) {
  }
}