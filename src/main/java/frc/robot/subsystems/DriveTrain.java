// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

  private final DifferentialDrive drive;

  public DriveTrain() {
    CANSparkMax leftMainMotor = new CANSparkMax(Constants.LEFT_MAIN_MOTOR_ID, MotorType.kBrushless);
    CANSparkMax rightMainMotor = new CANSparkMax(Constants.RIGHT_MAIN_MOTOR_ID, MotorType.kBrushless);

    CANSparkMax leftFollowerMotor = new CANSparkMax(Constants.LEFT_FOLLOWER_MOTOR_ID, MotorType.kBrushless);
    leftFollowerMotor.follow(leftMainMotor);

    leftMainMotor.setInverted(true);
    leftFollowerMotor.setInverted(true);

    CANSparkMax rightFollowerMotor = new CANSparkMax(Constants.RIGHT_FOLLOWER_MOTOR_ID, MotorType.kBrushless);
    rightFollowerMotor.follow(rightMainMotor);

    drive = new DifferentialDrive(leftMainMotor, rightMainMotor);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(double xSpeed, double zRotation, boolean isQuickTurn) {
    drive.curvatureDrive(xSpeed, zRotation, isQuickTurn);
    drive.feed();
  }

  public void stop() {
    drive.stopMotor();
  }

}
