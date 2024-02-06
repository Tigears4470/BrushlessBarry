package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Breakbeam extends SubsystemBase {
  /** Creates a new Breakbeam. */
  DigitalInput breakbeam;
  public Breakbeam() {
    breakbeam = new DigitalInput(1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Breakbeam Receiver", breakbeam.get());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
