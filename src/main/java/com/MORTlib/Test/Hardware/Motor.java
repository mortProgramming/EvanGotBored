package com.MORTlib.Test.Hardware;

import com.MORTlib.Test.Hardware.ctre.CTREMotor;
import com.MORTlib.Test.Hardware.rev.RevMotor;
import com.ctre.phoenix6.hardware.TalonFX;
import com.MORTlib.Test.Hardware.MotorTypeEnum;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Motor implements MotorIntf {

    public MotorTypeEnum motorType;
    public int ID;
    public CANSparkLowLevel.MotorType brushType;
    public boolean direction;

    public MotorIntf motor;
    
    public Motor(MotorTypeEnum motorType, int ID, boolean direction) {
        this.motorType = motorType;
        this.ID = ID;
        this.brushType = MotorType.kBrushless;
        this.direction = direction;

        switch (motorType) {
            case NEO:
                motor = new RevMotor(ID, brushType, direction);
                break;

            case NEO550:
                motor = new RevMotor(ID, brushType, direction);
                break;

            case FALCON:
                motor = new CTREMotor(ID, direction);
                break;

            case KRAKEN:
                motor = new CTREMotor(ID, direction);
                break;
        }
    }
    
    public Motor(MotorTypeEnum motorType, int ID, CANSparkLowLevel.MotorType brushType, boolean direction) {
        this.motorType = motorType;
        this.ID = ID;
        this.brushType = brushType;
        this.direction = direction;

        switch (motorType) {
            case NEO:
                motor = new RevMotor(ID, brushType, direction);
                break;

            case NEO550:
                motor = new RevMotor(ID, brushType, direction);
                break;

            case FALCON:
                motor = new CTREMotor(ID, direction);
                break;

            case KRAKEN:
                motor = new CTREMotor(ID, direction);
                break;
        }
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