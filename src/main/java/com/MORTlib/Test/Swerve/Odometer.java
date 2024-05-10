package com.MORTlib.Test.Swerve;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.math.numbers.N1;

public class Odometer {
    
    public SwerveDrivePoseEstimator swervePose;

    public double maxCamError;

    public Odometer(SwerveDriveKinematics kinematics, SwerveModulePosition[] modulePositions) {
        this(
            kinematics, Rotation2d.fromDegrees(0),
            modulePositions, new Pose2d()
        );
    }

    public Odometer(
            SwerveDriveKinematics kinematics, Rotation2d rotation, 
            SwerveModulePosition[] modulePositions
        ) {
        this(
            kinematics, rotation,
            modulePositions, new Pose2d()
        );
    }

    public Odometer(
            SwerveDriveKinematics kinematics, Rotation2d rotation, 
            SwerveModulePosition[] modulePositions, Pose2d position
        ) {
        this.swervePose = new SwerveDrivePoseEstimator(
            kinematics, rotation, 
            modulePositions, position
        );
    }
    
    public Odometer(
            SwerveDriveKinematics kinematics, Rotation2d rotation, 
            SwerveModulePosition[] modulePositions, Pose2d position,
            Matrix<N3, N1> posDeviation, Matrix<N3, N1> camDeviation
        ) {
        this.swervePose = new SwerveDrivePoseEstimator(
            kinematics, rotation, 
            modulePositions, position, 
            posDeviation, camDeviation
        );
    }

    public void resetPosition(Rotation2d angle, SwerveModulePosition[] modulePositions, Pose2d position) {
        this.swervePose.resetPosition(angle, modulePositions, position);
    }

    public void setMaxCamError(double error) {
        this.maxCamError = error;
    }

    public Pose2d getPosition() {
        return this.swervePose.getEstimatedPosition();
    }

    public void update(Rotation2d angle, SwerveModulePosition[] modulePositions) {
        this.swervePose.update(angle, modulePositions);
    }

    public void update(
            Rotation2d angle, SwerveModulePosition[] modulePositions, 
            Pose2d camPose, double timeStamp
        ) {
        this.swervePose.update(angle, modulePositions);

        if (
                Math.abs(this.getPosition().getX() - camPose.getX()) > this.maxCamError ||
                Math.abs(this.getPosition().getY() - camPose.getY()) > this.maxCamError
            ) {
            this.swervePose.addVisionMeasurement(camPose, timeStamp);
        }
    }
}
