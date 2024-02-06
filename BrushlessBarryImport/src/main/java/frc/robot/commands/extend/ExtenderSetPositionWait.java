package frc.robot.commands.extend;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ExtensionSubPID;


public class ExtenderSetPositionWait extends Command{
    // Required Subsystems
    private ExtensionSubPID m_extender;
    private double m_finalPosition;

    // Creation Function of the Class
    public ExtenderSetPositionWait(ExtensionSubPID ext){
        m_extender = ext;
        m_finalPosition = m_extender.getDesiredPosition();
        addRequirements(m_extender);
    }
    public ExtenderSetPositionWait(ExtensionSubPID ext, double position){
        m_extender = ext;
        m_finalPosition = position;
        addRequirements(m_extender);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_extender.setPosition(m_finalPosition);
    }

    // Called every time the scheduler runs while the command is scheduled.
    // Tells the extender motor to either move to it's angle or stablize
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    // Nothing is called here as it is covered already in the subsystem to stop the motor.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    // may backfire if extender due to voltage cannot reach within range
    @Override
    public boolean isFinished() {
        return m_extender.withinTolerance();
    }
}
