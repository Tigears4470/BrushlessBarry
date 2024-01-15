package frc.robot.subsystems;

import frc.robot.Constants.K_PneumaticSub;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PneumaticSub extends SubsystemBase {
    private final Compressor compressor;
    private final DoubleSolenoid doubleSolenoid;

    public PneumaticSub() {
        if(K_PneumaticSub.isUsingPneumatic){
            compressor = new Compressor(K_PneumaticSub.moduleType);
            doubleSolenoid = new DoubleSolenoid(K_PneumaticSub.moduleType, K_PneumaticSub.forwardChannel, K_PneumaticSub.reverseChannel);
            //Initializes Default Position for Double Solenoids ONLY
            doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
        }else{
            compressor = null;
            doubleSolenoid = null;
        }
    }

    public void moveForward(){
        doubleSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void moveReverse(){
        doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void toggle(){
        doubleSolenoid.toggle();
    }

    public void stop(){
        doubleSolenoid.set(DoubleSolenoid.Value.kOff);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
