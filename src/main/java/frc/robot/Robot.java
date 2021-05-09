// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Simple drive program for the TileRunner training chassis.
 */
public class Robot extends TimedRobot {
  // Declare motor controllers
  private final WPI_VictorSPX leftFront = new WPI_VictorSPX(1);
  private final WPI_VictorSPX leftRear = new WPI_VictorSPX(5);
  private final WPI_VictorSPX rightFront = new WPI_VictorSPX(0);
  private final WPI_VictorSPX rightRear = new WPI_VictorSPX(4);

  // Group motors together for each side
  private final SpeedControllerGroup leftVictors = new SpeedControllerGroup(leftFront, leftRear);
  private final SpeedControllerGroup rightVictors = new SpeedControllerGroup(rightFront, rightRear);

  // Declare drivetrain
  private final DifferentialDrive robotDrive = new DifferentialDrive(leftVictors, rightVictors);

  // Declare joystick
  private final Joystick driveStick = new Joystick(0);

  // Button ID for slow mode button
  private final int R1 = 6;

  private final double sensitivity = 0.5;

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    if (driveStick.getRawButton(R1)) { // Slow mode
      // robotDrive.arcadeDrive(-driveStick.getX() * sensitivity, driveStick.getY() * sensitivity);
      robotDrive.tankDrive(-driveStick.getY() * sensitivity, -driveStick.getRawAxis(3) * sensitivity);
    } else { // Normal drive
      // robotDrive.arcadeDrive(-driveStick.getX(), driveStick.getY());
      robotDrive.tankDrive(-driveStick.getY(), -driveStick.getRawAxis(3));
    }
  }
}
