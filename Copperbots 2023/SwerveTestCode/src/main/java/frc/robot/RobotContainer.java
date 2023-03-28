// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.AlignWithPoles;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TopRoller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Hand;
import frc.robot.subsystems.Intake;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.TurnOnField;
import frc.robot.commands.AutoCommands.RunIndexWithBeamBreak;
import frc.robot.commands.ButtonCommands.ArmToIndex;
import frc.robot.commands.ButtonCommands.ArmToPlayerStation;
import frc.robot.commands.ButtonCommands.ArmToSecondNode;
import frc.robot.commands.ButtonCommands.ArmToThirdNode;
import frc.robot.commands.ButtonCommands.BottomIntakeGoBrrrrrrr;
import frc.robot.commands.ButtonCommands.BottomIntakeGoBrrrrrrrBackwards;
import frc.robot.commands.ButtonCommands.HandTwist;
import frc.robot.commands.ButtonCommands.HandTwistBack;
import frc.robot.commands.ButtonCommands.IndexReverse;
import frc.robot.commands.ButtonCommands.RunIndex;
import frc.robot.commands.ButtonCommands.TopIntakeGoBrrrrrrr;
import frc.robot.commands.ButtonCommands.TopIntakeGoBrrrrrrrBackwards;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.BottomRoller;
import frc.robot.subsystems.Index;
import frc.robot.commands.Autos;
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
  public static final Drivetrain m_drivetrain = new Drivetrain();
  public static final Arm m_arm = new Arm();
  public static final Intake m_intake = new Intake();
  public static final TopRoller m_topRoller = new TopRoller();
  public static final BottomRoller m_bottomRoller = new BottomRoller();
  public static final Hand m_hand = new Hand();
  public static final Index m_index = new Index();
  private static final SendableChooser<CommandBase> m_autoChooser = new SendableChooser<>();

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


    // Configure the trigger bindings
    configureBindings();

    m_autoChooser.setDefaultOption("None", Autos.none());
    m_autoChooser.addOption("JustBalance", Autos.JustBalance());
    m_autoChooser.addOption("Taxi and Balance", Autos.TaxiAndBalance());
    m_autoChooser.addOption("Score Low and Balance", Autos.ScoreLowAndBalance());
    m_autoChooser.addOption("Score High", Autos.ScoreMiddle());
    m_autoChooser.addOption("Just Score Low", Autos.ScoreLow());

    SmartDashboard.putData("Auto mode", m_autoChooser);
    SmartDashboard.putData("Chosen Auto",  m_autoChooser.getSelected());
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
  
    // sets the driver controller options button to reset the drive gyro
    new JoystickButton(m_driver, 10).onTrue(new InstantCommand(m_drivetrain::resetGyro));

    // sets the driver controller center pad to the command TurnOnField(to facing the scoring area)  
    new JoystickButton(m_driver, 14).onTrue(new TurnOnField(m_drivetrain));

    //Operator Button Bindings

    // sets the operator controller square button to the command ArmToInDex
    new JoystickButton(m_operator, 1).onTrue(new SequentialCommandGroup(new InstantCommand(m_arm::retract), new WaitCommand(1), new ArmToIndex(m_arm)));
   
    // sets the operator controller X button to the command ArmToSecondNode
    new JoystickButton(m_operator, 2).onTrue(new ArmToSecondNode(m_arm));

    // sets the operator circle square button to the command ArmToThirdNode
    new JoystickButton(m_operator, 3).onTrue(new ArmToThirdNode(m_arm));


    new JoystickButton(m_operator, 4).onTrue(new ArmToPlayerStation(m_arm));
    
    // sets the first left bumper to the command HandClose
    new JoystickButton(m_operator, 5).onTrue(new InstantCommand(m_hand::retract));

    // sets the first left bumper to the command HandOpen 
    new JoystickButton(m_operator, 6).onTrue(new InstantCommand((m_hand::extend)));

    // sets the operator controller second left bumper to the command TopIntakeGoBrrrrrrr and BottomINtakeGoBrrrrrrr
    new JoystickButton(m_operator, 7).whileTrue(new ParallelCommandGroup(new TopIntakeGoBrrrrrrrBackwards(m_topRoller), new BottomIntakeGoBrrrrrrrBackwards(m_bottomRoller)));

    // sets the operator controller second right bumper to the command TopIntakeGoBrrrrrrr
    new JoystickButton(m_operator, 8).whileTrue(new ParallelCommandGroup(new TopIntakeGoBrrrrrrr(m_topRoller), new BottomIntakeGoBrrrrrrr(m_bottomRoller)));

    // sets the operator controller Share button to the RunIndex Command
    new JoystickButton(m_operator, 9).whileTrue(new IndexReverse(m_index));

    // sets the operator controller options button to the IndexReverse Command
    new JoystickButton(m_operator, 10).whileTrue(new RunIndex(m_index));

    new JoystickButton(m_operator, 11).onTrue(new HandTwist(m_hand));

    new JoystickButton(m_operator, 12).onTrue(new HandTwistBack(m_hand));

    // sets the operator controller ps4 button  to the command togglePiston on intake
    new JoystickButton(m_operator, 13).onTrue(new InstantCommand(m_intake::togglePiston));

     //sets the operator controller center pad to the command togglePiston on arm
    new JoystickButton(m_operator, 14).onTrue(new InstantCommand(m_arm::togglePiston));

  }


   /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  public Command getAutonomousCommand() {

    SmartDashboard.putData("Chosen Auto when on Autonomous Command",  m_autoChooser.getSelected());
    return m_autoChooser.getSelected();
  }
  
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
    // value = Math.copySign(value * value, value);

    return value;
  }
}