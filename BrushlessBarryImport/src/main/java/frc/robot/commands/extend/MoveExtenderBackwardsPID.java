package frc.robot.commands.extend;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ExtensionSubPID;


public class MoveExtenderBackwardsPID extends Command{

    // Required Subsystem of Extension Motor
    private ExtensionSubPID m_extender;

    public MoveExtenderBackwardsPID(ExtensionSubPID extender){        
        m_extender = extender;
        addRequirements(m_extender);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    // Tells the Extension Motor to go Backwards
    @Override
    public void execute() {
        m_extender.changePosition(-.1);
    }

    // Called once the command ends or is interrupted.
    // Tells the Extension Motor to Stop
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
