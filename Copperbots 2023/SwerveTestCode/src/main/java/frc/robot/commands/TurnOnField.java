package frc.robot.commands;


import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;


/**
 * command for the robot to rotate to face the cone nodes
 * 
 */
public class TurnOnField extends CommandBase {

  private final Drivetrain m_drivetrain;
  private ChassisSpeeds m_targetSpeeds;

  public TurnOnField(Drivetrain drivetrain) {
    this.m_drivetrain = drivetrain;
    addRequirements(m_drivetrain);
  }

  @Override
  public void initialize() {
    m_targetSpeeds = new ChassisSpeeds();
  }

 /**
  * sets the rotational speed to the gyro error (Propertional P) 
  */
  @Override
  public void execute(){
    m_targetSpeeds = new ChassisSpeeds(
      0.0, 
      0.0,
      getGyroErrorWithLimit(0.0) * Drivetrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND 
    );
    m_drivetrain.driveFromSpeeds(m_targetSpeeds, false);
  }

  @Override
  /**
   * stops the command when the gyro error is within 0.05% 
   */
  public boolean isFinished() {
    return Math.abs(getGyroError(0.0)) < 0.0005;
  }
  /**
   * if the command becomes interrupted, the robot goes back to regular drive
   */
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
    double adjustedAngle = m_drivetrain.getGyroPos() - targetAngle;
    if (adjustedAngle > 180) {
      return -(adjustedAngle - 360) / 360.0;
    } else {
      return -adjustedAngle / 360;
    }
  }
  
  /**
   * Get the percent angle error but with limit at 5% and -5% for so the robot doesnt go to slow, might have to make into PID if I want to make it faster
   * @param targetAngle 
   * @return percent angle error of the drivebase but with a limit
   */
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