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
  private final CANSparkMax leftMainMotor;
  private final CANSparkMax leftFollowerMotor;
  private final CANSparkMax rightMainMotor;
  private final CANSparkMax rightFollowerMotor;

  public DriveTrain() {
    leftMainMotor = new CANSparkMax(Constants.LEFT_MAIN_MOTOR_ID, MotorType.kBrushless);
    rightMainMotor = new CANSparkMax(Constants.RIGHT_MAIN_MOTOR_ID, MotorType.kBrushless);

    leftFollowerMotor = new CANSparkMax(Constants.LEFT_FOLLOWER_MOTOR_ID, MotorType.kBrushless);
    leftFollowerMotor.follow(leftMainMotor);

    rightFollowerMotor = new CANSparkMax(Constants.RIGHT_FOLLOWER_MOTOR_ID, MotorType.kBrushless);
    rightFollowerMotor.follow(rightMainMotor);

    drive = new DifferentialDrive(leftMainMotor, rightMainMotor);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Left Side Main Current", leftMainMotor.getOutputCurrent());
    SmartDashboard.putNumber("Left Side Follower Current", leftFollowerMotor.getOutputCurrent());
    SmartDashboard.putNumber("Right Side Main Current", rightMainMotor.getOutputCurrent());
    SmartDashboard.putNumber("Right Side Follower Current", rightFollowerMotor.getOutputCurrent());
  }

  public void arcadeDrive(double xSpeed, double zRotation, boolean isQuickTurn) {
    drive.curvatureDrive(xSpeed, zRotation, isQuickTurn);
    drive.feed();
  }

  public void stop() {
    drive.stopMotor();
  }

}
