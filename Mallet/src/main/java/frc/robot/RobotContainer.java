package frc.robot;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.*;
import frc.robot.commands.Pneumatic.PneumaticToggle;
import frc.robot.commands.claw.IntakeEmergencyStop;
import frc.robot.commands.claw.IntakeGrab;
import frc.robot.commands.claw.IntakeGrabContinuous;
import frc.robot.commands.claw.IntakeStop;
import frc.robot.commands.claw.IntakeThrow;
import frc.robot.commands.claw.IntakeThrowContinuous;
import frc.robot.commands.extend.MoveExtenderBackwardsPID;
import frc.robot.commands.extend.MoveExtenderForwardPID;
import frc.robot.subsystems.*;
import java.util.HashMap;

public class RobotContainer {
  // INIT SUBSYSTEMS
  private static final Drivetrain m_drivetrain = new Drivetrain();
  private static final Limelight m_limelight = new Limelight();
  private static final GyroScope m_gyro = new GyroScope();
  private static final IntakeSub m_intake = new IntakeSub();
  private static final ClawSub m_clawMotor = new ClawSub();
  private static final ExtensionSubPID m_extensionMotor = new ExtensionSubPID();

  // INIT XBOX CONTROLLER
  public static CommandXboxController xboxController = new CommandXboxController(0);
  // INIT XBOX CONTROLLER BUTTONS
  public static HashMap<String, Trigger> xboxButtons = new HashMap<String, Trigger>();

  // SMARTDASHBOARD
  // private SendableChooser<String> m_autoChooser = new SendableChooser<String>();
  private SendableChooser<Command> m_autoChooser = new SendableChooser<Command>();

  // SHUFFLEBOARD
  private ShuffleboardTab main = Shuffleboard.getTab("Driver's Tab");
  // GYRO INFO
  private GenericEntry entry_GyroX = main.add("Pitch (Up/Down)", 0).withWidget(BuiltInWidgets.kGyro).getEntry();

  private GenericEntry entry_GyroZ = main.add("Yaw (Side to Side)", 0).withWidget(BuiltInWidgets.kGyro).getEntry();
  // LIMELIGHT INFO
  private GenericEntry entry_LimelightXOffset = main.add("LimelightXOffset", 0).withWidget(BuiltInWidgets.kTextView)
      .getEntry();
  private GenericEntry entry_LimelightYOffset = main.add("LimelightYOffset", 0).withWidget(BuiltInWidgets.kTextView)
      .getEntry();
  private GenericEntry entry_ClawClosed = main.add("Is Claw Closed", false).withWidget(BuiltInWidgets.kBooleanBox).getEntry();

  public RobotContainer() {
    configureButtonBindings();
    main.add("Limelight", "CameraServer", "http://10.44.70.11:5800");
    main.add("Webcam", "CameraServer", "http://wpilibpi.local/1181");
    String[] autoList = { "Place and Leave", "Place and Balance", "Leave and Balance", "Do Nothing" };
    SmartDashboard.putStringArray("Auto List", autoList);
    initializeAutoChooser();
  }

  // update shuffleboard layout
  public void updateShuffleboard() {
    // GYRO
    entry_GyroX.setDouble(m_gyro.getAngleX());
    // IN CASE
    entry_GyroZ.setDouble(m_gyro.getAngleZ());

    // //LIMELIGHT INFO
    entry_LimelightXOffset.setDouble(m_limelight.getXOffset());
    entry_LimelightYOffset.setDouble(m_limelight.getYOffset());
    entry_ClawClosed.setBoolean(m_clawMotor.getIsOpen());
  }

  public void initializeAutoChooser() {    
    // with command chooser
    m_autoChooser.setDefaultOption("Do Nothing", new WaitCommand(0));
    m_autoChooser.addOption("Leave ", new MoveDistance(m_drivetrain, 5, false));
    main.add("Auto Routine", m_autoChooser).withWidget(BuiltInWidgets.kComboBoxChooser);

  }

  // assign button functions
  private void configureButtonBindings() {
    //Init XBOX buttons
    if(Constants.K_ISUSINGDRIVETRAIN)
      m_drivetrain.setDefaultCommand(new ArcadeDrive(m_drivetrain, xboxController));

    if(Constants.K_IntakeSub.isUsingIntake){
      xboxController.x().onTrue(new IntakeStop(m_intake));
      xboxController.y().onTrue(new IntakeEmergencyStop(m_intake));

      xboxController.leftBumper().onTrue(new IntakeGrabContinuous(m_intake));
      xboxController.leftTrigger().onTrue(new IntakeThrowContinuous(m_intake));
      
      xboxController.rightBumper().whileTrue(new IntakeGrab(m_intake));
      xboxController.rightTrigger().whileTrue(new IntakeThrow(m_intake));
    }
  }

  public Command getAutoInput() {
    return m_autoChooser.getSelected();
  }

  public Command resetEncodersCommand() {
    return new ResetEncoders(m_drivetrain);
  }

  public static CommandXboxController getController() {
    return xboxController;
  }
}