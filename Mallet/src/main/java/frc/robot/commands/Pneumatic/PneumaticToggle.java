package frc.robot.commands.Pneumatic;

import frc.robot.subsystems.PneumaticSub;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class PneumaticToggle extends CommandBase {
    private final PneumaticSub pneumaticSub;

    public PneumaticToggle(PneumaticSub pneumaticSub) {
        this.pneumaticSub = pneumaticSub;
        addRequirements(pneumaticSub);
    }

    @Override
    public void initialize() {
        pneumaticSub.toggle();
    }

    @Override
    public void execute() {
        pneumaticSub.toggle();
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return true;
    }
}