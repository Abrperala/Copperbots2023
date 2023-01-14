// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    // "I AM A GAMER!!!" -Lowell
    
  }

  public static final class DriveConstants {

    // Drive PID Constants
    // FIXME: Use SysID to determine actual values
    public static final double DRIVE_KP = 0.1;
    public static final double DRIVE_KS = 0.0;
    public static final double DRIVE_KA = 0.0;
    public static final double DRIVE_KV = 0.0;

    /**
     * The left-to-right distance between the drivetrain wheels
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = 0.572;
    /**
     * The front-to-back distance between the drivetrain wheels.
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_WHEELBASE_METERS = 0.572;

    // Front left module
    public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 4;
    public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 3;
    public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 10;
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(216.65); 
    public static final double FRONT_LEFT_OFFSET_DEGREES = 216.65;
    // PID constants for Front Left Module
    public static final double FRONT_LEFT_MODULE_DRIVE_KP = 0.1; // FIXME: get from sysid values
    public static final double FRONT_LEFT_MODULE_DRIVE_KI = 0.0; // FIXME: get from sysid values
    public static final double FRONT_LEFT_MODULE_DRIVE_KD = 0.01; // FIXME: get from sysid values
    public static final double FRONT_LEFT_MODULE_DRIVE_KF = 0.0; // FIXME: get from sysid values
    public static final double FRONT_LEFT_MODULE_TURN_KP = 0.2; // FIXME: get from sysid values
    public static final double FRONT_LEFT_MODULE_TURN_KI = 0.0; // FIXME: get from sysid values
    public static final double FRONT_LEFT_MODULE_TURN_KD = 0.1; // FIXME: get from sysid values
    public static final double FRONT_LEFT_MODULE_TURN_KF = 0.0; // FIXME: get from sysid values

    // front right module
    public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 2;
    public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 21;
    public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 9;
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(80.42); 
    public static final double FRONT_RIGHT_OFFSET_DEGREES = 80.42;
    // PID constants for Front Right Module
    public static final double FRONT_RIGHT_MODULE_DRIVE_KP = 0.1; // FIXME: get from sysid values
    public static final double FRONT_RIGHT_MODULE_DRIVE_KI = 0.0; // FIXME: get from sysid values
    public static final double FRONT_RIGHT_MODULE_DRIVE_KD = 0.01; // FIXME: get from sysid values
    public static final double FRONT_RIGHT_MODULE_DRIVE_KF = 0.0; // FIXME: get from sysid values
    public static final double FRONT_RIGHT_MODULE_TURN_KP = 0.2; // FIXME: get from sysid values
    public static final double FRONT_RIGHT_MODULE_TURN_KI = 0.0; // FIXME: get from sysid values
    public static final double FRONT_RIGHT_MODULE_TURN_KD = 0.1; // FIXME: get from sysid values
    public static final double FRONT_RIGHT_MODULE_TURN_KF = 0.0; // FIXME: get from sysid values

    // back left module
    public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 8;
    public static final int BACK_LEFT_MODULE_STEER_MOTOR = 7;
    public static final int BACK_LEFT_MODULE_STEER_ENCODER = 12;
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(137.81); 
    public static final double BACK_LEFT_OFFSET_DEGREES = 137.81;
    // PID constants for Back Left Module
    public static final double BACK_LEFT_MODULE_DRIVE_KP = 0.1; // FIXME: get from sysid values
    public static final double BACK_LEFT_MODULE_DRIVE_KI = 0.0; // FIXME: get from sysid values
    public static final double BACK_LEFT_MODULE_DRIVE_KD = 0.01; // FIXME: get from sysid values
    public static final double BACK_LEFT_MODULE_DRIVE_KF = 0.0; // FIXME: get from sysid values
    public static final double BACK_LEFT_MODULE_TURN_KP = 0.2; // FIXME: get from sysid values
    public static final double BACK_LEFT_MODULE_TURN_KI = 0.0; // FIXME: get from sysid values
    public static final double BACK_LEFT_MODULE_TURN_KD = 0.1; // FIXME: get from sysid values
    public static final double BACK_LEFT_MODULE_TURN_KF = 0.0; // FIXME: get from sysid values

    // back right module
    public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 6;
    public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 5;
    public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 11;
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(272.55);
    public static final double BACK_RIGHT_OFFSET_DEGREES = 272.55;

    }
}
