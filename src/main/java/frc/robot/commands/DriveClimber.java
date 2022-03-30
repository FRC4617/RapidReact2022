// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climber;

enum ClimberState {
  IDLE,
  MOVING
}

public class DriveClimber extends CommandBase {

  private final Climber climber;
  private boolean driveable = true;
  private boolean lastLimitSwitchVal = true;
  private ClimberState state = ClimberState.IDLE;

  /** Creates a new DriveShooter. */
  public DriveClimber(Climber subsytem) {
    climber = subsytem;

    addRequirements(subsytem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveable = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch (state) {
      case IDLE:
        if (RobotContainer.k_operator.getBButton()) state = ClimberState.MOVING;
        else climber.setMotorSpeed(-0.05);
        break;
      case MOVING:
        if (driveable) {
          climber.setMotorSpeed(RobotContainer.k_operator.getRawAxis(2));
          if (climber.getLimitSwitch() && !lastLimitSwitchVal) driveable = false;
        }
        else {
          climber.setMotorSpeed(0);
        }
    }
    lastLimitSwitchVal = climber.getLimitSwitch();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
