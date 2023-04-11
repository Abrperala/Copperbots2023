package frc.robot.commands.AutoCommands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveForwardSlowly extends CommandBase {
  
  private final Drivetrain m_drivetrain;
  private ChassisSpeeds m_targetSpeeds;
  
  public DriveForwardSlowly(Drivetrain drivetrain) {
  this.m_drivetrain = drivetrain;
  addRequirements(m_drivetrain);
  }
  
    @Override
    public void initialize(){
     m_targetSpeeds = new ChassisSpeeds();
    }
  
    @Override
    public void execute() { 
      
       m_targetSpeeds = new ChassisSpeeds(
        .1 * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND, 
        0.0,
        0.0
      );
      m_drivetrain.driveFromSpeeds(m_targetSpeeds, false);
    }
    
  
    @Override
    public boolean isFinished(){
        return false;
    }
  
  
    @Override
    public void end(boolean interrupted) {
      
    }
  

}
