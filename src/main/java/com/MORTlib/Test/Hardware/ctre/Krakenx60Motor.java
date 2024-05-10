package com.MORTlib.Test.Hardware.ctre;

import com.MORTlib.Test.Hardware.MotorIntf;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

public class Krakenx60Motor implements MotorIntf {

    public int ID;

    public TalonFX motor;

    public TalonFXConfiguration config;

    public Krakenx60Motor(int ID) {
        this.ID = ID;

        this.motor = new TalonFX(ID);
        this.config = new TalonFXConfiguration();

        this.config.Slot0.kP = 0;
        this.config.Slot0.kI = 0;
        this.config.Slot0.kD = 0;

        motor.getConfigurator().apply(config);
    }

    public void setCurrentLimit(double limit) {
        this.config.CurrentLimits.SupplyCurrentLimit = limit;
        this.motor.getConfigurator().apply(config);
    }

    public void setDirection(boolean direction) {
        this.motor.setInverted(direction);
    }

    public void setPIDValues(double kP, double kI, double kD) {
        this.config.Slot0.kP = kP;
        this.config.Slot0.kI = kI;
        this.config.Slot0.kD = kD;
        this.motor.getConfigurator().apply(config);
    }

    public void setPercent(double percent) {
        this.motor.set(percent);
    }

    public void setVoltage(double voltage) {
        this.motor.setVoltage(voltage);
    }

    public void setPositionD(double position, double setpoint) {
        //do
    }



    public double getPositionD() {
        return this.motor.getPosition().getValueAsDouble() * 360;
    }

    public double getPosition() {
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