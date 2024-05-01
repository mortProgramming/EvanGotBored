package com.MORTlib.Test.Hardware.ctre;

import com.MORTlib.Test.Hardware.MotorIntf;

import com.ctre.phoenix6.hardware.TalonFX;

public class CANCoderEncoder implements EncoderIntf {

    public int ID;

    public CANcoder encoder;

    public CANCoderEncoder(int ID) {
        this.ID = ID;

        this.encoder = new CANcoder(ID);
    }

    public double getPositionD() {
        return this.encoder.getPosition().getValueAsDouble() * 360;
    }

    public double getPositionR() {
        return this.encoder.getPosition().getValueAsDouble();
    }

    public CANcoder getEncoder() {
        return this.encoder;
    }
 
}