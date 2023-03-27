package frc.robot.commands.AutoCommands;

import frc.robot.subsystems.TopRoller;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class EjectIntake extends CommandBase{

 
  private final TopRoller m_topRoller;

  public EjectIntake(TopRoller topRoller) {
    this.m_topRoller = topRoller;
    addRequirements(m_topRoller);
  }


  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
  m_topRoller.rollTopIntake(-1);
  }
  


  @Override
  public boolean isFinished() {
 
    return true;
  }



  @Override
  public void end(boolean interrupted) {
  
  }
}
