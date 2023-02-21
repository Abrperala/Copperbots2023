// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import frc.robot.commands.AlignWithPoles;
import frc.robot.commands.ArmToIndex;
import frc.robot.commands.ArmToPlayerStation;
import frc.robot.commands.ArmToSecondNode;
import frc.robot.commands.ArmToThirdNode;
import frc.robot.commands.Balance;
import frc.robot.commands.BottomIntakeGoBrrrrrrr;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TopRoller;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Hand;
import frc.robot.subsystems.Intake;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.DriveUpRamp;
import frc.robot.commands.DriveUpRampBackwards;
import frc.robot.commands.HandControl;
import frc.robot.commands.TopIntakeGoBrrrrrrr;
import frc.robot.commands.TurnOnField;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.BottomRoller;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  

  private final Joystick m_driver = new Joystick(0);
  private final Joystick m_operator = new Joystick(1);
  // The robot's subsystems and commands are defined here...
  private final LimelightSubsystem m_limelight = new LimelightSubsystem();
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Arm m_arm = new Arm();
  private final Intake m_intake = new Intake();
  private final TopRoller m_topRoller = new TopRoller();
  private final BottomRoller m_bottomRoller = new BottomRoller();
  private final Hand m_hand = new Hand();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    m_drivetrain.setDefaultCommand(new DriveCommand(
      m_drivetrain,
      () -> -modifyAxis(m_driver.getRawAxis(1)) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND,
      () -> -modifyAxis(m_driver.getRawAxis(0)) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND,
      () -> -modifyAxis(m_driver.getRawAxis(2)) * Drivetrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND,
      true
));

  m_hand.setDefaultCommand(new HandControl(m_hand, ()->m_operator.getRawAxis(1)));

    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    
    //Driver Button Bindings
    new JoystickButton(m_driver, 1).onTrue(new AlignWithPoles(m_drivetrain, m_limelight));
 
    new JoystickButton(m_driver, 2).onTrue(new Balance(m_drivetrain));

    new JoystickButton(m_driver, 3).onTrue(new DriveUpRamp(m_drivetrain));

    new JoystickButton(m_driver, 4).onTrue(new DriveUpRampBackwards(m_drivetrain));

    new JoystickButton(m_driver, 5).onTrue(new SequentialCommandGroup(new DriveUpRamp(m_drivetrain), new Balance(m_drivetrain)));
  
    new JoystickButton(m_driver, 10).onTrue(new InstantCommand(m_drivetrain::resetGyro));

    new JoystickButton(m_driver, 14).onTrue(new TurnOnField(m_drivetrain));

    //Operator Button Bindings
    new JoystickButton(m_operator, 14).onTrue(new InstantCommand(m_arm::togglePiston));

    new JoystickButton(m_operator, 6).whileTrue(new TopIntakeGoBrrrrrrr(m_topRoller));

    new JoystickButton(m_operator, 5).whileTrue(new ParallelCommandGroup(new TopIntakeGoBrrrrrrr(m_topRoller), new BottomIntakeGoBrrrrrrr(m_bottomRoller)));
 
    new JoystickButton(m_operator, 1).onTrue(new ArmToIndex(m_arm));
   
    new JoystickButton(m_operator, 2).onTrue(new ArmToSecondNode(m_arm));

    new JoystickButton(m_operator, 3).onTrue(new ArmToThirdNode(m_arm));

    new JoystickButton(m_operator, 4).onTrue(new ArmToPlayerStation(m_arm));

  }

  


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  private static double deadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }

  private static double modifyAxis(double value) {
    // Deadband
    value = deadband(value, 0.055);

    // Square the axis
  //  value = Math.copySign(value * value, value);

    return value;
  }
  }


