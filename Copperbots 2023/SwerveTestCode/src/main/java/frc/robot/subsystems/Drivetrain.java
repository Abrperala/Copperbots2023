// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.swervedrivespecialties.swervelib.SdsModuleConfigurations;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.DriveConstants.*;
import edu.wpi.first.math.MathUtil;

/** Represents a swerve drive style drivetrain. */
public class Drivetrain extends SubsystemBase { 

  public static final double MAX_VELOCITY_METERS_PER_SECOND = 6380.0 / 60.0 *
  SdsModuleConfigurations.MK4I_L2.getDriveReduction() *
  SdsModuleConfigurations.MK4I_L2.getWheelDiameter() * Math.PI * 1.2;

  public static final double MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND = MAX_VELOCITY_METERS_PER_SECOND /
          Math.hypot(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0) * 0.9;

  private final SwerveModule m_frontLeft = new SwerveModule(FRONT_LEFT_MODULE_DRIVE_MOTOR, FRONT_LEFT_MODULE_STEER_MOTOR,
    FRONT_LEFT_MODULE_STEER_ENCODER, false, 1.993, 0.332, 2.200, 0.304, -79.87, 4.599, 0.01, 0.535, 0.228, 0.006); //kP is +.3 kS is +0.2

  private final SwerveModule m_frontRight = new SwerveModule(FRONT_RIGHT_MODULE_DRIVE_MOTOR, FRONT_RIGHT_MODULE_STEER_MOTOR, 
    FRONT_RIGHT_MODULE_STEER_ENCODER, false, 1.993, 0.332, 2.200, 0.304, -80.92, 4.732, 0.01, 0.500, 0.236, 0.007);

  private final SwerveModule m_backLeft = new SwerveModule(BACK_LEFT_MODULE_DRIVE_MOTOR, BACK_LEFT_MODULE_STEER_MOTOR, 
    BACK_LEFT_MODULE_STEER_ENCODER, false, 1.993, 0.332, 2.200, 0.304, -235.37, 4.954, 0.01, 0.511, 0.236, 0.007); //kP is +1

  private final SwerveModule m_backRight = new SwerveModule(BACK_RIGHT_MODULE_DRIVE_MOTOR, BACK_RIGHT_MODULE_STEER_MOTOR, 
    BACK_RIGHT_MODULE_STEER_ENCODER, true, 1.993, 0.332, 2.200, 0.304, -227.74, 4.900, 0.01, 0.584, 0.232, 0.007); //kP is +1

  // private final ADXRS450_Gyro m_gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
  private final AHRS m_gyro = new AHRS(SPI.Port.kMXP);

  public static final SwerveDriveKinematics m_kinematics =
      new SwerveDriveKinematics(
        new Translation2d(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0),
        new Translation2d(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -DRIVETRAIN_WHEELBASE_METERS / 2.0), 
        new Translation2d(-DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0),
        new Translation2d(-DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -DRIVETRAIN_WHEELBASE_METERS / 2.0));

  public final SwerveDriveOdometry m_odometry =
      new SwerveDriveOdometry(m_kinematics, robotRotation2d(), getModulePositions());

  // Class-wide desired chassis speeds
  private ChassisSpeeds m_chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

  public Drivetrain() {
    m_gyro.reset();
  }

