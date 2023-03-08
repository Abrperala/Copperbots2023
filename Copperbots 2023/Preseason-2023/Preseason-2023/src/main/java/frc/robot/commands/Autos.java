// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;

public final class Autos {
  /**  Example static factory for an autonomous command. 
  public static CommandBase exampleAuto(ExampleSubsystem subsystem) {
    return Commands.sequence(subsystem.exampleMethodCommand(), new ExampleCommand(subsystem));
  }

   */
  public static CommandBase noneAuto() {
    return Commands.none();
  }

  public static CommandBase driveAndStop(){
    return new SequentialCommandGroup(
    new WaitCommand(2),
    new DriveForward(RobotContainer.m_drivetrain),
    new WaitCommand(2),
    new DriveStop(RobotContainer.m_drivetrain),
    new WaitCommand(2),
    new DriveForward(RobotContainer.m_drivetrain),
    new WaitCommand(10000) 
    );

  }
}
