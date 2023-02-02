// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Balance;
import frc.robot.commands.GetOnChargeStation;
import frc.robot.commands.StickDrive;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Piston;
import frc.robot.Constants.*;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Declares and initializes the Drive Controller
  public final PS4Controller stick = new PS4Controller(0);
  

  // The robot's subsystems and are defined here...
  private final Drivetrain m_drivetrain = new Drivetrain();
  public final Piston m_piston = new Piston();
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    
    // Makes the default command the Stick Drive (Tells the robot that it should use the drive command)
    // Also sets the controls for Stick Drive to the Axis of the Joysticks
    m_drivetrain.setDefaultCommand(new StickDrive(m_drivetrain, ()->stick.getRawAxis(0) * -1 , ()-> stick.getRawAxis(5) * .70 ));
    
    
  
    // Configure the button bindings
    configureButtonBindings();
  }

 
    public void configureButtonBindings() {
      // holding down right bumper extends intake, letting go retracts intake
       new JoystickButton(stick, 6)
      .onTrue((Command) new InstantCommand(m_piston::extend))
      .onTrue((Command) new InstantCommand(m_piston::retract));
  
      //  left bumper toggles intake
      new JoystickButton(stick, 5)
      .onTrue(new InstantCommand(m_piston::togglePiston));
     
      // makes the Square button use the balance command 
      new JoystickButton(stick, ButtonBindings.SQUARE).onTrue(new Balance(m_drivetrain));
      //makes the X button reset the Gyro
      new JoystickButton(stick, ButtonBindings.X).onTrue((new InstantCommand(m_drivetrain::zeroGyro)));
      //makes the circle button drive the robot three wheel rotaations
      new JoystickButton(stick, ButtonBindings.CIRCLE).onTrue(new GetOnChargeStation(m_drivetrain));
     //makes the Triangle button drive the robot Three wheel rotations and balance
      new JoystickButton(stick, ButtonBindings.TRIANGLE).onTrue(new SequentialCommandGroup(new GetOnChargeStation(m_drivetrain), new Balance(m_drivetrain)));
    }

}