  /**
   * Method to drive the robot using joystick info.
   *
   * @param xSpeed Speed of the robot in the x direction (forward).
   * @param ySpeed Speed of the robot in the y direction (sideways).
   * @param rot Angular rate of the robot.
   * @param fieldRelative Whether the provided x and y speeds are relative to the field.
   */
  @SuppressWarnings("ParameterName")
  public void drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
    if(fieldRelative) {
      m_chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rot, m_gyro.getRotation2d());
    } else {
      m_chassisSpeeds = new ChassisSpeeds(xSpeed, ySpeed, rot);
    }
  }

  public void driveFromSpeeds(ChassisSpeeds chassisSpeeds, boolean fieldRelative) {
    if(fieldRelative) {
      m_chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
        chassisSpeeds.vxMetersPerSecond, chassisSpeeds.vyMetersPerSecond, chassisSpeeds.omegaRadiansPerSecond, robotRotation2d());
    } else {
    m_chassisSpeeds = new ChassisSpeeds(chassisSpeeds.vxMetersPerSecond, chassisSpeeds.vyMetersPerSecond, -chassisSpeeds.omegaRadiansPerSecond);
    }
  }

  /** Updates the field relative position of the robot. */
  public void updateOdometry() {
    m_odometry.update(
        robotRotation2d(),
        getModulePositions());
  }  
  /** Sets the robot odometry to a given Pose2d */
  public void resetOdometry(Pose2d pose) {
    m_odometry.resetPosition(robotRotation2d(),getModulePositions(), pose);
  }

    /**
   * Returns the currently-estimated pose of the robot.
   * @return The pose.
   */
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }
/**
 * Returns the positions of each swerve module 
 * 
 * @return the positions of each swerve module 
 */
  public SwerveModulePosition[] getModulePositions() {
     SwerveModulePosition[] positions = {
      m_frontLeft.getPosition(),
      m_frontRight.getPosition(),
      m_backLeft.getPosition(),
      m_backRight.getPosition()
    };
    return positions;
  }

  /**
   * Control serve module output with proper homing behavior
   * @param states Desired module states when m_chassisSpeeds != (0,0,0)
   */
  private void commandModules(SwerveModuleState[] states) {
    if (m_chassisSpeeds.vxMetersPerSecond == 0.0 && m_chassisSpeeds.vyMetersPerSecond == 0.0 && m_chassisSpeeds.omegaRadiansPerSecond == 0.0) {
      m_frontLeft.setDesiredState(new SwerveModuleState(0.0, new Rotation2d(0.0)));
      m_frontRight.setDesiredState(new SwerveModuleState(0.0, new Rotation2d(0.0)));
      m_backLeft.setDesiredState(new SwerveModuleState(0.0, new Rotation2d(0.0)));
      m_backRight.setDesiredState(new SwerveModuleState(0.0, new Rotation2d(0.0)));
    } else {
      m_frontLeft.setDesiredState(states[0]);
      m_frontRight.setDesiredState(states[1]);
      m_backLeft.setDesiredState(states[2]);
      m_backRight.setDesiredState(states[3]);      
    }
  }

  public double getGyroAngle() {
    return -1.0 * m_gyro.getAngle();
  }

  public Rotation2d robotRotation2d() {
    return new Rotation2d(Math.toRadians(getGyroAngle()));
  }
  
  public void resetGyro() {
    m_gyro.reset();
  }

  public double getGyroPos() {
    return MathUtil.inputModulus(robotRotation2d().getDegrees(), 0, 360);
  }
  public double getYaw() {
    return m_gyro.getYaw();
  }
  public double getPitch() {
    return m_gyro.getPitch();
  }
  public double getRoll() {
    return m_gyro.getRoll();
  }
  public double getAngle() {
    return m_gyro.getAngle();
  }

  @Override
  public void periodic() {

    SwerveModuleState[] states = m_kinematics.toSwerveModuleStates(m_chassisSpeeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(states, MAX_VELOCITY_METERS_PER_SECOND);

    // toSwerveModuleStates() no longer sets target pos to 0 when inputted (0,0,0) lmao
    commandModules(states);

    // Update the odometry in the periodic block -- duh @me
    updateOdometry();

      SmartDashboard.putNumber("Front Left encoder", m_frontLeft.getTurnAngle() * (180/Math.PI));
      SmartDashboard.putNumber("Front Right encoder", m_frontRight.getTurnAngle() * (180/Math.PI));
      SmartDashboard.putNumber("Back Left encoder", m_backLeft.getTurnAngle() * (180/Math.PI));
      SmartDashboard.putNumber("Back Right encoder", m_backRight.getTurnAngle() * (180/Math.PI));
      SmartDashboard.putNumber("Gyro Angles", m_gyro.getAngle());
 
    }
}