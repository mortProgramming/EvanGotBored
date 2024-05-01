package com.MORTlib.Test.Hardware;

import com.MORTlib.Test.Hardware.ctre.Krakenx60Motor;
import com.MORTlib.Test.Hardware.ctre.Falcon500Motor;
import com.MORTlib.Test.Hardware.rev.NEOMotor;
import com.MORTlib.Test.Hardware.rev.NEO550Motor;
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
                this.motor = new NEOMotor(ID, brushType, direction);
                break;

            case NEO550:
                this.motor = new NEO550Motor(ID, brushType, direction);
                break;

            case FALCON:
                this.motor = new Falcon500Motor(ID, direction);
                break;

            case KRAKEN:
                this.motor = new Krakenx60Motor(ID, direction);
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
                this.motor = new NEO550Motor(ID, brushType, direction);
                break;

            case NEO550:
                this.motor = new NEOMotor(ID, brushType, direction);
                break;

            case FALCON:
                this.motor = new Falcon500Motor(ID, direction);
                break;

            case KRAKEN:
                this.motor = new Krakenx60Motor(ID, direction);
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