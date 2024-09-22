package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Constants.ElevatorConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;


public class ElevatorSubsystem extends SubsystemBase {
    private final CANSparkMax motor1;


  public ElevatorSubsystem() {
    motor1 = new CANSparkMax(Constants.ElevatorConstants.ElevatorMotorID, MotorType.kBrushless);

    new Thread(() -> {
            try {
                Thread.sleep(250);
                motor1.setInverted(Constants.ElevatorConstants.ElevatorMotorInvert);
                motor1.getEncoder().setPosition(Constants.ElevatorConstants.ElevatorMotorStartPosition);

              } catch (Exception e) {}
        }).start();
  
  }

  public void setElevatorMotorSpeed(double speed, boolean ignoreBoundsRequirements) {
    if (checkElevatorMotorInvertsAreCorrect()) {
      if (checkElevatorMovementsAreValid(speed) || ignoreBoundsRequirements) {
        motor1.set(speed);
      } else {
        brakeElevatorMotors();
      }
    } else {
        brakeElevatorMotors();
        System.out.println("[setElevatorMotorSpeeds] WARNING: Elevator motor inverts are INCORRECT!");
    }
  }

  public void brakeElevatorMotors(){
    motor1.set(0);
  }

  public double getElevatorMotorPosition(){
    double motor1Position = motor1.getEncoder().getPosition(); // getPosition() returns the number of rotations of the motor so it should be a double.
    return Units.rotationsToDegrees(motor1Position); // you went with degrees so I'll go with it but I want to know why
  }
 
  public boolean checkElevatorMovementsAreValid(double speed) {
    if (getElevatorMotorPosition() < ElevatorConstants.ElevatorMotorMinPosition) {
            if (speed > 0) {
                return true;
            } else {
                return false;
            }
        } else if (getElevatorMotorPosition() > ElevatorConstants.ElevatorMotorMaxPosition) {
            if (speed < 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
  }
  
  public boolean checkElevatorMotorInvertsAreCorrect(){
    if (motor1.getInverted() != Constants.ElevatorConstants.ElevatorMotorInvert) {
      return false;
    } else {
      return true;
    }
  }


  @Override
  public void periodic() {
    // SmartDashboard.putNumber("Left Elevator Position: ", getLeftElevatorMotorPosition());
    // SmartDashboard.putNumber("Right Elevator Position: ", getRightElevatorMotorPosition())
    // System.out.println("Motor Inverts (L/R): " + leftElevatorMotor.getInverted() + ", " + rightElevatorMotor.getInverted());
  }

  @Override
  public void simulationPeriodic() {
  }
}
