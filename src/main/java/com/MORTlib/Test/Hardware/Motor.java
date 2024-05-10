package com.MORTlib.Test.Hardware;

import com.MORTlib.Test.Hardware.ctre.Krakenx60Motor;
import com.MORTlib.Test.Hardware.ctre.Falcon500Motor;
import com.MORTlib.Test.Hardware.rev.NEOMotor;
import com.MORTlib.Test.Hardware.rev.NEO550Motor;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkLowLevel.MotorType;

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
                this.motor = new NEO550Motor(ID, brushType);
                break;

            case NEO550:
                this.motor = new NEOMotor(ID, brushType);
                break;

            case FALCON:
                this.motor = new Falcon500Motor(ID);
                break;

            case KRAKEN:
                this.motor = new Krakenx60Motor(ID);
                break;
        }
    }

    public void setCurrentLimit(double limit) {
        this.motor.setCurrentLimit(limit);
    }

    public void setDirection(boolean direction) {
        this.motor.setDirection(direction);
    }

    public void setPIDValues(double kP, double kI, double kD) {
        this.motor.setPIDValues(kP, kI, kD);
    }

    public void setPercent(double percent) {
        this.motor.setPercent(percent);
    }

    public void setVoltage(double voltage) {
        this.motor.setVoltage(voltage);
    }

    public void setPositionD(double position, double setpoint) {
        this.motor.setPositionD(position, setpoint);
    }

    public double getPositionD() {
        return this.motor.getPositionD();
    }

    public double getPosition() {
        return this.motor.getPosition();
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