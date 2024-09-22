// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.commands.TeleopElevator;
import frc.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  // Controller
  private final Joystick weapons = new Joystick(Constants.OperatorConstants.weaponsControllerPort);

  // Free axis control
  private final int w_elevatorMovementAxis = XboxController.Axis.kLeftY.value;

  // Weapon buttons that I'm commenting out for now lol
  /*
  private final JoystickButton w_runElevatorPID = new JoystickButton(weapons, XboxController.Button.kA.value);
  private final JoystickButton w_rotateElevatorForward = new JoystickButton(weapons, XboxController.Button.kB.value);
  private final JoystickButton w_rotateElevatorBackward = new JoystickButton(weapons, XboxController.Button.kX.value);
  */

  private final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();

  public RobotContainer() {

    m_elevatorSubsystem.setDefaultCommand(
      new TeleopElevator(m_elevatorSubsystem, 
      () -> -weapons.getRawAxis(w_elevatorMovementAxis)
      )
    );

    configureBindings();
  }

  private void configureBindings() {
    /*
     w_runElevatorPID.onTrue(new PIDElevator(m_elevatorSubsystem, 1800).withTimeout(5));
     w_rotateElevatorPIDForward.onTrue(new PIDElevator(m_elevatorSubsytem, m_elevatorSubsystem.getElevatorMotorPosition() + 1800).withTimeout(5));
     w_rotateElevatorPIDForward.onTrue(new PIDElevator(m_elevatorSubsystem, m_elevatorSubsystem.getElevatorMotorPosition() - 1800).withTimeout(5));
     */
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
