package frc.robot.commands.Pneumatic;

import frc.robot.subsystems.PneumaticSub;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class PneumaticStop extends CommandBase {
    private final PneumaticSub pneumaticSub;

    public PneumaticStop(PneumaticSub pneumaticSub) {
        this.pneumaticSub = pneumaticSub;
        addRequirements(pneumaticSub);
    }

    @Override
    public void initialize() {
        pneumaticSub.stop();
    }

    @Override
    public void execute() {
        pneumaticSub.stop();
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return true;
    }
}