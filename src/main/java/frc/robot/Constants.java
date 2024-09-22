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
    public static final int weaponsControllerPort = 0;
  }

  public static class ElevatorConstants {
    public static final int ElevatorMotorID = 1;

    public static final boolean ElevatorMotorInvert = true;

    public static final double ElevatorMotorOutput = 0.25;

    public static final double ElevatorMotorMaxPosition = 36000;
    public static final double ElevatorMotorMinPosition = -36000;

    public static final double ElevatorMotorStartPosition = 0;

    public static final double maxElevatorMotorVoltage = 6;

    /* The constants below are used in ElevatorCommand.java */
    public static final double elevatorStickDeadband = 0.1;

    /* The constants below are used in PIDElevator.java */
    public static final double elevatorP = 0.1;
    public static final double elevatorI = 0;
    public static final double elevatorD = 0;
  }

}
