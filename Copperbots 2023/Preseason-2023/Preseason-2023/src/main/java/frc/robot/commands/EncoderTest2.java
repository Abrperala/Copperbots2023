package frc.robot.commands;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class EncoderTest2 extends CommandBase {
  TrapezoidProfile.Constraints constraints = new TrapezoidProfile.Constraints(10, 10);
  ProfiledPIDController pid = new ProfiledPIDController(.6, 0.1, 0, constraints);

  private final Drivetrain m_drivetrain;

  public EncoderTest2(Drivetrain drivetrain) {
    this.m_drivetrain = drivetrain;
    
    addRequirements(m_drivetrain);
}
@Override
public void initialize(){
pid.reset(4);
}

@Override
public void execute() { 
  m_drivetrain.drive(0, -pid.calculate(m_drivetrain.getLeftEncoderDistance(), 0));
}


@Override
public boolean isFinished(){
  boolean status = false;
  if (m_drivetrain.getLeftEncoderDistance() <= 0) {
  status = true;
}
return status;
}


@Override
public void end(boolean interrupted) {
  m_drivetrain.drive(0.0, 0.0);
}

}
