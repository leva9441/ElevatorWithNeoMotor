package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.math.MathUtil;

import java.util.function.DoubleSupplier;


public class TeleopElevator extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ElevatorSubsystem m_subsystem;
  private DoubleSupplier speedSup;

  public TeleopElevator(ElevatorSubsystem subsystem, DoubleSupplier speedSup) {
    m_subsystem = subsystem;
    addRequirements(subsystem);

    this.speedSup = speedSup;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double currentSpeed = MathUtil.applyDeadband(speedSup.getAsDouble(), Constants.ElevatorConstants.elevatorStickDeadband);

    m_subsystem.setElevatorMotorSpeed(currentSpeed, false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.brakeElevatorMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
