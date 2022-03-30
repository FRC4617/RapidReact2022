// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
<<<<<<< HEAD:src/main/java/frc/robot/commands/DriveShooter.java
import frc.robot.RobotContainer;
import frc.robot.subsystems.Shooter;

public class DriveShooter extends CommandBase {

  private final Shooter shooter;

  public DriveShooter(Shooter subsystem) {
    this.shooter = subsystem;

=======
import frc.robot.subsystems.DriveTrain;

public class DriveTime extends CommandBase {
  private final DriveTrain drivetrain;
  private final double x, y;

  public DriveTime(DriveTrain subsystem, double y, double x) {
>>>>>>> main:src/main/java/frc/robot/commands/DriveTime.java
    addRequirements(subsystem);
    this.drivetrain = subsystem;
    this.y = y;
    this.x = x;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
<<<<<<< HEAD:src/main/java/frc/robot/commands/DriveShooter.java
    shooter.driveShooter(RobotContainer.k_driver.getRawAxis(3));
=======
    this.drivetrain.arcadeDrive(y, x, true);
>>>>>>> main:src/main/java/frc/robot/commands/DriveTime.java
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
<<<<<<< HEAD:src/main/java/frc/robot/commands/DriveShooter.java
    shooter.stop();
=======
    this.drivetrain.stop();
>>>>>>> main:src/main/java/frc/robot/commands/DriveTime.java
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
