package frc.robot.commands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;



public class TurnOnField extends CommandBase {

  private final Drivetrain m_drivetrain;
  private ChassisSpeeds m_targetSpeeds;

  private static final int TARGET_ANGLE = 90;

  public TurnOnField(Drivetrain drivetrain) {
    this.m_drivetrain = drivetrain;

    addRequirements(m_drivetrain);
  }

  @Override
  public void initialize() {
    m_targetSpeeds = new ChassisSpeeds();
  }

  @Override
  public void execute(){
    SmartDashboard.putNumber("angle error pos", getGyroError(90.0));
    
    m_targetSpeeds = new ChassisSpeeds(
      0.0, 
      0.0,
      getGyroErrorWithLimit(90.0) * Drivetrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND * 1
    );
    m_drivetrain.driveFromSpeeds(m_targetSpeeds, false);
  }

  @Override
  public boolean isFinished() {
    return Math.abs(getGyroError(90.0)) < 0.0005;
  }

  @Override
  public void end(boolean interrupted) {
    m_drivetrain.driveFromSpeeds(new ChassisSpeeds(), false);
  }

  /**
   * Get the percent angle error
   * @param targetAngle desired gyro angle
   * @return The percent error of the drivebase
   */
  public double getGyroError(double targetAngle) {
    double adjustedAngle = m_drivetrain.getGyroPos();
    SmartDashboard.putNumber("adjusted angle", adjustedAngle);
    if (adjustedAngle > 180) {
      return -(adjustedAngle - 360) / 360.0;
    } else {
      return -adjustedAngle / 360;
    }
  }
  
  public double getGyroErrorWithLimit(double targetAngle) {
   if (getGyroError(targetAngle) < 0){
    if (getGyroError(targetAngle) < -0.05){
      return getGyroError(targetAngle);
    }
    else {
      return -0.05;
    }
  }
  else{
    if (getGyroError(targetAngle) < 0.05)
    return 0.05;
    else {
      return getGyroError(targetAngle);
    }
  }
}

}