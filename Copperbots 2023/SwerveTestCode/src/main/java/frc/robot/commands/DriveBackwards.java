package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveBackwards extends CommandBase {
  
  private final Drivetrain m_drivetrain;
  private ChassisSpeeds m_targetSpeeds;
  
  public DriveBackwards(Drivetrain drivetrain) {
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
        -.2, 
        0.0,
        0.0
      );
      m_drivetrain.driveFromSpeeds(m_targetSpeeds, false);
    }
    
  
    /**
     * stops the command when the error of the gyro is greater than 12
     */
    @Override
    public boolean isFinished(){
        return false;
    }
  
  
    @Override
    public void end(boolean interrupted) {
      m_drivetrain.driveFromSpeeds(new ChassisSpeeds(), false);
    }
  

}
