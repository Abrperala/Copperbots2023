package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hand;

public class KeepHandClosed extends CommandBase {
  

  private final Hand m_hand;
  
  public KeepHandClosed (Hand hand) {
    this.m_hand = hand;
    addRequirements(m_hand);
  }


  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
  if (m_hand.getEncoderDistance() >= 30){
    m_hand.controlHand(.09);
  }
  else {
    m_hand.controlHand(0.07);
  }
 }


  @Override
  public boolean isFinished() {
    boolean result = false;
    if (m_hand.getEncoderDistance() > 90) {
    result = true;
  }
    return result;
  } 



  @Override
  public void end(boolean interrupted) {
 m_hand.controlHand(0);
  }
}
