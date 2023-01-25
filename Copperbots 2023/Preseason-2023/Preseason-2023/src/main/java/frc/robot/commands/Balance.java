package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;


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

    error = 0 - currentAngle;
    drivePower = Math.min(0.020 * error, 1);
    
       
    /*if (drivePower < 0) {
      drivePower *= 1.35;
    }*/

    // Limit the max power
    if (Math.abs(drivePower) > 0.4) {
      drivePower = Math.copySign(0.4, drivePower);
    }

    m_drivetrain.drive(0, drivePower);
  
    SmartDashboard.putNumber("Current Angle: ", currentAngle);
    SmartDashboard.putNumber("Error ", error);
    SmartDashboard.putNumber("Drive Power: ", drivePower);
  }

  @Override
  public boolean isFinished(){
    return Math.abs(error) < .5;
  }


  @Override
  public void end(boolean interrupted) {
   
    m_drivetrain.drive(0.0, 0.0);
  }

  }

