// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climber;

public class DriveClimber extends CommandBase {

  private final Climber climber;
  private boolean driveable = true;

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
    //SmartDashboard.putBoolean("Limit Switch", climber.getLimitSwitch());

    if (RobotContainer.k_operator.getBButton()) {
      climber.setMotorSpeed(RobotContainer.k_operator.getRawAxis(2));
    }
    else {
      climber.setMotorSpeed(-0.05);
    }

    /*if (driveable == true && climber.getLimitSwitch() == true) {
      climber.setMotorSpeed(RobotContainer.k_operator.getRawAxis(2));
    }
    else if (driveable == true && climber.getLimitSwitch() == false) {
      driveable = false;
      climber.setMotorSpeed(RobotContainer.k_operator.getRawAxis(2));
    }
    else if (driveable == false && climber.getLimitSwitch() == true) {
      climber.setMotorSpeed(0);
    }*/
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
