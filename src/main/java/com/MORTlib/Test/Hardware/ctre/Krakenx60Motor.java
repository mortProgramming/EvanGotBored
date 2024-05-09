package com.MORTlib.Test.Hardware.ctre;

import com.MORTlib.Test.Hardware.MotorIntf;

import com.ctre.phoenix6.hardware.TalonFX;

public class Krakenx60Motor implements MotorIntf {

    public int ID;

    public TalonFX motor;

    public Krakenx60Motor(int ID) {
        this.ID = ID;

        this.motor = new TalonFX(ID);
    }

    public void setDirection(boolean direction) {
        this.motor.setInverted(direction);
    }

    public void setPercent(double percent) {
        this.motor.set(percent);
    }

    public void setVoltage(double voltage) {
        this.motor.setVoltage(voltage);
    }

    public double getPositionD() {
        return this.motor.getPosition().getValueAsDouble() * 360;
    }

    public double getPosition1() {
        return this.motor.getPosition().getValueAsDouble();
    }

    public double getVelocityD() {
        return this.motor.getVelocity().getValueAsDouble() * 360;
    }

    public double getVelocity1() {
        return this.motor.getVelocity().getValueAsDouble();
    }

    public TalonFX getMotor() {
        return this.motor;
    }
 
}