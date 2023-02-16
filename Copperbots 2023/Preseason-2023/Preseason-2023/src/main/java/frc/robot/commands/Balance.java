package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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


public Balance(Drivetrain drivetrain) {
this.m_drivetrain = drivetrain;

addRequirements(m_drivetrain);
}

  @Override
  public void initialize(){
    
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
    drivePower = Math.min(0.020 * error, 1);
 
 
    // Limit the max power of the wheels
    if (Math.abs(drivePower) > 0.4) {
      drivePower = Math.copySign(0.4, drivePower);
    }

    m_drivetrain.drive(0, drivePower);
     // Smartdashboard for
    SmartDashboard.putNumber("Current Angle: ", currentAngle);
    SmartDashboard.putNumber("Error ", error);
    SmartDashboard.putNumber("Drive Power: ", drivePower);
  }

  /**
   * stops the command when the error of the gyro is less than .5
   */
  @Override
  public boolean isFinished(){
    //return Math.abs(error) < .4;
    return false;
  }


  @Override
  public void end(boolean interrupted) {
    m_drivetrain.drive(0.0, 0.0);

  }

  }