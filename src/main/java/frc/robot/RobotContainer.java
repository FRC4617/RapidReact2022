// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.AutoDriveShooter;
import frc.robot.commands.DriveClimber;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.DriveShooter;
import frc.robot.commands.DriveTime;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain k_drivetrain = new DriveTrain();
  private final Shooter k_shooter = new Shooter();
  private final Climber k_climber = new Climber();

  public static XboxController k_driver = new XboxController(0);
  public static XboxController k_operator = new XboxController(1);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    k_drivetrain.setDefaultCommand(new ArcadeDrive(k_drivetrain));
    k_shooter.setDefaultCommand(new DriveShooter(k_shooter));
    k_climber.setDefaultCommand(new DriveClimber(k_climber));
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new SequentialCommandGroup(
      new AutoDriveShooter(k_shooter, 1).withTimeout(1),
      new DriveTime(k_drivetrain, 0.5, 0).withTimeout(1.5)
    );
  }
}
