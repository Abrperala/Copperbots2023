package frc.robot.commands;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeGoBrrrrrrr extends CommandBase{

 
  private final Intake m_intake;

  public IntakeGoBrrrrrrr(Intake intake) {
    this.m_intake = intake;
    
    addRequirements(m_intake);
  }


  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
  m_intake.rollIntake(1);
  }
  


  @Override
  public boolean isFinished() {
 
    return false;
  }



  @Override
  public void end(boolean interrupted) {
  m_intake.rollIntake(0);
  }
}
