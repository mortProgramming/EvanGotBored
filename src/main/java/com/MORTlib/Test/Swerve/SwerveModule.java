package com.MORTlib.Test.Swerve;

import edu.wpi.first.math.controller.PIDController;

import com.MORTlib.Test.Hardware.ctre.CTREUtility;
import com.MORTlib.Test.Hardware.rev.RevUtility;

import com.MORTlib.Test.Hardware.Motor;
import com.MORTlib.Test.Hardware.MotorIntf;
import com.MORTlib.Test.Hardware.MotorTypeEnum;
import com.MORTlib.Test.Hardware.Encoder;
import com.MORTlib.Test.Hardware.EncoderIntf;
import com.MORTlib.Test.Hardware.EncoderTypeEnum;

public class SwerveModule {

    public int driveMotorID;
    public int steerMotorID;
    public int encoderID;

    public MotorTypeEnum driveMotorType;
    public MotorTypeEnum steerMotorType;
    public EncoderTypeEnum encoderType;

    public MotorIntf driveMotor;
    public MotorIntf steerMotor;
    public EncoderIntf encoder;

    public SwerveModule(int driveMotorID, MotorTypeEnum driveMotorType, 
            int steerMotorID, MotorTypeEnum steerMotorType, 
            int encoderID, EncoderTypeEnum encoderType
        ) {
        this.driveMotorID = driveMotorID;
        this.driveMotorID = driveMotorID;
        this.encoderID = encoderID;

        this.driveMotorType = driveMotorType;
        this.steerMotorType = steerMotorType;
        this.encoderType = encoderType;

        this.driveMotor = new DriveMotor(driveMotorType, driveMotorID);
        this.steerMotor = new SteerMotor(steerMotorType, steerMotorID);
        this.encoder = new PositionEncoder(encoderType, encoderID);
    }

    // public void setPositionD(double position, double setpoint) {
    //     this.motor.setVoltage(controllerD.calculate(position, setpoint));
    // }

    // public void setPercent(double percent) {
    //     this.motor.setPercent(percent);
    // }

    // public void setVoltage(double voltage) {
    //     this.motor.setVoltage(voltage);
    // }

    // public double getPositionD() {
    //     return this.motor.getPositionD();
    // }

    // public double getPosition1() {
    //     return this.motor.getPosition1();
    // }

    // public double getVelocityD() {
    //     return this.motor.getVelocityD();
    // }

    // public double getVelocity1() {
    //     return this.motor.getVelocity1();
    // }

    // public MotorIntf getMotor() {
    //     return this.motor;
    // }

    // public MotorTypeEnum getMotorType() {
    //     return this.motorType;
    // }

}