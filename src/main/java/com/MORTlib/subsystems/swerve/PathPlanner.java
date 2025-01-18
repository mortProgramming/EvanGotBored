package com.MORTlib.subsystems.swerve;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.MORTlib.subsystems.swerve.swervedrives.OdometeredSwerveDrive;
import com.MORTlib.subsystems.swerve.swervedrives.SwerveDrive;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class PathPlanner {

    public static void configure (
            Subsystem drivetrain, OdometeredSwerveDrive swerveDrive, 
            PIDConstants translationalConstants, PIDConstants rotationalConstants, 
            double drivetrainRadii, double totalReplanningThresholdMeters, double spikeReplanningThresholdMeters
        ) {

        AutoBuilder.configureHolonomic(
            () -> swerveDrive.getPosition(),  //get current robot position on the field
            (Pose2d startPose) -> swerveDrive.resetPosition(startPose), //reset odometry to a given pose. WILL ONLY RUN IF AUTON HAS A SET POSE, DOES NOTHING OTHERWISE. 
            () -> swerveDrive.velocity, //get the current ROBOT RELATIVE SPEEDS
            (ChassisSpeeds robotRelativeOutput) -> swerveDrive.setVelocity(robotRelativeOutput), //makes the robot move given ROBOT RELATIVE CHASSISSPEEDS
            new HolonomicPathFollowerConfig(
                translationalConstants, //position PID
                rotationalConstants, //rotation PID
                swerveDrive.frontLeftModule.maxSpeed, //max Module Speed in M/s
                drivetrainRadii, //todo: Find this number
                new ReplanningConfig(true, true, 
                    totalReplanningThresholdMeters, spikeReplanningThresholdMeters
                )
            ), 
            () -> (DriverStation.getAlliance().isPresent() ? DriverStation.getAlliance().get() == Alliance.Red : false), //method for checking current alliance. Path flips if alliance is red
            drivetrain
        );
    }

    public static void configure (
            Subsystem drivetrain, OdometeredSwerveDrive swerveDrive, 
            PIDConstants translationalConstants, PIDConstants rotationalConstants, 
            double drivetrainRadii
        ) {

        AutoBuilder.configureHolonomic(
            () -> swerveDrive.getPosition(),  //get current robot position on the field
            (Pose2d startPose) -> swerveDrive.resetPosition(startPose), //reset odometry to a given pose. WILL ONLY RUN IF AUTON HAS A SET POSE, DOES NOTHING OTHERWISE. 
            () -> swerveDrive.velocity, //get the current ROBOT RELATIVE SPEEDS
            (ChassisSpeeds robotRelativeOutput) -> swerveDrive.setVelocity(robotRelativeOutput), //makes the robot move given ROBOT RELATIVE CHASSISSPEEDS
            new HolonomicPathFollowerConfig(
                translationalConstants, //position PID
                rotationalConstants, //rotation PID
                swerveDrive.frontLeftModule.maxSpeed, //max Module Speed in M/s
                drivetrainRadii, //todo: Find this number
                new ReplanningConfig()
            ), 
            () -> (DriverStation.getAlliance().isPresent() ? DriverStation.getAlliance().get() == Alliance.Red : false), //method for checking current alliance. Path flips if alliance is red
            drivetrain
        );
    }



    public static void configure (
            Subsystem drivetrain, SwerveDrive swerveDrive, 
            Supplier<Pose2d> getPos, Consumer<Pose2d> resetPos, 
            PIDConstants translationalConstants, PIDConstants rotationalConstants, 
            double drivetrainRadii, double totalReplanningThresholdMeters, double spikeReplanningThresholdMeters
        ) {

        AutoBuilder.configureHolonomic(
            getPos,  //get current robot position on the field
            resetPos, //reset odometry to a given pose. WILL ONLY RUN IF AUTON HAS A SET POSE, DOES NOTHING OTHERWISE. 
            () -> swerveDrive.velocity, //get the current ROBOT RELATIVE SPEEDS
            (ChassisSpeeds robotRelativeOutput) -> swerveDrive.setVelocity(robotRelativeOutput), //makes the robot move given ROBOT RELATIVE CHASSISSPEEDS
            new HolonomicPathFollowerConfig(
                translationalConstants, //position PID
                rotationalConstants, //rotation PID
                swerveDrive.frontLeftModule.maxSpeed, //max Module Speed in M/s
                drivetrainRadii, //todo: Find this number
                new ReplanningConfig(true, true, 
                    totalReplanningThresholdMeters, spikeReplanningThresholdMeters
                )
            ), 
            () -> (DriverStation.getAlliance().isPresent() ? DriverStation.getAlliance().get() == Alliance.Red : false), //method for checking current alliance. Path flips if alliance is red
            drivetrain
        );
    }

    public static void configure (
            Subsystem drivetrain, SwerveDrive swerveDrive, 
            Supplier<Pose2d> getPos, Consumer<Pose2d> resetPos, 
            PIDConstants translationalConstants, PIDConstants rotationalConstants, 
            double drivetrainRadii
        ) {

        AutoBuilder.configureHolonomic(
            getPos,  //get current robot position on the field
            resetPos, //reset odometry to a given pose. WILL ONLY RUN IF AUTON HAS A SET POSE, DOES NOTHING OTHERWISE. 
            () -> swerveDrive.velocity, //get the current ROBOT RELATIVE SPEEDS
            (ChassisSpeeds robotRelativeOutput) -> swerveDrive.setVelocity(robotRelativeOutput), //makes the robot move given ROBOT RELATIVE CHASSISSPEEDS
            new HolonomicPathFollowerConfig(
                translationalConstants, //position PID
                rotationalConstants, //rotation PID
                swerveDrive.frontLeftModule.maxSpeed, //max Module Speed in M/s
                drivetrainRadii, //todo: Find this number
                new ReplanningConfig()
            ), 
            () -> (DriverStation.getAlliance().isPresent() ? DriverStation.getAlliance().get() == Alliance.Red : false), //method for checking current alliance. Path flips if alliance is red
            drivetrain
        );
    }
}
