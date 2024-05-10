package com.MORTlib.Test.Swerve;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;

import com.MORTlib.Test.Hardware.ctre.CTREUtility.Falcon500;
import com.MORTlib.Test.Hardware.ctre.CTREUtility.Krakenx60;
import com.MORTlib.Test.Hardware.rev.RevUtility.NEO;
import com.MORTlib.Test.Hardware.rev.RevUtility.NEO550;
import com.MORTlib.Test.Hardware.Motor;
import com.MORTlib.Test.Hardware.PIDMotor;
import com.MORTlib.Test.Hardware.MotorIntf;
import com.MORTlib.Test.Hardware.MotorTypeEnum;
import com.MORTlib.Test.Hardware.Encoder;
import com.MORTlib.Test.Hardware.EncoderIntf;
import com.MORTlib.Test.Hardware.EncoderTypeEnum;
import static com.MORTlib.Test.Swerve.Constants.*;

public class SwerveModule {

    public int driveMotorID;
    public int steerMotorID;
    public int encoderID;

    public MotorTypeEnum driveMotorType;
    public MotorTypeEnum steerMotorType;
    public EncoderTypeEnum encoderType;
    public ModuleTypeEnum moduleType;

    public MotorIntf driveMotor;
    public MotorIntf steerMotor;
    public EncoderIntf encoder;

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

        this.driveMotor = new Motor(driveMotorType, driveMotorID);
        this.steerMotor = new PIDMotor(steerMotorType, steerMotorID);
        this.encoder = new Encoder(encoderType, encoderID);

        this.maxSpeed = Math.PI / 60;
        this.maxVoltage = 12;
        this.offset = 0;
        this.rotationToMeters = Math.PI;

        switch(driveMotorType) {
            case NEO:
                this.maxSpeed = this.maxSpeed * NEO.MAX_RPM;
                break;
            case NEO550:
                this.maxSpeed = this.maxSpeed * NEO550.MAX_RPM;
                break;
            case FALCON:
                this.maxSpeed = this.maxSpeed * Falcon500.MAX_RPM;
                break;
            case KRAKEN:
                this.maxSpeed = this.maxSpeed * Krakenx60.MAX_RPM;
                break;
        }

        switch (steerMotorType) {
            case NEO:
                this.steerMotor.setPIDValues(
                    NEO.SWERVE_STEER_KP, 
                    NEO.SWERVE_STEER_KI, 
                    NEO.SWERVE_STEER_KD
                );
                break;
            case NEO550:
                this.steerMotor.setPIDValues(
                    NEO550.SWERVE_STEER_KP, 
                    NEO550.SWERVE_STEER_KI, 
                    NEO550.SWERVE_STEER_KD
                );
                break;
            case FALCON:
                this.steerMotor.setPIDValues(
                    Falcon500.SWERVE_STEER_KP, 
                    Falcon500.SWERVE_STEER_KI, 
                    Falcon500.SWERVE_STEER_KD
                );
                break;
            case KRAKEN:
                this.steerMotor.setPIDValues(
                    Krakenx60.SWERVE_STEER_KP, 
                    Krakenx60.SWERVE_STEER_KI, 
                    Krakenx60.SWERVE_STEER_KD
                );
                break;
        }

        switch (moduleType) {
            case MK4i:
                this.maxSpeed = this.maxSpeed * MK4i.WHEEL_DIAMETER * MK4i.DRIVE_REDUCTION;
                this.rotationToMeters = this.rotationToMeters * MK4i.WHEEL_DIAMETER * MK4i.DRIVE_REDUCTION;
        }
    }

    public void setCurrentLimits(double limit) {
        this.driveMotor.setCurrentLimit(limit);
        this.steerMotor.setCurrentLimit(limit);
    }

    public void setPosition(Rotation2d setpoint) {
        this.setPositionD(setpoint.getDegrees());
    }

    public void setPositionD(double setpoint) {
        this.steerMotor.setPositionD((this.encoder.getPositionD() - offset), setpoint);
    }

    public void setDrivePercent(double percent) {
        this.driveMotor.setPercent(percent);
    }

    public void setDriveVoltage(double voltage) {
        this.driveMotor.setVoltage(voltage);
    }

    public void setDriveSpeedMeters(double speedMeters) {
        this.driveMotor.setVoltage((speedMeters / this.maxSpeed) * this.maxVoltage);
    }

    public void setModuleState(SwerveModuleState state) {
        this.state = state;

        setDriveSpeedMeters(state.speedMetersPerSecond);
        setPosition(state.angle);
    }

    public void setMaxVoltage(double maxVoltage) {
        this.maxVoltage = maxVoltage;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }



    public double getEncoderPositionD() {
        return this.encoder.getPositionD() - offset;
    }

    public double getDrivePosition() {
        return this.driveMotor.getPosition();
    }

    public double getDriveVelocityD() {
        return this.driveMotor.getVelocityD();
    }

    public double getMaxSpeed() {
        return this.maxSpeed;
    }

    public double getMaxVoltage() {
        return this.maxVoltage;
    }

    public SwerveModuleState getModuleState() {
        return this.state;
    }

    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(
            (this.driveMotor.getPosition() * this.rotationToMeters), 
            Rotation2d.fromDegrees(this.getEncoderPositionD())
        );
    }



    public MotorIntf getDriveMotor() {
        return this.driveMotor;
    }
    
    public MotorIntf getSteerMotor() {
        return this.steerMotor;
    }

    public EncoderIntf getEncoder() {
        return this.encoder;
    }



    public MotorTypeEnum getDriveMotorType() {
        return this.driveMotorType;
    }
    
    public MotorTypeEnum getSteerMotorType() {
        return this.steerMotorType;
    }

    public EncoderTypeEnum getEncoderType() {
        return this.encoderType;
    }

    public ModuleTypeEnum getModuleType() {
        return this.moduleType;
    }

}