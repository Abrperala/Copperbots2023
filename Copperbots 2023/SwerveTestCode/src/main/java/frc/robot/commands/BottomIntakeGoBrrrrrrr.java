package frc.robot.commands;

import frc.robot.subsystems.BottomRoller;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class BottomIntakeGoBrrrrrrr extends CommandBase{

 
  private final BottomRoller m_bottomRoller;

  public BottomIntakeGoBrrrrrrr(BottomRoller bottomRoller) {
    this.m_bottomRoller = bottomRoller;
    
    addRequirements(m_bottomRoller);
  }


  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
  m_bottomRoller.rollBottomIntake(1);
  }
  


  @Override
  public boolean isFinished() {
 
    return false;
  }



  @Override
  public void end(boolean interrupted) {
  m_bottomRoller.rollBottomIntake(0);
  }
}