// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.math.util.Units;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;

public class Drivetrain extends SubsystemBase {

  private final AHRS navx = new AHRS(SPI.Port.kMXP);

  // Declares and initializes the Sparkmax Motor Controllers
  final CANSparkMax left1 = new CANSparkMax(4, MotorType.kBrushless);
  final CANSparkMax left2 = new CANSparkMax(3, MotorType.kBrushless);
  final CANSparkMax right1 = new CANSparkMax(2, MotorType.kBrushless);
  final CANSparkMax right2 = new CANSparkMax(1, MotorType.kBrushless);

  // Declares and initializes the Motor Controller Groups
  final MotorControllerGroup leftMotor = new MotorControllerGroup(left1, left2);
  final MotorControllerGroup rightMotor = new MotorControllerGroup(right1, right2);
  
  // Declares and initializes the Encoders
  // Parameters are Roborio port 1, Roborio port 2, invert direction, and Encoding Rate
  private final Encoder rightEncoder = new Encoder(2, 3, false, CounterBase.EncodingType.k4X);
  private final Encoder leftEncoder = new Encoder(0, 1, true, CounterBase.EncodingType.k4X); 

  // Declares and initializes the Differential Drive 
  final DifferentialDrive drive = new DifferentialDrive(leftMotor, rightMotor);

  public Drivetrain() {
  navx.reset();
  // sets the encoders to measure in feet (one encoder unit = 1 foot) (I think math works)
  rightEncoder.setDistancePerPulse( (2.0 * Math.PI / 4096.0) * (12 / ( Math.PI * 7.75)));
  leftEncoder.setDistancePerPulse( (2.0 * Math.PI / 4096.0) * (12 / ( Math.PI * 7.75)));
  }
/**
 * Methods to utilize the NavX 
 */
  public void calibrateGyro() {
    navx.calibrate();
  }
  public void zeroGyro() {
    System.out.println("NavX Connected: " + navx.isConnected());
    navx.reset();
  }
  public double getYaw() {
    return navx.getYaw();
  }
  public double getPitch() {
    return navx.getPitch();
  }
  public double getRoll() {
    return navx.getRoll();
  }
  public double getAngle() {
    return navx.getAngle();
  }

  public void resetEncoders(){
    leftEncoder.reset();
    rightEncoder.reset();
  }
  public double getRightEncoderDistance(){
   return rightEncoder.getDistance();
  }
  public double getLeftEncoderDistance(){
    return leftEncoder.getDistance();
   }
  // Creates the drive method makes the robot drive, change arcade to tank to change drive type
  public void drive(double left, double right) {
      drive.arcadeDrive(left, right);

  }

  @Override
  public void periodic(){
    SmartDashboard.putNumber("Pitch: ", getPitch());
    SmartDashboard.putNumber("Roll ", getRoll());
    SmartDashboard.putNumber("Yaw: ", getYaw());
    SmartDashboard.putNumber("left encoder: ", leftEncoder.getDistance());
    SmartDashboard.putNumber("right encoder ", rightEncoder.getDistance());
  }


}