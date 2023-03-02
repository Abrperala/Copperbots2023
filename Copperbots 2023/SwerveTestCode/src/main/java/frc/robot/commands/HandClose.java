package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hand;

public class HandClose extends CommandBase{
  
  private final Hand m_hand;

  public HandClose (Hand hand) {
    this.m_hand = hand;
    addRequirements(m_hand);
  }


  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
  m_hand.controlHand(0.2);
  }
  


  @Override
  public boolean isFinished() {
    boolean result = false;
    if (m_hand.getCurrent() >= 18 || m_hand.getEncoderDistance() > 75) {
    result = true;
  }
    return result;
  } 



  @Override
  public void end(boolean interrupted) {
 m_hand.controlHand(0);
  }
}

