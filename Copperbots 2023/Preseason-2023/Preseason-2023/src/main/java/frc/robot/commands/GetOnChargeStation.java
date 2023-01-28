package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.math.controller.PIDController;

/**
 * Command to make the tankdrive robot automatically balance on the charge station, 
 */
public class GetOnChargeStation extends CommandBase{

  private final Drivetrain m_drivetrain;

  public GetOnChargeStation(Drivetrain drivetrain) {
    this.m_drivetrain = drivetrain;
    
    addRequirements(m_drivetrain);
    }

  @Override
  public void initialize(){
  m_drivetrain.resetEncoders();
  }

  @Override
  public void execute() { 
    m_drivetrain.drive(0, -0.4);
  }

 
  @Override
  public boolean isFinished(){
    boolean status = false;
    if (m_drivetrain.getRightEncoderDistance() >= 1) {
    status = true;
  }
  return status;
  }


  @Override
  public void end(boolean interrupted) {
    m_drivetrain.drive(0.0, 0.0);
  }


} 