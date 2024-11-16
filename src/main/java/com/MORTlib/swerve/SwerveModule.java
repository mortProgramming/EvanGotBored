package com.MORTlib.swerve;

import static com.MORTlib.swerve.Constants.*;

import com.MORTlib.hardware.brands.ctre.CTREUtility.Falcon500;
import com.MORTlib.hardware.brands.ctre.CTREUtility.Krakenx60;
import com.MORTlib.hardware.brands.rev.RevUtility.NEO;
import com.MORTlib.hardware.brands.rev.RevUtility.NEO550;
import com.MORTlib.hardware.encoder.Encoder;
import com.MORTlib.hardware.motor.Motor;
import com.MORTlib.hardware.motor.PIDMotor;
import com.MORTlib.hardware.encoder.EncoderTypeEnum;
import com.MORTlib.hardware.motor.MotorTypeEnum;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class SwerveModule {

    public int driveMotorID;
    public int steerMotorID;
    public int encoderID;

    public MotorTypeEnum driveMotorType;
    public MotorTypeEnum steerMotorType;
    public EncoderTypeEnum encoderType;
    public ModuleTypeEnum moduleType;

    public Motor driveMotor;
    public PIDMotor steerMotor;
    public Encoder encoder;

    public double maxSpeed;
    public double maxVoltage;
    public double rotationToMeters;

    public double offset;

    public SwerveModuleState state;

    public SwerveModule(MotorTypeEnum driveMotorType, int driveMotorID, 
            MotorTypeEnum steerMotorType, int steerMotorID, 
            EncoderTypeEnum encoderType, int encoderID,
            ModuleTypeEnum moduleType
        ) {
        this.driveMotorID = driveMotorID;
        this.driveMotorID = driveMotorID;
        this.encoderID = encoderID;

        this.driveMotorType = driveMotorType;
        this.steerMotorType = steerMotorType;
        this.encoderType = encoderType;
        this.moduleType = moduleType;

        driveMotor = new Motor(driveMotorType, driveMotorID);
        steerMotor = new PIDMotor(steerMotorType, steerMotorID);
        encoder = new Encoder(encoderType, encoderID);

        maxSpeed = Math.PI / 60;
        maxVoltage = 12;
        offset = 0;
        rotationToMeters = Math.PI;
        state = new SwerveModuleState(
            0, Rotation2d.fromDegrees(0)
        );

        switch(driveMotorType) {
            case NEO:
                maxSpeed = maxSpeed * NEO.MAX_RPM;
                break;
            case NEO550:
                maxSpeed = maxSpeed * NEO550.MAX_RPM;
                break;
            case FALCON:
                maxSpeed = maxSpeed * Falcon500.MAX_RPM;
                break;
            case KRAKEN:
                maxSpeed = maxSpeed * Krakenx60.MAX_RPM;
                break;
        }

        switch (steerMotorType) {
            case NEO:
                steerMotor.setPIDValues(
                    NEO.SWERVE_STEER_KP, 
                    NEO.SWERVE_STEER_KI, 
                    NEO.SWERVE_STEER_KD
                );
                break;
            case NEO550:
                steerMotor.setPIDValues(
                    NEO550.SWERVE_STEER_KP, 
                    NEO550.SWERVE_STEER_KI, 
                    NEO550.SWERVE_STEER_KD
                );
                break;
            case FALCON:
                steerMotor.setPIDValues(
                    Falcon500.SWERVE_STEER_KP, 
                    Falcon500.SWERVE_STEER_KI, 
                    Falcon500.SWERVE_STEER_KD
                );
                break;
            case KRAKEN:
                steerMotor.setPIDValues(
                    Krakenx60.SWERVE_STEER_KP, 
                    Krakenx60.SWERVE_STEER_KI, 
                    Krakenx60.SWERVE_STEER_KD
                );
                break;
        }

        steerMotor.setPIDEnableContinuousInput(0, 1);
        steerMotor.setPIDTolerance(0.5, 10);

        switch (moduleType) {
            case CUSTOM:
                System.out.println("DON'T FORGET TO CONFIGURE MODULE DIMENTIONS");
            case MK4_L1:
                maxSpeed = maxSpeed * MK4_L1.WHEEL_DIAMETER * MK4_L1.DRIVE_REDUCTION;
                rotationToMeters = rotationToMeters * MK4_L1.WHEEL_DIAMETER * MK4_L1.DRIVE_REDUCTION;
            case MK4_L2:
                maxSpeed = maxSpeed * MK4_L2.WHEEL_DIAMETER * MK4_L2.DRIVE_REDUCTION;
                rotationToMeters = rotationToMeters * MK4_L2.WHEEL_DIAMETER * MK4_L2.DRIVE_REDUCTION;
            case MK4_L3:
                maxSpeed = maxSpeed * MK4_L3.WHEEL_DIAMETER * MK4_L3.DRIVE_REDUCTION;
                rotationToMeters = rotationToMeters * MK4_L3.WHEEL_DIAMETER * MK4_L3.DRIVE_REDUCTION;
            case MK4_L4:
                maxSpeed = maxSpeed * MK4_L4.WHEEL_DIAMETER * MK4_L4.DRIVE_REDUCTION;
                rotationToMeters = rotationToMeters * MK4_L4.WHEEL_DIAMETER * MK4_L4.DRIVE_REDUCTION;
            case MK4i_L1:
                maxSpeed = maxSpeed * MK4i_L1.WHEEL_DIAMETER * MK4i_L1.DRIVE_REDUCTION;
                rotationToMeters = rotationToMeters * MK4i_L1.WHEEL_DIAMETER * MK4i_L1.DRIVE_REDUCTION;
            case MK4i_L2:
                maxSpeed = maxSpeed * MK4i_L2.WHEEL_DIAMETER * MK4i_L2.DRIVE_REDUCTION;
                rotationToMeters = rotationToMeters * MK4i_L2.WHEEL_DIAMETER * MK4i_L2.DRIVE_REDUCTION;
            case MK4i_L3:
                maxSpeed = maxSpeed * MK4i_L3.WHEEL_DIAMETER * MK4i_L3.DRIVE_REDUCTION;
                rotationToMeters = rotationToMeters * MK4i_L3.WHEEL_DIAMETER * MK4i_L3.DRIVE_REDUCTION;
        }
    }

    public void setCurrentLimits(double limit) {
        driveMotor.setCurrentLimit(limit);
        steerMotor.setCurrentLimit(limit);
    }

    public void setPosition(Rotation2d setpoint) {
        steerMotor.setPositionRotations(getEncoderPosition().getRotations(), setpoint.getRotations());
    }

    public void setDrivePercent(double percent) {
        driveMotor.setPercent(percent);
    }

    public void setDriveVoltage(double voltage) {
        driveMotor.setVoltage(voltage);
    }

    public void setDriveSpeedMeters(double speedMeters) {
        driveMotor.setVoltage((speedMeters / maxSpeed) * maxVoltage);
    }

    public void setModuleState(SwerveModuleState state) {
        this.state = SwerveModuleState.optimize(state, getEncoderPosition());

        setDriveSpeedMeters(this.state.speedMetersPerSecond);
        setPosition(this.state.angle);
    }

    public void setMaxVoltage(double maxVoltage) {
        this.maxVoltage = maxVoltage;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }

    public void setModuelDimensions(double wheelDiameter, double driveReduction) {
        maxSpeed = maxSpeed * wheelDiameter * driveReduction;
        rotationToMeters = rotationToMeters * wheelDiameter * driveReduction;
    }



    public Rotation2d getEncoderPosition() {
        return Rotation2d.fromDegrees(encoder.getPosition().getDegrees() - offset);
    }

    public double getDrivePositionRotations() {
        return driveMotor.getPositionRotations();
    }

    public double getDriveVelocityRPM() {
        return driveMotor.getVelocityRPM();
    }

    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(
            (driveMotor.getPositionRotations() * rotationToMeters), 
            getEncoderPosition()
        );
    }



    public MotorTypeEnum getDriveMotorType() {
        return driveMotorType;
    }
    
    public MotorTypeEnum getSteerMotorType() {
        return steerMotorType;
    }

    public EncoderTypeEnum getEncoderType() {
        return encoderType;
    }

    public ModuleTypeEnum getModuleType() {
        return moduleType;
    }
}
