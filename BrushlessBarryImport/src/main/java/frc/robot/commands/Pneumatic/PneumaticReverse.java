package frc.robot.commands.Pneumatic;

import frc.robot.subsystems.PneumaticSub;
import edu.wpi.first.wpilibj2.command.Command;

public class PneumaticReverse extends Command {
    private final PneumaticSub pneumaticSub;

    public PneumaticReverse(PneumaticSub pneumaticSub) {
        this.pneumaticSub = pneumaticSub;
        addRequirements(pneumaticSub);
    }

    @Override
    public void initialize() {
        pneumaticSub.moveReverse();
    }

    @Override
    public void execute() {
        pneumaticSub.moveReverse();
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return true;
    }
}