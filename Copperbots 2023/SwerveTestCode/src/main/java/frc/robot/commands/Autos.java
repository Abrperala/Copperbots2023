// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import java.util.HashMap;
import java.util.Map;

public final class Autos {
 
/**private static final Map<String, Command> eventMap = new Hashmap<>(Map.ofEntries(
  Map.entry("JustBalance", new SequentialCommandGroup(
    new DriveUpRamp(RobotContainer.m_drivetrain), 
    new Balance(RobotContainer.m_drivetrain)
    ))


)); */
  
  public static CommandBase none(){
    return Commands.none();
  }
  
  public static CommandBase JustBalance(){
   
    return new SequentialCommandGroup(
    new DriveUpRamp(RobotContainer.m_drivetrain), 
    new Balance(RobotContainer.m_drivetrain));

    }
  
  public static CommandBase PlaceCube(){
   return new SequentialCommandGroup(
    new HandClose(RobotContainer.m_hand),
    new ArmToSecondNode(RobotContainer.m_arm),
    new HandOpen(RobotContainer.m_hand), 
    new WaitCommand(1),
    new HandClose(RobotContainer.m_hand),
    new ArmToIndex(RobotContainer.m_arm)
   );
  }
  
  
  
  
  
  
  
  
  
  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
