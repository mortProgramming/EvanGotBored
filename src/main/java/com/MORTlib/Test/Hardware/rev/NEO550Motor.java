package com.MORTlib.Test.Hardware.rev;

import com.MORTlib.Test.Hardware.MotorIntf;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.SparkPIDController;

public class NEO550Motor implements MotorIntf {

    public int ID;
    public CANSparkLowLevel.MotorType brushType;

    public CANSparkMax motor;
    public SparkPIDController controller;

    // CANSparkLowLevel.MotorType.kBrushless
    public NEO550Motor(int ID, CANSparkLowLevel.MotorType brushType) {
        this.ID = ID;
        this.brushType = brushType;

        this.motor = new CANSparkMax(ID, brushType);
        this.controller = this.motor.getPIDController();

        controller.setP(0, 0);
        controller.setI(0, 0);
        controller.setD(0, 0);
    }

    public void setCurrentLimit(double limit) {
        this.motor.setSecondaryCurrentLimit(limit);
    }

    public void setDirection(boolean direction) {
        this.motor.setInverted(direction);
    }

    public void setPIDValues(double kP, double kI, double kD) {
        this.controller.setP(kP);
        this.controller.setI(kI);
        this.controller.setD(kD);
    }

    public void setPercent(double percent) {
        this.motor.set(percent);
    }

    public void setVoltage(double voltage) {
        this.motor.setVoltage(voltage);
    }

    public void setPositionD(double positon, double setpoint) {
        // do later
    }

    

    public double getPositionD() {
        return this.motor.getEncoder().getPosition() * 360;
    }

    public double getPosition() {
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