package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.LimelightSubsystem;


/**
 * Command to have our swerve robot use the to come in line with the cone nodes
 */
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
    //configures the limelight so it uses the aiming port
    m_limelight.limelightAimConfig();
    m_targetSpeeds = new ChassisSpeeds();
  }

  @Override
  public void execute() {
    //configures the limelight so it uses the aiming port
    m_limelight.limelightAimConfig();
    
    /**
     * says that if the robot is in position that it should no drive, 
     * kinda redundant because the robot should stop the command if it is in position, backup plan I guess 
     */
    if(m_limelight.inPositionX()) {
    m_targetSpeeds = new ChassisSpeeds(
      0.0 * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND, 
      0.0,
      0.0
    );
    m_drivetrain.driveFromSpeeds(m_targetSpeeds, false);
     }
       /**
       * drives the robot towards the target, limits max theoretical speed down to  0.4 (only uses 40%), 
       * the percent error should never be more than one theoreticaly
       */
     else { 
      m_targetSpeeds = new ChassisSpeeds(
       -m_limelight.getPercentErrorX() * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND * 0.4, 
        0.0,
        0.0
      );
      m_drivetrain.driveFromSpeeds(m_targetSpeeds, false);
     }
  }

  /**
   * if the robot has reached the target (infront of the target cone node)
   */
  @Override
  public boolean isFinished() {
   if (m_limelight.inPositionX()) {
    return true;
   }
   else {
    return false;
   }
  }


  /**
   * if the command becomes interrupted, the robot goes back to regular drive
   */
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.driveFromSpeeds(new ChassisSpeeds(), false);
  }
}

 