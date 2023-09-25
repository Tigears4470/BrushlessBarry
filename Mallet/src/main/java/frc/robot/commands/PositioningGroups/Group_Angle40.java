package frc.robot.commands.PositioningGroups;
import frc.robot.subsystems.ExtensionSub;
import frc.robot.subsystems.PivotSubPID;
import frc.robot.commands.pivot.PivotMoveToAngleWait;
import frc.robot.commands.extend.ExtenderSetPositionWaitForComplete;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Group_Angle40 extends SequentialCommandGroup {

    // ext -6.181
    // pivot 37.228
    //Variables
    public Group_Angle40(ExtensionSub extend, PivotSubPID pivot){
        //Adding a drivetrain
        //Adding Order of commands
        if (pivot.getEncoder().getPosition() < 50) {
            addCommands(
                new PivotMoveToAngleWait(pivot, 40),
                new ExtenderSetPositionWaitForComplete(extend, -8)
            );
        }
        else {
            addCommands(
                new ExtenderSetPositionWaitForComplete(extend, 0),
                new PivotMoveToAngleWait(pivot, 40),
                new ExtenderSetPositionWaitForComplete(extend, -8)
            );
        }
    }
}