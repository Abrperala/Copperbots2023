package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.LimelightSubsystem;


public class AlignWithPoles extends CommandBase {
  
  private final Drivetrain m_drivetrain;
  private final LimelightSubsystem m_limelight;
  private ChassisSpeeds m_targetSpeeds;


  public AlignWithPoles(Drivetrain drivetrain, LimelightSubsystem limelight) {
    this.m_drivetrain = drivetrain;
    this.m_limelight = limelight;
    
    addRequirements(m_drivetrain);
  }

  @Override
  public void initialize() {
    m_limelight.limelightAimConfig();
    m_targetSpeeds = new ChassisSpeeds();
  }

  @Override
  public void execute() {
    m_limelight.limelightAimConfig();
    
    if(m_limelight.inPositionX()) {
    m_targetSpeeds = new ChassisSpeeds(
      0.0 * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND, 
      0.0,
      0.0
    );
    m_drivetrain.driveFromSpeeds(m_targetSpeeds, false);
     }
     else { 
      m_targetSpeeds = new ChassisSpeeds(
       -m_limelight.getPercentErrorX() * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND * 0.4, 
        0.0,
        0.0
      );
      m_drivetrain.driveFromSpeeds(m_targetSpeeds, false);
     }
  }

  @Override
  public boolean isFinished() {
   if (m_limelight.inPositionX()) {
    return true;
   }
   else {
    return false;
   }
  }

  @Override
  public void end(boolean interrupted) {
    m_drivetrain.driveFromSpeeds(new ChassisSpeeds(), false);
  }
}

 