// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import frc.robot.commands.AlignWithPoles;
import frc.robot.commands.ArmExtend;
import frc.robot.commands.ArmToIndex;
import frc.robot.commands.ArmToSecondNode;
import frc.robot.commands.ArmToThirdNode;
import frc.robot.commands.Balance;
import frc.robot.commands.BottomIntakeGoBrrrrrrr;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TopRoller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Hand;
import frc.robot.subsystems.Intake;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.DriveUpRamp;
import frc.robot.commands.DriveUpRampBackwards;
import frc.robot.commands.HandClose;
import frc.robot.commands.HandControl;
import frc.robot.commands.HandOpen;
import frc.robot.commands.KeepHandClosed;
import frc.robot.commands.RunIndex;
import frc.robot.commands.TopIntakeGoBrrrrrrr;
import frc.robot.commands.TurnOnField;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.BottomRoller;
import frc.robot.subsystems.Index;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  

  private final Joystick m_driver = new Joystick(0); //port:0 is driver's controller
  private final Joystick m_operator = new Joystick(1); //port:1 is operator's controller

  // The robot's subsystems are defined here...
  //creates object of subsystem to be used for button commands
  private final LimelightSubsystem m_limelight = new LimelightSubsystem(); 
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Arm m_arm = new Arm();
  private final Intake m_intake = new Intake();
  private final TopRoller m_topRoller = new TopRoller();
  private final BottomRoller m_bottomRoller = new BottomRoller();
  private final Hand m_hand = new Hand();
  private final Index m_index = new Index();
  private final SendableChooser<String> m_autoChooser = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // sets the drivetrain default to be controlled by the axis of the driver controller
    m_drivetrain.setDefaultCommand(new DriveCommand(
      m_drivetrain,
      () -> -modifyAxis(m_driver.getRawAxis(1)) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND,
      () -> -modifyAxis(m_driver.getRawAxis(0)) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND,
      () -> -modifyAxis(-m_driver.getRawAxis(2)) * Drivetrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND,
      true
    ));

    // sets the hand to be controlled by the operator joystick
  m_hand.setDefaultCommand(new HandControl(m_hand, ()->m_operator.getRawAxis(1)));


    // Configure the trigger bindings
    configureBindings();

    SmartDashboard.putData("Autonomous Chooser", m_autoChooser);

    m_autoChooser.addOption("Just Balance", "Just Balance");
    m_autoChooser.addOption("Place Object high", "Place Object high");
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

    // sets the driver controller square button to the command AlignWithPoles
    //new JoystickButton(m_driver, 1).onTrue(new AlignWithPoles(m_drivetrain, m_limelight));
 
    // sets the driver controller X button to the command Balance Delete?
    //new JoystickButton(m_driver, 2).onTrue(new Balance(m_drivetrain));

    // sets the driver controller circle button to the command DriveUpRamp Delete?
    //new JoystickButton(m_driver, 3).onTrue(new DriveUpRamp(m_drivetrain));

    // sets the driver controller triangle button to the command DriveUpRampBackwards Delete?
   // new JoystickButton(m_driver, 4).onTrue(new DriveUpRampBackwards(m_drivetrain));

     // sets the driver controller left bumper button to the commands DriveUpRamp and Balance Delete?
   // new JoystickButton(m_driver, 5).onTrue(new SequentialCommandGroup(new DriveUpRamp(m_drivetrain), new Balance(m_drivetrain)));
  
    // sets the driver controller options button to the command resetGyro
    new JoystickButton(m_driver, 10).onTrue(new InstantCommand(m_drivetrain::resetGyro));

    // sets the driver controller center pad to the command TurnOnField  
   // new JoystickButton(m_driver, 14).onTrue(new TurnOnField(m_drivetrain));

    //Operator Button Bindings

    // sets the operator controller square button to the command ArmToInDex
     new JoystickButton(m_operator, 1).onTrue(new ArmToIndex(m_arm));
   
    // sets the operator controller X button to the command ArmToSecondNode
     new JoystickButton(m_operator, 2).onTrue(new ArmToSecondNode(m_arm));

    // sets the operator circle square button to the command ArmToThirdNode
    new JoystickButton(m_operator, 3).onTrue(new ArmToThirdNode(m_arm));

    // sets the first left bumper to the command HandClose
    new JoystickButton(m_operator, 5).onTrue(new SequentialCommandGroup(new HandClose(m_hand), new KeepHandClosed(m_hand)));

    // sets the first left bumper to the command HandOpen 
    new JoystickButton(m_operator, 6).onTrue(new HandOpen(m_hand));

    // sets the operator controller second left bumper to the command TopIntakeGoBrrrrrrr and BottomINtakeGoBrrrrrrr
    new JoystickButton(m_operator, 7).whileTrue(new ParallelCommandGroup(new TopIntakeGoBrrrrrrr(m_topRoller), new BottomIntakeGoBrrrrrrr(m_bottomRoller)));

    // sets the operator controller second right bumper to the command TopIntakeGoBrrrrrrr
    new JoystickButton(m_operator, 8).whileTrue(new TopIntakeGoBrrrrrrr(m_topRoller));

    // sets the operator controller Share button to the RunIndex Command
    new JoystickButton(m_operator, 9).onTrue(new RunIndex(m_index));

    // sets the operator controller center pad to the command togglePiston on intake
    new JoystickButton(m_operator, 13).onTrue(new InstantCommand(m_intake::togglePiston));

    // sets the operator controller center pad to the command togglePiston on arm
    new JoystickButton(m_operator, 14).onTrue(new InstantCommand(m_arm::togglePiston));


  }

  public Command getAutonomousCommand() {

    String chosenAuto = m_autoChooser.getSelected();

    SmartDashboard.putString("Chosen Auto", chosenAuto);
    
    switch(chosenAuto) {
      case "Just Balance":
      return new SequentialCommandGroup(
      new DriveUpRamp(m_drivetrain), 
      new Balance(m_drivetrain)
      );
      case "Place Object high":
      return new SequentialCommandGroup(
      new HandClose(m_hand),
      new ParallelCommandGroup( new KeepHandClosed(m_hand),
      new SequentialCommandGroup( new ArmToThirdNode(m_arm),
      new ArmExtend(m_arm),
      new HandOpen(m_hand))),
      new HandClose(m_hand),
      new ArmExtend(m_arm),
      new ArmToIndex(m_arm)
      );
      default:
       return new SequentialCommandGroup(
        new HandClose(m_hand),
        new ParallelCommandGroup( new KeepHandClosed(m_hand),
        new SequentialCommandGroup( new ArmToThirdNode(m_arm),
        new ArmExtend(m_arm),
        new HandOpen(m_hand))),
        new HandClose(m_hand),
        new ArmExtend(m_arm),
        new ArmToIndex(m_arm)
        );
    }
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