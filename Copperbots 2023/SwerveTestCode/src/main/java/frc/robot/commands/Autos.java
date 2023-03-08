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


public final class Autos {
 

  
  public static CommandBase none(){
    return Commands.none();
  }
  
  public static CommandBase JustBalance(){
   
    return new SequentialCommandGroup(
    new DriveUpRamp(RobotContainer.m_drivetrain), 
    new Balance(RobotContainer.m_drivetrain));

    }
  
    public static CommandBase LeaveCommunity(){
      return new SequentialCommandGroup(
      new ArmToThirdNode(RobotContainer.m_arm),
      new ArmToIndex(RobotContainer.m_arm),
      new WaitCommand(5),
      new DriveBackwards(RobotContainer.m_drivetrain)
      );
    }
  /**public static CommandBase PlaceCube(){
   return new SequentialCommandGroup(
    new HandClose(RobotContainer.m_hand),
    new ParallelCommandGroup(
      new KeepHandClosed(RobotContainer.m_hand),
      new ArmToSecondNode(RobotContainer.m_arm),
      new SequentialCommandGroup(
        new WaitCommand(1),
        new ParallelCommandGroup(
          new DriveForwardSlowly(RobotContainer.m_drivetrain),
          new SequentialCommandGroup(
            new WaitCommand(1),
            new HandOpen(RobotContainer.m_hand),
            new WaitCommand(.5),
            new HandClose(RobotContainer.m_hand),
            new ParallelCommandGroup(
              new DriveBackwards(RobotContainer.m_drivetrain),
                new SequentialCommandGroup(
                new WaitCommand(1),
                new ArmToIndex(RobotContainer.m_arm)
        
   )))))));
  }**/
  
  
  
  
  
  
  
  
  
  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
