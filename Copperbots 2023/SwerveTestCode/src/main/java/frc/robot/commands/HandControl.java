package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hand;
import java.util.function.DoubleSupplier;

public class HandControl extends CommandBase{
  
  private final Hand m_hand;
  private final DoubleSupplier m_speed;

  public HandControl (Hand hand, DoubleSupplier speed) {
    this.m_hand = hand;
    this.m_speed = speed;
    addRequirements(m_hand);
  }


  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
  m_hand.controlHand(m_speed.getAsDouble()*.4);
  }
  


  @Override
  public boolean isFinished() {
 
    return false;
  }



  @Override
  public void end(boolean interrupted) {
 
  }
}

