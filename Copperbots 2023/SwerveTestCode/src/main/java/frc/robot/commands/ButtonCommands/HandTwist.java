package frc.robot.commands.ButtonCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hand;

public class HandTwist extends CommandBase {
  PIDController pid = new PIDController(.01, 0.0, 1);
  private final Hand m_hand;
  

  public HandTwist(Hand hand) {
    this.m_hand = hand;
    addRequirements(m_hand);
  }


  @Override
  public void initialize() {
    pid.reset();
  }

  @Override
  public void execute() {
    SmartDashboard.putNumber("Hand Pid", pid.calculate(m_hand.getEncoderDistance(), 180));
      
    m_hand.controlHand(pid.calculate(m_hand.getEncoderDistance(), 180));

  }

  @Override
  public boolean isFinished(){
    Boolean result = false;
    if (Math.abs(180 - m_hand.getEncoderDistance()) <= .5){
      result = true;
    }
    return result;
  }

  @Override
  public void end(boolean interrupted) {
    m_hand.controlHand(0);
  }

  
}

