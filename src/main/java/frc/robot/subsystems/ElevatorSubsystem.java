package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;


public class ElevatorSubsystem extends SubsystemBase {
    private final CANSparkMax motor1; // Evan: Don't use number form when writing a variable name; use "motorOne"


  public ElevatorSubsystem() {
    motor1 = new CANSparkMax(Constants.ElevatorConstants.ElevatorMotorID, MotorType.kBrushless);
    /* Evan:
     * Importing a specific subclass means you can just pull a variable from that subclass using SUBCLASS.variable
     * ^ This shortens how much you have to write when using a constantme math
        */

    new Thread(() -> {
            try {
                Thread.sleep(250);
                motor1.setInverted(Constants.ElevatorConstants.ElevatorMotorInvert); // Evan: Might need to be changed after testing
                motor1.getEncoder().setPosition(Constants.ElevatorConstants.ElevatorMotorStartPosition);

              } catch (Exception e) {}
        }).start();
  
  }

  public void setElevatorMotorSpeed(double speed, boolean ignoreBoundsRequirements) {
    // Evan: This very much resembles the Jukebox.java code I had. There is no need for ignoreBoundsRequirements in this caseents) {
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


    // Wait Evan i'm confused. idle mode is an object? how do I use it...
    // It's a little bit of an odd one; don't worry too much about it for now
    // Ok, wait, I think maybe there is some desync. It *appears* that you are breaking the code heavily on my side, but maybe that is just a me issue
    // WDYM breaking? I just rewrote ".set(0);"
    // In my perspective I see "motor1.set(0);set(0);"
    // Evan: I will probably leave for now, given how I might be unintentionally breaking things
    // Lukas: ok. I probably need to go to bed haha.

    // Evan: Feel free to try using brake mode in the following line. I never decided to do it just in case...
  }

  public double getElevatorMotorPosition(){
    double motor1Position = motor1.getEncoder().getPosition(); // getPosition() returns the number of rotations of the motor so it should be a double.
    return Units.rotationsToDegrees(motor1Position); // you went with degrees so I'll go with it but I want to know why
  }
 
  public boolean checkElevatorMovementsAreValid(double speed) {
    if (getElevatorMotorPosition() < Constants.ElevatorConstants.ElevatorMotorMinPosition) { // Evan: Since you just want to use Constants, the use of ElevatorConstants.variable need to be corrected
            if (speed > 0) {
                return true;
            } else {
                return false;
            }
        } else if (getElevatorMotorPosition() > Constants.ElevatorConstants.ElevatorMotorMaxPosition) {
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
