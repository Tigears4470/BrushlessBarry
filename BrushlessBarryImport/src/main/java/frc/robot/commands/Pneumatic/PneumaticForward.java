package frc.robot.commands.Pneumatic;

import frc.robot.subsystems.PneumaticSub;
import edu.wpi.first.wpilibj2.command.Command;

public class PneumaticForward extends Command {
    private final PneumaticSub pneumaticSub;

    public PneumaticForward(PneumaticSub pneumaticSub) {
        this.pneumaticSub = pneumaticSub;
        addRequirements(pneumaticSub);
    }

    @Override
    public void initialize() {
        pneumaticSub.moveForward();
    }

    @Override
    public void execute() {
        pneumaticSub.moveForward();
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}