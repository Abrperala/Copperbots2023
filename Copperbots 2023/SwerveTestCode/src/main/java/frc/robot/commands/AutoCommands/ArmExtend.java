package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class ArmExtend extends CommandBase {
  private final Arm m_arm;
  public ArmExtend(Arm arm) {
    this.m_arm = arm;
    addRequirements(m_arm);
  }
  public Boolean intakeToggled = false;

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    if (m_arm.getEncoderDistance() >= 90){
      m_arm.togglePiston();
       intakeToggled = true;
    }
   }
  


  @Override
  public boolean isFinished() {
    Boolean result = false;
    if (intakeToggled = true) {
      result = true;
    } 
    return result;
  }



  @Override
  public void end(boolean interrupted) {
  m_arm.setArmPosition(0);
  }
}
