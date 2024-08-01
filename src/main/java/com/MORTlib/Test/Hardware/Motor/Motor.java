package com.MORTlib.Test.Hardware.Motor;

import com.MORTlib.Test.Hardware.Brands.CTRE.TalonFXMotor;
import com.MORTlib.Test.Hardware.Brands.REV.CANSparkMaxMotor;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.geometry.Rotation2d;

public class Motor implements MotorIntf {

    public MotorTypeEnum motorType;
    public int ID;
    public CANSparkLowLevel.MotorType brushType;
    public boolean direction;

    public MotorIntf motor;
    
    public Motor(MotorTypeEnum motorType, int ID) {
        this(motorType, ID, MotorType.kBrushless);
    }
    
    public Motor(MotorTypeEnum motorType, int ID, CANSparkLowLevel.MotorType brushType) {
        this.motorType = motorType;
        this.ID = ID;
        this.brushType = brushType;

        switch (motorType) {
            case NEO:
                motor = new CANSparkMaxMotor(ID, brushType);
                break;

            case NEO550:
                motor = new CANSparkMaxMotor(ID, brushType);
                break;

            case FALCON:
                motor = new TalonFXMotor(ID);
                break;

            case KRAKEN:
                motor = new TalonFXMotor(ID);
                break;
        }
    }

    public void setCurrentLimit(double limit) {
        motor.setCurrentLimit(limit);
    }

    public void setDirection(boolean direction) {
        motor.setDirection(direction);
    }

    public void setPIDValues(double kP, double kI, double kD) {
        motor.setPIDValues(kP, kI, kD);
    }

    public void setPercent(double percent) {
        motor.setPercent(percent);
    }

    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);
    }

    public void setPositionRotations(double setpoint) {
        motor.setPositionRotations(setpoint);
    }

    public void setCanivore(String canivore) {
        motor.setCanivore(canivore);
    }


    
    public double getPositionRotations() {
        return motor.getPositionRotations();
    }

    public double getVelocityRPM() {
        return motor.getVelocityRPM();
    }

    public MotorTypeEnum getMotorType() {
        return motorType;
    }

}