package frc.robot.commands.AutoCommands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;


/**
 * Command to make the tankdrive robot automatically balance on the charge station, 
 */
public class Balance extends CommandBase {
  
private double error;
private double currentAngle;
private double drivePower;

private final Drivetrain m_drivetrain;
private ChassisSpeeds m_targetSpeeds;

public Balance(Drivetrain drivetrain) {
this.m_drivetrain = drivetrain;
addRequirements(m_drivetrain);
}

  @Override
  public void initialize(){
   m_targetSpeeds = new ChassisSpeeds();
  }

  @Override
  public void execute() { 
    this.currentAngle = m_drivetrain.getRoll();
    /**
     * finds the error of the gyro from the target angle (0)
     */
    error = 0 - currentAngle;
    /**
     * makes the drive power that goes to the motor 0.020 (P) times the error, the number is so small because the error is in degrees and the motors want a percent (I think?)
     */
    drivePower = Math.min(0.022 * error, 1);
    
 
    // Limit the max power of the wheels
    if (Math.abs(drivePower) > 0.4) {
      drivePower = Math.copySign(0.5, drivePower);
    }

    m_targetSpeeds = new ChassisSpeeds(
      drivePower, 
      0.0,
      0.0
    );
    m_drivetrain.driveFromSpeeds(m_targetSpeeds, false);
  }
  

  /**
   * stops the command when the error of the gyro is less than .5
   */
  @Override
  public boolean isFinished(){
    return Math.abs(error) > 90;
  }


  @Override
  public void end(boolean interrupted) {
    m_drivetrain.driveFromSpeeds(new ChassisSpeeds(), false);
  }

  }