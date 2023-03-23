package frc.robot.commands.ButtonCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hand;

public class HandTwistBack extends CommandBase {
  PIDController pid = new PIDController(.01, 0, 1);
  private final Hand m_hand;

  public HandTwistBack(Hand hand) {
    this.m_hand = hand;
    addRequirements(m_hand);
  }


  @Override
  public void initialize() {
    pid.reset();
  }

  @Override
  public void execute() {
    SmartDashboard.putNumber("Hand Pid", pid.calculate(m_hand.getEncoderDistance(), 0));

    m_hand.controlHand(pid.calculate(m_hand.getEncoderDistance(), 0));
  }

  @Override
  public boolean isFinished(){
    Boolean result = false;
    if (Math.abs(0 - m_hand.getEncoderDistance()) <= .5){
      result = true;
    }

    return result;
  }

  @Override
  public void end(boolean interrupted) {
    m_hand.controlHand(0);
  }

  
}

