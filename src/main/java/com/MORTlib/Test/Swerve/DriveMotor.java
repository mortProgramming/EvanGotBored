package com.MORTlib.Test.Swerve;

import com.MORTlib.Test.Hardware.Motor;
import com.MORTlib.Test.Hardware.MotorIntf;
import com.MORTlib.Test.Hardware.MotorTypeEnum;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class DriveMotor implements MotorIntf {

    public MotorTypeEnum motorType;
    public int ID;
    public CANSparkLowLevel.MotorType brushType;

    public MotorIntf motor;
    
    public DriveMotor(MotorTypeEnum motorType, int ID) {
        this.motorType = motorType;
        this.ID = ID;
        this.brushType = MotorType.kBrushless;

        motor = new Motor(motorType, ID, false);
    }

    public void setPercent(double percent) {
        this.motor.setPercent(percent);
    }

    public void setVoltage(double voltage) {
        this.motor.setVoltage(voltage);
    }

    public double getPositionD() {
        return this.motor.getPositionD();
    }

    public double getPosition1() {
        return this.motor.getPosition1();
    }

    public double getVelocityD() {
        return this.motor.getVelocityD();
    }

    public double getVelocity1() {
        return this.motor.getVelocity1();
    }

    public MotorIntf getMotor() {
        return this.motor;
    }

    public MotorTypeEnum getMotorType() {
        return this.motorType;
    }

}