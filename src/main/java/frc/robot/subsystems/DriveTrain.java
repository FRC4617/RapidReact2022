// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

  private final DifferentialDrive drive;

  private final CANSparkMax leftMainMotor = new CANSparkMax(Constants.LEFT_MAIN_MOTOR_ID, MotorType.kBrushless);
  private final CANSparkMax rightMainMotor = new CANSparkMax(Constants.RIGHT_MAIN_MOTOR_ID, MotorType.kBrushless);

  private final CANSparkMax leftFollowerMotor = new CANSparkMax(Constants.LEFT_FOLLOWER_MOTOR_ID, MotorType.kBrushless);
  private final CANSparkMax rightFollowerMotor = new CANSparkMax(Constants.RIGHT_FOLLOWER_MOTOR_ID, MotorType.kBrushless);

  // 42 counts per revolution
  // 10.71 ratio
  // 6 inch diameter

  public DriveTrain() {
    leftFollowerMotor.follow(leftMainMotor);
    rightFollowerMotor.follow(rightMainMotor);

    leftMainMotor.setInverted(true);
    leftFollowerMotor.setInverted(true);

    drive = new DifferentialDrive(leftMainMotor, rightMainMotor);

    //resetEncoders();
  }

  @Override
  public void periodic() {
    //SmartDashboard.putNumber("Left Distance", leftRotations());
    //SmartDashboard.putNumber("Right Distance", rightRotations());
  }

  public double leftRotations() {
    return leftMainMotor.getEncoder().getPosition() / 10.71 * -1;
  }

  public double rightRotations() {
    return rightMainMotor.getEncoder().getPosition() / 10.71 * -1;
  }

  public double leftDistance() {
    return (leftRotations()) * (Math.PI * 0.1524);
  }

  public double rightDistance() {
    return (rightRotations()) * (Math.PI * 0.1524);
  }

  public double getEncoderCount(double meters) {
    return (meters / (Math.PI * 0.1524)) * 10.71 * 42.0;
  }

  public void resetEncoders() {
    leftMainMotor.getEncoder().setPosition(0);
    rightMainMotor.getEncoder().setPosition(0);
  }

  public void arcadeDrive(double xSpeed, double zRotation, boolean isQuickTurn) {
    drive.curvatureDrive(xSpeed, zRotation, isQuickTurn);
    drive.feed();
  }

  public void stop() {
    drive.stopMotor();
  }

}
