// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

// Imports  DriveTrain Subsystem, DoubleSupplier Function, and The CommandBase
import frc.robot.subsystems.Drivetrain;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

// A StickDrive Command that uses the DriveTrain Subsystem. 
public class StickDrive extends CommandBase {
  
  // Declares names for the DriveTrain and DoubleSuppliers
  private final Drivetrain m_drivetrain;
  private final DoubleSupplier m_forward;
  private final DoubleSupplier m_rotation;

  /**
   * Creates a new StickDrive Command
   *
   * @param subsystem The subsystem used by this command.
   */
  public StickDrive(Drivetrain subsystem, DoubleSupplier forward, DoubleSupplier rotation) {
    m_drivetrain = subsystem;
    m_forward = forward;
    m_rotation = rotation;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { 
    // Calls upon drive method in DriveTrain Subsystem to drive our m_drivetrain 
    m_drivetrain.drive(m_forward.getAsDouble(), m_rotation.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   // when command ends it sets drive to 0
    m_drivetrain.drive(0.0, 0.0);
  }
  
}
