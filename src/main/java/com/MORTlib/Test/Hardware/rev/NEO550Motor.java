package com.MORTlib.Test.Hardware.rev;

import com.MORTlib.Test.Hardware.MotorIntf;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.RelativeEncoder;

public class NEO550Motor implements MotorIntf {

    public int ID;
    public CANSparkLowLevel.MotorType brushType;
    public boolean direction;

    public CANSparkMax motor;

    // CANSparkLowLevel.MotorType.kBrushless
    public NEO550Motor(int ID, CANSparkLowLevel.MotorType brushType, boolean direction) {
        this.ID = ID;
        this.brushType = brushType;
        this.direction = direction;

        this.motor = new CANSparkMax(ID, brushType);
        this.motor.setInverted(direction);
    }

    public void setPercent(double percent) {
        this.motor.set(percent);
    }

    public void setVoltage(double voltage) {
        this.motor.setVoltage(voltage);
    }

    public double getPositionD() {
        return this.motor.getEncoder().getPosition() * 360;
    }

    public double getPosition1() {
        return this.motor.getEncoder().getPosition();
    }

    public double getVelocityD() {
        return this.motor.getEncoder().getVelocity() * 360;
    }

    public double getVelocity1() {
        return this.motor.getEncoder().getVelocity();
    }

    public CANSparkMax getMotor() {
        return this.motor;
    }
 
}