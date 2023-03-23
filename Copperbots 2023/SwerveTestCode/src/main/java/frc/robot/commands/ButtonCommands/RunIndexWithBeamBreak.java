package frc.robot.commands.ButtonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Index;

public class RunIndexWithBeamBreak extends CommandBase {
  
  private final Index m_index;

  public RunIndexWithBeamBreak (Index index) {
    this.m_index = index;
    addRequirements(m_index);
  }


  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
  
   m_index.controlIndex(1);
   
  }
  


  @Override
  public boolean isFinished() {
    boolean result = false;
    if (m_index.getBeamBreak()== false){
      result = true;
    }
    return result;
  }



  @Override
  public void end(boolean interrupted) {
    m_index.controlIndex(0);
  }
}
