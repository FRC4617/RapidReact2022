// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  CANSparkMax climberMotor = new CANSparkMax(Constants.CLIMBER_MOTOR_ID, MotorType.kBrushless);
  public Climber() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void setMotorSpeed(double speed){
    climberMotor.set(speed);
  }

  public void stop() {
    climberMotor.stopMotor();
  }
}