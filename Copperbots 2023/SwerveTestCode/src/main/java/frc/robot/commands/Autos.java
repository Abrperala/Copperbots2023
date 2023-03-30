// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Commands;

import java.util.HashMap;
import java.util.Map;

import com.pathplanner.lib.auto.PIDConstants;
import com.pathplanner.lib.auto.SwerveAutoBuilder;
import com.pathplanner.lib.commands.FollowPathWithEvents;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;

import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.commands.*;
import frc.robot.commands.AutoCommands.ArmUpWithStop;
import frc.robot.commands.AutoCommands.AutonArmToHigh;
import frc.robot.commands.AutoCommands.Balance;
import frc.robot.commands.AutoCommands.DriveBackwards;
import frc.robot.commands.AutoCommands.DriveForwardSlowly;
import frc.robot.commands.AutoCommands.DriveOverRamp;
import frc.robot.commands.AutoCommands.DriveOverRampBackwards;
import frc.robot.commands.AutoCommands.DriveUpRamp;
import frc.robot.commands.AutoCommands.DriveUpRampBackwards;
import frc.robot.commands.AutoCommands.EjectIntake;
import frc.robot.commands.AutoCommands.StopDrive;
import frc.robot.commands.AutoCommands.StopIntake;
import frc.robot.commands.ButtonCommands.ArmToIndex;
import frc.robot.commands.ButtonCommands.ArmToSecondNode;
import frc.robot.commands.ButtonCommands.ArmToThirdNode;
import frc.robot.commands.ButtonCommands.TopIntakeGoBrrrrrrrBackwards;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;

public final class Autos {
  public static final PIDConstants AUTO_TRANSLATION_CONSTANTS = new PIDConstants(0.01, 0, 0);
  public static final PIDConstants AUTO_ROTATION_CONSTANTS = new PIDConstants(2.8, 0, 0);
  private static final Map<String, Command> eventMap = new HashMap<>(Map.ofEntries(
    
  Map.entry("Start", new SequentialCommandGroup(
    new InstantCommand((RobotContainer.m_hand::extend)),
    new AutonArmToHigh(RobotContainer.m_arm)
    )),

  Map.entry("1stCube", new SequentialCommandGroup(
    new InstantCommand((RobotContainer.m_arm::extend)),
    new InstantCommand((RobotContainer.m_hand::retract)),
    new WaitCommand(.2),
    new InstantCommand((RobotContainer.m_arm::retract))
    ))

  

    ));



    private static final SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
    RobotContainer.m_drivetrain::getPose,
    RobotContainer.m_drivetrain::resetOdometry,
    AUTO_TRANSLATION_CONSTANTS,
    AUTO_ROTATION_CONSTANTS, 
    RobotContainer.m_drivetrain::setChassisSpeeds,
    eventMap,
    true,
    RobotContainer.m_drivetrain
  );

  public static CommandBase none(){
    return Commands.none();
  }
  

  public static CommandBase ScoreLow(){
   return new SequentialCommandGroup(
    new EjectIntake(RobotContainer.m_topRoller),
    new WaitCommand(.5),
    new StopIntake(RobotContainer.m_topRoller));
  }

  public static CommandBase JustBalance(){
    return new SequentialCommandGroup(
    new DriveUpRamp(RobotContainer.m_drivetrain), 
    new Balance(RobotContainer.m_drivetrain));

    }
  
    public static CommandBase TaxiAndBalance(){
    return new SequentialCommandGroup(
      new DriveUpRamp(RobotContainer.m_drivetrain),
      new DriveOverRamp(RobotContainer.m_drivetrain),
      new WaitCommand(0.2),
      new DriveOverRamp(RobotContainer.m_drivetrain),
      new DriveUpRampBackwards(RobotContainer.m_drivetrain),
      new Balance(RobotContainer.m_drivetrain)
      );
    }

    
  public static CommandBase ScoreLowAndBalance(){
    return new SequentialCommandGroup(
      new EjectIntake(RobotContainer.m_topRoller),
      new WaitCommand(.5),
      new StopIntake(RobotContainer.m_topRoller),
      new DriveUpRamp(RobotContainer.m_drivetrain), 
      new Balance(RobotContainer.m_drivetrain));

  }
  
  public static CommandBase ScoreMiddle(){
    return new SequentialCommandGroup(
      new InstantCommand((RobotContainer.m_hand::extend)),
      new AutonArmToHigh(RobotContainer.m_arm),
      new InstantCommand((RobotContainer.m_arm::extend)),
      new DriveForwardSlowly(RobotContainer.m_drivetrain),
      new WaitCommand(1),
      new StopDrive(RobotContainer.m_drivetrain),
      new InstantCommand((RobotContainer.m_hand::retract)),
      new WaitCommand(.2),
      new InstantCommand((RobotContainer.m_arm::retract)),
      new WaitCommand(.5),
      new DriveBackwards(RobotContainer.m_drivetrain),
      new WaitCommand(1),
      new StopDrive(RobotContainer.m_drivetrain),
      new ArmToIndex(RobotContainer.m_arm)
    );

  }
  
  
  
  
  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
