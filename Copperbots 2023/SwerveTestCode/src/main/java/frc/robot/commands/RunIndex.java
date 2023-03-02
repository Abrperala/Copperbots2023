package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Index;

public class RunIndex extends CommandBase {
  
  private final Index m_index;

  public RunIndex (Index index) {
    this.m_index = index;
    addRequirements(m_index);
  }


  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
   if (m_index.getBeamBreak() == true) {
   m_index.controlIndex(-1);
   }
  }
  


  @Override
  public boolean isFinished() {
    boolean result = false;
    if (m_index.getBeamBreak() == false){
      result = true;
    }
    return result;
  }



  @Override
  public void end(boolean interrupted) {
    m_index.controlIndex(0);
  }
}
