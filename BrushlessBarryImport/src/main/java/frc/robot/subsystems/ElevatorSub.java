package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.K_ElevatorSub;

public class ElevatorSub extends SubsystemBase {
  // Idle - Break
  // ID - 7
  private final CANSparkMax motor1;
  private final RelativeEncoder encoder1;
  private final CANSparkMax motor2;
  private final RelativeEncoder encoder2;

  // private final DigitalInput limitSwitch;

  private double direction;
  // controls speed of motor

  public ElevatorSub() {
    if (K_ElevatorSub.isUsingElevator) {
      motor1 = new CANSparkMax(K_ElevatorSub.MOTOR_1_CAN, MotorType.kBrushless);
      encoder1 = motor1.getEncoder();
      motor1.setIdleMode(IdleMode.kBrake);

      motor2 = new CANSparkMax(K_ElevatorSub.MOTOR_2_CAN, MotorType.kBrushless);
      encoder2 = motor2.getEncoder();
      motor2.setIdleMode(IdleMode.kBrake);

      motor2.follow(motor1, K_ElevatorSub.followerInverted);

      // limitSwitch = new DigitalInput(0);

      direction = 0;

      motor1.setInverted(true);
      // set original position which should represent original position
      encoder1.setPosition(0);

      // control intake speed
      motor1.setSmartCurrentLimit(30, 100);
    } else {
      motor1 = null;
      encoder1 = null;
      // limitSwitch = null;
    }
  }

  // Return the encoder
  public RelativeEncoder getClawEncoder() {
    return encoder1;
  }

  // Return if the intake is grabbing or throwing or neither
  public double getDirection() {
    if (K_ElevatorSub.isUsingElevator) {
      return direction;
    }
    return 0;
  }

  // -1, 0, or 1
  public void setDirection(double newDirection) {
    direction = Math.signum(newDirection);
  }

  // returns current through motor
  public double getCurrent() {
    if (K_ElevatorSub.isUsingElevator) {
      return motor1.getOutputCurrent();
    }
    return 0.0;
  }

  // Stops the motor in case of emergency
  public void emergencyStop() {
    motor1.stopMotor();
  }

  @Override
  public void periodic() {
    if (K_ElevatorSub.isUsingElevator) {
      motor1.setVoltage(direction * K_ElevatorSub.voltage);

      SmartDashboard.putNumber("Claw Encoder", encoder1.getPosition());
      SmartDashboard.putNumber("Claw Direction", direction);
    }
  }
}
