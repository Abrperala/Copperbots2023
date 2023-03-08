package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveForward extends CommandBase{
    private final Drivetrain m_drivetrain;

    public DriveForward(Drivetrain drivetrain) {
      this.m_drivetrain = drivetrain;
      
      addRequirements(m_drivetrain);
  }
  @Override
  public void initialize(){
  
  }
  
  @Override
  public void execute() { 

    m_drivetrain.drive(1,0);
   
    
  }
  
  
  @Override
  public boolean isFinished(){
    
  
  return true;
  }
  
  
  @Override
  public void end(boolean interrupted) {
     //m_drivetrain.drive(0.0, 0.0);
   
  }
  
  }
  
  
    

