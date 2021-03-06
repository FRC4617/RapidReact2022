// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DriveDistance extends PIDCommand {

  private final DriveTrain drivetrain;

  public DriveDistance(DriveTrain subsystem, double distance) {    
    super(
        // The controller that the command will use
        new PIDController(0.5, 0, 0),
        // This should return the measurement
        () -> (subsystem.leftDistance() + subsystem.rightDistance()) / 2,
        // This should return the setpoint (can also be a constant)
        () -> distance,
        // This uses the output
        output -> {
          SmartDashboard.putNumber("PID Output", output);
          subsystem.arcadeDrive(0, output, false);
        });

    this.drivetrain = subsystem;
    addRequirements(this.drivetrain);
    this.drivetrain.resetEncoders();

    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  @Override
  public void initialize() {
    this.drivetrain.resetEncoders();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (getController().atSetpoint()) return true;
    return false;
  }
}
